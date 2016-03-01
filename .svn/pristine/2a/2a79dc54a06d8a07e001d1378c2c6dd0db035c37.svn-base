package com.tianque.xichang.working.flow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.xichang.working.flow.dao.EvaluateFeedbacksDao;
import com.tianque.xichang.working.flow.domain.EvaluateFeedbacks;
import com.tianque.xichang.working.flow.service.EvaluateFeedbacksService;

@Service("evaluateFeedbacksService")
@Transactional
public class EvaluateFeedbacksServiceImp extends BaseService implements
		EvaluateFeedbacksService {
	@Autowired
	private EvaluateFeedbacksDao evaluateFeedbacksDao;

	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public Boolean isCanEvaluatePeopleAspirationByIdAndAccountType(
			Long accountId, String accountType) {
		if (accountId == null || accountType == null) {
			throw new BusinessValidationException("参数错误");
		}
		return evaluateFeedbacksDao
				.isCanEvaluatePeopleAspirationByIdAndAccountType(accountId,
						accountType);
	}

	@Override
	public EvaluateFeedbacks getEvaluateFeedbacksByAccountIdAndType(
			Long accountId, String accountType) {
		if (accountId == null || accountType == null) {
			throw new BusinessValidationException("参数错误");
		}
		return evaluateFeedbacksDao.getEvaluateFeedbacksByAccountIdAndType(
				accountId, accountType);
	}

	@Override
	public boolean addEvaluateFeedbacks(EvaluateFeedbacks evaluateFeedbacks) {
		if (evaluateFeedbacks.getAccountId() == null
				|| evaluateFeedbacks.getAccountType() == null) {
			throw new BusinessValidationException("参数错误");
		}
		validateEvaluateFeedback(evaluateFeedbacks);
		return evaluateFeedbacksDao.addEvaluateFeedbacks(evaluateFeedbacks);
	}

	private void validateEvaluateFeedback(EvaluateFeedbacks evaluateFeedbacks) {
		if (null == evaluateFeedbacks.getFeedbacksType()) {
			throw new BusinessValidationException("必须选择反馈方式");
		}
		if (null == evaluateFeedbacks.getFeedbacksOpinion()) {
			throw new BusinessValidationException("必须选择反馈意见");
		}
		if (StringUtil.isStringAvaliable(evaluateFeedbacks.getRemark())
				&& validateHelper.illegalStringLength(0, 200,
						evaluateFeedbacks.getRemark())) {
			throw new BusinessValidationException("备注不能大于200个字");
		}
	}

	@Override
	public void deleteEvaluateFeedbacksByAccountIdAndType(Long accountId,
			String accountType, String evaluateUser, Long orgId) {
		if (accountId == null || accountType == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		evaluateFeedbacksDao.deleteEvaluateFeedbacksByAccountIdAndType(
				accountId, accountType);
	}

}
