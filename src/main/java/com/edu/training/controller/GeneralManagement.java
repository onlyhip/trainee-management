package com.edu.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general-management")
public class GeneralManagement {

    @GetMapping(value = { "/trainer-list", "/" })
    public String displayTrainerList() {
        return "trainer-list";
    }

    @GetMapping("/trainee-list")
    public String displayTraineeList() {
        return "trainee-list";
    }

    @GetMapping("/subject-list")
    public String displaySubjectList() {
        return "subject-list";
    }

}
