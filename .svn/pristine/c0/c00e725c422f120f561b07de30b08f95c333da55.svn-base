package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPeaceVillage;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsPeaceVillageVo;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsPeaceVillageService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 村社区平安三联创:服务前端控制类
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
@Namespace("/fourTeams/peaceVillageManage")
@Scope("request")
@Controller("fourTeamsPeaceVillageController")
public class FourTeamsPeaceVillageController extends BaseAction {
	@Autowired
	private FourTeamsPeaceVillageService peaceVillageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private FourTeamsPeaceVillage peaceVillage;
	private SearchFourTeamsPeaceVillageVo searchPeaceVillageVo;
	private String ids;
	private Long id;
	private Long organizationId;
	private List<FourTeamsPeaceVillage> peaceVillageList;
	private String saveResult;

	// private Organization organization;

	@Action(value = "getPeaceVillageListBySearchVo", results = { @Result(name = "success", location = "/issue/pacificUnionFounding/pacificUnionFoundingVillageList.jsp") })
	public String getPeaceVillageListBySearchVo() throws Exception {
		peaceVillageList = peaceVillageService
				.getPeaceVillageListBySearchVo(searchPeaceVillageVo);
		return SUCCESS;
	}

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getPeaceVillageById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peaceVillage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	// @PermissionFilter(ename = "peaceVillageManagement")
	public String getPeaceVillageById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		peaceVillage = peaceVillageService.get(id);
		return SUCCESS;
	}

	@Action(value = "savePeaceVillage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"saveResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String savePeaceVillage() throws Exception {
		peaceVillageService.savePeaceVillage(peaceVillageList);
		saveResult = "success";
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addPeaceVillage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peaceVillage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	// @PermissionFilter(ename = "addPeaceVillage")
	public String addPeaceVillage() throws Exception {
		if (peaceVillage == null
				|| !checkOrganization(peaceVillage.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		peaceVillage = peaceVillageService.add(peaceVillage);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updatePeaceVillage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peaceVillage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	// @PermissionFilter(ename = "updatePeaceVillage")
	public String updatePeaceVillage() throws Exception {
		if (peaceVillage == null || peaceVillage.getId() == null
				|| !checkOrganization(peaceVillage.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		peaceVillage = peaceVillageService.update(peaceVillage);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deletePeaceVillageById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	// @PermissionFilter(ename = "deletePeaceVillage")
	public String deletePeaceVillageById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		peaceVillageService.delete(id);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deletePeaceVillageByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	// @PermissionFilter(ename = "deletePeaceVillage")
	public String deletePeaceVillageByIds() throws Exception {
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
		peaceVillageService.delete(idsLong);
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
	@Action(value = "findPeaceVillagePagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	// @PermissionFilter(ename = "peaceVillageManagement")
	public String findPeaceVillagePagerBySearchVo() throws Exception {
		if (searchPeaceVillageVo == null || organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (organization == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchPeaceVillageVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				peaceVillageService.findPagerBySearchVo(searchPeaceVillageVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public FourTeamsPeaceVillage getPeaceVillage() {
		return peaceVillage;
	}

	public void setPeaceVillage(FourTeamsPeaceVillage peaceVillage) {
		this.peaceVillage = peaceVillage;
	}

	public SearchFourTeamsPeaceVillageVo getSearchPeaceVillageVo() {
		return searchPeaceVillageVo;
	}

	public void setSearchPeaceVillageVo(
			SearchFourTeamsPeaceVillageVo searchPeaceVillageVo) {
		this.searchPeaceVillageVo = searchPeaceVillageVo;
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

	public List<FourTeamsPeaceVillage> getPeaceVillageList() {
		return peaceVillageList;
	}

	public void setPeaceVillageList(List<FourTeamsPeaceVillage> peaceVillageList) {
		this.peaceVillageList = peaceVillageList;
	}

	public String getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}

}
