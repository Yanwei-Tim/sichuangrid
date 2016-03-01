package com.tianque.tenHouseholdsJoint.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.service.impl.FamilyTeamServiceImpl;

@Component("tenHouseHoldsGroupValidatorImpl")
public class TenHouseHoldsGroupValidatorImpl extends AbstactDataManageValidator<FamilyTeam> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private FamilyTeamServiceImpl familyTeamService;
	@Override
	public ValidateResult validate(FamilyTeam domain) {
		ValidateResult result = new ValidateResult();
		
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			result.addErrorMessage(getColumNo("organization")
					+ "所属网格为空或网格名称填写错误！");
		}

		if (orgIsNotNull && !validateOrgIsGrid(domain.getOrganization())) {
			result.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}

		
		/**验证分组编号*/
		if(!StringUtil.isStringAvaliable(domain.getTeamCode())){
			result.addErrorMessage(getColumNo("teamCode")
					+ "分组编号不能为空");
		}else if(validateHelper.illegalStringLength(2, 20,domain.getTeamCode())){
			result.addErrorMessage(getColumNo("teamCode")
					+ "分组编号长度为2-20");
		}else if(!familyTeamService.checkFamilyTeam(domain)){
			result.addErrorMessage(getColumNo("teamCode")
					+ "分组编号已经存在，请重新输入");
		}
		
		/**验证分组名称*/
		if(!StringUtil.isStringAvaliable(domain.getTeamName())){
			result.addErrorMessage(getColumNo("teamName")
					+ "分组名称不能为空");
		}else if(validateHelper.illegalStringLength(2, 20,domain.getTeamName())){
			result.addErrorMessage(getColumNo("teamName")
					+ "分组名称长度为2-20");
		}
		/**验证户数*/
		if (domain.getHouseHolds() == null) {
			result.addErrorMessage(getColumNo("houseHolds")
					+ "户数必须输入");
		}else if (validateHelper.illegalInteger(String.valueOf(domain
				.getHouseHolds()))) {
			result.addErrorMessage(getColumNo("houseHolds")
					+ "户数只能输入不大于999999999的正整数");
		} else if (Double.valueOf(domain.getHouseHolds()).longValue() > 999999999) {
			result.addErrorMessage(getColumNo("houseHolds")
					+ "户数只能输入不大于999999999的正整数");
		}
		/**验证联户长*/
		if(!StringUtil.isStringAvaliable(domain.getHouseMaster())){
			result.addErrorMessage(getColumNo("houseMaster")
					+ "联户长不能为空");
		}else if(validateHelper.illegalStringLength(2, 20,domain.getHouseMaster())){
			result.addErrorMessage(getColumNo("houseMaster")
					+ "联户长长度为2-20");
		}
		/**验证联户长证件号码*/
		if(!StringUtil.isStringAvaliable(domain.getHouseMastCertificateNo())){
			result.addErrorMessage(getColumNo("houseMastCertificateNo")
					+ "联户长证件号码不能为空");
		}else if(validateHelper.illegalStringLength(2, 20,domain.getHouseMastCertificateNo())){
			result.addErrorMessage(getColumNo("houseMastCertificateNo")
					+ "联户长证件号码长度为2-20");
		}
		
		return result;
	}
	@Override
	public ValidateResult validateSpecializedInfo(FamilyTeam domain) {
		return null;
	}

}
