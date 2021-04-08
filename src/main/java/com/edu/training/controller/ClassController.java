package com.edu.training.controller;

import java.util.List;
import java.util.Optional;

import com.edu.training.entities.Course;
import com.edu.training.models.PaginationRange;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.TraineeRepository;

import com.edu.training.services.core.CourseService;
import com.edu.training.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private CourseService courseService;

    /**
     * Handing the request representing classes infor
     * @param model
     * @param page is page number in paging
     * @param size  is the quantity of element in a page
     * @param field is the field that user want to sorted by
     * @return class-management.html
     */
    @GetMapping()
    public String displayCourseList(Model model, @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, @RequestParam("field") Optional<String> field) {

        int cPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String sortField = field.orElse("default");

        if (pageSize < 5) {
            pageSize = 5;
        }
        if (pageSize > 50) {
            pageSize = 50;
        }

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

        model.addAttribute("classPage", classPage);
        model.addAttribute("cPage", cPage);
        model.addAttribute("size", pageSize);
        model.addAttribute("field", sortField);


        PaginationRange p = Pagination.paginationByRange(cPage, classPage.getTotalElements(), pageSize, 5);
        model.addAttribute("paginationRange", p);

        return "pages/class-views/class-management";
    }

    /**
     * Handling request of seeing class detail infor 
     * @param model
     * @param classId is the id of class which user want to see detail
     * @param page is the page number when paging
     * @param view is the type of view in showing list trainees in a class, there is two view
     * @return class-details 
     */
    @GetMapping("/class-details")
    public String displayClassDetail(Model model, @RequestParam("id") int classId,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("view") Optional<String> view) {

        int cPage = page.orElse(1);
        int pageSize = 10;
        String modeView = view.orElse("list");

        Course course = courseRepository.findById(classId);
        model.addAttribute("class", course);

        List<TraineeScoreDto> listTrainees = traineeRepository.findScoreByTrainee(classId);

        List<TraineeScoreDto> trainees = Pagination.getPage(listTrainees, cPage);

        int totalPages = (int) Math.ceil( (double)listTrainees.size()/ (double) pageSize) ;


        model.addAttribute("modeView", modeView);
        model.addAttribute("classId", classId);
        model.addAttribute("trainees", trainees);
        model.addAttribute("cPage", cPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size",pageSize);
        model.addAttribute("totalElements",listTrainees.size());

        PaginationRange p = Pagination.paginationByRange(cPage, listTrainees.size(), pageSize, 5);
        model.addAttribute("paginationRange", p);

        return "pages/class-views/class-details";
    }

    /**
     * handling request when user change the number of page size
     * @param model 
     * @param page is page number in paging 
     * @param size is the quantity of element in a page
     * @return
     */
    @PostMapping()
    public String displayCourseListByPageSize(Model model, @RequestParam("page") Optional<Integer> page,
                                              @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Course> ClassPage = courseService.findPaginated(currentPage, pageSize, "name");
        List<Course> listCourses = ClassPage.getContent();

        model.addAttribute("classes", listCourses);

        return "pages/class-views/class-management";
    }

}
