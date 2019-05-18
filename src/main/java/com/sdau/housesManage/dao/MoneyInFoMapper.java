package com.sdau.housesManage.dao;


import com.sdau.housesManage.entity.MoneyInFo;

import java.util.List;

public interface MoneyInFoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MoneyInFo record);

    MoneyInFo selectByPrimaryKey(Integer id);

    List<MoneyInFo> selectAll();

    int updateByPrimaryKey(MoneyInFo record);
}