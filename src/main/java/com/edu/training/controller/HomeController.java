package com.edu.training.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.Course;
import com.edu.training.entities.Fresher;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.*;
import com.edu.training.services.implementation.CourseServiceImpl;

import com.edu.training.utils.data.CreateData;
import com.edu.training.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassAdminRepository classAdminRepository;

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
    private CourseServiceImpl courseService;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private TrainingObjectiveRepository toRepository;


    @Autowired
    private ScoreRepository scoreRepository;


    @GetMapping("/")
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
            // c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
            
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

        return "index";
    }

    @RequestMapping(value = "/class-management", method = RequestMethod.GET)
    public String displayCourseList(Model model, @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, @RequestParam("field") Optional<String> field) {

        int cPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String sortField = field.orElse("default");

        Page<Course> classPage;

        if (sortField.equals("default")) {
            classPage = courseService.findPaginated(cPage, pageSize);
        } else {
            if (sortField.equals("head-teacher")) {
                classPage = courseService.findPaginated(cPage, pageSize, "trainer.name");
            } else {
                classPage = courseService.findPaginated(cPage, pageSize, sortField);
            }
        }

//        //List<Course> listCourses = classPage.getContent();
//        // listCourses.forEach(c ->
//        // c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId())));
//        for (Course c : listCourses) {
//            c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
//            c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate()) > 0 ? "Done" : "In Process");
//        }
        // listCourses.forEach(c ->
        // c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate())
        // > 0 ? "Done" : "In Process"));
        //  listCourses.forEach(c -> System.out.println(c));
        model.addAttribute("classPage", classPage);
        model.addAttribute("cPage", cPage);
        model.addAttribute("size", pageSize);
        model.addAttribute("field", sortField);


        return "class-management";
    }

    @RequestMapping(value = "/class-management", method = RequestMethod.POST)
    public String displayCourseListByPageSize(Model model, @RequestParam("page") Optional<Integer> page,
                                              @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Course> ClassPage = courseService.findPaginated(currentPage, pageSize, "name");
        List<Course> listCourses = ClassPage.getContent();

        model.addAttribute("classes", listCourses);

        return "class-management";
    }


    @GetMapping("/trainee-management")
    public String displayTraineeManagement(Model model,
                                           @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size,
                                           @RequestParam("field") Optional<String> field) {

        int cPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String sortField = field.orElse("default");


        List<TraineeScoreDto> listTrainees = traineeRepository.findScoreByAllTrainee();


        List<TraineeScoreDto> trainees = Pagination.getPage(listTrainees, cPage, pageSize);


        model.addAttribute("trainees", trainees);
        model.addAttribute("cPage", cPage);
        model.addAttribute("size", pageSize);
        model.addAttribute("totalPages", (listTrainees.size() / (pageSize + 1)) + 1);
        model.addAttribute("field", sortField);

        return "trainee-management";
    }

    @GetMapping("/download-templates")
    public String displayDownloadTemplates() {
        return "download-templates";
    }


    @GetMapping("/general-management")
    public String displayTrainerList() {
        return "trainer-list";
    }

    // @GetMapping("/page/{pageNo}")
    // public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
    // @RequestParam("sortField") String sortField,
    // @RequestParam("sortDir") String sortDir, Model model) {
    // int pageSize = 5;

    // Page<User> page = userService.findPaginated(pageNo, pageSize, sortField,
    // sortDir);
    // List<User> listUsers = page.getContent();

    // model.addAttribute("currentPage", pageNo);
    // model.addAttribute("totalPages", page.getTotalPages());
    // model.addAttribute("totalItems", page.getTotalElements());

    // model.addAttribute("sortField", sortField);
    // model.addAttribute("sortDir", sortDir);
    // model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

    // model.addAttribute("listUsers", listUsers);
    // return "index";
    // }

    @GetMapping("/404")
    public String error() {
        return "404";
    }

    public boolean checkOldPassword(String username, String oldPassword) {
        return userRepository.findPasswordByAccountClassAdmin(username).equals(oldPassword);
    }

    public ClassAdmin getLoginedAccount() {

        String loginedAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = classAdminRepository.findIdByAccount(loginedAccount);
        ClassAdmin loginedUser = classAdminRepository.getOne(id);
        return loginedUser;
    }


    @GetMapping("/create-data-first")
    public String createDataFirst(){
        CreateData createData = new CreateData();
        createData.createTrainer(trainerRepository);
        createData.createCourse(trainerRepository,courseRepository);
        createData.createStatus(statusRepository);
        createData.createFresher(courseRepository,statusRepository,fresherRepository);
        createData.createInternship(courseRepository,statusRepository,internshipRepository);
        createData.createTO(trainerRepository,toRepository, courseRepository);
        // createData.createScore(courseRepository,scoreRepository,toRepository, traineeRepository);
        for(Course c : courseRepository.findAll()) {
            c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
            c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate()) > 0 ? "Done"
                    : Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getOpenDate()) < 0 ? "Waiting" : "In Process");
            courseRepository.save(c);
        }
        return "create-database";
    }


    @GetMapping("/create-data-second")
    public String createDataSecond(){
        CreateData createData = new CreateData();
        createData.createScore(courseRepository,scoreRepository,toRepository, traineeRepository);
        createData.createAttendance(traineeRepository, attendanceRepository);
        return "create-database";
    }

}
