package com.edu.training.controller;

import java.util.List;

import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.entities.Trainer;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.TrainerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general-management")
public class GeneralManagement {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = { "/trainer-list", "/" })
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
    public String displaySubjectList(Model model) {

        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        return "subject-list";
    }

}
