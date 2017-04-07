package com.trong.form;

import lombok.Data;

@Data
public class EmployeeAccountForm {
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
}
