package com.tianque.tenHouseholdsJoint.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tenHouseholdsJoint.dao.FamilyInfoDAO;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.validator.TenHouseHoldsFamilyValidatorImpl;

@Service("familyInfoService")
@Transactional
public class FamilyInfoServiceImpl implements FamilyInfoService {
	@Autowired
	private FamilyInfoDAO familyInfoDAO;
	@Autowired
	private TenHouseHoldsFamilyValidatorImpl validator;
	@Autowired
	private FamilyTeamServiceImpl familyTeamService;
	private static final Long FAMILY_STATUS = 0L;// 用户资料新增时默认状态
	private static final Integer LOGOUT_STATUS = 0;// 取消注销
	private static final Long STATUS = 0L;// 是否
	@Autowired
	private OrganizationDubboService organizationService;

	@Override
	public PageResult<FamilyInfo> queryFamilyInfoForPageResult(
			Long organizationId, DefaultSortAndPage defaultSortAndPage) {
		if (null == organizationId) {
			throw new BusinessValidationException("组织机构参数传递错误");
		}
		Organization organization = organizationService
				.getSimpleOrgById(organizationId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", defaultSortAndPage.getSidx());
		map.put("order", defaultSortAndPage.getSord());
		map.put("orgInternalCode", organization.getOrgInternalCode());
		return familyInfoDAO.queryFamilyInfoForPageResult(map,
				defaultSortAndPage.getPage(), defaultSortAndPage.getRows());
	}

	@Override
	public FamilyInfo addFamilyInfo(FamilyInfo familyInfo) {
		if (ExcelImportHelper.isImport.get()) {
			FamilyTeam team = familyTeamService.getFamilyTeamByName(familyInfo
					.getTeamName(), familyInfo.getOrganization().getId());
			if (team != null && team.getId() != null) {
				familyInfo.setTeamId(team.getId());
			} else {
				throw new BusinessValidationException("导入获取分组信息失败");
			}
			familyInfo.setFamilyState(FAMILY_STATUS);
			familyInfo.setLogOut(0L);
		} else {
			isValue(familyInfo);
			ValidateResult result = validator.validate(familyInfo);
			if (result.hasError()) {
				throw new BusinessValidationException(result.getErrorMessages());
			}
			FamilyTeam team = familyTeamService.getFamilyTeamById(familyInfo
					.getTeamId());
			if (team != null && team.getId() != null) {
				familyInfo.setTeamName(team.getTeamName());
			} else {
				throw new BusinessValidationException("导入获取分组信息失败");
			}
		}
		try {
			Long id = familyInfoDAO.addFamilyInfo(familyInfo);
			return getFamilyInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FamilyInfoServiceImpl的addFamilyInfo方法出现异常，原因：",
					"新增用户信息出错！", e);
		}

	}

	@Override
	public FamilyInfo updateFamilyInfo(FamilyInfo familyInfo) {
		isValue(familyInfo);
		ValidateResult result = validator.validate(familyInfo);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		FamilyTeam team = familyTeamService.getFamilyTeamById(familyInfo
				.getTeamId());
		if (team != null && team.getId() != null) {
			familyInfo.setTeamName(team.getTeamName());
		} else {
			throw new BusinessValidationException("导入获取分组信息失败");
		}
		try {
			familyInfoDAO.updateFamilyInfo(familyInfo);
			return getFamilyInfoById(familyInfo.getId());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类FamilyInfoServiceImpl的updateFamilyInfo方法出现异常，原因：",
					"修改用户信息出错！", e);
		}

	}

	@Override
	public void deleteFamilyInfo(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("请至少选择一条数据进行删除");
		}
		familyInfoDAO.deleteFamilyInfo(ids);
	}

	@Override
	public FamilyInfo getFamilyInfoById(Long id) {
		return familyInfoDAO.getFamilyInfoById(id);

	}

	@Override
	public PageResult<FamilyInfo> queryFamilyByConditionForPageResult(
			FamilyInfo familyInfo, int pageNum, int pageSize) {
		if (familyInfo == null || familyInfo.getOrganization() == null
				|| familyInfo.getOrganization().getId() == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		Organization organization = organizationService
				.getSimpleOrgById(familyInfo.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", organization.getOrgInternalCode());
		map.put("name", familyInfo.getName());
		map.put("helpLine", familyInfo.getHelpLine());
		map.put("certificateNumber", familyInfo.getCertificateNumber());
		map.put("alarmCenter", familyInfo.getAlarmCenter());
		map.put("teamId", familyInfo.getTeamId());
		map.put("familyState", familyInfo.getFamilyState());
		map.put("logOut", familyInfo.getLogOut());
		map.put("policeRoom", familyInfo.getPoliceRoom());
		map.put("sortField", "id");
		map.put("order", "desc");
		return familyInfoDAO.queryFamilyByConditionForPageResult(map, pageNum,
				pageSize);
	}

	@Override
	public void updateLogoutFamilyById(String[] ids, Integer logoutStatus) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("请至少选择一条信息进行注销");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (!LOGOUT_STATUS.equals(logoutStatus)) {// 注销
			map.put("logStatus", 1);
		} else {// 取消注销
			map.put("logStatus", 0);
		}
		for (String id : ids) {
			map.put("id", id);
			familyInfoDAO.updateLogoutFamilyById(map);
		}

	}

	@Override
	public Boolean checkHelpLine(FamilyInfo familyInfo) {
		int count = familyInfoDAO.queryFamilyInfoByHelpLineForList(familyInfo)
				.size();
		if (count > 0) {
			return false;
		}
		return true;
	}

	@Override
	public FamilyInfo getFamilyInfoByHelpLine(String helpLine) {
		return familyInfoDAO.getFamilyInfoByHelpLine(helpLine);
	}

	@Override
	public List<FamilyInfo> findTeamFamilyInfosByHelpLine(String helpLine) {
		FamilyInfo familyInfo = familyInfoDAO.getFamilyInfoByHelpLine(helpLine);
		if (familyInfo == null || familyInfo.getHelpLine() == null) {
			throw new BusinessValidationException("无效号码!!");
		}
		return findTeamFamilyInfosByTeamId(familyInfo.getTeamId());
	}

	@Override
	public List<FamilyInfo> findTeamFamilyInfosByTeamId(Long teamId) {
		return familyInfoDAO.queryFamilyInfoByTeamIdForList(teamId);
	}

	// 复选框是否选中，没勾选赋值为O
	private void isValue(FamilyInfo familyInfo) {
		if (null == familyInfo.getIsCallOther()) {
			familyInfo.setIsCallOther(STATUS);
		}
		if (null == familyInfo.getIsInformOther()) {
			familyInfo.setIsInformOther(STATUS);
		}
		if (null == familyInfo.getIsReceiveOtherAlarm()) {
			familyInfo.setIsReceiveOtherAlarm(STATUS);
		}
		if (null == familyInfo.getIsReceiveOtherCall()) {
			familyInfo.setIsReceiveOtherCall(STATUS);
		}

	}

	@Override
	public List<FamilyInfo> findFamilyInfosForList(FamilyInfo familyInfo) {
		return familyInfoDAO.queryFamilyInfosForList(familyInfo);
	}
}
