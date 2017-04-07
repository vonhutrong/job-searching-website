package com.trong.controllers;

import com.trong.form.EmployeeAccountForm;
import com.trong.model.Employee;
import com.trong.model.User;
import com.trong.service.EmployeeService;
import com.trong.service.RoleService;
import com.trong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/nguoi-dung/dang-ky";
    }

    @GetMapping("/dang-ky/{accountType}")
    public String signUp(@PathVariable String accountType, Model model) {
        if ("nguoi-dung".equals(accountType)) {
            model.addAttribute("employeeAccountForm", new EmployeeAccountForm());
            return "employee/sign_up";
        } else if ("doanh-nghiep".equals(accountType)) {
            return "employer/sign_up";
        }
        return "index";
    }

    @PostMapping("/dang-ky/nguoi-dung")
    public String signUp(@ModelAttribute EmployeeAccountForm employeeAccountForm) {
        User user = new User();
        user.setEmail(employeeAccountForm.getEmail());
        user.setPassword(employeeAccountForm.getPassword());
        user.setRoles(new HashSet<>(Arrays.asList(roleService.findFirstByName("ROLE_EMPLOYEE"))));

        Employee employee = new Employee();
        employee.setUser(user);
        employee.setName(employeeAccountForm.getName());
        employeeService.save(employee);

        return "index";
    }
}
