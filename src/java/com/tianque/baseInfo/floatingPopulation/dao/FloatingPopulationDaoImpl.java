package com.tianque.baseInfo.floatingPopulation.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchFloatingPopulationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.syncSolrIndex.SyncPopulationSolrIndexForMQ;
import com.tianque.service.util.PopulationCatalog;

@Repository("floatingPopulationDao")
public class FloatingPopulationDaoImpl
		extends
		BaseInfoPopulationBaseDaoImpl<FloatingPopulation, SearchFloatingPopulationVo>
		implements FloatingPopulationDao {

	@Autowired
	private SyncPopulationSolrIndexForMQ syncPopulationSolrIndexForMQ;

	public List<Countrymen> findFloatingPopulationByCardNoAndNameAndOrgId(
			String orgInternalCode, String name, String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		if (name != null && name.length() > 0) {
			map.put("name", name);
		}
		if (idCardNo != null && idCardNo.length() > 0) {
			map.put("idCardNo", idCardNo);
		}
		return getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.findFloatingPopulationByCardNoAndNameAndOrgId",
						map);
	}

	@Override
	public FloatingPopulation findFloatingPopulationByCardNoAndOrgId(
			List<String> idCardNoList, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNoList", idCardNoList);
		map.put("orgId", orgId);
		return (FloatingPopulation) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.findFloatingPopulationByCardNoAndOrgId",
				map);
	}

	@Override
	public FloatingPopulation addFloatingPopulation(
			FloatingPopulation floatingPopulation) {
		checkIsNull(floatingPopulation);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"floatingPopulation.addFloatingPopulation", floatingPopulation);
		return getFloatingPopulationById(id);
	}

	@Override
	public PageInfo<FloatingPopulation> findFloatingPopulationsForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long logOut, Boolean isDeath) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("logOut", logOut);
		query.put("isDeath", isDeath);
		query.put("sortField", sortField);
		query.put("order", order);
		return findFloatingPopulationsForPageByMap(query, pageNum, pageSize);
	}

	@Override
	public PageInfo<FloatingPopulation> findFloatingPopulationsForPageByOrgInternalCodeDefaultList(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long logOut, Boolean isDeath) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("logOut", logOut);
		query.put("isDeath", isDeath);
		query.put("sortField", sortField);
		query.put("order", order);
		return findFloatingPopulationsForPageByMapDefaultList(query, pageNum,
				pageSize);
	}

	@Override
	public PageInfo<FloatingPopulation> searchFloatingPopulations(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchFloatingPopulationVo searchFloatingPopulationVo) {
		if (searchFloatingPopulationVo == null)
			return emptyFloatingPopulationPage(pageSize);
		PageInfo<FloatingPopulation> pageInfo = new PageInfo<FloatingPopulation>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countSearchFloatingPopulation",
				getConditionMap(searchFloatingPopulationVo, sortField, order));
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<FloatingPopulation> list = getSqlMapClientTemplate()
					.queryForList(
							"floatingPopulation.searchFloatingPopulation",
							getConditionMap(searchFloatingPopulationVo,
									sortField, order),
							(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<FloatingPopulation>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<FloatingPopulation> fastSearchFloatingPopulations(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchFloatingPopulationVo searchFloatingPopulationVo) {
		if (searchFloatingPopulationVo == null)
			return emptyFloatingPopulationPage(pageSize);
		PageInfo<FloatingPopulation> pageInfo = new PageInfo<FloatingPopulation>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countFastSearchFloatingPopulation",
				getConditionMap(searchFloatingPopulationVo, sortField, order));
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<FloatingPopulation> list = getSqlMapClientTemplate()
					.queryForList(
							"floatingPopulation.fastSearchFloatingPopulation",
							getConditionMap(searchFloatingPopulationVo,
									sortField, order),
							(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<FloatingPopulation>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	public List<FloatingPopulation> searchAllFloatingPopulations(String sidx,
			String sord, SearchFloatingPopulationVo searchFloatingPopulationVo) {
		Map<String, Object> map = getConditionMap(searchFloatingPopulationVo,
				sidx, sord);
		map.remove("isDeath");
		return getSqlMapClientTemplate().queryForList(
				"floatingPopulation.searchFloatingPopulation", map);
	}

	@Override
	public FloatingPopulation getFloatingPopulationById(Long id) {
		return (FloatingPopulation) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.getFloatingPopulationById", id);
	}

	@Override
	public List<FloatingPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", excludePopulationId);
		map.put("orgId", orgId);
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		return getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId",
						map);
	}

	@Override
	public List<FloatingPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", excludePopulationId);
		map.put("orgId", orgId);
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		return getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout",
						map);
	}

	@Override
	public FloatingPopulation updateFloatingPopulation(
			FloatingPopulation floatingPopulation) {
		checkIsNull(floatingPopulation);
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateFloatingPopulation",
				floatingPopulation);

		floatingPopulation = getFloatingPopulationById(floatingPopulation
				.getId());

		return floatingPopulation;
	}

	@Override
	public void deleteFloatingPopulationById(Long id) {
		getSqlMapClientTemplate().delete(
				"floatingPopulation.deleteFloatingPopulationById", id);
		syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(id,
				BaseInfoTables.FLOATINGPOPULATION_KEY, OperateMode.DELETE);
	}

	@Override
	public FloatingPopulation getFloatingPopulationByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("organizationId", organizationId);
		return (FloatingPopulation) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.getFloatingPopulationByIdCardNoAndOrgId",
				map);

	}

	private Map getConditionMap(SearchFloatingPopulationVo condition,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("fastSearchKeyWords", condition.getFastSearchKeyWords());
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("idCardNo", condition.getIdCardNo());
		map.put("name", condition.getName());
		map.put("inflowingReason", condition.getInflowingReason());
		map.put("usedName", condition.getUsedName());
		map.put("inflowingStartDate", condition.getInflowingStartDate());
		map.put("inflowingEndDate", condition.getInflowingEndDate());
		map.put("expectedStartDatedue", condition.getExpectedStartDatedue());
		map.put("expectedEndDatedue", condition.getExpectedEndDatedue());
		map.put("inflowingProvince", condition.getInflowingProvince());
		map.put("inflowingCity", condition.getInflowingCity());
		map.put("inflowingDistrict", condition.getInflowingDistrict());
		map.put("registrationType", condition.getRegistrationType());
		map.put("gender", condition.getGender());
		map.put("registerStartDate", condition.getRegisterStartDate());
		map.put("registerEndDate", condition.getRegisterEndDate());
		map.put("startBirthday", condition.getStartBirthday());
		map.put("endBirthday", condition.getEndBirthday());
		map.put("career", condition.getCareer());
		map.put("workUnit", condition.getWorkUnit());
		map.put("province", condition.getProvince());
		map.put("city", condition.getCity());
		map.put("district", condition.getDistrict());
		map.put("nativePlaceAddress", condition.getNativePlaceAddress());
		map.put("currentAddress", condition.getCurrentAddress());
		map.put("logOut", condition.getLogOut());
		map.put("isDeath", condition.getIsDeath());
		map.put("sortField", sortField);
		map.put("order", order);
		return map;
	}

	private PageInfo<FloatingPopulation> emptyFloatingPopulationPage(
			int pageSize) {
		PageInfo<FloatingPopulation> pageInfo = new PageInfo<FloatingPopulation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<FloatingPopulation>());
		return pageInfo;
	}

	private PageInfo<FloatingPopulation> findFloatingPopulationsForPageByMap(
			Map<String, Object> query, Integer pageNum, Integer pageSize) {
		Integer countFloatingPopulation = (Integer) getSqlMapClientTemplate()
				.queryForObject("floatingPopulation.countFloatingPopulations",
						query);
		pageNum = getPageNumByFloatingPopulations(pageNum, pageSize,
				countFloatingPopulation);
		List<FloatingPopulation> listFloatingPopulation = getSqlMapClientTemplate()
				.queryForList("floatingPopulation.findFloatingPopulations",
						query, (pageNum - 1) * pageSize, pageSize);
		return getPagInfoOfFloatingPopulation(pageNum, pageSize,
				countFloatingPopulation, listFloatingPopulation);
	}

	private PageInfo<FloatingPopulation> findFloatingPopulationsForPageByMapDefaultList(
			Map<String, Object> query, Integer pageNum, Integer pageSize) {
		Integer countFloatingPopulation = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"floatingPopulation.countFloatingPopulationsDefaultList",
						query);
		pageNum = getPageNumByFloatingPopulations(pageNum, pageSize,
				countFloatingPopulation);
		List<FloatingPopulation> listFloatingPopulation = getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.findFloatingPopulationsDefaultList",
						query, (pageNum - 1) * pageSize, pageSize);
		return getPagInfoOfFloatingPopulation(pageNum, pageSize,
				countFloatingPopulation, listFloatingPopulation);
	}

	private PageInfo<FloatingPopulation> getPagInfoOfFloatingPopulation(
			Integer pageNum, Integer pageSize, Integer countFloatingPopulation,
			List<FloatingPopulation> list) {
		PageInfo<FloatingPopulation> pageInfo = new PageInfo<FloatingPopulation>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countFloatingPopulation);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Integer getPageNumByFloatingPopulations(Integer pageNum,
			Integer pageSize, Integer countFloatingPopulation) {
		int pageCount = 0;
		if ((countFloatingPopulation % pageSize) == 0) {
			pageCount = countFloatingPopulation / pageSize;
		} else {
			pageCount = countFloatingPopulation / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return pageNum;
	}

	private void checkIsNull(FloatingPopulation floatingPopulation) {
		if (null == floatingPopulation.getIdCardNo()) {
			throw new BusinessValidationException("身份证号码不能为空!");
		}
		if (null == floatingPopulation.getName()) {
			throw new BusinessValidationException("姓名不能为空!");
		}
		if (null == floatingPopulation.getGender()
				|| null == floatingPopulation.getGender().getId()) {
			throw new BusinessValidationException("性别不能为空!");
		}
		if (null == floatingPopulation.getProvince()) {
			throw new BusinessValidationException("户籍地省不能为空!");
		}
		if (null == floatingPopulation.getCity()) {
			throw new BusinessValidationException("户籍地市不能为空!");
		}
		if (null == floatingPopulation.getDistrict()) {
			throw new BusinessValidationException("户籍地区县不能为空!");
		}
		if (null == floatingPopulation.getOrganization()
				|| null == floatingPopulation.getOrganization().getId()) {
			throw new BusinessValidationException("所属网格不能为空!");
		}
	}

	@Override
	public FloatingPopulation updateFloatingPopulationBusinessInfo(
			FloatingPopulation population) {
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateFloatingPopulationBusinessInfo",
				population);
		return getFloatingPopulationById(population.getId());
	}

	@Override
	public FloatingPopulation updateFloatingPopulationBaseInfo(
			FloatingPopulation population) {
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateFloatingPopulationBaseInfo",
				population);

		population = getFloatingPopulationById(population.getId());
		return population;
	}

	private PageInfo<FloatingPopulation> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<FloatingPopulation> list) {
		PageInfo<FloatingPopulation> pageInfo = new PageInfo<FloatingPopulation>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<FloatingPopulation> findGisHouseHoldStaffById(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"floatingPopulation.findGisHouseHoldStaffById", id);
	}

	@Override
	public PageInfo<FloatingPopulation> getFurtherStepGisPopulationInfoByPersonType(
			String orgInternalCode, String personType, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(orgInternalCode, "orgInternalCode");
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("populationType", personType);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countFurtherStepGisByPopulationType", map);
		List<FloatingPopulation> list = getSqlMapClientTemplate().queryForList(
				"floatingPopulation.findFurtherStepGisByPopulationType", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public PageInfo<FloatingPopulation> findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
			String orgInternalCode, List personType, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(orgInternalCode, "orgInternalCode");
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("populationType", personType);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countFurtherStepGisByPopulationTypeList",
				map);
		List<FloatingPopulation> list = getSqlMapClientTemplate().queryForList(
				"floatingPopulation.findFurtherStepGisByPopulationTypeList",
				map, (page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public void updateActualPopulationToHasHouseState(Long populationId,
			String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", true);
		map.put("noHouseReason", "");
		map.put("currentAddress", address);
		getSqlMapClientTemplate()
				.update("floatingPopulation.updateActualPopulationToHasHouseState",
						map);
	}

	@Override
	public Boolean updateActualPopulationToHasHouseState(
			FloatingPopulation floatingPopulation) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", floatingPopulation.getId());
		map.put("isHaveHouse", floatingPopulation.getIsHaveHouse());
		map.put("noHouseReason", floatingPopulation.getNoHouseReason());
		return getSqlMapClientTemplate()
				.update("floatingPopulation.updateActualPopulationToHasHouseState",
						map) > 0;
	}

	@Override
	public PageInfo<FloatingPopulation> findFloatingPopulationByorgCodeForGis(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countFloatingPopulations", map);
		List<FloatingPopulation> list = getSqlMapClientTemplate().queryForList(
				"floatingPopulation.findFloatingPopulationByorgCodeForGis",
				map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<FloatingPopulation> findAllBindingFloatingPopulationByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("personType", catalog.getParentCatalog());
		map.put("populationType", catalog.getCatalog());
		map.put("defaultLivingHouse", Boolean.TRUE);
		return getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.findAllBindingFloatingPopulationByorgCodeForGis",
						map);
	}

	@Override
	public List<FloatingPopulation> findFloatingPopulationsWhenIsOldForMark(
			int pageSize, Organization org) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endRow", pageSize);
		map.put("currentDate", CustomDateUtil.dateBeforeNowDate(0));
		map.put("whenOldDate", CustomDateUtil.dateBeforeNowDate(60));
		map.put("shardCode", org.getDepartmentNo());
		map.put("orgCode", org.getOrgInternalCode());
		return (List<FloatingPopulation>) getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.findFloatingPopulationsWhenIsOldForMark",
						map);
	}

	@Override
	public Integer countFloatingPopulationsWhenIsOldForMark() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentDate", CustomDateUtil.dateBeforeNowDate(0));
		map.put("whenOldDate", CustomDateUtil.dateBeforeNowDate(60));
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countFloatingPopulationsWhenIsOldForMark",
				map);
	}

	@Override
	public List<FloatingPopulation> findFloatingPopulationsWhenIsNurtuesWomenForMark(
			int pageSize, Organization org) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endRow", pageSize);
		map.put("minDate", CustomDateUtil.dateBeforeNowDate(15));
		map.put("maxDate", CustomDateUtil.dateBeforeNowDate(49));
		map.put("shardCode", org.getDepartmentNo());
		map.put("orgCode", org.getOrgInternalCode());
		return (List<FloatingPopulation>) getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.findFloatingPopulationsWhenIsNurtuesWomenForMark",
						map);
	}

	@Override
	public Integer countFloatingPopulationsWhenIsNurtuesWomenForMark(
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("minDate", CustomDateUtil.dateBeforeNowDate(15));
		map.put("maxDate", CustomDateUtil.dateBeforeNowDate(49));
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"floatingPopulation.countFloatingPopulationsWhenIsNurtuesWomenForMark",
						map);
	}

	@Override
	public void updateDeathStateById(Long id, Boolean death) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("death", death);
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateDeathStateById", map);
	}

	@Override
	public void updateActualPopulationToHasHouseState(Long populationId,
			HouseInfo houseInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", true);
		map.put("noHouseReason", "");
		map.put("currentAddress", houseInfo.getAddress());
		map.put("community", houseInfo.getCommunity());
		map.put("block", houseInfo.getBlock());
		map.put("unit", houseInfo.getUnit());
		map.put("room", houseInfo.getRoom());
		// map.put("addressType", houseInfo.getAddressType().getId());

		getSqlMapClientTemplate()
				.update("floatingPopulation.updateActualPopulationToHasHouseState",
						map);

	}

	@Override
	public void updateFloatingPopulationByDeleteHousePopulationRela(
			Long populationId, Boolean isHaveHouse, String noHouseReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", isHaveHouse);
		map.put("noHouseReason", noHouseReason);
		getSqlMapClientTemplate()
				.update("floatingPopulation.updateActualPopulationToHasHouseState",
						map);

	}

	@Override
	public FloatingPopulation getFloatingPopulationByIdAndBusinessType(Long id,
			String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", id);
		map.put("populationType", type);
		map.put("actualType", BaseInfoTables.FLOATINGPOPULATION_KEY);
		return (FloatingPopulation) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.getFloatingPopulationByIdAndBusinessType",
				map);
	}

	@Override
	public void updateLogOutPopulationById(LogoutDetail logoutDetail, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("logOut", logoutDetail.getLogout());
		map.put("logoutDetail", logoutDetail);
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateLogOutPopulationById", map);
	}

	@Override
	public void deleteFloatingPopulation(Long id) {
		getSqlMapClientTemplate().delete(
				"floatingPopulation.deleteFloatingPopulationById", id);
		syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(id,
				BaseInfoTables.FLOATINGPOPULATION_KEY, OperateMode.DELETE);
	}

	@Override
	public void updateGroupFamily(Map map) {
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyForMove", map);
	}

	@Override
	public FloatingPopulation getFloatingPopulationByBaseInfoId(
			Long baseInfoId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseInfoId", baseInfoId);
		map.put("orgId", orgId);
		return (FloatingPopulation) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.getFloatingPopulationByBaseInfoIdAndOrgId",
				map);
	}

	@Override
	public List<FloatingPopulation> getFloatingPopulationByBaseInfoId(
			Long baseInfoId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseInfoId", baseInfoId);
		return (List<FloatingPopulation>) getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.getFloatingPopulationByBaseInfoId",
						map);
	}

	@Override
	public Integer getCount(
			SearchFloatingPopulationVo searchFloatingPopulationVo) {
		if (searchFloatingPopulationVo == null) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countSearchFloatingPopulation",
				getConditionMap(searchFloatingPopulationVo, null, null));
	}

	@Override
	public void updateBaseHouseInfoAndRemark(FloatingPopulation population) {
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateBaseHouseInfoAndRemark", population);
	}

	@Override
	public List<FloatingPopulation> findFloatingPopulationByExpectedDatedue(
			int pageNum, int pageSize, Long remindTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", pageNum * pageSize);
		map.put("endRow", (pageNum + 1) * pageSize);
		map.put("remindTime", remindTime);
		return (List<FloatingPopulation>) getSqlMapClientTemplate()
				.queryForList(
						"floatingPopulation.findFloatingPopulationByExpectedDatedue",
						map);
	}

	@Override
	public Integer countFloatingPopulationByExpectedDatedue(Long remindTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("remindTime", remindTime);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"floatingPopulation.countFloatingPopulationByExpectedDatedue",
				map);
	}

	@Override
	public FloatingPopulation updateIsRemindByid(Long id) {
		Map map = new HashMap();
		map.put("id", id);
		getSqlMapClientTemplate().update(
				"floatingPopulation.updateIsRemindByid", map);
		return get(id);

	}

	@Override
	public void updateBirthdayAndGender(Long baseInfoId, Date birthday,
			Long genderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseInfoId", baseInfoId);
		map.put("birthday", birthday);
		map.put("genderId", genderId);

		getSqlMapClientTemplate().update(
				"floatingPopulation.updateBirthdayAndGender", map);

	}

	@Override
	public List<FloatingPopulation> findFloatingPopulationByIds(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		return getSqlMapClientTemplate().queryForList(
				"floatingPopulation.findFloatingPopulationByIds", map);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("floatingPopulations", id);
	}

	@Override
	public List<Organization> findOrgByAddress(Long addressId) {
		return getSqlMapClientTemplate().queryForList(
				"floatingPopulation.findOrgByAddress", addressId);
	}
}
