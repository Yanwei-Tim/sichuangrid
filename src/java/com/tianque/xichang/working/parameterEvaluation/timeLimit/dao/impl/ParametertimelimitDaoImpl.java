package com.tianque.xichang.working.parameterEvaluation.timeLimit.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.dao.ParametertimelimitDao;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.vo.SearchParametertimelimitVo;

/**
 * 三本台账时限标准表:数据操作层
 * 
 * @author
 * @date 2014-03-04 10:34:33
 */
@Repository("parametertimelimitDao")
public class ParametertimelimitDaoImpl extends
		BaseDaoImpl<Parametertimelimit, SearchParametertimelimitVo> implements
		ParametertimelimitDao {

	@Override
	public Parametertimelimit getParametertimelimitByOrgTypeAndOrgLevel(
			Long orgType, Long orgLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgType", orgType);
		map.put("orgLevel", orgLevel);
		return (Parametertimelimit) getSqlMapClientTemplate().queryForObject(
				"parametertimelimit.getParametertimelimitByOrgTypeAndOrgLevel",
				map);
	}

	@Override
	public Parametertimelimit getDefaultParametertimelimit() {
		return (Parametertimelimit) getSqlMapClientTemplate().queryForObject(
				"parametertimelimit.getDefaultParametertimelimit");
	}

	@Override
	public Parametertimelimit getParametertimelimitByDepartment(Long deptId) {

		return (Parametertimelimit) getSqlMapClientTemplate().queryForObject(
				"parametertimelimit.getParametertimelimitByDepartment", deptId);
	}

}
