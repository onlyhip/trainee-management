package com.edu.training.controller;

import java.util.List;
import java.util.Optional;

import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.utils.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/general-management/subject-list")
public class SubjectController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/subject-details")
    public String displaySubjectDetail(Model model, @RequestParam("id") int courseId, @RequestParam("page") Optional<Integer> page) {

        int cPage = page.orElse(1);
        int pageSize = 5;
        
        Course course = courseRepository.getOne(courseId);

        List<Trainee> trainees = course.getTrainee();

        List<Trainee> traineesAfterPaging = Pagination.getPage(trainees, cPage, pageSize);
        int currentIndex = trainees.indexOf(traineesAfterPaging.get(0));

        model.addAttribute("course", course);
        model.addAttribute("trainees", traineesAfterPaging);
        model.addAttribute("cPage", cPage);
        model.addAttribute("totalPages", (trainees.size() / (pageSize + 1)) + 1);
        model.addAttribute("currIndex", currentIndex);

        return "pages/general-views/subject-details";
    }
}
