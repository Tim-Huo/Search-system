package com.timhuo.dianping.controller.admin;

import com.timhuo.dianping.common.AdminPermission;
import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * /admin/admin   /管理员模块/管理员
 *
 * @description: 管理员controller
 * @author: Tim_Huo
 * @created: 2020/10/04 07:40
 */
@RestController
@RequestMapping("/admin/admin")
public class AdminController {

    @Value("admin.email")
    private String email;

    @Value("admin.encryptPassword")
    private String encryptPassword;

    /**
     * 将登陆的用户信息绑定到session中
     */
    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    /**
     * 首页
     *
     * @auther: Tim_Huo
     * @return: ModelAndView
     * @date: 2020/10/4 8:03 上午
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        modelAndView.addObject("userCount",userService.countAllUser());
//        modelAndView.addObject("shopCount",shopService.countAllShop());
//        modelAndView.addObject("categoryCount",categoryService.countAllCategory());
//        modelAndView.addObject("sellerCount",sellerService.countAllSeller());
        modelAndView.addObject("CONTROLLER_NAME","admin");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    /**
     * 登陆页面
     *
     * @auther: Tim_Huo
     * @return: ModelAndView
     * @date: 2020/10/4 8:03 上午
     */
    @RequestMapping("/loginpage")
    public ModelAndView loginpage(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/login");
        return modelAndView;
    }

    /**
     * 登陆
     *
     * @auther: Tim_Huo
     * @param: email 邮箱
     * @param: password 密码
     * @return: String
     * @date: 2020/10/4 8:05 上午
     */
    @PostMapping("/login")
    public String login(@RequestParam(name="email")String email,
                        @RequestParam(name="password")String password ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名密码不能为空");
        }
        if(email.equals(this.email) && encodeByMd5(password).equals(this.encryptPassword)){
            //登录成功
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION,email);
            return "redirect:/admin/admin/index";
        }else{
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名密码错误");
        }

    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确认计算方法MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));

    }

}
