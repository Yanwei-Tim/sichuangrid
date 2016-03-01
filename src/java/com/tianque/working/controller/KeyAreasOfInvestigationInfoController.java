package com.tianque.working.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
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
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.DetailedRuleDailyLogRela;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.state.KeyAreasOfInvestigationInfoState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.KeyAreasOfInvestigationInfoService;

/***
 * 治安重点排查情况
 */
@SuppressWarnings("serial")
@Controller("keyAreasOfInvestigationInfoController")
@Namespace("/daily/keyAreasOfInvestigationInfoManage")
@Scope("prototype")
@Transactional
public class KeyAreasOfInvestigationInfoController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(KeyAreasOfInvestigationInfoController.class);

	@Autowired
	private KeyAreasOfInvestigationInfoService keyAreasOfInvestigationInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PlatformMessageService platformaMessageService;

	private DailyDirectory dailyDirectory;
	private Organization organization;
	private KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo;
	private KeyAreasOfInvestigationInfoVo keyAreasOfInvestigationInfoVo;
	private boolean deleteSuccess;
	private PlatformMessage platformMessage;
	private boolean judgeSuccess;
	private String keyAreasOfInvestigationInfoids;
	private String subOrgIds;
	private String modeType;

	private Date dealFrom;
	private Date dealTo;
	private String returnString;
	private List<KeyAreasOfInvestigationInfo> result;
	private boolean bol = false;

	@Action(value = "findKeyAreasOfInvestigationInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findKeyAreasOfInvestigationInfos() throws Exception {
		if (dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<KeyAreasOfInvestigationInfo>());
			return SUCCESS;
		}
		if (null == organization || null == organization.getId()) {
			organization = ThreadVariable.getUser().getOrganization();
		}
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(
						keyAreasOfInvestigationInfoService
								.findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryId(
										organization.getId(),
										dailyDirectory.getId(), page, rows,
										sidx, sord), organizationDubboService,
						new String[] { "investigationOrg" }, organization
								.getId()));
		return SUCCESS;
	}

	@Action(value = "findKeyAreasOfInvestigationInfosByDirectoryParentId", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findKeyAreasOfInvestigationInfosByDirectoryParentId()
			throws Exception {
		if (organization == null || organization.getId() == null
				|| dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<KeyAreasOfInvestigationInfo>());
			return SUCCESS;
		}

		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				keyAreasOfInvestigationInfoService
						.getKeyAreasOfInvestigationInfosByInvestigationDate(
								keyAreasOfInvestigationInfoVo, page, rows, "",
								""), organizationDubboService,
				new String[] { "investigationOrg" }, organization.getId()));

		return SUCCESS;
	}

	@PermissionFilter(ename = "addWorkingRecord")
	@Action(value = "addKeyAreasOfInvestigationInfo", results = {
			@Result(type = "json", name = "success", params = { "root",
					"keyAreasOfInvestigationInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addKeyAreasOfInvestigationInfo() throws Exception {
		if (!vidateInput() || this.dailyDirectory == null
				|| this.dailyDirectory.getId() == null) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		keyAreasOfInvestigationInfo.setDailyDirectory(dailyDirectory);
		keyAreasOfInvestigationInfo.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoService
				.addKeyAreasOfInvestigationInfo(keyAreasOfInvestigationInfo);
		keyAreasOfInvestigationInfo.setInvestigationOrg(ControllerHelper
				.proccessRelativeOrgNameByOrg(
						keyAreasOfInvestigationInfo.getInvestigationOrg(),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/statementsReported/keyAreasOfInvestigationInfo/maintainKeyAreasOfInvestigationInfoDlg.jsp"),
			@Result(name = "back", location = "/daily/statementsReported/keyAreasOfInvestigationInfo/backKeyAreasOfInvestigationInfoBackDlg.jsp"),
			@Result(name = "search", location = "/daily/statementsReported/keyAreasOfInvestigationInfo/filterKeyAreasOfInverstigationInfo.jsp"),
			@Result(name = "print", location = "/daily/statementsReported/keyAreasOfInvestigationInfo/keyAreasOfInvestigationInfoDlgPrint.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			if (this.dailyDirectory == null
					|| this.dailyDirectory.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			keyAreasOfInvestigationInfo = new KeyAreasOfInvestigationInfo();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (keyAreasOfInvestigationInfo == null
					|| keyAreasOfInvestigationInfo.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoService
					.getKeyAreasOfInvestigationInfoById(keyAreasOfInvestigationInfo
							.getId());
		} else if ("back".equalsIgnoreCase(mode)) {
			if ("".equals(keyAreasOfInvestigationInfoids)
					|| "".equals(subOrgIds)) {
				errorMessage = "参数错误";
				return ERROR;
			}
			return "back";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			if (keyAreasOfInvestigationInfo == null
					|| keyAreasOfInvestigationInfo.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoService
					.getKeyAreasOfInvestigationInfoById(keyAreasOfInvestigationInfo
							.getId());
			return "print";
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteWorkingRecord")
	@Action(value = "deleteKeyAreasOfInvestigationInfoById", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteKeyAreasOfInvestigationInfoById() throws Exception {
		List<DetailedRuleDailyLogRela> judgeList = new ArrayList<DetailedRuleDailyLogRela>();
		judgeList = detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordIds(
						Integer.valueOf(StatementsReportedType.INVESTIGATION)
								.longValue(), keyAreasOfInvestigationInfoids);

		if (!judgeList.isEmpty()) {
			this.errorMessage = "操作失败，请联系管理员";
			return ERROR;
		}

		String[] ids = keyAreasOfInvestigationInfoids.split(",");

		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i]))
				continue;
			keyAreasOfInvestigationInfoService
					.deleteKeyAreasOfInvestigationInfoById(Long.valueOf(ids[i]));
		}
		bol = true;
		return SUCCESS;
	}

	/***
	 * 上报
	 */
	@PermissionFilter(ename = "upWorkingRecord")
	@Action(value = "reportedKeyAreasOfInvestigationInfoById", results = {
			@Result(type = "json", name = "success", params = { "root",
					"keyAreasOfInvestigationInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String reportedKeyAreasOfInvestigationInfoById() throws Exception {

		if (!StringUtil.isStringAvaliable(keyAreasOfInvestigationInfoids)) {

			errorMessage = "参数错误";

			return ERROR;
		}

		Long[] ids = analyze(keyAreasOfInvestigationInfoids);

		for (Long id : ids) {

			if (KeyAreasOfInvestigationInfoState.REPORTED
					.equals(keyAreasOfInvestigationInfoService
							.getKeyAreasOfInvestigationInfoById(id).getStatus())) {
				errorMessage = "已经上报不能再次上报";
				return ERROR;
			}
			keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoService
					.reportedKeyAreasOfInvestigationInfoById(id, ThreadVariable
							.getUser().getOrganization().getId(),
							keyAreasOfInvestigationInfo.getExpiredEntering());

			keyAreasOfInvestigationInfo.setInvestigationOrg(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							keyAreasOfInvestigationInfo.getInvestigationOrg(),
							organizationDubboService));
		}

		sendPlatformMessageToOrg(ids);

		return SUCCESS;
	}

	private void sendPlatformMessageToOrg(Long[] ids) {
		PlatformMessage pm = platformMessageFactory
				.createUnStatedReportsSubmitPlatformMessage(ids.length,
						StatementsReportedType.SIGNIFICANT_ISSUEDEAL);

		Organization org = organizationDubboService.getSimpleOrgById(ThreadVariable
				.getOrganization().getId());

		platformaMessageService.sendPlatformMessageToOrg(org.getParentOrg()
				.getId(), pm);
	}

	/***
	 * 回退
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "backWorkingRecord")
	@Action(value = "backKeyAreasOfInvestigationInfo", results = {
			@Result(type = "json", name = "success", params = { "root", "bol",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String backKeyAreasOfInvestigationInfo() throws Exception {

		if (!StringUtil.isStringAvaliable(keyAreasOfInvestigationInfoids)
				|| !vidateMessage()) {

			errorMessage = "参数错误";

			return ERROR;
		}

		List<DetailedRuleDailyLogRela> judgeList = new ArrayList<DetailedRuleDailyLogRela>();

		judgeList = detailedRuleDailyLogRelaService
				.getDetailedRuleDailyLogRelaByWorkingRecordIds(
						Integer.valueOf(StatementsReportedType.INVESTIGATION)
								.longValue(), keyAreasOfInvestigationInfoids);

		if (!judgeList.isEmpty()) {
			this.errorMessage = "已经被考核评估引用，不能回退";
			return ERROR;
		}

		Long[] ids = analyze(keyAreasOfInvestigationInfoids);

		for (Long id : ids) {

			deleteSuccess = keyAreasOfInvestigationInfoService
					.backKeyAreasOfInvestigationInfo(id);
		}

		sendPlatformMessageToUser(ids);

		bol = true;

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

	private boolean vidateMessage() {
		boolean bool = true;

		if (platformMessage == null
				|| !StringUtil.isStringAvaliable(platformMessage.getTitle())
				|| !StringUtil.isStringAvaliable(platformMessage.getContent())) {

			bool = false;
		}
		return bool;
	}

	@PermissionFilter(ename = "updateWorkingRecord")
	@Action(value = "editKeyAreasOfInvestigationInfo", results = {
			@Result(type = "json", name = "success", params = { "root",
					"keyAreasOfInvestigationInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateKeyAreasOfInvestigationInfo() throws Exception {
		if (!vidateInput() || keyAreasOfInvestigationInfo == null
				|| keyAreasOfInvestigationInfo.getId() == null
				|| keyAreasOfInvestigationInfo.getId().longValue() == 0L) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoService
				.updateKeyAreasOfInvestigationInfo(keyAreasOfInvestigationInfo);
		keyAreasOfInvestigationInfo.setInvestigationOrg(ControllerHelper
				.proccessRelativeOrgNameByOrg(
						keyAreasOfInvestigationInfo.getInvestigationOrg(),
						organizationDubboService));

		return SUCCESS;
	}

	@Action(value = "checkedIsReport", results = {
			@Result(type = "json", name = "success", params = {
					"includeProperties", "deleteSuccess,judgeSuccess",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String checkedIsReport() throws Exception {
		if (keyAreasOfInvestigationInfo == null
				|| keyAreasOfInvestigationInfo.getId() == null
				|| keyAreasOfInvestigationInfo.getId().longValue() == 0L) {
			this.errorMessage = "参数不正确";
		}
		keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoService
				.getKeyAreasOfInvestigationInfoById(keyAreasOfInvestigationInfo
						.getId());
		if (keyAreasOfInvestigationInfo.getInvestigationOrg() != null
				&& keyAreasOfInvestigationInfo.getInvestigationOrg().getId() != null) {
			if (keyAreasOfInvestigationInfo
					.getInvestigationOrg()
					.getId()
					.equals(keyAreasOfInvestigationInfo.getOrganization()
							.getId())) {
				judgeSuccess = false;
			} else {
				judgeSuccess = true;
			}
		}

		if (KeyAreasOfInvestigationInfoState.REPORTED
				.equals(keyAreasOfInvestigationInfo.getStatus())) {
			this.deleteSuccess = true;
		} else {
			this.deleteSuccess = false;
		}

		return SUCCESS;
	}

	@Action(value = "findKeyAreasOfInvestigationInfosForPrint", results = {
			@Result(name = "success", location = "/daily/statementsReported/keyAreasOfInvestigationInfo/printList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findKeyAreasOfInvestigationInfosForPrint() throws Exception {
		String[] ids = keyAreasOfInvestigationInfoids.split(",");
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		result = keyAreasOfInvestigationInfoService
				.findKeyAreasOfInvestigationInfosForPrint(organization.getId(),
						ids);
		return SUCCESS;
	}

	@Action(value = "searchKeyAreasOfInvestigationInfoWorkingRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchKeyAreasOfInvestigationInfoWorkingRecords()
			throws Exception {
		String allDailyDirectoryId = "";
		if (keyAreasOfInvestigationInfoVo == null
				|| keyAreasOfInvestigationInfoVo.getDailyDirectory().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (null == organization || null == organization.getId()) {
			gridPage = new GridPage(new PageInfo());
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		keyAreasOfInvestigationInfoVo.setOrganization(organization);
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(keyAreasOfInvestigationInfoVo
						.getDailyDirectory().getId());
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'"
					+ keyAreasOfInvestigationInfoVo.getDailyDirectory().getId()
					+ "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId()
							+ "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				keyAreasOfInvestigationInfoService
						.searchKeyAreasOfInvestigationInfoWorkingRecord(
								keyAreasOfInvestigationInfoVo, page, rows,
								sidx, sord, allDailyDirectoryId),
				organizationDubboService, new String[] { "investigationOrg" },
				organization.getId()));
		return SUCCESS;
	}

	private boolean vidateInput() {
		boolean bool = true;
		if (keyAreasOfInvestigationInfo.getInvestigationDate() == null
				|| keyAreasOfInvestigationInfo.getMainProblem() == null
				|| keyAreasOfInvestigationInfo.getPoliciesAndMeasures() == null
				|| keyAreasOfInvestigationInfo.getAreaName() == null) {
			bool = false;
		}
		return bool;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public KeyAreasOfInvestigationInfo getKeyAreasOfInvestigationInfo() {
		return keyAreasOfInvestigationInfo;
	}

	public void setKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo) {
		this.keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfo;
	}

	public boolean isDeleteSuccess() {
		return deleteSuccess;
	}

	public void setDeleteSuccess(boolean deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public PlatformMessage getPlatformMessage() {
		return platformMessage;
	}

	public void setPlatformMessage(PlatformMessage platformMessage) {
		this.platformMessage = platformMessage;
	}

	public boolean isJudgeSuccess() {
		return judgeSuccess;
	}

	public void setJudgeSuccess(boolean judgeSuccess) {
		this.judgeSuccess = judgeSuccess;
	}

	public String getKeyAreasOfInvestigationInfoids() {
		return keyAreasOfInvestigationInfoids;
	}

	public void setKeyAreasOfInvestigationInfoids(
			String keyAreasOfInvestigationInfoids) {
		this.keyAreasOfInvestigationInfoids = keyAreasOfInvestigationInfoids;
	}

	public String getSubOrgIds() {
		return subOrgIds;
	}

	public void setSubOrgIds(String subOrgIds) {
		this.subOrgIds = subOrgIds;
	}

	public KeyAreasOfInvestigationInfoVo getKeyAreasOfInvestigationInfoVo() {
		return keyAreasOfInvestigationInfoVo;
	}

	public void setKeyAreasOfInvestigationInfoVo(
			KeyAreasOfInvestigationInfoVo keyAreasOfInvestigationInfoVo) {
		this.keyAreasOfInvestigationInfoVo = keyAreasOfInvestigationInfoVo;
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

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public List<KeyAreasOfInvestigationInfo> getResult() {
		return result;
	}

	public void setResult(List<KeyAreasOfInvestigationInfo> result) {
		this.result = result;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

}
