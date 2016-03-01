package com.tianque.xichang.working.poorPeople.service.impl;

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
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.validate.AbstractWorkingValidator;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.service.AccountStepService;
import com.tianque.xichang.working.flow.service.AccountWarningService;
import com.tianque.xichang.working.poorPeople.constant.PoorPeopleConstant;
import com.tianque.xichang.working.poorPeople.dao.PoorPeopleDao;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;
import com.tianque.xichang.working.poorPeople.service.PoorPeopleService;
import com.tianque.xichang.working.report.service.ReportCount;

/**
 * @ClassName: PoorPeopleServiceImpl
 * @Description: 三本台账-困难群众台账-服务接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 23, 2013 5:55:54 PM
 */
@Transactional
@Service("poorPeopleService")
public class PoorPeopleServiceImpl implements PoorPeopleService, ReportCount {

	private static Logger logger = LoggerFactory
			.getLogger(PoorPeopleServiceImpl.class);

	@Autowired
	private PoorPeopleDao poorPeopleDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	@Qualifier("poorPeopleValidator")
	private AbstractWorkingValidator<PoorPeople> validator;
	@Autowired
	private AccountStepService accountStepService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private AccountWarningService accountWarningService;

	@Override
	public PageInfo findUndoPoorPeopleForList(PoorPeople poorPeople,
			Long orgId, String module, String listType) {
		try {
			PageInfo pageInfo = null;
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (PoorPeopleConstant.PERMISSION_ENAME.equals(module)// 我的待办
					&& PoorPeopleConstant.UNDO_LIST_TYPE.equals(listType)) {
				poorPeople.setOrgId(orgId);
				poorPeople.setIsfinish(0);
				pageInfo = poorPeopleDao.findUndoPoorPeopleForList(poorPeople);
				accountWarningService.fillUnDoEaringWarn(poorPeople.getOrgId(),
						poorPeople.getOrgCode(), new GridPage(pageInfo),
						poorPeople.getIsfinish(), AccountType.POORPEOPLE);
			} else if (PoorPeopleConstant.PERMISSION_ENAME.equals(module)// 我的已办
					&& PoorPeopleConstant.DONE_LIST_TYPE.equals(listType)) {
				poorPeople.setOrgId(orgId);
				poorPeople.setIsfinish(1);
				pageInfo = poorPeopleDao.findUndoPoorPeopleForList(poorPeople);
				accountWarningService.fillDoneEaringWarn(poorPeople.getOrgId(),
						poorPeople.getOrgCode(), new GridPage(pageInfo),
						poorPeople.getIsfinish(), AccountType.POORPEOPLE);
			} else if (PoorPeopleConstant.PERMISSION_ENAME_CHLID.equals(module)// 下辖待办
					&& PoorPeopleConstant.UNDO_LIST_TYPE.equals(listType)) {
				poorPeople.setOrgCode(org.getOrgInternalCode());
				poorPeople.setIsfinish(0);
				pageInfo = poorPeopleDao.findUndoPoorPeopleForList(poorPeople);
				accountWarningService.fillUnDoEaringWarn(poorPeople.getOrgId(),
						poorPeople.getOrgCode(), new GridPage(pageInfo),
						poorPeople.getIsfinish(), AccountType.POORPEOPLE);
			} else if (PoorPeopleConstant.PERMISSION_ENAME_CHLID.equals(module)// 下辖已办
					&& PoorPeopleConstant.DONE_LIST_TYPE.equals(listType)) {
				poorPeople.setOrgCode(org.getOrgInternalCode());
				poorPeople.setIsfinish(1);
				pageInfo = poorPeopleDao.findUndoPoorPeopleForList(poorPeople);
				accountWarningService.fillDoneEaringWarn(poorPeople.getOrgId(),
						poorPeople.getOrgCode(), new GridPage(pageInfo),
						poorPeople.getIsfinish(), AccountType.POORPEOPLE);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众台账获取列表失败", "困难群众台账获取列表失败", e);
		}
	}

	@Override
	public PoorPeople addPoorPeople(PoorPeople poorPeople) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validate(poorPeople);
			}
			Organization organization = ThreadVariable.getOrganization();
			poorPeople.setOrganization(organization);
			poorPeople.setOrgInternalCode(organization.getOrgInternalCode());
			poorPeople.setOccurOrgInternalCode(organizationDubboService
					.getOrgInternalCodeById(poorPeople.getOccurOrg().getId()));
			poorPeople = poorPeopleDao.addPoorPeople(poorPeople);
			accountStepService.registerAccountStep(poorPeople.getId(),
					AccountType.POORPEOPLE);
			return poorPeople;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("困难群众台账新增信息出现错误", e.getMessage());
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	private AccountLogs createAccountLog(PoorPeople poorPeople) {
		AccountLogs accountLog = new AccountLogs();
		accountLog.setAccountId(poorPeople.getId());
		accountLog.setAccountType(AccountType.POORPEOPLE);
		return accountLog;
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.POORPEOPLE_KEY)
	public void deletePoorPeople(String ids) {
		try {
			if (ids != null && !"".equals(ids)) {
				String idArry[] = ids.split(",");
				poorPeopleDao.deletePoorPeopleByIds(idArry);
				// for (String id : idArry) {
				// if (id != null && !"".equals(id)) {
				// poorPeopleDao.deletePoorPeople(id);
				// }
				// }
			}
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众台账删除信息出现错误", "困难群众台账删除信息出现错误", e);
		}
	}

	@Override
	public PoorPeople getPoorPeopleById(PoorPeople poorPeople) {
		validateId(poorPeople);
		try {
			return poorPeopleDao.getPoorPeopleById(poorPeople.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众台账获取详情出现错误", "困难群众台账获取详情出现错误", e);
		}
	}

	@Override
	public PoorPeople updatePoorPeople(PoorPeople poorPeople) {
		validateId(poorPeople);
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validate(poorPeople);
			}
			poorPeople.setOccurOrgInternalCode(organizationDubboService
					.getOrgInternalCodeById(poorPeople.getOccurOrg().getId()));
			return poorPeopleDao.updatePoorPeople(poorPeople);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("困难群众台账修改信息出现错误", e.getMessage());
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	private void validateId(PoorPeople poorPeople) {
		if (poorPeople == null || poorPeople.getId() == null
				|| poorPeople.getId().longValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
	}

	private void validate(PoorPeople poorPeople) {
		ValidateResult dataValidator = validator.validate(poorPeople);
		if (dataValidator.hasError()) {
			throw new BusinessValidationException(dataValidator.getErrorMessages());
		}
	}

	@Override
	public String exsistedIdCardByIdAndIdCardNo(PoorPeople poorPeople) {
		if (poorPeople == null || poorPeople.getIdCardNo() == null) {
			throw new BusinessValidationException("参数错误");
		}
		Long oldId = poorPeople.getId();
		List<PoorPeople> poorPeoples = poorPeopleDao
				.getPeopleAspirationsByIdCardNo(poorPeople);
		if (poorPeoples.size() == 0) {
			return null;
		} else if (poorPeoples.size() > 1) {
			return "身份证号码(" + poorPeople.getIdCardNo() + ")已存在重复数据，请联系管理员处理！";
		}
		poorPeople = poorPeoples.get(0);
		if (poorPeople != null && poorPeople.getId() != null
				&& !poorPeople.getId().equals(oldId)) {
			String userName = ThreadVariable.getUser().getUserName()
					.equals(poorPeople.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(poorPeople
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(poorPeople.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的身份证号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public String hasDuplicateSerialNumber(PoorPeople poorPeople) {
		if (poorPeople == null || poorPeople.getSerialNumber() == null) {
			throw new BusinessValidationException("参数错误");
		}
		Long oldId = poorPeople.getId();
		List<PoorPeople> poorPeoples = poorPeopleDao
				.getPeopleAspirationsBySerialNumber(poorPeople);
		if (poorPeoples.size() == 0) {
			return null;
		} else if (poorPeoples.size() > 1) {
			return "编号(" + poorPeople.getSerialNumber() + ")已存在重复数据，请联系管理员处理！";
		}
		poorPeople = poorPeoples.get(0);
		if (poorPeople != null && poorPeople.getId() != null
				&& !poorPeople.getId().equals(oldId)) {
			String userName = ThreadVariable.getUser().getUserName()
					.equals(poorPeople.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(poorPeople
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(poorPeople.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的编号,请重新输入";
		}
		return null;
	}

	private Map getReportMap(Long orgId, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startDate", start);
		map.put("endDate", end);
		return map;
	}

	private Map getReportMapWithIsFinish(Long orgId, Date start, Date end,
			Boolean isFinish) {
		Map map = getReportMap(orgId, start, end);
		map.put("isFinish", isFinish);
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportGroupCount(Long orgId, Date start,
			Date end) {
		return poorPeopleDao
				.getReportGroupCount(getReportMap(orgId, start, end));
	}

	@Override
	public List getUnfinishByMonth(Long orgId, Date start, Date end) {
		return poorPeopleDao
				.getUnfinishByMonth(getReportMap(orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(
			Long orgId, Date start, Date end) {
		return poorPeopleDao.getReportByFinishtypeAndItemcategory(getReportMap(
				orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(
			Long orgId, Date start, Date end) {
		return poorPeopleDao.getReportByFinishtypeAndItemcategory(getReportMap(
				orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(
			Long orgId, Date start, Date end) {
		return poorPeopleDao
				.getReportByReportToCityEndAndItemcategory(getReportMap(orgId,
						start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(
			Long orgId, Date start, Date end) {
		return poorPeopleDao
				.getReportByReportToTownEndAndItemcategory(getReportMap(orgId,
						start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndWithIsFinishAndItemcategory(
			Long orgId, Date start, Date end, Boolean isFinish) {
		return poorPeopleDao
				.getReportByReportToTownEndAndItemcategory(getReportMapWithIsFinish(
						orgId, start, end, isFinish));
	}

	@Override
	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(
			Long orgId, Date start, Date end, Long reportToTown,
			Long reportToCity) {
		return poorPeopleDao
				.getReportByVillageOrTownReportToCityAndItemcategory(getSearchMapWithReportLevel(
						orgId, start, end, reportToTown, reportToCity));
	}

	private Map getSearchMapWithReportLevel(Long orgId, Date start, Date end,
			Long reportToTown, Long reportToCity) {
		Map map = getReportMap(orgId, start, end);
		map.put("reportToTown", reportToTown);
		map.put("reportToCity", reportToCity);
		return map;
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
	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(
			String orgCode, Date start, Date end) {
		return poorPeopleDao
				.getReportByCreateOrgLevelAndItemcategory(getMapWithOrgCode(
						orgCode, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(
			String orgCode, Date start, Date end) {
		return poorPeopleDao
				.getReportByFinishOrgLevelAndItemcategory(getMapWithOrgCode(
						orgCode, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(
			String orgCode, Date start, Date end) {
		return poorPeopleDao
				.getReportByFinishtypeAndItemcategory(getByFinishtypeAndOrgCodeMap(
						orgCode, start, end));
	}

	private Map getByFinishtypeAndOrgCodeMap(String orgCode, Date start,
			Date end) {
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
	public List getReportToCityAndItemcategory(Long orgId, Date start,
			Date end, Long reportToCity) {
		return poorPeopleDao
				.getReportToCityAndItemcategory(getSearchMapWithReportLevel(
						orgId, start, end, null, reportToCity));
	}

	@Override
	public List<ReportGroupCount> getReportToTownAndItemcategory(Long orgId,
			Date start, Date end, Long reportToTownEnd) {
		return poorPeopleDao
				.getReportToTownAndItemcategory(getSearchMapWithReportLevel(
						orgId, start, end, reportToTownEnd, null));
	}
}
