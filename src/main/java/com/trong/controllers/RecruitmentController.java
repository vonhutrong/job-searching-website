package com.trong.controllers;

import com.trong.form.SearchForm;
import com.trong.model.ApplyHistory;
import com.trong.model.Recruitment;
import com.trong.service.ApplyHistoryService;
import com.trong.service.DepartmentService;
import com.trong.service.RecruitmentService;
import com.trong.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/tin-tuyen-dung")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final DepartmentService departmentService;
    private final ApplyHistoryService applyHistoryService;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService, DepartmentService departmentService, ApplyHistoryService applyHistoryService) {
        this.recruitmentService = recruitmentService;
        this.departmentService = departmentService;
        this.applyHistoryService = applyHistoryService;
    }

    @GetMapping("")
    public String viewWithPagination(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", new PageWrapper<Recruitment>(
                recruitmentService.recent(pageable),
                "/tin-tuyen-dung"));
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
    public String search(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        model.addAttribute("keyword", searchForm.getKeyword());
        model.addAttribute("recruitments", recruitmentService.searchBasic(searchForm.getKeyword(), searchForm.getDepartmentId()));
        return "employee/searching_result";
    }

    @RequestMapping("/ket-qua-tim-kiem")
    public String searchingResult() {
        return "employee/searching_result";
    }

    @GetMapping("/tai-xuong")
    public void downloadCv(@Param("applyHistoryId") Long applyHistoryId,
                            HttpServletResponse response) {
        try {
            ApplyHistory applyHistory = applyHistoryService.findById(applyHistoryId);
            InputStream is = new FileInputStream(new File(applyHistory.getCvPath()));
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
