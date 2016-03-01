package com.tianque.xichang.working.parameterEvaluation.deductionStandard.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.domain.ParameterConfig;

@Repository("parameterConfigDao")
public class ParameterConfigDaoImpl extends AbstractBaseDao implements
		ParameterConfigDao {

	@Override
	public ParameterConfig addParameterConfig(ParameterConfig parameterConfig) {
		getSqlMapClientTemplate().insert("parameterConfig.addParameterConfig",
				parameterConfig);
		return getParameterConfig();
	}

	@Override
	public ParameterConfig getParameterConfig() {

		return (ParameterConfig) getSqlMapClientTemplate().queryForObject(
				"parameterConfig.getParameterConfig");
	}

	@Override
	public void deleteParameterConfig() {
		getSqlMapClientTemplate().delete(
				"parameterConfig.deleteParameterConfig");
	}
}
