package com.ljq.demo.base.common.i18n;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 语言工具类
 * @Author: junqiang.lu
 * @Date: 2020/9/4
 */
@Slf4j
public class LanguageUtil {

    private static final String EVN_LANGUAGE = "env_language";

    private static ThreadLocal<String> currentLanguage = ThreadLocal.withInitial(() -> LanguageEnum.ZH_CN.getLanguage());

    private LanguageUtil() {
    }

    /**
     * 设置上下文语言环境
     *
     * @param request
     */
    public static void setContextLanguage(HttpServletRequest request) {
        String language = LanguageEnum.getLanguage(request.getHeader(EVN_LANGUAGE)).getLanguage();
        log.debug("context language: {}", language);
        currentLanguage.set(language);
    }

    /**
     * 移除上下文语言
     */
    public static void unset() {
        currentLanguage.remove();
    }

    /**
     * 获取上下文语言
     *
     * @return
     */
    public static String get() {
        return currentLanguage.get();
    }
}
