package com.tianque.xichang.working.parameterEvaluation.deductionStandard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.dao.ParameterConfigDao;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.domain.ParameterConfig;

@Transactional
@Service("parameterConfigService")
public class ParameterConfigServiceImpl implements ParameterConfigService {
	@Autowired
	private ParameterConfigDao parameterConfigDao;

	@Override
	public ParameterConfig saveDeductionStandard(ParameterConfig parameterConfig) {
		if (null == parameterConfig) {
			throw new BusinessValidationException("参数错误");
		}
		parameterConfigDao.deleteParameterConfig();
		return parameterConfigDao.addParameterConfig(parameterConfig);
	}

	@Override
	public ParameterConfig getParameterConfig() {

		return parameterConfigDao.getParameterConfig();
	}

}
