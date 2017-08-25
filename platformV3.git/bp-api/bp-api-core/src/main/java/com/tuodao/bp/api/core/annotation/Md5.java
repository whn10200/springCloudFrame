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

import com.tuodao.bp.api.core.annotation.def.Md5Def;

/**
 * @Description: 检查该值是否是合法的md5值的注解类。
 * @author Lwk
 * @date 2017年3月21日 下午8:39:40
 * Company：拓道金服
 *
 */
@Documented
@Constraint(validatedBy = { Md5Def.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Md5{
	String message() default "参数非法";  //{constraint.default.words.message}
    Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
    String filed() default "";
}
