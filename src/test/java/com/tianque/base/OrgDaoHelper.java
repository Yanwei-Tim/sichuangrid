package com.tianque.base;

import java.util.Calendar;
import java.util.Date;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class OrgDaoHelper {

	public static Organization addRootOrg(
			OrganizationDubboService organizationDubboService) {
		Organization parentOrg = new Organization();
		parentOrg.setCreateUser("admin");
		parentOrg.setCreateDate(Calendar.getInstance().getTime());
		parentOrg.setOrgName("浙江省");
		parentOrg.setFullPinyin(Chinese2pinyin.changeChinese2Pinyin("浙江省").get(
				"fullPinyin"));
		parentOrg.setSimplePinyin(Chinese2pinyin.changeChinese2Pinyin("浙江省")
				.get("simplePinyin"));
		PropertyDict orgTypeDict = new PropertyDict();
		orgTypeDict.setId(1L);
		parentOrg.setOrgType(orgTypeDict);
		parentOrg.setSeq(1L);
		parentOrg.setParentOrg(null);
		parentOrg.setOrgInternalCode("0001");

		return organizationDubboService.addOrganization(parentOrg);
	}

	public static Organization addOrganization(Organization parent,
			String subInnerCode, String orgName,
			OrganizationDubboService organizationDubboService) {
		Organization organization = new Organization();
		organization.setParentOrg(parent);
		organization.setOrgName(orgName);
		organization.setContactWay("13898754568");
		organization.setCreateDate(new Date());
		PropertyDict orgTypeDict = new PropertyDict();
		orgTypeDict.setId(1L);
		organization.setOrgType(orgTypeDict);
		organization.setOrgInternalCode(parent == null ? subInnerCode : parent
				.getOrgInternalCode() + "." + subInnerCode);
		organization.setSimplePinyin(Chinese2pinyin.changeChinese2Pinyin(
				orgName).get("simplePinyin"));
		organization.setFullPinyin(Chinese2pinyin.changeChinese2Pinyin(orgName)
				.get("fullPinyin"));
		organization.setCreateUser("admin");
		organization.setSeq(1L);

		return organizationDubboService.addOrganization(organization);

	}

	public static Organization addFunctionalOrganization(Organization parent,
			String subInnerCode, String orgName,
			OrganizationDubboService organizationDubboService) {
		Organization organization = new Organization();
		organization.setParentOrg(parent);
		organization.setOrgName(orgName);
		organization.setContactWay("13898754568");
		organization.setCreateDate(new Date());
		PropertyDict orgTypeDict = new PropertyDict();
		orgTypeDict.setId(new Long(OrganizationType.FUNCTIONAL_ORG));
		organization.setOrgType(orgTypeDict);
		organization.setOrgInternalCode(parent == null ? subInnerCode : parent
				.getOrgInternalCode() + "." + subInnerCode);
		organization.setSimplePinyin(Chinese2pinyin.changeChinese2Pinyin(
				orgName).get("simplePinyin"));
		organization.setFullPinyin(Chinese2pinyin.changeChinese2Pinyin(orgName)
				.get("fullPinyin"));
		organization.setCreateUser("admin");
		organization.setSeq(1L);

		return organizationDubboService.addOrganization(organization);

	}
}
