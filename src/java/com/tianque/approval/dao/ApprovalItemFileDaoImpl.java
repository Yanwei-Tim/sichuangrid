package com.tianque.approval.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.approval.domain.ApprovalItemFile;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;

@Repository("approvalItemFileDao")
public class ApprovalItemFileDaoImpl extends
		BaseDaoImpl<ApprovalItemFile, ApprovalItemFile> implements
		ApprovalItemFileDao {

	@Qualifier("approvalItemFileValidator")
	@Autowired
	private DomainValidator<ApprovalItemFile> approvalItemFileValidator;

	@Override
	protected void checkEntityWhenAdd(ApprovalItemFile entity) {
		ValidateResult baseDataValidator = approvalItemFileValidator
				.validate(entity);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	protected void checkEntityWhenUpdate(ApprovalItemFile entity) {
	}

	@Override
	public List<ApprovalItemFile> findApprovalItemFileByApprovalItemId(
			Long approvalItemId) {
		if (null == approvalItemId || approvalItemId < 0) {
			return null;
		}
		return this.getSqlMapClientTemplate().queryForList(
				"approvalItemFile.findApprovalItemFileByApprovalItemId",
				approvalItemId);
	}

	@Override
	public void deleteApprovalItemFileByApprovalItemId(Long approvalItemId) {
		getSqlMapClientTemplate().delete(
				"approvalItemFile.deleteApprovalItemFileByApprovalItemId",
				approvalItemId);
	}

}
