package com.app.dataentry.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class SizeOrNullValidator implements ConstraintValidator<SizeOrNull, String> {
	
	private int size;
	
	@Override
    public void initialize(SizeOrNull constraintAnnotation) {
        this.size = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( StringUtils.isEmpty(object) ) {
            return true;
        }
        if (object.length() == size) {
        	return true;
        }

        return false;
    }
}
