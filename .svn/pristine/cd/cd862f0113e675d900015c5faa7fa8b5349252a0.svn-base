package com.tianque.baseInfo.publicComplexPlaces.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.baseInfo.publicComplexPlaces.dao.PublicComplexPlacesDao;
import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
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
import com.tianque.jms.OperateMode;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;

@Service("publicComplexPlacesService")
@Transactional
public class PublicComplexPlacesServiceImpl extends LogableService implements
		PublicComplexPlacesService {

	@Autowired
	public PublicComplexPlacesDao publicComplexPlacesDao;
	@Autowired
	@Qualifier("publicComplexPlacesValidator")
	private DomainValidator<PublicComplexPlaces> placeValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public PublicComplexPlaces addPublicComplexPlaces(PublicComplexPlaces place) {
		try {
			validatePlace(place);
			autoFilledData(place);
			place.setIsEmphasis(false);
			place = publicComplexPlacesDao.addPlace(place);
			// 公共复杂场所新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(place, place.getPlaceName(), place
							.getOrgInternalCode()),
					BaseInfoTables.PUBLICPLACE_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.ADDT, "公共复杂场所新增");
			placeService.execute(DocumentType.PUBLICCOMPLEXPLACES,
					OperateMode.ADD.toString(), place);
		} catch (Exception e) {
			logger.error("", e);
			if (ExcelImportHelper.isImport.get()) {
				return null;
			}
		}
		return place;
	}

	@Override
	public void deletePublicComplexPlacesByIds(Long[] ids) {
		if (ids == null || ids.length == 0L) {
			throw new BusinessValidationException("ids没有获得到！");
		}
		for (int i = 0; i < ids.length; i++) {
			PublicComplexPlaces domain = publicComplexPlacesDao
					.getPlaceById(ids[i]);
			deletePlaceById(ids[i]);
			try {
				// 删除对应服务记录
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, ids[i],
						BaseInfoTables.PUBLICCOMPLEXPLACES_KEY);
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.PUBLICCOMPLEXPLACES_KEY, ids[i]);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(),
						domain.getPlaceName(), BaseInfoTables.PUBLICPLACE_KEY,
						ids[i]);
				issueTypeService.setOrgIdAndCardNoOrNameForPlace(domain
						.getOrganization().getId(), domain.getPlaceName(),
						BaseInfoTables.PUBLICPLACE_KEY, ids[i]);
			} catch (Exception e) {
				logger.error("", e);
			}
		}

	}

	@Override
	public PublicComplexPlaces getPlaceById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("按照ID查找时，id不能为空..");
		}
		return publicComplexPlacesDao.getPlaceById(id);
	}

	@Override
	public PublicComplexPlaces updatePublicComplexPlaces(
			PublicComplexPlaces place) {
		validatePlace(place);
		autoFilledData(place);
		PublicComplexPlaces publicComplexPlaces = publicComplexPlacesDao
				.updatePlace(place);
		placeService.execute(DocumentType.PUBLICCOMPLEXPLACES,
				OperateMode.EDIT.toString(), publicComplexPlaces);
		return publicComplexPlaces;
	}

	private void deletePlaceById(Long ids) {
		if (ids == null || ids < 1l) {
			throw new BusinessValidationException("删除公共复杂场所时,ids 不能为空！");
		}
		PublicComplexPlaces place = null;
		place = publicComplexPlacesDao.getPlaceById(ids);
		log(WARN, PUBLICPLACE, ThreadVariable.getSession().getUserName()
				+ "公共复杂场所" + place.getPlaceName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(place));
		// 公共复杂场所删除的轨迹
		orgLocationTracksService.addLocationTracks(new LocationTracksEntity(
				place, place.getPlaceName(), place.getOrgInternalCode()),
				BaseInfoTables.PUBLICPLACE_KEY, OrgLocationInitType.IMPORT,
				null, OrgLocationTracksOperationType.DELETE, "公共复杂场所删除");
		publicComplexPlacesDao.deletePlaceById(ids);
		placeService.execute(DocumentType.PUBLICCOMPLEXPLACES,
				OperateMode.DELETE.toString(), place);
	}

	private void autoFilledData(PublicComplexPlaces place) {
		autoFillOrganizationInternalCode(place);
		autoFillChinesePinyin(place);
	}

	private void autoFillOrganizationInternalCode(PublicComplexPlaces place) {
		Organization org = organizationDubboService.getSimpleOrgById(place
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		place.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(PublicComplexPlaces place) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(place
				.getPlaceName());
		place.setFullPinyin((String) pinyin.get("fullPinyin"));
		place.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void validatePlace(PublicComplexPlaces publicComplexPlaces) {
		ValidateResult validateResult = placeValidator
				.validate(publicComplexPlaces);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
		if (hasDuplicatePlace(publicComplexPlaces.getOrganization().getId(),
				publicComplexPlaces.getPlaceName(), publicComplexPlaces.getId())) {
			throw new BusinessValidationException("该网格下已存在相同的场所名称");
		}
	}

	@Override
	public PageInfo<PublicComplexPlaces> findPublicComplexPlacesForPageByOrgInternalCode(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis) {
		if (null == orgId) {
			return emptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return emptyPageInfo();
			} else {
				return publicComplexPlacesDao
						.findPublicComplexPlacesForPageByOrgInternalCode(
								org.getOrgInternalCode(), page, rows, sidx,
								sord, isEmphasis);
			}
		}
	}

	private PageInfo<PublicComplexPlaces> emptyPageInfo() {
		PageInfo<PublicComplexPlaces> pageInfo = new PageInfo<PublicComplexPlaces>();
		pageInfo.setResult(new ArrayList<PublicComplexPlaces>());
		return pageInfo;
	}

	@Override
	public PublicComplexPlaces updateEmphasiseByIds(Long[] ids,
			PublicComplexPlaces location) {

		for (int i = 0; i < ids.length; i++) {
			updateEmphasiseById(ids[i], location.getIsEmphasis(),
					location.getLogOutReason(), location.getLogOutTime());
			try {
				PluginServiceHelpUtil.doService("routerService",
						"updateServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class, Long.class },
						BaseInfoTables.PUBLICPLACE_KEY, ids[i],
						location.getIsEmphasis() ? 1l : 0l);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return null;
	}

	private void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		PublicComplexPlaces domain = getPlaceById(id);
		if (isEmphasis) {
			// 公共复杂场所取消关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getPlaceName(),
							domain.getOrgInternalCode()),
					BaseInfoTables.PUBLICPLACE_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.CANCEL_ATTENTION,
					"公共复杂场所取消关注");
		} else {
			// 公共复杂场所重新关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getPlaceName(),
							domain.getOrgInternalCode()),
					BaseInfoTables.PUBLICPLACE_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.AGAIN_ATTENTION,
					"公共复杂场所重新关注");
		}
		publicComplexPlacesDao.updateEmphasiseById(id, isEmphasis,
				logoutReason, logoutDate);
		Long emphasis = 0L;
		if (isEmphasis) {
			emphasis = 1L;
		}
		placeService.emphasisAndNotEmphasis(id,
				DocumentType.PUBLICCOMPLEXPLACES.toString(), emphasis);

	}

	@Override
	public Boolean hasDuplicatePlace(Long orgId, String placeName,
			Long exceptedId) {
		PublicComplexPlaces exsited = publicComplexPlacesDao
				.getPlaceByPlaceAddress(placeName, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public PublicComplexPlaces hasDuplicatePublicComplexPlaces(Long orgId,
			String placeName) {
		return publicComplexPlacesDao.getPlaceByPlaceAddress(placeName, orgId);
	}

	@Override
	public PublicComplexPlaces updatePlaceByPlaceNameAndOrgId(String placeName,
			Long orgId, PublicComplexPlaces domain) {
		PublicComplexPlaces place = publicComplexPlacesDao
				.getPlaceByPlaceAddress(placeName, orgId);
		domain.setId(place.getId());
		domain.setCreateDate(place.getCreateDate());
		domain.setCreateUser(place.getCreateUser());
		PublicComplexPlaces publicComplexPlaces = updatePublicComplexPlaces(domain);
		placeService.execute(DocumentType.PUBLICCOMPLEXPLACES,
				OperateMode.EDIT.toString(), publicComplexPlaces);
		return publicComplexPlaces;
	}

}
