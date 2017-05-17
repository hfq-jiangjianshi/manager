package com.hfq.house.manager.entity.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HouseSetting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键ID（自增）
	private String houseSellId; // 房源销售编号
	private Integer roomId; // 房间id
	private Integer settingCode; // 配置物品id
	private Integer settingNums; // 配置物品数量
	private Integer settingPosition; // 配置物品位置，1：卧室；2公共区域
	private Integer categoryType; // 配置类型
	private String settingName; // 配置名称
	private Integer settingPrice; // 配置价格
	private Integer settingCost; // 配置成本
	private Integer isCompleted; // 是否已经配置完成
	private Date completedDate; // 配置完成时间
	private Integer isDelete; // 该记录是否已被删除，1代表删除；0代表有效
	private Date creationDate; // 创建时间
	private Date lastChangeDate; // 更新时间
}
