package com.timhuo.dianping.controller;

import com.timhuo.dianping.common.AdminPermission;
import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.CommonUtil;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.model.SellerModel;
import com.timhuo.dianping.request.SellerCreateReq;
import com.timhuo.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商家管理
 * @author: Tim_Huo
 * @created: 2020/10/04 09:28
 */
@RestController
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * 查询所有商户
     *
     * @auther: Tim_Huo
     * @param: pageQuery
     * @return: ModelAndView
     * @date: 2020/10/4 9:30 上午
     */
    @GetMapping("/index")
    @AdminPermission
    public ModelAndView index(){
        List<SellerModel> sellerModelList = sellerService.selectAll();
        ModelAndView modelAndView = new ModelAndView("/admin/seller/index.html");
        modelAndView.addObject("data",sellerModelList);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    /**
     * 跳转到添加商家的页面
     *
     * @auther: Tim_Huo
     * @return: ModelAndView
     * @date: 2020/10/4 9:47 上午
     */
    @RequestMapping("/createPage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create.html");
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    /**
     * 创建商家
     *
     * @auther: Tim_Huo
     * @param: sellerCreateReq
     * @param: bindingResult
     * @return: String
     * @date: 2020/10/4 9:48 上午
     */
    @PostMapping("/create")
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());
        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";
    }



}
