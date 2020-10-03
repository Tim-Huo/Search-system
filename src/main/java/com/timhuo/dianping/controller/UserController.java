package com.timhuo.dianping.controller;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.CommonRes;
import com.timhuo.dianping.common.CommonUtil;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.model.UserModel;
import com.timhuo.dianping.request.LoginReq;
import com.timhuo.dianping.request.RegisterReq;
import com.timhuo.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 用户
 * @author: Tim_Huo
 * @created: 2020/10/03 16:45
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";


    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @auther: Tim_Huo
     * @param: registerReq
     * @param: bindingResult
     * @return: CommonRes
     * @date: 2020/10/4 5:51 上午
     */
    @PostMapping("/register")
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel registerUser = new UserModel();
        registerUser.setTelphone(registerReq.getTelphone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setNickName(registerReq.getNickName());
        registerUser.setGender(registerReq.getGender());

        UserModel resUserModel = userService.register(registerUser);

        return CommonRes.create(resUserModel);
    }

    /**
     * 用户登陆
     *
     * @auther: Tim_Huo
     * @param: loginReq
     * @param: bindingResult
     * @return: CommonRes
     * @date: 2020/10/4 7:10 上午
     */
    @PostMapping("/login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelphone(),loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);

        return CommonRes.create(userModel);
    }

    /**
     * 退出登陆
     *
     * @auther: Tim_Huo
     * @return: CommonRes
     * @date: 2020/10/4 7:10 上午
     */
    @GetMapping("/logout")
    @ResponseBody
    public CommonRes logout() {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    /**
     * 获取当前用户信息
     *
     * @auther: Tim_Huo
     * @return: CommonRes
     * @date: 2020/10/4 7:12 上午
     */
    @GetMapping("/getCurrentUser")
    public CommonRes getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }

}
