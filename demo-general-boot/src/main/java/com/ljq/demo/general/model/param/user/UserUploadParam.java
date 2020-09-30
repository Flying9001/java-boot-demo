package com.ljq.demo.general.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @Description: 用户上传文件
 * @Author: junqiang.lu
 * @Date: 2020/9/7
 */
@Data
@ApiModel(value = "用户上传文件", description = "用户上传文件")
public class UserUploadParam implements Serializable {

    private static final long serialVersionUID = 7402053256874393740L;

    /**
     * 文件夹名称
     */
    @Length(max = 64, message = "文件夹名称需要控制在 64 字符以内")
    @ApiModelProperty(value = "文件件名称", name = "dir")
    private String dir;

}
