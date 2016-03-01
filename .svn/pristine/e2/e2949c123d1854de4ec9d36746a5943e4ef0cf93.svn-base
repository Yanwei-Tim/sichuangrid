package com.tianque.plugin.weChat.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.evaluationIssueHandle.EvaluationIssueHandle;

/** 评价事件处理类 */
public interface EvaluationIssueHandleService {

	/** 评价事件处理列表 */
	public PageInfo<EvaluationIssueHandle> findEvaluationIssueHandles(
			EvaluationIssueHandle evaluationIssueHandle, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/** 添加评价记录 */
	public String addEvaluationIssueHandle(
			EvaluationIssueHandle evaluationIssueHandle);

	/** 根据事件服务单号查询 */
	public EvaluationIssueHandle getEvaluationIssueHandleBySerialNumber(
			String serialNumber);

}
