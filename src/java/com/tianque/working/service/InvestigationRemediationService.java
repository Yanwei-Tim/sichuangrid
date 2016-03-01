package com.tianque.working.service;

import com.tianque.working.domain.InvestigationRemediation;

public interface InvestigationRemediationService {
	/**
	 * 添加日常工作中重点治安上报信息
	 * 
	 * @param InvestigationRemediation
	 *        上报信息对象
	 * @return InvestigationRemediation 上报信息对象
	 * @throws Exception
	 */
	public InvestigationRemediation addInvestigationRemediation(
			InvestigationRemediation investigationRemediation) throws Exception;

	public InvestigationRemediation getInvestigationRemediationById(Long id);

	/**
	 * 删除上报信息
	 */
	public void deleteInvestigationRemediation(Long id);

	/**
	 * 修改上报信息
	 * 
	 * @throws Exception
	 */
	public InvestigationRemediation updateInvestigationRemediation(
			InvestigationRemediation investigationRemediation) throws Exception;

	/**
	 * 修改信息上报状态
	 * 
	 * @throws Exception
	 */
	public InvestigationRemediation updateSubmitState(Long id, Long submitStateId,
			Long expiredEntering);

	public InvestigationRemediation backWorkingRecord(Long id, Long submitStateId);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid判断是否汇总，并返回相应的月，季，年报
	 */
	public InvestigationRemediation reportSummarizing(Long reportTime, Long dailyDirectoryId,
			Long orgid);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid子辖区是否全部上报
	 */
	public boolean sunReportJudge(Long reportTime, Long dailyDirectoryId, Long orgid);
}
