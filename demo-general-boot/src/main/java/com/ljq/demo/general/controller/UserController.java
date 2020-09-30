package com.ljq.demo.general.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ljq.demo.base.common.api.ApiResult;
import com.ljq.demo.base.common.exception.CommonException;
import com.ljq.demo.base.common.util.SftpUtil;
import com.ljq.demo.general.model.entity.UserEntity;
import com.ljq.demo.general.model.param.user.*;
import com.ljq.demo.general.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 用户表
 * 
 * @author junqiang.lu
 * @date 2020-09-04 14:12:38
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/general/user")
@Api(value = "用户表控制层", tags = "用户表控制层")
public class UserController {

	@Autowired
	private UserService userService;

    /**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表保存(单条)",  notes = "用户表保存(单条)")
    public ResponseEntity<ApiResult<Long>> save(@Validated @RequestBody UserSaveParam userSaveParam) throws CommonException {
        ApiResult apiResult = userService.save(userSaveParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表查询详情(单条)",  notes = "用户表查询详情(单条)")
    public ResponseEntity<ApiResult<UserEntity>> info(@Validated UserInfoParam userInfoParam) throws CommonException {
        ApiResult apiResult = userService.info(userInfoParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表查询列表",  notes = "用户表查询列表")
    public ResponseEntity<ApiResult<IPage<UserEntity>>> list(@Validated UserListParam userListParam) throws CommonException {
        ApiResult apiResult = userService.list(userListParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 修改(单条)
     *
     * @param userUpdateParam
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表修改(单条)",  notes = "用户表修改(单条)")
    public ResponseEntity<ApiResult> update(@Validated @RequestBody UserUpdateParam userUpdateParam) throws CommonException {
        ApiResult apiResult = userService.update(userUpdateParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表删除(单条)",  notes = "用户表删除(单条)")
    public ResponseEntity<ApiResult> delete(@Validated @RequestBody UserDeleteParam userDeleteParam) throws CommonException {
        ApiResult apiResult = userService.delete(userDeleteParam);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 文件上传
     *
     * @param file
     * @param uploadParam
     * @return
     * @
     */
    @PostMapping(value = "/upload", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public ResponseEntity<ApiResult> upload(@Validated @NotNull @RequestParam("file") MultipartFile file,
                                            UserUploadParam uploadParam) throws CommonException {
        ApiResult apiResult = userService.upload(file, uploadParam);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiResult, headers, HttpStatus.OK);
    }

    /**
     * 文件下载
     *
     * @param downloadParam
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/download", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<byte[]> download(UserDownloadParam downloadParam) throws JSchException, SftpException, IOException {
        ResponseEntity<byte[]> responseEntity = userService.download(downloadParam);
        return responseEntity;
    }





}
