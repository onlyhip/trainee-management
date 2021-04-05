package com.edu.training.controller;

import com.edu.training.entities.Course;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trainee-management")
public class TraineeController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @GetMapping("/trainee-details")
    public String displayAllTraineeDetails(Model model){

        return "trainee-details";
    }
}
