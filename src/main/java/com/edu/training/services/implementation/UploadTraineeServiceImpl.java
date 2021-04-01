package com.edu.training.services.implementation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.edu.training.entities.Role;
import com.edu.training.entities.Trainee;
import com.edu.training.models.UploadEvent;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.RoleRepository;
import com.edu.training.repositories.StatusRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.services.core.UploadTraineeService;
import com.edu.training.utils.csv.DataRow;
import com.edu.training.utils.csv.DataTable;
import com.edu.training.utils.csv.ExcelTable;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * UploadTraineeService implementation
 * @author NguyenNK5
 */
public class UploadTraineeServiceImpl implements UploadTraineeService {

    private ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    private static final Logger logger = LoggerFactory.getLogger(UploadTraineeServiceImpl.class);

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Override
    public Map<String, Object> uploadCSV(@RequestParam("file") MultipartFile file, @RequestParam("token") String token) throws ServletException, IOException {

        logger.info("upload-excel invoked.");

        Map<String, Object> result = new HashMap<>();

        final String label = UUID.randomUUID().toString() + ".xlsx";
        final String filepath = "/Users/" + label;
        byte[] bytes = file.getBytes();
        File fh = new File("/Users/");
        if(!fh.exists()){
            fh.mkdir();
        }
        try {
            FileOutputStream writer = new FileOutputStream(filepath);
            writer.write(bytes);
            writer.close();

            logger.info("image bytes received: {}", bytes.length);

            executor.submit(() -> {
               try {
                   UploadEvent event = new UploadEvent();
                   event.setState("Uploaded filed received on server");
                   event.setEventType("start");
                   brokerMessagingTemplate.convertAndSend("/topics/event", JSON.toJSONString(event, SerializerFeature.BrowserCompatible));

                   final FileInputStream inputStream = new FileInputStream(filepath);
                   DataTable table = ExcelTable.load(() -> inputStream);

                   int rowCount = table.rowCount();

                   for (int i = 0; i < rowCount; i++) {
                       DataRow row = table.row(i);
                       Trainee trainee = new Trainee();

                       // TODO: some properties belong to One Trainee are still missing
                       // FIXME: Update fields to complete.
                       trainee.setId(Integer.parseInt(row.cell("id")));
                       trainee.setRoles(new HashSet<Role>((Collection<? extends Role>) roleRepository.findByName(row.cell("role"))));
                       trainee.setAccount(row.cell("account"));
                       trainee.setNational(row.cell("national"));
                       trainee.setName(row.cell("full name"));
                       trainee.setEmail(row.cell("email"));
                       trainee.setTelNumber(row.cell("phone"));
                       trainee.setFacebook(row.cell("facebook"));
                       trainee.setCourse(courseRepository.findCourseByName(row.cell("course")));
                       trainee.setBranch(row.cell("branch"));
                       trainee.setParentDepartment(row.cell("parent department"));
                       trainee.setRecInterviewDate(new Date(row.cell("record interview date")));
                       trainee.setRecInterviewStatus(row.cell("status"));
                       trainee.setNote(row.cell("note"));
                       trainee.setTraineeStatus(statusRepository.findStatusByType(row.cell("type")));
                       trainee.setUniversity(row.cell("university"));
                       trainee.setFaculty(row.cell("faculty"));

                       logger.info("Saving trainee: {}", trainee.getName());
                       traineeRepository.save(trainee);
                       event.setState(trainee);
                       event.setEventType("progress");
                       brokerMessagingTemplate.convertAndSend("/topics/event", JSON.toJSONString(event, SerializerFeature.BrowserCompatible));

                       Thread.sleep(5000L);
                   }

                   event = new UploadEvent();
                   event.setState("Uploaded filed deleted on server");
                   fh.delete();
                   event.setEventType("end");
                   brokerMessagingTemplate.convertAndSend("/topics/event", JSON.toJSONString(event, SerializerFeature.BrowserCompatible));
               } catch (Exception e) {
                   logger.error("Failed on saving trainee", e);
               }
            });

            result.put("success", true);
            result.put("id", label);
            result.put("error", "");

            return result;
        } catch (IOException ex) {
            logger.error("Failed to process the uploaded image", ex);
            result.put("success", false);
            result.put("id", "");
            result.put("error", ex.getMessage());
            return result;
        }
    }
}
