package com.hfq.house.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfq.house.manager.entity.model.HouseSetting;

public interface HouseSettingMapper {

	List<HouseSetting> selectHouseSettingBySellId(String houseSellId);

	HouseSetting selectByPrimaryKey(Integer id);

	int updateIsDeleteById(@Param("settingId") Integer settingId, @Param("delete") Integer delete);

	int insert(HouseSetting vo);

	List<HouseSetting> selectRoomSettingByRoomId(String roomId);
}
