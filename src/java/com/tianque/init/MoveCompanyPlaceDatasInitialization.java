package com.tianque.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveCompanyPlaceDatasInitialization {
	private static Logger logger = LoggerFactory
			.getLogger(MoveCompanyPlaceDatasInitialization.class);
	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveCompanyPlaceDatasInitialization.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			moveDataLog.error("开始迁移");
			if (args.length > 0 && args[0].toLowerCase().equals("development")) {
				developmentMode();
			} else if (args.length > 0
					&& args[0].toLowerCase().equals("production")) {
				productionMode();
			} else if (args.length > 0
					&& args[0].toLowerCase().equals("daotest")) {
				// daoTestMode();
			} else if (args.length > 0
					&& args[0].toLowerCase().equals("functiontest")) {
				// functionTestMode();
			}
		} catch (Exception e) {
			moveDataLog.error("异常信息", e);
			logger.error("异常信息", e);
		}
	}

	private static void developmentMode() throws Exception {
		new DevelopmentMoveCompanyPlaceDatasBuilder().builderTestEnv();
	}

	private static void productionMode() throws Exception {
		new ProductionMoveCompanyPlaceDatasBuilder().builderTestEnv();
	}
}
