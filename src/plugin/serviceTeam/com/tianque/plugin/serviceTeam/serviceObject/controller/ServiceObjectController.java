package com.tianque.plugin.serviceTeam.serviceObject.controller;

import java.util.ArrayList;
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

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.service.ServiceMemberWithObjectService;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.vo.ServiceMemberWithObjectVo;
import com.tianque.plugin.serviceTeam.serviceObject.service.ServiceObjectService;
import com.tianque.plugin.serviceTeam.serviceObject.vo.ServiceObjectVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceMemberInTeamVo;
import com.tianque.plugin.serviceTeam.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务对象控制类
 */
/**
 * @author Medyson
 */
@Namespace("/plugin/serviceTeam/serviceObject")
@Transactional
@Scope("request")
@Controller("serviceObjectController")
public class ServiceObjectController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(ServiceObjectController.class);

	// FIXME 不要用这种东东，离了注释不明白什么意思
	/** 服务对象主类型 */
	private String key;
	/** 服务对象类型存储List */
	private List<Map<String, String>> list;
	/** 服务对象 */
	private ServiceObjectVo serviceObjectVo;
	/** 成员在团队中的id字符串格式，多个id用，隔开 */
	private String memberIdStrs;
	/** 删除时使用的关联关系对象 */
	private ServiceMemberWithObject serviceMemberWithObject;
	/** 删除的行数 */
	private int deleteCount;
	private String from;// 从哪个页面上调用服务对象的维护
	public static final String FROM_SERVICE_MEMBER = "fromServiceMember";
	public static final String FROM_SERVICE_RECORD = "fromServiceRecord";
	/** 对象类型 */
	private String objectType;
	/** 服务对象 **/
	private String serviceObjects;
	/**
	 * 服务对象的id和名称
	 */
	private String objectIdsAndNames;
	/**
	 * 服务对象的id和团队的id
	 */
	private String memberIdAndTeamId;

	// 存放服务成员和团队的mapList，key成员id-团队id，value 团队名称
	private List<Map<String, String>> memberTeamMapList;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceObjectService serviceObjectService;
	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	@Autowired
	private ServiceMemberWithObjectService memberWithObjectService;

	/** 业务跳转 */
	@Action(value = "dispatch", results = { @Result(name = "selectObject", location = "/template/serviceTeam/serviceMemberWithObject/objectForServiceMaintainDlg.ftl") })
	public String dispatch() throws Exception {
		return "selectObject";
	}

	/**
	 * 查找记录用于服务对象列表中人员类型二级联动
	 */
	@Action(value = "findPopulationTypeBypopulationBigType", results = {
			@Result(type = "json", name = "success", params = { "root", "list",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPopulationTypeBypopulationBigType() throws Exception {
		getPopulationType();
		return SUCCESS;
	}

	/**
	 * 查找记录用于服务对象列表中人员类型一级联动
	 */
	@Action(value = "findPopulationBigType", results = {
			@Result(type = "json", name = "success", params = { "root", "list",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPopulationBigType() throws Exception {
		getPopulationBigType();
		return SUCCESS;
	}

	/**
	 * 查找用于记录新增的服务对象列表
	 */
	@Action(value = "findPopulationsForServiceRecord", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPopulationsForServiceRecord() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceObjectService.findObjects(serviceObjectVo, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, null));
		return SUCCESS;
	}

	/**
	 * 选中成员的所有服务对象列表显示（不限所在团队与层级关系）
	 */
	@Action(value = "findObjectsForServiceTeamMember", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findObjectsForServiceTeamMember() throws Exception {

		serviceObjectVo.setMemberIds(processStrToList(memberIdStrs));

		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(serviceObjectService
						.findObjectsForServiceTeamMember(serviceObjectVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, null));

		return SUCCESS;
	}

	/**
	 * 获得该成员的团队下拉列表
	 */
	@Action(value = "getMemberInTeam", results = { @Result(type = "json", name = "success", params = {
			"root", "memberTeamMapList", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String getMemberInTeam() throws Exception {
		List<Long> memberIds = processStrToList(memberIdStrs);
		List<ServiceMemberInTeamVo> serviceMemberList = null;
		if (null != memberIds && memberIds.size() > 0) {
			serviceMemberList = new ArrayList<ServiceMemberInTeamVo>();
			serviceMemberList.addAll(serviceTeamMemberService
					.getServiceMemberInTeamVoByMemberId(memberIds.get(0)));
		}
		if (null != serviceMemberList && serviceMemberList.size() > 0) {
			memberTeamMapList = new ArrayList<Map<String, String>>();
			Map<String, String> map;
			for (ServiceMemberInTeamVo vo : serviceMemberList) {
				map = new HashMap<String, String>();
				map.put(vo.getServiceMemberId() + "-" + vo.getTeamId(),
						vo.getTeamName());
				memberTeamMapList.add(map);
			}
		}
		return SUCCESS;
	}

	@Action(value = "addServiceObjectFromMember", results = { @Result(type = "json", name = "success", params = {
			"root", "", "ignoreHierarchy", "false", "excludeNullProperties",
			"true" }) })
	public String addServiceObjectFromMember() throws Exception {
		ServiceMemberWithObject serviceMemberWithObject = new ServiceMemberWithObject();
		String[] memberIdAndTeamIds = memberIdAndTeamId.split("-");
		if (null != objectIdsAndNames) {
			for (String str : objectIdsAndNames.split(",")) {
				serviceMemberWithObject.setMemberId(Long
						.parseLong(memberIdAndTeamIds[0]));
				serviceMemberWithObject.setTeamId(Long
						.parseLong(memberIdAndTeamIds[1]));
				serviceMemberWithObject.setObjectId(Long.parseLong((str
						.split("-")[0])));
				serviceMemberWithObject.setObjectName(str.split("-")[1]);
				serviceMemberWithObject.setObjectType(objectType);
				serviceMemberWithObject
						.setTeamMember(Constants.TeamMember.serviceMember);

				ServiceMemberWithObjectVo vo = memberWithObjectService
						.getServiceMemberWithObjectVoByMemberIdAndObjectTypeAndId(
								serviceMemberWithObject.getMemberId(),
								serviceMemberWithObject.getObjectId(),
								serviceMemberWithObject.getObjectType());
				if (vo == null) {
					memberWithObjectService
							.addObjectAndMemberRelation(serviceMemberWithObject);
				}
				if (vo != null && vo.getOnDuty() != null
						&& Constants.OnDudy.UNDUDY.equals(vo.getOnDuty())) {
					serviceMemberWithObject.setId(vo.getId());
					serviceMemberWithObject.setOnDuty(Constants.OnDudy.ONDUDY);
					memberWithObjectService
							.updateServiceMemberWithObject(serviceMemberWithObject);
					continue;
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "deleteServiceObjectFromMember", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceObjectFromMember() throws Exception {
		deleteCount = memberWithObjectService.deleteServiceObjectFromMember(
				processStrToList(memberIdStrs), serviceMemberWithObject);
		return SUCCESS;
	}

	private List<Long> processStrToList(String memberIdStrs) {
		List<Long> resultList = new ArrayList<Long>();
		if (null != memberIdStrs && memberIdStrs.indexOf(",") >= 0) {
			for (String str : memberIdStrs.split(",")) {
				resultList.add(Long.parseLong(str));
			}

		} else {
			resultList.add(Long.parseLong(memberIdStrs));

		}
		return resultList;
	}

	/**
	 * 获得具体分类
	 */
	public void getPopulationType() {
		list = new ArrayList<Map<String, String>>();
		for (String keyName : BaseInfoTables.keyTablesMaps.get(key).keySet()) {
			String cname = BaseInfoTables.getTypeDisplayNames(keyName);
			Map<String, String> map = new HashMap<String, String>();
			map.put(keyName, cname);
			list.add(map);
		}
	}

	/**
	 * 获得大分类
	 */
	public void getPopulationBigType() {
		list = new ArrayList<Map<String, String>>();
		for (String keyName : BaseInfoTables.keyTablesMaps.get(
				BaseInfoTables.POPULATIONKEYTABLES_KEY).keySet()) {
			String cname = BaseInfoTables.getTypeDisplayNames(keyName);
			Map<String, String> map = new HashMap<String, String>();
			map.put(keyName, cname);
			list.add(map);
		}
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ServiceObjectController.logger = logger;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	public ServiceObjectVo getServiceObjectVo() {
		return serviceObjectVo;
	}

	public void setServiceObjectVo(ServiceObjectVo serviceObjectVo) {
		this.serviceObjectVo = serviceObjectVo;
	}

	public OrganizationDubboService getorganizationDubboService() {
		return organizationDubboService;
	}

	public void setorganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public ServiceObjectService getServiceObjectService() {
		return serviceObjectService;
	}

	public void setServiceObjectService(
			ServiceObjectService serviceObjectService) {
		this.serviceObjectService = serviceObjectService;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getServiceObjects() {
		return serviceObjects;
	}

	public void setServiceObjects(String serviceObjects) {
		this.serviceObjects = serviceObjects;
	}

	public String getMemberIdStrs() {
		return memberIdStrs;
	}

	public void setMemberIdStrs(String memberIdStrs) {
		this.memberIdStrs = memberIdStrs;
	}

	public List<Map<String, String>> getMemberTeamMapList() {
		return memberTeamMapList;
	}

	public void setMemberTeamMapList(List<Map<String, String>> memberTeamMapList) {
		this.memberTeamMapList = memberTeamMapList;
	}

	public String getObjectIdsAndNames() {
		return objectIdsAndNames;
	}

	public void setObjectIdsAndNames(String objectIdsAndNames) {
		this.objectIdsAndNames = objectIdsAndNames;
	}

	public String getMemberIdAndTeamId() {
		return memberIdAndTeamId;
	}

	public void setMemberIdAndTeamId(String memberIdAndTeamId) {
		this.memberIdAndTeamId = memberIdAndTeamId;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public ServiceMemberWithObject getServiceMemberWithObject() {
		return serviceMemberWithObject;
	}

	public void setServiceMemberWithObject(
			ServiceMemberWithObject serviceMemberWithObject) {
		this.serviceMemberWithObject = serviceMemberWithObject;
	}

}
