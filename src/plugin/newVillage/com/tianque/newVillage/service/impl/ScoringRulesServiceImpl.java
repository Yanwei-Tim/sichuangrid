package com.tianque.newVillage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.ScoringRulesDao;
import com.tianque.newVillage.domain.ScoringRules;
import com.tianque.newVillage.service.ScoringRulesService;
import com.tianque.service.impl.LogableService;

/**
 * @ClassName: ScoringRulesServiceImpl
 * @Description: 新农村建设
 */
@Service("scoringRulesService")
@Transactional
public class ScoringRulesServiceImpl extends LogableService implements
		ScoringRulesService {
	@Autowired
	private ScoringRulesDao scoringRulesDao;

	@Qualifier("scoringRulesValidator")
	@Autowired
	private DomainValidator<ScoringRules> domainValidator;

	@Override
	public void addScoringRules(String[] correspondingValue,
			String[] calculationSymbol, String[] scoringRangeStart,
			String[] scoringRangeEnd, String[] fraction, Integer maxFraction) {
		scoringRulesDao.deleteScoringRules();
		for (int i = 0; i < correspondingValue.length; i++) {
			String errorMsg = "";// 验证错误信息
			String value = correspondingValue[i];
			String symbol = calculationSymbol[i];
			String fractionByFor = fraction[i];
			String scoringRangeStartByFor = scoringRangeStart[i];
			String scoringRangeEndByFor = scoringRangeEnd[i];
			errorMsg = validateValue(symbol, scoringRangeStartByFor,
					scoringRangeEndByFor, scoringRangeEndByFor, fractionByFor,
					maxFraction);
			if (StringUtil.isStringAvaliable(errorMsg)) {
				throw new BusinessValidationException(errorMsg);
			}
			ScoringRules scoringRules = new ScoringRules();
			scoringRules.setCalculationSymbol(Integer.parseInt(symbol));
			scoringRules.setCorrespondingValue(value);
			scoringRules.setFraction(Integer.parseInt(fractionByFor));
			scoringRules.setScoringRangeStart(Double
					.parseDouble(scoringRangeStartByFor));
			if (StringUtil.isStringAvaliable(scoringRangeEndByFor)) {
				scoringRules.setScoringRangeEnd(Double
						.parseDouble(scoringRangeEndByFor));
			}
			scoringRules.setCreateDate(new Date());
			scoringRules.setCreateUser(ThreadVariable.getUser().getUserName());
			// 数据校验
			ValidateResult baseDataValidator = domainValidator
					.validate(scoringRules);
			if (baseDataValidator.hasError()) {
				throw new BusinessValidationException(
						baseDataValidator.getErrorMessages());
			}
			try {
				scoringRulesDao.addScoringRules(scoringRules);
			} catch (Exception e) {
				throw new BusinessValidationException("新增信息失败");
			}
			scoringRules = null;
		}
	}

	/**
	 * 验证值是否有效
	 */
	private String validateValue(String symbol, String scoringRangeStartByFor,
			String scoringRangeEndByFor, String scoringRangeEnd,
			String fractionByFor, Integer maxFraction) {
		String errorMsg = "";

		if (StringUtil.isStringAvaliable(scoringRangeStartByFor)) {
			if (!scoringRangeStartByFor.matches("^[0-9]+(.[0-9]{0,2})?$")) {
				errorMsg += " 范围值始必须为正数 ";
			}
		} else {
			errorMsg += " 范围值始不能为空 ";
		}

		// 判断运算符是否为介于
		if (symbol.equals("4")) {
			if (StringUtil.isStringAvaliable(scoringRangeEndByFor)) {
				if (!scoringRangeEndByFor.matches("^[0-9]+(.[0-9]{0,2})?$")) {
					errorMsg += " 范围值末必须为正数 ";
				}
			} else {
				errorMsg += " 范围值末不能为空 ";
			}
		}

		if (StringUtil.isStringAvaliable(fractionByFor)) {
			if (!fractionByFor.matches("^[0-9]*$")) {
				errorMsg += " 得分值必须为正整数 ";
			}
		} else {
			errorMsg += " 得分值不能为空 ";
		}

		if (null != maxFraction) {
			if (maxFraction.intValue() > 100) {
				errorMsg += " 得分总值不能超过100 ";
			}
		}
		return errorMsg;
	}

	@Override
	public ScoringRules getScoringRulesById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误,获取信息失败");
		}
		return scoringRulesDao.getScoringRulesById(id);
	}

	@Override
	public void deleteScoringRules() {
		scoringRulesDao.deleteScoringRules();
	}

	@Override
	public void updateScoringRules(ScoringRules scoringRules) {
		if (null == scoringRules) {
			throw new BusinessValidationException("参数错误,修改信息失败");
		}
		try {
			scoringRulesDao.updateScoringRules(scoringRules);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息失败");
		}
	}

	@Override
	public List<ScoringRules> getScoringRulesListByValues() {
		List<ScoringRules> list = null;
		try {
			list = scoringRulesDao.getScoringRulesListByValues();
		} catch (Exception e) {
			throw new BusinessValidationException("获取信息列表出现错误");
		}
		return list;
	}

	@Override
	public ScoringRules checkValueIsSave(String correspondingValue,
			Integer calculationSymbol) {
		ScoringRules scoringRules = null;
		try {
			scoringRules = scoringRulesDao.checkValueIsSave(correspondingValue,
					calculationSymbol);
		} catch (Exception e) {
			throw new BusinessValidationException("获取信息出现错误");
		}
		return scoringRules;
	}

}
