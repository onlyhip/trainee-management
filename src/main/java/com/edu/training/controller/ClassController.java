package com.edu.training.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.TraineeRepository;

import com.edu.training.services.core.TraineeService;
import com.edu.training.utils.page.Pagination;
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
    private TraineeRepository traineeRepository;

    @Autowired
    private TraineeService traineeService;

    @GetMapping("/class-details")
    public String displayClassDetail(Model model, @RequestParam("id") int classId,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("view") Optional<String> view) {

        int cPage = page.orElse(1);
        int pageSize = 10;
        String modeView = view.orElse("list");

        Course course = courseRepository.findById(classId);
        course.setCurrCount(traineeRepository.countCourseByCourseId(course.getId()));
        course.setStatus(
                Timestamp.valueOf(LocalDateTime.now()).compareTo(course.getEndDate()) > 0 ? "Done" : "In Process");
        model.addAttribute("class", course);


        List<TraineeScoreDto> listTrainees = traineeRepository.findScoreByTrainee(classId);


        List<TraineeScoreDto> trainees = Pagination.getPage(listTrainees, cPage);

        model.addAttribute("modeView",modeView);
        model.addAttribute("classId", classId);
        model.addAttribute("trainees", trainees);
        model.addAttribute("cPage", cPage);
        model.addAttribute("totalPages", (listTrainees.size() / (pageSize + 1)) + 1);


        return "class-details";
    }
}
