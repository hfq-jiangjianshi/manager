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
public class HouseDetailDto extends HouseDetail{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer status; // 房源状态
	private Integer rentPriceMonth; // 月租金
	private Integer rentPriceMonthEnd; // 至月租金
	private Integer periodMonth; // 每次付几个月的租金
	private Integer depositMonth; // 押金押几个月
	private String companyName; // 公司名称
	private Integer isSale; // 0:未上架；1：上架
	private Integer imgCount; // 0:未上架；1：上架
}
