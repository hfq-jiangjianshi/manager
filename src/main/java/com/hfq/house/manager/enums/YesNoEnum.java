package com.hfq.house.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum YesNoEnum {
	YES(1, "是"), NO(0, "否");

	private int value;
	private String name;

	private YesNoEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public static String getName(Integer value) {
		if (value != null) {
			for (YesNoEnum item : YesNoEnum.values()) {
				if (item.value == value) {
					return item.name;
				}
			}
		}
		return "";
	}

	public static List getItemList() {
		List dataList = new ArrayList();
		for (YesNoEnum item : YesNoEnum.values()) {
			Map map = new HashMap();
			map.put(item.value, item.name);
			dataList.add(map);
		}
		return dataList;
	}
		
}