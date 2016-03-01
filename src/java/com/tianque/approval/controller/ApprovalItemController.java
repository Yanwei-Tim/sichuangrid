package com.tianque.approval.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.ItemAttachment;
import com.tianque.approval.domain.property.ApprovalItemStatus;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.approval.service.ApprovalItemFileService;
import com.tianque.approval.service.ApprovalItemService;
import com.tianque.approval.service.ItemAttachmentService;
import com.tianque.approval.service.ItemService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.sysadmin.service.PermissionService;

@Namespace("/approval/approvalItemManage")
@Transactional
@Scope("prototype")
@Controller("approvalItemController")
public class ApprovalItemController extends BaseAction {

	@Autowired
	private ItemAttachmentService itemAttachmentService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ApprovalItemService approvalItemService;
	@Autowired
	private ApprovalItemFileService approvalItemFileService;
	@Autowired
	private PermissionService permissionService;

	private List<ItemAttachment> itemAttachmentList;
	private ApprovalItem approvalItem;
	private Long id;
	private Item item;
	private Long itemId;
	private String[] attachFiles;
	private Organization organization;
	private ApprovalItemVo approvalItemVo;
	private List<ApprovalItemFile> approvalItemFileList;
	private Long approval;
	private IssueLogNew issueLog;
	private IssueNew issue;
	private String selectOrgName;
	private List<IssueAttachFile> issueAttachFiles = new ArrayList<IssueAttachFile>();
	private String searchtxt;

	@Action(value = "approval", results = { @Result(name = "success", type = "json", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String approval() {
		approvalItemService.approval(id, issueLog, approval);
		return SUCCESS;
	}

	@Action(value = "findApprovalItemPage", results = {
			@Result(name = "success", location = "/approval/approvalItem/simpleApprovalItemList.jsp"),
			@Result(name = "audit", type = "json", params = { "root",
					"gridPage" }) })
	public String findApprovalItemPage() {
		if (ApprovalItemStatus.PENDING_REVIEW
				.equals(approvalItemVo.getStatus())) {
			approvalItemVo.setOrganization(ThreadVariable.getOrganization());
		} else {
			approvalItemVo.setCreateOrg(ThreadVariable.getOrganization());
		}
		gridPage = new GridPage(approvalItemService.findApprovalItemPage(
				searchtxt, approvalItemVo, page, rows, sidx, sord));
		if ("audit".equals(mode)) {
			return "audit";
		}
		return SUCCESS;
	}

	@Action(value = "dispath", results = {
			@Result(name = "add", location = "/approval/approvalItem/approvalItemDlg.jsp"),
			@Result(name = "update", location = "/approval/approvalItem/approvalItemDlg.jsp"),
			@Result(name = "view", location = "/approval/approvalItem/approvalItemViewDlg.jsp"),
			@Result(name = "audit", location = "/approval/audit/auditItemDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispath() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			item = itemService.getItemById(itemId);
			itemAttachmentList = itemAttachmentService
					.findItemAttachmentByItemId(item.getId());
			organization = new Organization();
			organization.setId(ThreadVariable.getOrganization().getId());
			organization.setOrgName(ThreadVariable.getOrganization()
					.getOrgName());
			return "add";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			approvalItem = approvalItemService.getApprovalItemById(id);
			approvalItemFileList = approvalItemFileService
					.findApprovalItemFileByApprovalItemId(id);
			item = itemService.getItemById(approvalItem.getItem().getId());
			itemAttachmentList = itemAttachmentService
					.findItemAttachmentByItemId(item.getId());
			return "update";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			approvalItem = approvalItemService.getApprovalItemById(id);
			approvalItemFileList = approvalItemFileService
					.findApprovalItemFileByApprovalItemId(id);
			item = itemService.getItemById(approvalItem.getItem().getId());
			itemAttachmentList = itemAttachmentService
					.findItemAttachmentByItemId(item.getId());
			return "view";
		} else if ("audit".equals(mode)) {
			approvalItem = approvalItemService.getApprovalItemById(id);
			User user = permissionService.getSimpleUserById(ThreadVariable
					.getUser().getId());
			issueLog = new IssueLogNew();
			issueLog.setDealUserName(user.getName());
			issueLog.setMobile(user.getMobile());
			return "audit";
		}
		return SUCCESS;
	}

	/** 事项申请转入的事件 */
	@Action(value = "approvalItemToIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "approvalItem", "ignoreHierarchy", "false" }) })
	public String approvalItemToIssue() {
		approvalItem = approvalItemService.approvalItemToIssue(issue,
				approvalItem, attachFiles);
		return SUCCESS;
	}

	@Action(value = "auditItemPassToIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "approvalItem", "ignoreHierarchy", "false" }) })
	public String auditItemPassToIssue() {
		approvalItem = approvalItemService.auditItemPassToIssue(issue,
				approvalItem, attachFiles);
		return SUCCESS;
	}

	@Action(value = "addApprovalItem", results = { @Result(name = "success", type = "json", params = {
			"root", "approvalItem", "ignoreHierarchy", "false" }) })
	public String addApprovalItem() {
		approvalItem = approvalItemService.addApprovalItem(approvalItem,
				attachFiles);
		return SUCCESS;
	}

	@Action(value = "deleteApprovalItemById", results = { @Result(name = "success", type = "json", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String deleteApprovalItemById() {
		approvalItemService.deleteApprovalItemById(id);
		return SUCCESS;
	}

	@Action(value = "updateApprovalItem", results = { @Result(name = "success", type = "json", params = {
			"root", "approvalItem", "ignoreHierarchy", "false" }) })
	public String updateApprovalItem() {
		approvalItem = approvalItemService.updateApprovalItem(approvalItem,
				attachFiles);
		return SUCCESS;
	}

	@Action(value = "getApprovalItemById", results = { @Result(name = "success", type = "json", params = {
			"root", "approvalItem", "ignoreHierarchy", "false" }) })
	public String getApprovalItemById() {
		approvalItem = approvalItemService.getApprovalItemById(id);
		return SUCCESS;
	}

	public ApprovalItemService getApprovalItemService() {
		return approvalItemService;
	}

	public void setApprovalItemService(ApprovalItemService approvalItemService) {
		this.approvalItemService = approvalItemService;
	}

	public ApprovalItem getApprovalItem() {
		return approvalItem;
	}

	public void setApprovalItem(ApprovalItem approvalItem) {
		this.approvalItem = approvalItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemAttachment> getItemAttachmentList() {
		return itemAttachmentList;
	}

	public void setItemAttachmentList(List<ItemAttachment> itemAttachmentList) {
		this.itemAttachmentList = itemAttachmentList;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public ApprovalItemVo getApprovalItemVo() {
		return approvalItemVo;
	}

	public void setApprovalItemVo(ApprovalItemVo approvalItemVo) {
		this.approvalItemVo = approvalItemVo;
	}

	public List<ApprovalItemFile> getApprovalItemFileList() {
		return approvalItemFileList;
	}

	public void setApprovalItemFileList(
			List<ApprovalItemFile> approvalItemFileList) {
		this.approvalItemFileList = approvalItemFileList;
	}

	public Long getApproval() {
		return approval;
	}

	public void setApproval(Long approval) {
		this.approval = approval;
	}

	public IssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(IssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public List<IssueAttachFile> getIssueAttachFiles() {
		return issueAttachFiles;
	}

	public void setIssueAttachFiles(List<IssueAttachFile> issueAttachFiles) {
		this.issueAttachFiles = issueAttachFiles;
	}

	public String getSelectOrgName() {
		return selectOrgName;
	}

	public void setSelectOrgName(String selectOrgName) {
		this.selectOrgName = selectOrgName;
	}

	public String getSearchtxt() {
		return searchtxt;
	}

	public void setSearchtxt(String searchtxt) {
		this.searchtxt = searchtxt;
	}

}
