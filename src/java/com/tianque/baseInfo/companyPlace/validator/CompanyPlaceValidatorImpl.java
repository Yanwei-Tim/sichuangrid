package com.tianque.baseInfo.companyPlace.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@Component("companyPlaceValidator")
public class CompanyPlaceValidatorImpl implements DomainValidator<CompanyPlace> {
	@Autowired
	private ValidateHelper validateHelper;
	/** 最小长度 */
	private final int MIN_LENGTH = 2;
	/** 名称长度 */
	private final int NAME_LENGTH = 20;
	/** 营业执照长度 */
	private final int BUSINESSLICENSENO_LENGTH_MIN = 6;
	private final int BUSINESSLICENSENO_LENGTH = 20;
	/** 场所名称长度 */
	private final int COMPANYPLACENAME_LENGTH = 50;
	/** 地址长度长度 */
	private final int ADDRESS_LENGTH = 50;
	/** 存储设备长度 */
	private final int GENERALSTORAGE_LENGTH = 50;
	/** 主管部门 */
	private final int GENERALMANAGE_LENGTH = 50;
	/** 管理部门 */
	private final int GENERALMENTE_LENGTH = 50;
	/** 货物类别长度 */
	private final int GENERALTYPE_LENGTH = 50;
	/** 组织机构码长度 */
	private final int ORGCODE_LENGTH = 50;
	/** 学校英文名称长度 */
	private final int SCHOOLNAMEEN_LENGTH = 60;
	/** 电子邮箱长度 */
	private final int EMAILEN_LENGTH = 30;
	/** 学校网址长度 */
	private final int SCHOOLWEBSITE_LENGTH = 30;
	/** 简介长度 */
	private final int REMARK_LENGTH = 150;
	/** area通用字段长度 */
	private final int AREA_LENGTH = 100;
	/** 电话号码长度 */
	private final int TELEPHONE_LENGTH_MIN = 2;
	private final int TELEPHONE_LENGTH = 20;
	/** IP字段长度 */
	private final int IP_LENGTH = 50;
	/** 消防审核意见书号 */
	private final int FIREAUDITOPINION_LENGTH = 20;
	/** 网吧编号 */
	private final int INTERNETBARNO_LENGTH = 20;

	/**
	 * 验证是否输入特殊字符(数字,字母,中文字符,空格,点,括号)
	 */
	public boolean illegalExculdeParticalChar(String text) {
		return !text.matches("^[A-Za-z0-9\u4E00-\u9FA5\\s\\.\\(\\)\\（\\）]+$");
	}

	@Override
	public ValidateResult validate(CompanyPlace domain) {
		ValidateResult result = new ValidateResult();
		if (!validateOrgIsNotNull(domain.getOrg())
				&& !validateOrgIsNotNull(domain.getOrganization())) {
			result.addErrorMessage("所属区域必须输入");
		}
		// 单位场所名称验证
		if (validateHelper.emptyString(domain.getName())) {
			result.addErrorMessage("单位场所名称必须输入");
		} else {
			// 名称非法字符验证
			if (illegalExculdeParticalChar(domain.getName().replaceAll(" ", ""))) {
				result.addErrorMessage("单位场所名称不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getName())) {
				result.addErrorMessage("单位场所名称不能输入非法脚本");
			}
			// 名称长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					COMPANYPLACENAME_LENGTH, domain.getName())) {
				result.addErrorMessage("单位场所名称不能小于" + MIN_LENGTH + "个字符"
						+ "不能大于" + COMPANYPLACENAME_LENGTH + "个字符");
			}
		}
		// 地址验证
		if (validateHelper.emptyString(domain.getAddress())) {
			result.addErrorMessage("地址必须输入");
		} else {
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getAddress())) {
				result.addErrorMessage("地址不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, ADDRESS_LENGTH,
					domain.getAddress())) {
				result.addErrorMessage("地址不能小于" + MIN_LENGTH + "个字符" + "不能大于"
						+ ADDRESS_LENGTH + "个字符");
			}
		}
		// 备注验证
		if (!validateHelper.emptyString(domain.getRemarks())) {
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getRemarks())) {
				result.addErrorMessage("备注不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, REMARK_LENGTH,
					domain.getRemarks())) {
				result.addErrorMessage("备注不能小于" + MIN_LENGTH + "个字符" + "不能大于"
						+ REMARK_LENGTH + "个字符");
			}
		}
		// 类型那个验证
		validatorifType(domain.getType(), result);
		// 分类验证
		validatorifClassifiCation(domain.getClassifiCation(), result);
		// 类别验证
		validatorifCategory(domain.getCategory(), result);
		// 头部 公共字段 （除党政机关）
		// 负责人验证
		if (!validateHelper.emptyString(domain.getUserName())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain.getUserName())) {
				result.addErrorMessage(domain.getUserName() + ":不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getUserName())) {
				result.addErrorMessage(domain.getUserName() + ":不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, NAME_LENGTH,
					domain.getUserName())) {
				result.addErrorMessage(domain.getUserName() + ":不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + NAME_LENGTH + "个字符");
			}
		}
		// 联系手机验证
		if (!validateHelper.emptyString(domain.getMobileNumber())) {
			if (validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
				result.addErrorMessage("手机手机输入只能是以1开头的11位数字");
			}
		}
		// 联系电话验证
		if (!validateHelper.emptyString(domain.getTelePhone())) {
			if (validateHelper.illegalTelephone(domain.getTelePhone())) {
				result.addErrorMessage("固定电话不合法，只能输数字和横杠(-)");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(TELEPHONE_LENGTH_MIN,
					TELEPHONE_LENGTH, domain.getTelePhone())) {
				result.addErrorMessage("电话号码不能小于" + TELEPHONE_LENGTH_MIN
						+ "不能大于" + TELEPHONE_LENGTH + "个字符");
			}
		}
		// 传真号码验证
		if (!validateHelper.emptyString(domain.getFaxNumber())) {
			if (validateHelper.illegalTelephone(domain.getFaxNumber())) {
				result.addErrorMessage("传真号码不合法，只能输数字和横杠(-)");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(TELEPHONE_LENGTH_MIN,
					TELEPHONE_LENGTH, domain.getFaxNumber())) {
				result.addErrorMessage("传真号码不能小于" + TELEPHONE_LENGTH_MIN
						+ "不能大于" + TELEPHONE_LENGTH + "个字符");
			}
		}

		// area字段验证（通用字段：所在区域（主管单位，通道口、施工单位、周边情况、所属单位、副本许可范围、经营范围、所在派出所、主管单位）
		if (!validateHelper.emptyString(domain.getArea())) {
			// 非法字符验证
			if (validateHelper.illegalScript(domain.getArea())) {
				result.addErrorMessage(domain.getArea() + ":不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, AREA_LENGTH,
					domain.getArea())) {
				result.addErrorMessage(domain.getArea() + ":不能小于" + MIN_LENGTH
						+ "个字符" + "不能大于" + AREA_LENGTH + "个字符");
			}
		}

		// 占地面积，施工面积
		if (domain.getCoveredArea() != null) {
			if (domain.getCoveredArea().longValue() < 0
					|| domain.getCoveredArea().longValue() > 999999999) {
				result.addErrorMessage("面积为1-999999999的正数");
			}
		}
		// 营业执照号验证
		if (!validateHelper.emptyString(domain.getBusinessLicenseNo())) {
			// 长度验证
			if (validateHelper.illegalStringLength(
					BUSINESSLICENSENO_LENGTH_MIN, BUSINESSLICENSENO_LENGTH,
					domain.getBusinessLicenseNo())) {
				result.addErrorMessage("工商执照号码只能输入6-20个字符");
			}
		}
		// 组织机构代码
		if (!validateHelper.emptyString(domain.getOrgCode())) {

			// 规范验证
			if (validateHelper.illegalDigitStrAndUnderline(domain.getOrgCode())) {
				result.addErrorMessage("组织机构代码只能数字、字母、下划线");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, ORGCODE_LENGTH,
					domain.getOrgCode())) {
				result.addErrorMessage("组织机构代码不能小于" + MIN_LENGTH + "个字符"
						+ "不能大于" + ORGCODE_LENGTH + "个字符");
			}
		}

		// 党员数验证
		if (domain.getPartyCountNumber() != null) {
			// 规范验证
			if (validateHelper.illegalNumberZZ(domain.getPartyCountNumber()
					.toString())) {
				result.addErrorMessage("党员数量需要输入正整数");
			}
			// 长度验证
			if (domain.getPartyCountNumber().longValue() < 0
					|| domain.getPartyCountNumber().longValue() > 999999999) {
				result.addErrorMessage("党员数为1-999999999的正整数");
			}
		}
		// 注册资金验证
		if (domain.getRegisteredCapital() != null) {
			// 规范验证
			if (validateHelper.illegalNumberZS(domain.getRegisteredCapital()
					.toString())) {
				result.addErrorMessage("注册资金需要输入正数");
			}
			// 长度验证
			if (domain.getRegisteredCapital().longValue() < 0
					|| domain.getRegisteredCapital().longValue() > 999999999) {
				result.addErrorMessage("注册资金为1-999999999的正数");
			}
		}
		// 法制副校长验证
		if (!validateHelper.emptyString(domain.getLegalVicePrincipal())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getLegalVicePrincipal())) {
				result.addErrorMessage("法制副校长不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getLegalVicePrincipal())) {
				result.addErrorMessage("法制副校长不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH, NAME_LENGTH,
					domain.getLegalVicePrincipal())) {
				result.addErrorMessage("法制副校长不能小于" + MIN_LENGTH + "个字符"
						+ "不能大于" + NAME_LENGTH + "个字符");
			}
		}
		// 学校英文名称验证
		if (!validateHelper.emptyString(domain.getSchoolNameEn())) {
			// 英文名只能输入英文!
			if (validateHelper.illegalLetter(domain.getSchoolNameEn())) {
				result.addErrorMessage("英文名只能输入英文!");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					SCHOOLNAMEEN_LENGTH, domain.getSchoolNameEn())) {
				result.addErrorMessage("学校英文名称不能小于" + MIN_LENGTH + "个字符"
						+ "不能大于" + SCHOOLNAMEEN_LENGTH + "个字符");
			}
		}
		// 电子邮箱验证
		if (!validateHelper.emptyString(domain.getEmail())) {
			// 格式验证!
			if (validateHelper.illegalEmail(domain.getEmail())) {
				result.addErrorMessage("电子邮箱格式输入有误!");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(0, EMAILEN_LENGTH,
					domain.getEmail())) {
				result.addErrorMessage("电子邮箱不能大于" + EMAILEN_LENGTH + "个字符");
			}
		}
		// 学校网址验证
		if (!validateHelper.emptyString(domain.getSchoolWebSite())) {
			// 格式验证!
			if (validateHelper.illegalWebSite(domain.getSchoolWebSite())) {
				result.addErrorMessage("学校网址格式输入有误!");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(0, SCHOOLWEBSITE_LENGTH,
					domain.getSchoolWebSite())) {
				result.addErrorMessage("学校网址不能大于" + SCHOOLWEBSITE_LENGTH
						+ "个字符");
			}
		}
		// 存储设备，注册地址，接入方式验证
		if (!validateHelper.emptyString(domain.getGeneralStorage())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getGeneralStorage())) {
				result.addErrorMessage(domain.getGeneralStorage() + "不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getGeneralStorage())) {
				result.addErrorMessage(domain.getGeneralStorage() + "不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					GENERALSTORAGE_LENGTH, domain.getGeneralStorage())) {
				result.addErrorMessage(domain.getGeneralStorage() + "不能小于"
						+ MIN_LENGTH + "个字符" + "不能大于" + GENERALSTORAGE_LENGTH
						+ "个字符");
			}
		}

		// 宽带接入服务商、货物类别验证
		if (!validateHelper.emptyString(domain.getGeneralType())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getGeneralType())) {
				result.addErrorMessage("宽带接入服务商/货物类别不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getGeneralType())) {
				result.addErrorMessage("宽带接入服务商/货物类别不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					GENERALTYPE_LENGTH, domain.getGeneralType())) {
				result.addErrorMessage("宽带接入服务商/货物类别不能小于" + MIN_LENGTH + "个字符"
						+ "不能大于" + GENERALTYPE_LENGTH + "个字符");
			}
		}
		// 传真号码验证
		if (!validateHelper.emptyString(domain.getFaxNo())) {
			if (validateHelper.illegalTelephone(domain.getFaxNo())) {
				result.addErrorMessage("传真号码不合法，只能输数字和横杠(-)");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(TELEPHONE_LENGTH_MIN,
					TELEPHONE_LENGTH, domain.getFaxNo())) {
				result.addErrorMessage("传真号码不能小于" + TELEPHONE_LENGTH_MIN
						+ ",不能大于" + TELEPHONE_LENGTH + "个字符");
			}
		}
		// 注册资本验证
		if (domain.getRegisteredCapitalNo() != null) {
			// 规范验证
			if (validateHelper.illegalNumberZS(domain.getRegisteredCapitalNo()
					.toString())) {
				result.addErrorMessage("注册资本需要输入正数");
			}
			// 长度验证
			if (domain.getRegisteredCapitalNo().longValue() < 0
					|| domain.getRegisteredCapitalNo().longValue() > 999999999) {
				result.addErrorMessage("注册资本为1-999999999的正数");
			}
		}
		// 注册日期 与有效期至验证
		if (domain.getBegintime() != null) {
			if (domain.getBegintime().after(new Date())) {
				result.addErrorMessage("施工/注册开始日期不能大于当前时间");
			}
		}
		if (domain.getEndtime() != null) {
			if (domain.getEndtime().before(new Date())) {
				result.addErrorMessage("施工/注册结束日期不能小于等于当前时间");
			}
		}
		// 电脑台数，从业人数验证
		if (domain.getCountNo() != null) {
			if (validateHelper.illegalNumberZZ(domain.getCountNo().toString())) {
				result.addErrorMessage("电脑台数/从业人数需要输入正整数");
			}
			// 长度验证
			if (domain.getCountNo() > 999999999) {
				result.addErrorMessage("电脑台数/从业人数1-999999999的正整数");
			}
		}
		// 网络文化经营许可证号，主管部门
		if (!validateHelper.emptyString(domain.getGeneralManage())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getGeneralManage())) {
				result.addErrorMessage("网络文化经营许可证号/主管部门不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getGeneralManage())) {
				result.addErrorMessage("网络文化经营许可证号/主管部门不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					GENERALMANAGE_LENGTH, domain.getGeneralManage())) {
				result.addErrorMessage("主管部门不能小于" + MIN_LENGTH + "个字符" + "不能大于"
						+ GENERALMANAGE_LENGTH + "个字符");
			}
		}
		// 网络安全审核意见书号，管理部门
		if (!validateHelper.emptyString(domain.getGeneralMente())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getGeneralMente())) {
				result.addErrorMessage("网络安全审核意见书号/管理部门不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getGeneralMente())) {
				result.addErrorMessage("网络安全审核意见书号/管理部门不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					GENERALMENTE_LENGTH, domain.getGeneralMente())) {
				result.addErrorMessage("网络安全审核意见书号/管理部门不能小于" + MIN_LENGTH
						+ "个字符" + "不能大于" + GENERALMENTE_LENGTH + "个字符");
			}
		}
		// 营业面积验证
		if (domain.getBusinessArea() != null) {
			if (domain.getBusinessArea().longValue() < 0
					|| domain.getBusinessArea().longValue() > 999999999) {
				result.addErrorMessage("营业面积为1-999999999的正数");
			}
		}
		// IP验证
		if (!validateHelper.emptyString(domain.getNowip())) {
			// 格式验证
			if (!validateHelper.isIP(domain.getNowip())) {
				result.addErrorMessage("请输入目前在使用的ip地址，多个ip用逗号‘,’隔开");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(0, IP_LENGTH,
					domain.getNowip())) {
				result.addErrorMessage("ip长度不能大于" + IP_LENGTH + "个字符");
			}
		}
		// 临时上网卡数验证
		if (domain.getNetworkCardNo() != null) {
			// 格式验证
			if (validateHelper.illegalNumberZZ(domain.getNetworkCardNo()
					.toString())) {
				result.addErrorMessage("临时上网卡数只能输入正整数");
			}
			// 长度验证
			if (domain.getNetworkCardNo() > 999999999) {
				result.addErrorMessage("临时上网卡数为1-999999999的正整数");
			}
		}

		// 施工挖方量验证
		if (domain.getDigVolume() != null) {
			if (domain.getDigVolume().longValue() < 0
					|| domain.getDigVolume().longValue() > 999999999) {
				result.addErrorMessage("填方量为1-999999999的正数");
			}
		}

		// 施工填方量验证
		if (domain.getFillVolume() != null) {
			if (domain.getFillVolume().longValue() < 0
					|| domain.getFillVolume().longValue() > 999999999) {
				result.addErrorMessage("面积为1-999999999的正数");
			}
		}
		// 消防审核意见书号
		if (!validateHelper.emptyString(domain.getFireAuditOpinionNo())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getFireAuditOpinionNo())) {
				result.addErrorMessage("消防审核意见书号不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getFireAuditOpinionNo())) {
				result.addErrorMessage("消防审核意见书号不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					FIREAUDITOPINION_LENGTH, domain.getFireAuditOpinionNo())) {
				result.addErrorMessage("消防审核意见书号不能小于" + MIN_LENGTH + "个字符"
						+ "不能大于" + FIREAUDITOPINION_LENGTH + "个字符");
			}
		}
		// 网吧编号
		if (!validateHelper.emptyString(domain.getInternetBarNo())) {
			// 非法字符验证
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getInternetBarNo())) {
				result.addErrorMessage("网吧编号不能输入非法字符");
			}
			// 非法脚本验证
			if (validateHelper.illegalScript(domain.getInternetBarNo())) {
				result.addErrorMessage("网吧编号不能输入非法脚本");
			}
			// 长度验证
			if (validateHelper.illegalStringLength(MIN_LENGTH,
					INTERNETBARNO_LENGTH, domain.getInternetBarNo())) {
				result.addErrorMessage("网吧编号不能小于" + MIN_LENGTH + "个字符" + "不能大于"
						+ INTERNETBARNO_LENGTH + "个字符");
			}
		}
		return result;
	}

	private void validatorifCategory(PropertyDict category,
			ValidateResult result) {
		if (!typeIsNotNull(category)) {
			result.addErrorMessage("类别必须输入");
		}
	}

	private void validatorifType(PropertyDict type, ValidateResult result) {
		if (!typeIsNotNull(type)) {
			result.addErrorMessage("类型必须输入");
		}
	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null || org.getId() == null) {
			return false;
		}
		return true;
	}

	private void validatorifClassifiCation(PropertyDict classifiCation,
			ValidateResult result) {
		if (!typeIsNotNull(classifiCation)) {
			result.addErrorMessage("分类必须输入");
		}
	}

	public boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}
}
