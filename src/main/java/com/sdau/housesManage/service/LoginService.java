package com.sdau.housesManage.service;

import com.sdau.housesManage.common.SingletonBean;
import com.sdau.housesManage.dao.UserMapper;
import com.sdau.housesManage.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LoginService {

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private UserMapper userMapper;
    /**
     * 登录之前将以在线的删除
     * @param account
     * @throws Exception
     */
    public void beforeLogin(String account) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session : sessions){
            User user = (User) session.getAttribute("user");
            if (user == null) continue;
            if(account.equals(user.getAccount())) {
                if(0 != user.getId()){
                    sessionDAO.delete(session);
                    break;
                }
            }
        }
    }

    /**
     * 将当前登录用户放入session管理
     * @param currentUser
     * @throws Exception
     */
    public void setAttr(Subject currentUser)throws Exception{
        User user = userMapper.getUserByLoginName((String)currentUser.getPrincipal());
        Session session = currentUser.getSession();
        session.setAttribute("user", user);
        SingletonBean.userIdSessionMaps.put(user.getId(), session);
    }

    public boolean logout(){
        boolean status = false;
        try{
            Subject currentUser = SecurityUtils.getSubject();
            if(currentUser.isAuthenticated()){
                SingletonBean.userIdSessionMaps.remove(currentUser.getSession().getAttribute("userId"));
                currentUser.logout();
            }
            status = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

}
