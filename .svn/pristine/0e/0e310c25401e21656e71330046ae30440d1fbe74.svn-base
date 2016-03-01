package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchEnterpriseDao;
import com.tianque.domain.Enterprise;
import com.tianque.domain.vo.SearchEnterpriseVo;

@Repository("searchEnterpriseDao")
public class SearchEnterpriseDaoImpl extends AbstractBaseDao implements
		SearchEnterpriseDao {
	public PageInfo<Enterprise> searchEnterprise(SearchEnterpriseVo condition,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		if (condition == null)
			return emptyEnterprisePage(pageSize);

		condition.setSortField(sortField);
		condition.setOrder(order);
		if (condition.getKeyType().equals("safetyProductionKey")) {
			condition.setImportantLocationType("SAFETYPRODUCTIONKEY");
		} else if (condition.getKeyType().equals("fireSafetyKey")) {
			condition.setImportantLocationType("FIRESAFETYKEY");
		} else if (condition.getKeyType().equals("securityKey")) {
			condition.setImportantLocationType("SECURITYKEY");
		} else if (condition.getKeyType().equals("enterpriseKey")) {
			condition.setImportantLocationType("ENTERPRISEKEY");
		} else if (condition.getKeyType().equals("enterpriseDownKey")) {
			condition.setImportantLocationType("ENTERPRISEDOWNKEY");
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("sortField", sortField); map.put("order", order);
		 * map.put("orgInteranlCode", condition.getOrgInternalCode());
		 * map.put("name", condition.getName()); map.put("businessLicense",
		 * condition.getBusinessLicense()); map.put("legalPerson",
		 * condition.getLegalPerson()); map.put("industryId",
		 * condition.getIndustryId()); map.put("typeId", condition.getTypeId());
		 * //面积区间 map.put("minArea",condition.getMinArea());
		 * map.put("maxArea",condition.getMaxArea()); // 是否维护企业（默认为否）
		 * map.put("isRiskEnterprise",condition.getIsRiskEnterprise()); //企业联系电话
		 * map.put("enterpriseTelephone",condition.getEnterpriseTelephone());
		 * //党员数量区间
		 * map.put("minPartyMemberAmount",condition.getMinPartyMemberAmount());
		 * map.put("maxPartyMemberAmount",condition.getMaxPartyMemberAmount());
		 * //企业传真 map.put("fax",condition.getFax()); //员工数区间
		 * map.put("minEmployeeAmount",condition.getMinEmployeeAmount());
		 * map.put("maxEmployeeAmount",condition.getMaxEmployeeAmount());
		 * //法人手机号码 map.put("mobileNumber",condition.getMobileNumber());
		 * //注册资金区间
		 * map.put("minRegisteredCapital",condition.getMinRegisteredCapital());
		 * map.put("maxRegisteredCapital",condition.getMaxRegisteredCapital());
		 * //法人联系电话 map.put("telephone",condition.getTelephone()); //企业地址
		 * map.put("address",condition.getAddress()); //隐患情况
		 * map.put("hiddenCases",condition.getHiddenCases()); //整改情况
		 * map.put("hiddenRectification",condition.getHiddenRectification());
		 * map.put("keyType",condition.getKeyType()); //综治负责人
		 * map.put("comprehensiveManageMember1"
		 * ,condition.getComprehensiveManageMember1() == null ? null :
		 * condition.getComprehensiveManageMember1().getName()); //综治专干
		 * map.put("comprehensiveManageMember2"
		 * ,condition.getComprehensiveManageMember2() == null ? null :
		 * condition.getComprehensiveManageMember2().getName()); //综治负责人电话
		 * map.put("comprehensiveManageMember1Telephone",condition.
		 * getComprehensiveManageMember1() == null ? null :
		 * condition.getComprehensiveManageMember1().getTelephone()); //综治专干电话
		 * map.put("comprehensiveManageMember2Telephone",condition.
		 * getComprehensiveManageMember2() == null ? null :
		 * condition.getComprehensiveManageMember2().getTelephone());
		 * map.put("isEmphasis", condition.getIsEmphasis());
		 */
		PageInfo<Enterprise> pageInfo = new PageInfo<Enterprise>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchEnterprise.countSearchEnterprise", condition);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<Enterprise> list = getSqlMapClientTemplate().queryForList(
					"searchEnterprise.searchEnterprise", condition,
					(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<Enterprise>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	private PageInfo<Enterprise> emptyEnterprisePage(int pageSize) {
		PageInfo<Enterprise> pageInfo = new PageInfo<Enterprise>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Enterprise>());
		return pageInfo;
	}

	@Override
	public List<Enterprise> searchEnterpriseForExport(
			SearchEnterpriseVo condition, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (condition == null)
			return null;
		if (isStrotFieldAvaliable(sortField)) {
			condition.setSortField(sortField);
		}
		condition.setOrder(order);
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); if
		 * (isStrotFieldAvaliable(sortField)) { map.put("sortField", sortField);
		 * } map.put("order", order); map.put("orgInteranlCode",
		 * condition.getOrgInternalCode()); map.put("name",
		 * condition.getName()); map.put("businessLicense",
		 * condition.getBusinessLicense()); map.put("legalPerson",
		 * condition.getLegalPerson()); map.put("industryId",
		 * condition.getIndustryId()); map.put("typeId", condition.getTypeId());
		 * map.put("attentionExtentId", condition.getAttentionExtentId()); //
		 * 面积区间 map.put("minArea", condition.getMinArea()); map.put("maxArea",
		 * condition.getMaxArea()); // 是否维护企业（默认为否） map.put("isRiskEnterprise",
		 * condition.getIsRiskEnterprise()); // 企业联系电话
		 * map.put("enterpriseTelephone", condition.getEnterpriseTelephone());
		 * // 党员数量区间 map.put("minPartyMemberAmount",
		 * condition.getMinPartyMemberAmount()); map.put("maxPartyMemberAmount",
		 * condition.getMaxPartyMemberAmount()); // 企业传真 map.put("fax",
		 * condition.getFax()); // 员工数区间 map.put("minEmployeeAmount",
		 * condition.getMinEmployeeAmount()); map.put("maxEmployeeAmount",
		 * condition.getMaxEmployeeAmount()); // 法人手机号码 map.put("mobileNumber",
		 * condition.getMobileNumber()); // 注册资金区间
		 * map.put("minRegisteredCapital", condition.getMinRegisteredCapital());
		 * map.put("maxRegisteredCapital", condition.getMaxRegisteredCapital());
		 * // 法人联系电话 map.put("telephone", condition.getTelephone()); // 企业地址
		 * map.put("address", condition.getAddress()); // 隐患情况
		 * map.put("hiddenCases", condition.getHiddenCases()); // 整改情况
		 * map.put("hiddenRectification", condition.getHiddenRectification());
		 * // 综治负责人 map.put("comprehensiveManageMember1", condition
		 * .getComprehensiveManageMember1() == null ? null : condition
		 * .getComprehensiveManageMember1().getName()); // 综治专干
		 * map.put("comprehensiveManageMember2", condition
		 * .getComprehensiveManageMember2() == null ? null : condition
		 * .getComprehensiveManageMember2().getName()); // 综治负责人电话
		 * map.put("comprehensiveManageMember1Telephone", condition
		 * .getComprehensiveManageMember1() == null ? null : condition
		 * .getComprehensiveManageMember1().getTelephone()); // 综治专干电话
		 * map.put("comprehensiveManageMember2Telephone", condition
		 * .getComprehensiveManageMember2() == null ? null : condition
		 * .getComprehensiveManageMember2().getTelephone()); map.put("keyType",
		 * condition.getKeyType()); map.put("isEmphasis",
		 * condition.getIsEmphasis());
		 */
		if (condition.getKeyType().equals("safetyProductionKey")) {
			condition.setImportantLocationType("SAFETYPRODUCTIONKEY");
		} else if (condition.getKeyType().equals("fireSafetyKey")) {
			condition.setImportantLocationType("FIRESAFETYKEY");
		} else if (condition.getKeyType().equals("securityKey")) {
			condition.setImportantLocationType("SECURITYKEY");
		} else if (condition.getKeyType().equals("enterpriseKey")) {
			condition.setImportantLocationType("ENTERPRISEKEY");
		} else if (condition.getKeyType().equals("enterpriseDownKey")) {
			condition.setImportantLocationType("ENTERPRISEDOWNKEY");
		}
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchEnterprise.searchEnterprise", condition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchEnterprise.searchEnterprise", condition,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	public List findEnterpriseByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchEnterprise.findEnterpriseNameAndPinyinAndOrgInternalCode",
						map);
	}

	@Override
	public Integer getCount(SearchEnterpriseVo condition) {
		if (condition.getKeyType().equals("safetyProductionKey")) {
			condition.setImportantLocationType("SAFETYPRODUCTIONKEY");
		} else if (condition.getKeyType().equals("fireSafetyKey")) {
			condition.setImportantLocationType("FIRESAFETYKEY");
		} else if (condition.getKeyType().equals("securityKey")) {
			condition.setImportantLocationType("SECURITYKEY");
		} else if (condition.getKeyType().equals("enterpriseKey")) {
			condition.setImportantLocationType("ENTERPRISEKEY");
		} else if (condition.getKeyType().equals("enterpriseDownKey")) {
			condition.setImportantLocationType("ENTERPRISEDOWNKEY");
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchEnterprise.countSearchEnterprise", condition);
	}
}
