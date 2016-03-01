package com.tianque.approval.service;

import java.util.List;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;

public interface ApprovalItemService {

	public void approval(Long id, IssueLogNew issueLog, Long approval);

	public void complete(String approvalNumber);

	public void verification(String approvalNumber);

	public List<ApprovalItem> findApprovalItemByItemId(Long itemId);

	public ApprovalItem addApprovalItem(ApprovalItem approvalItem,
			String[] attachFiles);

	public ApprovalItem approvalItemToIssue(IssueNew issue,
			ApprovalItem approvalItem, String[] attachFiles);

	public ApprovalItem auditItemPassToIssue(IssueNew issue,
			ApprovalItem approvalItem, String[] attachFiles);

	public ApprovalItem updateApprovalItem(ApprovalItem approvalItem,
			String[] attachFiles);

	public ApprovalItem getApprovalItemById(Long id);

	public ApprovalItem getApprovalItemByApprovalNumber(String approvalNumber);

	public void deleteApprovalItemById(Long id);

	public void deleteApprovalItemByApprovalNumber(String approvalNumber);

	public PageInfo<ApprovalItem> findApprovalItemPage(String searchtxt,
			ApprovalItemVo approvalItemVo, Integer pageNum, Integer pageSize,
			String sortField, String order);

	public IssueNew getIssueBySerialNumber(String approvalNumber);

}
