package com.tianque.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrInitialization {

	private static Logger logger = LoggerFactory.getLogger(SolrInitialization.class);

	public static void main(String[] args) throws Exception {
		try {
			if (args.length > 0 && args[0].toLowerCase().equals("development")) {
				developmentMode();
			} else if (args.length > 0 && args[0].toLowerCase().equals("production")) {
				productionMode();
			} else if (args.length > 0 && args[0].toLowerCase().equals("daotest")) {
				daoTestMode();
			} else if (args.length > 0 && args[0].toLowerCase().equals("functiontest")) {
				functionTestMode();
			}
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	private static void productionMode() throws Exception {
		new ProductionEnvironmentBuilder().builderTestEnv();
	}

	private static void developmentMode() throws Exception {
		new DevelopmentEnvironmentBuilder().builderTestEnv();
	}

	private static void daoTestMode() throws Exception {
		new DaoTestEnvironmentBuilder().builderTestEnv();
	}

	private static void functionTestMode() throws Exception {
		new FunctionTestEnvironmentBuilder().builderTestEnv();
	}
}
