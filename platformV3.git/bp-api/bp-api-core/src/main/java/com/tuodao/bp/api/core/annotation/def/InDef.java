package com.tuodao.bp.api.core.annotation.def;


import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tuodao.bp.api.core.annotation.In;

/**
 * @Description: 检查该值是否是所属范围值内。如in(1,2)。
 * @author Lwk
 * @date 2017年3月21日 下午8:36:52
 * Company：拓道金服
 *
 */
public class InDef implements ConstraintValidator<In, Object> {
    // 待检查的值范围
    private String value;
    private boolean ignore;
    private boolean required;

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    public void initialize(In constraintAnnotation) {
        this.value = constraintAnnotation.value();
        this.ignore = constraintAnnotation.ignore();
        this.required = constraintAnnotation.required();
    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(Object values, ConstraintValidatorContext context) {
        String value;
        if (values instanceof String || values instanceof Integer) {
            value = String.valueOf(values);
        } else if (null == values) {
            value = null;
        } else {
            throw new RuntimeException("@IN目前只支持String和Integer类型");
        }

        if ((value == null || value.trim().equals("")) && this.required) {
            return false;
        } else if ((value == null || value.trim().equals("")) && !this.required) {
            return true;
        }

        String[] vals = this.value.split("\\|");
        List<String> list = Arrays.asList(vals);
        if (!this.ignore && !list.contains(value)) {
            return false;
        }

        String low = value.toLowerCase();
        for (String val : list) {
            if (low.equals(val.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
