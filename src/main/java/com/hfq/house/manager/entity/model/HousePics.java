package com.hfq.house.manager.entity.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HousePics implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //主键ID（自增）
	private String houseSellId; //房源销售编号
	private Integer roomId; //房间id
	private String picRootPath; //图片服务器位置
	private String picWebPath; //图片网络路径
	private Integer picType; //图片类型
	private Integer isDefault; //是否为首图，1：首图；0：非首图
	private Integer isDelete; //该记录是否已被删除，1代表删除；0代表有效
	private Date creationDate; //创建时间
	private Date lastChangeDate; //更新时间
	
}
