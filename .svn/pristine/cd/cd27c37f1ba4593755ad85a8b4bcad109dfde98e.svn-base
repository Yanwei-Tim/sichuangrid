package com.tianque.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.util.ApplicationContextFactory;

public class DevelopmentMoveDatasBuilder extends InitializationsRunner {

	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveDatasInitialization.class);

	public DevelopmentMoveDatasBuilder() throws Exception {
		addDefaultInitializations();
	}

	/**
	 * 数据初始化
	 * 
	 * @throws Exception
	 */
	private void addDefaultInitializations() throws Exception {
		// DatabaseMoveInitialization dataMove = new DatabaseMoveInitialization(
		// ContextType.development);
		// dataMove.init();
		// new CreateSessionForTestInitialization().init();
		// dataMove.dropTable();
		// dataMove.createTable();
		// dataMove.countOldData();
		// dataMove.insertDatas();
		// dataMove.countNewData();
		// new DatabaseConvertInitialization(getBaseInfoService(),
		// ContextType.development).init();
		// dataMove.alterTable();
		// dataMove.updateDatas();
		// dataMove.countNewData();
		// dataMove.addHouseholdstaffsDatas();
		// dataMove.countNewData();
		// dataMove.checkDatas();
		// dataMove.checkSequence();
		// new DatabaseToHouseHoldStaffInitialization(
		// getFloatingPopulationService(), ContextType.development).init();
		// dataMove.countNewData();
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.info("开发环境初始化结束!");
		moveDataLog.info("开发环境初始化结束!");
		System.exit(0);
	}

	private BaseInfoService getBaseInfoService() {
		return (BaseInfoService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "baseInfoService");
	}

	private FloatingPopulationService getFloatingPopulationService() {
		return (FloatingPopulationService) ApplicationContextFactory
				.getInstance().getBean(ContextType.development,
						"floatingPopulationService");
	}

}
