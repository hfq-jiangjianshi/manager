package com.hfq.house.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfq.house.manager.entity.dto.HouseDetailDto;
import com.hfq.house.manager.entity.dto.RoomDetailDto;
import com.hfq.house.manager.entity.model.HouseDetail;

public interface HouseDetailMapper {

	/**
	 * 分页查询房源列表
	 * 
	 * @return
	 */
	List<HouseDetailDto> selectHouseResourceByPage(HouseDetailDto detailDto);
	
	/**
	 * 分页查询房间列表
	 * @param roomDto
	 * @return
	 */
	List<RoomDetailDto> selectRoomResourceByPage(RoomDetailDto roomDto);

	HouseDetail selectHouseDetailBySellId(String houseSellId);

	int updateSelective(@Param("vo") HouseDetail vo);
}
