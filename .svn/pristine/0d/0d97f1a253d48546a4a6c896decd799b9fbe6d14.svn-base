package com.tianque.publicSecurity.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.publicSecurity.domain.Skynet;
import com.tianque.publicSecurity.domain.vo.SearchSkynetVo;
import com.tianque.publicSecurity.service.SkynetService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 天网表:服务前端控制类
 * 
 * @author
 * @date 2014-02-10 14:21:16
 */
@Namespace("/skynetManage")
@Scope("request")
@Controller("skynetController")
public class SkynetController extends BaseAction {

	@Autowired
	private SkynetService skynetService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Skynet skynet;
	private SearchSkynetVo searchSkynetVo;
	private String ids;
	private Organization organization;
	private String promptMessage;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/skynetManagement/maintainSkynetDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/skynetManagement/skynetViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			skynet = new Skynet();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			skynet.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (skynet == null || skynet.getId() == null) {
				return ERROR;
			}
			skynet = skynetService.get(skynet.getId());
			skynet.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									skynet.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewSkynet();
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转(解密调用)
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/skynetManagement/maintainSkynetDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/skynetManagement/skynetViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (skynet == null || skynet.getId() == null) {
				return ERROR;
			}
			skynet = skynetService.get(skynet.getId());
			skynet.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									skynet.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewSkynet();
		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewSkynet() throws Exception {
		if (skynet == null || skynet.getId() == null) {
			return ERROR;
		}
		skynet = skynetService.get(skynet.getId());
		skynet.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								skynet.getOrganization().getId(),
								OrganizationServiceHelper.getRootOrg(
										organizationDubboService).getId()));
		return "view";
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addSkynet", results = {
			@Result(name = "success", type = "json", params = { "root",
					"skynet", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addSkynet")
	public String addSkynet() throws Exception {
		if (skynet == null || !checkOrganization(skynet.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		skynet.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				skynet.getOrganization().getId()).getOrgInternalCode());
		skynet = skynetService.add(skynet);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateSkynet", results = {
			@Result(name = "success", type = "json", params = { "root",
					"skynet", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateSkynet")
	public String updateSkynet() throws Exception {
		if (skynet == null || skynet.getId() == null
				|| !checkOrganization(skynet.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		skynet = skynetService.update(skynet);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteSkynetByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteSkynet")
	public String deleteSkynetByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		skynetService.delete(idsLong);
		return SUCCESS;
	}

	private Long[] splitArray() {
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		return idsLong;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findSkynetPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "skynetManagement")
	public String findSkynetPagerBySearchVo() throws Exception {
		if (searchSkynetVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchSkynetVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				skynetService.findPagerBySearchVo(searchSkynetVo, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, null));
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateSkynetNo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateSkynetNo() throws Exception {
		if (skynet == null || skynet.getSkynetNo() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = skynetService.hasDuplicateSkynetNo(skynet.getId(),
					skynet.getOrganization().getId(), skynet.getSkynetNo());
		}

		return SUCCESS;
	}

	/**
	 * 关注、取消关注
	 * 
	 * @return
	 */

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root", "ids",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要关注(或取消关注)的记录!";
			return ERROR;
		}
		skynetService.updateEmphasiseByIds(idsLong, skynet.getIsEmphasis() ? 1L
				: 0L, skynet.getLogoutReason(), skynet.getLogoutTime());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root", "ids",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要关注(或取消关注)的记录!";
			return ERROR;
		}
		skynetService.updateEmphasiseByIds(idsLong, skynet.getIsEmphasis() ? 1L
				: 0L, skynet.getLogoutReason(), skynet.getLogoutTime());
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public Skynet getSkynet() {
		return skynet;
	}

	public void setSkynet(Skynet skynet) {
		this.skynet = skynet;
	}

	public SearchSkynetVo getSearchSkynetVo() {
		return searchSkynetVo;
	}

	public void setSearchSkynetVo(SearchSkynetVo searchSkynetVo) {
		this.searchSkynetVo = searchSkynetVo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPromptMessage() {
		return promptMessage;
	}

	public void setPromptMessage(String promptMessage) {
		this.promptMessage = promptMessage;
	}

}
