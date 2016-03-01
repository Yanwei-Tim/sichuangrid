package com.tianque.xichang.working.flow.service;

import com.tianque.xichang.working.flow.domain.EvaluateFeedbacks;

public interface EvaluateFeedbacksService {

	/**
	 * 验证是否已经存在评论
	 * 
	 * @param accountId
	 * @param accountType
	 * @param evaluateUser
	 * @param orgId
	 * */
	public Boolean isCanEvaluatePeopleAspirationByIdAndAccountType(
			Long accountId, String accountType);

	/**
	 * 根据类型和AccountId查询对应的反馈评论
	 * 
	 * @param accountId
	 * @param accountType
	 * @param evaluateUser
	 * @param orgId
	 * */
	public EvaluateFeedbacks getEvaluateFeedbacksByAccountIdAndType(
			Long accountId, String accountType);

	/**
	 * 新增反馈评论
	 * 
	 * @param evaluateFeedbacks
	 * */
	public boolean addEvaluateFeedbacks(EvaluateFeedbacks evaluateFeedbacks);

	/**
	 * 删除反馈评论
	 * 
	 * @param accountId
	 * @param accountType
	 * @param evaluateUser
	 * @param orgId
	 * */
	public void deleteEvaluateFeedbacksByAccountIdAndType(Long accountId,
			String accountType, String evaluateUser, Long orgId);
}
