package com.trong.form;

import lombok.Data;

@Data
public class EmployerAccountForm {
    private String email;
    private String password;
    private String passwordConfirm;

    private String name;
    private String description;

    private String address;
    private String phoneNumber;
    private String contactEmail;
}
