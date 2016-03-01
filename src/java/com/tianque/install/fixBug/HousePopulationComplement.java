package com.tianque.install.fixBug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.ThreadVariable;
import com.tianque.init.ContextType;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analyzing.service.CompanyPlaceLeaderViewService;
import com.tianque.plugin.analyzing.service.StatisticsPopulationService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.util.ApplicationContextFactory;

public class HousePopulationComplement {
	private static Logger logger = LoggerFactory
			.getLogger(DataComplement.class);

	public static void main(String[] args) {
		String[] years = args[0].split(",");
		String[] months = args[1].split(",");
		String type = args[2];
		JobHelper.createMockAdminSession();
		long startTime = System.currentTimeMillis();
		ThreadVariable.setUser(getPermissionService().findUserByUserName(
				"admin"));
		for (String year : years) {
			for (String month : months) {
				logger.error("补全" + year + "年" + month + "月" + type + "信息开始");
				try {
					if ("companyPlace".equals(type)) {// 单位场所
						getCompanyPlaceLeaderViewService()
								.addCompanyPlaceAnalyzStatisticsByTime(null,
										null, null, Integer.valueOf(year),
										Integer.valueOf(month));
					} else if ("yonthPopulation".equals(type)) {// 青少年、少先队员、团员
						getStatisticsPopulationService()
								.addYouthPopulationStat(Integer.valueOf(year),
										Integer.valueOf(month));

					} else if ("floatingPopulation".equals(type)) {
						getStatisticsPopulationService()
								.addFloatingPopulationStatForYearAndMonth(
										Integer.valueOf(year),
										Integer.valueOf(month));
					} else {// 流口、户籍、未落户、境外
						getStatisticsPopulationService()
								.addpopulationStatForYearAndMonth(
										Integer.valueOf(year),
										Integer.valueOf(month));
					}
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
				}
				double usedMinute = Math
						.floor((System.currentTimeMillis() - startTime) / 60000);
				logger.error("补全" + year + "年" + month + "月" + type
						+ "信息结束,共花了" + usedMinute + "分钟");
			}
		}
		System.exit(0);
	}

	// private static BaseinfoStatisticService getBaseinfoStatisticService() {
	// return (BaseinfoStatisticService) ApplicationContextFactory
	// .getInstance().getBean(ContextType.production,
	// "baseinfoStatisticService");
	// }

	private static StatisticsPopulationService getStatisticsPopulationService() {
		return (StatisticsPopulationService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"statisticsPopulationService");
	}

	private static PermissionService getPermissionService() {
		return (PermissionService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.production, "permissionService");
	}

	private static CompanyPlaceLeaderViewService getCompanyPlaceLeaderViewService() {
		return (CompanyPlaceLeaderViewService) ApplicationContextFactory
				.getInstance().getBean(ContextType.production,
						"companyPlaceLeaderViewService");
	}

}
