package com.tianque.core.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.IdCardUtil;

@Component("peopleHelper")
public class PeopleHelper {
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 自动填充组织机构内置编码、全拼、简拼、出生日期
	 * 
	 * @param people
	 */
	public void autoFilleOrgInternalCodeAndChinesePinyinAndBirthday(
			People people) {
		if (StringUtil.isStringAvaliable(people.getIdCardNo())) {
			people.setIdCardNo(people.getIdCardNo().toUpperCase());
		}
		autoFillOrgInternalCode(people);
		autoFillChinesePinyin(people);
		autoFillBirthday(people);
	}

	/**
	 * 自动填充组织机构内置编码
	 * 
	 * @param people
	 */
	private void autoFillOrgInternalCode(People people) {
		Organization org = organizationService.getSimpleOrgById(people
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		people.setOrgInternalCode(org.getOrgInternalCode());
	}

	/**
	 * 自动填充全拼、简拼
	 * 
	 * @param people
	 */
	public void autoFillChinesePinyin(People people) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(people
				.getName());
		people.setSimplePinyin(pinyin.get("simplePinyin"));
		people.setFullPinyin(pinyin.get("fullPinyin"));
	}

	/**
	 * 自动填充出生日期
	 * 
	 * @param people
	 */
	public void autoFillBirthday(People people) {
		if (StringUtil.isStringAvaliable(people.getIdCardNo())) {
			people.setBirthday(IdCardUtil.parseBirthday(people.getIdCardNo()));
		}
	}

	/**
	 * 自动填充性别
	 * 
	 * @param people
	 */
	public PropertyDict autoFillGender(String propertyDomainName,
			String idCardNo) {
		String genderByIdcard = getGenderByIdcard(idCardNo);
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						propertyDomainName, genderByIdcard);
		return propertyDict;
	}

	private String getGenderByIdcard(String idCard) {
		if (idCard == null || "".equals(idCard.trim())) {
			return "";
		}
		if (idCard.length() != 15 && idCard.length() != 18) {
			return "";
		}
		String genderByIdCardNo = "";
		if (idCard.length() == 15) {
			if (Long.parseLong(idCard.substring(14, 15)) % 2 == 1) {
				genderByIdCardNo = "男";
			} else {
				genderByIdCardNo = "女";
			}
		}
		if (idCard.length() == 18) {
			if (Long.parseLong(idCard.substring(16, 17)) % 2 == 1) {
				genderByIdCardNo = "男";
			} else {
				genderByIdCardNo = "女";
			}
		}
		return genderByIdCardNo;
	}
}
