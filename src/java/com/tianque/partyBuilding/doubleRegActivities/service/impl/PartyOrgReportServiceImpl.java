package com.tianque.partyBuilding.doubleRegActivities.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.PartyOrgReportDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.PartyOrgReport;
import com.tianque.partyBuilding.doubleRegActivities.domain.ReportHasProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchPartyOrgReportVo;
import com.tianque.partyBuilding.doubleRegActivities.service.PartyOrgReportService;
import com.tianque.partyBuilding.doubleRegActivities.service.ReportHasProjectService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 党组织报到表:业务逻辑层
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
@Transactional
@Service("partyOrgReportService")
public class PartyOrgReportServiceImpl extends
		BaseServiceImpl<PartyOrgReport, SearchPartyOrgReportVo> implements
		PartyOrgReportService {

	@Autowired
	private PartyOrgReportDao partyOrgReportDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ReportHasProjectService reportHasProjectService;

	@Autowired
	@Qualifier("partyOrgReportValidator")
	private DomainValidator<PartyOrgReport> domainValidator;

	@Resource(name = "partyOrgReportDao")
	public void setBaseDao(PartyOrgReportDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public PartyOrgReport add(PartyOrgReport partyorgReport) {
		partyorgReportValidator(partyorgReport);
		try {
			String[] ids = null;
			if (partyorgReport.getClaimServiceProjectIds() != null
					&& !"".equals(partyorgReport.getClaimServiceProjectIds())) {
				ids = partyorgReport.getClaimServiceProjectIds().split(",");

			}
			partyorgReport = getBaseDao().add(partyorgReport);
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					reportHasProjectService
							.addReportHasProject(new ReportHasProject(
									partyorgReport.getId(), Long
											.valueOf(ids[i])));
				}
			}
			return partyorgReport;
		} catch (Exception e) {
			return dealException("PartyOrgReportServiceImpl", "add",
					"新增党组织报到表信息出现错误", e);
		}
	}

	@Override
	public PartyOrgReport update(PartyOrgReport partyorgReport) {
		partyorgReportValidator(partyorgReport);
		try {
			reportHasProjectService
					.deleteReportHasProjectByReportId(partyorgReport.getId());// 根据删除党组织报到的id删除与服务项目关联的信息
			String[] ids = null;
			if (partyorgReport.getClaimServiceProjectIds() != null
					&& !"".equals(partyorgReport.getClaimServiceProjectIds())) {
				ids = partyorgReport.getClaimServiceProjectIds().split(",");
			}
			partyorgReport = getBaseDao().update(partyorgReport);
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					reportHasProjectService
							.addReportHasProject(new ReportHasProject(
									partyorgReport.getId(), Long
											.valueOf(ids[i])));
				}
			}
			return partyorgReport;
		} catch (Exception e) {

			return dealException("PartyOrgReportServiceImpl", "update",
					"更新党组织报到表信息出现错误", e);
		}
	}

	private void partyorgReportValidator(PartyOrgReport partyorgReport) {
		ValidateResult baseDataValidator = domainValidator
				.validate(partyorgReport);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, Long isEmphasis,
			String logOutReason, Date logOutTime) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		try {
			partyOrgReportDao.updateEmphasiseByIds(ids, isEmphasis,
					logOutReason, logOutTime);
		} catch (Exception e) {
			dealException("PartyOrgReportServiceImpl", "updateEmphasiseByIds",
					"根据ids更新党组织报到的关注情况出现错误", e);
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logOutReason, Date logOutTime) {
		if (id == null) {
			throw new BusinessValidationException("Id不能为空");
		}
		partyOrgReportDao.updateEmphasiseById(id, isEmphasis, logOutReason,
				logOutTime);
	}

	@Override
	public String hasDuplicateName(Long id, Long orgId, String name) {
		if (orgId == null || name == null) {
			throw new BusinessValidationException("参数错误");
		}
		PartyOrgReport getPartyOrgReport = partyOrgReportDao
				.getPartyOrgReportByNameAndOrgId(name, orgId);
		if (getPartyOrgReport != null && getPartyOrgReport.getId() != null
				&& !getPartyOrgReport.getId().equals(id)) {

			String userName = ThreadVariable.getUser().getUserName()
					.equals(getPartyOrgReport.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(getPartyOrgReport
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(
									getPartyOrgReport.getCreateUser())
							.getName();
			return "当前组织机构(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public void deletePartyorgReportByIdsAndType(Long[] ids, String type) {
		try {
			if (ids == null || ids.length == 0) {
				throw new BusinessValidationException("Id不能为空");
			}
			reportHasProjectService.deleteReportHasProjectByReportIds(ids);// 根据党组织报到的id删除与服务项目关联的信息
			partyOrgReportDao.deletePartyorgReportByIdsAndType(ids, type);
		} catch (Exception e) {
			dealException("PartyOrgReportServiceImpl",
					"deletePartyorgReportByIdsAndType", "删除党组织报到表信息出现错误", e);
		}

	}
}
