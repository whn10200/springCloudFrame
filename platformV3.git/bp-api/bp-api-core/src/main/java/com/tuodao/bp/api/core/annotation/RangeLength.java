package com.tuodao.bp.api.core.annotation;



import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.tuodao.bp.api.core.annotation.def.RangeLengthDef;

/**
 * @Description: 输入字段长度验证,长度不符和null为验证失败
 * @author Lwk
 * @date 2017年3月21日 下午8:40:52
 * Company：拓道金服
 *
 */
@Documented
@Constraint(validatedBy = { RangeLengthDef.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface RangeLength {
    /** 最大长度 */
    long min() default 0;
    /** 最小长度值*/
    long max() default Long.MAX_VALUE;
    /** 报错信息 */
    String message() default "参数长度非法";
    /** 是否必填项 */
    boolean  required() default true;
    /** 字段名 */
    String label() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
