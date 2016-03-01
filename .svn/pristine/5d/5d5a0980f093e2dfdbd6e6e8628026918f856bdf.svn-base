package com.tianque.baseInfo.laborEmploymentUnit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

/**
 * 劳动用工单位
 */

@Repository("laborEmploymentUnitDao")
public class LaborEmploymentUnitDaoImpl extends AbstractBaseDao implements
		LaborEmploymentUnitDao {

	@Override
	public LaborEmploymentUnit addLaborEmploymentUnit(
			LaborEmploymentUnit laborEmploymentUnit) {
		checkIsNotNull(laborEmploymentUnit);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"laborEmploymentUnit.addLaborEmploymentUnit",
				laborEmploymentUnit);
		return getLaborEmploymentUnitById(id);
	}

	private void checkIsNotNull(LaborEmploymentUnit laborEmploymentUnit) {
		if (laborEmploymentUnit.getCompanyName() == null
				|| "".equals(laborEmploymentUnit.getCompanyName())) {
			throw new BusinessValidationException("单位名称不能为空");
		}
	}

	@Override
	public LaborEmploymentUnit getLaborEmploymentUnitById(Long id) {
		return (LaborEmploymentUnit) getSqlMapClientTemplate().queryForObject(
				"laborEmploymentUnit.getLaborEmploymentUnitById", id);
	}

	@Override
	public LaborEmploymentUnit updateBusinessData(
			LaborEmploymentUnit laborEmploymentUnit) {
		getSqlMapClientTemplate().update(
				"laborEmploymentUnit.updateBusinessData", laborEmploymentUnit);
		return getLaborEmploymentUnitById(laborEmploymentUnit.getId());
	}

	@Override
	public LaborEmploymentUnit updateLaborEmploymentUnit(
			LaborEmploymentUnit laborEmploymentUnit) {
		checkIsNotNull(laborEmploymentUnit);
		getSqlMapClientTemplate().update(
				"laborEmploymentUnit.updateLaborEmploymentUnit",
				laborEmploymentUnit);
		return getLaborEmploymentUnitById(laborEmploymentUnit.getId());
	}

	@Override
	public void deleteLaborEmploymentUnit(Long id) {
		getSqlMapClientTemplate().delete(
				"laborEmploymentUnit.deleteLaborEmploymentUnit", id);
	}

	@Override
	public LaborEmploymentUnit getLaborEmploymentUnitByCompanyNameAndOrganizationId(
			String companyName, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyName", companyName);
		map.put("orgId", orgId);
		return (LaborEmploymentUnit) getSqlMapClientTemplate().queryForObject(
				"laborEmploymentUnit.getLaborEmploymentUnitByCompanyName", map);
	}

	@Override
	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);
		map.put("sortField", sortField);
		map.put("sord", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"laborEmploymentUnit.countLaborEmploymentUnit", map);
		map.put("countNum", countNum);
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"laborEmploymentUnit.findLaborEmploymentUnit", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<LaborEmploymentUnit> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<LaborEmploymentUnit> pageInfo = new PageInfo<LaborEmploymentUnit>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
