package com.tianque.xichang.working.steadyWork.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.validate.AbstractWorkingValidator;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.service.AccountStepService;
import com.tianque.xichang.working.report.service.ReportCount;
import com.tianque.xichang.working.steadyWork.dao.SteadyWorkDao;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.SteadyWork;
import com.tianque.xichang.working.steadyWork.domain.vo.PeopleInfoVo;
import com.tianque.xichang.working.steadyWork.service.PeopleInfoService;
import com.tianque.xichang.working.steadyWork.service.SteadyWorkService;

@Transactional
@Service("steadyWorkService")
public class SteadyWorkServiceImpl implements SteadyWorkService, ReportCount {
	private static Logger logger = LoggerFactory.getLogger(SteadyWorkServiceImpl.class);
	@Autowired
	private SteadyWorkDao steadyWorkDao;
	@Autowired
	private PeopleInfoService peopleInfoService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	@Qualifier("steadyWorkValidator")
	private AbstractWorkingValidator<SteadyWork> validator;
	@Autowired
	private AccountStepService accountStepService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public SteadyWork addSteadyWork(SteadyWork steadyWork, PeopleInfoVo peopleInfoVo) {
		try {
			steadyWork.setPeopleInfos(peopleInfoService.setPeopleInfoParam(peopleInfoVo));
			validate(steadyWork);
			steadyWork = steadyWorkDao.addSteadyWork(steadyWork);
			accountStepService.registerAccountStep(steadyWork.getId(), AccountType.STEADYWORK);
			editPeopleInfo(steadyWork, peopleInfoVo);
			return steadyWork;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceValidationException("添加稳定工作台账失败", e);
		}
	}

	@SuppressWarnings("unused")
	private AccountLogs createAccountLog(SteadyWork steadyWork) {
		AccountLogs accountLog = new AccountLogs();
		accountLog.setAccountId(steadyWork.getId());
		accountLog.setAccountType(AccountType.STEADYWORK);
		return accountLog;
	}

	@Override
	public SteadyWork updateSteadyWork(SteadyWork steadyWork, PeopleInfoVo peopleInfoVo) {
		try {
			steadyWork.setPeopleInfos(peopleInfoService.setPeopleInfoParam(peopleInfoVo));
			validate(steadyWork);
			steadyWork = steadyWorkDao.updateSteadyWork(steadyWork);
			editPeopleInfo(steadyWork, peopleInfoVo);
			return steadyWork;
		} catch (Exception e) {
			throw new ServiceValidationException("修改稳定工作台账失败", e);
		}
	}

	private void editPeopleInfo(SteadyWork steadyWork, PeopleInfoVo peopleInfoVo) {
		if (steadyWork.getId() != null) {
			peopleInfoService.deletePeopleInfoBySteadyWorkId(steadyWork.getId());
		}
		List<PeopleInfo> peopleInfos = peopleInfoService.setPeopleInfoParam(peopleInfoVo);
		for (PeopleInfo peopleInfo : peopleInfos) {
			peopleInfo.setSteadyWorkId(steadyWork.getId());
			peopleInfoService.addPeopleInfo(peopleInfo);
		}

		steadyWork
				.setPeopleInfos(peopleInfoService.findPeopleInfoBySteadyWorkId(steadyWork.getId()));
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.STEADYWORK_KEY)
	public boolean deleteSteadyWorkByIds(List<Long> ids) {
		try {
			return peopleInfoService.deletePeopleInfoBySteadyWorkIds(ids)
					&& steadyWorkDao.deleteSteadyWorkByIds(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceValidationException("删除稳定工作台账失败", e);
		}
	}

	@Override
	public SteadyWork getSteadyWorkById(Long id) {
		try {
			SteadyWork steadyWork = steadyWorkDao.getSteadyWorkById(id);
			steadyWork.setPeopleInfos(peopleInfoService.findPeopleInfoBySteadyWorkId(id));
			return steadyWork;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceValidationException("获取稳定工作台账失败", e);
		}
	}

	private void validate(SteadyWork steadyWork) {
		ValidateResult dataValidator = validator.validate(steadyWork);
		if (dataValidator.hasError()) {
			throw new BusinessValidationException(dataValidator.getErrorMessages());
		}
	}

	@Override
	public PageInfo<SteadyWork> findUndoSteadyWorkForPageByOrgInternalCode(SteadyWork steadyWork,
			Long orgId, Integer state, Pager pager) {
		try {
			return steadyWorkDao.findUndoSteadyWorkForPageByOrgInternalCode(steadyWork, orgId,
					state, pager);
		} catch (Exception e) {
			throw new ServiceValidationException("获取稳定工作台账列表失败", e);
		}
	}

	@Override
	public PageInfo findSteadyWorkForPageByCondition(SteadyWork steadyWork, PeopleInfo peopleInfo,
			Integer state, Pager pager) {
		try {
			return steadyWorkDao.findSteadyWorkForPageByCondition(steadyWork, peopleInfo, state,
					pager);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceValidationException("查询稳定工作台账列表失败", e);
		}
	}

	@Override
	public String hasDuplicateSerialNumber(Long id, String serialNumber) {
		if (serialNumber == null) {
			throw new BusinessValidationException("参数错误");
		}
		SteadyWork getSteadyWork = steadyWorkDao.getSteadyWorkBySerialNumber(serialNumber);
		if (getSteadyWork != null && getSteadyWork.getId() != null
				&& !getSteadyWork.getId().equals(id)) {
			String userName = ThreadVariable.getUser().getUserName()
					.equals(getSteadyWork.getCreateUser()) ? "您" : permissionService
					.getFullUserByUerName(getSteadyWork.getCreateUser()) == null ? ""
					: permissionService.getFullUserByUerName(getSteadyWork.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}
	}

	private Map getReportMap(Long orgId, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startDate", start);
		map.put("endDate", end);
		return map;
	}

	private Map getReportMapWithIsFinish(Long orgId, Date start, Date end, Boolean isFinish) {
		Map map = getReportMap(orgId, start, end);
		map.put("isFinish", isFinish);
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportGroupCount(Long orgId, Date start, Date end) {
		return steadyWorkDao.getReportGroupCount(getReportMap(orgId, start, end));
	}

	@Override
	public List getUnfinishByMonth(Long orgId, Date start, Date end) {
		return steadyWorkDao.getUnfinishByMonth(getReportMap(orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(Long orgId, Date start,
			Date end) {
		return steadyWorkDao.getReportByFinishtypeAndItemcategory(getReportMap(orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(Long orgId, Date start,
			Date end) {
		return steadyWorkDao.getReportByFinishtypeAndItemcategory(getReportMap(orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(Long orgId, Date start,
			Date end) {
		return steadyWorkDao.getReportByReportToCityEndAndItemcategory(getReportMap(orgId, start,
				end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(Long orgId, Date start,
			Date end) {
		return steadyWorkDao.getReportByReportToTownEndAndItemcategory(getReportMap(orgId, start,
				end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndWithIsFinishAndItemcategory(Long orgId,
			Date start, Date end, Boolean isFinish) {
		return steadyWorkDao.getReportByReportToTownEndAndItemcategory(getReportMapWithIsFinish(
				orgId, start, end, isFinish));
	}

	@Override
	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(Long orgId,
			Date start, Date end, Long reportToTown, Long reportToCity) {
		return steadyWorkDao
				.getReportByVillageOrTownReportToCityAndItemcategory(getSearchMapWithReportLevel(
						orgId, start, end, reportToTown, reportToCity));
	}

	private Map getSearchMapWithReportLevel(Long orgId, Date start, Date end, Long reportToTown,
			Long reportToCity) {
		Map map = getReportMap(orgId, start, end);
		map.put("reportToTown", reportToTown);
		map.put("reportToCity", reportToCity);
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(String orgCode,
			Date start, Date end) {
		return steadyWorkDao.getReportByCreateOrgLevelAndItemcategory(getMapWithOrgCode(orgCode,
				start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(String orgCode,
			Date start, Date end) {
		return steadyWorkDao.getReportByFinishOrgLevelAndItemcategory(getMapWithOrgCode(orgCode,
				start, end));
	}

	private Map getMapWithOrgCode(String orgCode, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("startDate", start);
		map.put("endDate", end);
		map.putAll(getOrganizationTypes());
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(String orgCode, Date start,
			Date end) {
		return steadyWorkDao.getReportByFinishtypeAndItemcategory(getByFinishtypeAndOrgCodeMap(
				orgCode, start, end));
	}

	private Map getByFinishtypeAndOrgCodeMap(String orgCode, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("orgCodeFindLevel", 2);
		map.put("startDate", start);
		map.put("endDate", end);
		return map;
	}

	private Map getOrganizationTypes() {
		List<PropertyDict> dicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_TYPE);
		Map map = new HashMap<String, Object>();
		for (PropertyDict dict : dicts) {
			if (OrganizationType.ADMINISTRATIVE_REGION == dict.getInternalId()) {
				map.put("administrative", dict.getId());
			} else if (OrganizationType.FUNCTIONAL_ORG == dict.getInternalId()) {
				map.put("functionalOrg", dict.getId());
			}
		}
		return map;
	}

	@Override
	public List getReportToCityAndItemcategory(Long orgId, Date start, Date end, Long reportToCity) {
		return steadyWorkDao.getReportToCityAndItemcategory(getSearchMapWithReportLevel(orgId,
				start, end, null, reportToCity));
	}

	@Override
	public List<ReportGroupCount> getReportToTownAndItemcategory(Long orgId, Date start, Date end,
			Long reportToTownEnd) {
		return steadyWorkDao.getReportToTownAndItemcategory(getSearchMapWithReportLevel(orgId,
				start, end, reportToTownEnd, null));
	}
}
