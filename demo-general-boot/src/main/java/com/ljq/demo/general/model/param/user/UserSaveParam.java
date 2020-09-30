package com.ljq.demo.general.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-09-03 15:35:46
 */
@Data
@ApiModel(value = "用户表保存(单条)", description = "用户表保存(单条)")
public class UserSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     * */
    @NotBlank(message = "api.response.demo.user.userNameNotNull")
    @Length(max = 30, message = "api.response.demo.user.userNameLengthError")
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;
    /**
     * 登陆密码
     * */
    @NotBlank(message = "api.response.demo.user.userPasscodeNotNull")
    @Length(min = 8, max = 16, message = "api.response.demo.user.userPasscodeLengthError")
    @Pattern(regexp = "^[a-zA-z0-9]{8,16}$", message = "api.response.demo.user.userPasscodeFormatError")
    @ApiModelProperty(value = "登陆密码", name = "userPasscode", required = true)
    private String userPasscode;
    /**
     * 邮箱
     * */
    @NotBlank(message = "api.response.demo.user.userEmailNotNull")
    @Email(message = "api.response.response.demo.user.userEmailFormatError")
    @Length(max = 50, message = "api.response.demo.user.userEmailLengthError")
    @ApiModelProperty(value = "邮箱", name = "userEmail", required = true)
    private String userEmail;

}
