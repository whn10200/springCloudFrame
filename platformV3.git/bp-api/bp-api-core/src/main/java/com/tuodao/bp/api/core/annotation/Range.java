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

import com.tuodao.bp.api.core.annotation.def.RangeDef;

/**
 * @Description: 数值范围验证
 * @author Lwk
 * @date 2017年3月21日 下午8:40:35
 * Company：拓道金服
 *
 */
@Documented
@Constraint(validatedBy = { RangeDef.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Range {
    /** 最小值 */
    long min() default 0;
    /** 最大值 */
    long max() default Long.MAX_VALUE;
    /** 报错信息 */
    String message() default "参数非法";
    /** 字段名 */
    String label() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @Range} annotations on the same element.
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Range[] value();
    }
}
