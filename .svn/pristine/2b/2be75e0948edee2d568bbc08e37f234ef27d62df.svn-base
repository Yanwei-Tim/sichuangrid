package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.FireSafetyEnterpriseDao;
import com.tianque.domain.ComprehensiveManageMember;
import com.tianque.domain.FireSafetyEnterprise;
import com.tianque.domain.Organization;
import com.tianque.domain.property.IssuePersonAndSiteType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.EnterpriseService;
import com.tianque.service.FireSafetyEnterpriseService;
import com.tianque.service.FloorpersonService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.PersonInChargesService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.service.vo.BaseInfoTableTypes;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.validate.impl.FireSafetyEnterpriseValidateImpl;

@Service("fireSafetyEnterpriseService")
@Transactional
public class FireSafetyEnterpriseServiceImpl extends LogableService implements
		FireSafetyEnterpriseService {
	@Autowired
	private FireSafetyEnterpriseDao fireSafetyEnterpriseDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private BaseInfoDeleter baseInfoDeleter;
	@Autowired
	private FloorpersonService floorpersonService;
	@Autowired
	private PersonInChargesService personInChargesService;
	private FireSafetyEnterpriseValidateImpl enterPriseValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public FireSafetyEnterprise addFireSafetyEnterprise(
			FireSafetyEnterprise enterprise) {
		try {
			autoFillOrganizationInternalCode(enterprise);
			enterPriseValidateImpl = new FireSafetyEnterpriseValidateImpl();
			enterPriseValidateImpl.setOrganizationDubboService(organizationDubboService);
			enterPriseValidateImpl.setValidateHelper(validateHelper);
			ValidateResult idleValidator = ((DomainValidator<FireSafetyEnterprise>) enterPriseValidateImpl)
					.validate(enterprise);
			if (!ExcelImportHelper.isImport.get()) {
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				} else if (hasDuplicateFireSafetyEnterpriseNameAndKeyType(
						enterprise.getOrganization().getId(),
						enterprise.getName(), enterprise.getId(),
						enterprise.getKeyType())) {
					throw new BusinessValidationException("该网格下已存在相同名称的企业");
				}
			}

			autoFillChinesePinyin(enterprise);

			FireSafetyEnterprise enterpriseSave = fireSafetyEnterpriseDao
					.addFireSafetyEnterprise(enterprise);

			// 轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(enterprise, enterprise.getName(),
							enterprise.getOrgInternalCode()),
					BaseInfoTables.FIRESAFETYKEY_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.ADDT, "消防安全重点新增");

			if (enterprise.getComprehensiveManageMembers() != null
					&& enterprise.getComprehensiveManageMembers().size() > 0) {
				for (ComprehensiveManageMember comprehensiveManageMember : enterprise
						.getComprehensiveManageMembers()) {
					if (isFilled(comprehensiveManageMember)) {

						addComprehensiveManageMemberToFireSafetyEnterprise(
								enterprise, comprehensiveManageMember);
					}
				}
			}

			enterpriseSave = this.getFireSafetyEnterpriseById(enterpriseSave
					.getId());
			placeService.execute(getDocumentType(enterpriseSave.getKeyType()),
					OperateMode.ADD.toString(), enterpriseSave);
			return enterpriseSave;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("新增信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	private DocumentType getDocumentType(String keyType) {
		if (BaseInfoTableTypes.toString(DocumentType.PROTECTIONKEY.toString())
				.equals(keyType)) {
			return DocumentType.PROTECTIONKEY;
		} else if (BaseInfoTableTypes.toString(
				DocumentType.SAFETYPRODUCTIONKEY.toString()).equals(keyType)) {
			return DocumentType.SAFETYPRODUCTIONKEY;
		} else if (BaseInfoTableTypes.toString(
				DocumentType.FIRESAFETYKEY.toString()).equals(keyType)) {
			return DocumentType.FIRESAFETYKEY;
		} else if (BaseInfoTableTypes.toString(
				DocumentType.SECURITYKEY.toString()).equals(keyType)) {
			return DocumentType.SECURITYKEY;
		} else if (BaseInfoTableTypes.toString(
				DocumentType.ENTERPRISEKEY.toString()).equals(keyType)) {
			return DocumentType.ENTERPRISEKEY;
		} else if (BaseInfoTableTypes.toString(
				DocumentType.ENTERPRISEDOWNKEY.toString()).equals(keyType)) {
			return DocumentType.ENTERPRISEDOWNKEY;
		} else {
			return null;
		}
	}

	private void addComprehensiveManageMemberToFireSafetyEnterprise(
			FireSafetyEnterprise enterprise,
			ComprehensiveManageMember comprehensiveManageMember) {
		comprehensiveManageMember.setEnterpriseId(enterprise.getId());
		autoFillChinesePinyin(comprehensiveManageMember);
		fireSafetyEnterpriseDao
				.addComprehensiveManageMember(comprehensiveManageMember);
	}

	public FireSafetyEnterprise updateFireSafetyEnterprise(
			FireSafetyEnterprise enterprise) {
		autoFillOrganizationInternalCode(enterprise);
		enterPriseValidateImpl = new FireSafetyEnterpriseValidateImpl();
		enterPriseValidateImpl.setOrganizationDubboService(organizationDubboService);
		enterPriseValidateImpl.setValidateHelper(validateHelper);
		ValidateResult idleValidator = ((DomainValidator<FireSafetyEnterprise>) enterPriseValidateImpl)
				.validate(enterprise);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateFireSafetyEnterpriseNameAndKeyType(enterprise
				.getOrganization().getId(), enterprise.getName(),
				enterprise.getId(), enterprise.getKeyType())) {
			throw new BusinessValidationException("该网格下已存在相同名称的企业");
		}

		autoFillChinesePinyin(enterprise);

		FireSafetyEnterprise enterpriseUpdate = fireSafetyEnterpriseDao
				.updateFireSafetyEnterprise(enterprise);

		if (enterprise.getComprehensiveManageMembers() != null
				&& enterprise.getComprehensiveManageMembers().size() > 0) {
			for (ComprehensiveManageMember comprehensiveManageMember : enterprise
					.getComprehensiveManageMembers()) {
				if (isFilled(comprehensiveManageMember)) {
					if (comprehensiveManageMember.getId() != null) {
						fireSafetyEnterpriseDao
								.updateFireSafetyEnterprise(enterpriseUpdate);
						fireSafetyEnterpriseDao
								.updateComprehensiveManageMemberMember(comprehensiveManageMember);
					} else {
						addComprehensiveManageMemberToFireSafetyEnterprise(
								enterprise, comprehensiveManageMember);
					}
				} else if (comprehensiveManageMember.getId() != null) {
					fireSafetyEnterpriseDao
							.deleteComprehensiveManageMembersById(comprehensiveManageMember
									.getId());
				}
			}
		}

		enterpriseUpdate = this.getFireSafetyEnterpriseById(enterpriseUpdate
				.getId());
		placeService.execute(DocumentType.FIRESAFETYKEY,
				OperateMode.EDIT.toString(), enterpriseUpdate);
		return enterpriseUpdate;
	}

	@Override
	public boolean deleteFireSafetyEnterpriseById(Long id) {
		if (id == null) {
			return false;
		}
		FireSafetyEnterprise domain = getFireSafetyEnterpriseById(id);
		log(WARN, ENTERPRISE, DELETE, OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		if (!baseInfoDeleter.checkRelatePlaceDelete(id,
				IssuePersonAndSiteType.ENTERPRISES)) {
			floorpersonService.deleteFloorperson(id, domain.getKeyType());
			personInChargesService.deletePersonInCharges(id,
					domain.getKeyType());
			fireSafetyEnterpriseDao
					.deleteComprehensiveManageMembersByEnterpriseId(id);
			fireSafetyEnterpriseDao.deleteFireSafetyEnterpriseById(id);
			placeService.execute(DocumentType.ENTERPRISEKEY,
					OperateMode.DELETE.toString(), domain);
			return true;
		}

		return false;
	}

	@Override
	public FireSafetyEnterprise getFireSafetyEnterpriseById(Long id) {
		FireSafetyEnterprise enterprise = fireSafetyEnterpriseDao
				.getFireSafetyEnterpriseById(id);
		if (enterprise != null) {
			enterprise.setComprehensiveManageMembers(fireSafetyEnterpriseDao
					.findComprehensiveManageMembersByEnterpriseId(enterprise
							.getId()));
		}
		return enterprise;
	}

	@Override
	public PageInfo<FireSafetyEnterprise> findFireSafetyEnterprisesForListPageByOrganizationIdAndKeyType(
			Long organizationId, String keyType, int pageSize, int pageNum,
			String sortField, String order, Long isEmphasis) {
		if (organizationId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (organization == null) {
				return constructEmptyPageInfo();
			} else {
				if (!StringUtil.isStringAvaliable(keyType)) {
					throw new BusinessValidationException("参数错误");
				}
				return fireSafetyEnterpriseDao
						.findFireSafetyEnterprisesForListPageByOrgInternalCodeAndKeyType(
								organization.getOrgInternalCode(), keyType,
								pageSize, pageNum, sortField, order, isEmphasis);
			}
		}
	}

	private void autoFillOrganizationInternalCode(
			FireSafetyEnterprise enterprise) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(enterprise.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			enterprise.setOrgInternalCode(organization.getOrgInternalCode());
		}

	}

	private void autoFillChinesePinyin(FireSafetyEnterprise enterprise) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(enterprise.getName());
		enterprise.setFullPinyin((String) pinyin.get("fullPinyin"));
		enterprise.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private PageInfo<FireSafetyEnterprise> constructEmptyPageInfo() {
		PageInfo<FireSafetyEnterprise> emptyPageInfo = new PageInfo<FireSafetyEnterprise>();
		emptyPageInfo.setResult(new ArrayList<FireSafetyEnterprise>());
		return emptyPageInfo;
	}

	private void autoFillChinesePinyin(
			ComprehensiveManageMember comprehensiveManageMember) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(comprehensiveManageMember.getName());
		comprehensiveManageMember.setFullPinyin((String) pinyin
				.get("fullPinyin"));
		comprehensiveManageMember.setSimplePinyin((String) pinyin
				.get("simplePinyin"));
	}

	private boolean isFilled(ComprehensiveManageMember comprehensiveManageMember) {

		if (StringUtil.isStringAvaliable(comprehensiveManageMember.getName())
				|| StringUtil.isStringAvaliable(comprehensiveManageMember
						.getTelephone())
				|| StringUtil.isStringAvaliable(comprehensiveManageMember
						.getMobileNumber())) {
			return true;
		}

		return false;
	}

	@Override
	public FireSafetyEnterprise updateFireSafetyEnterpriseByName(String name,
			Long orgId, FireSafetyEnterprise domain) {
		try {
			FireSafetyEnterprise older = fireSafetyEnterpriseDao
					.getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(name,
							orgId, domain.getKeyType());
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateFireSafetyEnterprise(domain);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("修改信息出现错误", e);
			} else {
				return null;
			}
		}

	}

	@Override
	public boolean hasDuplicateFireSafetyEnterpriseNameAndKeyType(
			Long ownerOrgId, String enterpriseName, Long exceptedId,
			String keyType) {
		FireSafetyEnterprise exsited = fireSafetyEnterpriseDao
				.getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(
						enterpriseName, ownerOrgId, keyType);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public FireSafetyEnterprise updateEmphasis(FireSafetyEnterprise enterprise) {
		return fireSafetyEnterpriseDao.updateEmphasis(enterprise);
	}

	@Override
	public FireSafetyEnterprise getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(
			String name, Long orgId, String keyType) {
		if (!StringUtil.isStringAvaliable(name) || orgId == null
				|| !StringUtil.isStringAvaliable(keyType)) {
			throw new BusinessValidationException("参数不正确");
		}
		return fireSafetyEnterpriseDao
				.getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
						keyType);
	}

	@Override
	public void shiftFireSafetyEnterprise(Long[] ids, Long orgId, String keyType) {
		for (int i = 0; i < ids.length; i++) {
			FireSafetyEnterprise enterprise = getFireSafetyEnterpriseById(ids[i]);
			enterprise.getOrganization().setId(orgId);
			enterprise.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
					orgId).getOrgInternalCode());
			boolean bol = hasDuplicateFireSafetyEnterpriseNameAndKeyType(orgId,
					enterprise.getName(), enterprise.getId(), keyType);
			if (bol) {
				updateFireSafetyEnterpriseByName(enterprise.getName(),
						enterprise.getOrganization().getId(), enterprise);
				deleteFireSafetyEnterpriseById(ids[i]);

			} else {
				updateFireSafetyEnterprise(enterprise);
			}
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, FireSafetyEnterprise location) {
		for (int i = 0; i < ids.length; i++) {

			updateEmphasiseById(ids[i],
					location.getIsEmphasis() ? IsEmphasis.IsNotEmphasis
							: IsEmphasis.Emphasis, location.getLogOutReason(),
					location.getLogOutTime());
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		FireSafetyEnterprise fireSafetyEnterprise = getFireSafetyEnterpriseById(id);
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					fireSafetyEnterprise.getKeyType().toUpperCase(), id,
					isEmphasis);
			/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
			PluginServiceHelpUtil.doService("routerService",
					"setOrgIdAndCardNoOrName", new Class[] { Long.class,
							String.class, String.class, Long.class },
					fireSafetyEnterprise.getOrganization().getId(),
					fireSafetyEnterprise.getName(), fireSafetyEnterprise
							.getKeyType().toUpperCase(), id);
			issueTypeService.setOrgIdAndCardNoOrNameForPlace(
					fireSafetyEnterprise.getOrganization().getId(),
					fireSafetyEnterprise.getName(), fireSafetyEnterprise
							.getKeyType().toUpperCase(), id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (isEmphasis == 1L) {
				// 取消关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(fireSafetyEnterprise,
								fireSafetyEnterprise.getName(),
								fireSafetyEnterprise.getOrgInternalCode()),
						BaseInfoTables.FIRESAFETYKEY_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.CANCEL_ATTENTION,
						"消防安全重点取消关注");
			} else {
				// 重新关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(fireSafetyEnterprise,
								fireSafetyEnterprise.getName(),
								fireSafetyEnterprise.getOrgInternalCode()),
						BaseInfoTables.FIRESAFETYKEY_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.AGAIN_ATTENTION,
						"消防安全重点重新关注");
			}

			fireSafetyEnterpriseDao.updateEmphasiseById(id, isEmphasis,
					logoutReason, logoutDate);
			placeService.emphasisAndNotEmphasis(
					id,
					getDocumentType(
							this.getFireSafetyEnterpriseById(id).getKeyType())
							.toString(), isEmphasis);
		}
	}

	@Override
	public List<Long> deleteFireSafetyEnterpriseByIds(Long[] ids) {
		return this.deleteFireSafetyEnterpriseById(Arrays.asList(ids));
	}

	@Override
	public List<Long> deleteFireSafetyEnterpriseById(List<Long> placeIds) {
		for (Long id : placeIds) {
			enterpriseService.deleteEnterpriseById(id);
		}
		return placeIds;
	}
}
