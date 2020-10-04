package com.timhuo.dianping.controller;

import com.timhuo.dianping.common.BusinessException;
import com.timhuo.dianping.common.CommonRes;
import com.timhuo.dianping.common.EmBusinessError;
import com.timhuo.dianping.model.CategoryModel;
import com.timhuo.dianping.model.ShopModel;
import com.timhuo.dianping.service.CategoryService;
import com.timhuo.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 门店服务（用户使用）
 * @author: Tim_Huo
 * @created: 2020/10/04 14:52
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 推荐服务 V1.0
     * 根据经度和纬度计算出距离， 距离取对数，削平对应的归一化，乘反比。
     * 取排序权重的最大值做0 - 1的归一化，然后取对应的百分比
     * @auther: Tim_Huo
     * @param: longitude 经度
     * @param: latitude 纬度
     * @return: CommonRes
     * @date: 2020/10/4 2:55 下午
     */
    @RequestMapping("/recommend")
    public CommonRes recommend(@RequestParam(name = "longitude") BigDecimal longitude,
                               @RequestParam(name = "latitude") BigDecimal latitude) throws BusinessException {
        if (longitude == null || latitude == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ShopModel> shopModelList = shopService.recommend(longitude, latitude);
        return CommonRes.create(shopModelList);
    }

    /**
     * 搜索服务 V1.0
     *
     * @auther: Tim_Huo
     * @param: longitude 经度
     * @param: latitude 纬度
     * @param: keyword 用户搜索输入的关键自字
     * @param: orderby 排序规则
     * @param: categoryId 二次过滤类目id
     * @param: tags 标签过滤
     * @return: CommonRes
     * @date: 2020/10/4 4:08 下午
     */
    @RequestMapping("/search")
    public CommonRes search(@RequestParam(name="longitude")BigDecimal longitude,
                            @RequestParam(name="latitude")BigDecimal latitude,
                            @RequestParam(name="keyword")String keyword,
                            @RequestParam(name="orderby",required = false)Integer orderby,
                            @RequestParam(name="categoryId",required = false)Integer categoryId,
                            @RequestParam(name="tags",required = false)String tags) throws BusinessException, IOException {
        if(StringUtils.isEmpty(keyword) || longitude == null || latitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // List<ShopModel> shopModelList = shopService.search(longitude,latitude,keyword,orderby,categoryId,tags);
        Map<String, Object> shopModelList = (Map<String, Object>) shopService.searchES(longitude,latitude,keyword,orderby,categoryId,tags).get("shop");
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        List<Map<String,Object>> tagsAggregation = shopService.searchGroupByTags(keyword,categoryId,tags);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("shop",shopModelList);
        resMap.put("category",categoryModelList);
        resMap.put("tags",tagsAggregation);
        return CommonRes.create(resMap);

    }

}
