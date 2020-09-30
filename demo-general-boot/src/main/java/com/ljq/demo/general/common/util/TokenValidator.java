package com.ljq.demo.general.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Token 校验类
 * @Author: junqiang.lu
 * @Date: 2020/9/4
 */
@Slf4j
public class TokenValidator {

    private static final String TOKEN_FIELD = "token";

    private TokenValidator() {
    }

    /**
     * 校验 Token
     *
     * @param request
     * @return
     */
    public static boolean validateToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_FIELD);
        if (StrUtil.isBlank(token)) {
            log.info("{}", "Token is null");
            return false;
        }
        // TODO 校验 Token


        return true;
    }
}
