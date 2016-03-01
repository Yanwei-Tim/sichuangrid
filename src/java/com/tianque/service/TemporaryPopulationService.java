package com.tianque.service;

import java.util.Map;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.viewObject.vo.ViewObjectVo;

public interface TemporaryPopulationService {

	public Map<String, String> addPopulationToTemporary(String id, People p);

	public Map<String, String> addHouseInfoToTemporary(String id, HouseInfo p);

	public Object getPopulationById(String key);

	public Object getObjectByIdFromTemporary(String key);

	public Map<String, String> addViewObjecToTemporary(ViewObjectVo vo);

	public String addPopulationToTemporary(People people);

	public Map<String, String> addObjectToTemporary(Object object);
}
