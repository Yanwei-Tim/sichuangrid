package com.tianque.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
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
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.TimeLimitHelper;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;
import com.tianque.working.service.SignificantIssuedealService;

@SuppressWarnings("serial")
@Controller("significantIssuedealWorkingRecordController")
@Scope("request")
public class SignificantIssuedealWorkingRecordController extends BaseAction {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;
	private List<SignificantIssuedeal> result;

	public List<SignificantIssuedeal> getResult() {
		return result;
	}

	public void setResult(List<SignificantIssuedeal> result) {
		this.result = result;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public SignificantIssuedealService getSignificantIssuedealService() {
		return significantIssuedealService;
	}

	public void setSignificantIssuedealService(
			SignificantIssuedealService significantIssuedealService) {
		this.significantIssuedealService = significantIssuedealService;
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

	@Autowired
	private SignificantIssuedealService significantIssuedealService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyLogAttachFileService dailyLogAttachFileService;
	@Autowired
	private PermissionService permissionService;

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
		return SUCCESS;
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

	public String findSignificantIssuedealsByInvestigationDate()
			throws Exception {

		if (significantIssuedealVo.getOrganization() == null
				|| significantIssuedealVo.getOrganization().getId() == null
				|| significantIssuedealVo.getDailyDirectory() == null
				|| significantIssuedealVo.getDailyDirectory().getId() == null) {
			gridPage = new GridPage(new PageInfo<SignificantIssuedeal>());
			return SUCCESS;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				significantIssuedealService
						.getSignificantIssuedealsByInvestigationDate(
								significantIssuedealVo, page, rows, "", ""),
				organizationDubboService, new String[] { "investigationOrg" },
				organization.getId()));
		return SUCCESS;
	}

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
		return SUCCESS;
	}

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
			if ("".equals(ids[i]))
				continue;

			significantIssuedealService.deleteSignificantIssuedealById(Long
					.valueOf(ids[i]));
		}
		deleteSuccess = true;
		return SUCCESS;
	}

	private boolean vidateMessage() {
		boolean bool = true;
		if (plantFormMessageConfig == null
				|| !StringUtil.isStringAvaliable(plantFormMessageConfig
						.getMsgTitle())
				|| !StringUtil.isStringAvaliable(plantFormMessageConfig
						.getMsgContent())) {
			bool = false;
		}
		return bool;
	}

	public String backSignificantIssuedeal() throws Exception {
		String[] ids = significantIssuedealids.split(",");
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
		for (int i = 0; i < ids.length; i++) {
			significantIssuedeal = new SignificantIssuedeal();
			significantIssuedeal.setId(Long.valueOf(ids[i]));
			if (significantIssuedeal == null
					|| significantIssuedeal.getId() == null
					|| significantIssuedeal.getId().longValue() == 0L
					|| !vidateMessage()) {
				this.errorMessage = "参数不正确";
				return ERROR;
			}
			deleteSuccess = significantIssuedealService
					.backSignificantIssuedeal(significantIssuedeal.getId());
		}
		List<User> users = permissionService.findChildUsersByEnameAndOrgCode(
				"addDailyLog", subOrgIds);
		addWarnMessageByUsers(users, plantFormMessageConfig);
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	private void addWarnMessageByUsers(List<User> users,
			PlantFormMessageConfig plantFormMessageConfig) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			String warnTitle = plantFormMessageConfig.getMsgTitle();
			String warnMessage = plantFormMessageConfig.getMsgContent();
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					warnMessage, warnTitle);
		}
	}

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

	public String significantIssuedealWorkingRecordList() throws Exception {
		if (organization == null || organization.getId() == null
				|| dailyDirectory == null || dailyDirectory.getId() == null) {
			gridPage = new GridPage(new PageInfo<SignificantIssuedeal>());
			return SUCCESS;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				significantIssuedealService
						.findSignificantIssuedealsForPageByOrgIdAndDirectoryId(
								organization.getId(), dailyDirectory.getId(),
								page, rows, sidx, sord),
				organizationDubboService, new String[] { "investigationOrg" },
				organization.getId()));
		return SUCCESS;
	}

	// 上报
	public String reportedSignificantIssuedealById() throws Exception {
		String[] ids = significantIssuedealids.split(",");
		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i]))
				continue;
			significantIssuedeal = new SignificantIssuedeal();
			significantIssuedeal.setId(Long.valueOf(ids[i]));
			if (significantIssuedeal == null
					|| significantIssuedeal.getId() == null
					|| significantIssuedeal.getId().longValue() == 0L) {
				errorMessage = "参数错误";
				return ERROR;
			}

			significantIssuedeal = validateWhenAdd(significantIssuedeal);// 上报时是否超期

			significantIssuedeal = significantIssuedealService
					.reportedSignificantIssuedealById(
							significantIssuedeal.getId(), ThreadVariable
									.getUser().getOrganization().getId(),
							significantIssuedeal.getExpiredEntering());
			significantIssuedeal.setInvestigationOrg(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							significantIssuedeal.getInvestigationOrg(),
							organizationDubboService));
		}

		return SUCCESS;
	}

	private SignificantIssuedeal validateWhenAdd(
			SignificantIssuedeal significantIssuedeal) {
		if (significantIssuedeal != null
				&& significantIssuedeal.getDailyDirectory() != null
				&& significantIssuedeal.getDailyDirectory().getId() != null) {
			DailyDirectory dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(significantIssuedeal
							.getDailyDirectory().getId());

			Date endDate = TimeLimitHelper.getEndDate(dailyDirectory);
			Date dealDate = CalendarUtil.now("yyyy-MM-dd HH:mm:ss");// significantIssuedeal.getReportedDate();
			if (dealDate != null && endDate != null) {
				if (dealDate.after(endDate)) {
					significantIssuedeal.setExpiredEntering(1L);// 1超期录入
				} else {
					significantIssuedeal.setExpiredEntering(0L);
				}
			}
		}
		return significantIssuedeal;
	}

	public String findSingificantIssuesealsForPrint() throws Exception {
		String[] ids = significantIssuedealids.split(",");
		result = significantIssuedealService.findSignificantIssuedealsForPrint(
				organization.getId(), ids);
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		return SUCCESS;
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

}
