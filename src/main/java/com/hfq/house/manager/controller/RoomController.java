package com.hfq.house.manager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hfq.house.manager.common.Consts;
import com.hfq.house.manager.common.PagedList;
import com.hfq.house.manager.common.RespMsg;
import com.hfq.house.manager.entity.dto.RoomDetailDto;
import com.hfq.house.manager.entity.model.RoomBase;
import com.hfq.house.manager.entity.vo.RoomDetailEditVo;
import com.hfq.house.manager.service.HouseDetailService;

@RestController
@RequestMapping(path = "/room")
public class RoomController extends BaseController {

	@Resource
	HouseDetailService houseDetailService;

	private static final String SUB_PATH = "room";

	/**
	 * 跳转列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toPage")
	public ModelAndView toPage() {

		ModelAndView mv = new ModelAndView(SUB_PATH + "/roomManager");
		return mv;
	}

	/**
	 * 查询房间列表
	 * 
	 * @param detailDto
	 * @return
	 */
	@RequestMapping(value = "/queryRoomDetailList")
	public PagedList<RoomDetailDto> queryRoomDetailList(RoomDetailDto detailDto) {

		PagedList<RoomDetailDto> pageList = houseDetailService.queryRoomDetailList(detailDto);
		return pageList;
	}

	/**
	 * 跳转编辑页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(String houseSellId, String roomId) {

		RoomDetailEditVo editVo = houseDetailService.getRoomDetail(houseSellId, roomId);
		ModelAndView mv = new ModelAndView(SUB_PATH + "/roomEdit");
		mv.addObject("vo", editVo);
		return mv;
	}

	/**
	 * 更新房间信息
	 * 
	 * @param detail
	 * @param base
	 * @return
	 */
	@RequestMapping(value = "/updateRoomInfo")
	public RespMsg<Integer> updateRoomInfo(RoomBase base) {

		int count = houseDetailService.updateRoomInfo(base);
		if (count > 0) {
			return success(Consts.SUCCESS_MSG, count);
		} else {
			return fail(Consts.FAIL_MSG, count);
		}

	}
}
