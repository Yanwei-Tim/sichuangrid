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
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.taskList.constant.TaskConstant;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.domain.PropagandaAndVerification;
import com.tianque.plugin.taskList.domain.PropagandaAndVerificationVo;
import com.tianque.plugin.taskList.service.PropagandaAndVerificationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Scope("request")
@Namespace("/baseInfo/propagandaAndVerificationManage")
@Controller("propagandaAndVerificationController")
public class PropagandaAndVerificationController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropagandaAndVerificationService propagandaAndVerificationDangerService;
	@Autowired
	private PropertyDictService propertyDictService;

	private Long id;

	private Long orgid;

	private String ids;

	/**
	 * 判断当前用户是否可以签收
	 */
	private Boolean flag;

	private PropagandaAndVerification propagandaAndVerification;
	
	/** 人员记录集合  **/
	private List<PropagandaAndVerification> propagandaAndVerifications;

	private PropagandaAndVerificationVo propagandaAndVerificationVo;

	@Action(value = "searchPropagandaAndVerificationByVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchPropagandaAndVerificationByVo() throws Exception {
		if(getTqmobile()!=null){
			propagandaAndVerificationVo.setMode(getTqmobile());
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				propagandaAndVerificationDangerService
						.searchPropagandaAndVerification(
								propagandaAndVerificationVo, page, rows, sidx,
								sord), organizationDubboService,
				new String[] { "organization" }, null));

		return SUCCESS;
	}

	@Action(value = "addPropagandaAndVerification", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addPropagandaAndVerification() throws Exception {

		propagandaAndVerificationDangerService
				.addPropagandaAndVerification(propagandaAndVerification);
		return SUCCESS;
	}

	@Action(value = "updatePropagandaAndVerification", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updatePropagandaAndVerification() throws Exception {
		if (propagandaAndVerification == null
				|| propagandaAndVerification.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		propagandaAndVerificationDangerService
				.updatePropagandaAndVerification(propagandaAndVerification);
		return SUCCESS;
	}

	@Action(value = "deletePropagandaAndVerification", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deletePropagandaAndVerification() throws Exception {
		propagandaAndVerificationDangerService
				.deletePropagandaAndVerification(analyzeIds(ids));
		return SUCCESS;
	}

	@Action(value = "getPropagandaAndVerificationById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"propagandaAndVerification", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String getPropagandaAndVerificationById() throws Exception {
		propagandaAndVerification = propagandaAndVerificationDangerService
				.getPropagandaAndVerificationById(id);
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

	/**
	 * 签收
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatePropagandaAndVerificationSignDetail", results = {
			@Result(name = SUCCESS, type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = ERROR, type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updatePropagandaAndVerificationSignDetail() throws Exception {
		if (propagandaAndVerification == null
				|| propagandaAndVerification.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		propagandaAndVerificationDangerService
				.updatePropagandaAndVerificationSignDetail(propagandaAndVerification);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "dispathOperate", results = {
			@Result(name = "add", location = "/task/propagandaAndVerificationManage/mainPropagandaAndVerificationDialog.jsp"),
			@Result(name = "edit", location = "/task/propagandaAndVerificationManage/mainPropagandaAndVerificationDialog.jsp"),
			@Result(name = "view", location = "/task/propagandaAndVerificationManage/viewPropagandaAndVerificationDialog.jsp"),
			@Result(name = "sign", location = "/task/propagandaAndVerificationManage/signPropagandaAndVerificationDialog.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String maintainPropagandaAndVerification() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (propagandaAndVerification == null) {
				propagandaAndVerification = new PropagandaAndVerification();
			}
			propagandaAndVerification.setOccurrenceDate(CalendarUtil.now());
			propagandaAndVerification
					.setVerificationReport(TaskConstant.HANDLE_STATE);
			propagandaAndVerification.setPropaganda(TaskConstant.HANDLE_STATE);
			return mode;
		}

		// 新加之外的操作都需要id
		if (propagandaAndVerification == null
				|| propagandaAndVerification.getId() == null) {
			this.errorMessage = "参数错误，未取到id";
			return ERROR;
		}
		propagandaAndVerification = propagandaAndVerificationDangerService
				.getPropagandaAndVerificationById(propagandaAndVerification
						.getId());
		propagandaAndVerification.setOrganization(organizationDubboService
				.getFullOrgById(propagandaAndVerification.getOrganization()
						.getId()));
		if (DialogMode.DIALOGMODE_SIGN.equals(mode)) {
			// 签收的当前时间和用户
			propagandaAndVerification = propagandaAndVerificationDangerService
					.getPropagandaAndVerificationById(propagandaAndVerification
							.getId());
			propagandaAndVerification.setSignDate(CalendarUtil.now());
			propagandaAndVerification.setSignUserName(ThreadVariable.getUser()
					.getName());
		}
		return mode;
	}

	@Action(value = "getPropagandaAndVerificationIsSign", results = {
			@Result(type = "json", name = "success", params = { "root", "flag",
					"ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root", "false",
					"ignoreHierarchy", "false" }) })
	public String getPropagandaAndVerificationIsSign() throws Exception {
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
	
	
	@Action(value = "findPropagandaAndVerificationByName", results = {
			@Result(name = SUCCESS, type = "json", params = { "root", "propagandaAndVerifications",
					"ignoreHierarchy", "false" }),
			@Result(name = ERROR, type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findPropagandaAndVerificationByName() throws Exception {
		propagandaAndVerifications = propagandaAndVerificationDangerService.findPropagandaAndVerificationByName(propagandaAndVerificationVo);
		return SUCCESS;
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

	public PropagandaAndVerification getPropagandaAndVerification() {
		return propagandaAndVerification;
	}

	public void setPropagandaAndVerification(
			PropagandaAndVerification propagandaAndVerification) {
		this.propagandaAndVerification = propagandaAndVerification;
	}

	public PropagandaAndVerificationVo getPropagandaAndVerificationVo() {
		return propagandaAndVerificationVo;
	}

	public void setPropagandaAndVerificationVo(
			PropagandaAndVerificationVo propagandaAndVerificationVo) {
		this.propagandaAndVerificationVo = propagandaAndVerificationVo;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public List<PropagandaAndVerification> getPropagandaAndVerifications() {
		return propagandaAndVerifications;
	}

	public void setPropagandaAndVerifications(
			List<PropagandaAndVerification> propagandaAndVerifications) {
		this.propagandaAndVerifications = propagandaAndVerifications;
	}
	
	

}
