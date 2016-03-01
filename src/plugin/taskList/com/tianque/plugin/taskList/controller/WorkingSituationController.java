package com.tianque.plugin.taskList.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.tinygroup.commons.tools.StringUtil;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.domain.WorkingSituation;
import com.tianque.plugin.taskList.domain.WorkingSituationVo;
import com.tianque.plugin.taskList.service.WorkingSituationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Scope("request")
@Namespace("/baseInfo/workingSituationManage")
@Controller("workingSituationController")
public class WorkingSituationController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkingSituationService workingSituationService;
	@Autowired
	private PropertyDictService propertyDictService;

	private Long id;

	private Long orgid;

	private String ids;
	
	/**
	 * 判断当前用户是否可以签收
	 */
	private Boolean flag;

	private WorkingSituation workingSituation;

	private WorkingSituationVo workingSituationVo;

	@Action(value = "searchWorkingSituationByVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchWorkingSituationByVo() throws Exception {
		if(getTqmobile()!=null){
			workingSituationVo.setMode(getTqmobile());
		}
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(
						workingSituationService.searchWorkingSituation(
								workingSituationVo, page, rows, sidx, sord),
						organizationDubboService,
						new String[] { "organization" }, null));

		return SUCCESS;
	}

	@Action(value = "addWorkingSituation", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addWorkingSituation() throws Exception {

		workingSituationService.addWorkingSituation(workingSituation);
		return SUCCESS;
	}

	@Action(value = "updateWorkingSituationSignDetail", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateWorkingSituationSignDetail() throws Exception {

		workingSituationService
				.updateWorkingSituationSignDetail(workingSituation);
		return SUCCESS;
	}

	@Action(value = "updateWorkingSituation", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateWorkingSituationr() throws Exception {

		workingSituationService.updateWorkingSituation(workingSituation);
		return SUCCESS;
	}

	@Action(value = "deleteWorkingSituation", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteWorkingSituation() throws Exception {
		workingSituationService.deleteWorkingSituation(analyzeIds(ids));
		return SUCCESS;
	}

	@Action(value = "getWorkingSituationById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workingSituation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String getWorkingSituationById() throws Exception {
		if (workingSituation != null && workingSituation.getId() != null) {
			workingSituation = workingSituationService
					.getWorkingSituationById(workingSituation.getId());
		}
		return SUCCESS;
	}

	@Action(value = "updatePage", results = {
			@Result(name = "success", location = "/task/workingSituation/mainWorkingSituationDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updatePage() throws Exception {

		

		workingSituation = workingSituationService.getWorkingSituationById(id);
		workingSituation.setOrganization(organizationDubboService
				.getFullOrgById(workingSituation.getOrganization().getId()));
		workingSituation.setSignDate(CalendarUtil.now());
		workingSituation.setSignUserName(ThreadVariable.getUser().getName());

		return SUCCESS;
	}

	@Action(value = "searchPage", results = {
			@Result(name = "success", location = "/template/task/workingSituation/searchWorkingSituationDlg.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchPage() throws Exception {
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@Actions({ @Action(value = "viewWorkingSituation", results = { @Result(name = "success", location = "/template/task/workingSituation/workingSituationView.ftl") }) })
	@EncryptAnnotation
	public String viewWorkingSituation() throws Exception {
		if (workingSituation != null && workingSituation.getId() != null) {
			workingSituation = workingSituationService
					.getWorkingSituationById(workingSituation.getId());
			workingSituation.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							workingSituation.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "addPage", results = {
			@Result(name = "success", location = "/task/workingSituation/mainWorkingSituationForAdd.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPage() throws Exception {

		workingSituation.setOccurrenceDate(CalendarUtil.now());

		return SUCCESS;
	}

	@Action(value = "updatePageForAll", results = {
			@Result(name = "success", location = "/task/workingSituation/mainWorkingSituationForAdd.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updatePageForAll() throws Exception {
		if (id == null) {
			this.errorMessage = "参数错误，未取到id";
			return ERROR;
		}
		workingSituation = workingSituationService.getWorkingSituationById(id);
		workingSituation.setOrganization(organizationDubboService
				.getFullOrgById(workingSituation.getOrganization().getId()));

		return SUCCESS;
	}

	@Action(value = "getWorkingSituationIsSign", results = {
			@Result(type = "json", name = "success", params = { "root", "flag",
					"ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root", "false",
					"ignoreHierarchy", "false" }) })
	public String getWorkingSituationIsSign() throws Exception {
		flag = false;
		Organization currentUserOrg = ThreadVariable.getUser()
				.getOrganization();
		// 获取职能部门
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTION_KEY);
		// 获取职能部门类型（公安部门，司法部门。。）
		PropertyDict functionOrgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.FUNCTIONAL_ORG_TYPE,
						Constants.POLICE_DEPARTMENT);
		// 获取组织结构层级（乡镇，县区）
		PropertyDict orgLevelDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN_KEY);
		if (currentUserOrg.getOrgType().getId().equals(orgTypeDict.getId())
				&& currentUserOrg.getOrgLevel().getId().equals(orgLevelDict.getId())
				&& currentUserOrg.getFunctionalOrgType().getId().equals(functionOrgTypeDict
						.getId())) {
			flag = true;
		}
		return SUCCESS;
	}

	private List<Long> analyzeIds(String ids) {
		if (ids == null) {
			return null;
		}
		String[] deleteId = ids.split(",");
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public WorkingSituation getWorkingSituation() {
		return workingSituation;
	}

	public void setWorkingSituation(WorkingSituation workingSituation) {
		this.workingSituation = workingSituation;
	}

	public WorkingSituationVo getWorkingSituationVo() {
		return workingSituationVo;
	}

	public void setWorkingSituationVo(WorkingSituationVo workingSituationVo) {
		this.workingSituationVo = workingSituationVo;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}


}
