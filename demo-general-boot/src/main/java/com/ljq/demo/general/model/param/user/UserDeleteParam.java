package com.ljq.demo.general.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户表参数接收类
 *
 * @author junqiang.lu
 * @date 2020-09-04 10:45:01
 */
@Data
@ApiModel(value = "用户表删除(单条)", description = "用户表删除(单条)")
public class UserDeleteParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id 主键
     **/
    @NotNull(message = "api.response.demo.user.idNotNull")
    @Min(value = 1, message = "api.response.demo.user.idMinError")
    @ApiModelProperty(value = "id 主键不能为空", name = "id", required = true, example = "1")
    private Long id;

}
