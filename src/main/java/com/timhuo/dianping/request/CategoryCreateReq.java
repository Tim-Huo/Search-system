package com.timhuo.dianping.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class CategoryCreateReq {
    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "iconUrl不能为空")
    private String iconUrl;

    @NotNull(message = "权重不能为空")
    private Integer sort;

}
