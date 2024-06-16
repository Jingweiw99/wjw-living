package com.wjw.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
* 1. 参考@NotNull 源码来开发
* 2. EnumConstraintValidator.class 是自定义的真正的校验器，需要自己开发 (一个自定义校验注解可以由多个校验器校验)
* 3. String message() default "{com.wjw.common.valid.EnumValidate.message}" 表示如果校验错误，
* 读取, resources\ValidationMessages.properties 的key=com.wjw.common.valid.EnumValidate.message
*/
@Documented
@Constraint(validatedBy = {EnumConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface EnumValidate {
    String message() default "{com.wjw.common.valid.EnumValidate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int[] values() default {};
}