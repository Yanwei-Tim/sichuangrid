package com.tianque.baseInfo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DataFromTypes;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @功能：处理数据来源
 */
public class DataFromInterceptor extends AbstractInterceptor {

	private final static Logger logger = LoggerFactory
			.getLogger(DataFromInterceptor.class);
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		PropertyDict propertyDict = getDataFromType(
				DataFromTypes.ENTRY_DATA_NAME, DataFromTypes.ENTRY_DATA_SEQ);
		ThreadVariable.setDataFrom(propertyDict);
		return ai.invoke();
	}

	private PropertyDict getDataFromType(String displayName, int seq) {
		PropertyDict propertyDict = (PropertyDict) cacheService.get(
				MemCacheConstant.DATAFROMTYPE_NAMESPACE, MemCacheConstant
						.getDataFromTypeKey(MemCacheConstant.DATAFROMTYPE_KEY,
								seq));
		if (null == propertyDict) {
			propertyDict = propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.DATAFROM_TYPE, displayName);
			cacheService.set(MemCacheConstant.DATAFROMTYPE_NAMESPACE,
					MemCacheConstant.getDataFromTypeKey(
							MemCacheConstant.DATAFROMTYPE_KEY, seq),
					propertyDict);
		}
		return propertyDict;
	}

	private void setDataFromIntoThreadLocal(String actionName) {

	}
}
