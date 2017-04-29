package com.trong.controllers;

import com.trong.form.ApplyForm;
import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import com.trong.model.Recruitment;
import com.trong.service.ApplyHistoryService;
import com.trong.service.EmployeeService;
import com.trong.service.RecruitmentService;
import com.trong.util.PageWrapper;
import com.trong.validation.ApplyFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final RecruitmentService recruitmentService;
    private final EmployeeService employeeService;
    private final ApplyHistoryService applyHistoryService;
    private final ApplyFormValidator applyFormValidator;

    @Autowired
    public EmployeeController(RecruitmentService recruitmentService, EmployeeService employeeService, ApplyHistoryService applyHistoryService, ApplyFormValidator applyFormValidator) {
        this.recruitmentService = recruitmentService;
        this.employeeService = employeeService;
        this.applyHistoryService = applyHistoryService;
        this.applyFormValidator = applyFormValidator;
    }

    @GetMapping("/apply")
    public String applyForm(@Param("recruitmentId") Long recruitmentId, Model model, Principal principal) {
        model.addAttribute("recruitment", recruitmentService.findById(recruitmentId));
        model.addAttribute("employee", employeeService.findByEmail(principal.getName()));
        model.addAttribute("applyForm", new ApplyForm());
        return "employee/applying";
    }

    @PostMapping("/apply")
    public String applyRecruitment(@ModelAttribute("applyForm") @Valid ApplyForm applyForm, BindingResult bindingResult, Principal principal, Model model) {
        applyFormValidator.validate(applyForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("recruitment", recruitmentService.findById(applyForm.getRecruitmentId()));
            model.addAttribute("employee", employeeService.findByEmail(principal.getName()));
            model.addAttribute("applyForm", applyForm);
            return "employee/applying";
        }

        String cvPath = saveCv(applyForm.getCv(), principal.getName());
        Date currentDatetime = new Date();
        Employee employee = employeeService.findByEmail(principal.getName());
        Recruitment recruitment = recruitmentService.findById(applyForm.getRecruitmentId());

        ApplyHistory applyHistory = new ApplyHistory();
        applyHistory.setCvPath(cvPath);
        applyHistory.setDatetime(currentDatetime);
        applyHistory.setEmployee(employee);
        applyHistory.setRecruitment(recruitment);

        applyHistoryService.save(applyHistory);
        return "redirect:/recruitment";
    }

    @GetMapping("/applyManagement")
    public String manageApply(@PageableDefault(size = 5) Pageable pageable, Principal principal, Model model) {
        Employee employee = employeeService.findByEmail(principal.getName());
        Page<ApplyHistory> applyHistories = applyHistoryService.findByEmployee(employee, pageable);
        model.addAttribute("page", new PageWrapper<ApplyHistory>(applyHistories, "/employee/applyManagement"));
        return "employee/apply_management";
    }

    private String saveCv(MultipartFile cv, String email) {
        return MainController.saveFile(cv, email);
    }
}
