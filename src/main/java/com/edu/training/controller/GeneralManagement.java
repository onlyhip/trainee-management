package com.edu.training.controller;

import java.util.List;
import java.util.Optional;

import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.entities.Trainer;
import com.edu.training.models.PaginationRange;
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

    /**
     * Playing trainer list 
     * @param model
     * @return trainer-list view 
     */
    @GetMapping(value = {"/trainer-list",""})
    public String displayTrainerList(Model model, @RequestParam("page") Optional<Integer> page) {


        int cPage = page.orElse(1);
        int pageSize = 5;

        List<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);

        List<Trainer> trainersAfterPaging = Pagination.getPage(trainers, cPage, pageSize);
        int currIndex = trainers.indexOf(trainersAfterPaging.get(0));
        int totalPages = (int) Math.ceil( (double)trainers.size()/ (double) pageSize);
        int totalElements = trainers.size();

        model.addAttribute("trainers", trainersAfterPaging);
        model.addAttribute("cPage", cPage);
        model.addAttribute("currIndex", currIndex);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("size",pageSize);

        PaginationRange p = Pagination.paginationByRange(cPage, totalElements, pageSize, 5);
        model.addAttribute("paginationRange", p);

        return "pages/general-views/trainer-list";
    }

    /**
     * Display trainee list
     * @param model
     * @return trainee-list view
     */
    @GetMapping("/trainee-list")
    public String displayTraineeList(Model model, @RequestParam("page") Optional<Integer> page) {


        int cPage = page.orElse(1);
        int pageSize = 5;

        List<Trainee> trainees = traineeRepository.findAll();
        model.addAttribute("trainees", trainees);

        List<Trainee> traineesAfterPaging = Pagination.getPage(trainees, cPage, pageSize);
        int currIndex = trainees.indexOf(traineesAfterPaging.get(0));
        int totalPages = (int) Math.ceil( (double)trainees.size()/ (double) pageSize);
        int totalElements = trainees.size();
        

        model.addAttribute("trainees", traineesAfterPaging);
        model.addAttribute("cPage", cPage);
        model.addAttribute("currIndex", currIndex);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("size",pageSize);

        PaginationRange p = Pagination.paginationByRange(cPage, totalElements, pageSize, 5);
        model.addAttribute("paginationRange", p);

        return "pages/general-views/trainee-list";
    }

    /**
     * Display subject list
     * @param model
     * @param page is the page number in paging 
     * @return subject-list view 
     */
    @GetMapping("/subject-list")
    public String displaySubjectList(Model model, @RequestParam("page") Optional<Integer> page) {

        int cPage = page.orElse(1);
        int pageSize = 5;

        List<Course> courses = courseRepository.findAll();

        List<Course> coursesAfterPaging = Pagination.getPage(courses, cPage, pageSize);
        int currIndex = courses.indexOf(coursesAfterPaging.get(0));
        int totalPages = (int) Math.ceil( (double)courses.size()/ (double) pageSize);
        int totalElements = courses.size();

        model.addAttribute("courses", coursesAfterPaging);
        model.addAttribute("cPage", cPage);
        model.addAttribute("currIndex", currIndex);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("size",pageSize);

        PaginationRange p = Pagination.paginationByRange(cPage, totalElements, pageSize, 5);
        model.addAttribute("paginationRange", p);



        return "pages/general-views/subject-list";
    }

}
