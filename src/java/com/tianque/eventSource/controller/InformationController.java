package com.tianque.eventSource.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchIssueDao;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.IssueSourceType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchCommandCenterIssueVoNew;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.domain.EventSourceVo;
import com.tianque.eventSource.service.EventSourceService;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.controller.strategy.IssueManageStrategy;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.factory.IssueManageStrategyFactory;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueTypeViewDefine;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.IssueTypeService;
import com.tianque.state.IssueQueryState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Namespace("/commandCenterManage")
@Transactional
@Scope("request")
@Controller("informationController")
public class InformationController extends BaseAction {
	@Autowired
	private EventSourceService eventSourceService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private IssueManageStrategyFactory factory;
	@Autowired
	private SearchIssueDao searchIssueDao;

	private final static String SYS_SERIES = IssueServiceFactory.DEFAULT_SERIES;
	private Integer internalId;
	private EventSource eventSource;
	private EventSourceVo eventSourceVo;
	private String eventSourceSelectIds;
	private String serialNumber;
	// 添加事件
	private IssueManageStrategy strategy;
	private Long keyId;
	private long stepId;
	private String moduleName;
	private IssueNew issue;
	private List<IssueTypeViewDefine> issueTypeNames;
	private Map<String, List<IssueType>> issueTypes;
	private Long dealOrgId;
	private IssueViewObject issueVO;
	private SearchCommandCenterIssueVoNew searchIssueVo;
	private String selectedTypes;
	private String involvedPlace;
	private String involvedPerson;
	private String[] attachFiles;
	private Long issueType;
	private IssueLogNew operation;
	private List<IssueOperate> canDoList;
	private int dealCode;
	private String tellOrgIds;
	private List<Object> josnlist;
	private List<IssueType> contradiction;
	private List<IssueType> specialisation;
	private List<IssueType> peopleliveService;
	private List<IssueType> otherType;
	private String selContradictionId;
	private String selSpecialisationId;
	private String selPeopleliveServiceId;
	private String selOtherTypeId;
	private Long orgId;
	private List<String> issueHasTypeDomainName;
	private List<IssueAttachFile> issueAttachFiles = new ArrayList<IssueAttachFile>();
	private Map<Long, List<IssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<IssueAttachFile>>();
	private int stateKind;
	/** 小时 */
	private String hours;
	/** 分钟 */
	private String minute;

	/***
	 * 社情民意信息列表
	 * 
	 * @return
	 */
	@Action(value = "informationList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findInformation() throws Exception {

		PageInfo<EventSourceVo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(eventSourceService.findInformation(
						eventSourceVo, page, rows), organizationDubboService,
						new String[] { "organization" }, null);

		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "search", location = "/commandCenter/searchInformation.jsp"),
			@Result(name = "view", location = "/commandCenter/informationView.jsp"),
			@Result(name = "event", location = "/commandCenter/informationForEvent.jsp"),
			@Result(name = "addIssue", location = "/commandCenter/maintainIssueDlg.jsp"),
			@Result(name = "editIssue", location = "/commandCenter/updateCommandCenterIssueDlg.jsp"),
			@Result(name = "searchIssue", location = "/commandCenter/searchIssueDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }),
			@Result(name = "viewIssue", location = "/commandCenter/viewIssueDlg.jsp") })
	public String dispatchOperate() throws Exception {
		if ("search".equals(mode)) {
			return "search";
		}
		if ("highsearch".equals(mode)) {
			prepareExistedIssueTypes();
			return "searchIssue";
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			getEventSourceInfo();
			return "view";
		}
		if ("event".equals(mode)) {
			return forwardToAddIssue();
		}
		if ("edit".equals(mode)) {
			return forwardToEditIssue();
		}
		if ("searchIssue".equals(mode)) {
			return "searchIssue";
		}
		if ("eventview".equals(mode)) {
			return forwardToView();
		}
		return SUCCESS;
	}

	/**
	 * 处理传过来的时间
	 */
	private void dealTime() throws Exception {
		String time = issue.getOccurDateString().substring(0, 10);
		if (!hours.equals("")) {
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
	}

	@PermissionFilter(ename = "addIssue")
	@Action(value = "addIssue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"josnlist", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" })

	})
	public String addIssue() throws Exception {
		if ("tranevent".equals(mode)) {
			reFillIssueProperty();
			List<IssueAttachFile> files = null;
			try {
				files = createIssueAttachFile(attachFiles);
			} catch (Exception e) {
			}
			if (validateInput(issue, files)) {
				dealTime();
				issueVO = getActualIssueServiceInstance().addIssue(issue,
						files, null, null, null, null);
				eventSourceService.updateInformationStateById(
						this.eventSource.getId(),
						IssueSourceType.TRANS_EVENT_NOTDONE,
						issueVO.getSerialNumber());
				this.josnlist = new ArrayList<Object>();
				this.josnlist.add(mode);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else if ("traneventanddone".equals(mode)) {
			reFillIssueProperty();
			List<IssueAttachFile> files = null;
			try {
				files = createIssueAttachFile(attachFiles);
			} catch (Exception e) {
			}
			if (validateInput(issue, files)) {
				dealTime();
				issueVO = getActualIssueServiceInstance().addIssue(issue,
						files, null, null, null, null);
				eventSourceService.updateInformationStateById(
						this.eventSource.getId(),
						IssueSourceType.TRANS_EVENT_NOTDONE,
						issueVO.getSerialNumber());
				this.josnlist = new ArrayList<Object>();
				this.josnlist.add(mode);
				this.josnlist.add(issueVO.getIssueStepId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	@PermissionFilter(ename = "updateIssue")
	@Action(value = "updateIssue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarch", "false" }) })
	public String updateIssue() throws Exception {
		reFillIssueProperty();
		List<IssueAttachFile> files = null;
		try {
			files = createIssueAttachFile(attachFiles);
		} catch (Exception e) {
		}
		if (validateInput(issue, files)) {
			dealTime();
			issueVO = getActualIssueServiceInstance().updateIssue(issue, files,
					stepId, null, null, null, null);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/***
	 * 社情民意中心中的待办诉求列表
	 * 
	 * @return
	 */
	@Action(value = "findMyNeedDo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMyNeedDo() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findcommandCenterMyNeedDoIssues(keyId, issueType, page,
							rows, sidx, sord);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	/***
	 * 待办诉求高级查询
	 * 
	 * @return
	 */
	@Action(value = "searchIssue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchIssue() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
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
		if (eventSourceList == null || eventSourceList.isEmpty()) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(searchIssueVo.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		preparePageData();
		transToIssueType();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < eventSourceList.size(); i++) {
			EventSource eventSource = eventSourceList.get(i);
			if (StringUtil.isStringAvaliable(eventSource.getSerialNumber())) {
				if (i < eventSourceList.size() - 1) {
					sb.append(eventSource.getSerialNumber()).append(",");
				} else {
					sb.append(eventSource.getSerialNumber());
				}
			}
		}
		searchIssueVo.setCommandCenterNum(sb.toString());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchCommandCenterIssuesNew(searchIssueVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		return SUCCESS;
	}

	@Action(value = "findMyDone", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMyDone() throws Exception {
		if (legalKeyIdParam()) {
			PageInfo<IssueViewObject> issues = getActualIssueServiceInstance()
					.findcommandCenterMyDone(keyId, issue, page, rows, sidx,
							sord, stateKind);
			issues = ControllerHelper.processAllOrgRelativeName(issues,
					organizationDubboService, new String[] { "occurOrg",
							"lastOrg", "targeOrg", "currentOrg" }, null);
			gridPage = new GridPage(issues);
		} else {
			createEmptyIssueList();
		}
		return SUCCESS;
	}

	@Action(value = "searchDoneIssues", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchDoneIssues() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(searchIssueVo.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		List<Long> dealStateList = new ArrayList<Long>();
		dealStateList.add(IssueQueryState.MY_DONE_ONE);
		dealStateList.add(IssueQueryState.MY_DONE_TWO);
		dealStateList.add(IssueQueryState.MY_DONE_THREE);
		searchIssueVo.setDealStateList(dealStateList);
		searchIssueVo.setCompleteCode(IssueState.DEALING_CODE);
		preparePageData();
		transToIssueType();

		EventSourceVo eventSourceVO = new EventSourceVo();
		PropertyDict state = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.EVENTSOURCE_STATE,
						IssueSourceType.TRANS_EVENT_DONE).get(0);
		eventSourceVO.setState(state);
		eventSourceVO.setDealState(1);// 0未处理，1已处理
		List<EventSource> eventSourceList = eventSourceService
				.findInformation(eventSourceVO);
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
		searchIssueVo.setCommandCenterNum(commandCenterNum);
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(searchIssueDao
						.searchCommandCenterDoneIssues(searchIssueVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "occurOrg", "lastOrg", "targeOrg",
								"currentOrg" }, orgId));
		return SUCCESS;
	}

	@Action(value = "searchInformation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchInformation() throws Exception {
		PageInfo<EventSource> pageInfo = ControllerHelper
				.processAllOrgRelativeName(eventSourceService
						.searchInformation(eventSourceVo, page, rows),
						organizationDubboService,
						new String[] { "organization" }, null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "updateInformationForNoShift", results = {
			@Result(name = "success", type = "json", params = { "root",
					"idList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateInformationForNoShift() throws Exception {
		String[] updateId = eventSourceSelectIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		eventSourceService.updateInformationStateById(idList,
				IssueSourceType.NOT_TRANS_EVENT);
		return SUCCESS;
	}

	@Action(value = "updateInformation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"eventSource", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateInformation() throws Exception {
		if (eventSource.getId() != null) {
			eventSource = eventSourceService.updateInformationStateById(
					eventSource.getId(), internalId, serialNumber);
		} else {
			eventSource = eventSourceService
					.updateInformationStateBySerialNumber(serialNumber,
							internalId);
		}
		return SUCCESS;
	}

	@Action(value = "dispatchDeal", results = { @Result(name = "default_dealing", location = "/commandCenter/dealIssueDlg.jsp") })
	public String Deal() throws Exception {
		if ("deal".equalsIgnoreCase(mode)) {
			return forwardToDeal();
		} else {
			return "";
		}
	}

	private String forwardToView() {
		if (legalKeyIdParam()) {
			IssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			issue.getOccurOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(issue
							.getOccurOrg().getId(), organizationDubboService));
			loadFullIssueTypes(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			loadAttachFiles(issue);
			return strategy.forwardToView();
		} else {
			return "error";
		}
	}

	@Action(value = "dealIssue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueVO", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deal() throws Exception {
		IssueNew myissue = getActualIssueServiceInstance()
				.getFullIssueByStepId(keyId);
		if (myissue != null) {
			eventSourceService
					.updateInformationStateBySerialNumber(
							myissue.getSerialNumber(),
							IssueSourceType.TRANS_EVENT_DONE);
		} else {
			return ERROR;
		}
		IssueOperate operate = IssueOperate.parse(dealCode);
		List<IssueAttachFile> files = null;
		try {
			files = createIssueAttachFile(attachFiles);
		} catch (Exception e) {
		}
		if (!validateDealInput(operation, operate, files)) {
			return ERROR;
		}
		if (IssueOperate.REPORT_TO.equals(operate)) {
			issueVO = getActualIssueServiceInstance().reportToCommandCenter(
					keyId, operation);
		} else if (IssueOperate.GIVETO.equals(operate)) {
			issueVO = getActualIssueServiceInstance().giveTo(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
		} else if (IssueOperate.CONCEPT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().concept(keyId, operation);
		} else if (IssueOperate.READ.equals(operate)) {
			issueVO = getActualIssueServiceInstance().read(keyId, operation);
		} else if (IssueOperate.COMMENT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().comment(keyId, operation,
					files);
		} else if (IssueOperate.COMPLETE.equals(operate)) {
			issueVO = getActualIssueServiceInstance().complete(keyId,
					operation, files);
		} else if (IssueOperate.ASSIGN.equals(operate)) {
			issueVO = getActualIssueServiceInstance().assign(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
		} else if (IssueOperate.SUBMIT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().submit(keyId, operation,
					operation.getTargeOrg().getId(),
					parseStringToLongArray(tellOrgIds), files);
		} else if (IssueOperate.BACK.equals(operate)) {
			issueVO = getActualIssueServiceInstance().back(keyId, operation,
					files);
		} else if (IssueOperate.INSTRUCT.equals(operate)) {
			issueVO = getActualIssueServiceInstance()
					.instruct(keyId, operation);
		} else if (isSuperviseDeal()) {
			issueVO = getActualIssueServiceInstance().supervise(operate, keyId,
					operation);
		} else if (IssueOperate.CANCEL_SUPERVISE.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelSupervise(keyId,
					operation);
		} else if (IssueOperate.URGENT.equals(operate)) {
			issueVO = getActualIssueServiceInstance().urgent(keyId, operation);
		} else if (IssueOperate.CANCEL_URGENT.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelUrgent(keyId,
					operation);
		} else if (IssueOperate.HISTORIC.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance()
					.historic(keyId, operation);
		} else if (IssueOperate.CANCEL_HISTORIC.equals(operate)) {
			createEmptyOperationLogByStepId(keyId);
			issueVO = getActualIssueServiceInstance().cancelHistoric(keyId,
					operation);
		} else {
			errorMessage = "未知的处理类型";
			return ERROR;
		}
		return SUCCESS;
	}

	private String forwardToEditIssue() {
		if (legalKeyIdParam()) {
			IssueManageStrategy strategy = getActualIssueManageStrategy();
			issue = getActualIssueServiceInstance().getFullIssueByStepId(keyId);
			loadFullIssueTypes(issue);
			loadAttachFiles(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			return strategy.forwardToEdit();
		} else {
			return "error";
		}
	}

	private void loadFullIssueTypes(IssueNew selectIssue) {
		selectIssue.getIssueType().setIssueTypeDomain(
				issueTypeService.getIssueTypeDomainById(selectIssue
						.getIssueType().getIssueTypeDomain().getId()));
		if (issueHasTypeDomainName == null) {
			issueHasTypeDomainName = new ArrayList<String>();
		} else if (!issueHasTypeDomainName.contains(selectIssue.getIssueType()
				.getIssueTypeDomain().getDomainName())) {
			issueHasTypeDomainName.add(selectIssue.getIssueType()
					.getIssueTypeDomain().getDomainName());
		}
	}

	private void loadAttachFiles(IssueNew selectIssue) {
		issueAttachFiles = getActualIssueServiceInstance()
				.loadIssueAttachFilesByIssueId(selectIssue.getId());
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

	private void prepareExistedIssueTypes() {
		Long currentOrgId = getCurrentLoginedOrgId();
		contradiction = issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION);
		specialisation = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE);
		peopleliveService = issueTypeService
				.findIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE);
		Organization org = organizationDubboService
				.getFullOrgById(currentOrgId);
		if (needShowOtherIssueTypes(org)) {
			otherType = issueTypeService.findAllIssueTypesByParentName(
					currentOrgId, IssueTypes.OTHERTYPE);
		} else {
			otherType = new ArrayList<IssueType>();
		}
	}

	private boolean needShowOtherIssueTypes(Organization org) {
		return org.getOrgLevel().getInternalId() < OrganizationLevel.CITY;
	}

	private Long getCurrentLoginedOrgId() {
		return ThreadVariable.getUser().getOrganization().getId();
	}

	private List<Long> parseSelToLong(String values) {
		List<Long> result = new ArrayList<Long>();
		if (values != null && StringUtil.isStringAvaliable(values)) {
			String[] objects = values.split(",");
			for (int i = 0; i < objects.length; i++) {
				result.add(Long.valueOf(objects[i].trim()));
			}
		}
		return result;
	}

	private IssueType createIssueTypeById(Long id) {
		IssueType issueType = new IssueType();
		issueType.setId(id);
		return issueType;
	}

	/**
	 * 将页面提交的数据List<Long>类型转换成List<IssueType>类型. 并且将已选择的选项对应到一个完成的IssueType.
	 * 
	 * @return
	 */
	private void transToIssueType() {
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		List<Long> selContradictionIdList = parseSelToLong(selContradictionId);
		List<Long> selSpecialisationIdList = parseSelToLong(selSpecialisationId);
		List<Long> selPeopleliveServiceIdList = parseSelToLong(selPeopleliveServiceId);
		List<Long> selOtherTypeIdList = parseSelToLong(selOtherTypeId);
		if (selContradictionIdList != null && selContradictionIdList.size() > 0)
			for (Long issueTypeId : selContradictionIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selSpecialisationIdList != null
				&& selSpecialisationIdList.size() > 0)
			for (Long issueTypeId : selSpecialisationIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selPeopleliveServiceIdList != null
				&& selPeopleliveServiceIdList.size() > 0)
			for (Long issueTypeId : selPeopleliveServiceIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selOtherTypeIdList != null && selOtherTypeIdList.size() > 0)
			for (Long issueTypeId : selOtherTypeIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		searchIssueVo.setIssueTypes(issueTypes);
	}

	private void preparePageData() {
		contradiction = issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION);
		specialisation = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE);
		peopleliveService = issueTypeService
				.findIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE);
	}

	private PageInfo<IssueNew> emptyIssuePage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	private void createEmptyIssueList() {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(rows);
		pageInfo.setResult(new ArrayList<IssueNew>());
		gridPage = new GridPage(pageInfo);
	}

	private IssueService getActualIssueServiceInstance() {
		return issueServiceFactory.getIssueServiceBySeries(getSeries());
	}

	protected String getSeries() {
		return SYS_SERIES;
	}

	private boolean validateInput(IssueNew issue, List<IssueAttachFile> files) {
		ValidateResult result = getActualIssueManageStrategy().validate(issue,
				files);
		if (result.hasError()) {
			errorMessage = result.getErrorMessages();
		}
		return !result.hasError();
	}

	private List<IssueAttachFile> createIssueAttachFile(String[] fileNameAndIdS)
			throws Exception {

		if (fileNameAndIdS == null) {
			return new ArrayList<IssueAttachFile>();
		}

		List<IssueAttachFile> list = new ArrayList<IssueAttachFile>();

		for (String fileNameAndId : fileNameAndIdS) {
			if (StringUtil.isStringAvaliable(fileNameAndId)) {

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

	/**
	 * 解析数据 params: selectedTypes 类型
	 */
	private void reFillIssueProperty() {
		// issue.setIssueTypes(parseToIssueTypes(selectedTypes));
		if (issue.getSourceKind() == null
				|| issue.getSourceKind().getId() == null) {
			factory.getIssueManageStrategyByModule(moduleName)
					.fillIssueSourceProperty(issue);
		}
	}

	private List<IssueType> parseToIssueTypes(String values) {
		List<IssueType> result = new ArrayList<IssueType>();
		IssueManageStrategy strategy = getActualIssueManageStrategy();
		Map<String, List<IssueType>> types = strategy
				.loadEnabledIssueTypes(strategy.getIssueTypeNames());
		if (values != null && StringUtil.isStringAvaliable(values)) {
			String[] objects = values.split(",");
			for (int i = 0; i < objects.length; i++) {
				IssueType type = lookupIssueTypeById(types,
						Long.valueOf(objects[i].trim()));
				if (type != null) {
					result.add(type);
				}
			}
		}
		return result;
	}

	private IssueType lookupIssueTypeById(
			Map<String, List<IssueType>> allTypes, Long id) {
		for (List<IssueType> types : allTypes.values()) {
			for (IssueType type : types) {
				if (type.getId().equals(id)) {
					return type;
				}
			}
		}
		return null;
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

	private void createEmptyOperationLogByStepId(Long stepId) {
		IssueStep step = getActualIssueServiceInstance().getIssueStepById(
				stepId);
		createEmptyOperationLogByStep(step);
	}

	private void createEmptyOperationLogByStep(IssueStep step) {
		operation = new IssueLogNew();
		fillOperationLog(operation, step);
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

	private boolean isSuperviseDeal() {
		return IssueOperate.NORMAL_SUPERVISE.getCode() == dealCode
				|| IssueOperate.YELLOW_SUPERVISE.getCode() == dealCode
				|| IssueOperate.RED_SUPERVISE.getCode() == dealCode;
	}

	private String forwardToDeal() {
		IssueStep step = getActualIssueServiceInstance()
				.getIssueStepById(keyId);
		if (!getCurrentLoginedOrg().equals(step.getTarget())) {
			errorMessage = "你不能处理不属于你部门的事件";
			return ERROR;
		} else {
			operation = new IssueLogNew();
			fillOperationLog(operation, step);
			loadCandoOperations(keyId);
			hiddenSomeOperate(step);
		}
		return getActualIssueManageStrategy().forwardToDealing();
	}

	private void fillOperationLog(IssueLogNew log, IssueStep step) {
		if (log != null) {
			log.setIssue(step.getIssue());
			log.setDealUserName(getCurrentLoginedUser().getName());
			log.setMobile(getCurrentLoginedUser().getMobile());
			log.setDealOrg(getCurrentLoginedOrg());
		}
	}

	private void loadCandoOperations(Long stepId) {
		if (!DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			canDoList = getActualIssueServiceInstance().getIssueCandoForOrg(
					stepId, getCurrentLoginedOrg());
		} else {
			canDoList = new ArrayList<IssueOperate>();
		}
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

	private User getCurrentLoginedUser() {
		return ThreadVariable.getUser();
	}

	private Organization getCurrentLoginedOrg() {
		return ThreadVariable.getSession().getOrganization();
	}

	private void getEventSourceInfo() {
		eventSource = eventSourceService.getEventSouceById(eventSource.getId());
	}

	private boolean legalKeyIdParam() {
		return null != keyId;
	}

	private String forwardToAddIssue() {
		IssueManageStrategy strategy = getActualIssueManageStrategy();
		if (legalKeyIdParam()) {
			EventSource eventSource = eventSourceService
					.getEventSouceById(this.eventSource.getId());
			getEventSourceInfo();
			issue.setSubject(eventSource.getTitle());
			issue.setOccurLocation(eventSource.getOrgName());
			issue.setIssueContent(eventSource.getContent());
			issue.setOccurDate(eventSource.getSourceDate());
			issue.setSourceKind(eventSource.getSourceType());
			issue.setSourcePerson(eventSource.getSourcePeople());
			fillPropertyDefaultValue(issue);
			issueTypeNames = strategy.getIssueTypeNames();
			issueTypes = strategy.loadEnabledIssueTypes(issueTypeNames);
			return strategy.forwardToAdd();
		} else {
			return "error";
		}
	}

	private void fillPropertyDefaultValue(IssueNew issue) {
		if (!levelGreatThenTown(dealOrgId)) {
			Organization org = organizationDubboService
					.getSimpleOrgById(dealOrgId);
			issue.setOccurOrg(org);// 添加发生网格
		}
	}

	private boolean levelGreatThenTown(Long orgId) {
		Organization fullOrg = organizationDubboService.getFullOrgById(orgId);
		return OrganizationLevel.levelCompare(fullOrg.getOrgLevel()
				.getInternalId(), OrganizationLevel.TOWN) > 0;
	}

	private IssueManageStrategy getActualIssueManageStrategy() {
		if (strategy == null) {
			strategy = factory.getIssueManageStrategyByModule(moduleName);
		}
		return strategy;
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

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public EventSource getEventSource() {
		return eventSource;
	}

	public void setEventSource(EventSource eventSource) {
		this.eventSource = eventSource;
	}

	public EventSourceVo getEventSourceVo() {
		return eventSourceVo;
	}

	public void setEventSourceVo(EventSourceVo eventSourceVo) {
		this.eventSourceVo = eventSourceVo;
	}

	public String getEventSourceSelectIds() {
		return eventSourceSelectIds;
	}

	public void setEventSourceSelectIds(String eventSourceSelectIds) {
		this.eventSourceSelectIds = eventSourceSelectIds;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public IssueManageStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IssueManageStrategy strategy) {
		this.strategy = strategy;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public List<IssueTypeViewDefine> getIssueTypeNames() {
		return issueTypeNames;
	}

	public void setIssueTypeNames(List<IssueTypeViewDefine> issueTypeNames) {
		this.issueTypeNames = issueTypeNames;
	}

	public Map<String, List<IssueType>> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(Map<String, List<IssueType>> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public Long getDealOrgId() {
		return dealOrgId;
	}

	public void setDealOrgId(Long dealOrgId) {
		this.dealOrgId = dealOrgId;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public long getStepId() {
		return stepId;
	}

	public void setStepId(long stepId) {
		this.stepId = stepId;
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

	public String getInvolvedPlace() {
		return involvedPlace;
	}

	public void setInvolvedPlace(String involvedPlace) {
		this.involvedPlace = involvedPlace;
	}

	public String getInvolvedPerson() {
		return involvedPerson;
	}

	public void setInvolvedPerson(String involvedPerson) {
		this.involvedPerson = involvedPerson;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public IssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(IssueLogNew operation) {
		this.operation = operation;
	}

	public List<IssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<IssueOperate> canDoList) {
		this.canDoList = canDoList;
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

	public List<Object> getJosnlist() {
		return josnlist;
	}

	public void setJosnlist(List<Object> josnlist) {
		this.josnlist = josnlist;
	}

	public SearchCommandCenterIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchCommandCenterIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
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

	public String getSelOtherTypeId() {
		return selOtherTypeId;
	}

	public void setSelOtherTypeId(String selOtherTypeId) {
		this.selOtherTypeId = selOtherTypeId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<IssueType> getOtherType() {
		return otherType;
	}

	public void setOtherType(List<IssueType> otherType) {
		this.otherType = otherType;
	}

	public List<String> getIssueHasTypeDomainName() {
		return issueHasTypeDomainName;
	}

	public void setIssueHasTypeDomainName(List<String> issueHasTypeDomainName) {
		this.issueHasTypeDomainName = issueHasTypeDomainName;
	}

	public List<IssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(List<IssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public int getStateKind() {
		return stateKind;
	}

	public void setStateKind(int stateKind) {
		this.stateKind = stateKind;
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

}
