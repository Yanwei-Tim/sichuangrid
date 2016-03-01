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
import com.tianque.partyBuilding.organizations.domain.TownPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchTownPartyOrgVo;
import com.tianque.partyBuilding.organizations.service.TownPartyOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 街道社区党组织表:服务前端控制类
 * 
 * @author
 * @date 2014-01-14 14:35:25
 */
@Namespace("/townPartyOrgManage")
@Scope("request")
@Controller("townPartyOrgController")
public class TownPartyOrgController extends BaseAction {

	@Autowired
	private TownPartyOrgService townPartyOrgService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	MemberAssociatePartyOrgService memberAssociatePartyOrgService;
	@Autowired
	ActivityRecordsService activityRecordsService;

	private String oldPartyOrg;
	private TownPartyOrg townPartyOrg;
	private SearchTownPartyOrgVo searchVo;
	private String ids;
	private Long id;
	private Long orgId;

	/**
	 * id加密控制跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/townPartyOrgManage/townPartyOrgDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "townPartyOrgManagement")
	public String dispatchByEncrypt() throws Exception {
		if (id != null) {
			townPartyOrg = townPartyOrgService.get(id);
		}
		if (orgId != null) {
			getTownPartyOrg().setOrganization(
					organizationDubboService.getSimpleOrgById(orgId));
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/townPartyOrgManage/townPartyOrgDlg.jsp"),
			@Result(name = "list", location = "/partyBuilding/organizations/townPartyOrgManage/townPartyOrgList.jsp"),
			@Result(name = "search", location = "/partyBuilding/organizations/townPartyOrgManage/townPartyOrgSearchDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "townPartyOrgManagement")
	public String dispatch() throws Exception {
		if (id != null) {
			townPartyOrg = townPartyOrgService.get(id);
		}
		if (orgId != null) {
			getTownPartyOrg().setOrganization(
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
	@Action(value = "getTownPartyOrgById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"townPartyOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "townPartyOrgManagement")
	public String getTownPartyOrgById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		townPartyOrg = townPartyOrgService.get(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addTownPartyOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"townPartyOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addTownPartyOrg")
	public String addTownPartyOrg() throws Exception {
		if (townPartyOrg == null
				|| !checkOrganization(townPartyOrg.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		townPartyOrg = townPartyOrgService.add(townPartyOrg);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateTownPartyOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"townPartyOrg", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateTownPartyOrg")
	public String updateTownPartyOrg() throws Exception {
		if (townPartyOrg == null || townPartyOrg.getId() == null
				|| !checkOrganization(townPartyOrg.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		townPartyOrg.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				townPartyOrg.getOrganization().getId()).getOrgInternalCode());
		memberAssociatePartyOrgService.updatePartyorgByPartyOrgTypeAndPartyorg(
				MemberType.TOWN_PARTY_ORG, oldPartyOrg, townPartyOrg.getName());
		townPartyOrg = townPartyOrgService.update(townPartyOrg);

		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteTownPartyOrgById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteTownPartyOrg")
	public String deleteTownPartyOrgById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		townPartyOrgService.delete(id);
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteTownPartyOrgByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteTownPartyOrg")
	@EncryptAnnotation
	public String deleteTownPartyOrgByIdsByEncrypt() throws Exception {
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
		townPartyOrgService.delete(idsLong);
		activityRecordsService
				.deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType(
						idsLong, OrganizationType.TOWN_PARTY_ORGANIZATION);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteTownPartyOrgByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteTownPartyOrg")
	public String deleteTownPartyOrgByIds() throws Exception {
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
		townPartyOrgService.delete(idsLong);
		activityRecordsService
				.deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType(
						idsLong, OrganizationType.TOWN_PARTY_ORGANIZATION);
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
	@Action(value = "findTownPartyOrgPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "townPartyOrgManagement")
	public String findTownPartyOrgPagerBySearchVo() throws Exception {
		if (orgId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchVo == null) {
			searchVo = new SearchTownPartyOrgVo();
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchVo.setOrgInternalCode(organization.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				townPartyOrgService.findPagerBySearchVo(searchVo, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @return Pager对象
	 */
	@Action(value = "statisticTownPartyOrgBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticTownPartyOrgBySearchVo() throws Exception {
		if (searchVo == null || searchVo.getOrgId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(townPartyOrgService.statisticBySearchVo(
				searchVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * 唯一性验证
	 */
	@Action(value = "hasDuplicateTownPartyOrg", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "townPartyOrgManagement")
	public String hasDuplicateTownPartyOrg() throws Exception {
		if (searchVo == null || searchVo.getOrgId() == null
				|| searchVo.getName() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (townPartyOrgService.hasDuplicate(searchVo)) {
			return SUCCESS;
		}
		return ERROR;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public TownPartyOrg getTownPartyOrg() {
		if (townPartyOrg == null) {
			townPartyOrg = new TownPartyOrg();
		}
		return townPartyOrg;
	}

	public void setTownPartyOrg(TownPartyOrg townPartyOrg) {
		this.townPartyOrg = townPartyOrg;
	}

	public SearchTownPartyOrgVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchTownPartyOrgVo searchVo) {
		this.searchVo = searchVo;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOldPartyOrg() {
		return oldPartyOrg;
	}

	public void setOldPartyOrg(String oldPartyOrg) {
		this.oldPartyOrg = oldPartyOrg;
	}

}
