package com.tianque.baseInfo.emergencyShelter.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.emergencyShelter.domain.EmergencyShelter;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("emergencyShelterDao")
public class EmergencyShelterDaoImpl extends AbstractBaseDao implements
		EmergencyShelterDao {

	@Override
	public EmergencyShelter addEmergencyShelter(
			EmergencyShelter emergencyShelter) {
		checkIsNotNull(emergencyShelter);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"emergencyShelter.addEmergencyShelter", emergencyShelter);
		return getEmergencyShelterById(id);
	}

	private void checkIsNotNull(EmergencyShelter emergencyShelter) {
		if (emergencyShelter.getName() == null
				|| "".equals(emergencyShelter.getName())) {
			throw new BusinessValidationException("单位名称不能为空");
		}
	}

	@Override
	public void deleteEmergencyShelter(Long id) {
		getSqlMapClientTemplate().delete(
				"emergencyShelter.deleteEmergencyShelter", id);
	}

	@Override
	public PageInfo<EmergencyShelter> findEmergencyShelterForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"emergencyShelter.countEmergencyShelter", map);
		map.put("countNum", countNum);
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"emergencyShelter.findEmergencyShelter", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<EmergencyShelter> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<EmergencyShelter> pageInfo = new PageInfo<EmergencyShelter>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public EmergencyShelter getEmergencyShelterByNameAndOrganizationId(
			String name, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		return (EmergencyShelter) getSqlMapClientTemplate().queryForObject(
				"emergencyShelter.getEmergencyShelterByName", map);
	}

	@Override
	public EmergencyShelter getEmergencyShelterById(Long id) {
		return (EmergencyShelter) getSqlMapClientTemplate().queryForObject(
				"emergencyShelter.getEmergencyShelterById", id);
	}

	@Override
	public EmergencyShelter updateEmergencyShelter(
			EmergencyShelter emergencyShelter) {
		getSqlMapClientTemplate().update(
				"emergencyShelter.updateEmergencyShelter", emergencyShelter);
		return getEmergencyShelterById(emergencyShelter.getId());
	}

	@Override
	public void deleteEmergencyShelterByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		getSqlMapClientTemplate().delete(
				"emergencyShelter.deleteEmergencyShelterByIds", ids);
	}

}
