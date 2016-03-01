package com.tianque.tqSearch.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.tqSearch.constant.TqSolrSearchOperateType;
import com.tianque.tqSearch.domain.BaseSolrDocument;
import com.tianque.tqSearch.dubboService.TqSearchDubboService;

@Component("tqSolrSearchCommonOperate")
public class TqSolrSearchCommonOperate {
	@Autowired
	private TqSearchDubboService tqSearchDubboService;
	@Autowired
	private GlobalSettingService globalSettingService;

	public void commonOperate(BaseDomain baseDomain,
			TqSolrSearchOperateType operateType) {
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			switch (operateType) {
			case ADD_OR_UPDATE:
				tqSearchDubboService
						.addSolrIndex((BaseSolrDocument) baseDomain);
				break;
			default:
				break;
			}
		}
	}
}
