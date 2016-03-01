package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.dangerousChemicalsUnit.service.DangerousChemicalsUnitService;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.druggy.service.DruggyService;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.internetBar.service.InternetBarService;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.service.MentalPatientService;
import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.baseInfo.otherAttentionPersonnel.service.OtherAttentionPersonnelService;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.publicPlace.service.PublicPlaceService;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Enterprise;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Hospital;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.RegradedPoints;
import com.tianque.domain.School;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.domain.property.IssueSourceType;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.domain.vo.WorkContacterRegradedReason;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.domain.EventSourceVo;
import com.tianque.eventSource.service.EventSourceService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.approval.service.FourTeamsApprovalItemService;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTag;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueViewType;
import com.tianque.fourTeams.fourTeamsIssue.controller.FourTeamsIssueOvertimeHelper;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueLogDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueProcessDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsTopIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccord;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueRelatedPeople;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsTopIssue;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueAccessConfigService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueRelatedPeopleService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSerialNumberGenerator;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueWorkFlowEngine;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueOperationLogValidator;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueValidator;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.fourTeams.service.FourTeamsIssueLogService;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.service.EnterpriseService;
import com.tianque.service.HospitalService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.OtherLocaleService;
import com.tianque.service.RegradedPointService;
import com.tianque.service.SchoolService;
import com.tianque.state.RegradedType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
public abstract class FourTeamsAbstractIssueServiceImpl extends
		AbstractBaseService implements FourTeamsIssueService {

	protected abstract FourTeamsIssueValidator getIssueValidator();

	protected abstract FourTeamsIssueOperationLogValidator getIssueLogValidator();

	protected abstract FourTeamsIssueSerialNumberGenerator getIssueSerialNumberGenerator();

	protected abstract FourTeamsIssueWorkFlowEngine getIssueWorkFlowEngine();

	@Autowired
	private FourTeamsIssueDao issueNewDao;
	@Autowired
	private RentalHouseService rentalHouseService;
	@Autowired
	private RectificativePersonService rectificativePersonService;
	@Autowired
	private PositiveInfoService posiviteInfoService;
	@Autowired
	private MentalPatientService mentalPatientService;
	@Autowired
	private IdleYouthService idleYouthService;
	@Autowired
	private DruggyService druggyService;
	@Autowired
	private DangerousGoodsPractitionerService dangerousGoodsPractitionerService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private OtherLocaleService otherLocaleService;
	@Autowired
	private OtherAttentionPersonnelService otherAttentionPersonnelService;
	@Autowired
	private SuperiorVisitService superiorVisitService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected PropertyDictService propertyDictService;
	@Autowired
	protected FourTeamsIssueProcessDao issueProcessDao;
	@Autowired
	protected FourTeamsIssueDao issueDao;
	@Autowired
	protected FourTeamsIssueLogDao issueLogDao;
	@Autowired
	private FourTeamsIssueLogService issueLogService;
	@Autowired
	protected FourTeamsTopIssueDao topIssueDao;
	@Autowired
	private FourTeamsApprovalItemService approvalItemService;
	@Autowired
	private EventSourceService eventSourceService;
	@Autowired
	private InternetBarService internetBarService;
	@Autowired
	private PublicPlaceService publicPlaceServic;
	@Autowired
	private DangerousChemicalsUnitService dangerousChemicalsUnitService;
	@Autowired
	private FourTeamsIssueAccessConfigService issueAccessConfigService;
	@Autowired
	private RegradedPointService regradedPointService;
	@Autowired
	private FourTeamsIssueOvertimeHelper issueOvertimeHelper;
	@Autowired
	private FourTeamsIssueRelatedPeopleService issueRelatedPeopleService;
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public FourTeamsIssueNew getIssueBySerialNumber(String serialNumber) {

		if (!StringUtil.isStringAvaliable(serialNumber)) {

			return null;
		}
		return issueDao.getIssueBySerialNumber(serialNumber);
	}

	@Override
	public FourTeamsIssueViewObject addIssue(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files, Map<String, String> map,
			String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeopleFixPhones) {
		try {
			validate(issue, files);
			validateNamesAndTelephones(issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones,
					issue);
			// 如果是服务审批转入的事件 就不再生成服务单号
			// if (!issue.isIssueType()) {
			// issue.setSerialNumber(getIssueSerialNumberGenerator()
			// .nextValue(issue));
			// }
			loadAndAutoFillAllOrgInfo(issue);
			setCenterLonLat(issue);// 设置三维坐标
			FourTeamsIssueNew savedIssue = issueDao.addIssue(issue);
			addIssueRelatedPeople(savedIssue, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			// 重点人员和重点场所
			if (map != null) {
				addEmphases(savedIssue.getId(), map, savedIssue);
			}
			fillIssueAttachFilesIssue(savedIssue, files);
			issueDao.addIssueAttachFiles(files);
			FourTeamsIssueStep step = getIssueWorkFlowEngine().register(
					savedIssue);
			return createIssueViewObject(savedIssue, step);
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(),
					e.getMessage(), e);
		}
	}

	private void setCenterLonLat(FourTeamsIssueNew issue) {
		if (StringUtil.isStringAvaliable(issue.getCenterLon())
				&& StringUtil.isStringAvaliable(issue.getCenterLat())) {
			GisBoundVo gisBoundVo = new GisBoundVo(issue.getCenterLon(),
					issue.getCenterLat(), issue.getGisType(),
					issue.getIsTransformat());
			GisTransformatUtil.autoFillGisBound(gisBoundVo);
			issue.setCenterLon(gisBoundVo.getLon());
			issue.setCenterLat(gisBoundVo.getLat());
			issue.setCenterLon2(gisBoundVo.getLon2());
			issue.setCenterLat2(gisBoundVo.getLat2());
		}
	}

	private void addEmphases(Long issueId, Map<String, String> map,
			FourTeamsIssueNew issueNew) {
		issueTypeService.deleteRelatePersons(issueId);
		issueTypeService.deleteRelatePlaces(issueId);
		for (int i = 0; i < map.size(); i++) {
			if (map.get("superiorvisit") != null
					&& !map.get("superiorvisit").equals("")) {
				String[] key = map.get("superiorvisit").split(",");
				for (int j = 0; j < key.length; j++) {
					SuperiorVisit superiorVisit = superiorVisitService
							.getSuperiorVisitById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "superiorvisit",
							superiorVisit.getId(), superiorVisit.getName());
				}
				map.remove("superiorvisit");
			}

			if (map.get("otherattention") != null
					&& !map.get("otherattention").equals("")) {
				String[] key = map.get("otherattention").split(",");
				for (int j = 0; j < key.length; j++) {
					OtherAttentionPersonnel otherAttention = otherAttentionPersonnelService
							.getSimpleOtherAttentionPersonnelById(Long
									.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId,
							"otherattention", otherAttention.getId(),
							otherAttention.getName());
				}
				map.remove("otherattention");
			}

			if (map.get("rectify") != null && !map.get("rectify").equals("")) {
				String[] key = map.get("rectify").split(",");
				for (int j = 0; j < key.length; j++) {
					RectificativePerson superiorVisit = rectificativePersonService
							.getRectificativePersonById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "rectify",
							superiorVisit.getId(), superiorVisit.getName());
				}
				map.remove("rectify");
			}

			if (map.get("positiveinfo") != null
					&& !map.get("positiveinfo").equals("")) {
				String[] key = map.get("positiveinfo").split(",");
				for (int j = 0; j < key.length; j++) {
					PositiveInfo superiorVisit = posiviteInfoService
							.getPositiveInfoById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "positiveinfo",
							superiorVisit.getId(), superiorVisit.getName());
				}
				map.remove("positiveinfo");
			}

			if (map.get("mentalpatient") != null
					&& !map.get("mentalpatient").equals("")) {
				String[] key = map.get("mentalpatient").split(",");
				for (int j = 0; j < key.length; j++) {
					MentalPatient mentalPatient = mentalPatientService
							.getMentalPatientById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "mentalpatient",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("mentalpatient");
			}

			if (map.get("idleyouth") != null
					&& !map.get("idleyouth").equals("")) {
				String[] key = map.get("idleyouth").split(",");
				for (int j = 0; j < key.length; j++) {
					IdleYouth mentalPatient = idleYouthService
							.getIdleYouthById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "idleyouth",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("idleyouth");
			}

			if (map.get("druggy") != null && !map.get("druggy").equals("")) {
				String[] key = map.get("druggy").split(",");
				for (int j = 0; j < key.length; j++) {
					Druggy mentalPatient = druggyService.getDruggyById(Long
							.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "druggy",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("druggy");
			}

			if (map.get("dangerousgoodspractitioner") != null
					&& !map.get("dangerousgoodspractitioner").equals("")) {
				String[] key = map.get("dangerousgoodspractitioner").split(",");
				for (int j = 0; j < key.length; j++) {
					DangerousGoodsPractitioner mentalPatient = dangerousGoodsPractitionerService
							.getSimpleDangerousGoodsPractitionerById(Long
									.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId,
							"dangerousgoodspractitioner",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("dangerousgoodspractitioner");
			}

			if (map.get("RENTALHOUSE") != null
					&& !map.get("RENTALHOUSE").equals("")) {
				String[] key = map.get("RENTALHOUSE").split(",");
				for (int j = 0; j < key.length; j++) {
					RentalHouse mentalPatient = rentalHouseService
							.getHouseInfoById(Long.parseLong(key[j]));
					issueTypeService.addRelatePlaces(issueId, "RENTALHOUSE",
							mentalPatient.getId(),
							mentalPatient.getRentalPerson());
					issueNew.setOccurLocation(mentalPatient.getAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("RENTALHOUSE");
			}

			updateMapWhenEnterpeise(map, issueId, issueNew,
					"SAFETYPRODUCTIONKEY");
			updateMapWhenEnterpeise(map, issueId, issueNew, "FIRESAFETYKEY");
			updateMapWhenEnterpeise(map, issueId, issueNew, "SECURITYKEY");
			updateMapWhenEnterpeise(map, issueId, issueNew, "ENTERPRISEKEY");

			if (map.get("SCHOOL") != null && !map.get("SCHOOL").equals("")) {
				String[] key = map.get("SCHOOL").split(",");
				for (int j = 0; j < key.length; j++) {
					School mentalPatient = schoolService.getSchoolById(Long
							.parseLong(key[j]));
					issueTypeService.addRelatePlaces(issueId, "SCHOOL",
							mentalPatient.getId(),
							mentalPatient.getChineseName());
					issueNew.setOccurLocation(mentalPatient.getAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("SCHOOL");
			}

			if (map.get("HOSPITAL") != null && !map.get("HOSPITAL").equals("")) {
				String[] key = map.get("HOSPITAL").split(",");
				for (int j = 0; j < key.length; j++) {
					Hospital hospital = hospitalService.getHospitalById(Long
							.parseLong(key[j]));
					issueTypeService.addRelatePlaces(issueId, "HOSPITAL",
							hospital.getId(), hospital.getHospitalName());
					issueNew.setOccurLocation(hospital.getAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("HOSPITAL");
			}

			if (map.get("OTHERLOCALES") != null
					&& !map.get("OTHERLOCALES").equals("")) {
				String[] key = map.get("OTHERLOCALES").split(",");
				for (int j = 0; j < key.length; j++) {
					OtherLocale mentalPatient = otherLocaleService
							.getOtherLocaleById(Long.parseLong(key[j]));
					issueTypeService.addRelatePlaces(issueId, "OTHERLOCALES",
							mentalPatient.getId(), mentalPatient.getName());
					issueNew.setOccurLocation(mentalPatient.getAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("OTHERLOCALES");
			}

			if (map.get("INTERNETBAR") != null
					&& !map.get("INTERNETBAR").equals("")) {
				String[] key = map.get("INTERNETBAR").split(",");
				for (int j = 0; j < key.length; j++) {
					InternetBar mentalPatient = internetBarService
							.getInternetBarById(Long.parseLong(key[j]));
					issueTypeService
							.addRelatePlaces(issueId, "INTERNETBAR",
									mentalPatient.getId(),
									mentalPatient.getPlaceName());
					issueNew.setOccurLocation(mentalPatient.getPlaceAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("INTERNETBAR");
			}

			if (map.get("PUBLICPLACE") != null
					&& !map.get("PUBLICPLACE").equals("")) {
				String[] key = map.get("PUBLICPLACE").split(",");
				for (int j = 0; j < key.length; j++) {
					PublicPlace mentalPatient = publicPlaceServic
							.getPlaceById(Long.parseLong(key[j]));
					issueTypeService
							.addRelatePlaces(issueId, "PUBLICPLACE",
									mentalPatient.getId(),
									mentalPatient.getPlaceName());
					issueNew.setOccurLocation(mentalPatient.getPlaceAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("PUBLICPLACE");
			}

			if (map.get("DANGEROUSCHEMICALSUNIT") != null
					&& !map.get("DANGEROUSCHEMICALSUNIT").equals("")) {
				String[] key = map.get("DANGEROUSCHEMICALSUNIT").split(",");
				for (int j = 0; j < key.length; j++) {
					DangerousChemicalsUnit mentalPatient = dangerousChemicalsUnitService
							.getDangerousChemicalsUnitById(Long
									.parseLong(key[j]));
					issueTypeService.addRelatePlaces(issueId,
							"DANGEROUSCHEMICALSUNIT", mentalPatient.getId(),
							mentalPatient.getUnitName());
					issueNew.setOccurLocation(mentalPatient.getUnitAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("DANGEROUSCHEMICALSUNIT");
			}
		}
	}

	private void updateMapWhenEnterpeise(Map<String, String> map, Long issueId,
			FourTeamsIssueNew issueNew, String enerpriseKey) {
		if (map.get(enerpriseKey) != null && !map.get(enerpriseKey).equals("")) {
			String[] key = map.get(enerpriseKey).split(",");
			for (int j = 0; j < key.length; j++) {
				Enterprise mentalPatient = enterpriseService
						.getEnterpriseById(Long.parseLong(key[j]));
				issueTypeService.addRelatePlaces(issueId, enerpriseKey,
						mentalPatient.getId(), mentalPatient.getName());
				issueNew.setOccurLocation(mentalPatient.getAddress());
				issueNewDao.updateIssue(issueNew);
			}
			map.remove(enerpriseKey);
		}
	}

	private void fillIssueAttachFilesIssueAndIssueLog(
			FourTeamsIssueLogNew issueLog, List<FourTeamsIssueAttachFile> files) {
		if (files != null && !files.isEmpty()) {
			for (FourTeamsIssueAttachFile issueAttachFile : files) {
				issueAttachFile.setIssue(issueLog.getIssue());
				issueAttachFile.setIssueLog(issueLog);
			}
		}
	}

	private void fillIssueAttachFilesIssue(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files) {
		if (files != null && !files.isEmpty()) {
			for (FourTeamsIssueAttachFile issueAttachFile : files) {
				issueAttachFile.setIssue(issue);
			}
		}
	}

	@Override
	public FourTeamsIssueViewObject updateIssue(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files, long stepId,
			Map<String, String> map, String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeoplefixPhones) {
		try {
			validate(issue, files);
			setCenterLonLat(issue);// 设置三维坐标
			validateNamesAndTelephones(issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeoplefixPhones,
					issue);
			loadAndAutoFillAllOrgInfo(issue);
			FourTeamsIssueNew postIssue = issueDao.updateIssue(issue);
			issueRelatedPeopleService
					.deleteIssueRelatedPeopleByIssueId(postIssue.getId());// 先删除相关人员信息
			addIssueRelatedPeople(postIssue, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeoplefixPhones);// 添加相关人员
			FourTeamsIssueNew existIssue = issueDao.getFullIssueById(issue
					.getId());
			updateIssueTypes(existIssue, issue);
			updateAttachFiles(postIssue, existIssue, files);
			FourTeamsIssueStep step = issueProcessDao
					.getSimpleIssueStepById(stepId);
			FourTeamsIssueViewObject issueViewObject = createIssueViewObject(
					postIssue, step);
			addEmphases(postIssue.getId(), map, postIssue);
			return issueViewObject;
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(),
					e.getMessage(), e);
		}
	}

	@Override
	public FourTeamsIssueLogNew updateIssueLog(FourTeamsIssueLogNew issueLog,
			List<FourTeamsIssueAttachFile> files) {
		try {
			FourTeamsIssueLogNew postIssueLog = issueLogDao.updateLog(issueLog);
			updateAttachFilesLog(postIssueLog, files);
			return postIssueLog;
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(),
					e.getMessage(), e);
		}
	}

	private void updateAttachFilesLog(FourTeamsIssueLogNew issueLog,
			List<FourTeamsIssueAttachFile> postFiles) {

		List<FourTeamsIssueAttachFile> existAttachFiles = issueDao
				.loadIssueAttachFilesByIssueAndLog(issueLog.getIssue().getId(),
						issueLog.getId());
		// 修改事件的时候没有上传附件
		if (postFiles == null || postFiles.isEmpty()) {
			// 该事件本来就没有附件
			if (existAttachFiles == null || existAttachFiles.isEmpty()) {
				return;
			} else {
				// 如果有附件则删除附件
				for (FourTeamsIssueAttachFile issueAttachFile : existAttachFiles) {
					issueDao.deleteAttachFileByAttachfilesId(issueAttachFile
							.getId());
					deleteFile(issueAttachFile.getFileActualUrl());
				}
			}
		} else {
			if (existAttachFiles == null || existAttachFiles.isEmpty()) {
				fillIssueAttachFilesIssueAndIssueLog(issueLog, postFiles);
				issueDao.addIssueAttachFiles(postFiles);
			} else {
				List<FourTeamsIssueAttachFile> addFile = new ArrayList<FourTeamsIssueAttachFile>();
				List<FourTeamsIssueAttachFile> saveFile = new ArrayList<FourTeamsIssueAttachFile>();
				for (FourTeamsIssueAttachFile file : postFiles) {
					if (file.getId() == null) {
						addFile.add(file);
					} else {
						saveFile.add(file);
					}
				}
				fillIssueAttachFilesIssueAndIssueLog(issueLog, addFile);
				issueDao.addIssueAttachFiles(addFile);
				existAttachFiles.removeAll(saveFile);
				for (FourTeamsIssueAttachFile file : existAttachFiles) {
					issueDao.deleteAttachFileByAttachfilesId(file.getId());
					deleteFile(file.getFileActualUrl());
				}
			}
		}

	}

	@Override
	public void addIssueAttachFiles(FourTeamsIssueNew postIssue,
			FourTeamsIssueNew existIssue, List<FourTeamsIssueAttachFile> files) {
		updateAttachFiles(postIssue, existIssue, files);

	}

	@Override
	public List<FourTeamsIssueAttachFile> getIssueEvaluateAttachFileAttachFileByIssueId(
			Long issueId) {
		if (null == issueId) {
			throw new BusinessValidationException("参数错误");
		}
		return issueDao.getIssueEvaluateAttachFileAttachFileByIssueId(issueId);
	}

	@Override
	public void addIssueEvaluateAttachFile(
			FourTeamsIssueEvaluate issueEvaluate,
			List<FourTeamsIssueAttachFile> files) {
		fillIssueAttachFilesIssue(issueEvaluate.getIssue(), files);
		List<FourTeamsIssueAttachFile> existAttachFiles = issueDao
				.getIssueEvaluateAttachFileAttachFileByIssueId(issueEvaluate
						.getIssue().getId());
		for (FourTeamsIssueAttachFile issueAttachFile : existAttachFiles) {
			if (null != issueAttachFile.getId())
				issueDao.deleteAttachFileByAttachfilesId(issueAttachFile
						.getId());
		}
		issueDao.addIssueEvaluateAttachFiles(files);
	}

	private void updateAttachFiles(FourTeamsIssueNew postIssue,
			FourTeamsIssueNew existIssue,
			List<FourTeamsIssueAttachFile> postFiles) {

		List<FourTeamsIssueAttachFile> existAttachFiles = issueDao
				.getIssueAttachFileAndNotLogAttachFileByIssueId(existIssue
						.getId());
		// 修改事件的时候没有上传附件
		if (postFiles == null || postFiles.isEmpty()) {
			// 该事件本来就没有附件
			if (existAttachFiles == null || existAttachFiles.isEmpty()) {
				return;
			} else {
				// 如果有附件则删除附件
				for (FourTeamsIssueAttachFile issueAttachFile : existAttachFiles) {
					issueDao.deleteAttachFileByAttachfilesId(issueAttachFile
							.getId());
					deleteFile(issueAttachFile.getFileActualUrl());
				}
			}
		} else {
			if (existAttachFiles == null || existAttachFiles.isEmpty()) {
				fillIssueAttachFilesIssue(postIssue, postFiles);
				issueDao.addIssueAttachFiles(postFiles);
			} else {
				List<FourTeamsIssueAttachFile> addFile = new ArrayList<FourTeamsIssueAttachFile>();
				List<FourTeamsIssueAttachFile> saveFile = new ArrayList<FourTeamsIssueAttachFile>();
				for (FourTeamsIssueAttachFile file : postFiles) {
					if (file.getId() == null) {
						addFile.add(file);
					} else {
						saveFile.add(file);
					}
				}
				fillIssueAttachFilesIssue(postIssue, addFile);
				issueDao.addIssueAttachFiles(addFile);
				existAttachFiles.removeAll(saveFile);
				for (FourTeamsIssueAttachFile file : existAttachFiles) {
					issueDao.deleteAttachFileByAttachfilesId(file.getId());
					deleteFile(file.getFileActualUrl());
				}
			}
		}

	}

	/** 物理删除文件 */
	private void deleteFile(String filePath) {
		filePath = FileUtil.getWebRoot() + File.separator + filePath;
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			try {
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 修改事件类型 对IssueType进行更新 更新方法是才用交集原则 就是保持两个集合的交集不变 在交集之外的删除旧的，增加新的
	 * 
	 * @param existIssue旧集合
	 * @param postIssue
	 *            新集合
	 */
	private void updateIssueTypes(FourTeamsIssueNew existIssue,
			FourTeamsIssueNew postIssue) {
		handleExistIssueTypes(existIssue.getId(), existIssue.getIssueTypes(),
				postIssue.getIssueTypes());
		handlePostIssueTypes(existIssue.getId(), existIssue.getIssueTypes(),
				postIssue.getIssueTypes());
	}

	/**
	 * ***删除旧集合 处理旧的集合只有删除
	 * 
	 * @param id
	 * @param existIssueTypes
	 *            存在的
	 * @param postIisueTypes
	 *            要添加的
	 */
	private void handlePostIssueTypes(Long issueId,
			List<IssueType> existIssueTypes, List<IssueType> postIssueTypes) {
		// 如果旧的集合不存在，则没有可删除的
		if (existIssueTypes == null || existIssueTypes.size() == 0) {
			return;
		}
		// 如果新的集合不存在，则所有旧的集合全部删掉
		if (postIssueTypes == null || postIssueTypes.size() == 0) {
			issueDao.deleteIssueHasTypesByIssueId(issueId);
		} else {
			// 如果existIssueTypes不包含要保存的，那么删除
			for (IssueType existIssueType : existIssueTypes) {
				if (!postIssueTypes.contains(existIssueType)) {
					issueDao.deleteIssueHasTypesByIssueIdAndIssueTypeId(
							issueId, existIssueType.getId());
				}
			}
		}
	}

	/**
	 * 新增新集合 处理新的集合只有增加
	 * 
	 * @param id
	 * @param existIssueTypes
	 *            存在的
	 * @param postIisueTypes
	 *            要添加的
	 */
	private void handleExistIssueTypes(Long issueId,
			List<IssueType> existIssueTypes, List<IssueType> postIssueTypes) {
		// 如果新的集合不存在，则没有可增加的
		if (postIssueTypes == null || postIssueTypes.size() == 0) {
			return;
		}
		// 如果旧的集合不存在，则所有的新的需要增加
		if (existIssueTypes == null || existIssueTypes.size() == 0) {
			for (IssueType postIssueType : postIssueTypes) {
				issueDao.addIssueHasTypes(issueId, postIssueType.getId(),
						postIssueType.getIssueTypeDomain().getId());
			}
		} else {
			for (IssueType postIssueType : postIssueTypes) {
				if (!existIssueTypes.contains(postIssueType)) {
					issueDao.addIssueHasTypes(issueId, postIssueType.getId(),
							postIssueType.getIssueTypeDomain().getId());
				}
			}
		}

	}

	@Override
	public boolean deleteIssueByIssueId(Long issueId) {
		FourTeamsIssueNew issue = getFullIssueByIssueId(issueId);
		approvalItemService.deleteApprovalItemByApprovalNumber(issue
				.getSerialNumber());
		removeIssueAllAttachFiles(issueId);
		removeIssueFromPublilty(issueId);
		issueDao.deleteIssueHasTypesByIssueId(issueId);
		issueLogDao.deleteIssueLogByIssueId(issueId);
		getIssueWorkFlowEngine().unRegister(issueId);
		issueTypeService.deleteRelatePersons(issueId);
		issueTypeService.deleteRelatePlaces(issueId);
		issueDao.deleteIssueById(issueId);
		topIssueDao.deleteTopIssue(issueId, ThreadVariable.getOrganization()
				.getId(), FourTeamsIssueTag.NEEDDO_ISSUE);
		issueRelatedPeopleService.deleteIssueRelatedPeopleByIssueId(issueId);// 删除相关人员信息
		return true;
	}

	@Override
	public boolean topIssue(FourTeamsTopIssue topIssue) {
		FourTeamsTopIssue ti = topIssueDao.getTopIssue(topIssue);
		if (ti == null) {
			topIssue.setTopState(FourTeamsTopIssue.TOP);
			topIssue.setTopDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
			FourTeamsTopIssue te = topIssueDao.addTopIssue(topIssue);
			return te != null;
		} else {

			return topIssueDao.deleteTopIssueById(ti.getId());
		}
	}

	@Override
	public FourTeamsIssueViewObject publiclty(Long issueId) {
		FourTeamsIssueNew issue = getFullIssueByIssueId(issueId);
		Organization org = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		if (!alreadyPubliclty(org, issue)) {
			issueDao.publiclty(org.getId(), issue.getId());
			topIssueDao.deleteTopIssue(issueId, org.getId(),
					FourTeamsIssueTag.DONE_ISSUE);
		}
		return createIssueViewObject(issue);
	}

	@Override
	public FourTeamsIssueViewObject cancelPubliclty(Long issueId) {
		FourTeamsIssueNew issue = getFullIssueByIssueId(issueId);
		Organization org = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		issueDao.removePubliclty(org.getId(), issue.getId());
		topIssueDao.deleteTopIssue(issueId, org.getId(),
				FourTeamsIssueTag.PUBLICLTYCASS_ISSUE);
		return createIssueViewObject(issue);
	}

	@Override
	public FourTeamsIssueViewObject reportToCommandCenter(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.REPORT_TO);
		validateOperationLog(FourTeamsIssueOperate.REPORT_TO, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().reportTo(issue,
				step, log);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject complete(Long stepId,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles) {
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.COMPLETE);
		validateOperationLog(FourTeamsIssueOperate.COMPLETE, log, attachFiles);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().complete(issue,
				step, log, attachFiles);
		issue = getFullIssueByStepId(stepId);
		// if (issue.isIssueType() == true) {
		// approvalItemService.complete(issue.getSerialNumber());
		// }
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject submit(Long stepId,
			FourTeamsIssueLogNew log, Long submitTarget, Long[] tells,
			List<FourTeamsIssueAttachFile> attachFiles) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.SUBMIT);
		validateOperationLog(FourTeamsIssueOperate.SUBMIT, log, attachFiles);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().submit(issue,
				step, log, submitTarget, tells, attachFiles);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject giveTo(Long stepId,
			FourTeamsIssueLogNew log, Long target, Long[] tells,
			List<FourTeamsIssueAttachFile> files) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.GIVETO);
		validateOperationLog(FourTeamsIssueOperate.GIVETO, log, files);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().giveTo(issue,
				step, log, target, tells, files);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject assign(Long stepId,
			FourTeamsIssueLogNew log, Long targetOrg, Long[] tells,
			List<FourTeamsIssueAttachFile> attachFiles) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.ASSIGN);
		validateOperationLog(FourTeamsIssueOperate.ASSIGN, log, attachFiles);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		if (null != log.getIssueStep()) {
			step.setItemTypeId(log.getIssueStep().getItemTypeId());
		}
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().assign(issue,
				step, log, targetOrg, tells, attachFiles);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject concept(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.CONCEPT);
		validateOperationLog(FourTeamsIssueOperate.CONCEPT, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().concept(issue,
				step, log);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject read(Long stepId, FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.READ);
		validateOperationLog(FourTeamsIssueOperate.READ, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().read(issue, step,
				log);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject comment(Long stepId,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.COMMENT);
		validateOperationLog(FourTeamsIssueOperate.COMMENT, log, attachFiles);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().comment(issue,
				step, log, attachFiles);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject back(Long stepId, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.BACK);
		validateOperationLog(FourTeamsIssueOperate.BACK, log, attachFiles);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		FourTeamsIssueStep newStep = getIssueWorkFlowEngine().back(issue, step,
				log, attachFiles);
		issue = getFullIssueByStepId(stepId);
		return createIssueViewObject(issue, newStep);
	}

	@Override
	public FourTeamsIssueViewObject cancelHistoric(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.CANCEL_HISTORIC);
		validateOperationLog(FourTeamsIssueOperate.CANCEL_HISTORIC, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueStepById(stepId);
		issueDao.updateIssueHistoricState(issue.getId(), Boolean.FALSE);
		getIssueWorkFlowEngine().fireIssueChanged(issue, step,
				FourTeamsIssueOperate.CANCEL_HISTORIC, log,
				new ArrayList<FourTeamsIssueAttachFile>());
		return createIssueViewObject(getFullIssueByStepId(stepId),
				getIssueWorkFlowEngine().getFullIssueStepById(stepId));
	}

	@Override
	public FourTeamsIssueViewObject cancelSupervise(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.CANCEL_SUPERVISE);
		validateOperationLog(FourTeamsIssueOperate.CANCEL_SUPERVISE, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		step = getIssueWorkFlowEngine().cancelSupervise(issue, step, log);
		return createIssueViewObject(issue, step);
	}

	@Override
	public FourTeamsIssueViewObject cancelUrgent(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.CANCEL_URGENT);
		validateOperationLog(FourTeamsIssueOperate.CANCEL_URGENT, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueStepById(stepId);
		issueDao.updateIssueUrgentState(issue.getId(), Boolean.FALSE);
		getIssueWorkFlowEngine().fireIssueChanged(issue, step,
				FourTeamsIssueOperate.CANCEL_URGENT, log,
				new ArrayList<FourTeamsIssueAttachFile>());
		return createIssueViewObject(getFullIssueByStepId(stepId),
				getIssueWorkFlowEngine().getFullIssueStepById(stepId));
	}

	@Override
	public FourTeamsIssueViewObject historic(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.HISTORIC);
		validateOperationLog(FourTeamsIssueOperate.HISTORIC, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueStepById(stepId);
		issueDao.updateIssueHistoricState(issue.getId(), Boolean.TRUE);
		getIssueWorkFlowEngine().fireIssueChanged(issue, step,
				FourTeamsIssueOperate.HISTORIC, log,
				new ArrayList<FourTeamsIssueAttachFile>());
		return createIssueViewObject(getFullIssueByStepId(stepId),
				getIssueWorkFlowEngine().getFullIssueStepById(stepId));
	}

	@Override
	public FourTeamsIssueViewObject instruct(Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.INSTRUCT);
		validateOperationLog(FourTeamsIssueOperate.INSTRUCT, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		getIssueWorkFlowEngine().instruct(issue, step, log);
		return createIssueViewObject(issue, step);
	}

	@Override
	public FourTeamsIssueViewObject supervise(
			FourTeamsIssueOperate superviseLevel, Long stepId,
			FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, superviseLevel);
		validateOperationLog(superviseLevel, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueWorkFlowEngine()
				.getFullIssueStepById(stepId);
		step = getIssueWorkFlowEngine().supervise(issue, step, log,
				superviseLevel);
		double cent = issueAccessConfigService
				.getIssueScoresConfig(superviseLevel);
		deductStatRegradedPoint(superviseLevel, issue, cent, step.getTarget()
				.getId());
		return createIssueViewObject(issue, step);
	}

	private RegradedPoints deductStatRegradedPoint(
			FourTeamsIssueOperate superviseLevel, FourTeamsIssueNew issue,
			Double cent, Long orgId) {
		RegradedPoints regradedPoints = null;
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			WorkContacterRegradedReason regradedReason = new WorkContacterRegradedReason();
			regradedReason.setContent("服务单号：" + issue.getSerialNumber()
					+ ",主题:" + issue.getSubject() + ",原因:"
					+ superviseLevel.getDesc());
			regradedReason.setIssueSerialNumber(issue.getSerialNumber());
			String type = RegradedType.REGRADEDBYPERSON;
			if (superviseLevel.getCode() == FourTeamsIssueOperate.YELLOW_SUPERVISE_CODE) {
				type = RegradedType.YELLOWCARD;
			} else if (superviseLevel.getCode() == FourTeamsIssueOperate.RED_SUPERVISE_CODE) {
				type = RegradedType.REDCARD;
			}

			regradedPoints = regradedPointService.deductPoints(org, type,
					regradedReason, cent,
					CalendarUtil.now("yyyy-MM-dd HH:mm:ss"),
					getLastIssueLogId(issue.getId()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return regradedPoints;
	}

	private Long getLastIssueLogId(Long id) {
		List<FourTeamsIssueLogNew> issueLogs = issueLogService
				.findIssueLogsByIssueId(id);
		FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
		return issueLog == null ? null : issueLog.getId();
	}

	@Override
	public FourTeamsIssueViewObject urgent(Long stepId, FourTeamsIssueLogNew log) {
		autoFillIssueLogProperty(log, FourTeamsIssueOperate.URGENT);
		validateOperationLog(FourTeamsIssueOperate.URGENT, log, null);
		FourTeamsIssueNew issue = getFullIssueByStepId(stepId);
		FourTeamsIssueStep step = getIssueStepById(stepId);
		issueDao.updateIssueUrgentState(issue.getId(), Boolean.TRUE);
		getIssueWorkFlowEngine().fireIssueChanged(issue, step,
				FourTeamsIssueOperate.URGENT, log,
				new ArrayList<FourTeamsIssueAttachFile>());
		return createIssueViewObject(getFullIssueByStepId(stepId),
				getIssueWorkFlowEngine().getFullIssueStepById(stepId));
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyNeedDoIssues(Long orgId,
			Long issueType, Integer page, Integer rows, String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findMyNeedDoIssues(orgId, issueType, page, rows, sidx, sord);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findcommandCenterMyNeedDoIssues(
			Long orgId, Long issueType, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		EventSourceVo eventSourceVO = new EventSourceVo();
		PropertyDict state = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.EVENTSOURCE_STATE,
						IssueSourceType.TRANS_EVENT_NOTDONE).get(0);
		eventSourceVO.setState(state);
		eventSourceVO.setDealState(1);// 0未处理，1已处理
		List<EventSource> eventSourceList = eventSourceService
				.findInformation(eventSourceVO);

		String commandCenterNum = "";
		if (eventSourceList != null) {
			for (Iterator<EventSource> it = eventSourceList.iterator(); it
					.hasNext();) {
				EventSource eventSource = it.next();
				if (!"".equals(eventSource.getSerialNumber())) {
					commandCenterNum += eventSource.getSerialNumber() + ",";
				}
			}
		}
		if (!"".equals(commandCenterNum)) {
			commandCenterNum = commandCenterNum.substring(0,
					commandCenterNum.length() - 1);
		}
		return issueDao.findcommandCenterMyNeedDoIssues(orgId, issueType, page,
				rows, sidx, sord, commandCenterNum);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyDone(Long orgId,
			FourTeamsIssueNew issue, Integer page, Integer rows, String sidx,
			String sord, Long issueType) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao.findMyDone(
				orgId, issue, page, rows, sidx, sord, issueType);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	public PageInfo<FourTeamsIssueViewObject> findcommandCenterMyDone(
			Long orgId, FourTeamsIssueNew issue, Integer page, Integer rows,
			String sidx, String sord, int stateKind) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		PropertyDict state = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.EVENTSOURCE_STATE,
						IssueSourceType.TRANS_EVENT_DONE).get(0);
		EventSourceVo eventSourceVo = new EventSourceVo();
		eventSourceVo.setState(state);
		eventSourceVo.setDealState(1);// 0为未处理,1为已处理
		List<EventSource> eventSourceList = eventSourceService
				.findInformation(eventSourceVo);
		String commandCenterNum = "";
		if (eventSourceList != null) {
			for (Iterator<EventSource> it = eventSourceList.iterator(); it
					.hasNext();) {
				EventSource eventSource = it.next();
				if (eventSource.getSerialNumber() != null
						&& !"".equals(eventSource.getSerialNumber())) {
					commandCenterNum += eventSource.getSerialNumber() + ",";
				}
			}
		}
		if (!"".equals(commandCenterNum)) {
			commandCenterNum = commandCenterNum.substring(0,
					commandCenterNum.length() - 1);
		}
		return issueDao.findcommandCenterMyDone(orgId, issue, page, rows, sidx,
				sord, commandCenterNum, stateKind);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyHistoricIssues(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findMyHistoricIssues(orgId, org.getOrgInternalCode(), page,
						rows, sidx, sord);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyPublicltyIssues(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findMyPublicltyIssues(orgId, page, rows, sidx, sord);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	/*******
	 * 
	 * 越级
	 */
	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsSkipIssue(org, page, rows, sidx, sord,
						issueType, orgLevel, leaderView, functionalOrgType,
						viewProcess, sourceType);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(
			String statusType, String fourTeamsType, String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		try {
			if (orgId == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization userOrg = ThreadVariable.getUser().getOrganization();
			org = setQueryOrg(org, userOrg);
			seachValue = setseachValue(seachValue, userOrg);
			PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsDone(statusType, fourTeamsType,
							seachValue, org, page, rows, sidx, sord, issueType,
							orgLevel, leaderView, functionalOrgType,
							viewProcess, sourceType);
			for (FourTeamsIssueViewObject issueViewObject : pageInfo
					.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), "已办事件查询出错", e);
		}

	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String fourTeamsType, String seachValue, Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalOrgType,
			String statusType, Integer viewProcess, Long sourceType) {
		try {
			if (orgId == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			Organization userOrg = ThreadVariable.getUser().getOrganization();
			org = setQueryOrg(org, userOrg);
			seachValue = setseachValue(seachValue, org);
			PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsCompleted(fourTeamsType, seachValue, org,
							page, rows, sidx, sord, issueType, orgLevel,
							leaderView, functionalOrgType, statusType,
							viewProcess, sourceType);
			for (FourTeamsIssueViewObject issueViewObject : pageInfo
					.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), "已办结事件查询出错", e);
		}
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsSubmit(org, page, rows, sidx, sord,
						issueType, orgLevel, leaderView, functionalOrgType,
						viewProcess, sourceType);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
			loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findMyCompleted(Long orgId,
			Integer page, Integer rows, String sidx, String sord, Long issueType) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao.findMyCompleted(
				orgId, org.getOrgInternalCode(), page, rows, sidx, sord,
				issueType);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			String fourTeamsType, String seachValue, Long orgId, Integer page,
			Integer rows, String sidx, String sord, Long issueType,
			Long orgLevel, String leaderView, Long functionalorgType,
			Integer viewProcess, Long sourceType) {
		try {
			if (orgId == null) {
				return createEmptyIssueVoPageInfo(rows, 0);
			}
			Organization org = organizationDubboService.getFullOrgById(orgId);
			Organization userOrg = ThreadVariable.getUser().getOrganization();
			org = setQueryOrg(org, userOrg);
			seachValue = setseachValue(seachValue, org);
			PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
					.findJurisdictionsNeedDo(fourTeamsType, seachValue, org,
							page, rows, sidx, sord, issueType, orgLevel,
							leaderView, functionalorgType, viewProcess,
							sourceType);
			for (FourTeamsIssueViewObject issueViewObject : pageInfo
					.getResult()) {
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
			if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), "待办事件查询出错", e);
		}
	}

	// 判断当前登录用户是否是职能部门
	private boolean OrgIsFunctional(Organization userOrg) {
		boolean flg = false;
		if (userOrg.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			flg = true;
		}
		return flg;
	}

	// 根据登陆用户的组织机构设定内置编码
	// 如果是职能部门，需要判断
	// selectOrg 页面选择的org ||||| userOrg：当前用户登录的org
	private Organization setQueryOrg(Organization selectOrg,
			Organization userOrg) {
		if (OrgIsFunctional(userOrg)) {
			// 登陆的用户是职能部门
			Long userParentOrgId = userOrg.getParentOrg().getId();
			if (selectOrg.getId().equals(userParentOrgId)) {
				// 如果选择的组织机构是当前职能部门的父类节点，则采用职能部门的节点,并且只有自己本级
				return userOrg;
			} else {
				return selectOrg;
			}
		} else {
			return selectOrg;
		}
	}

	// 如果当前登录的是职能部门，那么只显示本层级的
	private String setseachValue(String seachValue, Organization userOrg) {
		if (OrgIsFunctional(userOrg)) {
			// 单独为职能部门设置查询条件
			return BasicOrgType.ORGSCOPE_FUNCTIONAL;
		}
		return seachValue;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoing(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsDoing(seachValue, org, page, rows, sidx,
						sord, issueType, orgLevel, leaderView,
						functionalOrgType, viewProcess);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
			loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsAssgin(seachValue, org, page, rows, sidx,
						sord, issueType, orgLevel, leaderView,
						functionalOrgType, viewProcess, sourceType);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		if (FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
			loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDoForProcess(
			String seachValue, Long orgId, String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(0, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsNeedDoForProcess(seachValue, org, sidx, sord);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}

		loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
		return pageInfo;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDoneForProcess(
			String seachValue, Long orgId, String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(0, 0);
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionsDoneForProcess(seachValue, org, sidx, sord);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
		return pageInfo;
	}

	private void loadIssueOccurOrgAndCurrentOrgAndIssueTypes(
			PageInfo<FourTeamsIssueViewObject> pageInfo) {
		if (null != pageInfo.getResult() && pageInfo.getResult().size() > 0) {
			for (FourTeamsIssueViewObject ivo : pageInfo.getResult()) {
				FourTeamsIssueNew issueNew = issueDao.getFullIssueById(ivo
						.getIssueId());
				loadFullIssueTypes(issueNew, ivo);

				ivo.setOccurOrg(organizationDubboService.getSimpleOrgById(ivo
						.getOccurOrg() == null ? null : ivo.getOccurOrg()
						.getId()));
				ivo.setCurrentOrg(organizationDubboService.getSimpleOrgById(ivo
						.getCurrentOrg() == null ? null : ivo.getCurrentOrg()
						.getId()));
				ivo.setCreateOrg(organizationDubboService.getSimpleOrgById(issueNew
						.getCreateOrg() == null ? null : issueNew
						.getCreateOrg().getId()));
				ivo.setCreateUser(issueNew.getCreateUser());

			}
		}
	}

	private void loadFullIssueTypes(FourTeamsIssueNew selectIssue,
			FourTeamsIssueViewObject issueViewObject) {
		Long tempId = null;
		String issueTypes = "";
		for (IssueType type : selectIssue.getIssueTypes()) {
			if (!type.getIssueTypeDomain().getId().equals(tempId)) {
				IssueTypeDomain issueTypeDomain = issueTypeService
						.getIssueTypeDomainById(type.getIssueTypeDomain()
								.getId());
				issueTypes += issueTypeDomain.getDomainName() + "：";
			}
			issueTypes += type.getIssueTypeName() + "，";
			tempId = type.getIssueTypeDomain().getId();
		}
		issueViewObject.setIssueTypes(issueTypes);
	}

	@Override
	public FourTeamsIssueNew getFullIssueByStepId(Long keyId) {
		FourTeamsIssueNew result = issueDao.getFullIssueByStepId(keyId);
		populationIssueProperty(result);
		return result;
	}

	@Override
	public FourTeamsIssueNew getFullIssueByIssueId(Long issueId) {
		FourTeamsIssueNew issue = issueDao.getFullIssueById(issueId);
		populationIssueProperty(issue);
		return issue;
	}

	@Override
	public List<FourTeamsIssueAttachFile> loadIssueAttachFilesByIssueId(
			Long issueId) {
		return issueDao.loadIssueAttachFilesByIssueAndLog(issueId, null);
	}

	@Override
	public List<FourTeamsIssueAttachFile> loadIssueAttachFilesByIssueIdAndIssueLogId(
			Long issueId, Long issueLogId) {
		return issueDao.loadIssueAttachFilesByIssueAndLog(issueId, issueLogId);
	}

	@Override
	public List<FourTeamsIssueLogNew> loadIssueOperationLogsByIssueId(Long id) {
		return issueLogDao.loadIssueOperationLogsByIssueId(id);
	}

	@Override
	public FourTeamsIssueAttachFile getIssueAttachFileById(Long id) {
		return issueDao.getIssueAttachFileById(id);
	}

	@Override
	public List<FourTeamsIssueOperate> getIssueCandoForOrg(Long stepId,
			Organization operateOrg) {
		if (stepId == null || isHistoricIssue(stepId)) {
			return new ArrayList<FourTeamsIssueOperate>();
		}
		return getIssueWorkFlowEngine().getIssueCandoForOrg(stepId, operateOrg);
	}

	@Override
	public FourTeamsIssueStep getIssueStepById(Long stepId) {
		return getIssueWorkFlowEngine().getFullIssueStepById(stepId);
	}

	@Override
	public PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			String tag, FourTeamsIssueOperate operate, Long[] exceptIds,
			int pageIndex, int rows) {
		return getIssueWorkFlowEngine().findAdminTargetsByName(stepid, operate,
				tag, exceptIds, pageIndex, rows);
	}

	@Override
	public PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			String tag, FourTeamsIssueOperate operate, Long[] exceptIds,
			int pageIndex, int rows) {
		return getIssueWorkFlowEngine().findFunctionTargetsByName(stepid,
				operate, tag, exceptIds, pageIndex, rows);
	}

	@Override
	public PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			String tag, FourTeamsIssueOperate operate, boolean transferToAdmin,
			Long[] exceptIds, int page, int rows) {
		return getIssueWorkFlowEngine().findTellTargetsByName(stepid, operate,
				tag, transferToAdmin, exceptIds, page, rows);
	}

	private void autoFillIssueLogProperty(FourTeamsIssueLogNew log,
			FourTeamsIssueOperate operate) {
		log.setDealType(Long.valueOf(operate.getCode()));
		log.setDealOrg(ThreadVariable.getSession().getOrganization());
		log.setDealTime(CalendarUtil.now());
	}

	private void loadAndAutoFillAllOrgInfo(FourTeamsIssueNew issue) {
		issue.setCreateOrg(loadSimpleOrg(ThreadVariable.getSession()
				.getOrganization().getId()));
		issue.setLastOrg(issue.getCreateOrg());
		issue.setOccurOrg(loadSimpleOrg(issue.getOccurOrg().getId()));
	}

	private Organization loadSimpleOrg(Long orgId) {
		return orgId == null ? null : organizationDubboService
				.getSimpleOrgById(orgId);
	}

	private void validate(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files) {
		ValidateResult vr = getIssueValidator().validate(issue);
		vr.addAllErrorMessage(getIssueValidator().validateAttachFiles(files));
		if (vr.hasError()) {
			throw new BusinessValidationException(vr.getErrorMessages());
		}
	}

	private void validateNamesAndTelephones(String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeopleFixPhones, FourTeamsIssueNew issue) {
		if (null == issueRelatedPeopleNames
				|| null == issueRelatedPeopleTelephones
				|| null == issueRelatedPeopleFixPhones) {
			throw new BusinessValidationException("参数错误");
		}
		String[] issueRelatedPeopleNameArray = issueRelatedPeopleNames
				.split(",");
		String[] issueRelatedPeopleTelephoneArray = issueRelatedPeopleTelephones
				.split(",");
		String[] issueRelatedPeoplefixPhoneArray = issueRelatedPeopleFixPhones
				.split(",");
		IssueTypeDomain issueTypeDomain = issueTypeService
				.getIssueTypeDoaminByDomainName(FourTeamsIssueTypes.SECURITYPRECAUTIONS);

		if (issue.getIssueTypes().get(0).getIssueTypeDomain().getId()
				.equals(issueTypeDomain.getId())) {// 如果事件类型是参与治安防控，则不进行必填项验证
			for (String name : issueRelatedPeopleNameArray) {
				if (StringUtil.isStringAvaliable(name)) {
					if (validateHelper.illegalExculdeParticalChar2(name.trim()))
						throw new BusinessValidationException("姓名不能输入特殊字符");
				}
			}
			validateFixPhoneAndTelephone(issueRelatedPeopleTelephoneArray,
					issueRelatedPeoplefixPhoneArray);
		} else {
			for (String name : issueRelatedPeopleNameArray) {
				if (!StringUtil.isStringAvaliable(name)) {
					throw new BusinessValidationException("姓名不能为空");
				} else if (validateHelper.illegalExculdeParticalChar2(name
						.trim())) {
					throw new BusinessValidationException("姓名不能输入特殊字符");
				}
			}
			validateFixPhoneAndTelephone(issueRelatedPeopleTelephoneArray,
					issueRelatedPeoplefixPhoneArray);
		}
	}

	private void validateFixPhoneAndTelephone(
			String[] issueRelatedPeopleTelephoneArray,
			String[] issueRelatedPeoplefixPhoneArray) {
		for (String telephone : issueRelatedPeopleTelephoneArray) {
			if (StringUtil.isStringAvaliable(telephone)) {
				if (validateHelper.illegalMobilePhone(telephone.trim())) {
					throw new BusinessValidationException("手机号码必须由1开头的11位数字组成");
				}
			}
		}
		for (String fixPhone : issueRelatedPeoplefixPhoneArray) {
			if (StringUtil.isStringAvaliable(fixPhone)) {
				if (validateHelper.illegalTelephone(fixPhone.trim())) {
					throw new BusinessValidationException("固话输入不正确，只能输数字和横杠(-)");
				}
			}
		}
	}

	private void addIssueRelatedPeople(FourTeamsIssueNew issue,
			String issueRelatedPeopleNames,
			String issueRelatedPeopleTelephones,
			String issueRelatedPeoplefixPhones) {
		String[] issueRelatedPeopleNameArray = issueRelatedPeopleNames
				.split(",");
		String[] issueRelatedPeopleTelephoneArray = issueRelatedPeopleTelephones
				.replace(",", ", ").replace("  ", " ").split(",");
		String[] issueRelatedPeoplefixPhoneArray = issueRelatedPeoplefixPhones
				.replace(",", ", ").replace("  ", " ").split(",");
		for (int i = 0; i < issueRelatedPeopleNameArray.length; i++) {
			FourTeamsIssueRelatedPeople issueRelatedPeople = new FourTeamsIssueRelatedPeople();
			issueRelatedPeople.setIssue(issue);
			issueRelatedPeople.setName(issueRelatedPeopleNameArray[i].trim());
			issueRelatedPeople.setTelephone(issueRelatedPeopleTelephoneArray[i]
					.trim());
			issueRelatedPeople.setFixPhone(issueRelatedPeoplefixPhoneArray[i]
					.trim());
			issueRelatedPeopleService.addIssueRelatedPeople(issueRelatedPeople);
		}
	}

	private void validateOperationLog(FourTeamsIssueOperate operate,
			FourTeamsIssueLogNew log, List<FourTeamsIssueAttachFile> attachFiles) {
		ValidateResult vr = getIssueLogValidator().validate(operate, log,
				attachFiles);
		if (vr.hasError()) {
			throw new BusinessValidationException(vr.getErrorMessages());
		}
	}

	private FourTeamsIssueViewObject createIssueViewObject(
			FourTeamsIssueNew issue, FourTeamsIssueStep step) {
		FourTeamsIssueViewObject issueViewObject = new FourTeamsIssueViewObject();
		copyPropertyFromIssue(issue, issueViewObject);
		issueViewObject.setDealTime(step.getLastDealDate());
		issueViewObject.setSuperviseLevel(step.getSuperviseLevel());
		issueViewObject.setIssueLogId(step.getId());
		issueViewObject.setIssueStepId(step.getId());
		issueViewObject.setSupervisionState(step.getSuperviseLevel());
		issueViewObject.setTargeOrg(step.getTarget());
		issueViewObject.setDealState(Long.valueOf(step.getStateCode()));
		return issueViewObject;
	}

	private boolean isHistoricIssue(Long stepId) {
		return getFullIssueByStepId(stepId).isHistoric();
	}

	private PageInfo<FourTeamsIssueViewObject> createEmptyIssueVoPageInfo(
			int pageSize, int pageIndex) {
		PageInfo<FourTeamsIssueViewObject> result = new PageInfo<FourTeamsIssueViewObject>();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private void populationIssueProperty(FourTeamsIssueNew issue) {
		if (issue == null) {
			return;
		}
		if (issue.getSourceKind() != null
				&& issue.getSourceKind().getId() != null) {
			issue.setSourceKind(propertyDictService.getPropertyDictById(issue
					.getSourceKind().getId()));
		}
		if (issue.getCurrentStep() != null
				&& issue.getCurrentStep().getId() != null) {
			issue.setCurrentStep(getIssueStepById(issue.getCurrentStep()
					.getId()));
		}
		if (issue.getCurrentOrg() != null
				&& issue.getCurrentOrg().getId() != null) {
			issue.setCurrentOrg(organizationDubboService.getSimpleOrgById(issue
					.getCurrentOrg().getId()));
		}
		if (issue.getLastOrg() != null && issue.getLastOrg().getId() != null) {
			issue.setLastOrg(organizationDubboService.getSimpleOrgById(issue
					.getLastOrg().getId()));
		}
		if (issue.getOccurOrg() != null && issue.getOccurOrg().getId() != null) {
			issue.setOccurOrg(organizationDubboService.getSimpleOrgById(issue
					.getOccurOrg().getId()));
		}
	}

	private FourTeamsIssueViewObject createIssueViewObject(
			FourTeamsIssueNew issue) {
		FourTeamsIssueViewObject issueViewObject = new FourTeamsIssueViewObject();
		copyPropertyFromIssue(issue, issueViewObject);
		if (issue.getCurrentStep() != null) {
			FourTeamsIssueStep step = getIssueStepById(issue.getCurrentStep()
					.getId());
			issueViewObject.setDealTime(step.getLastDealDate());
			issueViewObject.setIssueLogId(step.getId());
			issueViewObject.setIssueStepId(step.getId());
			issueViewObject.setSupervisionState(step.getSuperviseLevel());
			issueViewObject.setTargeOrg(step.getTarget());
			issueViewObject.setDealState(Long
					.valueOf(FourTeamsIssueState.DEALING));
		} else {
			issueViewObject.setDealState(Long
					.valueOf(FourTeamsIssueState.COMPLETE));
		}
		return issueViewObject;
	}

	private void copyPropertyFromIssue(FourTeamsIssueNew issue,
			FourTeamsIssueViewObject issueViewObject) {
		issueViewObject.setIssueId(issue.getId());
		issueViewObject.setSerialNumber(issue.getSerialNumber());
		issueViewObject.setSubject(issue.getSubject());
		issueViewObject.setStatus(issue.getStatus());
		issueViewObject.setCurrentOrg(issue.getCurrentStep() == null ? null
				: issue.getCurrentStep().getTarget());
		issueViewObject.setOccurDate(issue.getOccurDate());
		issueViewObject.setLastOrg(issue.getLastOrg());
		issueViewObject.setOccurOrg(issue.getOccurOrg());
		issueViewObject.setUrgent(issue.getUrgent());
		issueViewObject.setSourcePerson(issue.getSourcePerson());
		issueViewObject.setSourceKind(issue.getSourceKind());
		issueViewObject.setSourceMobile(issue.getSourceMobile());
	}

	/** 判断事件是否已经设为宣传案例 */
	private boolean alreadyPubliclty(Organization org, FourTeamsIssueNew issue) {
		return issueDao.alreadyPubliclty(issue.getId(), org.getId());
	}

	/**
	 * 从宣传案例中删除事件
	 * 
	 * @param issueId
	 *            事件id
	 */
	private void removeIssueFromPublilty(Long issueId) {
		issueDao.removePubliclty(null, issueId);
	}

	/***************************************************************************
	 * 删除事件的附件和附件关联关系
	 * 
	 * @param issueId
	 *            事件id
	 */
	private void removeIssueAllAttachFiles(Long issueId) {
		List<FourTeamsIssueAttachFile> issueAttachFiles = loadIssueAttachFilesByIssueId(issueId);
		if (issueAttachFiles != null && issueAttachFiles.size() > 0) {
			String webRootPath = FileUtil.getWebRoot();
			for (FourTeamsIssueAttachFile issueFile : issueAttachFiles) {
				File file = new File(webRootPath + File.separator
						+ issueFile.getFileActualUrl());
				if (file.exists()) {
					file.delete();
				}
			}
		}
		issueDao.removeAllIssueAttachFiles(issueId);
	}

	public List<FourTeamsIssueNew> searchAllRoundIssues(GisInfo minOption,
			GisInfo maxOption) {
		return issueDao.searchAllRoundIssues(minOption, maxOption);
	}

	@Override
	public List<FourTeamsIssueMap> getIssueMap(Long issueId) {
		List<FourTeamsIssueStepGroup> stepGroupList = issueProcessDao
				.getIssueStepGroupByIssueId(issueId);
		List<FourTeamsIssueMap> issueMapList;
		if (null != stepGroupList && stepGroupList.size() > 0) {
			issueMapList = loadIssueMap(stepGroupList);
		} else {
			issueMapList = new ArrayList<FourTeamsIssueMap>();
		}
		return issueMapList;
	}

	private List<FourTeamsIssueMap> loadIssueMap(
			List<FourTeamsIssueStepGroup> stepGroupList) {
		List<FourTeamsIssueMap> issueMapList = new ArrayList<FourTeamsIssueMap>();
		for (int i = 0; i < stepGroupList.size(); i++) {
			FourTeamsIssueStepGroup isg = stepGroupList.get(i);
			if (i == stepGroupList.size() - 1) {
				isg.setOutLog(isg.getEntyLog());
			}
			FourTeamsIssueMap im = issueProcessDao.getIssueMapByStepGroup(isg);
			if (im != null) {
				im.setId(isg.getId());
				if (i + 1 < stepGroupList.size()) {
					im.setTo(stepGroupList.get(i + 1).getId());
				}
				setRelationAndStates(im);
				im.setFunctionalOrg(propertyDictService.getPropertyDictById(
						organizationDubboService
								.getSimpleOrgById(im.getOrgId()).getOrgType()
								.getId()).getInternalId() == OrganizationType.FUNCTIONAL_ORG);
				issueMapList.add(im);
			}
		}
		return issueMapList;
	}

	private void setRelationAndStates(FourTeamsIssueMap im) {
		List<String> states = new ArrayList<String>();
		if (new Integer(21).equals(im.getDealType())) {
			im.setRelation("交办");
		}
		if (new Integer(41).equals(im.getDealType())) {
			im.setRelation("上报");
		}
		if (new Integer(200).equals(im.getDealType())) {
			im.setRelation("回退");
		}
		if (new Integer(1).equals(im.getUrgent())) {
			states.add("加急");
		}
		if (new Integer(1).equals(im.getSuperviselevel())) {
			states.add("普通督办");
		}
		if (new Integer(100).equals(im.getSuperviselevel())) {
			states.add("黄牌督办");
		}
		if (new Integer(200).equals(im.getSuperviselevel())) {
			states.add("红牌督办");
		}
		im.setStates(states);
	}

	public List<FourTeamsIssueLogNew> getIssueDealLog(FourTeamsIssueMap issueMap) {
		FourTeamsIssueStepGroup group = issueProcessDao
				.getSimpleIssueStepGroupById(issueMap.getId());

		List<FourTeamsIssueLogNew> logs = issueLogDao
				.findIssueDealLogByIssueMapAndIssueStepGroup(issueMap, group);
		return logs;
	}

	public Map<String, List<EmphasesVo>> findRelatePerson(Long issueId) {
		Map<String, List<EmphasesVo>> map = new HashMap<String, List<EmphasesVo>>();
		List<EmphasesVo> list = issueTypeService
				.findRelatePersonByName(issueId);
		for (int i = 0; i < list.size(); i++) {
			if (map.get(list.get(i).getType()) != null) {
				map.get(list.get(i).getType()).add(list.get(i));
			} else {
				List<EmphasesVo> emphasesVos = new ArrayList<EmphasesVo>();
				list.add(list.get(i));
				map.put(list.get(i).getType(), emphasesVos);
			}
		}
		return map;
	}

	public Map<String, List<EmphasesVo>> findRelatePlace(Long issueId) {
		Map<String, List<EmphasesVo>> map = new HashMap<String, List<EmphasesVo>>();
		List<EmphasesVo> list = issueTypeService
				.findRelatePlacesByName(issueId);
		for (int i = 0; i < list.size(); i++) {
			if (map.get(list.get(i).getType()) != null) {
				map.get(list.get(i).getType()).add(list.get(i));
			} else {
				List<EmphasesVo> emphasesVos = new ArrayList<EmphasesVo>();
				list.add(list.get(i));
				map.put(list.get(i).getType(), emphasesVos);
			}
		}
		return map;
	}

	@Override
	public FourTeamsIssueNew saveIssueGrade(Long id,
			FourTeamsIssueAccord issueAccord) {
		if (id == null || issueAccord == null || issueAccord.getScore() == null
				|| issueAccord.getType() == null
				|| issueAccord.getOrgId() == null
				|| issueAccord.getInfo() == null
				|| issueAccord.getLogId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		int length = issueAccord.getScore().length;
		if (length != issueAccord.getType().length
				|| length != issueAccord.getOrgId().length
				|| length != issueAccord.getInfo().length
				|| length != issueAccord.getLogId().length) {
			throw new BusinessValidationException("参数个数错误");
		}
		FourTeamsIssueNew issueNew = getFullIssueByIssueId(id);
		if (issueNew == null || issueNew.getSerialNumber() == null) {
			throw new BusinessValidationException("参数错误");
		}
		for (int i = 0; i < length; i++) {
			if (issueAccord.getScore()[i] == 0) {
				continue;
			}
			regradedPointService.deleteRegradedPointByLogId(issueAccord
					.getLogId()[i]);
			WorkContacterRegradedReason regradedReason = new WorkContacterRegradedReason();
			regradedReason.setContent(issueAccord.getInfo()[i]);
			regradedReason.setIssueSerialNumber(issueNew.getSerialNumber());
			try {
				if (issueAccord.getType()[i].equals(1)) {
					regradedPointService.bonusPoints(organizationDubboService
							.getSimpleOrgById(issueAccord.getOrgId()[i]),
							RegradedType.REGRADEDBYPERSON, regradedReason,
							issueAccord.getScore()[i], CalendarUtil
									.now("yyyy-MM-dd HH:mm:ss"), issueAccord
									.getLogId()[i]);
				} else {
					regradedPointService.deductPoints(organizationDubboService
							.getSimpleOrgById(issueAccord.getOrgId()[i]),
							RegradedType.REGRADEDBYPERSON, regradedReason,
							issueAccord.getScore()[i], CalendarUtil
									.now("yyyy-MM-dd HH:mm:ss"), issueAccord
									.getLogId()[i]);
				}
			} catch (JSONException e) {
				throw new ServiceValidationException(e.getMessage(),
						e.getMessage(), e);
			}
		}
		return issueNew;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			Integer page, Integer rows, String sidx, String sord) {
		PageInfo<FourTeamsIssueViewObject> pageInfo = issueDao
				.findJurisdictionAuditDelay(ThreadVariable.getOrganization()
						.getId(), page, rows, sidx, sord);
		for (FourTeamsIssueViewObject issueViewObject : pageInfo.getResult()) {
			issueOvertimeHelper.fillEaringWarnField(issueViewObject);
		}
		/** 待审核的没有展示功能 不需要去拼接事件类型 */
		// loadIssueOccurOrgAndCurrentOrgAndIssueTypes(pageInfo);
		return pageInfo;
	}

	@Override
	public FourTeamsIssueEvaluate getIssueEvaluateById(Long id) {
		return issueDao.getIssueEvaluateById(id);
	}

	@Override
	public Integer getIssueStepDelayWorkDaysById(Long stepId) {
		return issueDao.getIssueStepDelayWorkDaysById(stepId);
	}

	@Override
	public Integer getIssueStepCountByIssueId(Long issueId) {
		return issueProcessDao.getIssueStepCountByIssueId(issueId);
	}

	@Override
	public List<FourTeamsIssueAttachFile> getDocfilesByIdAndModuleKey(
			Long issueId, String moduleType, String fileType) {
		if (issueId == null || moduleType == null || moduleType.equals("")
				|| fileType == null || fileType.equals("")) {
			throw new BusinessValidationException("参数错误");
		}
		return issueNewDao.getDocfilesByIdAndModuleKey(issueId, moduleType,
				fileType);
	}

	@Override
	public Integer findIssueExistForMobile(FourTeamsIssueNew issue) {
		Integer countVal = 0;
		try {
			if (issue == null || issue.getUniqueIdForMobile() == null
					|| "".equals(issue.getUniqueIdForMobile())
					|| issue.getOccurOrg() == null
					|| issue.getOccurOrg().getId() == null) {
				throw new BusinessValidationException("对象或参数有误");
			}
			countVal = issueDao.findIssueExistForMobile(issue);

		} catch (Exception e) {
			throw new ServiceValidationException("查询数据出错", e.getMessage(), e);
		}
		return countVal;
	}
}
