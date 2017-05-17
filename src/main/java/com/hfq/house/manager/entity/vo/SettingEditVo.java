package com.hfq.house.manager.entity.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SettingEditVo {

	private String houseSellId;
	private Integer roomId;
	private String settingDesc;
	private Integer settingId;
	private Integer checked = 0;// 是否选择 1 选中，0 不选中
	private Integer isDelete = 0;// 是否选择 1 删除，0 有效
}
