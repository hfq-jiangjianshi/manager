package com.hfq.house.manager.service;

import javax.servlet.http.HttpServletRequest;

import com.hfq.house.manager.common.RespMsg;

public interface UserService {
	
	RespMsg login(HttpServletRequest req);
}
