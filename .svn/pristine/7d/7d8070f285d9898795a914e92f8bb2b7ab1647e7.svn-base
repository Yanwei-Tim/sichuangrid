package com.tianque.working.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.DetailedRuleDailyLogRela;
import com.tianque.domain.Organization;
import com.tianque.domain.PlantFormMessageConfig;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.User;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.domain.vo.SignificantIssuedealVo;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.SignificantIssuedealAttachFiles;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;
import com.tianque.working.service.SignificantIssuedealAttachFileService;
import com.tianque.working.service.SignificantIssuedealService;

/***
 * 重大矛盾纠纷排查调处情况
 */
@SuppressWarnings("serial")
@Controller("significantIssuedealController")
@Namespace("/daily/significantIssuedealManage")
@Scope("prototype")
@Transactional
public class SignificantIssuedealController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(SignificantIssuedealController.class);

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PlatformMessageService platformaMessageService;

	private List<SignificantIssuedeal> result;

	private PlatformMessage platformMessage;

	public List<SignificantIssuedeal> getResult() {
		return result;
	}

	public void setResult(List<SignificantIssuedeal> result) {
		this.result = result;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public SignificantIssuedealService getSignificantIssuedealService() {
		return significantIssuedealService;
	}

	public void setSignificantIssuedealService(
			SignificantIssuedealService significantIssuedealService) {
		this.significantIssuedealService = significantIssuedealService;
	}

	public PlatformMessage getPlatformMessage() {
		return platformMessage;
	}

	public void setPlatformMessage(PlatformMessage platformMessage) {
		this.platformMessage = platformMessage;
	}

	public DailyDirectoryService getDailyDirectoryService() {
		return dailyDirectoryService;
	}

	public void setDailyDirectoryService(
			DailyDirectoryService dailyDirectoryService) {
		this.dailyDirectoryService = dailyDirectoryService;
	}

	public DailyLogAttachFileService getDailyLogAttachFileService() {
		return dailyLogAttachFileService;
	}

	public void setDailyLogAttachFileService(
			DailyLogAttachFileService dailyLogAttachFileService) {
		this.dailyLogAttachFileService = dailyLogAttachFileService;
	}

	public SignificantIssuedeal getSignificantIssuedealWorkingRecord() {
		return significantIssuedealWorkingRecord;
	}

	public void setSignificantIssuedealWorkingRecord(
			SignificantIssuedeal significantIssuedealWorkingRecord) {
		this.significantIssuedealWorkingRecord = significantIssuedealWorkingRecord;
	}

	private Long getOrganizationId() {
		return ThreadVariable.getUser().getOrganization().getId();
	}

	@Autowired
	private SignificantIssuedealService significantIssuedealService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyLogAttachFileService dailyLogAttachFileService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private SignificantIssuedealAttachFileService disputeAttachFileService;

	private SignificantIssuedealAttachFiles disputeAttachFiles;
	private SignificantIssuedeal significantIssuedealWorkingRecord;
	private Organization organization;
	private DailyDirectory dailyDirectory;
	private Long dailyDirectoryId;
	private String[] attachFiles;
	private SignificantIssuedeal significantIssuedeal;
	private String significantIssuedealids;
	private boolean deleteSuccess;
	private PlantFormMessageConfig plantFormMessageConfig;
	private String subOrgIds;
	private SignificantIssuedealVo significantIssuedealVo;
	private String modeType;
	private SignificantIssuedealVo searchSignificantIssuedealWorkingRecord;

	@Action(value = "deleteSignificantIssuedealAttachFiles", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteSuccess", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSignificantIssuedealAttachFiles() throws Exception {
		if (disputeAttachFiles == null || disputeAttachFiles.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		disputeAttachFileService
				.deleteSignificantIssuedealAttachFilesById(disputeAttachFiles
						.getId());
		deleteSuccess = true;
		return SUCCESS;
	}

	@Action(value = "downLoadAttachFile", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downloadDisputeAttachFile() throws Exception {
		if (disputeAttachFiles == null || disputeAttachFiles.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		disputeAttachFiles = disputeAttachFileService
				.getSimpleSignificantIssuedealAttachFilesById(disputeAttachFiles
						.getId());
		if (disputeAttachFiles == null) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(createStoreFile(
				disputeAttachFiles.getFileactualUrl(),
				disputeAttachFiles.getFileName()));
		return STREAM_SUCCESS;
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

	@PermissionFilter(ename = "addWorkingRecord")
	@Action(value = "addSignificantIssuedeal", results = {
			@Result(type = "json", name = "success", params = { "root",
					"significantIssuedealWorkingRecord", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSignificantIssuedealWorkingRecord() throws Exception {
		if (!vidateInput() || this.dailyDirectory == null
				|| this.dailyDirectory.getId() == null) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		significantIssuedealWorkingRecord.setDailyDirectory(dailyDirectory);
		significantIssuedealWorkingRecord.setOrganization(ThreadVariable
				.getUser().getOrganization());
		significantIssuedealWorkingRecord = significantIssuedealService
				.addSignificantIssuedeal(significantIssuedealWorkingRecord);
		significantIssuedealWorkingRecord
				.setInvestigationOrg(ControllerHelper
						.proccessRelativeOrgNameByOrg(
								significantIssuedealWorkingRecord
										.getInvestigationOrg(),
								organizationDubboService));
		addSignificantIssuedealWorkingRecordAttachFile(
				significantIssuedealWorkingRecord, attachFiles);
		autoSetAttachFiles(significantIssuedealWorkingRecord);
		return SUCCESS;
	}

	public String getSignificantIssuedealWorkingRecordById() {
		significantIssuedealWorkingRecord = significantIssuedealService
				.getSignificantIssuedealById(significantIssuedealWorkingRecord
						.getId());
		if (significantIssuedealWorkingRecord != null
				&& significantIssuedealWorkingRecord.getId() != null) {
			autoSetAttachFiles(significantIssuedealWorkingRecord);
		}
		return SUCCESS;
	}

	private boolean addSignificantIssuedealWorkingRecordAttachFile(
			SignificantIssuedeal disputeIssuedealWorkingRecord,
			String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(disputeIssuedealWorkingRecord, fileName))
				return false;
		}
		return true;

	}

	private boolean addAttachFile(
			SignificantIssuedeal disputeIssuedealWorkingRecord, String fileName) {
		SignificantIssuedealAttachFiles disputeAttachFile = new SignificantIssuedealAttachFiles();
		disputeAttachFile
				.setSignificantIssuedealWorkingRecord(disputeIssuedealWorkingRecord);
		StoredFile storedFile = null;
		try {
			storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.DAILYLOG_PATH);
		} catch (Exception e) {
			logger.error("处理附件错误：", e);
			return false;
		}
		disputeAttachFile.setFileSize(storedFile.getFileSize());
		disputeAttachFile.setFileactualUrl(storedFile.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		disputeAttachFile.setFileName(fileName);
		disputeIssuedealWorkingRecord.getSignificantIssuedealAttachFiles().add(
				disputeAttachFileService
						.addSignificantIssuedealAttachFiles(disputeAttachFile));
		return true;
	}

	private boolean vidateInput() {
		boolean bool = true;
		if (significantIssuedealWorkingRecord.getInvestigationDate() == null
				|| significantIssuedealWorkingRecord.getAddress() == null
				|| significantIssuedealWorkingRecord.getAccountabilityLeading() == null
				|| significantIssuedealWorkingRecord.getAccountabilityUnit() == null
				|| significantIssuedealWorkingRecord
						.getSignificantIssuedealReason() == null) {
			bool = false;
		}
		return bool;
	}

	private Date dealFrom;
	private Date dealTo;

	@JSON(format = "yyyy-MM-dd")
	public SignificantIssuedealVo getSignificantIssuedealVo() {
		return significantIssuedealVo;
	}

	public void setSignificantIssuedealVo(
			SignificantIssuedealVo significantIssuedealVo) {
		this.significantIssuedealVo = significantIssuedealVo;
	}

	@JSON(format = "yyyy-MM-dd")
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

	@Action(value = "findSignificantIssuedealsByInvestigationDate", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSignificantIssuedealsByInvestigationDate()
			throws Exception {

		if (significantIssuedealVo.getOrganization() == null
				|| significantIssuedealVo.getOrganization().getId() == null
				|| significantIssuedealVo.getDailyDirectory() == null
				|| significantIssuedealVo.getDailyDirectory().getId() == null) {
			gridPage = new GridPage(new PageInfo<SignificantIssuedeal>());
			return SUCCESS;
		}
		try {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							significantIssuedealService
									.getSignificantIssuedealsByInvestigationDate(
											significantIssuedealVo, page, rows,
											"", ""), organizationDubboService,
							new String[] { "investigationOrg" },
							getOrganizationId()));
		} catch (Exception e) {
			logger.error("列表展现", e);
			gridPage = new GridPage(new PageInfo<SignificantIssuedeal>());
		}
		return SUCCESS;
	}

	@Action(value = "findSingificantIssuesealsForPrint", results = {
			@Result(name = "success", location = "/daily/statementsReported/singificantIssueseal/printList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSingificantIssuesealsForPrint() throws Exception {
		String[] ids = significantIssuedealids.split(",");
		result = significantIssuedealService.findSignificantIssuedealsForPrint(
				organization.getId(), ids);
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		return SUCCESS;
	}

	private boolean margeAttachFiles(
			SignificantIssuedeal significantIssuedealWorkingIssuedeal) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<SignificantIssuedealAttachFiles> significantIssuedealAttachFiles = disputeAttachFileService
				.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(significantIssuedealWorkingIssuedeal
						.getId());
		List<String> dailyLogAttachFileName = new ArrayList<String>();
		for (SignificantIssuedealAttachFiles significantIssuedealAttachFile : significantIssuedealAttachFiles) {
			dailyLogAttachFileName.add(significantIssuedealAttachFile
					.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyLogAttachFileName.contains(fileName))
				if (!addAttachFile(significantIssuedealWorkingIssuedeal,
						fileName))
					return false;
		}
		return true;
	}

	@PermissionFilter(ename = "updateWorkingRecord")
	@Action(value = "updateSignificantIssuedeal", results = {
			@Result(type = "json", name = "success", params = { "root",
					"significantIssuedealWorkingRecord", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateSignificantIssuedealWorkingRecord() throws Exception {
		if (!vidateInput() || significantIssuedealWorkingRecord == null
				|| significantIssuedealWorkingRecord.getId() == null
				|| significantIssuedealWorkingRecord.getId().longValue() == 0L) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		significantIssuedealWorkingRecord = significantIssuedealService
				.updateSignificantIssuedeal(significantIssuedealWorkingRecord);
		significantIssuedealWorkingRecord
				.setInvestigationOrg(ControllerHelper
						.proccessRelativeOrgNameByOrg(
								significantIssuedealWorkingRecord
										.getInvestigationOrg(),
								organizationDubboService));

		if (!margeAttachFiles(significantIssuedealWorkingRecord))
			return ERROR;
		autoSetAttachFiles(significantIssuedealWorkingRecord);
		return SUCCESS;
	}

	@Action(value = "deleteSignificantIssuedealWorkingRecord", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteSuccess", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSignificantIssuedealWorkingRecord() throws Exception {
		List<DetailedRuleDailyLogRela> judgeList = new ArrayList<DetailedRuleDailyLogRela>();
		judgeList = detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordIds(
						Integer.valueOf(
								StatementsReportedType.SIGNIFICANT_ISSUEDEAL)
								.longValue(), significantIssuedealids);

		if (!judgeList.isEmpty()) {
			return ERROR;
		}

		String[] ids = significantIssuedealids.split(",");
		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i])) {
				continue;
			}
			Long id = Long.valueOf(ids[i]);
			disputeAttachFileService
					.deleteSignificantIssuedealAttachFilesByDisputeIssuedealId(id);
			significantIssuedealService.deleteSignificantIssuedealById(id);
		}
		deleteSuccess = true;
		return SUCCESS;
	}

	private boolean vidateMessage() {
		boolean bool = true;

		if (platformMessage == null
				|| !StringUtil.isStringAvaliable(platformMessage.getTitle())
				|| !StringUtil.isStringAvaliable(platformMessage.getContent())) {

			bool = false;
		}
		return bool;
	}

	/***
	 * 上报重大矛盾纠纷排查调处情况
	 */
	@PermissionFilter(ename = "upWorkingRecord")
	@Action(value = "reportedSignificantIssuedealById", results = {
			@Result(type = "json", name = "success", params = { "root",
					"significantIssuedeal", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String reportedSignificantIssuedealById() throws Exception {

		if (!StringUtil.isStringAvaliable(significantIssuedealids)) {
			errorMessage = "参数错误";
			return ERROR;
		}

		Long[] ids = analyze(significantIssuedealids);

		for (int i = 0; i < ids.length; i++) {
			significantIssuedeal = significantIssuedealService
					.reportedSignificantIssuedealById(ids[i],
							getOrganizationId(),
							significantIssuedeal.getExpiredEntering());

			significantIssuedeal.setInvestigationOrg(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							significantIssuedeal.getInvestigationOrg(),
							organizationDubboService));
		}

		sendPlatformMessageToOrg(ids);

		return SUCCESS;
	}

	private void sendPlatformMessageToOrg(Long[] ids) {
		PlatformMessage pm = platformMessageFactory
				.createUnStatedReportsSubmitPlatformMessage(ids.length,
						StatementsReportedType.INVESTIGATION);

		Organization org = organizationDubboService.getSimpleOrgById(ThreadVariable
				.getOrganization().getId());

		platformaMessageService.sendPlatformMessageToOrg(org.getParentOrg()
				.getId(), pm);
	}

	/***
	 * 回退重大矛盾纠纷排查调处情况
	 */
	@PermissionFilter(ename = "backWorkingRecord")
	@Action(value = "backSignificantIssuedeal", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteSuccess", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String backSignificantIssuedeal() throws Exception {

		if (!StringUtil.isStringAvaliable(significantIssuedealids)
				|| !vidateMessage()) {
			errorMessage = "参数错误";
			return ERROR;
		}

		List<DetailedRuleDailyLogRela> judgeList = new ArrayList<DetailedRuleDailyLogRela>();

		judgeList = detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordIds(
						Integer.valueOf(
								StatementsReportedType.SIGNIFICANT_ISSUEDEAL)
								.longValue(), significantIssuedealids);

		if (!judgeList.isEmpty()) {
			this.errorMessage = "已经被考核评估引用，不能回退";
			return ERROR;
		}

		Long[] ids = analyze(significantIssuedealids);

		for (int i = 0; i < ids.length; i++) {
			deleteSuccess = significantIssuedealService
					.backSignificantIssuedeal(ids[i]);
		}

		sendPlatformMessageToUser(ids);

		return SUCCESS;
	}

	private void sendPlatformMessageToUser(Long[] ids) {

		List<User> users = permissionService.findChildUsersByEnameAndOrgCode(
				"myWorkingRecordManagement", subOrgIds.toString());

		if (null != users && users.size() > 0) {

			platformMessage = platformMessageFactory
					.createUnStatedReportsBackPlatformMessage(platformMessage);

			for (User user : users) {
				platformMessage.setReceiverId(user.getId());
				platformaMessageService
						.sendPlatformMessageToUser(platformMessage);
			}
		}
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/statementsReported/singificantIssueseal/maintainSingificantIssuesealDlg.jsp"),
			@Result(name = "search", location = "/daily/statementsReported/singificantIssueseal/filterSingificantIssuesealDlg.jsp"),
			@Result(name = "back", location = "/daily/statementsReported/singificantIssueseal/backSingificantIssuesealBackDlg.jsp"),
			@Result(name = "print", location = "/daily/statementsReported/singificantIssueseal/singificantIssuesealDlgPrint.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			if (this.dailyDirectory == null
					|| this.dailyDirectory.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			significantIssuedealWorkingRecord = new SignificantIssuedeal();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (significantIssuedealWorkingRecord == null
					|| significantIssuedealWorkingRecord.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			significantIssuedealWorkingRecord = significantIssuedealService
					.getSignificantIssuedealById(significantIssuedealWorkingRecord
							.getId());
			autoSetAttachFiles(significantIssuedealWorkingRecord);
		} else if ("back".equalsIgnoreCase(mode)) {
			if ("".equals(significantIssuedealids) || "".equals(subOrgIds)) {
				errorMessage = "参数错误";
				return ERROR;
			}
			return "back";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			if (significantIssuedealWorkingRecord == null
					|| significantIssuedealWorkingRecord.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			significantIssuedealWorkingRecord = significantIssuedealService
					.getSignificantIssuedealById(significantIssuedealWorkingRecord
							.getId());
			return "print";
		}
		return SUCCESS;
	}

	@Action(value = "significantIssuedealWorkingRecordList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String significantIssuedealWorkingRecordList() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<SignificantIssuedeal>());
			return SUCCESS;
		}

		try {
			if (null == organization || null == organization.getId()) {
				organization = ThreadVariable.getUser().getOrganization();
			}
			PageInfo<SignificantIssuedeal> pageInfo = new PageInfo<SignificantIssuedeal>();
			pageInfo = significantIssuedealService
					.findSignificantIssuedealsForPageByOrgIdAndDirectoryId(
							organization.getId(), dailyDirectory.getId(), page,
							rows, sidx, sord);
			createGridPage(pageInfo);
		} catch (Exception e) {
			logger.error("列表展现错误：", e);
			this.errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "searchSignificantIssuedealWorkingRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchSignificantIssuedealWorkingRecords() throws Exception {
		if (searchSignificantIssuedealWorkingRecord == null
				|| searchSignificantIssuedealWorkingRecord.getDailyDirectory()
						.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String allDailyDirectoryId = createParams();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				significantIssuedealService
						.searchSignificantIssuedealWorkingRecord(
								searchSignificantIssuedealWorkingRecord, page,
								rows, sidx, sord, allDailyDirectoryId),
				organizationDubboService, new String[] { "investigationOrg" },
				organization.getId()));
		return SUCCESS;
	}

	private String createParams() {
		String str = "";
		if (null == organization || null == organization.getId()) {
			organization = ThreadVariable.getUser().getOrganization();
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		searchSignificantIssuedealWorkingRecord.setOrganization(organization);
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(searchSignificantIssuedealWorkingRecord
						.getDailyDirectory().getId());
		if (dailyDirectories.size() == 0) {
			str = "'"
					+ searchSignificantIssuedealWorkingRecord
							.getDailyDirectory().getId() + "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					str = "'" + dailyDirectories.get(i).getId() + "'";
				} else {
					str = str + ",'" + dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		return str;
	}

	private void createGridPage(PageInfo<SignificantIssuedeal> pageInfo) {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageInfo, organizationDubboService,
				new String[] { "investigationOrg" }, organization.getId()));
		autoSetAttachFilesBySignificantIssuedealList(pageInfo.getResult());
	}

	private void autoSetAttachFilesBySignificantIssuedealList(
			List<SignificantIssuedeal> significantIssuedealList) {
		if (null == significantIssuedealList
				|| significantIssuedealList.size() <= 0) {
			return;
		}
		for (SignificantIssuedeal obj : significantIssuedealList) {
			autoSetAttachFiles(obj);
		}
	}

	private void autoSetAttachFiles(SignificantIssuedeal significantIssuedeal) {
		significantIssuedeal
				.setSignificantIssuedealAttachFiles(disputeAttachFileService
						.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(significantIssuedeal
								.getId()));
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public SignificantIssuedeal getSignificantIssuedeal() {
		return significantIssuedeal;
	}

	public void setSignificantIssuedeal(
			SignificantIssuedeal significantIssuedeal) {
		this.significantIssuedeal = significantIssuedeal;
	}

	public String getSignificantIssuedealids() {
		return significantIssuedealids;
	}

	public void setSignificantIssuedealids(String significantIssuedealids) {
		this.significantIssuedealids = significantIssuedealids;
	}

	public boolean isDeleteSuccess() {
		return deleteSuccess;
	}

	public void setDeleteSuccess(boolean deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
	}

	public PlantFormMessageConfig getPlantFormMessageConfig() {
		return plantFormMessageConfig;
	}

	public void setPlantFormMessageConfig(
			PlantFormMessageConfig plantFormMessageConfig) {
		this.plantFormMessageConfig = plantFormMessageConfig;
	}

	public String getSubOrgIds() {
		return subOrgIds;
	}

	public void setSubOrgIds(String subOrgIds) {
		this.subOrgIds = subOrgIds;
	}

	public SignificantIssuedealAttachFiles getDisputeAttachFiles() {
		return disputeAttachFiles;
	}

	public void setDisputeAttachFiles(
			SignificantIssuedealAttachFiles disputeAttachFiles) {
		this.disputeAttachFiles = disputeAttachFiles;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public SignificantIssuedealVo getSearchSignificantIssuedealWorkingRecord() {
		return searchSignificantIssuedealWorkingRecord;
	}

	public void setSearchSignificantIssuedealWorkingRecord(
			SignificantIssuedealVo searchSignificantIssuedealWorkingRecord) {
		this.searchSignificantIssuedealWorkingRecord = searchSignificantIssuedealWorkingRecord;
	}

}
