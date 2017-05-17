/** 
 * Project Name: fileservice_project 
 * File Name: FileInterceptor.java 
 * Package Name: com.huifenqi.file 
 * Date: 2015年12月30日下午2:13:24 
 * Copyright (c) 2015, www.huizhaofang.com All Rights Reserved. 
 * 
 */
package com.hfq.house.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;

/*
 * 拦截器
 */
@Component
public class PageInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String page = "1"; // 第几页
		String pageSize = "20";// 每页显示几条
		if (StringUtil.isNotEmpty(request.getParameter("page"))) {
			page = request.getParameter("page");
		}
		if (StringUtil.isNotEmpty(request.getParameter("pageSize"))) {
			pageSize = request.getParameter("pageSize");
		}
		if (StringUtil.isNotEmpty(request.getParameter("page"))
				|| StringUtil.isNotEmpty(request.getParameter("pageSize"))) {
			PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
		}

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
