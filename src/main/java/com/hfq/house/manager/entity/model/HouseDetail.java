package com.hfq.house.manager.entity.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author jjs
 *
 */
@Data
@ToString
public class HouseDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键ID（自增）
	private String houseSellId; // 房源销售编号
	private String buildingNo; // 楼栋编号
	private String unitNo; // 单元号
	private String flowNo; // 所在楼层
	private String flowTotal; // 总楼层
	private String houseNo; // 门牌号
	private Double area; // 建筑面积
	private Integer orientations; // 朝向
	private Integer bedroomNums; // 卧室数量
	private Integer livingroomNums; // 起居室数量
	private Integer kitchenNums; // 厨房数量
	private Integer toiletNums; // 卫生间数量
	private String province; // 省份
	private String city; // 市
	private Integer houseFunction; // 房源用途
	private String district; // 行政区
	private String bizname; // 商圈
	private String address; // 详细地址
	private String subway; // 最近地铁站
	private Integer subwayDistance; // 到最近地铁站距离
	private String busStations; // 附近公交站
	private String surround; // 周边
	private Integer cityId; // 城市id
	private Integer districtId; // 行政区id
	private Integer bizId; // 商圈id
	private String subwayLineId; // 地铁线路id
	private String subwayStationId; // 地铁站id
	private String communityName; // 小区名称
	private String buildingName; // 楼栋名称
	private Integer balconyNums; // 阳台数量
	private String baiduLo; // 百度坐标，经度
	private String baiduLa; // 百度坐标，纬度
	private Integer buildingType; // 建筑类型
	private Integer buildingYear; // 建筑时间
	private Integer toilet; // 卫生间独立
	private Integer balcony; // 独立阳台
	private Integer insurance; // 家财险
	private Integer decoration; // 装修档次
	private Integer entireRent; // 租住类型 0分租 1整租 2整分皆可
	private String comment; // 房源描述
	private String houseTag; // 房屋标签，客户自定义标签
	private String source; // 来源
	private Integer run; // 0:表示数据未经采集；1：表示数据已经采集过
	private Integer isDelete; // 该记录是否已被删除，1代表删除；0代表有效
	private Date creationDate; // 创建时间
	private Date lastChangeDate; // 更新时间

}
