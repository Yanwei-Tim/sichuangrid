package com.tianque.service.impl;

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
import com.tianque.dao.SchoolDao;
import com.tianque.domain.Organization;
import com.tianque.domain.School;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.FloorpersonService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.PersonInChargesService;
import com.tianque.service.SchoolService;
import com.tianque.service.bridge.BaseInfoDeleter;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;
import com.tianque.validate.impl.SchoolValidatorImpl;

@Transactional
@Service("schoolService")
public class SchoolServiceImpl extends LogableService implements SchoolService {

	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private BaseInfoDeleter baseInfoDeleter;

	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private FloorpersonService floorpersonService;
	@Autowired
	private PersonInChargesService personInChargesService;
	private SchoolValidatorImpl schoolValidatorImpl;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;

	@Override
	public School addSchool(School school) {
		if (!ExcelImportHelper.isImport.get()) {
			schoolValidatorImpl = new SchoolValidatorImpl();
			schoolValidatorImpl.setOrganizationDubboService(organizationDubboService);
			schoolValidatorImpl.setValidateHelper(validateHelper);
			ValidateResult schoolValidator = ((DomainValidator<School>) schoolValidatorImpl)
					.validate(school);
			if (schoolValidator.hasError()) {
				throw new BusinessValidationException(
						schoolValidator.getErrorMessages());
			} else if (hasDuplicateSchoolName(school.getOrganization().getId(),
					school.getChineseName(), school.getId())) {
				throw new BusinessValidationException("该网格下已存在相同名称的学校");
			}
			SchoolServiceHelper.checkMentalPatient(school);
		}
		try {
			this.autoFillOrganizationInternalCode(school);
			this.autoFillChinesePinyin(school);
			school = schoolDao.addSchool(school);
			// 学校新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(school, school.getChineseName(),
							school.getOrgInternalCode()),
					BaseInfoTables.SCHOOL_KEY, OrgLocationInitType.IMPORT,
					null, OrgLocationTracksOperationType.ADDT, "学校新增");
			placeService.execute(DocumentType.SCHOOLS,
					OperateMode.ADD.toString(), school);
			return school;
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("新增信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	private void autoFillOrganizationInternalCode(School school) {
		if (school.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(school.getOrganization().getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			school.setOrgInternalCode(organization.getOrgInternalCode());
		}

	}

	private void autoFillChinesePinyin(School school) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(school
				.getChineseName());
		school.setFullPinyin((String) pinyin.get("fullPinyin"));
		school.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	@Override
	public boolean deleteSchoolById(Long id) {
		School domain = getSchoolById(id);
		log(WARN, SCHOOL, ThreadVariable.getSession().getUserName() + "删除学校"
				+ domain.getChineseName(), OperatorType.DELETE,
				ObjectToJSON.convertJSON(domain));
		floorpersonService.deleteFloorperson(id, "school");
		personInChargesService.deletePersonInCharges(id, "school");
		// 学校删除的轨迹
		orgLocationTracksService.addLocationTracks(new LocationTracksEntity(
				domain, domain.getChineseName(), domain.getOrgInternalCode()),
				BaseInfoTables.SCHOOL_KEY, OrgLocationInitType.IMPORT, null,
				OrgLocationTracksOperationType.DELETE, "学校删除");
		schoolDao.deleteSchoolById(id);
		placeService.execute(DocumentType.SCHOOLS,
				OperateMode.DELETE.toString(), domain);
		try {
			// 删除对应服务记录与对象的关联关系
			PluginServiceHelpUtil.doService("serviceRecordService",
					"deleteServiceRecordHasObject", new Class[] { Long.class,
							String.class }, id, BaseInfoTables.SCHOOL_KEY);
			PluginServiceHelpUtil.doService("routerService",
					"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class },
					BaseInfoTables.SCHOOL_KEY, id);
			/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
			PluginServiceHelpUtil.doService("routerService",
					"setOrgIdAndCardNoOrName", new Class[] { Long.class,
							String.class, String.class, Long.class }, domain
							.getOrganization().getId(),
					domain.getChineseName(), BaseInfoTables.SCHOOL_KEY, id);
			issueTypeService.setOrgIdAndCardNoOrNameForPlace(domain
					.getOrganization().getId(), domain.getChineseName(),
					BaseInfoTables.SCHOOL_KEY, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public PageInfo<School> finallSchoolList(School school, Integer page,
			Integer rows, String sidx, String sord, Long isEmphasis) {
		return schoolDao.finallSchoolList(school, page, rows, sidx, sord,
				isEmphasis);
	}

	@Override
	public School getSchoolById(Long id) {
		return schoolDao.getSimpleSchoolById(id);
	}

	@Override
	public School updateSchool(School school) {
		schoolValidatorImpl = new SchoolValidatorImpl();
		schoolValidatorImpl.setOrganizationDubboService(organizationDubboService);
		schoolValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult schoolValidator = ((DomainValidator<School>) schoolValidatorImpl)
				.validate(school);
		if (schoolValidator.hasError()) {
			throw new BusinessValidationException(
					schoolValidator.getErrorMessages());
		} else if (hasDuplicateSchoolName(school.getOrganization().getId(),
				school.getChineseName(), school.getId())) {
			throw new BusinessValidationException("该网格下已存在相同名称的学校");
		}
		this.autoFillOrganizationInternalCode(school);
		this.autoFillChinesePinyin(school);
		school = schoolDao.updateSchool(school);
		placeService.execute(DocumentType.SCHOOLS, OperateMode.EDIT.toString(),
				school);
		return school;
	}

	@Override
	public School findSchoolByChineseNameAndOrgId(String chineseName, Long orgId) {
		return schoolDao.findSchoolByChineseNameAndOrgId(chineseName, orgId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<School> findSchoolByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		return schoolDao.findSchoolByNameAndPinyinAndOrgInternalCode(name,
				orgInternalCode);
	}

	@Override
	public School updateSchoolByName(String chineseName, Long orgId,
			School domain) {
		try {
			School older = schoolDao.getSchoolByName(chineseName, orgId);
			domain.setId(older.getId());
			domain.setCreateDate(older.getCreateDate());
			domain.setCreateUser(older.getCreateUser());
			return updateSchool(domain);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("修改信息出现错误", e);
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean hasDuplicateSchoolName(Long ownerOrgId, String hospitalName,
			Long exceptedId) {
		School exsited = schoolDao.getSchoolByName(hospitalName, ownerOrgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	public School updateSchoolByEmphasis(School school) {
		return schoolDao.updateSchool(school);
	}

	@Override
	public List<Long> deleteSchoolById(List<Long> placeIds) {
		// List<Long> exitPersonIdsList = baseInfoDeleter.getRelateplaceId(
		// placeIds, "SCHOOL");
		// placeIds.removeAll(exitPersonIdsList);
		// try {
		// for (Long id : exitPersonIdsList) {
		// School domain = schoolDao.getSimpleSchoolById(id);
		// log(WARN, SCHOOL, ThreadVariable.getSession().getUserName()
		// + "删除学校" + domain.getChineseName(),
		// OperatorType.DELETE, ObjectToJSON.convertJSON(domain));
		// }
		for (Long id : placeIds) {
			deleteSchoolById(id);
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
				placeIds, "FIRESAFETYKEY");
		if (exitPersonIdsList != null && exitPersonIdsList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void shiftSchool(Long[] ids, Long orgId) {
		School school = new School();
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null) {
				continue;
			}
			school = getSchoolById(ids[i]);
			school.getOrganization().setId(orgId);
			school.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
					orgId).getOrgInternalCode());
			boolean bol = hasDuplicateSchoolName(orgId,
					school.getChineseName(), school.getId());
			if (bol) {
				updateSchoolByName(school.getChineseName(), school
						.getOrganization().getId(), school);
				deleteSchoolById(ids[i]);

			} else {
				updateSchool(school);
			}
		}
	}

	@Override
	public void updateEmphasiseByIds(Long[] ids, School location) {
		for (int i = 0; i < ids.length; i++) {
			Long isEmphasis = 0l;
			if (location.getIsEmphasis()) {
				isEmphasis = 1l;
			}
			updateEmphasiseById(ids[i], isEmphasis, location.getLogOutReason(),
					location.getLogOutTime());
		}
	}

	private void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		School domain = getSchoolById(id);
		try {
			PluginServiceHelpUtil.doService("routerService",
					"updateServiceTeamHasObjectsAndServiceMemberHasObject",
					new Class[] { String.class, Long.class, Long.class },
					BaseInfoTables.SCHOOL_KEY, id, isEmphasis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (isEmphasis == 1L) {
				// 学校取消关注增加轨迹
				orgLocationTracksService
						.addLocationTracks(
								new LocationTracksEntity(domain, domain
										.getChineseName(), domain
										.getOrgInternalCode()),
								BaseInfoTables.SCHOOL_KEY,
								OrgLocationInitType.TRANSFOR_DOOM,
								null,
								OrgLocationTracksOperationType.CANCEL_ATTENTION,
								"学校取消关注");
			} else {
				// 学校重新关注增加轨迹
				orgLocationTracksService
						.addLocationTracks(
								new LocationTracksEntity(domain, domain
										.getChineseName(), domain
										.getOrgInternalCode()),
								BaseInfoTables.SCHOOL_KEY,
								OrgLocationInitType.TRANSFOR_DOOM, null,
								OrgLocationTracksOperationType.AGAIN_ATTENTION,
								"学校重新关注");
			}
			schoolDao.updateEmphasiseById(id, isEmphasis, logoutReason,
					logoutDate);
			placeService.emphasisAndNotEmphasis(id,
					DocumentType.SCHOOLS.toString(), isEmphasis);// 更新keyPlaces表的状态
		}
	}

	@Override
	public School hasDuplicateSchool(Long orgId, String chineseName) {
		return schoolDao.getSchoolByName(chineseName, orgId);
	}

	@Override
	public List<Long> deleteSchoolByIds(Long[] ids) {
		return this.deleteSchoolById(Arrays.asList(ids));
	}

	@Override
	public List<String> autoCompleteSchoolName(String schoolName) {
		return schoolDao.autoCompleteSchoolName(schoolName);
	}
}
