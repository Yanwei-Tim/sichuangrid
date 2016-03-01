package com.tianque.baseInfo.companyPlace.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.companyPlace.constant.IsKeyType;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;

@Component("CompanyPlaceBusinessValidatorImpl")
public class CompanyPlaceBusinessValidatorImpl implements
		DomainValidator<CompanyPlaceBusinessVO> {
	@Autowired
	private ValidateHelper validateHelper;
	/** 最小长度 */
	private final int MIN_LENGTH = 2;
	/** 隐患情况长度 */
	private final int HIDDANGERINFO_LENGTH = 100;
	/** 隐患整改情况长度 */
	private final int CORRECTIONINFO_LENGTH = 100;
	/** 污染情况长度 */
	private final int POLLUTIONINFO_LENGTH = 100;

	@Override
	public ValidateResult validate(CompanyPlaceBusinessVO domain) {
		ValidateResult result = new ValidateResult();

		if (domain.getProKeyCompanyPlaceBusiness() != null
				&& domain.getProKeyCompanyPlaceBusiness().getIsKeyType() != null
				&& domain.getProKeyCompanyPlaceBusiness().getIsKeyType()
						.equals(IsKeyType.PRODUCTION_KEY_TYPE)) {
			// 安全生产重点验证
			// 隐患情况验证
			if (!validateHelper.emptyString(domain
					.getProKeyCompanyPlaceBusiness().getHiddangerInfo())) {
				// 非法字符验证
				if (validateHelper.illegalScript(domain
						.getProKeyCompanyPlaceBusiness().getHiddangerInfo())) {
					result.addErrorMessage("隐患情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						HIDDANGERINFO_LENGTH, domain
								.getProKeyCompanyPlaceBusiness()
								.getHiddangerInfo())) {
					result.addErrorMessage("隐患情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + HIDDANGERINFO_LENGTH + "个字符");
				}
			}
			// 隐患整改情况验证
			if (!validateHelper.emptyString(domain
					.getProKeyCompanyPlaceBusiness().getCorrectionInfo())) {
				// 非法字符验证
				if (validateHelper.illegalScript(domain
						.getProKeyCompanyPlaceBusiness().getCorrectionInfo())) {
					result.addErrorMessage("隐患整改情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						CORRECTIONINFO_LENGTH, domain
								.getProKeyCompanyPlaceBusiness()
								.getCorrectionInfo())) {
					result.addErrorMessage(" 隐患整改情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + CORRECTIONINFO_LENGTH + "个字符");
				}
			}
		}
		if (domain.getFireSafetyKeyCompanyPlaceBusiness() != null
				&& domain.getFireSafetyKeyCompanyPlaceBusiness().getIsKeyType() != null
				&& domain.getFireSafetyKeyCompanyPlaceBusiness().getIsKeyType()
						.equals(IsKeyType.FIRESAFETY_KEY_TYPE)) {
			// 消防安全重点验证
			// 隐患情况验证
			if (!validateHelper.emptyString(domain
					.getFireSafetyKeyCompanyPlaceBusiness().getHiddangerInfo())) {
				// 非法字符验证
				if (validateHelper.illegalScript(domain
						.getFireSafetyKeyCompanyPlaceBusiness()
						.getHiddangerInfo())) {
					result.addErrorMessage("隐患情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						HIDDANGERINFO_LENGTH, domain
								.getFireSafetyKeyCompanyPlaceBusiness()
								.getHiddangerInfo())) {
					result.addErrorMessage("隐患情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + HIDDANGERINFO_LENGTH + "个字符");
				}
			}
			// 隐患整改情况验证
			if (!validateHelper
					.emptyString(domain.getFireSafetyKeyCompanyPlaceBusiness()
							.getCorrectionInfo())) {
				// 非法字符验证
				if (validateHelper.illegalScript(domain
						.getFireSafetyKeyCompanyPlaceBusiness()
						.getCorrectionInfo())) {
					result.addErrorMessage("隐患整改情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						CORRECTIONINFO_LENGTH, domain
								.getFireSafetyKeyCompanyPlaceBusiness()
								.getCorrectionInfo())) {
					result.addErrorMessage(" 隐患整改情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + CORRECTIONINFO_LENGTH + "个字符");
				}
			}
		}
		if (domain.getSecurityKeyCompanyPlaceBusiness() != null
				&& domain.getSecurityKeyCompanyPlaceBusiness().getIsKeyType() != null
				&& domain.getSecurityKeyCompanyPlaceBusiness().getIsKeyType()
						.equals(IsKeyType.SECURITY_KEY_TYPE)) {
			// 治安重点验证
			// 隐患情况验证
			if (!validateHelper.emptyString(domain
					.getSecurityKeyCompanyPlaceBusiness().getHiddangerInfo())) {
				// 非法字符验证
				if (validateHelper.illegalScript(domain
						.getSecurityKeyCompanyPlaceBusiness()
						.getHiddangerInfo())) {
					result.addErrorMessage("隐患情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						HIDDANGERINFO_LENGTH, domain
								.getSecurityKeyCompanyPlaceBusiness()
								.getHiddangerInfo())) {
					result.addErrorMessage("隐患情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + HIDDANGERINFO_LENGTH + "个字符");
				}
			}
			// 隐患整改情况验证
			if (!validateHelper.emptyString(domain
					.getSecurityKeyCompanyPlaceBusiness().getCorrectionInfo())) {
				// 非法字符验证
				if (validateHelper.illegalScript(domain
						.getSecurityKeyCompanyPlaceBusiness()
						.getCorrectionInfo())) {
					result.addErrorMessage("隐患整改情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						CORRECTIONINFO_LENGTH, domain
								.getSecurityKeyCompanyPlaceBusiness()
								.getCorrectionInfo())) {
					result.addErrorMessage(" 隐患整改情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + CORRECTIONINFO_LENGTH + "个字符");
				}
			}
			// 整改时限验证
			if (null != domain.getSecurityKeyCompanyPlaceBusiness()
					.getCorrectionBeiginDate()
					&& null != domain.getSecurityKeyCompanyPlaceBusiness()
							.getCorrectionEndDate()) {
				if (domain
						.getSecurityKeyCompanyPlaceBusiness()
						.getCorrectionEndDate()
						.before(domain.getSecurityKeyCompanyPlaceBusiness()
								.getCorrectionBeiginDate())) {
					result.addErrorMessage("整改时限结束日期不能大于开始日期");
				}
			}
		}
		if (domain.getPollSourceCompanyPlaceBusiness() != null
				&& domain.getPollSourceCompanyPlaceBusiness().getIsKeyType() != null
				&& domain.getPollSourceCompanyPlaceBusiness().getIsKeyType()
						.equals(IsKeyType.POLLUTION_SOURCE_TYPE)) {
			// 污染源验证
			// 污染情况验证
			if (!validateHelper.emptyString(domain
					.getPollSourceCompanyPlaceBusiness().getPollutionInfo())) {
				// 非法字符验证
				if (validateHelper
						.illegalScript(domain
								.getPollSourceCompanyPlaceBusiness()
								.getPollutionInfo())) {
					result.addErrorMessage("污染情况不能输入非法脚本");
				}
				// 长度验证
				if (validateHelper.illegalStringLength(MIN_LENGTH,
						POLLUTIONINFO_LENGTH, domain
								.getPollSourceCompanyPlaceBusiness()
								.getPollutionInfo())) {
					result.addErrorMessage("污染情况不能小于" + MIN_LENGTH + "个字符"
							+ "不能大于" + POLLUTIONINFO_LENGTH + "个字符");
				}
			}
		}
		return result;
	}
}
