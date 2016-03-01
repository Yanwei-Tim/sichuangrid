package com.tianque.install.fixBug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.init.ContextType;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.BaseInfoStatTypeService;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.util.ApplicationContextFactory;

public class DataComplement {
	private static Logger logger = LoggerFactory
			.getLogger(DataComplement.class);

	public static void main(String[] args) {
		String[] years = args[0].split(",");
		String[] months = args[1].split(",");
		JobHelper.createMockAdminSession();
		for (String year : years) {
			for (String month : months) {
				logger.error("开始人员--------");
				try {
					getBaseinfoStatisticService().createHistoryStatisticByType(
							Integer.valueOf(year), Integer.valueOf(month));
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
				}
				logger.error("结束人员-------------");
				logger.error("开始场所=============");
				try {
					getBaseInfoStatTypeService().addBaseInfoStatType(
							Integer.valueOf(year), Integer.valueOf(month));
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
				}
				logger.error("结束场所==============");
			}
		}
	}

	private static BaseinfoStatisticService getBaseinfoStatisticService() {
		return (BaseinfoStatisticService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"baseinfoStatisticService");
	}

	private static BaseInfoStatTypeService getBaseInfoStatTypeService() {
		return (BaseInfoStatTypeService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"baseInfoStatTypeService");
	}
}
