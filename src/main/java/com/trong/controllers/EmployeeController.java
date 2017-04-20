package com.trong.controllers;

import com.trong.form.ApplyForm;
import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import com.trong.model.Recruitment;
import com.trong.model.User;
import com.trong.service.*;
import com.trong.validator.ApplyFormValidator;
import com.trong.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/nguoi-dung")
public class EmployeeController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ApplyHistoryService applyHistoryService;

    @Autowired
    private ApplyFormValidator applyFormValidator;

    @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
    public String signUP(@ModelAttribute("userForm")User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @GetMapping("/nop-don")
    public String applyForm(@Param("recruitmentId") Long recruitmentId, Model model, Principal principal) {
        model.addAttribute("recruitment", recruitmentService.findById(recruitmentId));
        model.addAttribute("employee", employeeService.findByEmail(principal.getName()));
        model.addAttribute("applyForm", new ApplyForm());
        return "employee/applying";
    }

    @PostMapping("/nop-don")
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
        return "redirect:/tin-tuyen-dung";
    }

    private String saveCv(MultipartFile cv, String email) {
        return MainController.saveFile(cv, email);
    }

    @RequestMapping("/trang-chu")
    public String home() {
        return "employee/home";
    }
}
