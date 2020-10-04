package com.timhuo.dianping.service;


import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    /**
     * 创建品类
     *
     * @auther: Tim_Huo
     * @param: categoryModel
     * @return: CategoryModel
     * @date: 2020/10/4 10:41 上午
     */
    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    /**
     * 根据id获取品类
     *
     * @auther: Tim_Huo
     * @param: id
     * @return: CategoryModel
     * @date: 2020/10/4  10:41 上午
     */
    CategoryModel get(Integer id);

    /**
     * 查询所有品类
     * @auther: Tim_Huo
     * @return: CategoryModel
     * @date: 2020/10/4 10:42 上午
     */
    List<CategoryModel> selectAll();

    /**
     * 计算所有的品类
     *
     * @auther: Tim_Huo
     * @return: Integer
     * @date: 2020/10/4 2:40 下午
     */
    Integer countAllCategory();
}
