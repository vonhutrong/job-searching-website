package com.trong.controllers;

import com.trong.form.AdvancedSearchForm;
import com.trong.form.SearchForm;
import com.trong.model.ApplyHistory;
import com.trong.model.Recruitment;
import com.trong.service.ApplyHistoryService;
import com.trong.service.DepartmentService;
import com.trong.service.RecruitmentService;
import com.trong.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;

@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
    private static final int BUFFER_SIZE = 4096;
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
        model.addAttribute("page", new PageWrapper<Recruitment>(recruitmentService.recent(pageable),"/recruitment"));
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("searchForm", new SearchForm());
        return "recruitment/home";
    }

    @RequestMapping("/details")
    public String details(@RequestParam("id") Long id, Model model) {
        model.addAttribute("recruitment", recruitmentService.findById(id));
        return "recruitment/details";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 5) Pageable pageable, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        model.addAttribute("keyword", searchForm.getKeyword());
        Page<Recruitment> recruitments = recruitmentService.searchBasic(searchForm.getKeyword(), searchForm.getDepartmentId(), pageable);
        String url = String.format("/recruitment/search?keyword=%s&departmentId=%s", searchForm.getKeyword(), searchForm.getDepartmentId());
        model.addAttribute("page", new PageWrapper<Recruitment>(recruitments, url));
        return "recruitment/searching_result";
    }

    @GetMapping("/advancedSearch")
    public String advancedSearch(Model model) {
        return advancedSearchPage(new AdvancedSearchForm(), model);
    }

    @PostMapping("/advancedSearch")
    public String advancedSearchProcessing(@ModelAttribute("advancedSearchForm") @Valid AdvancedSearchForm advancedSearchForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return advancedSearchPage(advancedSearchForm, model);
        }
        return "redirect:/";
    }

    private String advancedSearchPage(AdvancedSearchForm advancedSearchForm, Model model) {
        model.addAttribute("advancedSearchForm", advancedSearchForm);
        return "recruitment/advanced_search";
    }

    @GetMapping("/cv")
    public void downloadApplication(@Param("applyHistoryId") Long applyHistoryId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApplyHistory applyHistory = applyHistoryService.findById(applyHistoryId);
        String cvPath = applyHistory.getCvPath();
        File downloadFile = new File(cvPath);
        InputStream inputStream = new FileInputStream(downloadFile);

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(cvPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }
}
