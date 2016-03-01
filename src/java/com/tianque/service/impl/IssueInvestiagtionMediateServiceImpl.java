package com.tianque.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.IssueInvestigationMediateDao;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueNewDao;
import com.tianque.domain.IssueInvestigationMediate;
import com.tianque.domain.Organization;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.report.builder.IssueInvestigationMediateMonthBuilder;
import com.tianque.report.builder.IssueInvestigationMediateVoBuilder;
import com.tianque.report.vo.IssueInvestigationMediateVo;
import com.tianque.service.IssueInvestiagtionMediateService;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 矛盾纠纷排查调处
 */
@Service("issueInvestiagtionMediateService")
public class IssueInvestiagtionMediateServiceImpl extends AbstractBaseService implements
		IssueInvestiagtionMediateService {

	@Autowired
	private IssueInvestigationMediateDao issueInvestigationMediateDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueNewDao issueNewDao;
	@Autowired
	private IssueTypeService issueTypeServiceImpl;
	@Autowired
	private IssueLogDaoNew issueLogDao;
	@Autowired
	private IssueTypeDomainService issueTypeDomainService;

	@Override
	public void rebuildMonthIssueInvestigationMediate(int year, int month, Long orgId) {
		Date startDate = CalendarUtil.getMonthStart(year, month);
		Date endDate = CalendarUtil.getNextMonthStart(year, month);

		Organization rootOrg = organizationDubboService.getSimpleOrgById(orgId);
		clearExistedMonthIssueInvestigationMediate(year, month, rootOrg.getOrgInternalCode());
		IssueInvestigationMediateMonthBuilder builder = new IssueInvestigationMediateMonthBuilder(
				rootOrg, year, month, organizationDubboService, issueNewDao, issueTypeServiceImpl);
		builder.setInvestigation(issueTypeDomainService
				.getIssueTypeDoaminByDomainName(IssueTypes.CONTRADICTION));
		List<IssueLogNew> monthDealedLogs = findNeedProcessIssueLogs(rootOrg, startDate, endDate);
		for (IssueLogNew log : monthDealedLogs) {
			builder.addIssueLog(log);
		}
		List<IssueInvestigationMediate> results = builder.getResult();
		issueInvestigationMediateDao.addIssueInvestigationMediates(results);
	}

	private List<IssueLogNew> findNeedProcessIssueLogs(Organization rootOrg, Date startDate,
			Date endDate) {
		return issueLogDao.findDealStatIssueLogByOrgInternalCodeAndDate(
				rootOrg.getOrgInternalCode(), startDate, endDate);
	}

	private void clearExistedMonthIssueInvestigationMediate(int year, int month, String code) {
		issueInvestigationMediateDao.deleteIssueInvestigationMediateByStartDateAndOrg(year, month,
				code);
	}

	@Override
	public List<IssueInvestigationMediateVo> findIssueInvestigationMediates(int year, int month,
			Long orgId) {
		Organization rootOrg = organizationDubboService.getSimpleOrgById(orgId);
		List<IssueInvestigationMediate> exsitedMonthDatas = issueInvestigationMediateDao
				.sumIssueInvestigationMediateByOrgAndMonth(year, month,
						rootOrg.getOrgInternalCode());
		IssueInvestigationMediateVoBuilder builder = new IssueInvestigationMediateVoBuilder(
				rootOrg.getOrgInternalCode(), year, month, issueInvestigationMediateDao);
		builder.setMonthDatas(exsitedMonthDatas);
		builder.setAllContradictionTypes(issueTypeServiceImpl
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION));
		return builder.getResult();
	}

}
