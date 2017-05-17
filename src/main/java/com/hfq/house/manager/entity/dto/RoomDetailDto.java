package com.hfq.house.manager.entity.dto;

import com.hfq.house.manager.entity.model.HouseDetail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author jjs
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomDetailDto extends HouseDetail{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer roomId; // 房间ID
	private Integer status; // 房源状态
	private Integer rentPriceMonth; // 月租金
	private Integer rentPriceMonthEnd; // 至月租金
	private Integer periodMonth; // 每次付几个月的租金
	private Integer depositMonth; // 押金押几个月
	private Integer imgCount; // 0:未上架；1：上架
	private Integer roomType; // 房间类型 主卧1 次卧10 优化间20
}
