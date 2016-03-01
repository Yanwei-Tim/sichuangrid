package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.analyzing.domain.PopulationStatType;
import com.tianque.plugin.analyzing.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;

@Repository("primaryOrganizationsStatisticsDao")
public class PrimaryOrganizationsStatisticsDAOImpl extends AbstractBaseDao
		implements PrimaryOrganizationsStatisticsDao {

	@Override
	public List<Map<String, Object>> getStatisticList(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"primaryOrganizationsStatistics.getStatisticList",
				statisticSearchVo);
	}

	@Override
	public List<Map<String, Object>> getNewSocietyStatisticList(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"primaryOrganizationsStatistics.getNewSocietyStatisticList",
				statisticSearchVo);
	}

	@Override
	public List<SearchPrimaryOrganizationDataColumnVo> findPrimaryOrganizationDataColumnByDate(
			int year, int month, String ogrinternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("ogrinternalCode", ogrinternalCode);
		List<SearchPrimaryOrganizationDataColumnVo> columnVoList = getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizationsStatistics.findPrimaryOrganizationDataColumnByDate",
						map);
		return columnVoList;
	}

	@Override
	public List<SearchPrimaryOrganizationDataColumnVo> findPrimaryOrganizationDataColumnByDateTwo(
			int year, int month, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgId", orgId);
		List<SearchPrimaryOrganizationDataColumnVo> columnVoList = getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizationsStatistics.findPrimaryOrganizationDataColumnByDateTwo",
						map);
		return columnVoList;
	}

	@Override
	public List<SearchPrimaryOrganizationDataColumnVo> findPrimaryOrganizationDataColumnByOgrinternalCode(
			int year, int month, String ogrinternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("ogrinternalCode", ogrinternalCode);
		List<SearchPrimaryOrganizationDataColumnVo> columnVoList = getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizationsStatistics.findPrimaryOrganizationDataColumnByOgrinternalCode",
						map);
		return columnVoList;
	}

	@Override
	public Integer countPrimaryOrgByMap(String typeId, String timeStr,
			String orgInternalCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("typeId", typeId);
		// 获取年月
		String time[] = timeStr.split("-");
		map.put("year", time[0]);
		map.put("month", time[1].startsWith("0") ? time[1].substring(1)
				: time[1]);
		map.put("orgInternalCode", orgInternalCode);
		Integer sum = (Integer) (getSqlMapClientTemplate()
				.queryForObject(
						"primaryOrganizationsStatistics.getPrimaryOrgCountByMonth",
						map));
		return sum == null ? 0 : sum;
	}

	@Override
	public List<Map<String, Object>> getPrimaryOrganizationsListFromHistory(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizationsStatistics.getPrimaryOrganizationsListFromHistory",
						statisticSearchVo);
	}

	@Override
	public void deletePrimaryStatType(int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete(
				"primaryOrganizationsStatistics.deletePrimaryStatType", map);
	}

	@Override
	public void addPrimaryOrganizationsStat(PopulationStatType populationType,
			String tableName, Date createDate, Date nextMonthStart,
			String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("beginDate", createDate);
		map.put("nextMonthStart", nextMonthStart);
		map.put("populationStatType", populationType);
		map.put("orginternalcode", orgCode);
		getSqlMapClientTemplate().insert(
				"primaryOrganizationsStatistics.addPrimaryOrganizationsStat",
				map);// 基本组织表
		tableName = BaseInfoTables.NEWSOCIETYORGANIZATIONS;
		map.put("tableName", tableName);
		getSqlMapClientTemplate()
				.insert("primaryOrganizationsStatistics.addNewSocietyOrganizationsStat",
						map);// 社会组织表
		tableName = BaseInfoTables.NEWECONOMICORGANIZATIONS;
		map.put("tableName", tableName);
		getSqlMapClientTemplate()
				.insert("primaryOrganizationsStatistics.addNewSocietyOrganizationsStat",
						map);// 非公有制经济组织表

	}

	@Override
	public List<SearchPrimaryOrganizationDataColumnVo> getPrimaryOrganizarionMember(
			int teamClass, Long orgId, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamClass", teamClass);
		map.put("orgId", orgId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"primaryOrganizationsStatistics.getPrimaryOrganizarionMember",
				map);
	}

}
