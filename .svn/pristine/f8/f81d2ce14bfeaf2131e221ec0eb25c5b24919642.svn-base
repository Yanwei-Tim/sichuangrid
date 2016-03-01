package com.tianque.issueAbutmentJoint.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.issueAbutmentJoint.constants.IssueJointTypeInternalId;
import com.tianque.issueAbutmentJoint.domain.IssueJoint;
import com.tianque.issueAbutmentJoint.domain.IssueJointAttachfile;
import com.tianque.issueAbutmentJoint.domain.IssueJointLog;
import com.tianque.issueAbutmentJoint.domain.vo.IssueJointVo;
import com.tianque.issueAbutmentJoint.service.IssueJointAttachfileService;
import com.tianque.issueAbutmentJoint.service.IssueJointLogService;
import com.tianque.issueAbutmentJoint.service.IssueJointService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @Description:对接事件控制层
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-24 下午10:36:16
 */
@Controller("issueJointController")
@Namespace("/issueJointManage")
@Scope("request")
public class IssueJointController extends BaseAction {
	public final static Logger logger = LoggerFactory
			.getLogger(IssueJointController.class);

	private IssueJointVo issueJointVo;
	private IssueJoint issueJoint;
	private List<IssueJointLog> issueJointLogs;
	@Autowired
	private IssueJointService issueJointService;
	@Autowired
	private IssueJointLogService issueJointLogService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IssueJointAttachfileService issueJointAttachfileService;
	private Integer orgTypeInternalid;
	private List<IssueJointAttachfile> issueJointAttachfiles = new ArrayList<IssueJointAttachfile>();
	private List<IssueJointAttachfile> issueJointLogAttachfiles = new ArrayList<IssueJointAttachfile>();
	private Long issueJointAttachfileId;

	/**
	 * 下载事件附件
	 * 
	 * @return
	 */
	@Action(value = "downLoadAttachFile", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downLoadActualFile() throws Exception {

		IssueJointAttachfile file = issueJointAttachfileService
				.findIssueJointAttachfileById(issueJointAttachfileId);
		if (file != null) {
			try {
				inputStream = new java.io.FileInputStream(createStoreFile(
						file.getPhysicsFullFileName(), file.getFileName()));
			} catch (Exception e) {
				this.errorMessage = e.getMessage();
				return ERROR;
			}

		}
		return "streamSuccess";
	}

	private File createStoreFile(String path, String fileName)
			throws IOException {
		String downFilePath = FileUtil.getWebRoot() + File.separator + path;
		downloadFileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
		File storedFile = new File(downFilePath);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	/***
	 * 验证登陆的用户的组织机构类型
	 * 
	 * @return
	 */
	@Action(value = "getLoginUserOrgType", results = { @Result(type = "json", name = "success", params = {
			"root", "orgTypeInternalid", "ignoreHierarchy", "false" }) })
	public String getLoginUserOrgType() throws Exception {
		Organization org = ThreadVariable.getOrganization();
		if (org != null && org.getOrgType() != null) {
			PropertyDict dict = propertyDictService.getPropertyDictById(org
					.getOrgType().getId());
			if (dict != null) {
				orgTypeInternalid = dict.getInternalId();
			}
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "issueAbutmentJointManagement")
	@Action(value = "searchIssueJointForList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchIssueJointForList() throws Exception {
		if (issueJointVo == null || issueJointVo.getCreateOrg() == null
				|| issueJointVo.getCreateOrg().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		PageResult<IssueJoint> pageResult = issueJointService
				.queryIssueJointByIssueJointVoForPageResult(issueJointVo, sidx,
						sord, page, rows);
		gridPage = new GridPage(pageResult);
		return SUCCESS;
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "view", location = "/issueAbutmentJointManage/viewIssueJointDlg.jsp"),
			@Result(name = "search", location = "/issueAbutmentJointManage/searchIssueJointDlg.jsp") })
	public String dispatchOperate() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			if (issueJoint == null || issueJoint.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			issueJoint = issueJointService
					.getIssueJointById(issueJoint.getId());
			issueJointLogs = issueJointLogService
					.queryIssueJointLogByIssueJointIdForList(issueJoint.getId());
			issueJointAttachfiles = issueJointAttachfileService
					.queryIssueJointAttachfileByIssueIdForList(
							issueJoint.getId(),
							IssueJointTypeInternalId.ADD_ISSUE);
			issueJointLogAttachfiles = issueJointAttachfileService
					.queryIssueJointAttachfileByIssueIdForList(
							issueJoint.getId(),
							IssueJointTypeInternalId.COMPLETE_ISSUE);
		}
		return mode;
	}

	public IssueJointVo getIssueJointVo() {
		return issueJointVo;
	}

	public void setIssueJointVo(IssueJointVo issueJointVo) {
		this.issueJointVo = issueJointVo;
	}

	public IssueJoint getIssueJoint() {
		return issueJoint;
	}

	public void setIssueJoint(IssueJoint issueJoint) {
		this.issueJoint = issueJoint;
	}

	public List<IssueJointLog> getIssueJointLogs() {
		return issueJointLogs;
	}

	public void setIssueJointLogs(List<IssueJointLog> issueJointLogs) {
		this.issueJointLogs = issueJointLogs;
	}

	public Integer getOrgTypeInternalid() {
		return orgTypeInternalid;
	}

	public void setOrgTypeInternalid(Integer orgTypeInternalid) {
		this.orgTypeInternalid = orgTypeInternalid;
	}

	public List<IssueJointAttachfile> getIssueJointAttachfiles() {
		return issueJointAttachfiles;
	}

	public void setIssueJointAttachfiles(
			List<IssueJointAttachfile> issueJointAttachfiles) {
		this.issueJointAttachfiles = issueJointAttachfiles;
	}

	public List<IssueJointAttachfile> getIssueJointLogAttachfiles() {
		return issueJointLogAttachfiles;
	}

	public void setIssueJointLogAttachfiles(
			List<IssueJointAttachfile> issueJointLogAttachfiles) {
		this.issueJointLogAttachfiles = issueJointLogAttachfiles;
	}

	public Long getIssueJointAttachfileId() {
		return issueJointAttachfileId;
	}

	public void setIssueJointAttachfileId(Long issueJointAttachfileId) {
		this.issueJointAttachfileId = issueJointAttachfileId;
	}

}
