package com.wyyx.cn.consumer.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by GY on 2019/10/19 14:11
 */
@Data
public class PerfectVo implements Serializable {

    @ApiModelProperty(value = "手机号",required = true)
    private String userPhone;

    @ApiModelProperty(value = "名字",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String userPassword;

    @ApiModelProperty(value = "性别",required = true)
    private String sex;

    @ApiModelProperty(value = "生日",required = true)
    private Date birthday;
}
