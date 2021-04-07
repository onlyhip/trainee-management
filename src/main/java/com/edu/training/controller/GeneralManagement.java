package com.edu.training.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.entities.Trainer;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.ScoreRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.TrainerRepository;
import com.edu.training.repositories.TrainingObjectiveRepository;
import com.edu.training.utils.page.Pagination;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private TrainingObjectiveRepository toRepository;

    @GetMapping(value = {"/trainer-list", "/"})
    public String displayTrainerList(Model model) {

        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);

        return "trainer-list";
    }

    @GetMapping("/trainee-list")
    public String displayTraineeList(Model model) {

        List<Trainee> trainees = traineeRepository.findAll();
        model.addAttribute("trainees", trainees);

        return "trainee-list";
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

        return "subject-list";
    }

}
