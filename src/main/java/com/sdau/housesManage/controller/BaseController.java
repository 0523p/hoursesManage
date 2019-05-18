package com.sdau.housesManage.controller;

import com.sdau.housesManage.common.CommonTools;
import com.sdau.housesManage.entity.SystemInFo;
import com.sdau.housesManage.model.ResultModel;
import com.sdau.housesManage.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//小区基础信息接口
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/addBaseInFo")
    public String addBaseInFo(HttpServletRequest request) {
        String formData = request.getParameter("formData");
        SystemInFo systemInFo = CommonTools.jsonToObject(formData,SystemInFo.class);
        boolean isSucess = baseService.addBaseInFo(systemInFo);
        if (isSucess) {
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"添加成功",""));
        }
        return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"添加失败",""));
    }
}
