package com.trong.validation.validator;

import com.trong.validation.annotation.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
   private String firstFieldName;
   private String secondFieldName;
   private String message;

   public void initialize(final FieldMatch constraintAnnotation) {
       firstFieldName = constraintAnnotation.first();
       secondFieldName = constraintAnnotation.second();
       message = constraintAnnotation.message();
   }

   public boolean isValid(final Object value, ConstraintValidatorContext context) {
       try {
           final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
           final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName).addConstraintViolation();
           context.buildConstraintViolationWithTemplate(message).addPropertyNode(secondFieldName).addConstraintViolation();

           return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
       } catch (final Exception ignore) {}
       return true;
   }
}
