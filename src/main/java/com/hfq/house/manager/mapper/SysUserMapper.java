package com.hfq.house.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfq.house.manager.entity.model.SysUser;

public interface SysUserMapper {
	
	/**
	 * 根据邮箱查询
	 * @param id
	 * @return
	 */
	List<SysUser> selectByAccount(String account);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	SysUser selectByPrimaryKey(Integer id);
	
	/**
	 * 选择更新
	 * @param vo
	 * @return
	 */
	int updateSelective(@Param("vo") SysUser vo);
}
