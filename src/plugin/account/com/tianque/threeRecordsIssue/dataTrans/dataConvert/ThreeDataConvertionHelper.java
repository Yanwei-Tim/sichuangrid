package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.threeRecordsIssue.dataTrans.dataImport.ExcelDataImport;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Component("threeDataConvertionHelper")
@Scope("prototype")
public class ThreeDataConvertionHelper {
	private final static Logger logger = LoggerFactory.getLogger(ThreeDataConvertionHelper.class);
	@Autowired
	private PropertyDictService propertyService;
	@Autowired
	private OrganizationDubboRemoteService orgService;

	// private Map<Long ,Organization> cacheOrgs=new HashMap<Long
	// ,Organization>();

	public PropertyDict convertToPropertyDict(String propertyDomainName,
			String dictDisplayName) {
		if (!StringUtil.isStringAvaliable(dictDisplayName)) {
			return new PropertyDict();
		}
		return propertyService.findPropertyDictByDomainNameAndDictDisplayName(
				propertyDomainName, dictDisplayName);
	}

	public List<PropertyDict> convertToPropertyDicts(String propertyDomainName,
			int[] internalIds) {
		if (internalIds == null || internalIds.length == 0) {
			return null;
		}
		return propertyService.findPropertyDictByDomainNameAndInternalIds(
				propertyDomainName, internalIds);
	}

	public Date convertToDate(String dateText) {
		if (!StringUtil.isStringAvaliable(dateText)) {
			return null;
		}
		Date result = null;
		try {
			result = DateUtils.parseDate(dateText, "yyyy/MM/dd hh:mm:ss a",
					"yyyy-MM-dd hh:mm:ss a", "yyyy/MM/dd hh:mm:ss a", "yyyy-MM-dd HH:mm:ss",
					"yyyy/MM/dd ", "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Organization convertToOrganization(Organization uploadOrganization,
			String orgName) {
		if (null == orgName || orgName.isEmpty()) {
			return uploadOrganization;
		}
		orgName = orgName.trim();
		String[] unitNameSplit = orgName.split("->");
		List<Organization> orgList = null;
		if (unitNameSplit.length == 2) {
			orgList = orgService.findOrganizationsByOrgName(unitNameSplit[1]);
		} else {
			orgList = orgService.findOrganizationsByOrgName(orgName);
		}
		Organization nowOrg = null;
		if (orgList.size() == 1) {
			nowOrg = orgList.get(0);
		} else if (orgList.size() > 1) {
			// excel中设定的单位
			String settingOrg = uploadOrganization.getFullOrgName();
			if (StringUtils.isBlank(settingOrg)) {
				settingOrg = orgService.getFullOrgById(uploadOrganization.getId()).toString();
			}
			for (Organization org : orgList) {
				org = orgService.getFullOrgById(org.getId());
				if (org.getFullOrgName().startsWith(settingOrg)
						&& org.getFullOrgName().endsWith(orgName)) {
					nowOrg = org;
					break;
				}
			}
		}
		if (nowOrg != null && StringUtils.isBlank(nowOrg.getFullOrgName())) {
			nowOrg = orgService.getFullOrgById(nowOrg.getId());
		}
		if (nowOrg != null
				&& (!nowOrg.getFullOrgName().startsWith(uploadOrganization.getFullOrgName()) || !nowOrg
						.getFullOrgName().endsWith(orgName))) {
			nowOrg = null;
		}
		if (nowOrg == null) {
			String errorMsg = "未找到组织：" + uploadOrganization.getFullOrgName() + "==》" + orgName;
			ExcelDataImport.setThreadLocal("error_msg", errorMsg);
			String maybeOrg = "";
			if (orgList != null) {
				for (Organization o : orgList) {
					maybeOrg += o.getFullOrgName() + ";";
				}
			}
			throw new BusinessValidationException(errorMsg + ",可能有：" + maybeOrg);
		}
		return nowOrg;

		//		List<Organization> results = null;
		//		if (orgName != null)
		//			orgName = orgName.trim();
		//		// if (cacheOrgs.containsKey(key))
		//		int value = orgName.indexOf("@");
		//		if (value == -1) {
		//			results = orgService.findOrganizationsByOrgNameAndParentId(null,
		//					orgName, uploadOrganization.getParentOrg().getId());
		//			if (results == null || results.size() == 0) {
		//				results = orgService.findOrganizationsByOrgNameAndParentId(
		//						null, orgName, uploadOrganization.getId());
		//			}
		//		}
		//		if (value != -1) {
		//			String countryOrgName = orgName.substring(0, value);
		//			String gridOrgName = orgName.substring(value + 1);
		//			results = orgService.findOrganizationsByOrgNameAndParentId(null,
		//					countryOrgName, uploadOrganization.getId());
		//			if (results == null || results.size() != 1) {
		//				return null;
		//			}
		//			results = orgService.findOrganizationsByOrgNameAndParentId(null,
		//					gridOrgName, results.get(0).getId());
		//		}
		//
		//		return results != null && results.size() == 1 ? results.get(0) : null;
	}

	public boolean convertToBoolean(String booleanText) {
		if (!StringUtil.isStringAvaliable(booleanText)) {
			return Boolean.FALSE;
		}
		return "是".equals(booleanText.trim()) ? true : false;
	}

	public Double convertToDouble(String doubleText) {
		if (StringUtil.isStringAvaliable(doubleText)) {
			return Double.valueOf(doubleText);
		} else {
			return null;
		}
	}

	public double convertToDoubleValue(String doubleText) {
		Double result = convertToDouble(doubleText);
		return result == null ? 0 : result.doubleValue();
	}

	public BigDecimal convertToBigDecima(String bigText) {
		if (StringUtil.isStringAvaliable(bigText)) {
			return new BigDecimal(bigText);
		} else {
			return null;
		}
	}

	public Long convertToLong(String longText) {
		if (StringUtil.isStringAvaliable(longText)) {
			return Long.valueOf(longText);
		} else {
			return null;
		}
	}

	public Integer convertToInteger(String text) {
		if (StringUtil.isStringAvaliable(text)) {
			return Integer.valueOf(text);
		} else {
			return null;
		}
	}

}
