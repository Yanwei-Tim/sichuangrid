package com.tianque.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ConstructionUnit;
import com.tianque.exception.base.BusinessValidationException;

@Repository("constructionUnitDao")
public class ConstructionUnitDaoImpl extends AbstractBaseDao implements
		ConstructionUnitDao {

	@Override
	public ConstructionUnit addConstructionUnit(
			ConstructionUnit constructionUnit) {
		if (!validateNotNull(constructionUnit)) {
			throw new BusinessValidationException("参数错误");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"constructionUnit.addConstructionUnit", constructionUnit);

		return getConstructionUnitById(id);
	}

	@Override
	public ConstructionUnit getConstructionUnitById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		return (ConstructionUnit) getSqlMapClientTemplate().queryForObject(
				"constructionUnit.getConstructionUnitById", id);
	}

	@Override
	public ConstructionUnit updateConstructionUnit(
			ConstructionUnit constructionUnit) {
		getSqlMapClientTemplate().update(
				"constructionUnit.updateConstructionUnit", constructionUnit);
		return this.getConstructionUnitById(constructionUnit.getId());
	}

	@Override
	public ConstructionUnit updateConstructionUnitById(
			ConstructionUnit constructionUnit) {
		getSqlMapClientTemplate()
				.update("constructionUnit.updateConstructionUnitById",
						constructionUnit);
		return this.getConstructionUnitById(constructionUnit.getId());
	}

	@Override
	public int deleteConstructionUnitById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("ID为空");
		}
		int count = getSqlMapClientTemplate().delete(
				"constructionUnit.deleteConstructionUnitById", id);
		return count;
	}

	public boolean validateNotNull(ConstructionUnit constructionUnit) {
		if (null == constructionUnit
				|| null == constructionUnit.getProjectName()
				|| "".equals(constructionUnit.getProjectName().trim())) {
			return false;
		}
		if (null == constructionUnit.getOrganization()) {
			return false;
		}
		return true;
	}

	@Override
	public PageInfo<ConstructionUnit> findConstructionUnitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageSize, Integer pageNum,
			String sidx, String sord, Long isEmphasis) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", isEmphasis);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"constructionUnit.countConstructionUnits", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		List<ConstructionUnit> list = getSqlMapClientTemplate().queryForList(
				"constructionUnit.findConstructionUnits", map,
				(pageNum - 1) * pageSize, pageSize);

		PageInfo<ConstructionUnit> pageInfo = new PageInfo<ConstructionUnit>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	@Override
	public ConstructionUnit getConstructionUnitByProjectName(
			String projectName, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectName", projectName);
		map.put("id", id);
		ConstructionUnit domain = (ConstructionUnit) getSqlMapClientTemplate()
				.queryForObject(
						"constructionUnit.getConstructionUnitByProjectName",
						map);
		return domain;
	}

}
