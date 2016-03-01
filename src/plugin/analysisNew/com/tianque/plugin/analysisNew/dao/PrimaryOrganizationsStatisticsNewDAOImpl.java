package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.analysisNew.domain.PopulationStatType;
import com.tianque.plugin.analysisNew.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;

@Repository("primaryOrganizationsStatisticsNewDao")
public class PrimaryOrganizationsStatisticsNewDAOImpl extends AbstractBaseDao
		implements PrimaryOrganizationsStatisticsNewDao {

	@Override
	public List<Map<String, Object>> getStatisticList(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"primaryOrganizationsStatisticsNew.getStatisticList",
				statisticSearchVo);
	}

	@Override
	public List<Map<String, Object>> getNewSocietyStatisticList(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"primaryOrganizationsStatisticsNew.getNewSocietyStatisticList",
				statisticSearchVo);
	}

	@Override
	public List<SearchPrimaryOrganizationDataColumnVo> findPrimaryOrganizationDataColumnByDate(
			int year, int month, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgId", orgId);
		List<SearchPrimaryOrganizationDataColumnVo> columnVoList = getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizationsStatisticsNew.findPrimaryOrganizationDataColumnByDate",
						map);
		return columnVoList;
	}

	@Override
	public Integer countPrimaryOrgByMap(String typeId, String timeStr,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeId", typeId);
		// 获取年月
		String time[] = timeStr.split("-");
		map.put("year", time[0]);
		map.put("month", time[1].startsWith("0") ? time[1].substring(1)
				: time[1]);
		map.put("orgId", orgId);
		Integer sum = (Integer) (getSqlMapClientTemplate()
				.queryForObject(
						"primaryOrganizationsStatisticsNew.getPrimaryOrgCountByMonth",
						map));
		return sum == null ? 0 : sum;
	}

	@Override
	public List<Map<String, Object>> getPrimaryOrganizationsListFromHistory(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate()
				.queryForList(
						"primaryOrganizationsStatisticsNew.getPrimaryOrganizationsListFromHistory",
						statisticSearchVo);
	}

	@Override
	public void deletePrimaryStatType(int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete(
				"primaryOrganizationsStatisticsNew.deletePrimaryStatType", map);
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
				"primaryOrganizationsStatisticsNew.addPrimaryOrganizationsStat",
				map);// 基本组织表
		tableName = BaseInfoTables.NEWSOCIETYORGANIZATIONS;
		map.put("tableName", tableName);
		getSqlMapClientTemplate()
				.insert("primaryOrganizationsStatisticsNew.addNewSocietyOrganizationsStat",
						map);// 社会组织表
		tableName = BaseInfoTables.NEWECONOMICORGANIZATIONS;
		map.put("tableName", tableName);
		getSqlMapClientTemplate()
				.insert("primaryOrganizationsStatisticsNew.addNewSocietyOrganizationsStat",
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
				"primaryOrganizationsStatisticsNew.getPrimaryOrganizarionMember",
				map);
	}

}
