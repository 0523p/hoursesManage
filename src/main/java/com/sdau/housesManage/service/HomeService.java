package com.sdau.housesManage.service;

import com.sdau.housesManage.dao.HomeMapper;
import com.sdau.housesManage.entity.Home;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeService {

    @Autowired
    private HomeMapper homeDao;

    /**
     * 添加房产信息
     * @param params
     * @return
     */
    public boolean addHome(Map<String,Object> params) {
        Home home = new Home();
        //楼号
        home.setStoreynum(params.get("storeynum").toString());
        //单元号
        home.setUnitnum(params.get("unitnum").toString());
        //门牌号
        home.setHousenum(params.get("housenum").toString());
        //户型
        home.setType(params.get("type").toString());
        //面积
        home.setArea(params.get("area").toString());
        //地址
        home.setAddress(params.get("address").toString());
        //备注
        home.setRemark(params.get("remark").toString());

        if (homeDao.insert(home) > 0) {
            if (Strings.isNotBlank((String) params.get("userId"))) {
                Map<String, Object> homeUserLink = new HashMap<>();
                homeUserLink.put("homeId", home.getId());
                homeUserLink.put("userId", params.get("userId"));
                return homeDao.insertHomeUserLink(homeUserLink) > 0;
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @param params
     * @return
     */
    public boolean editHome(Map<String,Object> params) {
        Home home = new Home();
        //楼号
        home.setStoreynum(params.get("storeynum").toString());
        //单元号
        home.setUnitnum(params.get("unitnum").toString());
        //门牌号
        home.setHousenum(params.get("housenum").toString());
        //户型
        home.setType(params.get("type").toString());
        //面积
        home.setArea(params.get("area").toString());
        //地址
        home.setAddress(params.get("address").toString());
        //备注
        home.setRemark(params.get("remark").toString());
        home.setId(Integer.valueOf(params.get("homeId").toString()));
        homeDao.updateByPrimaryKey(home);
        //删除关联关系重新添加
        homeDao.deleteHomeUserLink(home.getId());
        if (Strings.isNotBlank((String) params.get("userId"))) {
            Map<String, Object> homeUserLink = new HashMap<>();
            homeUserLink.put("homeId", home.getId());
            homeUserLink.put("userId", params.get("userId"));
            return homeDao.insertHomeUserLink(homeUserLink) > 0;
        }
        return true;
    }

    /**
     * 删除房产
     * @param homeId
     * @return
     */
    @Transactional
    public boolean deleteHome(String homeId) {
        Integer id = Integer.valueOf(homeId);
        //删除房产数据
        homeDao.deleteByPrimaryKey(id);
        //删除关系
        homeDao.deleteHomeUserLink(id);
        return true;
    }

    public List<Map<String, Object>> selectAll(Map<String, Object> params) {
        return homeDao.selectAll(params);
    }

    public Home selectByPrimaryKey(int id) {
        return homeDao.selectByPrimaryKey(id);
    }
}
