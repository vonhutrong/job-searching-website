package com.trong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tin-tuyen-dung")
public class RecruitmentController {
    @RequestMapping("/chi-tiet")
    public String details() {
        return "employee/details";
    }

    @RequestMapping("/ket-qua-tim-kiem")
    public String searchingResult() {
        return "employee/searching_result";
    }
}
