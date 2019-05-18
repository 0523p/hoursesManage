package com.sdau.housesManage.shiro;

import com.sdau.housesManage.common.CommonTools;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SessionFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

    private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String strURL =  httpServletRequest.getRequestURI();

        Enumeration enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName = (String)enu.nextElement();
            String str = request.getParameter(paraName);
            if (sqlPattern.matcher(str).find()) {
                logger.error("参数" + paraName + ": " + str + " 含有安全隐患");
                Map<String, Object> result = new HashMap<>();
                httpServletResponse.setHeader("sessionstatus", "timeout");
                result.put("params_warning", true);
                httpServletResponse.getWriter().print(CommonTools.objectToJson(result));
                return;
            }
        }

        //设置以下请求不用检测在线状态
        if(strURL.equals("/checkIsLogin")){
            Map<String, Object> result = new HashMap<>();
            if(SecurityUtils.getSubject().isAuthenticated()){
                result.put("result_data","ok");
            }else{
                result.put("result_data","error");
            }
            httpServletResponse.getWriter().print(CommonTools.objectToJson(result));
            return;
        }
        if(!strURL.equals("/login/userLogin") && !strURL.startsWith("/commonInterface") && !strURL.startsWith("/pay") && !strURL.startsWith("/payed") && !strURL.startsWith("/interactive")) {
            //判断用户是否已验证
            if (!SecurityUtils.getSubject().isAuthenticated()) {
                httpServletResponse.setHeader("sessionstatus", "timeout");
                Map<String, Object> result = new HashMap<>();
                result.put("session_ok", false);
                httpServletResponse.getWriter().print(CommonTools.objectToJson(result));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
