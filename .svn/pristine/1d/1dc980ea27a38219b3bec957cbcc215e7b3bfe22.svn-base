package com.tianque.baseInfo.newSocietyOrganizations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.newSocietyOrganizations.dao.NewSocietyOrganizationsDao;
import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;

@Service("newSocietyOrganizationsService")
@Transactional
public class NewSocietyOrganitionsServiceImpl extends LogableService implements
		NewSocietyOrganitionsService {
	@Autowired
	private NewSocietyOrganizationsDao newSocietyOrganizationsDao;
	@Autowired
	OrganizationDubboService organizationDubboService;

	@Qualifier("newSocietyOrganizationsValidate")
	@Autowired
	private DomainValidator<NewSocietyOrganizations> domainValidator;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;
	@Autowired
	private KeyPlaceService placeService;

	@Override
	public boolean hasDuplicateNewSocietyOrganizationsName(Long ownerOrgId,
			String newSocietyOrganizationsName, Long exceptedId) {
		NewSocietyOrganizations exsited = newSocietyOrganizationsDao
				.getNewSocietyOrganizationsByName(newSocietyOrganizationsName,
						ownerOrgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	public NewSocietyOrganizations hasDuplicateNewSocietyOrganizations(
			Long ownerOrgId, String newSocietyOrganizationsName) {
		NewSocietyOrganizations exsited = newSocietyOrganizationsDao
				.getNewSocietyOrganizationsByName(newSocietyOrganizationsName,
						ownerOrgId);
		return exsited;
	}

	@Override
	public PageInfo<NewSocietyOrganizations> findNewSocietyOrganizationsForPageByOrgInternalCode(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return newSocietyOrganizationsDao
						.findNewSocietyOrganizationsForPageByOrgInternalCode(
								org.getOrgInternalCode(), page, rows, sidx,
								sord, isEmphasis);
			}
		}
	}

	@Override
	public NewSocietyOrganizations addNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				ValidateResult idleValidator = domainValidator
						.validate(newSocietyOrganizations);
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				}
			}
			autoFillOrganizationInternalCode(newSocietyOrganizations);
			autoFillChinesePinyin(newSocietyOrganizations);
			NewSocietyOrganizations domain = newSocietyOrganizationsDao
					.addNewSocietyOrganizations(newSocietyOrganizations);
			// 社会组织新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getName(), domain
							.getOrgInternalCode()),
					BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.ADDT, "社会组织新增");
			placeService.execute(DocumentType.NEWSOCIETYFEDERATIONS,
					OperateMode.ADD.toString(), domain);
			return domain;
		} catch (Exception e) {
			logger.error(
					"类NewSocietyOrganitionsServiceImpl的addNewSocietyOrganizations方法出现异常，原因：",
					e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增社会组织出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public NewSocietyOrganizations getSimpleNewSocietyOrganizations(Long id) {
		return newSocietyOrganizationsDao.getSimpleNewSocietyOrganizations(id);
	}

	@Override
	public NewSocietyOrganizations updateNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations) {
		try {
			ValidateResult idleValidator = domainValidator
					.validate(newSocietyOrganizations);
			if (idleValidator.hasError()) {
				throw new BusinessValidationException(
						idleValidator.getErrorMessages());
			}

			autoFillOrganizationInternalCode(newSocietyOrganizations);
			autoFillChinesePinyin(newSocietyOrganizations);
			newSocietyOrganizations = newSocietyOrganizationsDao
					.updateNewSocietyOrganizations(newSocietyOrganizations);
			placeService.execute(DocumentType.NEWSOCIETYFEDERATIONS,
					OperateMode.EDIT.toString(), newSocietyOrganizations);
			return newSocietyOrganizations;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewSocietyOrganitionsServiceImpl的updateNewSocietyOrganizations方法出现异常，原因：",
					"更新社会组织出现错误", e);
		}
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.NEWSOCIETYORG_KEY)
	public void deleteNewSocietyOrganizationsByIds(List<Long> deleteIds) {
		if (deleteIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		for (Long id : deleteIds) {
			NewSocietyOrganizations domain = newSocietyOrganizationsDao
					.getSimpleNewSocietyOrganizations(id);
			deleteNewSocietyOrganizationsById(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getName(),
						BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY, id);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	@Override
	public void deleteNewSocietyOrganizationsById(Long id) {
		try {
			if (id == null || id < 0L) {
				throw new BusinessValidationException("id没有获得");
			}
			NewSocietyOrganizations domain = getSimpleNewSocietyOrganizations(id);
			log(WARN, ModelType.NEWSOCIETYFEDERATION, ThreadVariable
					.getSession().getUserName() + "删除社会组织" + domain.getName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
			// 社会组织删除的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getName(), domain
							.getOrgInternalCode()),
					BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.DELETE, "社会组织删除");
			newSocietyOrganizationsDao.deleteNewSocietyOrganizationsById(id);
			placeService.execute(DocumentType.NEWSOCIETYFEDERATIONS,
					OperateMode.DELETE.toString(), domain);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewSocietyOrganitionsServiceImpl的deleteNewSocietyOrganizationsById方法出现异常，原因：",
					"删除社会组织出现错误", e);
		}
	}

	public void deleteNewSocietyOrganizationsByIds(Long[] ids) {
		try {
			if (ids == null || ids.length <= 0L) {
				throw new BusinessValidationException("id没有获得");
			}
			for (Long id : ids) {
				NewSocietyOrganizations domain = getSimpleNewSocietyOrganizations(id);
				log(WARN, ModelType.NEWSOCIETYFEDERATION,
						ThreadVariable.getSession().getUserName() + "删除新社会组织"
								+ domain.getName(), OperatorType.DELETE,
						ObjectToJSON.convertJSON(domain));
				newSocietyOrganizationsDao
						.deleteNewSocietyOrganizationsById(id);
			}

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewSocietyOrganitionsServiceImpl的deleteNewSocietyOrganizationsByIds方法出现异常，原因：",
					"删除新社会组织出现错误", e);
		}
	}

	@Override
	public void updateEmphasiseByIds(List<Long> ids,
			NewSocietyOrganizations location) {
		for (Long id : ids) {
			updateEmphasiseById(id, location.getIsEmphasis(),
					location.getLogOutReason());
			try {
				PluginServiceHelpUtil.doService("routerService",
						"updateServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class, Long.class },
						BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY, id,
						location.getIsEmphasis() ? 1l : 0l);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logOutReason) {
		NewSocietyOrganizations domain = getSimpleNewSocietyOrganizations(id);
		try {
			if (isEmphasis) {
				// 社会组织取消关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(domain, domain.getName(),
								domain.getOrgInternalCode()),
						BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.CANCEL_ATTENTION,
						"社会组织取消关注");
			} else {
				// 社会组织重新关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(domain, domain.getName(),
								domain.getOrgInternalCode()),
						BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.AGAIN_ATTENTION,
						"社会组织重新关注");
			}
			Long emphasis = 0L;
			if (isEmphasis) {
				emphasis = 1L;
			}
			newSocietyOrganizationsDao.updateEmphasiseById(id, isEmphasis,
					logOutReason);
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.NEWSOCIETYFEDERATIONS.toString(), emphasis);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewSocietyOrganitionsServiceImpl的updateEmphasiseById方法出现异常，原因：",
					"注销或取消注销社会组织出现错误", e);
		}
	}

	@Override
	public NewSocietyOrganizations updateNewSocietyOrganizationsByName(
			String name, Long orgId, NewSocietyOrganizations domain) {
		try {
			NewSocietyOrganizations older = newSocietyOrganizationsDao
					.getNewSocietyOrganizationsByName(name, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateNewSocietyOrganizations(domain);
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	private void autoFillOrganizationInternalCode(
			NewSocietyOrganizations newSocietyFederation) {
		if (newSocietyFederation.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(newSocietyFederation.getOrganization()
							.getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			newSocietyFederation.setOrgInternalCode(organization
					.getOrgInternalCode());
		}
	}

	private void autoFillChinesePinyin(
			NewSocietyOrganizations newSocietyFederation) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(newSocietyFederation.getName());
		newSocietyFederation.setFullPinyin((String) pinyin.get("fullPinyin"));
		newSocietyFederation.setSimplePinyin((String) pinyin
				.get("simplePinyin"));
	}

	private PageInfo<NewSocietyOrganizations> constructEmptyPageInfo() {
		PageInfo<NewSocietyOrganizations> result = new PageInfo<NewSocietyOrganizations>();
		result.setResult(new ArrayList<NewSocietyOrganizations>());
		return result;
	}

	@Override
	public int getNewSocietyOrganizationsCountByOrgId(Long orgId) {
		Organization org = organizationDubboService.getSimpleOrgById(orgId);

		return newSocietyOrganizationsDao
				.getNewSocietyOrganizationsCountByOrgCode(org
						.getOrgInternalCode());
	}

	@Override
	public void shiftNewSocietyOrganization(Long[] ids, Long orgId) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null) {
				continue;
			}
			NewSocietyOrganizations newSocietyFederation = getSimpleNewSocietyOrganizations(ids[i]);
			newSocietyFederation.getOrganization().setId(orgId);
			newSocietyFederation.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode());
			boolean bol = hasDuplicateNewSocietyOrganizationsName(orgId,
					newSocietyFederation.getName(),
					newSocietyFederation.getId());
			if (bol) {
				updateNewSocietyOrganizationsByName(
						newSocietyFederation.getName(), newSocietyFederation
								.getOrganization().getId(),
						newSocietyFederation);
				deleteNewSocietyOrganizationsById(ids[i]);

			} else {
				updateNewSocietyOrganizations(newSocietyFederation);
			}
		}
	}

	/************************ 组织机构迁移合并 ****************************/
	/**
	 * 
	 * @Title: findNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO查询出源部门与目标部门重复的社会组织数据
	 * @param @param newOrgId
	 * @param @param oldOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:21:59
	 */
	@Override
	public List<NewSocietyOrganizations> findNewSocietyOrganizationsListForOrgChange(
			Long newOrgId, Long oldOrgId) {
		try {
			if (newOrgId == null || oldOrgId == null) {
				throw new BusinessValidationException("查询条件不完善");
			}

			return newSocietyOrganizationsDao
					.findNewSocietyOrganizationsListForOrgChange(newOrgId,
							oldOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewSocietyOrganitionsServiceImpl的findNewSocietyOrganizationsListForOrgChange方法出现异常，原因：",
					"查询重复数据出现错误", e);
		}
	}

	/**
	 * 
	 * @Title: findTargetNewSocietyOrganizationsListForOrgChange
	 * @Description: TODO根据名字类型，组织机构id查询出目标部门的重复数据
	 * @param @param name
	 * @param @param type
	 * @param @param newOrgId
	 * @param @return 设定文件
	 * @return List<NewSocietyOrganizations> 返回类型
	 * @author wanggz
	 * @date 2014-10-18 下午02:35:59
	 */
	@Override
	public List<NewSocietyOrganizations> findTargetNewSocietyOrganizationsListForOrgChange(
			NewSocietyOrganizations newOrg, Long newOrgId) {
		try {
			if (newOrg == null
					|| !StringUtil.isStringAvaliable(newOrg.getName())
					|| newOrgId == null) {
				throw new BusinessValidationException("查询条件不完善");
			}

			return newSocietyOrganizationsDao
					.findTargetNewSocietyOrganizationsListForOrgChange(
							newOrg.getName(), newOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类NewSocietyOrganitionsServiceImpl的findTargetNewSocietyOrganizationsListForOrgChange方法出现异常，原因：",
					"查询目标部门重复数据出现错误", e);
		}
	}

}
