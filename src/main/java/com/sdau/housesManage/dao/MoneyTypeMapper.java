package com.sdau.housesManage.dao;


import com.sdau.housesManage.entity.MoneyType;

import java.util.List;

public interface MoneyTypeMapper  {
    int deleteByPrimaryKey(Integer id);

    int insert(MoneyType record);

    MoneyType selectByPrimaryKey(Integer id);

    List<MoneyType> selectAll();

    int updateByPrimaryKey(MoneyType record);
}