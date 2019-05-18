package com.sdau.housesManage.dao;

import com.sdau.housesManage.entity.Car;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    Car selectByPrimaryKey(Integer id);

    List<Car> selectAll();

    int updateByPrimaryKey(Car record);
}