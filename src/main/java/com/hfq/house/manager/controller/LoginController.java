package com.hfq.house.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfq.house.manager.common.RespMsg;
import com.hfq.house.manager.entity.model.SysUser;
import com.hfq.house.manager.service.UserService;

@Controller
public class LoginController extends BaseController{

	@Resource
	private UserService userService;

	@RequestMapping(path = "/index")
	public String index(HttpServletRequest req, HttpServletResponse res) {
		return "login";
	}

	@RequestMapping(path = "/main")
	public String main(HttpServletRequest req, HttpServletResponse res) {
		return "main";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RespMsg<SysUser> login(HttpServletRequest req) {
		
		return userService.login(req);
	}
	
	@RequestMapping(path = "/getLoginUser", method = RequestMethod.POST)
	@ResponseBody
	public RespMsg<SysUser> getLoginUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		SysUser user = (SysUser) session.getAttribute(session.getId());
		return success("获取成功",user);
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public RespMsg<SysUser> logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return success("登出成功");
	}
}
