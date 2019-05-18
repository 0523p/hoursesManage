package com.sdau.housesManage.controller;

import com.sdau.housesManage.common.CommonTools;
import com.sdau.housesManage.common.SingletonBean;
import com.sdau.housesManage.model.ResultModel;
import com.sdau.housesManage.service.LoginService;
import com.sdau.housesManage.shiro.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    PasswordHelper passwordHelper;

    /**
     * 登陆
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    public String userLogin(String loginName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginName,password);
        //获取当前对象
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            currentUser.logout();
        }
        boolean status = true;
        String message = "";
        try{
            //验证登录是否成功  不成功直接抛异常
            currentUser.login(token);
            loginService.beforeLogin(loginName);
            loginService.setAttr(currentUser);
        }catch (UnknownAccountException e) {
            //用户名没有找到
            message = "账号错误";
            status = false;
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            //用户名密码不匹配
            message = "密码错误";
            status = false;
            e.printStackTrace();
        } catch (Exception e) {
            //其他的登录错误
            message = "服务器异常";
            status = false;
            e.printStackTrace();
        }
        ResultModel resultModel = null;
        if(status){
            resultModel = new ResultModel(ResultModel.STATUS.OK,"登录成功","");
        }else{
            resultModel = new ResultModel(ResultModel.STATUS.ERROR,message,"");
        }
        return CommonTools.getResultJson(resultModel);
    }

    /**
     * 注销用户
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        boolean status =  loginService.logout();
        ResultModel resultModel = null;
        if(status){
            resultModel = new ResultModel(ResultModel.STATUS.OK,"注销成功","ok");
        }else{
            resultModel = new ResultModel(ResultModel.STATUS.OK,"服务器异常","error");
        }
        return CommonTools.getResultJson(resultModel);
    }

    @RequestMapping("/testingPassword")
    public String testingPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        if (passwordHelper.testingPassword(password, SingletonBean.getUserInfo()))
            return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.OK,"验证通过",""));
        return CommonTools.getResultJson(new ResultModel(ResultModel.STATUS.ERROR,"密码错误，请重新输入",""));
    }

}
