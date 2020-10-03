package com.timhuo.dianping.service;

import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.model.UserModel;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {

    /**
     * 用户注册
     *
     * @auther: Tim_Huo
     * @param: userModel
     * @return: UserModel
     * @date: 2020/10/3 10:45 下午
     */
    UserModel register(UserModel userModel) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel getUser(Integer id);

    /**
     * 用户登陆
     *
     * @auther: Tim_Huo
     * @param: telphone 电话
     * @param: password 密码
     * @return: UserModel
     * @date: 2020/10/4 6:34 上午
     */
    UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;


}
