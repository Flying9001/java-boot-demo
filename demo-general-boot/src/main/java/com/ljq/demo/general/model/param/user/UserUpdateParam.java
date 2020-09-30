package com.ljq.demo.general.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-09-04 10:27:45
 */
@Data
@ApiModel(value = "用户表修改(单条)", description = "用户表修改(单条)")
public class UserUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 主键
     **/
    @NotNull(message = "api.response.demo.user.idNotNull")
    @Min(value = 1, message = "api.response.demo.user.idMinError")
    @ApiModelProperty(value = "id 主键不能为空", name = "id", required = true, example = "1")
    private Long id;
    /**
     * 用户名
     * */
    @Length(max = 30, message = "api.response.demo.user.userNameLengthError")
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;
    /**
     * 登陆密码
     * */
    @Length(min = 8, max = 16, message = "api.response.demo.user.userPasscodeLengthError")
    @ApiModelProperty(value = "登陆密码", name = "userPasscode", required = true)
    private String userPasscode;
    /**
     * 邮箱
     * */
    @Email(message = "api.response.response.demo.user.userEmailFormatError")
    @Length(max = 50, message = "api.response.demo.user.userEmailLengthError")
    @ApiModelProperty(value = "邮箱", name = "userEmail", required = true)
    private String userEmail;



}
