package com.tianque.plugin.orgchange.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.dao.OrgChangeInfoDAO;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 组织机构迁移合并信息服务实现
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@Transactional
@Service("OrgChangeInfoService")
public class OrgChangeInfoServiceImpl implements OrgChangeInfoService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OrgChangeInfoDAO orgChangeInfoDAO;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public void addOrganizationChangeInfo(Organization organization,
			String orgIds, String operate) {
		try {
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			List<OrgChangeInfo> orgTransferAndMegerList = getOrgTransferAndMegerList(
					organization, orgIds, operate);
			for (OrgChangeInfo orgChangeInfo : orgTransferAndMegerList) {
				orgChangeInfoDAO.addInfo(orgChangeInfo);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("保存组织机构迁移合并信息时报错", e);
		}
	}

	private List<OrgChangeInfo> getOrgTransferAndMegerList(
			Organization organization, String orgIds, String operate) {
		List<Long> orgIdList = getOrgId(orgIds);
		return getOrgTransferAndMergInfoList(organization, orgIdList, operate);
	}

	private List<OrgChangeInfo> getOrgTransferAndMergInfoList(
			Organization organization, List<Long> orgIdList, String operate) {

		List<OrgChangeInfo> orgIdInfoList = new ArrayList<OrgChangeInfo>();
		for (Long orgId : orgIdList) {
			OrgChangeInfo orgChangeInfo = new OrgChangeInfo();
			orgChangeInfo.setIsDeal(false);
			orgChangeInfo.setSourceOrgId(orgId);
			orgChangeInfo.setTargetorgId(organization.getId());
			orgChangeInfo.setTargetorgCode(organization.getOrgInternalCode());
			if (OrgChangeUtils.ORGCHANGE_MEGER.equals(operate)) {
				orgChangeInfo.setOperateType(OrgChangeUtils.MERGE);
			} else if (OrgChangeUtils.ORGCHANGE_TRANSFER.equals(operate)) {
				orgChangeInfo.setOperateType(OrgChangeUtils.TRANSFER);
			}
			// 填充 源/目标 操作目标的完整层级路径

			Organization sourceOrg = organizationDubboService
					.getFullOrgById(orgId);
			if (validateHelper.nullObj(sourceOrg)
					|| validateHelper.nullObj(sourceOrg.getId())
					|| validateHelper.emptyString(sourceOrg
							.getOrgInternalCode())) {
				throw new BusinessValidationException("查询源组织机构信息不存在");
			}
			Organization targetOrg = organizationDubboService
					.getFullOrgById(organization.getId());
			if (validateHelper.nullObj(targetOrg)
					|| validateHelper.nullObj(targetOrg.getId())
					|| validateHelper.emptyString(targetOrg
							.getOrgInternalCode())) {
				throw new BusinessValidationException("查询目标组织机构信息不存在");
			}
			orgChangeInfo.setSourceOrgName(sourceOrg.getFullOrgName());
			orgChangeInfo.setDepartmentNo(sourceOrg.getDepartmentNo());
			orgChangeInfo.setTargetorgCode(targetOrg.getOrgInternalCode());
			orgChangeInfo.setTargetorgId(targetOrg.getId());
			orgChangeInfo.setTargetOrgName(targetOrg.getFullOrgName());
			orgIdInfoList.add(orgChangeInfo);

		}

		return orgIdInfoList;

	}

	private List<Long> getOrgId(String orgIds) {
		if (orgIds == null) {
			throw new BusinessValidationException("组织机构迁移合并传入参数不存在");
		}
		try {
			String[] ids = orgIds.split(",");
			List<Long> orgIdList = new ArrayList<Long>();
			for (String id : ids) {
				Organization organization = organizationDubboService
						.getSimpleOrgById(Long.valueOf(id));
				orgIdList.add(organization.getId());
			}
			return orgIdList;
		} catch (Exception e) {
			throw new ServiceValidationException("查询组织机构迁移合并信息时报错", e);
		}

	}

	@Override
	public PageResult<OrgChangeInfo> findNoDealInfoList(Integer isDeal,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortField", sidx);
			map.put("order", sord);
			map.put("isDeal", isDeal);
			return orgChangeInfoDAO.queryNoDealInfoForPageResult(map, pageNum,
					pageSize);
		} catch (Exception e) {
			throw new ServiceValidationException("查询可执行job的迁移合并信息报错", e);
		}
	}

	@Override
	public void deleteOrgChangeInfoByIds(Long changeId) {
		if (changeId == null) {
			throw new BusinessValidationException("job信息删除失败，未获得删除参数");
		}
		try {
			orgChangeInfoDAO.deleteOrgChangeInfo(changeId);
		} catch (Exception e) {
			throw new ServiceValidationException("删除组织机构迁移合并job信息出错", e);
		}
	}

	@Override
	public OrgChangeInfo getInfoById(Long id) {
		return orgChangeInfoDAO.getInfoById(id);
	}

	@Override
	public int getCount(OrgChangeInfo condition) {
		return orgChangeInfoDAO.getCount(condition);
	}

	@Override
	public List<OrgChangeInfo> queryAllForList(OrgChangeInfo condition,
			int start, int count) {
		return orgChangeInfoDAO.queryAllForList(condition, start, count);
	}

	@Override
	public void updateInfo(OrgChangeInfo orgChangeInfo) {
		orgChangeInfoDAO.updateInfo(orgChangeInfo);
	}

}
