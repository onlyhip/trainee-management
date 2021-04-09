package com.edu.training.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.edu.training.entities.Course;
import com.edu.training.entities.Fresher;
import com.edu.training.repositories.*;

import com.edu.training.utils.data.CreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private TrainingObjectiveRepository toRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * Handling Home request
     * @param model 
     * @return the reporting view of trainee and class 
     */
    @GetMapping(value = {"/", "home"})
    public String viewHomePage(Model model) {

        List<Course> listCourse = courseRepository.findAll();
        List<Fresher> listFresher = fresherRepository.findAll();
        int waitingCourse = 0;
        int releaseCourse = 0;
        int runningCourse = 0;
        int waitingFresher = 0;
        int releaseFresher = 0;
        int runningFresher = 0;
        for (Course c : listCourse) {

            if (c.getStatus().equals("Done"))
                releaseCourse++;
            else if (c.getStatus().equals("Waiting"))
                waitingCourse++;
            else
                runningCourse++;
        }

        for (Fresher f : listFresher) {
            if (Timestamp.valueOf(LocalDateTime.now()).compareTo(f.getTraineeStatus().getStartDay()) < 0)
                waitingFresher++;
            else if (Timestamp.valueOf(LocalDateTime.now()).compareTo(f.getTraineeStatus().getEndDate()) > 0)
                releaseFresher++;
            else
                runningFresher++;
        }

        model.addAttribute("totalCourse", listCourse.size());
        model.addAttribute("totalFresher", listFresher.size());
        model.addAttribute("wCourse", waitingCourse);
        model.addAttribute("rCourse", releaseCourse);
        model.addAttribute("rnCourse", runningCourse);
        model.addAttribute("wFresher", waitingFresher);
        model.addAttribute("rFresher", releaseFresher);
        model.addAttribute("rnFresher", runningFresher);

        return "/pages/index";
    }

    /**
     * Handling error request
     * @return the error view 
     */
    @GetMapping("/404")
    public String error() {
        return "/pages/util-views/404";
    }

    /**
     * Create demo data
     * @return create-database.html
     */
    @GetMapping("/create-data-first")
    public String createDataFirst() {
        CreateData createData = new CreateData();
        Random rand = new Random(System.currentTimeMillis());
        createData.createTrainer(trainerRepository);
        createData.createCourse(trainerRepository, courseRepository);
        createData.createStatus(statusRepository);
        createData.createFresher(courseRepository, statusRepository, fresherRepository);
        createData.createInternship(courseRepository, statusRepository, internshipRepository);
        createData.createTO(trainerRepository, toRepository, courseRepository);
        // createData.createScore(courseRepository,scoreRepository,toRepository, traineeRepository);
        for (Course c : courseRepository.findAll()) {
            c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
            c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate()) > 0 ? "Done"
                    : Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getOpenDate()) < 0 ? "Waiting" : "In Process");
            c.setDuration(rand.nextInt(50) + 1);
            courseRepository.save(c);
        }

        return "pages/util-views/create-database";
    }

    /**
     * Create Demo data
     * @return create-database.html
     */
    @GetMapping("/create-data-second")
    public String createDataSecond() {
        CreateData createData = new CreateData();
        createData.createScore(courseRepository, scoreRepository, toRepository, traineeRepository);
        createData.createAttendance(traineeRepository, attendanceRepository);

        scoreRepository.findAll().forEach(s -> {
            toRepository.getOne(s.getTrainingObjective().getId()).setName(s.getName());
            toRepository.save(toRepository.getOne(s.getTrainingObjective().getId()));
        });

        return "pages/util-views/create-database";
    }

}
