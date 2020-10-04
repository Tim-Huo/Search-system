package com.timhuo.dianping.request;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
@Data
public class SellerCreateReq {

    @NotBlank(message = "商户名不能为空")
    private String name;

}
