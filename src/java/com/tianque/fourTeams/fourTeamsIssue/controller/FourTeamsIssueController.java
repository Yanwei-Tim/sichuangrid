package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.approval.service.ApprovalItemFileService;
import com.tianque.approval.service.ItemService;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.fourTeams.controller.FourTeamsControllerHelper;
import com.tianque.fourTeams.fourTeamsIssue.approval.service.FourTeamsApprovalItemService;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueConstants;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueViewType;
import com.tianque.fourTeams.fourTeamsIssue.controller.strategy.FourTeamsIssueManageStrategy;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccord;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccords;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueRelatedPeople;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsTopIssue;
import com.tianque.fourTeams.fourTeamsIssue.factory.FourTeamsIssueManageStrategyFactory;
import com.tianque.fourTeams.fourTeamsIssue.factory.FourTeamsIssueServiceFactory;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueEvaluateService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueRelatedPeopleService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSkipruleService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueStandardForFunOrgService;
import com.tianque.fourTeams.fourTeamsIssue.service.SearchFourTeamsIssueStepService;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueTypeViewDefine;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.fourTeams.service.FourTeamsIssueLogService;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;
import com.tianque.plugin.weChat.domain.sendMessage.text.TextSendMessage;
import com.tianque.plugin.weChat.service.InboxAttachmentService;
import com.tianque.plugin.weChat.service.InboxService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.service.IssueTypeService;
import com.tianque.service.RegradedPointService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("fourTeamsIssueController")
@Scope("prototype")
@Namespace("/fourTeamsIssueManage")
public class FourTeamsIssueController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private FourTeamsIssueServiceFactory issueServiceFactory;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private FourTeamsIssueManageStrategyFactory factory;
	@Autowired
	private FourTeamsIssueService issueService;
	@Autowired
	private FourTeamsIssueEvaluateService issueEvaluateService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private FourTeamsApprovalItemService approvalItemService;
	@Autowired
	private ApprovalItemFileService approvalItemFileService;
	@Autowired
	private FourTeamsIssueLogService issueLogService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private FourTeamsIssueStandardForFunOrgService issueStandardForFunOrgService;
	@Autowired
	private FourTeamsIssueSkipruleService issueSkipruleService;

	@Autowired
	private FourTeamsIssueRelatedPeopleService issueRelatedPeopleService;

	@Autowired
	private SearchFourTeamsIssueStepService searchIssueStepService;

	@Autowired
	private InboxAttachmentService inboxAttachmentService;
	@Autowired
	private InboxService inboxService;

	private IssueType issueHasType;

	private static Logger logger = LoggerFactory
			.getLogger(FourTeamsIssueController.class);

	private final static String SYS_SERIES = FourTeamsIssueServiceFactory.DEFAULT_SERIES;
	/** 事件处理记录 日志 */
	private List<FourTeamsIssueLogNew> issueLogs;
	/** 事件实体类 用于后台保存事件数据 */
	private FourTeamsIssueNew issue;
	/** 事件实体类 用于前 台显示事件的相关数据 */
	private FourTeamsIssueViewObject issueVO;
	/** 根据操作不同 可能是事件id、orgid、事件处理步骤id(issueStepId) */
	private Long keyId;
	/** 事件类型：民生服务的子类型 */
	private List<IssueType> contradiction;
	/** 事件类型：治安、安全隐患的子类型 */
	private List<IssueType> specialisation;
	/** 事件类型：其他的子类型 */
	private List<IssueType> otherType;
	/** 事件类型：矛盾纠纷的子类型 */
	private List<IssueType> peopleliveService;
	/** 事件类型：服务审批的子类型 */
	private List<IssueType> itemIssueService;
	/** 事件类型:矛盾化解 */
	private List<IssueType> resolveTheContradictions;
	/** 事件类型：治安防范的子类型 */
	private List<IssueType> securityPrecautions;
	/** 事件类型：特殊人群服务管理报告的子类型 */
	private List<IssueType> specialPopulations;
	/** 事件类型：社情民意收集的子类型 */
	private List<IssueType> socialConditions;
	/** 事件类型：政策法律宣传的子类型 */
	private List<IssueType> policiesAndLaws;
	/** 事件类型：突发事件报告的子类型 */
	private List<IssueType> emergencies;

	/** 事件处理记录实体类 */
	private FourTeamsIssueLogNew operation;
	/** 事件分类 key是大类名称(民生服务、治安、安全隐...) value是每个大类的下面的小类 用于在页面上显示每个大类下小类 * */
	private Map<String, List<IssueType>> issueTypes;
	/** 事件分类的大类名称 (民生服务、治安、安全隐...) 用于页面上显示分类名称 * */
	private List<FourTeamsIssueTypeViewDefine> issueTypeNames;
	/** 事件已经选择的所属类型的名称 */
	private List<String> issueHasTypeDomainName;
	private String moduleName;
	/** 选择的事件类型id 如果有多个类型中间以分号分割 */
	private String selectedTypes;
	/** 从页面上提交过来的附件文件id(修改事件的时候)和名称 eg: id,fileName */
	private String[] attachFiles;
	/** 事件中包含的附件集合 用于在页面显示附件 */
	private List<FourTeamsIssueAttachFile> issueAttachFiles = new ArrayList<FourTeamsIssueAttachFile>();
	/** 事件的处理记录 用于在页面上显示处理记录 */
	private List<FourTeamsIssueLogNew> issueDealLogs;
	/** 事件处办理过程中添加的附件 用于页面显示 */
	private Map<Long, List<FourTeamsIssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<FourTeamsIssueAttachFile>>();
	private List<FourTeamsIssueAttachFile> issueEvaluateAttachFiles = new ArrayList<FourTeamsIssueAttachFile>();
	/** 可以对事件进行办理的操作列表 */
	private List<FourTeamsIssueOperate> canDoList;
	/** 事件处理操作类型代码， 具体含义定义在IssueOperate中 */
	private int dealCode;
	/** 事件处理步骤的id */
	private long stepId;
	/** 事件办理时的抄告单位的id */
	private String tellOrgIds;
	/** 选择主办单位或抄告单位时 以已经选择的部门id (在候选结果里要排除这些已经选择部门) */
	private String exceptIds;
	/** 主办单位或抄告单位联想输入时输入的关键字 */
	private String tag;
	/** 是否是行政单位(综治办) 事件办理时选择主办单位、抄告单位时会用到 */
	private boolean adminTarget;
	private FourTeamsIssueManageStrategy strategy;
	/** 服务审批号 */
	private String approvalNumber;
	private Long issueType;
	/** 事件验证实体类 */
	private FourTeamsIssueEvaluate issueEvaluate;
	/** 获取到的事件处理记录的图表视图数据 用于前台组件生事件处理记录成图表 */
	private List<FourTeamsIssueMap> data;
	/** 事件置顶操作 用来封装事件的id、orgid和 事件子模块(待办事项、已办事项、已办结事项、历史遗留....) */
	private FourTeamsTopIssue topIssue;
	/** 用来封装 在事件处理记录图表视图查看部门处理记录时的提交数据 */
	private FourTeamsIssueMap issueMap;
	private Map<String, Object> jsonMap = new HashMap<String, Object>();
	private Long id;
	/** 服务审批事项 */
	private ApprovalItem approvalItem;
	/** 申请事项的附件 */
	private List<ApprovalItemFile> approvalItemFileList = new ArrayList<ApprovalItemFile>();
	/** 手机端附件名称 */
	private String attachFile;
	/** 小时 */
	private String hours;
	/** 分钟 */
	private String minute;

	// 涉及重点场所
	private String involvedPlace;
	// 涉及重点人员
	private String involvedPersonnel;
	// 是否重点场所
	private Boolean isInvolvedPlace;
	// 下辖事项动态流程列表分类
	private String processType;
	private Map<String, List> relatePlaces = new HashMap<String, List>();
	private Map<String, List> relatePersons = new HashMap<String, List>();
	private Map<String, String> emphas = new HashMap<String, String>();
	private boolean checkOccurOrgIdIsTown;
	private Map<String, List<EmphasesVo>> relatePersonMap;
	private Map<String, List<EmphasesVo>> relatePlacesMap;
	private Long funOrgId;
	private Map<Long, String> itemTypes;
	private String type;
	private String viewType;// 视图类型

	private Long orgLevel;
	private Integer orgLevelInternalId;
	private String leaderView;
	private Long functionalOrgType;// 职能部门类型
	private Long sourceType; // 事件来源
	private Integer sourceTypeInternalId;

	private FourTeamsIssueAccord issueAccord;// 考核打分
	private FourTeamsIssueAccords issueAccords;// 考核打分

	private String issueRelatedPeopleNames;// 相关人员姓名
	private String issueRelatedPeopleTelephones;// 相关人员手机
	private String issueRelatedPeopleFixPhones;// 相关人员手机

	private FourTeamsIssueSkiprule issueSkiprule;// 越级设置

	private FourTeamsIssueLogNew issueLogForEdit;
	private String statusType;

	private Integer viewProcess;// 是否是查询大屏滚动的数据

	private String selectedIssueType;

	private Date date;
	private String createOrgByTencent;
	private List<Inbox> map = new ArrayList<Inbox>();
	private List<InboxAttachment> inboxAttachmentList;
	private List<InboxAttachment> mapAtt = new ArrayList<InboxAttachment>();
	private String inboxIds;
	private Long inboxId;
	private Inbox inbox;
	private TextSendMessage textSendMessage;

	private String seachValue;// 四支队伍查询本级下辖
	private String fourTeamsType;// 四支队伍查询队伍类型

	@Action(value = "findItemTypeByDealOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"itemTypes", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findItemTypeByDealOrgId() throws Exception {
		List<FourTeamsIssueStandardForFunOrg> issueStandardForFunOrgs = issueStandardForFunOrgService
				.findItemTypeByFunOrgId(funOrgId);

		itemTypes = new HashMap<Long, String>();
		for (FourTeamsIssueStandardForFunOrg issueStandardForFunOrg : issueStandardForFunOrgs) {
			PropertyDict itemType = propertyDictService
					.getPropertyDictById(issueStandardForFunOrg.getItemName()
							.getId());
			itemTypes.put(itemType.getId(), itemType.getDisplayName());
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "dispatch", results = {
					@Result(name = "addIssue", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsMaintainIssueDlg.jsp"),
					@Result(name = "eventsToAccept", location = "/fourTeamsManage/fourTeamsIssueManage/otherModule/maintainInboxDlgs.jsp"),
					@Result(name = "editIssueForTab", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsIssueEditTabList.jsp"),
					@Result(name = "editIssue", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsMaintainIssueDlg.jsp"),
					@Result(name = "editIssueForView", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsMaintainIssueForViewDlg.jsp"),
					@Result(name = "editApproveItemIssue", location = "/approval/approvalItem/updateApprovalItemToIssueDlg.jsp"),
					@Result(name = "searchOrgs", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/deals/fourTeamsSearchOrgDlg.jsp"),
					@Result(name = "search", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsSearchIssueDlg.jsp"),
					@Result(name = "viewIssue", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/viewIssueDlg.jsp"),
					@Result(name = "viewIssueNew", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsViewIssueNewDlg.jsp"),
					@Result(name = "editIssueProcessRecord", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsEditIssueProcessRecordDlg.jsp"),
					@Result(name = "editIssueLog", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsEditIssueLogDlg.jsp"),
					@Result(name = "convertToWorkingRecord", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/completedIssueConvertToWorkingRecordDlg.jsp") }),
			@Action(value = "dispatchForGis", results = { @Result(name = "viewIssue", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/viewIssueDlg.jsp") }),
			@Action(value = "dispatchforBench", results = { @Result(name = "addIssue", location = "/workBench/issueManage/maintainIssueDlg.jsp") }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return forwardToAdd();
		} else if ("eventsToAccept".equals(mode)) {
			return forwardToAddByTencent();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return forwardToEditIssue(mode);
		} else if ("editIssueForTab".equalsIgnoreCase(mode)) {
			return forwardToEditIssue(mode);
		} else if ("searchTarget".equalsIgnoreCase(mode)
				|| "searchTells".equalsIgnoreCase(mode)) {
			return "searchOrgs";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			// prepareExistedIssueTypes();
			return "search";
		} else if ("viewNew".equals(mode)) {
			// 新的路径
			return forwardToViewNew();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return forwardToView();
			// 结案事件转为台帐
		} else if ("convertToWorkingRecord".equalsIgnoreCase(mode)) {
			return forwardToWorkingRecord();
		} else if ("editIssueProcessRecord".equals(mode)) {
			return forwardToEditIssue(mode);
		} else if ("editIssueLog".equalsIgnoreCase(mode)) {
			issueLogForEdit = issueLogService
					.getFullIssueLogById(issueLogForEdit.getId());
			issueAttachFiles = getActualIssueServiceInstance()
					.loadIssueAttachFilesByIssueIdAndIssueLogId(issue.getId(),
							issueLogForEdit.getId());
			return "editIssueLog";
		}
		return "view";
	}

	private String forwardToEditIssue(String mode) {
		if ("editIssueForTab".equalsIgnoreCase(mode)) {
			if (!hasPermission(null, keyId)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			return "editIssueForTab";
		} else if ("editIssueProcessRecord".equals(mode)) {
			if (!hasPermission(keyId, null)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			forwardToViewNew();
			return "editIssueProcessRecord";
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (!hasPermission(null, keyId)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			return forwardToEdit();
		}
		return ERROR;
	}

	private String forwardToViewNew() {
		if (legalKeyIdParam()) {
			Long num = 0L;
			if (issuesKeyId > 0) {
				num = keyId;
				keyId = issuesKeyId;
			}
			if (!hasPermission(keyId, null)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			issue.getOccurOrg().setOrgName(
					FourTeamsControllerHelper.getOrganizationRelativeName(issue
							.getOccurOrg().getId(), organizationDubboService));
			loadMainCharacters();
			loadRelatePersons();
			loadIssueTypeAndIssueTypeDomain();
			loadAttachFiles(issue);
			// 查看时 增加查看事件记录
			loadIssueOperationLogs(issue);
			mobileCompleValue();
			issueEvaluate = getActualIssueServiceInstance()
					.getIssueEvaluateById(keyId);
			keyId = num;
			return "viewIssueNew";
		} else {
			return ERROR;
		}
	}

	private void loadRelatePersons() {
		List<EmphasesVo> vos = issueTypeService.findRelatePersonByName(issue
				.getId());
		StringBuffer buffer = new StringBuffer();
		for (EmphasesVo vo : vos) {
			if (vo != null) {
				buffer.append(vo.getName()).append(";");
			}
		}
		involvedPersonnel = buffer.length() == 0 ? "" : buffer.toString()
				.substring(0, buffer.length() - 1);
		issue.setInvolvedPersonnel(involvedPersonnel);
	}

	/** 加载和事件相关的人员信息包括（主要当事人和相关人员） */
	private void loadMainCharacters() {
		List<FourTeamsIssueRelatedPeople> issueRelatedPeoples = issueRelatedPeopleService
				.findIssueRelatedPeopleByIssueId(issue.getId());
		StringBuffer buffer = new StringBuffer();
		for (FourTeamsIssueRelatedPeople issueRelatedPeople : issueRelatedPeoples) {
			if (issueRelatedPeople != null) {
				if (StringUtil.isStringAvaliable(issueRelatedPeople.getName())) {
					buffer.append(issueRelatedPeople.getName());
					if (StringUtil.isStringAvaliable(issueRelatedPeople
							.getTelephone())
							&& StringUtil.isStringAvaliable(issueRelatedPeople
									.getFixPhone())) {
						buffer.append("：")
								.append(issueRelatedPeople.getTelephone())
								.append("，")
								.append(issueRelatedPeople.getFixPhone())
								.append("；");
					} else if (StringUtil.isStringAvaliable(issueRelatedPeople
							.getTelephone())
							&& !StringUtil.isStringAvaliable(issueRelatedPeople
									.getFixPhone())) {
						buffer.append("：")
								.append(issueRelatedPeople.getTelephone())
								.append("；");
					} else if (!StringUtil.isStringAvaliable(issueRelatedPeople
							.getTelephone())
							&& StringUtil.isStringAvaliable(issueRelatedPeople
									.getFixPhone())) {
						buffer.append("：")
								.append(issueRelatedPeople.getFixPhone())
								.append("；");
					} else {
						buffer.append("；");
					}
				}
			}
		}
		issue.setMainCharacters(buffer.length() == 0 ? "" : buffer.toString()
				.substring(0, buffer.length() - 1));
	}

	private void loadIssueTypeAndIssueTypeDomain() {
		IssueType issueType = issueTypeService.getIssueTypeByIssueId(issue
				.getId());
		IssueTypeDomain issueTypeDomain = issueTypeService
				.getIssueTypeDomainById(issueType.getIssueTypeDomain().getId());
		issueType = issueTypeService.getIssueTypeById(issueTypeDomain.getId(),
				issueType.getId());
		issue.setIssueTypeDomainName(issueTypeDomain.getDomainName());
		issue.setIssueTypeName(issueType.getIssueTypeName());
	}

	private String forwardToAdd() {
		FourTeamsIssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			issue = new FourTeamsIssueNew();
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			return strategy.forwardToAdd();
		} else {
			return "error";
		}
	}

	private String forwardToAddByTencent() {
		FourTeamsIssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			issue = new FourTeamsIssueNew();
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			date = null;
			for (int i = 0; i < Arrays.asList(analyzePopulationIds()).size(); i++) {
				inbox = inboxService.getInboxById(Arrays.asList(
						analyzePopulationIds()).get(i));
				createOrgByTencent = organizationDubboService.getSimpleOrgById(
						inbox.getOrg().getId()).getOrgName();
				if (date == null) {
					date = inbox.getCreateTime();
				}
				issue = new FourTeamsIssueNew();
				issue.setOccurOrg(inbox.getOrg());
				map.add(inbox);
				inboxAttachmentList = inboxAttachmentService
						.getInboxAttachmentByInboxId(Arrays.asList(
								analyzePopulationIds()).get(i));
				if (inboxAttachmentList != null) {
					for (int j = 0; j < inboxAttachmentList.size(); j++) {

						mapAtt.add(inboxAttachmentList.get(j));
					}
				}
			}
			return "eventsToAccept";
		} else {
			return "error";
		}
	}

	private String forwardToEdit() {
		if (legalKeyIdParam()) {
			FourTeamsIssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			loadAttachFiles(issue);
			// 服务审批
			// if (issue.isIssueType()) {
			// approvalItem = approvalItemService
			// .getApprovalItemByApprovalNumber(issue
			// .getSerialNumber());
			// mode = "editIssue";
			// return "editApproveItemIssue";
			// }

			loadFullIssueTypes(issue);
			loadIssueRelatedPeople(issue);
			if (null != issue.getEmergencyLevel())
				issue.setEmergencyLevel(propertyDictService
						.getPropertyDictById(issue.getEmergencyLevel().getId()));
			issueHasType = issue.getIssueTypes().get(0);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			relatePersonMap = getActualIssueServiceInstance().findRelatePerson(
					issue.getId());
			relatePlacesMap = getActualIssueServiceInstance().findRelatePlace(
					issue.getId());
			if (ThreadVariable.getUser().isAdmin()) {
				return strategy.forwardToEdit();
			} else {
				if (ThreadVariable.getUser().getOrganization().getId()
						.equals(issue.getCreateOrg().getId())
						&& getActualIssueServiceInstance()
								.getIssueStepCountByIssueId(issue.getId()) == 1) {
					return strategy.forwardToEdit();
				} else {
					return "editIssueForView";
				}
			}
		} else {
			return "error";
		}
	}

	// 获取相关人员姓名和电话
	private void loadIssueRelatedPeople(FourTeamsIssueNew issue) {
		issue.setIssueRelatedPeoples(issueRelatedPeopleService
				.findIssueRelatedPeopleByIssueId(issue.getId()));
	}

	private String forwardToView() {
		if (legalKeyIdParam()) {
			// 处理事件ID。大于0代表是处理里的查询。
			Long num = 0L;
			if (issuesKeyId > 0) {
				num = keyId;
				keyId = issuesKeyId;
			}
			FourTeamsIssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			issue.getOccurOrg().setOrgName(
					FourTeamsControllerHelper.getOrganizationRelativeName(issue
							.getOccurOrg().getId(), organizationDubboService));
			loadFullIssueTypes(issue);
			/** 加载相关人员信息 */
			loadMainCharacters();
			loadIssueRelatedPeople(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			relatePersonMap = getActualIssueServiceInstance().findRelatePerson(
					issue.getId());
			loadAttachFiles(issue);
			// 查看时 增加查看事件记录
			loadIssueOperationLogs(issue);
			mobileCompleValue();
			keyId = num;
			return strategy.forwardToView();
		} else {
			return "error";
		}
	}

	private String forwardToWorkingRecord() {
		if (legalKeyIdParam()) {
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			loadFullIssueTypes(issue);
			loadIssueOperationLogs(issue);
			loadAttachFiles(issue);
			loadEvaluate(issue);
			return "convertToWorkingRecord";
		}
		return "";
	}

	/***************************************************************************
	 * 事件办理(包括加急、督办、批示等操作)的页面跳转
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "dispatchDeal", results = {
					@Result(name = "default_dealing", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/deals/fourTeamsDealIssueDlg.jsp"),
					@Result(name = "default_simple_dealing", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/deals/fourTeamsSimpleDealDlg.jsp"),
					@Result(name = "default_single_content_dealing", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/deals/fourTeamsSingleContentIssueDlg.jsp"),
					@Result(name = "default_supervise_dealing", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/deals/fourTeamsSuperviseIssueDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/houseInfo/actualHouse/searchActualHouseDlg.jsp") }),
			@Action(value = "dispatchDealForBench", results = {
					@Result(name = "default_supervise_dealing", location = "/workBench/issueManage/superviseIssueDlg.jsp"),
					@Result(name = "default_single_content_dealing", location = "/workBench/issueManage/singleContentIssueDlg.jsp") }),
			@Action(value = "dispatchDealIssueForMobile", results = {
					@Result(name = "default_dealing", type = "json", params = {
							"root", "jsonMap", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "default_simple_dealing", type = "json", params = {
							"root", "jsonMap", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }) }) })
	public String dispatchDeal() throws Exception {
		// 正常的事件办理
		if ("deal".equalsIgnoreCase(mode)) {
			return forwardToDeal();
			// 批示、加急
		} else if (isSimpleContentDeal()) {
			return forwardToSingleContent();
			// 督办
		} else if (isSuperviseDeal()) {
			return forwardToSupervise();
		} else if (dealCode > 0) {
			return forwardToSimpleDeal();
		} else {
			return "";
		}
	}

	private Long issuesKeyId = 0L;

	public Long getIssuesKeyId() {
		return issuesKeyId;
	}

	public void setIssuesKeyId(Long issuesKeyId) {
		this.issuesKeyId = issuesKeyId;
	}

	private String forwardToDeal() throws Exception {
		if (issuesKeyId > 0) {
			forwardToView();
		}
		FourTeamsIssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(keyId);
		if (!getCurrentLoginedOrg().equals(step.getTarget())) {
			errorMessage = "你不能处理不属于你部门的事件";
			return ERROR;
		} else {
			operation = new FourTeamsIssueLogNew();
			fillOperationLog(operation, step);
			loadCandoOperations(keyId);
			hiddenSomeOperate(step);
			mobileTransact(operation, keyId, canDoList);
			loadIssueSkipRule(keyId);
		}
		return getActualIssueManageStrategy().forwardToDealing();
	}

	private void loadIssueSkipRule(Long keyId) {
		FourTeamsIssueNew issueNew = getActualIssueServiceInstance()
				.getFullIssueByStepId(keyId);
		issueSkiprule = issueSkipruleService
				.getFourTeamsIssueSkipruleByIssue(issueNew);
	}

	private void mobileTransact(FourTeamsIssueLogNew operation, Long keyId,
			List<FourTeamsIssueOperate> canDoList) throws Exception {
		jsonMap.put("keyId", keyId);
		jsonMap.put("operation", operation);
		jsonMap.put("canDoList", canDoList);
		findReportedAssignedByDepartment(keyId, canDoList);
	}

	private boolean isSimpleContentDeal() {
		return FourTeamsIssueOperate.INSTRUCT.getCode() == dealCode
				|| FourTeamsIssueOperate.URGENT.getCode() == dealCode;
	}

	private String forwardToSingleContent() {
		createEmptyOperationLogByStepId(keyId);
		return "default_single_content_dealing";
	}

	private boolean isSuperviseDeal() {
		return FourTeamsIssueOperate.NORMAL_SUPERVISE.getCode() == dealCode
				|| FourTeamsIssueOperate.YELLOW_SUPERVISE.getCode() == dealCode
				|| FourTeamsIssueOperate.RED_SUPERVISE.getCode() == dealCode;
	}

	private String forwardToSupervise() {
		createEmptyOperationLogByStepId(keyId);
		// IssueOperate supervise = IssueOperate.parse(dealCode);
		// double cent =
		// issueAccessConfigService.getIssueScoresConfig(supervise);
		// if (Math.abs(supervise.getCent()) > 0) {
		// tag = supervise.toString() + "将导致该处理部门被扣" + cent + "分";
		// } else {
		// tag = "";
		// }
		return "default_supervise_dealing";
	}

	private String forwardToSimpleDeal() {
		createEmptyOperationLogByStepId(keyId);
		mobileAdmissible(keyId, dealCode);
		return "default_simple_dealing";
	}

	private void mobileAdmissible(Long keyId, int dealCode) {
		jsonMap.put("keyId", keyId);
		jsonMap.put("dealCode", dealCode);
		jsonMap.put("operation", operation);
	}

	/**
	 * 处理传过来的时间
	 */
	private void dealTime() {
		try {
			String time = issue.getOccurDateString().substring(0, 10);
			if (hours != null && !hours.equals("")) {
				if (minute.equals("")) {
					minute = "00";
				}
				time = time + " " + hours + ":" + minute;
				issue.setOccurDate(new SimpleDateFormat("yyyy-MM-dd HH:mm")
						.parse(time));
			}
			issue.setOccurDate(new SimpleDateFormat("yyyy-MM-dd").parse(time));
			issue.setHours(hours);
			issue.setMinute(minute);
		} catch (ParseException e) {
			logger.error("格式化日期失败：", e);
		}
	}

	/***************************************************************************
	 * 新增事件
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "addMobileIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "addIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"issueVO", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String addIssue() throws Exception {
		fillIssueProperty();
		prepareEmphas();
		List<FourTeamsIssueAttachFile> files = null;
		createAttachFilesForMobile();
		files = createIssueAttachFile(attachFiles);
		if (validateInput(issue, files)) {
			dealTime();
			issueVO = getActualIssueServiceInstance().addIssue(issue, files,
					emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<FourTeamsIssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("新增事件，服务单号为：" + issueVO.getSerialNumber());
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// issueLog);
			return SUCCESS;
		}
		return ERROR;
	}

	@PermissionFilter(ename = "acceptIssue")
	@Actions({ @Action(value = "addIssueByTencent", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String addIssueByTencent() throws Exception {
		fillIssueProperty();
		prepareEmphas();
		List<FourTeamsIssueAttachFile> files = null;
		createAttachFilesForMobile();
		files = createInboxAttachFile(attachFiles);
		if (validateInput(issue, files)) {
			dealTime();
			issue.setSourceKind(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.SOURCE_KIND,
							FourTeamsIssueConstants.WECHAT_INPUT));
			issueVO = getActualIssueServiceInstance().addIssue(issue, files,
					emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<FourTeamsIssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("新增事件，服务单号为：" + issueVO.getSerialNumber());
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// issueLog);
			Long saveIssueId = issueVO.getIssueId();
			for (int i = 0; i < Arrays.asList(analyzePopulationIds()).size(); i++) {
				inbox = inboxService.getInboxById(Arrays.asList(
						analyzePopulationIds()).get(i));
				inbox.setDealState(Constants.ACCEPT);
				inbox.setIssueId(saveIssueId);
				inboxService.update(inbox);
				if (i != Arrays.asList(analyzePopulationIds()).size() - 1) {
					inboxService.saveReplyMessage(inbox, textSendMessage);
				}
			}
			inboxService.replyMessage(inbox, textSendMessage);

			return SUCCESS;
		}
		return ERROR;
	}

	private List<FourTeamsIssueAttachFile> createInboxAttachFile(
			String[] fileNameAndIdS) throws Exception {

		if (fileNameAndIdS == null) {
			return new ArrayList<FourTeamsIssueAttachFile>();
		}

		List<FourTeamsIssueAttachFile> list = new ArrayList<FourTeamsIssueAttachFile>();

		for (String fileNameAndId : fileNameAndIdS) {
			if (StringUtil.isStringAvaliable(fileNameAndId)) {
				String[] fileName = fileNameAndId.split(",");
				if (fileNameAndId.indexOf(",") == 0
						&& fileName[1].indexOf(".") == -1) {
					fileNameAndId = fileNameAndId.substring(1);
				}
				/*
				 * if (fileNameAndId.indexOf(",") == 0) { fileNameAndId =
				 * fileNameAndId.substring(1); }
				 */
				String[] id_fileName = fileNameAndId.split(",");
				String id = id_fileName[0];

				InboxAttachment inboxAttachment = new InboxAttachment();
				FourTeamsIssueAttachFile issueAttachFile = new FourTeamsIssueAttachFile();
				if (StringUtil.isStringAvaliable(id)) {
					inboxAttachment = inboxAttachmentService
							.getInboxAttachmentById(Long.parseLong(id));
					if (inboxAttachment != null) {
						issueAttachFile.setFileActualUrl(inboxAttachment
								.getFileActualUrl());
						issueAttachFile.setFileName(inboxAttachment
								.getFileName());
					}
				} else {
					StoredFile sf = FileUtil.copyTmpFileToStoredFile(
							id_fileName[1], GridProperties.ISSUE_ATTACHFILE);

					issueAttachFile.setFileActualUrl(sf.getFullName());

					issueAttachFile.setFileName(id_fileName[1]);
				}
				list.add(issueAttachFile);
			}
		}
		return list;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = inboxIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	private void prepareEmphas() {
		emphas = new HashMap<String, String>();
		if (isInvolvedPlace != null) {
			if (involvedPlace != null && !involvedPlace.trim().equals("")) {
				String[] places = involvedPlace.split(",");
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
			String[] personnels = involvedPersonnel.split(",");
			for (int i = 0; i < personnels.length; i++) {
				String value = personnels[i].substring(
						personnels[i].indexOf("-") + 1, personnels[i].length());
				String name = personnels[i].substring(0,
						personnels[i].indexOf("-"));
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

	/***************************************************************************
	 * 修改事件处理日志
	 * 
	 * @return
	 */
	@Action(value = "updateIssueLog", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueLogForEdit", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarch", "false" }) })
	public String updateIssueLog() throws Exception {
		List<FourTeamsIssueAttachFile> files = null;
		files = createIssueAttachFile(attachFiles);
		issueLogForEdit = getActualIssueServiceInstance().updateIssueLog(
				issueLogForEdit, files);
		return SUCCESS;
	}

	/***************************************************************************
	 * 修改事件
	 * 
	 * @return
	 */
	@Action(value = "updateIssue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarch", "false" }) })
	public String updateIssue() throws Exception {
		fillIssueProperty();
		prepareEmphas();
		List<FourTeamsIssueAttachFile> files = null;
		createAttachFilesForMobile();
		files = createIssueAttachFile(attachFiles);
		if (validateInput(issue, files)) {
			dealTime();
			issueVO = getActualIssueServiceInstance().updateIssue(issue, files,
					stepId, emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<FourTeamsIssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("修改事件，服务单号为：" + issueVO.getSerialNumber());
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// issueLog);
			return SUCCESS;
		}
		return ERROR;
	}

	private List<FourTeamsIssueAttachFile> createIssueAttachFile(
			String[] fileNameAndIdS) throws Exception {

		if (fileNameAndIdS == null) {
			return new ArrayList<FourTeamsIssueAttachFile>();
		}

		List<FourTeamsIssueAttachFile> list = new ArrayList<FourTeamsIssueAttachFile>();

		for (String fileNameAndId : fileNameAndIdS) {
			if (StringUtil.isStringAvaliable(fileNameAndId)) {
				String[] fileName = fileNameAndId.split(",");
				if (fileNameAndId.indexOf(",") == 0
						&& fileName[1].indexOf(".") == -1) {
					fileNameAndId = fileNameAndId.substring(1);
				}
				/*
				 * if (fileNameAndId.indexOf(",") == 0) { fileNameAndId =
				 * fileNameAndId.substring(1); }
				 */
				String[] id_fileName = fileNameAndId.split(",");
				String id = id_fileName[0];

				FourTeamsIssueAttachFile issueAttachFile = new FourTeamsIssueAttachFile();

				if (StringUtil.isStringAvaliable(id)) {

					issueAttachFile = getActualIssueServiceInstance()
							.getIssueAttachFileById(Long.parseLong(id));

				} else {

					StoredFile sf = FileUtil.copyTmpFileToStoredFile(
							id_fileName[1], GridProperties.ISSUE_ATTACHFILE);

					issueAttachFile.setFileActualUrl(sf.getFullName());

					issueAttachFile.setFileName(id_fileName[1]);
				}
				list.add(issueAttachFile);
			}
		}
		return list;
	}

	/** 填充事件的类型和事件来源方式 */
	private void fillIssueProperty() {
		issue.setIssueTypes(parseIssueTypes(selectedTypes));
		if (issue.getSourceKind() == null
				|| issue.getSourceKind().getId() == null) {
			factory.getIssueManageStrategyByModule(moduleName)
					.fillIssueSourceProperty(issue);
		}
	}

	/** 解析选择的事件类型 */
	private List<IssueType> parseIssueTypes(String issueTypeIdsStr) {

		List<IssueType> result = new ArrayList<IssueType>();

		if (StringUtil.isStringAvaliable(issueTypeIdsStr)) {

			FourTeamsIssueManageStrategy strategy = getActualIssueManageStrategy();

			Map<String, List<IssueType>> allIssueTypes = strategy
					.loadEnabledIssueTypes(strategy.getIssueTypeNames());

			String[] issueTypeIds = issueTypeIdsStr.split(",");

			for (String issueTypeId : issueTypeIds) {
				if (issueTypeId.equals("")) {
					continue;
				}
				for (List<IssueType> types : allIssueTypes.values()) {
					for (IssueType issueType : types) {
						if (issueType.getId().equals(
								Long.valueOf(issueTypeId.trim()))) {
							result.add(issueType);
						}
					}
				}
			}

		}
		return result;
	}

	/***************************************************************************
	 * 事件的各种处理操作
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "dealIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"issueVO", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "dealIssueForMobile", results = { @Result(name = "success", type = "json", params = {
					"root", "true", "ignoreHierarchy", "false" }) }) })
	public String deal() throws Exception {
		FourTeamsIssueOperate operate = FourTeamsIssueOperate.parse(dealCode);
		createAttachFilesForMobile();
		List<FourTeamsIssueAttachFile> files = createIssueAttachFile(attachFiles);

		if (!validateDealInput(operation, operate, files)) {
			return ERROR;
		}
		if (FourTeamsIssueOperate.REPORT_TO.equals(operate)) {
			issueVO = getActualIssueServiceInstance().reportToCommandCenter(
					keyId, operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.GIVETO.equals(operate)) {
			issueVO = getActualIssueServiceInstance().giveTo(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.CONCEPT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().concept(keyId, operation);
			List<FourTeamsIssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("受理事件，服务单号为：" + issueVO.getSerialNumber());
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// issueLog);
		} else if (FourTeamsIssueOperate.READ.equals(operate)) {
			issueVO = getActualIssueServiceInstance().read(keyId, operation);
			List<FourTeamsIssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("阅读事件，服务单号为：" + issueVO.getSerialNumber());
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// issueLog);
		} else if (FourTeamsIssueOperate.COMMENT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().comment(keyId, operation,
					files);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.COMPLETE.equals(operate)) {
			issueVO = getActualIssueServiceInstance().complete(keyId,
					operation, files);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.ASSIGN.equals(operate)) {
			issueVO = getActualIssueServiceInstance().assign(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.SUBMIT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().submit(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.BACK.equals(operate)) {
			issueVO = getActualIssueServiceInstance().back(keyId, operation,
					files);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.INSTRUCT.equals(operate)) {
			issueVO = getActualIssueServiceInstance()
					.instruct(keyId, operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (isSuperviseDeal()) {
			issueVO = getActualIssueServiceInstance().supervise(operate, keyId,
					operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.CANCEL_SUPERVISE.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelSupervise(keyId,
					operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.URGENT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().urgent(keyId, operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.CANCEL_URGENT.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelUrgent(keyId,
					operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.HISTORIC.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance()
					.historic(keyId, operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else if (FourTeamsIssueOperate.CANCEL_HISTORIC.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelHistoric(keyId,
					operation);
			// workDiaryService.addWorkDiary(issueVO.getIssueId(),
			// operation);
		} else {
			errorMessage = "未知的处理类型";
			return ERROR;
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 事件验证页面跳转
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "dispatchIssueEvaluate", results = { @Result(location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsIssueEvaluateDlg.jsp") }),
			@Action(value = "dispatchIssueEvaluateForMobile", results = { @Result(name = "success", type = "json", params = {
					"root", "issueEvaluate", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }) }) })
	public String dispatchIssueEvaluate() throws Exception {
		FourTeamsIssueEvaluate ie = issueEvaluateService
				.getIssueEvaluateByIssueId(issueEvaluate.getIssue().getId());
		if (ie != null) {
			issueEvaluate = ie;
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 事件验证
	 * 
	 * @return
	 */
	@Action(value = "issueEvaluate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueEvaluate", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String evaluate() throws Exception {
		List<FourTeamsIssueAttachFile> files = null;
		files = createIssueAttachFile(attachFiles);
		issueEvaluate.setIssueAttachFiles(files);
		issueEvaluate = issueEvaluateService.evaluate(issueEvaluate);
		return SUCCESS;
	}

	/***************************************************************************
	 * 设置宣传案例
	 * 
	 * @return
	 */
	@Action(value = "publicltyCass", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String publicltyCass() throws Exception {
		if (legalKeyIdParam()) {
			issueVO = getActualIssueServiceInstance().publiclty(keyId);
			return SUCCESS;
		}
		return ERROR;
	}

	/***************************************************************************
	 * 取消宣传案例
	 * 
	 * @return
	 */
	@Action(value = "cancelPublicltyCass", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String cancelPublicltyCass() throws Exception {
		if (legalKeyIdParam()) {
			issueVO = getActualIssueServiceInstance().cancelPubliclty(keyId);
			return SUCCESS;
		}
		return ERROR;
	}

	/***************************************************************************
	 * 删除事件
	 * 
	 * @return
	 */
	@Action(value = "deleteIssue", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteIssue() throws Exception {
		if (legalKeyIdParam()) {
			if (!hasPermission(keyId, null)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			FourTeamsIssueNew newIssue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			List<FourTeamsIssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(keyId);
			FourTeamsIssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("删除事件，服务单号为：" + newIssue.getSerialNumber());
			// workDiaryService.addWorkDiary(keyId, issueLog);
			getActualIssueServiceInstance().deleteIssueByIssueId(keyId);
			return SUCCESS;
		}
		return ERROR;
	}

	/***************************************************************************
	 * 为武胜县演示服务的视图类
	 * 
	 * @return
	 */
	@Action(value = "findIssueForView", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findIssueForView() throws Exception {
		if (orgLevel == null && orgLevelInternalId != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_LEVEL,
							orgLevelInternalId);
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				orgLevel = orgLevels.get(0).getId();
			}
		}
		if (sourceType == null && sourceTypeInternalId != null) {
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SOURCE_KIND, sourceTypeInternalId);
			if (orgLevels != null && orgLevels.size() > 0
					&& orgLevels.get(0) != null) {
				sourceType = orgLevels.get(0).getId();
			}
		}
		// 根据不同类型调用不同的service
		if (FourTeamsIssueViewType.NEED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (FourTeamsIssueViewType.DONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDone();
		} else if (FourTeamsIssueViewType.DOING.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDoing();
		} else if (FourTeamsIssueViewType.COMPLETED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsCompleted();
		} else if (FourTeamsIssueViewType.ASSIGN.equalsIgnoreCase(viewType)) {
			return findJurisdictionsAssgin();
		} else if (FourTeamsIssueViewType.SUBMIT.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSubmit();
		} else if (FourTeamsIssueViewType.CHECKPENDING
				.equalsIgnoreCase(viewType)) {
			return findJurisdictionAuditDelay();
		} else if (FourTeamsIssueViewType.SKIP.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSkipIssue();
		} else {
			createEmptyIssueList();
			return ERROR;
		}

	}

	@Action(value = "findJurisdictionsForProcess", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsIssueProcess.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findJurisdictionsForProcess() throws Exception {
		return findIssueForView();
	}

	/***************************************************************************
	 * 我的事项-待办事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findMyNeedDo", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/simpleIssueList.jsp") }),
			@Action(value = "findMyNeedDoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findMyNeedDo() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findMyNeedDoIssues(keyId, issueType, page, rows, sidx,
							sord);
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] {
							"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/** 事件模块的置顶操作 */
	@Action(value = "topIssue", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@Actions(value = {
			@Action(value = "topNeedDoIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "topDoneIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "topCompletedIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "topHistoricalIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "topPublicltyCassIssue", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String topIssue() throws Exception {
		if (topIssue == null || topIssue.getIssueId() == null
				|| topIssue.getIssueTag() == 0) {
			return ERROR;
		}
		topIssue.setOrgId(ThreadVariable.getOrganization().getId());
		if (getActualIssueServiceInstance().topIssue(topIssue)) {
			return SUCCESS;
		}
		return ERROR;
	}

	@Action(value = "findMyNeedDoForWorkBench", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findMyNeedDoForWorkBench() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findMyNeedDoIssues(keyId, issueType, page, rows, sidx,
							sord);
			for (FourTeamsIssueViewObject issue : issues.getResult()) {
				issue.setCanDoList(getActualIssueServiceInstance()
						.getIssueCandoForOrg(issue.getIssueStepId(),
								getCurrentLoginedOrg()));
			}
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] {
							"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	@Action(value = "auditItemToIssueDlg", results = {
			@Result(name = "success", location = "/approval/approvalItem/updateApprovalItemToIssueDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String auditItemToIssueDlg() throws Exception {
		if (id != null) {
			approvalItem = approvalItemService.getApprovalItemById(id);
			approvalItem.setItem(itemService.getItemById(approvalItem.getItem()
					.getId()));
			if (approvalItem.getOrganization() != null
					&& approvalItem.getOrganization().getId() != null) {
				approvalItem.setOrganization(organizationDubboService
						.getSimpleOrgById(approvalItem.getOrganization()
								.getId()));
			}
			issue = approvalItemService.getIssueBySerialNumber(approvalItem
					.getApprovalNumber());
			loadAttachFiles(issue);
		}
		return SUCCESS;

	}

	/***************************************************************************
	 * 服务审批转入事件页面跳转
	 * 
	 * @return
	 */
	@Action(value = "approvalItemToIssueDlg", results = {
			@Result(name = "success", location = "/approval/approvalItem/updateApprovalItemToIssueDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String approvalItemToIssueDlg() throws Exception {
		if (approvalItem != null && approvalItem.getId() != null) {
			approvalItem = approvalItemService.getApprovalItemById(approvalItem
					.getId());
			approvalItem.setItem(itemService.getItemById(approvalItem.getItem()
					.getId()));
			if (approvalItem.getOrganization() != null
					&& approvalItem.getOrganization().getId() != null) {
				approvalItem.setOrganization(organizationDubboService
						.getSimpleOrgById(approvalItem.getOrganization()
								.getId()));
			}
			issue = approvalItemService.getIssueBySerialNumber(approvalItem
					.getApprovalNumber());
			loadAttachFiles(issue);
		}
		return SUCCESS;

	}

	/***************************************************************************
	 * 我的事项-已办事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findMyDone", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/simpleIssueDoneList.jsp") }),
			@Action(value = "findMyDoneForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findMyDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findMyDone(keyId, issue, page, rows, sidx, sord, issueType);
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] {
							"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 历史遗留列表
	 * 
	 * @return
	 */
	@Action(value = "findMyHistoricalIssues", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/simpleIssueHistoricalList.jsp") })
	public String findMyHistoricalIssues() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findMyHistoricIssues(keyId, page, rows, sidx, sord);
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] {
							"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 宣传案例列表
	 * 
	 * @return
	 */
	@Action(value = "findMyPublicltyIssues", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/simplePublicltyCassList.jsp") })
	public String findMyPublicltyIssues() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findMyPublicltyIssues(keyId, page, rows, sidx, sord);
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] {
							"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-待办事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsNeedDo", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsNeedDoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsNeedDo() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsNeedDo(fourTeamsType, seachValue, keyId,
							page, rows, sidx, sord, issueType, orgLevel,
							leaderView, functionalOrgType, viewProcess,
							sourceType);

			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"occurOrg", "lastOrg", "targeOrg",
								"currentOrg", "createOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg",
								"createOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-办理中事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsDoing", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsDoingForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsDoing() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsDoing(seachValue, keyId, page, rows,
							sidx, sord, issueType, orgLevel, leaderView,
							functionalOrgType, viewProcess);
			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService,
						new String[] { "occurOrg", "lastOrg", "targeOrg",
								"currentOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-上级交办事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsAssgin", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsAssginForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsAssgin() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsAssgin(seachValue, keyId, page, rows,
							sidx, sord, issueType, orgLevel, leaderView,
							functionalOrgType, viewProcess, sourceType);
			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService,
						new String[] { "occurOrg", "lastOrg", "targeOrg",
								"currentOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-已办事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsCompleted", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsCompletedForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsCompleted() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsCompleted(fourTeamsType, seachValue,
							keyId, page, rows, sidx, sord, issueType, orgLevel,
							leaderView, functionalOrgType, statusType,
							viewProcess, sourceType);
			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"occurOrg", "lastOrg", "targeOrg",
								"currentOrg", "createOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg",
								"createOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 查询越级流程的事件
	 */
	public String findJurisdictionsSkipIssue() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsSkipIssue(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							viewProcess, sourceType);
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] { "lastOrg",
							"targeOrg", "currentOrg", "occurOrg" }, null);

			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-已办结案事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsDone", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsDoneForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsDone(statusType, fourTeamsType,
							seachValue, keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							viewProcess, sourceType);
			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"occurOrg", "lastOrg", "targeOrg",
								"currentOrg", "createOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg",
								"createOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-上报案事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsSubmit", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsSubmitForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsSubmit() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsSubmit(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							viewProcess, sourceType);
			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService,
						new String[] { "occurOrg", "lastOrg", "targeOrg",
								"currentOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private String findJurisdictionAuditDelay() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionAuditDelay(page, rows, sidx, sord);
			if (!FourTeamsIssueViewType.VIEWPROCESS.equals(viewProcess)) {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService,
						new String[] { "occurOrg", "lastOrg", "targeOrg",
								"currentOrg" }, null);
			} else {
				issues = FourTeamsControllerHelper.processAllOrgRelativeName(
						issues, organizationDubboService, new String[] {
								"lastOrg", "targeOrg", "currentOrg" }, null);
			}
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private void resolveIssueType() {
		if (!StringUtil.isStringAvaliable(type)) {
			issueType = null;
		} else {
			IssueTypeDomain domain = issueTypeService
					.getIssueTypeDoaminByDomainName(FourTeamsIssueTypes
							.getChineseName(type));
			if (domain == null) {
				issueType = null;
			} else {
				issueType = domain.getId();
			}
		}
	}

	/**
	 * 我的事项-已办结事项
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findMyCompleted", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/simpleIssueMyCompletedList.jsp") }),
			@Action(value = "findMyCompletedForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findMyComplete() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<FourTeamsIssueViewObject> issues = getActualIssueServiceInstance()
					.findMyCompleted(keyId, page, rows, sidx, sord, issueType);
			issues = FourTeamsControllerHelper.processAllOrgRelativeName(
					issues, organizationDubboService, new String[] {
							"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/**
	 * 服务审批 我的事项详情查看
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "viewApprovalItemDetail", results = {
			@Result(name = "default_view", location = "/approval/itemHandle/itemNeedDoDetail.jsp"),
			@Result(name = "search", location = "/baseinfo/houseInfo/actualHouse/searchActualHouseDlg.jsp") }) })
	public String viewApprovalItemDetail() throws Exception {
		if (approvalNumber != null) {
			issue = issueService.getIssueBySerialNumber(approvalNumber);
			if (issue != null) {
				keyId = issue.getId();
				loadFullIssueTypes(issue);
				loadIssueOperationLogs(issue);
				loadAttachFiles(issue);
				if (issue.getCurrentStep() != null) {
					loadCandoOperations(issue.getCurrentStep().getId());
				}
			}
			if (id != null) {
				approvalItem = approvalItemService.getApprovalItemById(id);
				approvalItemFileList = approvalItemFileService
						.findApprovalItemFileByApprovalItemId(id);
				issue.setOccurOrg(approvalItem.getOrganization());
			}
			return getActualIssueManageStrategy().forwardToViewDetail();
		}
		return "";
	}

	/***************************************************************************
	 * 查事件详情(事件列表右侧的事件详情页面)
	 * 
	 * @return
	 */
	@Action(value = "viewSubDetail", results = {
			@Result(name = "default_view", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/issueAccordingContext.jsp"),
			@Result(name = "empty_view", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/emptyIssueAccordingContext.jsp"),
			@Result(name = "print", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsPrintIssueDlg.jsp"),
			@Result(name = "printWord", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsPrintWordIssueDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/houseInfo/actualHouse/searchActualHouseDlg.jsp") })
	public String viewIssueDetail() throws Exception {
		if (legalKeyIdParam()) {
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			loadFullIssueTypes(issue);
			loadIssueOperationLogs(issue);
			loadAttachFiles(issue);
			loadCandoOperations(keyId);
			loadRelatePersons();
			loadMainCharacters();
			mobileCompleValue();
			proccessIssueLogsOrgAndDeal(issue.getId());
			if ("print".equals(mode)) {

				return "print";
			}
			if ("printWord".equals(mode)) {
				return "printWord";
			}
			return getActualIssueManageStrategy().forwardToViewDetail();
		} else {
			return getActualIssueManageStrategy().forwardToEmptyViewDetail();
		}
	}

	private void proccessIssueLogsOrgAndDeal(Long id) {
		issueLogs = FourTeamsControllerHelper.processAllOrgName(
				issueLogService.findIssueLogsByIssueId(id),
				organizationDubboService,
				new String[] { "dealOrg", "targeOrg" });
		issueLogs = FourTeamsControllerHelper
				.processAllIssueLogDealDescription(issueLogs);
	}

	@Action(value = "viewIssueForMobile", results = {
			@Result(name = "default_view", type = "json", params = { "root",
					"jsonMap", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String viewIssueForMobile() throws Exception {
		if (null != id) {
			FourTeamsIssueNew issueNew = getActualIssueServiceInstance()
					.getFullIssueByIssueId(id);
			if (null != issueNew) {
				if (null != issueNew.getCurrentStep()
						&& null != issueNew.getCurrentStep().getId()) {
					keyId = issueNew.getCurrentStep().getId();
				} else {
					issue = issueNew;
					loadFullIssueTypes(issue);
					loadIssueOperationLogs(issue);
					loadAttachFiles(issue);
					loadCandoOperations(keyId);
					mobileCompleValue();
					return getActualIssueManageStrategy().forwardToViewDetail();
				}
			}
		}
		if (legalKeyIdParam()) {
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			loadFullIssueTypes(issue);
			loadIssueOperationLogs(issue);
			loadAttachFiles(issue);
			loadCandoOperations(keyId);
			mobileCompleValue();
			return getActualIssueManageStrategy().forwardToViewDetail();
		} else {
			errorMessage = "查看事件错误";
			return ERROR;
		}
	}

	private void mobileCompleValue() {
		jsonMap.put("keyId", keyId);
		jsonMap.put("issueNew", issue);
		jsonMap.put("issueHasTypeDomainName", issueHasTypeDomainName);
		issueDealLogs = FourTeamsControllerHelper.processAllOrgName(
				issueDealLogs, organizationDubboService,
				new String[] { "dealOrg" });
		jsonMap.put("issueLogs", issueDealLogs);
		jsonMap.put("issueAttachFiles", issueAttachFiles);
		mobileOperate(canDoList);
	}

	private void mobileOperate(List<FourTeamsIssueOperate> canDoList) {
		if (null != canDoList && canDoList.size() > 0) {
			for (FourTeamsIssueOperate operate : canDoList) {
				jsonMap.put("dealCode", operate.getCode());
				if (FourTeamsIssueOperate.BACK.getCode() == operate.getCode()) {
					jsonMap.put("method", "doHandle");
					jsonMap.put("action", "办理");
					break;
				} else if (FourTeamsIssueOperate.READ.getCode() == operate
						.getCode()) {
					jsonMap.put("阅读", "阅读");
					break;
				} else if (FourTeamsIssueOperate.CONCEPT.getCode() == operate
						.getCode()) {
					jsonMap.put("method", "doAccept");
					jsonMap.put("action", "受理");
					break;
				}
			}
		}
	}

	@Actions({ @Action(value = "viewSubDetailByIssueId", results = { @Result(name = "default_view", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/issueAccordingContext.jsp") }) })
	public String viewSubDetailByIssueId() throws Exception {
		if (legalKeyIdParam()) {
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			loadFullIssueTypes(issue);
			loadIssueOperationLogs(issue);
			loadAttachFiles(issue);
			loadEvaluate(issue);
			return getActualIssueManageStrategy().forwardToViewDetail();
		}
		return "";
	}

	/***************************************************************************
	 * 获取事件处理记录的图表视图数据
	 * 
	 * @return
	 */
	@Action(value = "viewIssueMap", results = { @Result(name = "success", type = "json", params = {
			"root", "data" }) })
	public String viewIssueMap() throws Exception {
		if (!hasPermission(issue.getId(), null)) {
			return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
		}
		data = getActualIssueServiceInstance().getIssueMap(issue.getId());
		return SUCCESS;
	}

	/**
	 * 在事件处理记录的图表视图中查看部门的事件处理记录
	 * 
	 * @return
	 */
	@Action(value = "viewIssueDealLog", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/viewIssueDealLogDlg.jsp") })
	public String viewIssueDealLog() throws Exception {
		issueDealLogs = getActualIssueServiceInstance().getIssueDealLog(
				issueMap);
		if (issueDealLogs != null && issueDealLogs.size() > 0) {
			issue = new FourTeamsIssueNew();
			issue.setId(issueDealLogs.get(0).getIssue().getId());
			loadAttachFiles(issue);
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下载事件附件
	 * 
	 * @return
	 */
	@Action(value = "downLoadAttachFile", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downLoadActualFile() throws Exception {
		if (legalKeyIdParam()) {
			FourTeamsIssueAttachFile file = getActualIssueServiceInstance()
					.getIssueAttachFileById(keyId);
			if (file != null) {
				inputStream = new java.io.FileInputStream(createStoreFile(
						file.getFileActualUrl(), file.getFileName()));
				return getActualIssueManageStrategy()
						.forwardToDownLoadAttachFile();
			}
		}
		return "";
	}

	/***************************************************************************
	 * 事件办理时选择主办单位/主办单位联想输入
	 * 
	 * @return
	 */
	@Action(value = "searchTransferTarget", results = {
			@Result(name = "for_auto", type = "json", params = { "root",
					"gridPage.rows", "ignoreHierarchy", "false" }),
			@Result(name = "for_page", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }) })
	public String searchTransferTarget() throws Exception {
		if (legalKeyIdParam()) {
			if (adminTarget) {
				gridPage = new GridPage(getActualIssueServiceInstance()
						.findAdminTargetsByName(keyId, tag,
								FourTeamsIssueOperate.parse(dealCode),
								parseStringToLongArray(exceptIds), page, rows));
			} else {
				gridPage = new GridPage(getActualIssueServiceInstance()
						.findFunctionTargetsByName(keyId, tag,
								FourTeamsIssueOperate.parse(dealCode),
								parseStringToLongArray(exceptIds), page, rows));
			}
			return searchForAutocomplete() ? "for_auto" : "for_page";
		}
		return "for_page";
	}

	/***************************************************************************
	 * 事件办理时 当选择上报或交办自动填充一个唯一(如果有)的主办单位
	 * 
	 * @return
	 */
	@Action(value = "getUniqueTrgetOrg", results = {
			@Result(name = "unique", type = "json", params = { "root",
					"gridPage.rows.get(0)", "ignoreHierarchy", "false" }),
			@Result(name = "no_unique", type = "json", params = { "root",
					"false" }) })
	public String searchUniqueTrgetOrg() throws Exception {
		if (legalKeyIdParam()) {
			if (adminTarget) {
				gridPage = new GridPage(getActualIssueServiceInstance()
						.findAdminTargetsByName(keyId, "",
								FourTeamsIssueOperate.parse(dealCode), null, 1,
								10));
			} else {
				gridPage = new GridPage(getActualIssueServiceInstance()
						.findFunctionTargetsByName(keyId, "",
								FourTeamsIssueOperate.parse(dealCode), null, 1,
								10));
			}
			return gridPage.getRows().size() == 1 ? "unique" : "no_unique";
		}
		return "no_unique";
	}

	/***************************************************************************
	 * 事件办理时选择抄告单位/抄告单位的联想输入
	 * 
	 * @return
	 */
	@Action(value = "searchTellTarget", results = {
			@Result(name = "for_auto", type = "json", params = { "root",
					"gridPage.rows", "ignoreHierarchy", "false" }),
			@Result(name = "for_page", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }) })
	public String searchTellTarget() throws Exception {
		if (legalKeyIdParam()) {
			gridPage = new GridPage(getActualIssueServiceInstance()
					.findTellTargetsByName(keyId, tag,
							FourTeamsIssueOperate.parse(dealCode), adminTarget,
							parseStringToLongArray(exceptIds), page, rows));
			return searchForAutocomplete() ? "for_auto" : "for_page";
		}
		return "for_page";
	}

	@Action(value = "hasTellTarget", results = {
			@Result(name = "has", type = "json", params = { "root", "true" }),
			@Result(name = "hasNot", type = "json", params = { "root", "false" }) })
	public String hasTellTarget() throws Exception {
		if (legalKeyIdParam()) {
			gridPage = new GridPage(getActualIssueServiceInstance()
					.findTellTargetsByName(keyId, "",
							FourTeamsIssueOperate.parse(dealCode), adminTarget,
							parseStringToLongArray(exceptIds), 1, 10));
			return gridPage.getRows().size() > 0 ? "has" : "hasNot";
		}
		return "hasNot";
	}

	private boolean searchForAutocomplete() {
		return "auto".equalsIgnoreCase(mode);
	}

	private Long[] parseStringToLongArray(String idsStr) {
		if (StringUtil.isStringAvaliable(idsStr)) {
			String[] ids = idsStr.split(",");
			List<Long> list = new ArrayList<Long>();
			for (int index = 0; index < ids.length; index++) {
				if (StringUtil.isStringAvaliable(ids[index])) {
					list.add(Long.valueOf(ids[index]));
				}
			}
			Long[] result = new Long[list.size()];
			return list.toArray(result);
		} else {
			return new Long[] {};
		}
	}

	private File createStoreFile(String path, String fileName)
			throws IOException {
		String downFilePath = FileUtil.getWebRoot() + File.separator + path;
		downloadFileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
		File storedFile = new File(downFilePath);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private void loadCandoOperations(Long stepId) {
		if (!DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			canDoList = getActualIssueServiceInstance().getIssueCandoForOrg(
					stepId, getCurrentLoginedOrg());
		} else {
			canDoList = new ArrayList<FourTeamsIssueOperate>();
		}
	}

	/***************************************************************************
	 * 加载事件的处理记录
	 * 
	 * @param selectIssue
	 */
	private void loadIssueOperationLogs(FourTeamsIssueNew selectIssue) {

		issueDealLogs = getActualIssueServiceInstance()
				.loadIssueOperationLogsByIssueId(selectIssue.getId());

		if (!selectIssue.getStatus().equals(FourTeamsIssueState.COMPLETE)) {

			for (FourTeamsIssueLogNew log : issueDealLogs) {

				if (log.getDealType() != null && log.getTargeOrg() != null) {

					FourTeamsIssueOperate op = FourTeamsIssueOperate.parse(log
							.getDealType().intValue());
					// 找出最后一次的交办的操作记录
					if (FourTeamsIssueOperate.ASSIGN.equals(op)
							&& selectIssue.getCurrentStep().getTarget().getId()
									.equals(log.getTargeOrg().getId())
							&& selectIssue.getCurrentStep().getId()
									.equals(log.getIssueStep().getId())) {

						operation = log;

						break;
					}
				}
			}
		}

	}

	/***************************************************************************
	 * 加载事件的附件
	 * 
	 * @param selectIssue
	 */
	private void loadAttachFiles(FourTeamsIssueNew selectIssue) {
		issueAttachFiles = getActualIssueServiceInstance()
				.loadIssueAttachFilesByIssueId(selectIssue.getId());
		issueEvaluateAttachFiles = getActualIssueServiceInstance()
				.getIssueEvaluateAttachFileAttachFileByIssueId(
						selectIssue.getId());
		for (int index = issueAttachFiles.size(); index > 0; index--) {
			FourTeamsIssueAttachFile file = issueAttachFiles.get(index - 1);
			if (isLogAttachFile(file)) {
				List<FourTeamsIssueAttachFile> logFiles = lookupLogFilesFromAllLogFile(file
						.getIssueLog().getId());
				logFiles.add(file);
				issueAttachFiles.remove(index - 1);
			}
		}
	}

	/**
	 * 加载事件的验证
	 * 
	 * @param selectIssue
	 */
	private void loadEvaluate(FourTeamsIssueNew selectIssue) {
		if (null == selectIssue || null == selectIssue.getId()) {
			issueEvaluate = new FourTeamsIssueEvaluate();
		} else {
			issueEvaluate = issueEvaluateService
					.getIssueEvaluateByIssueId(selectIssue.getId());
		}

	}

	private List<FourTeamsIssueAttachFile> lookupLogFilesFromAllLogFile(Long id) {
		if (issueLogAttachFiles.containsKey(id)) {
			return issueLogAttachFiles.get(id);
		} else {
			List<FourTeamsIssueAttachFile> files = new ArrayList<FourTeamsIssueAttachFile>();
			issueLogAttachFiles.put(id, files);
			return files;
		}
	}

	private boolean isLogAttachFile(FourTeamsIssueAttachFile file) {
		return file.getIssueLog() != null && file.getIssueLog().getId() != null;
	}

	private void loadFullIssueTypes(FourTeamsIssueNew selectIssue) {
		for (IssueType type : selectIssue.getIssueTypes()) {
			type.setIssueTypeDomain(issueTypeService
					.getIssueTypeDomainById(type.getIssueTypeDomain().getId()));
			if (issueHasTypeDomainName == null) {
				issueHasTypeDomainName = new ArrayList<String>();
			} else if (!issueHasTypeDomainName.contains(type
					.getIssueTypeDomain().getDomainName())) {
				issueHasTypeDomainName.add(type.getIssueTypeDomain()
						.getDomainName());
			}
		}
	}

	private FourTeamsIssueService getActualIssueServiceInstance() {
		return issueServiceFactory.getIssueServiceBySeries(getSeries());
	}

	private boolean legalKeyIdParam() {
		return null != keyId;
	}

	private void createEmptyIssueList() {
		PageInfo<FourTeamsIssueNew> pageInfo = new PageInfo<FourTeamsIssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<FourTeamsIssueNew>());
		gridPage = new GridPage(pageInfo);
	}

	private boolean validateInput(FourTeamsIssueNew issue,
			List<FourTeamsIssueAttachFile> files) {
		ValidateResult result = getActualIssueManageStrategy().validate(issue,
				files);
		if (result.hasError()) {
			errorMessage = result.getErrorMessages();
		}
		return !result.hasError();
	}

	private boolean validateDealInput(FourTeamsIssueLogNew log,
			FourTeamsIssueOperate operate, List<FourTeamsIssueAttachFile> files) {
		if (operation == null)
			return true;
		ValidateResult result = getActualIssueManageStrategy().validateDealLog(
				operate, operation, files);
		if (result.hasError()) {
			errorMessage = result.getErrorMessages();
		}
		return !result.hasError();
	}

	private void findReportedAssignedByDepartment(Long keyId,
			List<FourTeamsIssueOperate> canDoList) throws Exception {
		page = 1;
		rows = 1000;
		Map<String, List<Organization>> targetOrgs = new LinkedHashMap<String, List<Organization>>();
		if (null != canDoList && canDoList.size() > 0) {
			for (FourTeamsIssueOperate operate : canDoList) {
				dealCode = operate.getCode();
				if (FourTeamsIssueOperate.ASSIGN.getCode() == operate.getCode()) {
					adminTarget = true;
					searchUniqueTrgetOrg();
					targetOrgs.put("assignAdmin",
							autoCompleteDataTransformOrganization(gridPage)); // 交办行政
					adminTarget = false;
					searchUniqueTrgetOrg();
					targetOrgs.put("assignFun",
							autoCompleteDataTransformOrganization(gridPage)); // 交办职能
					searchUniqueTrgetOrg();
				} else if (FourTeamsIssueOperate.SUBMIT.getCode() == operate
						.getCode()) {
					adminTarget = true;
					searchUniqueTrgetOrg();
					targetOrgs.put("submitAdmin",
							autoCompleteDataTransformOrganization(gridPage)); // 上报行政
					adminTarget = false;
					searchUniqueTrgetOrg();
					targetOrgs.put("submitFun",
							autoCompleteDataTransformOrganization(gridPage));// 上报职能
				}
			}
		}
		jsonMap.put("targetOrgs", targetOrgs);
	}

	private List<Organization> autoCompleteDataTransformOrganization(
			GridPage gridPage) {
		if (null == gridPage)
			return null;
		List<Organization> list = new ArrayList<Organization>();
		Organization organization = null;
		AutoCompleteData autoCompleteData = null;
		for (int i = 0; i < gridPage.getRows().size(); i++) {
			organization = new Organization();
			autoCompleteData = (AutoCompleteData) gridPage.getRows().get(i);
			organization.setId(Long.valueOf(autoCompleteData.getValue()));
			organization.setOrgName(autoCompleteData.getLabel());
			list.add(organization);
		}
		return list;
	}

	private void hiddenSomeOperate(FourTeamsIssueStep step) {
		if (step.getBackTo() == null) {
			canDoList.remove(FourTeamsIssueOperate.BACK);
		}
		canDoList.remove(FourTeamsIssueOperate.CANCEL_SUPERVISE);
		canDoList.remove(FourTeamsIssueOperate.CONCEPT);
		canDoList.remove(FourTeamsIssueOperate.INSTRUCT);
		canDoList.remove(FourTeamsIssueOperate.NORMAL_SUPERVISE);
		canDoList.remove(FourTeamsIssueOperate.READ);
		canDoList.remove(FourTeamsIssueOperate.REPORT_TO);
		canDoList.remove(FourTeamsIssueOperate.YELLOW_SUPERVISE);
		canDoList.remove(FourTeamsIssueOperate.RED_SUPERVISE);
	}

	private void fillOperationLog(FourTeamsIssueLogNew log,
			FourTeamsIssueStep step) {
		if (log != null) {
			log.setIssue(step.getIssue());
			log.setDealUserName(getCurrentLoginedUser().getName());
			log.setMobile(getCurrentLoginedUser().getMobile());
			log.setDealOrg(getCurrentLoginedOrg());
		}
	}

	private Organization getCurrentLoginedOrg() {
		return organizationDubboService.getFullOrgById(ThreadVariable
				.getSession().getOrganization().getId());
	}

	private User getCurrentLoginedUser() {
		return ThreadVariable.getUser();
	}

	private FourTeamsIssueManageStrategy getActualIssueManageStrategy() {
		if (strategy == null) {
			strategy = factory.getIssueManageStrategyByModule(moduleName);
		}
		return strategy;
	}

	private void fillPropertyDefaultValue(FourTeamsIssueNew issue) {
		if (!levelGreatThenTown(keyId)) {
			Organization org = organizationDubboService.getSimpleOrgById(keyId);
			issue.setOccurOrg(org);
		}
		// 新增事件的时候给它一个默认的时间
		// 否则在页面发生时间为空 js组件就会设置一个默认时间，但该时间是客户端时间可能会与服务器时间不同步，验证发生时间的时候无法通过。
		issue.setOccurDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
	}

	private boolean levelGreatThenTown(Long orgId) {
		Organization fullOrg = organizationDubboService.getFullOrgById(orgId);
		return OrganizationLevel.levelCompare(fullOrg.getOrgLevel()
				.getInternalId(), OrganizationLevel.TOWN) > 0;
	}

	protected String getSeries() {
		return SYS_SERIES;
	}

	@Action(value = "getIssueActionForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "jsonMap", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getIssueAction() throws Exception {
		if (isAdminPermissions()) {
			return SUCCESS;
		} else {
			List<FourTeamsIssueOperate> issueActions = new ArrayList<FourTeamsIssueOperate>();
			List<String> userPermissionAll = permissionService
					.findUserAllPermissionEnameByUserId(ThreadVariable
							.getUser().getId());
			List<String> permissions = isthesePermissions();
			userPermissionAll.retainAll(permissions);
			for (String ename : userPermissionAll) {
				issueActions.add(FourTeamsIssueOperate.pressionOperate
						.get(ename));
			}
			jsonMap.put("issueActions", issueActions);
		}
		return SUCCESS;
	}

	private boolean hasPermission(Long issueId, Long stepId) {
		boolean hasPermission = false;
		String userOrgCode = ThreadVariable.getOrganization()
				.getOrgInternalCode();
		List<FourTeamsIssueStep> issueSteps;
		if (issueId != null) {
			issueSteps = searchIssueStepService
					.searchIssueStepsByIssueId(issueId);
		} else {
			issueSteps = searchIssueStepService
					.searchAllIssueStepsByStepId(stepId);
		}

		if (issueSteps == null) {
			return true;
		}
		for (FourTeamsIssueStep IssueStep : issueSteps) {
			Organization issueStepOrg = IssueStep.getTarget();
			String issueStepOrgCode;
			if (issueStepOrg != null) {
				issueStepOrgCode = issueStepOrg.getOrgInternalCode();
			} else {
				continue;
			}
			if (StringUtil.isStringAvaliable(issueStepOrgCode)
					&& issueStepOrgCode.indexOf(userOrgCode) == 0) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}

	private boolean isAdminPermissions() {
		List<FourTeamsIssueOperate> issueActions = new ArrayList<FourTeamsIssueOperate>();
		if (ThreadVariable.getUser().isAdmin() == true) {
			for (String ename : isthesePermissions()) {
				issueActions.add(FourTeamsIssueOperate.pressionOperate
						.get(ename));
			}
			jsonMap.put("issueActions", issueActions);
			return true;
		}

		return false;
	}

	private List<String> isthesePermissions() {
		List<String> permissions = new ArrayList<String>();
		permissions.add("normalIssue");
		permissions.add("yellowCardIssue");
		permissions.add("redCardIssue");
		permissions.add("cancleSuperviseIssue");
		return permissions;
	}

	@Action(value = "findIssueTypesForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "jsonMap", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findIssueTypes() throws Exception {
		prepareExistedIssueTypes();
		jsonMap.put("民生服务", contradiction);
		jsonMap.put("治安、安全隐患", specialisation);
		jsonMap.put("矛盾纠纷", peopleliveService);
		jsonMap.put("服务审批", itemIssueService);
		return SUCCESS;
	}

	private void prepareExistedIssueTypes() {
		peopleliveService = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.PEOPLELIVE_SERVICE);
		resolveTheContradictions = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.RESOLVETHECONTRADICTIONS);

		securityPrecautions = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.SECURITYPRECAUTIONS);
		specialPopulations = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.SPECIALPOPULATIONS);
		socialConditions = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.SOCIALCONDITIONS);
		policiesAndLaws = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.POLICIESANDLAWS);
		emergencies = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.EMERGENCIES);
		otherType = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.OTHERMANAGE);
	}

	private void createEmptyOperationLogByStepId(Long stepId) {
		FourTeamsIssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(stepId);
		createEmptyOperationLogByStep(step);
	}

	private void createEmptyOperationLogByStep(FourTeamsIssueStep step) {
		operation = new FourTeamsIssueLogNew();
		fillOperationLog(operation, step);
	}

	private void createAttachFilesForMobile() {
		if (null != attachFile && !"".equals(attachFile)) {
			String[] arrStr = attachFile.split(",");
			attachFiles = new String[arrStr.length];
			for (int i = 0; i < arrStr.length; i++) {
				if (null != arrStr[i] && !"".equals(arrStr[i])) {
					attachFiles[i] = "," + arrStr[i];
				}
			}
		}
		if (null != attachFiles && attachFiles.length == 1
				&& !attachFiles[0].startsWith(",")) {
			attachFiles[0] = "," + attachFiles[0];
		}
	}

	@Action(value = "checkOccurOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "checkOccurOrgIdIsTown", "ignoreHierarchy", "false" }) })
	public String checkOccurOrgId() throws Exception {
		if (issue == null || issue.getOccurOrg() == null) {
			this.errorMessage = "网格不得为空!";
			return ERROR;
		} else {
			checkOccurOrgIdIsTown = organizationDubboService
					.isTownOrganization(issue.getOccurOrg().getId());
		}
		return SUCCESS;
	}

	@Autowired
	private RegradedPointService regradedPointService;

	@Actions({
			@Action(value = "issueGrade", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsIssueGradeDlg.jsp") }),
			@Action(value = "issueGradeForMobile", results = { @Result(name = "success", type = "json", params = {
					"root", "issue", "ignoreHierarchy", "false" }) }) })
	public String issueGrade() throws Exception {
		if (legalKeyIdParam()) {
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			loadIssueOperationLogs(issue);
			if (issueDealLogs != null) {
				issue.setIssueDealLogs(issueDealLogs);
				List<Long> idList = new ArrayList<Long>();
				for (FourTeamsIssueLogNew issueLogNew : issueDealLogs) {
					idList.add(issueLogNew.getId());
				}
				issue.setRegradedPointsList(regradedPointService
						.queryRegradedPointsForListByIds(idList));
			}
		}
		return SUCCESS;
	}

	@Action(value = "issueGradeHistory", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String issueGradeHistory() throws Exception {
		if (legalKeyIdParam()) {
			gridPage = regradedPointService
					.queryIssueGradeHistoryForPageResultByIssueId(false, keyId,
							page, rows, sidx, sord);
		}
		return SUCCESS;
	}

	@Action(value = "issueGradeIsVisabled", results = { @Result(name = "success", type = "json", params = {
			"root", "callback" }) })
	public String issueGradeIsVisabled() throws Exception {
		if (legalKeyIdParam()) {
			callback = regradedPointService.issueGradeIsVisabled(keyId);
			if ("0".equals(callback)) {
				callback = "该事件已被统计，不可再打分";
			}
		} else {
			callback = "参数错误";
		}
		return SUCCESS;
	}

	@Action(value = "saveIssueGrade", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String saveIssueGrade() throws Exception {
		if (doIssueAccord() != null) {
			return ERROR;
		}
		if (issue == null || issue.getId() == null
				|| issue.getId().longValue() == 0L || issueAccord == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		issueService.saveIssueGrade(issue.getId(), issueAccord);
		return SUCCESS;
	}

	private String doIssueAccord() throws Exception {
		if (issueAccords != null && isEmpty(issueAccords.getScore())
				&& isEmpty(issueAccords.getType())
				&& isEmpty(issueAccords.getOrgId())
				&& isEmpty(issueAccords.getInfo())
				&& isEmpty(issueAccords.getLogId())) {
			String scores[] = issueAccords.getScore().split(",");
			String types[] = issueAccords.getType().split(",");
			String orgIds[] = issueAccords.getOrgId().split(",");
			String logIds[] = issueAccords.getLogId().split(",");
			int len = scores.length;
			if (len != types.length || len != orgIds.length
					|| len != logIds.length) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			Double score[] = new Double[scores.length];
			Integer type[] = new Integer[types.length];
			Long orgId[] = new Long[orgIds.length];
			Long logId[] = new Long[logIds.length];
			for (int i = 0; i < len; i++) {
				score[i] = Double.parseDouble(scores[i]);
				type[i] = Integer.parseInt(types[i]);
				orgId[i] = Long.parseLong(orgIds[i]);
				logId[i] = Long.parseLong(logIds[i]);
			}
			issueAccord = new FourTeamsIssueAccord();
			issueAccord.setScore(score);
			issueAccord.setType(type);
			issueAccord.setOrgId(orgId);
			issueAccord.setLogId(logId);
			issueAccord.setInfo(issueAccords.getInfo().split(","));
		}
		return null;
	}

	/**
	 * 
	 * @Title: findIssueIfExistForMobile
	 * @Description: TODO用来判断事件是否已存在数据库中 手机端
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-5-14 上午10:46:47
	 */
	public boolean findIssueIfExistForMobile(FourTeamsIssueNew issueNew)
			throws Exception {
		boolean flag = false;
		Integer countVal = issueService.findIssueExistForMobile(issueNew);
		if (countVal != null && countVal > 0) {
			flag = true;
		}
		return flag;
	}

	private boolean isEmpty(String arg) {
		return arg != null && !"".equals(arg);
	}

	public Map<String, List<IssueType>> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(Map<String, List<IssueType>> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public List<FourTeamsIssueTypeViewDefine> getIssueTypeNames() {
		return issueTypeNames;
	}

	public void setIssueTypeNames(
			List<FourTeamsIssueTypeViewDefine> issueTypeNames) {
		this.issueTypeNames = issueTypeNames;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public FourTeamsIssueNew getIssue() {
		return issue;
	}

	public void setIssue(FourTeamsIssueNew issue) {
		this.issue = issue;
	}

	public FourTeamsIssueViewObject getIssueVO() {
		return issueVO;
	}

	public void setIssueVO(FourTeamsIssueViewObject issueVO) {
		this.issueVO = issueVO;
	}

	public String getSelectedTypes() {
		return selectedTypes;
	}

	public void setSelectedTypes(String selectedTypes) {
		this.selectedTypes = selectedTypes;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<FourTeamsIssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(
			List<FourTeamsIssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public List<FourTeamsIssueLogNew> getIssueDealLogs() {
		return issueDealLogs;
	}

	public void setIssueDealLogs(List<FourTeamsIssueLogNew> issueDealLogs) {
		this.issueDealLogs = issueDealLogs;
	}

	public Map<Long, List<FourTeamsIssueAttachFile>> getIssueLogAttachFiles() {
		return issueLogAttachFiles;
	}

	public void setIssueLogAttachFiles(
			Map<Long, List<FourTeamsIssueAttachFile>> issueLogAttachFiles) {
		this.issueLogAttachFiles = issueLogAttachFiles;
	}

	public List<FourTeamsIssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<FourTeamsIssueOperate> canDoList) {
		this.canDoList = canDoList;
	}

	public FourTeamsIssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(FourTeamsIssueLogNew operation) {
		this.operation = operation;
	}

	public int getDealCode() {
		return dealCode;
	}

	public void setDealCode(int dealCode) {
		this.dealCode = dealCode;
	}

	public String getTellOrgIds() {
		return tellOrgIds;
	}

	public void setTellOrgIds(String tellOrgIds) {
		this.tellOrgIds = tellOrgIds;
	}

	public String getExceptIds() {
		return exceptIds;
	}

	public void setExceptIds(String exceptId) {
		this.exceptIds = exceptId;
	}

	public boolean isAdminTarget() {
		return adminTarget;
	}

	public void setAdminTarget(boolean adminTarget) {
		this.adminTarget = adminTarget;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getStepId() {
		return stepId;
	}

	public void setStepId(long stepId) {
		this.stepId = stepId;
	}

	public List<IssueType> getContradiction() {
		return contradiction;
	}

	public void setContradiction(List<IssueType> contradiction) {
		this.contradiction = contradiction;
	}

	public List<IssueType> getOtherType() {
		return otherType;
	}

	public void setOtherType(List<IssueType> otherType) {
		this.otherType = otherType;
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

	public List<String> getIssueHasTypeDomainName() {
		return issueHasTypeDomainName;
	}

	public void setIssueHasTypeDomainName(List<String> issueHasTypeDomainName) {
		this.issueHasTypeDomainName = issueHasTypeDomainName;
	}

	public void setIssueEvaluate(FourTeamsIssueEvaluate issueEvaluate) {
		this.issueEvaluate = issueEvaluate;
	}

	public FourTeamsIssueEvaluate getIssueEvaluate() {
		return issueEvaluate;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public void setData(List<FourTeamsIssueMap> data) {
		this.data = data;
	}

	public List<FourTeamsIssueMap> getData() {
		return data;
	}

	public FourTeamsTopIssue getTopIssue() {
		return topIssue;
	}

	public void setTopIssue(FourTeamsTopIssue topIssue) {
		this.topIssue = topIssue;
	}

	public FourTeamsIssueMap getIssueMap() {
		return issueMap;
	}

	public void setIssueMap(FourTeamsIssueMap issueMap) {
		this.issueMap = issueMap;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApprovalItem getApprovalItem() {
		return approvalItem;
	}

	public void setApprovalItem(ApprovalItem approvalItem) {
		this.approvalItem = approvalItem;
	}

	public List<IssueType> getItemIssueService() {
		return itemIssueService;
	}

	public void setItemIssueService(List<IssueType> itemIssueService) {
		this.itemIssueService = itemIssueService;
	}

	public List<ApprovalItemFile> getApprovalItemFileList() {
		return approvalItemFileList;
	}

	public void setApprovalItemFileList(
			List<ApprovalItemFile> approvalItemFileList) {
		this.approvalItemFileList = approvalItemFileList;
	}

	public List<FourTeamsIssueLogNew> getIssueLogs() {
		return issueLogs;
	}

	public void setIssueLogs(List<FourTeamsIssueLogNew> issueLogs) {
		this.issueLogs = issueLogs;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public String getInvolvedPlace() {
		return involvedPlace;
	}

	public void setInvolvedPlace(String involvedPlace) {
		this.involvedPlace = involvedPlace;
	}

	public String getInvolvedPersonnel() {
		return involvedPersonnel;
	}

	public void setInvolvedPersonnel(String involvedPersonnel) {
		this.involvedPersonnel = involvedPersonnel;
	}

	public Boolean getIsInvolvedPlace() {
		return isInvolvedPlace;
	}

	public void setIsInvolvedPlace(Boolean isInvolvedPlace) {
		this.isInvolvedPlace = isInvolvedPlace;
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

	public Map<String, String> getEmphas() {
		return emphas;
	}

	public void setEmphas(Map<String, String> emphas) {
		this.emphas = emphas;
	}

	public boolean isCheckOccurOrgIdIsTown() {
		return checkOccurOrgIdIsTown;
	}

	public void setCheckOccurOrgIdIsTown(boolean checkOccurOrgIdIsTown) {
		this.checkOccurOrgIdIsTown = checkOccurOrgIdIsTown;
	}

	public Map<String, List<EmphasesVo>> getRelatePersonMap() {
		return relatePersonMap;
	}

	public void setRelatePersonMap(Map<String, List<EmphasesVo>> relatePersonMap) {
		this.relatePersonMap = relatePersonMap;
	}

	public Map<String, List<EmphasesVo>> getRelatePlacesMap() {
		return relatePlacesMap;
	}

	public void setRelatePlacesMap(Map<String, List<EmphasesVo>> relatePlacesMap) {
		this.relatePlacesMap = relatePlacesMap;
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

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public Long getFunOrgId() {
		return funOrgId;
	}

	public void setFunOrgId(Long funOrgId) {
		this.funOrgId = funOrgId;
	}

	public Map<Long, String> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(Map<Long, String> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setResolveTheContradictions(
			List<IssueType> resolveTheContradictions) {
		this.resolveTheContradictions = resolveTheContradictions;
	}

	public List<IssueType> getResolveTheContradictions() {
		return resolveTheContradictions;
	}

	public void setSecurityPrecautions(List<IssueType> securityPrecautions) {
		this.securityPrecautions = securityPrecautions;
	}

	public List<IssueType> getSecurityPrecautions() {
		return securityPrecautions;
	}

	public void setSpecialPopulations(List<IssueType> specialPopulations) {
		this.specialPopulations = specialPopulations;
	}

	public List<IssueType> getSpecialPopulations() {
		return specialPopulations;
	}

	public void setSocialConditions(List<IssueType> socialConditions) {
		this.socialConditions = socialConditions;
	}

	public List<IssueType> getSocialConditions() {
		return socialConditions;
	}

	public void setPoliciesAndLaws(List<IssueType> policiesAndLaws) {
		this.policiesAndLaws = policiesAndLaws;
	}

	public List<IssueType> getPoliciesAndLaws() {
		return policiesAndLaws;
	}

	public void setEmergencies(List<IssueType> emergencies) {
		this.emergencies = emergencies;
	}

	public List<IssueType> getEmergencies() {
		return emergencies;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getViewType() {
		return viewType;
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

	public void setIssueHasType(IssueType issueHasType) {
		this.issueHasType = issueHasType;
	}

	public IssueType getIssueHasType() {
		return issueHasType;
	}

	public Long getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(Long functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
	}

	public Long getSourceType() {
		return sourceType;
	}

	public void setSourceType(Long sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getSourceTypeInternalId() {
		return sourceTypeInternalId;
	}

	public void setSourceTypeInternalId(Integer sourceTypeInternalId) {
		this.sourceTypeInternalId = sourceTypeInternalId;
	}

	public Integer getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Integer orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public FourTeamsIssueAccord getIssueAccord() {
		return issueAccord;
	}

	public void setIssueAccord(FourTeamsIssueAccord issueAccord) {
		this.issueAccord = issueAccord;
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

	public FourTeamsIssueSkiprule getIssueSkiprule() {
		return issueSkiprule;
	}

	public void setIssueSkiprule(FourTeamsIssueSkiprule issueSkiprule) {
		this.issueSkiprule = issueSkiprule;
	}

	public void setIssueLogForEdit(FourTeamsIssueLogNew issueLogForEdit) {
		this.issueLogForEdit = issueLogForEdit;
	}

	public FourTeamsIssueLogNew getIssueLogForEdit() {
		return issueLogForEdit;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public FourTeamsIssueAccords getIssueAccords() {
		return issueAccords;
	}

	public void setIssueAccords(FourTeamsIssueAccords issueAccords) {
		this.issueAccords = issueAccords;
	}

	public void setViewProcess(Integer viewProcess) {
		this.viewProcess = viewProcess;
	}

	public Integer getViewProcess() {
		return viewProcess;
	}

	public String getSelectedIssueType() {
		return selectedIssueType;
	}

	public void setSelectedIssueType(String selectedIssueType) {
		this.selectedIssueType = selectedIssueType;
	}

	public void setIssueRelatedPeopleFixPhones(
			String issueRelatedPeopleFixPhones) {
		this.issueRelatedPeopleFixPhones = issueRelatedPeopleFixPhones;
	}

	public String getIssueRelatedPeopleFixPhones() {
		return issueRelatedPeopleFixPhones;
	}

	public void setIssueEvaluateAttachFiles(
			List<FourTeamsIssueAttachFile> issueEvaluateAttachFiles) {
		this.issueEvaluateAttachFiles = issueEvaluateAttachFiles;
	}

	public List<FourTeamsIssueAttachFile> getIssueEvaluateAttachFiles() {
		return issueEvaluateAttachFiles;
	}

	public List<FourTeamsIssueAttachFile> getDocfilesByIdAndModuleKey(
			Long issueId, String moduleType, String fileType) {
		return issueService.getDocfilesByIdAndModuleKey(issueId, moduleType,
				fileType);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCreateOrgByTencent() {
		return createOrgByTencent;
	}

	public void setCreateOrgByTencent(String createOrgByTencent) {
		this.createOrgByTencent = createOrgByTencent;
	}

	public List<Inbox> getMap() {
		return map;
	}

	public void setMap(List<Inbox> map) {
		this.map = map;
	}

	public List<InboxAttachment> getInboxAttachmentList() {
		return inboxAttachmentList;
	}

	public void setInboxAttachmentList(List<InboxAttachment> inboxAttachmentList) {
		this.inboxAttachmentList = inboxAttachmentList;
	}

	public List<InboxAttachment> getMapAtt() {
		return mapAtt;
	}

	public void setMapAtt(List<InboxAttachment> mapAtt) {
		this.mapAtt = mapAtt;
	}

	public String getInboxIds() {
		return inboxIds;
	}

	public void setInboxIds(String inboxIds) {
		this.inboxIds = inboxIds;
	}

	public Long getInboxId() {
		return inboxId;
	}

	public void setInboxId(Long inboxId) {
		this.inboxId = inboxId;
	}

	public Inbox getInbox() {
		return inbox;
	}

	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	public TextSendMessage getTextSendMessage() {
		return textSendMessage;
	}

	public void setTextSendMessage(TextSendMessage textSendMessage) {
		this.textSendMessage = textSendMessage;
	}

	public String getSeachValue() {
		return seachValue;
	}

	public void setSeachValue(String seachValue) {
		this.seachValue = seachValue;
	}

	public String getFourTeamsType() {
		return fourTeamsType;
	}

	public void setFourTeamsType(String fourTeamsType) {
		this.fourTeamsType = fourTeamsType;
	}

}
