package com.ljq.demo.general.interceptor;

import com.ljq.demo.base.common.i18n.LanguageUtil;
import com.ljq.demo.general.common.api.SubApiMsgEnum;
import com.ljq.demo.general.common.exception.BizException;
import com.ljq.demo.general.common.util.TokenValidator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 自定义拦截器
 * @Author: junqiang.lu
 * @Date: 2020/9/4
 */
public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置上下文语言环境
        LanguageUtil.setContextLanguage(request);
        // Token 校验
        if (!TokenValidator.validateToken(request)) {
            throw new BizException(SubApiMsgEnum.TOKEN_IS_NULL);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // post

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // after

    }


}
