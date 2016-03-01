package com.tianque.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HospitalDao;
import com.tianque.domain.Hospital;
import com.tianque.domain.vo.SearchHospitalVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.HospitalService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.util.PluginServiceHelpUtil;

@Service("hospitalService")
public class HospitalServiceImpl extends LogableService implements
		HospitalService {
	@Autowired
	private HospitalDao hospitalDao;
	@Autowired
	@Qualifier("hospitalValidator")
	private DomainValidator<Hospital> hospitalValidateImpl;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	private void autoFillChinesePinyin(Hospital hospital) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(hospital.getHospitalName());
		hospital.setFullPinyin((String) pinyin.get("fullPinyin"));
		hospital.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	private void validatePlace(Hospital hospital) {
		ValidateResult validateResult = hospitalValidateImpl.validate(hospital);
		if (validateResult.hasError()) {
			throw new BusinessValidationException(
					validateResult.getErrorMessages());
		}

	}

	private void validateHasDuplicateHospital(Hospital hospital) {
		Hospital exsited = hospitalDao.getHospitalByPlaceNameAndOrgId(
				hospital.getHospitalName(), hospital.getOrganization().getId());
		if (exsited != null && !exsited.getId().equals(hospital.getId())) {
			throw new BusinessValidationException("网格中已经存在相同名称的网吧");
		}
	}

	@Override
	public Hospital addHospital(Hospital hospital) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validatePlace(hospital);
				validateHasDuplicateHospital(hospital);
			}
			autoFillChinesePinyin(hospital);
			hospital.setOrgInternalCode(hospital.getOrganization()
					.getOrgInternalCode());
			Hospital hospital1 = hospitalDao.addHospital(hospital);
			// 网吧新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(hospital1, hospital1
							.getHospitalName(), hospital1.getOrganization()
							.getOrgInternalCode()),
					BaseInfoTables.HOSPITAL_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.ADDT, "网吧新增");
			placeService.execute(DocumentType.HOSPITAL,
					OperateMode.ADD.toString(), hospital1);
			return hospital1;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("新增信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	@Override
	public Hospital getHospitalById(Long id) {
		return hospitalDao.getHospitalById(id);
	}

	@Override
	public void deleteHospitalByIds(Long[] ids) {
		for (Long id : ids) {
			Hospital hospital = getHospitalById(id);
			// 网吧删除的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(hospital, hospital
							.getHospitalName(), hospital.getOrganization()
							.getOrgInternalCode()),
					BaseInfoTables.HOSPITAL_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.DELETE, "网吧删除");
			hospitalDao.deleteHospitalById(id);
			placeService.execute(DocumentType.HOSPITAL,
					OperateMode.DELETE.toString(), hospital);
			try {
				// 删除对应服务记录与对象的关联关系
				PluginServiceHelpUtil.doService("serviceRecordService",
						"deleteServiceRecordHasObject", new Class[] {
								Long.class, String.class }, id,
						BaseInfoTables.HOSPITAL_KEY);
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.HOSPITAL_KEY, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						hospital.getOrganization().getId(),
						hospital.getHospitalName(),
						BaseInfoTables.HOSPITAL_KEY, id);
				issueTypeService.setOrgIdAndCardNoOrNameForPlace(hospital
						.getOrganization().getId(), hospital.getHospitalName(),
						BaseInfoTables.HOSPITAL_KEY, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Hospital updateHospital(Hospital hospital) {
		validatePlace(hospital);
		validateHasDuplicateHospital(hospital);
		autoFillChinesePinyin(hospital);
		hospital.setOrgInternalCode(hospital.getOrganization()
				.getOrgInternalCode());
		Hospital Hospital1 = hospitalDao.updateHospital(hospital);
		placeService.execute(DocumentType.HOSPITAL,
				OperateMode.EDIT.toString(), Hospital1);
		return Hospital1;
	}

	@Override
	public Hospital updateEmphasiseById(Long id, Hospital location) {
		Hospital domain = getHospitalById(id);// 未修改时的信息
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					BaseInfoTables.HOSPITAL_KEY, id,
					location.getIsEmphasis() ? 1l : 0l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (location.getIsEmphasis()) {
			// 网吧取消关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getHospitalName(),
							domain.getOrganization().getOrgInternalCode()),
					BaseInfoTables.HOSPITAL_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.CANCEL_ATTENTION, "网吧取消关注");
		} else {
			// 网吧重新关注增加轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getHospitalName(),
							domain.getOrganization().getOrgInternalCode()),
					BaseInfoTables.HOSPITAL_KEY,
					OrgLocationInitType.TRANSFOR_DOOM, null,
					OrgLocationTracksOperationType.AGAIN_ATTENTION, "网吧重新关注");
		}
		hospitalDao.updateEmphasiseById(id, location.getIsEmphasis(),
				location.getLogOutReason(), location.getLogOutTime());
		Long emphasis = 0L;
		if (location.getIsEmphasis()) {
			emphasis = 1L;
		}
		placeService.emphasisAndNotEmphasis(id,
				DocumentType.HOSPITAL.toString(), emphasis);
		return hospitalDao.getHospitalById(id);
	}

	@Override
	public Boolean hasDuplicateHospital(Long orgId, String placeName,
			Long exceptedId) {
		Hospital exsited = hospitalDao.getHospitalByPlaceNameAndOrgId(
				placeName, orgId);
		if (exsited != null && !exsited.getId().equals(exceptedId)) {
			return true;
		}
		return false;
	}

	@Override
	public Hospital hasDuplicateHospital(Long orgId, String placeName) {
		try {
			return hospitalDao.getHospitalByPlaceNameAndOrgId(placeName, orgId);
		} catch (Exception e) {
			throw new ServiceValidationException("判断网吧名称是否存在出现错误", e);
		}
	}

	@Override
	public PageInfo<Hospital> searchHospitalForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHospitalVo searchHospitalVo) {
		return hospitalDao.searchHospitalForPage(pageNum, pageSize, sortField,
				order, searchHospitalVo);
	}

	@Override
	public Hospital updateHospitalForImport(Hospital Hospital) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				// validatePlace(Hospital);
			}
			autoFillChinesePinyin(Hospital);
			Hospital exsited = hospitalDao.getHospitalByPlaceNameAndOrgId(
					Hospital.getHospitalName(), Hospital.getOrganization()
							.getId());
			Hospital.setId(exsited.getId());
			Hospital Hospital1 = hospitalDao.updateHospital(Hospital);
			placeService.execute(DocumentType.HOSPITAL,
					OperateMode.EDIT.toString(), Hospital1);
			return Hospital1;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("修改信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	@Override
	public Integer getCount(SearchHospitalVo searchHospitalVo) {
		return hospitalDao.getCount(searchHospitalVo);
	}

	@Override
	public Hospital updateHospitalByName(String hospitalName, Long orgId,
			Hospital domain) {
		try {
			Hospital older = hospitalDao.getHospitalByPlaceNameAndOrgId(
					hospitalName, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateHospital(domain);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("修改信息出现错误", e);
			} else {
				return null;
			}
		}
	}

}
