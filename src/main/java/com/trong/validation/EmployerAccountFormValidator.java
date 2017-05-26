package com.trong.validation;

import com.trong.form.EmployerAccountForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployerAccountFormValidator implements Validator {
    private final long MAX_FILE_SIZE = 1024 * 1024; //1MB

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployerAccountForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmployerAccountForm employerAccountForm = (EmployerAccountForm) o;

        if (employerAccountForm.getLogo().getOriginalFilename().equals("")) {
            errors.rejectValue("logo", "NotNull.employerAccountForm.logo");
        } else if (employerAccountForm.getLogo().getSize() > MAX_FILE_SIZE) {
            errors.rejectValue("logo", "LimitedSize.employerAccountForm.logo");
        }
    }
}
