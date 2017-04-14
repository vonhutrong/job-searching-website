package com.trong.controllers;

import com.trong.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tin-tuyen-dung")
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("")
    private String view(Model model) {
        model.addAttribute("recentRecruitments", recruitmentService.recent());
        return "employee/home";
    }

    @RequestMapping("/chi-tiet")
    public String details() {
        return "employee/details";
    }

    @RequestMapping("/ket-qua-tim-kiem")
    public String searchingResult() {
        return "employee/searching_result";
    }
}
