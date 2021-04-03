package com.edu.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trainee-management")
public class TraineeController {

    @GetMapping("/trainee-details")
    public String displayTraineeDetails() {
        
        return "trainee-details";
    }
}
