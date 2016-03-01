package com.tianque.xichang.working.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.json.JSON;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.report.constant.ReportRow;
import com.tianque.xichang.working.report.dao.AccountReportDao;
import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.domain.AccountReportVo;
import com.tianque.xichang.working.report.service.AccountReportService;
import com.tianque.xichang.working.report.service.ReportCount;

@Scope("prototype")
@Service("accountReportService")
public class AccountReportServiceImpl implements AccountReportService {
	private static Logger logger = LoggerFactory
			.getLogger(AccountReportServiceImpl.class);

	@Autowired
	private AccountReportDao accountReportDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private Map<String, ReportCount> reportCountServices;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TableManageService tableManageService;

	private static Map<String, List<PropertyDict>> itemDictMap = new HashMap<String, List<PropertyDict>>();

	@Override
	public AccountReport getAccountReportById(Long id, Integer reportYear,
			Integer reportMonth) {
		if (id == null || reportYear == null || reportMonth == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return accountReportDao.getAccountReportById(id, reportYear,
					reportMonth);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceValidationException("获取三本台账报表失败", e);
		}
	}

	@Override
	public AccountReport findAccountReportBySearchVo(AccountReportVo searchVo) {
		try {
			Boolean isTableExist = tableManageService.createAnalyseTable(
					AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME,
					AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME_SQL,
					searchVo.getReportYear(), searchVo.getReportMonth());
			List<AccountReport> list = null;
			if (!isTableExist) {
				list = accountReportDao.findAccountReportBySearchVo(searchVo);
			}

			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				AccountReport newAccountReport = new AccountReport();
				newAccountReport.setAccountType(searchVo.getAccountType());
				newAccountReport.setReportYear(searchVo.getReportYear());
				newAccountReport.setReportMonth(searchVo.getReportMonth());
				newAccountReport.setReportType(searchVo.getReportType());
				Organization org = organizationDubboService
						.getSimpleOrgById(searchVo.getOrgId());
				newAccountReport.setOrganization(org);
				newAccountReport.setOrgInternalCode(org.getOrgInternalCode());
				return editAccountReport(newAccountReport);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("获取三本台账报表失败", e);
		}
	}

	private boolean hasDuplicate(AccountReport accountReport) {
		AccountReportVo searchVo = new AccountReportVo(accountReport
				.getOrganization().getId(), accountReport.getReportYear(),
				accountReport.getReportMonth(), accountReport.getAccountType(),
				accountReport.getReportType());
		List<AccountReport> list = accountReportDao
				.findAccountReportBySearchVo(searchVo);
		if (list == null || list.size() == 0) {
			return false;
		} else if (list.size() == 1) {
			AccountReport exsited = list.get(0);
			Long exceptedId = accountReport.getId();
			return exceptedId == null ? exsited != null
					: (exsited != null && exceptedId.equals(exsited.getId()));
		}
		return true;

	}

	@Override
	public AccountReport getAccountReportByAccountReport(
			AccountReport accountReport) {
		AccountReportVo searchVo = new AccountReportVo(accountReport
				.getOrganization().getId(), accountReport.getReportYear(),
				accountReport.getReportMonth(), accountReport.getAccountType(),
				accountReport.getReportType());
		Boolean isTableExist = tableManageService.createAnalyseTable(
				AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME,
				AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME_SQL,
				searchVo.getReportYear(), searchVo.getReportMonth());
		List<AccountReport> list = null;
		if (!isTableExist) {
			list = accountReportDao.findAccountReportBySearchVo(searchVo);
		}
		if (list != null && list.size() == 1) {
			AccountReport exsited = list.get(0);
			return exsited;
		}
		return null;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AccountReport editAccountReport(AccountReport accountReport) {

		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			tableManageService.createAnalyseTable(
					AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME,
					AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME_SQL,
					accountReport.getReportYear(),
					accountReport.getReportMonth());
			dispatchByAccountTypeAndReportType(accountReport);
			if (!hasDuplicate(accountReport)) {
				// System.out.println("插入成功");
				return accountReportDao.addAccountReport(accountReport);
			} else {
				// System.out.println("更新成功");
				return accountReportDao.updateAccountReport(accountReport);
			}

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AccountReportService的addAccountReport方法出现异常，原因：",
					"编辑三本台账报表信息出现错误", e);
		}
	}

	@Override
	public AccountReport saveAccountReport(AccountReport accountReport) {
		if (accountReport == null || accountReport.getOrganization() == null
				|| accountReport.getOrganization().getId() == null
				|| accountReport.getContent() == null
				|| "".equals(accountReport.getContent().trim())) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			if (!hasDuplicate(accountReport)) {
				return accountReportDao.addAccountReport(accountReport);
			} else {
				return accountReportDao.updateAccountReport(accountReport);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AccountReportService的addAccountReport方法出现异常，原因：",
					"编辑三本台账报表信息出现错误", e);
		}
	}

	public AccountReport refershAccountReport(AccountReport accountReport) {
		dispatchByAccountTypeAndReportType(accountReport);
		return accountReport;
	}

	/**
	 * 报表适配器方法：根据参数获取相应Service和对应的列字典项，分发请求
	 * 
	 * @param accountReport
	 *            报表参数
	 */
	private void dispatchByAccountTypeAndReportType(AccountReport accountReport) {
		String accountType = accountReport.getAccountType();
		int reportType = accountReport.getReportType().intValue();
		ReportCount reportCountService = null;
		List<PropertyDict> itemDicts = null;
		if (AccountType.PEOPLEASPIRATION.equalsIgnoreCase(accountType)) {
			reportCountService = reportCountServices
					.get("peopleAspirationService");
			// 民生诉求的项目类别(大类)
			if (itemDictMap.get(AccountType.PEOPLEASPIRATION) != null) {
				itemDicts = itemDictMap.get(AccountType.PEOPLEASPIRATION);
			} else {
				itemDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.ITEM_CATEGORY);
				itemDictMap.put(AccountType.PEOPLEASPIRATION, itemDicts);
			}
		} else if (AccountType.POORPEOPLE.equalsIgnoreCase(accountType)) {
			reportCountService = reportCountServices.get("poorPeopleService");
			// 困难群众的困难原因(大类)
			if (itemDictMap.get(AccountType.POORPEOPLE) != null) {
				itemDicts = itemDictMap.get(AccountType.POORPEOPLE);
			} else {
				itemDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.POORBIGINFO);
				itemDictMap.put(AccountType.POORPEOPLE, itemDicts);
			}
		} else if (AccountType.STEADYWORK.equalsIgnoreCase(accountType)) {
			reportCountService = reportCountServices.get("steadyWorkService");
			// 稳定工作台账的诉求类别（大类）
			if (itemDictMap.get(AccountType.STEADYWORK) != null) {
				itemDicts = itemDictMap.get(AccountType.STEADYWORK);
			} else {
				itemDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.ASPIRATIONTYPE);

				itemDictMap.put(AccountType.STEADYWORK, itemDicts);
			}
		}
		switch (reportType) {
		case 1:
			constructDistrictContent(accountReport, reportCountService,
					itemDicts);
			break;
		case 2:
			constructTownOneContent(accountReport, reportCountService,
					itemDicts);
			break;
		case 3:
			constructTownTwoContent(accountReport, reportCountService,
					itemDicts);
			break;
		case 4:
			constructVillageMouldContent(accountReport, reportCountService,
					itemDicts);
			break;
		case 5:
			constructxXiChangOneContent(accountReport, reportCountService,
					itemDicts);
			break;
		case 6:
			constructxXiChangTwoContent(accountReport, reportCountService,
					itemDicts);
			break;
		case 7:
			constructxXiChangThreeContent(accountReport, reportCountService,
					itemDicts);
			break;
		default:
			throw new BusinessValidationException("无法定位报表类型");
		}
	}

	/**
	 * 区县级报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructDistrictContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		Date date1 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 3);
		// 上个月统计的开始时间（当前月份的的上个月的上个月的20号23:59:59【上月月报表开始时间】）
		Date date2 = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - 1);
		// 当前年份的上一年的12月20号23:59:59【年度报表统计开始时间：上年的12月20】
		Date date3 = calendar.getTime();
		// calendar.set(Calendar.YEAR, accountReport.getReportYear() + 1);
		/** 年度结束时间（是以当前月为结尾还是以年度为结尾） */
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		// 当前年份的当前月的20号
		Date date4 = calendar.getTime();
		try {
			Long orgId = accountReport.getOrganization().getId();
			List<ReportRow> reportRows = ReportRow.getDistrictReportRowList();
			// 按照 建表类型 和 项目类型 统计数据
			List<ReportGroupCount> list = reportCountService
					.getReportGroupCount(orgId, date1, date0);
			// 建表类型
			List<PropertyDict> createTableDicts;
			if (itemDictMap.get(AccountType.CREATETABLE) != null) {
				createTableDicts = itemDictMap.get(AccountType.CREATETABLE);
			} else {
				createTableDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.CREATE_TABLE_TYPE);

				itemDictMap.put(AccountType.CREATETABLE, createTableDicts);
			}
			JSONObject json = createRowInfoByMap(
					remap(mapByPropertyDictKey(list, itemDicts,
							createTableDicts)), reportRows, null);
			// 按照 项目类型 统计上月月份数据
			List precedingmonthunfinish = reportCountService
					.getUnfinishByMonth(orgId, date2, date1);
			json = createRowInfoByMap(
					remap(createSingleRow(precedingmonthunfinish, itemDicts,
							ReportRow.PRECEDINGMONTHUNFINISH)), reportRows,
					json);
			// 按照 项目类型 统计本月月份数据
			List thismonthunfinish = reportCountService.getUnfinishByMonth(
					orgId, date1, date0);
			json = createRowInfoByMap(
					remap(createSingleRow(thismonthunfinish, itemDicts,
							ReportRow.THISMONTHUNFINISH)), reportRows, json);
			// 按照 办结情况 和 项目类型 统计数据
			List<ReportGroupCount> listByIsFinish = reportCountService
					.getReportByIsFinishAndItemcategory(orgId, date1, date0);
			Map<String, Map<PropertyDict, Integer>> datas = mapByStrKey(
					listByIsFinish, itemDicts, ReportRow.getIsFinishKeys(),
					null);
			// 按照 办结类型 和 项目类型 统计数据
			List<ReportGroupCount> listByFinishType = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date1, date0);
			datas = mapByStrKey(listByFinishType, itemDicts,
					ReportRow.getEndType(), datas);
			json = createRowInfoByMap(remap(datas), reportRows, json);
			// 按照 呈报类型 和 项目类型 统计数据
			List<ReportGroupCount> reportToCity = reportCountService
					.getReportByReportToCityEndAndItemcategory(orgId, date1,
							date0);
			json = createRowInfoByMap(
					remap(mapByStrKey(reportToCity, itemDicts,
							ReportRow.getReportToCity(), null)), reportRows,
					json);

			List<ReportRow> yearReportRows = ReportRow
					.getDistrictReportRowYearList();
			// 按照 建表类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listYear = reportCountService
					.getReportGroupCount(orgId, date3, date4);
			json = createRowInfoByMap(
					remap(mapByPropertyDictKey(listYear, itemDicts,
							createTableDicts)), yearReportRows, json);
			// 按照 办结情况 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listByIsFinishYear = reportCountService
					.getReportByIsFinishAndItemcategory(orgId, date3, date4);
			Map<String, Map<PropertyDict, Integer>> datasYear = mapByStrKey(
					listByIsFinishYear, itemDicts, ReportRow.getIsFinishKeys(),
					null);
			// 按照 办结类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listByFinishTypeYear = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date3, date4);
			datasYear = mapByStrKey(listByFinishTypeYear, itemDicts,
					ReportRow.getEndType(), datasYear);
			json = createRowInfoByMap(remap(datasYear), yearReportRows, json);
			// 按照 呈报类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> reportToCityYear = reportCountService
					.getReportByReportToCityEndAndItemcategory(orgId, date3,
							date4);
			json = createRowInfoByMap(
					remap(mapByStrKey(reportToCityYear, itemDicts,
							ReportRow.getReportToCity(), null)),
					yearReportRows, json);
			// 获取可编辑行的数据（固定行："row12"—>累计;"row27"—>本月;）
			if (accountReport.getContent() != null) {
				String strReport = accountReport.getContent();
				json.put("row12", JSONObject.fromObject(strReport).get("row12"));
				json.put("row27", JSONObject.fromObject(strReport).get("row27"));
			}
			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			throw new ServiceValidationException("生成台账信息报表失败", e);
		}
	}

	/**
	 * 西昌市1报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructxXiChangOneContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		Date date1 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 3);
		// 上个月统计的开始时间（当前月份的的上个月的上个月的20号23:59:59【上月月报表开始时间】）
		Date date2 = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - 1);
		// 当前年份的上一年的12月20号23:59:59【年度报表统计开始时间：上年的12月20】
		Date date3 = calendar.getTime();
		// calendar.set(Calendar.YEAR, accountReport.getReportYear() + 1);
		/** 年度结束时间（是以当前月为结尾还是以年度为结尾） */
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		// 当前年份的当前月的20号
		Date date4 = calendar.getTime();
		String orgCode = accountReport.getOrgInternalCode();
		Long orgId = accountReport.getOrganization().getId();
		if (orgCode == null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(accountReport.getOrganization().getId());
			orgCode = org.getOrgInternalCode();
		}
		if (orgCode == null) {
			throw new BusinessValidationException("组织机构不存在");
		}
		try {
			// 本月收集
			List<ReportRow> rowListCollection = ReportRow
					.getXiChangOneReportRowListCollection();
			// 本月办结
			List<ReportRow> rowListEnd = ReportRow
					.getXiChangOneReportRowListEnd();

			// 本年收集
			List<ReportRow> rowListYearCollection = ReportRow
					.getXiChangOneReportRowListYearCollection();
			// 本年办结
			List<ReportRow> rowListYearEnd = ReportRow
					.getXiChangOneReportRowListYearEnd();

			// 乡镇、街道呈报数(和市级机关、企事业单位呈报数) 和项目类型统计数据(第row1、2行数据)
			// 按照 职能部门或者行政部门 和 项目类型 统计数据(年)
			// 网格类型
			List<PropertyDict> orgTypeDicts;
			if (itemDictMap.get(AccountType.ORGANIZATION_TYPE) != null) {
				orgTypeDicts = itemDictMap.get(AccountType.ORGANIZATION_TYPE);
			} else {
				orgTypeDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_TYPE);
				itemDictMap.put(AccountType.ORGANIZATION_TYPE, orgTypeDicts);
			}
			List<ReportGroupCount> orgListYear = reportCountService
					.getReportToCityAndItemcategory(orgId, date3, date4,
							ReportRow.REPORT_TO_CITY_END);
			// 市级领导交办 和项目类型统计数据(年度)(第row3行数据)
			// 建表类型
			List<PropertyDict> createTableDicts;
			if (itemDictMap.get(AccountType.CREATETABLE) != null) {
				createTableDicts = itemDictMap.get(AccountType.CREATETABLE);
			} else {
				createTableDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.CREATE_TABLE_TYPE);

				itemDictMap.put(AccountType.CREATETABLE, createTableDicts);
			}

			List<ReportGroupCount> createDateListYear = reportCountService
					.getReportGroupCount(orgId, date3, date4);

			Map<String, Map<PropertyDict, Integer>> datas = mapByPropertyDictKey(
					orgListYear, itemDicts, orgTypeDicts, null);

			datas = mapByPropertyDictKey(createDateListYear, itemDicts,
					createTableDicts, datas);
			JSONObject json = createRowInfoByMap(remap(datas),
					rowListYearCollection, null);

			// 本年合计 转办乡镇、街道数 转办市级机关、企事业单位数 年 办结(row 6、7)
			List<ReportGroupCount> toTownListYear = reportCountService
					.getReportToTownAndItemcategory(orgId, date3, date4,
							ReportRow.CITY_REPORT_TO_TOWN_END);
			json = createRowInfoByMap(
					remap(mapByPropertyDictKey(toTownListYear, itemDicts,
							orgTypeDicts)), rowListYearEnd, json);

			// 按照 项目类型 统计上月月份数据(上月办理中)(第row9行数据)
			List precedingmonthunfinish = reportCountService
					.getUnfinishByMonth(orgId, date2, date1);
			json = createRowInfoByMap(
					remap(createSingleRow(precedingmonthunfinish, itemDicts,
							ReportRow.PRECEDINGMONTHUNFINISH)),
					rowListCollection, json);

			// 乡镇、街道呈报数(和市级机关、企事业单位呈报数) 和项目类型统计数据(第row11、12行数据)
			// 按照 职能部门或者行政部门 和 项目类型 统计数据(月)
			List<ReportGroupCount> orgListMonth = reportCountService
					.getReportToCityAndItemcategory(orgId, date1, date0,
							ReportRow.REPORT_TO_CITY_END);

			// 市级领导交办 和项目类型统计数据(月度)(第row13行数据)
			List<ReportGroupCount> createDateListMonth = reportCountService
					.getReportGroupCount(orgId, date1, date0);

			Map<String, Map<PropertyDict, Integer>> tempDatas = mapByPropertyDictKey(
					orgListMonth, itemDicts, orgTypeDicts, null);

			tempDatas = mapByPropertyDictKey(createDateListMonth, itemDicts,
					createTableDicts, datas);
			json = createRowInfoByMap(remap(tempDatas), rowListCollection, json);

			// 转办乡镇、街道数(转办市级机关、企事业单位数) 和项目类型统计数据(第row16、17行数据)
			// 按照 职能部门或者行政部门 和 项目类型 统计数据(月)
			List<ReportGroupCount> toTownListMonth = reportCountService
					.getReportToTownAndItemcategory(orgId, date1, date0,
							ReportRow.CITY_REPORT_TO_TOWN_END);
			json = createRowInfoByMap(
					remap(mapByPropertyDictKey(toTownListMonth, itemDicts,
							orgTypeDicts)), rowListEnd, json);

			// 按照 项目类型 统计本月月份数据(本月办理中)(第row19行数据)
			List thismonthunfinish = reportCountService.getUnfinishByMonth(
					orgId, date1, date0);
			json = createRowInfoByMap(
					remap(createSingleRow(thismonthunfinish, itemDicts,
							ReportRow.THISMONTHUNFINISH)), rowListCollection,
					json);

			// 获取可编辑行的数据（固定行："row4"—>累计收集;"row8"—>累计办结;"row14"—>本月收集;"row18"—>本月办结;）
			if (accountReport.getContent() != null) {
				String strReport = accountReport.getContent();
				json.put("row4", JSONObject.fromObject(strReport).get("row4"));
				json.put("row8", JSONObject.fromObject(strReport).get("row8"));
				json.put("row14", JSONObject.fromObject(strReport).get("row14"));
				json.put("row18", JSONObject.fromObject(strReport).get("row18"));
			}
			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			throw new ServiceValidationException("生成西昌市1报表失败", e);
		}
	}

	/**
	 * 西昌市2报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructxXiChangTwoContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		// Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		// Date date1 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 3);
		// 上个月统计的开始时间（当前月份的的上个月的上个月的20号23:59:59【上月月报表开始时间】）
		// Date date2 = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - 1);
		// 当前年份的上一年的12月20号23:59:59【年度报表统计开始时间：上年的12月20】
		Date date3 = calendar.getTime();
		// calendar.set(Calendar.YEAR, accountReport.getReportYear() + 1);
		/** 年度结束时间（是以当前月为结尾还是以年度为结尾） */
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		// 当前年份的当前月的20号
		Date date4 = calendar.getTime();
		String orgCode = accountReport.getOrgInternalCode();
		if (orgCode == null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(accountReport.getOrganization().getId());
			orgCode = org.getOrgInternalCode();
		}
		if (orgCode == null) {
			throw new BusinessValidationException("组织机构不存在");
		}
		try {
			List<ReportRow> reportRow = ReportRow
					.getXiChangTwoReportRowYearList();
			// 按照 建账层级（1 as 市县区，2 as 乡镇街道，3 as 村社区） 和 项目类型 查询
			List<ReportGroupCount> reportByCreateOrgLevel = reportCountService
					.getReportByCreateOrgLevelAndItemcategory(orgCode, date3,
							date4);
			JSONObject json = createRowInfoByMap(
					remap(mapByStrKey(reportByCreateOrgLevel, itemDicts,
							ReportRow.getCreateOrgLevel(), null)), reportRow,
					null);
			// 按照 办结层级（1 as 市县区，2 as 乡镇街道，3 as 村社区） 和 项目类型 查询
			List<ReportGroupCount> reportByFinishOrgLevel = reportCountService
					.getReportByFinishOrgLevelAndItemcategory(orgCode, date3,
							date4);
			json = createRowInfoByMap(
					remap(mapByStrKey(reportByFinishOrgLevel, itemDicts,
							ReportRow.getFinishOrgLevel(), null)), reportRow,
					json);
			// 按照 办结类型 和 项目类型 统计数据
			List<ReportGroupCount> byFinishType = reportCountService
					.getReportByFinishtypeAndItemcategory(orgCode, date3, date4);
			json = createRowInfoByMap(
					remap(mapByStrKey(byFinishType, itemDicts,
							ReportRow.getEndType(), null)), reportRow, json);
			// 按照 呈报类型 和 项目类型 统计数据
			List<ReportGroupCount> reportToCity = reportCountService
					.getReportByReportToCityEndAndItemcategory(accountReport
							.getOrganization().getId(), date3, date4);
			json = createRowInfoByMap(
					remap(mapByStrKey(reportToCity, itemDicts,
							ReportRow.getReportToCity(), null)), reportRow,
					json);
			// 获取可编辑行的数据（固定行："row11"—>累计;）
			if (accountReport.getContent() != null) {
				String strReport = accountReport.getContent();
				json.put("row11", JSONObject.fromObject(strReport).get("row11"));
			}
			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			throw new ServiceValidationException("生成台账信息报表失败", e);
		}
	}

	/**
	 * 西昌市3报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructxXiChangThreeContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date date0 = calendar.getTime();
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		Date date1 = calendar.getTime();
		String orgCode = accountReport.getOrgInternalCode();
		if (orgCode == null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(accountReport.getOrganization().getId());
			orgCode = org.getOrgInternalCode();
		}
		if (orgCode == null) {
			throw new BusinessValidationException("组织机构不存在");
		}
		try {
			List<ReportRow> reportRow = ReportRow
					.getXiChangThreeReportRowList();
			// 按照 建账层级（1 as 市县区，2 as 乡镇街道，3 as 村社区） 和 项目类型 查询
			List<ReportGroupCount> reportByCreateOrgLevel = reportCountService
					.getReportByCreateOrgLevelAndItemcategory(orgCode, date1,
							date0);
			JSONObject json = createRowInfoByMap(
					remap(mapByStrKey(reportByCreateOrgLevel, itemDicts,
							ReportRow.getCreateOrgLevel(), null)), reportRow,
					null);
			// 按照 办结层级（1 as 市县区，2 as 乡镇街道，3 as 村社区） 和 项目类型 查询
			List<ReportGroupCount> reportByFinishOrgLevel = reportCountService
					.getReportByFinishOrgLevelAndItemcategory(orgCode, date1,
							date0);
			json = createRowInfoByMap(
					remap(mapByStrKey(reportByFinishOrgLevel, itemDicts,
							ReportRow.getFinishOrgLevel(), null)), reportRow,
					json);
			// 按照 办结类型 和 项目类型 统计数据
			List<ReportGroupCount> byFinishType = reportCountService
					.getReportByFinishtypeAndItemcategory(orgCode, date1, date0);
			json = createRowInfoByMap(
					remap(mapByStrKey(byFinishType, itemDicts,
							ReportRow.getEndType(), null)), reportRow, json);
			// 按照 呈报类型 和 项目类型 统计数据
			List<ReportGroupCount> reportToCity = reportCountService
					.getReportByReportToCityEndAndItemcategory(accountReport
							.getOrganization().getId(), date1, date0);
			json = createRowInfoByMap(
					remap(mapByStrKey(reportToCity, itemDicts,
							ReportRow.getReportToCity(), null)), reportRow,
					json);
			// 获取可编辑行的数据（固定行："row11"—>本月;）
			if (accountReport.getContent() != null) {
				String strReport = accountReport.getContent();
				json.put("row11", JSONObject.fromObject(strReport).get("row11"));
			}
			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			throw new ServiceValidationException("生成台账信息报表失败", e);
		}
	}

	/**
	 * 乡镇级报表1
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructTownOneContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		Date date1 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 3);
		// 上个月统计的开始时间（当前月份的的上个月的上个月的20号23:59:59【上月月报表开始时间】）
		Date date2 = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - 1);
		// 当前年份的上一年的12月20号23:59:59【年度报表统计开始时间：上年的12月20】
		Date date3 = calendar.getTime();
		// calendar.set(Calendar.YEAR, accountReport.getReportYear() + 1);
		/** 年度结束时间（是以当前月为结尾还是以年度为结尾） */
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		// 当前年份的当前月的20号
		Date date4 = calendar.getTime();
		String orgCode = accountReport.getOrgInternalCode();
		if (orgCode == null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(accountReport.getOrganization().getId());
			orgCode = org.getOrgInternalCode();
		}
		if (orgCode == null) {
			throw new BusinessValidationException("组织机构不存在");
		}
		try {
			Long orgId = accountReport.getOrganization().getId();
			List<ReportRow> reportRows = ReportRow.getTownOneReportRowList();
			// 按照 建表类型 和 项目类型 统计数据
			List<ReportGroupCount> list = reportCountService
					.getReportGroupCount(orgId, date1, date0);
			// 建表类型
			List<PropertyDict> createTableDicts;
			if (itemDictMap.get(AccountType.CREATETABLE) != null) {
				createTableDicts = itemDictMap.get(AccountType.CREATETABLE);
			} else {
				createTableDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.CREATE_TABLE_TYPE);

				itemDictMap.put(AccountType.CREATETABLE, createTableDicts);
			}
			Map<String, Map<PropertyDict, Integer>> datas = mapByPropertyDictKey(
					list, itemDicts, createTableDicts);
			JSONObject json = createRowInfoByMap(remap(datas), reportRows, null);
			// 按照 办结类型 和 项目类型 统计数据
			List<ReportGroupCount> listByFinishType = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date1, date0);
			datas = mapByStrKey(listByFinishType, itemDicts,
					ReportRow.getEndType(), null);
			json = createRowInfoByMap(remap(datas), reportRows, json);
			// 按照 村社区呈报乡镇 和 项目类型 统计数据(月)
			List<ReportGroupCount> reportToTownFinish = reportCountService
					.getReportByReportToTownEndWithIsFinishAndItemcategory(
							orgId, date1, date0, true);
			datas = mapByStrKey(reportToTownFinish, itemDicts,
					ReportRow.getReportToTownFinish(), null);
			json = createRowInfoByMap(remap(datas), reportRows, json);

			List<ReportRow> yearReportRows = ReportRow
					.getTownOneReportRowYearList();
			// 按照 建表类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listYear = reportCountService
					.getReportGroupCount(orgId, date3, date4);
			Map<String, Map<PropertyDict, Integer>> datasYear = mapByPropertyDictKey(
					listYear, itemDicts, createTableDicts);
			// 按照 办结类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listByFinishTypeYear = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date3, date4);
			datasYear = mapByStrKey(listByFinishTypeYear, itemDicts,
					ReportRow.getEndType(), datasYear);
			json = createRowInfoByMap(remap(datasYear), yearReportRows, json);

			// 按照 村社区呈报乡镇 和 项目类型 统计数据(年度)
			List<ReportGroupCount> reportToTownFinishYear = reportCountService
					.getReportByReportToTownEndWithIsFinishAndItemcategory(
							orgId, date3, date4, true);
			Map<String, Map<PropertyDict, Integer>> datasFinishYear = mapByStrKey(
					reportToTownFinishYear, itemDicts,
					ReportRow.getReportToTownFinish(), null);
			json = createRowInfoByMap(remap(datasFinishYear), yearReportRows,
					json);

			// 按照 项目类型 统计上月月份数据
			List precedingmonthunfinish = reportCountService
					.getUnfinishByMonth(orgId, date2, date1);
			json = createRowInfoByMap(
					remap(createSingleRow(precedingmonthunfinish, itemDicts,
							ReportRow.PRECEDINGMONTHUNFINISH)), reportRows,
					json);
			// 按照 项目类型 统计本月月份数据
			List thismonthunfinish = reportCountService.getUnfinishByMonth(
					orgId, date1, date0);
			json = createRowInfoByMap(
					remap(createSingleRow(thismonthunfinish, itemDicts,
							ReportRow.THISMONTHUNFINISH)), reportRows, json);

			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			throw new ServiceValidationException("生成台账信息报表失败", e);
		}
	}

	/**
	 * 乡镇级报表2
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructTownTwoContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		Date date1 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 3);
		// 上个月统计的开始时间（当前月份的的上个月的上个月的20号23:59:59【上月月报表开始时间】）
		Date date2 = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - 1);
		// 当前年份的上一年的12月20号23:59:59【年度报表统计开始时间：上年的12月20】
		Date date3 = calendar.getTime();
		// calendar.set(Calendar.YEAR, accountReport.getReportYear() + 1);
		/** 年度结束时间（是以当前月为结尾还是以年度为结尾） */
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		// 当前年份的当前月的20号
		Date date4 = calendar.getTime();
		try {
			Long orgId = accountReport.getOrganization().getId();
			List<ReportRow> reportRows = ReportRow.getTownTwoReportRowList();
			// 按照 建表类型 和 项目类型 统计数据
			List<ReportGroupCount> list = reportCountService
					.getReportGroupCount(orgId, date1, date0);
			// 建表类型
			List<PropertyDict> createTableDicts;
			if (itemDictMap.get(AccountType.CREATETABLE) != null) {
				createTableDicts = itemDictMap.get(AccountType.CREATETABLE);
			} else {
				createTableDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.CREATE_TABLE_TYPE);

				itemDictMap.put(AccountType.CREATETABLE, createTableDicts);
			}
			Map<String, Map<PropertyDict, Integer>> tempData = mapByPropertyDictKey(
					list, itemDicts, createTableDicts);
			// 按照 呈报、流转乡镇 和 项目类型 统计数据
			List<ReportGroupCount> reportToTown = reportCountService
					.getReportByReportToTownEndAndItemcategory(orgId, date1,
							date0);
			tempData = mapByStrKey(reportToTown, itemDicts,
					ReportRow.getReportToTown(), tempData);
			JSONObject json = createRowInfoByMap(remap(tempData), reportRows,
					null);
			// 按照 项目类型 统计上月月份数据
			List precedingmonthunfinish = reportCountService
					.getUnfinishByMonth(orgId, date2, date1);
			json = createRowInfoByMap(
					remap(createSingleRow(precedingmonthunfinish, itemDicts,
							ReportRow.PRECEDINGMONTHUNFINISH)), reportRows,
					json);
			// 按照 项目类型 统计本月月份数据
			List thismonthunfinish = reportCountService.getUnfinishByMonth(
					orgId, date1, date0);
			json = createRowInfoByMap(
					remap(createSingleRow(thismonthunfinish, itemDicts,
							ReportRow.THISMONTHUNFINISH)), reportRows, json);
			// 按照 村社区呈报乡镇 和 项目类型 统计数据
			List<ReportGroupCount> reportToTownFinish = reportCountService
					.getReportByReportToTownEndWithIsFinishAndItemcategory(
							orgId, date1, date0, true);
			Map<String, Map<PropertyDict, Integer>> datas = mapByStrKey(
					reportToTownFinish, itemDicts,
					ReportRow.getReportToTownFinish(), null);
			// 按照 办结类型 和 项目类型 统计数据
			List<ReportGroupCount> listByFinishType = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date1, date0);
			datas = mapByStrKey(listByFinishType, itemDicts,
					ReportRow.getEndType(), datas);
			json = createRowInfoByMap(remap(datas), reportRows, json);
			// 获得按（村、社区呈报乡镇 或 乡镇直接建账）呈报市台账办 和项目类型统计数据
			List<ReportGroupCount> toOrgLevelReport = reportCountService
					.getReportByVillageOrTownReportToCityAndItemcategory(orgId,
							date1, date0, ReportRow.REPORT_TO_TOWN_END,
							ReportRow.REPORT_TO_CITY_END);
			json = createRowInfoByMap(
					remap(mapByStrKey(toOrgLevelReport, itemDicts,
							ReportRow.getWhoReportToCity(), null)), reportRows,
					json);

			List<ReportRow> yearReportRows = ReportRow
					.getTownTwoReportRowYearList();
			// 按照 建表类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listYear = reportCountService
					.getReportGroupCount(orgId, date3, date4);
			Map<String, Map<PropertyDict, Integer>> tempDataYear = mapByPropertyDictKey(
					listYear, itemDicts, createTableDicts);
			// 按照 呈报类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> reportToTownYear = reportCountService
					.getReportByReportToTownEndAndItemcategory(orgId, date3,
							date4);
			tempDataYear = mapByStrKey(reportToTownYear, itemDicts,
					ReportRow.getReportToTown(), tempDataYear);
			json = createRowInfoByMap(remap(tempDataYear), yearReportRows, json);
			// 按照 村社区呈报乡镇 和 项目类型 统计数据(年度)
			List<ReportGroupCount> reportToTownFinishYear = reportCountService
					.getReportByReportToTownEndWithIsFinishAndItemcategory(
							orgId, date3, date4, true);
			Map<String, Map<PropertyDict, Integer>> datasYear = mapByStrKey(
					reportToTownFinishYear, itemDicts,
					ReportRow.getReportToTownFinish(), null);
			// 按照 办结类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listByFinishTypeYear = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date3, date4);
			datasYear = mapByStrKey(listByFinishTypeYear, itemDicts,
					ReportRow.getEndType(), datasYear);
			json = createRowInfoByMap(remap(datasYear), yearReportRows, json);
			// 获得按（村、社区呈报乡镇 或 乡镇直接建账）呈报市台账办 和项目类型统计数据(年度)
			List<ReportGroupCount> toOrgLevelReportYear = reportCountService
					.getReportByVillageOrTownReportToCityAndItemcategory(orgId,
							date3, date4, ReportRow.REPORT_TO_TOWN_END,
							ReportRow.REPORT_TO_CITY_END);
			json = createRowInfoByMap(
					remap(mapByStrKey(toOrgLevelReportYear, itemDicts,
							ReportRow.getWhoReportToCity(), null)),
					yearReportRows, json);
			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			throw new ServiceValidationException("生成台账信息报表失败", e);
		}
	}

	/**
	 * 村、社区报表
	 * 
	 * @param accountReport
	 *            报表参数
	 * @param reportCountService
	 *            具体Service实现
	 * @param itemDicts
	 *            生成报表列的字典项
	 */
	private void constructVillageMouldContent(AccountReport accountReport,
			ReportCount reportCountService, List<PropertyDict> itemDicts) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		Date date1 = calendar.getTime();
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 3);
		// 上个月统计的开始时间（当前月份的的上个月的上个月的20号23:59:59【上月月报表开始时间】）
		Date date2 = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - 1);
		// 当前年份的上一年的12月20号23:59:59【年度报表统计开始时间：上年的12月20】
		Date date3 = calendar.getTime();
		// calendar.set(Calendar.YEAR, accountReport.getReportYear() + 1);
		/** 年度结束时间（是以当前月为结尾还是以年度为结尾） */
		calendar.set(Calendar.YEAR, accountReport.getReportYear());
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - 1);
		// 当前年份的当前月的20号
		Date date4 = calendar.getTime();
		String orgCode = accountReport.getOrgInternalCode();
		if (orgCode == null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(accountReport.getOrganization().getId());
			orgCode = org.getOrgInternalCode();
			accountReport.setOrgInternalCode(orgCode);
		}
		if (orgCode == null) {
			throw new BusinessValidationException("组织机构不存在");
		}
		try {
			Long orgId = accountReport.getOrganization().getId();
			List<ReportRow> reportRows = ReportRow.getVillageReportRowList();
			// 按照 建表类型 和 项目类型 统计数据
			List<ReportGroupCount> list = reportCountService
					.getReportGroupCount(orgId, date1, date0);
			// 建表类型
			List<PropertyDict> createTableDicts;
			if (itemDictMap.get(AccountType.CREATETABLE) != null) {
				createTableDicts = itemDictMap.get(AccountType.CREATETABLE);
			} else {
				createTableDicts = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.CREATE_TABLE_TYPE);

				itemDictMap.put(AccountType.CREATETABLE, createTableDicts);
			}

			// Map<String, Map<PropertyDict, Integer>> datas =
			// mapByPropertyDictKey(
			// list, itemDicts, createTableDicts);
			// JSONObject json = createRowInfoByMap(remap(datas), reportRows,
			// null);

			JSONObject json = createRowInfoByMap(
					remap(mapByPropertyDictKey(list, itemDicts,
							createTableDicts)), reportRows, null);
			// 按照 办结类型 和 项目类型 统计数据
			List<ReportGroupCount> listByFinishType = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date1, date0);
			Map<String, Map<PropertyDict, Integer>> datas = mapByStrKey(
					listByFinishType, itemDicts, ReportRow.getEndType(), null);
			json = createRowInfoByMap(remap(datas), reportRows, json);
			// 按照 村社区呈报乡镇 和 项目类型 统计数据(月)
			List<ReportGroupCount> reportToTownFinish = reportCountService
					.getReportByReportToTownEndWithIsFinishAndItemcategory(
							orgId, date1, date0, true);
			datas = mapByStrKey(reportToTownFinish, itemDicts,
					ReportRow.getReportToTownFinish(), null);
			json = createRowInfoByMap(remap(datas), reportRows, json);

			List<ReportRow> yearReportRows = ReportRow
					.getVillageReportRowYearList();
			// 按照 建表类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listYear = reportCountService
					.getReportGroupCount(orgId, date3, date4);
			json = createRowInfoByMap(
					remap(mapByPropertyDictKey(listYear, itemDicts,
							createTableDicts)), yearReportRows, json);
			// = mapByPropertyDictKey(
			// listYear, itemDicts, createTableDicts);
			// 按照 办结类型 和 项目类型 统计数据(年度)
			List<ReportGroupCount> listByFinishTypeYear = reportCountService
					.getReportByFinishtypeAndItemcategory(orgId, date3, date4);
			Map<String, Map<PropertyDict, Integer>> datasYear = mapByStrKey(
					listByFinishTypeYear, itemDicts, ReportRow.getEndType(),
					null);
			json = createRowInfoByMap(remap(datasYear), yearReportRows, json);

			// 按照 村社区呈报乡镇 和 项目类型 统计数据(年度)
			List<ReportGroupCount> reportToTownFinishYear = reportCountService
					.getReportByReportToTownEndWithIsFinishAndItemcategory(
							orgId, date3, date4, true);
			Map<String, Map<PropertyDict, Integer>> datasFinishYear = mapByStrKey(
					reportToTownFinishYear, itemDicts,
					ReportRow.getReportToTownFinish(), null);
			json = createRowInfoByMap(remap(datasFinishYear), yearReportRows,
					json);

			// 按照 项目类型 统计上月月份数据
			List precedingmonthunfinish = reportCountService
					.getUnfinishByMonth(orgId, date2, date1);
			json = createRowInfoByMap(
					remap(createSingleRow(precedingmonthunfinish, itemDicts,
							ReportRow.PRECEDINGMONTHUNFINISH)), reportRows,
					json);
			// 按照 项目类型 统计本月月份数据
			List thismonthunfinish = reportCountService.getUnfinishByMonth(
					orgId, date1, date0);
			json = createRowInfoByMap(
					remap(createSingleRow(thismonthunfinish, itemDicts,
							ReportRow.THISMONTHUNFINISH)), reportRows, json);

			accountReport.setContentObject(json);
			accountReport.setContent(JSON.json(json));
		} catch (Exception e) {
			logger.error("社区级报表统计错误", e);
			throw new ServiceValidationException("生成台账信息报表失败", e);
		}

	}

	/**
	 * 对组合型字典项字段进行分割，模拟重组成单个字典项的元数据。（仅针对Map类型）
	 * 
	 * @param list
	 *            需要对列字典项进行分割的Map对象的集合
	 * @return 重组后的{@code Map<PropertyDict, count>} 的List集合
	 * @throws Exception
	 */
	private List<Map> splitMapItems(final List<Map> list) throws Exception {
		if (list == null || list.size() == 0) {
			return list;
		}
		List<Map> newList = new ArrayList<Map>();
		Map<Long, Long> tempMap = new HashMap<Long, Long>();
		Iterator<Map> it = list.iterator();
		while (it.hasNext()) {
			Map reportCount = it.next();
			Object keys = reportCount.get("ITEMCATEGORY");
			if (keys instanceof BigDecimal) {
				return list;
			}
			String[] ids = ((String) keys).split(",");
			for (String id : ids) {
				Long key = Long.valueOf(id.trim());
				long sum = tempMap.get(key) == null ? 0L : tempMap.get(key);
				long tmp = Long.parseLong(String.valueOf(reportCount
						.get("COUNTS")));
				tempMap.put(key, sum + tmp);
			}
		}
		Set<Entry<Long, Long>> counts = tempMap.entrySet();
		for (Entry<Long, Long> count : counts) {
			Map elementData = new HashMap();
			elementData.put("ITEMCATEGORY", BigDecimal.valueOf(count.getKey()));
			elementData.put("COUNTS", BigDecimal.valueOf(count.getValue()));
			newList.add(elementData);
		}
		return newList;
	}

	/**
	 * 对组合型字典项字段进行分割，重组成单个字典项的元数据。（仅针对ReportGroupCount类型）
	 * 
	 * @param list
	 *            需要对列字典项进行分割的ReportGroupCount对象的集合
	 * @return 重组后的ReportGroupCount的List集合
	 * @throws Exception
	 */
	private List<ReportGroupCount> splitItems(List<ReportGroupCount> list)
			throws Exception {
		if (list == null || list.size() == 0 || list.get(0).getItems() == null) {
			return list;
		}
		List<ReportGroupCount> newList = new ArrayList<ReportGroupCount>();
		ReportGroupCount newGroupCount = null;
		for (ReportGroupCount groupCount : list) {
			String[] ids = groupCount.getItems().split(",");
			for (String id : ids) {
				List<ReportGroupCount> removeList = new ArrayList<ReportGroupCount>();
				newGroupCount = new ReportGroupCount();
				newGroupCount.setRowType(groupCount.getRowType());
				newGroupCount.setCount(groupCount.getCount());
				PropertyDict dict = new PropertyDict();
				dict.setId(0L);
				dict.setId(Long.valueOf(id.trim()));
				newGroupCount.setItem(dict);
				for (ReportGroupCount existsCount : newList) {
					if (existsCount.getRowType() == null) {
						continue;
					}
					if (existsCount.getRowType().equals(
							newGroupCount.getRowType())
							&& existsCount.getItem().getId()
									.equals(newGroupCount.getItem().getId())) {
						newGroupCount.setCount(existsCount.getCount()
								+ newGroupCount.getCount());
						removeList.add(existsCount);
					}
				}
				newList.removeAll(removeList);
				newList.add(newGroupCount);
				newGroupCount = null;
			}
		}
		return newList;
	}

	/**
	 * 根据查询的Map数据结果 和 给定的行定义，生成可被remap所支持的数据类型
	 * 
	 * @param list
	 *            查询数据库得到的分组数据
	 * @param itemDicts
	 *            用于匹配列的字典项
	 * @param reportRowKey
	 *            单行数据的中文描述行键
	 * @return {@code Map<中文描述行键（行）, Map<字典项（列）, 统计数>>}
	 * @throws Exception
	 */
	private Map<String, Map<PropertyDict, Integer>> createSingleRow(
			List<Map> list, List<PropertyDict> itemDicts, String reportRowKey)
			throws Exception {
		list = splitMapItems(list);
		Map<String, Map<PropertyDict, Integer>> map = new HashMap<String, Map<PropertyDict, Integer>>();
		Map<PropertyDict, Integer> subMap = new HashMap<PropertyDict, Integer>();
		for (PropertyDict itemDict : itemDicts) {
			subMap.put(itemDict, 0);
			for (Map<String, Integer> itemMap : list) {
				if (itemDict.getId().equals(itemMap.get("ITEMCATEGORY"))) {
					subMap.put(itemDict, Integer.parseInt(String
							.valueOf(itemMap.get("COUNTS"))));
					break;
				}
			}
		}
		map.put(reportRowKey, subMap);
		return map;
	}

	private Map<String, Map<PropertyDict, Integer>> mapByPropertyDictKey(
			List<ReportGroupCount> list, List<PropertyDict> itemDicts,
			List<PropertyDict> rowKeyList,
			Map<String, Map<PropertyDict, Integer>> existsDatas)
			throws Exception {
		list = splitItems(list);
		if (existsDatas == null) {
			existsDatas = new HashMap<String, Map<PropertyDict, Integer>>();
		}
		Map<PropertyDict, Integer> subMap = new HashMap<PropertyDict, Integer>();
		for (PropertyDict rowKey : rowKeyList) {
			for (PropertyDict itemDict : itemDicts) {
				subMap.put(itemDict, 0);
				for (ReportGroupCount reportGroup : list) {
					if (rowKey.getId().equals(reportGroup.getRowType())
							&& itemDict.getId().equals(
									reportGroup.getItem().getId())) {
						subMap.put(itemDict, reportGroup.getCount());
						break;
					}
				}
			}
			existsDatas.put(rowKey.toString(), subMap);
			subMap = new HashMap<PropertyDict, Integer>();
		}
		return existsDatas;
	}

	/**
	 * 根据查询的ReportGroupCount类型数据结果 和 给定的字典项类型的行定义，生成可被remap所支持的数据类型
	 * 
	 * @param list
	 *            查询数据库得到的分组数据
	 * @param itemDicts
	 *            用于匹配列的字典项
	 * @param rowKeyList
	 *            对应的字典项行健集合
	 * @return {@code Map<中文描述行键（行）, Map<字典项（列）, 统计数>>}
	 * @throws Exception
	 */
	private Map<String, Map<PropertyDict, Integer>> mapByPropertyDictKey(
			List<ReportGroupCount> list, List<PropertyDict> itemDicts,
			List<PropertyDict> rowKeyList) throws Exception {
		list = splitItems(list);
		Map<String, Map<PropertyDict, Integer>> map = new HashMap<String, Map<PropertyDict, Integer>>();
		Map<PropertyDict, Integer> subMap = new HashMap<PropertyDict, Integer>();
		for (PropertyDict rowKey : rowKeyList) {
			for (PropertyDict itemDict : itemDicts) {
				subMap.put(itemDict, 0);
				for (ReportGroupCount reportGroup : list) {
					if (rowKey.getId().equals(reportGroup.getRowType())
							&& itemDict.getId().equals(
									reportGroup.getItem().getId())) {
						subMap.put(itemDict, reportGroup.getCount());
						break;
					}
				}
			}
			map.put(rowKey.toString(), subMap);
			subMap = new HashMap<PropertyDict, Integer>();
		}
		return map;
	}

	/**
	 * 根据查询的ReportGroupCount类型数据结果 和 给定的字典项类型的行定义，生成可被remap所支持的数据类型
	 * 
	 * @param list
	 *            查询数据库得到的分组数据
	 * @param itemDicts
	 *            用于匹配列的字典项
	 * @param rowKeyList
	 *            对应的字典项行健集合
	 * @return {@code Map<中文描述行键（行）, Map<字典项（列）, 统计数>>}
	 * @throws Exception
	 */
	/**
	 * 根据查询的ReportGroupCount类型数据结果 和 给定映射关系的行定义，生成可被remap所支持的数据类型
	 * 
	 * @param list
	 *            查询数据库得到的分组数据
	 * @param itemDicts
	 *            用于匹配列的字典项
	 * @param strKeys
	 *            行键和代表行键的数值所组成Map集合
	 * @param existsDatas
	 *            已经存在的且和返回数据类型一致的数据（比如需要一起对列进行求和的数据）
	 * @return {@code Map<中文描述行键（行）, Map<字典项（列）, 统计数>>}
	 * @throws Exception
	 */
	private Map<String, Map<PropertyDict, Integer>> mapByStrKey(
			List<ReportGroupCount> list, List<PropertyDict> itemDicts,
			Map<Long, String> strKeys,
			Map<String, Map<PropertyDict, Integer>> existsDatas)
			throws Exception {
		list = splitItems(list);
		if (existsDatas == null) {
			existsDatas = new HashMap<String, Map<PropertyDict, Integer>>();
		}
		Map<PropertyDict, Integer> subMap = new HashMap<PropertyDict, Integer>();
		for (Entry<Long, String> strKey : strKeys.entrySet()) {
			for (PropertyDict itemDict : itemDicts) {
				subMap.put(itemDict, 0);
				for (ReportGroupCount reportGroup : list) {
					if (strKey.getKey().equals(reportGroup.getRowType())
							&& itemDict.getId().equals(
									reportGroup.getItem().getId())) {
						subMap.put(itemDict, reportGroup.getCount());
						break;
					}
				}
			}
			existsDatas.put(strKey.getValue(), subMap);
			subMap = new HashMap<PropertyDict, Integer>();
		}
		return existsDatas;
	}

	/**
	 * 将矩阵数据的列转化成符合前端可处理的列数据
	 * 
	 * @param oldMap
	 *            待转换的Map集合
	 * @return 转换后的Map集合
	 * @throws Exception
	 */
	private Map<String, Map<String, Integer>> remap(
			Map<String, Map<PropertyDict, Integer>> oldMap) throws Exception {
		Map map = new HashMap<String, Map>();
		Map subMap = new HashMap<String, Integer>();
		int all = 0;
		for (Entry<String, Map<PropertyDict, Integer>> entry : oldMap
				.entrySet()) {
			if (entry.getValue() == null) {
				continue;
			}
			for (Entry<PropertyDict, Integer> entrySub : entry.getValue()
					.entrySet()) {
				subMap.put("col" + entrySub.getKey().getId(),
						entrySub.getValue());
				all += entrySub.getValue();
			}
			subMap.put("colAll", all);
			all = 0;
			map.put(entry.getKey(), subMap);
			subMap = new HashMap<String, Integer>();
		}
		return map;
	}

	/**
	 * 统计计算创建行数据，产生用于前端展现和数据库存储的JSON数据
	 * 
	 * @param map
	 *            remap计算后的数据
	 * @param reportRows
	 *            跟前端格式一一对应的行定义的集合
	 * @param json
	 *            用于拼装的json串，没有可传空值
	 * @return 可用于前端展现和数据库持久化的JSON串
	 * @throws Exception
	 */
	private JSONObject createRowInfoByMap(
			Map<String, Map<String, Integer>> map, List<ReportRow> reportRows,
			JSONObject json) throws Exception {
		if (json == null) {
			json = new JSONObject();
		}
		JSONObject jsonSub = new JSONObject();
		for (ReportRow reportRow : reportRows) {
			Map<String, Integer> countMap = new HashMap<String, Integer>();
			for (Entry<String, Map<String, Integer>> entry : map.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				if (reportRow.getRowContents().contains(entry.getKey())) {
					Set<String> set = entry.getValue().keySet();
					for (String dict : set) {
						Integer i = countMap.get(dict);
						Integer ii = entry.getValue().get(dict);
						countMap.put(dict, (i != null ? i : 0) + ii);
					}
				}
			}
			for (Entry<String, Integer> entrySub : countMap.entrySet()) {
				jsonSub.accumulate(entrySub.getKey(), entrySub.getValue());
			}
			if (jsonSub.size() > 0) {
				json.accumulate(reportRow.getRowI(), jsonSub);
			}
			jsonSub = new JSONObject();
		}
		return json;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteAllVillageAccountReport(Integer reportYear,
			Integer reportMonth, Integer reportType, String orgCodeFindLevel) {
		if (reportYear == null || reportMonth == null || reportType == null
				|| orgCodeFindLevel == null || "".equals(orgCodeFindLevel)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			accountReportDao.deleteAllVillageAccountReport(reportYear,
					reportMonth, reportType, orgCodeFindLevel);
		} catch (Exception e) {
			throw new ServiceValidationException("删除特定的三本台账报表错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchAddVillageAccountReportForJob(
			List<AccountReport> accountReports) {
		if (accountReports == null || accountReports.size() == 0) {
			throw new BusinessValidationException("参数错误");
		}

		try {
			List<AccountReport> accountReportTemps = new ArrayList<AccountReport>();
			ReportCount reportCountService = null;
			List<PropertyDict> itemDicts = null;
			for (AccountReport accountReport : accountReports) {
				if (accountReport != null && accountReport.getReportType() == 4
						&& accountReport.getOrganization() != null
						&& accountReport.getOrganization().getId() != null) {

					String accountType = accountReport.getAccountType();
					if (AccountType.PEOPLEASPIRATION
							.equalsIgnoreCase(accountType)) {
						reportCountService = reportCountServices
								.get("peopleAspirationService");
						// 民生诉求的项目类别(大类)
						if (itemDictMap.get(AccountType.PEOPLEASPIRATION) != null) {
							itemDicts = itemDictMap
									.get(AccountType.PEOPLEASPIRATION);
						} else {
							itemDicts = propertyDictService
									.findPropertyDictByDomainName(PropertyTypes.ITEM_CATEGORY);
							itemDictMap.put(AccountType.PEOPLEASPIRATION,
									itemDicts);
						}
					} else if (AccountType.POORPEOPLE
							.equalsIgnoreCase(accountType)) {
						reportCountService = reportCountServices
								.get("poorPeopleService");
						// 困难群众的困难原因(大类)
						if (itemDictMap.get(AccountType.POORPEOPLE) != null) {
							itemDicts = itemDictMap.get(AccountType.POORPEOPLE);
						} else {
							itemDicts = propertyDictService
									.findPropertyDictByDomainName(PropertyTypes.POORBIGINFO);
							itemDictMap.put(AccountType.POORPEOPLE, itemDicts);
						}
					} else if (AccountType.STEADYWORK
							.equalsIgnoreCase(accountType)) {
						reportCountService = reportCountServices
								.get("steadyWorkService");
						// 稳定工作台账的诉求类别（大类）
						if (itemDictMap.get(AccountType.STEADYWORK) != null) {
							itemDicts = itemDictMap.get(AccountType.STEADYWORK);
						} else {
							itemDicts = propertyDictService
									.findPropertyDictByDomainName(PropertyTypes.ASPIRATIONTYPE);

							itemDictMap.put(AccountType.STEADYWORK, itemDicts);
						}
					}
					constructVillageMouldContent(accountReport,
							reportCountService, itemDicts);
					accountReportTemps.add(accountReport);
				}
			}

			accountReportDao.batchAddDate(accountReportTemps);
		} catch (Exception e) {
			throw new ServiceValidationException("新增社区级三本台账报表错误", e);
		}
	}

	@Override
	public Integer countDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month, int orgLevelInternalId,
			int orgTypeInternalId) {
		List<Long> orgIdList = findOrgIdsForMode(idModList,taskParameter,orgLevelInternalId,orgTypeInternalId);
		return accountReportDao.countDirtyDataByModel(orgIdList,year,month);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void deleteDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month, int orgLevelInternalId,
			int orgTypeInternalId) {
		List<Long> orgIdList = findOrgIdsForMode(idModList,taskParameter,orgLevelInternalId,orgTypeInternalId);
		accountReportDao.deleteDirtyDataByModel(orgIdList,year,month);
	}
	
	private List<Long> findOrgIdsForMode(List<Long> idModList,
			String taskParameter,int orgLevelInternalId,int orgTypeInternalId){
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setOrgTypeId(getOrgDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_TYPE, orgTypeInternalId));
		organizationVo.setOrgLevelId(getOrgDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_LEVEL, orgLevelInternalId));
		organizationVo.setOrgIdsList(ParametersConvertUtil.convertToListString(idModList));
		taskParameter = StringUtil.isStringAvaliable(taskParameter) ? taskParameter: "10";
		try {
			Integer.parseInt(taskParameter);
		} catch (Exception e) {
			taskParameter = "10";
		}
		organizationVo.setTaskParameter(taskParameter);
		return organizationDubboService.findOrgIdsBySearchVo(organizationVo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchTownAccountReportForJob(List<AccountReport> accountReports) {
		if (accountReports == null || accountReports.size() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			List<AccountReport> accountReportTemps = new ArrayList<AccountReport>();
			ReportCount reportCountService = null;
			List<PropertyDict> itemDicts = null;
			for (AccountReport accountReport : accountReports) {
				String accountType = accountReport.getAccountType();
				if (AccountType.PEOPLEASPIRATION.equalsIgnoreCase(accountType)) {
					reportCountService = reportCountServices
							.get("peopleAspirationService");
					// 民生诉求的项目类别(大类)
					if (itemDictMap.get(AccountType.PEOPLEASPIRATION) != null) {
						itemDicts = itemDictMap
								.get(AccountType.PEOPLEASPIRATION);
					} else {
						itemDicts = propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.ITEM_CATEGORY);
						itemDictMap
								.put(AccountType.PEOPLEASPIRATION, itemDicts);
					}
				} else if (AccountType.POORPEOPLE.equalsIgnoreCase(accountType)) {
					reportCountService = reportCountServices
							.get("poorPeopleService");
					// 困难群众的困难原因(大类)
					if (itemDictMap.get(AccountType.POORPEOPLE) != null) {
						itemDicts = itemDictMap.get(AccountType.POORPEOPLE);
					} else {
						itemDicts = propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.POORBIGINFO);
						itemDictMap.put(AccountType.POORPEOPLE, itemDicts);
					}
				} else if (AccountType.STEADYWORK.equalsIgnoreCase(accountType)) {
					reportCountService = reportCountServices
							.get("steadyWorkService");
					// 稳定工作台账的诉求类别（大类）
					if (itemDictMap.get(AccountType.STEADYWORK) != null) {
						itemDicts = itemDictMap.get(AccountType.STEADYWORK);
					} else {
						itemDicts = propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.ASPIRATIONTYPE);

						itemDictMap.put(AccountType.STEADYWORK, itemDicts);
					}
				}
				if (accountReport.getReportType() == AccountType.TOWN_ONE_REPORT_TYPE) {
					constructTownOneContent(accountReport, reportCountService,
							itemDicts);
				} else if (accountReport.getReportType() == AccountType.TOWN_TOW_REPORT_TYPE) {
					constructTownTwoContent(accountReport, reportCountService,
							itemDicts);
				}
				if (StringUtil.isStringAvaliable(accountReport.getContent())) {
					accountReportTemps.add(accountReport);
				}
			}
			accountReportDao.batchAddDate(accountReportTemps);

		} catch (Exception e) {
			throw new ServiceValidationException("新增乡镇级三本台账报表错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void batchDistrictAccountReportForJob(
			List<AccountReport> accountReports) {
		if (accountReports == null || accountReports.size() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			List<AccountReport> accountReportTemps = new ArrayList<AccountReport>();
			ReportCount reportCountService = null;
			List<PropertyDict> itemDicts = null;
			for (AccountReport accountReport : accountReports) {
				String accountType = accountReport.getAccountType();
				if (AccountType.PEOPLEASPIRATION.equalsIgnoreCase(accountType)) {
					reportCountService = reportCountServices
							.get("peopleAspirationService");
					// 民生诉求的项目类别(大类)
					if (itemDictMap.get(AccountType.PEOPLEASPIRATION) != null) {
						itemDicts = itemDictMap
								.get(AccountType.PEOPLEASPIRATION);
					} else {
						itemDicts = propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.ITEM_CATEGORY);
						itemDictMap
								.put(AccountType.PEOPLEASPIRATION, itemDicts);
					}
				} else if (AccountType.POORPEOPLE.equalsIgnoreCase(accountType)) {
					reportCountService = reportCountServices
							.get("poorPeopleService");
					// 困难群众的困难原因(大类)
					if (itemDictMap.get(AccountType.POORPEOPLE) != null) {
						itemDicts = itemDictMap.get(AccountType.POORPEOPLE);
					} else {
						itemDicts = propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.POORBIGINFO);
						itemDictMap.put(AccountType.POORPEOPLE, itemDicts);
					}
				} else if (AccountType.STEADYWORK.equalsIgnoreCase(accountType)) {
					reportCountService = reportCountServices
							.get("steadyWorkService");
					// 稳定工作台账的诉求类别（大类）
					if (itemDictMap.get(AccountType.STEADYWORK) != null) {
						itemDicts = itemDictMap.get(AccountType.STEADYWORK);
					} else {
						itemDicts = propertyDictService
								.findPropertyDictByDomainName(PropertyTypes.ASPIRATIONTYPE);

						itemDictMap.put(AccountType.STEADYWORK, itemDicts);
					}
				}
				List<AccountReport> accountReportList = accountReportDao
						.findAccountReport(accountReport.getOrganization()
								.getId(), accountReport.getReportYear(),
								accountReport.getReportMonth(), accountReport
										.getAccountType(), accountReport
										.getReportType());

				if (accountReportList != null && accountReportList.size() > 0) {
					AccountReport accountReportTemp = accountReportList.get(0);
					if (accountReportTemp != null
							&& StringUtil.isStringAvaliable(accountReportTemp
									.getContent())) {
						accountReport = accountReportTemp;
						accountReportDao
								.deleteAccountReportByOrgIdAndTimeAndType(
										accountReport.getOrganization().getId(),
										accountReport.getReportYear(),
										accountReport.getReportMonth(),
										accountReport.getAccountType(),
										accountReport.getReportType());
					}
				}
				/** 县区级报表 */
				if (accountReport.getReportType() == AccountType.DISTRICT_REPORT_TYPE) {
					constructDistrictContent(accountReport, reportCountService,
							itemDicts);
					/** 西昌1报表 */
				} else if (accountReport.getReportType() == AccountType.XICHANG_ONE_REPORT_TYPE) {
					constructxXiChangOneContent(accountReport,
							reportCountService, itemDicts);
					/** 西昌2报表 */
				} else if (accountReport.getReportType() == AccountType.XICHANG_TWO_REPORT_TYPE) {
					constructxXiChangTwoContent(accountReport,
							reportCountService, itemDicts);
					/** 西昌3报表 */
				} else if (accountReport.getReportType() == AccountType.XICHANG_THREE_REPORT_TYPE) {
					constructxXiChangThreeContent(accountReport,
							reportCountService, itemDicts);
				}
				if (StringUtil.isStringAvaliable(accountReport.getContent())) {
					accountReportTemps.add(accountReport);
				}
			}
			accountReportDao.batchAddDate(accountReportTemps);

		} catch (Exception e) {
			throw new ServiceValidationException("新增县区级三本台账报表错误", e);
		}
	}

	// 这里查询组织机构的类型，一般情况下只会有一种情况
	private Long getOrgDictByDomainNameAndInternalId(String domainName,
			int internalId) {
		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(domainName,
						internalId);
		if (propertyDictList != null && propertyDictList.size() > 0) {
			return propertyDictList.get(0).getId();
		}
		return null;
	}
}
