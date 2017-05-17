package com.hfq.house.manager.service;

import com.hfq.house.manager.common.PagedList;
import com.hfq.house.manager.entity.dto.HouseDetailDto;
import com.hfq.house.manager.entity.dto.RoomDetailDto;
import com.hfq.house.manager.entity.model.HouseBase;
import com.hfq.house.manager.entity.model.HouseDetail;
import com.hfq.house.manager.entity.model.RoomBase;
import com.hfq.house.manager.entity.vo.HouseDetailEditVo;
import com.hfq.house.manager.entity.vo.HousePicEditVo;
import com.hfq.house.manager.entity.vo.RoomDetailEditVo;
import com.hfq.house.manager.entity.vo.SettingEditVo;

public interface HouseDetailService {

	/**
	 * 分页查询房源列表
	 * 
	 * @param req
	 * @return
	 */
	PagedList<HouseDetailDto> queryHouseDetailList(HouseDetailDto detailDto);

	/**
	 * 获取房源详情
	 * 
	 * @param houseSellId
	 * @return
	 */
	HouseDetailEditVo getHouseDetail(String houseSellId);

	/**
	 * 获取房源详情
	 * 
	 * @param houseSellId
	 * @param roomId
	 * @return
	 */
	RoomDetailEditVo getRoomDetail(String houseSellId, String roomId);

	/**
	 * 保存房源信息
	 * 
	 * @param detail
	 * @param base
	 * @return
	 */
	int updateHouseInfo(HouseDetail detail, HouseBase base);

	/**
	 * 更新房源或房间配置
	 * 
	 * @param setting
	 * @return
	 */
	int updateOrAddSetting(SettingEditVo setting);

	/**
	 * 选择性更新图片状态
	 * 
	 * @param vo
	 * @return
	 */
	int setImgDefaultOrDelete(HousePicEditVo vo);

	/**
	 * 查询房间列表
	 * 
	 * @param detailDto
	 * @return
	 */
	PagedList<RoomDetailDto> queryRoomDetailList(RoomDetailDto detailDto);
	
	/**
	 * 更新roomId
	 * @param base
	 * @return
	 */
	int updateRoomInfo(RoomBase base);

}
