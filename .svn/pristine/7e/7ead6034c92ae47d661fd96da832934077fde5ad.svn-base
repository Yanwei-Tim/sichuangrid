package com.tianque.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.solr.init.DocumentInit;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("solrInitController")
public class SolrInitController {
	@Autowired
	private GlobalSettingService globalSettingService;

	public void initSolr() throws Exception {
		DocumentInit.init(globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL));
	}
}
