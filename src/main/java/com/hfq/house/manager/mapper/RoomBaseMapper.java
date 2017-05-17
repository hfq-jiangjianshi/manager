package com.hfq.house.manager.mapper;

import org.apache.ibatis.annotations.Param;

import com.hfq.house.manager.entity.model.RoomBase;

public interface RoomBaseMapper {

	RoomBase selectRoomBaseByRoomId(String roomId);

	int updateSelective(@Param("vo") RoomBase vo);
}
