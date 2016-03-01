package com.tianque.approval.service;

import java.util.List;

import com.tianque.approval.domain.ApprovalItemFile;

public interface ApprovalItemFileService {

	public ApprovalItemFile addApprovalItemFile(ApprovalItemFile approvalItemFile);

	public List<ApprovalItemFile> findApprovalItemFileByApprovalItemId(Long approvalItemId);

	public void deleteApprovalItemFileByApprovalItemId(Long approvalItemId);

	public void deleteApprovalItemFileById(Long id);

	public ApprovalItemFile getApprovalItemFileById(Long id);
}
