package com.timhuo.dianping.common;

import lombok.Data;

/**
 * @description: 全局异常
 * @author: Tim_Huo
 * @created: 2020/10/03 19:52
 */
@Data
public class CommonRes {

    /**
     * 请求返回的状态码
     */
    private String status;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 定义一个通用的创建返回对象的方法
     * @param result
     * @return CommonRes
     */
    public static CommonRes create(Object result){
        return CommonRes.create(result,"success");
    }

    public static CommonRes create(Object result,String status){
        CommonRes commonRes = new CommonRes();
        commonRes.setStatus(status);
        commonRes.setData(result);

        return commonRes;
    }

}
