package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueSkipruleVo;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueMessageRemindService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSkipruleService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 四支队伍事件类型越级规则设置:服务前端控制类
 * 
 * @author
 * 
 */
@Namespace("/fourTeamsIssueSkipruleManage")
@Scope("request")
@Controller("fourTeamsIssueSkipruleController")
public class FourTeamsIssueSkipruleController extends BaseAction {

	@Autowired
	private FourTeamsIssueSkipruleService fourTeamsIssueSkipruleService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private FourTeamsIssueMessageRemindService fourTeamsIssueMessageRemindService;

	private FourTeamsIssueSkiprule fourTeamsIssueSkiprule;
	private List<FourTeamsIssueMessageRemind> fourTeamsIssueMessageRemindList;
	private SearchFourTeamsIssueSkipruleVo searchFourTeamsIssueSkipruleVo;
	private String ids;
	private Long organizationId;
	private List<PropertyDict> propertyDicts;
	private String dailogName;

	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "edit", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/fourTeamsIssueSkipruleViewDlg.jsp"),
			@Result(name = "search", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/searchFourTeamsIssueSkipruleDlg.jsp"),
			@Result(name = "view", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/fourTeamsIssueSkipruleViewDlg.jsp"),
			@Result(name = "add", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/fourTeamsIssueSkipruleViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			fourTeamsIssueSkiprule = new FourTeamsIssueSkiprule();
			fourTeamsIssueSkiprule.setOrgId(ThreadVariable.getUser()
					.getOrganization().getId());
			return mode;
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (id == null) {
				this.errorMessage = "参数无效!";
				return ERROR;
			}
			fourTeamsIssueSkiprule = fourTeamsIssueSkipruleService
					.getFourTeamsIssueSkipruleById(id);
			fourTeamsIssueMessageRemindList = fourTeamsIssueMessageRemindService
					.findFourTeamsIssueMessageRemindListBySkipRuleId(fourTeamsIssueSkiprule
							.getId());
			if (fourTeamsIssueMessageRemindList == null
					|| fourTeamsIssueMessageRemindList.size() == 0)
				fourTeamsIssueMessageRemindList = null;
			return mode;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			propertyDicts = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.URGENT_LEVEL);
			return mode;
		}
		return ERROR;
	}

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getFourTeamsIssueSkipruleById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeamsIssueSkiprule", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "viewFourTeamsIssueSkiprule")
	public String getFourTeamsIssueSkipruleById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		fourTeamsIssueSkiprule = fourTeamsIssueSkipruleService
				.getFourTeamsIssueSkipruleById(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addFourTeamsIssueSkiprule", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeamsIssueSkiprule", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addFourTeamsIssueSkiprule")
	public String addFourTeamsIssueSkiprule() throws Exception {
		if (fourTeamsIssueSkiprule == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		fourTeamsIssueSkiprule = fourTeamsIssueSkipruleService
				.addFourTeamsIssueSkiprule(fourTeamsIssueSkiprule,
						fourTeamsIssueMessageRemindList);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateFourTeamsIssueSkiprule", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeamsIssueSkiprule", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateFourTeamsIssueSkiprule")
	public String updateFourTeamsIssueSkiprule() throws Exception {
		if (fourTeamsIssueSkiprule == null
				|| fourTeamsIssueSkiprule.getId() == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		fourTeamsIssueSkiprule = fourTeamsIssueSkipruleService
				.updateFourTeamsIssueSkiprule(fourTeamsIssueSkiprule,
						fourTeamsIssueMessageRemindList);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteFourTeamsIssueSkipruleById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteFourTeamsIssueSkiprule")
	public String deleteFourTeamsIssueSkipruleById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		fourTeamsIssueSkipruleService.delete(id);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteFourTeamsIssueSkipruleByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteFourTeamsIssueSkiprule")
	public String deleteFourTeamsIssueSkipruleByIds() throws Exception {
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
		fourTeamsIssueSkipruleService.delete(idsLong);
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
	@Action(value = "findFourTeamsIssueSkiprulePagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "findFourTeamsIssueSkiprule")
	public String findFourTeamsIssueSkiprulePagerBySearchVo() throws Exception {
		if (searchFourTeamsIssueSkipruleVo == null
				|| searchFourTeamsIssueSkipruleVo.getOrgId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(
				fourTeamsIssueSkipruleService.findPagerBySearchVo(
						searchFourTeamsIssueSkipruleVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "getUrgentLevelList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"propertyDicts", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getUrgentLevelList() throws Exception {
		if (fourTeamsIssueSkiprule == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getParentOrgByOrgTypeAndChildOrgId(
						fourTeamsIssueSkiprule.getOrgId(),
						OrganizationLevel.DISTRICT);

		fourTeamsIssueSkiprule.setOrgId(organization.getId());
		List<FourTeamsIssueSkiprule> isList = fourTeamsIssueSkipruleService
				.findFourTeamsIssueSkiprulesByRules(fourTeamsIssueSkiprule);
		if (isList != null) {
			propertyDicts = new ArrayList<PropertyDict>();
			for (FourTeamsIssueSkiprule is : isList) {
				propertyDicts.add(propertyDictService.getPropertyDictById(is
						.getIssueUrgentLevel().getId()));
			}
		}
		return SUCCESS;
	}

	public FourTeamsIssueSkiprule getFourTeamsIssueSkiprule() {
		return fourTeamsIssueSkiprule;
	}

	public void setFourTeamsIssueSkiprule(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		this.fourTeamsIssueSkiprule = fourTeamsIssueSkiprule;
	}

	public SearchFourTeamsIssueSkipruleVo getSearchFourTeamsIssueSkipruleVo() {
		return searchFourTeamsIssueSkipruleVo;
	}

	public void setSearchFourTeamsIssueSkipruleVo(
			SearchFourTeamsIssueSkipruleVo searchFourTeamsIssueSkipruleVo) {
		this.searchFourTeamsIssueSkipruleVo = searchFourTeamsIssueSkipruleVo;
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

	public List<PropertyDict> getPropertyDicts() {
		return propertyDicts;
	}

	public void setPropertyDicts(List<PropertyDict> propertyDicts) {
		this.propertyDicts = propertyDicts;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public List<FourTeamsIssueMessageRemind> getFourTeamsIssueMessageRemindList() {
		return fourTeamsIssueMessageRemindList;
	}

	public void setFourTeamsIssueMessageRemindList(
			List<FourTeamsIssueMessageRemind> fourTeamsIssueMessageRemindList) {
		this.fourTeamsIssueMessageRemindList = fourTeamsIssueMessageRemindList;
	}

}
