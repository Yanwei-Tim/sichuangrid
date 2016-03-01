package com.tianque.xichang.working.peopleAspiration.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
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
import com.tianque.xichang.working.flow.service.AccountStepService;
import com.tianque.xichang.working.peopleAspiration.dao.PeopleAspirationsDao;
import com.tianque.xichang.working.peopleAspiration.domain.PeopleAspirations;
import com.tianque.xichang.working.peopleAspiration.domain.vo.SearchPeopleAspirationsVo;
import com.tianque.xichang.working.peopleAspiration.service.PeopleAspirationService;
import com.tianque.xichang.working.report.service.ReportCount;

@Transactional
@Service("peopleAspirationService")
public class PeopleAspirationServiceImpl extends
		BaseServiceImpl<PeopleAspirations, SearchPeopleAspirationsVo> implements
		PeopleAspirationService, ReportCount {

	private static Logger logger = LoggerFactory.getLogger(PeopleAspirationServiceImpl.class);

	@Autowired
	private PeopleAspirationsDao peopleAspirationsDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	@Qualifier("peopleAspirationsValidator")
	private AbstractWorkingValidator<PeopleAspirations> domainValidator;

	@Autowired
	private AccountStepService accountStepService;

	@Resource(name = "peopleAspirationsDao")
	public void setBaseDao(PeopleAspirationsDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public PeopleAspirations get(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误!");
		}
		return getBaseDao().get(id);
	}

	@Override
	public PeopleAspirations add(PeopleAspirations peopleAspirations) {
		peopleAspirationsValidator(peopleAspirations);
		try {
			peopleAspirations = getBaseDao().add(peopleAspirations);
			accountStepService.registerAccountStep(peopleAspirations.getId(),
					AccountType.PEOPLEASPIRATION);// 注册处理步骤
			return peopleAspirations;
		} catch (Exception e) {
			throw new ServiceValidationException("类PeopleaspirationsServiceImpl的add方法出现异常，原因：",
					"新增民生诉求表信息出现错误", e);
		}
	}

	@Override
	public PeopleAspirations update(PeopleAspirations peopleAspirations) {
		peopleAspirationsValidator(peopleAspirations);
		try {
			peopleAspirations = getBaseDao().update(peopleAspirations);
			return peopleAspirations;
		} catch (Exception e) {
			throw new ServiceValidationException("类PeopleaspirationsServiceImpl的update方法出现异常，原因：",
					"更新民生诉求表信息出现错误", e);
		}
	}

	private void peopleAspirationsValidator(PeopleAspirations peopleAspirations) {
		ValidateResult baseDataValidator = domainValidator.validate(peopleAspirations);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String exsistedIdCardByIdAndIdCardNo(Long id, Long orgId, String idCardNo) {
		if (orgId == null || idCardNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		PeopleAspirations getPeopleAspirations = peopleAspirationsDao
				.getPeopleAspirationsByIdCardNo(idCardNo);

		if (getPeopleAspirations != null && getPeopleAspirations.getId() != null
				&& !getPeopleAspirations.getId().equals(id)) {
			String userName = ThreadVariable.getUser().getUserName()
					.equals(getPeopleAspirations.getCreateUser()) ? "您" : permissionService
					.getFullUserByUerName(getPeopleAspirations.getCreateUser()) == null ? ""
					: permissionService.getFullUserByUerName(getPeopleAspirations.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的身份证号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public String hasDuplicateSerialNumber(Long id, Long orgId, String serialNumber) {
		if (orgId == null || serialNumber == null) {
			throw new BusinessValidationException("参数错误");
		}
		PeopleAspirations getPeopleAspirations = peopleAspirationsDao
				.getPeopleAspirationsBySerialNumber(serialNumber);

		if (getPeopleAspirations != null && getPeopleAspirations.getId() != null
				&& !getPeopleAspirations.getId().equals(id)) {
			String userName = ThreadVariable.getUser().getUserName()
					.equals(getPeopleAspirations.getCreateUser()) ? "您" : permissionService
					.getFullUserByUerName(getPeopleAspirations.getCreateUser()) == null ? ""
					: permissionService.getFullUserByUerName(getPeopleAspirations.getCreateUser())
							.getName();
			return "当前用户(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public PageInfo findDonePagerBySearchVo(SearchPeopleAspirationsVo searchVo, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			return peopleAspirationsDao.findDonePagerBySearchVo(searchVo, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleaspirationsServiceImpl的findDonePagerBySearchVo方法出现异常，原因：",
					"根据SearchVo进行查询已办信息(提供分页、查找、排序功能)出现错误", e);
		}
	}

	private Map getReportMap(Long orgId, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startDate", start);
		map.put("endDate", end);
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportGroupCount(Long orgId, Date start, Date end) {
		return peopleAspirationsDao.getReportGroupCount(getReportMap(orgId, start, end));
	}

	@Override
	public List getUnfinishByMonth(Long orgId, Date start, Date end) {
		return peopleAspirationsDao.getUnfinishByMonth(getReportMap(orgId, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(Long orgId, Date start,
			Date end) {
		return peopleAspirationsDao.getReportByFinishtypeAndItemcategory(getReportMap(orgId, start,
				end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(String orgCode, Date start,
			Date end) {
		return peopleAspirationsDao
				.getReportByFinishtypeAndItemcategory(getByFinishtypeAndOrgCodeMap(orgCode, start,
						end));
	}

	private Map getByFinishtypeAndOrgCodeMap(String orgCode, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("orgCodeFindLevel", 2);
		map.put("startDate", start);
		map.put("endDate", end);
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(Long orgId, Date start,
			Date end) {
		return peopleAspirationsDao.getReportByFinishtypeAndItemcategory(getReportMap(orgId, start,
				end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(Long orgId, Date start,
			Date end) {
		return peopleAspirationsDao.getReportByReportToCityEndAndItemcategory(getReportMap(orgId,
				start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(Long orgId, Date start,
			Date end) {
		return peopleAspirationsDao.getReportByReportToTownEndAndItemcategory(getReportMap(orgId,
				start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndWithIsFinishAndItemcategory(Long orgId,
			Date start, Date end, Boolean isFinish) {
		return peopleAspirationsDao
				.getReportByReportToTownEndAndItemcategory(getReportMapWithIsFinish(orgId, start,
						end, isFinish));
	}

	private Map getReportMapWithIsFinish(Long orgId, Date start, Date end, Boolean isFinish) {
		Map map = getReportMap(orgId, start, end);
		map.put("isFinish", isFinish);
		return map;
	}

	@Override
	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(Long orgId,
			Date start, Date end, Long reportToTown, Long reportToCity) {
		return peopleAspirationsDao
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
		return peopleAspirationsDao.getReportByCreateOrgLevelAndItemcategory(getMapWithOrgCode(
				orgCode, start, end));
	}

	@Override
	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(String orgCode,
			Date start, Date end) {
		return peopleAspirationsDao.getReportByFinishOrgLevelAndItemcategory(getMapWithOrgCode(
				orgCode, start, end));
	}

	private Map getMapWithOrgCode(String orgCode, Date start, Date end) {
		Map map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("startDate", start);
		map.put("endDate", end);
		map.putAll(getOrganizationTypes());
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
		return peopleAspirationsDao.getReportToCityAndItemcategory(getSearchMapWithReportLevel(
				orgId, start, end, null, reportToCity));
	}

	@Override
	public List<ReportGroupCount> getReportToTownAndItemcategory(Long orgId, Date start, Date end,
			Long reportToTownEnd) {
		return peopleAspirationsDao.getReportToTownAndItemcategory(getSearchMapWithReportLevel(
				orgId, start, end, reportToTownEnd, null));
	}

	@Override
	public PeopleAspirations updatePeopleAspirations(PeopleAspirations peopleAspirations) {
		peopleAspirationsValidator(peopleAspirations);
		try {
			return peopleAspirationsDao.updatePeopleAspirations(peopleAspirations);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改民生诉求台账信息出现错误", e.getMessage());
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}
	
	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.PEOPLEASPIRATIONS_KEY)
	public void delete(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			delete(id);
		}

	}
}
