package com.tianque.baseInfo.primaryOrg.validator;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.GridTeam;
import com.tianque.baseInfo.primaryOrg.service.GridTeamService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Component("gridTeamValidator")
public class GridTeamValidator implements DomainValidator<GridTeam> {
	@Autowired
	public ValidateHelper validateHelper;
	@Autowired
	private GridTeamService gridTeamService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(GridTeam domain) {
		ValidateResult result = new ValidateResult();
		if (domain == null || domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			result.addErrorMessage("未获得网格员组织机构参数,验证失败");
			return result;
		}

		//系统中已有的网格员账号不需要验证机构
		if ((domain.getUser() == null || domain.getUser().getId() == null)
				&& !organizationDubboService.isVillageOrGridOrganization(domain
						.getOrganization().getId())) {
			result.addErrorMessage("网格员所在层级只能选择社区或者网格层级");
		}
		/***
		 * 成员名称校验
		 */
		if (!StringUtil.isStringAvaliable(domain.getMemeberName())) {
			result.addErrorMessage(getColumNo("memeberName") + "成员名称必须填写");
		} else if (validateHelper.illegalStringLength(0, 10,
				domain.getMemeberName())) {
			result.addErrorMessage(getColumNo("memeberName") + "成员名称不能超过10个字符");
		}
		/**
		 * 验证当前机构中 身份证信息是否已存在
		 */
		if (domain.getId() != null) {
			/**修改验证是否是修改的同一条数据*/
			if (!validateHelper.emptyString(domain.getIdCardNo())
					&& gridTeamService.getGridTeamByIdCardNo(domain
							.getOrganization().getId(), domain.getIdCardNo()) != null
					&& gridTeamService
							.getGridTeamByIdCardNo(
									domain.getOrganization().getId(),
									domain.getIdCardNo()).getId().longValue() != domain
							.getId().longValue()) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "当前组织机构中该身份证信息已经存在，请重新输入");
			}
		} else {
			if (!validateHelper.emptyString(domain.getIdCardNo())
					&& gridTeamService.getGridTeamByIdCardNo(domain
							.getOrganization().getId(), domain.getIdCardNo()) != null) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "当前网格中该身份证信息已经存在，请重新输入");
			}
		}

		/***
		 * 电话号码唯一性验证
		 */
		if(domain.getPhoneNumber()!=null){
			List<GridTeam> list = gridTeamService
					.getGridTeamByPhoneNumber(domain.getPhoneNumber());
			if (domain.getId() != null) {
				/**修改验证是否是修改的同一条数据*/
				boolean flag = false;
				for (GridTeam gridTeam : list) {
					if (gridTeam.getId().equals(domain.getId())) {
						flag = true;
					}
				}
				if (!validateHelper.emptyString(domain.getPhoneNumber())
						&& list != null && list.size() != 0 && !flag) {
					result.addErrorMessage(getColumNo("phoneNumber")
							+ "成员电话号码已经存在，请重新输入");
				}
			} else {
				if (StringUtil.isStringAvaliable(domain.getMemeberName())
						&& !validateHelper.emptyString(domain.getPhoneNumber())
						&& list != null && list.size()!=0) {
					result.addErrorMessage(getColumNo("phoneNumber")
							+ "成员电话号码已经存在，请重新输入");
				}
			}
		}

		/***
		 * 验证性别
		 */
		if (validateHelper.nullObj(domain.getGender())) {
			result.addErrorMessage(getColumNo("gender") + "性别必须选择");
		}

		/***
		 * 验证专兼职情况
		 */
		if (validateHelper.nullObj(domain.getPositionType())) {
			result.addErrorMessage(getColumNo("positionType") + "专兼职情况必须选择");
		}

		/***
		 * 验证政治面貌
		 */
		if (validateHelper.nullObj(domain.getPoliticalBackground())) {
			result.addErrorMessage(getColumNo("politicalBackground")
					+ "政治面貌必须选择");
		}

		/***
		 * 验证文化程度
		 */
		if (validateHelper.nullObj(domain.getEducation())) {
			result.addErrorMessage(getColumNo("education") + "文化程度必须选择");
		}

		/***
		 * 成员身份证验证
		 */
		if (validateHelper.emptyString(domain.getIdCardNo())) {
			result.addErrorMessage(getColumNo("idCardNo") + "身份证号码必须输入");
		}
		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalExculdeParticalChar(domain
						.getIdCardNo())) {
			result.addErrorMessage(getColumNo("idCardNo") + "身份证号码不能输入非法字符");
		}
		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalIdcard(domain.getIdCardNo())) {
			result.addErrorMessage(getColumNo("idCardNo") + "身份证号码输入不正确");
		}

		/***
		 * 验证备注
		 */
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 100,
						domain.getRemark())) {
			result.addErrorMessage(getColumNo("remark") + "备注不能超过getRemark个字符");
		}
		/**
		 * 验证登记时间
		 */
		if (validateHelper.nullObj(domain.getRegisteredDate())) {
			result.addErrorMessage(getColumNo("registeredDate") + "登记时间不能为空");
		} else if (domain.getRegisteredDate().after(new Date())) {
			result.addErrorMessage(getColumNo("registeredDate")
					+ "登记日期不能大于当前日期");
		}

		return result;
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))
				&& ExcelImportHelper.realRow.get() != null) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(Integer.valueOf(ExcelImportHelper
							.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}
}
