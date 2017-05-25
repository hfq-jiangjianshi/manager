package com.hfq.house.manager.service.Impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.hfq.house.manager.common.PagedList;
import com.hfq.house.manager.entity.dto.HouseDetailDto;
import com.hfq.house.manager.entity.dto.RoomDetailDto;
import com.hfq.house.manager.entity.model.HouseBase;
import com.hfq.house.manager.entity.model.HouseDetail;
import com.hfq.house.manager.entity.model.HousePics;
import com.hfq.house.manager.entity.model.HouseSetting;
import com.hfq.house.manager.entity.model.RoomBase;
import com.hfq.house.manager.entity.vo.HouseDetailEditVo;
import com.hfq.house.manager.entity.vo.HousePicEditVo;
import com.hfq.house.manager.entity.vo.RoomDetailEditVo;
import com.hfq.house.manager.entity.vo.SettingEditVo;
import com.hfq.house.manager.enums.HouseTagEnum;
import com.hfq.house.manager.enums.SettingsEnum;
import com.hfq.house.manager.mapper.HouseBaseMapper;
import com.hfq.house.manager.mapper.HouseDetailMapper;
import com.hfq.house.manager.mapper.HousePicsMapper;
import com.hfq.house.manager.mapper.HouseSettingMapper;
import com.hfq.house.manager.mapper.RoomBaseMapper;
import com.hfq.house.manager.service.HouseDetailService;

@Service
@Transactional
public class HouseDetailServiceImpl implements HouseDetailService {

	@Resource
	private HouseDetailMapper houseDetailMapper;
	@Resource
	private HouseBaseMapper houseBaseMapper;
	@Resource
	private HousePicsMapper housePicsMapper;
	@Resource
	private HouseSettingMapper houseSettingMapper;
	@Resource
	private RoomBaseMapper roomBaseMapper;

	private static final Integer HUNDRED = 100;

	/**
	 * 分页查询房源列表
	 */
	@Override
	public PagedList<HouseDetailDto> queryHouseDetailList(HouseDetailDto detailDto) {

		List<HouseDetailDto> pos = houseDetailMapper.selectHouseResourceByPage(detailDto);
		Iterator<HouseDetailDto> it = pos.iterator();
		while (it.hasNext()) {
			HouseDetailDto dto = it.next();
			int imgCount = housePicsMapper.selectImgCountBySellId(dto.getHouseSellId());
			dto.setImgCount(imgCount);
		}
		PageInfo<HouseDetailDto> pageInfo = new PageInfo<HouseDetailDto>(pos);
		PagedList<HouseDetailDto> pagedList = PagedList.newMe(pageInfo);
		return pagedList;
	}

	/**
	 * 编辑房源
	 */
	@Override
	public HouseDetailEditVo getHouseDetail(String houseSellId) {

		HouseDetailEditVo editVo = new HouseDetailEditVo();
		HouseDetail detail = houseDetailMapper.selectHouseDetailBySellId(houseSellId);
		String houseTag = detail.getHouseTag();
		if (!StringUtils.isEmpty(houseTag)) {
			String tags[] = houseTag.split(",");
			StringBuffer sb = new StringBuffer();
			for (String tag : tags) {// 将标签编码转为中文
				if (!StringUtils.isEmpty(tag)) {
					String cTag = HouseTagEnum.getDescByCode(Integer.parseInt(tag));
					sb.append(cTag).append(",");
				}
			}
			if (sb.length() > 0) {
				detail.setHouseTag(sb.substring(0, sb.length() - 1));
			}
		}
		HouseBase base = houseBaseMapper.selectHouseBaseBySellId(houseSellId);
		base.setRentPriceMonth(base.getRentPriceMonth() / HUNDRED);// 月租金
		base.setDepositFee(base.getDepositFee() / HUNDRED);// 押金
		base.setServiceFee(base.getServiceFee() / HUNDRED);// 服务费或中介费
		List<HousePics> imgsList = housePicsMapper.selectPicsBySellIdAndRoomId(houseSellId, null);
		for (HousePics pic : imgsList) {
			if (StringUtils.isEmpty(pic.getPicRootPath())) {
				pic.setPicRootPath(pic.getPicWebPath());
			}
		}
		List<HouseSetting> settingList = houseSettingMapper.selectSettingBySellIdAndRoomId(houseSellId, null);

		List<SettingEditVo> allSettingList = SettingsEnum.getSettingList();
		for (SettingEditVo vo : allSettingList) {

			for (HouseSetting set : settingList) {
				String desc = SettingsEnum.getSettingDesc(set.getCategoryType(), set.getSettingCode());
				if (vo.getSettingDesc().equals(desc)) {
					vo.setChecked(1);// 是否选择 1 选中，0 不选中
					vo.setSettingId(set.getId());
				}
			}
		}
		editVo.setDetail(detail);
		editVo.setBase(base);
		editVo.setImgsList(imgsList);
		editVo.setSettingList(allSettingList);
		return editVo;
	}

	@Override
	public int updateHouseInfo(HouseDetail detail, HouseBase base) {

		int dCount = 0, bCount = 0;
		if (detail != null) {
			String houseTag = detail.getHouseTag();
			String tags[] = houseTag.split(",");
			StringBuffer sb = new StringBuffer();
			for (String tag : tags) {// 将标签编码转为中文
				Integer iTag = HouseTagEnum.getCodeByDesc(tag);
				if (iTag != 0) {
					sb.append(iTag).append(",");
				}
			}
			if (sb.length() > 1) {
				detail.setHouseTag(sb.substring(0, sb.length() - 1));
			}
			dCount = houseDetailMapper.updateSelective(detail);
		}
		if (base != null) {
			base.setRentPriceMonth(base.getRentPriceMonth() * HUNDRED);// 月租金
			base.setDepositFee(base.getDepositFee() * HUNDRED);// 押金
			base.setServiceFee(base.getServiceFee() * HUNDRED);// 服务费或中介费
			bCount = houseBaseMapper.updateSelective(base);
		}

		return dCount + bCount;
	}

	@Override
	public int updateOrAddSetting(SettingEditVo vo) {
		if (vo.getSettingId() != 0) {
			return houseSettingMapper.updateIsDeleteById(vo.getSettingId(), vo.getIsDelete());
		} else {
			Map<String, Integer> map = SettingsEnum.getSettingTypeAndCode(vo.getSettingDesc());
			HouseSetting setPo = new HouseSetting();
			setPo.setHouseSellId(vo.getHouseSellId());
			setPo.setRoomId(vo.getRoomId());
			if (map != null) {
				setPo.setCategoryType(map.get("categoryType"));
				setPo.setSettingCode(map.get("settingCode"));
			}
			houseSettingMapper.insert(setPo);
			return setPo.getId();
		}
	}

	/**
	 * 选择性更新图片状态
	 */
	@Override
	public int setImgDefaultOrDelete(HousePicEditVo vo) {
		if (vo.getIsDefault() != null) {
			HousePicEditVo pic = new HousePicEditVo();
			pic.setHouseSellId(vo.getHouseSellId());
			pic.setIsDefault(0);// 全部设置为非首图
			pic.setRoomId(vo.getRoomId());
			housePicsMapper.updateImgStatusSelective(pic);
		}
		return housePicsMapper.updateImgStatusSelective(vo);
	}

	/**
	 * 查询房间列表
	 */
	@Override
	public PagedList<RoomDetailDto> queryRoomDetailList(RoomDetailDto detailDto) {

		if (detailDto.getRentPriceMonth() != null) {
			detailDto.setRentPriceMonth(detailDto.getRentPriceMonth() * HUNDRED);
		}
		if (detailDto.getRentPriceMonthEnd() != null) {
			detailDto.setRentPriceMonthEnd(detailDto.getRentPriceMonthEnd() * HUNDRED);
		}
		List<RoomDetailDto> pos = houseDetailMapper.selectRoomResourceByPage(detailDto);
		Iterator<RoomDetailDto> it = pos.iterator();
		while (it.hasNext()) {
			RoomDetailDto dto = it.next();
			int imgCount = housePicsMapper.selectImgCountBySellId(dto.getHouseSellId());
			dto.setImgCount(imgCount);
		}
		PageInfo<RoomDetailDto> pageInfo = new PageInfo<RoomDetailDto>(pos);
		PagedList<RoomDetailDto> pagedList = PagedList.newMe(pageInfo);
		return pagedList;
	}

	@Override
	public RoomDetailEditVo getRoomDetail(String houseSellId, String roomId) {

		RoomDetailEditVo editVo = new RoomDetailEditVo();
		HouseDetail detail = houseDetailMapper.selectHouseDetailBySellId(houseSellId);

		RoomBase base = roomBaseMapper.selectRoomBaseByRoomId(roomId);
		String houseTag = base.getRoomTag();
		if (!StringUtils.isEmpty(houseTag)) {
			String tags[] = houseTag.split(",");
			StringBuffer sb = new StringBuffer();
			for (String tag : tags) {// 将标签编码转为中文
				if (!StringUtils.isEmpty(tag)) {
					String cTag = HouseTagEnum.getDescByCode(Integer.parseInt(tag));
					sb.append(cTag).append(",");
				}
			}
			if (sb.length() > 0) {
				base.setRoomTag(sb.substring(0, sb.length() - 1));
			}
		}
		base.setRentPriceMonth(base.getRentPriceMonth() / HUNDRED);// 月租金
		base.setDepositFee(base.getDepositFee() / HUNDRED);// 押金
		base.setServiceFee(base.getServiceFee() / HUNDRED);// 服务费或中介费
		List<HousePics> imgsList = housePicsMapper.selectPicsBySellIdAndRoomId(houseSellId, roomId);
		for (HousePics pic : imgsList) {
			if (StringUtils.isEmpty(pic.getPicRootPath())) {
				pic.setPicRootPath(pic.getPicWebPath());
			}
		}
		List<HouseSetting> settingList = houseSettingMapper.selectSettingBySellIdAndRoomId(houseSellId, roomId);

		List<SettingEditVo> allSettingList = SettingsEnum.getSettingList();
		for (SettingEditVo vo : allSettingList) {

			for (HouseSetting set : settingList) {
				String desc = SettingsEnum.getSettingDesc(set.getCategoryType(), set.getSettingCode());
				if (vo.getSettingDesc().equals(desc)) {
					vo.setChecked(1);// 是否选择 1 选中，0 不选中
					vo.setSettingId(set.getId());
				}
			}
		}
		editVo.setDetail(detail);
		editVo.setBase(base);
		editVo.setImgsList(imgsList);
		editVo.setSettingList(allSettingList);
		return editVo;
	}

	/**
	 * 跟新roomBase
	 * 
	 * @param base
	 * @return
	 */
	@Override
	public int updateRoomInfo(RoomBase base) {

		base.setRentPriceMonth(base.getRentPriceMonth() * HUNDRED);// 月租金
		base.setDepositFee(base.getDepositFee() * HUNDRED);// 押金
		base.setServiceFee(base.getServiceFee() * HUNDRED);// 服务费或中介费
		return roomBaseMapper.updateSelective(base);

	}

}
