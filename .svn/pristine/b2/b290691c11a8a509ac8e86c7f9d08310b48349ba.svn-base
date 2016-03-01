package com.tianque.workBench.publicNotice.controller;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.publicNotice.domain.PublicNotice;
import com.tianque.publicNotice.domain.PublicNoticeAttachFiles;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.Notice4ShowDubboService;

@Namespace("/workBench/noticeManage")
@Transactional
@Scope("request")
@Controller("noticeController")
public class noticeController extends BaseAction {
	private PublicNotice publicNotice;
	private Organization organization;
	private List<PublicNotice> notices;
	private Long fileId;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private Notice4ShowDubboService notice4ShowDubboService;

	/**
	 * 查找publicNotice用于显示
	 */
	@Action(value = "findNoticeByOrgCode", results = { @Result(name = "success", location = "/newWorkBench/noticeList.jsp") })
	public String findNoticeByOrgCode() throws Exception {
		String orgCode = ThreadVariable.getSession().getOrgInternalCode();
		notices = notice4ShowDubboService.findNoticeByOrgCode(orgCode);
		return SUCCESS;
	}

	/**
	 * 查找publicNotice用于通知通报历史显示
	 */
	@Action(value = "findNoticeForHistoryByOrgCode", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findNoticeForHistoryByOrgCode() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				notice4ShowDubboService.findNoticeForHistoryByOrgCode(
						ThreadVariable.getSession().getOrgInternalCode(), page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, ThreadVariable.getSession()
						.getOrganization().getId()));
		return SUCCESS;
	}

	/**
	 * 页面功能跳转
	 */

	@Action(value = "dispatchOperate", results = {
			@Result(name = "view", location = "/workBench/noticeDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		publicNotice = notice4ShowDubboService.findNoticeById(publicNotice
				.getId());
		if (publicNotice == null) {
			return "error";
		} else {
			publicNotice.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(publicNotice
							.getOrganization().getId(),
							organizationDubboService));
			return "view";
		}
	}

	@Action(value = "downLoadActualNoticeFile", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downLoadActualNoticeFile() throws Exception {
		PublicNoticeAttachFiles noticeFile = notice4ShowDubboService
				.getPublicNoticeAttachFilesById(fileId);
		if (noticeFile == null) {
			this.errorMessage = "您下载的文件不存在!";
			return ERROR;
		}
		String downFilePath = FileUtil.getWebRoot() + File.separator
				+ noticeFile.getFileActualUrl();
		downloadFileName = new String(noticeFile.getFileName().getBytes("gbk"),
				"ISO8859-1");
		inputStream = new java.io.FileInputStream(new File(downFilePath));
		return STREAM_SUCCESS;
	}

	public PublicNotice getPublicNotice() {
		return publicNotice;
	}

	public void setPublicNotice(PublicNotice publicNotice) {
		this.publicNotice = publicNotice;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<PublicNotice> getNotices() {
		return notices;
	}

	public void setNotices(List<PublicNotice> notices) {
		this.notices = notices;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

}
