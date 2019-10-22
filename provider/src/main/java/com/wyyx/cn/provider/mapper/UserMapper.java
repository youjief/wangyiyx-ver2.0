package com.wyyx.cn.provider.mapper;

import com.wyyx.cn.provider.model.User;
import com.wyyx.cn.provider.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getAllUser(User user);

    List<User> userPhone(User user);

    int findLevel(Integer userLevel);

    int findVip(int vip);

    String findAddr(User user);

    String findIpAddr(String userName);
}