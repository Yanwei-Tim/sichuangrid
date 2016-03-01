package com.tianque.plugin.analysisNew.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analysisNew.dao.DepartmentPartyNewDAO;
import com.tianque.plugin.analysisNew.domain.DepartmentPartyColumnVo;
import com.tianque.plugin.analysisNew.domain.PopulationStatType;
import com.tianque.plugin.analysisNew.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analysisNew.util.PacketStatisticsTables;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

@Service("departmentPartyNewService")
@Transactional
public class DepartmentPartyNewServiceImpl implements DepartmentPartyNewService {
	@Autowired
	private DepartmentPartyNewDAO departmentPartyNewDAO;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PacketStatisticsService packetStatisticsService;

	@Override
	public List<DepartmentPartyColumnVo> queryDepartmentPartyDataByYearMonthColumnVoForList(
			Long orgId, int year, int month, Integer orgLevelDistance) {
		String key = MemCacheConstant.getDepartmentKey(
				MemCacheConstant.DEPARTEMENT_CACHKEY, year, month, orgId,
				ModulTypes.STATISTICSTABLELIST, orgLevelDistance);

		List<DepartmentPartyColumnVo> listData = null;
		if (key != null) {
			listData = (List<DepartmentPartyColumnVo>) cacheService.get(
					MemCacheConstant.ANALYSE_DEPARTEMENT_NAMESPACE, key);
			if (listData != null) {
				return listData;
			}
		}
		try {
			tableService.createAnalyseTable(AnalyseUtilNew.DEPARTMENTPARTYNAME,
					AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
			List<Organization> organizationsList = null;
			Organization organization = organizationDubboService
					.getFullOrgById(orgId);
			if (orgLevelDistance == null) {

				organizationsList = organizationDubboService
						.findAdminOrgsByParentId(orgId);
			} else {

				organizationsList = organizationDubboService
						.findDistrictAdminOrgsByOrgType(
								propertyDictService
										.findPropertyDictByDomainNameAndInternalId(
												OrganizationType.ORG_TYPE_KEY,
												OrganizationType.ADMINISTRATIVE_REGION)
										.get(0).getId(),
								organization.getOrgInternalCode(),
								String.valueOf(orgLevelDistance));
			}

			organizationsList.add(organization);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("year", year);
			map.put("month", month);
			map.put("orgId", organization.getId());
			map.put("orgLevelDistance", orgLevelDistance);

			List<SearchPrimaryOrganizationDataColumnVo> columnVoList = departmentPartyNewDAO
					.queryDepartmentPartyDataByYearMonthColumnVoForList(map);
			listData = dealLoginManageDataColumnVo(organization, columnVoList,
					organizationsList);

			if (key != null) {
				cacheService.set(
						MemCacheConstant.ANALYSE_DEPARTEMENT_NAMESPACE, key,
						ModulTypes.CACHETIME, listData);
			}

			return listData;
		} catch (Exception e) {
			throw new ServiceValidationException("组织机构报表失败，原因：", "操作失败，请重试", e);
		}
	}

	/******************************************************************************************************************/
	@Override
	public void createHistoryStatisticByType(int year, int month, String type,
			String orgInternalCode) {
		try {
			checkDate(year, month);
			checkHistoryStatisticTable(year, month);
			addDepartmentParty(type, year, month);
			packetStatisticsService.createPacketStatisticsByTypeAndTime(
					PacketStatisticsTables.DEPARTMENTPARTY_KEY, null, year,
					month);
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	/******************************************************************************************************************/
	/***
	 * 组织机构数据统计
	 */
	@Override
	public void addDepartmentParty(String type, Integer year, Integer month) {
		try {
			Boolean isCreate = tableService.createAnalyseTable(
					AnalyseUtilNew.DEPARTMENTPARTYNAME,
					AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
			if (!isCreate) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("year", year);
				map.put("month", month);
				departmentPartyNewDAO.deleteDepartmentParty(map);
			}

			Map<String, Object> deleteMap = new HashMap<String, Object>();
			deleteMap.put("year", year);
			deleteMap.put("month", month);

			PopulationStatType populationStat = new PopulationStatType();
			populationStat.setYear(year);
			populationStat.setMonth(month);
			populationStat
					.setStartDate(CalendarUtil.getMonthStart(year, month));
			populationStat.setEndDate(CalendarUtil.getNextMonthStart(year,
					month));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", type);
			map.put("beginDate", CalendarUtil.getMonthStart(year, month));
			map.put("nextMonthStart",
					CalendarUtil.getNextMonthStart(year, month));
			map.put("populationStatType", populationStat);
			map.put("orginternalcode", null);

			departmentPartyNewDAO.addDepartmentParty(map);
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
		tableService.createAnalyseTable(AnalyseUtilNew.DEPARTMENTPARTYNAME,
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

	private List<DepartmentPartyColumnVo> dealLoginManageDataColumnVo(
			Organization organization,
			List<SearchPrimaryOrganizationDataColumnVo> columnVoList,
			List<Organization> organizationsList) {
		if (organizationsList != null && columnVoList.size() != 0
				&& organizationsList.size() != 0) {
			List<DepartmentPartyColumnVo> dealList = dealLoginManageDataColumnVoList(
					organizationsList, columnVoList);
			if (dealList.size() == 1) {
				dealList.get(0).getOrg().setOrgName("本级");
			}

			dealList = DataCalculation(dealList, organization);

			List<DepartmentPartyColumnVo> dealListForZk = dealDataColumnVoListForZk(dealList);
			List<DepartmentPartyColumnVo> dealFinal = dealDataColumnVoListForHj(dealListForZk);
			return dealFinal;
		} else {
			List<DepartmentPartyColumnVo> list = new ArrayList<DepartmentPartyColumnVo>();
			for (Organization org : organizationsList) {
				DepartmentPartyColumnVo columnVo = new DepartmentPartyColumnVo();
				columnVo.setOrg(org);
				list.add(columnVo);
			}
			if (list.size() == 1) {
				list.get(0).getOrg().setOrgName("本级");
			}
			list = DataCalculation(list, organization);

			Organization org = new Organization();
			org.setOrgName("合计");
			DepartmentPartyColumnVo columnVo = new DepartmentPartyColumnVo();
			columnVo.setOrg(org);
			list.add(columnVo);
			return list;

		}
	}

	/**
	 * 统计各类队伍信息
	 * 
	 * @param parentOrgId
	 * @return
	 */

	public List<DepartmentPartyColumnVo> dealLoginManageDataColumnVoList(
			List<Organization> organizationsList,
			List<SearchPrimaryOrganizationDataColumnVo> columnVoList) {
		List<DepartmentPartyColumnVo> list = new ArrayList<DepartmentPartyColumnVo>();
		DepartmentPartyColumnVo dataColumnVo = null;
		for (Organization organization : organizationsList) {
			int zongzhiweiCount = 0;
			int zongzhiweiMemberCount = 0;
			int zongzhibanCount = 0;
			int zongzhibanMemberCount = 0;

			String orgInternalCode = organization.getOrgInternalCode();
			dataColumnVo = new DepartmentPartyColumnVo();
			for (SearchPrimaryOrganizationDataColumnVo searchDataColumnVo : columnVoList) {
				if (searchDataColumnVo.getOrgInternalCode().startsWith(
						orgInternalCode)) {
					// 判断是否是同一机构
					String orgName = propertyDictService.getPropertyDictById(
							new Long(searchDataColumnVo.getTeamClazz()))
							.getDisplayName();
					if (BasicOrgType.ZOGNZHIWEI.equals(orgName)) {
						zongzhiweiCount += searchDataColumnVo.getTeamNum();

						zongzhiweiMemberCount += searchDataColumnVo
								.getMemberNum();

					} else if (BasicOrgType.ZOGNZHIBAN.equals(orgName)) {
						zongzhibanCount += searchDataColumnVo.getTeamNum();

						zongzhibanMemberCount += searchDataColumnVo
								.getMemberNum();
					}
				}
				dataColumnVo.setZongzhiweiCount(zongzhiweiCount);
				dataColumnVo.setZongzhiweiMemberCount(zongzhiweiMemberCount);
				dataColumnVo.setZongzhibanCount(zongzhibanCount);
				dataColumnVo.setZongzhibanMemberCount(zongzhibanMemberCount);
				dataColumnVo.setOrg(organization);
			}
			list.add(dataColumnVo);
		}

		return list;
	}

	/***
	 * 数据计算
	 */
	private List<DepartmentPartyColumnVo> DataCalculation(
			List<DepartmentPartyColumnVo> dealList, Organization organization) {

		for (DepartmentPartyColumnVo departmentPartyColumnVo : dealList) {
			if (departmentPartyColumnVo.getOrg().getOrgInternalCode()
					.equals(organization.getOrgInternalCode())) {

				for (DepartmentPartyColumnVo departmentPartyColumnVoTwo : dealList) {
					if (!(departmentPartyColumnVo.getOrg().getOrgInternalCode()
							.equals(departmentPartyColumnVoTwo.getOrg()
									.getOrgInternalCode()))) {

						departmentPartyColumnVo
								.setZongzhiweiCount(departmentPartyColumnVo
										.getZongzhiweiCount()
										- departmentPartyColumnVoTwo
												.getZongzhiweiCount());
						departmentPartyColumnVo
								.setZongzhiweiMemberCount(departmentPartyColumnVo
										.getZongzhiweiMemberCount()
										- departmentPartyColumnVoTwo
												.getZongzhiweiMemberCount());
						departmentPartyColumnVo
								.setZongzhibanCount(departmentPartyColumnVo
										.getZongzhibanCount()
										- departmentPartyColumnVoTwo
												.getZongzhibanCount());
						departmentPartyColumnVo
								.setZongzhibanMemberCount(departmentPartyColumnVo
										.getZongzhibanMemberCount()
										- departmentPartyColumnVoTwo
												.getZongzhibanMemberCount());

						departmentPartyColumnVo.getOrg().setOrgName("本级");
					}
				}
				break;
			}
		}
		return dealList;
	}

	/**
	 * 处理数据（总况）
	 * 
	 * @param parentOrgId
	 * @return
	 */
	private List<DepartmentPartyColumnVo> dealDataColumnVoListForZk(
			List<DepartmentPartyColumnVo> list) {
		int teamCount = 0;
		int teamMemberCount = 0;
		for (DepartmentPartyColumnVo columnVo : list) {
			teamMemberCount = columnVo.getZongzhiweiMemberCount()
					+ columnVo.getZongzhibanMemberCount();

			teamCount = columnVo.getZongzhiweiCount()
					+ columnVo.getZongzhibanCount();

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
	private List<DepartmentPartyColumnVo> dealDataColumnVoListForHj(
			List<DepartmentPartyColumnVo> list) {
		int zongzhiweiCount = 0;
		int zongzhiweiMemberCount = 0;
		int zongzhibanCount = 0;
		int zongzhibanMemberCount = 0;

		DepartmentPartyColumnVo departmentPartyColumnVo = new DepartmentPartyColumnVo();
		for (DepartmentPartyColumnVo columnVo : list) {
			zongzhiweiCount += columnVo.getZongzhiweiCount();
			zongzhiweiMemberCount += columnVo.getZongzhiweiMemberCount();
			zongzhibanCount += columnVo.getZongzhibanCount();
			zongzhibanMemberCount += columnVo.getZongzhibanMemberCount();
		}
		// 新加的部门
		departmentPartyColumnVo.setZongzhiweiCount(zongzhiweiCount);
		departmentPartyColumnVo.setZongzhiweiMemberCount(zongzhiweiMemberCount);
		departmentPartyColumnVo.setZongzhibanCount(zongzhibanCount);
		departmentPartyColumnVo.setZongzhibanMemberCount(zongzhibanMemberCount);

		Organization organization = new Organization();
		organization.setOrgName("合计");
		departmentPartyColumnVo.setOrg(organization);
		list.add(departmentPartyColumnVo);

		return list;
	}

	@Override
	public List<Map<String, Object>> queryDepartmentPartyListFromHistoryForList(
			StatisticSearchVo statisticSearchVo) {
		return departmentPartyNewDAO
				.queryDepartmentPartyListFromHistoryForList(statisticSearchVo);
	}
}
