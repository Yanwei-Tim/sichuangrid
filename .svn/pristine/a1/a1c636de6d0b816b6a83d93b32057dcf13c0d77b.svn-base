package com.tianque.install.fixBug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.dao.impl.RecoveryAttachFilesDaoImpl;
import com.tianque.init.ContextType;
import com.tianque.job.SessionHelper;
import com.tianque.util.ApplicationContextFactory;

public class RecoveryAttachFiles {
	private static Logger logger = LoggerFactory.getLogger(RecoveryAttachFiles.class);

	public static void main(String[] args) {
		try {
			SessionHelper.createMockAdminSession();
			logger.error("开始处理重大矛盾纠纷排查报表附件数据");
			getRecoveryAttachFilesService().operatingData();
			logger.error("已经完成");
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	private static RecoveryAttachFilesDaoImpl getRecoveryAttachFilesService() {
		return (RecoveryAttachFilesDaoImpl) ApplicationContextFactory.getInstance().getBean(
				ContextType.production, "recoveryAttachFilesDaoImpl");
	}
}
