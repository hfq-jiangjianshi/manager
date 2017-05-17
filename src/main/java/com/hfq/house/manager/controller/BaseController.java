package com.hfq.house.manager.controller;

import com.hfq.house.manager.common.Consts;
import com.hfq.house.manager.common.RespMsg;

/**
 * controller 基类
 * 
 * @author jjs 2016年10月14日 下午2:31:57
 */
public class BaseController {

	/**
	 * 参数异常
	 * 
	 * @param msg
	 * @return
	 */
	public <T> RespMsg<T> badRequest(String msg) {
		RespMsg<T> resp = new RespMsg<T>();
		resp.setData(null);
		resp.setMsg(msg);
		resp.setStatus(Consts.BAD_REQUEST);
		return resp;
	}

	/**
	 * 参数异常
	 * 
	 * @param msg
	 * @param data
	 * @return
	 */
	public <T> RespMsg<T> badRequest(String msg, T data) {
		RespMsg<T> resp = new RespMsg<T>();
		resp.setData(data);
		resp.setMsg(msg);
		resp.setStatus(Consts.BAD_REQUEST);
		return resp;
	}

	/**
	 * 请求成功
	 * 
	 * @param msg
	 * @return
	 */
	public <T> RespMsg<T> success(String msg) {
		return success(msg, null);
	}

	/**
	 * 请求成功
	 * 
	 * @param msg,
	 *            data
	 * @return
	 */
	public <T> RespMsg<T> success(String msg, T data) {
		RespMsg<T> resp = new RespMsg<T>();
		resp.setStatus(Consts.STATUS_SUCCESS);
		resp.setMsg(msg);
		resp.setData(data);
		return resp;
	}

	/**
	 * 请求失败
	 * 
	 * @param msg
	 * @return
	 */
	public <T> RespMsg<T> fail(String msg) {
		return fail(msg, null);
	}

	/**
	 * 请求失败
	 * 
	 * @param msg,data
	 * @return
	 */
	public <T> RespMsg<T> fail(String msg, T data) {
		RespMsg<T> resp = new RespMsg<T>();
		resp.setStatus(Consts.STATUS_FAIL);
		resp.setMsg(msg);
		resp.setData(data);
		return resp;
	}
}
