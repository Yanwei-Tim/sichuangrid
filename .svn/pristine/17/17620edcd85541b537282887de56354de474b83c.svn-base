package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.EnterpriseDao;
import com.tianque.domain.ComprehensiveManageMember;
import com.tianque.domain.Enterprise;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("enterpriseDao")
public class EnterpriseDaoImpl extends AbstractBaseDao implements EnterpriseDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public Enterprise addEnterprise(Enterprise enterprise) {
		if (enterprise == null)
			throw new BusinessValidationException("参数错误");

		Long id = (Long) getSqlMapClientTemplate().insert(
				"enterprise.addEnterprise", enterprise);
		return getEnterpriseById(id);
	}

	@Override
	public Enterprise updateEnterprise(Enterprise enterprise) {
		if (enterprise == null || enterprise.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("enterprise.updateEnterprise",
				enterprise);
		return getEnterpriseById(enterprise.getId());
	}

	public Enterprise updateEmphasis(Enterprise enterprise) {
		if (enterprise == null || enterprise.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("enterprise.updateEnterprise",
				enterprise);
		return getEnterpriseById(enterprise.getId());
	}

	@Override
	public Enterprise getEnterpriseById(Long id) {
		Enterprise enterprise = (Enterprise) getSqlMapClientTemplate()
				.queryForObject("enterprise.getEnterpriseById", id);
		return enterprise;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Enterprise> findEnterprisesForListPageByOrgInternalCode(
			String orgInternalCode, int pageSize, int pageNum,
			String sortField, String order) {

		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			return emptyPage(pageSize);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sortField);
		map.put("order", order);
		return findEnterprisesForListPageByMap(map, pageSize, pageNum,
				"enterprise.countEnterprisesForListPage",
				"enterprise.findEnterprisesForListPage");
	}

	@Override
	public PageInfo<Enterprise> findEnterprisesForListPageByOrgInternalCodeAndKeyType(
			String orgInternalCode, String keyType, int pageSize, int pageNum,
			String sortField, String order, Long isEmphasis) {

		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			return emptyPage(pageSize);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		if (!StringUtil.isStringAvaliable(keyType)) {
			throw new BusinessValidationException("参数错误");
		}
		map.put("keyType", keyType);
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("isEmphasis", isEmphasis);
		if (keyType.equals("safetyProductionKey")) {
			map.put("importantLocationType", "SAFETYPRODUCTIONKEY");
		} else if (keyType.equals("fireSafetyKey")) {
			map.put("importantLocationType", "FIRESAFETYKEY");
		} else if (keyType.equals("securityKey")) {
			map.put("importantLocationType", "SECURITYKEY");
		}// 加入规上----规下企业
		else if (keyType.equals("enterpriseKey")) {
			map.put("importantLocationType", "ENTERPRISEKEY");
		} else if (keyType.equals("enterpriseDownKey")) {
			map.put("importantLocationType", "ENTERPRISEDOWNKEY");
		}
		return findEnterprisesForListPageByMap(map, pageSize, pageNum,
				"enterprise.countEnterprisesForListPageByKeyType",
				"enterprise.findEnterprisesForListPageByKeyType");
	}

	private PageInfo<Enterprise> findEnterprisesForListPageByMap(Map map,
			int pageSize, int pageNum, String sqlIdCount, String sqlIdResult) {
		Integer countEnterprises = (Integer) getSqlMapClientTemplate()
				.queryForObject(sqlIdCount, map);
		int pageCount = 0;
		if ((countEnterprises % pageSize) == 0) {
			pageCount = countEnterprises / pageSize;
		} else {
			pageCount = countEnterprises / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<Enterprise> list = getSqlMapClientTemplate().queryForList(
				sqlIdResult, map, (pageNum - 1) * pageSize, pageSize);

		PageInfo<Enterprise> pageInfo = new PageInfo<Enterprise>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countEnterprises);
		pageInfo.setCurrentPage(pageNum);

		return pageInfo;
	}

	public int deleteEnterpriseById(Long id) {
		if (id == null) {
			return 0;
		}
		//		SiteMsg msg = new SiteMsg(getEnterpriseById(id), OperateMode.DELETE);
		int count = getSqlMapClientTemplate().delete(
				"enterprise.deleteEnterpriseById", id);
		//		activeMQMessageSender.send(msg);
		return count;
	}

	private PageInfo<Enterprise> emptyPage(int pageSize) {
		PageInfo<Enterprise> pageInfo = new PageInfo<Enterprise>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Enterprise>());
		return pageInfo;

	}

	@Override
	public ComprehensiveManageMember addComprehensiveManageMember(
			ComprehensiveManageMember comprehensiveManageMember) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"enterprise.addComprehensiveManageMember",
				comprehensiveManageMember);
		return getComprehensiveManageMemberById(id);
	}

	@Override
	public ComprehensiveManageMember getComprehensiveManageMemberById(Long id) {
		return (ComprehensiveManageMember) getSqlMapClientTemplate()
				.queryForObject("enterprise.getComprehensiveManageMemberById",
						id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComprehensiveManageMember> findComprehensiveManageMembersByEnterpriseId(
			Long id) {
		return getSqlMapClientTemplate().queryForList(
				"enterprise.findComprehensiveManageMembersByEnterpriseId", id);
	}

	@Override
	public int deleteComprehensiveManageMembersById(Long id) {
		if (id == null)
			return 0;

		return getSqlMapClientTemplate().delete(
				"enterprise.deleteComprehensiveManageMemberById", id);
	}

	@Override
	public int deleteComprehensiveManageMembersByEnterpriseId(Long id) {
		if (id == null)
			return 0;
		return getSqlMapClientTemplate().delete(
				"enterprise.deleteComprehensiveManageMemberByEnterpriseId", id);
	}

	@Override
	public ComprehensiveManageMember updateComprehensiveManageMemberMember(
			ComprehensiveManageMember comprehensiveManageMember) {
		getSqlMapClientTemplate().update(
				"enterprise.updateComprehensiveManageMember",
				comprehensiveManageMember);

		return getComprehensiveManageMemberById(comprehensiveManageMember
				.getId());
	}

	@Override
	public Enterprise findEnterpriseByNameAndOrgId(String name, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		return (Enterprise) getSqlMapClientTemplate().queryForObject(
				"enterprise.findEnterpriseByNameAndOrgId", map);
	}

	@Override
	public Enterprise findEnterpriseByNameAndOrgIdAndId(String name,
			Long orgId, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		map.put("id", id);
		return (Enterprise) getSqlMapClientTemplate().queryForObject(
				"enterprise.findEnterpriseByNameAndOrgIdAndId", map);
	}

	@Override
	public Enterprise getEnterpriseByNameAndOrgIdAndKeyType(String name,
			Long orgId, String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		map.put("keyType", keyType);
		return (Enterprise) getSqlMapClientTemplate().queryForObject(
				"enterprise.getEnterpriseByNameAndOrgIdAndKeyType", map);
	}

	@Override
	public int getEnterpriseCountByOrgIdAndKeyType(Long orgId, String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("keyType", keyType);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"enterprise.getEnterpriseCountByOrgIdAndKeyType", map);
	}

	@Override
	public int getEnterpriseCountByOrgCodeAndKeyType(String orgCode,
			String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("keyType", keyType);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"enterprise.getEnterpriseCountByOrgCodeAndKeyType", map);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		getSqlMapClientTemplate().update("enterprise.updateEmphasiseById", map);

	}

}
