package com.hfq.house.manager.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.hfq.house.manager.common.RespMsg;
import com.hfq.house.manager.controller.BaseController;
import com.hfq.house.manager.entity.model.SysUser;
import com.hfq.house.manager.mapper.SysUserMapper;
import com.hfq.house.manager.service.UserService;
import com.hfq.house.manager.util.EncryptUtil;

@Service
@Transactional
public class UserServiceImpl extends BaseController implements UserService {

	@Resource
	private SysUserMapper sysUserMapper;

	@Override
	public RespMsg<SysUser> login(HttpServletRequest req) {

		String account = req.getParameter("account");
		String password = req.getParameter("password");
		if (StringUtils.isEmpty(account)) {
			return fail("用户名为空");
		}
		if (StringUtils.isEmpty(password)) {
			return fail("密码为空");
		}
		String reqIp = req.getRemoteAddr();
		List<SysUser> userList = sysUserMapper.selectByAccount(account.trim());
		SysUser user = null;
		Date lastLoginTime = null;//保存上次登录时间
		if (CollectionUtils.isEmpty(userList)) {
			return fail("用户不存在");
		} else {
			user = userList.get(0);
			lastLoginTime = user.getLastLoginTime();
		}
		String encryptPassword = EncryptUtil.md5(user.getAccount() + user.getSalt() + password.trim());
		if (!encryptPassword.equals(user.getPassword())) {
			return fail("密码错误");
		}
		String token = UUID.randomUUID().toString().replaceAll("\\-", "");
		
		SysUser loginUser = new SysUser();
		loginUser.setId(user.getId());
		loginUser.setToken(token);
		loginUser.setLoginIp(reqIp);
		loginUser.setLastLoginTime(new Date());
		sysUserMapper.updateSelective(loginUser);
		
		HttpSession session = req.getSession();
		loginUser.setLastLoginTime(lastLoginTime);
		loginUser.setRealName(user.getRealName());
		session.setAttribute(session.getId(), loginUser);
		return success("登录成功", loginUser);
	}

}
