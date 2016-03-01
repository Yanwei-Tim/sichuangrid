package com.tianque.approval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.dao.ApprovalItemFileDao;
import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;

@Service("approvalItemFileService")
@Transactional
public class ApprovalItemFileServiceImpl implements ApprovalItemFileService {

	@Qualifier("approvalItemFileValidator")
	@Autowired
	private DomainValidator<ApprovalItemFile> approvalItemFileValidator;

	@Autowired
	private ApprovalItemFileDao approvalItemFileDao;

	@Override
	public ApprovalItemFile addApprovalItemFile(
			ApprovalItemFile approvalItemFile) {
		// TODO Auto-generated method stub
		ValidateResult baseDataValidator = approvalItemFileValidator
				.validate(approvalItemFile);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		return approvalItemFileDao.add(approvalItemFile);
	}

	@Override
	public void deleteApprovalItemFileById(Long id) {
		// TODO Auto-generated method stub
		if (null == id || id < 0) {
			return;
		}
		approvalItemFileDao.delete(id);
	}

	@Override
	public List<ApprovalItemFile> findApprovalItemFileByApprovalItemId(
			Long approvalItemId) {
		// TODO Auto-generated method stub
		if (null == approvalItemId || approvalItemId < 0) {
			return null;
		}
		return approvalItemFileDao
				.findApprovalItemFileByApprovalItemId(approvalItemId);
	}

	@Override
	public ApprovalItemFile getApprovalItemFileById(Long id) {
		// TODO Auto-generated method stub
		if (null == id || id < 0) {
			return null;
		}
		return approvalItemFileDao.get(id);
	}

	@Override
	public void deleteApprovalItemFileByApprovalItemId(Long approvalItemId) {
		approvalItemFileDao
				.deleteApprovalItemFileByApprovalItemId(approvalItemId);
	}

}
