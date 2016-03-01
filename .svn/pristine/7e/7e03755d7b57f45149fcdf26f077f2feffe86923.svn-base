package com.tianque.plugin.orgchange.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.PacificUnionFounding;
import com.tianque.issue.service.PacificUnionFoundingService;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.constant.IssuesStat;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

/****
 * 社区 以上 平安和谐
 * 
 * @author LG
 * 
 */
@Component("pacificUnionFoundingHander")
public class PacificUnionFoundingHander extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(PacificUnionFoundingHander.class);

	@Autowired
	private PacificUnionFoundingService pacificUnionFoundingService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void init(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin init.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int count = commonHandlerDAO.getCount(OrgChangeUtils.replaceScript(
				orgMapping, orgMapping.getModuleTable().getCountScript()));
		if (count > 0) {
			orgMapping.setHasData(true);
		} else {
			orgMapping.setHasData(false);
		}
		log.appendSuccessDesc("共需更新数据量[" + count + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end init.orgMapping:{}", orgMapping);
		}
	}

	@Override
	public void backup(OrgMapping orgMapping) {

	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int count = commonHandlerDAO.updateData(OrgChangeUtils.replaceScript(
				orgMapping, orgMapping.getModuleTable().getUpdateScript()));
		log.appendSuccessDesc("迁移更新数据量:[" + count + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.orgMapping:{}", orgMapping);
		}
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int count = 0;
		if (orgMapping.getOldOrgId().equals(orgMapping.getNewOrgId())) {
			// 实际是迁移操作
			transfer(orgMapping);
		} else {
			count = doMerge(orgMapping);
		}
		log.appendSuccessDesc("合并更新数据量[" + count + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.orgMapping:{}", orgMapping);
		}
	}

	// 合并操作
	private int doMerge(OrgMapping orgMapping) {
		int updateCount = 0;
		List<String> years = commonHandlerDAO.queryStringForList(OrgChangeUtils
				.replaceScript(orgMapping, IssuesStat.SELECT_YEAR_SQL));
		if (CollectionUtil.isAvaliable(years)) {
			PacificUnionFounding PacificUnionFoundingVo = new PacificUnionFounding();
			Organization sourceOrg = organizationDubboService
					.getSimpleOrgById(orgMapping.getOldOrgId());
			Organization targetOrg = organizationDubboService
					.getSimpleOrgById(orgMapping.getNewOrgId());

			for (String year : years) {
				PacificUnionFoundingVo.setYear(Integer.valueOf(year));
				PacificUnionFoundingVo.setOrganization(sourceOrg);
				PacificUnionFounding sourcePuf = pacificUnionFoundingService
						.getPacificUnionFoundingByOrgAndYear(PacificUnionFoundingVo);

				PacificUnionFoundingVo.setOrganization(targetOrg);
				PacificUnionFounding targetPuf = pacificUnionFoundingService
						.getPacificUnionFoundingByOrgAndYear(PacificUnionFoundingVo);
				targetPuf = loadTargPuf(sourcePuf, targetPuf);
				if (targetPuf != null) {
					updateCount += pacificUnionFoundingService
							.updatePacificUnionFounding(targetPuf);
				}
				backup(orgMapping, sourcePuf);
				delete(orgMapping, sourcePuf);
			}
		}
		return updateCount;
	}

	// 备份
	private void backup(OrgMapping orgMapping, PacificUnionFounding sourcePuf) {
		if (sourcePuf != null) {
			String selectSql = OrgChangeUtils.replaceScript(orgMapping,
					orgMapping.getModuleTable().getSelectScript()
							+ " AND ID = " + sourcePuf.getId());
			if (StringUtil.isStringAvaliable(selectSql)) {
				orgMapping.getModuleTable().setSelectScript(selectSql);
				backupBaseDataService.addBackupBaseData(orgMapping);
			}
		}
	}

	// 删除
	private int delete(OrgMapping orgMapping, PacificUnionFounding sourcePuf) {
		int deleteCount = 0;
		if (sourcePuf != null) {
			String deleteSql = OrgChangeUtils.replaceScript(orgMapping,
					orgMapping.getModuleTable().getDeleteScript()
							+ " AND ID = " + sourcePuf.getId());
			deleteCount = commonHandlerDAO.deleteData(deleteSql);
		}
		return deleteCount;
	}

	private PacificUnionFounding loadTargPuf(PacificUnionFounding sourcePuf,
			PacificUnionFounding targetPuf) {
		if (sourcePuf == null || targetPuf == null) {
			return targetPuf;
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getJanuary())) {
			targetPuf.setJanuary(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getFebruary())) {
			targetPuf.setFebruary(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getMarch())) {
			targetPuf.setMarch(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getApril())) {
			targetPuf.setApril(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getMay())) {
			targetPuf.setMay(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getJune())) {
			targetPuf.setJune(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getJuly())) {
			targetPuf.setJuly(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getAugust())) {
			targetPuf.setAugust(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getSeptember())) {
			targetPuf.setSeptember(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getOctober())) {
			targetPuf.setOctober(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getNovember())) {
			targetPuf.setNovember(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePuf.getDecember())) {
			targetPuf.setDecember(IssuesStat.HASDATA);
		}
		return targetPuf;
	}
}
