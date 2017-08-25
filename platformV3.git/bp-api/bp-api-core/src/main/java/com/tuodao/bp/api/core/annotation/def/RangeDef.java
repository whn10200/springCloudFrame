package com.tuodao.bp.api.core.annotation.def;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tuodao.bp.api.core.annotation.Range;


/**
 * @Description: 数值范围验证,允许为空
 * @author Lwk
 * @date 2017年3月21日 下午8:41:27
 * Company：拓道金服
 *
 */
public class RangeDef implements ConstraintValidator<Range, String> {

    // 最小值
    private long min;

    // 最大值
    private long max;

    @Override
    public void initialize(Range constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // 如果为空则不验证
        if ( value == null || "".equals(value)) {
            return true;
        }

        // 如果不为数字
        if (!isInteger(value)) {
            return false;
        }

        // 比较值得范围
        long validateValue = Long.valueOf(value);
        // min <= validateValue <= max
        if (min <= validateValue && validateValue <= max) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
