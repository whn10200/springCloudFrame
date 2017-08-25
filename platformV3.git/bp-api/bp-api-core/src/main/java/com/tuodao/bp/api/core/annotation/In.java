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

import com.tuodao.bp.api.core.annotation.def.InDef;

/**
 * @Description: 检查该值是否是所属范围值内的注解类。
 * @author Lwk
 * @date 2017年3月21日 下午8:39:11
 * Company：拓道金服
 *
 */
@Documented
@Constraint(validatedBy = { InDef.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface In {
	String message() default "参数非法";  //{constraint.default.words.message}
    Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
    String filed() default "";  
    
	/**
	 * 待检查的值(以|符分隔)
	 * 
	 */
	String value();
	
	/**
	 * 是否忽略大小写。
	 */
	boolean ignore() default true;
	
	/**
	 * 是否必须
	 */
	boolean required() default false;
}
