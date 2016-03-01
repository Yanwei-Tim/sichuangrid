package com.tianque.tenHouseholdsJoint.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tenHouseholdsJoint.dao.FamilyTeamDAO;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;
import com.tianque.tenHouseholdsJoint.validator.TenHouseHoldsGroupValidatorImpl;

@Service("familyTeamService")
@Transactional
public class FamilyTeamServiceImpl extends AbstractBaseService implements
		FamilyTeamService {
	@Autowired
	private FamilyTeamDAO familyTeamDao;
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private TenHouseHoldsGroupValidatorImpl validator;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public FamilyTeam addFamilyTeam(FamilyTeam familyTeam) {
		if (!ExcelImportHelper.isImport.get()) {
			ValidateResult result = validator.validate(familyTeam);
			if (result.hasError()) {
				throw new BusinessValidationException(result.getErrorMessages());
			}
		}
		Long id = familyTeamDao.addFamilyTeam(familyTeam);
		if (id != null) {
			return familyTeamDao.getFamilyTeamById(id);
		} else {
			return null;
		}
	}

	@Override
	public PageResult<FamilyTeam> queryFamilyTeamForPageResult(
			Organization organization, DefaultSortAndPage defaultSortAndPage) {
		return familyTeamDao.queryFamilyTeamForPageResult(organization,
				defaultSortAndPage.getPage(), defaultSortAndPage.getRows());
	}

	@Override
	public FamilyTeam getFamilyTeamByName(String name, Long orgId) {
		if (name == null || orgId == null) {
			return new FamilyTeam();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamName", name);
		map.put("orgId", orgId);
		return familyTeamDao.getFamilyTeamByName(map);
	}

	@Override
	public FamilyTeam getFamilyTeamById(Long id) {
		return familyTeamDao.getFamilyTeamById(id);
	}

	@Override
	public int updateFamilyTeam(FamilyTeam familyTeam) {
		ValidateResult result = validator.validate(familyTeam);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		return familyTeamDao.updateFamilyTeam(familyTeam);
	}

	@Override
	public void delteFamilyTeamByIds(String ids) {
		if (!StringUtil.isStringAvaliable(ids)) {
			throw new BusinessValidationException("数据传输错误!");
		}
		String[] idsArray = ids.split(",");
		List<Long> idList = new ArrayList<Long>();
		for (String id : idsArray) {
			// 判断该分组下是否有用户
			List<FamilyInfo> familyInfoList = familyInfoService
					.findTeamFamilyInfosByTeamId(Long.parseLong(id));
			if (familyInfoList != null && familyInfoList.size() != 0) {
				throw new BusinessValidationException(
						"所删除分组中有含有成员分组，请先删除成员再删除分组!");
			} else {
				idList.add(Long.valueOf(id));
			}
		}
		if (idList.size() == 0) {
			throw new BusinessValidationException("数据传输错误!");
		}
		familyTeamDao.batchDeleteFamilyTeamByIds(idList);
	}

	@Override
	public PageResult<FamilyTeam> querySearchFamilyTeamForPageResult(
			FamilyTeam familyTeam, DefaultSortAndPage defaultSortAndPage) {
		return familyTeamDao.querySearchFamilyTeamForPageResult(familyTeam,
				defaultSortAndPage.getPage(), defaultSortAndPage.getRows());
	}

	@Override
	public List<FamilyTeam> queryFamilyTeamByOrgIdForList(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构信息未获得!");
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization == null) {
			throw new BusinessValidationException("组织机构信息未获得!");
		}

		return familyTeamDao.queryFamilyTeamByOrgIdForList(organization
				.getOrgInternalCode());
	}

	@Override
	public boolean checkFamilyTeam(FamilyTeam familyTeam) {
		if (familyTeam == null || familyTeam.getTeamCode() == null
				|| familyTeam.getOrganization() == null
				|| familyTeam.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		return familyTeamDao.getFamilyTeamByTeamCodeAndOrgid(familyTeam) > 0 ? false
				: true;
	}

	@Override
	public List<FamilyTeam> findFamilyTeamsForList(FamilyTeam familyTeam) {
		return familyTeamDao.queryFamilyTeamsForList(familyTeam);
	}

}
