package com.tianque.plugin.orgchange.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;

/**
 * 
 * @ClassName: AccountHandler
 * @Description: TODO三本台账迁移合并handler
 * @author wanggz
 * @date 2014-10-29 上午09:59:49
 */
@Component("accountHandler")
public class AccountHandler extends AbstractOrgChangeHandler {

	private final static Logger logger = LoggerFactory
			.getLogger(AccountHandler.class);

	@Override
	public void init(OrgMapping orgMapping) {
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.");
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		String countNumSql = "SELECT COUNT(*) FROM #TABLENAME# WHERE TARGETORGID = #OLDORGID#";
		countNumSql = OrgChangeUtils.replaceScript(orgMapping, countNumSql);
		int conutNum = commonHandlerDAO.getCount(countNumSql);
		log.appendSuccessDesc("应更新数据量[" + conutNum + "]");
		String updateSql = "UPDATE #TABLENAME# SET TARGETORGID=#NEWORGID#,TARGETORGCODE=#NEWORGCODE# WHERE TARGETORGID = #OLDORGID#";
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		int num = commonHandlerDAO.updateData(updateSql);
		log.appendSuccessDesc("更新数据量[" + num + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.");
		}
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.");
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		String countNumSql = "SELECT COUNT(*) FROM #TABLENAME# WHERE TARGETORGID = #OLDORGID# AND ISFINISH=0";
		countNumSql = OrgChangeUtils.replaceScript(orgMapping, countNumSql);
		int conutNum = commonHandlerDAO.getCount(countNumSql);
		log.appendSuccessDesc("应更新数据量[" + conutNum + "]");
		String updateSql = "UPDATE #TABLENAME# SET TARGETORGID=#NEWORGID#,TARGETORGCODE=#NEWORGCODE# WHERE TARGETORGID = #OLDORGID# AND ISFINISH=0";
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		int updateNum = commonHandlerDAO.updateData(updateSql);
		log.appendSuccessDesc("应更新数据量[" + updateNum + "]");

		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
		}
	}

}
