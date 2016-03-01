package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.ScoringRules;

@Component("scoringRulesValidator")
public class ScoringRulesValidator implements DomainValidator<ScoringRules> {
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(ScoringRules domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();

		if (domain.getCalculationSymbol() != null) {
			// 判断是否是介于，是介于判断范围值末
			if (domain.getCalculationSymbol().intValue() == 4) {
				if (domain.getScoringRangeEnd() != null) {
					if (domain.getScoringRangeEnd() > 99999.99D
							|| domain.getScoringRangeEnd() < 0) {
						result.addErrorMessage("范围值末的值应在0-99999");
					}
				}
			}
		}

		// 验证起始值
		if (domain.getScoringRangeStart() != null) {
			if (domain.getScoringRangeStart() > 99999.99D
					|| domain.getScoringRangeStart() < 0) {
				result.addErrorMessage("范围值始的值应在0-99999");
			}
		}
		// 验证 得分
		if (domain.getFraction() != null) {
			if (domain.getFraction() > 100 || domain.getFraction() < 0) {
				result.addErrorMessage("得分值应在范围0-100");
			}
		}

		return result;
	}
}
