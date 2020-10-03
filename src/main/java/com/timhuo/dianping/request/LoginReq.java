package com.timhuo.dianping.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description: 登陆实体
 * @author: Tim_Huo
 * @created: 2020/10/04 06:42
 */
@Data
public class LoginReq {

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;
}
