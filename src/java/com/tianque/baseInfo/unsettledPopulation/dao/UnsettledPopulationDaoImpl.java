package com.tianque.baseInfo.unsettledPopulation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.util.PopulationCatalog;

@Repository("unsettledPopulationDao")
public class UnsettledPopulationDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<UnsettledPopulation, UnsettledPopulation>
		implements UnsettledPopulationDao {

	@Override
	public UnsettledPopulation addUnsettledPopulation(
			UnsettledPopulation unsettledPopulation) {
		checkIsNull(unsettledPopulation);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"unsettledPopulation.addUnsettledPopulation",
				unsettledPopulation);
		unsettledPopulation = getUnsettledPopulationById(id);
		return unsettledPopulation;
	}

	@Override
	public PageInfo<UnsettledPopulation> findUnsettledPopulationsForPageByOrgInternalCode(
			Long logOut, Long isDeath, String orgInternalCode, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("isDeath", isDeath);
		query.put("logOut", logOut);
		query.put("orgInternalCode", orgInternalCode);
		query.put("sortField", sortField);
		query.put("order", order);
		PageInfo<UnsettledPopulation> unst = null;
		unst = findUnsettledPopulationsForPageByMap(query, pageNum, pageSize);
		return unst;
	}

	@Override
	public UnsettledPopulation getUnsettledPopulationById(Long id) {
		UnsettledPopulation unsettledPopulation = null;
		unsettledPopulation = (UnsettledPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"unsettledPopulation.getUnsettledPopulationById", id);
		return unsettledPopulation;
	}

	@Override
	public UnsettledPopulation getUnsettledPopulationByIdCard(
			List<String> idCardNoList, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("idCardNoList", idCardNoList);
		UnsettledPopulation unsettledPopulation = (UnsettledPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"unsettledPopulation.getUnsettledPopulationByIdCard",
						query);
		return unsettledPopulation;
	}

	@Override
	public List<UnsettledPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", excludePopulationId);
		map.put("orgId", orgId);
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		return getSqlMapClientTemplate()
				.queryForList(
						"unsettledPopulation.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId",
						map);
	}

	@Override
	public UnsettledPopulation updateUnsettledPopulation(
			UnsettledPopulation unsettledPopulation) {
		checkIsNull(unsettledPopulation);
		getSqlMapClientTemplate().update(
				"unsettledPopulation.updateUnsettledPopulation",
				unsettledPopulation);
		return getUnsettledPopulationById(unsettledPopulation.getId());
	}

	@Override
	public int deleteUnsettledPopulationById(Long id) {

		if (id == null)
			return 0;
		UnsettledPopulation unsettledPopulation = getUnsettledPopulationById(id);
		int deleteCount = getSqlMapClientTemplate().delete(
				"unsettledPopulation.deleteUnsettledPopulationById", id);
		if (unsettledPopulation != null && unsettledPopulation.getId() != null) {
		}
		return deleteCount;
	}

	@Override
	public UnsettledPopulation getUnsettledPopulationByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("organizationId", organizationId);
		UnsettledPopulation unsettledPopulation = null;
		unsettledPopulation = (UnsettledPopulation) getSqlMapClientTemplate()
				.queryForObject(
						"unsettledPopulation.getUnsettledPopulationByIdCardAndOrganizationId",
						map);
		return unsettledPopulation;
	}

	private PageInfo<UnsettledPopulation> findUnsettledPopulationsForPageByMap(
			Map<String, Object> query, Integer pageNum, Integer pageSize) {
		Integer countUnsettledPopulation = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"unsettledPopulation.countUnsettledPopulations", query);
		pageNum = getPageNumByUnsettledPopulations(pageNum, pageSize,
				countUnsettledPopulation);
		List<UnsettledPopulation> list = getSqlMapClientTemplate()
				.queryForList("unsettledPopulation.findUnsettledPopulations",
						query, (pageNum - 1) * pageSize, pageSize);
		return getPageInfoOfUnsettledPopulation(pageNum, pageSize,
				countUnsettledPopulation, list);
	}

	private PageInfo<UnsettledPopulation> getPageInfoOfUnsettledPopulation(
			Integer pageNum, Integer pageSize,
			Integer countUnsettledPopulation, List<UnsettledPopulation> list) {
		PageInfo<UnsettledPopulation> pageInfo = new PageInfo<UnsettledPopulation>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countUnsettledPopulation);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Integer getPageNumByUnsettledPopulations(Integer pageNum,
			Integer pageSize, Integer countUnsettledPopulation) {
		int pageCount = 0;
		if ((countUnsettledPopulation % pageSize) == 0) {
			pageCount = countUnsettledPopulation / pageSize;
		} else {
			pageCount = countUnsettledPopulation / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return pageNum;
	}

	private void checkIsNull(UnsettledPopulation unsettledPopulation) {
		if (unsettledPopulation == null) {
			throw new BusinessValidationException("不能为空!");
		} else {
			if (null == unsettledPopulation.getName()) {
				throw new BusinessValidationException("姓名不能为空!");
			}
			if (null == unsettledPopulation.getGender()
					|| null == unsettledPopulation.getGender().getId()) {
				throw new BusinessValidationException("性别不能为空!");
			}

			if (null == unsettledPopulation.getOrganization()
					|| null == unsettledPopulation.getOrganization().getId()) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
		}
	}

	@Override
	public UnsettledPopulation findGisUnsettledPopulationById(Long personId) {
		return (UnsettledPopulation) getSqlMapClientTemplate().queryForObject(
				"unsettledPopulation.findGisUnsettledPopulationById", personId);
	}

	@Override
	public void updateActualPopulationToHasHouseState(Long populationId,
			String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", true);
		map.put("noHouseReason", "");
		map.put("currentAddress", address);
		getSqlMapClientTemplate().update(
				"unsettledPopulation.updateActualPopulationToHasHouseState",
				map);
	}

	@Override
	public List<UnsettledPopulation> findAllBindingUnsettledPopulationByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("personType", catalog.getParentCatalog());
		map.put("populationType", catalog.getCatalog());
		map.put("defaultLivingHouse", Boolean.TRUE);
		return getSqlMapClientTemplate()
				.queryForList(
						"unsettledPopulation.findAllBindingUnsettledPopulationByorgCodeForGis",
						map);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("death", death);
		map.put("logoutState", logoutState);
		getSqlMapClientTemplate().update(
				"unsettledPopulation.updateDeathAndLogoutStateById", map);
	}

	@Override
	public UnsettledPopulation changeUnsettledPopulationIdCardNo(
			Countrymen countrymen, String idCardNo) {
		Map map = new HashMap();
		map.put("countrymen", countrymen);
		map.put("idCardNo", idCardNo);
		getSqlMapClientTemplate().update(
				"unsettledPopulation.updateUnsettledPopulationIdCardNo", map);
		return getUnsettledPopulationById(countrymen.getId());
	}

}
