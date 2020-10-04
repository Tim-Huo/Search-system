package com.timhuo.dianping.service;


import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.model.ShopModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopService {

    /**
     * 创建门店
     *
     * @auther: Tim_Huo
     * @param: shopModel
     * @return: ShopModel
     * @date: 2020/10/4 1:48 下午
     */
    ShopModel create(ShopModel shopModel) throws BusinessException;
    
    /**
     * 根据id获取门店
     *
     * @auther: Tim_Huo
     * @param: id
     * @return: ShopModel
     * @date: 2020/10/4 1:48 下午
     */
    ShopModel get(Integer id);

    /**
     * 查询所有门店
     *
     * @auther: Tim_Huo
     * @return: ShopModel
     * @date: 2020/10/4 1:49 下午
     */
    List<ShopModel> selectAll();


    List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude);

    List<Map<String,Object>> searchGroupByTags(String keyword, Integer categoryId, String tags);

    /**
     * 计算所有的门店
     *
     * @auther: Tim_Huo
     * @return: Integer
     * @date: 2020/10/4 2:44 下午
     */
    Integer countAllShop();

    List<ShopModel> search(BigDecimal longitude,
                           BigDecimal latitude,
                           String keyword,
                           Integer orderby,
                           Integer categoryId,
                           String tags);

    Map<String, Object> searchES(BigDecimal longitude,
                               BigDecimal latitude,
                               String keyword,
                               Integer orderby,
                               Integer categoryId,
                               String tags) throws IOException;
}
