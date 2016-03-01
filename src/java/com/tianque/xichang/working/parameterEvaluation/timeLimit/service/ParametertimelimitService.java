package com.tianque.xichang.working.parameterEvaluation.timeLimit.service;

import com.tianque.core.base.BaseService;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.vo.SearchParametertimelimitVo;

/**
 * 三本台账时限标准表:业务逻辑层接口
 * 
 * @author
 * @date 2014-03-04 10:34:33
 */
public interface ParametertimelimitService extends
		BaseService<Parametertimelimit, SearchParametertimelimitVo> {

	public Parametertimelimit getParametertimelimitByOrgId(Long orgId);
}
