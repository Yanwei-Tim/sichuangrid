package com.tianque.working.service;

import com.tianque.domain.workingRecord.EmphasisSafetyDetail;

public interface EmphasisSafetyDetailService {
	/**
	 * 添加日常工作中重点治安上报信息
	 * 
	 * @param EmphasisSafetyDetail
	 *        上报信息对象
	 * @return EmphasisSafetyDetail 上报信息对象
	 * @throws Exception
	 */
	public EmphasisSafetyDetail addEmphasisSafetyDetail(EmphasisSafetyDetail emphasisSafetyDetail)
			throws Exception;

	public EmphasisSafetyDetail getEmphasisSafetyDetailById(Long id);

	/**
	 * 删除上报信息
	 */
	public void deleteEmphasisSafetyDetail(Long id);

	/**
	 * 修改上报信息
	 * 
	 * @throws Exception
	 */
	public EmphasisSafetyDetail updateEmphasisSafetyDetail(EmphasisSafetyDetail emphasisSafetyDetail)
			throws Exception;

	/**
	 * 修改信息上报状态
	 * 
	 * @throws Exception
	 */
	public EmphasisSafetyDetail updateSubmitState(Long id, Long submitStateId, Long expiredEntering);

	public EmphasisSafetyDetail backWorkingRecord(Long id, Long submitStateId);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid判断是否汇总，并返回相应的月，季，年报
	 */
	public EmphasisSafetyDetail reportSummarizing(Long reportTime, Long dailyDirectoryId, Long orgid);

	/**
	 * 根据reprTime,dailyDirectoryId,orgid子辖区是否全部上报
	 */
	public boolean sunReportJudge(Long reportTime, Long dailyDirectoryId, Long orgid);
}
