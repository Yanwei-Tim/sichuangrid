package com.tianque.baseInfo.internetBar.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.internetBar.dao.InternetBarDao;
import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchInternetBarVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.util.PluginServiceHelpUtil;

@Service("internetBarService")
public class InternetBarServiceImpl extends LogableService implements
		InternetBarService {
	@Autowired
	private InternetBarDao internetBarDao;
	@Autowired
	@Qualifier("internetBarValidator")
	private DomainValidator<InternetBar> internetBarValidateImpl;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	private void autoFillChinesePinyin(InternetBar internetBar) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(internetBar.getPlaceName());
		internetBar.setFullPinyin((String) pinyin.get("fullPinyin"));
		internetBar.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void validatePlace(InternetBar internetBar) {
		ValidateResult validateResult = internetBarValidateImpl
				.validate(internetBar);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}

	}

	private void validateHasDuplicateInternetBar(InternetBar internetBar) {
		InternetBar exsited = internetBarDao.getInternetBarByPlaceNameAndOrgId(
				internetBar.getPlaceName(), internetBar.getOrganization()
						.getId());
		if (exsited != null && !exsited.getId().equals(internetBar.getId())) {
			throw new BusinessValidationException("网格中已经存在相同名称的网吧");
		}
	}

	@Override
	public InternetBar addInternetBar(InternetBar internetBar) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validatePlace(internetBar);
				validateHasDuplicateInternetBar(internetBar);
			}
			autoFillChinesePinyin(internetBar);
			InternetBar internetBar1 = internetBarDao
					.addInternetBar(internetBar);
			// 网吧新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(internetBar1, internetBar1
							.getPlaceName(), internetBar1.getOrganization()
							.getOrgInternalCode()),
					BaseInfoTables.INTERNETBAR_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.ADDT, "网吧新增");
			placeService.execute(DocumentType.INTERNETBAR,
					OperateMode.ADD.toString(), internetBar1);
			return internetBar1;
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("新增信息出现错误" + e);
			} else {
				return null;
			}
		}
	}

	@Override
	public InternetBar getInternetBarById(Long id) {
		return internetBarDao.getInternetBarById(id);
	}

	@Override
	public void deleteInternetBarByIds(Long[] ids) {
		for (Long id : ids) {
			InternetBar internetBar = getInternetBarById(id);
			// 网吧删除的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(internetBar, internetBar
							.getPlaceName(), internetBar.getOrganization()
							.getOrgInternalCode()),
					BaseInfoTables.INTERNETBAR_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.DELETE, "网吧删除");
			internetBarDao.deleteInternetBarById(id);
			placeService.execute(DocumentType.INTERNETBAR,
					OperateMode.DELETE.toString(), internetBar);
			try {
				// 删除对应服务记录
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, id,
						BaseInfoTables.INTERNETBAR_KEY);
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.INTERNETBAR_KEY, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						internetBar.getOrganization().getId(),
						internetBar.getPlaceName(),
						BaseInfoTables.INTERNETBAR_KEY, id);
				issueTypeService.setOrgIdAndCardNoOrNameForPlace(internetBar
						.getOrganization().getId(), internetBar.getPlaceName(),
						BaseInfoTables.INTERNETBAR_KEY, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public InternetBar updateInternetBar(InternetBar internetBar) {
		validatePlace(internetBar);
		validateHasDuplicateInternetBar(internetBar);
		autoFillChinesePinyin(internetBar);
		InternetBar internetBar1 = internetBarDao
				.updateInternetBar(internetBar);
		placeService.execute(DocumentType.INTERNETBAR,
				OperateMode.EDIT.toString(), internetBar1);
		return internetBar1;
	}

	@Override
	public InternetBar updateEmphasiseById(Long id, InternetBar location) {
		InternetBar domain = getInternetBarById(id);// 未修改时的信息
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					BaseInfoTables.INTERNETBAR_KEY, id,
					location.getIsEmphasis() ? 1l : 0l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (location.getIsEmphasis()) {
			// 网吧取消关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getPlaceName(),
							domain.getOrganization().getOrgInternalCode()),
					BaseInfoTables.INTERNETBAR_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.CANCEL_ATTENTION, "网吧取消关注");
		} else {
			// 网吧重新关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getPlaceName(),
							domain.getOrganization().getOrgInternalCode()),
					BaseInfoTables.INTERNETBAR_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.AGAIN_ATTENTION, "网吧重新关注");
		}
		internetBarDao.updateEmphasiseById(id, location.getIsEmphasis(),
				location.getLogOutReason(), location.getLogOutTime());
		Long emphasis = 0L;
		if (location.getIsEmphasis()) {
			emphasis = 1L;
		}
		placeService.emphasisAndNotEmphasis(id,
				DocumentType.INTERNETBAR.toString(), emphasis);
		return internetBarDao.getInternetBarById(id);
	}

	@Override
	public Boolean hasDuplicateInternetBar(Long orgId, String placeName,
			Long exceptedId) {
		InternetBar exsited = internetBarDao.getInternetBarByPlaceNameAndOrgId(
				placeName, orgId);
		if (exsited != null && !exsited.getId().equals(exceptedId)) {
			return true;
		}
		return false;
	}

	@Override
	public InternetBar hasDuplicateInternetBar(Long orgId, String placeName) {
		try {
			return internetBarDao.getInternetBarByPlaceNameAndOrgId(placeName,
					orgId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类InternetBarServiceImpl的hasDuplicateInternetBar方法出现异常，原因：",
					"判断网吧名称是否存在出现错误", e);
		}
	}

	@Override
	public PageInfo<InternetBar> searchInternetBarForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchInternetBarVo searchInternetBarVo) {
		return internetBarDao.searchInternetBarForPage(pageNum, pageSize,
				sortField, order, searchInternetBarVo);
	}

	@Override
	public InternetBar updateInternetBarForImport(InternetBar internetBar) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validatePlace(internetBar);
			}
			autoFillChinesePinyin(internetBar);
			InternetBar exsited = internetBarDao
					.getInternetBarByPlaceNameAndOrgId(internetBar
							.getPlaceName(), internetBar.getOrganization()
							.getId());
			internetBar.setId(exsited.getId());
			InternetBar internetBar1 = internetBarDao
					.updateInternetBar(internetBar);
			placeService.execute(DocumentType.INTERNETBAR,
					OperateMode.EDIT.toString(), internetBar1);
			return internetBar1;
		} catch (Exception e) {
			logger.error("", e);
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public Integer getCount(SearchInternetBarVo searchInternetBarVo) {
		// TODO Auto-generated method stub
		return internetBarDao.getCount(searchInternetBarVo);
	}

}
