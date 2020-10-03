package com.timhuo.dianping.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @description: 公共工具
 * @author: Tim_Huo
 * @created: 2020/10/04 05:57
 */
public class CommonUtil {

     public static String processErrorString(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getDefaultMessage() + "");
        }
         return stringBuilder.substring(0, stringBuilder.length() - 1);
     }


}
