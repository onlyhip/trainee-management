package com.edu.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general-management")
public class GeneralManagement {

    @GetMapping(value = { "/subject-list", "/" })
    public String displaySubjectList() {
        return "subject-list";
    }
}
