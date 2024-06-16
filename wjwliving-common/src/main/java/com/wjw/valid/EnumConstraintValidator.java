package com.wjw.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class EnumConstraintValidator implements ConstraintValidator<EnumValidate, Integer> {
    private Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        //将注解指定的值取出放入到 set 集合
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            set.add(value);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //这个 value 就是从表单传递的数值
        //判断是否在注解填写的枚举值中
        return set.contains(value);
    }
}
