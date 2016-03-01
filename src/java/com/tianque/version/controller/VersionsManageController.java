package com.tianque.version.controller;

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
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.version.domain.Versions;
import com.tianque.version.service.VersionsService;

/**
 * @author 杨立忠
 * @version V1.0
 * @创建时间：2013-5-13 下午03:17:42
 * @功能 管理版本信息
 */

@Namespace("/sysadmin/versionsManage")
@Transactional
@Scope("request")
@Controller("versionsManageController")
public class VersionsManageController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(VersionsManageController.class);
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private VersionsService versionsService;

	private Versions versions;
	private String oldVersionsId;

	@Action(value = "versionsList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getvVersionsList() throws Exception {
		Session session = ThreadVariable.getSession();
		User user = permissionService.getSimpleUserById(session.getUserId());
		Organization org = organizationDubboService.getFullOrgById(user
				.getOrganization().getId());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				versionsService.findGridPage(this.page, this.rows, this.sidx,
						this.sord), organizationDubboService, new String[] {
						"organization", "claimOrg" }, org.getId()));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "maintainVersions", location = "/sysadmin/versioneManage/maintainVersionsDlg.jsp"),
			@Result(name = "update", location = "/sysadmin/versioneManage/maintainVersionsDlg.jsp"),
			@Result(name = "view", location = "/sysadmin/versioneManage/versionesView.jsp") })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintainVersions";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			versions = versionsService.getVersionsById(versions.getVersionId());
			return "update";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			versions = versionsService.getVersionsById(versions.getVersionId());
			return "view";
		}
		return SUCCESS;
	}

	@Action(value = "maintainVersions", results = {
			@Result(type = "json", name = "success", params = { "root",
					"versions", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainVersions() throws Exception {
		if (!dataValidate()) {
			return ERROR;
		}
		// 增加
		if ("add".equals(mode)) {
			if (versionsService.getVersionsById(versions.getVersionId()) != null) {
				this.errorMessage = "版本号不能重复！";
				return ERROR;
			}
			versions = versionsService.addVersion(versions);
			// 修改
		} else if ("edit".equals(mode)) {
			if (!versions.getVersionId().equals(oldVersionsId)) {
				if (versionsService.getVersionsById(versions.getVersionId()) != null) {
					this.errorMessage = "版本号不能重复！";
					return ERROR;
				}
			}
			versionsService.updateVersion(versions, oldVersionsId);
			versions = versionsService.getVersionsById(versions.getVersionId());
		}

		return SUCCESS;
	}

	private boolean dataValidate() {
		if (versions.getVersionId() == null
				|| "".equals(versions.getVersionId())) {
			this.errorMessage = "版本号必填";
			return false;
		}
		if (versions.getVersionContent() == null
				|| "".equals(versions.getVersionContent())) {
			this.errorMessage = "版本说明必填";
			return false;
		}
		return true;
	}

	public Versions getVersions() {
		return versions;
	}

	public void setVersions(Versions versions) {
		this.versions = versions;
	}

	public String getOldVersionsId() {
		return oldVersionsId;
	}

	public void setOldVersionsId(String oldVersionsId) {
		this.oldVersionsId = oldVersionsId;
	}

}
