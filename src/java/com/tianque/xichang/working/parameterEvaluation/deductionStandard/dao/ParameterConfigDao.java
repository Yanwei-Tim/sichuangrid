package com.tianque.xichang.working.parameterEvaluation.deductionStandard.dao;

import com.tianque.xichang.working.parameterEvaluation.deductionStandard.domain.ParameterConfig;

public interface ParameterConfigDao {

	public ParameterConfig addParameterConfig(ParameterConfig parameterConfig);

	public ParameterConfig getParameterConfig();

	public void deleteParameterConfig();

}
