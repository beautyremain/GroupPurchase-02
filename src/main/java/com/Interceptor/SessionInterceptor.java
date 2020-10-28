package com.Interceptor;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/23 17:07
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

/**
 * 对某些接口进行放行
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        //request.getParameter()
        //排除sql注入
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (int i=0;i<values.length;i++) {

                values[i] = clearXss(values[i]);
            }
        }
        //首页路径以及登录放行
        System.out.println(request.getRequestURI());
        String page_identity="";
        if(request.getRequestURI().contains("/")) {
            page_identity = request.getRequestURI().split("/")[1];
        }
        System.out.println(page_identity);
        if ("log".equals(page_identity) || "test".equals(page_identity) || "register".equals(page_identity)) {
            System.out.println("pass interceptor success");
            return true;
        }

        //重定向
        Object user_name = request.getSession().getAttribute("user_name");
        String identity = (String) request.getSession().getAttribute("identity");
        //通过这种方式感觉有些不靠谱，改为使用URi判断似乎好一些
//        String page_identity=(String) request.getParameter("identity");//identity

        if (null == user_name) {

            response.sendRedirect("/redirectToLog.txt");
            System.out.println("pass interceptor fail");
            return false;
        }
        if(!identity.equals(page_identity)){
            //身份不匹配
            System.out.println(identity+","+page_identity);
            request.getSession().removeAttribute("user_name");
            request.getSession().removeAttribute("identity");
            System.out.println("pass interceptor fail because of the identity: ");
            response.sendRedirect("/redirectToLog.txt");
            return false;
        }
        System.out.println("pass interceptor success by session");
        return true;
    }
    /**
     * 处理字符转义
     *
     * @param value
     * @return
     */
    private String clearXss(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "<").replaceAll(">", ">");
        value = value.replaceAll("\\(", "(").replace("\\)", ")");
        value = value.replaceAll("'", "'");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
                "\"\"");
        value = value.replace("script", "");
        return value;
    }

}