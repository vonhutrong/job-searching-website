package com.trong.controllers;

import com.trong.model.User;
import com.trong.service.SecurityService;
import com.trong.service.UserService;
import com.trong.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/nguoi-dung")
public class EmployeeController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

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

    @RequestMapping("/nop-don")
    public String apply() {
        return "employee/applying";
    }

    @RequestMapping("/trang-chu")
    public String home() {
        return "employee/home";
    }
}
