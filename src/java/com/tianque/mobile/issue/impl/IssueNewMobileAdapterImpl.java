package com.tianque.mobile.issue.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.mode.ManagementMode;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchIssueDao;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.IssuePersonAndSiteType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.domain.vo.SearchBaseInfoPlace;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.issue.IssueNewMobileAdapter;
import com.tianque.mobile.vo.MobileIssueType;
import com.tianque.service.IssueLogService;
import com.tianque.service.IssueNewService;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.solr.domain.KeyPlaceDocument;
import com.tianque.solr.service.SolrKeyPlaceService;
import com.tianque.state.IssueDealState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.keyGenerator.KeyGenerator;
import com.tianque.working.service.WorkDiaryService;

/**
 * 手机端：事件处理
 */
@SuppressWarnings("deprecation")
@Transactional
@Scope("request")
@Controller("issueNewMobileAdapter")
public class IssueNewMobileAdapterImpl extends BaseMobileAction implements
		IssueNewMobileAdapter {
	@Autowired
	private IssueNewService issueService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	public IssueNewService issueNewService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private IssueTypeDomainService issueTypeDomainService;
	@Autowired
	private SearchIssueDao searchIssueDao;
	@Autowired
	private IssueLogService issueLogService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private KeyGenerator issueSerial;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private SolrKeyPlaceService solrKeyPlaceService;
	@Autowired
	private KeyPlaceService placeService;
	@Autowired
	private WorkDiaryService workDiaryService;
	private Long issueId;
	private Long orgId;
	private List<IssueType> contradiction;
	private List<IssueType> specialisation;
	private List<IssueType> peopleliveService;

	private SearchIssueVoNew searchIssueVo;
	private String selContradictionId;
	private String selSpecialisationId;
	private String selPeopleliveServiceId;

	private List<IssueType> safe;
	private List<IssueType> socialOpinion;
	private List<IssueType> policyLaw;
	private List<IssueType> incidentReport;
	private List<IssueType> other;

	private String selSafeId;
	private String selSocialOpinionId;
	private String selPolicyLawId;
	private String selIncidentReportId;
	private String selOtherId;

	private List<IssueType> selSafe;
	private List<IssueType> selSocialOpinion;
	private List<IssueType> selPolicyLaw;
	private List<IssueType> selIncidentReport;
	private List<IssueType> selOther;

	private List<Long> selOtherTypeId;
	private IssueLogNew issueLogNew;
	private IssueNew issueNew;
	private Organization organization;
	private List<IssueType> otherType;
	private List<IssueType> selContradiction;
	private List<IssueType> selSpecialisation;
	private List<IssueType> selPeopleliveService;
	private List<IssueType> selOtherType;
	private String selContradictionString;
	private String selSpecialisationString;
	private String selPeopleliveServiceString;
	private String selOtherTypeString;
	private String selContradictionTxt;
	private String selSpecialisationTxt;
	private String selPeopleliveServiceTxt;
	private String selOtherTypeTxt;
	private Map<String, List> relatePlaces = new HashMap<String, List>();
	private Map<String, List> relatePersons = new HashMap<String, List>();
	private List<IssueAttachFile> issueAttachFiles = new ArrayList<IssueAttachFile>();
	private List<IssueLogNew> issueLogs;
	private Map<String, List> issueLogAttachFilesMap = new HashMap<String, List>();
	private String managementMode;
	private List<IssueOperate> canDoList;
	private List<IssueOperate> IssueActions;
	private Map<String, Object> jsonMap;
	// 涉及主要当人
	private String involvedPersonnel;
	// 附件
	private String[] attachFiles;
	// 是否重点场所
	private Boolean isInvolvedPlace;
	// 涉及重点场所
	private String involvedPlace;
	private Map<String, String> emphas = new HashMap<String, String>();
	private IssueViewObject issueViewObject;
	private String name;
	private String idCardNo;
	private List<Countrymen> countrymenList;
	private String placeName;
	private String separator;
	private List<Map<String, List<MobileIssueType>>> mobileIssueTypeList;
	private Long stepId;
	private IssueLogNew issueLog;
	private IssueOperate operate;
	private int dealType = -1;
	private boolean transferTo = false;
	private String tellOrgIds;
	private File uploadFile;
	private String uploadFileFileName;

	/*
	 * 下辖事件-获取领导批示所需信息
	 */
	@Override
	public String getIssueAction() throws Exception {
		jsonMap = new HashMap<String, Object>();
		IssueActions = new ArrayList<IssueOperate>();
		IssueActions.add(IssueOperate.parse(IssueOperate.INSTRUCT_CODE));
		IssueActions
				.add(IssueOperate.parse(IssueOperate.NORMAL_SUPERVISE_CODE));
		IssueActions
				.add(IssueOperate.parse(IssueOperate.YELLOW_SUPERVISE_CODE));
		IssueActions.add(IssueOperate.parse(IssueOperate.RED_SUPERVISE_CODE));
		IssueActions
				.add(IssueOperate.parse(IssueOperate.CANCEL_SUPERVISE_CODE));
		IssueActions.add(IssueOperate.parse(IssueOperate.URGENT_CODE));
		IssueActions.add(IssueOperate.parse(IssueOperate.CANCEL_URGENT_CODE));
		jsonMap.put("IssueActions", IssueActions);
		return IssueActions.size() > 0 ? "success" : "simpleDeal";
	}

	@Override
	public String searchDoneIssues() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		if (null == searchIssueVo) {
			searchIssueVo = new SearchIssueVoNew();
			searchIssueVo.setTargeOrgId(orgId);
		} else {
			searchIssueVo.setTargeOrgId(orgId);
		}
		preparePageData();
		searchIssueVo.setDealState(Long.parseLong(IssueState.STEPCOMPLETE_CODE
				+ ""));
		searchIssueVo.setIssueTypes(transToIssueType());

		PageInfo<IssueViewObject> pageInfo = proccessRelativeOrgNameByPageInfo(
				searchIssueDao.searchDoneIssues(searchIssueVo, page, rows,
						sidx, sord), organizationDubboService);
		for (IssueViewObject issueViewObject : pageInfo.getResult()) {
			if (issueViewObject.getDealState() != null
					&& issueViewObject.getDealState().equals(
							Long.parseLong("" + IssueState.STEPCOMPLETE_CODE))) {
				issueViewObject.setDealState(Long.parseLong(""
						+ IssueState.DEALING_CODE));
			}
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String enclosureUpload() throws Exception {
		InputStream inputStream = new FileInputStream(uploadFile);
		copyFile(inputStream);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		writer.print("{success: true}");
		writer.flush();
		writer.close();
		return SUCCESS;
	}

	private void copyFile(InputStream inputStream) throws Exception,
			FileNotFoundException, IOException {
		File file = FileUtil.createTmpStoreFile(uploadFileFileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		IOUtils.copy(inputStream, fileOutputStream);
		fileOutputStream.close();
		inputStream.close();
	}

	public String dealIssue() throws Exception {
		if (dealType == -1 || dealType == 0) {
			dealType = IssueOperate.CONCEPT_CODE;
		}
		operate = IssueOperate.parse(dealType);
		if (!validateDealInput()) {
			return MOBILE_ERROR;
		}
		IssueNew issue = issueNewService.getFullIssueByIssueStepId(stepId);
		IssueStep step = issueNewService.getIssueStepById(stepId);
		if (IssueOperate.REPORT_TO.equals(operate)) {
			issueViewObject = issueNewService.reportToCommandCenter(issue,
					step, issueLog);
		} else if (IssueOperate.GIVETO.equals(operate)) {
			issueViewObject = issueNewService.giveTo(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		} else if (IssueOperate.CONCEPT.equals(operate)) {
			issueViewObject = issueNewService.concept(issue, step, issueLog);
		} else if (IssueOperate.READ.equals(operate)) {
			issueViewObject = issueNewService.read(issue, step, issueLog);
		} else if (IssueOperate.COMMENT.equals(operate)) {
			issueViewObject = issueNewService.comment(issue, step, issueLog,
					attachFiles);
		} else if (IssueOperate.COMPLETE.equals(operate)) {
			issueViewObject = issueNewService.complete(issue, step, issueLog,
					attachFiles);
		} else if (IssueOperate.ASSIGN.equals(operate)) {
			issueViewObject = issueNewService.assign(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		} else if (IssueOperate.SUBMIT.equals(operate)) {
			issueViewObject = issueNewService.submit(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		} else if (IssueOperate.BACK.equals(operate)) {
			issueViewObject = issueNewService.back(issue, step, issueLog,
					attachFiles);
		} else if (IssueOperate.FEEDBACK.equals(operate)) {
			issueViewObject = issueNewService.feedBack(issue, step, issueLog,
					conversionStringsToLongList(tellOrgIds), attachFiles);
		}
		if (mustInputDealContent()) {
			String content = workDiaryService.assemblingContent(
					issue.getSubject(), operate.toString(),
					issueLog.getContent(), WorkDiaryTypes.ISSUE_DEAL, "", "");
			workDiaryService.addWorkDiary(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.WORKDIARY_DIARY_TYPE,
							WorkDiaryTypes.ISSUE_DEAL),
					WorkDiaryTypes.TYPE_ISSUEBUSINESS, issue.getId(), content,
					issue.getOccurLocation(), issueLog.getDealUserName(),
					CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		}
		return SUCCESS;
	}

	private List<Long> conversionStringsToLongList(String ids) {
		List<Long> list = null;
		if (ids != null) {
			list = new ArrayList<Long>();
			String[] strs = ids.split(",");
			if (strs.length < 0) {
				return null;
			}

			for (String str : strs) {
				if (StringUtil.isStringAvaliable(str.trim())) {
					list.add(Long.valueOf(str.trim()));
				}
			}
		}
		return list;
	}

	private boolean validateDealInput() {
		boolean result = true;
		if (issueLog.getDealOrg() == null
				|| issueLog.getDealOrg().getId() == null || operate == null) {
			appendErrorMessage("参数不正确!");
			result = false;
		}
		if (!StringUtil.isStringAvaliable(issueLog.getDealUserName())) {
			appendErrorMessage("处理人不能为空!");
			result = false;
		}
		if (mustInputDealContent() && issueLog.getContent() == null) {
			appendErrorMessage("意见不能为空!");
			result = false;
		}
		return result;
	}

	private boolean mustInputDealContent() {
		if (!IssueOperate.REPORT_TO.equals(operate)
				&& !IssueOperate.CANCEL_SUPERVISE.equals(operate)
				&& !IssueOperate.CONCEPT.equals(operate)
				&& !IssueOperate.NORMAL_SUPERVISE.equals(operate)
				&& !IssueOperate.READ.equals(operate)
				&& !IssueOperate.RED_SUPERVISE.equals(operate)
				&& !IssueOperate.YELLOW_SUPERVISE.equals(operate)) {
			return true;
		} else {
			return false;
		}
	}

	public String dispatchDeal() throws Exception {
		if (null == stepId) {
			appendErrorMessage("参数不正确!");
			return MOBILE_ERROR;
		}
		jsonMap = new HashMap<String, Object>();
		organization = ThreadVariable.getUser().getOrganization();
		organization = organizationDubboService.getFullOrgById(organization
				.getId());
		jsonMap.put("functionalDepartmentsForAssign",
				findFunctionalDepartmentsForAssign(organization));
		jsonMap.put("functionalDepartmentsForSubmitForward",
				findFunctionalDepartmentsForSubmitForward(organization));
		organization.setOrgLevel(propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId()));
		IssueStep step = issueNewService.getIssueStepById(stepId);
		if (!organization.equals(step.getTarget())) {
			appendErrorMessage("参数不正确!");
		}
		issueLog = new IssueLogNew();
		issueLog.setIssue(step.getIssue());
		IssueLogNew logNew = issueLogService
				.findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(step
						.getIssue().getId(), organization.getId(), organization
						.getId());
		if (logNew != null && logNew.getReportedGrade() != null) {
			issueLog.setReportedGrade(logNew.getReportedGrade());
		}
		fillDealUserAndOrg(null);
		if (isSuperviseOperate()) {
			return "toSupervise";
		} else if (isInstructOperate()) {
			return "toInstruct";
		} else {
			canDoList = issueNewService.getCurrentLoginedOrgCanDo(
					issueLog.getIssue(), issueLog.getDealOrg());
			if (step.getBackTo() == null) {
				canDoList.remove(IssueOperate.BACK);
			}
			canDoList.remove(IssueOperate.CANCEL_SUPERVISE);
			canDoList.remove(IssueOperate.CONCEPT);
			canDoList.remove(IssueOperate.INSTRUCT);
			canDoList.remove(IssueOperate.NORMAL_SUPERVISE);
			canDoList.remove(IssueOperate.READ);
			canDoList.remove(IssueOperate.REPORT_TO);
			canDoList.remove(IssueOperate.YELLOW_SUPERVISE);
			canDoList.remove(IssueOperate.RED_SUPERVISE);
			Organization org = organizationDubboService
					.getFullOrgById(organization.getParentOrg().getId());
			if (organization != null && organization.getOrgLevel() != null
					&& organization.getOrgLevel().getInternalId() <= 2) {
				// 镇级上级部门是市级有交办
				int parentLevelIntId = propertyDictService.getPropertyDictById(
						org.getOrgLevel().getId()).getInternalId();
				int parentTypeInternalId = propertyDictService
						.getPropertyDictById(organization.getOrgType().getId())
						.getInternalId();
				if (!(parentLevelIntId == OrganizationLevel.CITY && parentTypeInternalId == OrganizationType.ADMINISTRATIVE_REGION)) {
					canDoList.remove(IssueOperate.ASSIGN);
					jsonMap.put("transferTo", false);
				} else {
					jsonMap.put("transferTo", true);
					transferTo = true;
				}
			}

			if (null == canDoList || canDoList.size() == 0) {
				canDoList = new ArrayList<IssueOperate>();
				canDoList.add(IssueOperate.parse(IssueOperate.CONCEPT_CODE));
				jsonMap.put("canDoList", canDoList);
			} else {
				jsonMap.put("canDoList", canDoList);
			}
			jsonMap.put("reportedDepartment", org);
			// jsonMap.put("issueLog", issueLog);
			jsonMap.put("stepId", stepId);
			// jsonMap.put("organization", organization);
			return canDoList.size() > 0 ? "success" : "simpleDeal";
		}
	}

	private List<Organization> findFunctionalDepartmentsForAssign(
			Organization organization) {
		List<PropertyDict> propertyList = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTIONAL_ORG);
		if (propertyList != null && propertyList.size() > 0
				&& organization != null && organization.getId() != null) {
			return organizationDubboService
					.findOrganizationByParentIdAndOrgType(organization.getId(),
							propertyList.get(0).getId());
		}
		return null;
	}

	private List<Organization> findFunctionalDepartmentsForSubmitForward(
			Organization organization) {
		List<PropertyDict> propertyList = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTIONAL_ORG);
		if (propertyList != null && propertyList.size() > 0
				&& organization != null && organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null) {
			return organizationDubboService
					.findOrganizationByParentIdAndOrgType(organization
							.getParentOrg().getId(), propertyList.get(0)
							.getId());
		}
		return null;
	}

	private boolean isInstructOperate() {
		operate = operate == null ? IssueOperate.parse(dealType) : operate;
		if (operate != null && IssueOperate.INSTRUCT.equals(operate)) {
			return true;
		}
		return false;
	}

	private boolean isSuperviseOperate() {
		operate = operate == null ? IssueOperate.parse(dealType) : operate;
		if (operate != null
				&& (IssueOperate.NORMAL_SUPERVISE.equals(operate)
						|| IssueOperate.YELLOW_SUPERVISE.equals(operate) || IssueOperate.RED_SUPERVISE
						.equals(operate))) {
			return true;
		}
		return false;
	}

	private void fillDealUserAndOrg(Organization org) {
		issueLog.setDealUserName(ThreadVariable.getUser().getName());
		issueLog.setMobile(ThreadVariable.getUser().getMobile());
		issueLog.setDealOrg(ThreadVariable.getSession().getOrganization());
		// issueLog.setDealOrgInternalCode(org.getOrgInternalCode());
	}

	public String prepareExistedEnabledIssueType() {
		mobileIssueTypeList = new ArrayList<Map<String, List<MobileIssueType>>>();
		List<MobileIssueType> contradiction = issueTypeService
				.findMobileEnabledIssueTypesByParentName(IssueTypes.CONTRADICTION);
		Map<String, List<MobileIssueType>> contradictionMap = new HashMap<String, List<MobileIssueType>>();
		contradictionMap.put(IssueTypes.CONTRADICTION, contradiction);
		mobileIssueTypeList.add(contradictionMap);
		List<MobileIssueType> specialisation = issueTypeService
				.findMobileEnabledIssueTypesByParentName(IssueTypes.SECURITYTROUBLE);
		Map<String, List<MobileIssueType>> specialisationMap = new HashMap<String, List<MobileIssueType>>();
		specialisationMap.put(IssueTypes.SECURITYTROUBLE, specialisation);
		mobileIssueTypeList.add(specialisationMap);
		List<MobileIssueType> peopleliveService = issueTypeService
				.findMobileEnabledIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE);
		Map<String, List<MobileIssueType>> peopleliveServiceMap = new HashMap<String, List<MobileIssueType>>();
		peopleliveServiceMap.put(IssueTypes.PEOPLELIVE_SERVICE,
				peopleliveService);
		mobileIssueTypeList.add(peopleliveServiceMap);
		return SUCCESS;
	}

	@Override
	public String findPlaceList() throws Exception {
		boolean boolSolr = false;
		if (placeName == null || placeName.trim().equals("")) {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				placeName = "*:";
			} else {
				placeName = "";
			}
		}
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		if (organizationDubboService.isTownOrganization(orgId)) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		} else {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				if (placeName == null || "".equals(placeName)) {
					placeName = "*:";
				}
			} else {
				if (placeName == null || "".equals(placeName)) {
					placeName = "";
				}
			}
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		PageInfo<SearchBaseInfoPlace> pageInfo = new PageInfo<SearchBaseInfoPlace>();
		List<SearchBaseInfoPlace> lists = new ArrayList<SearchBaseInfoPlace>();
		String isSenderSolrMsg = (String) globalSettingService
				.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
		if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
			boolSolr = true;
		}
		if (boolSolr) {
			PageInfo<KeyPlaceDocument> keyPlaceDocuments = solrKeyPlaceService
					.findKeyPlaceDocumentPageByTag(placeName,
							organization.getOrgInternalCode(), page, rows);
			if (keyPlaceDocuments != null) {
				for (int i = 0; i < keyPlaceDocuments.getResult().size(); i++) {
					KeyPlaceDocument keyPlaceDocument = keyPlaceDocuments
							.getResult().get(i);
					SearchBaseInfoPlace searchBaseInfoPlace = new SearchBaseInfoPlace();
					searchBaseInfoPlace.setId(keyPlaceDocument.getKey());
					searchBaseInfoPlace.setName(keyPlaceDocument.getName());
					searchBaseInfoPlace.setOrgName(organizationDubboService
							.getFullOrgById(keyPlaceDocument.getOrgId())
							.getOrgName());
					searchBaseInfoPlace.setAddress(keyPlaceDocument
							.getAddress());
					searchBaseInfoPlace.setTypeName(IssuePersonAndSiteType
							.toString(keyPlaceDocument.getType()));
					lists.add(searchBaseInfoPlace);
				}
				pageInfo.setResult(lists);
				pageInfo.setTotalRowSize(keyPlaceDocuments.getTotalRowSize());
			}
		} else {
			PageInfo<KeyPlace> keyPlaces = placeService
					.searchKeyPlacePageByName(placeName,
							organization.getOrgInternalCode(), page, rows);
			if (keyPlaces != null) {
				for (int i = 0; i < keyPlaces.getResult().size(); i++) {
					KeyPlace keyPlace = keyPlaces.getResult().get(i);
					SearchBaseInfoPlace searchBaseInfoPlace = new SearchBaseInfoPlace();
					searchBaseInfoPlace.setId(keyPlace.getType() + "-"
							+ keyPlace.getId());
					searchBaseInfoPlace.setName(keyPlace.getName());
					searchBaseInfoPlace.setOrgName(organizationDubboService
							.getFullOrgById(keyPlace.getOrgId()).getOrgName());
					searchBaseInfoPlace.setAddress(keyPlace.getAddress());
					searchBaseInfoPlace.setTypeName(IssuePersonAndSiteType
							.toString(keyPlace.getType()));
					lists.add(searchBaseInfoPlace);
				}
				pageInfo.setResult(lists);
				pageInfo.setTotalRowSize(keyPlaces.getTotalRowSize());
			}
		}
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Override
	public String findPopulationsByCardNoAndNameAndOrgId() {

		return SUCCESS;
	}

	@Override
	public String addIssue() throws Exception {
		if (!addIssueSuccessed()) {
			return MOBILE_ERROR;
		}
		return SUCCESS;
	}

	private boolean addIssueSuccessed() throws Exception {
		if (!validateInput()) {
			return false;
		}
		prepareExistedEnabledIssueTypes();
		prepareEmphas();
		String prefix = getCurrentIssuePrefix(issueNew);
		// issueNew.setIssueTypes(transToIssueType());
		if (issueNew.getSerialNumber() == null
				|| "".equals(issueNew.getSerialNumber())) {
			issueNew.setSerialNumber(issueSerial.getNextKey(prefix));
		}
		issueNew.setCreateOrg(getCurrentUser().getOrganization());
		issueNew.setLastUsername(getCurrentUser().getName());
		issueViewObject = issueNewService.addIssue(issueNew, attachFiles,
				emphas, null);
		issueViewObject.setOccurOrg(ControllerHelper
				.proccessRelativeOrgNameByOrg(issueNew.getOccurOrg(),
						organizationDubboService));
		return true;
	}

	private String getCurrentIssuePrefix(IssueNew issue) {
		Organization district = organizationDubboService
				.getDistrictOrganizationId(issue.getOccurOrg().getId());
		if (district.getDepartmentNo() == null
				|| district.getDepartmentNo().trim().length() == 0) {
			district = organizationDubboService
					.getDistrictTownOrganizationId(issue.getOccurOrg().getId());
		}
		return CalendarUtil.format("yyMMdd", new Date())
				+ district.getDepartmentNo();
	}

	private void prepareEmphas() {
		emphas = new HashMap<String, String>();
		if (isInvolvedPlace != null) {
			if (involvedPlace != null && !involvedPlace.trim().equals("")) {
				String[] places = involvedPlace.split(separator);
				for (int i = 0; i < places.length; i++) {
					String value = places[i].substring(
							places[i].indexOf("-") + 1, places[i].length());
					String name = places[i]
							.substring(0, places[i].indexOf("-"));
					if (emphas.containsKey(name)) {
						String oldValue = emphas.get(name);
						emphas.remove(name);
						emphas.put(name, oldValue + "," + value);
					} else {
						emphas.put(name, value);
					}
				}
			}
		}
		if (involvedPersonnel != null && !involvedPersonnel.trim().equals("")) {
			String[] personnels = involvedPersonnel.split(separator);
			for (int i = 0; i < personnels.length; i++) {
				String key = personnels[i].substring(
						personnels[i].indexOf("-") + 1, personnels[i].length());
				String value = personnels[i].substring(0,
						personnels[i].indexOf("-"));
				if (emphas.get(key) != null) {
					String oldValue = emphas.get(key);
					emphas.remove(key);
					emphas.put(key, oldValue + "," + value);
				} else {
					emphas.put(key, value);
				}
			}
		}
	}

	private boolean validateInput() {
		boolean result = true;
		if (!StringUtil.isStringAvaliable(issueNew.getSubject())) {
			appendErrorMessage("事件处理主题不得为空!");
			result = false;
		} else if (issueNew.getSubject().trim().length() < 2) {
			appendErrorMessage("事件处理主题不得小于2个字符!");
			result = false;
		}
		if (issueNew.getRelatePeopleCount() == null
				|| issueNew.getRelatePeopleCount() == 0) {
			appendErrorMessage("事件处理涉及人数不得为空!");
			result = false;
		}
		if (issueNew.getMainCharacters() == null
				|| issueNew.getMainCharacters().trim().length() <= 0) {
			appendErrorMessage("主要人员不能为空!");
			result = false;
		}
		if (issueNew.getOccurDate() == null) {
			appendErrorMessage("事件处理发生时间不得为空!");
			result = false;
		}
		if (issueNew.getIssueContent() == null) {
			appendErrorMessage("事件处理事件内容不得为空!");
			result = false;
		}
		if (issueNew.getIssueKind() == null
				|| issueNew.getIssueKind().getId() == null)
			appendErrorMessage("事件处理事件性质不得为空!");
		if (issueNew.getOccurOrg() != null) {
			if (!isTownBelowOrg(organizationDubboService
					.getFullOrgById(issueNew.getOccurOrg().getId()))) {
				appendErrorMessage("发生网格请选择乡镇(街道)及以下级别!");
				result = false;
			}
		}
		if (selContradictionId == null && selSpecialisationId == null
				&& selPeopleliveServiceId == null && selOtherTypeId == null
				&& selSafeId == null && selSocialOpinionId == null
				&& selPolicyLawId == null && selIncidentReportId == null
				&& selOtherId == null) {
			appendErrorMessage("请至少选择一个事件处理事件类型!");
			result = false;
		}
		if (attachFiles != null) {
			if (attachFiles.length > 5) {
				appendErrorMessage("事件处理附件最多只能上传5个!");
				result = false;
			}
		}
		if (isInvolvedPlace != null && involvedPlace != null) {
			String[] places = involvedPlace.split(",");
			if (places.length > 1) {
				appendErrorMessage("最多只允许选择一个重点场所!");
				result = false;
			}
		}
		return result;
	}

	private boolean isTownBelowOrg(Organization org) {
		return (org.getOrgLevel().getInternalId() == OrganizationLevel.GRID
				|| org.getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE || org
				.getOrgLevel().getInternalId() == OrganizationLevel.TOWN)
				&& (org.getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION);
	}

	@Override
	public String viewIssueDetail() throws Exception {
		if (issueId == null || issueId == 0L) {
			errorMessage = "无法定位数据";
			return MOBILE_ERROR;
		}
		if (issueLogNew != null && issueLogNew.getId() != null) {
			issueLogNew = issueLogService.getFullIssueLogById(issueId);
			issueNew = issueNewService.getFullIssueById(issueLogNew.getIssue()
					.getId());
			issueId = issueNew.getCurrentStep().getId();
		}
		organization = ThreadVariable.getUser().getOrganization();
		issueNew = issueNewService.getFullIssueMobileByIssueId(issueId);
		if (null == issueNew) {
			issueNew = issueNewService.getFullIssueByIssueStepId(issueId);
		}
		if (issueNew == null || issueNew.getId() == null) {
			errorMessage = "无法定位数据";
			return MOBILE_ERROR;
		}
		// prepareAllIssueTypesFromIssue(issueNew);
		preparePropertyDict();
		// prepareIssueTypes();
		prepareRelateObjects(issueNew);
		prepareOrganization();
		prepareIssueAttachFiles(issueNew);
		proccessIssueLogsOrgAndDeal();
		prepareLogAttachFiles();
		prepareCanDoList(issueNew);
		if (issueLogNew != null
				&& issueLogNew.getDealState() != null
				&& IssueDealState.COMPLETE.longValue() == issueLogNew
						.getDealState().longValue()) {
			issueNew.setEndDate(new SimpleDateFormat("yyyy年MM月dd HH:mm:ss")
					.format(issueLogNew.getDealTime()));
		}
		if (issueLogNew != null
				&& issueLogNew.getDealState() != null
				&& IssueDealState.PUBLICLTYCASS.longValue() == issueLogNew
						.getDealState().longValue()) {
			IssueLogNew issueLogNews = issueLogService.findLastOperationLog(
					issueId, issueNew.getOccurOrg().getId(),
					IssueDealState.COMPLETE);
			if (issueLogNews != null) {
				issueNew.setEndDate(new SimpleDateFormat("yyyy年MM月dd HH:mm:ss")
						.format(issueLogNews.getDealTime()));
			}
		}
		if (ManagementMode.PRINT.equalsIgnoreCase(managementMode)) {
			return "print";
		} else if (ManagementMode.PRINT_WORD.equalsIgnoreCase(managementMode)) {
			return "printWord";
		}
		jsonMap = new HashMap<String, Object>();
		if (null != canDoList && canDoList.size() > 0) {
			for (int i = 0; i < canDoList.size(); i++) {
				if (IssueOperate.CONCEPT_CODE == canDoList.get(i).getCode()) {
					jsonMap.put("action", "受理");
					jsonMap.put("method", "doAccept");
					break;
				}
				if (IssueOperate.COMMENT_CODE == canDoList.get(i).getCode()) {
					jsonMap.put("action", "办理");
					jsonMap.put("method", "doHandle");
					break;
				}
			}
		}
		issueNew.setCurrentOrg(organizationDubboService.getFullOrgById(issueNew
				.getCurrentOrg().getId()));
		jsonMap.put("issueNew", issueNew);
		jsonMap.put("issueAttachFiles", issueAttachFiles); // 图片
		jsonMap.put("issueLogs", issueLogs);
		return SUCCESS;
	}

	@Override
	public String findNeedDoIssueList() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (null == searchIssueVo) {
			searchIssueVo = new SearchIssueVoNew();
		}
		searchIssueVo.setTargeOrgId(orgId);
		preparePageData();
		searchIssueVo.setIssueTypes(transToIssueType());
		searchIssueVo.setDealState(Long.parseLong(IssueState.STEPCOMPLETE_CODE
				+ ""));
		PageInfo<IssueViewObject> pageInfo = proccessRelativeOrgNameByPageInfo(
				searchIssueDao.searchIssuesNew(searchIssueVo, page, rows, sidx,
						sord), organizationDubboService);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void prepareCanDoList(IssueNew issue) {
		if (!ManagementMode.VIEW.equalsIgnoreCase(managementMode)) {
			canDoList = issueNewService.getCurrentLoginedOrgCanDo(issue,
					ThreadVariable.getUser().getOrganization());
		} else {
			canDoList = new ArrayList<IssueOperate>();
		}
	}

	private void prepareLogAttachFiles() {
		List<IssueAttachFile> logAttachFilesList = issueLogService
				.findIssueLogAttachFilesById(issueId);
		for (IssueAttachFile issueAttachFile : logAttachFilesList) {
			getList(issueAttachFile.getIssueLog().getId().toString(),
					issueLogAttachFilesMap).add(issueAttachFile);
		}
	}

	private void proccessIssueLogsOrgAndDeal() {
		issueLogs = ControllerHelper.processAllOrgName(
				issueLogService.findIssueLogsByIssueId(issueNew.getId()),
				organizationDubboService,
				new String[] { "dealOrg", "targeOrg" });
	}

	private void prepareIssueAttachFiles(IssueNew issueNew) {
		issueAttachFiles = issueNewService.findIssueAttachFilesById(issueNew
				.getId());
	}

	private void prepareOrganization() {
		if (issueNew.getOccurOrg() != null
				&& issueNew.getOccurOrg().getId() != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(issueNew.getOccurOrg().getId());
			issueNew.setOccurOrg(org);
		}
	}

	private void prepareRelateObjects(IssueNew issueNew) {
		Long issueId = 0L;
		issueId = issueNew.getId();
		List<EmphasesVo> relatePlacesList = issueTypeService
				.findRelatePlacesByName(issueId);
		List<EmphasesVo> relatePersonList = issueTypeService
				.findRelatePersonByName(issueId);

		for (EmphasesVo relatePlace : relatePlacesList) {
			getList(relatePlace.getType(), relatePlaces).add(relatePlace);
		}
		for (EmphasesVo relatePerson : relatePersonList) {
			getList(relatePerson.getType(), relatePersons).add(relatePerson);
		}
	}

	private List getList(String type, Map resultMap) {
		if (resultMap.containsKey(type)) {
			return (List) resultMap.get(type);
		} else {
			List valueList = new ArrayList();
			resultMap.put(type, valueList);
			return valueList;
		}
	}

	private void preparePropertyDict() {
		if (issueNew.getIssueKind() != null
				&& issueNew.getIssueKind().getId() != null) {
			PropertyDict issueKindDict = propertyDictService
					.getPropertyDictById(issueNew.getIssueKind().getId());
			issueNew.setIssueKind(issueKindDict);
		}
		if (issueNew.getSourceKind() != null
				&& issueNew.getSourceKind().getId() != null) {
			PropertyDict issueSourceKind = propertyDictService
					.getPropertyDictById(issueNew.getSourceKind().getId());
			issueNew.setSourceKind(issueSourceKind);
		}
	}

	// private void prepareAllIssueTypesFromIssue(IssueNew issue) {
	// prepareExistedEnabledIssueTypes();
	// for (IssueType type : issueNew.getIssueTypes()) {
	// if (!issueTypeHasLoaded(type)) {
	// loadSingleIssueType(type);
	// }
	// }
	// }

	private void prepareExistedEnabledIssueTypes() {
		Long currentOrgId = getCurrentLoginedOrgId();
		contradiction = issueTypeService.findEnabledIssueTypesByParentName(
				currentOrgId, IssueTypes.CONTRADICTION);
		specialisation = issueTypeService.findEnabledIssueTypesByParentName(
				currentOrgId, IssueTypes.SECURITYTROUBLE);
		peopleliveService = issueTypeService.findEnabledIssueTypesByParentName(
				currentOrgId, IssueTypes.PEOPLELIVE_SERVICE);
		Organization org = organizationDubboService
				.getFullOrgById(currentOrgId);
		if (needShowOtherIssueTypes(org)) {
			otherType = issueTypeService.findEnabledIssueTypesByParentName(
					currentOrgId, IssueTypes.OTHERTYPE);
		} else {
			otherType = new ArrayList<IssueType>();
		}
	}

	private Long getCurrentLoginedOrgId() {
		return ThreadVariable.getUser().getOrganization().getId();
	}

	private boolean needShowOtherIssueTypes(Organization org) {
		return org.getOrgLevel().getInternalId() < OrganizationLevel.CITY;
	}

	private boolean issueTypeHasLoaded(IssueType type) {
		return contradiction.contains(type) || specialisation.contains(type)
				|| peopleliveService.contains(type) || otherType.contains(type);
	}

	private void loadSingleIssueType(IssueType type) {
		IssueType issueType = issueTypeService.getIssueTypeById(null,
				type.getId());
		IssueTypeDomain typeDomain = issueTypeDomainService
				.getIssueTypeDoaminById(type.getIssueTypeDomain().getId());
		if (isContradictionType(typeDomain)) {
			contradiction.add(issueType);
		} else if (isSpecialisationType(typeDomain)) {
			specialisation.add(issueType);
		} else if (isPeopleliveServiceType(typeDomain)) {
			peopleliveService.add(issueType);
		} else if (isOtherType(typeDomain)) {
			otherType.add(issueType);
		}
	}

	// private void prepareIssueTypes() {
	// selContradiction = transIssueTypeToList(contradiction,
	// issueNew.getIssueTypes(), 0);
	// selSpecialisation = transIssueTypeToList(specialisation,
	// issueNew.getIssueTypes(), 0);
	// selPeopleliveService = transIssueTypeToList(peopleliveService,
	// issueNew.getIssueTypes(), 0);
	// selOtherType = transIssueTypeToList(otherType,
	// issueNew.getIssueTypes(), 0);
	// if (selContradiction.size() > 0) {
	// selContradictionString = "矛盾纠纷：";
	// }
	// if (selSpecialisation.size() > 0) {
	// selSpecialisationString = "治安,安全隐患：";
	// }
	// if (selPeopleliveService.size() > 0) {
	// selPeopleliveServiceString = "民生服务：";
	// }
	// if (selOtherType.size() > 0) {
	// selOtherTypeString = "其他：";
	// }
	// }

	private List<IssueType> transIssueTypeToList(List<IssueType> asIssueType,
			List<IssueType> issueTypes, int i) {
		List<IssueType> selIssueTypes = new ArrayList<IssueType>();
		if (issueTypes == null || asIssueType == null)
			return selIssueTypes;
		if (i == 1) {
			selContradictionTxt = "";
			for (IssueType issueType : issueTypes) {
				if (asIssueType.contains(issueType)) {
					selIssueTypes.add(issueType);
					selContradictionTxt += issueType.getIssueTypeName() + ",";
				}
			}
		} else if (i == 2) {
			selSpecialisationTxt = "";
			for (IssueType issueType : issueTypes) {
				if (asIssueType.contains(issueType)) {
					selIssueTypes.add(issueType);
					selSpecialisationTxt += issueType.getIssueTypeName() + ",";
				}
			}
		} else if (i == 3) {
			selPeopleliveServiceTxt = "";
			for (IssueType issueType : issueTypes) {
				if (asIssueType.contains(issueType)) {
					selIssueTypes.add(issueType);
					selPeopleliveServiceTxt += issueType.getIssueTypeName()
							+ ",";
				}
			}
		} else if (i == 4) {
			selOtherTypeTxt = "";
			for (IssueType issueType : issueTypes) {
				if (asIssueType.contains(issueType)) {
					selIssueTypes.add(issueType);
					selOtherTypeTxt += issueType.getIssueTypeName() + ",";
				}
			}
		} else {
			for (IssueType issueType : issueTypes) {
				if (asIssueType.contains(issueType))
					selIssueTypes.add(issueType);
			}
		}
		return selIssueTypes;
	}

	private boolean isContradictionType(IssueTypeDomain typeDomain) {
		return IssueTypes.CONTRADICTION.equals(typeDomain.getDomainName());
	}

	private boolean isSpecialisationType(IssueTypeDomain typeDomain) {
		return IssueTypes.SECURITYTROUBLE.equals(typeDomain.getDomainName());
	}

	private boolean isPeopleliveServiceType(IssueTypeDomain typeDomain) {
		return IssueTypes.PEOPLELIVE_SERVICE.equals(typeDomain.getDomainName());
	}

	private boolean isOtherType(IssueTypeDomain typeDomain) {
		return IssueTypes.OTHERTYPE.equals(typeDomain.getDomainName());
	}

	private List<IssueType> transToIssueType() {
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		if (selContradictionId != null
				&& selContradictionId.trim().length() > 0) {
			List<Long> contradictionId = new ArrayList<Long>();
			if (selContradictionId.contains(separator)) {
				String[] id = selContradictionId.split(separator);
				for (int i = 0; i < id.length; i++) {
					contradictionId.add(Long.parseLong(id[i]));
				}
			} else {
				contradictionId.add(Long.parseLong(selContradictionId));
			}

			for (Long issueTypeId : contradictionId) {
				if (selContradiction == null)
					selContradiction = new ArrayList<IssueType>();
				selContradiction.add(createIssueTypeById(issueTypeId));
			}
		}

		if (selSafeId != null && selSafeId.trim().length() > 0) {
			List<Long> contradictionId = new ArrayList<Long>();
			if (selSafeId.contains(separator)) {
				String[] id = selSafeId.split(separator);
				for (int i = 0; i < id.length; i++) {
					contradictionId.add(Long.parseLong(id[i]));
				}
			} else {
				contradictionId.add(Long.parseLong(selSafeId));
			}

			for (Long issueTypeId : contradictionId) {
				if (selSafe == null)
					selSafe = new ArrayList<IssueType>();
				selSafe.add(createIssueTypeById(issueTypeId));
			}
		}
		if (selSocialOpinionId != null
				&& selSocialOpinionId.trim().length() > 0) {
			List<Long> contradictionId = new ArrayList<Long>();
			if (selSocialOpinionId.contains(separator)) {
				String[] id = selSocialOpinionId.split(separator);
				for (int i = 0; i < id.length; i++) {
					contradictionId.add(Long.parseLong(id[i]));
				}
			} else {
				contradictionId.add(Long.parseLong(selSocialOpinionId));
			}

			for (Long issueTypeId : contradictionId) {
				if (selSocialOpinion == null)
					selSocialOpinion = new ArrayList<IssueType>();
				selSocialOpinion.add(createIssueTypeById(issueTypeId));
			}
		}
		if (selPolicyLawId != null && selPolicyLawId.trim().length() > 0) {
			List<Long> contradictionId = new ArrayList<Long>();
			if (selPolicyLawId.contains(separator)) {
				String[] id = selPolicyLawId.split(separator);
				for (int i = 0; i < id.length; i++) {
					contradictionId.add(Long.parseLong(id[i]));
				}
			} else {
				contradictionId.add(Long.parseLong(selPolicyLawId));
			}

			for (Long issueTypeId : contradictionId) {
				if (selPolicyLaw == null)
					selPolicyLaw = new ArrayList<IssueType>();
				selPolicyLaw.add(createIssueTypeById(issueTypeId));
			}
		}
		if (selIncidentReportId != null
				&& selIncidentReportId.trim().length() > 0) {
			List<Long> contradictionId = new ArrayList<Long>();
			if (selIncidentReportId.contains(separator)) {
				String[] id = selIncidentReportId.split(separator);
				for (int i = 0; i < id.length; i++) {
					contradictionId.add(Long.parseLong(id[i]));
				}
			} else {
				contradictionId.add(Long.parseLong(selIncidentReportId));
			}

			for (Long issueTypeId : contradictionId) {
				if (selIncidentReport == null)
					selIncidentReport = new ArrayList<IssueType>();
				selIncidentReport.add(createIssueTypeById(issueTypeId));
			}
		}
		if (selOtherId != null && selOtherId.trim().length() > 0) {
			List<Long> contradictionId = new ArrayList<Long>();
			if (selOtherId.contains(separator)) {
				String[] id = selOtherId.split(separator);
				for (int i = 0; i < id.length; i++) {
					contradictionId.add(Long.parseLong(id[i]));
				}
			} else {
				contradictionId.add(Long.parseLong(selOtherId));
			}

			for (Long issueTypeId : contradictionId) {
				if (selOther == null)
					selOther = new ArrayList<IssueType>();
				selOther.add(createIssueTypeById(issueTypeId));
			}
		}

		if (selSpecialisationId != null
				&& selSpecialisationId.trim().length() > 0) {
			List<Long> specialisationId = new ArrayList<Long>();
			if (selSpecialisationId.contains(separator)) {
				String[] id = selSpecialisationId.split(separator);
				for (int i = 0; i < id.length; i++) {
					specialisationId.add(Long.parseLong(id[i]));
				}
			} else {
				specialisationId.add(Long.parseLong(selSpecialisationId));
			}
			if (selSpecialisation == null)
				selSpecialisation = new ArrayList<IssueType>();
			for (Long issueTypeId : specialisationId) {
				selSpecialisation.add(createIssueTypeById(issueTypeId));
			}
		}
		if (selPeopleliveServiceId != null
				&& selPeopleliveServiceId.trim().length() > 0) {
			List<Long> peopleliveServiceId = new ArrayList<Long>();
			if (selPeopleliveServiceId.contains(separator)) {
				String[] id = selPeopleliveServiceId.split(separator);
				for (int i = 0; i < id.length; i++) {
					peopleliveServiceId.add(Long.parseLong(id[i]));
				}
			} else {
				peopleliveServiceId.add(Long.parseLong(selPeopleliveServiceId));
			}
			for (Long issueTypeId : peopleliveServiceId) {
				if (selPeopleliveService == null)
					selPeopleliveService = new ArrayList<IssueType>();
				selPeopleliveService.add(createIssueTypeById(issueTypeId));
			}
		}
		if (selOtherTypeId != null)
			for (Long issueTypeId : selOtherTypeId) {
				if (selOtherType == null)
					selOtherType = new ArrayList<IssueType>();
				selOtherType.add(createIssueTypeById(issueTypeId));
			}

		if (selSafe != null)
			for (IssueType issueType : safe) {
				if (selSafe.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selSocialOpinion != null)
			for (IssueType issueType : socialOpinion) {
				if (selSocialOpinion.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selPolicyLaw != null)
			for (IssueType issueType : policyLaw) {
				if (selPolicyLaw.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selIncidentReport != null)
			for (IssueType issueType : incidentReport) {
				if (selIncidentReport.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selOther != null)
			for (IssueType issueType : other) {
				if (selOther.contains(issueType))
					issueTypes.add(issueType);
			}

		if (selContradiction != null)
			for (IssueType issueType : contradiction) {
				if (selContradiction.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selSpecialisation != null)
			for (IssueType issueType : specialisation) {
				if (selSpecialisation.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selPeopleliveService != null)
			for (IssueType issueType : peopleliveService) {
				if (selPeopleliveService.contains(issueType))
					issueTypes.add(issueType);
			}
		if (selOtherType != null)
			for (IssueType issueType : otherType) {
				if (selOtherType.contains(issueType))
					issueTypes.add(issueType);
			}
		return issueTypes;
	}

	private IssueType createIssueTypeById(Long id) {
		IssueType issueType = new IssueType();
		issueType.setId(id);
		return issueType;
	}

	private void preparePageData() {
		contradiction = issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION);
		specialisation = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE);
		peopleliveService = issueTypeService
				.findIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE);
	}

	private PageInfo<IssueNew> emptyPage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	@Override
	public String findMyJurisdictionsNeedDo() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (null == searchIssueVo) {
			searchIssueVo = new SearchIssueVoNew();
		}
		searchIssueVo.setTargeOrgId(orgId);
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setOrgInternalCode(org.getOrgInternalCode());
		preparePageData();
		searchIssueVo.setIssueTypes(transToIssueType());
		searchIssueVo.setDealState(Long.parseLong(IssueState.STEPCOMPLETE_CODE
				+ ""));
		PageInfo<IssueViewObject> pageInfo = proccessRelativeOrgNameByPageInfo(
				searchIssueDao.searchJurisdictionsIssues(searchIssueVo, page,
						rows, sidx, sord), organizationDubboService);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;

	}

	@Override
	public String findJurisdictionsDoneIssues() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (null == searchIssueVo) {
			searchIssueVo = new SearchIssueVoNew();
		}
		searchIssueVo.setTargeOrgId(orgId);
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		preparePageData();
		searchIssueVo.setIssueTypes(transToIssueType());
		searchIssueVo.setDealState(Long.parseLong(IssueState.ISSUECOMPLETE_CODE
				+ ""));

		PageInfo<IssueViewObject> pageInfo = proccessRelativeOrgNameByPageInfo(
				searchIssueDao.searchJurisdictionsDoneIssues(searchIssueVo,
						page, rows, sidx, sord), organizationDubboService);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;

	}

	private PageInfo<IssueViewObject> proccessRelativeOrgNameByPageInfo(
			PageInfo<IssueViewObject> pageInfo,
			OrganizationDubboService organizationDubboService) {
		pageInfo = ControllerHelper.processAllOrgRelativeName(pageInfo,
				organizationDubboService, new String[] { "occurOrg", "lastOrg",
						"targeOrg", "currentOrg" }, orgId);
		return pageInfo;
	}

	@Override
	public String doIssueAction() throws Exception {
		if (null == issueLog.getIssue().getId()) {
			appendErrorMessage("参数不正确!");
			return ERROR;
		}
		IssueStep step = issueService.getSimpleIssueByIssueId(issueLog
				.getIssue().getId());
		if (!getCurrentUser().getOrganization().equals(step.getTarget())) {
			appendErrorMessage("参数不正确!");
		}
		issueLog.setIssue(step.getIssue());
		IssueNew issue = issueService.getFullIssueByIssueStepId(step.getId());
		fillDealUserAndOrg(null);
		operate = IssueOperate.parse(dealType);

		if (IssueOperate.INSTRUCT.equals(operate)) {
			if (!StringUtil.isStringAvaliable(issueLog.getContent())) {
				this.errorMessage = "领导批示意见不能为空!";
				return ERROR;
			}
			issueViewObject = issueService.instruct(step, issueLog);
		} else if (IssueOperate.NORMAL_SUPERVISE.equals(operate)) {
			if (!StringUtil.isStringAvaliable(issueLog.getContent())) {
				this.errorMessage = "普通督办意见不能为空!";
				return ERROR;
			}
			issueViewObject = issueService.normalSupervise(step, issueLog);
		} else if (IssueOperate.YELLOW_SUPERVISE.equals(operate)) {
			if (!StringUtil.isStringAvaliable(issueLog.getContent())) {
				this.errorMessage = "黄牌督办意见不能为空!";
				return ERROR;
			}
			issueViewObject = issueService.yellowSupervise(step, issueLog);
		} else if (IssueOperate.RED_SUPERVISE.equals(operate)) {
			if (!StringUtil.isStringAvaliable(issueLog.getContent())) {
				this.errorMessage = "红牌督办意见不能为空!";
				return ERROR;
			}
			issueViewObject = issueService.redSupervise(step, issueLog);
		} else if (IssueOperate.CANCEL_SUPERVISE.equals(operate)) {
			issueService.cancelSupervise(step);
		} else if (IssueOperate.URGENT.equals(operate)) {
			if (!StringUtil.isStringAvaliable(issueLog.getContent())) {
				this.errorMessage = "加急意见不能为空!";
				return ERROR;
			}
			issueViewObject = issueService.urgent(issue, issueLog);
			issueViewObject.setOccurOrg(ControllerHelper
					.proccessRelativeOrgNameByOrg(issue.getOccurOrg(),
							organizationDubboService));
		} else if (IssueOperate.CANCEL_URGENT.equals(operate)) {
			if (null == step || null == step.getId()) {
				this.errorMessage = "事件处理ID不得为空!";
				return ERROR;
			}
			if (issue.getUrgent() != 1) {
				this.errorMessage = "找不到要取消的事件处理加急!";
				return ERROR;
			}
			issueViewObject = issueService.cancelUrgent(issue);
		} else {
			errorMessage = "未知的督办类型";
			return ERROR;
		}
		return SUCCESS;

	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<IssueType> getContradiction() {
		return contradiction;
	}

	public void setContradiction(List<IssueType> contradiction) {
		this.contradiction = contradiction;
	}

	public List<IssueType> getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(List<IssueType> specialisation) {
		this.specialisation = specialisation;
	}

	public SearchIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
	}

	public String getSelContradictionId() {
		return selContradictionId;
	}

	public void setSelContradictionId(String selContradictionId) {
		this.selContradictionId = selContradictionId;
	}

	public String getSelPeopleliveServiceId() {
		return selPeopleliveServiceId;
	}

	public void setSelPeopleliveServiceId(String selPeopleliveServiceId) {
		this.selPeopleliveServiceId = selPeopleliveServiceId;
	}

	public List<Long> getSelOtherTypeId() {
		return selOtherTypeId;
	}

	public void setSelOtherTypeId(List<Long> selOtherTypeId) {
		this.selOtherTypeId = selOtherTypeId;
	}

	public List<IssueType> getPeopleliveService() {
		return peopleliveService;
	}

	public void setPeopleliveService(List<IssueType> peopleliveService) {
		this.peopleliveService = peopleliveService;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public IssueLogNew getIssueLogNew() {
		return issueLogNew;
	}

	public void setIssueLogNew(IssueLogNew issueLogNew) {
		this.issueLogNew = issueLogNew;
	}

	public IssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(IssueNew issueNew) {
		this.issueNew = issueNew;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<IssueType> getOtherType() {
		return otherType;
	}

	public void setOtherType(List<IssueType> otherType) {
		this.otherType = otherType;
	}

	public List<IssueType> getSelContradiction() {
		return selContradiction;
	}

	public void setSelContradiction(List<IssueType> selContradiction) {
		this.selContradiction = selContradiction;
	}

	public List<IssueType> getSelSpecialisation() {
		return selSpecialisation;
	}

	public void setSelSpecialisation(List<IssueType> selSpecialisation) {
		this.selSpecialisation = selSpecialisation;
	}

	public List<IssueType> getSelPeopleliveService() {
		return selPeopleliveService;
	}

	public void setSelPeopleliveService(List<IssueType> selPeopleliveService) {
		this.selPeopleliveService = selPeopleliveService;
	}

	public List<IssueType> getSelOtherType() {
		return selOtherType;
	}

	public void setSelOtherType(List<IssueType> selOtherType) {
		this.selOtherType = selOtherType;
	}

	public String getSelContradictionString() {
		return selContradictionString;
	}

	public void setSelContradictionString(String selContradictionString) {
		this.selContradictionString = selContradictionString;
	}

	public String getSelSpecialisationString() {
		return selSpecialisationString;
	}

	public void setSelSpecialisationString(String selSpecialisationString) {
		this.selSpecialisationString = selSpecialisationString;
	}

	public String getSelPeopleliveServiceString() {
		return selPeopleliveServiceString;
	}

	public void setSelPeopleliveServiceString(String selPeopleliveServiceString) {
		this.selPeopleliveServiceString = selPeopleliveServiceString;
	}

	public String getSelOtherTypeString() {
		return selOtherTypeString;
	}

	public void setSelOtherTypeString(String selOtherTypeString) {
		this.selOtherTypeString = selOtherTypeString;
	}

	public String getSelContradictionTxt() {
		return selContradictionTxt;
	}

	public void setSelContradictionTxt(String selContradictionTxt) {
		this.selContradictionTxt = selContradictionTxt;
	}

	public String getSelSpecialisationTxt() {
		return selSpecialisationTxt;
	}

	public void setSelSpecialisationTxt(String selSpecialisationTxt) {
		this.selSpecialisationTxt = selSpecialisationTxt;
	}

	public String getSelPeopleliveServiceTxt() {
		return selPeopleliveServiceTxt;
	}

	public void setSelPeopleliveServiceTxt(String selPeopleliveServiceTxt) {
		this.selPeopleliveServiceTxt = selPeopleliveServiceTxt;
	}

	public String getSelOtherTypeTxt() {
		return selOtherTypeTxt;
	}

	public void setSelOtherTypeTxt(String selOtherTypeTxt) {
		this.selOtherTypeTxt = selOtherTypeTxt;
	}

	public Map<String, List> getRelatePlaces() {
		return relatePlaces;
	}

	public void setRelatePlaces(Map<String, List> relatePlaces) {
		this.relatePlaces = relatePlaces;
	}

	public Map<String, List> getRelatePersons() {
		return relatePersons;
	}

	public void setRelatePersons(Map<String, List> relatePersons) {
		this.relatePersons = relatePersons;
	}

	public List<IssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(List<IssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public List<IssueLogNew> getIssueLogs() {
		return issueLogs;
	}

	public void setIssueLogs(List<IssueLogNew> issueLogs) {
		this.issueLogs = issueLogs;
	}

	public Map<String, List> getIssueLogAttachFilesMap() {
		return issueLogAttachFilesMap;
	}

	public void setIssueLogAttachFilesMap(
			Map<String, List> issueLogAttachFilesMap) {
		this.issueLogAttachFilesMap = issueLogAttachFilesMap;
	}

	public String getManagementMode() {
		return managementMode;
	}

	public void setManagementMode(String managementMode) {
		this.managementMode = managementMode;
	}

	public List<IssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<IssueOperate> canDoList) {
		this.canDoList = canDoList;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	private User getCurrentUser() {
		return permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
	}

	public String getInvolvedPersonnel() {
		return involvedPersonnel;
	}

	public void setInvolvedPersonnel(String involvedPersonnel) {
		this.involvedPersonnel = involvedPersonnel;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public Boolean getIsInvolvedPlace() {
		return isInvolvedPlace;
	}

	public void setIsInvolvedPlace(Boolean isInvolvedPlace) {
		this.isInvolvedPlace = isInvolvedPlace;
	}

	public String getInvolvedPlace() {
		return involvedPlace;
	}

	public void setInvolvedPlace(String involvedPlace) {
		this.involvedPlace = involvedPlace;
	}

	public Map<String, String> getEmphas() {
		return emphas;
	}

	public void setEmphas(Map<String, String> emphas) {
		this.emphas = emphas;
	}

	public IssueViewObject getIssueViewObject() {
		return issueViewObject;
	}

	public void setIssueViewObject(IssueViewObject issueViewObject) {
		this.issueViewObject = issueViewObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public List<Countrymen> getCountrymenList() {
		return countrymenList;
	}

	public void setCountrymenList(List<Countrymen> countrymenList) {
		this.countrymenList = countrymenList;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getSelSpecialisationId() {
		return selSpecialisationId;
	}

	public void setSelSpecialisationId(String selSpecialisationId) {
		this.selSpecialisationId = selSpecialisationId;
	}

	public List<Map<String, List<MobileIssueType>>> getMobileIssueTypeList() {
		return mobileIssueTypeList;
	}

	public void setMobileIssueTypeList(
			List<Map<String, List<MobileIssueType>>> mobileIssueTypeList) {
		this.mobileIssueTypeList = mobileIssueTypeList;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public IssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(IssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public IssueOperate getOperate() {
		return operate;
	}

	public void setOperate(IssueOperate operate) {
		this.operate = operate;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public boolean isTransferTo() {
		return transferTo;
	}

	public void setTransferTo(boolean transferTo) {
		this.transferTo = transferTo;
	}

	public String getTellOrgIds() {
		return tellOrgIds;
	}

	public void setTellOrgIds(String tellOrgIds) {
		this.tellOrgIds = tellOrgIds;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	private PageInfo<IssueNew> emptyIssuePage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	public List<IssueOperate> getIssueActions() {
		return IssueActions;
	}

	public void setIssueActions(List<IssueOperate> issueActions) {
		IssueActions = issueActions;
	}

	public List<IssueType> getSafe() {
		return safe;
	}

	public void setSafe(List<IssueType> safe) {
		this.safe = safe;
	}

	public List<IssueType> getSocialOpinion() {
		return socialOpinion;
	}

	public void setSocialOpinion(List<IssueType> socialOpinion) {
		this.socialOpinion = socialOpinion;
	}

	public List<IssueType> getPolicyLaw() {
		return policyLaw;
	}

	public void setPolicyLaw(List<IssueType> policyLaw) {
		this.policyLaw = policyLaw;
	}

	public List<IssueType> getIncidentReport() {
		return incidentReport;
	}

	public void setIncidentReport(List<IssueType> incidentReport) {
		this.incidentReport = incidentReport;
	}

	public List<IssueType> getOther() {
		return other;
	}

	public void setOther(List<IssueType> other) {
		this.other = other;
	}

	public String getSelSafeId() {
		return selSafeId;
	}

	public void setSelSafeId(String selSafeId) {
		this.selSafeId = selSafeId;
	}

	public String getSelSocialOpinionId() {
		return selSocialOpinionId;
	}

	public void setSelSocialOpinionId(String selSocialOpinionId) {
		this.selSocialOpinionId = selSocialOpinionId;
	}

	public String getSelPolicyLawId() {
		return selPolicyLawId;
	}

	public void setSelPolicyLawId(String selPolicyLawId) {
		this.selPolicyLawId = selPolicyLawId;
	}

	public String getSelIncidentReportId() {
		return selIncidentReportId;
	}

	public void setSelIncidentReportId(String selIncidentReportId) {
		this.selIncidentReportId = selIncidentReportId;
	}

	public String getSelOtherId() {
		return selOtherId;
	}

	public void setSelOtherId(String selOtherId) {
		this.selOtherId = selOtherId;
	}

	public List<IssueType> getSelSafe() {
		return selSafe;
	}

	public void setSelSafe(List<IssueType> selSafe) {
		this.selSafe = selSafe;
	}

	public List<IssueType> getSelSocialOpinion() {
		return selSocialOpinion;
	}

	public void setSelSocialOpinion(List<IssueType> selSocialOpinion) {
		this.selSocialOpinion = selSocialOpinion;
	}

	public List<IssueType> getSelPolicyLaw() {
		return selPolicyLaw;
	}

	public void setSelPolicyLaw(List<IssueType> selPolicyLaw) {
		this.selPolicyLaw = selPolicyLaw;
	}

	public List<IssueType> getSelIncidentReport() {
		return selIncidentReport;
	}

	public void setSelIncidentReport(List<IssueType> selIncidentReport) {
		this.selIncidentReport = selIncidentReport;
	}

	public List<IssueType> getSelOther() {
		return selOther;
	}

	public void setSelOther(List<IssueType> selOther) {
		this.selOther = selOther;
	}

}
