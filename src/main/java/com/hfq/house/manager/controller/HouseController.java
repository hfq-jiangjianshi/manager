package com.hfq.house.manager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hfq.house.manager.common.Consts;
import com.hfq.house.manager.common.PagedList;
import com.hfq.house.manager.common.RespMsg;
import com.hfq.house.manager.entity.dto.HouseDetailDto;
import com.hfq.house.manager.entity.model.HouseBase;
import com.hfq.house.manager.entity.model.HouseDetail;
import com.hfq.house.manager.entity.vo.HouseDetailEditVo;
import com.hfq.house.manager.entity.vo.HousePicEditVo;
import com.hfq.house.manager.entity.vo.SettingEditVo;
import com.hfq.house.manager.service.HouseDetailService;

@RestController
@RequestMapping(path = "/house")
public class HouseController extends BaseController {

	@Resource
	HouseDetailService houseDetailService;

	private static final String SUB_PATH = "house";

	/**
	 * 跳转列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toPage")
	public ModelAndView toPage() {

		ModelAndView mv = new ModelAndView(SUB_PATH + "/houseManager");
		return mv;
	}

	@RequestMapping(value = "/queryHouseDetailList")
	public PagedList<HouseDetailDto> queryHouseDetailList(HouseDetailDto detailDto) {

		PagedList<HouseDetailDto> pageList = houseDetailService.queryHouseDetailList(detailDto);
		return pageList;
	}

	/**
	 * 跳转编辑页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(String houseSellId) {

		HouseDetailEditVo editVo = houseDetailService.getHouseDetail(houseSellId);

		ModelAndView mv = new ModelAndView(SUB_PATH + "/HouseEdit");
		mv.addObject("vo", editVo);
		return mv;
	}

	/**
	 * 更新房源信息
	 * 
	 * @param detail
	 * @param base
	 * @return
	 */
	@RequestMapping(value = "/updateHouseInfo")
	public RespMsg<Integer> updateHouseInfo(HouseDetail detail, HouseBase base) {

		int count = houseDetailService.updateHouseInfo(detail, base);
		return success(Consts.SUCCESS_MSG, count);
	}

	/**
	 * 添加或去除房价配置
	 * 
	 * @param setting
	 * @return
	 */
	@RequestMapping(value = "/updateOrAddSetting")
	public RespMsg<Integer> updateOrAddSetting(SettingEditVo setting) {

		int value = houseDetailService.updateOrAddSetting(setting);
		return success(Consts.SUCCESS_MSG, value);
	}

	/**
	 * 选择性更新图片状态
	 * 
	 * @param pic
	 * @return
	 */
	@RequestMapping(value = "/setImgDefaultOrDelete")
	public RespMsg<Integer> setImgDefaultOrDelete(HousePicEditVo pic) {

		int value = houseDetailService.setImgDefaultOrDelete(pic);
		if (value > 0) {
			return success(Consts.SUCCESS_MSG, value);
		} else {
			return fail(Consts.FAIL_MSG, value);
		}
	}

}
