package com.example.security.demo.validation;

import com.example.security.demo.model.RolePrefix;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleCorrectValidator implements ConstraintValidator<RoleCorrect, Set<String>> {

  @Override
  public void initialize(RoleCorrect contactNumber) { }

  @Override
  public boolean isValid(Set<String> roles, ConstraintValidatorContext context) {
    for (RolePrefix role : RolePrefix.values()) {
      if (roles.contains(role.toString())){
        return true;
      }
    }
   return false;
  }
}
