package com.edu.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download-templates")
public class TemplateController {

    @GetMapping()
    public String displayDownloadTemplates() {
        return "pages/template-views/download-templates";
    }

}
