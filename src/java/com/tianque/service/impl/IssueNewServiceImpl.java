package com.tianque.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.baseInfo.dangerousGoodsPractitioner.service.DangerousGoodsPractitionerService;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.druggy.service.DruggyService;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.service.MentalPatientService;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SuperiorVisitService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HistoricalIssueDao;
import com.tianque.dao.IssueAttachFileDao;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueNewDao;
import com.tianque.dao.IssueTypeDao;
import com.tianque.dao.OverTimeIssueLogDao;
import com.tianque.dao.PublicltyCassDao;
import com.tianque.domain.Enterprise;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Hospital;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.School;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.util.GisCountTypeMapUtil;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.EnterpriseService;
import com.tianque.service.HospitalService;
import com.tianque.service.IssueNewService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.OtherLocaleService;
import com.tianque.service.SchoolService;
import com.tianque.state.IssueDealType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.keyGenerator.KeyGenerator;
import com.tianque.working.service.WorkDiaryService;

/**
 * 服务办事
 */
@SuppressWarnings("deprecation")
@Service("issueNewService")
@Transactional
public class IssueNewServiceImpl extends AbstractBaseService implements
		IssueNewService, IssueConstants {
	@Autowired
	private IssueAttachFileDao issueAttachFileDao;
	@Autowired
	private IssueNewDao issueNewDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueBusinessDelegate issueBusinessDelegate;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private SuperiorVisitService superiorVisitService;
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
	private PublicltyCassDao publicltyCassDao;
	@Autowired
	private HistoricalIssueDao historicalIssueDao;
	@Autowired
	private IssueTypeDao issueTypeDao;
	@Autowired
	private IssueLogDaoNew issueLogDao;
	@Autowired
	private OverTimeIssueLogDao overTimeIssueLogDao;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private KeyGenerator issueSerial;

	@Override
	public IssueNew getSimpleIssueById(Long id) {
		return issueNewDao.getSimpleIssueById(id);
	}

	@Override
	public IssueStep getSimpleIssueByIssueId(Long issueId) {
		return issueNewDao.getSimpleIssueByIssueId(issueId);
	}

	@Override
	public IssueViewObject addIssue(IssueNew issueNew, String[] files,
			Map<String, String> map, String issueMode) {
		if (files != null) {
			if (files.length > 5)
				throw new BusinessValidationException();
		}
		if (!StringUtil.isStringAvaliable(issueNew.getSerialNumber())) {
			issueNew.setSerialNumber(createNewSerialNumber(issueNew
					.getOccurOrg()));
		}
		if (!validate(issueNew))
			throw new BusinessValidationException();
		autoFillOccurOrgInternalCode(issueNew);
		autoFillCreateOrgInternalCode(issueNew);
		if (issueNew.getSourceKind() == null) {
			autoFillCreateSourceKind(issueNew);
		}
		IssueNew saveIssueNew = issueNewDao.addIssue(issueNew);
		addEmphases(saveIssueNew.getId(), map, saveIssueNew);
		// if (issueNew.getIssueTypes() != null
		// && issueNew.getIssueTypes().size() > 0) {
		// for (IssueType issueType : issueNew.getIssueTypes()) {
		// issueNewDao.addIssueHasTypes(saveIssueNew.getId(), issueType
		// .getId(), issueType.getIssueTypeDomain().getId());
		// }
		// }
		try {
			saveIssueAttachFiles(saveIssueNew, null, files);
		} catch (Exception e) {
			throw new BusinessValidationException(e.getMessage());
		}
		IssueStep step = issueBusinessDelegate.register(saveIssueNew);
		IssueViewObject issueViewObject = createIssueViewObject(saveIssueNew,
				step);
		IssueLogNew log = new IssueLogNew();
		// log.setContent(content);
		log.setDealDescription("新增");
		log.setDealOrg(ThreadVariable.getSession().getOrganization());
		log.setDealTime(step.getEntryDate());
		log.setDealUserName(ThreadVariable.getUser().getName());
		log.setIssue(saveIssueNew);
		log.setMobile(ThreadVariable.getUser().getMobile());
		log.setCreateUser(ThreadVariable.getUser().getUserName());
		log.setCreateDate(CalendarUtil.now());
		issueBusinessDelegate.addIssueLog(log);
		// log.

		String content = workDiaryService.assemblingContent(
				saveIssueNew.getSubject(),
				IssueDealType.toString(IssueDealType.Add.intValue()),
				saveIssueNew.getIssueContent(), WorkDiaryTypes.ISSUE_DEAL, "",
				"");
		workDiaryService.addWorkDiary(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.WORKDIARY_DIARY_TYPE,
						WorkDiaryTypes.ISSUE_DEAL),
				WorkDiaryTypes.TYPE_ISSUENEW, saveIssueNew.getId(), content,
				saveIssueNew.getOccurLocation(), getUser().getName(),
				CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		if (issueMode != null && "complete".equals(issueMode)) {
			this.backUps(saveIssueNew, step, log);
		} else if (issueMode != null && "submit".equals(issueMode)) {
			log.setTargeOrg(organizationDubboService.getSimpleOrgById(
					ThreadVariable.getSession().getOrganization().getId())
					.getParentOrg());
			this.submit(saveIssueNew, step, log, null, null);
		}
		return issueViewObject;
	}

	private String createNewSerialNumber(Organization org) {
		return issueSerial.getNextKey(getCurrentIssuePrefix(org));
	}

	private String getCurrentIssuePrefix(Organization org) {
		Organization district = organizationDubboService
				.getDistrictOrganizationId(org.getId());
		return CalendarUtil.format("yyMMdd", new Date())
				+ district.getDepartmentNo();
	}

	private void autoFillCreateSourceKind(IssueNew issueNew) {
		PropertyDict manul = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.SOURCE_KIND, MANUAL_INPUT);
		issueNew.setSourceKind(manul);
	}

	private User getUser() {
		return permissionService.getSimpleUserById(ThreadVariable.getSession()
				.getUserId());
	}

	private void addEmphases(Long issueId, Map<String, String> map,
			IssueNew issueNew) {
		issueTypeService.deleteRelatePersons(issueId);
		issueTypeService.deleteRelatePlaces(issueId);
		if (map == null)
			return;
		for (int i = 0; i < map.size(); i++) {
			if (map.get("SUPERIOR_VISIT") != null
					&& !map.get("SUPERIOR_VISIT").equals("")) {
				String[] key = map.get("SUPERIOR_VISIT").split(",");
				for (int j = 0; j < key.length; j++) {
					SuperiorVisit superiorVisit = superiorVisitService
							.getSuperiorVisitById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId,
							"SUPERIOR_VISIT", superiorVisit.getId(),
							superiorVisit.getName());
				}
				map.remove("SUPERIOR_VISIT");
			}

			if (map.get("RECTIFICATIVEPERSON") != null
					&& !map.get("RECTIFICATIVEPERSON").equals("")) {
				String[] key = map.get("RECTIFICATIVEPERSON").split(",");
				for (int j = 0; j < key.length; j++) {
					RectificativePerson superiorVisit = rectificativePersonService
							.getRectificativePersonById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId,
							"RECTIFICATIVEPERSON", superiorVisit.getId(),
							superiorVisit.getName());
				}
				map.remove("RECTIFICATIVEPERSON");
			}

			if (map.get("POSITIVE_INFO") != null
					&& !map.get("POSITIVE_INFO").equals("")) {
				String[] key = map.get("POSITIVE_INFO").split(",");
				for (int j = 0; j < key.length; j++) {
					PositiveInfo superiorVisit = posiviteInfoService
							.getPositiveInfoById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "POSITIVE_INFO",
							superiorVisit.getId(), superiorVisit.getName());
				}
				map.remove("POSITIVE_INFO");
			}

			if (map.get("MENTAL_PATIENT") != null
					&& !map.get("MENTAL_PATIENT").equals("")) {
				String[] key = map.get("MENTAL_PATIENT").split(",");
				for (int j = 0; j < key.length; j++) {
					MentalPatient mentalPatient = mentalPatientService
							.getMentalPatientById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId,
							"MENTAL_PATIENT", mentalPatient.getId(),
							mentalPatient.getName());
				}
				map.remove("MENTAL_PATIENT");
			}

			if (map.get("IDLE_YOUTH") != null
					&& !map.get("IDLE_YOUTH").equals("")) {
				String[] key = map.get("IDLE_YOUTH").split(",");
				for (int j = 0; j < key.length; j++) {
					IdleYouth mentalPatient = idleYouthService
							.getIdleYouthById(Long.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "IDLE_YOUTH",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("IDLE_YOUTH");
			}

			if (map.get("DRUGGY") != null && !map.get("DRUGGY").equals("")) {
				String[] key = map.get("DRUGGY").split(",");
				for (int j = 0; j < key.length; j++) {
					Druggy mentalPatient = druggyService.getDruggyById(Long
							.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId, "DRUGGY",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("DRUGGY");
			}

			if (map.get("DANGEROUS_GOODS_PRACTITIONER") != null
					&& !map.get("DANGEROUS_GOODS_PRACTITIONER").equals("")) {
				String[] key = map.get("DANGEROUS_GOODS_PRACTITIONER").split(
						",");
				for (int j = 0; j < key.length; j++) {
					DangerousGoodsPractitioner mentalPatient = dangerousGoodsPractitionerService
							.getSimpleDangerousGoodsPractitionerById(Long
									.parseLong(key[j]));
					issueTypeService.addRelatePersons(issueId,
							"DANGEROUS_GOODS_PRACTITIONER",
							mentalPatient.getId(), mentalPatient.getName());
				}
				map.remove("DANGEROUS_GOODS_PRACTITIONER");
			}

			updateMapWhenEnterpeise(map, issueId, issueNew,
					"SAFETYPRODUCTIONKEY");
			updateMapWhenEnterpeise(map, issueId, issueNew, "FIRESAFETYKEY");
			updateMapWhenEnterpeise(map, issueId, issueNew, "SECURITYKEY");

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

			if (map.get("OTHER_LOCALE") != null
					&& !map.get("OTHER_LOCALE").equals("")) {
				String[] key = map.get("OTHER_LOCALE").split(",");
				for (int j = 0; j < key.length; j++) {
					OtherLocale mentalPatient = otherLocaleService
							.getOtherLocaleById(Long.parseLong(key[j]));
					issueTypeService.addRelatePlaces(issueId, "OTHER_LOCALE",
							mentalPatient.getId(), mentalPatient.getName());
					issueNew.setOccurLocation(mentalPatient.getAddress());
					issueNewDao.updateIssue(issueNew);
				}
				map.remove("OTHER_LOCALE");
			}

		}
	}

	private void updateMapWhenEnterpeise(Map<String, String> map, Long issueId,
			IssueNew issueNew, String enerpriseKey) {
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

	private void saveIssueAttachFiles(IssueNew issue, IssueLogNew log,
			String[] files) throws Exception {
		if (files == null || files.length == 0)
			return;
		for (String fileName : files) {
			StoredFile file = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.ISSUE_ATTACHFILE);
			String fileActualPath = file.getStoredFilePath()
					+ File.separatorChar + file.getStoredFileName();
			IssueAttachFile attachFile = new IssueAttachFile();
			attachFile.setFileActualUrl(fileActualPath);
			attachFile.setFileName(fileName);
			attachFile.setIssue(issue);
			attachFile.setIssueLog(log);
			issueAttachFileDao.addIssueAttachFile(attachFile);
		}

	}

	@Override
	public void deleteIssueById(Long id) {

		List<IssueAttachFile> issueAttachFileList = issueAttachFileDao
				.findIssueAttachFilesByIssueId(id);
		String webRootPath = FileUtil.getWebRoot();
		if (issueAttachFileList != null) {
			for (int i = 0; i < issueAttachFileList.size(); i++) {
				IssueAttachFile issueAttachFile = (IssueAttachFile) issueAttachFileList
						.get(i);
				File file = new File(webRootPath + "/"
						+ issueAttachFile.getFileActualUrl());
				if (file.exists())
					file.delete();
			}
		}
		issueAttachFileDao.deleteAttachFilesByIssueId(id);
		// regradedPointsDao.deleteDeductPointsByIssueId(id);
		// deductPointsDao.deleteDeductPointsByIssueId(id);
		issueNewDao.deleteIssueHasTypesByIssueId(id);
		overTimeIssueLogDao.deleteOverTimeIssueLogsByIssueId(id);
		publicltyCassDao.deletePublicltyCassByIssueId(id);
		historicalIssueDao.deleteHistoricalIssueByIssueId(id);
		issueTypeDao.deleteRelatePersons(id);
		issueTypeDao.deleteRelatePlaces(id);
		issueLogDao.deleteIssueLogByIssueId(id);
		issueNewDao.deleteIssueById(id);
	}

	@Override
	public IssueNew getFullIssueById(Long id) {
		return issueNewDao.getFullIssueById(id);
	}

	@Override
	public IssueNew getFullIssueByIssueStepId(Long id) {
		return issueNewDao.getFullIssueByIssueStepId(id);
	}

	@Override
	public IssueViewObject updateIssue(IssueNew issueNew, Long stepId,
			Map<String, String> map, String[] attachFiles) {
		if (attachFiles != null) {
			if (attachFiles.length > 5)
				throw new BusinessValidationException();
		}
		if (!validate(issueNew))
			throw new BusinessValidationException();
		IssueNew fullIssueNew = issueNewDao.getFullIssueById(issueNew.getId());
		processIssueTypes(fullIssueNew, issueNew);
		autoFillOccurOrgInternalCode(issueNew);

		issueNew.setCurrentOrgDisplayName(fullIssueNew
				.getCurrentOrgDisplayName());
		issueNew.setLastUsername(fullIssueNew.getLastUsername());

		IssueNew updateIssueNew = issueNewDao.updateIssue(issueNew);
		IssueStep step = issueBusinessDelegate.getIssueStepById(stepId);
		IssueViewObject issueViewObject = createIssueViewObject(updateIssueNew,
				step);
		addEmphases(updateIssueNew.getId(), map, updateIssueNew);
		updateAttachFiles(issueNew, null, attachFiles);
		issueBusinessDelegate.sendPersonelMessageOnCreateIssue(issueNew);

		String content = workDiaryService.assemblingContent(
				issueNew.getSubject(),
				IssueDealType.toString(IssueDealType.Add.intValue()),
				issueNew.getIssueContent(), WorkDiaryTypes.ISSUE_DEAL, "", "");
		workDiaryService.updateWorkDiary(WorkDiaryTypes.TYPE_ISSUENEW,
				issueNew.getId(), content, issueNew.getOccurLocation(),
				getUser().getName(), CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));

		return issueViewObject;
	}

	private void updateAttachFiles(IssueNew issue, IssueLogNew issueLog,
			String[] files) {
		List<IssueAttachFile> existAttachFiles = issueAttachFileDao
				.findIssueAttachFilesByIssueId(issue.getId());

		List<String> existAttachFileNames = new ArrayList<String>();
		for (IssueAttachFile existAttachFile : existAttachFiles) {
			existAttachFileNames.add(existAttachFile.getFileName());
		}
		if (files != null) {
			saveNewIssueAttachFilesForUpdate(issue, issueLog, files,
					existAttachFileNames);
		}

		deleteMarginAttachFileNames(files, existAttachFiles,
				existAttachFileNames);
	}

	private void deleteMarginAttachFileNames(String[] files,
			List<IssueAttachFile> existAttachFiles,
			List<String> existAttachFileNames) {
		if (files != null) {
			existAttachFileNames.removeAll(Arrays.asList(files));
		}
		String webRootPath = FileUtil.getWebRoot();
		for (String existAttachFileName : existAttachFileNames) {
			for (IssueAttachFile existAttachFile : existAttachFiles) {
				if (existAttachFileName.equals(existAttachFile.getFileName())) {
					File file = new File(webRootPath + "/"
							+ existAttachFile.getFileActualUrl());
					if (file.exists())
						file.delete();
					issueAttachFileDao.deleteAttachFileById(existAttachFile
							.getId());
				}
			}
		}
	}

	private void saveNewIssueAttachFilesForUpdate(IssueNew issue,
			IssueLogNew issueLog, String[] files,
			List<String> existAttachFileNames) {

		List<String> newAttachFileNames = new ArrayList<String>();
		if (files != null) {
			for (String fileName : files) {
				newAttachFileNames.add(fileName);
			}

			newAttachFileNames.removeAll(existAttachFileNames);
			files = newAttachFileNames.toArray(new String[newAttachFileNames
					.size()]);
			try {
				saveIssueAttachFiles(issue, issueLog, files);
			} catch (Exception e) {
				throw new BusinessValidationException(e.getMessage());
			}
		}
	}

	private boolean validate(IssueNew issueNew) {
		if (issueNew == null)
			return false;
		if (issueNew.getSerialNumber() == null)
			return false;
		if (issueNew.getOccurOrg() == null
				|| issueNew.getOccurOrg().getId() == null)
			return false;
		if (issueNew.getSubject() == null
				|| "".equals(issueNew.getSubject().trim()))
			return false;
		if (issueNew.getIssueKind() == null
				|| issueNew.getIssueKind().getId() == null)
			return false;
		if (issueNew.getOccurDate() == null)
			return false;
		return true;
	}

	private void autoFillOccurOrgInternalCode(IssueNew issueNew) {
		Organization occurOrg = organizationDubboService
				.getSimpleOrgById(issueNew.getOccurOrg().getId());
		if (occurOrg == null) {
			throw new BusinessValidationException("找不到指定的发生网格");
		} else {
			issueNew.setOccurOrgInternalCode(occurOrg.getOrgInternalCode());
		}
	}

	private void autoFillCreateOrgInternalCode(IssueNew issueNew) {
		if (issueNew.getCreateOrg() == null) {
			issueNew.setCreateOrg(ThreadVariable.getSession().getOrganization());
		}
		Organization createOrg = organizationDubboService
				.getSimpleOrgById(issueNew.getCreateOrg().getId());
		if (createOrg == null) {
			throw new BusinessValidationException("找不到指定的创建网格");
		} else {
			issueNew.setCreateOrgInternalCode(createOrg.getOrgInternalCode());
		}
	}

	/**
	 * 对IssueType进行更新 更新方法是才用交集原则 就是保持两个集合的交集不变 在交集之外的删除旧的，增加新的
	 * 
	 * @param savedIssueNew旧集合
	 * @param postIssueNew新集合
	 */
	private void processIssueTypes(IssueNew savedIssueNew, IssueNew postIssueNew) {
		// processSavedIssueTypes(savedIssueNew.getId(),
		// savedIssueNew.getIssueTypes(), postIssueNew.getIssueTypes());
		// processPostIssueTypes(savedIssueNew.getId(),
		// savedIssueNew.getIssueTypes(), postIssueNew.getIssueTypes());
	}

	/**
	 * 处理旧集合 对旧集合只有删除操作，
	 * 
	 * @param savedIssueTypes
	 * @param postIssueTypes
	 */
	private void processSavedIssueTypes(Long issueId,
			List<IssueType> savedIssueTypes, List<IssueType> postIssueTypes) {
		if (savedIssueTypes == null || savedIssueTypes.size() == 0)
			return;
		if (postIssueTypes == null || postIssueTypes.size() == 0)
			issueNewDao.deleteIssueHasTypesByIssueId(issueId);
		else {
			for (IssueType issueType : savedIssueTypes) {
				if (!postIssueTypes.contains(issueType))
					issueNewDao.deleteIssueHasTypesByIssueIdAndIssueTypeId(
							issueId, issueType.getId());
			}
		}
	}

	/**
	 * 处理新集合,对新集合只有新增操作.
	 * 
	 * @param savedIssueTypes
	 * @param postIssueTypes
	 */
	private void processPostIssueTypes(Long issueId,
			List<IssueType> savedIssueTypes, List<IssueType> postIssueTypes) {
		if (postIssueTypes == null || postIssueTypes.size() == 0)
			return;
		if (savedIssueTypes == null || savedIssueTypes.size() == 0) {
			for (IssueType issueType : postIssueTypes)
				issueNewDao.addIssueHasTypes(issueId, issueType.getId(),
						issueType.getIssueTypeDomain().getId());
		} else {
			for (IssueType issueType : postIssueTypes) {
				if (!savedIssueTypes.contains(issueType))
					issueNewDao.addIssueHasTypes(issueId, issueType.getId(),
							issueType.getIssueTypeDomain().getId());
			}
		}
	}

	@Override
	public PageInfo<IssueViewObject> findMyNeedDoForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows,
			String sidx, String sord) {
		return issueNewDao.findMyNeedDoForPageByTargeOrgIdAndDealState(
				targeOrgId, dealState, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<IssueViewObject> findMyJurisdictionsNeedDoForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord) {
		return issueNewDao
				.findMyJurisdictionsNeedDoForPageByTargeOrgInternalCodeAndDealState(
						targeOrgId, targeOrgInternalCode, dealState, page,
						rows, sidx, sord);
	}

	@Override
	public PageInfo<IssueViewObject> findMyDoneForPageByTargeOrgIdAndDealState(
			Long targeOrgId, List<Long> dealStateList, Integer page,
			Integer rows, String sidx, String sord) {
		return issueNewDao.findMyDoneForPageByTargeOrgIdAndDealState(
				targeOrgId, dealStateList, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<IssueViewObject> findMyJurisdictionsDoneForPageByTargeOrgInternalCodeAndDealState(
			Long targeOrgId, String targeOrgInternalCode, Long dealState,
			Integer page, Integer rows, String sidx, String sord) {
		return issueNewDao
				.findMyJurisdictionsDoneForPageByTargeOrgInternalCodeAndDealState(
						targeOrgId, targeOrgInternalCode, dealState, page,
						rows, sidx, sord);
	}

	@Override
	public List<IssueAttachFile> findIssueAttachFilesById(Long issueId) {
		return issueAttachFileDao.findIssueAttachFilesByIssueId(issueId);
	}

	@Override
	public IssueViewObject getIssueViewObjectById(Long id) {
		IssueViewObject issueViewObject = issueNewDao
				.getViewIssueViewObjectById(id);
		issueViewObject.setOccurOrg(organizationDubboService
				.getSimpleOrgById(issueViewObject.getOccurOrg().getId()));
		return issueViewObject;
	}

	private IssueViewObject createIssueViewObject(IssueNew saveIssueNew,
			IssueStep step) {
		IssueViewObject issueViewObject = new IssueViewObject();
		issueViewObject.setIssueId(saveIssueNew.getId());
		issueViewObject.setSerialNumber(saveIssueNew.getSerialNumber());
		issueViewObject.setSubject(saveIssueNew.getSubject());
		issueViewObject.setStatus(saveIssueNew.getStatus());
		issueViewObject.setOccurDate(saveIssueNew.getOccurDate());
		// issueViewObject.setCurrentOrgDisplayName(saveIssueNew
		// .getCurrentOrgDisplayName());
		issueViewObject.setOccurOrg(saveIssueNew.getOccurOrg());
		issueViewObject.setDealTime(step.getLastDealDate());
		issueViewObject.setSourcePerson(saveIssueNew.getSourcePerson());
		issueViewObject.setSourceKind(saveIssueNew.getSourceKind());
		issueViewObject.setSourceMobile(saveIssueNew.getSourceMobile());
		issueViewObject.setIssueLogId(step.getId());
		// issueViewObject.setSupervisionState(step.getSuperviseLevel());
		// issueViewObject.setUrgent(saveIssueNew.getUrgent());
		issueViewObject.setTargeOrg(step.getTarget());
		return issueViewObject;
	}

	@SuppressWarnings("unused")
	private void createIssueViewObject(IssueNew saveIssueNew,
			IssueLogNew issueLogNew, IssueViewObject issueViewObject) {
		issueViewObject.setIssueId(saveIssueNew.getId());
		issueViewObject.setSerialNumber(saveIssueNew.getSerialNumber());
		issueViewObject.setSubject(saveIssueNew.getSubject());
		issueViewObject.setStatus(saveIssueNew.getStatus());
		issueViewObject.setOccurDate(saveIssueNew.getOccurDate());
		// issueViewObject.setCurrentOrgDisplayName(saveIssueNew
		// .getCurrentOrgDisplayName());
		issueViewObject.setOccurOrg(saveIssueNew.getOccurOrg());
		issueViewObject.setDealTime(issueLogNew.getDealTime());
		issueViewObject.setSourcePerson(saveIssueNew.getSourcePerson());
		issueViewObject.setSourceKind(saveIssueNew.getSourceKind());
		issueViewObject.setSourceMobile(saveIssueNew.getSourceMobile());
		issueViewObject.setIssueLogId(issueLogNew.getId());
		// issueViewObject.setSupervisionState(issueLogNew.getSupervisionState());
		issueViewObject.setUrgent(saveIssueNew.getUrgent());
	}

	@Override
	public PageInfo<IssueViewObject> findSuperviseIssueList(String orgCode,
			Long dealState1, Long dealState2, Integer page, Integer rows,
			String sidx, String sord) {
		return issueNewDao.findSuperviseIssueList(orgCode, dealState1,
				dealState2, page, rows, sidx, sord);
	}

	@Override
	public Integer getMyNeedDoForByTargeOrgIdAndDealState(Long targeOrgId,
			Long dealState) {
		return issueNewDao.getMyNeedDoForByTargeOrgIdAndDealState(targeOrgId,
				dealState);
	}

	@Override
	public List<IssueOperate> getCurrentLoginedOrgCanDo(IssueNew issue,
			Organization org) {
		return issueBusinessDelegate.getCurrentLoginedOrgCanDo(issue, org);
	}

	@Override
	public IssueStep getIssueStepById(Long stepId) {
		return issueBusinessDelegate.getIssueStepById(stepId);
	}

	private IssueStep getFullIssueStepById(Long stepId) {
		return issueBusinessDelegate.getFullIssueStepById(stepId);
	}

	@Override
	public Integer getNeedDoCount(Long orgId) {
		return issueNewDao.getNeedDoCount(orgId);
	}

	private String translateIssueOperate(IssueOperate operate,
			Organization org, List<Organization> tellOrgs) {
		String result = operate.getDesc() + " 给 " + org.getOrgName();
		if (tellOrgs != null && tellOrgs.size() > 0) {
			result = result + " 并 抄告 给 ";
			for (int index = 0; index < tellOrgs.size(); index++) {
				if (index == tellOrgs.size() - 1) {
					result = result + tellOrgs.get(index).getOrgName();
				} else {
					result = result + tellOrgs.get(index).getOrgName() + ",";
				}
			}
		}
		return result;
	}

	private List<Organization> convertToOrgList(List<Long> orgs) {
		List<Organization> result = new ArrayList<Organization>();
		if (orgs != null && orgs.size() > 0) {
			for (Long id : orgs) {
				result.add(organizationDubboService.getFullOrgById(id));
			}
		}
		return result;
	}

	private IssueLogNew appendOperationLog(IssueNew issue, IssueLogNew log,
			String desc, IssueOperate operate) {
		if (log.getTargeOrg() != null) {
			log.setTargeOrg(organizationDubboService.getSimpleOrgById(log
					.getTargeOrg().getId()));
		}
		if (null != operate) {
			log.setDealType(Long.valueOf(operate.getCode()));
		}
		log.setDealDescription(desc);
		log.setDealOrg(ThreadVariable.getSession().getOrganization());
		log.setDealTime(CalendarUtil.now());
		log.setDealUserName(ThreadVariable.getUser().getName());
		log.setIssue(issue);
		log.setMobile(ThreadVariable.getUser().getMobile());
		log.setCreateUser(ThreadVariable.getUser().getUserName());
		log.setCreateDate(CalendarUtil.now());
		log = issueBusinessDelegate.addIssueLog(log);
		if (log.getTargeOrg() != null) {
			log.setTargeOrg(organizationDubboService.getFullOrgById(log
					.getTargeOrg().getId()));
		}
		return log;
	}

	@Override
	public IssueViewObject reportToCommandCenter(IssueNew issue,
			IssueStep step, IssueLogNew log) {
		// log.setContent(content);
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		step = issueBusinessDelegate.reportTo(issue, step);
		log.setTargeOrg(step.getTarget());
		appendOperationLog(issue, log, IssueOperate.REPORT_TO.getDesc(),
				IssueOperate.REPORT_TO);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject giveTo(IssueNew issue, IssueStep step,
			IssueLogNew log, List<Long> tellOrgs, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		List<Organization> tells = convertToOrgList(tellOrgs);
		log.setTargeOrg(organizationDubboService.getSimpleOrgById(log
				.getTargeOrg().getId()));
		log = appendOperationLog(
				issue,
				log,
				translateIssueOperate(IssueOperate.GIVETO, log.getTargeOrg(),
						tells), IssueOperate.GIVETO);
		step = issueBusinessDelegate.giveTo(issue, step, log, tells,
				attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject concept(IssueNew issue, IssueStep step,
			IssueLogNew log) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		step = issueBusinessDelegate.concept(issue, step);
		log.setTargeOrg(step.getTarget());
		log = appendOperationLog(issue, log, IssueOperate.CONCEPT.getDesc(),
				IssueOperate.CONCEPT);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject comment(IssueNew issue, IssueStep step,
			IssueLogNew log, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		log = appendOperationLog(issue, log, IssueOperate.COMMENT.getDesc(),
				IssueOperate.COMMENT);
		step = issueBusinessDelegate.comment(issue, step, log, attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject complete(IssueNew issue, IssueStep step,
			IssueLogNew log, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		log = appendOperationLog(issue, log, IssueOperate.COMPLETE.getDesc(),
				IssueOperate.COMPLETE);
		step = issueBusinessDelegate.complete(issue, step, log, attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject backUps(IssueNew issue, IssueStep step,
			IssueLogNew log) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		log = appendOperationLog(issue, log, IssueOperate.BACKUPS.getDesc(),
				IssueOperate.BACKUPS);
		step = issueBusinessDelegate.backUps(issue, step, log);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject assign(IssueNew issue, IssueStep step,
			IssueLogNew log, List<Long> tellOrgs, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		List<Organization> tells = convertToOrgList(tellOrgs);
		log.setTargeOrg(organizationDubboService.getSimpleOrgById(log
				.getTargeOrg().getId()));
		log = appendOperationLog(
				issue,
				log,
				translateIssueOperate(IssueOperate.ASSIGN, log.getTargeOrg(),
						tells), IssueOperate.ASSIGN);
		step = issueBusinessDelegate.assign(issue, step, log, tells,
				attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject back(IssueNew issue, IssueStep step,
			IssueLogNew log, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		log = appendOperationLog(issue, log, IssueOperate.BACK.getDesc(),
				IssueOperate.BACK);
		step = issueBusinessDelegate.back(issue, step, log, attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject submit(IssueNew issue, IssueStep step,
			IssueLogNew log, List<Long> tellOrgs, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		List<Organization> tells = convertToOrgList(tellOrgs);
		log.setTargeOrg(organizationDubboService.getSimpleOrgById(log
				.getTargeOrg().getId()));
		log = appendOperationLog(
				issue,
				log,
				translateIssueOperate(IssueOperate.SUBMIT, log.getTargeOrg(),
						tells), IssueOperate.SUBMIT);
		step = issueBusinessDelegate.submit(issue, step, log, tells,
				attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject read(IssueNew issue, IssueStep step, IssueLogNew log) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		step = issueBusinessDelegate.read(issue, step);
		log.setTargeOrg(step.getTarget());
		appendOperationLog(issue, log, IssueOperate.READ.getDesc(),
				IssueOperate.READ);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject normalSupervise(IssueStep step, IssueLogNew log) {
		IssueNew issue = getFullIssueByIssueStepId(step.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		step = issueBusinessDelegate.supervise(issue, step,
				IssueOperate.NORMAL_SUPERVISE.getCode());
		appendOperationLog(issue, log, IssueOperate.NORMAL_SUPERVISE.getDesc(),
				IssueOperate.NORMAL_SUPERVISE);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject redSupervise(IssueStep step, IssueLogNew log) {
		IssueNew issue = getFullIssueByIssueStepId(step.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		step = issueBusinessDelegate.supervise(issue, step,
				IssueOperate.RED_SUPERVISE.getCode());
		appendOperationLog(issue, log, IssueOperate.RED_SUPERVISE.getDesc(),
				IssueOperate.RED_SUPERVISE);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject yellowSupervise(IssueStep step, IssueLogNew log) {
		IssueNew issue = getFullIssueByIssueStepId(step.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		step = issueBusinessDelegate.supervise(issue, step,
				IssueOperate.YELLOW_SUPERVISE.getCode());
		appendOperationLog(issue, log, IssueOperate.YELLOW_SUPERVISE.getDesc(),
				IssueOperate.YELLOW_SUPERVISE);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject cancelSupervise(IssueStep step) {
		IssueNew issue = getFullIssueByIssueStepId(step.getId());
		step = getFullIssueStepById(step.getId());
		IssueLogNew log = new IssueLogNew();
		log.setTargeOrg(step.getTarget());
		step = issueBusinessDelegate.cancelSupervise(issue, step);
		appendOperationLog(issue, log, IssueOperate.CANCEL_SUPERVISE.getDesc(),
				IssueOperate.CANCEL_SUPERVISE);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject instruct(IssueStep step, IssueLogNew log) {
		IssueNew issue = getFullIssueByIssueStepId(step.getId());
		step = getFullIssueStepById(step.getId());
		log.setTargeOrg(step.getTarget());
		step = issueBusinessDelegate.instruct(issue, step);
		appendOperationLog(issue, log, IssueOperate.INSTRUCT.getDesc(),
				IssueOperate.INSTRUCT);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueViewObject urgent(IssueNew issue, IssueLogNew log) {
		issue = issueBusinessDelegate.urgent(issue);
		appendOperationLog(issue, log, "加急", null);
		return issueNewDao.getViewIssueViewObjectByIssueId(issue.getId());
	}

	@Override
	public IssueViewObject cancelUrgent(IssueNew issue) {
		issue = issueBusinessDelegate.cancelUrgent(issue);
		appendOperationLog(issue, new IssueLogNew(), "取消加急", null);
		return issueNewDao.getViewIssueViewObjectByIssueId(issue.getId());
	}

	@Override
	public IssueViewObject cancelHistorical(IssueNew issue) {
		issueBusinessDelegate.cancelHistorical(issue);
		appendOperationLog(issue, new IssueLogNew(), "取消历史遗留", null);
		return issueNewDao.getViewIssueViewObjectByIssueId(issue.getId());
	}

	@Override
	public IssueViewObject cancelPubliclty(IssueNew issue) {
		issueBusinessDelegate.cancelPubliclty(issue);
		appendOperationLog(issue, new IssueLogNew(), "取消宣传案例", null);
		return issueNewDao.getViewIssueViewObjectByIssueId(issue.getId());
	}

	@Override
	public IssueViewObject historical(IssueNew issue) {
		issueBusinessDelegate.historical(issue);
		appendOperationLog(issue, new IssueLogNew(), "设置为历史遗留", null);
		return issueNewDao.getViewIssueViewObjectByIssueId(issue.getId());
	}

	@Override
	public IssueViewObject publiclty(IssueNew issue) {
		IssueLogNew issueLogNew = appendOperationLog(issue, new IssueLogNew(),
				"设置为宣传案例", null);
		issueBusinessDelegate.publiclty(issue, issueLogNew);
		return issueNewDao.getViewIssueViewObjectByIssueId(issue.getId());
	}

	@Override
	public IssueViewObject feedBack(IssueNew issue, IssueStep step,
			IssueLogNew log, List<Long> tellOrgs, String[] attachFiles) {
		issue = getFullIssueById(issue.getId());
		step = getFullIssueStepById(step.getId());
		List<Organization> tells = convertToOrgList(tellOrgs);
		log.setTargeOrg(organizationDubboService.getSimpleOrgById(log
				.getTargeOrg().getId()));
		log = appendOperationLog(
				issue,
				log,
				translateIssueOperate(IssueOperate.FEEDBACK, log.getTargeOrg(),
						tells), IssueOperate.FEEDBACK);
		step = issueBusinessDelegate.submit(issue, step, log, tells,
				attachFiles);
		return createIssueViewObject(issue, step);
	}

	@Override
	public IssueNew getFullIssueMobileByIssueId(Long id) {
		return issueNewDao.getFullIssueMobileByIssueStepId(id);
	}

	@Override
	public List<IssueNew> countActualHouseByOrgId(Long orgId) {
		if (null == orgId) {
			return new ArrayList<IssueNew>();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			return issueNewDao.countActualHouseByOrgInternalCode(org
					.getOrgInternalCode());
		}
	}

	@Override
	public PageInfo<IssueNew> issueNewsListSearchByQueryStrAndOrgId(Long orgId,
			String queryStr, Integer page, Integer rows, String sidx,
			String sord) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误，orgId不能为空");
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			return issueNewDao.issueNewsListSearchByQueryStrAndOrgId(
					org.getOrgInternalCode(), queryStr, page, rows, sidx, sord);
		}
	}

	@Override
	public List<IssueNew> getIssueNewsDatialInfoByIssueId(Long orgId,
			Long issueId) {
		if (null == issueId) {
			throw new BusinessValidationException("Gis事件处理详细信息查询时,获取事件Id失败！");
		} else {
			return issueNewDao.getIssueNewsDatialInfoByIssueId(orgId, issueId);
		}
	}

	@Override
	public PageInfo<IssueNew> searchKeyIssueNewsListByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord) {
		if (issueNewsType.equals(GisCountTypeMapUtil.UNCOMPLETEISSUE)
				|| issueNewsType.equals(GisCountTypeMapUtil.COMPLETEISSUE)) {
			return issueNewDao.searchStateKeyIssueNewsListByOrgId(
					orgInternalCode, issueNewsfield, issueNewsType, page, rows,
					sidx, sord);
		} else {
			return issueNewDao.searchTypeKeyIssueNewsListByOrgCode(
					orgInternalCode, issueNewsfield, issueNewsType, page, rows,
					sidx, sord);
		}
	}

	@Override
	public PageInfo<IssueNew> gisIssueNewsFutureSearchByOrgCode(
			String orgInternalCode, Object issueNewsfield,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord) {
		return issueNewDao.gisIssueNewsFutureSearchByOrgCode(orgInternalCode,
				issueNewsfield, issueNewsType, issueNewsState, page, rows,
				sidx, sord);
	}

	@Override
	public IssueNew updateIssueNewsInfoForGis(Long issueId, GisInfo gisInfo) {
		IssueNew issueNew = new IssueNew();
		issueNew.setId(issueId);
		issueNew.setGisInfo(gisInfo);
		return issueNewDao.updateIssueNewsInfoForGis(issueNew);
	}

	@Override
	public IssueNew unBindIssueNewsOnMap(Long issueId) {
		if (null == issueId) {
			throw new BusinessValidationException("事件处理Id为空，请检查！");
		} else {
			return issueNewDao.unBindIssueNewsOnMap(issueId);
		}
	}

	@Override
	public List<IssueNew> findBindingIssueNewsByOrgIdAndType(
			String orgInternalCode, String issueNewsType, Object issueNewsfield) {
		return issueNewDao.findBindingIssueNewsByOrgCodeAndType(
				orgInternalCode, issueNewsType, issueNewsfield,
				IssueState.STEPCOMPLETE_CODE);
	}

	@Override
	public List<IssueNew> findAllBindingIssueNewsByOrgInternalCode(
			String orgInternalCode) {
		return issueNewDao
				.findAllBindingIssueNewsByOrgInternalCode(orgInternalCode);
	}

	@Override
	public IssueNew getIssueBySerialNumber(String serialNumber) {
		return issueNewDao.getIssueBySerialNumber(serialNumber);
	}

	@Override
	public IssueNew getIssueByFromSerialNumber(String fromSerialNumber) {
		return issueNewDao.getIssueByFromSerialNumber(fromSerialNumber);
	}
}
