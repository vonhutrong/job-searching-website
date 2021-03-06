package com.trong.validation;

import com.trong.form.ApplyForm;
import com.trong.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ApplyFormValidator implements Validator {
    @Autowired
    private RecruitmentService recruitmentService;

    private final long MAX_FILE_SIZE = 2 * 1024 * 1024; //2MB

    @Override
    public boolean supports(Class<?> aClass) {
        return ApplyForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ApplyForm applyForm = (ApplyForm) o;

        if (recruitmentService.findById(applyForm.getRecruitmentId()) == null) {
            errors.rejectValue("recruitmentId", "Invalid.applyForm.recruitmentId");
        }

        if (applyForm.getCv().getOriginalFilename().equals("")) {
            errors.rejectValue("cv", "NotNull.applyForm.cv");
        } else if (applyForm.getCv().getSize() > MAX_FILE_SIZE) {
            errors.rejectValue("cv", "Limited.fileSize");
        }
    }
}
