package com.timhuo.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timhuo.dianping.common.AdminPermission;
import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.CommonUtil;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.model.ShopModel;
import com.timhuo.dianping.request.PageQuery;
import com.timhuo.dianping.request.ShopCreateReq;
import com.timhuo.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 门店管理服务（运营人员）
 * @author: Tim_Huo
 * @created: 2020/10/04 14:13
 */
@RestController
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 门店列表
     *
     * @auther: Tim_Huo
     * @param: pageQuery
     * @return: ModelAndView
     * @date: 2020/10/4 2:14 下午
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<ShopModel> shopModelList = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);
        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");
        modelAndView.addObject("data",shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    /**
     * 跳转到添加页面
     *
     * @auther: Tim_Huo
     * @return: ModelAndView
     * @date: 2020/10/4 2:15 下午
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    /**
     * 创建门店
     *
     * @auther: Tim_Huo
     * @param: shopCreateReq
     * @param: bindingResult
     * @return: String
     * @date: 2020/10/4 2:18 下午
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setIconUrl(shopCreateReq.getIconUrl());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setCategoryId(shopCreateReq.getCategoryId());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setLongitude(shopCreateReq.getLongitude());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setSellerId(shopCreateReq.getSellerId());

        shopService.create(shopModel);


        return "redirect:/admin/shop/index";


    }
}
