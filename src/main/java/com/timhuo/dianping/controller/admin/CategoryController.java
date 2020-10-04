package com.timhuo.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timhuo.dianping.common.AdminPermission;
import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.CommonUtil;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.model.CategoryModel;
import com.timhuo.dianping.request.CategoryCreateReq;
import com.timhuo.dianping.request.PageQuery;
import com.timhuo.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 品类模块（运营人员）
 * @author: Tim_Huo
 * @created: 2020/10/04 10:47
 */
@RestController
@RequestMapping("/admin/category ")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有品类
     *
     * @auther: Tim_Huo
     * @param: pageQuery
     * @return: ModelAndView
     * @date: 2020/10/4 10:50 上午
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModelList);
        ModelAndView modelAndView = new ModelAndView("/admin/category/index.html");
        modelAndView.addObject("data",categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    /**
     * 跳转到添加页面
     *
     * @auther: Tim_Huo
     * @return: ModelAndView
     * @date: 2020/10/4 10:50 上午
     */
    @RequestMapping("/createPage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/create.html");
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    /**
     * 创建品类
     *
     * @auther: Tim_Huo
     * @param: categoryCreateReq
     * @param: bindingResult
     * @return: String
     * @date: 2020/10/4 10:50 上午
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryCreateReq.getName());
        categoryModel.setIconUrl(categoryCreateReq.getIconUrl());
        categoryModel.setSort(categoryCreateReq.getSort());

        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";


    }
    
    
}
