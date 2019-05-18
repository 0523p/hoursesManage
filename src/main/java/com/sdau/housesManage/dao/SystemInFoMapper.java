package com.sdau.housesManage.dao;


import com.sdau.housesManage.entity.SystemInFo;

import java.util.List;

public interface SystemInFoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemInFo record);

    SystemInFo selectByPrimaryKey(Integer id);

    List<SystemInFo> selectAll();

    int updateByPrimaryKey(SystemInFo record);
}