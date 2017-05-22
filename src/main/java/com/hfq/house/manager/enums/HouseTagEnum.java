package com.hfq.house.manager.enums;

import com.alibaba.druid.util.StringUtils;

/**
 * 房屋标签
 * @author jjs
 *
 */
public enum HouseTagEnum {
	
	
		PUB_FIRST_TIME("首次发布", 1),
		ORIENTATION_SOUTH("南向",2),
		INDEPENDENT_TOILET("独立卫生间",3),
		INDEPENDENT_BALCONY("独立阳台",4),
		DECORATION_FINE("精装修",5),
		CLOSE_TO_SUBWAY("地铁周边",6),
		FACILITIES_WELL_EQUIPPED("设施齐全",7),
		CENTRAL_HEATING("集中供暖",8),
		PERIOD_MONTH_ONE("月付",9);

	private String desc;
	private Integer code;

	private HouseTagEnum(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getCode() {
		return code;
	}

	public static String getDescByCode(Integer code) {
		if(code == null){
			return "";
		}
		for (HouseTagEnum o : HouseTagEnum.values()) {
			if (o.code == code) {
				return o.desc;
			}
		}
		return "";
	}

	public static Integer getCodeByDesc(String desc) {
		if(StringUtils.isEmpty(desc)){
			return 0;
		}
		for (HouseTagEnum o : HouseTagEnum.values()) {
			if (o.desc.equals(desc)) {
				return o.code;
			}
		}
		return 0;
	}
		
}
