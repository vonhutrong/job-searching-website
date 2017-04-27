package com.trong.validation.validator;

import com.trong.repository.UserRepository;
import com.trong.validation.annotation.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
   private final UserRepository userRepository;

   @Autowired
   public UniqueEmailValidator(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void initialize(UniqueEmail constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      if (obj == null || userRepository.findByEmail(obj) != null) {
         return false;
      }
      return true;
   }
}
