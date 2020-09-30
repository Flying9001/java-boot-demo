package com.ljq.demo.general.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljq.demo.general.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户表
 * 
 * @author junqiang.lu
 * @date 2020-09-03 15:35:46
 */
@Repository
public interface UserDao extends BaseMapper<UserEntity> {
	
}
