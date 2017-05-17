package com.hfq.house.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfq.house.manager.entity.model.HousePics;
import com.hfq.house.manager.entity.vo.HousePicEditVo;

public interface HousePicsMapper {
	
	/**
	 * 根据houseSellId查询图片数量
	 * @return
	 */
	int selectImgCountBySellId(String houseSellId);
	
	/**
	 * 查询实体
	 * @param houseSellId
	 * @return
	 */
	List<HousePics> selectHousePicsBySellId(String houseSellId);
	
	/**
	 * 选择性更新图片状态
	 * @param vo
	 * @return
	 */
	int updateImgStatusSelective(@Param("vo") HousePicEditVo vo);
	
	/**
	 * 查询实体
	 * @param roomId
	 * @return
	 */
	List<HousePics> selectRoomPicsByRoomId(String roomId);
}
