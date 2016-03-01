package com.tianque.plugin.analysisNew.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analysisNew.dao.PrimaryOrganizationsStatisticsNewDao;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.domain.PopulationStatType;
import com.tianque.plugin.analysisNew.domain.PrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analysisNew.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analysisNew.util.PacketStatisticsHelper;
import com.tianque.plugin.analysisNew.util.PacketStatisticsTables;
import com.tianque.plugin.analysisNew.util.PopulationCatalog;
import com.tianque.plugin.analysisNew.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.tableManage.service.TableManageService;

@Service("primaryOrganizationsStatisticsNewService")
@Transactional
public class PrimaryOrganizationsStatisticsNewServiceImpl extends
		AbstractBaseService implements PrimaryOrganizationsStatisticsNewService {
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PrimaryOrganizationsStatisticsNewDao primaryOrganizationsStatisticsNewDao;
	@Autowired
	private PropertyDomainService propertyDomainService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PacketStatisticsService packetStatisticsService;
	@Autowired
	private DepartmentPartyNewService departmentPartyNewService;
	@Autowired
	private CacheService cacheService;

	/***
	 * 区域分布图
	 */
	@Override
	public HighchartColumnVo getPrimaryOrgnizationsStisticsArealDistributionList(
			String type, Long orgId, int year, int month) {
		StatisticSearchVo statisticSearchVo = null;
		try {
			statisticSearchVo = createStatisticSearchVo(type, orgId, "");
			statisticSearchVo.setOrgId(orgId);
			statisticSearchVo.setStartDate(CalendarUtil.getMonthStart(year,
					month));
			statisticSearchVo.setEndDate(CalendarUtil.getNextMonthStart(year,
					month));
			return getArealDistributionList(statisticSearchVo, type);
		} catch (Exception e) {
			throw new ServiceValidationException("区域分布图执行失败，原因:", "操作失败，请重试", e);
		}
	}

	private StatisticSearchVo createStatisticSearchVo(String type, long orgId,
			String domainName) {
		PopulationCatalog populationCatalog = PopulationCatalog.parse(type);
		if (populationCatalog == null) {
			throw new BusinessValidationException("查询条件封装失败");
		}
		StatisticSearchVo statisticSearchVo = new StatisticSearchVo();
		statisticSearchVo.setType(type);
		statisticSearchVo.setTableDisplayName(PopulationType
				.getCnameByPopulationType(type));
		if (null != populationCatalog.getStatisticListSetting()) {
			if ("".equals(domainName) || null == domainName) {
				statisticSearchVo.setDomainName(populationCatalog
						.getStatisticListSetting().getDomainName());
			} else {
				statisticSearchVo.setDomainName(domainName);
			}
			statisticSearchVo.setTable(populationCatalog
					.getStatisticListSetting().getTableName());
			statisticSearchVo.setPropertyField(populationCatalog
					.getStatisticListSetting().getPropertyField());
			statisticSearchVo.setCountFieldMap(populationCatalog
					.getStatisticListSetting().getCountMap());
		}
		// statisticSearchVo.setIsemphasis(null);
		statisticSearchVo.setOrgId(orgId);
		return statisticSearchVo;
	}

	/**
	 * 区域图数据
	 */
	private HighchartColumnVo getArealDistributionList(
			StatisticSearchVo statisticSearchVo, String type) {

		List<Map<String, Object>> list = primaryOrganizationsStatisticsNewDao
				.getStatisticList(statisticSearchVo);
		// 判断是否要创建表
		if (PopulationType.DEPARTMENTPARTY.equals(type)) {
			list = removeList(list);
		}
		statisticSearchVo.setTable(BaseInfoTables.NEWSOCIETYORGANIZATIONS);// 新经济组织

		statisticSearchVo.setPropertyField(propertyDictService
				.getPropertyDictByDomainName(
						BaseInfoTables.NEWSOCIETYORGANIZATION_DISPLAYNAME)
				.getId().toString());
		statisticSearchVo.setOrgType(getOrgType(statisticSearchVo));
		statisticSearchVo
				.setPropertyDomainId(getPropertyDomainId(statisticSearchVo));
		List<Map<String, Object>> newSocietylist = primaryOrganizationsStatisticsNewDao
				.getNewSocietyStatisticList(statisticSearchVo);

		statisticSearchVo.setTable(BaseInfoTables.NEWECONOMICORGANIZATIONS);// 非公有制经济组织

		statisticSearchVo.setPropertyField(propertyDictService
				.getPropertyDictByDomainName(
						BaseInfoTables.NEWECONOMICORGANIZATION_DISPLAYNAME)
				.getId().toString());

		List<Map<String, Object>> newEconomiclist = primaryOrganizationsStatisticsNewDao
				.getNewSocietyStatisticList(statisticSearchVo);
		// 将三个list的数据整合
		dataIntegration(list, newSocietylist, newEconomiclist);
		return createHighchartColumnVoByCurrent(list, statisticSearchVo);

	}

	private HighchartColumnVo createHighchartColumnVoByCurrent(
			List<Map<String, Object>> list, StatisticSearchVo statisticSearchVo) {
		HighchartColumnVo chartColumn = new HighchartColumnVo();
		// 组装数据
		chartColumn = createHighchartColumnVoByList(list, statisticSearchVo,
				chartColumn);
		return chartColumn;
	}

	private HighchartColumnVo createHighchartColumnVoByList(
			List<Map<String, Object>> list,
			StatisticSearchVo statisticSearchVo, HighchartColumnVo chartColumn) {
		chartColumn.setModuleName(statisticSearchVo.getTableDisplayName());
		if (list == null || list.size() == 0) {
			return new HighchartColumnVo();
		}

		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainName(statisticSearchVo.getDomainName());
		List<PropertyDict> movepropertyDictList = new ArrayList<PropertyDict>();
		if (propertyDictList != null && propertyDictList.size() != 0) {
			for (PropertyDict propertyDict : propertyDictList) {
				if (vaidateTypeNotNeed(propertyDict.getDisplayName())) {
					movepropertyDictList.add(propertyDict);
				}
				if (BaseInfoTables.DEPARTMENTOFPARTY_DISPLAYNAME
						.equals(propertyDict.getDisplayName())) {
					propertyDict
							.setDisplayName(BaseInfoTables.PARTYCOMMITTEE_DISPLAYNAME);
				}
			}
			propertyDictList.removeAll(movepropertyDictList);
		}
		int DictSize = propertyDictList.size();// 属性的条目
		int size = list.size() / DictSize;// 地区条目

		List<List<Integer>> datas = new ArrayList<List<Integer>>();// 存放各类型对应的数量
		for (int i = 0; i < DictSize; i++) {
			datas.add(new ArrayList<Integer>());
		}
		List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>(); // 各类型对应的数据
		HighchartDataColumnVo column;

		List<String> categoriesList = new ArrayList<String>();
		int len = list.size();

		for (int i = 0; i < len; i++) {
			// 从查询结果中取一条记录
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = Integer.parseInt(((BigDecimal) list.get(i).get(
					"SUMNUM")).toString());
			if (i % DictSize == 0) {
				categoriesList.add(orgName);
				datas.get(i % DictSize).add(sum);

			} else {
				datas.get(i % DictSize).add(sum);
			}
		}

		String[] categories = new String[categoriesList.size()];
		categoriesList.toArray(categories);
		chartColumn.setCategories(categories);
		Integer[] data = new Integer[size];
		for (int i = 0; i < DictSize; i++) {

			column = new HighchartDataColumnVo();

			column.setStack(statisticSearchVo.getDomainName());
			datas.get(i).toArray(data);
			column.setData(getIntByInteger(data));
			if (null != chartColumn.getSeries()) {
				String[] propertyDictLists = AnalyseUtilNew.groupMap
						.get(statisticSearchVo.getTableDisplayName());
				column.setName(propertyDictLists[i]);
				column.setStack(propertyDictLists[propertyDictLists.length - 1]);
				chartColumn.getSeries().add(column);
			} else {
				column.setName(propertyDictList.get(i).getDisplayName());
				series.add(column);
			}

		}
		if (null == chartColumn.getSeries()) {
			chartColumn.setSeries(series);
		}
		return chartColumn;
	}

	// 根据时间加载历史数据
	@Override
	public HighchartColumnVo getArealDistributionListFromHistory(Long orgId,
			String type, int year, int month) {
		String key = null;
		if (PopulationType.DEPARTMENTPARTY.equals(type)) {
			key = MemCacheConstant.getCachKeyForSixData(
					MemCacheConstant.DEPARTEMENT_CACHKEY, type, year, month,
					orgId, ModulTypes.STATISTICSCOLUMN);
		} else {
			key = MemCacheConstant.getCachKeyForSixData(
					MemCacheConstant.PRIMARYORGANIZATIONS_CACHKEY, type, year,
					month, orgId, ModulTypes.STATISTICSCOLUMN);
		}

		HighchartColumnVo highchartColumnVo = null;
		if (key != null) {
			if (PopulationType.DEPARTMENTPARTY.equals(type)) {
				highchartColumnVo = (HighchartColumnVo) cacheService.get(
						MemCacheConstant.ANALYSE_DEPARTEMENT_NAMESPACE, key);
			} else {
				highchartColumnVo = (HighchartColumnVo) cacheService.get(
						MemCacheConstant.ANALYSE_ORGANIZATION_NAMESPACE, key);
			}
			if (highchartColumnVo != null) {
				return highchartColumnVo;
			}
		}
		try {
			// 判断是否要创建表
			if (PopulationType.DEPARTMENTPARTY.equals(type)) {
				tableService.createAnalyseTable(
						AnalyseUtilNew.DEPARTMENTPARTYNAME,
						AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
			} else {
				tableService.createAnalyseTable(
						AnalyseUtilNew.PRIMARYORGANIZATIONSENAME,
						AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
			}
			StatisticSearchVo statisticSearchVo = null;
			statisticSearchVo = createStatisticSearchVo(type, orgId, "");
			statisticSearchVo.setYear(year);
			statisticSearchVo.setMonth(month);
			if (null == statisticSearchVo.getTableDisplayName()) {
				PopulationCatalog catalog = PopulationCatalog.parse(type);
				statisticSearchVo.setTableDisplayName(catalog.getDisplayName());
			}
			highchartColumnVo = getArealDistributionListFromHistory(
					statisticSearchVo, type);
			if (key != null) {
				if (PopulationType.DEPARTMENTPARTY.equals(type)) {
					cacheService.set(
							MemCacheConstant.ANALYSE_DEPARTEMENT_NAMESPACE,
							key, ModulTypes.CACHETIME, highchartColumnVo);
				} else {
					cacheService.set(
							MemCacheConstant.ANALYSE_ORGANIZATION_NAMESPACE,
							key, ModulTypes.CACHETIME, highchartColumnVo);
				}
			}
			return highchartColumnVo;
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	private HighchartColumnVo getArealDistributionListFromHistory(
			StatisticSearchVo statisticSearchVo, String type) {
		statisticSearchVo.setOrgType(getOrgType(statisticSearchVo));
		statisticSearchVo
				.setPropertyDomainId(getPropertyDomainId(statisticSearchVo));
		List<Map<String, Object>> list = null;
		if (PopulationType.DEPARTMENTPARTY.equals(type)) {
			list = departmentPartyNewService
					.queryDepartmentPartyListFromHistoryForList(statisticSearchVo);
		} else {
			list = primaryOrganizationsStatisticsNewDao
					.getPrimaryOrganizationsListFromHistory(statisticSearchVo);
		}
		list = removeList(list);
		return createResultByHistory(list, statisticSearchVo);

	}

	// 历史记录添加帮教情况数据
	private HighchartColumnVo createResultByHistory(
			List<Map<String, Object>> list, StatisticSearchVo statisticSearchVo) {
		// 查询帮教情况，并组装数据
		HighchartColumnVo chartColumn = new HighchartColumnVo();
		chartColumn = createHighchartColumnVoByList(list, statisticSearchVo,
				chartColumn);
		return chartColumn;
	}

	/******************************************************************************************************************/

	/***
	 * 报表
	 */
	@Override
	public List<PrimaryOrganizationDataColumnVo> getPrimaryOrganizationDataByYearMonthColumnVoList(
			Long orgId, int year, int month) {
		String key = MemCacheConstant.getCachKeyForSixData(
				MemCacheConstant.PRIMARYORGANIZATIONS_CACHKEY, "", year, month,
				orgId, ModulTypes.STATISTICSTABLELIST);

		List<PrimaryOrganizationDataColumnVo> listData = null;

		if (key != null) {
			listData = (List<PrimaryOrganizationDataColumnVo>) cacheService
					.get(MemCacheConstant.ANALYSE_ORGANIZATION_NAMESPACE, key);
			if (listData != null) {
				return listData;
			}
		}

		try {
			tableService.createAnalyseTable(
					AnalyseUtilNew.PRIMARYORGANIZATIONSENAME,
					AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);

			List<Organization> organizationsList = organizationDubboService
					.findAdminOrgsByParentId(orgId);
			Organization organization = organizationDubboService
					.getFullOrgById(orgId);
			organizationsList.add(organization);
			List<SearchPrimaryOrganizationDataColumnVo> columnVoList = primaryOrganizationsStatisticsNewDao
					.findPrimaryOrganizationDataColumnByDate(year, month,
							organization.getId());

			listData = dealPrimaryOrganizationDataColumnVo(organization,
					columnVoList, organizationsList);
			if (key != null) {
				cacheService.set(
						MemCacheConstant.ANALYSE_ORGANIZATION_NAMESPACE, key,
						ModulTypes.CACHETIME, listData);
			}
			return listData;
		} catch (Exception e) {
			throw new ServiceValidationException("组织机构报表失败，原因：", "操作失败，请重试", e);
		}
	}

	private List<PrimaryOrganizationDataColumnVo> dealPrimaryOrganizationDataColumnVo(
			Organization organization,
			List<SearchPrimaryOrganizationDataColumnVo> columnVoList,
			List<Organization> organizationsList) {
		if (organizationsList != null && columnVoList.size() != 0
				&& organizationsList.size() != 0) {
			List<PrimaryOrganizationDataColumnVo> dealList = dealPrimaryOrganizationDataColumnVoList(
					organizationsList, columnVoList);
			if (dealList.size() == 1) {
				dealList.get(0).getOrg().setOrgName("本级");
			}

			dealList = DataCalculation(dealList, organization);

			List<PrimaryOrganizationDataColumnVo> dealListForZk = dealDataColumnVoListForZk(dealList);
			List<PrimaryOrganizationDataColumnVo> dealFinal = dealDataColumnVoListForHj(dealListForZk);
			return dealFinal;
		} else {
			List<PrimaryOrganizationDataColumnVo> list = new ArrayList<PrimaryOrganizationDataColumnVo>();
			for (Organization org : organizationsList) {
				PrimaryOrganizationDataColumnVo columnVo = new PrimaryOrganizationDataColumnVo();
				columnVo.setOrg(org);
				list.add(columnVo);
			}
			if (list.size() == 1) {
				list.get(0).getOrg().setOrgName("本级");
			}
			list = DataCalculation(list, organization);

			Organization org = new Organization();
			org.setOrgName("合计");
			PrimaryOrganizationDataColumnVo columnVo = new PrimaryOrganizationDataColumnVo();
			columnVo.setOrg(org);
			list.add(columnVo);
			return list;

		}
	}

	/***
	 * 数据计算
	 */
	private List<PrimaryOrganizationDataColumnVo> DataCalculation(
			List<PrimaryOrganizationDataColumnVo> dealList,
			Organization organization) {
		for (PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVo : dealList) {
			if (primaryOrganizationDataColumnVo.getOrg().getOrgInternalCode()
					.equals(organization.getOrgInternalCode())) {
				for (PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVoTwo : dealList) {
					if (!(primaryOrganizationDataColumnVo.getOrg()
							.getOrgInternalCode()
							.equals(primaryOrganizationDataColumnVoTwo.getOrg()
									.getOrgInternalCode()))) {
						/** 社会组织 */
						primaryOrganizationDataColumnVo
								.setSocialOrganizationCount(primaryOrganizationDataColumnVo
										.getSocialOrganizationCount()
										- primaryOrganizationDataColumnVoTwo
												.getSocialOrganizationCount());
						/** 非公有制经济组织 */
						primaryOrganizationDataColumnVo
								.setNewEconomicOrganizationsCount(primaryOrganizationDataColumnVo
										.getNewEconomicOrganizationsCount()
										- primaryOrganizationDataColumnVoTwo
												.getNewEconomicOrganizationsCount());
						/** 政府部门 */
						primaryOrganizationDataColumnVo
								.setGovernmentDepartmentsCount(primaryOrganizationDataColumnVo
										.getGovernmentDepartmentsCount()
										- primaryOrganizationDataColumnVoTwo
												.getGovernmentDepartmentsCount());
						primaryOrganizationDataColumnVo
								.setGovernmentDepartmentsMemberCount(primaryOrganizationDataColumnVo
										.getGovernmentDepartmentsMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getGovernmentDepartmentsMemberCount());
						/** 群防群治组织 */
						primaryOrganizationDataColumnVo
								.setMassOrganizationCount(primaryOrganizationDataColumnVo
										.getMassOrganizationCount()
										- primaryOrganizationDataColumnVoTwo
												.getMassOrganizationCount());
						primaryOrganizationDataColumnVo
								.setMassOrganizationMemberCount(primaryOrganizationDataColumnVo
										.getMassOrganizationMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getMassOrganizationMemberCount());
						/** 党委部门 */
						primaryOrganizationDataColumnVo
								.setPartyCommitteeCount(primaryOrganizationDataColumnVo
										.getPartyCommitteeCount()
										- primaryOrganizationDataColumnVoTwo
												.getPartyCommitteeCount());
						primaryOrganizationDataColumnVo
								.setPartyCommitteeMemberCount(primaryOrganizationDataColumnVo
										.getPartyCommitteeMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getPartyCommitteeMemberCount());
						/** 基层自治组织 */
						primaryOrganizationDataColumnVo
								.setAutonmoyTeamMemberCount(primaryOrganizationDataColumnVo
										.getAutonmoyTeamMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getAutonmoyTeamMemberCount());
						primaryOrganizationDataColumnVo
								.setAutonomyTeamCount(primaryOrganizationDataColumnVo
										.getAutonomyTeamCount()
										- primaryOrganizationDataColumnVoTwo
												.getAutonomyTeamCount());
						/** 群防群治组织 */
						primaryOrganizationDataColumnVo
								.setMassesTeamCount(primaryOrganizationDataColumnVo
										.getMassesTeamCount()
										- primaryOrganizationDataColumnVoTwo
												.getMassesTeamCount());
						primaryOrganizationDataColumnVo
								.setMassesTeamMemberCount(primaryOrganizationDataColumnVo
										.getMassesTeamMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getMassesTeamMemberCount());
						/** 社会志愿者队伍 */
						primaryOrganizationDataColumnVo
								.setPostulantTeamCount(primaryOrganizationDataColumnVo
										.getPostulantTeamCount()
										- primaryOrganizationDataColumnVoTwo
												.getPostulantTeamCount());
						primaryOrganizationDataColumnVo
								.setPostulantTeamMemberCount(primaryOrganizationDataColumnVo
										.getPostulantTeamMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getPostulantTeamMemberCount());
						/** 基层党委 */
						primaryOrganizationDataColumnVo
								.setPartyTeamCount(primaryOrganizationDataColumnVo
										.getPartyTeamCount()
										- primaryOrganizationDataColumnVoTwo
												.getPartyTeamCount());
						primaryOrganizationDataColumnVo
								.setPartyTeamMemberCount(primaryOrganizationDataColumnVo
										.getPartyTeamMemberCount()
										- primaryOrganizationDataColumnVoTwo
												.getPartyTeamMemberCount());

						primaryOrganizationDataColumnVo.getOrg().setOrgName(
								"本级");
					}
				}
				break;
			}
		}
		return dealList;
	}

	/**
	 * 统计各类队伍信息
	 * 
	 * @param parentOrgId
	 * @return
	 */

	public List<PrimaryOrganizationDataColumnVo> dealPrimaryOrganizationDataColumnVoList(
			List<Organization> organizationsList,
			List<SearchPrimaryOrganizationDataColumnVo> columnVoList) {
		List<PrimaryOrganizationDataColumnVo> list = new ArrayList<PrimaryOrganizationDataColumnVo>();
		PrimaryOrganizationDataColumnVo dataColumnVo = null;
		for (Organization organization : organizationsList) {
			int partyTeamCount = 0;// 基层党委
			int partyTeamMemberCount = 0;
			int autonomyTeamCount = 0;// 基层自治组织
			int autonmoyTeamMemberCount = 0;
			int massesTeamCount = 0;// 群防群治队伍
			int massesTeamMemberCount = 0;
			int postulantTeamCount = 0;// 社工志愿者队伍
			int postulantTeamMemberCount = 0;

			// 新增的部门
			int partyCommitteeCount = 0;// 党委部门 new
			int partyCommitteeMemberCount = 0;// 党委部门人数 new

			int socialOrganizationCount = 0;// 社会组织 new
			int newEconomicOrganizationsCount = 0;// 非公有制经济组织 new

			int governmentDepartmentsCount = 0;// 政府部门 new
			int governmentDepartmentsMemberCount = 0;// 政府部门人数 new

			int massOrganizationCount = 0;// 群团组织
			int massOrganizationMemberCount = 0;// 群团组织人数 new

			String orgInternalCode = organization.getOrgInternalCode();
			for (SearchPrimaryOrganizationDataColumnVo searchDataColumnVo : columnVoList) {
				dataColumnVo = new PrimaryOrganizationDataColumnVo();
				if (searchDataColumnVo.getOrgInternalCode().startsWith(
						orgInternalCode)) {
					// 判断是否是同一机构
					int basicOrgType = propertyDictService.getPropertyDictById(
							new Long(searchDataColumnVo.getTeamClazz()))
							.getInternalId();
					if (basicOrgType == BasicOrgType.PERMARY_PARTY
							|| basicOrgType == BasicOrgType.BASICLEVEL_PARTY) {// 基层党委.BASICLEVEL_PARTY
						// 党组织
						partyTeamCount += searchDataColumnVo.getTeamNum();

						partyTeamMemberCount += searchDataColumnVo
								.getMemberNum();

					} else if (basicOrgType == BasicOrgType.AUTONOMY_ORG) {
						// 自治组织
						autonomyTeamCount += searchDataColumnVo.getTeamNum();
						autonmoyTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.MASS_TREAT_TEAM) {
						// 群防群治队伍
						massesTeamCount += searchDataColumnVo.getTeamNum();
						massesTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.VOLUNTARY_TEAM) {
						// 社工志愿者队伍
						postulantTeamCount += searchDataColumnVo.getTeamNum();
						postulantTeamMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.DEPARTMENT_PARTY) {
						// 党委部门
						partyCommitteeCount += searchDataColumnVo.getTeamNum();
						partyCommitteeMemberCount += searchDataColumnVo
								.getMemberNum();

					} else if (basicOrgType == BasicOrgType.SOCIALORGANIZATION) {
						// 社会组织
						socialOrganizationCount += searchDataColumnVo
								.getTeamNum();
					} else if (basicOrgType == BasicOrgType.NEWECONOMICORGANIZATIONS) {
						// 非公有制经济组织
						newEconomicOrganizationsCount += searchDataColumnVo
								.getTeamNum();
					} else if (basicOrgType == BasicOrgType.GOVERNMENT_DEPARTMENT) {
						// 政府部门
						governmentDepartmentsCount += searchDataColumnVo
								.getTeamNum();
						governmentDepartmentsMemberCount += searchDataColumnVo
								.getMemberNum();
					} else if (basicOrgType == BasicOrgType.MASS_ORGANIZATION) {
						// 群团组织
						massOrganizationCount += searchDataColumnVo
								.getTeamNum();
						massOrganizationMemberCount += searchDataColumnVo
								.getMemberNum();
					}
				}
				dataColumnVo.setPartyCommitteeCount(partyCommitteeCount);
				dataColumnVo
						.setPartyCommitteeMemberCount(partyCommitteeMemberCount);
				dataColumnVo
						.setSocialOrganizationCount(socialOrganizationCount);
				dataColumnVo
						.setNewEconomicOrganizationsCount(newEconomicOrganizationsCount);
				dataColumnVo
						.setGovernmentDepartmentsCount(governmentDepartmentsCount);
				dataColumnVo
						.setGovernmentDepartmentsMemberCount(governmentDepartmentsMemberCount);
				dataColumnVo.setMassOrganizationCount(massOrganizationCount);
				dataColumnVo
						.setMassOrganizationMemberCount(massOrganizationMemberCount);

				dataColumnVo
						.setAutonmoyTeamMemberCount(autonmoyTeamMemberCount);
				dataColumnVo.setAutonomyTeamCount(autonomyTeamCount);
				dataColumnVo.setMassesTeamCount(massesTeamCount);
				dataColumnVo.setMassesTeamMemberCount(massesTeamMemberCount);
				dataColumnVo.setOrg(organization);
				dataColumnVo.setPartyTeamCount(partyTeamCount);
				dataColumnVo.setPartyTeamMemberCount(partyTeamMemberCount);
				dataColumnVo.setPostulantTeamCount(postulantTeamCount);
				dataColumnVo
						.setPostulantTeamMemberCount(postulantTeamMemberCount);

			}
			list.add(dataColumnVo);

		}

		return list;
	}

	/**
	 * 处理数据（总况）
	 * 
	 * @param parentOrgId
	 * @return
	 */
	private List<PrimaryOrganizationDataColumnVo> dealDataColumnVoListForZk(
			List<PrimaryOrganizationDataColumnVo> list) {
		int teamCount = 0;
		int teamMemberCount = 0;
		for (PrimaryOrganizationDataColumnVo columnVo : list) {
			teamMemberCount = columnVo.getPartyCommitteeMemberCount()
					+ columnVo.getPartyTeamMemberCount()
					+ columnVo.getAutonmoyTeamMemberCount()
					+ columnVo.getMassesTeamMemberCount()
					+ columnVo.getPostulantTeamMemberCount()
					+ columnVo.getGovernmentDepartmentsMemberCount()
					+ columnVo.getMassOrganizationMemberCount();

			teamCount = columnVo.getPartyCommitteeCount()
					+ columnVo.getPartyTeamCount()
					+ columnVo.getSocialOrganizationCount()
					+ columnVo.getNewEconomicOrganizationsCount()
					+ columnVo.getAutonomyTeamCount()
					+ columnVo.getMassesTeamCount()
					+ columnVo.getPostulantTeamCount()
					+ columnVo.getGovernmentDepartmentsCount()
					+ columnVo.getMassOrganizationCount();
			columnVo.setTeamCount(teamCount);
			columnVo.setTeamMemberCount(teamMemberCount);
		}
		return list;
	}

	/**
	 * 处理数据（合计）
	 * 
	 * @param parentOrgId
	 * @return
	 */
	private List<PrimaryOrganizationDataColumnVo> dealDataColumnVoListForHj(
			List<PrimaryOrganizationDataColumnVo> list) {
		int partyTeamCount = 0;// 党组织
		int partyTeamMemberCount = 0;
		int autonomyTeamCount = 0;// 自治组织
		int autonmoyTeamMemberCount = 0;
		int massesTeamCount = 0;// 群防群治队伍
		int massesTeamMemberCount = 0;
		int postulantTeamCount = 0;// 社工志愿者队伍
		int postulantTeamMemberCount = 0;

		int partyCommitteeCount = 0;// 党委部门 new
		int partyCommitteeMemberCount = 0;// 党委部门人数 new

		int socialOrganizationCount = 0;// 社会组织 new
		int newEconomicOrganizationsCount = 0;// 非公有制经济组织 new

		int governmentDepartmentsCount = 0;// 政府部门 new
		int governmentDepartmentsMemberCount = 0;// 政府部门人数 new

		int massOrganizationCount = 0;// 群团组织
		int massOrganizationMemberCount = 0;// 群团组织人数 new

		PrimaryOrganizationDataColumnVo primaryOrganizationDataColumnVo = new PrimaryOrganizationDataColumnVo();
		for (PrimaryOrganizationDataColumnVo columnVo : list) {
			partyTeamCount += columnVo.getPartyTeamCount();
			partyTeamMemberCount += columnVo.getPartyTeamMemberCount();
			autonomyTeamCount += columnVo.getAutonomyTeamCount();
			autonmoyTeamMemberCount += columnVo.getAutonmoyTeamMemberCount();
			massesTeamCount += columnVo.getMassesTeamCount();
			massesTeamMemberCount += columnVo.getMassesTeamMemberCount();
			postulantTeamCount += columnVo.getPostulantTeamCount();
			postulantTeamMemberCount += columnVo.getPostulantTeamMemberCount();
			// 新增加的几个部门
			partyCommitteeCount += columnVo.getPartyCommitteeCount();
			partyCommitteeMemberCount += columnVo
					.getPartyCommitteeMemberCount();
			socialOrganizationCount += columnVo.getSocialOrganizationCount();
			newEconomicOrganizationsCount += columnVo
					.getNewEconomicOrganizationsCount();
			governmentDepartmentsCount += columnVo
					.getGovernmentDepartmentsCount();
			governmentDepartmentsMemberCount += columnVo
					.getGovernmentDepartmentsMemberCount();
			massOrganizationCount += columnVo.getMassOrganizationCount();
			massOrganizationMemberCount += columnVo
					.getMassOrganizationMemberCount();
		}
		// 新加的部门
		primaryOrganizationDataColumnVo
				.setPartyCommitteeCount(partyCommitteeCount);
		primaryOrganizationDataColumnVo
				.setPartyCommitteeMemberCount(partyCommitteeMemberCount);
		primaryOrganizationDataColumnVo
				.setSocialOrganizationCount(socialOrganizationCount);
		primaryOrganizationDataColumnVo
				.setNewEconomicOrganizationsCount(newEconomicOrganizationsCount);
		primaryOrganizationDataColumnVo
				.setGovernmentDepartmentsCount(governmentDepartmentsCount);
		primaryOrganizationDataColumnVo
				.setGovernmentDepartmentsMemberCount(governmentDepartmentsMemberCount);
		primaryOrganizationDataColumnVo
				.setMassOrganizationCount(massOrganizationCount);
		primaryOrganizationDataColumnVo
				.setMassOrganizationMemberCount(massOrganizationMemberCount);

		primaryOrganizationDataColumnVo
				.setAutonmoyTeamMemberCount(autonmoyTeamMemberCount);
		primaryOrganizationDataColumnVo.setAutonomyTeamCount(autonomyTeamCount);
		primaryOrganizationDataColumnVo.setMassesTeamCount(massesTeamCount);
		primaryOrganizationDataColumnVo
				.setMassesTeamMemberCount(massesTeamMemberCount);
		primaryOrganizationDataColumnVo.setPartyTeamCount(partyTeamCount);
		primaryOrganizationDataColumnVo
				.setPartyTeamMemberCount(partyTeamMemberCount);
		primaryOrganizationDataColumnVo
				.setPostulantTeamCount(postulantTeamCount);
		primaryOrganizationDataColumnVo
				.setPostulantTeamMemberCount(postulantTeamMemberCount);
		Organization organization = new Organization();
		organization.setOrgName("合计");
		primaryOrganizationDataColumnVo.setOrg(organization);
		list.add(primaryOrganizationDataColumnVo);

		return list;
	}

	/******************************************************************************************************************/

	@Override
	public HighchartColumnVo findTendencyChartForPositiveinfo(String type,
			Long orgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization == null || organization.getId() == null) {
			throw new BusinessValidationException("组织机构信息不存在");
		}
		String key = MemCacheConstant.getCachKeyForThreeData(
				MemCacheConstant.PRIMARYORGANIZATIONS_CACHKEY,
				organization.getId(), ModulTypes.STATISTICSTREND);
		HighchartColumnVo highchartColumn = null;
		if (key != null) {
			highchartColumn = (HighchartColumnVo) cacheService.get(
					MemCacheConstant.ANALYSE_ORGANIZATION_NAMESPACE, key);
			if (highchartColumn != null) {
				return highchartColumn;
			}
		}
		try {
			HighchartColumnVo highchartColumnVo = new HighchartColumnVo();
			String[] time = new String[12];
			time = getTime(time);
			highchartColumnVo.setCategories(time);
			List<HighchartDataColumnVo> highchartDataColumnVos = getHighchartDataColumnVoListByType(
					type, orgId);

			highchartColumnVo.setModuleName(PopulationType
					.getCnameByPopulationType(type) + "增长图");

			highchartDataColumnVos
					.add(newGetHighchartDataColumnVo(orgId, time));

			highchartColumnVo.setSeries(highchartDataColumnVos);
			if (key != null) {
				cacheService.set(
						MemCacheConstant.ANALYSE_ORGANIZATION_NAMESPACE, key,
						ModulTypes.CACHETIME, highchartColumnVo);
			}
			return highchartColumnVo;
		} catch (Exception e) {
			throw new ServiceValidationException("组织机构趋势图统计失败，原因：", "操作失败，请重试",
					e);
		}
	}

	private HighchartDataColumnVo newGetHighchartDataColumnVo(Long orgId,
			String[] time) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		highchartDataColumnVo
				.setData(tendencyChartForPositiveinfo(time, orgId));
		highchartDataColumnVo.setName("总数量");
		highchartDataColumnVo.setStack("12345");
		return highchartDataColumnVo;
	}

	private List<HighchartDataColumnVo> getHighchartDataColumnVoListByType(
			String type, Long orgId) {
		String[] time = new String[12];
		time = getTime(time);
		PopulationCatalog populationCatalog = PopulationCatalog.parse(type);
		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainName(populationCatalog
						.getStatisticListSetting().getDomainName());
		if (propertyDictList != null && propertyDictList.size() != 0) {
			List<PropertyDict> moveList = new ArrayList<PropertyDict>();
			for (PropertyDict propertyDict : propertyDictList) {
				if (vaidateTypeNotNeed(propertyDict.getDisplayName())) {
					moveList.add(propertyDict);
				}
				if (BaseInfoTables.DEPARTMENTOFPARTY_DISPLAYNAME
						.equals(propertyDict.getDisplayName())) {
					propertyDict
							.setDisplayName(BaseInfoTables.PARTYCOMMITTEE_DISPLAYNAME);
				}
			}
			propertyDictList.removeAll(moveList);
		}
		HighchartDataColumnVo column;
		List<HighchartDataColumnVo> resultList = new ArrayList<HighchartDataColumnVo>();
		for (PropertyDict propertyDict : propertyDictList) {
			String typeName = propertyDict.getDisplayName();
			Long typeId = propertyDict.getId();
			final int len = time.length;
			column = new HighchartDataColumnVo();
			column.setData(new int[len]);
			column.setName(typeName);
			for (int i = 0; i < len; i++) {
				String timeData[] = time[i].split("-");
				tableService.createAnalyseTable(
						AnalyseUtilNew.PRIMARYORGANIZATIONSENAME,
						AnalyseUtilNew.PRIMARYORGANIZATIONSSQL,
						Integer.parseInt(timeData[0]),
						Integer.parseInt(timeData[1]));
				column.getData()[i] = primaryOrganizationsStatisticsNewDao
						.countPrimaryOrgByMap(typeId.toString(), time[i], orgId);
			}
			resultList.add(column);
		}
		return resultList;
	}

	/******************************************************************************************************************/
	/***
	 * 组织机构数据统计
	 */
	@Override
	public void addPrimaryOrganizationsStat(String type, Integer year,
			Integer month) {
		try {
			Boolean isCreate = tableService.createAnalyseTable(
					AnalyseUtilNew.PRIMARYORGANIZATIONSENAME,
					AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
			if (!isCreate) {
				primaryOrganizationsStatisticsNewDao.deletePrimaryStatType(
						year, month);
			}
			PopulationStatType populationStat = new PopulationStatType();
			populationStat.setYear(year);
			populationStat.setMonth(month);
			populationStat
					.setStartDate(CalendarUtil.getMonthStart(year, month));
			populationStat.setEndDate(CalendarUtil.getNextMonthStart(year,
					month));
			primaryOrganizationsStatisticsNewDao.addPrimaryOrganizationsStat(
					populationStat, type,
					CalendarUtil.getMonthStart(year, month),
					CalendarUtil.getNextMonthStart(year, month), null);
			packetStatisticsService.createPacketStatisticsByTypeAndTime(
					PacketStatisticsTables.PRIMARYORGANIZATIONS_KEY, null,
					year, month);
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	/******************************************************************************************************************/
	@Override
	public void createHistoryStatisticByType(int year, int month, String type,
			String orgInternalCode) {
		try {
			checkDate(year, month);
			checkHistoryStatisticTable(year, month);
			addPrimaryOrganizationsStat(type, year, month);
			packetStatisticsService
					.packetStatisticsByTableAndGroupType(PacketStatisticsHelper
							.createPacketStatisticsVo(year, month, type));
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	/**
	 * 判断数据库表是否存在
	 * 
	 * @param year
	 * @param month
	 */
	private void checkHistoryStatisticTable(int year, int month) {
		tableService.createAnalyseTable(
				AnalyseUtilNew.PRIMARYORGANIZATIONSENAME,
				AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
	}

	/******************************************************************************************************************/
	/**
	 * 判断时间是否超出了时间范围
	 * 
	 * @param year
	 * @param month
	 */
	private void checkDate(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		if (c.after(Calendar.getInstance())) {
			throw new BusinessValidationException("所查月份有误，请重新选择要生成报表的月份！");
		}

	}

	private int[] tendencyChartForPositiveinfo(String[] time, Long orgId) {
		int[] data = new int[12];
		for (int i = 0; i < time.length; i++) {
			Integer countSum = primaryOrganizationsStatisticsNewDao
					.countPrimaryOrgByMap(null, time[i], orgId);
			if (countSum != null) {
				data[i] = countSum;
			} else {
				data[i] = 0;
			}
		}
		return data;
	}

	private List<PopulationCatalog> getPopulationCatalogListByType(String type) {
		if (type.equals(PopulationCatalog.ALL_BIRTH_POPULATION)) {
			return PopulationCatalog.getAllBirthPopulationCatalog();

		} else if (type.equals(PopulationCatalog.ALL_UNEMPLOYED_POPULATION)) {
			return PopulationCatalog.getAllUnemployPopulationCatalog();
		} else if (type.equals(PopulationCatalog.ALL_CIVIL_POPULATION)) {
			return PopulationCatalog.getAllCivilPopulationCatalog();
		} else if (type.equals(PopulationCatalog.ALL_ATTENTION_POPULATION)) {
			return PopulationCatalog.getAllAttentionPopulationCatalog();
		} else {
			return null;
		}
	}

	private String[] getTime(String[] time) {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		nowCalendar.add(Calendar.MONTH, -12);

		for (int i = 0; i < 12; i++) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			time[i] = timePattern.format(nowCalendar.getTime());
			nowCalendar.add(Calendar.MONTH, 1);
		}

		return time;
	}

	private int[] getIntByInteger(Integer[] integers) {

		int len = integers.length;
		int[] results = new int[len];
		for (int i = 0; i < len; i++) {
			if (null == integers[i]) {
				results[i] = 0;
			} else {
				results[i] = integers[i];
			}
		}
		return results;
	}

	/***
	 * 整合数据
	 * 
	 * @param list
	 * @param statisticSearchVo
	 * @return
	 */
	private List<Map<String, Object>> dataIntegration(
			List<Map<String, Object>> list,
			List<Map<String, Object>> newSocietylist,
			List<Map<String, Object>> newEconomiclist) {
		List<Map<String, Object>> moveList = new ArrayList<Map<String, Object>>();
		if (list != null && list.size() != 0) {
			for (Map<String, Object> map : list) {
				String typeName = (String) map.get("TYPENAME");
				if (vaidateType(typeName)) {
					moveList.add(map);
				}
			}
			list.removeAll(moveList);
		}
		newSocietylist = removeNotSocietylist(newSocietylist);

		newEconomiclist = removeNotEconomic(newEconomiclist);

		return megerList(list, newSocietylist, newEconomiclist);

	}

	/** 将社会组织list和非公有制经济组织list合并到list中 */
	private List<Map<String, Object>> megerList(List<Map<String, Object>> list,
			List<Map<String, Object>> newSocietylist,
			List<Map<String, Object>> newEconomiclist) {
		if (newSocietylist != null && list.size() != 0) {
			for (Map<String, Object> maps : list) {
				Long orgId = Long.parseLong(((BigDecimal) maps.get("ORGID"))
						.toString());
				String typeNames = (String) maps.get("TYPENAME");
				for (Map<String, Object> map : newSocietylist) {
					Long newOrgId = Long.parseLong(((BigDecimal) map
							.get("ORGID")).toString());
					String typeName = (String) map.get("TYPENAME");
					if (typeName
							.equals(BaseInfoTables.NEWSOCIETYORGANIZATION_DISPLAYNAME)
							&& typeName.equals(typeNames)
							&& orgId.equals(newOrgId)) {
						maps.put("SUMNUM", map.get("SUMNUM"));
					}
				}
			}
		}

		if (newEconomiclist != null && list.size() != 0) {
			for (Map<String, Object> maps : list) {
				Long orgId = Long.parseLong(((BigDecimal) maps.get("ORGID"))
						.toString());
				String typeNames = (String) maps.get("TYPENAME");
				for (Map<String, Object> map : newEconomiclist) {
					Long newOrgId = Long.parseLong(((BigDecimal) map
							.get("ORGID")).toString());
					String typeName = (String) map.get("TYPENAME");
					if (typeName
							.equals(BaseInfoTables.NEWECONOMICORGANIZATION_DISPLAYNAME)
							&& typeName.equals(typeNames)
							&& orgId.equals(newOrgId)) {
						maps.put("SUMNUM", map.get("SUMNUM"));
					}
				}
			}
		}

		return list;
	}

	/** 去掉社会组织list中非社会组织的数据 */
	private List<Map<String, Object>> removeNotSocietylist(
			List<Map<String, Object>> newSocietylist) {
		if (newSocietylist != null && newSocietylist.size() != 0) {
			List<Map<String, Object>> newSocietylists = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> maps : newSocietylist) {
				if (!BaseInfoTables.NEWSOCIETYORGANIZATION_DISPLAYNAME
						.equals(maps.get("TYPENAME"))) {
					newSocietylists.add(maps);
				}
			}
			newSocietylist.removeAll(newSocietylists);
		}
		return newSocietylist;
	}

	/** 去掉非公有制经济组织中其他数据 */
	private List<Map<String, Object>> removeNotEconomic(
			List<Map<String, Object>> newEconomiclist) {
		if (newEconomiclist != null && newEconomiclist.size() != 0) {
			List<Map<String, Object>> newEconomiclists = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> maps : newEconomiclist) {
				if (!BaseInfoTables.NEWSOCIETYORGANIZATION_DISPLAYNAME
						.equals(maps.get("TYPENAME"))) {
					newEconomiclists.add(maps);
				}
			}
			newEconomiclist.removeAll(newEconomiclists);
		}
		return newEconomiclist;
	}

	private boolean vaidateType(String typeName) {
		if (typeName == null || typeName.trim().length() == 0) {
			return false;
		}
		if (getPrimaryOrganizationListByNeedType().contains(typeName)) {
			return true;
		}
		return false;
	}

	private boolean vaidateTypeNotNeed(String typeName) {
		if (typeName == null || typeName.trim().length() == 0) {
			return false;
		}
		if (getPrimaryOrganizationListByNotNeedType().contains(typeName)) {
			return true;
		}
		return false;
	}

	private List<String> getPrimaryOrganizationListByNeedType() {
		List<String> primaryOrganizationList = new ArrayList<String>();
		// primaryOrganizationList
		// .add(BaseInfoTables.NEWSOCIETYORGANIZATION_DISPLAYNAME);
		// primaryOrganizationList
		// .add(BaseInfoTables.NEWECONOMICORGANIZATION_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.SPECIALORGANIZATION_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.COMPREHENSIVEORGANIZATION_DISPLAYNAME);
		primaryOrganizationList.add(BaseInfoTables.GRASSROOTSPARTY_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.GRIDORGANIZATION_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.OTHERORGANIZATION_DISPLAYNAME);
		return primaryOrganizationList;
	}

	private List<String> getPrimaryOrganizationListByNotNeedType() {
		List<String> primaryOrganizationList = new ArrayList<String>();
		primaryOrganizationList
				.add(BaseInfoTables.SPECIALORGANIZATION_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.COMPREHENSIVEORGANIZATION_DISPLAYNAME);
		primaryOrganizationList.add(BaseInfoTables.GRASSROOTSPARTY_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.GRIDORGANIZATION_DISPLAYNAME);
		primaryOrganizationList
				.add(BaseInfoTables.OTHERORGANIZATION_DISPLAYNAME);
		primaryOrganizationList.add(BaseInfoTables.ZUZHIBU);
		primaryOrganizationList.add(BaseInfoTables.XUANCHUANBU);
		primaryOrganizationList.add(BaseInfoTables.TONGZHANBU);
		primaryOrganizationList.add(BaseInfoTables.ZHEGNFAWEI);
		primaryOrganizationList.add(BaseInfoTables.FANGXIEBAN);
		primaryOrganizationList.add(BaseInfoTables.XINFANGBAN);
		primaryOrganizationList.add(BaseInfoTables.ZZCYDANWEI);
		primaryOrganizationList.add(BaseInfoTables.ZXGZLDXIAOZU);
		return primaryOrganizationList;
	}

	private Long getOrgType(StatisticSearchVo statisticSearchVo) {
		return propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						statisticSearchVo.getOrganizationType(),
						OrganizationType.ADMINISTRATIVE_REGION).get(0).getId();
	}

	private Long getPropertyDomainId(StatisticSearchVo statisticSearchVo) {
		return propertyDomainService.getPropertyDomainByDomainName(
				statisticSearchVo.getDomainName()).getId();
	}

	private List<Map<String, Object>> removeList(List<Map<String, Object>> list) {
		if (list != null && list.size() != 0) {
			List<Map<String, Object>> moveList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : list) {
				if (vaidateTypeNotNeed((String) map.get("TYPENAME"))) {
					moveList.add(map);
				}
			}
			if (moveList != null) {
				list.removeAll(moveList);
			}
		}
		return list;
	}
}
