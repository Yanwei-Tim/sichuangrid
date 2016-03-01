package com.tianque.plugin.orgchange.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.PeaceVillage;
import com.tianque.issue.service.PeaceVillageService;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.constant.IssuesStat;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

/****
 * 社区 平安和谐
 * 
 * @author LG
 * 
 */
@Component("peaceVillageHander")
public class PeaceVillageHander extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(PeaceVillageHander.class);

	@Autowired
	private PeaceVillageService peaceVillageService;
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
			PeaceVillage peaceVillageVo = new PeaceVillage();
			Organization sourceOrg = organizationDubboService
					.getSimpleOrgById(orgMapping.getOldOrgId());
			Organization targetOrg = organizationDubboService
					.getSimpleOrgById(orgMapping.getNewOrgId());
			for (String year : years) {
				for (Long month = 1L; month <= 12L; month++) {
					peaceVillageVo.setYear(Long.valueOf(year));
					peaceVillageVo.setMonth(month);
					peaceVillageVo.setOrganization(sourceOrg);
					PeaceVillage sourcePv = peaceVillageService
							.getPeaceVillageByOrgAndYearMonth(peaceVillageVo);
					peaceVillageVo.setOrganization(targetOrg);
					PeaceVillage targetPv = peaceVillageService
							.getPeaceVillageByOrgAndYearMonth(peaceVillageVo);
					targetPv = loadTargPv(sourcePv, targetPv);
					if (targetPv != null) {
						updateCount += peaceVillageService
								.updatePeaceVillage(targetPv);
					}
					backup(orgMapping, sourcePv);
					delete(orgMapping, sourcePv);
				}
			}
		}
		return updateCount;
	}

	// 备份
	private void backup(OrgMapping orgMapping, PeaceVillage sourcePv) {
		if (sourcePv != null) {
			String selectSql = OrgChangeUtils.replaceScript(orgMapping,
					orgMapping.getModuleTable().getSelectScript()
							+ " AND ID = " + sourcePv.getId());
			if (StringUtil.isStringAvaliable(selectSql)) {
				orgMapping.getModuleTable().setSelectScript(selectSql);
				backupBaseDataService.addBackupBaseData(orgMapping);
			}
		}
	}

	// 删除
	private int delete(OrgMapping orgMapping, PeaceVillage sourcePv) {
		int deleteCount = 0;
		if (sourcePv != null) {
			String deleteSql = OrgChangeUtils.replaceScript(orgMapping,
					orgMapping.getModuleTable().getDeleteScript()
							+ " AND ID = " + sourcePv.getId());
			deleteCount = commonHandlerDAO.deleteData(deleteSql);
		}
		return deleteCount;
	}

	private PeaceVillage loadTargPv(PeaceVillage sourcePv, PeaceVillage targetPv) {
		if (sourcePv == null || targetPv == null) {
			return targetPv;
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getNoCriminal())) {
			targetPv.setNoCriminal(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getNoDrugs())) {
			targetPv.setNoDrugs(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getNoAccident())) {
			targetPv.setNoAccident(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getNoGroupEvents())) {
			targetPv.setNoGroupEvents(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getNoDirty())) {
			targetPv.setNoDirty(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getHasOrganization())) {
			targetPv.setHasOrganization(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getHasMeasures())) {
			targetPv.setHasMeasures(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getHasLegalAdvocacy())) {
			targetPv.setHasLegalAdvocacy(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getHasSocialForces())) {
			targetPv.setHasSocialForces(IssuesStat.HASDATA);
		}
		if (IssuesStat.HASDATA.equals(sourcePv.getHasCulturalActivities())) {
			targetPv.setHasCulturalActivities(IssuesStat.HASDATA);
		}
		return targetPv;
	}
}
