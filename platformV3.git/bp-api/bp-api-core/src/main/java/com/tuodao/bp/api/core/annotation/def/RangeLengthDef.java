package com.tuodao.bp.api.core.annotation.def;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tuodao.bp.api.core.annotation.RangeLength;

/**
 * @Description: 输入字段长度验证
 * @author Lwk
 * @date 2017年3月21日 下午8:36:17
 * Company：拓道金服
 *
 */
public class RangeLengthDef implements ConstraintValidator<RangeLength, String> {

    // 最小值
    private long min;

    // 最大值
    private long max;
    // 是否必填
    private boolean required;

    @Override
    public void initialize(RangeLength constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.required=constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // 如果为空则长度非法
        if ( value == null || "".equals(value)) {
            return !required;
        }
        // min <= validateValue <= max
        if (min <= value.length() && value.length() <= max) {
            return true;
        }
        return false;
    }


}
