package com.hfq.house.manager.mapper;

import org.apache.ibatis.annotations.Param;

import com.hfq.house.manager.entity.model.HouseBase;

public interface HouseBaseMapper {

	HouseBase selectHouseBaseBySellId(String houseSellId);
	
	int updateSelective(@Param("vo") HouseBase vo);
}
