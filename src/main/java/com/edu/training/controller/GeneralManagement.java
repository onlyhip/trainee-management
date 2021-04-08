package com.edu.training.controller;

import java.util.List;
import java.util.Optional;

import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.entities.Trainer;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.TrainerRepository;
import com.edu.training.utils.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/general-management")
public class GeneralManagement {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping(value = {"/trainer-list",""})
    public String displayTrainerList(Model model) {

        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);

        return "pages/general-views/trainer-list";
    }

    @GetMapping("/trainee-list")
    public String displayTraineeList(Model model) {

        List<Trainee> trainees = traineeRepository.findAll();
        model.addAttribute("trainees", trainees);

        return "pages/general-views/trainee-list";
    }

    @GetMapping("/subject-list")
    public String displaySubjectList(Model model, @RequestParam("page") Optional<Integer> page) {

        int cPage = page.orElse(1);
        int pageSize = 5;

        List<Course> courses = courseRepository.findAll();

        List<Course> coursesAfterPaging = Pagination.getPage(courses, cPage, pageSize);
        int currentIndex = courses.indexOf(coursesAfterPaging.get(0));

        model.addAttribute("courses", coursesAfterPaging);
        model.addAttribute("cPage", cPage);
        model.addAttribute("totalPages", (courses.size() / (pageSize + 1)) + 1);
        model.addAttribute("currIndex", currentIndex);

        return "pages/general-views/subject-list";
    }

}
