package com.hfq.house.manager.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hfq.house.manager.entity.model.SysUser;
import com.hfq.house.manager.mapper.SysUserMapper;

/**
 * 登录拦截器
 * 
 * @author jjs
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Resource
	private SysUserMapper sysUserMapper;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			String uri = req.getRequestURI();
			List<String> exculdeList = Arrays.asList("/", "/error", "/index", "/login", "/logout", "/IncJs.a");// 无需校验的URI
			if (exculdeList.contains(uri)) {
				return true;
			}
			HttpSession session = req.getSession();
			SysUser loginUser = (SysUser) session.getAttribute(session.getId());
			if (loginUser == null) {
				res.sendRedirect("/index");
				return false;
			}
			SysUser dbUser = sysUserMapper.selectByPrimaryKey(loginUser.getId());
			if (loginUser.getToken().equals(dbUser.getToken())) {
				return true;
			} else {
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/html; charset=utf-8");
				PrintWriter writer = res.getWriter();
				writer.write("登录token已过期，请重新登录");
				return false;
			}
		} else {
			return true;
		}

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView mv)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception ex)
			throws Exception {

	}
}
