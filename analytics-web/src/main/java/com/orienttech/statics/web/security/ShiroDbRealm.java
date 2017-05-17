package com.orienttech.statics.web.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;

import antlr.StringUtils;

import com.ctc.wstx.util.StringUtil;
import com.orienttech.statics.commons.crypt.ICrypt;
import com.orienttech.statics.commons.crypt.UnixCrypt;
import com.orienttech.statics.commons.security.SessionUser;
import com.orienttech.statics.service.model.usermng.UserBo;
import com.orienttech.statics.service.usermng.OrgDeptService;
import com.orienttech.statics.service.usermng.UserMngService;

public class ShiroDbRealm extends AuthorizingRealm {
	
	private UserMngService userMngService;
	private OrgDeptService orgDeptService;
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		String username = token.getUsername();
		String password = String.valueOf(token.getPassword());
		
		UserBo user = userMngService.findUserOfLogin(username,password);
		
		if (user == null) {
			throw new UnknownAccountException();
		}
		
		ICrypt crypt=new UnixCrypt();
		if(!crypt.matches(user.getPassword(), password)){//如果密码错误
			throw new IncorrectCredentialsException();
		}
		/**
		 * 处理其他机构
		 */
//		String otherOrgName = "";
//		String otherOrgCodes = user.getOtherOrgCode();
//		if(otherOrgCodes != null){
//			String[] otherOrgs = otherOrgCodes.split(",");
//			for(String orgCode : otherOrgs){
//				String orgName = orgDeptService.findOrgNameByOrgId(orgCode);
//				otherOrgName = orgName + ",";
//			}
//			otherOrgName = otherOrgName.substring(0, otherOrgName.length()-1);
//		}
		SessionUser shiroUser = new SessionUser();
		
		BeanUtils.copyProperties(user, shiroUser);
//		shiroUser.setOtherOrgName(otherOrgName);
		
		return new SimpleAuthenticationInfo(shiroUser, password,getName());
	}

	/**
	 * 鉴权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("admin");
		return info;
	}

	public void setUserMngService(UserMngService userMngService) {
		this.userMngService = userMngService;
	}
	
}
