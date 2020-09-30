package com.ljq.demo.general.common.api;

import com.ljq.demo.base.common.api.ApiResult;
import com.ljq.demo.base.common.i18n.I18nMsgUtil;
import com.ljq.demo.base.common.i18n.LanguageUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * @Description: ApiResponse 子类
 * @Author: junqiang.lu
 * @Date: 2020/9/17
 */
public class SubApiResult<T> extends ApiResult<T> {

    private static final long serialVersionUID = 1631563143674217882L;

    /**
     * 失败
     *
     * @param apiMsgEnum 接口返回结果信息枚举类
     * @return
     */
    public static <T>ApiResult<T> fail(SubApiMsgEnum apiMsgEnum) {
        return fail(apiMsgEnum.getKey(), apiMsgEnum.getDefaultMsg());
    }

    /**
     * 失败,返回信息国际化
     *
     * @param apiMsgEnum 接口返回结果信息枚举类
     * @return
     */
    public static <T>ApiResult<T> failI18n(SubApiMsgEnum apiMsgEnum) {
        if (Objects.isNull(apiMsgEnum)) {
            apiMsgEnum = SubApiMsgEnum.FAIL;
        }
        String message;
        try {
            message = I18nMsgUtil.getMessage(LanguageUtil.get(), apiMsgEnum.getKey(), apiMsgEnum.getDefaultMsg());
        } catch (IOException e) {
            message = DEFAULT_ERROR_MESSAGE;
        }
        return fail(apiMsgEnum.getKey(), message);
    }
}
