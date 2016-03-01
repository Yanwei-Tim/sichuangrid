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
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.EnterpriseDao;
import com.tianque.domain.ComprehensiveManageMember;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.EnterpriseService;
import com.tianque.service.FloorpersonService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.PersonInChargesService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.service.vo.BaseInfoTableTypes;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.validate.impl.EnterPriseValidateImpl;

@Service("enterpriseService")
@Transactional
public class EnterpriseServiceImpl extends LogableService implements
		EnterpriseService {
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private BaseInfoDeleter baseInfoDeleter;
	@Autowired
	private FloorpersonService floorpersonService;
	@Autowired
	private PersonInChargesService personInChargesService;
	private EnterPriseValidateImpl enterPriseValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public Enterprise addEnterprise(Enterprise enterprise) {
		try {
			autoFillOrganizationInternalCode(enterprise);
			enterPriseValidateImpl = new EnterPriseValidateImpl();
			enterPriseValidateImpl.setOrganizationDubboService(organizationDubboService);
			enterPriseValidateImpl.setValidateHelper(validateHelper);
			ValidateResult idleValidator = ((DomainValidator<Enterprise>) enterPriseValidateImpl)
					.validate(enterprise);
			if (!ExcelImportHelper.isImport.get()) {
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				} else if (hasDuplicateEnterpriseNameAndKeyType(enterprise
						.getOrganization().getId(), enterprise.getName(),
						enterprise.getId(), enterprise.getKeyType())) {
					throw new BusinessValidationException("该网格下已存在相同名称的企业");
				}
			}
			enterprise.setIsEmphasis(false);// 现在关注
			autoFillChinesePinyin(enterprise);

			Enterprise enterpriseSave = enterpriseDao.addEnterprise(enterprise);
			if ("securityKey".equals(enterpriseSave.getKeyType())) {
				// 治安重点新增的轨迹
				orgLocationTracksService
						.addLocationTracks(new LocationTracksEntity(
								enterpriseSave, enterpriseSave.getName(),
								enterpriseSave.getOrgInternalCode()),
								BaseInfoTables.SECURITYKEY_KEY,
								OrgLocationInitType.IMPORT, null,
								OrgLocationTracksOperationType.ADDT, "治安重点新增");
			} else if ("enterpriseKey".equals(enterpriseSave.getKeyType())) {
				// 规上企业新增的轨迹
				orgLocationTracksService
						.addLocationTracks(new LocationTracksEntity(
								enterpriseSave, enterpriseSave.getName(),
								enterpriseSave.getOrgInternalCode()),
								BaseInfoTables.ENTERPRISEKEY_KEY,
								OrgLocationInitType.IMPORT, null,
								OrgLocationTracksOperationType.ADDT, "规上企业新增");
			} else if ("enterpriseDownKey".equals(enterpriseSave.getKeyType())) {
				// 规下企业新增的轨迹
				orgLocationTracksService
						.addLocationTracks(new LocationTracksEntity(
								enterpriseSave, enterpriseSave.getName(),
								enterpriseSave.getOrgInternalCode()),
								BaseInfoTables.ENTERPRISEDOWNKEY_KEY,
								OrgLocationInitType.IMPORT, null,
								OrgLocationTracksOperationType.ADDT, "规下企业新增");
			} else {
				// 安全生产重点新增的轨迹
				orgLocationTracksService
						.addLocationTracks(new LocationTracksEntity(
								enterpriseSave, enterpriseSave.getName(),
								enterpriseSave.getOrgInternalCode()),
								BaseInfoTables.SAFETYPRODUCTIONKEY_KEY,
								OrgLocationInitType.IMPORT, null,
								OrgLocationTracksOperationType.ADDT, "安全生产重点新增");
			}
			if (enterprise.getComprehensiveManageMembers() != null
					&& enterprise.getComprehensiveManageMembers().size() > 0) {
				for (ComprehensiveManageMember comprehensiveManageMember : enterprise
						.getComprehensiveManageMembers()) {
					if (isFilled(comprehensiveManageMember)) {

						addComprehensiveManageMemberToEnterprise(enterprise,
								comprehensiveManageMember);
					}
				}
			}

			enterpriseSave = this.getEnterpriseById(enterpriseSave.getId());
			placeService.execute(getDocumentType(enterpriseSave.getKeyType()),
					OperateMode.ADD.toString(), enterpriseSave);
			return enterpriseSave;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
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

	private void addComprehensiveManageMemberToEnterprise(
			Enterprise enterprise,
			ComprehensiveManageMember comprehensiveManageMember) {
		comprehensiveManageMember.setEnterpriseId(enterprise.getId());
		autoFillChinesePinyin(comprehensiveManageMember);
		enterpriseDao.addComprehensiveManageMember(comprehensiveManageMember);
	}

	public Enterprise updateEnterprise(Enterprise enterprise) {
		autoFillOrganizationInternalCode(enterprise);
		enterPriseValidateImpl = new EnterPriseValidateImpl();
		enterPriseValidateImpl.setOrganizationDubboService(organizationDubboService);
		enterPriseValidateImpl.setValidateHelper(validateHelper);
		ValidateResult idleValidator = ((DomainValidator<Enterprise>) enterPriseValidateImpl)
				.validate(enterprise);
		if (idleValidator.hasError()) {
			throw new BusinessValidationException(
					idleValidator.getErrorMessages());
		} else if (hasDuplicateEnterpriseNameAndKeyType(enterprise
				.getOrganization().getId(), enterprise.getName(),
				enterprise.getId(), enterprise.getKeyType())) {
			throw new BusinessValidationException("该网格下已存在相同名称的企业");
		}

		autoFillChinesePinyin(enterprise);

		Enterprise enterpriseUpdate = enterpriseDao
				.updateEnterprise(enterprise);

		if (enterprise.getComprehensiveManageMembers() != null
				&& enterprise.getComprehensiveManageMembers().size() > 0) {
			for (ComprehensiveManageMember comprehensiveManageMember : enterprise
					.getComprehensiveManageMembers()) {
				if (isFilled(comprehensiveManageMember)) {
					if (comprehensiveManageMember.getId() != null) {
						enterpriseDao.updateEnterprise(enterpriseUpdate);
						enterpriseDao
								.updateComprehensiveManageMemberMember(comprehensiveManageMember);
					} else {
						addComprehensiveManageMemberToEnterprise(enterprise,
								comprehensiveManageMember);
					}
				} else if (comprehensiveManageMember.getId() != null) {
					enterpriseDao
							.deleteComprehensiveManageMembersById(comprehensiveManageMember
									.getId());
				}
			}
		}

		enterpriseUpdate = this.getEnterpriseById(enterpriseUpdate.getId());
		if (enterpriseUpdate.getKeyType().equals("safetyProductionKey")) {
			placeService.execute(DocumentType.SAFETYPRODUCTIONKEY,
					OperateMode.EDIT.toString(), enterpriseUpdate);
		} else if (enterpriseUpdate.getKeyType().equals("fireSafetyKey")) {
			placeService.execute(DocumentType.FIRESAFETYKEY,
					OperateMode.EDIT.toString(), enterpriseUpdate);
		} else if (enterpriseUpdate.getKeyType().equals("securityKey")) {
			placeService.execute(DocumentType.SECURITYKEY,
					OperateMode.EDIT.toString(), enterpriseUpdate);
		} else if (enterpriseUpdate.getKeyType().equals("enterpriseKey")) {
			placeService.execute(DocumentType.ENTERPRISEKEY,
					OperateMode.EDIT.toString(), enterpriseUpdate);
		} else if (enterpriseUpdate.getKeyType().equals("enterpriseDownKey")) {
			placeService.execute(DocumentType.ENTERPRISEDOWNKEY,
					OperateMode.EDIT.toString(), enterpriseUpdate);
		}

		return enterpriseUpdate;
	}

	@Override
	public boolean deleteEnterpriseById(Long id) {
		if (id == null) {
			return false;
		}
		Enterprise domain = getEnterpriseById(id);
		floorpersonService.deleteFloorperson(id, domain.getKeyType());
		personInChargesService.deletePersonInCharges(id, domain.getKeyType());
		enterpriseDao.deleteComprehensiveManageMembersByEnterpriseId(id);
		if (domain.getKeyType().equals("enterpriseKey")) {
			log(WARN, ModelType.ENTERPRISE, ThreadVariable.getSession()
					.getUserName() + "删除规上企业" + domain.getName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
			placeService.execute(DocumentType.ENTERPRISEKEY,
					OperateMode.DELETE.toString(), domain);
		}
		if (domain.getKeyType().equals("fireSafetyKey")) {
			log(WARN, ModuleConstants.FIRESAFETYKEY,
					ThreadVariable.getSession().getUserName() + "删除消防安全重点"
							+ domain.getName(), OperatorType.DELETE,
					ObjectToJSON.convertJSON(domain));
			placeService.execute(DocumentType.FIRESAFETYKEY,
					OperateMode.DELETE.toString(), domain);
		}
		if (domain.getKeyType().equals("safetyProductionKey")) {
			log(WARN, ModuleConstants.SAFE_PLACE, ThreadVariable.getSession()
					.getUserName() + "删除安全生产重点" + domain.getName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
			placeService.execute(DocumentType.SAFETYPRODUCTIONKEY,
					OperateMode.DELETE.toString(), domain);
		}
		if (domain.getKeyType().equals("securityKey")) {
			log(WARN, ModuleConstants.SECURITYKEY, ThreadVariable.getSession()
					.getUserName() + "删除治安重点" + domain.getName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
			placeService.execute(DocumentType.SECURITYKEY,
					OperateMode.DELETE.toString(), domain);
		}

		if (domain.getKeyType().equals("enterpriseDownKey")) {
			log(WARN, ModelType.ENTERPRISEDOWN, ThreadVariable.getSession()
					.getUserName() + "删除规下企业" + domain.getName(),
					OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
			placeService.execute(DocumentType.ENTERPRISEDOWNKEY,
					OperateMode.DELETE.toString(), domain);
		}
		// log(WARN, ModuleConstants.IMPORTANT+DocumentType.,
		// ThreadVariable.getSession().getUserName()+"删除安全生产重点"+domain.getName(),OperatorType.DELETE,
		// ObjectToJSON.convertJSON(domain));

		enterpriseDao.deleteEnterpriseById(id);

		return true;
	}

	@Override
	public Enterprise getEnterpriseById(Long id) {
		Enterprise enterprise = enterpriseDao.getEnterpriseById(id);
		if (enterprise != null) {
			enterprise.setComprehensiveManageMembers(enterpriseDao
					.findComprehensiveManageMembersByEnterpriseId(enterprise
							.getId()));
		}
		return enterprise;
	}

	@Override
	public PageInfo<Enterprise> findEnterprisesForListPageByOrganizationId(
			Long organizationId, int pageSize, int pageNum, String sortField,
			String order) {
		if (organizationId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (organization == null) {
				return constructEmptyPageInfo();
			} else {
				return enterpriseDao
						.findEnterprisesForListPageByOrgInternalCode(
								organization.getOrgInternalCode(), pageSize,
								pageNum, sortField, order);
			}
		}
	}

	@Override
	public PageInfo<Enterprise> findEnterprisesForListPageByOrganizationIdAndKeyType(
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
				return enterpriseDao
						.findEnterprisesForListPageByOrgInternalCodeAndKeyType(
								organization.getOrgInternalCode(), keyType,
								pageSize, pageNum, sortField, order, isEmphasis);
			}
		}
	}

	private void autoFillOrganizationInternalCode(Enterprise enterprise) {
		if (null == enterprise.getOrganization()
				|| null == enterprise.getOrganization().getId()) {
			throw new BusinessValidationException("找不到指定网格!");
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(enterprise.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			enterprise.setOrgInternalCode(organization.getOrgInternalCode());
		}

	}

	private void autoFillChinesePinyin(Enterprise enterprise) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(enterprise.getName());
		enterprise.setFullPinyin((String) pinyin.get("fullPinyin"));
		enterprise.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private PageInfo<Enterprise> constructEmptyPageInfo() {
		PageInfo<Enterprise> emptyPageInfo = new PageInfo<Enterprise>();
		emptyPageInfo.setResult(new ArrayList<Enterprise>());
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
	public Enterprise findEnterpriseByNameAndOrgId(String name, Long orgId) {
		return enterpriseDao.findEnterpriseByNameAndOrgId(name, orgId);
	}

	@Override
	public Enterprise updateEnterpriseByName(String name, Long orgId,
			Enterprise domain) {
		try {
			Enterprise older = enterpriseDao
					.getEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
							domain.getKeyType());
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateEnterprise(domain);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}

	}

	@Override
	public boolean hasDuplicateEnterpriseNameAndKeyType(Long ownerOrgId,
			String enterpriseName, Long exceptedId, String keyType) {
		Enterprise exsited = enterpriseDao
				.getEnterpriseByNameAndOrgIdAndKeyType(enterpriseName,
						ownerOrgId, keyType);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public Enterprise findEnterpriseByNameAndOrgIdAndId(String name,
			Long orgId, Long id) {
		return enterpriseDao.findEnterpriseByNameAndOrgIdAndId(name, orgId, id);
	}

	@Override
	public Enterprise updateEmphasis(Enterprise enterprise) {
		return enterpriseDao.updateEmphasis(enterprise);
	}

	@Override
	public Enterprise getEnterpriseByNameAndOrgIdAndKeyType(String name,
			Long orgId, String keyType) {
		if (!StringUtil.isStringAvaliable(name) || orgId == null
				|| !StringUtil.isStringAvaliable(keyType)) {
			throw new BusinessValidationException("参数不正确");
		}
		return enterpriseDao.getEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
				keyType);
	}

	@Override
	public List<Long> deleteEnterpriseById(List<Long> placeIds) {
		if (placeIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		// 暂不处理删除时的依赖关系
		// List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
		// placeIds, "SAFETYPRODUCTIONKEY");
		// if (exitPersonIdsList == null || exitPersonIdsList.size() == 0) {
		// exitPersonIdsList = baseInfoDeleter.getRelateplaceId(placeIds,
		// "FIRESAFETYKEY");
		// }
		// if (exitPersonIdsList == null || exitPersonIdsList.size() == 0) {
		// exitPersonIdsList = baseInfoDeleter.getRelateplaceId(placeIds,
		// "SECURITYKEY");
		// }
		// placeIds.removeAll(exitPersonIdsList);
		for (Long id : placeIds) {
			Enterprise enterprise = enterpriseDao.getEnterpriseById(id);
			if ("safetyProductionKey".equals(enterprise.getKeyType())) {
				// 安全生产重点新增删除的轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(enterprise, enterprise
								.getName(), enterprise.getOrgInternalCode()),
						BaseInfoTables.SAFETYPRODUCTIONKEY_KEY,
						OrgLocationInitType.IMPORT, null,
						OrgLocationTracksOperationType.DELETE, "安全生产重点删除");
			} else if ("securityKey".equals(enterprise.getKeyType())) {
				// 治安重点新增删除的轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(enterprise, enterprise
								.getName(), enterprise.getOrgInternalCode()),
						BaseInfoTables.SECURITYKEY_KEY,
						OrgLocationInitType.IMPORT, null,
						OrgLocationTracksOperationType.DELETE, " 治安重点删除");
			} else if ("enterpriseKey".equals(enterprise.getKeyType())) {
				// 规上企业新增删除的轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(enterprise, enterprise
								.getName(), enterprise.getOrgInternalCode()),
						BaseInfoTables.ENTERPRISEKEY_KEY,
						OrgLocationInitType.IMPORT, null,
						OrgLocationTracksOperationType.DELETE, "规上企业删除");
			} else if ("enterpriseDownKey".equals(enterprise.getKeyType())) {
				// 规下企业新增删除的轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(enterprise, enterprise
								.getName(), enterprise.getOrgInternalCode()),
						BaseInfoTables.ENTERPRISEDOWNKEY_KEY,
						OrgLocationInitType.IMPORT, null,
						OrgLocationTracksOperationType.DELETE, "规下企业删除");
			} else {
				// 消防安全重点新增删除的轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(enterprise, enterprise
								.getName(), enterprise.getOrgInternalCode()),
						BaseInfoTables.FIRESAFETYKEY_KEY,
						OrgLocationInitType.IMPORT, null,
						OrgLocationTracksOperationType.DELETE, "消防安全重点删除");
			}
			deleteEnterpriseById(id);
			try {
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, id, enterprise
								.getKeyType().toUpperCase());
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class }, enterprise
								.getKeyType().toUpperCase(), id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						enterprise.getOrganization().getId(), enterprise
								.getName(), enterprise.getKeyType()
								.toUpperCase(), id);
				issueTypeService.setOrgIdAndCardNoOrNameForPlace(enterprise
						.getOrganization().getId(), enterprise.getName(),
						enterprise.getKeyType().toUpperCase(), id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return placeIds;
	}

	@Override
	public boolean hasRelatePlacce(List<Long> placeIds) {
		if (placeIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
				placeIds, "FIRESAFETYKEY");
		if (exitPersonIdsList == null || exitPersonIdsList.size() == 0) {
			exitPersonIdsList = baseInfoDeleter.getRelateplaceId(placeIds,
					"SAFETYPRODUCTIONKEY");
		}
		if (exitPersonIdsList == null || exitPersonIdsList.size() == 0) {
			exitPersonIdsList = baseInfoDeleter.getRelateplaceId(placeIds,
					"SECURITYKEY");
		}
		if (exitPersonIdsList != null && exitPersonIdsList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void shiftEnterprise(Long[] ids, Long orgId, String keyType) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null) {
				continue;
			}
			Enterprise enterprise = getEnterpriseById(ids[i]);
			enterprise.getOrganization().setId(orgId);
			enterprise.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
					orgId).getOrgInternalCode());
			boolean bol = hasDuplicateEnterpriseNameAndKeyType(orgId,
					enterprise.getName(), enterprise.getId(), keyType);
			if (bol) {
				updateEnterpriseByName(enterprise.getName(), enterprise
						.getOrganization().getId(), enterprise);
				deleteEnterpriseById(ids[i]);

			} else {
				updateEnterprise(enterprise);
			}
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, Enterprise location) {
		for (int i = 0; i < ids.length; i++) {
			updateEmphasiseById(ids[i], location.getIsEmphasis() ? 1l : 0l,
					location.getLogOutReason(), location.getLogOutTime());
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {

		Enterprise domain = getEnterpriseById(id);
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					domain.getKeyType().toUpperCase(), id, isEmphasis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (isEmphasis == 1L) {
				if ("securityKey".equals(domain.getKeyType())) {
					// 治安重点取消关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.SECURITYKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.CANCEL_ATTENTION,
							"治安重点取消关注");
				} else if ("enterpriseKey".equals(domain.getKeyType())) {
					// 规上企业取消关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.ENTERPRISEKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.CANCEL_ATTENTION,
							"规上企业取消关注");
				} else if ("enterpriseDownKey".equals(domain.getKeyType())) {
					// 规下企业取消关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.ENTERPRISEDOWNKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.CANCEL_ATTENTION,
							"规下企业取消关注");
				} else {
					// 取消关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.SAFETYPRODUCTIONKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.CANCEL_ATTENTION,
							"安全生产重点取消关注");
				}
			} else {
				if ("securityKey".equals(domain.getKeyType())) {
					// 治安重点重新关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.SECURITYKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.AGAIN_ATTENTION,
							"治安重点重新关注");
				} else if ("enterpriseKey".equals(domain.getKeyType())) {
					// 规上企业重新关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.ENTERPRISEKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.AGAIN_ATTENTION,
							"规上企业重新关注");
				} else if ("enterpriseDownKey".equals(domain.getKeyType())) {
					// 规下企业重新关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.ENTERPRISEDOWNKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.AGAIN_ATTENTION,
							"规下企业重新关注");
				} else {
					// 重新关注增加轨迹
					orgLocationTracksService.addLocationTracks(
							new LocationTracksEntity(domain, domain.getName(),
									domain.getOrgInternalCode()),
							BaseInfoTables.SAFETYPRODUCTIONKEY_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.AGAIN_ATTENTION,
							"安全生产重点重新关注");
				}
			}
			enterpriseDao.updateEmphasiseById(id, isEmphasis, logoutReason,
					logoutDate);
			placeService.emphasisAndNotEmphasis(id,
					getDocumentType(this.getEnterpriseById(id).getKeyType())
							.toString(), isEmphasis);
		}
	}

	@Override
	public int getEnterpriseCountByOrgIdAndKeyType(Long orgId, String keyType) {
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return enterpriseDao.getEnterpriseCountByOrgCodeAndKeyType(
				org.getOrgInternalCode(), keyType);
	}

	@Override
	public List<Long> deleteEnterpriseByIds(Long[] ids) {
		return this.deleteEnterpriseById(Arrays.asList(ids));
	}

	@Override
	public Enterprise hasDuplicateEnterprise(Long orgId, String name,
			String type) {
		return enterpriseDao.getEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
				type);
	}

	@Override
	public Enterprise hasDuplicateFireSafetyEnterprise(Long orgId, String name) {
		return enterpriseDao.getEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
				"fireSafetyKey");
	}

	// @Override
	// public Enterprise hasDuplicateSafetyProduction(Long orgId, String name) {
	// return enterpriseDao.getEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
	// "safetyProductionKey");
	// }
	//
	// @Override
	// public Enterprise hasDuplicateSecurityEnterprise(Long orgId, String name)
	// {
	// return enterpriseDao.getEnterpriseByNameAndOrgIdAndKeyType(name, orgId,
	// "securityKey");
	// }
}
