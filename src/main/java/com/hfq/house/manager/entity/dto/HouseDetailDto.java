package com.hfq.house.manager.entity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class HouseDetailDto extends HouseDetail {

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
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date firstPubDate; // 第一次发布时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pubDate; // 发布(更新)时间
}
