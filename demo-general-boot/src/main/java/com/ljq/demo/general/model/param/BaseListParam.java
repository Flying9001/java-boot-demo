package com.ljq.demo.general.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Description: 列表查询基类
 * @Author: junqiang.lu
 * @Date: 2020/9/6
 */
@Data
public class BaseListParam implements Serializable {

    private static final long serialVersionUID = 3630949946964004278L;

    /**
     * 当前页
     */
    @Min(value = 1, message = "api.response.demo.common.listCurrentPageMinError")
    @ApiModelProperty(value = "当前页,至少为 1,默认为 1", name = "currPage", example = "1")
    private Integer currentPage = 1;
    /**
     * 每页显示条数
     */
    @Min(value = 3, message = "api.response.demo.common.listPageSizeMinError")
    @Max(value = 100, message = "api.response.demo.common.listPageSizeMaxError")
    @ApiModelProperty(value = "每页显示条数,每页至少展示 3 条结果,最多为 100 条,默认为 5 ", example = "5")
    private Integer pageSize = 5;
    /**
     * 排序规则,true:升序,false:降序(默认)
     */
    @ApiModelProperty(value = "排序规则,true:升序,false:降序(默认)", example = "desc")
    private boolean ascFlag = false;

}
