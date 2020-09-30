package com.ljq.demo.general.interceptor;

import com.ljq.demo.base.common.api.ApiResult;
import com.ljq.demo.base.common.constant.ExceptionConst;
import com.ljq.demo.base.common.exception.CommonException;
import com.ljq.demo.base.common.i18n.LanguageUtil;
import com.ljq.demo.general.common.api.SubApiMsgEnum;
import com.ljq.demo.general.common.api.SubApiResult;
import com.ljq.demo.general.common.constant.SubExceptionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description: 全局异常处理
 * @Author: junqiang.lu
 * @Date: 2020/9/5
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认栈轨迹深度
     */
    private static final int DEFAULT_STACK_TRACE_DEPTH = 3;
    @Value("${exceptionLogStackDepth}")
    private int exceptionLogStackDepth;

    @ExceptionHandler(value = {CommonException.class,Exception.class})
    public ResponseEntity<ApiResult<String>> exceptionHandler(Exception e, HttpServletRequest request) {
        LanguageUtil.setContextLanguage(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SubApiMsgEnum apiMsgEnum;
        switch (e.getClass().getName()) {
            case SubExceptionConst.LOCAL_BIZ_EXCEPTION:
            case ExceptionConst.LOCAL_COMMON_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.getByKey(((CommonException) e).getKey());
                break;
            case ExceptionConst.PARAM_BIND_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.getByKey(((BindException)e).getBindingResult()
                        .getAllErrors().get(0).getDefaultMessage());
                break;
            case ExceptionConst.PARAM_CONVERT_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.PARAM_CONVERT_ERROR;
                break;
            case ExceptionConst.PARAM_VALIDATE_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.getByKey(((MethodArgumentNotValidException)e).getBindingResult()
                        .getAllErrors().get(0).getDefaultMessage());
                break;
            case ExceptionConst.HTTP_METHOD_NOT_SUPPORT_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.HTTP_METHOD_NOT_SUPPORT_ERROR;
                break;
            case ExceptionConst.HTTP_MEDIA_TYPE_NOT_SUPPORT_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.HTTP_MEDIA_TYPE_NOT_SUPPORT_ERROR;
                break;
            case ExceptionConst.MISSING_UPLOAD_FILE_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.MISSING_UPLOAD_FILE_ERROR;
                break;
            case ExceptionConst.MAX_UPLOAD_SIZE_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.MAX_UPLOAD_SIZE_ERROR;
                break;
            case ExceptionConst.CANNOT_CREATE_TRANSACTION_EXCEPTION:
                apiMsgEnum = SubApiMsgEnum.CANNOT_CREATE_TRANSACTION_ERROR;
                break;
            default:
                log.warn("exception class: {}", e.getClass().getName());
                apiMsgEnum = SubApiMsgEnum.UNKNOWN_ERROR;
                break;
        }
        log.warn("key:{}, msg:{}, trace:{}",apiMsgEnum.getKey(), e.getMessage(), getStackTraceInfo(e.getStackTrace(),
                exceptionLogStackDepth > 0 ? exceptionLogStackDepth : DEFAULT_STACK_TRACE_DEPTH));
        return new ResponseEntity<>(SubApiResult.failI18n(apiMsgEnum),
                headers, HttpStatus.BAD_REQUEST);
    }

    /**
     * 获取栈轨迹信息
     *
     * @param traceElements 栈轨迹元素数组
     * @param depth 读取深度
     * @return
     */
    private String getStackTraceInfo(StackTraceElement[] traceElements, int depth) {
        if (Objects.isNull(traceElements) || traceElements.length < 1) {
            return null;
        }
        if (traceElements.length < depth) {
            depth = traceElements.length;
        }
        StringBuilder traceBuilder = new StringBuilder("\n\t");
        for (int i = 0; i < depth - 1; i++) {
            traceBuilder.append(traceElements[i]).append("\n\t");
        }
        traceBuilder.append(traceElements[depth]);
        return traceBuilder.toString();

    }
}
