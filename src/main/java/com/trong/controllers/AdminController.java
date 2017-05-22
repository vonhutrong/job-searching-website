package com.trong.controllers;

import com.trong.model.Recruitment;
import com.trong.service.RecruitmentService;
import com.trong.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RecruitmentService recruitmentService;

    @Autowired
    public AdminController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @GetMapping("")
    public String index() {
        return "redirect:/admin/recruitmentReportManagement";
    }

    @GetMapping("/recruitmentReportManagement")
    public String manageRecruitmentReport(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Recruitment> page = recruitmentService.getTopReportedRecruitments(pageable);
        String url = "/admin/recruitmentReportManagement";
        model.addAttribute("page", new PageWrapper<Recruitment>(page, url));
        return "admin/recruitment_report_management";
    }
}
