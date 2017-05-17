package com.hfq.house.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hfq.house.manager.entity.vo.SettingEditVo;

public enum SettingsEnum {

	BED("床", "1", "71", SettingCategoryEnum.FURNITURE),
    SOFA("沙发", "2", "84", SettingCategoryEnum.FURNITURE),
    TABLE("电脑桌", "3", "73", SettingCategoryEnum.FURNITURE),
    WARDROBE("衣柜", "4", "72", SettingCategoryEnum.FURNITURE),
    CHAIR("椅子", "5", "", SettingCategoryEnum.FURNITURE),

    TV("电视机", "1", "76", SettingCategoryEnum.ELECTRIC),
    FRIDGE("冰箱", "2", "82", SettingCategoryEnum.ELECTRIC),
    AC("空调", "3", "74", SettingCategoryEnum.ELECTRIC),
    MICROWAVEOVEN("微波炉", "4", "78", SettingCategoryEnum.ELECTRIC),
    WASHER("洗衣机", "5", "81", SettingCategoryEnum.ELECTRIC),
    KETTLE("电水壶", "6",  "", SettingCategoryEnum.ELECTRIC),

    CURTAIN("窗帘", "1", "", SettingCategoryEnum.HOUSEHOLD),
    MATTRESS("被褥", "2", "", SettingCategoryEnum.HOUSEHOLD),
    VASE("花瓶", "3", "", SettingCategoryEnum.HOUSEHOLD),
    LAMP("台灯", "4", "", SettingCategoryEnum.HOUSEHOLD),
    DECORATION("台灯", "5", "", SettingCategoryEnum.HOUSEHOLD),

    WIFI("wifi", "1", "83", SettingCategoryEnum.OTHER);

	// 一下为模拟百度独有
	// HEATING("暖气", "2", "75", SettingCategoryEnum.OTHER),
	// GAS("燃气", "3", "77", SettingCategoryEnum.OTHER),
	// COOKER("电磁炉", "4", "79", SettingCategoryEnum.OTHER),
	// WATER_HEATER("热水器", "5", "80", SettingCategoryEnum.OTHER),
	// CABINET("橱柜", "6", "85", SettingCategoryEnum.OTHER),
	// SMOKE_EXHAUST("油烟机", "7", "86", SettingCategoryEnum.OTHER);

	private String desc;
	private String code;
	private String bdCode;
	private SettingCategoryEnum category;

	private SettingsEnum(String desc, String code, String bdCode, SettingCategoryEnum category) {
		this.desc = desc;
		this.code = code;
		this.bdCode = bdCode;
		this.category = category;
	}

	/**
	 * 
	 * @param type
	 * @param code
	 * @return
	 */
	public static String getSettingKey(int type, int code) {

		for (SettingsEnum o : SettingsEnum.values()) {
			if (o.category.getCode() == type && o.code.equals(String.valueOf(code))) {
				return o.name();
			}
		}
		return "";
	}

	/**
	 * 
	 * @param type
	 * @param code
	 * @return
	 */
	public static String getSettingDesc(int type, int code) {

		for (SettingsEnum o : SettingsEnum.values()) {
			if (o.category.getCode() == type && o.code.equals(String.valueOf(code))) {
				return o.desc;
			}
		}
		return "";
	}
	
	/**
	 * 根据中文名，获取category和code
	 * @param desc
	 * @return
	 */
	public static Map<String, Integer> getSettingTypeAndCode(String desc) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (SettingsEnum o : SettingsEnum.values()) {
			if (o.desc.equals(desc)) {
				map.put("categoryType", o.category.getCode());
				map.put("settingCode", Integer.parseInt(o.code));
				return map;
			}
		}
		return null;
	}

	/**
	 * 获取所有setting列表
	 * 
	 * @return
	 */
	public static List<SettingEditVo> getSettingList() {

		List<SettingEditVo> setVoList = new ArrayList<SettingEditVo>();
		for (SettingsEnum o : SettingsEnum.values()) {

			SettingEditVo setVo = new SettingEditVo();
			setVo.setSettingDesc(o.desc);
			setVoList.add(setVo);
		}
		return setVoList;
	}

}
