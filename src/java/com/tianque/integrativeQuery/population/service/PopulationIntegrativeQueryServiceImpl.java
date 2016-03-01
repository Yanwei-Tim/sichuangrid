package com.tianque.integrativeQuery.population.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.integrativeQuery.population.dao.PopulationIntegrativeQueryDao;
import com.tianque.integrativeQuery.population.domain.PopulationBaseQueryVo;
import com.tianque.integrativeQuery.population.domain.PopulationQueryVo;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.PermissionService;

@Service("populationIntegrativeQueryServie")
public class PopulationIntegrativeQueryServiceImpl implements
		PopulationIntegrativeQueryServie {
	@Autowired
	private PopulationIntegrativeQueryDao populationIntegrativeQueryDao;
	@Autowired
	private PermissionService permissionService;

	@Override
	public PageInfo<Countrymen> searchPopulationByIntegrativeCondition(
			PopulationQueryVo populationQueryVo, String actualPersonType,
			String[] attentionPopulationTypes, PropertyDict gender,
			Date birthdayStrart, Date birthdayEnd, Boolean hasHouse,
			String orgCode, Integer pageNum, Integer pageSize, String sidx,
			String sord) {

		return populationIntegrativeQueryDao
				.searchPopulationByIntegrativeCondition(populationQueryVo,
						getAcutalPopulation(actualPersonType),
						attentionPopulationTypes, getActualTypesByPermission(),
						gender, birthdayStrart, birthdayEnd, hasHouse, orgCode,
						pageNum, pageSize, sidx, sord);
	}

	/**
	 * 根据权限判断用户有几张实口表可查 如果是admin的话，三张表都可查
	 * 
	 * @return
	 */
	private String[] getActualTypesByPermission() {
		StringBuilder actualPersonTypes = new StringBuilder();
		Session session = ThreadVariable.getSession();
		if (session != null && session.getUserId() != null) {

			User user = this.permissionService.getSimpleUserById(session
					.getUserId());
			if (user.isAdmin()) {
				return actualPersonTypes.append(PopulationType.HOUSEHOLD_STAFF)
						.append(",").append(PopulationType.FLOATING_POPULATION)
						.append(",")
						.append(PopulationType.UNSETTLED_POPULATION)
						.append(",").toString().split(",");
			}

			List<String> enameList = permissionService
					.findPermissionsEnameByUserId(session.getUserId());

			for (String str : enameList) {
				if (str.equals("householdStaffIntegrativeQuery")) {
					actualPersonTypes.append(PopulationType.HOUSEHOLD_STAFF)
							.append(",");
					continue;
				}
				if (str.equals("floatingPopulationIntegrativeQuery")) {
					actualPersonTypes
							.append(PopulationType.FLOATING_POPULATION).append(
									",");
					continue;
				}
				if (str.equals("unsettledPopulationIntegrativeQuery")) {
					actualPersonTypes.append(
							PopulationType.UNSETTLED_POPULATION).append(",");
					continue;
				}
			}
		}
		return actualPersonTypes.toString().split(",");

	}

	/**
	 * 获取实口的类型
	 * 
	 * @param actualPersonType
	 * @return
	 */
	private String getAcutalPopulation(String actualPersonType) {
		// actualPersonType 为null说明实口选择的结果为不限，则结果一定在实口类之中
		if (actualPersonType == null
				|| actualPersonType.isEmpty()
				|| PopulationQueryVo.ALL_ACTUAL_POPULATION
						.equals(actualPersonType)) {

			return actualPersonType = PopulationCatalog.ALL_ACTUAL_POPULATION;
		}
		return actualPersonType;
	}

	@Override
	public List<Countrymen> searchPopulationByIntegrativeConditionForExport(
			PopulationQueryVo populationQueryVo, String actualPersonType,
			String[] attentionPopulationTypes, PropertyDict gender,
			Date birthdayStrart, Date birthdayEnd, Boolean hasHouse,
			String orgCode, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		return populationIntegrativeQueryDao
				.searchPopulationByIntegrativeConditionForExport(
						populationQueryVo,
						getAcutalPopulation(actualPersonType),
						attentionPopulationTypes, getActualTypesByPermission(),
						gender, birthdayStrart, birthdayEnd, hasHouse, orgCode,
						pageNum, pageSize, sidx, sord);
	}

	@Override
	public String[][] getExportPopertyArray() {
		return SpecialGroupsContext
				.getPopulationIntegrativeQueryPropertyArray();
	}

	@Override
	public PageInfo queryPopulationForWorkBench(String searchText,
			String orgCode, Integer page, Integer rows, String sidx, String sord) {

		return populationIntegrativeQueryDao.queryPopulationForWorkBench(
				searchText, orgCode, page, rows, sidx, sord);
	}

	@Override
	public PageInfo<Countrymen> findPopulationsByBaseQueryVoAndTypes(
			PopulationBaseQueryVo populationBaseQueryVo,
			String[] populationTypes, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (null == populationTypes || populationTypes.length <= 0) {
			throw new BusinessValidationException("请指定要查询的人员的类型");
		}
		List<Map<String, String>> typeTableMapList = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		for (String str : populationTypes) {
			map = new HashMap<String, String>();
			map.put("type", str);
			map.put("table", PopulationCatalog.parse(str).getTableName());
			typeTableMapList.add(map);
		}
		return populationIntegrativeQueryDao
				.findPopulationsByBaseQueryVoAndTypes(populationBaseQueryVo,
						typeTableMapList, pageNum, pageSize, sidx, sord);

	}

}
