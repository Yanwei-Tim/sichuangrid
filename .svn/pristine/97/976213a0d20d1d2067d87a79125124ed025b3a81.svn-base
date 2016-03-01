package com.tianque.publicSecurity.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tianque.publicSecurity.domain.Bayonet;
import com.tianque.publicSecurity.domain.vo.SearchBayonetVo;
import com.tianque.publicSecurity.service.BayonetService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 卡口表:服务前端控制类
 * 
 * @author
 * @date 2014-02-11 10:44:36
 */
@Namespace("/bayonetManage")
@Scope("request")
@Controller("bayonetController")
public class BayonetController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(BayonetController.class);

	@Autowired
	private BayonetService bayonetService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Bayonet bayonet;
	private SearchBayonetVo searchBayonetVo;
	private String ids;
	private Organization organization;
	private String promptMessage;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/bayonetManagement/maintainBayonetDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/bayonetManagement/bayonetViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			bayonet = new Bayonet();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			bayonet.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (bayonet == null || bayonet.getId() == null) {
				return ERROR;
			}
			bayonet = bayonetService.get(bayonet.getId());
			bayonet.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									bayonet.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewBayonet();
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/bayonetManagement/maintainBayonetDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/bayonetManagement/bayonetViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (bayonet == null || bayonet.getId() == null) {
				return ERROR;
			}
			bayonet = bayonetService.get(bayonet.getId());
			bayonet.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									bayonet.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewBayonet();
		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewBayonet() throws Exception {
		if (bayonet == null || bayonet.getId() == null) {
			return ERROR;
		}
		bayonet = bayonetService.get(bayonet.getId());
		bayonet.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								bayonet.getOrganization().getId(),
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
	@Action(value = "addBayonet", results = {
			@Result(name = "success", type = "json", params = { "root",
					"bayonet", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addBayonet")
	public String addBayonet() throws Exception {
		if (bayonet == null || !checkOrganization(bayonet.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		bayonet.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				bayonet.getOrganization().getId()).getOrgInternalCode());
		bayonet = bayonetService.add(bayonet);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateBayonet", results = {
			@Result(name = "success", type = "json", params = { "root",
					"bayonet", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateBayonet")
	public String updateBayonet() throws Exception {
		if (bayonet == null || bayonet.getId() == null
				|| !checkOrganization(bayonet.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		bayonet = bayonetService.update(bayonet);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteBayonetByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteBayonet")
	public String deleteBayonetByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		bayonetService.delete(idsLong);
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
	@Action(value = "findBayonetPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "bayonetManagement")
	public String findBayonetPagerBySearchVo() throws Exception {
		if (searchBayonetVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchBayonetVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				bayonetService.findPagerBySearchVo(searchBayonetVo, page, rows,
						sidx, sord), organizationDubboService,
				new String[] { "organization" }, null));
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateBayonetNo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateBayonetNo() throws Exception {
		if (bayonet == null || bayonet.getBayonetNo() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = bayonetService.hasDuplicateBayonetNo(
					bayonet.getId(), bayonet.getOrganization().getId(),
					bayonet.getBayonetNo());
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
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String updateEmphasiseById() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			return ERROR;
		}
		bayonetService.updateEmphasiseByIds(idsLong,
				bayonet.getIsEmphasis() ? 1L : 0L, bayonet.getLogoutReason(),
				bayonet.getLogoutTime());
		return SUCCESS;
	}

	/**
	 * ID 加密重新关注
	 * 
	 * @param org
	 * 
	 * @return
	 */
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
		bayonetService.updateEmphasiseByIds(idsLong,
				bayonet.getIsEmphasis() ? 1L : 0L, bayonet.getLogoutReason(),
				bayonet.getLogoutTime());
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public Bayonet getBayonet() {
		return bayonet;
	}

	public void setBayonet(Bayonet bayonet) {
		this.bayonet = bayonet;
	}

	public SearchBayonetVo getSearchBayonetVo() {
		return searchBayonetVo;
	}

	public void setSearchBayonetVo(SearchBayonetVo searchBayonetVo) {
		this.searchBayonetVo = searchBayonetVo;
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
