package com.trong.validation.validator;

import com.trong.validation.annotation.ValidSalaryBounds;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSalaryBoundsValidator implements ConstraintValidator<ValidSalaryBounds, Object> {
    private String minFieldName;
    private String maxFieldName;
    private String message;

    public void initialize(ValidSalaryBounds constraint) {
        minFieldName = constraint.min();
        maxFieldName = constraint.max();
        message = constraint.message();
    }

    public boolean isValid(final Object value, ConstraintValidatorContext context) {
        boolean isValid = false;
        try {
            final Double minObj = Double.valueOf(BeanUtils.getProperty(value, minFieldName));
            final Double maxObj = Double.valueOf(BeanUtils.getProperty(value, maxFieldName));
            isValid = minObj <= maxObj;
        } catch (Exception ignored) {
            isValid = false;
        }
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(minFieldName).addConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(maxFieldName).addConstraintViolation();
        }
        return isValid;
    }
}
