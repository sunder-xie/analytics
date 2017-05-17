package com.orienttech.statics.service.usermng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.orienttech.statics.dao.entity.UserSso;
import com.orienttech.statics.service.model.usermng.UserBo;

public interface UserMngService {
	
	/**
	 * @param ssoId
	 * @return
	 */
	UserBo findUserBySsoId(String ssoId);
	/**
	 * 根据角色查用户
	 * @param roleId
	 * @return
	 */
	List<UserSso> findUsersByRoleId(String roleId);
	/**
	 * @param username
	 * @return
	 */
	UserBo findUserByUserName(String userName);
	/**
	 * @param username
	 * @return
	 */
	UserBo findUserOfLogin(String userName,String passwd);
	
	/**
	 * 保证ssoId唯一
	 * @param sso
	 */
	void saveUserSsoBySsoId(UserSso user);
	
	/**
	 * 修改用户角色
	 * @param loginNameArrays 多个用户id组成的字符串
	 * @param roleId 角色id
	 */
	boolean modifyUserRole(String roleId,String ssoId);
	
	/**
	 * 分页查询所有用户
	 * @return list
	 */
	Page<UserSso> findAll(String search, Integer pageNumber,
			Integer pageSize);
	
	/**
	 * 查找用户信息
	 * @param search
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Object[]> findUserSsoInfo(String search, Integer pageNumber,
			Integer pageSize);
}
