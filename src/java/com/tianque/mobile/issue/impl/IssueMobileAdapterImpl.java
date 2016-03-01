package com.tianque.mobile.issue.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.SearchIssueController;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.file.constants.AttachFileModule;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;
import com.tianque.issue.constants.IssueAttachFileType;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.controller.IssueApplyDelayController;
import com.tianque.issue.controller.IssueController;
import com.tianque.issue.controller.IssueSkipruleController;
import com.tianque.issue.controller.SearchIssueByLevelController;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueRelatedPeople;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.domain.vo.IssueTypesVo;
import com.tianque.issue.domain.vo.SearchIssueSkipruleVo;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.IssueEvaluateService;
import com.tianque.issue.service.IssueRelatedPeopleService;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.service.IssueSkipruleService;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.issue.IssueMobileAdapter;
import com.tianque.mobile.vo.MobileIssueType;
import com.tianque.service.IssueNewService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 手机端：事件处理
 */
@Transactional
@Scope("request")
@Controller("issueMobileAdapter")
public class IssueMobileAdapterImpl extends BaseMobileAction implements
		IssueMobileAdapter {
	private List<IssueOperate> IssueActions;
	private Map<String, Object> jsonMap = new HashMap<String, Object>();
	private SearchIssueVoNew searchIssueVo;
	private Long orgId;
	private Long issueId;
	private IssueNew issueNew;
	private List<IssueLogNew> issueLogs;
	private List<Map<String, List<MobileIssueType>>> mobileIssueTypeList;
	private String selContradictionId;
	private String selSpecialisationId;
	private String selPeopleliveServiceId;

	private String selSafeId;
	private String selSocialOpinionId;
	private String selPolicyLawId;
	private String selIncidentReportId;
	private String selOtherId;

	private List<IssueType> selContradiction;
	private List<IssueType> selSpecialisation;
	private List<IssueType> selPeopleliveService;
	private String separator;
	private List<IssueType> contradiction;
	private List<IssueType> specialisation;
	private List<IssueType> peopleliveService;
	// 涉及主要当人
	private String involvedPerson;
	// 涉及重点场所
	private String involvedPlace;
	private String selectedTypes;
	private IssueViewObject issueViewObject;
	private List<IssueOperate> canDoList;
	private Long stepId;
	/** 查看类型 */
	private String managementMode;
	private IssueStep step;
	private Organization organization;
	private IssueLogNew issueLog;
	private boolean transferTo = false;
	private int dealType = -1;
	/** 小时 */
	private String hours;
	/** 分钟 */
	private String minute;
	private String[] attachFiles;
	private String attachFile;
	private String attachfiles;
	private List<IssueAttachFile> issueAttachFiles = new ArrayList<IssueAttachFile>();
	/** 待审核列表统计个数 */
	private int auditDelayCount;
	/** 待办事件已督办列表统计个数 */
	private int overseerIssueCount;
	/** 待办事件列表统计个数 */
	private int needDoCount;

	private int superviseType;
	@Autowired
	private SearchIssueController searchIssueController;
	@Autowired
	private IssueController issueController;
	@Autowired
	private IssueApplyDelayController issueApplyDelayController;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private PropertyDictService propertyDictService;
	/** 事件评价实体类 */
	private IssueEvaluate issueEvaluate;
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private SearchIssueByLevelController searchIssueBylevelController;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private GlobalSettingService globalSettingService;
	private String issueRelatedPeopleNames;// 相关人员姓名
	private String issueRelatedPeopleTelephones;// 相关人员手机
	private String involvedPersonnel;// 特殊人群
	private String issueRelatedPeopleFixPhones;// 固话

	private String issueEvaluateAttachFile;

	private List<PropertyDict> issueBigTypes;

	private String message;

	private IssueTypesVo issueTypesVo;

	private List<IssueTypesVo> issueTypesVoList;

	private IssueStep issueStep;

	private FourTeams fourTeams;

	/** 根据操作不同 可能是事件id、orgid、事件处理步骤id(issueStepId) */
	private Long keyId;
	private Long issueType;
	private Long orgLevel;
	private String leaderView;
	private Long functionalOrgType;// 职能部门类型
	private String statusType;
	private Integer viewProcess;// 是否是查询大屏滚动的数据
	private Long sourceType; // 事件来源
	private String viewType;// 视图类型

	private SearchIssueSkipruleVo searchIssueSkipruleVo;// 越级规则对象

	@Autowired
	private IssueSkipruleController issueSkipruleController;

	@Autowired
	private IssueRelatedPeopleService issueRelatedPeopleService;

	private Long issueNewId;

	private final static String SYS_SERIES = IssueServiceFactory.DEFAULT_SERIES;

	private IssueSkiprule issueSkiprule;

	@Autowired
	private IssueSkipruleService issueSkipruleService;

	private Integer orgLevelInternalId;

	/** 事件处理操作类型代码， 具体含义定义在IssueOperate中 */
	private int dealCode;
	/** 事件实体类 用于后台保存事件数据 */
	private IssueNew issue;

	private Long issuesKeyId;

	private String mode;
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private IssueEvaluateService issueEvaluateService;

	/*
	 * 获取领导批示所需信息
	 */
	@Override
	public String getIssueAction() {
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
		jsonMap.put("issueActions", IssueActions);
		return IssueActions.size() > 0 ? "success" : "simpleDeal";
	}

	/*
	 * 待办列表和查询
	 */

	@Override
	public String findNeedDoIssueList() throws Exception {
		Integer status = IssueState.DEALING;
		legalOrgId();
		setObjectValue(status);
		setControllerProperties();

		String types[] = setIssueTypes().split(",");
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		for (String type : types) {
			if (type != null && !"".equals(type)) {
				IssueType issueType = new IssueType();
				issueType.setId(Long.parseLong(type));
				issueTypes.add(issueType);
			}
		}
		searchIssueVo.setIssueTypes(issueTypes);
		searchIssueController.searchIssue();
		gridPage = searchIssueController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 我的已办结列表和查询
	 * 
	 * @throws Exception
	 */
	public String findCompletedIssues() throws Exception {
		Integer status = IssueState.COMPLETE;
		legalOrgId();
		setObjectValue(status);
		setControllerProperties();

		String types[] = setIssueTypes().split(",");
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		for (String type : types) {
			if (type != null && !"".equals(type)) {
				IssueType issueType = new IssueType();
				issueType.setId(Long.parseLong(type));
				issueTypes.add(issueType);
			}
		}
		searchIssueVo.setIssueTypes(issueTypes);

		searchIssueController.searchMyCompletedIssues();
		gridPage = searchIssueController.getGridPage();
		List<IssueViewObject> list = gridPage.getRows();
		for (IssueViewObject issueViewObject : list) {
			issueViewObject.setAttachFilesList(issueController
					.getDocfilesByIdAndModuleKey(issueViewObject.getIssueId(),
							AttachFileModule.MODULE_TYPE,
							IssueAttachFileType.ISSUEEVALUATE_FILE));
		}
		return SUCCESS;
	}

	/*
	 * 我的已办列表和查询
	 */
	@Override
	public String searchDoneIssues() throws Exception {
		Integer status = null;
		legalOrgId();
		setObjectValue(status);
		searchIssueVo.setSortField(sidx);// 就按照事件ID排序
		searchIssueVo.setOrder(sord);

		String types[] = setIssueTypes().split(",");
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		for (String type : types) {
			if (type != null && !"".equals(type)) {
				IssueType issueType = new IssueType();
				issueType.setId(Long.parseLong(type));
				issueTypes.add(issueType);
			}
		}
		searchIssueVo.setIssueTypes(issueTypes);

		searchIssueBylevelController.setViewType(IssueViewType.DONE);
		searchIssueBylevelController.setSearchIssueVo(searchIssueVo);
		searchIssueBylevelController.setPage(page);
		searchIssueBylevelController.setRows(rows);
		searchIssueBylevelController.setSidx(sidx);
		searchIssueBylevelController.setSord(sord);
		searchIssueBylevelController.findIssueForView();
		gridPage = searchIssueBylevelController.getGridPage();
		List<IssueViewObject> list = gridPage.getRows();
		for (IssueViewObject issueViewObject : list) {
			if (issueViewObject.getDealState() != null
					&& issueViewObject.getDealState().equals(
							Long.parseLong("" + IssueState.STEPCOMPLETE_CODE))) {
				issueViewObject.setDealState(Long.parseLong(""
						+ IssueState.DEALING_CODE));
			}
		}
		return SUCCESS;
	}

	/*
	 * 下辖待待办列表和查询
	 */
	@Override
	public String findMyJurisdictionsNeedDo() throws Exception {
		Integer status = IssueState.DEALING;
		legalOrgId();
		setObjectValue(status);
		setControllerProperties();
		searchIssueController.searchJurisdictions();
		gridPage = searchIssueController.getGridPage();
		return SUCCESS;
	}

	/*
	 * 下辖待已办列表和查询
	 */
	@Override
	public String findJurisdictionsDoneIssues() throws Exception {
		Integer status = IssueState.COMPLETE;
		legalOrgId();
		setObjectValue(status);
		setControllerProperties();
		searchIssueController.searchJurisdictionsDoneIssues();
		gridPage = searchIssueController.getGridPage();
		return SUCCESS;
	}

	/*
	 * 下辖待已办结列表和查询
	 */
	public String findJurisdictionsCompletedIssues() throws Exception {
		Integer status = null;
		legalOrgId();
		setObjectValue(status);
		setControllerProperties();
		searchIssueController.searchJurisdictionsDoneIssues();
		gridPage = searchIssueController.getGridPage();
		return SUCCESS;
	}

	/*
	 * 查看事件内容
	 */
	@Override
	public String viewIssueDetail() throws Exception {
		issueController.setKeyId(stepId);
		if ("manage".equals(managementMode)) {
			issueController.viewIssueDetail();
		} else {
			issueController.viewSubDetailByIssueId();
		}
		this.issueNew = issueController.getIssue();
		this.issueLogs = issueController.getIssueDealLogs();
		this.canDoList = issueController.getCanDoList();
		this.issueAttachFiles = issueController.getIssueAttachFiles();
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
		List<IssueRelatedPeople> peopleList = issueRelatedPeopleService
				.findIssueRelatedPeopleByIssueId(issueNewId);
		for (IssueRelatedPeople issuePeople : peopleList) {
			issuePeople.setIssue(null);// 因不需要返回此数据，为精简数据，减少流量
		}
		jsonMap.put("issueNew", issueNew);
		jsonMap.put("issueLogs", issueLogs);
		jsonMap.put("issueAttachFiles", issueAttachFiles);
		jsonMap.put("issueRelatedPeople", peopleList);
		return SUCCESS;
	}

	/*
	 * 获取新增事件的事件类别(民生服务、治安隐患和矛盾纠纷)
	 */
	@Override
	public String prepareExistedEnabledIssueType() {
		mobileIssueTypeList = new ArrayList<Map<String, List<MobileIssueType>>>();
		String types[] = { IssueTypes.PEOPLELIVE_SERVICE,
				IssueTypes.RESOLVETHECONTRADICTIONS,
				IssueTypes.SECURITYPRECAUTIONS, IssueTypes.SPECIALPOPULATIONS,
				IssueTypes.SOCIALCONDITIONS, IssueTypes.POLICIESANDLAWS,
				IssueTypes.EMERGENCIES, IssueTypes.OTHERMANAGE };
		for (String type : types) {
			List<MobileIssueType> contradiction = issueTypeService
					.findMobileEnabledIssueTypesByParentName(type);
			Map<String, List<MobileIssueType>> contradictionMap = new HashMap<String, List<MobileIssueType>>();
			contradictionMap.put(type, contradiction);
			mobileIssueTypeList.add(contradictionMap);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: prepareExistedEnabledIssueTypeNew
	 * @Description: TODO获取新增事件的事件类别(民生服务、治安隐患和矛盾纠纷) 新方法
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-5-27 上午11:19:27
	 */
	@Override
	public String prepareExistedEnabledIssueTypeNew() {
		issueTypesVoList = new ArrayList<IssueTypesVo>();
		String types[] = { IssueTypes.PEOPLELIVE_SERVICE,
				IssueTypes.RESOLVETHECONTRADICTIONS,
				IssueTypes.SECURITYPRECAUTIONS, IssueTypes.SPECIALPOPULATIONS,
				IssueTypes.SOCIALCONDITIONS, IssueTypes.POLICIESANDLAWS,
				IssueTypes.EMERGENCIES, IssueTypes.OTHERMANAGE };
		for (String type : types) {
			List<MobileIssueType> contradiction = issueTypeService
					.findMobileEnabledIssueTypesByParentName(type);
			IssueTypeDomain domain = issueTypeService
					.getIssueTypeDoaminByDomainName(type);
			Map<String, List<MobileIssueType>> contradictionMap = new HashMap<String, List<MobileIssueType>>();
			contradictionMap.put(type, contradiction);
			issueTypesVo = new IssueTypesVo();
			if (domain != null) {
				issueTypesVo.setId(domain.getId());
			}
			issueTypesVo.setName(type);
			issueTypesVo.setChildMap(contradictionMap);
			issueTypesVoList.add(issueTypesVo);
		}
		return SUCCESS;
	}

	/*
	 * 新增事件
	 */
	@Override
	public String addIssue() throws Exception {
		if (issueNew == null
				|| !StringUtil.isStringAvaliable(issueNew
						.getUniqueIdForMobile())
				|| issueNew.getOccurOrg() == null
				|| issueNew.getOccurOrg().getId() == null) {
			errorMessage = "请确认必填参数是否正确和完善";
			return ERROR;
		}
		if (issueController.findIssueIfExistForMobile(issueNew)) {
			errorMessage = "该事件已存在，请确认是否重复提交";
			return ERROR;
		}
		String tqmobile = ServletActionContext.getRequest().getParameter(
				"tqmobile");
		if (tqmobile != null && !"".equals(tqmobile)) {
			issueController.setTqmobile(tqmobile);
		}
		/** 大类小类冗余不需要再去解析 */
		// issueController.setSelectedTypes(setSelectedIssueTypes());
		issueController.setIssue(issueNew);
		issueController.setAttachFiles(attachFiles);
		issueController.setAttachFile(attachFile);
		issueController.setIssueRelatedPeopleNames(issueRelatedPeopleNames);
		// issueController
		// .setIssueRelatedPeopleFixPhones(issueRelatedPeopleFixPhones);
		issueController
				.setIssueRelatedPeopleFixPhones(issueRelatedPeopleFixPhones);
		issueController
				.setIssueRelatedPeopleTelephones(issueRelatedPeopleTelephones);
		issueController.setInvolvedPersonnel(involvedPersonnel);
		if (hours == null || minute == null) {
			// appendErrorMessage("小时或分钟参数不正确!");
			// return MOBILE_ERROR;
		}
		issueController.setHours(hours);
		issueController.setMinute(minute);
		String returnStr = issueController.addIssue();
		if (ERROR.equals(returnStr)) {
			errorMessage = issueController.getErrorMessage();
			return ERROR;
		}
		this.issueViewObject = issueController.getIssueVO();
		this.issueNew = new IssueNew();
		issueNew.setId(issueViewObject.getIssueId());
		issueNew.setSerialNumber(issueViewObject.getSerialNumber());
		return SUCCESS;
	}

	/*
	 * 获取事件处理办理所需信息
	 */
	public String dispatchDeal() throws Exception {
		canDoList = new ArrayList<IssueOperate>();
		if (null == stepId) {
			appendErrorMessage("参数不正确!");
			return MOBILE_ERROR;
		}
		issueController.setMode(DialogMode.DEAL);
		issueController.setKeyId(stepId);
		issueController.dispatchDeal();
		jsonMap.put("targetOrgs", issueController.getJsonMap()
				.get("targetOrgs"));
		jsonMap.put("issueSkiprule", issueController.getIssueSkiprule());
		canDoList = issueController.getCanDoList();
		if (null == canDoList || canDoList.size() == 0) {
			if (null == canDoList) {
				canDoList = new ArrayList<IssueOperate>();
			}
			canDoList.add(IssueOperate.parse(IssueOperate.CONCEPT_CODE));
			jsonMap.put("canDoList", canDoList);
		} else {
			jsonMap.put("canDoList", canDoList);
		}
		organization = ThreadVariable.getUser().getOrganization();

		IssueStep step = issueServiceFactory.getIssueServiceBySeries(
				IssueServiceFactory.DEFAULT_SERIES).getIssueStepById(stepId);
		if (!organization.equals(step.getTarget())) {
			appendErrorMessage("参数不正确!");
		}
		if (organization != null && organization.getOrgLevel() != null
				&& organization.getOrgLevel().getInternalId() <= 2) {
			int parentLevelIntId = propertyDictService.getPropertyDictById(
					organization.getOrgLevel().getId()).getInternalId();
			int parentTypeInternalId = propertyDictService.getPropertyDictById(
					organization.getOrgType().getId()).getInternalId();
			if (!(parentLevelIntId == OrganizationLevel.CITY && parentTypeInternalId == OrganizationType.ADMINISTRATIVE_REGION)) {
				canDoList.remove(IssueOperate.ASSIGN);
				jsonMap.put("transferTo", false);
			} else {
				jsonMap.put("transferTo", true);
				transferTo = true;
			}
		}
		// jsonMap.put("reportedDepartment", organization);
		jsonMap.put("stepId", stepId);
		return canDoList.size() > 0 ? "success" : "simpleDeal";
	}

	/*
	 * 事件办理
	 */
	public String dealIssue() throws Exception {
		if (stepId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		if (issueLog.getIssueStep() == null) {
			issueLog.setIssueStep(new IssueStep());
		}
		if (issueLog.getIssueStep() != null
				&& issueLog.getIssueStep().getFourTeams() == null) {
			IssueStep issueStep = issueLog.getIssueStep();
			issueStep.setFourTeams(new FourTeams());
			issueLog.setIssueStep(issueStep);
		}
		if (issueStep == null) {
			issueStep = new IssueStep();
		}
		issueStep.setId(stepId);
		issueController.setIssueStep(issueStep);

		issueController.setOperation(issueLog);
		issueController.setDealCode(dealType);
		issueController.setKeyId(stepId);
		issueController.setAttachFiles(attachFiles);
		issueController.setAttachFile(attachFile);
		issueController.deal();
		errorMessage = issueController.getErrorMessage();
		if (StringUtil.isStringAvaliable(errorMessage)) {
			return ERROR;
		}
		issueViewObject = issueController.getIssueVO();
		return SUCCESS;
	}

	private String dealNull(String str) {
		return str == null ? "" : str;
	}

	private String setIssueTypes() {
		selectedTypes = dealNull(selContradictionId) + ","
				+ dealNull(selSpecialisationId) + ","
				+ dealNull(selPeopleliveServiceId) + "," + dealNull(selSafeId)
				+ "," + dealNull(selSocialOpinionId) + ","
				+ dealNull(selPolicyLawId) + ","
				+ dealNull(selIncidentReportId) + "," + dealNull(selOtherId);
		selectedTypes = selectedTypes.replace(" ", "").replace(",,", ",")
				.replace(",,", ",");
		selectedTypes = ",".equals(selectedTypes.substring(0, 1)) ? selectedTypes
				.substring(1) : selectedTypes;
		if (selectedTypes.substring(selectedTypes.length() - 1,
				(selectedTypes.length())).equals(",")) {
			selectedTypes = selectedTypes.substring(0,
					(selectedTypes.length() - 1));
		}
		return selectedTypes;
	}

	private String setSelectedIssueTypes() {
		selectedTypes = dealNull(selectedTypes) + ","
				+ dealNull(selContradictionId) + ","
				+ dealNull(selSpecialisationId) + ","
				+ dealNull(selPeopleliveServiceId) + "," + dealNull(selSafeId)
				+ "," + dealNull(selSocialOpinionId) + ","
				+ dealNull(selPolicyLawId) + ","
				+ dealNull(selIncidentReportId) + "," + dealNull(selOtherId);
		selectedTypes = selectedTypes.replace(" ", "").replace(",,", ",")
				.replace(",,", ",");
		selectedTypes = ",".equals(selectedTypes.substring(0, 1)) ? selectedTypes
				.substring(1) : selectedTypes;
		if (selectedTypes.substring(selectedTypes.length() - 1,
				(selectedTypes.length())).equals(",")) {
			selectedTypes = selectedTypes.substring(0,
					(selectedTypes.length() - 1));
		}
		return selectedTypes;
	}

	private String legalOrgId() {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			return "";
		}
	}

	private void setObjectValue(Integer status) {
		if (null == searchIssueVo) {
			searchIssueVo = new SearchIssueVoNew();
		}
		Organization org = organizationDubboService.getFullOrgById(orgId);
		searchIssueVo.setOrgLevel(org.getOrgLevel().getId());
		searchIssueVo.setTargeOrgId(orgId);
		searchIssueVo.setStatus(status);
	}

	private void setControllerProperties() {
		searchIssueVo.setSortField(sidx);// 就按照事件ID排序
		searchIssueVo.setOrder(sord);
		searchIssueController.setSearchIssueVo(searchIssueVo);
		searchIssueController.setPage(page);
		searchIssueController.setRows(rows);
		searchIssueController.setSidx(sidx);
		searchIssueController.setSord(sord);
	}

	private PageInfo<IssueNew> emptyPage(int pageSize) {
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

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public SearchIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public List<IssueLogNew> getIssueLogs() {
		return issueLogs;
	}

	public void setIssueLogs(List<IssueLogNew> issueLogs) {
		this.issueLogs = issueLogs;
	}

	public IssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(IssueNew issueNew) {
		this.issueNew = issueNew;
	}

	public List<Map<String, List<MobileIssueType>>> getMobileIssueTypeList() {
		return mobileIssueTypeList;
	}

	public void setMobileIssueTypeList(
			List<Map<String, List<MobileIssueType>>> mobileIssueTypeList) {
		this.mobileIssueTypeList = mobileIssueTypeList;
	}

	public String getSelContradictionId() {
		return selContradictionId;
	}

	public void setSelContradictionId(String selContradictionId) {
		this.selContradictionId = selContradictionId;
	}

	public String getSelSpecialisationId() {
		return selSpecialisationId;
	}

	public void setSelSpecialisationId(String selSpecialisationId) {
		this.selSpecialisationId = selSpecialisationId;
	}

	public String getSelPeopleliveServiceId() {
		return selPeopleliveServiceId;
	}

	public void setSelPeopleliveServiceId(String selPeopleliveServiceId) {
		this.selPeopleliveServiceId = selPeopleliveServiceId;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
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

	public List<IssueType> getPeopleliveService() {
		return peopleliveService;
	}

	public void setPeopleliveService(List<IssueType> peopleliveService) {
		this.peopleliveService = peopleliveService;
	}

	public String getInvolvedPerson() {
		return involvedPerson;
	}

	public void setInvolvedPerson(String involvedPerson) {
		this.involvedPerson = involvedPerson;
	}

	public String getInvolvedPlace() {
		return involvedPlace;
	}

	public void setInvolvedPlace(String involvedPlace) {
		this.involvedPlace = involvedPlace;
	}

	public String getSelectedTypes() {
		return selectedTypes;
	}

	public void setSelectedTypes(String selectedTypes) {
		this.selectedTypes = selectedTypes;
	}

	public IssueViewObject getIssueViewObject() {
		return issueViewObject;
	}

	public void setIssueViewObject(IssueViewObject issueViewObject) {
		this.issueViewObject = issueViewObject;
	}

	public List<IssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<IssueOperate> canDoList) {
		this.canDoList = canDoList;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public IssueStep getStep() {
		return step;
	}

	public void setStep(IssueStep step) {
		this.step = step;
	}

	public IssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(IssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public boolean isTransferTo() {
		return transferTo;
	}

	public void setTransferTo(boolean transferTo) {
		this.transferTo = transferTo;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<IssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(List<IssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public String getManagementMode() {
		return managementMode;
	}

	public void setManagementMode(String managementMode) {
		this.managementMode = managementMode;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	@Override
	public String issueEvaluate() throws Exception {
		issueController.setIssueEvaluate(issueEvaluate);
		issueController.setAttachFiles(createAttachFilesForMobile());
		issueController.evaluate();
		return SUCCESS;
	}

	private String[] createAttachFilesForMobile() {
		String[] fileArrays = null;
		if (null != attachFile && !"".equals(attachFile)) {
			String[] arrStr = attachFile.split(",");
			for (int i = 0; i < arrStr.length; i++) {
				if (null != arrStr[i] && !"".equals(arrStr[i])) {
					issueEvaluateAttachFile += ";," + arrStr[i];
				}
			}
		}
		for (int i = 0; attachFiles != null && i < attachFiles.length; i++) {
			issueEvaluateAttachFile += ";," + attachFiles[i];
		}
		if (null != issueEvaluateAttachFile
				&& !"".equals(issueEvaluateAttachFile)) {
			String[] arrStrs = issueEvaluateAttachFile.split(";");
			fileArrays = new String[arrStrs.length];
			int i = 0;
			for (String str : arrStrs) {
				if (null != str && !"".equals(str)) {
					String fileStrs[] = str.split(",");
					if (fileStrs.length == 2) {
						fileArrays[i] = fileStrs[0] + "," + fileStrs[1];
						i++;
					}
				}
			}
		}
		if (null != fileArrays && fileArrays.length == 1
				&& !fileArrays[0].startsWith(",")) {
			fileArrays[0] = "," + fileArrays[0];
		}
		return fileArrays;
	}

	@Override
	public String findIssueIfExist() throws Exception {
		if (issueController.findIssueIfExistForMobile(issueNew)) {
			return SUCCESS;
		} else if (issueController.getErrorMessage() == null
				|| "".equals(issueController.getErrorMessage())) {
			ServletActionContext.getResponse().setHeader("Error", "");
			return "noexist";
		} else {
			errorMessage = issueController.getErrorMessage();
		}
		return ERROR;
	}

	@Override
	public String findIssueStepByIssueId() throws Exception {
		if (issueId == null) {
			errorMessage = "事件id为空！";
			return MOBILE_ERROR;
		}
		issueController.setIssueId(issueId);
		issueController.findIssueStepByIssueId();
		issueStep = issueController.getIssueStep();
		if (issueStep != null) {
			if (issueStep.getFourTeams() == null
					|| issueStep.getFourTeams().getNames() == null
					|| "".equals(issueStep.getFourTeams().getNames())
					|| issueStep.getFourTeamsTypeID() == null) {
				return "noexist";
			}
			fourTeams = new FourTeams();
			fourTeams.setId(issueStep.getFourTeams().getId());
			fourTeams.setNames(issueStep.getFourTeams().getNames());
			fourTeams.setParentTeamId(issueStep.getFourTeamsTypeID());
		} else {
			return "noexist";
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findVerificationIssue
	 * @Description: TODO查询待验证事件列表
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @throws Exception
	 * @date 2014-8-22 下午02:09:11
	 */
	@Override
	public String findVerificationIssue() throws Exception {
		if (keyId == null || rows == null || page == null || sidx == null
				|| "".equals(sidx) || sord == null || "".equals(sord)
				|| orgLevel == null || leaderView == null
				|| "".equals(leaderView)) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setKeyId(keyId);
		issueController.setPage(page);
		issueController.setRows(rows);
		issueController.setSidx(sidx);
		issueController.setSord(sord);
		issueController.setOrgLevel(orgLevel);
		issueController.setLeaderView(leaderView);
		issueController.findJurisdictionsVerification();
		gridPage = issueController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: searchIssueByIssueVo
	 * @Description: TODO根据不同属性 查询事件
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @throws Exception
	 * @date 2014-8-25 上午10:11:20
	 */
	@Override
	public String searchIssueByIssueVo() throws Exception {
		if (searchIssueVo == null || searchIssueVo.getSortField() == null
				|| "".equals(searchIssueVo.getSortField())
				|| searchIssueVo.getOrder() == null
				|| "".equals(searchIssueVo.getOrder())
				|| searchIssueVo.getOrgLevelInternalId() == null
				|| searchIssueVo.getLeaderView() == null
				|| "".equals(searchIssueVo.getLeaderView()) || viewType == null
				|| "".equals(viewType) || searchIssueVo.getTargeOrgId() == null
				|| rows == null || page == null || sidx == null
				|| "".equals(sidx) || sord == null || "".equals(sord)) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		searchIssueBylevelController.setSearchIssueVo(searchIssueVo);
		searchIssueBylevelController.setViewType(viewType);
		searchIssueBylevelController.setPage(page);
		searchIssueBylevelController.setRows(rows);
		searchIssueBylevelController.setSidx(sidx);
		searchIssueBylevelController.setSord(sord);
		if (issueType != null) {
			searchIssueBylevelController.setIssueType(issueType);
		}
		searchIssueBylevelController.findIssueForView();
		List<IssueViewObject> list = searchIssueBylevelController.getGridPage()
				.getRows();
		for (IssueViewObject issueObj : list) {
			IssueNew issue = issueNewService.getFullIssueById(issueObj
					.getIssueId());
			if (issue != null) {
				issueObj.setIssueContent(issue.getIssueContent());
			}
		}
		gridPage = searchIssueBylevelController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findCheckGradeIssue
	 * @Description: TODO查询待评分事件
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @throws Exception
	 * @date 2014-8-25 下午03:46:30
	 */
	@Override
	public String findCheckGradeIssue() throws Exception {
		if (keyId == null || rows == null || page == null || sidx == null
				|| "".equals(sidx) || sord == null || "".equals(sord)
				|| orgLevel == null || leaderView == null
				|| "".equals(leaderView)) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}

		issueController.setKeyId(keyId);
		issueController.setPage(page);
		issueController.setRows(rows);
		issueController.setSidx(sidx);
		issueController.setSord(sord);
		issueController.setOrgLevel(orgLevel);
		issueController.setLeaderView(leaderView);
		issueController.findJurisdictionsGradeDelay();
		gridPage = issueController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: updateIssueById
	 * @Description: TODO通过id修改事件
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @throws Exception
	 * @date 2014-8-25 下午04:24:39
	 */
	@Override
	public String updateIssueById() throws Exception {
		if (issueNew == null || issueNew.getId() == null || stepId == null
				|| issueNew.getOccurOrg() == null
				|| issueNew.getOccurOrg().getId() == null
				|| issueNew.getSerialNumber() == null
				|| "".equals(issueNew.getSerialNumber())
				|| issueNew.getSubject() == null
				|| "".equals(issueNew.getSubject())
				|| issueNew.getOccurDate() == null
				|| issueNew.getIssueContent() == null
				|| "".equals(issueNew.getIssueContent())) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setIssue(issueNew);
		if (attachfiles != null) {
			attachFiles = attachfiles.split(";");
		}
		issueController.setAttachFiles(attachFiles);
		issueController.setAttachFile(attachFile);
		issueController.setSelectedTypes(selectedTypes);
		issueController.setIssueRelatedPeopleNames(issueRelatedPeopleNames);
		issueController
				.setIssueRelatedPeopleFixPhones(issueRelatedPeopleFixPhones);
		issueController
				.setIssueRelatedPeopleTelephones(issueRelatedPeopleTelephones);
		issueController.setInvolvedPersonnel(involvedPersonnel);
		issueController.setHours(hours);
		issueController.setMinute(minute);
		issueController.setStepId(stepId);
		issueController.updateIssue();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findIssueSkiprule
	 * @Description: TODO查询 越级规则列表
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @throws Exception
	 * @date 2014-8-27 上午09:24:47
	 */
	@Override
	public String findIssueSkiprule() throws Exception {
		if (searchIssueSkipruleVo == null
				|| searchIssueSkipruleVo.getOrgId() == null || rows == null
				|| page == null || sidx == null || "".equals(sidx)
				|| sord == null || "".equals(sord)) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		// 查询传入组织机构是否高于制定越级规则的组织
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN_KEY);
		Organization organization = organizationDubboService
				.getFullOrgById(searchIssueSkipruleVo.getOrgId());
		if (dict == null || dict.getId() == null || organization == null
				|| organization.getOrgLevel() == null
				|| organization.getOrgLevel().getId() == null) {
			errorMessage = "字典项或组织机构查询出错";
			return ERROR;
		}
		if (organization.getOrgLevel().getId() >= dict.getId()) {
			errorMessage = "登录用户层级无越级上报规则";
			return ERROR;
		}
		// 根据传入的组织机构，查询上级（县级，镇级）组织机构
		Organization org = organizationDubboService
				.getOrganizationByIdAndDictName(
						searchIssueSkipruleVo.getOrgId(),
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.DISTRICT_KEY);
		Organization org1 = organizationDubboService
				.getOrganizationByIdAndDictName(
						searchIssueSkipruleVo.getOrgId(),
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN_KEY);

		if (org == null || org.getId() == null || org1 == null
				|| org1.getId() == null) {
			errorMessage = "查询上级(县级，镇级)组织机构出错";
			return ERROR;
		}
		searchIssueSkipruleVo.setOrgId(null);
		searchIssueSkipruleVo.setOrgIds(org.getId() + "," + org1.getId());
		if (organization.getOrgLevel().getDisplayName().equals("村（社区）")) {
			searchIssueSkipruleVo.setOrgId(org.getId());
			searchIssueSkipruleVo.setOrgIds(null);
		}
		issueSkipruleController.setSearchIssueSkipruleVo(searchIssueSkipruleVo);
		issueSkipruleController.setPage(page);
		issueSkipruleController.setRows(rows);
		issueSkipruleController.setSidx(sidx);
		issueSkipruleController.setSord(sord);
		issueSkipruleController.findIssueSkiprulePagerBySearchVo();
		gridPage = issueSkipruleController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findIssueSkipruleByStepId
	 * @Description: TODO通过事件步骤id查询对应的越级规则对象
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-9-4 下午01:44:35
	 */
	@Override
	public String findIssueSkipruleByStepId() {
		if (issueStep == null || issueStep.getId() == null
				|| issueStep.getEmergencyLevel() == null
				|| issueStep.getEmergencyLevel().getId() == null
				|| orgId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		IssueStep newIssueStep = new IssueStep();
		newIssueStep.setEmergencyLevel(issueStep.getEmergencyLevel());
		// 查询传入组织机构是否高于制定越级规则的组织
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.DISTRICT_KEY);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (dict == null || dict.getId() == null || organization == null
				|| organization.getOrgLevel() == null
				|| organization.getOrgLevel().getId() == null) {
			errorMessage = "字典项或组织机构查询出错";
			return ERROR;
		}
		if (organization.getOrgLevel().getId() > dict.getId()) {
			errorMessage = "登录用户层级比制定越级规则用户层级高";
			return ERROR;
		}
		issueStep = getActualIssueServiceInstance().getIssueStepById(
				issueStep.getId());
		issueStep.setEmergencyLevel(newIssueStep.getEmergencyLevel());
		IssueNew issueNew = getActualIssueServiceInstance()
				.getFullIssueByStepId(issueStep.getId());
		issueSkiprule = issueSkipruleService.getIssueSkipruleByIssue(issueNew,
				issueStep);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findCompletedIssueNew
	 * @Description: TODO查询已办结事件 新方法
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @throws Exception
	 * @date 2014-9-9 上午09:45:26
	 */
	@Override
	public String findCompletedIssueNew() throws Exception {
		if (keyId == null || rows == null || page == null || sidx == null
				|| "".equals(sidx) || sord == null || "".equals(sord)
				|| leaderView == null || "".equals(leaderView)
				|| viewType == null || "".equals(viewType)
				|| orgLevelInternalId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setKeyId(keyId);
		issueController.setPage(page);
		issueController.setRows(rows);
		issueController.setSidx(sidx);
		issueController.setSord(sord);
		issueController.setViewType(viewType);
		issueController.setOrgLevelInternalId(orgLevelInternalId);
		issueController.setLeaderView(leaderView);
		issueController.setStatusType(statusType);
		issueController.findIssueForView();
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
			List<IssueViewObject> list = issueController.getGridPage()
					.getRows();
			for (IssueViewObject completedIssue : list) {
				IssueNew issue = issueNewService
						.getFullIssueById(completedIssue.getIssueId());
				if (issue != null) {
					completedIssue.setIssueContent(issue.getIssueContent());
				}
				IssueEvaluate issueEvaluate = issueEvaluateService
						.getIssueEvaluateByIssueIdForGrade(completedIssue
								.getIssueId());
				if (issueEvaluate != null) {
					completedIssue.setEvaluateTime(issueEvaluate
							.getEvaluateTime());
					completedIssue.setEvaluateContent(issueEvaluate
							.getEvaluateContent());
					completedIssue.setEvaluateType(issueEvaluate
							.getEvaluateType());
				}
			}
		} else {
			List<CompletedIssue> list = issueController.getGridPage().getRows();
			for (CompletedIssue completedIssue : list) {
				IssueNew issue = issueNewService
						.getFullIssueById(completedIssue.getIssueId());
				if (issue != null) {
					completedIssue.setIssueContent(issue.getIssueContent());
				}
				IssueEvaluate issueEvaluate = issueEvaluateService
						.getIssueEvaluateByIssueIdForGrade(completedIssue
								.getIssueId());
				if (issueEvaluate != null) {
					completedIssue.setEvaluateTime(issueEvaluate
							.getEvaluateTime());
					completedIssue.setEvaluateContent(issueEvaluate
							.getEvaluateContent());
					completedIssue.setEvaluateType(issueEvaluate
							.getEvaluateType());
				}
			}
		}
		gridPage = issueController.getGridPage();
		return SUCCESS;
	}

	@Override
	public String verificationIssueById() throws Exception {
		if (issueLog == null || issueLog.getDealOrg() == null
				|| issueLog.getDealOrg().getId() == null
				|| issueLog.getIssue() == null
				|| issueLog.getIssue().getId() == null || keyId == null
				|| !StringUtil.isStringAvaliable(issueLog.getDealUserName())
				|| !StringUtil.isStringAvaliable(issueLog.getMobile())
				|| !StringUtil.isStringAvaliable(issueLog.getContent())
				|| issue == null || issue.getOccurDate() == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setAttachFiles(attachFiles);
		issueController.setAttachFile(attachFile);
		issueController.setOperation(issueLog);
		issueController.setKeyId(keyId);
		issueController.setDealCode(dealCode);
		issueController.setIssue(issue);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findVerificationCanDoList
	 * @Description: TODO查询待验证中办理功能的操作类型
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-14 下午04:23:48
	 */
	@Override
	public String findVerificationCanDoList() throws Exception {
		if (!StringUtil.isStringAvaliable(mode) || keyId == null
				|| issuesKeyId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setKeyId(keyId);
		issueController.setIssuesKeyId(issuesKeyId);
		issueController.setMode(mode);
		issueController.dispatchDeal();
		canDoList = issueController.getCanDoList();
		jsonMap.put("canDoList", canDoList);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: dealIssueByVerificationType
	 * @Description: TODO待验证列表中办理的方法
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-14 下午05:35:55
	 */
	@Override
	public String dealIssueByVerificationType() throws Exception {
		if (issueLog == null || issueLog.getDealOrg() == null
				|| issueLog.getDealOrg().getId() == null
				|| issueLog.getIssue() == null
				|| issueLog.getIssue().getId() == null || keyId == null
				|| !StringUtil.isStringAvaliable(issueLog.getDealUserName())
				|| !StringUtil.isStringAvaliable(issueLog.getMobile())
				|| !StringUtil.isStringAvaliable(issueLog.getContent())
				|| issue == null || issue.getOccurDate() == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setAttachFiles(attachFiles);
		issueController.setAttachFile(attachFile);
		issueController.setOperation(issueLog);
		issueController.setKeyId(keyId);
		issueController.setDealCode(dealCode);
		issueController.setIssue(issue);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: publicltyCassForMobile
	 * @Description: TODO设置宣传案例
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-12-3 上午09:37:08
	 */
	public String publicltyCassForMobile() throws Exception {
		if (keyId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setKeyId(keyId);
		issueController.publicltyCass();
		issueViewObject = issueController.getIssueVO();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findPublicltyCassListForMobile
	 * @Description: TODO获取事件列表
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-12-3 上午10:26:49
	 */
	public String findIssueForViewListForMobile() throws Exception {
		if (!StringUtil.isStringAvaliable(viewType)
				|| orgLevelInternalId == null || judgeFourValues()
				|| !StringUtil.isStringAvaliable(leaderView) || keyId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setSuperviseType(superviseType);
		issueController.setViewType(viewType);
		issueController.setOrgLevelInternalId(orgLevelInternalId);
		issueController.setPage(page);
		issueController.setRows(rows);
		issueController.setSord(sord);
		issueController.setSidx(sidx);
		issueController.setLeaderView(leaderView);
		issueController.setKeyId(keyId);
		issueController.findIssueForView();
		gridPage = issueController.getGridPage();
		if (StringUtil.isStringAvaliable(viewType)
				&& IssueViewType.TIMEOUT.equalsIgnoreCase(viewType)) {
			List<IssueViewObject> list = issueController.getGridPage()
					.getRows();
			for (IssueViewObject issueObj : list) {
				IssueNew issue = issueNewService.getFullIssueById(issueObj
						.getIssueId());
				if (issue != null) {
					issueObj.setIssueContent(issue.getIssueContent());
				}
			}
		}
		return SUCCESS;
	}

	@Override
	public String findJurisdictionsAuditDelayCountForMobile() throws Exception {
		if (orgLevelInternalId == null || orgId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueApplyDelayController.setFunctionalOrgType(functionalOrgType);
		issueApplyDelayController.setOrgId(orgId);
		issueApplyDelayController.setOrgLevelInternalId(orgLevelInternalId);
		issueApplyDelayController.getJurisdictionsAuditDelayCount();
		auditDelayCount = issueApplyDelayController.getAuditDelayCount();
		return SUCCESS;
	}

	/**
	 * 获取待办事件个数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String findJurisdictionsNeedDoCountForMobile() throws Exception {
		if (orgLevelInternalId == null || orgId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueApplyDelayController.setFunctionalOrgType(functionalOrgType);
		issueApplyDelayController.setOrgLevelInternalId(orgLevelInternalId);
		issueApplyDelayController.setOrgId(orgId);
		issueApplyDelayController.getJurisdictionsNeedDoCountForMobile();
		needDoCount = issueApplyDelayController.getAuditDelayCount();
		return SUCCESS;
	};

	/**
	 * 获取督办事件个数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String findOverseerIssueCountForMobile() throws Exception {
		if (orgLevelInternalId == null || orgId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueApplyDelayController.setFunctionalOrgType(functionalOrgType);
		issueApplyDelayController.setOrgLevelInternalId(orgLevelInternalId);
		issueApplyDelayController.setOrgId(orgId);
		issueApplyDelayController.getOverseerIssueCountForMobile();
		overseerIssueCount = issueApplyDelayController.getAuditDelayCount();
		return SUCCESS;
	};

	/**
	 * 获取督办事件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String findOverseerIssueListForMobile() throws Exception {

		if (!StringUtil.isStringAvaliable(viewType)
				|| orgLevelInternalId == null || judgeFourValues()
				|| !StringUtil.isStringAvaliable(leaderView) || keyId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		issueController.setViewType(viewType);
		issueController.setOrgLevelInternalId(orgLevelInternalId);
		issueController.setPage(page);
		issueController.setRows(rows);
		issueController.setSord(sord);
		issueController.setSidx(sidx);
		issueController.setLeaderView(leaderView);
		issueController.setKeyId(keyId);
		issueController.findIssueForView();
		issueController.getOverseerIssueListForMobile();

		gridPage = issueController.getGridPage();
		return SUCCESS;
	};

	private boolean judgeFourValues() {
		boolean flag = false;
		if (rows == null || page == null || !StringUtil.isStringAvaliable(sidx)
				|| !StringUtil.isStringAvaliable(sord)) {
			flag = true;
		}
		return flag;
	}

	private IssueService getActualIssueServiceInstance() {
		return issueServiceFactory.getIssueServiceBySeries(getSeries());
	}

	protected String getSeries() {
		return SYS_SERIES;
	}

	public IssueEvaluate getIssueEvaluate() {
		return issueEvaluate;
	}

	public void setIssueEvaluate(IssueEvaluate issueEvaluate) {
		this.issueEvaluate = issueEvaluate;
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

	public String getIssueRelatedPeopleNames() {
		return issueRelatedPeopleNames;
	}

	public void setIssueRelatedPeopleNames(String issueRelatedPeopleNames) {
		this.issueRelatedPeopleNames = issueRelatedPeopleNames;
	}

	public String getIssueRelatedPeopleTelephones() {
		return issueRelatedPeopleTelephones;
	}

	public void setIssueRelatedPeopleTelephones(
			String issueRelatedPeopleTelephones) {
		this.issueRelatedPeopleTelephones = issueRelatedPeopleTelephones;
	}

	public String getInvolvedPersonnel() {
		return involvedPersonnel;
	}

	public void setInvolvedPersonnel(String involvedPersonnel) {
		this.involvedPersonnel = involvedPersonnel;
	}

	@Override
	public String findIssueBigTypes() {
		String types[] = { IssueTypes.PEOPLELIVE_SERVICE,
				IssueTypes.RESOLVETHECONTRADICTIONS,
				IssueTypes.SECURITYPRECAUTIONS, IssueTypes.SPECIALPOPULATIONS,
				IssueTypes.SOCIALCONDITIONS, IssueTypes.POLICIESANDLAWS,
				IssueTypes.EMERGENCIES, IssueTypes.OTHERMANAGE };
		issueBigTypes = new ArrayList<PropertyDict>();
		for (String type : types) {
			IssueTypeDomain itd = issueTypeService
					.getIssueTypeDoaminByDomainName(type);
			if (itd != null) {
				PropertyDict pd = new PropertyDict();
				pd.setId(itd.getId());
				pd.setDisplayName(itd.getDomainName());
				issueBigTypes.add(pd);
			}
		}
		return SUCCESS;
	}

	public List<PropertyDict> getIssueBigTypes() {
		return issueBigTypes;
	}

	public void setIssueBigTypes(List<PropertyDict> issueBigTypes) {
		this.issueBigTypes = issueBigTypes;
	}

	public String getIssueRelatedPeopleFixPhones() {
		return issueRelatedPeopleFixPhones;
	}

	public void setIssueRelatedPeopleFixPhones(
			String issueRelatedPeopleFixPhones) {
		this.issueRelatedPeopleFixPhones = issueRelatedPeopleFixPhones;
	}

	public String getIssueEvaluateAttachFile() {
		return issueEvaluateAttachFile;
	}

	public void setIssueEvaluateAttachFile(String issueEvaluateAttachFile) {
		this.issueEvaluateAttachFile = issueEvaluateAttachFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IssueTypesVo getIssueTypesVo() {
		return issueTypesVo;
	}

	public void setIssueTypesVo(IssueTypesVo issueTypesVo) {
		this.issueTypesVo = issueTypesVo;
	}

	public List<IssueTypesVo> getIssueTypesVoList() {
		return issueTypesVoList;
	}

	public void setIssueTypesVoList(List<IssueTypesVo> issueTypesVoList) {
		this.issueTypesVoList = issueTypesVoList;
	}

	public IssueStep getIssueStep() {
		return issueStep;
	}

	public void setIssueStep(IssueStep issueStep) {
		this.issueStep = issueStep;
	}

	public FourTeams getFourTeams() {
		return fourTeams;
	}

	public void setFourTeams(FourTeams fourTeams) {
		this.fourTeams = fourTeams;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getLeaderView() {
		return leaderView;
	}

	public void setLeaderView(String leaderView) {
		this.leaderView = leaderView;
	}

	public Long getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(Long functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Integer getViewProcess() {
		return viewProcess;
	}

	public void setViewProcess(Integer viewProcess) {
		this.viewProcess = viewProcess;
	}

	public Long getSourceType() {
		return sourceType;
	}

	public void setSourceType(Long sourceType) {
		this.sourceType = sourceType;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public SearchIssueSkipruleVo getSearchIssueSkipruleVo() {
		return searchIssueSkipruleVo;
	}

	public void setSearchIssueSkipruleVo(
			SearchIssueSkipruleVo searchIssueSkipruleVo) {
		this.searchIssueSkipruleVo = searchIssueSkipruleVo;
	}

	public Long getIssueNewId() {
		return issueNewId;
	}

	public void setIssueNewId(Long issueNewId) {
		this.issueNewId = issueNewId;
	}

	public IssueSkiprule getIssueSkiprule() {
		return issueSkiprule;
	}

	public void setIssueSkiprule(IssueSkiprule issueSkiprule) {
		this.issueSkiprule = issueSkiprule;
	}

	public Integer getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Integer orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public int getDealCode() {
		return dealCode;
	}

	public void setDealCode(int dealCode) {
		this.dealCode = dealCode;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public Long getIssuesKeyId() {
		return issuesKeyId;
	}

	public void setIssuesKeyId(Long issuesKeyId) {
		this.issuesKeyId = issuesKeyId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getAuditDelayCount() {
		return auditDelayCount;
	}

	public void setAuditDelayCount(int auditDelayCount) {
		this.auditDelayCount = auditDelayCount;
	}

	public int getOverseerIssueCount() {
		return overseerIssueCount;
	}

	public void setOverseerIssueCount(int overseerIssueCount) {
		this.overseerIssueCount = overseerIssueCount;
	}

	public int getNeedDoCount() {
		return needDoCount;
	}

	public void setNeedDoCount(int needDoCount) {
		this.needDoCount = needDoCount;
	}

	public int getSuperviseType() {
		return superviseType;
	}

	public void setSuperviseType(int superviseType) {
		this.superviseType = superviseType;
	}

	public String getAttachfiles() {
		return this.attachfiles;
	}

	public void setAttachfiles(String attachfiles) {
		this.attachfiles = attachfiles;
	}

}