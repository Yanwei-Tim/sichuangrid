package com.tianque.plugin.weChat.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.EvaluationIssueHandleDao;
import com.tianque.plugin.weChat.domain.ModuleTable;
import com.tianque.plugin.weChat.domain.evaluationIssueHandle.EvaluationIssueHandle;

@Repository("evaluationIssueHandleDao")
public class EvaluationIssueHandleDaoImpl extends AbstractBaseDao implements
		EvaluationIssueHandleDao {

	@Override
	public Long addEvaluationIssueHandle(
			EvaluationIssueHandle evaluationIssueHandle) {
		return (Long) getSqlMapClientTemplate().insert(
				"evaluationIssueHandle.addEvaluationIssueHandle", evaluationIssueHandle);
	}

	@Override
	public EvaluationIssueHandle getEvaluationIssueHandleBySerialNumber(
			String serialNumber) {
		return (EvaluationIssueHandle) getSqlMapClientTemplate().queryForObject(
				"evaluationIssueHandle.getEvaluationIssueHandleBySerialNumber", serialNumber);
	}

	@Override
	public PageInfo<EvaluationIssueHandle> findEvaluationIssueHandles(
			Map<String, Object> parameterMap, Integer pageNum, Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<ModuleTable>(),
				"evaluationIssueHandle.countFindEvaluationIssueHandleResults",
				"evaluationIssueHandle.findEvaluationIssueHandleResults", pageNum, pageSize,
				parameterMap);
	}

}
