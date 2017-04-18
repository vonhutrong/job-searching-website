package com.trong.validator;

import com.trong.form.EmployerAccountForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployerAccountFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployerAccountForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmployerAccountForm employerAccountForm = (EmployerAccountForm) o;

        if (!employerAccountForm.getPassword().equals(employerAccountForm.getPasswordConfirm())) {
            errors.rejectValue("password", "notMatch.password");
            errors.rejectValue("passwordConfirm", "notMatch.passwordConfirm");
        }

        if (employerAccountForm.getLogo().getOriginalFilename().equals("")) {
            errors.rejectValue("logo", "notNull.logo");
        }
    }
}
