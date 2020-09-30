package com.ljq.demo.base.common.exception;

import lombok.Data;

/**
 * @Description: 自定义公共异常
 * @Author: junqiang.lu
 * @Date: 2020/9/4
 */
@Data
public class CommonException extends Exception {

    /**
     * 异常编码 key
     */
    protected String key;
    /**
     * 异常提示信息
     */
    protected String message;

    public CommonException() {
        super();
    }

    public CommonException(Exception e) {
        super(e);
    }

    public CommonException(String message) {
        this.message = message;
    }

    public CommonException(String key, String message) {
        this.key = key;
        this.message = message;
    }


}
