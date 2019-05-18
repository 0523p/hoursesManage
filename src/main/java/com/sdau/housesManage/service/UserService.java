package com.sdau.housesManage.service;

import com.sdau.housesManage.common.CommonTools;
import com.sdau.housesManage.dao.UserMapper;
import com.sdau.housesManage.entity.User;
import com.sdau.housesManage.shiro.PasswordHelper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    public boolean checkAccount(String account) {
        return userMapper.getUserByAccount(account) == null;
    }

    @Transactional
    public boolean addUser(User user) {
        user.setType("1");
        user.setCreateTime(new Date());
        //对密码进行加密处理
        passwordHelper.encryptPassword(user);
        return userMapper.insert(user) > 0;
    }

    public List<User> selectAll(Map<String, Object> params) {
        return userMapper.selectAll(params);
    }

    public boolean deleteByPrimaryKey(int id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

}
