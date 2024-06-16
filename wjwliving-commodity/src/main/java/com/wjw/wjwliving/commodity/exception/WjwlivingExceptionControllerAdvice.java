package com.wjw.wjwliving.commodity.exception;

import com.wjw.constant.WjwlivingCodeEnum;
import com.wjw.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.wjw.wjwliving.commodity.controller")
@ResponseBody
public class WjwlivingExceptionControllerAdvice {
    /**
     * 数据校验出现异常 MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            map.put(field, message);
        });
        return R.error(WjwlivingCodeEnum.INVALID_EXCEPTION.getCode(), WjwlivingCodeEnum.INVALID_EXCEPTION.getMsg()).put("data", map);
    }
    /**
     * 没有精确匹配到的异常, 走这里
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        return R.error(WjwlivingCodeEnum.UNKNOWN_EXCEPTION.getCode(), WjwlivingCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
