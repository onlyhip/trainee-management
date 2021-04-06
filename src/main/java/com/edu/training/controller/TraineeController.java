package com.edu.training.controller;

import com.edu.training.dto.TOScoreDto;
import com.edu.training.entities.Attendance;
import com.edu.training.entities.Course;
import com.edu.training.entities.Trainee;
import com.edu.training.models.TraineeScoreDto;
import com.edu.training.repositories.AttendanceRepository;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.ScoreRepository;
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

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ScoreRepository scoreRepository;

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

        return "trainee-details";
    }
}
