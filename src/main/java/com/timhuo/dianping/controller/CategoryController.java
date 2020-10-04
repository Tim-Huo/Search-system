package com.timhuo.dianping.controller;

import com.timhuo.dianping.common.CommonRes;
import com.timhuo.dianping.model.CategoryModel;
import com.timhuo.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品类管理（C端用户）
 *
 * @auther: Tim_Huo
 * @date: 2020/10/4 1:57 下午
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询全部品类，提供给c端用户使用
     *
     * @auther: Tim_Huo
     * @param:
     * @return:
     * @date: 2020/10/4 11:09 上午
     */
    @RequestMapping("/list")
    public CommonRes list(){
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        return CommonRes.create(categoryModelList);

    }

}
