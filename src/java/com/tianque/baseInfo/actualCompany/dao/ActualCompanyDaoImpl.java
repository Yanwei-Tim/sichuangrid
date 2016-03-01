package com.tianque.baseInfo.actualCompany.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.domain.ActualCompanyPractitioners;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("actualCompanyDao")
public class ActualCompanyDaoImpl extends AbstractBaseDao implements
		ActualCompanyDao {

	@Override
	public ActualCompany addActualCompany(ActualCompany actualCompany) {
		checkIsNotNull(actualCompany);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"actualCompany.addActualCompany", actualCompany);
		return getActualCompanyById(id);
	}

	private void checkIsNotNull(ActualCompany actualCompany) {
		if (actualCompany.getCompanyName() == null
				|| "".equals(actualCompany.getCompanyName())) {
			throw new BusinessValidationException("单位名称不能为空");
		}

	}

	@Override
	public ActualCompany getActualCompanyById(Long id) {

		return (ActualCompany) getSqlMapClientTemplate().queryForObject(
				"actualCompany.getActualCompanyById", id);
	}

	@Override
	public void deleteActualCompany(Long id) {
		getSqlMapClientTemplate().delete("actualCompany.deleteActualCompany",
				id);

	}

	@Override
	public ActualCompany updateActualCompany(ActualCompany actualCompany) {
		checkIsNotNull(actualCompany);
		getSqlMapClientTemplate().update("actualCompany.updateActualCompany",
				actualCompany);
		return getActualCompanyById(actualCompany.getId());
	}

	@Override
	public ActualCompany updateKeyCrucialPosition(ActualCompany actualCompany) {
		getSqlMapClientTemplate().update(
				"actualCompany.updateKeyCrucialPosition", actualCompany);
		return getActualCompanyById(actualCompany.getId());
	}

	@Override
	public ActualCompany updatePreventionFacilities(ActualCompany actualCompany) {
		getSqlMapClientTemplate().update(
				"actualCompany.updatePreventionFacilities", actualCompany);
		return getActualCompanyById(actualCompany.getId());
	}

	@Override
	public PageInfo<ActualCompany> findActualCompanyForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);
		map.put("sortField", sortField);
		map.put("order", sord);// 排序
		map.put("objectType", "actualCompany");
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countActualCompany", map);
		map.put("countNum", countNum);
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.findActualCompany", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<ActualCompany> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<ActualCompany> pageInfo = new PageInfo<ActualCompany>();
		pageInfo.setResult(list);
		int currentPage = pageInfo.getCurrentPage();
		pageNum = pageNum >= currentPage ? pageNum : currentPage;
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public ActualCompany getActualCompanyByCompanyNameAndOrganizationId(
			String companyName, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyName", companyName);
		map.put("orgId", orgId);
		return (ActualCompany) getSqlMapClientTemplate().queryForObject(
				"actualCompany.getActualCompanyByCompanyName", map);
	}

	@Override
	public List<ActualCompany> countActualCompanyByorgInternalCode(
			String orgInternalCode) {
		return getSqlMapClientTemplate().queryForList(
				"actualCompany.countActualCompanyByOrgId", orgInternalCode);
	}

	@Override
	public PageInfo<ActualCompany> searchActualUnitforGisByorgIdAndQueryStr(
			String queryStr, String orgInternalCode, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("queryStr", queryStr);
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countActualUnitforGisByorgIdAndQueryStr", map);
		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.searchActualUnitforGisByorgIdAndQueryStr", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);

	}

	@Override
	public ActualCompany updateactualCompanyInfoForGis(
			ActualCompany actualCompany) {
		getSqlMapClientTemplate().update(
				"actualCompany.updateactualCompanyInfoForGis", actualCompany);
		return getActualCompanyById(actualCompany.getId());
	}

	@Override
	public ActualCompany unBindActualCompanyOnMap(Long unitId) {
		getSqlMapClientTemplate().update(
				"actualCompany.unBindActualCompanyOnMap", unitId);
		return getActualCompanyById(unitId);
	}

	@Override
	public ActualCompany getActualUnitDatialInfoByUnitId(Long id, Long orgId) {
		return (ActualCompany) getSqlMapClientTemplate().queryForObject(
				"actualCompany.getActualUnitDatialInfoByUnitId", id);
	}

	@Override
	public PageInfo<ActualCompany> searchKeyUnitListSearchByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countKeyUnitListSearchByOrgInternalCode", map);
		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.searchKeyUnitListSearchByOrgInternalCode", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public List<ActualCompany> findAllBindingActualUnitByOrgInternalCodeForGis(
			String orgInternalCode) {
		return getSqlMapClientTemplate()
				.queryForList(
						"actualCompany.findAllBindingActualUnitByOrgInternalCodeForGis",
						orgInternalCode);
	}

	@Override
	public int countBindingActualUnitBybuildingIdForGis(Long buildingId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countBindingActualUnitBybuildingIdForGis",
				buildingId);
	}

	@Override
	public List<ActualCompany> searchAllRoundCompany(GisInfo minOption,
			GisInfo maxOption) {
		Map<String, GisInfo> map = new HashMap<String, GisInfo>();
		map.put("minOption", minOption);
		map.put("maxOption", maxOption);
		return (List<ActualCompany>) getSqlMapClientTemplate().queryForList(
				"actualCompany.searchAllRoundCompany", map);
	}

	@Override
	public PageInfo<ActualCompany> findActualCompanyByBuilddatasIdForPage(
			Long builddatasId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", builddatasId);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countActualCompanyByBuilddatasId", map);

		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.findActualCompanyByBuilddatasIdForPage", map,
				(pageNum - 1) * pageSize, pageSize);
		PageInfo<ActualCompany> pageInfo = new PageInfo<ActualCompany>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<ActualCompany> findunBoundActualCompanyListForPage(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String queryStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("queryStr", queryStr);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countUnboundActualCompanyByOrg", map);

		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.findUnboundActualCompanyByOrgForPage", map,
				(pageNum - 1) * pageSize, pageSize);
		PageInfo<ActualCompany> pageInfo = new PageInfo<ActualCompany>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void boundActualCompany(List<Long> houseIds, Long builddatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", houseIds);
		map.put("buildDatasId", builddatasId);
		getSqlMapClientTemplate().update("actualCompany.boundActualCompany",
				map);

	}

	@Override
	public void unboundActualCompany(List<Long> houseIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", houseIds);
		getSqlMapClientTemplate().update("actualCompany.unboundActualCompany",
				map);

	}

	@Override
	public List<LayoutTagVo> searchAllActualCompanyName4LayoutTag(
			String orgInternalCode, Long builddatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("buildDatasId", builddatasId);
		List<LayoutTagVo> layoutTagList = (List<LayoutTagVo>) getSqlMapClientTemplate()
				.queryForList("actualCompany.searchAllActualCompanyName", map);
		return layoutTagList;
	}

	@Override
	public List<ActualCompany> findActualCompanyListByBuildingId(
			Long builddatasId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("builddatasId", builddatasId);

		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.findActualCompanyByBuilddatasIdForPage", map);
		return list;
	}

	@Override
	public String getActualCompanyNameById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (String) getSqlMapClientTemplate().queryForObject(
				"actualCompany.getActualCompanyNameById", map);
	}

	@Override
	public PageInfo findActualCompanysPractitioners(ActualCompany location,
			Integer pageNum, Integer pageSize, String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sortField);
		map.put("order", sord);// 排序
		map.put("name", location.getCorporateRepresentative());
		map.put("id", location.getId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countActualCompanysPractitioners", map);
		map.put("countNum", countNum);
		List<People> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.findActualCompanysPractitioners", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo findActualCompanysAddPractitionersForList(
			ActualCompany location, Integer pageNum, Integer pageSize,
			String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sortField);
		map.put("order", sord);// 排序
		map.put("name", location.getCorporateRepresentative());
		map.put("orgInternalCode", location.getOrgInternalCode());
		map.put("id", location.getId());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"actualCompany.countActualCompanysAddPractitioners", map);
		map.put("countNum", countNum);
		List<People> list = getSqlMapClientTemplate().queryForList(
				"actualCompany.findActualCompanysAddPractitioners", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public void deleteActualCompanyPractitioners(Long id) {
		getSqlMapClientTemplate().delete(
				"actualCompany.deleteActualCompanyPractitioners", id);
	}

	@Override
	public void saveActualCompanyPractitioners(ActualCompanyPractitioners acp) {
		getSqlMapClientTemplate().insert(
				"actualCompany.saveActualCompanyPractitioners", acp);
	}

	@Override
	public List<ActualCompanyPractitioners> queryActualCompanysAddPractitionersById(
			Long id) {
		return getSqlMapClientTemplate().queryForList(
				"actualCompany.queryActualCompanysAddPractitionersById", id);
	}

	@Override
	public void delPersonForPractitionerst(ActualCompanyPractitioners acp) {
		getSqlMapClientTemplate().delete(
				"actualCompany.delPersonForPractitionerst", acp);
	}

}
