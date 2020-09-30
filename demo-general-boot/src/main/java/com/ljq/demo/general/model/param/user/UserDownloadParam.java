package com.ljq.demo.general.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 文件下载
 * @Author: junqiang.lu
 * @Date: 2020/9/29
 */
@Data
@ApiModel(value = "文件下载", description = "文件下载")
public class UserDownloadParam implements Serializable {

    private static final long serialVersionUID = -4612871626349518150L;

    /**
     * 文件路径
     */
    @NotBlank(message = "文件路径不能为空")
    @ApiModelProperty(value = "文件路径", name = "filePath")
    private String filePath;

}
