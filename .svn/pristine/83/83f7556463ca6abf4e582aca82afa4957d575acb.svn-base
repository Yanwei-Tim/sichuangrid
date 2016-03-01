package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.NewEconomicOrganizationsDao;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchNewEconomicOrganizationsVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.NewEconomicOrganizationsService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;

@Transactional
@Service("newEconomicOrganizationsService")
public class NewEconomicOrganizationsServiceImpl extends LogableService
		implements NewEconomicOrganizationsService {

	@Qualifier("newEconomicOrganizationsValidator")
	@Autowired
	private DomainValidator<NewEconomicOrganizations> domainValidator;
	@Autowired
	private NewEconomicOrganizationsDao newEconomicOrganizationsDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;
	@Autowired
	private KeyPlaceService placeService;

	@Override
	public NewEconomicOrganizations addNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations) {
		try {
			autoFillOrganizationInternalCode(newEconomicOrganizations);
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult newEconomicOrganizationsValidator = domainValidator
						.validate(newEconomicOrganizations);
				if (newEconomicOrganizationsValidator.hasError()) {
					throw new BusinessValidationException(
							newEconomicOrganizationsValidator
									.getErrorMessages());
				}
			}
			autoFillChinesePinyin(newEconomicOrganizations);
			newEconomicOrganizations = newEconomicOrganizationsDao
					.addNewEconomicOrganizations(newEconomicOrganizations);
			// 新经济组织新增的轨迹
			orgLocationTracksService.addNewEconomicOrganizationsLocationTracks(
					newEconomicOrganizations,
					BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.ADDT, "新经济组织新增");
			placeService.execute(DocumentType.NEWECONOMICORGANIZATIONS,
					OperateMode.ADD.toString(), newEconomicOrganizations);
			return newEconomicOrganizations;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public NewEconomicOrganizations getNewEconomicOrganizationsById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("ID不能为空");
		}
		return newEconomicOrganizationsDao.getNewEconomicOrganizationsById(id);
	}

	@Override
	public PageInfo<NewEconomicOrganizations> findNewEconomicOrganizationsForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return newEconomicOrganizationsDao
						.findNewEconomicOrganizationsForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageNum, pageSize,
								sidx, sord, isEmphasis);
			}
		}
	}

	@Override
	public NewEconomicOrganizations updateNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations) {
		autoFillOrganizationInternalCode(newEconomicOrganizations);
		ValidateResult newEconomicOrganizationsValidator = domainValidator
				.validate(newEconomicOrganizations);

		if (newEconomicOrganizationsValidator.hasError()) {
			throw new BusinessValidationException(
					newEconomicOrganizationsValidator.getErrorMessages());
		}
		autoFillChinesePinyin(newEconomicOrganizations);
		// 新经济组织重新关注增加轨迹
		orgLocationTracksService.addNewEconomicOrganizationsLocationTracks(
				newEconomicOrganizations,
				BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY,
				OrgLocationInitType.TRANSFOR_DOOM, null,
				OrgLocationTracksOperationType.AGAIN_ATTENTION, "新经济组织重新关注");
		newEconomicOrganizations = newEconomicOrganizationsDao
				.updateNewEconomicOrganizations(newEconomicOrganizations);
		placeService.execute(DocumentType.NEWECONOMICORGANIZATIONS,
				OperateMode.EDIT.toString(), newEconomicOrganizations);
		return newEconomicOrganizations;
	}

	@Override
	public boolean deleteNewEconomicOrganizationsById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		NewEconomicOrganizations domain = newEconomicOrganizationsDao
				.getNewEconomicOrganizationsById(id);
		if (null != domain) {
			// 新经济组织删除的轨迹
			orgLocationTracksService.addNewEconomicOrganizationsLocationTracks(
					domain, BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.DELETE, "新经济组织删除");
			newEconomicOrganizationsDao.deleteNewEconomicOrganizations(id);
			placeService.execute(DocumentType.NEWECONOMICORGANIZATIONS,
					OperateMode.DELETE.toString(), domain);
		}
		return true;
	}

	public void deleteNewEconomicOrganizationsByIds(Long[] ids) {
		try {
			if (ids == null || ids.length <= 0L) {
				throw new BusinessValidationException("id没有获得");
			}
			/*
			 * for (Long id : ids) { NewEconomicOrganizations domain =
			 * newEconomicOrganizationsDao .getNewEconomicOrganizationsById(id);
			 * if (null != domain) { newEconomicOrganizationsDao
			 * .deleteNewEconomicOrganizations(id); } }
			 */
			newEconomicOrganizationsDao
					.deleteNewEconomicOrganizationsForMore(ids);

		} catch (Exception e) {
			throw new ServiceValidationException("删除新经济组织出现错误", e);
		}

	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.NEWECONOMICORG_KEY)
	public void deleteNewEconomicOrganizationssByIdList(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("新经济组织idList不能为空");
		}
		for (Long id : idList) {
			NewEconomicOrganizations domain = getNewEconomicOrganizationsById(id);
			this.deleteNewEconomicOrganizationsById(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getName(),
						BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<NewEconomicOrganizations> updateLogOutOfNewEconomicOrganizationssByIdList(
			List<Long> idList, Long isEmphasis) {
		if (null == idList) {
			throw new BusinessValidationException("新经济组织idList不能为空");
		}
		List<NewEconomicOrganizations> newEconomicOrganizationsList = new ArrayList<NewEconomicOrganizations>();
		for (Long id : idList) {
			NewEconomicOrganizations newEconomicOrganizations = this
					.getNewEconomicOrganizationsById(id);
			newEconomicOrganizations.setIsEmphasis(isEmphasis);
			newEconomicOrganizations = this
					.updateNewEconomicOrganizations(newEconomicOrganizations);
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.NEWECONOMICORGANIZATIONS.toString(),
					isEmphasis);
			newEconomicOrganizationsList.add(newEconomicOrganizations);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"updateServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class, Long.class },
						BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY, id, 0l);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newEconomicOrganizationsList;
	}

	private boolean validateNumber(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		if (searchNewEconomicOrganizationsVo.getAreaStart() != null
				&& !"".equals(searchNewEconomicOrganizationsVo.getAreaStart())
				&& validateHelper
						.illegalNumeric(searchNewEconomicOrganizationsVo
								.getAreaStart())) {
			return false;
		} else if (searchNewEconomicOrganizationsVo.getAreaEnd() != null
				&& !"".equals(searchNewEconomicOrganizationsVo.getAreaEnd())
				&& validateHelper
						.illegalNumeric(searchNewEconomicOrganizationsVo
								.getAreaEnd())) {
			return false;
		} else if (searchNewEconomicOrganizationsVo.getEmployeeNumberStart() != null
				&& validateHelper.illegalNum(searchNewEconomicOrganizationsVo
						.getEmployeeNumberStart())) {
			return false;
		} else if (searchNewEconomicOrganizationsVo.getEmployeeNumberEnd() != null
				&& validateHelper.illegalNum(searchNewEconomicOrganizationsVo
						.getEmployeeNumberEnd())) {
			return false;
		} else if (searchNewEconomicOrganizationsVo.getPartyMemberNumberStart() != null
				&& validateHelper.illegalNum(searchNewEconomicOrganizationsVo
						.getPartyMemberNumberStart())) {
			return false;
		} else if (searchNewEconomicOrganizationsVo.getPartyMemberNumberEnd() != null
				&& validateHelper.illegalNum(searchNewEconomicOrganizationsVo
						.getPartyMemberNumberEnd())) {
			return false;
		}
		return true;
	}

	@Override
	public PageInfo<NewEconomicOrganizations> searchNewEconomicOrganizationss(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		if (validateNumber(searchNewEconomicOrganizationsVo)) {
			searchNewEconomicOrganizationsVo.setSortField(sortField);
			searchNewEconomicOrganizationsVo.setOrder(order);
			return newEconomicOrganizationsDao.searchNewEconomicOrganizationss(
					pageNum, pageSize, searchNewEconomicOrganizationsVo);
		} else {
			return constructEmptyPageInfo();
		}

	}

	@Override
	public List<NewEconomicOrganizations> searchAllNewEconomicOrganizationss(
			String sortField, String order,
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		if (validateNumber(searchNewEconomicOrganizationsVo)) {
			searchNewEconomicOrganizationsVo.setSortField(sortField);
			searchNewEconomicOrganizationsVo.setOrder(order);
			return newEconomicOrganizationsDao
					.searchAllNewEconomicOrganizationss(searchNewEconomicOrganizationsVo);
		} else {
			return new ArrayList<NewEconomicOrganizations>();
		}

	}

	private void autoFillOrganizationInternalCode(
			NewEconomicOrganizations newEconomicOrganizations) {
		if (newEconomicOrganizations.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(newEconomicOrganizations
							.getOrganization().getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			newEconomicOrganizations.setOrgInternalCode(organization
					.getOrgInternalCode());
		}
	}

	private void autoFillChinesePinyin(
			NewEconomicOrganizations newEconomicOrganizations) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(newEconomicOrganizations.getName());
		newEconomicOrganizations.setFullPinyin((String) pinyin
				.get("fullPinyin"));
		newEconomicOrganizations.setSimplePinyin((String) pinyin
				.get("simplePinyin"));
	}

	private PageInfo<NewEconomicOrganizations> constructEmptyPageInfo() {
		PageInfo<NewEconomicOrganizations> result = new PageInfo<NewEconomicOrganizations>();
		result.setResult(new ArrayList<NewEconomicOrganizations>());
		return result;
	}

	@Override
	public NewEconomicOrganizations updateNewEconomicOrganizationsByNameAndLicenseNumber(
			String name, String licenseNumber, Long orgId,
			NewEconomicOrganizations domain) {
		try {
			NewEconomicOrganizations older = this
					.getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(
							name, licenseNumber, orgId).get(0);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return this.updateNewEconomicOrganizations(domain);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public List<NewEconomicOrganizations> getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(
			String name, String licenseNumber, Long orgId) {
		return newEconomicOrganizationsDao
				.getNewEconomicOrganizationsByNameAndLicenseNumberAndOrgId(
						name, licenseNumber, orgId);
	}

	@Override
	public boolean hasDuplicateNewEconomicOrganizationsByName(String name,
			Long orgId, Long exceptedId) {
		NewEconomicOrganizations exsited = newEconomicOrganizationsDao
				.getNewEconomicOrganizationsByNameAndOrgId(name, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public boolean hasDuplicateNewEconomicOrganizationsByLicenseNumber(
			String licenseNumber, Long orgId, Long exceptedId) {
		NewEconomicOrganizations exsited = newEconomicOrganizationsDao
				.getNewEconomicOrganizationsByLicenseNumberAndOrgId(
						licenseNumber, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	public NewEconomicOrganizations hasDuplicateNewEconomicOrganizations(
			Long orgId, String name) {
		NewEconomicOrganizations exsited = newEconomicOrganizationsDao
				.getNewEconomicOrganizationsByNameAndOrgId(name, orgId);
		return exsited;
	}

	@Override
	public void updateEmphasiseByIds(List<Long> ids,
			NewEconomicOrganizations location) {
		for (Long id : ids) {
			updateEmphasiseById(id, location.getIsEmphasis(), location
					.getLogoutDetail().getLogoutReason());
			try {
				PluginServiceHelpUtil.doService("routerService",
						"updateServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class, Long.class },
						BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY, id, 1l);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logOutReason) {
		NewEconomicOrganizations newEconomicOrganizations = getNewEconomicOrganizationsById(id);
		try {
			// 新经济组织取消关注增加轨迹
			orgLocationTracksService.addNewEconomicOrganizationsLocationTracks(
					newEconomicOrganizations,
					BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.CANCEL_ATTENTION,
					"新经济组织取消关注");
			newEconomicOrganizationsDao.updateEmphasiseById(id, isEmphasis,
					logOutReason);
		} catch (Exception e) {
			dealException(this, "updateEmphasiseById", "注销或取消注销新经济组织出现错误", e);
		}
	}

	@Override
	public Integer getCount(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		// TODO Auto-generated method stub
		return newEconomicOrganizationsDao
				.getCount(searchNewEconomicOrganizationsVo);
	}

	/******************** 组织机构迁移合并方法 ********************/
	/**
	 * 
	 * @Title: findRepeatNewEconomicOrganizationsForOrgChange
	 * @Description: TODO查询出源部门和目标部门重复的非公有制经济组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewEconomicOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午05:04:16
	 */
	@Override
	public List<NewEconomicOrganizations> findRepeatNewEconomicOrganizationsForOrgChange(
			Long newOrgId, Long oldOrgId) {
		try {
			if (newOrgId == null || oldOrgId == null) {
				throw new BusinessValidationException("查询条件不完善");
			}
			return newEconomicOrganizationsDao
					.findRepeatNewEconomicOrganizationsForOrgChange(newOrgId,
							oldOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewEconomicOrganizationsServiceImpl的findRepeatNewEconomicOrganizationsForOrgChange方法出现异常",
					"查询重复数据失败", e);
		}
	}

	/**
	 * 
	 * @Title: findRepeatNewEconomicOrganizationsByNameForOrgChange
	 * @Description: TODO查询出源部门和目标部门名称重复的非公有制经济组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewEconomicOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午05:35:43
	 */
	@Override
	public List<NewEconomicOrganizations> findRepeatNewEconomicOrganizationsByNameForOrgChange(
			Long newOrgId, Long oldOrgId) {
		try {
			if (newOrgId == null || oldOrgId == null) {
				throw new BusinessValidationException("查询条件不完善");
			}
			return newEconomicOrganizationsDao
					.findRepeatNewEconomicOrganizationsByNameForOrgChange(
							newOrgId, oldOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewEconomicOrganizationsServiceImpl的findRepeatNewEconomicOrganizationsByNameForOrgChange方法出现异常",
					"查询重复数据失败", e);
		}
	}
}
