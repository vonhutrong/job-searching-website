package com.trong.controllers;

import com.trong.form.SearchForm;
import com.trong.service.DepartmentService;
import com.trong.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tin-tuyen-dung")
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
    private String view(Model model) {
        model.addAttribute("recruitments", recruitmentService.recent());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("searchForm", new SearchForm());
        return "employee/home";
    }

    @RequestMapping("/chi-tiet")
    public String details(@RequestParam("id") Long id, Model model) {
        model.addAttribute("recruitment", recruitmentService.findById(id));
        return "recruitment/details";
    }

    @GetMapping("/tim-kiem")
    private String search(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        model.addAttribute("keyword", searchForm.getKeyword());
        model.addAttribute("recruitments", recruitmentService.searchBasic(searchForm.getKeyword(), searchForm.getDepartmentId()));
        return "employee/searching_result";
    }

    @RequestMapping("/ket-qua-tim-kiem")
    public String searchingResult() {
        return "employee/searching_result";
    }
}
