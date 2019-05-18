package com.sdau.housesManage.dao;

import com.sdau.housesManage.entity.Info;
import java.util.List;

public interface InfoMapper  {
    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    Info selectByPrimaryKey(Integer id);

    List<Info> selectAll();

    int updateByPrimaryKey(Info record);
}