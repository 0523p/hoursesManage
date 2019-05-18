package com.sdau.housesManage.dao;

import com.sdau.housesManage.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int insert(User record);

    User getUserByAccount(String account);

    List<User> selectAll(Map<String, Object> params);

    int deleteByPrimaryKey(int id);

}
