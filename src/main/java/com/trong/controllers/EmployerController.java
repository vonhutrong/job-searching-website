package com.trong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nha-tuyen-dung")
public class EmployerController {
    @RequestMapping("/dang-tin")
    public String publicRecruitment() {
        return "employer/public_recruitment";
    }
}
