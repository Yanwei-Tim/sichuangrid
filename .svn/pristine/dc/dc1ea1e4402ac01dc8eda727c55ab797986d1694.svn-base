package com.tianque.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PlantFormMessageConfig;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Report;
import com.tianque.domain.ReportLevel;
import com.tianque.domain.ReportState;
import com.tianque.domain.ReportType;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.vo.PenalArrestVo;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.PenalArrestService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@SuppressWarnings("serial")
@Controller("penalArrestController")
@Scope("prototype")
@Transactional
public class PenalArrestController extends BaseAction {
	@Autowired
	private PenalArrestService penalArrestService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	private Organization organization;
	private PenalArrestVo penalArrestVo;
	private PlantFormMessageConfig plantFormMessageConfig;

	private String opType;
	private String orgids;

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddReport();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return prepareViewReportDetail();
		}
		return SUCCESS;
	}

	private String prepareViewReportDetail() throws Exception {
		if (penalArrestVo.getReportState().equals(ReportState.CREATE)) {
			penalArrestVo.setReportType(ReportType.PENAL_ARREST);
			penalArrestVo = penalArrestService.findPenalArrestVo(penalArrestVo);
		} else {
			penalArrestVo.setReportType(ReportType.PENAL_ARREST);
			penalArrestVo = penalArrestService
					.findPenalArrestVoById(penalArrestVo);
		}
		return "forwardview";
	}

	private String prepareAddReport() {
		if (penalArrestVo == null) {
			penalArrestVo = new PenalArrestVo();
		}
		penalArrestVo.setReportType(ReportType.PENAL_ARREST);
		penalArrestVo.setReportLevel(ReportLevel.YEAR);
		penalArrestVo.setReportState(ReportState.CREATE);
		return SUCCESS;
	}

	public String addpenalArrestVo() throws Exception {
		if (penalArrestVo == null) {
			return ERROR;
		}
		penalArrestVo.setReportLevel(ReportLevel.YEAR);
		penalArrestVo.setReportState(ReportState.CREATE);
		penalArrestVo.setReportType(ReportType.PENAL_ARREST);
		penalArrestVo.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		penalArrestVo.setOrgInternalCode(ThreadVariable.getUser()
				.getOrgInternalCode());
		organization = organizationDubboService.getSimpleOrgById(penalArrestVo
				.getOrganization().getId());
		PropertyDict orgLevelDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		if (penalArrestVo.getCreateDate() != null) {
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			SimpleDateFormat month = new SimpleDateFormat("MM");
			penalArrestVo.setYear(year.format(penalArrestVo.getCreateDate()));
			penalArrestVo.setMonth(month.format(penalArrestVo.getCreateDate()));
		}
		if (orgLevelDict.getInternalId() != OrganizationLevel.VILLAGE
				&& orgLevelDict.getInternalId() != OrganizationLevel.GRID) {
			if (countPenalArrest(penalArrestVo)) {
				this.errorMessage = "还有辖区单位没有上报，具体单位请参考辖区上报情况！";
				return ERROR;
			}
		}
		penalArrestVo = penalArrestService.addPenalArrestVo(penalArrestVo);
		return SUCCESS;
	}

	public boolean countPenalArrest(PenalArrestVo penalArrestVo)
			throws Exception {
		List<Organization> list = organizationDubboService
				.findAdminOrgsByParentId(penalArrestVo.getOrganization()
						.getId());
		for (int i = 0; i < list.size(); i++) {
			int num = penalArrestService.countPenalArrest(
					penalArrestVo.getYear(), penalArrestVo.getMonth(),
					ReportType.PENAL_ARREST.toString(), list.get(i).getId());
			if (num == 0) {
				return true;
			}
		}
		return false;
	}

	public String deletepenalArrestVo() throws Exception {
		if (penalArrestVo == null) {
			return ERROR;
		}
		penalArrestService.deleteReportById(penalArrestVo.getId());
		return SUCCESS;
	}

	public String savepenalArrestVo() throws Exception {
		if (penalArrestVo.getReportState().equals(ReportState.SUBMIT)) {
			Date now = new Date();
			penalArrestVo.setReportDate(now);
		}
		penalArrestService.savePenalArrestVo(penalArrestVo);
		return SUCCESS;
	}

	public String addReportWarmMessage() throws Exception {
		String[] subInfos = orgids.split(",");
		if (subInfos.length == 0)
			return ERROR;
		List<User> users = permissionService.findChildUsersByEnameAndOrgCode(
				"addReport", orgids);
		addWarnMessageByUsers(users);
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	private void addWarnMessageByUsers(List<User> users) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			String warnTitle = "检察机关批捕起诉刑事案件情况报表编制息提醒";
			String warnMessage = "您还没有编制完成检察机关批捕起诉刑事案件情况，请您及时完成相关检察机关批捕起诉刑事案件情况报表工作";
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					warnMessage, warnTitle);
		}
	}

	public String reportBack() throws Exception {
		if (penalArrestVo == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (plantFormMessageConfig == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (StringUtils.isBlank(plantFormMessageConfig.getMsgTitle())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		if (StringUtils.isBlank(plantFormMessageConfig.getMsgContent())) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		penalArrestService.updatePenalArrestVoSateById(penalArrestVo);
		List<User> users = permissionService
				.findChildUsersByEnameAndInternalCode("addReport",
						penalArrestVo.getOrgInternalCode());
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			platformaMessageService.sendPlatformMessageToUser(user.getId(),
					plantFormMessageConfig.getMsgContent(),
					plantFormMessageConfig.getMsgTitle());
		}
		return SUCCESS;
	}

	public String reportCpxResultList() throws Exception {
		penalArrestVo.setReportType(ReportType.PENAL_ARREST);
		PageInfo<Report> pageInfo = penalArrestService
				.findreportsByOrgCodeAndyear(penalArrestVo, page, rows, sidx,
						sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String reportUnderLineList() throws Exception {
		penalArrestVo.setReportType(ReportType.PENAL_ARREST);
		PageInfo<Report> pageInfo = penalArrestService
				.findUnderLinePenalArrestVosByOrgCodeAndyear(penalArrestVo,
						page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOrgids() {
		return orgids;
	}

	public void setOrgids(String orgids) {
		this.orgids = orgids;
	}

	public PlantFormMessageConfig getPlantFormMessageConfig() {
		return plantFormMessageConfig;
	}

	public void setPlantFormMessageConfig(
			PlantFormMessageConfig plantFormMessageConfig) {
		this.plantFormMessageConfig = plantFormMessageConfig;
	}

	public PenalArrestVo getPenalArrestVo() {
		return penalArrestVo;
	}

	public void setPenalArrestVo(PenalArrestVo penalArrestVo) {
		this.penalArrestVo = penalArrestVo;
	}

}
