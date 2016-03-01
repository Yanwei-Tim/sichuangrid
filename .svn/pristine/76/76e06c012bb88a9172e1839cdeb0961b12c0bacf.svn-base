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
import com.tianque.publicSecurity.domain.VideoSystem;
import com.tianque.publicSecurity.domain.vo.SearchVideoSystemVo;
import com.tianque.publicSecurity.service.VideoSystemService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 监控视频系统表:服务前端控制类
 */
@Namespace("/videoSystemManage")
@Scope("request")
@Controller("videoSystemController")
public class VideoSystemController extends BaseAction {

	@Autowired
	private VideoSystemService videoSystemService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private VideoSystem videoSystem;
	private SearchVideoSystemVo searchVideoSystemVo;
	private String ids;
	private Organization organization;
	private String promptMessage;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/videoSystemManagement/maintainVideoSystemDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/videoSystemManagement/videoSystemViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			videoSystem = new VideoSystem();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(organization.getId(),
							OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));
			videoSystem.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (videoSystem == null || videoSystem.getId() == null) {
				return ERROR;
			}
			videoSystem = videoSystemService.get(videoSystem.getId());
			videoSystem.getOrganization().setOrgName(
					organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(videoSystem
							.getOrganization().getId(),
							OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewVideoSystem();
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转(解密)
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/videoSystemManagement/maintainVideoSystemDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/videoSystemManagement/videoSystemViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (videoSystem == null || videoSystem.getId() == null) {
				return ERROR;
			}
			videoSystem = videoSystemService.get(videoSystem.getId());
			videoSystem.getOrganization().setOrgName(
					organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(videoSystem
							.getOrganization().getId(),
							OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewVideoSystem();
		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewVideoSystem() throws Exception {
		if (videoSystem == null || videoSystem.getId() == null) {
			return ERROR;
		}
		videoSystem = videoSystemService.get(videoSystem.getId());
		videoSystem.getOrganization().setOrgName(
				organizationDubboService.getOrganizationRelativeNameByRootOrgIdAndOrgId(videoSystem
						.getOrganization().getId(),
						OrganizationServiceHelper.getRootOrg(organizationDubboService).getId()));
		return "view";
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addVideosystem", results = {
			@Result(name = "success", type = "json", params = { "root", "videoSystem",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "addVideoSystem")
	public String addVideosystem() throws Exception {
		if (videoSystem == null || !checkOrganization(videoSystem.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		videoSystem.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				videoSystem.getOrganization().getId()).getOrgInternalCode());
		videoSystem = videoSystemService.add(videoSystem);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateVideosystem", results = {
			@Result(name = "success", type = "json", params = { "root", "videoSystem",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "updateVideoSystem")
	public String updateVideosystem() throws Exception {
		if (videoSystem == null || videoSystem.getId() == null
				|| !checkOrganization(videoSystem.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		videoSystem = videoSystemService.update(videoSystem);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteVideosystemByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteVideoSystem")
	public String deleteVideosystemByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		videoSystemService.delete(idsLong);
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
	@Action(value = "findVideosystemPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "videoSystemManagement")
	public String findVideosystemPagerBySearchVo() throws Exception {
		if (searchVideoSystemVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchVideoSystemVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(videoSystemService.findPagerBySearchVo(
						searchVideoSystemVo, page, rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, null));
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateVideoNo", results = {
			@Result(name = "success", type = "json", params = { "root", "promptMessage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateVideoNo() throws Exception {
		if (videoSystem == null || videoSystem.getVideoNo() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = videoSystemService.hasDuplicateVideoNo(videoSystem.getId(), videoSystem
					.getOrganization().getId(), videoSystem.getVideoNo());
		}
		return SUCCESS;
	}

	/**
	 * 关注、取消关注
	 * 
	 * @return
	 */

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root", "ids", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要关注(或取消关注)的记录!";
			return ERROR;
		}
		videoSystemService.updateEmphasiseByIds(idsLong, videoSystem.getIsEmphasis() ? 1L : 0L,
				videoSystem.getLogoutReason(), videoSystem.getLogoutTime());
		return SUCCESS;
	}

	/**
	 * ID加密 取消关注
	 * 
	 * @param org
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root", "ids", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要关注(或取消关注)的记录!";
			return ERROR;
		}
		videoSystemService.updateEmphasiseByIds(idsLong, videoSystem.getIsEmphasis() ? 1L : 0L,
				videoSystem.getLogoutReason(), videoSystem.getLogoutTime());
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public VideoSystem getVideoSystem() {
		return videoSystem;
	}

	public void setVideoSystem(VideoSystem videoSystem) {
		this.videoSystem = videoSystem;
	}

	public SearchVideoSystemVo getSearchVideoSystemVo() {
		return searchVideoSystemVo;
	}

	public void setSearchVideoSystemVo(SearchVideoSystemVo searchVideoSystemVo) {
		this.searchVideoSystemVo = searchVideoSystemVo;
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
