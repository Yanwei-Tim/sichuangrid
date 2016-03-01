package com.tianque.solr.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.solr.util.SolrServerFactory;
import com.tianque.solr.util.SolrServerType;

public class DocumentsOptimize implements Job {

	private static Logger logger = LoggerFactory.getLogger(DocumentsOptimize.class);

	@Autowired
	private GlobalSettingService globalSettingService;

	protected void executeInternal(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		logger.info("job开始优化索引库！");
		try {
			optimize();
		} catch (Exception e) {
			logger.error("异常信息", e);
			logger.info(e.getMessage());
		}
		logger.info("job优化索引库完毕！");
	}

	private void optimize() {
		logger.info("job开始优化索引库！");
		if (!Boolean.valueOf(globalSettingService.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG))) {
			logger.info("job不优化索引库！");
			return;
		}

		try {
			SolrServerFactory.getSolrServer(SolrServerType.KEY_PLACE,
					globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL)).optimize();
			SolrServerFactory.getSolrServer(SolrServerType.KEY_PLACE,
					globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL)).commit();
			SolrServerFactory.getSolrServer(SolrServerType.KEY_POPULATION,
					globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL)).optimize();
			SolrServerFactory.getSolrServer(SolrServerType.KEY_POPULATION,
					globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL)).commit();
			SolrServerFactory.getSolrServer(SolrServerType.COMMON_POPULATION,
					globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL)).optimize();
			SolrServerFactory.getSolrServer(SolrServerType.COMMON_POPULATION,
					globalSettingService.getGlobalValue(GlobalSetting.SOLR_URL)).commit();
		} catch (Exception e) {
			logger.error("异常信息", e);
			logger.info(e.getMessage());
		}
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		optimize();
	}

}
