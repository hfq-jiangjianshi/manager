package com.hfq.house.manager.common;

public class Consts {

    // -1，用于处理非法数据组件，防止集合为空
    public final static long   NEGATIVE_ONE          = -1l;

    //默认分页
    public final static int    DEFAULT_PAGE_SIZE     = 10;

    /**
     * 用户登录后存储在redis中的token值
     */
    public final static String USER_TOKEN_PREFIX     = "X-token";

    public final static String TYPE                  = "type";
    public final static String VALUE                 = "value";
    public final static String NAME                  = "name";

    //成功状态
    public final static int    STATUS_SUCCESS        = 0;
    //失败状态
    public final static int    STATUS_FAIL           = 1;
    //异常状态
    public final static int    STATUS_ERROR          = -1;
    //需要登录状态
    public final static int    STATUS_NEED_LOGIN     = 600;
    public final static int    BAD_REQUEST           = 400;
    public final static int    PAGE_NOT_FOUND        = 404;
    public final static int    FORBIDDEN_REQUEST     = 403;
    public final static int    SERVER_ERROR          = 500;

    public final static String BAD_REQUEST_MSG       = "请求参数异常";
    public final static String PAGE_NOT_FOUND_MSG    = "页面找不到";
    public final static String FORBIDDEN_REQUEST_MSG = "没有访问权限";
    public final static String SERVER_ERROR_MSG      = "服务器开小差啦";
    public final static String SUCCESS_MSG           = "操作成功";
    public final static String FAIL_MSG              = "操作失败";
    
    
    public static final Integer NOTFOUND = 11111;
	public static final String UNKNOWN = "unknown";

}
