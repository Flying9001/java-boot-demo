package com.ljq.demo.general.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ljq.demo.base.common.api.ApiResult;
import com.ljq.demo.base.common.exception.CommonException;
import com.ljq.demo.base.common.util.SftpUtil;
import com.ljq.demo.general.common.api.SubApiMsgEnum;
import com.ljq.demo.general.common.config.SftpUploadConfig;
import com.ljq.demo.general.common.exception.BizException;
import com.ljq.demo.general.dao.UserDao;
import com.ljq.demo.general.model.entity.UserEntity;
import com.ljq.demo.general.model.param.user.*;
import com.ljq.demo.general.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * 用户表业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2020-09-03 15:35:46
 */
@Service("userService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Autowired
	private SftpUploadConfig uploadConfig;

	/**
	 * 保存(单条)
	 *
	 * @param userSaveParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(UserSaveParam userSaveParam) throws CommonException {
		// 请求参数获取
		UserEntity userParam = new UserEntity();
		BeanUtil.copyProperties(userSaveParam,userParam,CopyOptions.create()
				.setIgnoreNullValue(true).setIgnoreError(true));
		// TODO 业务逻辑校验与数据处理

		// 保存
		String nowTime = String.valueOf(System.currentTimeMillis());
		userParam.setUserInsertTime(nowTime);
		userParam.setUserUpdateTime(nowTime);
		userDao.insert(userParam);

		return ApiResult.success(userParam.getId());
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param userInfoParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult info(UserInfoParam userInfoParam) throws CommonException {
		UserEntity userDB = userDao.selectById(userInfoParam.getId());
		// TODO 数据处理(如脱敏处理,关联信息)


		return ApiResult.success(userDB);
	}

	/**
	 * 查询列表
	 *
	 * @param userListParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult list(UserListParam userListParam) throws CommonException {
		// 查询条件构建
		LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
		userWrapper.eq(Objects.nonNull(userListParam.getId()), UserEntity::getId, userListParam.getId())
				.like(StrUtil.isNotBlank(userListParam.getUserName()), UserEntity::getUserName, userListParam.getUserName())
				.like(StrUtil.isNotBlank(userListParam.getUserEmail()), UserEntity::getUserEmail, userListParam.getUserEmail())
				.orderBy(true, userListParam.isAscFlag(), UserEntity::getId);
		// 分页查询
		IPage<UserEntity> page = new Page<>(userListParam.getCurrentPage(), userListParam.getPageSize());
		page = userDao.selectPage(page, userWrapper);

		return ApiResult.success(page);
	}

	/**
	 * 更新(单条)
	 *
	 * @param userUpdateParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult update(UserUpdateParam userUpdateParam) throws CommonException {
		// 请求参数获取
		UserEntity userParam = new UserEntity();
		BeanUtil.copyProperties(userUpdateParam, userParam, CopyOptions.create().ignoreError().ignoreNullValue());
		// TODO 业务逻辑校验与数据处理

		// 更新对象
		userParam.setUserUpdateTime(String.valueOf(System.currentTimeMillis()));
		int countUpdate = userDao.updateById(userParam);
		if (countUpdate < 1) {
			throw new BizException(SubApiMsgEnum.DEMO_USER_NOT_EXIST);
		}

		return ApiResult.success();
	}

	/**
	 * 删除(单条)
	 *
	 * @param userDeleteParam
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult delete(UserDeleteParam userDeleteParam) throws CommonException {
		// 删除对象
		int countDelete = userDao.deleteById(userDeleteParam.getId());
		if (countDelete < 1) {
			throw new BizException(SubApiMsgEnum.DEMO_USER_NOT_EXIST);
		}

		return ApiResult.success();
	}

	/**
	 * 上传文件
	 *
	 * @param file
	 * @param uploadParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public ApiResult upload(MultipartFile file, UserUploadParam uploadParam) throws CommonException {
		if (Objects.isNull(file) || file.isEmpty()) {
			throw new BizException(SubApiMsgEnum.MISSING_UPLOAD_FILE_ERROR);
		}
		log.debug("原始文件名:{},文件大小: {} Mb", file.getOriginalFilename(), (file.getSize()/1048576));
		// 储存文件
		SftpUtil.SftpConfig sftpConfig = SftpUtil.sftpConfig();
		BeanUtil.copyProperties(uploadConfig, sftpConfig, CopyOptions.create().ignoreNullValue().ignoreError());
		String filePath;
		if (StrUtil.isNotBlank(uploadParam.getDir())) {
			uploadParam.setDir(uploadParam.getDir().replaceAll("[^a-zA-Z0-9]", ""));
		}
		try {
			filePath = SftpUtil.upload(sftpConfig,uploadParam.getDir(), System.currentTimeMillis() +
					file.getOriginalFilename(), file.getInputStream());
		} catch (Exception e) {
			log.warn("文件上传失败,{}", e.getMessage());
			throw new BizException(SubApiMsgEnum.FAIL);
		}
		return ApiResult.success(filePath);
	}

	/**
	 * 文件下载
	 *
	 * @param downloadParam
	 * @return
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 */
	@Override
	public ResponseEntity<byte[]> download(UserDownloadParam downloadParam) throws JSchException, SftpException, IOException {
		SftpUtil.SftpConfig sftpConfig = SftpUtil.sftpConfig();
		BeanUtil.copyProperties(uploadConfig, sftpConfig, CopyOptions.create().ignoreNullValue().ignoreError());
		byte[] bytes = SftpUtil.download(sftpConfig, downloadParam.getFilePath());
		HttpHeaders headers = new HttpHeaders();
		String fileName = downloadParam.getFilePath().substring(downloadParam.getFilePath().lastIndexOf("/") + 1);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
		return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
	}


}
