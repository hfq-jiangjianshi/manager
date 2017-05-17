package com.hfq.house.manager.entity.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoomBase {

	private Integer id; // 主键ID（自增）
	private String houseSellId; // 房源销售编号
	private Integer status; // 房间状态 0未上架 2待出租 3已出租
	private Double area; // 建筑面积
	private String roomComment; // 房间描述
	private Integer roomType; // 房间类型 主卧1 次卧10 优化间20
	private Integer roomUse; // 房间用途类型
	private Integer orientations; // 朝向 10001东 10002西 10003南 10004北 10023:西南
									// 10024:西北 10014:东北 10013:东南 10034:南北
									// 10012:东西
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date canCheckinDate; // 可入住时间
	private Integer rentPriceMonth; // 月租金
	private Integer rentPriceDay; // 日租金
	private Integer serviceFee; // 服务费或中介费
	private Integer depositFee; // 押金
	private Integer depositMonth; // 押金押几个月
	private Integer periodMonth; // 每次付几个月的租金
	private Integer approvedId; // 审核房源的人员id
	private Integer decoration; // 装修档次
	private Integer toilet; // 卫生间独立
	private Integer balcony; // 独立阳台
	private Integer insurance; // 家财险
	private String comment; // 房间描述
	private String roomCode; // 房间代号
	private String roomTag; // 房间标签
	private String productionName; // 产品名称
	private String roomName; // 房间名称
	private Date firstPubDate; // 第一次上架时间
	private Date pubDate; // 上架时间
	private Integer hasKey; // 是否有钥匙；1:有；0:无
	private Integer isDelete; // 该记录是否已被删除，1代表删除；0代表有效
	private Date creationDate; // 创建时间
	private Date lastChangeDate; // 更新时间
}
