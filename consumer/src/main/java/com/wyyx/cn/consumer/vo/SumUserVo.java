package com.wyyx.cn.consumer.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/19
 * Time: 14:29
 * Description: No Description
 */
public class SumUserVo implements Serializable {
    private Long userId;

    private String userPhone;

    private Integer isDelete;

    private String userName;

    private String userPassword;

    private Integer userLevel;

    private Date createTime;

    private Date updateTime;

    private String openid;

    private String nickname;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private String privilege;

    private String sex;

    private Integer expValue;

    private Integer userScores;

    private String ipAddress;

    private Integer vip;

    private Date birthday;

    private Integer birthIsChange;

    private String userAddr;

    private Date vipEndTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getExpValue() {
        return expValue;
    }

    public void setExpValue(Integer expValue) {
        this.expValue = expValue;
    }

    public Integer getUserScores() {
        return userScores;
    }

    public void setUserScores(Integer userScores) {
        this.userScores = userScores;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getBirthIsChange() {
        return birthIsChange;
    }

    public void setBirthIsChange(Integer birthIsChange) {
        this.birthIsChange = birthIsChange;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }
}
