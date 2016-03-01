package com.tianque.baseInfo.primaryOrg.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.primaryOrg.domain.RedCuffTeam;
import com.tianque.baseInfo.primaryOrg.service.RedCuffTeamService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/baseinfo/redCuffTeamManage")
@Controller("redCuffTeamController")
public class RedCuffTeamController extends BaseAction {

	private Long id;
	private Organization organization;
	private RedCuffTeam redCuffTeam;
	private String ids;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private RedCuffTeamService redCuffTeamService;
	@Autowired
	private CacheService cacheService;

	private Boolean allowCache;// 允许缓存
	/** 已认证 */
	private static final Integer IS_CERTIFICATION = 1;
	/** 未认证 */
	private static final Integer IS_NOT_CERTIFICATION = 0;

	/**
	 * 列表查询
	 */
	@Action(value = "findRedCuffTeamForPageResult", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String findRedCuffTeamForPageResult() throws Exception {
		StringBuilder cacheKey = null;
		if (allowCache != null && allowCache) {// 警告此处缓存仅针以下参数+分页
			cacheKey = new StringBuilder("controller_findRedCuffTeam");
			if (redCuffTeam != null && redCuffTeam.getOrganization() != null
					&& redCuffTeam.getOrganization().getId() != null) {
				cacheKey.append("_orgId_").append(redCuffTeam.getOrganization().getId());
			}
			if (redCuffTeam != null && redCuffTeam.getTeamType() != null
					&& redCuffTeam.getTeamType().getId() != null) {
				cacheKey.append("_teamType_").append(redCuffTeam.getTeamType().getId());
			}
			if (redCuffTeam != null && redCuffTeam.getSubTeamType() != null
					&& redCuffTeam.getSubTeamType().getId() != null) {
				cacheKey.append("_subTeamType_").append(redCuffTeam.getSubTeamType().getId());
			}
			if (redCuffTeam != null && redCuffTeam.getOrganization() != null
					&& redCuffTeam.getOrganization().getId() != null) {
				cacheKey.append("_orgId_").append(redCuffTeam.getOrganization().getId());
			}
			cacheKey.append("_rows_").append(rows);
			cacheKey.append("_page_").append(page);
			gridPage = (GridPage) cacheService.get(cacheKey.toString());
			if (gridPage != null) {
				return SUCCESS;
			}
		}
		if (redCuffTeam == null || redCuffTeam.getOrganization() == null
				|| redCuffTeam.getOrganization().getId() == null) {
			errorMessage = "查询失败，未获得组织机构信息";
			return ERROR;
		}
		PageInfo<RedCuffTeam> pageInfo = ControllerHelper.processAllOrgRelativeName(
				redCuffTeamService.findRedCuffTeamForList(redCuffTeam, page, rows, ids, ids),
				organizationDubboService, new String[] { "organization" }, redCuffTeam
						.getOrganization().getId());
		gridPage = new GridPage(pageInfo);
		if (allowCache != null && allowCache) {
			cacheService.set(cacheKey.toString(), 60 * 2, gridPage);
		}
		return SUCCESS;
	}

	/***
	 * 新增或修改
	 */
	@Action(value = "maintainRedCuffInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "redCuffTeam",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String maintainRedCuffInfo() throws Exception {
		if (redCuffTeam == null) {
			errorMessage = "操作失败，数据未获得";
			return ERROR;
		}
		if (redCuffTeam.getId() != null) {
			redCuffTeam = redCuffTeamService.updateRedCuffTeam(redCuffTeam);
		} else {
			redCuffTeam.setWechatNo("未认证");
			redCuffTeam = redCuffTeamService.addRedCuffTeam(redCuffTeam);
		}
		return SUCCESS;
	}

	/***
	 * 注销红袖套认证
	 */
	@Action(value = "cancellationOfCertification", results = {
			@Result(name = "success", type = "json", params = { "root", "redCuffTeam",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String cancellationOfCertification() throws Exception {
		if (ids == null) {
			errorMessage = "操作失败，数据未获得";
			return ERROR;
		}
		List<Long> idList = analyzeIds(ids);
		for (Long id : idList) {
			redCuffTeam = redCuffTeamService.getRedCuffTeamById(id);
			redCuffTeam.setIsCertification(IS_NOT_CERTIFICATION);
			redCuffTeam.setWechatNo("未认证");
			redCuffTeam = redCuffTeamService.updateRedCuffTeam(redCuffTeam);
		}
		return SUCCESS;
	}

	/***
	 * 界面跳转控制
	 */
	@Actions(value = { @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/redCuffTeam/maintainRedCuffTeamDialog.jsp"),
			@Result(name = "search", location = "/baseinfo/redCuffTeam/searchRedCuffTeamDialog.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) }) })
	public String dispatchOperate() throws Exception {
		
		if(DialogMode.SEARCH_MODE.equals(mode)){
			if (redCuffTeam == null || redCuffTeam.getOrganization() == null
					|| redCuffTeam.getOrganization().getId() == null) {
				errorMessage = "操作失败，未获得组织机构参数";
				return ERROR;
			}
			Organization organization = organizationDubboService.getSimpleOrgById(redCuffTeam
					.getOrganization().getId());
			redCuffTeam.setOrganization(organization);
			return mode;
		}
		
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (redCuffTeam == null || redCuffTeam.getOrganization() == null
					|| redCuffTeam.getOrganization().getId() == null) {
				errorMessage = "操作失败，未获得组织机构参数";
				return ERROR;
			}
			Organization organization = organizationDubboService.getSimpleOrgById(redCuffTeam
					.getOrganization().getId());
			redCuffTeam.setOrganization(organization);
			redCuffTeam.setRegisteredPerson(ThreadVariable.getUser().getName());
			redCuffTeam.setRegisteredDate(CalendarUtil.now());
			redCuffTeam.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(redCuffTeam.getOrganization()
							.getId(), organizationDubboService));
		}
		if (DialogMode.EDIT_MODE.equals(mode) || DialogMode.VIEW_MODE.equals(mode)) {
			if (redCuffTeam == null || redCuffTeam.getId() == null) {
				errorMessage = "操作失败，未获得数据信息";
				return ERROR;
			}
			redCuffTeam = redCuffTeamService.getRedCuffTeamById(redCuffTeam.getId());
			Organization organization = organizationDubboService.getSimpleOrgById(redCuffTeam
					.getOrganization().getId());
			redCuffTeam.setOrganization(organization);
			redCuffTeam.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(redCuffTeam.getOrganization()
							.getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	/***
	 * 数据删除
	 */
	@Action(value = "deleteRedCuffTeamByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String deleteRedCuffTeamByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "删除数据失败，未得到正确参数";
			return ERROR;
		}
		redCuffTeamService.deleteRedCuffTeamByIds(ids.split(","));
		return SUCCESS;
	}

	private List<Long> analyzeIds(String idStr) {
		if (idStr == null) {
			return null;
		}
		String[] deleteId = idStr.split(",");
		List<Long> idList = new ArrayList<Long>();
		if (org.tinygroup.commons.tools.StringUtil.isEmpty(deleteId[0])) {
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public RedCuffTeam getRedCuffTeam() {
		return redCuffTeam;
	}

	public void setRedCuffTeam(RedCuffTeam redCuffTeam) {
		this.redCuffTeam = redCuffTeam;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Boolean getAllowCache() {
		return allowCache;
	}

	public void setAllowCache(Boolean allowCache) {
		this.allowCache = allowCache;
	}

}
