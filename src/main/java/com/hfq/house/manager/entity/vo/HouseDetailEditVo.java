package com.hfq.house.manager.entity.vo;

import java.util.List;

import com.hfq.house.manager.entity.model.HouseBase;
import com.hfq.house.manager.entity.model.HouseDetail;
import com.hfq.house.manager.entity.model.HousePics;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HouseDetailEditVo {

	private List<HousePics> imgsList; // 图片列表
	private List<SettingEditVo> settingList;// 配置列表
	private HouseDetail detail;
	private HouseBase base;
}
