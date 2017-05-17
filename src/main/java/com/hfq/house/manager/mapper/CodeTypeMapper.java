package com.hfq.house.manager.mapper;

import java.util.List;

import com.hfq.house.manager.common.CodeType;

public interface CodeTypeMapper {
	
	
	List<CodeType> selectByTypeCode(String typeCode);
}
