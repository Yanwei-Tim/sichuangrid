package com.tianque.xichang.working.flow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.constant.FinishType;
import com.tianque.xichang.working.flow.dao.AccountLogsDao;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.domain.AccountStep;
import com.tianque.xichang.working.flow.service.AccountLogsService;
import com.tianque.xichang.working.flow.service.AccountStepService;

@Service("accountLogsService")
@Transactional
public class AccountLogsServiceImpl implements AccountLogsService {

	@Autowired
	private AccountLogsDao accountLogsDao;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private AccountStepService accountStepService;

	@Override
	public boolean addAccountLogs(AccountLogs accountLogs) {
		if (null == accountLogs) {
			throw new BusinessValidationException("参数错误");
		}
		autoFillAccountLogs(accountLogs);
		validateAccountLog(accountLogs);
		addAccountStep(accountLogs);// 产生step
		return accountLogsDao.addAccountLogs(accountLogs);
	}

	private void addAccountStep(AccountLogs accountLogs) {
		Organization org = ThreadVariable.getOrganization();
		PropertyDict orgLevel = propertyDictService.getPropertyDictById(org
				.getOrgLevel().getId());
		if (null != accountLogs.getFinishType()
				&& FinishType.REPORT_TO_TOWN == accountLogs.getFinishType()) {
			if (orgLevel.getInternalId() == OrganizationLevel.DISTRICT
					&& org.getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {// 区县行政部门流转乡镇
				validateTargerOrg(accountLogs);
				updateAccountStep(accountLogs,
						FinishType.CITY_REPORT_TO_TOWN_END, null, null,
						FinishType.CIRCULATION_FINISH);// 更新step
				AccountStep accountStep = createAccountStep(accountLogs);
				Organization targetOrg = organizationDubboService
						.getSimpleOrgById(accountLogs.getTargetOrg().getId());
				accountStep.setTargetOrg(targetOrg);
				accountStep
						.setReportToTownEnd(FinishType.CITY_REPORT_TO_TOWN_END);
				accountStepService.addAccountStep(accountStep);
			} else if (orgLevel.getInternalId() == OrganizationLevel.DISTRICT
					&& org.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {// 区县职能部门流转乡镇
				validateTargerOrg(accountLogs);
				AccountStep accountStep = createAccountStep(accountLogs);
				Organization targetOrg = organizationDubboService
						.getSimpleOrgById(accountLogs.getTargetOrg().getId());
				accountStep.setTargetOrg(targetOrg);
				updateAccountStep(accountLogs,
						FinishType.FUNCTION_REPORT_TO_TOWN_END, null, null,
						FinishType.CIRCULATION_FINISH);// 更新step
				accountStep
						.setReportToTownEnd(FinishType.FUNCTION_REPORT_TO_TOWN_END);
				accountStepService.addAccountStep(accountStep);
			} else if (orgLevel.getInternalId() == OrganizationLevel.VILLAGE) {// 社区呈报乡镇
				AccountStep accountStep = createAccountStep(accountLogs);
				accountStep.setTargetOrg(organizationDubboService
						.getSimpleOrgById(org.getParentOrg().getId()));
				updateAccountStep(accountLogs, FinishType.REPORT_TO_TOWN_END,
						null, null, FinishType.CIRCULATION_FINISH);// 更新step
				accountStep.setReportToTownEnd(FinishType.REPORT_TO_TOWN_END);
				accountStepService.addAccountStep(accountStep);
			}
		} else if (null != accountLogs.getFinishType()
				&& (FinishType.REPORT_TO_LIFE == accountLogs.getFinishType()
						|| FinishType.REPORT_TO_OFFICE == accountLogs
								.getFinishType() || FinishType.REPORT_TO_KEEP == accountLogs
						.getFinishType())) {
			AccountStep accountStep = createAccountStep(accountLogs);
			accountStep.setTargetOrg(organizationDubboService.getSimpleOrgById(org
					.getParentOrg().getId()));
			updateAccountStep(accountLogs, null, FinishType.REPORT_TO_CITY_END,
					null, FinishType.CIRCULATION_FINISH);// 更新step
			accountStep.setReportToCityEnd(FinishType.REPORT_TO_CITY_END);
			accountStepService.addAccountStep(accountStep);
		} else if (null != accountLogs.getFinishType()
				&& FinishType.REPORT_TO_FUNCTION == accountLogs.getFinishType()) {
			validateTargerOrg(accountLogs);
			AccountStep accountStep = createAccountStep(accountLogs);
			Organization targetOrg = organizationDubboService
					.getSimpleOrgById(accountLogs.getTargetOrg().getId());
			accountStep.setTargetOrg(targetOrg);
			updateAccountStep(accountLogs, null, null,
					FinishType.REPORT_TO_FUNCTION_END,
					FinishType.CIRCULATION_FINISH);// 更新step
			accountStep
					.setReportToFunctionEnd(FinishType.REPORT_TO_FUNCTION_END);
			accountStepService.addAccountStep(accountStep);
		} else if (null != accountLogs.getFinishType()) {
			updateAccountStep(accountLogs, null, null, null,
					FinishType.REAL_FINISH);// 更新step
		}
	}

	private void validateTargerOrg(AccountLogs accountLogs) {
		if (null == accountLogs.getTargetOrg().getId()) {
			throw new BusinessValidationException("请选择组织机构");
		}
	}

	private void updateAccountStep(AccountLogs accountLogs, Integer reportTown,
			Integer reportCity, Integer reportFunction,
			Integer realOrCirculation) {
		AccountStep updateAccountStep = createAccountStep(accountLogs);
		updateAccountStep.setTargetOrg(ThreadVariable.getOrganization());
		updateAccountStep.setReportToTownEnd(reportTown);
		updateAccountStep.setReportToCityEnd(reportCity);
		updateAccountStep.setReportToFunctionEnd(reportFunction);
		updateAccountStep.setRealOrCirculation(realOrCirculation);
		updateAccountStep.setIsFinish(true);
		accountStepService.updateAccountStep(updateAccountStep);// 更新step
	}

	private AccountStep createAccountStep(AccountLogs accountLogs) {
		AccountStep accountStep = new AccountStep();
		accountStep.setAccountId(accountLogs.getAccountId());
		accountStep.setAccountType(accountLogs.getAccountType());
		accountStep.setIsFinish(false);
		accountStep.setFinishType(accountLogs.getEssenceAndProgram());
		return accountStep;
	}

	private void autoFillAccountLogs(AccountLogs accountLogs) {
		accountLogs.setIsSysOperate(false);// 设置是否系统级别日志
		accountLogs.setDealOrganization(ThreadVariable.getOrganization());
	}

	@Override
	public PageInfo<AccountLogs> findAccountLogsForPageByAccountId(
			Long accountId, String accountType, Pager genaratePager) {
		return accountLogsDao.findAccountLogsForPageByAccountId(accountId,
				accountType, genaratePager);
	}

	@Override
	public AccountLogs updateAccountLogs(AccountLogs accountLogs) {
		if (null == accountLogs) {
			throw new BusinessValidationException("参数错误");
		}
		autoFillAccountLogs(accountLogs);
		validateAccountLog(accountLogs);
		if (null != accountLogs.getFinishType()) {
			addAccountStep(accountLogs);
		}
		return accountLogsDao.updateAccountLogs(accountLogs);
	}

	@Override
	public boolean deleteAccountLogsById(Long accountLogsId) {
		return accountLogsDao.deleteAccountLogsById(accountLogsId);
	}

	@Override
	public AccountLogs getAccountLogsById(Long id) {
		return accountLogsDao.getAccountLogsById(id);
	}

	@Override
	public boolean registerAccountLogs(AccountLogs accountLogs) {
		if (null == accountLogs) {
			throw new BusinessValidationException("参数错误");
		}
		createRegisterAccountLog(accountLogs);
		return accountLogsDao.addAccountLogs(accountLogs);
	}

	private void createRegisterAccountLog(AccountLogs accountLogs) {
		accountLogs.setLogDate(new Date());
		accountLogs.setDealDate(new Date());
		accountLogs.setDealUser(ThreadVariable.getUser().getUserName());
		accountLogs.setIsFinish(false);// 默认是未完成
		accountLogs.setDealOrganization(ThreadVariable.getOrganization());
		accountLogs.setContent("新增");
		accountLogs.setOpinion("新增");
		accountLogs.setIsSysOperate(true);// 设置 是系统级别日志
	}

	private void validateAccountLog(AccountLogs accountLogs) {
		if (null == accountLogs.getDealDate()) {
			throw new BusinessValidationException("必须选择处理时间");
		}
		if (!StringUtil.isStringAvaliable(accountLogs.getDealUser())) {
			throw new BusinessValidationException("必须输入承办人");
		}
		if (AccountType.PEOPLEASPIRATION.equals(accountLogs.getAccountType())) {
			if (!StringUtil.isStringAvaliable(accountLogs.getContent())) {
				throw new BusinessValidationException("必须输入工作措施及内容");
			} else if (validateHelper.illegalStringLength(0, 300,
					accountLogs.getContent())) {
				throw new BusinessValidationException("工作措施及内容不能大于300个字");
			}
		}
		if (AccountType.POORPEOPLE.equals(accountLogs.getAccountType())) {
			if (!StringUtil.isStringAvaliable(accountLogs.getContent())) {
				throw new BusinessValidationException("必须输入帮扶措施及落实情况");
			} else if (validateHelper.illegalStringLength(0, 300,
					accountLogs.getContent())) {
				throw new BusinessValidationException("帮扶措施及落实情况不能大于300个字");
			}
		}
		if (AccountType.STEADYWORK.equals(accountLogs.getAccountType())) {
			if (!StringUtil.isStringAvaliable(accountLogs.getContent())) {
				throw new BusinessValidationException("必须输入工作措施及内容");
			} else if (validateHelper.illegalStringLength(0, 300,
					accountLogs.getContent())) {
				throw new BusinessValidationException("工作措施及内容不能大于300个字");
			}
			if (!StringUtil.isStringAvaliable(accountLogs.getOpinion())) {
				throw new BusinessValidationException("必须输入当事人或当事人代表意见");
			} else if (validateHelper.illegalStringLength(0, 300,
					accountLogs.getOpinion())) {
				throw new BusinessValidationException("当事人或当事人代表意见不能大于300个字");
			}
		}
		if (null != accountLogs.getIsFinish() && accountLogs.getIsFinish()) {
			if (null == accountLogs.getFinishDate()) {
				throw new BusinessValidationException("办结时间必须选择");
			}
			if (null == accountLogs.getFinishType()) {
				throw new BusinessValidationException("办结方式必须选择");
			}
			if (!StringUtil.isStringAvaliable(accountLogs.getFinishContent())) {
				throw new BusinessValidationException("必须输入 办结结果");
			} else if (validateHelper.illegalStringLength(0, 300,
					accountLogs.getFinishContent())) {
				throw new BusinessValidationException("办结结果不能大于300个字");
			}
		}

	}

	@Override
	public List<AccountLogs> findAccountLogsByAccountIdAndAccountType(
			Long accountId, String accountType, Long targetOrgId) {
		if (accountId == null || accountType == null || "".equals(accountType)) {
			throw new BusinessValidationException("参数错误");
		}
		return accountLogsDao.findAccountLogsByAccountIdAndAccountType(
				accountId, accountType, targetOrgId);
	}

	@Override
	public PageInfo findAdminTargetsByName(Long targetOrgId, String tag,
			Integer page, Integer rows) {
		if (targetOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getFullOrgById(targetOrgId);
		if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType().getInternalId()
				&& org.getParentOrg() != null) {
			targetOrgId = org.getParentOrg().getId();
		}
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setParentOrgId(targetOrgId);
		organizationVo.setOrgName(tag);
		if(orgTypeDict!=null){
			organizationVo.setOrgTypeId(orgTypeDict.getId());
		}
		return findTargetsByOrgIdAndOrgNameAndOrgType(organizationVo, page, rows);
	}

	@Override
	public PageInfo findFunctionTargetsByName(Long targetOrgId, String tag,
			Integer page, Integer rows) {
		if (targetOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		OrganizationVo organizationVo = new OrganizationVo();
		Organization org = organizationDubboService.getFullOrgById(targetOrgId);
		if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType().getInternalId()) {
			organizationVo.setParentFunOrgId(org.getId());
			organizationVo.setOrgName(tag);
			return findTargetsByOrgIdAndOrgNameAndOrgType(organizationVo, page, rows);
		} else {
			PropertyDict orgTypeDict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			organizationVo.setParentOrgId(targetOrgId);
			organizationVo.setOrgName(tag);
			if(orgTypeDict!=null){
				organizationVo.setOrgTypeId(orgTypeDict.getId());
			}
			return findTargetsByOrgIdAndOrgNameAndOrgType(organizationVo, page, rows);
		}
	}
	
	@Override
	public PageInfo findFunctionTargetsByOrgId(Long targetOrgId, String tag,
			Integer page, Integer rows) {
		if (targetOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getFullOrgById(targetOrgId);
		if (org != null && org.getOrgInternalCode() != null) {
			PropertyDict orgTypeDict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.FUNCTIONAL_ORG).get(0);
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgInternalCode(org.getOrgInternalCode());
			organizationVo.setOrgName(tag);
			if (orgTypeDict != null) {
				organizationVo.setOrgTypeId(orgTypeDict.getId());
			}
			return findTargetsByOrgIdAndOrgNameAndOrgType(organizationVo, page, rows);
		} else {
			throw new BusinessValidationException("组织机构为空，请重试");
		}
	}
	
	private PageInfo<AutoCompleteData> findTargetsByOrgIdAndOrgNameAndOrgType(OrganizationVo organizationVo, Integer page, Integer rows){
		organizationVo.setPage(page);
		organizationVo.setRows(rows);
		Integer totalCount = organizationDubboService.countOrgsBySearchVo(organizationVo);
		List<Organization> orgList = organizationDubboService.findNameAndRemarkBySearchVo(organizationVo);
		List<AutoCompleteData> autoCompleteDataList = new ArrayList<AutoCompleteData>();
		for (Organization organization : orgList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setValue(organization.getId()+"");
			autoCompleteData.setLabel(organization.getOrgName());
			autoCompleteData.setDesc(organization.getRemark());
			autoCompleteDataList.add(autoCompleteData);
		}
		return new PageInfo<AutoCompleteData>(page, rows, totalCount, autoCompleteDataList);
	}
}
