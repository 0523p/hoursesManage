package com.sdau.housesManage.service;

import com.sdau.housesManage.dao.SystemInFoMapper;
import com.sdau.housesManage.entity.SystemInFo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    private SystemInFoMapper systemInFoMapper;

    /**
     * 添加基础信息
     * @param systemInFo
     * @return
     */
    public boolean addBaseInFo(SystemInFo systemInFo) {
        if (systemInFoMapper.insert(systemInFo) != 1) {
            return false;
        }
        return true;
    }
}
