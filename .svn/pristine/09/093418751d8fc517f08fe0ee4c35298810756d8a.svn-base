package com.tianque.partyBuilding.baseInfos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.baseInfos.dao.CaseImageUploadDao;
import com.tianque.partyBuilding.baseInfos.domain.CaseImageUpload;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchCaseImageUploadVo;
import com.tianque.partyBuilding.baseInfos.service.CaseImageUploadService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 图片上传表:业务逻辑层
 * 
 * @author
 */
@Transactional
@Service("caseImageUploadService")
public class CaseImageUploadServiceImpl extends
		BaseServiceImpl<CaseImageUpload, SearchCaseImageUploadVo> implements
		CaseImageUploadService {

	@Autowired
	private CaseImageUploadDao caseImageUploadDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Resource(name = "caseImageUploadDao")
	public void setBaseDao(CaseImageUploadDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public CaseImageUpload add(CaseImageUpload entity) {
		autoFillOrganizationInternalCode(entity);
		try {
			entity = getBaseDao().add(entity);
			return entity;
		} catch (Exception e) {
			return dealException("CaseImageUploadServiceImpl", "add",
					"新增社区基本情况表信息出现错误", e);
		}
	}

	@Override
	public CaseImageUpload update(CaseImageUpload entity) {
		try {
			entity = getBaseDao().update(entity);
			return entity;
		} catch (Exception e) {
			return dealException("CaseImageUploadServiceImpl", "update",
					"更新社区基本情况表信息出现错误", e);
		}
	}

	private void autoFillOrganizationInternalCode(
			CaseImageUpload caseImageUpload) {
		if (caseImageUpload.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(caseImageUpload.getOrganization().getId());
			caseImageUpload.setOrgInternalCode(organization
					.getOrgInternalCode());
		}
	}

	@Override
	public List<CaseImageUpload> getCaseImageUploadsByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("找不到指定网格");
		}
		return caseImageUploadDao.getCaseImageUploadsByOrgId(orgId);
	}

	@Override
	public boolean deleteCaseImageUploadByIdAndOrgId(Long id, Long orgId) {
		if (id == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return caseImageUploadDao.deleteCaseImageUploadByIdAndOrgId(id, orgId);
	}

	@Override
	public List<CaseImageUpload> findByOrgIdAndType(Long orgId, String type) {
		if (orgId == null) {
			throw new BusinessValidationException("找不到指定网格");
		}
		return caseImageUploadDao.findByOrgIdAndType(orgId, type);
	}

}
