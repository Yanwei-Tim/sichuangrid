package com.tianque.fourTeams.fourTeamsIssue.approval.service;

import java.util.List;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;

public interface FourTeamsApprovalItemService {

	public void approval(Long id, FourTeamsIssueLogNew issueLog, Long approval);

	public void complete(String approvalNumber);

	public List<ApprovalItem> findApprovalItemByItemId(Long itemId);

	public ApprovalItem addApprovalItem(ApprovalItem approvalItem,
			String[] attachFiles);

	public ApprovalItem approvalItemToIssue(FourTeamsIssueNew issue,
			ApprovalItem approvalItem, String[] attachFiles);

	public ApprovalItem auditItemPassToIssue(FourTeamsIssueNew issue,
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

	public FourTeamsIssueNew getIssueBySerialNumber(String approvalNumber);

}
