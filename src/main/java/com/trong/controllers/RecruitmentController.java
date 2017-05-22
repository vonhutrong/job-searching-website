package com.trong.controllers;

import com.trong.form.AdvancedSearchForm;
import com.trong.form.RecruitmentReportForm;
import com.trong.form.SearchForm;
import com.trong.model.*;
import com.trong.service.*;
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
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
    private static final int BUFFER_SIZE = 4096;
    private final RecruitmentService recruitmentService;
    private final DepartmentService departmentService;
    private final ApplyHistoryService applyHistoryService;
    private final EducationalLevelService educationalLevelService;
    private final NotificationService notificationService;
    private final RecruitmentReportService recruitmentReportService;
    private final EmployeeService employeeService;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService, DepartmentService departmentService, ApplyHistoryService applyHistoryService, EducationalLevelService educationalLevelService, NotificationService notificationService, RecruitmentReportService recruitmentReportService, EmployeeService employeeService) {
        this.recruitmentService = recruitmentService;
        this.departmentService = departmentService;
        this.applyHistoryService = applyHistoryService;
        this.educationalLevelService = educationalLevelService;
        this.notificationService = notificationService;
        this.recruitmentReportService = recruitmentReportService;
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String viewWithPagination(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("page", new PageWrapper<Recruitment>(recruitmentService.recent(pageable),"/recruitment"));
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("searchForm", new SearchForm());
        return "recruitment/home";
    }

    @RequestMapping("/details")
    public String details(@RequestParam("id") Long id, Model model, Principal principal) {
        Recruitment recruitment = recruitmentService.findById(id);
        if (null == recruitment) {
            notificationService.addErrorMessage("Invalid.recruitmentId");
            return "error";
        }
        model.addAttribute("recruitment", recruitment);
        if (null != principal) {
            Employee employee = employeeService.findByEmail(principal.getName());
            model.addAttribute("isHasReported", recruitmentReportService.isHasReported(employee, recruitment));
        }
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

    @GetMapping("/advancedSearchProcessing")
    public String advancedSearchProcessing(@PageableDefault(size = 5) Pageable pageable, @ModelAttribute("advancedSearchForm") @Valid AdvancedSearchForm advancedSearchForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return advancedSearchPage(advancedSearchForm, model);
        }
        Page<Recruitment> recruitments = recruitmentService.search(advancedSearchForm, pageable);
        String url = generateAdvancedSearchUrl(advancedSearchForm);
        model.addAttribute("page", new PageWrapper<Recruitment>(recruitments, url));
        return "recruitment/searching_result";
    }

    @GetMapping("/report")
    public String reportRecruitment(@RequestParam("recruitmentId") Long recruitmentId, Model model) {
        model.addAttribute("recruitment", recruitmentService.findById(recruitmentId));
        model.addAttribute("recruitmentReportForm", new RecruitmentReportForm());
        return "recruitment/report";
    }

    @PostMapping("/report")
    public String report(@ModelAttribute("recruitmentReportForm")RecruitmentReportForm recruitmentReportForm, Principal principal) {
        Employee employee = employeeService.findByEmail(principal.getName());
        Recruitment recruitment = recruitmentService.findById(recruitmentReportForm.getRecruitmentId());
        RecruitmentReport recruitmentReport = new RecruitmentReport();
        recruitmentReport.setEmployee(employee);
        recruitmentReport.setRecruitment(recruitment);
        recruitmentReport.setMessage(recruitmentReportForm.getMessage());
        if (null != recruitmentReportService.save(recruitmentReport))
            notificationService.addInfoMessage("Success.reportRecruitment");
        else
            notificationService.addErrorMessage("Fail.reportRecruitment");
        return "redirect:/";
    }

    @GetMapping("/ban")
    public String ban(@RequestParam("id") Long id) {
        Recruitment recruitment = recruitmentService.findById(id);
        recruitment.setBanned(true);
        recruitmentService.save(recruitment);
        notificationService.addInfoMessage("Success.banRecruitment");
        return "redirect:/recruitment/details?id=" + id;
    }

    private String generateAdvancedSearchUrl(AdvancedSearchForm advancedSearchForm) {
        StringBuffer stringBuffer = new StringBuffer("/recruitment/advancedSearchProcessing?");
        stringBuffer.append("keyword=" + advancedSearchForm.getKeyword());
        if (advancedSearchForm.getDepartmentId() != null)
            stringBuffer.append("&departmentId=" + advancedSearchForm.getDepartmentId());
        if (advancedSearchForm.getRequiredFemale() != null)
            stringBuffer.append("&requiredFemale=" + advancedSearchForm.getRequiredFemale());
        if (advancedSearchForm.getAverageAge() != null)
            stringBuffer.append("&averageAge=" + advancedSearchForm.getAverageAge());
        if (advancedSearchForm.getYearsOfExperience() != null)
            stringBuffer.append("&yearsOfExperience=" + advancedSearchForm.getYearsOfExperience());
        if (advancedSearchForm.getSalary() != null)
            stringBuffer.append("&salary=" + advancedSearchForm.getSalary());
        if (advancedSearchForm.getEducationalLevelId() != null)
            stringBuffer.append("&educationalLevelId=" + advancedSearchForm.getEducationalLevelId());
        return stringBuffer.toString();
    }

    private String advancedSearchPage(AdvancedSearchForm advancedSearchForm, Model model) {
        model.addAttribute("advancedSearchForm", advancedSearchForm);
        List<Department> departments = departmentService.findAll();
        departments.add(0, new Department());
        model.addAttribute("departments", departments);
        List<EducationalLevel> educationalLevels = educationalLevelService.findAll();
        educationalLevels.add(0, new EducationalLevel());
        model.addAttribute("educationalLevels", educationalLevels);
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
