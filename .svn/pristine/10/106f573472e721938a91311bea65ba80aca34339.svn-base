package com.tianque.working.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.vo.ExtTreeStringIdData;
import com.tianque.controller.vo.WorkingRecordTreeData;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueInspect;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PlantFormMessageConfig;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyLog;
import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;
import com.tianque.working.service.KeyAreasOfInvestigationInfoService;
import com.tianque.working.service.SignificantIssuedealService;
import com.tianque.working.service.WorkingRecordService;
import com.tianque.working.vo.SearchWorkingRecordVo;

@Transactional
@Scope("request")
@Namespace("/workingRecord/workingRecordManage")
@Controller("workingRecordController")
public class WorkingRecordController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(DailyLogController.class);
	@Autowired
	private WorkingRecordService workingRecordService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyLogAttachFileService dailyLogAttachFileService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private KeyAreasOfInvestigationInfoService areasOfInvestigationInfoService;
	@Autowired
	private SignificantIssuedealService issSignificantIssuedealService;
	private DailyLog dailyLog;
	private DailyDirectory dailyDirectory;
	private Long dailyYearId;
	private Long parentId;
	private int internalId;
	private Organization organization;
	private Map<String, String> issueDetailSumData = new HashMap<String, String>();
	private IssueInspect issueInspect;
	private String[] attachFiles;// 附件
	private Long[] issueAttachFileIds;// 已办结事项的附件id
	private DailyLogAttachFile dailyLogAttachFile;
	private String issueDetails;
	private Date dealFrom;
	private Date dealTo;
	private Date serverDate;
	private Long parentIndate;

	private String orgid;
	private String selectMonth;
	private PlantFormMessageConfig plantFormMessageConfig;

	List<DailyDirectory> dailyTrunkDirectorys = new ArrayList<DailyDirectory>();
	private int existedCount;

	private List<ExtTreeStringIdData> extTreeDatas;
	/** 用于list */
	private Long dailyDirectoryId;
	/** 用于maintain */
	private WorkingRecord workingRecord;
	/** 用于查看 */
	private List<DailyLogAttachFile> dailyLogAttachFiles = new ArrayList<DailyLogAttachFile>();
	/** 用于查询 */
	private SearchWorkingRecordVo searchWorkingRecordVo;
	/** 显示本级（true）或者显示本级及下辖（false） **/
	private boolean displayLevel;
	/** 要删除的台账ids/要共享的ids */
	private String ids;
	/** 用于共享 */
	private List<WorkingRecord> workingRecords;
	private String identification;
	private MyProfile myProfile;
	private Long sendMessage;
	private Long resourcePoolTypeId;
	private String setPermissionCacheId;
	private List<MyProfile> myProfiles;
	private boolean fromIssue = false;

	/**
	 * id加密页面跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "edit", location = "/daily/workingRecord/maintainWorkingRecordDlg.jsp"),
			@Result(name = "view", location = "/daily/workingRecord/viewWorkingRecordDlg.jsp"),
			@Result(name = "share", location = "/knowledgeSharing/myProfile/synchFileDlg.jsp") })
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return "add";
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			getWorkingRecordInfo();
			return "edit";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			getWorkingRecordInfo();
			return "view";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if ("sharing".equalsIgnoreCase(mode)) {
			workingRecords = workingRecordService.getWorkingRecordsByIds(ids);
			return "share";
		}
		return ERROR;
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "add", location = "/daily/workingRecord/maintainWorkingRecordDlg.jsp"),
			@Result(name = "edit", location = "/daily/workingRecord/maintainWorkingRecordDlg.jsp"),
			@Result(name = "view", location = "/daily/workingRecord/viewWorkingRecordDlg.jsp"),
			@Result(name = "search", location = "/daily/workingRecord/searchWorkingRecordDlg.jsp"),
			@Result(name = "share", location = "/knowledgeSharing/myProfile/synchFileDlg.jsp") })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return "add";
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			getWorkingRecordInfo();
			return "edit";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			getWorkingRecordInfo();
			return "view";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if ("sharing".equalsIgnoreCase(mode)) {
			workingRecords = workingRecordService.getWorkingRecordsByIds(ids);
			return "share";
		}
		return ERROR;
	}

	@PermissionFilter(ename = "addWorkingRecord")
	@Action(value = "dispatchBusiness", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workingRecord", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return addWorkingRecord();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return updateWorkingRecord();
		} else if ("convertToWorkingRecord".equalsIgnoreCase(mode)) {
			return convertToWorkingRecord();
		}
		return ERROR;
	}

	@Action(value = "workingRecordList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String workingRecordList() throws Exception {
		if (null == organization || null == organization.getId()) {
			organization = ThreadVariable.getUser().getOrganization();
		}
		gridPage = new GridPage(
				workingRecordService
						.findWorkingRecordForPageByOrgIdAndDailyDirectoryId(
								organization.getId(), page, rows, sidx, sord,
								dailyDirectoryId, displayLevel));
		return SUCCESS;
	}

	@Action(value = "getWorkingRecordById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workingRecord", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getWorkingRecordById() throws Exception {
		workingRecord = workingRecordService.getWorkingRecordById(workingRecord
				.getId());
		return SUCCESS;
	}

	@Action(value = "findWorkingRecordsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findWorkingRecordsByQueryCondition() throws Exception {
		PageInfo<WorkingRecord> pageInfo = workingRecordService
				.findPagerBySearchVo(searchWorkingRecordVo, page, rows, sidx,
						sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * id加密删除
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteWorkingRecord")
	@Action(value = "deleteWorkingRecordByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String deleteWorkingRecordByIdsByEncrypt() throws Exception {
		workingRecordService.deleteWorkingRecordByIds(ids);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteWorkingRecord")
	@Action(value = "deleteWorkingRecordByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String deleteWorkingRecordByIds() throws Exception {
		workingRecordService.deleteWorkingRecordByIds(ids);
		return SUCCESS;
	}

	@Action(value = "downloadDailyLogAttachFile")
	public String downloadDailyLogAttachFile() throws Exception {
		if (dailyLogAttachFile == null || dailyLogAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dailyLogAttachFile = dailyLogAttachFileService
				.getSimpleDailyLogAttachFileById(dailyLogAttachFile.getId());
		if (dailyLogAttachFile == null) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator + dailyLogAttachFile.getFileActualUrl());
		downloadFileName = new String(dailyLogAttachFile.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}

	@Action(value = "deleteDailyLogAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDailyLogAttachFile() throws Exception {
		if (dailyLogAttachFile == null || dailyLogAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dailyLogAttachFileService
				.deleteDailyLogAttachFileById(dailyLogAttachFile.getId());
		return SUCCESS;
	}

	@Action(value = "synchToMyProfile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"myProfiles", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String synchToMyProfile() throws Exception {
		myProfiles = workingRecordService.synchToMyProfile(ids, sendMessage,
				resourcePoolTypeId, setPermissionCacheId);
		return SUCCESS;
	}

	/**
	 * 根据台账Id获取当前台账信息 返回包含附件列表的台账信息
	 */
	private void getWorkingRecordInfo() {
		workingRecord = workingRecordService.getWorkingRecordById(workingRecord
				.getId());
		dailyLogAttachFiles = dailyLogAttachFileService
				.getSimpleDailyLogAttachFileByDailyLogId(workingRecord.getId());
		workingRecord.setDailyLogAttachFile(dailyLogAttachFiles);
		// 用于判断该台帐是否是来自已办结事项 如果是,该台帐的内容和类型不能修改
		if (workingRecord.getContent() != null
				&& (workingRecord.getContent().contains(
						"class=\"issueContable\"") || workingRecord
						.getContent().contains("<DIV>"))) {
			fromIssue = true;
		}

	}

	private String updateWorkingRecord() {
		try {

			workingRecord = workingRecordService.updateWorkingRecord(
					workingRecord, attachFiles);
		} catch (Exception e) {
			logger.error("updateWorkingRecord", e);
			this.errorMessage = "updateWorkingRecord错误";
			return ERROR;
		}
		return SUCCESS;
	}

	private String addWorkingRecord() {
		try {
			workingRecord = workingRecordService.addWorkingRecord(
					workingRecord, attachFiles);
		} catch (Exception e) {
			logger.error("addWorkingRecord", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/** 已办结事项转为台帐 */
	private String convertToWorkingRecord() {

		try {
			workingRecord = workingRecordService.convertToWorkingRecord(
					workingRecord, attachFiles, issueAttachFileIds);
		} catch (Exception e) {
			logger.error("convertToWorkingRecord", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findExtTreeDataByDirectoryId", results = { @Result(name = "success", type = "json", params = {
			"root", "extTreeDatas", "ignoreHierarchy", "false" }) })
	public String findExtTreeDataByDirectoryId() throws Exception {
		extTreeDatas = new ArrayList<ExtTreeStringIdData>();
		dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(parentId);
		internalId = dailyDirectory.getType().getInternalId();

		if (internalId == StatementsReportedType.INVESTIGATION) {
			List<KeyAreasOfInvestigationInfo> areasList = areasOfInvestigationInfoService
					.findKeyAreasOfInvestigationInfos(ThreadVariable.getUser()
							.getOrganization().getId(), parentId);
			for (KeyAreasOfInvestigationInfo areas : areasList) {
				extTreeDatas.add(new WorkingRecordTreeData(areas));
			}
		} else if (internalId == StatementsReportedType.SIGNIFICANT_ISSUEDEAL) {
			List<SignificantIssuedeal> signsList = issSignificantIssuedealService
					.findSignificantIssuedeals(ThreadVariable.getUser()
							.getOrganization().getId(), parentId);
			for (SignificantIssuedeal signs : signsList) {
				extTreeDatas.add(new WorkingRecordTreeData(signs));
			}
		} else {
			List<WorkingRecord> workingRecords = workingRecordService
					.findWorkingRecordByDailyDirectoryId(parentId,
							ThreadVariable.getUser().getOrganization().getId());
			for (WorkingRecord workingRecord : workingRecords) {
				extTreeDatas.add(new WorkingRecordTreeData(workingRecord));
			}
		}

		return SUCCESS;
	}

	private void loadTypeDailyDirectory(DailyDirectory dailyDirectory) {
		if (dailyDirectory != null)
			dailyDirectory.setType(propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId()));
	}

	public String dailyLogMenu() {
		dailyTrunkDirectorys = dailyDirectoryService
				.findDailyAllTrunkDirectory();

		return SUCCESS;
	}

	public String dailyLogForPage() {
		organization = ThreadVariable.getUser().getOrganization();
		if (dailyYearId != null)
			dailyTrunkDirectorys = dailyDirectoryService
					.findDailyDirectoryByDailyYearId(dailyYearId);
		for (DailyDirectory dailyDirectory : dailyTrunkDirectorys) {
			loadTypeDailyDirectory(dailyDirectory);
		}
		return SUCCESS;
	}

	public DailyLog getDailyLog() {
		return dailyLog;
	}

	public void setDailyLog(DailyLog dailyLog) {
		this.dailyLog = dailyLog;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public List<DailyDirectory> getDailyTrunkDirectorys() {
		return dailyTrunkDirectorys;
	}

	public void setDailyTrunkDirectorys(
			List<DailyDirectory> dailyTrunkDirectorys) {
		this.dailyTrunkDirectorys = dailyTrunkDirectorys;
	}

	public Long getDailyYearId() {
		return dailyYearId;
	}

	public void setDailyYearId(Long dailyYearId) {
		this.dailyYearId = dailyYearId;
	}

	public DailyLogAttachFile getDailyLogAttachFile() {
		return dailyLogAttachFile;
	}

	public void setDailyLogAttachFile(DailyLogAttachFile dailyLogAttachFile) {
		this.dailyLogAttachFile = dailyLogAttachFile;
	}

	public Date getDealFrom() {
		return dealFrom;
	}

	public void setDealFrom(Date dealFrom) {
		this.dealFrom = dealFrom;
	}

	public Date getDealTo() {
		return dealTo;
	}

	public void setDealTo(Date dealTo) {
		this.dealTo = dealTo;
	}

	public Map<String, String> getIssueDetailSumData() {
		return issueDetailSumData;
	}

	public void setIssueDetailSumData(Map<String, String> issueDetailSumData) {
		this.issueDetailSumData = issueDetailSumData;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getIssueDetails() {
		return issueDetails;
	}

	public void setIssueDetails(String issueDetails) {
		this.issueDetails = issueDetails;
	}

	public IssueInspect getIssueInspect() {
		return issueInspect;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public void setIssueInspect(IssueInspect issueInspect) {
		this.issueInspect = issueInspect;
	}

	public int getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(int existedCount) {
		this.existedCount = existedCount;
	}

	public List<ExtTreeStringIdData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeStringIdData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public String getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(String selectMonth) {
		this.selectMonth = selectMonth;
	}

	public PlantFormMessageConfig getPlantFormMessageConfig() {
		return plantFormMessageConfig;
	}

	public void setPlantFormMessageConfig(
			PlantFormMessageConfig plantFormMessageConfig) {
		this.plantFormMessageConfig = plantFormMessageConfig;
	}

	public Date getServerDate() {
		return serverDate;
	}

	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}

	public Long getParentIndate() {
		return parentIndate;
	}

	public void setParentIndate(Long parentIndate) {
		this.parentIndate = parentIndate;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public WorkingRecord getWorkingRecord() {
		return workingRecord;
	}

	public void setWorkingRecord(WorkingRecord workingRecord) {
		this.workingRecord = workingRecord;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<DailyLogAttachFile> getDailyLogAttachFiles() {
		return dailyLogAttachFiles;
	}

	public void setDailyLogAttachFiles(
			List<DailyLogAttachFile> dailyLogAttachFiles) {
		this.dailyLogAttachFiles = dailyLogAttachFiles;
	}

	public SearchWorkingRecordVo getSearchWorkingRecordVo() {
		return searchWorkingRecordVo;
	}

	public void setSearchWorkingRecordVo(
			SearchWorkingRecordVo searchWorkingRecordVo) {
		this.searchWorkingRecordVo = searchWorkingRecordVo;
	}

	public boolean isDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(boolean displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<WorkingRecord> getWorkingRecords() {
		return workingRecords;
	}

	public void setWorkingRecords(List<WorkingRecord> workingRecords) {
		this.workingRecords = workingRecords;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public MyProfile getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(MyProfile myProfile) {
		this.myProfile = myProfile;
	}

	public Long getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(Long sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Long getResourcePoolTypeId() {
		return resourcePoolTypeId;
	}

	public void setResourcePoolTypeId(Long resourcePoolTypeId) {
		this.resourcePoolTypeId = resourcePoolTypeId;
	}

	public String getSetPermissionCacheId() {
		return setPermissionCacheId;
	}

	public void setSetPermissionCacheId(String setPermissionCacheId) {
		this.setPermissionCacheId = setPermissionCacheId;
	}

	public List<MyProfile> getMyProfiles() {
		return myProfiles;
	}

	public void setMyProfiles(List<MyProfile> myProfiles) {
		this.myProfiles = myProfiles;
	}

	public Long[] getIssueAttachFileIds() {
		return issueAttachFileIds;
	}

	public void setIssueAttachFileIds(Long[] issueAttachFileIds) {
		this.issueAttachFileIds = issueAttachFileIds;
	}

	public boolean isFromIssue() {
		return fromIssue;
	}

	public void setFromIssue(boolean fromIssue) {
		this.fromIssue = fromIssue;
	}

}
