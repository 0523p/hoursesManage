package com.sdau.housesManage.controller;

import com.sdau.housesManage.common.CommonTools;
import com.sdau.housesManage.model.ResultModel;
import com.sdau.housesManage.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    /**
     *添加房产信息
     * @param request
     * @return
     */
    @RequestMapping("/addHome")
    public String addHome(HttpServletRequest request){
        String fromData = request.getParameter("fromData");
        Map<String,Object> params = CommonTools.jsonToObject(fromData,Map.class);
        boolean isSuccess = homeService.addHome(params);
        if (isSuccess) {
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"添加房产信息成功",""));
        }
       return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"添加房产信息失败",""));
    }

    /**
     *编辑房产信息
     * @param request
     * @return
     */
    @RequestMapping("/editHome")
    public String editHome(HttpServletRequest request){
        String fromData = request.getParameter("fromData");
        Map<String,Object> params = CommonTools.jsonToObject(fromData,Map.class);
        boolean isSuccess = homeService.editHome(params);
        if (isSuccess) {
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"编辑房产信息成功",""));
        }
        return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"编辑房产信息失败",""));
    }

    /**
     *删除房产信息
     * @param request
     * @return
     */
    @RequestMapping("/deleteHome")
    public String deleteHome(HttpServletRequest request){
        String homeId = request.getParameter("fromData");
        boolean isSuccess = homeService.deleteHome(homeId);
        if (isSuccess) {
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"删除房产信息成功",""));
        }
        return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"删除房产信息失败",""));
    }
}
