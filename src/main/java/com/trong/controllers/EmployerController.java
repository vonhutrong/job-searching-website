package com.trong.controllers;

import com.trong.form.RecruitmentForm;
import com.trong.model.*;
import com.trong.service.*;
import com.trong.validation.RecruitmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
@RequestMapping("/nha-tuyen-dung")
public class EmployerController {
    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EducationalLevelService educationalLevelService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private ApplyHistoryService applyHistoryService;

    @GetMapping("/dang-tin")
    public String publicRecruitmentPage(Model model) {
        model.addAttribute("recruitmentForm", new RecruitmentForm());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("educationalLevels", educationalLevelService.findAll());
        return "employer/public_recruitment";
    }

    @PostMapping("/dang-tin")
    public String publicRecruitment(@ModelAttribute("recruitmentForm") @Valid RecruitmentForm recruitmentForm, BindingResult bindingResult, Principal principal, ModelMap model) {
        RecruitmentValidator recruitmentValidator = new RecruitmentValidator();
        recruitmentValidator.validate(recruitmentForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("recruitmentForm", recruitmentForm);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("educationalLevels", educationalLevelService.findAll());
            return "employer/public_recruitment";
        }

        saveRecruitment(recruitmentForm, principal);
        return "redirect:/nha-tuyen-dung/dang-tin";
    }

    @GetMapping("/quan-ly-tin")
    public String manageRecruitment(Model model, Principal principal) {
        model.addAttribute("recruitments", recruitmentService.findByEmployer(employerService.findByEmail(principal.getName())));
        return "employer/recruitment_manage";
    }

    @GetMapping("/cap-nhat-ho-so")
    public String updateApplyHistory(@Param("applyHistoryId") Long applyHistoryId, @Param("approved") Boolean approved) {
        ApplyHistory applyHistory = applyHistoryService.findById(applyHistoryId);
        applyHistory.setApproved(approved);
        applyHistoryService.save(applyHistory);
        return "redirect:/nha-tuyen-dung/dang-tin";
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
