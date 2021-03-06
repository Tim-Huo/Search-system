package com.timhuo.dianping.dao;

import com.timhuo.dianping.model.CategoryModel;

import java.util.List;

public interface CategoryModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Sun Oct 04 10:38:12 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Sun Oct 04 10:38:12 CST 2020
     */
    int insert(CategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Sun Oct 04 10:38:12 CST 2020
     */
    int insertSelective(CategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Sun Oct 04 10:38:12 CST 2020
     */
    CategoryModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Sun Oct 04 10:38:12 CST 2020
     */
    int updateByPrimaryKeySelective(CategoryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Sun Oct 04 10:38:12 CST 2020
     */
    int updateByPrimaryKey(CategoryModel record);

    List<CategoryModel> selectAll();

    Integer countAllCategory();
}