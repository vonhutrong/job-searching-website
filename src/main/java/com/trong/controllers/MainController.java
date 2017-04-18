package com.trong.controllers;

import com.trong.form.EmployeeAccountForm;
import com.trong.form.EmployerAccountForm;
import com.trong.model.Employee;
import com.trong.model.Employer;
import com.trong.model.User;
import com.trong.service.EmployeeService;
import com.trong.service.EmployerService;
import com.trong.service.RoleService;
import com.trong.validator.EmployerAccountFormValidator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/dang-ky/{accountType}")
    public String signUp(@PathVariable String accountType, Model model) {
        if ("nguoi-dung".equals(accountType)) {
            model.addAttribute("employeeAccountForm", new EmployeeAccountForm());
            return "employee/sign_up";
        } else if ("doanh-nghiep".equals(accountType)) {
            model.addAttribute("employerAccountForm", new EmployerAccountForm());
            return "employer/sign_up";
        }
        return "index";
    }

    @PostMapping("/dang-ky/nguoi-dung")
    public String signUp(@ModelAttribute EmployeeAccountForm employeeAccountForm) {
        User user = new User();
        user.setEmail(employeeAccountForm.getEmail());
        user.setPassword(passwordEncoder.encode(employeeAccountForm.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleService.findByName("ROLE_EMPLOYEE"))));

        Employee employee = new Employee();
        employee.setUser(user);
        employee.setName(employeeAccountForm.getName());
        employeeService.save(employee);

        return "index";
    }

    @PostMapping("/dang-ky/doanh-nghiep")
    private String signUp(@ModelAttribute("employerAccountForm") @Valid EmployerAccountForm employerAccountForm, BindingResult bindingResult, Model model, HttpServletRequest httpRequest) {
        new EmployerAccountFormValidator().validate(employerAccountForm, bindingResult);
        String realPath = httpRequest.getSession().getServletContext().getRealPath("/");
        if (bindingResult.hasErrors()) {
            model.addAttribute("employerAccountForm", employerAccountForm);
            return "employer/sign_up";
        }

        User user = new User();
        user.setEmail(employerAccountForm.getEmail());
        user.setPassword(passwordEncoder.encode(employerAccountForm.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleService.findByName("ROLE_EMPLOYER"))));

        Employer employer = new Employer();
        employer.setUser(user);
        employer.setName(employerAccountForm.getName());
        employer.setDescription(employerAccountForm.getDescription());
        employer.setAddress(employerAccountForm.getAddress());
        employer.setPhoneNumber(employerAccountForm.getPhoneNumber());
        employer.setContactEmail(employerAccountForm.getContactEmail());
        employer.setLogoPath(saveFile(employerAccountForm.getLogo(), employerAccountForm.getEmail()));

        employerService.save(employer);

        return "index";
    }

    public String saveFile(MultipartFile file, String email) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "files" + File.separator + email);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return serverFile.getAbsolutePath();
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return null;
        }
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImageAsByteArray() throws IOException {
        InputStream in = new FileInputStream(new File("C:\\Users\\trongvn13\\Desktop\\download.jpg"));
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/logo")
    public void returnLogo(@Param("logoPath") String logoPath, HttpServletResponse response) throws IOException {
        InputStream in = new FileInputStream(new File(logoPath));
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        org.apache.commons.io.IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/dang-nhap")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
