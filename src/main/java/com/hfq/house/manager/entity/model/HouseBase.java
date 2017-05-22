package com.hfq.house.manager.entity.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HouseBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键ID（自增）
	private String houseSellId; // 房源销售编号
	private Integer isSale; // 0:未上架；1：上架
	private Integer status; // 房源状态
	private String ext400; // 400分机号
	private String houseComment; // 房源描述

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date canCheckinDate; // 可入住时间
	private Integer rentPriceMonth; // 月租金
	private Integer rentPriceDay; // 日租金
	private Integer serviceFee; // 服务费或中介费
	private Integer depositFee; // 押金
	private Integer depositMonth; // 押金押几个月
	private Integer periodMonth; // 每次付几个月的租金
	private Integer companyId; // 公司id
	private String companyName; // 公司名称
	private Integer agencyId; // 经纪人id
	private String agencyPhone; // 经纪人电话
	private String agencyName; // 经纪人姓名
	private String agencyIntroduce; // 经纪人介绍
	private Integer agencyGender; // 经纪人性别
	private String agencyAvatar; // 经纪人头像
	private Integer approvedId; // 审核房源的人员id
	private Date firstPubDate; // 第一次上架时间
	private Date pubDate; // 上架时间
	private Integer hasKey; // 是否有钥匙；1:有；0:无
	private Integer isDelete; // 该记录是否已被删除，1代表删除；0代表有效
	private Date creationDate; // 创建时间
	private Date lastChangeDate; // 更新时间
	private Integer sourceFlag;// 来源标识 0:中介 1：抓取

}
