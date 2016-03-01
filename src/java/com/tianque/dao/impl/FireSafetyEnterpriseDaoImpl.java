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
import com.tianque.dao.FireSafetyEnterpriseDao;
import com.tianque.domain.ComprehensiveManageMember;
import com.tianque.domain.FireSafetyEnterprise;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("fireSafetyEnterpriseDao")
public class FireSafetyEnterpriseDaoImpl extends AbstractBaseDao implements
		FireSafetyEnterpriseDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public FireSafetyEnterprise addFireSafetyEnterprise(
			FireSafetyEnterprise fireSafetyEnterprise) {
		if (fireSafetyEnterprise == null)
			throw new BusinessValidationException("参数错误");

		Long id = (Long) getSqlMapClientTemplate().insert(
				"fireSafetyEnterprise.addFireSafetyEnterprise",
				fireSafetyEnterprise);
		return getFireSafetyEnterpriseById(id);
	}

	@Override
	public FireSafetyEnterprise updateFireSafetyEnterprise(
			FireSafetyEnterprise enterprise) {
		if (enterprise == null || enterprise.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update(
				"fireSafetyEnterprise.updateFireSafetyEnterprise", enterprise);
		return getFireSafetyEnterpriseById(enterprise.getId());
	}

	public FireSafetyEnterprise updateEmphasis(FireSafetyEnterprise enterprise) {
		if (enterprise == null || enterprise.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update(
				"fireSafetyEnterprise.updateFireSafetyEnterprise", enterprise);
		return getFireSafetyEnterpriseById(enterprise.getId());
	}

	@Override
	public FireSafetyEnterprise getFireSafetyEnterpriseById(Long id) {
		FireSafetyEnterprise enterprise = (FireSafetyEnterprise) getSqlMapClientTemplate()
				.queryForObject(
						"fireSafetyEnterprise.getFireSafetyEnterpriseById", id);
		return enterprise;
	}

	@Override
	public PageInfo<FireSafetyEnterprise> findFireSafetyEnterprisesForListPageByOrgInternalCodeAndKeyType(
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
		map.put("importantLocationType", "FIRESAFETYKEY");
		return findFireSafetyEnterprisesForListPageByMap(
				map,
				pageSize,
				pageNum,
				"fireSafetyEnterprise.countFireSafetyEnterprisesForListPageByKeyType",
				"fireSafetyEnterprise.findFireSafetyEnterprisesForListPageByKeyType");
	}

	private PageInfo<FireSafetyEnterprise> findFireSafetyEnterprisesForListPageByMap(
			Map map, int pageSize, int pageNum, String sqlIdCount,
			String sqlIdResult) {
		Integer countEnterprises = (Integer) getSqlMapClientTemplate()
				.queryForObject(sqlIdCount, map);
		int pageCount = 0;
		if ((countEnterprises % pageSize) == 0) {
			pageCount = countEnterprises / pageSize;
		} else {
			pageCount = countEnterprises / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<FireSafetyEnterprise> list = getSqlMapClientTemplate()
				.queryForList(sqlIdResult, map, (pageNum - 1) * pageSize,
						pageSize);

		PageInfo<FireSafetyEnterprise> pageInfo = new PageInfo<FireSafetyEnterprise>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(countEnterprises);
		pageInfo.setCurrentPage(pageNum);

		return pageInfo;
	}

	@Override
	public int deleteFireSafetyEnterpriseById(Long id) {
		if (id == null) {
			return 0;
		}
		// SiteMsg msg = new SiteMsg(getFireSafetyEnterpriseById(id),
		// OperateMode.DELETE);
		int count = getSqlMapClientTemplate().delete(
				"fireSafetyEnterprise.deleteFireSafetyEnterpriseById", id);
		// activeMQMessageSender.send(msg);
		return count;
	}

	private PageInfo<FireSafetyEnterprise> emptyPage(int pageSize) {
		PageInfo<FireSafetyEnterprise> pageInfo = new PageInfo<FireSafetyEnterprise>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<FireSafetyEnterprise>());
		return pageInfo;

	}

	@Override
	public ComprehensiveManageMember addComprehensiveManageMember(
			ComprehensiveManageMember comprehensiveManageMember) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fireSafetyEnterprise.addComprehensiveManageMember",
				comprehensiveManageMember);
		return getComprehensiveManageMemberById(id);
	}

	@Override
	public ComprehensiveManageMember getComprehensiveManageMemberById(Long id) {
		return (ComprehensiveManageMember) getSqlMapClientTemplate()
				.queryForObject(
						"fireSafetyEnterprise.getComprehensiveManageMemberById",
						id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComprehensiveManageMember> findComprehensiveManageMembersByEnterpriseId(
			Long id) {
		return getSqlMapClientTemplate()
				.queryForList(
						"fireSafetyEnterprise.findComprehensiveManageMembersByEnterpriseId",
						id);
	}

	@Override
	public int deleteComprehensiveManageMembersById(Long id) {
		if (id == null)
			return 0;

		return getSqlMapClientTemplate().delete(
				"fireSafetyEnterprise.deleteComprehensiveManageMemberById", id);
	}

	@Override
	public int deleteComprehensiveManageMembersByEnterpriseId(Long id) {
		if (id == null)
			return 0;
		return getSqlMapClientTemplate()
				.delete("fireSafetyEnterprise.deleteComprehensiveManageMemberByEnterpriseId",
						id);
	}

	@Override
	public ComprehensiveManageMember updateComprehensiveManageMemberMember(
			ComprehensiveManageMember comprehensiveManageMember) {
		getSqlMapClientTemplate().update(
				"fireSafetyEnterprise.updateComprehensiveManageMember",
				comprehensiveManageMember);

		return getComprehensiveManageMemberById(comprehensiveManageMember
				.getId());
	}

	@Override
	public FireSafetyEnterprise getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(
			String name, Long orgId, String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		map.put("keyType", keyType);
		return (FireSafetyEnterprise) getSqlMapClientTemplate()
				.queryForObject(
						"fireSafetyEnterprise.getFireSafetyEnterpriseByNameAndOrgIdAndKeyType",
						map);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		getSqlMapClientTemplate().update(
				"fireSafetyEnterprise.updateEmphasiseById", map);

	}

}
