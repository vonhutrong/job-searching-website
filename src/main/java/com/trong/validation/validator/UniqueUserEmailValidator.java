package com.trong.validation.validator;

import com.trong.repository.UserRepository;
import com.trong.validation.annotation.UniqueUserEmail;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, Object> {
    private final UserRepository userRepository;
    private String fieldName;
    private String message;

    @Autowired
    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize(UniqueUserEmail constraint) {
        fieldName = constraint.fieldName();
        message = constraint.message();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object obj = BeanUtils.getProperty(value, fieldName);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return obj != null && userRepository.findByEmail((String)obj) == null;
        } catch (final Exception ex) {}
        return true;
    }
}
