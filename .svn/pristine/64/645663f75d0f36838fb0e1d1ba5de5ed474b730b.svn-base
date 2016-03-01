package com.tianque.plugin.serviceTeam.router;

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

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.service.ServiceGuardersWithObjectService;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.vo.ServiceGuardersWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.service.ServiceMemberWithObjectService;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMembersWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.domain.ServiceTeamGuarders;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.service.ServiceTeamGuardersService;
import com.tianque.plugin.serviceTeam.serviceTeamGuarders.vo.ServiceTeamGuardersVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 路由，重点人口调用插件入口
 * 
 * @author yangpengdian
 */
@Namespace("/plugin/serviceTeam/router/routerManage")
@Controller("routerController")
@Scope("prototype")
@Transactional
public class RouterController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(RouterController.class);

	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	@Autowired
	private ServiceMemberWithObjectService memberWithObjectService;
	@Autowired
	private ServiceGuardersWithObjectService serviceGuardersWithObjectService;
	@Autowired
	private ServiceTeamGuardersService serviceTeamGuardersService;
	@Autowired
	private OrganizationDubboService orgService;

	/** 人员类型 */
	private String populationType;
	/** 对话框名称 */
	private String dailogName;
	/** 对象ID */
	private Long id;
	/** 对象姓名 */
	private String name;
	/** 对象来源 */
	private String fromSource;
	/** 是否需要显示记录类型 */
	private String showRecordType;
	/** 当前选择的组织机构ID */
	private Long orgId;
	/** 团队成员VO */
	private ServiceTeamMemberVo serviceTeamMemberVo;
	/** 服务对象和团队成员关系VO */
	private ServiceMemberWithObjectVo serviceMemberWithObjectVo;
	/** 服务对象和团队成员关系domain */
	private ServiceMemberWithObject serviceMemberWithObject;
	/** 服务对象和监护人员关系VO */
	private ServiceGuardersWithObjectVo serviceGuardersWithObjectVo;
	/** 服务对象和监护人员关系domain */
	private ServiceGuardersWithObject serviceGuardersWithObject;
	/** 监护人的信息 */
	private ServiceTeamGuarders serviceTeamGuarders;
	/** 监护人的信息vo */
	private ServiceTeamGuardersVo serviceTeamGuardersVo;
	/** 服务成员与服务对象关联的vo **/
	private ServiceMemberVo serviceMemberVo;
	/** 删除的行数 */
	private int deleteCount;
	/** 要删除或修改的行 */
	private String selectedIds;
	/** 修改的行数 */
	private int updateCount;
	/**
	 * 模块
	 */
	private String module;

	private Boolean pollution = false;

	private ServiceMembersWithObjectVo serviceMembersWithObjectVo;

	/**
	 * 维护重点人口服务成员
	 * 
	 * @return
	 */
	@Action(value = "maintainServiceMemberForPopulation", results = {
			@Result(name = "maintain", location = "/template/router/serviceMember/serviceMemberWithObject/serviceMemberWithObjectMaintainDlg.ftl"),
			@Result(name = "maintainTeamMembers", location = "/template/router/serviceMember/serviceMemberWithObject/serviceMembersWithObjectMaintainDlg.ftl"),
			@Result(name = "maintainGuarders", location = "/template/router/serviceMember/serviceMemberWithObject/serviceGuardersWithObjectMaintainDlg.ftl"),
			@Result(name = "editGuarders", location = "/template/router/serviceMember/serviceMemberWithObject/serviceGuardersMaintainDlg.ftl"),
			@Result(name = "success", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForPopulation.ftl"),
			@Result(name = "view", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForPopulationView.ftl"),
			@Result(name = "print", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForPopulationPrint.ftl") })
	public String maintainServiceMemberForPopulation() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (dailogName.equals("_serviceTeamMemberDialog")) {
				return "maintain";
			} else if (dailogName.equals("_serviceTeamMembersDialog")) {
				return "maintainTeamMembers";
			} else {
				return "maintainGuarders";
			}
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceTeamGuardersById();
			return "editGuarders";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 维护重点人口服务成员
	 * 
	 * @return
	 */
	@Action(value = "maintainServiceMemberForPopulationByEncrypt", results = {
			@Result(name = "maintain", location = "/template/router/serviceMember/serviceMemberWithObject/serviceMemberWithObjectMaintainDlg.ftl"),
			@Result(name = "maintainGuarders", location = "/template/router/serviceMember/serviceMemberWithObject/serviceGuardersWithObjectMaintainDlg.ftl"),
			@Result(name = "editGuarders", location = "/template/router/serviceMember/serviceMemberWithObject/serviceGuardersMaintainDlg.ftl"),
			@Result(name = "success", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForPopulation.ftl"),
			@Result(name = "view", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForPopulationView.ftl"),
			@Result(name = "print", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForPopulationPrint.ftl") })
	@EncryptAnnotation
	public String maintainServiceMemberForPopulationByEncrypt()
			throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (dailogName.equals("_serviceTeamMemberDialog")) {
				return "maintain";
			} else {
				return "maintainGuarders";
			}
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceTeamGuardersById();
			return "editGuarders";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 维护场所服务成员（治安负责人）
	 * 
	 * @return
	 */
	@Action(value = "maintainServiceMemberForLocation", results = {
			@Result(name = "success", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForLocation.ftl"),
			@Result(name = "view1", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForLocationView.ftl"),
			@Result(name = "view", location = "/template/router/serviceMember/serviceMemberWithObject/serviceMemberWithLocationMaintainDlg.ftl"),
			@Result(name = "print1", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForLocationPrint.ftl") })
	public String maintainServiceMemberForLocation() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.VIEW1_MODE.equals(mode)) {
			return "view1";
		} else if ("print1".equals(mode)) {
			return "print1";
		}
		return SUCCESS;
	}

	/**
	 * 维护场所服务成员（治安负责人）(解密调用)
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "maintainServiceMemberForLocationByEncrypt", results = {
			@Result(name = "view1", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForLocationView.ftl"),
			@Result(name = "view", location = "/template/router/serviceMember/serviceMemberWithObject/serviceMemberWithLocationMaintainDlg.ftl"),
			@Result(name = "print1", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForLocationPrint.ftl") })
	public String maintainServiceMemberForLocationByEncrypt() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.VIEW1_MODE.equals(mode)) {
			return "view1";
		} else if ("print1".equals(mode)) {
			return "print1";
		}
		return SUCCESS;
	}

	/**
	 * 维护对象服务记录(解密调用)
	 * 
	 * @return
	 */
	@Action(value = "maintainServiceRecordForPopulationByEncrypt", results = {
			@Result(name = "page-population", location = "/template/router/serviceRecord/serviceRecordListForPopulationView.ftl"),
			@Result(name = "print-population", location = "/template/router/serviceRecord/serviceRecordListForPopulationPrint.ftl") })
	@EncryptAnnotation
	public String maintainServiceRecordForPopulationByEncrypt()
			throws Exception {
		if ("page".equals(mode) || "print".equals(mode)) {
			return mode + "-" + fromSource;
		}
		if (name != null && name.indexOf("href") > 0) {
			name = name
					.substring(name.indexOf("\">") + 2, name.indexOf("</a>"));
		}
		return SUCCESS;
	}

	/**
	 * 服务人员管理(房屋)
	 * 
	 * @return
	 */
	@Action(value = "maintainServiceMemberForHouse", results = {
			@Result(name = "success", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForHouse.ftl"),
			@Result(name = "view1", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForHouseView.ftl"),
			@Result(name = "print1", location = "/template/router/serviceMember/serviceMemberWithObject/serviceTeamMemberListForHousePrint.ftl"),
			@Result(name = "view", location = "/template/router/serviceMember/serviceMemberWithObject/serviceMemberWithHouseMaintainDlg.ftl") })
	public String maintainServiceMemberForHouse() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.VIEW1_MODE.equals(mode)) {
			return "view1";
		} else if ("print1".equals(mode)) {
			return "print1";
		}
		if (name != null && name.indexOf("href") > 0) {
			name = name
					.substring(name.indexOf("\">") + 2, name.indexOf("</a>"));
		}
		return SUCCESS;
	}

	/**
	 * 社区矫正人员-从团队中查询服务人员
	 */
	@Action(value = "findServiceMemberFromTeams", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findServiceMemberFromTeams() throws Exception {
		if (DialogMode.ALLSEARCH_MODE.equals(mode)) {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					serviceTeamMemberService.findServiceMembersFromTeams(
							serviceTeamMemberVo, page, rows, sidx, sord),
					orgService, new String[] { "org" }, orgId));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					serviceTeamMemberService.findServiceMemberFromTeams(
							serviceTeamMemberVo, page, rows, sidx, sord),
					orgService, new String[] { "org" }, orgId));
		}
		return SUCCESS;
	}

	/**
	 * 显示（服务对象所拥有的）服务成员列表
	 */
	@Action(value = "findServiceMembersByServiceMemberVo", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findServiceMembersByServiceMemberVo() throws Exception {
		gridPage = new GridPage<ServiceMemberVo>(serviceTeamMemberService
				.findServiceMembersByServiceMemberVo(serviceMemberVo, page,
						rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * 为服务对象添加团队成员
	 */
	@Actions( {
			@Action(value = "addObjectAndMemberRelationForMobile", results = { @Result(type = "json", name = "success", params = {
					"root", "true" }) }),
			@Action(value = "addObjectAndMemberRelation", results = { @Result(type = "json", name = "success", params = {
					"root", "serviceMemberWithObjectVo", "ignoreHierarchy",
					"false" }) }) })
	public String addObjectAndMemberRelation() throws Exception {
		serviceMemberWithObjectVo = memberWithObjectService
				.addObjectAndMemberRelation(serviceMemberWithObject);
		return SUCCESS;
	}

	/**
	 * 为一个或多个服务对象添加一个或多个团队成员
	 */
	@Action(value = "addObjectAndMembersRelation", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String addObjectAndMembersRelation() throws Exception {
		if (serviceMembersWithObjectVo == null
				|| serviceMembersWithObjectVo.getServiceObjectMembers() == null
				|| serviceMembersWithObjectVo.getServiceObjectMembers().size() == 0) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		memberWithObjectService.addObjectAndMembersRelation(
				serviceMembersWithObjectVo.getServiceObjectMembers(), true);
		return SUCCESS;
	}

	@Action(value = "addObjectAndGuardersRelation", results = { @Result(type = "json", name = "success", params = {
			"root", "serviceTeamGuardersVo", "ignoreHierarchy", "false" }) })
	public String addObjectAndGuardersRelation() throws Exception {
		serviceTeamGuardersVo = serviceTeamGuardersService
				.addServiceTeamGuarders(serviceTeamGuarders);
		return SUCCESS;
	}

	/**
	 * 删除服务对象关联的服务成员
	 */

	@Actions( {
			@Action(value = "deleteServiceMemberWithObjectForMobile", results = { @Result(type = "json", name = "success", params = {
					"root", "true" }) }),
			@Action(value = "deleteServiceMemberWithObject", results = { @Result(type = "json", name = "success", params = {
					"root", "deleteCount", "ignoreHierarchy", "false" }) }) })
	public String deleteServiceMemberWithObject() throws Exception {
		deleteCount = memberWithObjectService
				.deleteServiceMemberWithObjectById(selectedIds);
		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/**
	 * 删除服务对象关联的监护人员
	 */
	@Action(value = "deleteServiceGuardersWithObject", results = { @Result(type = "json", name = "success", params = {
			"root", "deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceGuardersWithObject() throws Exception {
		deleteCount = serviceGuardersWithObjectService
				.deleteServiceGuardersWithObject(selectedIds);

		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/**
	 * 修改服务对象的监护人信息
	 */
	@Action(value = "editObjectAndGuardersRelation", results = { @Result(name = "success", type = "json", params = {
			"root", "serviceTeamGuardersVo", "ignoreHierarchy", "false" }) })
	public String updateGuarders() throws Exception {
		serviceTeamGuardersVo = serviceTeamGuardersService
				.updateServiceTeamGuarders(serviceTeamGuarders);

		return SUCCESS;
	}

	/**
	 * 服务成员卸任与重新担任
	 */
	@Action(value = "leaveOrBackOnDuty", results = { @Result(name = "success", type = "json", params = {
			"root", "updateCount", "ignoreHierarchy", "false" }) })
	public String leaveOrBackOnDuty() throws Exception {
		updateCount = memberWithObjectService
				.updateServiceMemberWithObject(serviceMemberWithObject);

		return SUCCESS;
	}

	/**
	 * 根据id获取监护人员信息
	 */
	private void getServiceTeamGuardersById() {
		Long guardersById = serviceTeamGuarders.getId();
		serviceTeamGuardersVo = serviceTeamGuardersService
				.getServiceTeamGuardersById(guardersById);
	}

	/**
	 * ID加密 服务监管措施
	 */
	/**
	 * 维护对象服务记录
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "maintainServiceRecordForPopulationByEncryptId", results = {
			@Result(name = "success", location = "/template/router/serviceRecord/serviceRecordListForPopulation.ftl"),
			@Result(name = "locationList", location = "/template/router/serviceRecord/serviceRecordListForLocation.ftl"),
			@Result(name = "serviceSupervisionMeasures", location = "/template/router/serviceRecord/serviceSupervisionMeasuresView.ftl"),
			@Result(name = "serviceSupervisionMeasuresView", location = "/template/router/serviceRecord/serviceSupervisionMeasuresViewDlg.ftl"),
			@Result(name = "page-population", location = "/template/router/serviceRecord/serviceRecordListForPopulationView.ftl"),
			@Result(name = "print-population", location = "/template/router/serviceRecord/serviceRecordListForPopulationPrint.ftl") })
	public String maintainServiceRecordForPopulationByEncryptId()
			throws Exception {
		if ("page".equals(mode) || "print".equals(mode)) {
			return mode + "-" + fromSource;
		} else if (showRecordType.equals("1")) {
			return "locationList";
		} else if (showRecordType.equals("serviceSupervisionMeasures")) {
			return "serviceSupervisionMeasures";
		} else if (showRecordType.equals("serviceSupervisionMeasuresView")) {
			return "serviceSupervisionMeasuresView";
		}
		if (name != null && name.indexOf("href") > 0) {
			name = name
					.substring(name.indexOf("\">") + 2, name.indexOf("</a>"));
		}
		return SUCCESS;
	}
	
	
	/**
	 * 管理走访记录
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "maintainInterviewRecordForPopulation", results = {
			@Result(name = "positiveInfo", location = "/baseinfo/positiveInfo/positiveInfoInterviewRecordList.ftl"),
			@Result(name = "rectificativePerson", location = "/baseinfo/rectify/termerInterviewRecordList.ftl"),
			@Result(name = "mentalPatient", location = "/baseinfo/mentalPatient/mentalPatientInterviewList.ftl"),
			@Result(name = "druggy", location = "/baseinfo/druggyManage/druggyInterviewList.ftl"),
			@Result(name = "error", type = "json", params = { "root",
		     "errorMessage" })})
	public String maintainInterviewRecordForPopulationByEncryptId()
			throws Exception {
		if("positiveInfo".equals(populationType)){
			return "positiveInfo";
		}else if("rectificativePerson".equals(populationType)){
			return "rectificativePerson";
		}else if("mentalPatient".equals(populationType)){
			return "mentalPatient";
		}else if("druggy".equals(populationType)){
			return "druggy";
		}
		return ERROR;
	}
	

	/**
	 * 查看走访记录
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "viewInterviewRecordForPopulation", results = {
			@Result(name = "positiveInfo", location = "/baseinfo/positiveInfo/positiveInfoInterviewRecordView.ftl"),
			@Result(name = "rectificativePerson", location = "/baseinfo/rectify/termerInterviewRecordView.ftl"),
			@Result(name = "mentalPatient", location = "/baseinfo/mentalPatient/mentalPatientInterviewView.ftl"),
			@Result(name = "druggy", location = "/baseinfo/druggyManage/druggyInterviewListView.ftl"),
			@Result(name = "error", type = "json", params = { "root",
		     "errorMessage" })})
	public String viewInterviewRecordForPopulation()
			throws Exception {
		if("positiveInfo".equals(populationType)){
			return "positiveInfo";
		}else if("rectificativePerson".equals(populationType)){
			return "rectificativePerson";
		}else if("mentalPatient".equals(populationType)){
			return "mentalPatient";
		}else if("druggy".equals(populationType)){
			return "druggy";
		}
		return ERROR;
	}

	/**
	 * 维护对象服务记录
	 * 
	 * @return
	 */
	@Action(value = "maintainServiceRecordForPopulation", results = {
			@Result(name = "success", location = "/template/router/serviceRecord/serviceRecordListForPopulation.ftl"),
			@Result(name = "locationList", location = "/template/router/serviceRecord/serviceRecordListForLocation.ftl"),
			@Result(name = "serviceSupervisionMeasures", location = "/template/router/serviceRecord/serviceSupervisionMeasuresView.ftl"),
			@Result(name = "serviceSupervisionMeasuresView", location = "/template/router/serviceRecord/serviceSupervisionMeasuresViewDlg.ftl"),
			@Result(name = "page-population", location = "/template/router/serviceRecord/serviceRecordListForPopulationView.ftl"),
			@Result(name = "print-population", location = "/template/router/serviceRecord/serviceRecordListForPopulationPrint.ftl") })
	public String maintainServiceRecordForPopulation() throws Exception {
		if ("page".equals(mode) || "print".equals(mode)) {
			return mode + "-" + fromSource;
		} else if (showRecordType.equals("1")) {
			return "locationList";
		} else if (showRecordType.equals("serviceSupervisionMeasures")) {
			return "serviceSupervisionMeasures";
		} else if (showRecordType.equals("serviceSupervisionMeasuresView")) {
			return "serviceSupervisionMeasuresView";
		}
		if (name != null && name.indexOf("href") > 0) {
			name = name
					.substring(name.indexOf("\">") + 2, name.indexOf("</a>"));
		}
		return SUCCESS;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		RouterController.logger = logger;
	}

	public ServiceTeamMemberVo getServiceTeamMemberVo() {
		return serviceTeamMemberVo;
	}

	public void setServiceTeamMemberVo(ServiceTeamMemberVo serviceTeamMemberVo) {
		this.serviceTeamMemberVo = serviceTeamMemberVo;
	}

	public ServiceMemberWithObjectVo getServiceMemberWithObjectVo() {
		return serviceMemberWithObjectVo;
	}

	public void setServiceMemberWithObjectVo(
			ServiceMemberWithObjectVo serviceMemberWithObjectVo) {
		this.serviceMemberWithObjectVo = serviceMemberWithObjectVo;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public ServiceMemberWithObject getServiceMemberWithObject() {
		return serviceMemberWithObject;
	}

	public void setServiceMemberWithObject(
			ServiceMemberWithObject serviceMemberWithObject) {
		this.serviceMemberWithObject = serviceMemberWithObject;
	}

	public ServiceGuardersWithObjectVo getServiceGuardersWithObjectVo() {
		return serviceGuardersWithObjectVo;
	}

	public void setServiceGuardersWithObjectVo(
			ServiceGuardersWithObjectVo serviceGuardersWithObjectVo) {
		this.serviceGuardersWithObjectVo = serviceGuardersWithObjectVo;
	}

	public ServiceGuardersWithObject getServiceGuardersWithObject() {
		return serviceGuardersWithObject;
	}

	public void setServiceGuardersWithObject(
			ServiceGuardersWithObject serviceGuardersWithObject) {
		this.serviceGuardersWithObject = serviceGuardersWithObject;
	}

	public ServiceTeamGuarders getServiceTeamGuarders() {
		return serviceTeamGuarders;
	}

	public void setServiceTeamGuarders(ServiceTeamGuarders serviceTeamGuarders) {
		this.serviceTeamGuarders = serviceTeamGuarders;
	}

	public ServiceTeamGuardersVo getServiceTeamGuardersVo() {
		return serviceTeamGuardersVo;
	}

	public void setServiceTeamGuardersVo(
			ServiceTeamGuardersVo serviceTeamGuardersVo) {
		this.serviceTeamGuardersVo = serviceTeamGuardersVo;
	}

	public ServiceMemberVo getServiceMemberVo() {
		return serviceMemberVo;
	}

	public void setServiceMemberVo(ServiceMemberVo serviceMemberVo) {
		this.serviceMemberVo = serviceMemberVo;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}

	public String getShowRecordType() {
		return showRecordType;
	}

	public void setShowRecordType(String showRecordType) {
		this.showRecordType = showRecordType;
	}

	public Boolean getPollution() {
		return pollution;
	}

	public void setPollution(Boolean pollution) {
		this.pollution = pollution;
	}

	public ServiceMembersWithObjectVo getServiceMembersWithObjectVo() {
		return serviceMembersWithObjectVo;
	}

	public void setServiceMembersWithObjectVo(
			ServiceMembersWithObjectVo serviceMembersWithObjectVo) {
		this.serviceMembersWithObjectVo = serviceMembersWithObjectVo;
	}

}
