package com.timhuo.dianping.service.impl;

import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.dao.UserModelMapper;
import com.timhuo.dianping.model.UserModel;
import com.timhuo.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * @description: 用户服务
 * @author: Tim_Huo
 * @created: 2020/10/03 22:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;


    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone,encodeByMd5(password));
        if(userModel == null){
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        return userModel;
    }

    @Override
    public Integer countAllUser() {
        return userModelMapper.countAllUser();
    }

    @Override
    @Transactional
    public UserModel register(UserModel userModel) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        userModel.setPassword(encodeByMd5(userModel.getPassword()));
        userModel.setCreatedAt(new Date());
        userModel.setUpdatedAt(new Date());

        try {
            userModelMapper.insertSelective(userModel);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }

        return getUser(userModel.getId());
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }


}
