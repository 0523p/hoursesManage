package com.sdau.housesManage.dao;


import com.sdau.housesManage.entity.NoticeInFo;

import java.util.List;

public interface NoticeInFoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeInFo record);

    NoticeInFo selectByPrimaryKey(Integer id);

    List<NoticeInFo> selectAll();

    int updateByPrimaryKey(NoticeInFo record);
}