package com.tianque.baseInfo.dangerousChemicalsUnit.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.dangerousChemicalsUnit.dao.DangerousChemicalsUnitDao;
import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;

@Service("dangerousChemicalsUnitService")
@Transactional
public class DangerousChemicalsUnitServiceImpl extends LogableService implements
		DangerousChemicalsUnitService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DangerousChemicalsUnitDao dangerousChemicalsUnitDao;

	@Qualifier("dangerousChemicalsUnitValidator")
	@Autowired
	private DomainValidator<DangerousChemicalsUnit> domainValidator;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public PageInfo<DangerousChemicalsUnit> findDangerChemUnitForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Boolean isEmphasis) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return dangerousChemicalsUnitDao
						.findDangerChemUnitForPageByOrgInternalCode(
								org.getOrgInternalCode(), pageNum, pageSize,
								sidx, sord, isEmphasis);
			}
		}
	}

	@Override
	public DangerousChemicalsUnit updateDangerousChemicalsUnitByUnitNameAndOrgId(
			String unitName, Long orgId, DangerousChemicalsUnit domain) {
		try {
			DangerousChemicalsUnit older = this
					.getDangerousChemicalsUnitByUnitName(unitName, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			DangerousChemicalsUnit dangerousChemicalsUnit = updateDangerousChemicalsUnit(domain);
			placeService.execute(DocumentType.DANGEROUSCHEMICALSUNIT,
					OperateMode.EDIT.toString(), dangerousChemicalsUnit);
			return dangerousChemicalsUnit;
		} catch (Exception e) {
			logger.error(
					"类dangerousChemicalsUnitServiceImpl的updateDangerousChemicalsUnitByUnitNameAndOrgId方法出现异常，原因：",
					e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改危险化学品出现错误");
			} else {
				return null;
			}
		}

	}

	private DangerousChemicalsUnit getDangerousChemicalsUnitByUnitName(
			String unitName, Long orgId) {
		return dangerousChemicalsUnitDao.getDangerousChemicalsUnitByUnitName(
				unitName, orgId);
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, DangerousChemicalsUnit location) {
		for (int i = 0; i < ids.length; i++) {
			dangerousChemicalsUnitDao.updateEmphasiseById(ids[i],
					location.getIsEmphasis(), location.getLogOutReason());
			updateEmphasiseById(ids[i], location.getIsEmphasis(),
					location.getLogOutReason());
		}
	}

	private void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logOutReason) {
		DangerousChemicalsUnit domain = getDangerousChemicalsUnitById(id);
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY, id,
					isEmphasis ? 1l : 0l);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (isEmphasis) {
				// 危险化学品单位取消关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(domain, domain.getUnitName(),
								domain.getOrgInternalCode()),
						BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.CANCEL_ATTENTION,
						"危险化学品单位取消关注");
			} else {
				// 危险化学品单位重新关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(domain, domain.getUnitName(),
								domain.getOrgInternalCode()),
						BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.AGAIN_ATTENTION,
						"危险化学品单位重新关注");
			}
			dangerousChemicalsUnitDao.updateEmphasiseById(id, isEmphasis,
					logOutReason);
			Long emphasis = 0L;
			if (isEmphasis) {
				emphasis = 1L;
			}
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.DANGEROUSCHEMICALSUNIT.toString(), emphasis);
		}
	}

	private PageInfo<DangerousChemicalsUnit> constructEmptyPageInfo() {
		PageInfo<DangerousChemicalsUnit> result = new PageInfo<DangerousChemicalsUnit>();
		result.setResult(new ArrayList<DangerousChemicalsUnit>());
		return result;
	}

	@Override
	public DangerousChemicalsUnit getDangerousChemicalsUnitById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("危险化学品单位id不合法");
		}
		return dangerousChemicalsUnitDao.getDangerousChemicalsUnitById(id);
	}

	@Override
	public DangerousChemicalsUnit addDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		try {
			dangerCheUnitValidate(dangerousChemicalsUnit);
			autoFilledData(dangerousChemicalsUnit);
			dangerousChemicalsUnit = dangerousChemicalsUnitDao
					.addDangerousChemicalsUnit(dangerousChemicalsUnit);
			// 危险化学品单位新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(dangerousChemicalsUnit,
							dangerousChemicalsUnit.getUnitName(),
							dangerousChemicalsUnit.getOrgInternalCode()),
					BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.ADDT, "危险化学品单位新增");
			placeService.execute(DocumentType.DANGEROUSCHEMICALSUNIT,
					OperateMode.ADD.toString(), dangerousChemicalsUnit);
			return dangerousChemicalsUnit;
		} catch (Exception e) {
			logger.error(
					"类DangerousChemicalsUnitServiceImpl的addDangerousChemicalsUnit方法出现异常，原因：",
					e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增危险化学品单位出现错误");
			} else {
				return null;
			}
		}

	}

	@Override
	public DangerousChemicalsUnit updateDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		validateUpdate(dangerousChemicalsUnit);
		dangerCheUnitValidate(dangerousChemicalsUnit);
		autoFilledData(dangerousChemicalsUnit);
		dangerousChemicalsUnit = dangerousChemicalsUnitDao
				.updateDangerousChemicalsUnit(dangerousChemicalsUnit);
		placeService.execute(DocumentType.DANGEROUSCHEMICALSUNIT,
				OperateMode.EDIT.toString(), dangerousChemicalsUnit);
		return dangerousChemicalsUnit;
	}

	@Override
	public void deleteDangerousChemicalsUnitByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("id没有获得");
		}
		for (int i = 0; i < ids.length; i++) {
			DangerousChemicalsUnit domain = getDangerousChemicalsUnitById(ids[i]);
			deleteDangerousChemicalsUnitById(ids[i]);
			try {
				// 删除对应服务记录
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, ids[i],
						BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY);
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY, ids[i]);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getUnitName(),
						BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY, ids[i]);
				issueTypeService.setOrgIdAndCardNoOrNameForPlace(domain
						.getOrganization().getId(), domain.getUnitName(),
						BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY, ids[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public DangerousChemicalsUnit existDangerousChemicalsUnit(String unitName,
			Long orgId) {
		if (unitName == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return getDangerousChemicalsUnitByUnitName(unitName, orgId);
	}

	private void deleteDangerousChemicalsUnitById(Long id) {
		if (id == null || id < 0L) {
			throw new BusinessValidationException("id没有获得");
		}
		DangerousChemicalsUnit dangerousChemicalsUnit;
		dangerousChemicalsUnit = dangerousChemicalsUnitDao
				.getDangerousChemicalsUnitById(id);
		log(WARN, DRUGGY, ThreadVariable.getSession().getUserName()
				+ "删除危险化学品单位" + dangerousChemicalsUnit.getUnitName(),
				OperatorType.DELETE,
				ObjectToJSON.convertJSON(dangerousChemicalsUnit));
		// 危险化学品单位删除的轨迹
		orgLocationTracksService.addLocationTracks(new LocationTracksEntity(
				dangerousChemicalsUnit, dangerousChemicalsUnit.getUnitName(),
				dangerousChemicalsUnit.getOrgInternalCode()),
				BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY,
				OrgLocationInitType.IMPORT, null,
				OrgLocationTracksOperationType.DELETE, "危险化学品单位删除");
		dangerousChemicalsUnitDao.deleteDangerousChemicalsUnitById(id);
		placeService.execute(DocumentType.DANGEROUSCHEMICALSUNIT,
				OperateMode.DELETE.toString(), dangerousChemicalsUnit);
	}

	private void validateUpdate(DangerousChemicalsUnit update) {
		DangerousChemicalsUnit dangerousChemicalsUnit = this.dangerousChemicalsUnitDao
				.getDangerousChemicalsUnitById(update.getId());
		if (null != update.getOrganization()
				&& null != update.getOrganization().getId()
				&& !update
						.getOrganization()
						.getId()
						.equals(dangerousChemicalsUnit.getOrganization()
								.getId())) {
			throw new BusinessValidationException("所属网格不能修改");
		}
		if (null != update.getCreateUser()
				&& !update.getCreateUser().equals(
						dangerousChemicalsUnit.getCreateUser())) {
			throw new BusinessValidationException("创建人不能修改");
		}
		if (null != update.getCreateDate()
				&& !update.getCreateDate().equals(
						dangerousChemicalsUnit.getCreateDate())) {
			throw new BusinessValidationException("创建时间不能修改");
		}
	}

	private void autoFilledData(DangerousChemicalsUnit dangerousChemicalsUnit) {
		autoFillOrganizationInternalCode(dangerousChemicalsUnit);
		autoFillChinesePinyin(dangerousChemicalsUnit);
	}

	private void autoFillOrganizationInternalCode(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		Organization org = organizationDubboService
				.getSimpleOrgById(dangerousChemicalsUnit.getOrganization()
						.getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		dangerousChemicalsUnit.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void dangerCheUnitValidate(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		ValidateResult dangerCheUnitValidator = domainValidator
				.validate(dangerousChemicalsUnit);
		if (dangerCheUnitValidator.hasError()) {
			throw new BusinessValidationException(
					dangerCheUnitValidator.getErrorMessages());
		}
	}

	private void autoFillChinesePinyin(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(dangerousChemicalsUnit.getUnitName());
		dangerousChemicalsUnit.setFullPinyin((String) pinyin.get("fullPinyin"));
		dangerousChemicalsUnit.setSimplePinyin((String) pinyin
				.get("simplePinyin"));
	}

	@Override
	public boolean hasDangerousChemicalsUnit(Long orgId, Long exceptedId,
			String unitName) {
		DangerousChemicalsUnit dangerousChemicalsUnit = this
				.getDangerousChemicalsUnitByUnitName(unitName, orgId);
		return exceptedId == null ? dangerousChemicalsUnit != null
				: (dangerousChemicalsUnit != null && !exceptedId
						.equals(dangerousChemicalsUnit.getId()));
	}

	@Override
	public DangerousChemicalsUnit hasDuplicateDangerousChemicalsUnit(
			Long orgId, String unitName) {
		try {
			return dangerousChemicalsUnitDao
					.getDangerousChemicalsUnitByUnitNameAndOrgId(unitName,
							orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DangerousChemicalsUnitServiceImpl的hasDuplicateDangerousChemicalsUnit方法出现异常，原因：",
					"判断危险化学品单位名称是否存在出现错误", e);
		}
	}
}
