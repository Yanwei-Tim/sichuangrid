package com.tianque.partyBuilding.volunteerTeam.controller;

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
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerTeam;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerTeamVo;
import com.tianque.partyBuilding.volunteerTeam.service.VolunteerTeamService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 党员志愿者队伍表:服务前端控制类
 * 
 * @author
 * @date 2014-02-11 15:27:44
 */
@Namespace("/volunteerTeamManage")
@Scope("request")
@Controller("volunteerTeamController")
public class VolunteerTeamController extends BaseAction {

	@Autowired
	private VolunteerTeamService volunteerTeamService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private VolunteerTeam volunteerTeam = new VolunteerTeam();
	private SearchVolunteerTeamVo searchVo;
	private String ids;
	private Long id;
	private Long organizationId;

	/**
	 * id加密控制跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/volunteerTeam/volunteerTeamDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String dispatchByEncrypt() throws Exception {
		if (id != null) {
			volunteerTeam = volunteerTeamService.get(id);
			organizationId = volunteerTeam.getOrganization().getId();
		}
		if (organizationId != null) {
			volunteerTeam.setOrganization(organizationDubboService
					.getSimpleOrgById(organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/volunteerTeam/volunteerTeamDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String dispatch() throws Exception {
		if (id != null) {
			volunteerTeam = volunteerTeamService.get(id);
			organizationId = volunteerTeam.getOrganization().getId();
		}
		if (organizationId != null) {
			volunteerTeam.setOrganization(organizationDubboService
					.getSimpleOrgById(organizationId));
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
	@Action(value = "getVolunteerTeamById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerTeam", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String getVolunteerTeamById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		volunteerTeam = volunteerTeamService.get(id);
		return SUCCESS;
	}

	@Action(value = "hasDuplicate", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String hasDuplicate() throws Exception {
		if (volunteerTeam == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		if (volunteerTeamService.hasDuplicate(volunteerTeam)) {
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addVolunteerTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerTeam", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addVolunteerTeam")
	public String addVolunteerTeam() throws Exception {
		if (volunteerTeam == null
				|| !checkOrganization(volunteerTeam.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		volunteerTeam = volunteerTeamService.add(volunteerTeam);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateVolunteerTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerTeam", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateVolunteerTeam")
	public String updateVolunteerTeam() throws Exception {
		if (volunteerTeam == null || volunteerTeam.getId() == null
				|| !checkOrganization(volunteerTeam.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		volunteerTeam = volunteerTeamService.update(volunteerTeam);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteVolunteerTeamById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerTeam")
	public String deleteVolunteerTeamById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		volunteerTeamService.delete(id);
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@EncryptAnnotation
	@Action(value = "deleteVolunteerTeamByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerTeam")
	public String deleteVolunteerTeamByIdsByEncrypt() throws Exception {
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
		volunteerTeamService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteVolunteerTeamByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerTeam")
	public String deleteVolunteerTeamByIds() throws Exception {
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
		volunteerTeamService.delete(idsLong);
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
	@Action(value = "findVolunteerTeamPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String findVolunteerTeamPagerBySearchVo() throws Exception {
		if (searchVo == null || searchVo.getOrgId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(searchVo.getOrgId());
		if (organization == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchVo.setOrgInternalCode(organization.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				volunteerTeamService.findPagerBySearchVo(searchVo, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public VolunteerTeam getVolunteerTeam() {
		return volunteerTeam;
	}

	public void setVolunteerTeam(VolunteerTeam volunteerTeam) {
		this.volunteerTeam = volunteerTeam;
	}

	public SearchVolunteerTeamVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchVolunteerTeamVo searchVo) {
		this.searchVo = searchVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

}
