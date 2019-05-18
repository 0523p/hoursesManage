package com.sdau.housesManage.dao;

import com.sdau.housesManage.entity.User;

public interface UserMapper {

    User getUserByLoginName(String account);

}
