package com.hfq.house.manager.entity.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HousePicEditVo {

	private String houseSellId;
	private Integer roomId;
	private Integer imgId;
	private Integer isDelete; // 1 为删除状态
	private Integer isDefault;// 1 为首图
}
