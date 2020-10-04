package com.timhuo.dianping.service;


import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.model.SellerModel;

import java.util.List;

public interface SellerService {

    /**
     * 商家创建
     *
     * @auther: Tim_Huo
     * @param: sellerModel
     * @return: SellerModel
     * @date: 2020/10/4 9:16 上午
     */
    SellerModel create(SellerModel sellerModel);

    /**
     * 商家查询
     *
     * @auther: Tim_Huo
     * @param: id
     * @return: SellerModel
     * @date: 2020/10/4 9:17 上午
     */
    SellerModel get(Integer id);

    /**
     * 查询所有商家
     *
     * @auther: Tim_Huo
     * @return: SellerModel
     * @date: 2020/10/4 9:17 上午
     */
    List<SellerModel> selectAll();
    
    /**
     * 启动，禁用商家
     *
     * @auther: Tim_Huo
     * @param: id
     * @param: disabledFlag
     * @return: SellerModel
     * @date: 2020/10/4 9:18 上午
     */
    SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException;

    /**
     * 计算所有的商家
     *
     * @auther: Tim_Huo
     * @return: Integer
     * @date: 2020/10/4 2:33 下午
     */
    Integer countAllSeller();
}
