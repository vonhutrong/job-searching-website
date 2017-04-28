package com.trong.form;

import com.trong.validation.annotation.FieldMatch;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
@FieldMatch(first = "password", second = "passwordConfirm")
public class EmployerAccountForm {
    @Email
    @NotEmpty
    private String email;
    @Length(min = 6)
    private String password;
    @Length(min = 6)
    private String passwordConfirm;

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private MultipartFile logo;

    @NotEmpty
    private String address;
    @NotEmpty
    @Pattern(regexp="^[0-9]*$")
    private String phoneNumber;
    @NotEmpty
    @Email
    private String contactEmail;
}
