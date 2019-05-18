package com.sdau.housesManage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdau.housesManage.common.CommonTools;
import com.sdau.housesManage.common.DataTablePager;
import com.sdau.housesManage.common.SingletonBean;
import com.sdau.housesManage.entity.User;
import com.sdau.housesManage.model.ResultModel;
import com.sdau.housesManage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/selectByPrimaryKey")
    public String selectByPrimaryKey() {
        return CommonTools.getResultJson(SingletonBean.getUserInfo());
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
        List<User> records = userService.selectAll(params);
        PageInfo pgInfo = new PageInfo(records);
        DataTablePager page = new DataTablePager();
        page.setDataResult(records);
        page.setiTotalRecords(pgInfo.getTotal());
        page.setiTotalDisplayRecords(pgInfo.getTotal());
        page.setiDisplayLength(iDisplayLength);
        page.setsEcho(sEcho);
        return CommonTools.getResultJson(page);
    }

    @RequestMapping("/deleteByPrimaryKey")
    public String deleteByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (userService.deleteByPrimaryKey(Integer.valueOf(id)))
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK, "删除成功", ""));
        return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR, "删除失败", ""));
    }

    /**
     * 注册用户
     * @param request
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request) {
        String formData = request.getParameter("formData");
        User user = CommonTools.jsonToObject(formData, User.class);

        //效验登录名是否被占用
        if(!userService.checkAccount(user.getAccount()))
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"账号已存在,请更换",""));

        if(userService.addUser(user)){
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"用户注册成功",""));
        }else{
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"服务器异常",""));
        }
    }

    @RequestMapping("/selectUserList")
    public String selectUserList() {
        Map<String, Object> params = new HashMap<>();
        List<User> records = userService.selectAll(params);
        return CommonTools.getResultJson(records);
    }

}
