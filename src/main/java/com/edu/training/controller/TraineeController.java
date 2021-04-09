package com.edu.training.controller;

import com.edu.training.dto.TOScoreDto;
import com.edu.training.entities.Attendance;
import com.edu.training.entities.Trainee;
import com.edu.training.models.PaginationRange;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.AttendanceRepository;
import com.edu.training.repositories.ScoreRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trainee-management")
public class TraineeController {
    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * Display List of all trainees
     * @param model
     * @param page is the page number of paging trainee list
     * @param size is the quantity of elements in a page
     * @param field is the name of attribute for sorted type
     * @return the trainee-list view
     */
    @GetMapping()
    public String displayTraineeManagement(Model model,
                                           @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size,
                                           @RequestParam("field") Optional<String> field) {

        int cPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String sortField = field.orElse("default");

        pageSize = pageSize < 5 ? 5 : pageSize > 50 ? 50 : pageSize;

        List<TraineeScoreDto> listTrainees = traineeRepository.findScoreByAllTrainee();
        List<TraineeScoreDto> trainees = Pagination.getPage(listTrainees, cPage, pageSize);

        int totalPages = (int) Math.ceil((double) listTrainees.size() / (double) pageSize);

        model.addAttribute("trainees", trainees);
        model.addAttribute("cPage", cPage);
        model.addAttribute("size", pageSize);
        model.addAttribute("totalElements", listTrainees.size());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("field", sortField);

        PaginationRange p = Pagination.paginationByRange(cPage, listTrainees.size(), pageSize, 5);
        model.addAttribute("paginationRange", p);

        return "pages/trainee-views/trainee-management";
    }

    /**
     * Display infor of trainee when user click on trainee list 
     * @param model
     * @param traineeId is the ID of trainee which was clicked on trainee-list by User
     * @return trainee infor view
     */
    @GetMapping("/trainee-details")
    public String displayAllTraineeDetails(Model model, @RequestParam("id") int traineeId){

        Trainee trainee = traineeRepository.getOne(traineeId);
        double finalScore = scoreRepository.findAvgScoreByTraineeId(traineeId);
        int presentAttendance = attendanceRepository.findPresentAttendanceByTraineeId(traineeId);
        int totalAttendance = attendanceRepository.findTotalAttendanceByTraineeId(traineeId);
        List<TOScoreDto> listNameAndScore = scoreRepository.findScoreEachTOByTraineeId(traineeId);
        List<Attendance> listDateAndAttendance = attendanceRepository.findAttendanceByTraineeId(traineeId);

        double scale = Math.pow(10, 1);

        model.addAttribute("trainee", trainee);
        model.addAttribute("finalScore", (int)(Math.round(finalScore * scale) / scale) * 10);
        model.addAttribute("presentAttendance", presentAttendance);
        model.addAttribute("totalAttendance", totalAttendance);
        model.addAttribute("listNameAndScore", listNameAndScore);
        model.addAttribute("listDateAndAttendance", listDateAndAttendance);

        return "pages/trainee-views/trainee-details";
    }
}
