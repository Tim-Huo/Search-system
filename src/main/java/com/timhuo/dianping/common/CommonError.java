package com.timhuo.dianping.common;

import lombok.Data;

/**
 * @description: 定义全局错误字段
 * @author: Tim_Huo
 * @created: 2020/10/03 19:58
 */
@Data
public class CommonError {

    /**
     * 错误状态码
     */
    private Integer errCode;

    /**
     * 错误的状态信息
     */
    private String errMsg;

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(EmBusinessError emBusinessError){
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }

}
