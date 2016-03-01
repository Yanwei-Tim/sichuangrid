package com.tianque.plugin.analysisNew.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analysisNew.dao.BuilddataStatNewDao;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.plugin.analysisNew.util.PacketStatisticsTables;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.vo.PacketStatisticsVo;

/**
 * @author hxpwangyi@163.com
 * @date 2013-3-26
 */
@Component("builddataStatNewService")
@Transactional
public class BuilddataStatNewServiceImpl implements BuilddataStatNewService {
	private final static Logger logger = LoggerFactory
			.getLogger(BuilddataStatNewServiceImpl.class);
	@Autowired
	private BuilddataStatNewDao builddataStatNewDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PacketStatisticsService packetStatisticsService;

	@Override
	public void addBuilddataStat(int year, int month) {
		Organization org = organizationDubboService.getRootOrganization();
		String orgCode = org.getOrgInternalCode();
		addHistoryStatisticInfo(year, month,
				PopulationCatalog.parse(BaseInfoTables.BUILDDATA_KEY)
						.getCatalog(), orgCode);
		packetStatisticsService.createPacketStatisticsByTypeAndTime(
				PacketStatisticsTables.STATISTICHISTORY_DISPLAYNAME,
				PopulationCatalog.parse(BaseInfoTables.BUILDDATA_KEY)
						.getCatalog(), year, month);
	}

	@Override
	public void manualBuilddataCompletion(PacketStatisticsVo packetStatisticsVo) {
		if (packetStatisticsVo == null
				|| !StringUtil.isStringAvaliable(packetStatisticsVo
						.getTypeName())
				|| packetStatisticsVo.getStartYear() == null
				|| packetStatisticsVo.getStartMonth() == null
				|| packetStatisticsVo.getEndMonth() == null
				|| packetStatisticsVo.getEndYear() == null) {
			throw new BusinessValidationException("参数错误");
		}
		for (int year = packetStatisticsVo.getStartYear(); year <= (Integer) packetStatisticsVo
				.getEndYear(); year++) {
			int startMonth = 1;
			int endMonth = 12;
			if (year == packetStatisticsVo.getStartYear()) {
				startMonth = packetStatisticsVo.getStartMonth();
			}
			if (year == packetStatisticsVo.getEndYear()) {
				endMonth = (Integer) packetStatisticsVo.getEndMonth();
			}
			for (int month = startMonth; month <= endMonth; month++) {

				logger.error("执行" + packetStatisticsVo.getTypeName() + "_"
						+ year + "_" + month + "表分组统计开始。");
				addBuilddataStat(year, month);
				logger.error("执行" + packetStatisticsVo.getTypeName() + "_"
						+ year + "_" + month + "表分组统计开始。");
			}
		}
	}

	private void createHistoryStatistic(int year, int month, String type,
			StatisticSearchVo statisticSearchVo) {
		statisticSearchVo.setYear(year);
		statisticSearchVo.setMonth(month);
		statisticSearchVo.setEndDate(CalendarUtil
				.getNextMonthStart(year, month));
		statisticSearchVo.setStartDate(CalendarUtil.getMonthStart(year, month));
		statisticSearchVo.setType(type);
		statisticSearchVo.setTableDisplayName("楼宇");
		builddataStatNewDao.deleteBuilddataStatType(
				statisticSearchVo.getOrginternalcode(), year, month, type);
		fillOrgLevelAndOrgType(statisticSearchVo);
		builddataStatNewDao.addHistoryStatistic(statisticSearchVo);
		// Map<String, Object> map = new HashMap<String, Object>();
		// map = builddataStatDao.getMonthCreateNumAndTotal(statisticSearchVo);
		// builddataStatDao
		// .updateMonthCreateAttentionNumAndTotal(buildMapByMapAndStatisticSearchVo(
		// statisticSearchVo, map));

	}

	private void fillOrgLevelAndOrgType(StatisticSearchVo statisticSearchVo) {
		PropertyDict gridOrgLevel = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.GRID).get(0);

		PropertyDict adminOrgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		statisticSearchVo.setOrgType(adminOrgType.getId());
		statisticSearchVo.setOrgLevel(gridOrgLevel.getId());
	}

	private Map<String, Object> buildMapByMapAndStatisticSearchVo(
			StatisticSearchVo statisticSearchVo, Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return new HashMap<String, Object>();
		}
		map.put("year", statisticSearchVo.getYear());
		map.put("month", statisticSearchVo.getMonth());
		map.put("orgInternalcode", statisticSearchVo.getOrginternalcode());
		map.put("type", statisticSearchVo.getType());
		return map;
	}

	private void outofDate(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		if (c.after(Calendar.getInstance())) {
			throw new BusinessValidationException("所查月份有误，请重新选择要生成报表的月份！");
		}

	}

	public void addHistoryStatisticInfo(int year, int month, String type,
			String orgInternalCode) {
		outofDate(year, month);

		if (type != null && !type.trim().equals("")) {
			StatisticSearchVo statisticSearchVo = createStatisticSearchVo(type,
					0);

			statisticSearchVo.setOrginternalcode(orgInternalCode);
			createHistoryStatistic(year, month, type, statisticSearchVo);
		}

	}

	private StatisticSearchVo createStatisticSearchVo(String type, long orgId) {
		PopulationCatalog populationCatalog = PopulationCatalog.parse(type);
		StatisticSearchVo statisticSearchVo = new StatisticSearchVo();
		statisticSearchVo.setType(type);
		statisticSearchVo.setDomainName(populationCatalog
				.getStatisticListSetting().getDomainName());

		statisticSearchVo.setTable(populationCatalog.getStatisticListSetting()
				.getTableName());
		statisticSearchVo.setTableDisplayName(PopulationType
				.getCnameByPopulationType(type));
		statisticSearchVo.setPropertyField(populationCatalog
				.getStatisticListSetting().getPropertyField());
		statisticSearchVo.setCountFieldMap(populationCatalog
				.getStatisticListSetting().getCountMap());
		statisticSearchVo.setOrgId(orgId);

		statisticSearchVo.setIsemphasis(null);

		return statisticSearchVo;
	}
}
