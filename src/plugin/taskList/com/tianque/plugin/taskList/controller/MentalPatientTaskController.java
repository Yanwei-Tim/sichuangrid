package com.tianque.plugin.taskList.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.plugin.taskList.constant.TaskConstant;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.tinygroup.commons.tools.StringUtil;

import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.service.MentalPatientService;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.plugin.taskList.domain.MentalPatientTask;
import com.tianque.plugin.taskList.service.MentalPatientTaskService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/baseInfo/mentalPatientTaskManage")
@Controller("mentalPatientTaskController")
public class MentalPatientTaskController extends BaseAction {
	private Long orgId;
	private String ids;
	private MentalPatientTask mentalPatientTask;
	private Long mentalPatientTaskId;
	@Autowired
	private MentalPatientTaskService mentalPatientTaskService;
	@Autowired
	private MentalPatientService mentalPatientService;
	@Autowired
	private OrganizationDubboService organizationService;
	private String signType;
	private Date currentTime;
	/**
	 * 搜索关键字
	 */
	private String fastSearchKeyWords;
	private MentalPatient population;
	/***
	 * 人口信息精神病人员id
	 */
	private Long mentalPatientInfoId;

	private String addFlag;
	// 只查有异常的
	private Boolean onlyHasException;

	@Action(value = "getMentalPatientTaskList", results = {
			@Result(name = "success", type = "json", params = {"root", "gridPage"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage"})})
	public String getMentalPatientTaskList() throws Exception {
		if (mentalPatientTask == null) {
			mentalPatientTask = new MentalPatientTask();
		}
		if (onlyHasException != null && onlyHasException) {
			Organization org = organizationService.getFullOrgById(orgId);
			if (org != null && org.getFullOrgName().contains(TaskConstant.GUANG_AN)) {
				mentalPatientTask.setHasException(1);
			}
		}
		mentalPatientTask.setOrganization(organizationService.getSimpleOrgById(orgId));
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(mentalPatientTaskService
						.getMentalPatientTaskList(mentalPatientTask, page, rows, sidx, sord),
				organizationService, new String[]{"organization"}, orgId));
		return SUCCESS;
	}

	@Action(value = "getInterViewMentalPatientList", results = {
			@Result(name = "success", type = "json", params = {"root", "gridPage"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage"})})
	public String getInterViewMentalPatientList() throws Exception {
		if (mentalPatientTask == null) {
			mentalPatientTask = new MentalPatientTask();
		}

//		mentalPatientTask.setOrganization(organizationService.getSimpleOrgById(orgId));
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(mentalPatientTaskService
						.getMentalPatientTaskList(mentalPatientTask, page, rows, sidx, sord),
				organizationService, new String[]{"organization"}, orgId));
		return SUCCESS;
	}


	/**
	 * 中转站 liu
	 */
	@Action(value = "dispatch", results = {
			@Result(name = "add", location = "/template/task/addMentalPatientTask.ftl"),
			@Result(name = "sign", location = "/template/task/mentalPatientTaskInfoDlg.ftl"),
			@Result(name = "search", location = "/template/task/mentalPatient/mentalPatientSearch.ftl")})
	public String dispatch() {
		if ("add".equals(mode)) {
			if (orgId == null) {
				errorMessage = "数据新增失败，未获得正确组织机构信息";
				return ERROR;
			}
			if (mentalPatientInfoId != null) {
				population = mentalPatientService.getMentalPatientById(mentalPatientInfoId);
			}
			return "add";
		}
		if ("search".equals(mode)) {
			return "search";
		}
		if ("sign".equals(mode)) {
			currentTime = CalendarUtil.now();
			mentalPatientTask = mentalPatientTaskService
					.getMentalPatientTaskById(mentalPatientTaskId);
			mentalPatientTask.setSignType(signType);
			mentalPatientTask.setCurrentDate(currentTime);
			return "sign";
		}
		if ("update".equals(mode)) {
			mentalPatientTask = mentalPatientTaskService
					.getMentalPatientTaskById(mentalPatientTaskId);
			return "update";
		}
		return SUCCESS;
	}

	@Action(value = "addMentalPatientTask", results = {
			@Result(name = "success", type = "json", params = {"root", "mentalPatientTask",
					"ignoreHierarchy", "false", "excludeNullProperties", "true"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage"})})
	public String addMentalPatientTask() throws Exception {
		if (mentalPatientTask.getMentalPatientId()!=null&&mentalPatientTask.getHasException()==0) {
			mentalPatientTask.setStatusJustice(1L);
			mentalPatientTask.setStatusPolice(1L);
			mentalPatientTask.setSignDateJustice(new Date());
			mentalPatientTask.setSignDatePolice(new Date());
			mentalPatientTask.setSignMemberNameJustice("系统管理员");
			mentalPatientTask.setSignMemberNamePolice("系统管理员");
		}else{
			mentalPatientTask.setStatusJustice(0L);
			mentalPatientTask.setStatusPolice(0L);
		}
		mentalPatientTask = mentalPatientTaskService.addMentalPatientTask(mentalPatientTask);
		return SUCCESS;
	}

	@Action(value = "updateMentalPatientTask", results = {
			@Result(name = "success", type = "json", params = {"root", "mentalPatientTask",
					"ignoreHierarchy", "false", "excludeNullProperties", "true"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage"})})
	public String updateMentalPatientTask() throws Exception {
		mentalPatientTask = mentalPatientTaskService.updateMentalPatientTask(mentalPatientTask);
		return SUCCESS;
	}

	@Action(value = "viewMentalPatientTask", results = {@Result(name = "success", location = "/template/task/mentalPatient/mentalPatientTaskView.ftl")})
	public String viewMentalPatientTask() throws Exception {
		mentalPatientTask = mentalPatientTaskService.getMentalPatientTaskById(id);
		return SUCCESS;
	}

	@Action(value = "viewInterViewMentalPatient", results = {@Result(name = "success", location = "/template/task/mentalPatient/InterViewMentalPatientView.ftl")})
	public String viewInterViewMentalPatient() throws Exception {
		mentalPatientTask = mentalPatientTaskService.getMentalPatientTaskById(id);
		return SUCCESS;
	}

	@Action(value = "deleteMentalPatientTask", results = {
			@Result(name = "success", type = "json", params = {"root", "true",
					"excludeNullProperties", "true"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage"})})
	public String deleteMentalPatientTask() throws Exception {
		mentalPatientTaskService.deleteMentalPatientTaskByIds(analyzeIds(ids));
		return SUCCESS;
	}

	@Action(value = "searchMentalPatient", results = {
			@Result(name = "success", type = "json", params = {"root", "gridPage",
					"ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = {"root", "errorMessage",
					"ignoreHierarchy", "false"})})
	public String searchMentalPatient() throws Exception {
		if (onlyHasException != null && onlyHasException) {
			if (mentalPatientTask.getOrganization() != null) {
				Organization org = organizationService.getFullOrgById(mentalPatientTask.getOrganization().getId());
				if (org != null && org.getFullOrgName().contains(TaskConstant.GUANG_AN)) {
					mentalPatientTask.setHasException(1);
				}
			}
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(mentalPatientTaskService
						.searchMentalPatient(mentalPatientTask, page, rows, sidx, sord),
				organizationService, new String[]{"organization"}, null));

		return SUCCESS;
	}

	private List<Long> analyzeIds(String idStr) {
		if (idStr == null) {
			return null;
		}
		String[] deleteId = idStr.split(",");
		List<Long> idList = new ArrayList<Long>();
		if (StringUtil.isEmpty(deleteId[0])) {
			return null;
		} else {
			for (int i = 0; i < deleteId.length; i++) {
				idList.add(Long.parseLong(deleteId[i]));
			}
		}
		return idList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public MentalPatientTask getMentalPatientTask() {
		return mentalPatientTask;
	}

	public void setMentalPatientTask(MentalPatientTask mentalPatientTask) {
		this.mentalPatientTask = mentalPatientTask;
	}

	public Long getMentalPatientTaskId() {
		return mentalPatientTaskId;
	}

	public void setMentalPatientTaskId(Long mentalPatientTaskId) {
		this.mentalPatientTaskId = mentalPatientTaskId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public MentalPatientTaskService getMentalPatientTaskService() {
		return mentalPatientTaskService;
	}

	public void setMentalPatientTaskService(
			MentalPatientTaskService mentalPatientTaskService) {
		this.mentalPatientTaskService = mentalPatientTaskService;
	}

	public MentalPatient getPopulation() {
		return population;
	}

	public void setPopulation(MentalPatient population) {
		this.population = population;
	}

	public Long getMentalPatientInfoId() {
		return mentalPatientInfoId;
	}

	public void setMentalPatientInfoId(Long mentalPatientInfoId) {
		this.mentalPatientInfoId = mentalPatientInfoId;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public Boolean getOnlyHasException() {
		return onlyHasException;
	}

	public void setOnlyHasException(Boolean onlyHasException) {
		this.onlyHasException = onlyHasException;
	}
}
