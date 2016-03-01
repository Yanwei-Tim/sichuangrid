package com.tianque.issue.controller;

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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.URL;
import com.tianque.account.api.LedgerConvertDubboService;
import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.approval.service.ApprovalItemFileService;
import com.tianque.approval.service.ApprovalItemService;
import com.tianque.approval.service.ItemService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
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
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.EmphasesVo;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.constants.IssueViewType;
import com.tianque.issue.controller.strategy.IssueManageStrategy;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.IssueAccord;
import com.tianque.issue.domain.IssueAccords;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueRelatedPeople;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStandardForFunOrg;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.domain.TopIssue;
import com.tianque.issue.factory.IssueManageStrategyFactory;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.CompletedIssueService;
import com.tianque.issue.service.IssueEvaluateService;
import com.tianque.issue.service.IssueHistoryService;
import com.tianque.issue.service.IssueRelatedPeopleService;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.service.IssueSkipruleService;
import com.tianque.issue.service.IssueStandardForFunOrgService;
import com.tianque.issue.service.SearchIssueStepService;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.uitl.IssueToCMSUtil;
import com.tianque.issue.vo.IssueTypeViewDefine;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.issueLeaderViewCache.service.IssueLeaderViewCacheService;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;
import com.tianque.plugin.weChat.domain.inbox.PreciseInbox;
import com.tianque.plugin.weChat.domain.inbox.ReplyMessage;
import com.tianque.plugin.weChat.domain.sendMessage.text.TextSendMessage;
import com.tianque.plugin.weChat.service.InboxAttachmentService;
import com.tianque.plugin.weChat.service.InboxService;
import com.tianque.plugin.weChat.service.PreciseInboxService;
import com.tianque.plugin.weChat.service.ReplyMessageService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.service.IssueLogService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.RegradedPointService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tenHouseholdsJoint.domain.ReceiveMsgInfo;
import com.tianque.tenHouseholdsJoint.service.ReceiveMsgInfoService;
import com.tianque.tqSearch.common.TqSolrSearchCommonOperate;
import com.tianque.tqSearch.constant.TqSolrSearchOperateType;
import com.tianque.tqSearch.convert.IssueSolrDocumentConvert;
import com.tianque.working.service.WorkDiaryService;

@Controller("issueController")
@Scope("prototype")
@Namespace("/issues/issueManage")
public class IssueController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private IssueManageStrategyFactory factory;
	@Autowired
	private IssueService issueService;
	@Autowired
	private IssueEvaluateService issueEvaluateService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ApprovalItemService approvalItemService;
	@Autowired
	private ApprovalItemFileService approvalItemFileService;
	@Autowired
	private IssueLogService issueLogService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private IssueStandardForFunOrgService issueStandardForFunOrgService;
	@Autowired
	private IssueSkipruleService issueSkipruleService;

	@Autowired
	private IssueRelatedPeopleService issueRelatedPeopleService;

	@Autowired
	private SearchIssueStepService searchIssueStepService;

	@Autowired
	private InboxAttachmentService inboxAttachmentService;
	@Autowired
	private InboxService inboxService;
	@Autowired
	private PreciseInboxService preciseInboxService;
	@Autowired
	private CompletedIssueService completedIssueService;
	@Autowired
	private ReceiveMsgInfoService receiveMsgInfoService;

	@Autowired
	private LedgerConvertDubboService ledgerConvertDubboService;
	@Autowired
	private TqSolrSearchCommonOperate tqSolrSearchCommonOperate;
	@Autowired
	private ReplyMessageService replyMessageService;

	private IssueType issueHasType;

	private IssueStep issueStep;
	/** 事件快速筛选存储的组织机构id */
	private Long searchOrgId;
	/** 事件快速筛选时，根据orgLevel获取的值 */
	private int orgCodeFindLevel;

	private Long issueId;
	/** 是否是历史的事件 */
	private boolean isHistory;
	@Autowired
	private IssueHistoryService issueHistoryService;

	private static Logger logger = LoggerFactory
			.getLogger(IssueController.class);

	private final static String SYS_SERIES = IssueServiceFactory.DEFAULT_SERIES;
	/** 事件处理记录 日志 */
	private List<IssueLogNew> issueLogs;
	/** 事件实体类 用于后台保存事件数据 */
	private IssueNew issue;
	/** 事件实体类 用于前 台显示事件的相关数据 */
	private IssueViewObject issueVO;
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
	private IssueLogNew operation;
	/** 事件分类 key是大类名称(民生服务、治安、安全隐...) value是每个大类的下面的小类 用于在页面上显示每个大类下小类 * */
	private Map<String, List<IssueType>> issueTypes;
	/** 事件分类的大类名称 (民生服务、治安、安全隐...) 用于页面上显示分类名称 * */
	private List<IssueTypeViewDefine> issueTypeNames;
	/** 事件已经选择的所属类型的名称 */
	private List<String> issueHasTypeDomainName;
	private String moduleName;
	/** 选择的事件类型id 如果有多个类型中间以分号分割 */
	private String selectedTypes;
	/** 从页面上提交过来的附件文件id(修改事件的时候)和名称 eg: id,fileName */
	private String[] attachFiles;
	/** 事件中包含的附件集合 用于在页面显示附件 */
	private List<IssueAttachFile> issueAttachFiles = new ArrayList<IssueAttachFile>();
	/** 事件的处理记录 用于在页面上显示处理记录 */
	private List<IssueLogNew> issueDealLogs;
	/** 事件处办理过程中添加的附件 用于页面显示 */
	private Map<Long, List<IssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<IssueAttachFile>>();
	private List<IssueAttachFile> issueEvaluateAttachFiles = new ArrayList<IssueAttachFile>();
	/** 可以对事件进行办理的操作列表 */
	private List<IssueOperate> canDoList;
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
	// 是否上报到12345指挥中心（呼叫中心）
	private boolean report12345;

	private IssueManageStrategy strategy;
	/** 服务审批号 */
	private String approvalNumber;
	private Long issueType;
	/** 事件验证实体类 */
	private IssueEvaluate issueEvaluate;
	/** 获取到的事件处理记录的图表视图数据 用于前台组件生事件处理记录成图表 */
	private List<IssueMap> data;
	/** 事件置顶操作 用来封装事件的id、orgid和 事件子模块(待办事项、已办事项、已办结事项、历史遗留....) */
	private TopIssue topIssue;
	/** 用来封装 在事件处理记录图表视图查看部门处理记录时的提交数据 */
	private IssueMap issueMap;
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

	private IssueAccord issueAccord;// 考核打分
	private IssueAccords issueAccords;// 考核打分

	private String issueRelatedPeopleNames;// 相关人员姓名
	private String issueRelatedPeopleTelephones;// 相关人员手机
	private String issueRelatedPeopleFixPhones;// 相关人员手机

	private IssueSkiprule issueSkiprule;// 越级设置

	private IssueLogNew issueLogForEdit;
	private String statusType;// 区分是否是历史数据
	private String evaluationType;// 评价类型
	private Integer superviseType;// 事件的督办类型（超时事件的筛选条件）
	private Integer viewProcess;// 是否是查询大屏滚动的数据

	private String selectedIssueType;

	private Date date;
	private String createOrgByTencent;
	private List<Inbox> map = new ArrayList<Inbox>();
	private List<PreciseInbox> preciseInboxList = new ArrayList<PreciseInbox>();
	private List<InboxAttachment> inboxAttachmentList;
	private List<InboxAttachment> mapAtt = new ArrayList<InboxAttachment>();
	private String inboxIds;
	private Long inboxId;
	private Inbox inbox;
	private PreciseInbox preciseInbox;
	private TextSendMessage textSendMessage;
	/** 用户是否有事件办理的抄告操作 */
	private boolean isOperationOfTell;
	private int verificationCount;// 待验证数量
	private int gradeDelayCount;// 待评分数量
	private String issueGradeIsVisabled;// 是否评分
	private String source;// 工作台的待办事项，是高层还是基层，或者事件打印来源于待办或者已办结
	private String tqmobile;// 是否手机端事件
	private List<PropertyDict> urgentLevels;// 重大紧急等级
	private Boolean isSkip = false;// 是否越级
	@Autowired
	private CacheService cacheService;
	private User user;// 事件打印时候，显示创建本事件的人的名字和手机号码，用于显示信息来源和联系电话
	private static final int TOPSTATE = 0;// 验证通过的事件，流转到已办结列表的时候，默认没有置顶
	@Autowired
	private IssueLeaderViewCacheService issueLeaderViewCacheService;
	@Autowired
	private GlobalSettingService globalSettingService;
	// 微信模糊消息转事件用
	private String openId;

	// @Autowired
	// private IssueLeaderViewCacheService issueLeaderViewCacheService;

	@Action(value = "findItemTypeByDealOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"itemTypes", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findItemTypeByDealOrgId() throws Exception {
		List<IssueStandardForFunOrg> issueStandardForFunOrgs = issueStandardForFunOrgService
				.findItemTypeByFunOrgId(funOrgId);

		itemTypes = new HashMap<Long, String>();
		for (IssueStandardForFunOrg issueStandardForFunOrg : issueStandardForFunOrgs) {
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
					@Result(name = "addIssue", location = "/issue/issueManage/maintainIssueDlg.jsp"),
					@Result(name = "eventsToAccept", location = "/issue/otherModule/maintainInboxDlgs.jsp"),
					@Result(name = "preciseInboxIssues", location = "/issue/otherModule/maintainPreciseInboxDlgs.jsp"),
					@Result(name = "editIssueForTab", location = "/issue/issueManage/issueEditTabList.jsp"),
					@Result(name = "editIssue", location = "/issue/issueManage/maintainIssueDlg.jsp"),
					@Result(name = "editIssueForView", location = "/issue/issueManage/maintainIssueForViewDlg.jsp"),
					@Result(name = "editApproveItemIssue", location = "/approval/approvalItem/updateApprovalItemToIssueDlg.jsp"),
					@Result(name = "searchOrgs", location = "/issue/issueManage/deals/searchOrgDlg.jsp"),
					@Result(name = "search", location = "/issue/issueManage/searchIssueDlg.jsp"),
					@Result(name = "viewIssue", location = "/issue/issueManage/viewIssueDlg.jsp"),
					@Result(name = "viewIssueNew", location = "/issue/issueManage/viewIssueNewDlg.jsp"),
					@Result(name = "editIssueProcessRecord", location = "/issue/issueManage/editIssueProcessRecordDlg.jsp"),
					@Result(name = "editIssueLog", location = "/issue/issueManage/editIssueLogDlg.jsp"),
					@Result(name = "convertToWorkingRecord", location = "/issue/issueManage/completedIssueConvertToWorkingRecordDlg.jsp"),
					@Result(name = "gradeHistory", location = "/issue/issueManage/issueGradeHistory.jsp") }),
			@Action(value = "dispatchForGis", results = { @Result(name = "viewIssue", location = "/issue/issueManage/viewIssueDlg.jsp") }),
			@Action(value = "dispatchforBench", results = { @Result(name = "addIssue", location = "/workBench/issueManage/maintainIssueDlg.jsp") }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return forwardToAdd();
		} else if ("addTenHJointIssue".equals(mode)) {
			return forwardToAddTenHJointIssue();
		} else if ("eventsToAccept".equals(mode)) {
			return forwardToAddByTencent();
		} else if ("preciseInboxIssues".equals(mode)) {
			return forwardPreciseInboxIssues();
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
			// statusType为1的时候查询历史的事件，statusType为0或者为null的时候，查询近期的事件
			if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
				// 事件查看操作，查询历史的步骤表
				return forwardToViewHistoryIssue();
			} else {
				return forwardToViewNew();
			}
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			// statusType为1的时候，为历史事件，该事件步骤表查询历史的步骤表
			if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
				return forwardToViewHistory();
			} else {
				return forwardToView();
			}

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
		} else if (DialogMode.GRADEHISTORY.equals(mode)) {
			return findIssueEvaluate(keyId, mode);
		}
		return "view";
	}

	private String findIssueEvaluate(Long keyId, String mode) {
		if (keyId == null) {
			return ERROR;
		}
		try {
			issueEvaluate = issueEvaluateService
					.getIssueEvaluateByIssueIdForGrade(keyId);
			issue = issueService.getFullIssueByIssueId(keyId);
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("评分详情获取失败！", e);
			return ERROR;
		}
		return mode;
	}

	private String forwardToEditIssue(String mode) {
		if ("editIssueForTab".equalsIgnoreCase(mode)) {
			if (!hasPermission(null, keyId, Boolean.FALSE)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			return "editIssueForTab";
		} else if ("editIssueProcessRecord".equals(mode)) {
			if (!hasPermission(keyId, null, Boolean.FALSE)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			forwardToViewNew();
			return "editIssueProcessRecord";
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (!hasPermission(null, keyId, Boolean.FALSE)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			return forwardToEdit();
		}
		return ERROR;
	}

	private String forwardToViewHistoryIssue() {
		if (legalKeyIdParam()) {
			Long num = 0L;
			if (issuesKeyId > 0) {
				num = keyId;
				keyId = issuesKeyId;
			}
			if (!hasPermission(keyId, null, Boolean.TRUE)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			issue = issueHistoryService.getFullIssueHistoryById(keyId);
			issue.getOccurOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(issue
							.getOccurOrg().getId(), organizationDubboService));
			loadMainCharacters();
			loadRelatePersons();
			loadIssueTypeAndIssueTypeDomain();
			loadAttachFiles(issue);
			// 查看时 增加查看事件记录
			loadIssueHistoryOperationLogs(issue);
			mobileCompleValue();
			issueEvaluate = getActualIssueServiceInstance()
					.getIssueEvaluateById(keyId);
			keyId = num;
			return "viewIssueNew";
		} else {
			return ERROR;
		}
	}

	private String forwardToViewNew() {
		if (legalKeyIdParam()) {
			Long num = 0L;
			if (issuesKeyId > 0) {
				num = keyId;
				keyId = issuesKeyId;
			}
			if (!hasPermission(keyId, null, Boolean.FALSE)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			issue.getOccurOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(issue
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
		List<IssueRelatedPeople> issueRelatedPeoples = issueRelatedPeopleService
				.findIssueRelatedPeopleByIssueId(issue.getId());
		StringBuffer buffer = new StringBuffer();
		for (IssueRelatedPeople issueRelatedPeople : issueRelatedPeoples) {
			if (issueRelatedPeople != null) {
				if (StringUtil.isStringAvaliable(issueRelatedPeople.getName())) {
					buffer.append(issueRelatedPeople.getName());
				}
				buffer = loadPhoneOrFix(issueRelatedPeople, buffer);
			}
		}
		issue.setMainCharacters(buffer.length() == 0 ? "" : buffer.toString()
				.substring(0, buffer.length() - 1));
	}

	private StringBuffer loadPhoneOrFix(IssueRelatedPeople issueRelatedPeople,
			StringBuffer buffer) {
		if (StringUtil.isStringAvaliable(issueRelatedPeople.getTelephone())
				&& StringUtil.isStringAvaliable(issueRelatedPeople
						.getFixPhone())) {
			if (StringUtil.isStringAvaliable(issueRelatedPeople.getName())) {
				buffer.append("：");
			}
			buffer.append(issueRelatedPeople.getTelephone()).append("，")
					.append(issueRelatedPeople.getFixPhone()).append("；");
		} else if (StringUtil.isStringAvaliable(issueRelatedPeople
				.getTelephone())
				&& !StringUtil.isStringAvaliable(issueRelatedPeople
						.getFixPhone())) {
			if (StringUtil.isStringAvaliable(issueRelatedPeople.getName())) {
				buffer.append("：");
			}
			buffer.append(issueRelatedPeople.getTelephone()).append("；");
		} else if (!StringUtil.isStringAvaliable(issueRelatedPeople
				.getTelephone())
				&& StringUtil.isStringAvaliable(issueRelatedPeople
						.getFixPhone())) {
			if (StringUtil.isStringAvaliable(issueRelatedPeople.getName())) {
				buffer.append("，");
			}
			buffer.append(issueRelatedPeople.getFixPhone()).append("；");
		} else {
			buffer.append("；");
		}
		return buffer;
	}

	private void loadIssueTypeAndIssueTypeDomain() {
		IssueTypeDomain issueTypeDomain = issueTypeService
				.getIssueTypeDomainById(issue.getIssueType()
						.getIssueTypeDomain().getId());
		IssueType issueType = issueTypeService.getIssueTypeById(
				issueTypeDomain.getId(), issue.getIssueType().getId());
		issue.setIssueTypeDomainName(issueTypeDomain.getDomainName());
		issue.setIssueTypeName(issueType.getIssueTypeName());
	}

	private String forwardToAdd() {
		IssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			issue = new IssueNew();
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			return strategy.forwardToAdd();
		} else {
			return "error";
		}
	}

	/** 十户联防受理警情 */
	private String forwardToAddTenHJointIssue() {
		IssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			issue = new IssueNew();
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			if ("addTenHJointIssue".equals(mode)) {
				ReceiveMsgInfo receiveMsgInfo = receiveMsgInfoService
						.getReceiveMsgInfoById(id);
				Organization org = organizationDubboService
						.getSimpleOrgById(receiveMsgInfo.getOrganization()
								.getId());
				issue.setOccurOrg(org);
				issue.setOccurOrgInternalCode(org.getOrgInternalCode());
				issue.setSubject(org.getOrgName() + "发生警情");
				issue.setOccurDate(receiveMsgInfo.getSendDate());
				issue.setOccurLocation(receiveMsgInfo.getUser()
						.getFamilyAddress());
				List<IssueRelatedPeople> issueRelatedPeoples = new ArrayList<IssueRelatedPeople>();
				IssueRelatedPeople issueRelatedPeople = new IssueRelatedPeople();
				issueRelatedPeople.setName(receiveMsgInfo.getUser().getName());
				issueRelatedPeople.setTelephone(receiveMsgInfo.getUser()
						.getHelpLine());
				issueRelatedPeoples.add(issueRelatedPeople);
				issue.setIssueRelatedPeoples(issueRelatedPeoples);
				issue.setIssueContent(receiveMsgInfo.getMessage().getText());
				issue.setCenterLon(receiveMsgInfo.getUser().getCenterLon());
				issue.setCenterLat(receiveMsgInfo.getUser().getCenterLat());
				issue.setCenterLon2(receiveMsgInfo.getUser().getCenterLon2());
				issue.setCenterLat2(receiveMsgInfo.getUser().getCenterLat2());
				mode = DialogMode.ADD_MODE;
			}
			return strategy.forwardToAdd();
		} else {
			return "error";
		}
	}

	private String forwardToAddByTencent() {
		IssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			issue = new IssueNew();
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			date = null;
			
			createOrgByTencent = organizationDubboService.getSimpleOrgById(keyId).getOrgName();
			/*
			 * for (int i = 0; i < Arrays.asList(analyzePopulationIds()).size();
			 * i++) { inbox = inboxService.getInboxById(Arrays.asList(
			 * analyzePopulationIds()).get(i)); createOrgByTencent =
			 * organizationDubboService.getSimpleOrgById(
			 * inbox.getOrg().getId()).getOrgName(); if (date == null) { date =
			 * inbox.getCreateTime(); } issue = new IssueNew();
			 * issue.setOccurOrg(inbox.getOrg()); map.add(inbox);
			 * inboxAttachmentList = inboxAttachmentService
			 * .getInboxAttachmentByInboxId(Arrays.asList(
			 * analyzePopulationIds()).get(i)); if (inboxAttachmentList != null)
			 * { for (int j = 0; j < inboxAttachmentList.size(); j++) {
			 * 
			 * mapAtt.add(inboxAttachmentList.get(j)); } } }
			 */
			return "eventsToAccept";
		} else {
			return "error";
		}
	}

	/** 精确消息转为事件 */
	private String forwardPreciseInboxIssues() {
		IssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			issue = new IssueNew();
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			date = null;
			for (int i = 0; i < Arrays.asList(analyzePopulationIds()).size(); i++) {
				preciseInbox = preciseInboxService.getPreciseInboxById(Arrays
						.asList(analyzePopulationIds()).get(i));
				createOrgByTencent = organizationDubboService.getSimpleOrgById(
						preciseInbox.getOrg().getId()).getOrgName();
				if (date == null) {
					date = preciseInbox.getCreateDate();
				}
				issue = new IssueNew();
				if (preciseInbox != null && preciseInbox.getOrg() != null
						&& preciseInbox.getIssueName() != null
						&& preciseInbox.getOccurLocation() != null
						&& preciseInbox.getReportPeopleName() != null
						&& preciseInbox.getReportPeoplePhoneNumber() != null
						&& preciseInbox.getProfile() != null
						&& preciseInbox.getIssueTypeDomainId() != null
						&& preciseInbox.getIssueTypeId() != null) {
					issue.setOccurOrg(preciseInbox.getOrg());
					issue.setSubject(preciseInbox.getIssueName());
					issue.setOccurLocation(preciseInbox.getOccurLocation());
					issue.setSourcePerson(preciseInbox.getReportPeopleName());
					issue.setSourceMobile(Long.toString(preciseInbox
							.getReportPeoplePhoneNumber()));
					issue.setIssueContent(preciseInbox.getProfile());
					IssueType issueType = new IssueType();
					IssueTypeDomain issueTypeDomain = new IssueTypeDomain();
					issueTypeDomain.setId(preciseInbox.getIssueTypeDomainId());
					issueType.setId(preciseInbox.getIssueTypeId());
					issueType.setIssueTypeDomain(issueTypeDomain);
					issue.setIssueType(issueType);

					if (preciseInbox.getIssueScale() != null) {
						PropertyDict propertyDict = propertyDictService
								.getPropertyDictById(preciseInbox
										.getIssueScale());
						if (propertyDict != null) {
							issue.setIssueKind(propertyDict);
						}
					}
					if (preciseInbox.getRelatePeopleCount() != null) {
						issue.setRelatePeopleCount(preciseInbox
								.getRelatePeopleCount().intValue());
					}
					preciseInboxList.add(preciseInbox);
				}
			}
			return "preciseInboxIssues";
		} else {
			return "error";
		}
	}

	private String forwardToEdit() {
		if (legalKeyIdParam()) {
			IssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			loadAttachFiles(issue);
			loadFullIssueTypes(issue);
			loadIssueRelatedPeople(issue);
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
	private void loadIssueRelatedPeople(IssueNew issue) {
		issue.setIssueRelatedPeoples(issueRelatedPeopleService
				.findIssueRelatedPeopleByIssueId(issue.getId()));
	}

	private String forwardToViewHistory() {
		if (legalKeyIdParam()) {
			// 处理事件ID。大于0代表是处理里的查询。
			Long num = 0L;
			if (issuesKeyId > 0) {
				num = keyId;
				keyId = issuesKeyId;
			}
			IssueManageStrategy strategy = getActualIssueManageStrategy();
			// 根据事件id查询事件历史表
			issue = issueHistoryService.getFullIssueHistoryById(keyId);
			issue.getOccurOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(issue
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
			loadIssueHistoryOperationLogs(issue);
			mobileCompleValue();
			keyId = num;
			return strategy.forwardToView();
		} else {
			return "error";
		}
	}

	private String forwardToView() {
		if (legalKeyIdParam()) {
			// 处理事件ID。大于0代表是处理里的查询。
			Long num = 0L;
			if (issuesKeyId > 0) {
				num = keyId;
				keyId = issuesKeyId;
			}
			IssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance()
					.getFullIssueByIssueId(keyId);
			issue.getOccurOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(issue
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
					@Result(name = "default_dealing", location = "/issue/issueManage/deals/dealIssueDlg.jsp"),
					@Result(name = "default_simple_dealing", location = "/issue/issueManage/deals/simpleDealDlg.jsp"),
					@Result(name = "default_single_content_dealing", location = "/issue/issueManage/deals/singleContentIssueDlg.jsp"),
					@Result(name = "default_supervise_dealing", location = "/issue/issueManage/deals/superviseIssueDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/houseInfo/actualHouse/searchActualHouseDlg.jsp") }),
			@Action(value = "dispatchDealForBench", results = {
					@Result(name = "workBenchDeal", location = "/workBench/issueManage/dealIssueDlg.jsp"),
					@Result(name = "default_simple_dealing", location = "/workBench/issueManage/simpleDealDlg.jsp"),
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
		} else if ("workBenchDeal".equalsIgnoreCase(mode)) {
			return forwardToWorkBenchDeal();
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

	private void getUserOrgLevel() {
		Organization org = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		orgLevel = (long) org.getOrgLevel().getInternalId();

	}

	private String forwardToDeal() throws Exception {
		if (issuesKeyId > 0) {
			forwardToViewNew();
		}
		getUserOrgLevel();
		IssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(keyId);
		// 如果是待验证事件，跳过部门验证
		if (!getCurrentLoginedOrg().equals(step.getTarget())
				&& !(IssueState.ISSUEVERIFICATION_CODE == step.getStateCode())) {
			errorMessage = "你不能处理不属于你部门的事件";
			return ERROR;
		} else {
			operation = new IssueLogNew();
			fillOperationLog(operation, step);
			loadCandoOperationsOfDeal(keyId, isSkip);
			hiddenSomeOperate(step);
			mobileTransact(operation, keyId, canDoList);
			if (isSkip) {
				loadIssueSkipRule(keyId);
			}
			operation.setIssueStep(step);
			if (step.getFourTeams() != null) {
				operation.setFourTeamsName(step.getFourTeams().getNames());
			}
			// 根据用户类别（职能部门，行政部门）判断抄告操作
			isOperationOfTell = (operation.getDealOrg().getOrgType()
					.getInternalId() == OrganizationType.FUNCTIONAL_ORG) ? true
					: false;
		}
		return getActualIssueManageStrategy().forwardToDealing();
	}

	private String forwardToWorkBenchDeal() throws Exception {
		if (issuesKeyId > 0) {
			forwardToView();
		}
		getUserOrgLevel();
		IssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(keyId);
		// 如果是待验证事件，跳过部门验证
		if (!getCurrentLoginedOrg().equals(step.getTarget())
				&& !(IssueState.ISSUEVERIFICATION_CODE == step.getStateCode())) {
			errorMessage = "你不能处理不属于你部门的事件";
			return ERROR;
		} else {
			operation = new IssueLogNew();
			fillOperationLog(operation, step);
			loadCandoOperationsOfDeal(keyId, isSkip);
			hiddenSomeOperate(step);
			mobileTransact(operation, keyId, canDoList);
			if (isSkip) {
				loadIssueSkipRule(keyId);
			}
			operation.setIssueStep(step);
			if (step.getFourTeams() != null) {
				operation.setFourTeamsName(step.getFourTeams().getNames());
			}
			// 根据用户类别（职能部门，行政部门）判断抄告操作
			isOperationOfTell = (operation.getDealOrg().getOrgType()
					.getInternalId() == OrganizationType.FUNCTIONAL_ORG) ? true
					: false;
		}
		return getActualIssueManageStrategy().forwardToWorkBenchDeal();
	}

	@Action(value = "updateIssueStepUrgentLevelForCache", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String updateIssueStepUrgentLevelForCache() throws Exception {
		if (issueStep == null || issueStep.getId() == null
				|| issueStep.getEmergencyLevel() == null) {
			return ERROR;
		}
		String key = "issueStep_" + ThreadVariable.getUser().getId() + "_"
				+ issueStep.getId();
		cacheService.set(key, 30, issueStep.getEmergencyLevel());
		return SUCCESS;
	}

	@Actions({
			@Action(value = "dispatchUrgentLevel", results = { @Result(name = "success", location = "/issue/issueManage/emergencyLevel.jsp") }),
			@Action(value = "dispatchUrgentLevelOfWorkBench", results = {
					@Result(name = "success", type = "json", params = { "root",
							"urgentLevels", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String dispatchUrgentLevel() throws Exception {
		IssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(keyId);
		IssueNew issueNew = getActualIssueServiceInstance()
				.getFullIssueByStepId(keyId);
		loadFullIssueTypes(issueNew);
		IssueSkiprule isr = new IssueSkiprule();
		isr.setIssueTypeDomainId(issueNew.getIssueType().getIssueTypeDomain()
				.getId());
		isr.setIssueTypeId(issueNew.getIssueType().getId());
		isr.setOrgId(step.getTarget().getId());
		setUrgentLevels(issueSkipruleService.getUrgentLevelList(isr));
		return SUCCESS;
	}

	// 更新重大紧急程度到数据库,并清除缓存
	private void updateIssueStepUrgentLevel() {
		if (issueStep != null && issueStep.getEmergencyLevel() != null
				&& issueStep.getEmergencyLevel().getId() != null) {
			getActualIssueServiceInstance().updateEmergencylevelByIssueStepId(
					issueStep);
		}
		String key = "issueStep_" + ThreadVariable.getUser().getId() + "_"
				+ issueStep.getId();
		cacheService.remove(key);
	}

	private void loadIssueSkipRule(Long keyId) {
		issueStep = getActualIssueServiceInstance().getIssueStepById(keyId);
		IssueNew issueNew = getActualIssueServiceInstance()
				.getFullIssueByStepId(keyId);
		String key = "issueStep_" + ThreadVariable.getUser().getId() + "_"
				+ issueStep.getId();
		issueStep.setEmergencyLevel((PropertyDict) cacheService.get(key));
		issueSkiprule = issueSkipruleService.getIssueSkipruleByIssue(issueNew,
				issueStep);
	}

	private void mobileTransact(IssueLogNew operation, Long keyId,
			List<IssueOperate> canDoList) throws Exception {
		jsonMap.put("keyId", keyId);
		jsonMap.put("operation", operation);
		jsonMap.put("canDoList", canDoList);
		findReportedAssignedByDepartment(keyId, canDoList);
	}

	private boolean isSimpleContentDeal() {
		return IssueOperate.INSTRUCT.getCode() == dealCode
				|| IssueOperate.URGENT.getCode() == dealCode;
	}

	private String forwardToSingleContent() {
		createEmptyOperationLogByStepId(keyId);
		return "default_single_content_dealing";
	}

	private boolean isSuperviseDeal() {
		return IssueOperate.NORMAL_SUPERVISE.getCode() == dealCode
				|| IssueOperate.YELLOW_SUPERVISE.getCode() == dealCode
				|| IssueOperate.RED_SUPERVISE.getCode() == dealCode;
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
		List<IssueAttachFile> files = null;
		if (validateInput(issue, files)) {
			dealTime();
			getActualIssueServiceInstance().validateDataForAddIssue(issue,
					files, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			try {
				createAttachFilesForMobile();
				files = createIssueAttachFile(attachFiles);
			} catch (Exception e) {
				logger.error("新增事件时附件上传失败", e.getMessage());
				errorMessage = "附件上传失败！";
				return ERROR;
			}
			issueVO = getActualIssueServiceInstance().addIssue(issue, files,
					emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<IssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("新增事件，服务单号为：" + issueVO.getSerialNumber());
			workDiaryService.addWorkDiary(issueVO.getIssueId(), issueLog);
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
		List<IssueAttachFile> files = null;
		createAttachFilesForMobile();
		files = createInboxAttachFile(attachFiles);
		if (validateInput(issue, files)) {
			dealTime();
			issue.setSourceKind(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.SOURCE_KIND,
							IssueConstants.WECHAT_INPUT));
			issueVO = getActualIssueServiceInstance().addIssue(issue, files,
					emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<IssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("新增事件，服务单号为：" + issueVO.getSerialNumber());
			workDiaryService.addWorkDiary(issueVO.getIssueId(), issueLog);
			Long saveIssueId = issueVO.getIssueId();

			if (textSendMessage.getTouser() != null) {
				if(inbox==null){					
					inbox=new Inbox();
				}
				inbox.setServiceId(issueVO.getSerialNumber());
				inbox.setFromUserName(textSendMessage.getTouser());
				// 该用户下所有消息状态为已接入的消息
				inbox.setDealState(Constants.ACCESS);
				inbox.setIsKeyWordMsg(Constants.NOT_KEYWORDMSG);
				List<Inbox> inboxList = inboxService
						.findInboxsByOpenIdAndDealState(inbox);
                
				if (inboxList != null && !inboxList.isEmpty()) {
					if(inboxList.get(0)!=null&&inboxList.get(0).getToUserName()!=null){
						inbox.setToUserName(inboxList.get(0).getToUserName());
						inbox.setFromUserName(inboxList.get(0).getFromUserName());
						inbox.setCreateUser(inboxList.get(0).getCreateUser());
					}
					for (Inbox inbox2 : inboxList) {
						inbox2.setDealState(Constants.ACCEPT);
						inbox2.setIssueId(saveIssueId);
						inbox2.setServiceId(issueVO.getSerialNumber());
						inboxService.update(inbox2);
						
						//System.out.println(inbox2.getId()+"+"+inbox2.getDealState()+"+"+inbox2.getServiceId());

					}
				}

				// 获取回复当前客户消息记录
				List<ReplyMessage> replyMessageList = replyMessageService
						.findReplyMessagesByOpenIdAndDealState(
								inbox.getFromUserName(), Constants.NOT_ACCEPT);
				if (replyMessageList != null && !replyMessageList.isEmpty()) {

					for (ReplyMessage replyMessage : replyMessageList) {
						replyMessage.setIsIssue(Constants.ACCEPT);
						replyMessage.setServiceId(issueVO.getSerialNumber());
						System.out.println(replyMessage.getReplyMessageId()+"+"+replyMessage.getCreateUser()+"+"+replyMessage.getContent());
						replyMessageService.update(replyMessage);
					}
				}

				inboxService.replyMessage(inbox, textSendMessage);
			}
			return SUCCESS;
		}
		return ERROR;
	}

	@PermissionFilter(ename = "acceptIssue")
	@Actions({ @Action(value = "addIssueByTencentPreciseInbox", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String addIssueByTencentPreciseInbox() throws Exception {

		fillIssueProperty();
		prepareEmphas();
		List<IssueAttachFile> files = null;
		createAttachFilesForMobile();
		files = createInboxAttachFile(attachFiles);
		if (validateInput(issue, files)) {
			dealTime();
			issue.setSourceKind(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.SOURCE_KIND,
							IssueConstants.WECHAT_INPUT));
			issueVO = getActualIssueServiceInstance().addIssue(issue, files,
					emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<IssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("新增事件，服务单号为：" + issueVO.getSerialNumber());
			workDiaryService.addWorkDiary(issueVO.getIssueId(), issueLog);
			Long saveIssueId = issueVO.getIssueId();
			for (int i = 0; i < Arrays.asList(analyzePopulationIds()).size(); i++) {
				preciseInbox = preciseInboxService.getPreciseInboxById(Arrays
						.asList(analyzePopulationIds()).get(i));
				preciseInbox.setDealState(Constants.ACCEPT);
				preciseInbox.setIssueId(saveIssueId);
				preciseInbox.setServiceId(issueVO.getSerialNumber());
				preciseInboxService.update(preciseInbox);
				if (i != Arrays.asList(analyzePopulationIds()).size() - 1) {
					preciseInboxService.saveReplyMessage(preciseInbox,
							textSendMessage);
				}
			}
			preciseInboxService.replyMessage(preciseInbox, textSendMessage);

			return SUCCESS;
		}
		return ERROR;
	}

	private List<IssueAttachFile> createInboxAttachFile(String[] fileNameAndIdS)
			throws Exception {

		if (fileNameAndIdS == null) {
			return new ArrayList<IssueAttachFile>();
		}

		List<IssueAttachFile> list = new ArrayList<IssueAttachFile>();

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
				IssueAttachFile issueAttachFile = new IssueAttachFile();
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
		List<IssueAttachFile> files = null;
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
		List<IssueAttachFile> files = null;
		createAttachFilesForMobile();
		files = createIssueAttachFile(attachFiles);
		if (validateInput(issue, files)) {
			dealTime();
			issueVO = getActualIssueServiceInstance().updateIssue(issue, files,
					stepId, emphas, issueRelatedPeopleNames,
					issueRelatedPeopleTelephones, issueRelatedPeopleFixPhones);
			List<IssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("修改事件，服务单号为：" + issueVO.getSerialNumber());
			workDiaryService.addWorkDiary(issueVO.getIssueId(), issueLog);
			return SUCCESS;
		}
		return ERROR;
	}

	private List<IssueAttachFile> createIssueAttachFile(String[] fileNameAndIdS)
			throws Exception {

		if (fileNameAndIdS == null) {
			return new ArrayList<IssueAttachFile>();
		}

		List<IssueAttachFile> list = new ArrayList<IssueAttachFile>();

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

				IssueAttachFile issueAttachFile = new IssueAttachFile();

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
		if (issue.getSourceKind() == null
				|| issue.getSourceKind().getId() == null) {
			factory.getIssueManageStrategyByModule(moduleName)
					.fillIssueSourceProperty(issue);
			if (null != tqmobile && !"".equals(tqmobile)
					&& "true".equals(tqmobile)) {
				issue.setSourceKind(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.SOURCE_KIND,
								IssueConstants.MOBILE_INPUT));
			} else {
				issue.setSourceKind(propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.SOURCE_KIND,
								IssueConstants.PC_INPUT));
			}
		}
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
	@Transactional
	public String deal() throws Exception {
		IssueOperate operate = IssueOperate.parse(dealCode);
		createAttachFilesForMobile();
		List<IssueAttachFile> files = createIssueAttachFile(attachFiles);
		if (!validateDealInput(operation, operate, files)) {
			return ERROR;
		}
		if (IssueOperate.REPORT_TO.equals(operate)) {
			issueVO = getActualIssueServiceInstance().reportToCommandCenter(
					keyId, operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.GIVETO.equals(operate)) {
			issueVO = getActualIssueServiceInstance().giveTo(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.CONCEPT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().concept(keyId, operation);
			List<IssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("受理事件，服务单号为：" + issueVO.getSerialNumber());
			workDiaryService.addWorkDiary(issueVO.getIssueId(), issueLog);
		} else if (IssueOperate.READ.equals(operate)) {
			issueVO = getActualIssueServiceInstance().read(keyId, operation);
			List<IssueLogNew> issueLogs = issueLogService
					.findIssueLogsByIssueId(issueVO.getIssueId());
			IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
			issueLog.setContent("阅读事件，服务单号为：" + issueVO.getSerialNumber());
			workDiaryService.addWorkDiary(issueVO.getIssueId(), issueLog);
		} else if (IssueOperate.COMMENT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().comment(keyId, operation,
					files);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.COMPLETE.equals(operate)) {
			issueVO = getActualIssueServiceInstance().complete(keyId,
					operation, files);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.VERIFICATION.equals(operate)) {
			issueVO = getActualIssueServiceInstance()
					.verification(keyId, operation, files,
							IssueConstants.NOT_CHANGEINTOTHREERECORDS);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);

			// 添加到 已办结 的表中
			CompletedIssue completedIssue = createCompletedIssue(issueVO);
			completedIssue.setTopState(TOPSTATE);// 验证通过的事件，流转到已办结列表的时候，默认没有置顶
			completedIssueService.addCompletedIssue(completedIssue);

		} else if (IssueOperate.ASSIGN.equals(operate)) {
			updateIssueStepUrgentLevel();
			issueVO = getActualIssueServiceInstance().assign(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.SUBMIT.equals(operate)) {
			updateIssueStepUrgentLevel();
			issueVO = getActualIssueServiceInstance().submit(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.BACK.equals(operate)) {
			issueVO = getActualIssueServiceInstance().back(keyId, operation,
					files);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.INSTRUCT.equals(operate)) {
			issueVO = getActualIssueServiceInstance()
					.instruct(keyId, operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (isSuperviseDeal()) {
			issueVO = getActualIssueServiceInstance().supervise(operate, keyId,
					operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.CANCEL_SUPERVISE.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelSupervise(keyId,
					operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.URGENT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().urgent(keyId, operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.CANCEL_URGENT.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelUrgent(keyId,
					operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.HISTORIC.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance()
					.historic(keyId, operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else if (IssueOperate.CANCEL_HISTORIC.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelHistoric(keyId,
					operation);
			workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		} else {
			errorMessage = "未知的处理类型";
			return ERROR;
		}
		tqSolrSearchCommonOperate.commonOperate(
				IssueSolrDocumentConvert.createIssueSolrDocument(issueVO),
				TqSolrSearchOperateType.ADD_OR_UPDATE);
		return SUCCESS;
	}

	private CompletedIssue createCompletedIssue(IssueViewObject issueVO) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("completeCode", IssueState.ISSUECOMPLETE_CODE);
		param.put("sourceType", issueVO.getSourceKind().getId());
		param.put("issueId", issueVO.getIssueId());
		param.put("orgId", ThreadVariable.getOrganization().getId());
		CompletedIssue completedIssue = completedIssueService
				.buildCompletedIssueByIssueId(param);

		return completedIssue;
	}

	/***************************************************************************
	 * 事件的转入三本台账操作
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "toChangeIntoThreeRecords", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	@Transactional
	public String toChangeIntoThreeRecords() throws Exception {
		operation.setDealUserName(getCurrentLoginedUser().getName());
		operation.setMobile(getCurrentLoginedUser().getMobile());
		operation.setDealOrg(getCurrentLoginedOrg());
		operation.setContent("事件转入三本台帐");

		List<IssueAttachFile> files = createIssueAttachFile(attachFiles);
		if (!validateDealInput(operation, IssueOperate.COMPLETE, files)) {
			return ERROR;
		}
		// 事件已办结操作
		issueVO = getActualIssueServiceInstance().complete(keyId, operation,
				files);
		workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);
		issueService.updateEventStateByIssueId(issueVO.getIssueId());
		// 事件验证操作
		if (!validateDealInput(operation, IssueOperate.VERIFICATION, files)) {
			return ERROR;
		}
		issueVO = getActualIssueServiceInstance().verification(
				issueVO.getIssueStepId(), operation, files,
				IssueConstants.IS_CHANGEINTOTHREERECORDS);

		workDiaryService.addWorkDiary(issueVO.getIssueId(), operation);

		CompletedIssue completedIssue = createCompletedIssue(issueVO);
		completedIssue.setTopState(TOPSTATE);// 验证通过的事件，流转到已办结列表的时候，默认没有置顶
		completedIssueService.addCompletedIssue(completedIssue);
		completedIssueService.updateEventStateByIssueId(issueVO.getIssueId());

		ledgerConvertDubboService
				.addLedgerConvert(fillLedgerConvertProperty(issueVO));
		tqSolrSearchCommonOperate.commonOperate(
				IssueSolrDocumentConvert.createIssueSolrDocument(issueVO),
				TqSolrSearchOperateType.ADD_OR_UPDATE);
		return SUCCESS;
	}

	/** 填充事件转三本台帐 */
	private LedgerConvert fillLedgerConvertProperty(
			IssueViewObject issueViewObject) {
		LedgerConvert ledgerConvert = new LedgerConvert();
		ledgerConvert.setIssueName(issueViewObject.getSubject());
		ledgerConvert.setOccurOrg(issueViewObject.getOccurOrg());
		ledgerConvert.setCreateOrg(ThreadVariable.getOrganization());
		ledgerConvert.setCreateDate(new Date());
		ledgerConvert.setIssueId(issueViewObject.getIssueId());
		ledgerConvert.setSerialNumber(issueViewObject.getSerialNumber());
		ledgerConvert.setDescription(issueViewObject.getIssueContent());
		ledgerConvert.setOccurDate(issueViewObject.getOccurDate());
		IssueTypeDomain issueTypeDomain = issueTypeService
				.getIssueTypeDomainById(issueViewObject.getIssueType()
						.getIssueTypeDomain().getId());
		IssueType issueType = issueTypeService
				.getIssueTypeById(issueTypeDomain.getId(), issueViewObject
						.getIssueType().getId());
		ledgerConvert.setIssueTypeName(issueTypeDomain.getDomainName() + "-"
				+ issueType.getIssueTypeName());
		List<IssueRelatedPeople> issueRelatedPeoples = issueRelatedPeopleService
				.findIssueRelatedPeopleByIssueId(issueViewObject.getIssueId());
		if (issueRelatedPeoples != null && issueRelatedPeoples.size() > 0) {
			ledgerConvert.setName(issueRelatedPeoples.get(0).getName());
			ledgerConvert
					.setMobile(issueRelatedPeoples.get(0).getTelephone() != null ? issueRelatedPeoples
							.get(0).getTelephone() : issueRelatedPeoples.get(0)
							.getFixPhone());
		}
		return ledgerConvert;
	}

	/***************************************************************************
	 * 事件验证页面跳转
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "dispatchIssueEvaluate", results = { @Result(location = "/issue/issueManage/issueEvaluateDlg.jsp") }),
			@Action(value = "dispatchIssueEvaluateForMobile", results = { @Result(name = "success", type = "json", params = {
					"root", "issueEvaluate", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }) }) })
	public String dispatchIssueEvaluate() throws Exception {
		IssueEvaluate ie = issueEvaluateService
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
		List<IssueAttachFile> files = null;
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
			// 判断是否是已办结列表的删除，如果是则判断该事件是不是历史已办结
			isHistory = (IssueViewType.COMPLETED.equalsIgnoreCase(viewType) || IssueViewType.CHECKGRADE
					.equalsIgnoreCase(viewType))
					&& IssueConstants.STATUSTYPE_HISTORY
							.equals(completedIssueService
									.getCompletedByIssueId(keyId).getMoveMark());
			if (!hasPermission(keyId, null, isHistory)) {
				return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
			}
			workDiaryService.addWorkDiary(keyId,
					getIssueLogByHistory(isHistory, keyId));
			// 若为历史事件，删除对应的历史表信息
			if (isHistory) {
				issueHistoryService.deleteIssueHistoryByIssueId(keyId);
			} else {
				getActualIssueServiceInstance().deleteIssueByIssueId(keyId);
			}
			return SUCCESS;
		}
		return ERROR;
	}

	/** 获取事件日志，若为历史事件，查询对应的历史表 **/
	private IssueLogNew getIssueLogByHistory(boolean isHistory, Long issueId) {
		IssueNew newIssue = null;
		List<IssueLogNew> issueLogs = new ArrayList<IssueLogNew>();
		if (isHistory) {
			newIssue = issueHistoryService.getFullIssueHistoryById(issueId);
			issueLogs = issueHistoryService
					.loadIssueOperationLogsHistoryByIssueId(issueId);
		} else {
			newIssue = getActualIssueServiceInstance().getFullIssueByIssueId(
					issueId);
			issueLogs = issueLogService.findIssueLogsByIssueId(issueId);
		}
		IssueLogNew issueLog = issueLogs.get(issueLogs.size() - 1);
		issueLog.setContent("删除事件，服务单号为：" + newIssue.getSerialNumber());
		return issueLog;
	}

	/**
	 * 为手机端提供查询督办事件列表
	 */
	public String getOverseerIssueListForMobile() throws Exception {
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
			List<PropertyDict> sourceTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SOURCE_KIND, sourceTypeInternalId);
			if (sourceTypes != null && sourceTypes.size() > 0
					&& sourceTypes.get(0) != null) {
				sourceType = sourceTypes.get(0).getId();
			}
		}

		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsNeedDoForOverseerForMobile(seachValue,
							keyId, page, rows, sidx, sord, issueType, orgLevel,
							leaderView, functionalOrgType, viewProcess,
							sourceType);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
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
			List<PropertyDict> sourceTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SOURCE_KIND, sourceTypeInternalId);
			if (sourceTypes != null && sourceTypes.size() > 0
					&& sourceTypes.get(0) != null) {
				sourceType = sourceTypes.get(0).getId();
			}
		}
		// if (ThreadVariable.getOrganization().getOrgLevel().getInternalId() ==
		// OrganizationLevel.PROVINCE
		// && (!"1".equals(statusType) || ("1".equals(statusType) &&
		// IssueViewType.DONE
		// .equalsIgnoreCase(viewType)))
		// && functionalOrgType == null && page * rows <= 200) {
		// String key = null;
		// if ("1".equals(statusType)
		// && IssueViewType.DONE.equalsIgnoreCase(viewType)) {
		// key = viewType + "_" + ThreadVariable.getOrganization().getId()
		// + "_" + orgLevel + "_history";
		// } else {
		// key = viewType + "_" + ThreadVariable.getOrganization().getId()
		// + "_" + orgLevel;
		// }
		//
		// String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ?
		// new String[] {
		// "occurOrg", "lastOrg", "targeOrg", "currentOrg",
		// "createOrg" }
		// : new String[] { "lastOrg", "targeOrg", "currentOrg",
		// "createOrg" };
		// PageInfo issues = null;
		// if (IssueViewType.COMPLETED.equalsIgnoreCase(viewType)
		// || IssueViewType.CHECKGRADE.equalsIgnoreCase(viewType)) {
		// issues = issueLeaderViewCacheService
		// .findPageInfoByCacheKeyForCompleted(key, page, rows);
		// } else {
		// issues = issueLeaderViewCacheService.findPageInfoByCacheKey(
		// key, page, rows);
		// }
		// ControllerHelper.processAllOrgRelativeName(issues,
		// organizationDubboService, params, null);
		// gridPage = new GridPage(issues);
		// return SUCCESS;
		// }
		// 根据不同类型调用不同的service
		if (IssueViewType.NEED.equalsIgnoreCase(viewType)) {
			return findJurisdictionsNeedDo();
		} else if (IssueViewType.DONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDone();
		} else if (IssueViewType.DOING.equalsIgnoreCase(viewType)) {
			return findJurisdictionsDoing();
		} else if (IssueViewType.COMPLETED.equalsIgnoreCase(viewType)) {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				return findJurisdictionsCompleted();
			} else {
				return findCompletedIssueList();
			}
		} else if (IssueViewType.VERIFICATION.equalsIgnoreCase(viewType)) {
			return findJurisdictionsVerification();
		} else if (IssueViewType.ASSIGN.equalsIgnoreCase(viewType)) {
			return findJurisdictionsAssgin();
		} else if (IssueViewType.SUBMIT.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSubmit();
		} else if (IssueViewType.CHECKPENDING.equalsIgnoreCase(viewType)) {
			return findJurisdictionAuditDelay();
		} else if (IssueViewType.SKIP.equalsIgnoreCase(viewType)) {
			return findJurisdictionsSkipIssue();
		} else if (IssueViewType.FOLLOW.equalsIgnoreCase(viewType)) {
			return findJurisdictionsFollow();
		} else if (IssueViewType.CHECKGRADE.equalsIgnoreCase(viewType)) {
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.IS_TQSEARCH_OPEN))) {
				return findJurisdictionsGradeDelay();
			} else {
				return findGradeDelayIssueList();
			}
		} else if (IssueViewType.PUBLICLTYCASSDONE.equalsIgnoreCase(viewType)) {
			return findJurisdictionsPublicltyCassDone();
		} else if (IssueViewType.TIMEOUT.equalsIgnoreCase(viewType)) {
			return findTimeOutIssue();
		} else {
			createEmptyIssueList();
			return ERROR;
		}
	}

	@Action(value = "findJurisdictionsForProcess", results = {
			@Result(name = "success", location = "/issue/issueManage/issueProcess.jsp"),
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
			@Action(value = "findMyNeedDo", results = { @Result(name = "success", location = "/issue/issueManage/simpleIssueList.jsp") }),
			@Action(value = "findMyNeedDoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findMyNeedDo() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findMyNeedDoIssues(keyId, issueType, page, rows, sidx,
							sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findMyNeedDoIssues(keyId, issueType, page, rows, sidx,
							sord);
			for (IssueViewObject issue : issues.getResult()) {
				issue.setCanDoList(getActualIssueServiceInstance()
						.getIssueCandoForOrg(issue.getIssueStepId(),
								getCurrentLoginedOrg()));
			}
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
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
			@Action(value = "findMyDone", results = { @Result(name = "success", location = "/issue/issueManage/simpleIssueDoneList.jsp") }),
			@Action(value = "findMyDoneForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findMyDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findMyDone(keyId, issue, page, rows, sidx, sord, issueType);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
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
	@Action(value = "findMyHistoricalIssues", results = { @Result(name = "success", location = "/issue/issueManage/simpleIssueHistoricalList.jsp") })
	public String findMyHistoricalIssues() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findMyHistoricIssues(keyId, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
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
	@Action(value = "findMyPublicltyIssues", results = { @Result(name = "success", location = "/issue/issueManage/simplePublicltyCassList.jsp") })
	public String findMyPublicltyIssues() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findMyPublicltyIssues(keyId, page, rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private String seachValue;

	public String getSeachValue() {
		return seachValue;
	}

	public void setSeachValue(String seachValue) {
		this.seachValue = seachValue;
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsNeedDo(seachValue, keyId, page, rows,
							sidx, sord, issueType, orgLevel, leaderView,
							functionalOrgType, viewProcess, sourceType,
							orgCodeFindLevel, searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
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
			@Action(value = "findIssueNeedDo", results = {
					@Result(name = "super", location = "/workBench/issueNeedOfSuper.jsp"),
					@Result(name = "middle", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false" }),
					@Result(name = "lower", location = "/workBench/issueNeedOfWorkBench.jsp") }),
			@Action(value = "findIssueNeedDoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findIssueNeedDo() throws Exception {
		if (orgLevel == null) {
			orgLevel = ThreadVariable.getOrganization().getOrgLevel().getId();
		}
		if (functionalOrgType == null) {
			if (ThreadVariable.getOrganization().getFunctionalOrgType() != null
					&& ThreadVariable.getOrganization().getFunctionalOrgType()
							.getId() != null) {
				functionalOrgType = ThreadVariable.getOrganization()
						.getFunctionalOrgType().getId();
			}
		}
		if (sourceType == null && sourceTypeInternalId != null) {
			List<PropertyDict> sourceTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SOURCE_KIND, sourceTypeInternalId);
			if (sourceTypes != null && sourceTypes.size() > 0
					&& sourceTypes.get(0) != null) {
				sourceType = sourceTypes.get(0).getId();
			}
		}

		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findIssueNeedDo(seachValue, keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							viewProcess, sourceType);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return source;
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsDoing(seachValue, keyId, page, rows,
							sidx, sord, issueType, orgLevel, leaderView,
							functionalOrgType, viewProcess, orgCodeFindLevel,
							searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsAssgin(seachValue, keyId, page, rows,
							sidx, sord, issueType, orgLevel, leaderView,
							functionalOrgType, statusType, viewProcess,
							sourceType, orgCodeFindLevel, searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 
	 * 下辖事项-已办事项列表
	 * 
	 * @return
	 */
	private String findCompletedIssueList() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			// 快速检索的组织机构的code
			String searchOrgCode = "";
			if (searchOrgId != null) {
				searchOrgCode = organizationDubboService
						.getOrgInternalCodeById(searchOrgId);
			}
			PageInfo<CompletedIssue> issues = completedIssueService
					.findCompletedIssueByPage(keyId, page, rows, sidx, sord,
							orgLevel, functionalOrgType, leaderView,
							sourceType, issueType, evaluationType, viewProcess,
							orgCodeFindLevel, searchOrgId, searchOrgCode);

			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsCompleted(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							statusType, viewProcess, sourceType,
							issueGradeIsVisabled, orgCodeFindLevel,
							searchOrgId, evaluationType);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-待验证事数量
	 * 
	 * @return
	 */
	@Action(value = "getJurisdictionsVerificationCount", results = {
			@Result(type = "json", name = "success", params = { "root",
					"verificationCount", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getJurisdictionsVerificationCount() throws Exception {
		if (keyId == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
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
		// keyid = orgid
		if (ThreadVariable.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.PROVINCE
				&& functionalOrgType == null) {
			String key = IssueViewType.VERIFICATION + "_"
					+ ThreadVariable.getOrganization().getId() + "_" + orgLevel
					+ "_count";
			setVerificationCount(issueLeaderViewCacheService
					.getCountByCacheKey(key));
		} else {
			setVerificationCount(issueService
					.getJurisdictionsVerificationCountForViewTab(orgLevel,
							keyId, functionalOrgType));
		}

		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-待评分事数量
	 * 
	 * @return
	 */
	@Action(value = "getJurisdictionsGradeDelayCount", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gradeDelayCount", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getJurisdictionsGradeDelayCount() throws Exception {
		if (keyId == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
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

		if (ThreadVariable.getOrganization().getOrgLevel().getInternalId() == OrganizationLevel.PROVINCE
				&& functionalOrgType == null) {
			String key = IssueViewType.CHECKGRADE + "_"
					+ ThreadVariable.getOrganization().getId() + "_" + orgLevel
					+ "_count";
			setGradeDelayCount(issueLeaderViewCacheService
					.getCountByCacheKey(key));
		} else {
			setGradeDelayCount(issueService
					.getJurisdictionsGradeDelayCountForViewTab(orgLevel, keyId,
							functionalOrgType));
		}

		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-待验证事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsVerification", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsVerificationForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsVerification() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsVerification(keyId, page, rows, sidx,
							sord, issueType, orgLevel, leaderView,
							functionalOrgType, statusType, viewProcess,
							sourceType, orgCodeFindLevel, searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsSkipIssue(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							statusType, viewProcess, sourceType,
							orgCodeFindLevel, searchOrgId);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService,
					new String[] { "lastOrg", "targeOrg", "currentOrg",
							"occurOrg", "createOrg" }, null);

			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-待跟进事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsFollow", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsFollowForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsFollow() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsFollow(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							viewProcess, sourceType, orgCodeFindLevel,
							searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsDone(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							statusType, viewProcess, sourceType,
							orgCodeFindLevel, searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-超时事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findTimeOutIssue", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findTimeOutIssueForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findTimeOutIssue() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findTimeOutIssue(keyId, page, rows, sidx, sord, issueType,
							superviseType, orgLevel, leaderView,
							functionalOrgType, viewProcess, sourceType,
							orgCodeFindLevel, searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项-待评分案事项列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsGradeDelay", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsGradeDelayForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsGradeDelay() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsGradeDelay(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							statusType, viewProcess, sourceType,
							orgCodeFindLevel, searchOrgId, evaluationType);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 
	 * 下辖事项-待评分案事项列表(新方法，直接从已办结表中获取)
	 * 
	 * @return
	 */
	private String findGradeDelayIssueList() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			// 快速检索的组织机构的code
			String searchOrgCode = "";
			if (searchOrgId != null) {
				searchOrgCode = organizationDubboService
						.getOrgInternalCodeById(searchOrgId);
			}
			PageInfo<CompletedIssue> issues = completedIssueService
					.findGradeDelayIssueByPage(keyId, page, rows, sidx, sord,
							orgLevel, functionalOrgType, leaderView,
							sourceType, issueType, evaluationType, viewProcess,
							orgCodeFindLevel, searchOrgId, searchOrgCode);

			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg", "createOrg" },
					null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 下辖事项 宣传案例列表
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "findJurisdictionsPublicltyCassDone", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findJurisdictionsPublicltyCassDoneForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findJurisdictionsPublicltyCassDone() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsPublicltyCassDone(keyId, page, rows,
							sidx, sord, issueType, orgLevel, leaderView,
							functionalOrgType, statusType, viewProcess,
							sourceType, issueGradeIsVisabled, orgCodeFindLevel,
							searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
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
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionsSubmit(keyId, page, rows, sidx, sord,
							issueType, orgLevel, leaderView, functionalOrgType,
							statusType, viewProcess, sourceType,
							orgCodeFindLevel, searchOrgId);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	private String findJurisdictionAuditDelay() {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findJurisdictionAuditDelay(keyId, orgLevel,
							functionalOrgType, page, rows, sidx, sord,
							orgCodeFindLevel, searchOrgId, issueType);
			String[] params = !IssueViewType.VIEWPROCESS.equals(viewProcess) ? new String[] {
					"occurOrg", "lastOrg", "targeOrg", "currentOrg",
					"createOrg" }
					: new String[] { "lastOrg", "targeOrg", "currentOrg",
							"createOrg" };
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, params, null);
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
					.getIssueTypeDoaminByDomainName(IssueTypes
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
			@Action(value = "findMyCompleted", results = { @Result(name = "success", location = "/issue/issueManage/simpleIssueMyCompletedList.jsp") }),
			@Action(value = "findMyCompletedForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String findMyComplete() throws Exception {
		if (legalKeyIdParam()) {
			resolveIssueType();
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findMyCompleted(keyId, page, rows, sidx, sord, issueType);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
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
			@Result(name = "default_view", location = "/issue/issueManage/issueAccordingContext.jsp"),
			@Result(name = "empty_view", location = "/issue/issueManage/emptyIssueAccordingContext.jsp"),
			@Result(name = "print", location = "/issue/issueManage/printIssueDlg.jsp"),
			@Result(name = "printWord", location = "/issue/issueManage/printWordIssueDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/houseInfo/actualHouse/searchActualHouseDlg.jsp") })
	public String viewIssueDetail() throws Exception {
		if (legalKeyIdParam()) {
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			user = permissionService
					.getFullUserByUerName(issue.getCreateUser());
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
		issueLogs = ControllerHelper.processAllOrgName(
				issueLogService.findIssueLogsByIssueId(id),
				organizationDubboService,
				new String[] { "dealOrg", "targeOrg" });
		issueLogs = ControllerHelper
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
			IssueNew issueNew = getActualIssueServiceInstance()
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
		issueDealLogs = ControllerHelper.processAllOrgName(issueDealLogs,
				organizationDubboService, new String[] { "dealOrg" });
		jsonMap.put("issueLogs", issueDealLogs);
		jsonMap.put("issueAttachFiles", issueAttachFiles);
		mobileOperate(canDoList);
	}

	private void mobileOperate(List<IssueOperate> canDoList) {
		if (null != canDoList && canDoList.size() > 0) {
			for (IssueOperate operate : canDoList) {
				jsonMap.put("dealCode", operate.getCode());
				if (IssueOperate.BACK.getCode() == operate.getCode()) {
					jsonMap.put("method", "doHandle");
					jsonMap.put("action", "办理");
					break;
				} else if (IssueOperate.READ.getCode() == operate.getCode()) {
					jsonMap.put("阅读", "阅读");
					break;
				} else if (IssueOperate.CONCEPT.getCode() == operate.getCode()) {
					jsonMap.put("method", "doAccept");
					jsonMap.put("action", "受理");
					break;
				}
			}
		}
	}

	@Actions({ @Action(value = "viewSubDetailByIssueId", results = { @Result(name = "default_view", location = "/issue/issueManage/issueAccordingContext.jsp") }) })
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
		boolean isHistory = IssueConstants.STATUSTYPE_HISTORY
				.equals(statusType) ? Boolean.TRUE : Boolean.FALSE;
		if (!hasPermission(issue.getId(), null, isHistory)) {
			return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
		}
		if (isHistory) {
			data = issueHistoryService.getIssueHistoryMap(issue.getId());
		} else {
			data = getActualIssueServiceInstance().getIssueMap(issue.getId());

		}
		return SUCCESS;
	}

	/**
	 * 在事件处理记录的图表视图中查看部门的事件处理记录
	 * 
	 * @return
	 */
	@Action(value = "viewIssueDealLog", results = { @Result(name = "success", location = "/issue/issueManage/viewIssueDealLogDlg.jsp") })
	public String viewIssueDealLog() throws Exception {
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			issueDealLogs = issueHistoryService
					.getIssueDealLogHistory(issueMap);
		} else {
			issueDealLogs = getActualIssueServiceInstance().getIssueDealLog(
					issueMap);
		}
		if (issueDealLogs != null && issueDealLogs.size() > 0) {
			issue = new IssueNew();
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
			IssueAttachFile file = getActualIssueServiceInstance()
					.getIssueAttachFileById(keyId);
			if (file != null) {
				try {
					inputStream = new java.io.FileInputStream(createStoreFile(
							file.getFileActualUrl(), file.getFileName()));
				} catch (Exception e) {
					this.errorMessage = e.getMessage();
					return ERROR;
				}
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
								IssueOperate.parse(dealCode),
								parseStringToLongArray(exceptIds), page, rows));
			} else if (report12345) {
				Organization organization = ThreadVariable.getOrganization();
				String userDepartmentNo = organization.getDepartmentNo();
				String departmentNo = userDepartmentNo.substring(0, 6);
				if (organization.getOrgLevel().getInternalId() >= OrganizationLevel.DISTRICT) {// 区县以上，显示市级别12345
					departmentNo = userDepartmentNo.substring(0, 4);
				}
				List locationIds = Arrays
						.asList(parseStringToLongArray(exceptIds));
				List<AutoCompleteData> resultTemp = new ArrayList<AutoCompleteData>();
				// 读取JSONArray，用下标索引获取
				List<Organization> cmsLocationList = IssueToCMSUtil
						.getLocationOrgNameByDepartmentNo(URL.encode(tag),
								departmentNo);
				for (int i = 0; i < cmsLocationList.size(); i++) {
					AutoCompleteData data = new AutoCompleteData();
					Organization cmsLocation = cmsLocationList.get(i);
					data.setValue(cmsLocation.getId() + "");
					data.setLabel(cmsLocation.getOrgName());
					if (!locationIds.contains(Long.parseLong(data.getValue()))) {
						resultTemp.add(data);
					}
				}
				gridPage = new GridPage(resultTemp);
			} else {
				gridPage = new GridPage(getActualIssueServiceInstance()
						.findFunctionTargetsByName(keyId, tag,
								IssueOperate.parse(dealCode),
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
								IssueOperate.parse(dealCode), null, 1, 10));
			} else if (report12345) {
				Organization organization = ThreadVariable.getOrganization();
				String userDepartmentNo = organization.getDepartmentNo();
				String departmentNo = userDepartmentNo.substring(0, 6);
				if (organization.getOrgLevel().getInternalId() >= OrganizationLevel.DISTRICT) {// 区县以上，显示市级别12345
					departmentNo = userDepartmentNo.substring(0, 4);
				}
				List<AutoCompleteData> resultTemp = new ArrayList<AutoCompleteData>();
				// 读取JSONArray，用下标索引获取
				List<Organization> cmsLocationList = IssueToCMSUtil
						.getLocationOrgNameByDepartmentNo(URL.encode(tag),
								departmentNo);
				for (int i = 0; i < cmsLocationList.size(); i++) {
					AutoCompleteData data = new AutoCompleteData();
					Organization cmsLocation = cmsLocationList.get(i);
					data.setValue(cmsLocation.getId() + "");
					data.setLabel(cmsLocation.getOrgName());
					resultTemp.add(data);
				}
				gridPage = new GridPage(resultTemp);
			} else {
				gridPage = new GridPage(getActualIssueServiceInstance()
						.findFunctionTargetsByName(keyId, "",
								IssueOperate.parse(dealCode), null, 1, 10));
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
							IssueOperate.parse(dealCode), adminTarget,
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
							IssueOperate.parse(dealCode), adminTarget,
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
			canDoList = new ArrayList<IssueOperate>();
		}
	}

	private void loadCandoOperationsOfDeal(Long stepId, Boolean isSkip) {
		if (!DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			canDoList = getActualIssueServiceInstance().getIssueCandoForOrg(
					stepId, getCurrentLoginedOrg());
			if (!isSkip) {
				if (canDoList.contains(IssueOperate.SUBMIT)) {
					canDoList.remove(IssueOperate.SUBMIT);
					canDoList.add(IssueOperate.SUBMIT);
				}
				if (canDoList.contains(IssueOperate.COMPLETE)) {
					canDoList.remove(IssueOperate.COMPLETE);
					canDoList.add(IssueOperate.COMPLETE);
				}
			}
		} else {
			canDoList = new ArrayList<IssueOperate>();
		}
	}

	/***************************************************************************
	 * 加载历史事件的处理记录
	 * 
	 * @param selectIssue
	 */
	private void loadIssueHistoryOperationLogs(IssueNew selectIssue) {

		issueDealLogs = issueHistoryService
				.loadIssueOperationLogsHistoryByIssueId(selectIssue.getId());

		if (!selectIssue.getStatus().equals(IssueState.VERIFICATION)) {

			for (IssueLogNew log : issueDealLogs) {

				if (log.getDealType() != null && log.getTargeOrg() != null) {

					IssueOperate op = IssueOperate.parse(log.getDealType()
							.intValue());
					// 找出最后一次的交办的操作记录
					if (IssueOperate.ASSIGN.equals(op)
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
	 * 加载事件的处理记录
	 * 
	 * @param selectIssue
	 */
	private void loadIssueOperationLogs(IssueNew selectIssue) {

		issueDealLogs = getActualIssueServiceInstance()
				.loadIssueOperationLogsByIssueId(selectIssue.getId());
		if (issueDealLogs == null || (issueDealLogs.size() == 0)) {
			issueDealLogs = issueHistoryService
					.loadIssueOperationLogsHistoryByIssueId(selectIssue.getId());
		}
		if (!selectIssue.getStatus().equals(IssueState.VERIFICATION)) {

			for (IssueLogNew log : issueDealLogs) {

				if (log.getDealType() != null && log.getTargeOrg() != null) {

					IssueOperate op = IssueOperate.parse(log.getDealType()
							.intValue());
					// 找出最后一次的交办的操作记录
					if (IssueOperate.ASSIGN.equals(op)
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
	private void loadAttachFiles(IssueNew selectIssue) {
		issueAttachFiles = getActualIssueServiceInstance()
				.loadIssueAttachFilesByIssueId(selectIssue.getId());
		issueEvaluateAttachFiles = getActualIssueServiceInstance()
				.getIssueEvaluateAttachFileAttachFileByIssueId(
						selectIssue.getId());
		for (int index = issueAttachFiles.size(); index > 0; index--) {
			IssueAttachFile file = issueAttachFiles.get(index - 1);
			if (isLogAttachFile(file)) {
				List<IssueAttachFile> logFiles = lookupLogFilesFromAllLogFile(file
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
	private void loadEvaluate(IssueNew selectIssue) {
		if (null == selectIssue || null == selectIssue.getId()) {
			issueEvaluate = new IssueEvaluate();
		} else {
			issueEvaluate = issueEvaluateService
					.getIssueEvaluateByIssueId(selectIssue.getId());
		}

	}

	private List<IssueAttachFile> lookupLogFilesFromAllLogFile(Long id) {
		if (issueLogAttachFiles.containsKey(id)) {
			return issueLogAttachFiles.get(id);
		} else {
			List<IssueAttachFile> files = new ArrayList<IssueAttachFile>();
			issueLogAttachFiles.put(id, files);
			return files;
		}
	}

	private boolean isLogAttachFile(IssueAttachFile file) {
		return file.getIssueLog() != null && file.getIssueLog().getId() != null;
	}

	private void loadFullIssueTypes(IssueNew selectIssue) {
		selectIssue.setIssueType(issueTypeService.getIssueTypeById(selectIssue
				.getIssueType().getIssueTypeDomain().getId(), selectIssue
				.getIssueType().getId()));
		selectIssue.getIssueType().setIssueTypeDomain(
				issueTypeService.getIssueTypeDomainById(selectIssue
						.getIssueType().getIssueTypeDomain().getId()));

		// if (issueHasTypeDomainName == null) {
		// issueHasTypeDomainName = new ArrayList<String>();
		// } else if (!issueHasTypeDomainName.contains(type.getIssueTypeDomain()
		// .getDomainName())) {
		// issueHasTypeDomainName.add(type.getIssueTypeDomain()
		// .getDomainName());
		// }
	}

	private IssueService getActualIssueServiceInstance() {
		return issueServiceFactory.getIssueServiceBySeries(getSeries());
	}

	private boolean legalKeyIdParam() {
		return null != keyId;
	}

	private void createEmptyIssueList() {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<IssueNew>());
		gridPage = new GridPage(pageInfo);
	}

	private boolean validateInput(IssueNew issue, List<IssueAttachFile> files) {
		ValidateResult result = getActualIssueManageStrategy().validate(issue,
				files);
		if (result.hasError()) {
			errorMessage = result.getErrorMessages();
		}
		return !result.hasError();
	}

	private boolean validateDealInput(IssueLogNew log, IssueOperate operate,
			List<IssueAttachFile> files) {
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
			List<IssueOperate> canDoList) throws Exception {
		page = 1;
		rows = 1000;
		Map<String, List<Organization>> targetOrgs = new LinkedHashMap<String, List<Organization>>();
		if (null != canDoList && canDoList.size() > 0) {
			for (IssueOperate operate : canDoList) {
				dealCode = operate.getCode();
				if (IssueOperate.ASSIGN.getCode() == operate.getCode()) {
					adminTarget = true;
					searchUniqueTrgetOrg();
					targetOrgs.put("assignAdmin",
							autoCompleteDataTransformOrganization(gridPage)); // 交办行政
					adminTarget = false;
					searchUniqueTrgetOrg();
					targetOrgs.put("assignFun",
							autoCompleteDataTransformOrganization(gridPage)); // 交办职能
					searchUniqueTrgetOrg();
				} else if (IssueOperate.SUBMIT.getCode() == operate.getCode()) {
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

	private void hiddenSomeOperate(IssueStep step) {
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
	}

	private void fillOperationLog(IssueLogNew log, IssueStep step) {
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

	private IssueManageStrategy getActualIssueManageStrategy() {
		if (strategy == null) {
			strategy = factory.getIssueManageStrategyByModule(moduleName);
		}
		return strategy;
	}

	private void fillPropertyDefaultValue(IssueNew issue) {
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
			List<IssueOperate> issueActions = new ArrayList<IssueOperate>();
			List<String> userPermissionAll = permissionService
					.findUserAllPermissionEnameByUserId(ThreadVariable
							.getUser().getId());
			List<String> permissions = isthesePermissions();
			userPermissionAll.retainAll(permissions);
			for (String ename : userPermissionAll) {
				issueActions.add(IssueOperate.pressionOperate.get(ename));
			}
			jsonMap.put("issueActions", issueActions);
		}
		return SUCCESS;
	}

	private boolean hasPermission(Long issueId, Long stepId, boolean isHistory) {
		// boolean hasPermission = false;
		// String userOrgCode = ThreadVariable.getOrganization()
		// .getOrgInternalCode();
		// List<IssueStep> issueSteps = getIssueSteps(isHistory, issueId,
		// stepId);
		// if (issueSteps == null) {
		// return true;
		// }
		// for (IssueStep IssueStep : issueSteps) {
		// Organization issueStepOrg = IssueStep.getTarget();
		// String issueStepOrgCode;
		// if (issueStepOrg != null) {
		// issueStepOrgCode = issueStepOrg.getOrgInternalCode();
		// } else {
		// continue;
		// }
		// if (StringUtil.isStringAvaliable(issueStepOrgCode)
		// && issueStepOrgCode.indexOf(userOrgCode) == 0) {
		// hasPermission = true;
		// break;
		// }
		// }
		// return hasPermission;

		// 事件的权限判断去除(迁移合并组织机构code变更)
		return true;
	}

	private List<IssueStep> getIssueSteps(boolean isHistory, Long issueId,
			Long stepId) {
		if (isHistory) {
			// 历史查询
			if (issueId != null) {
				return issueHistoryService
						.searchIssueStepsHistoryByIssueId(issueId);
			} else {
				return issueHistoryService
						.searchAllIssueStepsHistoryByStepId(stepId);
			}
		} else {
			if (issueId != null) {
				return searchIssueStepService
						.searchIssueStepsByIssueId(issueId);
			} else {
				return searchIssueStepService
						.searchAllIssueStepsByStepId(stepId);
			}
		}
	}

	private boolean isAdminPermissions() {
		List<IssueOperate> issueActions = new ArrayList<IssueOperate>();
		if (ThreadVariable.getUser().isAdmin() == true) {
			for (String ename : isthesePermissions()) {
				issueActions.add(IssueOperate.pressionOperate.get(ename));
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
				.findIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE);
		resolveTheContradictions = issueTypeService
				.findIssueTypesByParentName(IssueTypes.RESOLVETHECONTRADICTIONS);

		securityPrecautions = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYPRECAUTIONS);
		specialPopulations = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SPECIALPOPULATIONS);
		socialConditions = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SOCIALCONDITIONS);
		policiesAndLaws = issueTypeService
				.findIssueTypesByParentName(IssueTypes.POLICIESANDLAWS);
		emergencies = issueTypeService
				.findIssueTypesByParentName(IssueTypes.EMERGENCIES);
		otherType = issueTypeService
				.findIssueTypesByParentName(IssueTypes.OTHERMANAGE);
	}

	private void createEmptyOperationLogByStepId(Long stepId) {
		IssueStep step = getActualIssueServiceInstance().getIssueStepById(
				stepId);
		createEmptyOperationLogByStep(step);
	}

	private void createEmptyOperationLogByStep(IssueStep step) {
		operation = new IssueLogNew();
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
			@Action(value = "issueGrade", results = { @Result(name = "success", location = "/issue/issueManage/issueGradeDlg.jsp") }),
			@Action(value = "issueGradeOfBench", results = { @Result(name = "success", location = "/workBench/issueManage/issueGradeDlg.jsp") }),
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
				for (IssueLogNew issueLogNew : issueDealLogs) {
					idList.add(issueLogNew.getId());
				}
				issue.setRegradedPointsList(regradedPointService
						.queryRegradedPointsForListByIds(idList));
			}
		}
		IssueEvaluate ie = issueEvaluateService
				.getIssueEvaluateByIssueIdForGrade(keyId);
		if (ie != null) {
			issueEvaluate = ie;
			return SUCCESS;
		} else {
			return SUCCESS;
		}
	}

	@Action(value = "findIssueEvaluateForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "issueEvaluate", "ignoreHierarchy", "false" }) })
	public String findIssueEvaluateForMobile() throws Exception {
		if (keyId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		IssueEvaluate ie = issueEvaluateService
				.getIssueEvaluateByIssueIdForGrade(keyId);
		if (ie != null) {
			issueEvaluate = ie;
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
			// 判断是否是已办结列表的删除，如果是则判断该事件是不是历史已办结
			isHistory = (IssueViewType.COMPLETED.equalsIgnoreCase(viewType) || IssueViewType.CHECKGRADE
					.equalsIgnoreCase(viewType))
					&& IssueConstants.STATUSTYPE_HISTORY
							.equals(completedIssueService
									.getCompletedByIssueId(keyId).getMoveMark());
			gridPage = regradedPointService
					.queryIssueGradeHistoryForPageResultByIssueId(isHistory,
							keyId, page, rows, sidx, sord);
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
				|| issue.getId().longValue() == 0L
				|| (issueAccord == null && issueEvaluate == null)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		issueService.saveIssueGrade(issue.getId(), issueAccord, issueEvaluate);
		return SUCCESS;
	}

	private String doIssueAccord() {
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
			try {
				for (int i = 0; i < len; i++) {
					score[i] = Double.parseDouble(scores[i]);
					type[i] = Integer.parseInt(types[i]);
					orgId[i] = Long.parseLong(orgIds[i]);
					logId[i] = Long.parseLong(logIds[i]);
				}
			} catch (Exception e) {
				errorMessage = "参数错误，打分失败";
				logger.error(errorMessage, e.getMessage());
				return ERROR;
			}
			issueAccord = new IssueAccord();
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
	public boolean findIssueIfExistForMobile(IssueNew issueNew)
			throws Exception {
		boolean flag = false;
		Integer countVal = issueService.findIssueExistForMobile(issueNew);
		if (countVal != null && countVal > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @Title: findIssueStepByIssueId
	 * @Description: TODO为手机端新增方法
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-6-13 下午06:40:52
	 */
	public String findIssueStepByIssueId() throws Exception {
		if (issueId == null) {
			errorMessage = "事件id为空";// 手机端调用方法，所以直接把此异常抛出，可明确知道问题在哪儿
			return ERROR;
		}
		issueStep = searchIssueStepService.findIssueStepByIssueId(issueId);
		return SUCCESS;
	}

	@Action(value = "issueCanDeal", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String issueCanDeal() throws Exception {
		if (keyId == null) {
			errorMessage = "该事件不可办理";
			return ERROR;
		}
		IssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(keyId);
		// 如果是待验证事件，跳过部门验证
		if (!getCurrentLoginedOrg().equals(step.getTarget())
				&& !(IssueState.ISSUEVERIFICATION_CODE == step.getStateCode())) {
			// 不是本部门的事件
			errorMessage = "你不能处理不属于你部门的事件";
			return ERROR;
		}
		return SUCCESS;
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

	public List<IssueTypeViewDefine> getIssueTypeNames() {
		return issueTypeNames;
	}

	public void setIssueTypeNames(List<IssueTypeViewDefine> issueTypeNames) {
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

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public IssueViewObject getIssueVO() {
		return issueVO;
	}

	public void setIssueVO(IssueViewObject issueVO) {
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

	public List<IssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(List<IssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public List<IssueLogNew> getIssueDealLogs() {
		return issueDealLogs;
	}

	public void setIssueDealLogs(List<IssueLogNew> issueDealLogs) {
		this.issueDealLogs = issueDealLogs;
	}

	public Map<Long, List<IssueAttachFile>> getIssueLogAttachFiles() {
		return issueLogAttachFiles;
	}

	public void setIssueLogAttachFiles(
			Map<Long, List<IssueAttachFile>> issueLogAttachFiles) {
		this.issueLogAttachFiles = issueLogAttachFiles;
	}

	public List<IssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<IssueOperate> canDoList) {
		this.canDoList = canDoList;
	}

	public IssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(IssueLogNew operation) {
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

	public void setIssueEvaluate(IssueEvaluate issueEvaluate) {
		this.issueEvaluate = issueEvaluate;
	}

	public IssueEvaluate getIssueEvaluate() {
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

	public void setData(List<IssueMap> data) {
		this.data = data;
	}

	public List<IssueMap> getData() {
		return data;
	}

	public TopIssue getTopIssue() {
		return topIssue;
	}

	public void setTopIssue(TopIssue topIssue) {
		this.topIssue = topIssue;
	}

	public IssueMap getIssueMap() {
		return issueMap;
	}

	public void setIssueMap(IssueMap issueMap) {
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

	public List<IssueLogNew> getIssueLogs() {
		return issueLogs;
	}

	public void setIssueLogs(List<IssueLogNew> issueLogs) {
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

	public IssueAccord getIssueAccord() {
		return issueAccord;
	}

	public void setIssueAccord(IssueAccord issueAccord) {
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

	public IssueSkiprule getIssueSkiprule() {
		return issueSkiprule;
	}

	public void setIssueSkiprule(IssueSkiprule issueSkiprule) {
		this.issueSkiprule = issueSkiprule;
	}

	public void setIssueLogForEdit(IssueLogNew issueLogForEdit) {
		this.issueLogForEdit = issueLogForEdit;
	}

	public IssueLogNew getIssueLogForEdit() {
		return issueLogForEdit;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public IssueAccords getIssueAccords() {
		return issueAccords;
	}

	public void setIssueAccords(IssueAccords issueAccords) {
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
			List<IssueAttachFile> issueEvaluateAttachFiles) {
		this.issueEvaluateAttachFiles = issueEvaluateAttachFiles;
	}

	public List<IssueAttachFile> getIssueEvaluateAttachFiles() {
		return issueEvaluateAttachFiles;
	}

	public List<IssueAttachFile> getDocfilesByIdAndModuleKey(Long issueId,
			String moduleType, String fileType) {
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

	public IssueStep getIssueStep() {
		return issueStep;
	}

	public void setIssueStep(IssueStep issueStep) {
		this.issueStep = issueStep;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public boolean isOperationOfTell() {
		return isOperationOfTell;
	}

	public void setOperationOfTell(boolean isOperationOfTell) {
		this.isOperationOfTell = isOperationOfTell;
	}

	public int getVerificationCount() {
		return verificationCount;
	}

	public void setVerificationCount(int verificationCount) {
		this.verificationCount = verificationCount;
	}

	public String getIssueGradeIsVisabled() {
		return issueGradeIsVisabled;
	}

	public void setIssueGradeIsVisabled(String issueGradeIsVisabled) {
		this.issueGradeIsVisabled = issueGradeIsVisabled;
	}

	public int getGradeDelayCount() {
		return gradeDelayCount;
	}

	public void setGradeDelayCount(int gradeDelayCount) {
		this.gradeDelayCount = gradeDelayCount;
	}

	public String getTqmobile() {
		return tqmobile;
	}

	public void setTqmobile(String tqmobile) {
		this.tqmobile = tqmobile;
	}

	public void setUrgentLevels(List<PropertyDict> urgentLevels) {
		this.urgentLevels = urgentLevels;
	}

	public List<PropertyDict> getUrgentLevels() {
		return urgentLevels;
	}

	public Boolean getIsSkip() {
		return isSkip;
	}

	public void setIsSkip(Boolean isSkip) {
		this.isSkip = isSkip;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isReport12345() {
		return report12345;
	}

	public void setReport12345(boolean report12345) {
		this.report12345 = report12345;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public boolean isHistory() {
		return isHistory;
	}

	public void setHistory(boolean isHistory) {
		this.isHistory = isHistory;
	}

	public Long getSearchOrgId() {
		return searchOrgId;
	}

	public void setSearchOrgId(Long searchOrgId) {
		this.searchOrgId = searchOrgId;
	}

	public int getOrgCodeFindLevel() {
		return orgCodeFindLevel;
	}

	public void setOrgCodeFindLevel(int orgCodeFindLevel) {
		this.orgCodeFindLevel = orgCodeFindLevel;
	}

	public void setSuperviseType(Integer superviseType) {
		this.superviseType = superviseType;
	}

	public PreciseInbox getPreciseInbox() {
		return preciseInbox;
	}

	public void setPreciseInbox(PreciseInbox preciseInbox) {
		this.preciseInbox = preciseInbox;
	}

	public List<PreciseInbox> getPreciseInboxList() {
		return preciseInboxList;
	}

	public void setPreciseInboxList(List<PreciseInbox> preciseInboxList) {
		this.preciseInboxList = preciseInboxList;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
