package com.tianque.plugin.weChat.dao;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.evaluationIssueHandle.EvaluationIssueHandle;

public interface EvaluationIssueHandleDao {

	/** 列表查询 */
	public PageInfo<EvaluationIssueHandle> findEvaluationIssueHandles(
			Map<String, Object> parameterMap, Integer pageNum, Integer pageSize);

	/** 添加评价记录 */
	public Long addEvaluationIssueHandle(
			EvaluationIssueHandle evaluationIssueHandle);

	/** 根据事件服务单号查询 */
	public EvaluationIssueHandle getEvaluationIssueHandleBySerialNumber(
			String serialNumber);
}
