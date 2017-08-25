package com.tuodao.bp.api.core.annotation.def;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tuodao.bp.api.core.annotation.Md5;

/**
 * @Description: 
 * @author Lwk
 * @date 2017年3月21日 下午8:37:16
 * Company：拓道金服
 *
 */
public class Md5Def implements ConstraintValidator<Md5, String> {
	
	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
    public void initialize(Md5 constraintAnnotation) {
    }

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	if(value == null || value.trim().equals("")){
    		return false;
    	}

		if (value.length() != 32) {
			return false;
		}

		return value.matches("[0-9A-Fa-f]+");
    }
}
