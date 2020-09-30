package com.ljq.demo.general.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ljq.demo.base.common.api.ApiResult;
import com.ljq.demo.base.common.exception.CommonException;
import com.ljq.demo.general.model.param.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 用户表业务层接口
 * 
 * @author junqiang.lu
 * @date 2020-09-03 15:35:46
 */
public interface UserService {

	/**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
	 * @throws CommonException
     */
	ApiResult save(UserSaveParam userSaveParam) throws CommonException;

	/**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
	 * @throws CommonException
     */
	ApiResult info(UserInfoParam userInfoParam) throws CommonException;

	/**
     * 查询列表
     *
     * @param userListParam
     * @return
	 * @throws CommonException
     */
	ApiResult list(UserListParam userListParam) throws CommonException;

	/**
     * 更新(单条)
     *
     * @param userUpdateParam
     * @return
	 * @throws CommonException
     */
	ApiResult update(UserUpdateParam userUpdateParam) throws CommonException;

	/**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
	 * @throws CommonException
     */
	ApiResult delete(UserDeleteParam userDeleteParam) throws CommonException;

	/**
	 * 上传文件
	 *
	 * @param file
	 * @param uploadParam
	 * @return
	 * @throws CommonException
	 */
	ApiResult upload(MultipartFile file, UserUploadParam uploadParam) throws CommonException;

	/**
	 * 文件下载
	 *
	 * @param downloadParam
	 * @return
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 */
	ResponseEntity<byte[]> download(UserDownloadParam downloadParam) throws JSchException, SftpException, IOException;


}
