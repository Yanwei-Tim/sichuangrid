package com.tianque.partyBuilding.organizations.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.activityRecords.constant.OrganizationType;
import com.tianque.partyBuilding.activityRecords.service.ActivityRecordsService;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.organizations.domain.NewPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchNewPartyOrgVo;
import com.tianque.partyBuilding.organizations.service.NewPartyOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 两新组织党组织表:服务前端控制类
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
@Namespace("/newPartyOrgManage")
@Scope("request")
@Controller("newPartyOrgController")
public class NewPartyOrgController extends BaseAction {

	@Autowired
	private NewPartyOrgService newPartyOrgService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;
	@Autowired
	private ActivityRecordsService activityRecordsService;

	private NewPartyOrg newPartyOrg;
	private SearchNewPartyOrgVo searchVo;
	private String ids;
	private Long id;
	private Long orgId;

	private String oldPartyOrg;

	/**
	 * id加密控制跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/newPartyOrgManage/newPartyOrgDlg.jsp"),
			@Result(name = "list", location = "/partyBuilding/organizations/newPartyOrgManage/newPartyOrgList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "newPartyOrgManagement")
	public String dispatchByEncrypt() throws Exception {
		if (id != null) {
			newPartyOrg = newPartyOrgService.get(id);
		}
		if (orgId != null) {
			getNewPartyOrg().setOrganization(
					organizationDubboService.getSimpleOrgById(orgId));
		}
		if (mode.equals("search") || mode.equals("list")) {
			return mode;
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/newPartyOrgManage/newPartyOrgDlg.jsp"),
			@Result(name = "list", location = "/partyBuilding/organizations/newPartyOrgManage/newPartyOrgList.jsp"),
			@Result(name = "search", location = "/partyBuilding/organizations/newPartyOrgManage/newPartyOrgSearchDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "newPartyOrgManagement")
	public String dispatch() throws Exception {
		if (id != null) {
			newPartyOrg = newPartyOrgService.get(id);
		}
		if (orgId != null) {
			getNewPartyOrg().setOrganization(
					organizationDubboService.getSimpleOrgById(orgId));
		}
		if (mode.equals("search") || mode.equals("list")) {
			return mode;
		}
		return SUCCESS;
	}

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getNewPartyOrgById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newPartyOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "newPartyOrgManagement")
	public String getNewPartyOrgById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		newPartyOrg = newPartyOrgService.get(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addNewPartyOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newPartyOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addNewPartyOrg")
	public String addNewPartyOrg() throws Exception {
		if (newPartyOrg == null
				|| !checkOrganization(newPartyOrg.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		newPartyOrg = newPartyOrgService.add(newPartyOrg);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateNewPartyOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newPartyOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateNewPartyOrg")
	public String updateNewPartyOrg() throws Exception {
		if (newPartyOrg == null || newPartyOrg.getId() == null
				|| !checkOrganization(newPartyOrg.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		newPartyOrg.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				newPartyOrg.getOrganization().getId()).getOrgInternalCode());
		memberAssociatePartyOrgService.updatePartyorgByPartyOrgTypeAndPartyorg(
				MemberType.TWO_NEW_ORGNIZATION_PARTY_ORG, oldPartyOrg,
				newPartyOrg.getName());
		newPartyOrg = newPartyOrgService.update(newPartyOrg);

		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteNewPartyOrgById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteNewPartyOrg")
	public String deleteNewPartyOrgById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		newPartyOrgService.delete(id);
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteNewPartyOrgByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteNewPartyOrg")
	@EncryptAnnotation
	public String deleteNewPartyOrgByIdsByEncrypt() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		newPartyOrgService.delete(idsLong);
		activityRecordsService
				.deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType(
						idsLong, OrganizationType.NEW_PARTY_ORGANIZATION);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteNewPartyOrgByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteNewPartyOrg")
	public String deleteNewPartyOrgByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		newPartyOrgService.delete(idsLong);
		activityRecordsService
				.deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType(
						idsLong, OrganizationType.NEW_PARTY_ORGANIZATION);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findNewPartyOrgPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "newPartyOrgManagement")
	public String findNewPartyOrgPagerBySearchVo() throws Exception {
		if (orgId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		if (searchVo == null) {
			searchVo = new SearchNewPartyOrgVo();
		}
		searchVo.setOrgInternalCode(organization.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				newPartyOrgService.findPagerBySearchVo(searchVo, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @return Pager对象
	 */
	@Action(value = "statisticNewPartyOrgBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticNewPartyOrgBySearchVo() throws Exception {
		if (searchVo == null || searchVo.getOrgId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(newPartyOrgService.statisticBySearchVo(
				searchVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * 唯一性验证
	 */
	@Action(value = "hasDuplicateNewPartyOrg", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "newPartyOrgManagement")
	public String hasDuplicateNewPartyOrg() throws Exception {
		if (searchVo == null || searchVo.getOrgId() == null
				|| searchVo.getName() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (newPartyOrgService.hasDuplicate(searchVo)) {
			return SUCCESS;
		}
		return ERROR;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public NewPartyOrg getNewPartyOrg() {
		if (newPartyOrg == null) {
			newPartyOrg = new NewPartyOrg();
		}
		return newPartyOrg;
	}

	public void setNewPartyOrg(NewPartyOrg newPartyOrg) {
		this.newPartyOrg = newPartyOrg;
	}

	public SearchNewPartyOrgVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchNewPartyOrgVo searchVo) {
		this.searchVo = searchVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getOldPartyOrg() {
		return oldPartyOrg;
	}

	public void setOldPartyOrg(String oldPartyOrg) {
		this.oldPartyOrg = oldPartyOrg;
	}

}
