package com.ljq.demo.general.model.param.user;

import com.ljq.demo.general.model.param.BaseListParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-09-03 15:35:46
 */
@Data
@ApiModel(value = "用户表查询列表", description = "用户表查询列表")
public class UserListParam extends BaseListParam {

    private static final long serialVersionUID = 1L;
    /**
     * id 主键
     **/
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
     * 邮箱
     * */
    @Length(max = 50, message = "api.response.demo.user.userEmailLengthError")
    @ApiModelProperty(value = "邮箱", name = "userEmail", required = true)
    private String userEmail;



}
