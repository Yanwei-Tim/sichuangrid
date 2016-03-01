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
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.OtherLocaleDao;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.OtherLocaleService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.validate.impl.OtherlocalValidateImpl;

@Service("otherLocaleService")
@Transactional
public class OtherLocaleServiceImpl extends LogableService implements
		OtherLocaleService {

	@Autowired
	private OtherLocaleDao otherLocaleDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private BaseInfoDeleter baseInfoDeleter;
	@Autowired
	private KeyPlaceService placeService;
	private OtherlocalValidateImpl otherlocalValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public OtherLocale addOtherLocale(OtherLocale otherLocale) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				otherlocalValidateImpl = new OtherlocalValidateImpl();
				otherlocalValidateImpl.setValidateHelper(validateHelper);
				otherlocalValidateImpl
						.setOrganizationDubboService(organizationDubboService);
				ValidateResult otherLocaleValidator = ((DomainValidator<OtherLocale>) otherlocalValidateImpl)
						.validate(otherLocale);
				if (otherLocaleValidator.hasError()) {
					throw new BusinessValidationException(
							otherLocaleValidator.getErrorMessages());
				} else if (hasDuplicateOtherLocaleName(otherLocale
						.getOrganization().getId(), otherLocale.getName(),
						otherLocale.getId())) {
					throw new BusinessValidationException("该网格下已存在相同名称的其他场所");
				}
			}
			setChinesePinyin(otherLocale);
			autoFillOrganizationInternalCode(otherLocale);
			otherLocale = otherLocaleDao.addOtherLocale(otherLocale);
			// 其他场所新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(otherLocale,
							otherLocale.getName(), otherLocale
									.getOrgInternalCode()),
					BaseInfoTables.OTHERLOCALE_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.ADDT, "其他场所新增");
			placeService.execute(DocumentType.OTHERLOCALES,
					OperateMode.ADD.toString(), otherLocale);
			return otherLocale;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public OtherLocale getOtherLocaleById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id没有获得");
		}
		return otherLocaleDao.getOtherLocaleById(id);
	}

	@Override
	public boolean deleteOtherLocaleById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id没有获得");
		}
		OtherLocale domain = getOtherLocaleById(id);
		log(WARN, OTHER_LOCATION, ThreadVariable.getSession().getUserName()
				+ "删除其他场所" + domain.getName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		// 其他场所删除的轨迹
		orgLocationTracksService.addLocationTracks(new LocationTracksEntity(
				domain, domain.getName(), domain.getOrgInternalCode()),
				BaseInfoTables.OTHERLOCALE_KEY, OrgLocationInitType.IMPORT,
				null, OrgLocationTracksOperationType.DELETE, "其他场所删除");
		otherLocaleDao.deleteOtherLocaleById(id);
		placeService.execute(DocumentType.OTHERLOCALES,
				OperateMode.DELETE.toString(), domain);
		return true;
	}

	@Override
	public OtherLocale updateOtherLocale(OtherLocale otherLocale) {
		otherlocalValidateImpl = new OtherlocalValidateImpl();
		otherlocalValidateImpl.setValidateHelper(validateHelper);
		otherlocalValidateImpl.setOrganizationDubboService(organizationDubboService);
		ValidateResult otherLocaleValidator = ((DomainValidator<OtherLocale>) otherlocalValidateImpl)
				.validate(otherLocale);
		if (otherLocaleValidator.hasError()) {
			throw new BusinessValidationException(
					otherLocaleValidator.getErrorMessages());
		} else if (hasDuplicateOtherLocaleName(otherLocale.getOrganization()
				.getId(), otherLocale.getName(), otherLocale.getId())) {
			throw new BusinessValidationException("该网格下已存在相同名称的其他场所");
		}
		setChinesePinyin(otherLocale);
		autoFillOrganizationInternalCode(otherLocale);
		otherLocale = otherLocaleDao.updateOtherLocale(otherLocale);
		placeService.execute(DocumentType.OTHERLOCALES,
				OperateMode.EDIT.toString(), otherLocale);
		return otherLocale;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<OtherLocale> findOtherLocalesForPage(Long orgId,
			Integer pageSize, Integer pageNum, String sidx, String sord,
			Long isEmphasis) {
		if (null == orgId) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (null == org) {
				return constructEmptyPageInfo();
			} else {
				return otherLocaleDao.findOtherLocalesForPageByOrgInternalCode(
						org.getOrgInternalCode(), pageSize, pageNum, sidx,
						sord, isEmphasis);
			}
		}

	}

	private PageInfo<OtherLocale> constructEmptyPageInfo() {
		PageInfo<OtherLocale> result = new PageInfo<OtherLocale>();
		result.setResult(new ArrayList<OtherLocale>());
		return result;
	}

	private void setChinesePinyin(OtherLocale otherLocale) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(otherLocale.getName());
		otherLocale.setFullPinyin((String) pinyin.get("fullPinyin"));
		otherLocale.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void autoFillOrganizationInternalCode(OtherLocale otherLocale) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(otherLocale.getOrganization().getId());
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			otherLocale.setOrgInternalCode(organization.getOrgInternalCode());
		}
	}

	@Override
	public Integer countExsistedOtherLocale(String name, Long orgId, Long id) {
		if (orgId == null)
			return 0;
		return otherLocaleDao.countExsistedOtherLocale(name, orgId, id);
	}

	@Override
	public OtherLocale updateOtherLocaleByName(String name, Long orgId,
			OtherLocale domain) {
		try {
			OtherLocale older = otherLocaleDao
					.getOtherLocaleByName(name, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateOtherLocale(domain);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean hasDuplicateOtherLocaleName(Long ownerOrgId,
			String otherLocaleName, Long exceptedId) {
		OtherLocale exsited = otherLocaleDao.getOtherLocaleByName(
				otherLocaleName, ownerOrgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	public OtherLocale hasDuplicateOtherLocale(Long ownerOrgId,
			String otherLocaleName) {
		return otherLocaleDao.getOtherLocaleByName(otherLocaleName, ownerOrgId);
	}

	@Override
	public OtherLocale updateOtherLocaleById(OtherLocale otherLocale) {
		return otherLocaleDao.updateOtherLocaleById(otherLocale);
	}

	@Override
	public void deleteOtherLocaleByIds(Long[] ids) {
		deleteOtherLocaleById(Arrays.asList(ids));
	}

	@Override
	public List<Long> deleteOtherLocaleById(List<Long> placeIds) {
		if (placeIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		// List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
		// placeIds, "OTHER_LOCALE");
		// placeIds.removeAll(exitPersonIdsList);
		// try {
		// for (Long id : exitPersonIdsList) {
		// OtherLocale domain = otherLocaleDao.getOtherLocaleById(id);
		// log(WARN, OTHER_LOCATION, ThreadVariable.getSession()
		// .getUserName()
		// + "删除其他场所" + domain.getName(), OperatorType.DELETE,
		// ObjectToJSON.convertJSON(domain));
		// }
		for (Long id : placeIds) {
			OtherLocale domain = getOtherLocaleById(id);
			deleteOtherLocaleById(id);
			try {
				// 删除对应服务记录与对象的关联关系
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, id,
						BaseInfoTables.OTHERLOCALE_KEY);
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.OTHERLOCALE_KEY, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(), domain.getName(),
						BaseInfoTables.OTHERLOCALE_KEY, id);
				issueTypeService.setOrgIdAndCardNoOrNameForPlace(domain
						.getOrganization().getId(), domain.getName(),
						BaseInfoTables.OTHERLOCALE_KEY + "S", id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// } catch (JSONException e) {
		// logger.error("异常信息", e);
		// }
		return placeIds;
	}

	@Override
	public boolean hasRelatePlacce(List<Long> placeIds) {
		if (placeIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
				placeIds, "OTHER_LOCALE");
		if (exitPersonIdsList != null && exitPersonIdsList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void shiftOtherLocale(Long[] ids, Long orgId) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null) {
				continue;
			}
			OtherLocale otherLocale = getOtherLocaleById(ids[i]);
			otherLocale.getOrganization().setId(orgId);
			otherLocale.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode());
			boolean bol = hasDuplicateOtherLocaleName(orgId,
					otherLocale.getName(), otherLocale.getId());
			if (bol) {
				updateOtherLocaleByName(otherLocale.getName(), otherLocale
						.getOrganization().getId(), otherLocale);
				deleteOtherLocaleById(ids[i]);

			} else {
				updateOtherLocale(otherLocale);
			}
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, OtherLocale location) {
		for (int i = 0; i < ids.length; i++) {
			updateEmphasiseById(ids[i], location.getIsEmphasis() ? 1l : 0l,
					location.getLogOutReason(), location.getLogOutTime());
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		OtherLocale domain = getOtherLocaleById(id);
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					BaseInfoTables.OTHERLOCALE_KEY, id, isEmphasis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (isEmphasis == 1L) {
				// 其他场所取消关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(domain, domain.getName(),
								domain.getOrgInternalCode()),
						BaseInfoTables.OTHERLOCALE_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.CANCEL_ATTENTION,
						"其他场所取消关注");
			} else {
				// 其他场所重新关注增加轨迹
				orgLocationTracksService.addLocationTracks(
						new LocationTracksEntity(domain, domain.getName(),
								domain.getOrgInternalCode()),
						BaseInfoTables.OTHERLOCALE_KEY,
						OrgLocationInitType.TRANSFOR_DOOM, null,
						OrgLocationTracksOperationType.AGAIN_ATTENTION,
						"其他场所重新关注");
			}
			otherLocaleDao.updateEmphasiseById(id, isEmphasis, logoutReason,
					logoutDate);
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.OTHERLOCALES.toString(), isEmphasis);
		}
	}
}
