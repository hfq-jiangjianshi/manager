package com.hfq.house.manager.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
	public RespMsg login(HttpServletRequest req) {

		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String reqIp = req.getRemoteAddr();

		List<SysUser> userList = sysUserMapper.selectByAccount(account);
		SysUser user = null;
		if (!CollectionUtils.isEmpty(userList)) {
			user = userList.get(0);
		}
		String encryptPassword = EncryptUtil.md5(user.getAccount() + user.getSalt() + password);
		if (!user.getPassword().equals(encryptPassword)) {
			return fail("用户名或密码错误");
		}
		String token = UUID.randomUUID().toString().replaceAll("\\-", "");
		user.setToken(token);
		user.setLoginIp(reqIp);
		user.setLastLoginTime(new Date());
		sysUserMapper.updateSelective(user);
		return success("登录成功", user);
	}

}
