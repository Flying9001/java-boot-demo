package com.ljq.demo.general.common.api;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * @Description: 接口返回结果信息枚举类
 * @Author: junqiang.lu
 * @Date: 2020/9/3
 */
@Getter
@ToString
public enum SubApiMsgEnum {

    /**
     * 基础提示信息
     */
    SUCCESS("api.response.success","成功"),
    FAIL("api.response.fail","失败"),
    TOKEN_IS_NULL("api.response.tokenIsNull","Token 为空"),
    PARAM_CONVERT_ERROR("api.response.paramConvertError", "参数转换错误,请输入正确格式的参数"),
    HTTP_METHOD_NOT_SUPPORT_ERROR("api.response.httpMethodNotSupportError","HTTP 请求方式错误"),
    HTTP_MEDIA_TYPE_NOT_SUPPORT_ERROR("api.response.httpMediaTypeNotSupportError", "网络请求参数格式错误"),
    MISSING_UPLOAD_FILE_ERROR("api.response.missingUploadFileError", "缺失上传文件"),
    MAX_UPLOAD_SIZE_ERROR("api.response.maxUploadSizeError", "上传文件过大"),
    CANNOT_CREATE_TRANSACTION_ERROR("api.response.cannotCreateTractionError", "无法创建数据库连接"),

    /**
     * 演示项目-公共模块
     */
    DEMO_COMMON_LIST_CURRENT_PAGE_MIN_ERROR("api.response.demo.common.listCurrentPageMinError", "当前页最少为 1"),
    DEMO_COMMON_LIST_PAGE_SIZE_MIN_ERROR("api.response.demo.common.listPageSizeMinError", "每页最少显示 3 条"),
    DEMO_COMMON_LIST_PAGE_SIZE_MAX_ERROR("api.response.demo.common.listPageSizeMaxError", "每页最多显示 100 条"),

    /**
     * 演示项目-用户模块
     */
    DEMO_USER_ID_NOT_NULL("api.response.demo.user.idNotNull", "用户 ID 不能为空"),
    DEMO_USER_NAME_NOT_NULL("api.response.demo.user.userNameNotNull", "用户名不能为空"),
    DEMO_USER_NAME_LENGTH_ERROR("api.response.demo.user.userNameLengthError", "用户名长度需要控制在 30 字符以内"),
    DEMO_USER_PASSCODE_NOT_NULL("api.response.demo.user.userPasscodeNotNull", "用户密码不能为空"),
    DEMO_USER_PASSCODE_LENGTH_ERROR("api.response.demo.user.userPasscodeLengthError", "用户密码长度需要控制在 8-16 字符以内"),
    DEMO_USER_PASSCODE_FORMAT_ERROR("api.response.demo.user.userPasscodeFormatError", "用户密码格式错误,请输入字母和数字的组合"),
    DEMO_USER_EMAIL_NOT_NULL("api.response.demo.user.userEmailNotNull", "用户邮箱不能为空"),
    DEMO_USER_EMAIL_FORMAT_ERROR("api.response.response.demo.user.userEmailFormatError", "用户邮箱格式错误"),
    DEMO_USER_EMAIL_LENGTH_ERROR("api.response.demo.user.userEmailLengthError", "用户邮箱长度需要控制在 50 字符以内"),
    DEMO_USER_NOT_EXIST("api.response.demo.user.notExist", "用户不存在"),
    DEMO_USER_EXISTED("api.response.demo.user.existed", "用户已经存在(注册)"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("api.response.unknownError", "未知异常");

    /**
     * 返回 key
     */
    private String key;
    /**
     * 默认提示信息
     */
    private String defaultMsg;

    SubApiMsgEnum(String key, String defaultMsg) {
        this.key = key;
        this.defaultMsg = defaultMsg;
    }

    /**
     * 通过 key 获取返回对象
     *
     * @param key
     * @return
     */
    public static SubApiMsgEnum getByKey(String key) {
        if (Objects.isNull(key) || key.trim().isEmpty()) {
            return SubApiMsgEnum.UNKNOWN_ERROR;
        }
        for (SubApiMsgEnum responseEnum : SubApiMsgEnum.values()) {
            if (responseEnum.key.equalsIgnoreCase(key)) {
                return responseEnum;
            }
        }
        return SubApiMsgEnum.UNKNOWN_ERROR;
    }

    /**
     * 更新 key
     *
     * @param responseEnum
     * @param key
     * @return
     */
    public static SubApiMsgEnum updateKey(SubApiMsgEnum responseEnum, String key) {
        responseEnum.key = key;
        return responseEnum;
    }




}
