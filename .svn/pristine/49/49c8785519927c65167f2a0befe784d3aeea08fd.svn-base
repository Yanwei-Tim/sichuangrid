package com.tianque.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.core.cache.service.CacheService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.util.PropertyUtil;
import com.tianque.viewObject.vo.ViewObjectVo;

@Service("temporaryPopulationService")
public class TemporaryPopulationServiceImpl implements
		TemporaryPopulationService {
	private static Logger logger = LoggerFactory
			.getLogger(TemporaryPopulationServiceImpl.class);
	@Autowired
	private CacheService cacheService;

	@Override
	public Map<String, String> addPopulationToTemporary(String id, People people) {
		logger.info(people.getClass().getName());
		Object oldPeople = null;
		if (null != id && !"".equals(id)) {
			oldPeople = cacheService.get(id);
			if (oldPeople == null) {
				oldPeople = people;
			} else {
				try {
					PropertyUtil.copyPropertiesWithRecursion(people.getClass(),
							oldPeople, people);
				} catch (Exception e) {
					logger.error("属性copy", e);
				}
			}
		} else {
			id = UUID.randomUUID().toString();
			oldPeople = people;
		}
		cacheService.set(id, oldPeople);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return map;
	}

	@Override
	public Object getPopulationById(String key) {
		return cacheService.get(key);
	}

	@Override
	public Map<String, String> addHouseInfoToTemporary(String id,
			HouseInfo houseInfo) {
		Object oldHouseInfo = null;
		if (null != id && !"".equals(id)) {
			oldHouseInfo = cacheService.get(id);
			try {
				PropertyUtil.copyPropertiesWithRecursion(houseInfo.getClass(),
						oldHouseInfo, houseInfo);
			} catch (Exception e) {
				logger.error("属性copy", e);
			}
		} else {
			id = UUID.randomUUID().toString();
			oldHouseInfo = houseInfo;
		}
		cacheService.set(id, oldHouseInfo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return map;
	}

	public String addPopulationToTemporary(People people) {
		String id = UUID.randomUUID().toString();
		cacheService.set(id, people);
		return id;
	}

	@Override
	public Map<String, String> addViewObjecToTemporary(ViewObjectVo vo) {
		String id = UUID.randomUUID().toString();
		cacheService.set(id, vo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return map;
	}

	@Override
	public Object getObjectByIdFromTemporary(String key) {
		if (null == key || key.isEmpty()) {
			throw new BusinessValidationException("请输入缓存的key值");
		}
		return cacheService.get(key);
	}

	@Override
	public Map<String, String> addObjectToTemporary(Object object) {
		String id = UUID.randomUUID().toString();
		cacheService.set(id, object);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return map;
	}
}
