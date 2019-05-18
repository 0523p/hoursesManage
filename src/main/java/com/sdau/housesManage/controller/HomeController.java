package com.sdau.housesManage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdau.housesManage.common.CommonTools;
import com.sdau.housesManage.common.DataTablePager;
import com.sdau.housesManage.model.ResultModel;
import com.sdau.housesManage.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        String formData = request.getParameter("formData");
        Map<String,Object> params = CommonTools.jsonToObject(formData, Map.class);
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
        String formData = request.getParameter("formData");
        Map<String,Object> params = CommonTools.jsonToObject(formData, Map.class);
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
        String homeId = request.getParameter("id");
        boolean isSuccess = homeService.deleteHome(homeId);
        if (isSuccess) {
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"删除房产信息成功",""));
        }
        return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"删除房产信息失败",""));
    }

    @RequestMapping("/selectAll")
    public String selectAll(HttpServletRequest request) {
        //分页处理
        String formData = request.getParameter("formData");
        Map<String, Object> params = CommonTools.jsonToObject(formData, Map.class);

        int iDisplayStart = CommonTools.stringToNumber(request.getParameter("iDisplayStart"));
        int iDisplayLength = CommonTools.stringToNumber(request.getParameter("iDisplayLength"));
        String sEcho = request.getParameter("sEcho");
        int startNum = iDisplayStart / iDisplayLength + 1;
        PageHelper.startPage(startNum, iDisplayLength);
        List<Map<String, Object>> records = homeService.selectAll(params);
        PageInfo pgInfo = new PageInfo(records);
        DataTablePager page = new DataTablePager();
        page.setDataResult(records);
        page.setiTotalRecords(pgInfo.getTotal());
        page.setiTotalDisplayRecords(pgInfo.getTotal());
        page.setiDisplayLength(iDisplayLength);
        page.setsEcho(sEcho);
        return CommonTools.getResultJson(page);
    }

    @RequestMapping("/selectByPrimaryKey")
    public String selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id");
        return CommonTools.getResultJson(homeService.selectByPrimaryKey(Integer.valueOf(id)));
    }


}
