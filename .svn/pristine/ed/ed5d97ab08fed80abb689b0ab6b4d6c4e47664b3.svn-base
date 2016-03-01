package com.tianque.plugin.analyzing.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.youths.constants.YouthsType;
import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.dao.PopulationStatTypeDao;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.PopulationAreaDataVo;
import com.tianque.plugin.analyzing.domain.PopulationDetailDataVo;
import com.tianque.plugin.analyzing.domain.PopulationStatType;
import com.tianque.plugin.analyzing.domain.StatisticSearchPopulationVo;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.impl.LogableService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.shard.util.ShardConversion;
import com.tianque.shard.util.ShardTables;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

@Service("statisticsPopulationService")
public class StatisticsPopulationServiceImpl extends LogableService implements
		StatisticsPopulationService {
	private static final String ALL_POPULATION_STATISTICS = "all_population_statistics";
	private static final String SEARCH_POPULATION_STATISTICS = "searchPopulationStatistics";
	@Autowired
	private PopulationStatTypeDao populationStatTypeDao;

	@Autowired
	PropertyDictService propertyDictService;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	// 分表存储时用的service
	@Autowired
	private TableManageService tableService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public List<PopulationAreaDataVo> getCurrentAreaDate(Long orgId,
			String populationType, Integer orgLevelDistance) {
		if (populationType == null || "".equals(populationType)
				|| BaseInfoTables.POPULATION_KEY.equals(populationType)) {
			return getPopulationAreaDataByOrgId(orgId, orgLevelDistance);
		} else {
			return getPopulationAreaDataByOrgIdAndPopulationType(orgId,
					populationType);
		}
	}

	@Override
	public List<PopulationAreaDataVo> getAreaDateByDate(Long orgId,
			String populationType, int year, int month, Integer orgLevelDistance) {
		String keyName = "";
		if (!StringUtil.isStringAvaliable(populationType)) {
			keyName = ALL_POPULATION_STATISTICS;
		} else {
			keyName = populationType;
		}
		String key = SEARCH_POPULATION_STATISTICS + "_" + keyName + "_"
				+ ModulTypes.STATISTICSTABLELIST + "_" + orgId + "_"
				+ orgLevelDistance + "_" + year + "_" + month;
		List<PopulationAreaDataVo> listData = (List<PopulationAreaDataVo>) cacheService
				.get(key);
		if (listData != null) {
			return listData;
		}
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		if (populationType == null || "".equals(populationType)) {
			listData = getPopulationAreaDataByOrgIdAndMonth(orgId, year, month,
					orgLevelDistance);
		} else {
			listData = getPopulationAreaDataByOrgIdAndMonthAndPopulationType(
					orgId, populationType, year, month);
		}

		cacheService.set(key, ModulTypes.CACHETIME, listData);
		return listData;
	}

	public void addpopulationStat() {
		List<Long> countryList = getOrganizationsByLevel(OrganizationLevel.COUNTRY_KEY);
		int year = CalendarUtil.getLastMonthYearValue();
		int month = CalendarUtil.getLastMonth();
		// 判断表是否存在，不存在创建表
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		populationStatTypeDao.deletePopulationStatType(null, year, month, null);
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			PopulationStatType populationStat = new PopulationStatType();
			populationStat.setPopulationType(entry.getKey());
			populationStat.setYear(year);
			populationStat.setMonth(month);
			populationStat
					.setStartDate(CalendarUtil.getMonthStart(year, month));
			populationStat.setEndDate(CalendarUtil.getNextMonthStart(year,
					month));
			for (Long countryOrgId : countryList) {
				final Organization organization = organizationDubboService
						.getSimpleOrgById(countryOrgId);
				if (!BaseInfoTables.personnelTables.get(
						BaseInfoTables.HOUSEHOLDSTAFF_KEY).equalsIgnoreCase(
						entry.getValue())) {
					populationStatTypeDao.addpopulationStatType(populationStat,
							entry.getValue(),
							CalendarUtil.getMonthStart(year, month),
							CalendarUtil.getNextMonthStart(year, month), null);
				} else {
					if (shardConversion.isOverCity(organization)) {
						List<Organization> orgs = organizationDubboService
								.findOrgsByOrgTypeAndOrgLevelAndParentId(
										OrganizationType.ADMINISTRATIVE_REGION,
										OrganizationLevel.CITY,
										organization.getId());
						for (Organization org : orgs) {
							populationStat.setOrgInternalCode(org
									.getOrgInternalCode());
							populationStat.setShardCode(shardConversion
									.getShardCode(org));
							populationStatTypeDao
									.addpopulationStatType(
											populationStat,
											entry.getValue()
													+ "_"
													+ shardConversion
															.getShardCode(org),
											CalendarUtil.getMonthStart(year,
													month), CalendarUtil
													.getNextMonthStart(year,
															month), null);
						}
					}
				}
			}
		}
	}

	public void addpopulationStat(Integer year, Integer month, Long orgId,
			String populationType) {
		if (year == null) {
			year = Calendar.getInstance().get(Calendar.YEAR);
			month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		String orgCode = null;
		if (orgId != null) {
			orgCode = organizationDubboService.getSimpleOrgById(orgId)
					.getOrgInternalCode();
		}
		// 判断表是否存在，不存在创建表
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		if (populationType == null || "".equals(populationType)) {
			populationStatTypeDao.deletePopulationStatType(orgCode, year,
					month, null);
			for (Entry<String, String> entry : BaseInfoTables.personnelTables
					.entrySet()) {
				PopulationStatType populationStat = new PopulationStatType();
				populationStat.setPopulationType(entry.getKey());
				populationStat.setYear(year);
				populationStat.setMonth(month);
				populationStat.setStartDate(CalendarUtil.getMonthStart(year,
						month));
				populationStat.setEndDate(CalendarUtil.getNextMonthStart(year,
						month));
				// populationStatTypeDao.addpopulationStatType(populationStat,
				// entry.getValue(),
				// CalendarUtil.getMonthStart(year, month),
				// CalendarUtil.getNextMonthStart(year, month), orgCode);
				addpopulationStatTypeForShard(populationStat, entry.getValue(),
						CalendarUtil.getMonthStart(year, month),
						CalendarUtil.getNextMonthStart(year, month), orgCode,
						orgId);
			}
		} else {

			String tableName = BaseInfoTables.personnelTables
					.get(populationType);
			populationStatTypeDao.deletePopulationStatType(orgCode, year,
					month, populationType);
			PopulationStatType populationStat = new PopulationStatType();
			populationStat.setPopulationType(populationType);
			populationStat.setYear(year);
			populationStat.setMonth(month);
			populationStat
					.setStartDate(CalendarUtil.getMonthStart(year, month));
			populationStat.setEndDate(CalendarUtil.getNextMonthStart(year,
					month));
			// populationStatTypeDao.addpopulationStatType(populationStat,
			// tableName, null,
			// CalendarUtil.getNextMonthStart(year, month), orgCode);
			addpopulationStatTypeForShard(populationStat, tableName, null,
					CalendarUtil.getNextMonthStart(year, month), orgCode, orgId);
		}

	}

	private void addpopulationStatTypeForShard(
			PopulationStatType populationType, String tableName,
			Date createDate, Date nextMonthStart, String orgCode, Long orgId) {
		if (ShardTables.SHARD_HOUSEHOLDSTAFFS.equalsIgnoreCase(tableName)) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			String shardCode = null;
			String tempTableName = null;
			if (shardConversion.isOverCity(organization)) {
				List<Organization> orgs = organizationDubboService
						.findOrgsByOrgTypeAndOrgLevelAndParentId(
								OrganizationType.ADMINISTRATIVE_REGION,
								OrganizationLevel.CITY, organization.getId());
				for (Organization org : orgs) {
					shardCode = shardConversion.getShardCode(org.getId());
					tempTableName = tableName + "_" + shardCode;
					populationStatTypeDao.addpopulationStatType(populationType,
							tempTableName, createDate, nextMonthStart, orgCode);
				}
			} else {
				shardCode = shardConversion.getShardCode(orgId);
				tempTableName = tableName + "_" + shardCode;
				populationStatTypeDao.addpopulationStatType(populationType,
						tempTableName, createDate, nextMonthStart, orgCode);
			}
		} else {
			populationStatTypeDao.addpopulationStatType(populationType,
					tableName, createDate, nextMonthStart, orgCode);
		}
	}

	public List<PopulationAreaDataVo> getPopulationAreaDataByOrgIdAndMonth(
			Long orgId, int year, int month, Integer orgLevelDistance) {
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<Map<String, Object>> list;
		if (orgLevelDistance == null) {
			list = populationStatTypeDao.findPopulationStatTypeForOrg(orgId,
					null, year, month, null, null, getOrgType());
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			list = populationStatTypeDao.findPopulationStatTypeForOrg(orgId,
					null, year, month, org.getOrgInternalCode(),
					orgLevelDistance, getOrgType());
		}
		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();

		Long oldOrgId = -1L;
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
					.longValue();
			String orgCode = (String) list.get(i).get("ORGCODE");
			String orgName = (String) list.get(i).get("ORGNAME");
			String populationType = (String) list.get(i).get("POPULATIONTYPE");
			Integer sum = ((BigDecimal) list.get(i).get("COUNT")).intValue();
			PopulationAreaDataVo areaDate = null;
			if (!orginternalId.equals(oldOrgId)) {
				if (oldOrgId != -1) {
					createSumRow(populationAreaDataVos
							.get(populationAreaDataVos.size() - 1));
				}
				areaDate = createPopulationAreaDatas(orginternalId, orgCode,
						orgName);
				populationAreaDataVos.add(areaDate);
			} else {
				areaDate = populationAreaDataVos.get(populationAreaDataVos
						.size() - 1);
			}
			createPopulationDetailDate(
					BaseInfoTables.getTypeDisplayNames(populationType), sum,
					areaDate, null);
			oldOrgId = orginternalId;
			total = total + sum;

		}
		createSumRow(populationAreaDataVos
				.get(populationAreaDataVos.size() - 1));
		PopulationAreaDataVo lasetareaDate = createLastTotal(total);
		populationAreaDataVos.add(lasetareaDate);
		return populationAreaDataVos;
	}

	private void createSumRow(PopulationAreaDataVo areaDate) {
		PopulationDetailDataVo populationDetailDataVoSum = new PopulationDetailDataVo();
		populationDetailDataVoSum.setName("合计");
		populationDetailDataVoSum.setShowLink(false);
		int total = 0;
		for (int i = 0; i < areaDate.getPopulationDetailDatas().size(); i++) {
			total = total
					+ areaDate.getPopulationDetailDatas().get(i).getAmount();
		}
		populationDetailDataVoSum.setAmount(total);
		if (total == 0) {
			populationDetailDataVoSum
					.setAmountPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(0)));
		} else {
			populationDetailDataVoSum
					.setAmountPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(1)));
		}
		// 求每条记录的百分比
		for (int i = 0; i < areaDate.getPopulationDetailDatas().size(); i++) {
			areaDate.getPopulationDetailDatas()
					.get(i)
					.setAmountPercentage(
							Double.parseDouble(new java.text.DecimalFormat(
									"#.0000").format(areaDate
									.getPopulationDetailDatas().get(i)
									.getAmount()
									/ (double) (total == 0 ? 1 : total))));
		}
		areaDate.getPopulationDetailDatas().add(populationDetailDataVoSum);
	}

	@Override
	public List<PopulationAreaDataVo> getPopulationAreaDataByOrgIdAndMonthAndPopulationType(
			Long orgId, String populationType, int year, int month) {
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<Map<String, Object>> list = populationStatTypeDao
				.findPopulationStatTypeForOrg(orgId, populationType, year,
						month, null, null, getOrgType());

		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();
		int countNum = countAlldata(list, "COUNT");
		for (int i = 0; i < list.size(); i++) {
			Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
					.longValue();
			String orgCode = (String) list.get(i).get("ORGCODE");
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("COUNT")).intValue();
			PopulationAreaDataVo areaDate = createPopulationAreaDatas(
					orginternalId, orgCode, orgName);
			populationAreaDataVos.add(areaDate);
			createPopulationDetailDate(
					BaseInfoTables.getTypeDisplayNames(populationType), sum,
					areaDate, countNum);
		}
		PopulationAreaDataVo areaDateTotal = createLastTotal(countNum);
		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	@Override
	public List<PopulationAreaDataVo> getPopulationAreaDataByOrgId(Long orgId,
			Integer orgLevelDistance) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();
		int total = 0;

		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			List<Map<String, Object>> list;
			if (orgLevelDistance == null) {
				list = populationStatTypeDao
						.getCountByOrgInternalCodeAndTableNameAndMap(
								entry.getValue(), orgId, null, null,
								getOrgType());
			} else {
				Organization org = organizationDubboService
						.getSimpleOrgById(orgId);
				list = populationStatTypeDao
						.getCountByOrgInternalCodeAndTableNameAndMap(
								entry.getValue(), orgId,
								org.getOrgInternalCode(), orgLevelDistance,
								getOrgType());
			}

			for (int i = 0; i < list.size(); i++) {
				Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
						.longValue();
				String orgCode = (String) list.get(i).get("ORGCODE");
				String orgName = (String) list.get(i).get("ORGNAME");
				Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
				total = total + sum;
				PopulationAreaDataVo areaDate = createPopulationAreaDatas(
						orginternalId, orgCode, orgName, populationAreaDataVos);
				createPopulationDetailDate(
						BaseInfoTables.getTypeDisplayNames(entry.getKey()),
						sum, areaDate, null);
			}
		}

		// 添加每个区域合计行
		countAreaNumber(populationAreaDataVos);

		PopulationAreaDataVo areaDateTotal = createLastTotal(total);
		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	private void countAreaNumber(
			List<PopulationAreaDataVo> populationAreaDataVos) {
		for (int i = 0; i < populationAreaDataVos.size(); i++) {
			PopulationDetailDataVo temp = new PopulationDetailDataVo();
			temp.setName("合计");
			temp.setShowLink(false);
			temp.setAmount(populationAreaDataVos.get(i).getAmount());
			temp.setAmountPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.00").format(1)));
			populationAreaDataVos.get(i).getPopulationDetailDatas().add(temp);
			for (int j = 0; j < populationAreaDataVos.get(i)
					.getPopulationDetailDatas().size(); j++) {
				populationAreaDataVos
						.get(i)
						.getPopulationDetailDatas()
						.get(j)
						.setAmountPercentage(
								Double.parseDouble(new java.text.DecimalFormat(
										"#.0000").format(populationAreaDataVos
										.get(i).getPopulationDetailDatas()
										.get(j).getAmount()
										/ (double) (temp.getAmount() == 0 ? 1
												: temp.getAmount()))));

			}

		}
	}

	private PopulationAreaDataVo createLastTotal(int countNum) {
		PopulationAreaDataVo areaDateTotal = new PopulationAreaDataVo();
		Organization organization = new Organization();
		organization.setOrgName("合计");
		areaDateTotal.setOrg(organization);
		areaDateTotal.setAmount(countNum);
		areaDateTotal
				.setPopulationDetailDatas((List) new ArrayList<PopulationAreaDataVo>());
		PopulationDetailDataVo detailDateTotal = new PopulationDetailDataVo();
		detailDateTotal.setName("/");
		detailDateTotal.setAmount(countNum);
		detailDateTotal.setShowLink(false);
		if (countNum == 0) {
			detailDateTotal
					.setAmountPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(0)));
		} else {
			detailDateTotal
					.setAmountPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(1)));
		}
		areaDateTotal.getPopulationDetailDatas().add(detailDateTotal);
		return areaDateTotal;
	}

	private void createPopulationDetailDate(String populationType, Integer sum,
			PopulationAreaDataVo areaDate, Integer total) {
		PopulationDetailDataVo detailDate = new PopulationDetailDataVo();
		detailDate.setName(populationType);
		detailDate.setAmount(sum);
		if (total != null && total != 0) {
			detailDate.setAmountPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.0000")
							.format(sum / (double) total)));
		}
		areaDate.getPopulationDetailDatas().add(detailDate);
		if (areaDate.getAmount() != null) {
			areaDate.setAmount(areaDate.getAmount() + sum);
		} else {
			areaDate.setAmount(sum);
		}
	}

	private PopulationAreaDataVo createPopulationAreaDatas(Long orginternalId,
			String orgCode, String orgName,
			List<PopulationAreaDataVo> populationAreaDataVos) {
		PopulationAreaDataVo areaDate = exist(orginternalId,
				populationAreaDataVos);
		if (areaDate == null) {
			Organization org = new Organization();
			org.setId(orginternalId);
			org.setOrgName(orgName);
			org.setOrgInternalCode(orgCode);
			areaDate = new PopulationAreaDataVo();
			areaDate.setOrg(org);
			areaDate.setOrgId(orginternalId);
			areaDate.setPopulationDetailDatas((List) new ArrayList<PopulationAreaDataVo>());
			populationAreaDataVos.add(areaDate);
		}
		return areaDate;

	}

	private PopulationAreaDataVo createPopulationAreaDatas(Long orginternalId,
			String orgCode, String orgName) {
		Organization org = new Organization();
		org.setId(orginternalId);
		org.setOrgName(orgName);
		org.setOrgInternalCode(orgCode);
		PopulationAreaDataVo areaDate = new PopulationAreaDataVo();
		areaDate.setOrg(org);
		areaDate.setOrgId(orginternalId);
		areaDate.setPopulationDetailDatas((List) new ArrayList<PopulationAreaDataVo>());
		return areaDate;
	}

	private PopulationAreaDataVo exist(Long orginternalId,
			List<PopulationAreaDataVo> populationAreaDataVos) {
		for (int i = 0; i < populationAreaDataVos.size(); i++) {
			if (populationAreaDataVos.get(i).getOrgId().equals(orginternalId)) {
				return populationAreaDataVos.get(i);
			}
		}
		return null;
	}

	@Override
	public List<PopulationAreaDataVo> getPopulationAreaDataByOrgIdAndPopulationType(
			Long orgId, String populationType) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();

		String tableName = BaseInfoTables.personnelTables.get(populationType);
		List<Map<String, Object>> list = populationStatTypeDao
				.getCountByOrgInternalCodeAndTableNameAndMap(tableName, orgId,
						null, null, getOrgType());
		int countNum = countAlldata(list, "SUM");
		for (int i = 0; i < list.size(); i++) {
			Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
					.longValue();
			String orgCode = (String) list.get(i).get("ORGCODE");
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			PopulationAreaDataVo areaDate = null;
			areaDate = createPopulationAreaDatas(orginternalId, orgCode,
					orgName);
			populationAreaDataVos.add(areaDate);
			createPopulationDetailDate(
					BaseInfoTables.getTypeDisplayNames(populationType), sum,
					areaDate, countNum);
		}

		PopulationAreaDataVo areaDateTotal = createLastTotal(countNum);

		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	private int countAlldata(List<Map<String, Object>> list, String columnName) {
		int countNum = 0;
		for (int i = 0; i < list.size(); i++) {
			Integer sum = ((BigDecimal) list.get(i).get(columnName)).intValue();
			countNum = countNum + sum;
		}
		return countNum;
	}

	@Override
	public HighchartColumnVo getPopulationColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.POPULATION_KEY));
		getPopulationColumnDate(highchartColumn, orgId);
		return highchartColumn;
	}

	private void getPopulationColumnDate(HighchartColumnVo highchartColumn,
			Long orgId) {
		List<String> categories = new ArrayList<String>();
		List<Integer> datas = new ArrayList<Integer>();
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			List<Map<String, Object>> list = populationStatTypeDao
					.getCountByOrgInternalCodeAndTableNameAndMap(
							entry.getValue(), orgId, null, null, getOrgType());

			for (int i = 0; i < list.size(); i++) {
				String orgName = (String) list.get(i).get("ORGNAME");
				Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
				if (!categories.contains(orgName)) {
					categories.add(orgName);
				}
				// 每一次list的长度都一样 所以第一次把数据填充上去 以后每次都是相加
				if (datas.size() < i + 1) {
					datas.add(sum);
				} else {
					datas.set(i, datas.get(i) + sum);
				}
			}
		}

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		positiveinfoColumnData.setName(highchartColumn.getModuleName());
		positiveinfoColumnData.setData(ArrayUtils.toPrimitive(datas
				.toArray(new Integer[datas.size()])));
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		positiveinfoColumns.add(positiveinfoColumnData);
		highchartColumn.setCategories(categories.toArray(new String[categories
				.size()]));
		highchartColumn.setSeries(positiveinfoColumns);
	}

	@Override
	public HighchartColumnVo getPopulationColumnByOrgIdAndType(Long orgId,
			String populationType) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(populationType));
		getPopulationColumnDate(highchartColumn, orgId, populationType);
		return highchartColumn;
	}

	private void getPopulationColumnDate(HighchartColumnVo highchartColumn,
			Long orgId, String populationType) {
		List<String> categories = new ArrayList<String>();
		List<Integer> datas = new ArrayList<Integer>();

		List<Map<String, Object>> list = populationStatTypeDao
				.getCountByOrgInternalCodeAndTableNameAndMap(
						BaseInfoTables.personnelTables.get(populationType),
						orgId, null, null, getOrgType());

		for (int i = 0; i < list.size(); i++) {
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			categories.add(orgName);
			datas.add(sum);
		}

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		positiveinfoColumnData.setName(highchartColumn.getModuleName());
		positiveinfoColumnData.setData(ArrayUtils.toPrimitive(datas
				.toArray(new Integer[datas.size()])));
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		positiveinfoColumns.add(positiveinfoColumnData);
		highchartColumn.setCategories(categories.toArray(new String[categories
				.size()]));
		highchartColumn.setSeries(positiveinfoColumns);
	}

	@Override
	public List<Object[]> getPopulationPieByOrgId(Long orgId,
			String populationType) {
		List<Object[]> populationPieDatas = new ArrayList<Object[]>();
		Map<String, Integer> map = getPopulationCountSumByOrgInternalCode(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode());
		double total = map.get("total");
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			if ("populationGis".equals(populationType)) {
				if ("OVERSEAPERSONNEL".equals(entry.getKey())) {
					continue;
				}
			}
			Object[] populationData = new Object[2];
			double count = map.get(entry.getKey());
			if (total == 0) {
				populationData[1] = 0;
			} else {
				populationData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(count / total * 100));
			}
			populationData[0] = BaseInfoTables.getTypeDisplayNames(entry
					.getKey())
					+ "( "
					+ new java.text.DecimalFormat("#").format(count) + " )";
			populationPieDatas.add(populationData);
		}
		return populationPieDatas;
	}

	private Map<String, Integer> getPopulationCountSumByOrgInternalCode(
			String orgCode) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			int sum = populationStatTypeDao.getCountByTableName(
					entry.getValue(), orgCode);
			map.put(entry.getKey(), sum);
			total = total + sum;
		}
		map.put("total", total);
		return map;
	}

	@Override
	public List<Object[]> getPopulationPieByOrgIdAndMonth(Long orgId,
			String populationType, int year, int month) {
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		List<Object[]> populationPieDatas = new ArrayList<Object[]>();
		Map<String, Integer> map = getPopulationCountSum(
				organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode(), year, month);
		double total = map.get("total");
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			Object[] populationData = new Object[2];
			double count = map.get(entry.getKey());
			if (total == 0) {
				populationData[1] = 0;
			} else {
				populationData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(count / total * 100));
			}
			populationData[0] = BaseInfoTables.getTypeDisplayNames(entry
					.getKey())
					+ "( "
					+ new java.text.DecimalFormat("#").format(count) + " )";
			populationPieDatas.add(populationData);
		}
		return populationPieDatas;
	}

	private Map<String, Integer> getPopulationCountSum(String orgCode,
			int year, int month) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			int sum = populationStatTypeDao.findTendencyChart(entry.getKey(),
					orgCode, year, month);
			map.put(entry.getKey(), sum);
			total = total + sum;
		}
		map.put("total", total);
		return map;
	}

	@Override
	public HighchartColumnVo getPopulationColumnByTime(Long orgId,
			String populationType, int year, int month) {
		String keyName = "";
		if (!StringUtil.isStringAvaliable(populationType)) {
			keyName = ALL_POPULATION_STATISTICS;
		} else {
			keyName = populationType;
		}

		String key = SEARCH_POPULATION_STATISTICS + "_" + keyName + "_"
				+ ModulTypes.STATISTICSCOLUMN + "_" + orgId + "_" + year + "_"
				+ month;
		HighchartColumnVo highchartColumnVo = (HighchartColumnVo) cacheService
				.get(key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		}

		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		if (populationType == null || "".equals(populationType)) {
			highchartColumn.setModuleName(BaseInfoTables
					.getTypeDisplayNames(BaseInfoTables.POPULATION_KEY));
		} else {
			highchartColumn.setModuleName(BaseInfoTables
					.getTypeDisplayNames(populationType));
		}

		List<String> categories = new ArrayList<String>();
		List<Integer> datas = new ArrayList<Integer>();

		List<Map<String, Object>> list = populationStatTypeDao
				.getPopulationColumnByTime(orgId, populationType, year, month,
						getOrgType());

		for (int i = 0; i < list.size(); i++) {
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			categories.add(orgName);
			datas.add(sum);
		}

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		positiveinfoColumnData.setName(highchartColumn.getModuleName());
		positiveinfoColumnData.setData(ArrayUtils.toPrimitive(datas
				.toArray(new Integer[datas.size()])));
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		positiveinfoColumns.add(positiveinfoColumnData);
		highchartColumn.setCategories(categories.toArray(new String[categories
				.size()]));
		highchartColumn.setSeries(positiveinfoColumns);

		cacheService.set(key, ModulTypes.CACHETIME, highchartColumn);

		return highchartColumn;
	}

	@Override
	public int getCountByOrgId(Long orgId, String tableType) {
		String tableName = BaseInfoTables.personnelTables.get(tableType);

		List<Map<String, Object>> list = populationStatTypeDao
				.getCountByOrgInternalCodeAndTableNameAndMap(tableName, orgId,
						null, null, getOrgType());
		int countNum = countAlldata(list, "SUM");
		// return populationStatTypeDao.getCountByOrgId(tableName, orgId);
		return countNum;
	}

	/**
	 * 实有人口通用的去显示类型分布图（包括总的总况的类型分布图）
	 * */
	public List<Object[]> getPopulationPieInfo(int year, int month, Long orgId,
			String populationType) {
		String keyName = "";
		if (!StringUtil.isStringAvaliable(populationType)) {
			keyName = ALL_POPULATION_STATISTICS;
		} else {
			keyName = populationType;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		outofDate(year, month);

		String key = SEARCH_POPULATION_STATISTICS + "_" + keyName + "_"
				+ ModulTypes.STATISTICSPIE + "_" + organization.getId() + "_"
				+ year + "_" + month;
		List<Object[]> list = (List<Object[]>) cacheService.get(key);
		if (list != null) {
			return list;
		}
		StatisticSearchPopulationVo statisticSearchPopulationVo = null;
		if (populationType != null && !"".equals(populationType.trim())) {
			// 实有人口单张表的特定字段（根据要求要分布的字段）的分布显示
			statisticSearchPopulationVo = createStatisticSearchPopulationVo(
					populationType, orgId, year, month);
		}
		// 当前月份实时取数据
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			// 实有人口总况的类型分布数据
			if (populationType == null || "".equals(populationType.trim())) {
				list = (List<Object[]>) getPopulationPieByOrgId(orgId,
						populationType);
				cacheService.set(key, ModulTypes.CACHETIME, list);
				return list;
			}

			if (statisticSearchPopulationVo != null) {
				statisticSearchPopulationVo.setOrginternalcode(organization
						.getOrgInternalCode());
			}
			list = getHouseholdStaffPopulationCountSumByStatisticSearchPopulationVo(statisticSearchPopulationVo);
			cacheService.set(key, ModulTypes.CACHETIME, list);
			return list;

		}
		// 实有人口总况的往月类型分布数据
		if (populationType == null || "".equals(populationType.trim())) {
			list = getPopulationPieByOrgIdAndMonth(orgId, populationType, year,
					month);
			cacheService.set(key, ModulTypes.CACHETIME, list);
			return list;
		}
		// 实有人口单张表的往月类型分布数据
		// 测试下addHouseholdStaffPopulationStat();

		if (statisticSearchPopulationVo.getDisplayName() != null
				&& !"".equals(statisticSearchPopulationVo.getDisplayName()
						.trim())) {
			tableService.createAnalyseTable(
					AnalyseUtil.ACTUALPERSONCATEGORYTABLENAME,
					AnalyseUtil.ACTUALPERSONCATEGORYSQL, year, month);
		}
		// 单个表类型分布图

		statisticSearchPopulationVo.setYear(year);
		statisticSearchPopulationVo.setMonth(month);
		statisticSearchPopulationVo.setOrginternalcode(organization
				.getOrgInternalCode());
		statisticSearchPopulationVo
				.setType(AnalyseUtil.ACTUALPERSONCATEGORYTABLENAME);

		list = getStatisticInfoFromHistory(statisticSearchPopulationVo);
		cacheService.set(key, ModulTypes.CACHETIME, list);
		return list;
	}

	/**
	 * 从历史报表中获取类型分布图需要的数据
	 * 
	 * @param statisticSearchPopulationVo
	 * @return
	 */
	private List<Object[]> getStatisticInfoFromHistory(
			StatisticSearchPopulationVo statisticSearchPopulationVo) {

		List<Object[]> resultList = new ArrayList<Object[]>();
		Object[] objs;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", statisticSearchPopulationVo.getType());
		map.put("year", statisticSearchPopulationVo.getYear());
		map.put("month", statisticSearchPopulationVo.getMonth());
		map.put("orgCode", statisticSearchPopulationVo.getOrginternalcode());
		map.put("domainName", statisticSearchPopulationVo.getDomainName());
		double total = Double.parseDouble(String.valueOf(populationStatTypeDao
				.countByMapFromHistorySta(map)));

		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainName(statisticSearchPopulationVo
						.getPropertyField());
		for (PropertyDict propertyDict : propertyDictList) {
			objs = new Object[2];

			map.put("typeName", propertyDict.getId());
			Long sum = populationStatTypeDao.countByMapFromHistorySta(map);

			objs[0] = propertyDict.getDisplayName() + "( "
					+ new java.text.DecimalFormat("#").format(sum) + " )";
			if (sum == 0) {
				objs[1] = Double.parseDouble("0");
			} else {
				objs[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(sum / total * 100));
			}

			resultList.add(objs);
		}
		return resultList;
	}

	private List<Object[]> getHouseholdStaffPopulationCountSumByStatisticSearchPopulationVo(
			StatisticSearchPopulationVo statisticSearchPopulationVo) {
		List<Object[]> populationPieDatas = new ArrayList<Object[]>();
		Map<String, Integer> map = getHouseholdStaffPopulationCountSumMapByStatisticSearchPopulationVo(statisticSearchPopulationVo);
		double total = map.get("total");
		for (Entry<String, String> entry : BaseInfoTables.householdStafftypes
				.entrySet()) {
			Object[] populationData = new Object[2];
			double count = map.get(entry.getKey());
			if (total == 0) {
				populationData[1] = 0;
			} else {
				populationData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(count / total * 100));
			}
			populationData[0] = BaseInfoTables.getTypeDisplayNames(entry
					.getKey())
					+ "( "
					+ new java.text.DecimalFormat("#").format(count) + " )";
			populationPieDatas.add(populationData);
		}
		return populationPieDatas;

	}

	private Map<String, Integer> getHouseholdStaffPopulationCountSumMapByStatisticSearchPopulationVo(
			StatisticSearchPopulationVo statisticSearchPopulationVo) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(statisticSearchPopulationVo
						.getPropertyField());
		int total = 0;

		if (propertyDicts != null && propertyDicts.size() > 0) {

			for (PropertyDict propertyDict : propertyDicts) {
				int sum = populationStatTypeDao.getHouseholdStaffCountByTypes(
						statisticSearchPopulationVo.getOrginternalcode(),
						propertyDict.getId(),
						statisticSearchPopulationVo.getStartDate(),
						statisticSearchPopulationVo.getEndDate(),
						statisticSearchPopulationVo.getTable(),
						statisticSearchPopulationVo.getDisplayName(),
						statisticSearchPopulationVo.getLogOut());
				map.put(propertyDict.getFullPinyin(), sum);
				total += sum;
			}
			map.put("total", total);

		}
		return map;
	}

	/**
	 * 定时统计类型分布图
	 */
	public void addHouseholdStaffPopulationStat() {

		Organization org = organizationDubboService.getRootOrganization();
		String orgCode = org.getOrgInternalCode();

		int year = CalendarUtil.getLastMonthYearValue();
		int month = CalendarUtil.getLastMonth();
		// 实有人口的
		for (PopulationCatalog populationCatalog : PopulationCatalog
				.getAllActualPopulationCatalog()) {
			if (populationCatalog.getStatisticListSetting().getSearchField() != null
					&& !"".equals(populationCatalog.getStatisticListSetting()
							.getSearchField().trim())) {
				addHouseholdStaffHistoryStatisticInfo(year, month,
						populationCatalog.getCatalog(), orgCode, org.getId());
			}

		}

	}

	private void addHouseholdStaffHistoryStatisticInfo(int year, int month,
			String type, String orgCode, Long orgId) {
		List<Long> countryList = getOrganizationsByLevel(OrganizationLevel.COUNTRY_KEY);
		outofDate(year, month);
		tableService.createAnalyseTable(
				AnalyseUtil.ACTUALPERSONCATEGORYTABLENAME,
				AnalyseUtil.ACTUALPERSONCATEGORYSQL, year, month);
		if (type != null && !"".equals(type.trim())) {
			StatisticSearchPopulationVo statisticSearchPopulationVo = createStatisticSearchPopulationVo(
					type, orgId, year, month);
			statisticSearchPopulationVo.setOrginternalcode(orgCode);
			statisticSearchPopulationVo
					.setType(AnalyseUtil.ACTUALPERSONCATEGORYTABLENAME);
			statisticSearchPopulationVo.setYear(year);
			statisticSearchPopulationVo.setMonth(month);
			for (Long countryOrgId : countryList) {
				final Organization organization = organizationDubboService
						.getSimpleOrgById(countryOrgId);
				if (!BaseInfoTables.personnelTables.get(
						BaseInfoTables.HOUSEHOLDSTAFF_KEY).equalsIgnoreCase(
						statisticSearchPopulationVo.getTable())) {
					createHouseholdStaffHistoryStatistic(year, month, type,
							statisticSearchPopulationVo);
				} else {
					if (shardConversion.isOverCity(organization)) {
						List<Organization> orgs = organizationDubboService
								.findOrgsByOrgTypeAndOrgLevelAndParentId(
										OrganizationType.ADMINISTRATIVE_REGION,
										OrganizationLevel.CITY,
										organization.getId());
						String domainName = statisticSearchPopulationVo
								.getTable();
						for (Organization org : orgs) {
							populationStatTypeDao
									.deleteHouseholdStaffHistoryStatistic(org
											.getOrgInternalCode(), year, month,
											statisticSearchPopulationVo
													.getType());
							statisticSearchPopulationVo.setTable(domainName
									+ "_" + shardConversion.getShardCode(org));
							statisticSearchPopulationVo.setOrginternalcode(org
									.getOrgInternalCode());
							addHouseholdStaffHistoryStatistic(statisticSearchPopulationVo);

						}
					}
				}

			}
		}
	}

	private void createHouseholdStaffHistoryStatistic(int year, int month,
			String type, StatisticSearchPopulationVo statisticSearchPopulationVo) {
		// 删除原有记录
		populationStatTypeDao.deleteHouseholdStaffHistoryStatistic(
				statisticSearchPopulationVo.getOrginternalcode(), year, month,
				statisticSearchPopulationVo.getType());
		statisticSearchPopulationVo.setYear(year);
		statisticSearchPopulationVo.setMonth(month);

		addHouseholdStaffHistoryStatistic(statisticSearchPopulationVo);

	}

	private void addHouseholdStaffHistoryStatistic(
			StatisticSearchPopulationVo statisticSearchPopulationVo) {
		List<PropertyDict> dicts = propertyDictService
				.findPropertyDictByDomainName(statisticSearchPopulationVo
						.getPropertyField());

		for (int i = 0; i < dicts.size(); i++) {
			StatisticSearchPopulationVo addSearchPopulationVo = (StatisticSearchPopulationVo) statisticSearchPopulationVo
					.clone();
			addSearchPopulationVo.setPropertyField(dicts.get(i)
					.getDisplayName());
			addSearchPopulationVo.setPropertyDict(dicts.get(i));
			populationStatTypeDao
					.addHouseholdStaffHistoryStatistic(addSearchPopulationVo);
		}

	}

	// 根据populationType 创建对应的StatisticSearchPopulationVo
	private StatisticSearchPopulationVo createStatisticSearchPopulationVo(
			String populationType, Long orgId, int year, int month) {
		PopulationCatalog populationCatalog = PopulationCatalog
				.parse(populationType);
		StatisticSearchPopulationVo statisticSearchPopulationVo = new StatisticSearchPopulationVo();

		statisticSearchPopulationVo.setTableDisplayName(PopulationType
				.getCnameByPopulationType(populationType));
		statisticSearchPopulationVo.setType(populationType);
		if (null != populationCatalog.getStatisticListSetting()) {

			statisticSearchPopulationVo.setDomainName(populationCatalog
					.getStatisticListSetting().getDomainName());

			// 设置表
			statisticSearchPopulationVo.setTable(populationCatalog
					.getStatisticListSetting().getTableName());

			// 设置搜索字典项
			statisticSearchPopulationVo.setPropertyField(populationCatalog
					.getStatisticListSetting().getPropertyField());

			// 设置搜索字段
			statisticSearchPopulationVo.setDisplayName(populationCatalog
					.getStatisticListSetting().getSearchField());
		}

		// 设置起始时间
		statisticSearchPopulationVo.setStartDate(getFirstDate(year, month));
		statisticSearchPopulationVo.setEndDate(getLastDate(year, month));

		statisticSearchPopulationVo.setOrgId(orgId);

		return statisticSearchPopulationVo;
	}

	private String getLastDate(int year, int month) {
		Date date = CalendarUtil.getMonthEnd(year, month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDate = sdf.format(date);

		return lastDate;
	}

	private String getFirstDate(int year, int month) {

		Date date = CalendarUtil.getMonthStart(year, month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String firstDate = sdf.format(date);
		return firstDate;
	}

	// 判断时间是否超出了时间范围
	private void outofDate(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		if (c.after(Calendar.getInstance())) {
			throw new BusinessValidationException("所查月份有误，请重新选择要生成报表的月份！");
		}

	}

	@Override
	public void addpopulationStatForYearAndMonth(Integer year, Integer month) {
		// 判断表是否存在，不存在创建表
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		populationStatTypeDao.deletePopulationStatType(null, year, month, null);
		for (Entry<String, String> entry : BaseInfoTables.personnelTables
				.entrySet()) {
			PopulationStatType populationStat = new PopulationStatType();
			populationStat.setPopulationType(entry.getKey());
			populationStat.setYear(year);
			populationStat.setMonth(month);
			populationStat
					.setStartDate(CalendarUtil.getMonthStart(year, month));
			populationStat.setEndDate(CalendarUtil.getNextMonthStart(year,
					month));
			populationStatTypeDao.addpopulationStatType(populationStat,
					entry.getValue(), CalendarUtil.getMonthStart(year, month),
					CalendarUtil.getNextMonthStart(year, month), null);
		}
	}

	@Override
	public void addFloatingPopulationStatForYearAndMonth(Integer year,
			Integer month) {
		// 判断表是否存在，不存在创建表
		tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
				AnalyseUtil.ACTUALPERSONSQL, year, month);
		populationStatTypeDao.deletePopulationStatType(null, year, month,
				BaseInfoTables.FLOATINGPOPULATION_KEY);
		PopulationStatType populationStat = new PopulationStatType();
		populationStat.setPopulationType(BaseInfoTables.FLOATINGPOPULATION_KEY);
		populationStat.setYear(year);
		populationStat.setMonth(month);
		populationStat.setStartDate(CalendarUtil.getMonthStart(year, month));
		populationStat.setEndDate(CalendarUtil.getNextMonthStart(year, month));
		populationStatTypeDao.addpopulationStatType(populationStat,
				"floatingPopulations", CalendarUtil.getMonthStart(year, month),
				CalendarUtil.getNextMonthStart(year, month), null);
	}

	@Override
	public void addYouthPopulationStat(int year, int month) {
		List<Long> countryList = getOrganizationsByLevel(OrganizationLevel.COUNTRY_KEY);
		// 判断表是否存在，不存在创建表
		boolean isCreate = tableService.createAnalyseTable(
				AnalyseUtil.YOUTH_TABLE_NAME, AnalyseUtil.ACTUALPERSONSQL,
				year, month);
		if (!isCreate) {
			populationStatTypeDao.deleteYouthStatType(year, month);
		}

		PopulationStatType populationStat = new PopulationStatType();
		populationStat.setYear(year);
		populationStat.setMonth(month);
		populationStat.setStartDate(CalendarUtil.getMonthStart(year, month));
		populationStat.setEndDate(CalendarUtil.getMonthEnd(year, month));
		for (Map.Entry<String, String> youthsTypeEntry : YouthsType.youthsTypes
				.entrySet()) {
			populationStat.setPopulationType(youthsTypeEntry.getKey());
			populationStat.setTypeName(youthsTypeEntry.getValue());
			SearchYouthsVo searchYouthsVo = new SearchYouthsVo();
			searchYouthsVo.setKeyType(youthsTypeEntry.getKey());
			YouthsType.fillBeginAgeAndEndAge(searchYouthsVo,
					propertyDictService);
			populationStat.setSearchYouthsVo(searchYouthsVo);
			// 流动人口
			populationStat.getSearchYouthsVo().setTableName(
					YouthsType.YOUTHS_FLOATINGPOPULATIONS);
			populationStatTypeDao.addYouthStatType(populationStat);
			// 未落户人口
			populationStat.getSearchYouthsVo().setTableName(
					YouthsType.YOUTHS_UNSETTLEDPOPULATIONS);
			populationStatTypeDao.addYouthStatType(populationStat);
			// 户籍人口
			populationStat.getSearchYouthsVo().setTableName(
					YouthsType.YOUTHS_HOUSEHOLDSTAFFS);
			for (Long countryOrgId : countryList) {
				List<Organization> orgs = organizationDubboService
						.findOrgsByOrgTypeAndOrgLevelAndParentId(
								OrganizationType.ADMINISTRATIVE_REGION,
								OrganizationLevel.CITY, countryOrgId);
				for (Organization org : orgs) {
					populationStat.setOrgInternalCode(org.getOrgInternalCode());
					populationStat.setShardCode(shardConversion
							.getShardCode(org));
					// 户籍人口
					populationStatTypeDao.addYouthStatType(populationStat);
				}
			}
		}

	}

	private Long getOrgType() {
		Long orgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0).getId();
		return orgType;
	}

	private List<Long> getOrganizationsByLevel(String level) {
		PropertyDict levelDict = propertyDictService
				.getPropertyDictByDomainName(level);

		PropertyDict typeDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationType.ADMINISTRATIVE_KEY);

		if (levelDict != null && levelDict.getId() != null && typeDict != null
				&& typeDict.getId() != null) {
			return organizationDubboService.getOrganizationsByLevel(
					typeDict.getId(), levelDict.getId());
		}
		return null;
	}
}
