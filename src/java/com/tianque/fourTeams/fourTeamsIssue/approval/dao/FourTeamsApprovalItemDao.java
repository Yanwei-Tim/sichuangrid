package com.tianque.fourTeams.fourTeamsIssue.approval.dao;

import java.util.List;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;

public interface FourTeamsApprovalItemDao extends BaseDao<ApprovalItem, ApprovalItem> {

	public void updateApprovalItemByApprovalNumber(Long status, String approvalNumber);

	public PageInfo<ApprovalItem> findApprovalItemPage(String searchtxt,
			ApprovalItemVo approvalItemVo, Integer pageNum, Integer pageSize, String sortField,
			String order);

	public ApprovalItem getApprovalItemByApprovalNumber(String approvalNumber);

	public List<ApprovalItem> findApprovalItemByItemId(Long itemId);

	public void deleteApprovalItemByApprovalNumber(String approvalNumber);
}
