package com.tianque.baseInfo.context.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;

@Service("baseInfoContextService")
@Transactional
public class BaseInfoContextServiceImpl implements BaseInfoContextService {

	@Autowired
	private CacheService cacheService;

	private final static String HOUSE_ID = "houseId";
	private final static String ACTUAL_POPULATION_CACHE_ID = "actualPopulationCacheId";
	private final static String ACTUAL_POPULATION_ID = "actualPopulationId";
	private final static String POPULATION_CACHE_ID = "populationCacheId";
	private final static String POPULATION_ID = "populationId";

	@Override
	public Long getHouseIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		String houseId = context.get(HOUSE_ID);
		if (null == houseId) {
			return null;
		}
		return Long.valueOf(houseId);
	}

	private Map<String, String> getContextFromCache(String contextId) {
		Map<String, String> context = (Map<String, String>) cacheService.get(
				MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId);
		if (null == context) {
			context = new HashMap<String, String>();
		}
		return context;
	}

	@Override
	public String getActualPopulationCacheIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		return context.get(ACTUAL_POPULATION_CACHE_ID);
	}

	@Override
	public Long getActualPopulationIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		String populationId = context.get(ACTUAL_POPULATION_ID);
		if (null == populationId) {
			return null;
		} else {
			return Long.valueOf(populationId);
		}
	}

	@Override
	public void putHouseIdToContext(String contextId, Long houseId) {
		if (null != houseId) {
			Map<String, String> context = getContextFromCache(contextId);
			context.put(HOUSE_ID, houseId.toString());
			cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE,
					contextId, context);
		}
	}

	@Override
	public void putActualPopulationCacheIdToContext(String contextId,
			String populationCacheId) {
		Map<String, String> context = getContextFromCache(contextId);
		context.put(ACTUAL_POPULATION_CACHE_ID, populationCacheId);
		cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId,
				context);

	}

	@Override
	public void putActualPopulationIdToContext(String contextId,
			Long populationId) {
		Map<String, String> context = getContextFromCache(contextId);
		context.put(ACTUAL_POPULATION_ID, populationId.toString());
		cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId,
				context);
	}

	@Override
	public void removeHouseIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		context.remove(HOUSE_ID);
		cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId,
				context);
	}

	@Override
	public void removeActualPopulationIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		context.remove(ACTUAL_POPULATION_ID);
		cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId,
				context);
	}

	@Override
	public String getPopulationCacheIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		return context.get(POPULATION_CACHE_ID);
	}

	@Override
	public Long getPopulationIdFromContext(String contextId) {
		Map<String, String> context = getContextFromCache(contextId);
		String populationId = context.get(POPULATION_ID);
		if (null == populationId) {
			return null;
		} else {
			return Long.valueOf(populationId);
		}
	}

	@Override
	public void putPopulationCacheIdToContext(String contextId,
			String populationCacheId) {
		Map<String, String> context = getContextFromCache(contextId);
		context.put(POPULATION_CACHE_ID, populationCacheId);
		cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId,
				context);

	}

	@Override
	public void putPopulationIdToContext(String contextId, Long populationId) {
		Map<String, String> context = getContextFromCache(contextId);
		context.put(POPULATION_ID, populationId.toString());
		cacheService.set(MemCacheConstant.BASEINFOCONTEXT_NAMESPACE, contextId,
				context);
	}
}
