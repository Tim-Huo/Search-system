package com.timhuo.dianping.service.impl;

import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.dao.CategoryModelMapper;
import com.timhuo.dianping.model.CategoryModel;
import com.timhuo.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Tim_Huo
 * @created: 2020/10/04 10:43
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    public CategoryModel create(CategoryModel categoryModel) throws BusinessException {
        categoryModel.setCreatedAt(new Date());
        categoryModel.setUpdatedAt(new Date());

        try{
            categoryModelMapper.insertSelective(categoryModel);
        }catch(DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
        }

        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAllCategory() {
        return categoryModelMapper.countAllCategory();
    }
}
