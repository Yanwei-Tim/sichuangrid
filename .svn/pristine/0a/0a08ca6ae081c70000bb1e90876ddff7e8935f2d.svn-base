package com.tianque.baseInfo.publicPlace.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.baseInfo.publicPlace.dao.PublicPlaceDao;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
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

@Service("publicPlaceService")
@Transactional
public class PublicPlaceServiceImpl extends LogableService implements
		PublicPlaceService {

	@Autowired
	public PublicPlaceDao publicPlaceDao;
	@Autowired
	@Qualifier("publicPlaceValidator")
	private DomainValidator<PublicPlace> placeValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public PublicPlace addPublicPlace(PublicPlace place) {
		try {
			validatePlace(place);
			autoFilledData(place);
			place.setIsEmphasis(false);
			place = publicPlaceDao.addPlace(place);
			// 公共场所新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(place, place.getPlaceName(), place
							.getOrgInternalCode()),
					BaseInfoTables.PUBLICPLACE_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.ADDT, "公共场所新增");
			placeService.execute(DocumentType.PUBLICPLACE,
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
	public void deletePublicPlaceByIds(Long[] ids) {
		if (ids == null || ids.length == 0L) {
			throw new BusinessValidationException("ids没有获得到！");
		}
		for (int i = 0; i < ids.length; i++) {
			PublicPlace domain = publicPlaceDao.getPlaceById(ids[i]);
			deletePlaceById(ids[i]);
			try {
				// 删除对应服务记录
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, ids[i],
						BaseInfoTables.PUBLICPLACE_KEY);
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.PUBLICPLACE_KEY, ids[i]);
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
	public PublicPlace getPlaceById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("按照ID查找时，id不能为空..");
		}
		return publicPlaceDao.getPlaceById(id);
	}

	@Override
	public PublicPlace updatePublicPlace(PublicPlace place) {
		validatePlace(place);
		autoFilledData(place);
		PublicPlace publicPlace = publicPlaceDao.updatePlace(place);
		placeService.execute(DocumentType.PUBLICPLACE,
				OperateMode.EDIT.toString(), publicPlace);
		return publicPlace;
	}

	private void deletePlaceById(Long ids) {
		if (ids == null || ids < 1l) {
			throw new BusinessValidationException("删除公共场所时,ids 不能为空！");
		}
		PublicPlace place = null;
		place = publicPlaceDao.getPlaceById(ids);
		log(WARN, PUBLICPLACE, ThreadVariable.getSession().getUserName()
				+ "公共场所" + place.getPlaceName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(place));
		// 公共场所删除的轨迹
		orgLocationTracksService.addLocationTracks(new LocationTracksEntity(
				place, place.getPlaceName(), place.getOrgInternalCode()),
				BaseInfoTables.PUBLICPLACE_KEY, OrgLocationInitType.IMPORT,
				null, OrgLocationTracksOperationType.DELETE, "公共场所删除");
		publicPlaceDao.deletePlaceById(ids);
		placeService.execute(DocumentType.PUBLICPLACE,
				OperateMode.DELETE.toString(), place);
	}

	private void autoFilledData(PublicPlace place) {
		autoFillOrganizationInternalCode(place);
		autoFillChinesePinyin(place);
	}

	private void autoFillOrganizationInternalCode(PublicPlace place) {
		Organization org = organizationDubboService.getSimpleOrgById(place
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		place.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(PublicPlace place) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(place
				.getPlaceName());
		place.setFullPinyin((String) pinyin.get("fullPinyin"));
		place.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void validatePlace(PublicPlace publicPlace) {
		ValidateResult validateResult = placeValidator.validate(publicPlace);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}
		if (hasDuplicatePlace(publicPlace.getOrganization().getId(),
				publicPlace.getPlaceName(), publicPlace.getId())) {
			throw new BusinessValidationException("该网格下已存在相同的场所名称");
		}
	}

	@Override
	public PageInfo<PublicPlace> findPublicPlaceForPageByOrgInternalCode(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis) {
		if (null == orgId) {
			return emptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return emptyPageInfo();
			} else {
				return publicPlaceDao.findPublicPlaceForPageByOrgInternalCode(
						org.getOrgInternalCode(), page, rows, sidx, sord,
						isEmphasis);
			}
		}
	}

	private PageInfo<PublicPlace> emptyPageInfo() {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setResult(new ArrayList<PublicPlace>());
		return pageInfo;
	}

	@Override
	public PublicPlace updateEmphasiseByIds(Long[] ids, PublicPlace location) {

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
				e.printStackTrace();
			}
		}
		return null;
	}

	private void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		PublicPlace domain = getPlaceById(id);
		if (isEmphasis) {
			// 公共场所取消关注增加轨迹
			orgLocationTracksService
					.addLocationTracks(
							new LocationTracksEntity(domain, domain
									.getPlaceName(), domain
									.getOrgInternalCode()),
							BaseInfoTables.PUBLICPLACE_KEY,
							OrgLocationInitType.TRANSFOR_DOOM, null,
							OrgLocationTracksOperationType.CANCEL_ATTENTION,
							"公共场所取消关注");
		} else {
			// 公共场所重新关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getPlaceName(),
							domain.getOrgInternalCode()),
					BaseInfoTables.PUBLICPLACE_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.AGAIN_ATTENTION, "公共场所重新关注");
		}
		publicPlaceDao.updateEmphasiseById(id, isEmphasis, logoutReason,
				logoutDate);
		Long emphasis = 0L;
		if (isEmphasis) {
			emphasis = 1L;
		}
		placeService.emphasisAndNotEmphasis(id,
				DocumentType.PUBLICPLACE.toString(), emphasis);

	}

	@Override
	public Boolean hasDuplicatePlace(Long orgId, String placeName,
			Long exceptedId) {
		PublicPlace exsited = publicPlaceDao.getPlaceByPlaceAddress(placeName,
				orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	@Override
	public PublicPlace hasDuplicatePublicPlace(Long orgId, String placeName) {
		return publicPlaceDao.getPlaceByPlaceAddress(placeName, orgId);
	}

	@Override
	public PublicPlace updatePlaceByPlaceNameAndOrgId(String placeName,
			Long orgId, PublicPlace domain) {
		PublicPlace place = publicPlaceDao.getPlaceByPlaceAddress(placeName,
				orgId);
		domain.setId(place.getId());
		domain.setCreateDate(place.getCreateDate());
		domain.setCreateUser(place.getCreateUser());
		PublicPlace publicPlace = updatePublicPlace(domain);
		placeService.execute(DocumentType.PUBLICPLACE,
				OperateMode.EDIT.toString(), publicPlace);
		return publicPlace;
	}

}
