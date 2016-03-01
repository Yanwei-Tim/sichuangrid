package com.tianque.partyBuilding.members.dataConverter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.service.MemberService;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("memberDataConverter")
public class MemberDataConverter extends AbstractDataConverter<Member> {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("memberValidatorImpl")
	private AbstractCountrymenValidator<Member> memberValidator;

	@Override
	public ValidateResult validate(Member domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		domain.setPartyOrgType(Integer.parseInt(ThreadVariable.getModule()
				.trim()));
		ValidateResult result = memberValidator.validate(domain);
		/** 所在党支部 */
		if (!memberService.isExsistedPartyOrg(domain.getPartyOrg(),
				domain.getPartyOrgType())) {
			result.addErrorMessage(getColumNo("partyOrg")
					+ "系统中不存在该党组织，请检查后重新输入");
		}
		/**
		 * 党员互斥
		 */
		if (memberService.isExistMemberInPartyOrg(domain)) {
			result.addErrorMessage(getColumNo("idCardNo")
					+ "该党员已经存在于其他党组织中，不允许重复添加");
		}
		/**
		 * 入党时间验证
		 */
		if (domain.getJoinPartyDate() != null) {
			if (domain.getJoinPartyDate().after(new Date())) {
				result.addErrorMessage(getColumNo("joinPartyDate")
						+ "入党时间不能大于当前时间");
			}
			if (domain.getJoinPartyDate().before(domain.getBirthday())) {
				result.addErrorMessage(getColumNo("joinPartyDate")
						+ "入党时间不能小于出生日期");
			}
		}

		/**
		 * 进入当前党支部时间验证
		 */
		if (domain.getJoinPartyBranchDate() != null) {
			if (domain.getJoinPartyBranchDate().after(new Date())) {
				result.addErrorMessage(getColumNo("joinPartyBranchDate")
						+ "进入当前党支部时间不能大于当前时间");
			}
			if (domain.getJoinPartyBranchDate().before(domain.getBirthday())) {
				result.addErrorMessage(getColumNo("joinPartyBranchDate")
						+ "进入当前党支部时间不能小于出生日期");
			}
		}
		/**
		 * 党费交纳至验证
		 */
		if (domain.getEndDate() != null) {
			if (domain.getEndDate().before(new Date())) {
				result.addErrorMessage(getColumNo("endDate")
						+ "党费交纳至日期不能小于当前日期");
			}
		}

		/**
		 * 户籍地验证
		 */
		if (null != domain.getProvince() && null != domain.getCity()
				&& null != domain.getDistrict()) {
			memberValidator.validatorProvinceAndCityAndDistrict(
					domain.getProvince(), domain.getCity(),
					domain.getDistrict(), result, null);
		}
		return result;

	}

	@Override
	public Member convertToDomain(String[] cellValues, ValidateResult result) {
		return null;
	}

	@Override
	public Member persistenceDomain(Member domain) {
		return memberService.editMember(domain);
	}

	@Override
	public Member updateDomain(Member domain) {
		return null;
	}

	@Override
	public boolean isRepeatData(Member domain) {
		return false;
	}

}
