package com.edu.training.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.edu.training.dto.TraineeScoreDTO;
import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.TraineeRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/class-management")
public class ClassController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TraineeRepositoryImpl traineeRepositoryImpl;

    @Autowired
    private TraineeRepository traineeRepository;

    @GetMapping("/class-details")
    public String displayClassDetail(Model model, @RequestParam("id") int classId) {
      
        Course course = courseRepository.findById(classId);
        course.setCurrCount(traineeRepository.countCourseByCourseId(course.getId()));
        course.setStatus(
                Timestamp.valueOf(LocalDateTime.now()).compareTo(course.getEndDate()) > 0 ? "Done" : "In Process");
        model.addAttribute("class", course);


        List<TraineeScoreDto> trainees = traineeRepository.findScoreByTrainee(classId);

        model.addAttribute("trainees", trainees);

        return "class-details";
    }
}
