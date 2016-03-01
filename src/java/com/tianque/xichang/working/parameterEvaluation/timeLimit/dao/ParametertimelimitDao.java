package com.tianque.xichang.working.parameterEvaluation.timeLimit.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.vo.SearchParametertimelimitVo;

/**
 * 三本台账时限标准表:数据操作层接口
 * 
 * @author
 * @date 2014-03-04 10:34:33
 */
public interface ParametertimelimitDao extends
		BaseDao<Parametertimelimit, SearchParametertimelimitVo> {

	public Parametertimelimit getParametertimelimitByOrgTypeAndOrgLevel(
			Long orgType, Long orgLevel);

	public Parametertimelimit getDefaultParametertimelimit();

	public Parametertimelimit getParametertimelimitByDepartment(Long deptId);

}
