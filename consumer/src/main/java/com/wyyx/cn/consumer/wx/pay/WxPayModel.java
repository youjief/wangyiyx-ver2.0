package com.wyyx.cn.consumer.wx.pay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/15
 * Time: 13:47
 * Description: No Description
 */
@Component
@ConfigurationProperties(prefix = "wxPayConfig")
@Data
public class WxPayModel implements Serializable {
private String   appid;
    private String   mchid;
    private String   key;
    private String   unified;
    private String   notifyurl;
    private String   type;
}
