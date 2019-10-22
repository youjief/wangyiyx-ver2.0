package com.wyyx.cn.consumer.wxUser;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by GY on 2019/10/17 14:59
 */
@Data
public class WxUserLogin implements Serializable {
    private String openid;

    private String nickname;

    private String language;

    private String city;

    private String province;

    private String sex;

    private String country;

    private String headimgurl;
}
