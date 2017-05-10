package com.trong.controllers;

import com.trong.form.RecruitmentForm;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    private final RecruitmentService recruitmentService;
    private final DepartmentService departmentService;
    private final EducationalLevelService educationalLevelService;
    private final EmployerService employerService;
    private final ApplyHistoryService applyHistoryService;
    private final NotificationService notificationService;

    @Autowired
    public EmployerController(RecruitmentService recruitmentService, DepartmentService departmentService, EducationalLevelService educationalLevelService, EmployerService employerService, ApplyHistoryService applyHistoryService, NotificationService notificationService) {
        this.recruitmentService = recruitmentService;
        this.departmentService = departmentService;
        this.educationalLevelService = educationalLevelService;
        this.employerService = employerService;
        this.applyHistoryService = applyHistoryService;
        this.notificationService = notificationService;
    }

    @GetMapping("/postRecruitment")
    public String publicRecruitmentPage(Model model) {
        model.addAttribute("recruitmentForm", new RecruitmentForm());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("educationalLevels", educationalLevelService.findAll());
        return "employer/public_recruitment";
    }

    @PostMapping("/postRecruitment")
    public String publicRecruitment(@ModelAttribute("recruitmentForm") @Valid RecruitmentForm recruitmentForm, BindingResult bindingResult, Principal principal, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("recruitmentForm", recruitmentForm);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("educationalLevels", educationalLevelService.findAll());
            notificationService.addErrorMessage("PostFail.employer");
            return "employer/public_recruitment";
        }

        saveRecruitment(recruitmentForm, principal);
        notificationService.addInfoMessage("PostSuccess.employer");
        return "redirect:/";
    }

    @GetMapping("/recruitmentManagement")
    public String manageRecruitment(@PageableDefault(size = 1) Pageable pageable, Model model, Principal principal) {
        Employer employer = employerService.findByEmail(principal.getName());
        Page<Recruitment> recruitments = recruitmentService.findByEmployer(employer, pageable);
        model.addAttribute("page", new PageWrapper<Recruitment>(recruitments, "/employer/recruitmentManagement"));
        return "employer/recruitment_manage";
    }

    @GetMapping("/updateApplication")
    public String updateApplyHistory(@Param("applyHistoryId") Long applyHistoryId, @Param("approved") Boolean approved) {
        ApplyHistory applyHistory = applyHistoryService.findById(applyHistoryId);
        applyHistory.setApproved(approved);
        applyHistoryService.save(applyHistory);
        return String.format("redirect:/recruitment/details?id=%s", applyHistory.getRecruitment().getId());
    }

    private void saveRecruitment(RecruitmentForm recruitmentForm, Principal principal) {
        Recruitment recruitment = new Recruitment();
        recruitment.setTitle(recruitmentForm.getTitle());
        recruitment.setContent(recruitmentForm.getContent());
        recruitment.setRequiredFemale(recruitmentForm.getRequiredFemale());
        recruitment.setAverageAge(recruitmentForm.getAverageAge());
        recruitment.setYearsOfExperience(recruitmentForm.getYearsOfExperience());
        recruitment.setCreatedDatetime(new java.util.Date());
        recruitment.setLowestSalary(recruitmentForm.getLowestSalary());
        recruitment.setHighestSalary(recruitmentForm.getHighestSalary());
        recruitment.setDeadlineForSubmission(new java.sql.Date(recruitmentForm.getDeadlineForSubmission().getTime()));
        Department department = departmentService.findById(recruitmentForm.getDepartmentId());
        recruitment.setDepartment(department);
        EducationalLevel educationalLevel = educationalLevelService.findById(recruitmentForm.getEducationalLevelId());
        recruitment.setEducationalLevel(educationalLevel);
        Employer employer = employerService.findByEmail(principal.getName());
        recruitment.setEmployer(employer);
        recruitmentService.save(recruitment);
    }
}
