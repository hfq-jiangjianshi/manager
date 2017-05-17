package com.hfq.house.manager.entity.vo;

import java.util.List;

import com.hfq.house.manager.entity.model.HouseDetail;
import com.hfq.house.manager.entity.model.HousePics;
import com.hfq.house.manager.entity.model.RoomBase;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoomDetailEditVo {

	private List<HousePics> imgsList; // 图片列表
	private List<SettingEditVo> settingList;// 配置列表
	private HouseDetail detail;
	private RoomBase base;
}
