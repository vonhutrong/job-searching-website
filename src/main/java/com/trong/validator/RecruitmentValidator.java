package com.trong.validator;

import com.trong.form.RecruitmentForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

public class RecruitmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RecruitmentForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RecruitmentForm recruitmentForm = (RecruitmentForm) o;

        if (recruitmentForm.getLowestSalary() != null &&
                recruitmentForm.getHighestSalary() != null &&
                recruitmentForm.getLowestSalary() > recruitmentForm.getHighestSalary()) {
            errors.rejectValue("lowestSalary", "invalid.lowestSalary");
            errors.rejectValue("highestSalary", "invalid.highestSalary");
        }

        Date currentDate = new Date();
        if (recruitmentForm.getDeadlineForSubmission() != null &&
                (recruitmentForm.getDeadlineForSubmission().before(currentDate) ||
                        recruitmentForm.getDeadlineForSubmission().equals(currentDate))) {
            errors.rejectValue("deadlineForSubmission", "invalid.deadlineForSubmission");
        }
    }
}
