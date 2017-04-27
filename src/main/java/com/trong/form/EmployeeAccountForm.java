package com.trong.form;

import com.trong.validation.annotation.FieldMatch;
import com.trong.validation.annotation.UniqueUserEmail;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@FieldMatch(first = "password", second = "passwordConfirm")
@UniqueUserEmail(fieldName = "email")
public class EmployeeAccountForm {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Length(min = 6)
    private String password;
    @NotEmpty
    private String passwordConfirm;
}
