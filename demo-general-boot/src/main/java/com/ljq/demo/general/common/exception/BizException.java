package com.ljq.demo.general.common.exception;

import com.ljq.demo.base.common.exception.CommonException;
import com.ljq.demo.base.common.i18n.I18nMsgUtil;
import com.ljq.demo.base.common.i18n.LanguageUtil;
import com.ljq.demo.general.common.api.SubApiMsgEnum;

import java.io.IOException;

/**
 * @Description: 业务异常
 * @Author: junqiang.lu
 * @Date: 2020/9/17
 */
public class BizException extends CommonException {

    public BizException(SubApiMsgEnum responseEnum) {
        this.key = responseEnum.getKey();
        String msg;
        try {
            msg = I18nMsgUtil.getMessage(LanguageUtil.get(), responseEnum.getKey(), responseEnum.getDefaultMsg());
        } catch (IOException e) {
            msg = responseEnum.getDefaultMsg();
        }
        this.message = msg;
    }
}
