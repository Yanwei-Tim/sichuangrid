package com.tianque.baseInfo.primaryOrg.primaryMembers.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.primaryOrg.primaryMembers.dao.PrimaryMembersOrgTypeDao;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.core.base.AbstractBaseDao;

@Repository("primaryMembersOrgTypeDao")
public class PrimaryMembersOrgTypeDaoImpl extends AbstractBaseDao implements
		PrimaryMembersOrgTypeDao {

	@Override
	public Long addPrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		return (Long) getSqlMapClientTemplate().insert(
				"primaryMembersOrgType.addPrimaryMembersOrgType",
				primaryMembersOrgType);
	}

	@Override
	public List<PrimaryMembersOrgType> findPrimaryMembersOrgTypeByMember(
			Long memberId) {
		return getSqlMapClientTemplate().queryForList(
				"primaryMembersOrgType.findPrimaryMembersOrgTypeByMember",
				memberId);
	}

	@Override
	public PrimaryMembersOrgType findPrimaryMembersOrgTypeByMemberAndPrimaryOrg(
			Long memberId, Long primaryOrgId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", String.valueOf(memberId));
		map.put("primaryOrgId", String.valueOf(primaryOrgId));
		return (PrimaryMembersOrgType) getSqlMapClientTemplate()
				.queryForObject(
						"primaryMembersOrgType.findPrimaryMembersOrgTypeByMemberAndPrimaryOrg",
						map);
	}

	@Override
	public void deltePrimaryMembersOrgType(Long primaryMembersId,
			Long primaryOrgId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("primaryMembersId", primaryMembersId);
		map.put("primaryOrgId", primaryOrgId);
		getSqlMapClientTemplate().delete(
				"primaryMembersOrgType.deltePrimaryMembersOrgType", map);
	}

	@Override
	public void updatePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		getSqlMapClientTemplate().update(
				"primaryMembersOrgType.updatePrimaryMembersOrgType",
				primaryMembersOrgType);
	}

	@Override
	public void updatePrimaryMembers(PrimaryMembersOrgType primaryMembersOrgType) {
		getSqlMapClientTemplate().update(
				"primaryMembersOrgType.updatePrimaryMembers",
				primaryMembersOrgType);
	}

	@Override
	public void deltePrimaryMembersOrgTypeByPrimaryMembersId(
			Long primaryMembersId) {
		getSqlMapClientTemplate()
				.delete("primaryMembersOrgType.deltePrimaryMembersOrgTypeByPrimaryMembersId",
						primaryMembersId);
	}

	@Override
	public void deltePrimaryMembersOrgTypeByPrimaryMembersIds(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		getSqlMapClientTemplate()
				.delete("primaryMembersOrgType.deltePrimaryMembersOrgTypeByPrimaryMembersIds",
						map);
	}

	@Override
	public List<PrimaryMembersOrgType> getprimaryOrgName(Long primaryMembersId) {
		return getSqlMapClientTemplate().queryForList(
				"primaryMembersOrgType.getprimaryOrgName", primaryMembersId);

	}

	@Override
	public int countFindPrimaryOrgMembers(
			PrimaryMembersOrgType primaryMembersOrgType) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"primaryMembersOrgType.countFindPrimaryOrgMembers",
				primaryMembersOrgType);
		return countNum;
	}

	@Override
	public void deletePrimaryMembersOrgType(
			PrimaryMembersOrgType primaryMembersOrgType) {
		getSqlMapClientTemplate().delete(
				"primaryMembersOrgType.deletePrimaryMembersOrgType",
				primaryMembersOrgType);
	}

	/**
	 * 
	 * @Title: updatePrimarymembersorgtypeOrgIdForOrgChange
	 * @Description: TODO修改成员关联表信息 党委组织
	 * @param @param newId
	 * @param @param oldId 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-10-17 上午10:03:40
	 */
	@Override
	public void updatePrimarymembersorgtypeOrgIdForOrgChange(Long newId,
			Long oldId) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("newId", newId);
		map.put("oldId", oldId);
		getSqlMapClientTemplate()
				.update("primaryMembersOrgType.updatePrimarymembersorgtypeOrgIdForOrgChange",
						map);
	}
}
