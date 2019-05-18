package com.sdau.housesManage.dao;


import com.sdau.housesManage.entity.Home;

import java.util.List;
import java.util.Map;

public interface HomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    Home selectByPrimaryKey(Integer id);

    List<Map<String, Object>> selectAll(Map<String, Object> params);

    int updateByPrimaryKey(Home record);

    int insertHomeUserLink(Map<String,Object> params);

    int deleteHomeUserLink(Integer homeId);

}