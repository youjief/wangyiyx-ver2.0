//package com.wyyx.cn.consumer.wx.pay.api;
//
//import com.google.common.collect.Maps;
//
//import com.wyyx.cn.consumer.untils.wx.RandomStr;
//import com.wyyx.cn.consumer.untils.wx.WxPayUtils;
//import com.wyyx.cn.consumer.wx.pay.WxPayModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
//
//
///**
// * Created with IntelliJ IDEA.
// * User: 91917
// * Date: 2019/10/15
// * Time: 13:55
// * Description: No Description
// */
//@Component
//public class WxPayAPI {
//    @Autowired
//    private WxPayModel payModel;
//
//    public String requestPay(WxPayVo wxPayVo) throws Exception {
//
//        SortedMap<String, String> param = new TreeMap<String, String>();
//        param.put("appid", payModel.getAppid());
//        param.put("mch_id", payModel.getMchid());
//        param.put("nonce_str", RandomStr.createStr(32));
//        param.put("sign", payModel.getKey());
//        param.put("body", wxPayVo.getBody());
//        param.put("out_trade_no", wxPayVo.getOut_trade_no());
//        param.put("total_fee", String.valueOf(wxPayVo.getTotal_fee()));
//        param.put("spbill_create_ip", "192.168.1.139");
//        param.put("notify_url", payModel.getNotifyurl());
//        param.put("trade_type", payModel.getType());
//
//        //签名188
//        String sign = generateSignature(param, payModel.getKey());
//
//        param.put("sign", sign);
//        //将map转成xml
//        String payXml = mapToXml(param);
//        //请求微信统一下单接口（post请求）
//
//        String resultData = doPost(payModel.getUnified(), payXml, 5000);
//        //方法请求之后将返回的xml转为map使用xmltoMap方法
//
//        Map<String, String> data = xmlToMap(resultData);
//        //最后从map中获取get("code_url")
//        if (data != null) {
//            return data.get("code_url");
//
//        }
//        return null;
//    }
//
//
//    public String returnPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        InputStream inputStream = request.getInputStream();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//        StringBuffer sb = new StringBuffer();
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            sb.append(line);
//        }
//        bufferedReader.close();
//        inputStream.close();
//        Map<String, String> resultMap = WxPayUtils.xmlToMap(sb.toString());
//        //成功回调了
//        if ("SUCCESS".equals(resultMap.get("return_code"))) {
//            //验证签名与金额
//            boolean isCheckSign = WxPayUtils.checkSign(resultMap, payModel.getKey());
//            if (isCheckSign) {
//                //todo
//                //xxxx();
//                Map<String, String> rMap = Maps.newHashMap();
//                rMap.put("return_code", "SUCCESS");
//                rMap.put("return_msg", "OK");
//                String xml = WxPayUtils.mapToXml(rMap);
//                response.getWriter().write(xml);
//            }
//        }
//        return null;
//    }
//}
//
//
//
//
