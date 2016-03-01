package com.tianque.fourTeams.fourTeamsManage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsService;

@Component("fourTeamsValidatorImpl")
public class FourTeamsValidatorImpl implements DomainValidator<FourTeams> {

	@Autowired
	private ValidateHelper validateHelper;

	@Autowired
	private FourTeamsService fourteamsService;
	/** 最小长度 **/
	private final int MIN_LENGTH = 2;
	/** 最大长度 **/
	private final int MAX_LENGTH = 50;
	/** 备注最长长度 */
	private final int CONTENT_MAX_LENGTH = 100;

	@Override
	public ValidateResult validate(FourTeams domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();

		/** 验证队伍名称 非空、最长32 */
		if (validateHelper.emptyString(domain.getNames())) {
			result.addErrorMessage("队伍名称必填!");
		} else if (validateHelper.illegalStringLength(MIN_LENGTH, MAX_LENGTH,
				domain.getNames())) {
			result.addErrorMessage("队伍名称不能小于" + MIN_LENGTH + "个字符，不能大于"
					+ MAX_LENGTH + "个字符!");
		}

		/**
		 * 验证队伍名称是否重复
		 */
		if (domain.getOrganization() != null
				&& domain.getOrganization().getId() != null) {
			if (fourteamsService.repeatTeamName(domain.getId(),
					domain.getNames(), domain.getOrganization().getId())) {
				result.addErrorMessage("队伍名称不能重复!");
			}
		}

		/** 验证队伍所属部门 非空 */
		if (!validateHelper.emptyString(domain.getDepartementName())) {
			if (validateHelper.illegalStringLength(0, MAX_LENGTH,
					domain.getNames())) {
				result.addErrorMessage("队伍所属部门长度不能大于" + MAX_LENGTH + "个字符");
			}
		}
		/** 验证队伍备注 最长 */
		if (!validateHelper.emptyString(domain.getComments())) {
			if (validateHelper.illegalStringLength(0, CONTENT_MAX_LENGTH,
					domain.getNames())) {
				result.addErrorMessage("队伍备注不能大于" + CONTENT_MAX_LENGTH + "个字符!");
			}
		}

		/** 服务范围 */
		if (!validateHelper.emptyString(domain.getServiceArea())) {
			if (validateHelper.illegalStringLength(0, CONTENT_MAX_LENGTH,
					domain.getServiceArea())) {
				result.addErrorMessage("服务范围不能大于" + CONTENT_MAX_LENGTH + "个字符!");
			}
		}
		return result;
	}

}
