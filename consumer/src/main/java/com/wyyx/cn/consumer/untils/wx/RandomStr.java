package com.wyyx.cn.consumer.untils.wx;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/15
 * Time: 10:44
 * Description: No Description
 */
public class RandomStr {
    public static String createStr(int size){

        return UUID.randomUUID().toString().replace("-","").substring(0,size);
    }
}
