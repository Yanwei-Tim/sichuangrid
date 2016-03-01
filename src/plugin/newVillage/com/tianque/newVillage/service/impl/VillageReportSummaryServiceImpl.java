package com.tianque.newVillage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
import com.tianque.newVillage.dao.VillageReportSummaryDao;
import com.tianque.newVillage.domain.BasicYearInfo;
import com.tianque.newVillage.domain.CapitalInvested;
import com.tianque.newVillage.domain.CommonServiceInfo;
import com.tianque.newVillage.domain.EnvironmentalReform;
import com.tianque.newVillage.domain.FarmerPerIncomeInfo;
import com.tianque.newVillage.domain.IndustryDevelopment;
import com.tianque.newVillage.domain.Infrastructure;
import com.tianque.newVillage.domain.NewVillage;
import com.tianque.newVillage.domain.NewVillageReport;
import com.tianque.newVillage.domain.OrganizationConstruction;
import com.tianque.newVillage.domain.ReportDataSummary;
import com.tianque.newVillage.service.NewVillageBasicService;
import com.tianque.newVillage.service.VillageReportSummaryService;
import com.tianque.newVillage.util.NewVillageInfoUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

/***
 * 数据上报
 * 
 * @author N-223
 * 
 */
@Service("villageReportSummaryService")
@Transactional
public class VillageReportSummaryServiceImpl implements
		VillageReportSummaryService {

	@Autowired
	private VillageReportSummaryDao villageReportSummaryDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private NewVillageBasicService newVillageBasicService;

	/**
	 * 统计判断
	 */
	private static final Integer isCount = 0;
	/**
	 * 审核查询判断
	 */
	private static final Integer isQuery = 1;

	@Override
	public void addVillageReportSummary(ReportDataSummary reportDataSummary) {
		villageReportSummaryDao.addVillageReportSummary(reportDataSummary);
	}

	@Override
	public void updateReportSummaryReportStatus(List<Long> ids,
			Integer reportStatus, String idsStr, Integer isPlaning) {
		// 将ids的数据查询返回一个list
		List<ReportDataSummary> list = villageReportSummaryDao
				.findReportSummaryByIds(ids);
		ReportDataSummary sumReportDataSummary = new ReportDataSummary();
		// 新增一条上报数据，上报单位为本层级。查看单位为父层级
		Organization currentOrg = organizationDubboService
				.getSimpleOrgById(list.get(0).getOrganization().getId());
		Organization parentOrg = organizationDubboService
				.getSimpleOrgById(currentOrg.getParentOrg().getId());
		sumReportDataSummary.setReportOrg(currentOrg);
		sumReportDataSummary.setOrganization(parentOrg);
		sumReportDataSummary.setReportIds(idsStr);
		sumReportDataSummary.setIsPlaning(isPlaning);
		Integer year = list.get(0).getYear();
		Integer quarter = list.get(0).getQuarter();
		sumReportDataSummary.setYear(year);
		sumReportDataSummary.setQuarter(quarter);
		if (checkReportSummary(parentOrg.getId(), year, quarter, isPlaning) > 0) {
			if (quarter != null && isPlaning == 0) {
				throw new ServiceValidationException("上报失败,同年同季度只能上报一次",
						new Exception());
			} else {
				throw new ServiceValidationException("上报失败,同年只能上报一次年度任务规划",
						new Exception());
			}
		}
		sumReportDataSummary
				.setReportStatus(ComprehensiveInfoConstant.REPORT_NOT);
		sumReportDataSummary
				.setCheckStatus(ComprehensiveInfoConstant.CHECK_NOT);
		try {

			if (list != null && list.size() != 0) {

				NewVillage newVillageForCurrent = new NewVillage();
				BasicYearInfo basicYearInfo = new BasicYearInfo();
				CommonServiceInfo commonServiceInfo = new CommonServiceInfo();
				EnvironmentalReform environmentalReform = new EnvironmentalReform();
				IndustryDevelopment industryDevelopment = new IndustryDevelopment();
				Infrastructure infrastructure = new Infrastructure();
				OrganizationConstruction organizationConstruction = new OrganizationConstruction();

				CapitalInvested capitalInvested = new CapitalInvested();
				FarmerPerIncomeInfo farmerPerIncomeInfo = new FarmerPerIncomeInfo();


				// 遍历list，并且将里面各个类别的数据累加
				for (ReportDataSummary reportDataSummary : list) {
					JSONObject newVillageJson = JSONObject
							.fromObject(reportDataSummary.getNewVillageData());
					reportDataSummary
							.setNewVillage((NewVillage) (newVillageJson.toBean(
									newVillageJson, NewVillage.class)));
					if (reportDataSummary.getNewVillage() != null) {
						newVillageForCurrent = (NewVillage) NewVillageInfoUtil
								.dealInfo(newVillageForCurrent,
										reportDataSummary.getNewVillage());
					}

					JSONObject basicYearInfoJson = JSONObject
							.fromObject(reportDataSummary
									.getBasicYearInfoData());
					reportDataSummary
							.setBasicYearInfo((BasicYearInfo) (basicYearInfoJson
									.toBean(basicYearInfoJson,
											BasicYearInfo.class)));
					if (reportDataSummary.getBasicYearInfo() != null) {
						basicYearInfo = (BasicYearInfo) NewVillageInfoUtil
								.dealInfo(basicYearInfo,
										reportDataSummary.getBasicYearInfo());

					}

					JSONObject commonServiceJson = JSONObject
							.fromObject(reportDataSummary
									.getCommonServiceInfoData());
					reportDataSummary
							.setCommonServiceInfo((CommonServiceInfo) (commonServiceJson
									.toBean(commonServiceJson,
											CommonServiceInfo.class)));
					if (reportDataSummary.getCommonServiceInfo() != null) {
						commonServiceInfo = (CommonServiceInfo) NewVillageInfoUtil
								.dealInfo(commonServiceInfo, reportDataSummary
										.getCommonServiceInfo());

					}

					JSONObject environmentalReformJson = JSONObject
							.fromObject(reportDataSummary
									.getEnvironmentalReformData());
					reportDataSummary
							.setEnvironmentalReform((EnvironmentalReform) (environmentalReformJson
									.toBean(environmentalReformJson,
											EnvironmentalReform.class)));
					if (reportDataSummary.getEnvironmentalReform() != null) {
						environmentalReform = (EnvironmentalReform) NewVillageInfoUtil
								.dealInfo(environmentalReform,
										reportDataSummary
												.getEnvironmentalReform());
					}

					JSONObject industryDevelopmentJson = JSONObject
							.fromObject(reportDataSummary
									.getIndustryDevelopmentData());
					reportDataSummary
							.setIndustryDevelopment((IndustryDevelopment) (industryDevelopmentJson
									.toBean(industryDevelopmentJson,
											IndustryDevelopment.class)));
					if (reportDataSummary.getIndustryDevelopment() != null) {
						industryDevelopment = (IndustryDevelopment) NewVillageInfoUtil
								.dealInfo(industryDevelopment,
										reportDataSummary
												.getIndustryDevelopment());
					}

					JSONObject infrastructureVoJson = JSONObject
							.fromObject(reportDataSummary
									.getInfrastructureData());
					reportDataSummary
							.setInfrastructure((Infrastructure) (infrastructureVoJson
									.toBean(infrastructureVoJson,
											Infrastructure.class)));
					if (reportDataSummary.getInfrastructure() != null) {
						infrastructure = (Infrastructure) NewVillageInfoUtil
								.dealInfo(infrastructure,
										reportDataSummary.getInfrastructure());
					}

					JSONObject organizationConstructionJson = JSONObject
							.fromObject(reportDataSummary
									.getOrganizationConstructionData());
					reportDataSummary
							.setOrganizationConstruction((OrganizationConstruction) (organizationConstructionJson
									.toBean(organizationConstructionJson,
											OrganizationConstruction.class)));
					if (reportDataSummary.getOrganizationConstruction() != null) {
						organizationConstruction = (OrganizationConstruction) NewVillageInfoUtil
								.dealInfo(organizationConstruction,
										reportDataSummary
												.getOrganizationConstruction());
					}

					JSONObject capitalInvestedJson = JSONObject
							.fromObject(reportDataSummary
									.getCapitalInvestedData());
					reportDataSummary
							.setCapitalInvested((CapitalInvested) (capitalInvestedJson
									.toBean(capitalInvestedJson,
											CapitalInvested.class)));
					if (reportDataSummary.getCapitalInvested() != null) {
						capitalInvested = (CapitalInvested) NewVillageInfoUtil
								.dealInfo(capitalInvested,
										reportDataSummary.getCapitalInvested());
					}

					JSONObject farmerPerIncomeInfoJson = JSONObject
							.fromObject(reportDataSummary
									.getFarmerPerIncomeInfoData());
					reportDataSummary
							.setFarmerPerIncomeInfo((FarmerPerIncomeInfo) (farmerPerIncomeInfoJson
									.toBean(farmerPerIncomeInfoJson,
											FarmerPerIncomeInfo.class)));
					if (reportDataSummary.getFarmerPerIncomeInfo() != null) {
						farmerPerIncomeInfo = (FarmerPerIncomeInfo) NewVillageInfoUtil
								.dealInfo(farmerPerIncomeInfo,
										reportDataSummary
												.getFarmerPerIncomeInfo());
					}
					// 更改上报数据的上报状态
					villageReportSummaryDao.updateReportSummaryReportStatus(
							reportDataSummary.getId(), reportStatus,
							CalendarUtil.now(), ThreadVariable.getUser()
									.getUserName());
				}
				int listSize = list.size(); // 该组织下有多少子组织信息

				if (newVillageForCurrent != null) {
					// 新村建设
					JSONObject newVillageJson = JSONObject
							.fromObject(newVillageForCurrent);
					sumReportDataSummary.setNewVillageData(newVillageJson
							.toString());
				}

				if (basicYearInfo != null) {
					// 基本信息
					JSONObject basicYearInfoJson = JSONObject
							.fromObject(basicYearInfo);
					sumReportDataSummary.setBasicYearInfoData(basicYearInfoJson
							.toString());
				}

				if (commonServiceInfo != null) {
					// 公共服务
					JSONObject commonServiceInfoJson = JSONObject
							.fromObject(commonServiceInfo);
					sumReportDataSummary
							.setCommonServiceInfoData(commonServiceInfoJson
									.toString());
				}

				if (environmentalReform != null) {
					// 环境改造
					JSONObject environmentalReformJson = JSONObject
							.fromObject(environmentalReform);
					sumReportDataSummary
							.setEnvironmentalReformData(environmentalReformJson
									.toString());
				}

				if (industryDevelopment != null) {
					// 产业发展
					JSONObject industryDevelopmentJson = JSONObject
							.fromObject(industryDevelopment);
					sumReportDataSummary
							.setIndustryDevelopmentData(industryDevelopmentJson
									.toString());
				}

				if (infrastructure != null) {
					// 基础设施
					JSONObject infrastructureJson = JSONObject
							.fromObject(infrastructure);
					sumReportDataSummary
							.setInfrastructureData(infrastructureJson
									.toString());
				}

				if (organizationConstruction != null) {
					// 组织机构两个满意度需要平均
					if (organizationConstruction.getThreeSatisfaction() != null
							&& listSize != 0) {
						organizationConstruction
								.setThreeSatisfaction(organizationConstruction
										.getThreeSatisfaction() / listSize);
					}
					if (organizationConstruction.getSurveySatisfaction() != null
							&& listSize != 0) {
						organizationConstruction
								.setSurveySatisfaction(organizationConstruction
										.getSurveySatisfaction() / listSize);
					}
					JSONObject organizationConstructionJson = JSONObject
							.fromObject(organizationConstruction);
					sumReportDataSummary
							.setOrganizationConstructionData(organizationConstructionJson
									.toString());
				}

				if (capitalInvested != null) {
					// 资金投入
					JSONObject capitalInvestedJson = JSONObject
							.fromObject(capitalInvested);
					sumReportDataSummary
							.setCapitalInvestedData(capitalInvestedJson
									.toString());
				}

				if (farmerPerIncomeInfo != null) {
					// 农民人均可支配收入
					// 处理满意度和人均收入:该两项应该为相加之后再平均
					if (farmerPerIncomeInfo.getFarmerPerIncome() != null
							&& listSize != 0) {
						farmerPerIncomeInfo
								.setFarmerPerIncome(farmerPerIncomeInfo
										.getFarmerPerIncome() / listSize);
					}
					JSONObject farmerPerIncomeInfoJson = JSONObject
							.fromObject(farmerPerIncomeInfo);
					sumReportDataSummary
							.setFarmerPerIncomeInfoData(farmerPerIncomeInfoJson
									.toString());
				}

				// 新增数据
				addVillageReportSummary(sumReportDataSummary);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("上报失败", e);
		}
	}

	@Override
	public void updateReportSummaryCheckStatus(String[] ids, Integer checkStatus) {
		// 将ids的数据查询返回一个list
		List<Long> idLists = new ArrayList<Long>();
		for (String idStr : ids) {
			idLists.add(Long.parseLong(idStr));
		}
		List<ReportDataSummary> list = villageReportSummaryDao
				.findReportSummaryByIds(idLists);
		Organization currentOrg = organizationDubboService
				.getSimpleOrgById(list.get(0).getOrganization().getId());
		Organization parentOrg = organizationDubboService
				.getSimpleOrgById(currentOrg.getParentOrg().getId());
		Integer year = list.get(0).getYear();
		Integer quarter = list.get(0).getQuarter();
		Integer isPlaning = list.get(0).getIsPlaning();

		for (String id : ids) {
			dealForReportData(Long.parseLong(id), checkStatus, 1);
			if (checkStatus == ComprehensiveInfoConstant.CHECK_BACK) {
				// 审核不通过 删除该数据.删除之前判断该条数据是否已经上报到更高一级,如果是则不能删除
				if (checkReportSummary(parentOrg.getId(), year, quarter,
						isPlaning) > 0) {
					if (quarter != null && isPlaning == 0) {
						throw new ServiceValidationException(
								"删除失败,该年该季度信息已经上报", new Exception());
					} else {
						throw new ServiceValidationException(
								"删除失败,该年的年度任务规划已经上报", new Exception());
					}
				}
				villageReportSummaryDao.deleteReportSummaryById(Long
						.parseLong(id));
			} else {
				villageReportSummaryDao.updateReportSummaryCheckStatus(
						Long.parseLong(id), checkStatus, CalendarUtil.now(),
						ThreadVariable.getUser().getUserName());
			}
		}

	}

	@Override
	public void deleteReportSummaryById(String[] ids) {
		if (ids == null) {
			throw new BusinessValidationException("删除上报数据失败");
		}
	}

	/**
	 * 检查数据唯一
	 */
	private Integer checkReportSummary(Long orgId, Integer year,
			Integer quarter, Integer isPlaning) {
		return villageReportSummaryDao.checkReportSummary(orgId, year, quarter,
				isPlaning);
	}

	/***
	 * 业务处理
	 */
	private void dealForReportData(Long basicId, Integer operateStatus,
			Integer operateType) {
		// 根据ID查询得出上报数据
		ReportDataSummary reportData = villageReportSummaryDao
				.getReportSummaryById(basicId);
		if (reportData != null) {
			// 判断上报的层级是否是村层级
			Organization reportOrg = organizationDubboService
					.getFullOrgById(reportData.getReportOrg().getId());
			if (OrganizationLevel.VILLAGE == reportOrg.getOrgLevel()
					.getInternalId()) {
				if (operateType == 1) {// 审核
					newVillageBasicService.checkNewVillageBasic(Long
							.parseLong(reportData.getReportIds()),
							operateStatus, CalendarUtil.now(), ThreadVariable
									.getUser().getUserName());
				} else {// 上报
						// 村社区层级需要将村社区层级的上报数据状态更改
					newVillageBasicService.reportNewVillageBasic(Long
							.parseLong(reportData.getReportIds()),
							operateStatus, CalendarUtil.now(), ThreadVariable
									.getUser().getUserName());
				}

			} else {// 非村社区层级，则直接修改该数据
				String reportIds = reportData.getReportIds();
				if (StringUtil.isStringAvaliable(reportIds)) {
					for (String id : reportIds.split(",")) {
						if (operateType == 1) {// 审核
							villageReportSummaryDao
									.updateReportSummaryCheckStatus(Long
											.parseLong(id), operateStatus,
											CalendarUtil.now(), ThreadVariable
													.getUser().getUserName());
						} else {// 上报
							villageReportSummaryDao
									.updateReportSummaryReportStatus(Long
											.parseLong(id), operateStatus,
											CalendarUtil.now(), ThreadVariable
													.getUser().getUserName());
						}
					}
				}
			}
		}
	}

	@Override
	public PageInfo<ReportDataSummary> findReportSummaryForList(Long orgId,
			Integer year, Integer quarter, Integer isPlaning, Integer page,
			Integer rows, String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("列表查询失败");
		}
		return villageReportSummaryDao.findReportSummaryForList(orgId, year,
				quarter, isPlaning, page, rows, sidx, sord);
	}

	@Override
	public List<ReportDataSummary> findReportSummaryByIds(List<Long> idList) {
		try {
			List<ReportDataSummary> list = villageReportSummaryDao
					.findReportSummaryByIds(idList);
			list = utilReportSummaryForList(list, isQuery);
			return list;
		} catch (Exception e) {
			throw new ServiceValidationException("审核失败", e);
		}
	}

	@Override
	public List<NewVillageReport> countReportSummaryForList(Long orgId,
			Integer year, Integer quarter) {
		if (orgId == null) {
			throw new BusinessValidationException("报表查询失败");
		}
		try {
			// 查询上报数据
			List<ReportDataSummary> reportList = villageReportSummaryDao
					.countReportSummaryForList(orgId, year, null,
							ComprehensiveInfoConstant.IS_REPORT_KEY);
			if (reportList == null || reportList.size() == 0) {
				reportList = new ArrayList<ReportDataSummary>();
			}
			// 查询规划数据
			List<ReportDataSummary> planingList = villageReportSummaryDao
					.countReportSummaryForList(orgId, year, null,
							ComprehensiveInfoConstant.IS_PLANING);
			if (planingList == null || planingList.size() == 0) {
				planingList = new ArrayList<ReportDataSummary>();
			}
			// 查询当前年份之前的数据
			List<ReportDataSummary> beforList = villageReportSummaryDao
					.findReportSummaryByYear(orgId, year,
							ComprehensiveInfoConstant.IS_REPORT_KEY, false);
			if (beforList == null || beforList.size() == 0) {
				beforList = new ArrayList<ReportDataSummary>();
			}
			// 查询累计数(当前年以前包括当前年数据)
			List<ReportDataSummary> allList = villageReportSummaryDao
					.findReportSummaryByYear(orgId, year,
							ComprehensiveInfoConstant.IS_REPORT_KEY, true);
			if (allList == null || allList.size() == 0) {
				allList = new ArrayList<ReportDataSummary>();
			}
			return dealNewVillageReportData(reportList, planingList, beforList,
					allList, orgId, year);
		} catch (Exception e) {
			throw new ServiceValidationException("报表查询失败", e);
		}
	}

	public ReportDataSummary dealNewVillageReport(List<ReportDataSummary> list,
			String displayName, Organization organization, Integer year) {
		for (ReportDataSummary planingReport : list) {
			if (planingReport.getReportOrg() != null
					&& organization.getId().equals(
							planingReport.getReportOrg().getId())) {
				planingReport = dealSummaryJson(planingReport);
				planingReport.setStatisticsDimensions(year + displayName);
				return planingReport;
			}
		}
		ReportDataSummary report = new ReportDataSummary();
		report.setStatisticsDimensions(year + displayName);
		return report;
	}

	/***
	 * 将json数据转换成实体
	 * 
	 * @param reportList
	 * @param planingList
	 * @param beforList
	 * @param allList
	 * @param orgId
	 * @param year
	 * @return
	 */
	private ReportDataSummary dealSummaryJson(
			ReportDataSummary reportDataSummary) {
		BasicYearInfo basicYearInfo = new BasicYearInfo();
		NewVillage newVillageForCurrent = new NewVillage();
		CommonServiceInfo commonServiceInfo = new CommonServiceInfo();
		EnvironmentalReform environmentalReform = new EnvironmentalReform();
		IndustryDevelopment industryDevelopment = new IndustryDevelopment();
		Infrastructure infrastructure = new Infrastructure();
		OrganizationConstruction organizationConstruction = new OrganizationConstruction();
		CapitalInvested capitalInvested = new CapitalInvested();
		FarmerPerIncomeInfo farmerPerIncomeInfo = new FarmerPerIncomeInfo();

		JSONObject basicYearInfoJson = JSONObject.fromObject(reportDataSummary
				.getBasicYearInfoData());
		basicYearInfo = (BasicYearInfo) (basicYearInfoJson.toBean(
				basicYearInfoJson, BasicYearInfo.class));

		JSONObject newVillageJson = JSONObject.fromObject(reportDataSummary
				.getNewVillageData());
		newVillageForCurrent = (NewVillage) (newVillageJson.toBean(
				newVillageJson, NewVillage.class));

		JSONObject commonServiceJson = JSONObject.fromObject(reportDataSummary
				.getCommonServiceInfoData());
		commonServiceInfo = (CommonServiceInfo) (commonServiceJson.toBean(
				commonServiceJson, CommonServiceInfo.class));

		JSONObject environmentalReformJson = JSONObject
				.fromObject(reportDataSummary.getEnvironmentalReformData());

		environmentalReform = (EnvironmentalReform) (environmentalReformJson
				.toBean(environmentalReformJson, EnvironmentalReform.class));

		JSONObject industryDevelopmentJson = JSONObject
				.fromObject(reportDataSummary.getIndustryDevelopmentData());

		industryDevelopment = (IndustryDevelopment) (industryDevelopmentJson
				.toBean(industryDevelopmentJson, IndustryDevelopment.class));

		JSONObject infrastructureVoJson = JSONObject
				.fromObject(reportDataSummary.getInfrastructureData());

		infrastructure = (Infrastructure) (infrastructureVoJson.toBean(
				infrastructureVoJson, Infrastructure.class));

		JSONObject organizationConstructionJson = JSONObject
				.fromObject(reportDataSummary.getOrganizationConstructionData());

		organizationConstruction = (OrganizationConstruction) (organizationConstructionJson
				.toBean(organizationConstructionJson,
						OrganizationConstruction.class));

		// 资金投资
		JSONObject capitalInvestedJson = JSONObject
				.fromObject(reportDataSummary.getCapitalInvestedData());
		capitalInvested = (CapitalInvested) (capitalInvestedJson.toBean(
				capitalInvestedJson, CapitalInvested.class));
		// 农民可支配收入
		JSONObject farmerPerIncomeInfoJson = JSONObject
				.fromObject(reportDataSummary.getFarmerPerIncomeInfoData());

		farmerPerIncomeInfo = (FarmerPerIncomeInfo) (farmerPerIncomeInfoJson
				.toBean(farmerPerIncomeInfoJson, FarmerPerIncomeInfo.class));

		reportDataSummary.setBasicYearInfo(basicYearInfo);
		reportDataSummary.setNewVillage(newVillageForCurrent);
		reportDataSummary.setCommonServiceInfo(commonServiceInfo);
		reportDataSummary.setEnvironmentalReform(environmentalReform);
		reportDataSummary.setIndustryDevelopment(industryDevelopment);
		reportDataSummary.setInfrastructure(infrastructure);
		reportDataSummary.setOrganizationConstruction(organizationConstruction);
		reportDataSummary.setCapitalInvested(capitalInvested);
		reportDataSummary.setFarmerPerIncomeInfo(farmerPerIncomeInfo);
		return reportDataSummary;
	}

	public List<NewVillageReport> dealNewVillageReportData(
			List<ReportDataSummary> reportList,
			List<ReportDataSummary> planingList,
			List<ReportDataSummary> beforList, List<ReportDataSummary> allList,
			Long orgId, Integer year) {
		List<Organization> orgList = organizationDubboService
				.findOrganizationsByParentId(orgId);
		List<NewVillageReport> endList = new ArrayList<NewVillageReport>();
		try {
			beforList = utilReportSummaryForList(beforList, 0);
			allList = utilReportSummaryForList(allList, 0);
			if (orgList != null && orgList.size() != 0) {
				for (Organization org : orgList) {
					List<ReportDataSummary> deledList = new ArrayList<ReportDataSummary>();
					NewVillageReport report = new NewVillageReport();
					report.setOrganization(org);
					// 处理基期数
					//deledList.add(dealNewVillageReport(beforList, "年基期数", org,
					//		year));
					deledList.add(dealSumListToOneReportDataSummary(beforList,
							"年基期数", org, year));
					// 处理规划数据
					deledList.add(dealNewVillageReport(planingList, "年规划数",
							org, year));
					// 处理完成数
					deledList.add(dealSumListToOneReportDataSummary(reportList,
							"年完成数", org, year));
					// 处理累计数
					//deledList.add(dealNewVillageReport(allList, "年累计数", org,
					//		year));
					deledList.add(dealSumListToOneReportDataSummary(allList,
							"年累计数", org, year));
					report.setList(deledList);
					endList.add(report);
				}
			}
			return endList;
		} catch (Exception e) {
			throw new ServiceValidationException("报表查询错误", e);
		}
	}

	/**
	 * 反射调用设值工具类
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<ReportDataSummary> utilReportSummaryForList(
			List<ReportDataSummary> list, Integer checkType) throws Exception {
		ReportDataSummary sumReportDataSummary = new ReportDataSummary();
		if (list != null && list.size() != 0) {
			Organization currentOrg = organizationDubboService
					.getFullOrgById(list.get(0).getOrganization().getId());
			if (checkType == isCount) {
				currentOrg.setOrgName("合计");
			}
			sumReportDataSummary.setReportOrg(currentOrg);
			NewVillage newVillageForCurrent = new NewVillage();
			BasicYearInfo basicYearInfo = new BasicYearInfo();
			CommonServiceInfo commonServiceInfo = new CommonServiceInfo();
			EnvironmentalReform environmentalReform = new EnvironmentalReform();
			IndustryDevelopment industryDevelopment = new IndustryDevelopment();
			Infrastructure infrastructure = new Infrastructure();
			OrganizationConstruction organizationConstruction = new OrganizationConstruction();

			CapitalInvested capitalInvested = new CapitalInvested();
			FarmerPerIncomeInfo farmerPerIncomeInfo = new FarmerPerIncomeInfo();

			for (ReportDataSummary reportDataSummary : list) {
				JSONObject newVillageJson = JSONObject
						.fromObject(reportDataSummary.getNewVillageData());

				NewVillage newVillage = (NewVillage) (newVillageJson.toBean(
						newVillageJson, NewVillage.class));
				reportDataSummary
						.setNewVillage(newVillage == null ? new NewVillage()
								: newVillage);
				if (reportDataSummary.getNewVillage() != null) {
					newVillageForCurrent = (NewVillage) NewVillageInfoUtil
							.dealInfo(newVillageForCurrent,
									reportDataSummary.getNewVillage());
				}

				JSONObject basicYearInfoJson = JSONObject
						.fromObject(reportDataSummary.getBasicYearInfoData());
				BasicYearInfo byi = (BasicYearInfo) (basicYearInfoJson.toBean(
						basicYearInfoJson, BasicYearInfo.class));
				reportDataSummary
						.setBasicYearInfo(byi == null ? new BasicYearInfo()
								: byi);
				if (reportDataSummary.getBasicYearInfo() != null) {
					basicYearInfo = (BasicYearInfo) NewVillageInfoUtil
							.dealInfo(basicYearInfo,
									reportDataSummary.getBasicYearInfo());
				}

				JSONObject commonServiceJson = JSONObject
						.fromObject(reportDataSummary
								.getCommonServiceInfoData());
				CommonServiceInfo com = (CommonServiceInfo) (commonServiceJson
						.toBean(commonServiceJson, CommonServiceInfo.class));
				reportDataSummary
						.setCommonServiceInfo(com == null ? new CommonServiceInfo()
								: com);

				if (reportDataSummary.getCommonServiceInfo() != null) {
					commonServiceInfo = (CommonServiceInfo) NewVillageInfoUtil
							.dealInfo(commonServiceInfo,
									reportDataSummary.getCommonServiceInfo());
				}

				JSONObject environmentalReformJson = JSONObject
						.fromObject(reportDataSummary
								.getEnvironmentalReformData());

				EnvironmentalReform env = (EnvironmentalReform) (environmentalReformJson
						.toBean(environmentalReformJson,
								EnvironmentalReform.class));
				reportDataSummary
						.setEnvironmentalReform(env == null ? new EnvironmentalReform()
								: env);
				if (reportDataSummary.getEnvironmentalReform() != null) {
					environmentalReform = (EnvironmentalReform) NewVillageInfoUtil
							.dealInfo(environmentalReform,
									reportDataSummary.getEnvironmentalReform());
				}

				JSONObject industryDevelopmentJson = JSONObject
						.fromObject(reportDataSummary
								.getIndustryDevelopmentData());

				IndustryDevelopment indus = (IndustryDevelopment) (industryDevelopmentJson
						.toBean(industryDevelopmentJson,
								IndustryDevelopment.class));
				reportDataSummary
						.setIndustryDevelopment(indus == null ? new IndustryDevelopment()
								: indus);
				if (reportDataSummary.getIndustryDevelopment() != null) {
					industryDevelopment = (IndustryDevelopment) NewVillageInfoUtil
							.dealInfo(industryDevelopment,
									reportDataSummary.getIndustryDevelopment());
				}

				JSONObject infrastructureVoJson = JSONObject
						.fromObject(reportDataSummary.getInfrastructureData());

				Infrastructure infrastructureTemp = (Infrastructure) (infrastructureVoJson
						.toBean(infrastructureVoJson, Infrastructure.class));
				reportDataSummary
						.setInfrastructure(infrastructureTemp == null ? new Infrastructure()
								: infrastructureTemp);
				if (reportDataSummary.getInfrastructure() != null) {
					infrastructure = (Infrastructure) NewVillageInfoUtil
							.dealInfo(infrastructure,
									reportDataSummary.getInfrastructure());
				}

				JSONObject organizationConstructionJson = JSONObject
						.fromObject(reportDataSummary
								.getOrganizationConstructionData());

				OrganizationConstruction construction = (OrganizationConstruction) (organizationConstructionJson
						.toBean(organizationConstructionJson,
								OrganizationConstruction.class));
				// 记录阵地建设数量 用于统计
				construction = (construction == null ? new OrganizationConstruction()
						: construction);
				reportDataSummary.setOrganizationConstruction(construction);
				if (reportDataSummary.getOrganizationConstruction() != null) {
					organizationConstruction = (OrganizationConstruction) NewVillageInfoUtil
							.dealInfo(organizationConstruction,
									reportDataSummary
											.getOrganizationConstruction());
				}

				// 资金投资
				JSONObject capitalInvestedJson = JSONObject
						.fromObject(reportDataSummary.getCapitalInvestedData());
				CapitalInvested newcapitalInvested = (CapitalInvested) (capitalInvestedJson
						.toBean(capitalInvestedJson, CapitalInvested.class));
				reportDataSummary
						.setCapitalInvested(newcapitalInvested == null ? new CapitalInvested()
								: newcapitalInvested);
				if (reportDataSummary.getCapitalInvested() != null) {
					capitalInvested = (CapitalInvested) NewVillageInfoUtil
							.dealInfo(capitalInvested,
									reportDataSummary.getCapitalInvested());
				}
				// 农民可支配收入
				JSONObject farmerPerIncomeInfoJson = JSONObject
						.fromObject(reportDataSummary
								.getFarmerPerIncomeInfoData());

				FarmerPerIncomeInfo newFarmerPerIncomeInfo = (FarmerPerIncomeInfo) (farmerPerIncomeInfoJson
						.toBean(farmerPerIncomeInfoJson,
								FarmerPerIncomeInfo.class));

				reportDataSummary
						.setFarmerPerIncomeInfo(newFarmerPerIncomeInfo == null ? new FarmerPerIncomeInfo()
								: newFarmerPerIncomeInfo);
				if (reportDataSummary.getFarmerPerIncomeInfo() != null) {
					farmerPerIncomeInfo = (FarmerPerIncomeInfo) NewVillageInfoUtil
							.dealInfo(farmerPerIncomeInfo,
									reportDataSummary.getFarmerPerIncomeInfo());
				}
			}
			int listSize = list.size(); // 该组织下有多少子组织信息
			sumReportDataSummary.setBasicYearInfo(basicYearInfo);
			sumReportDataSummary.setNewVillage(newVillageForCurrent);
			sumReportDataSummary.setCommonServiceInfo(commonServiceInfo);
			sumReportDataSummary.setEnvironmentalReform(environmentalReform);

			sumReportDataSummary.setIndustryDevelopment(industryDevelopment);
			sumReportDataSummary.setInfrastructure(infrastructure);

			Double threeSatisfaction = organizationConstruction
					.getThreeSatisfaction();
			if (threeSatisfaction != null) {
				organizationConstruction.setThreeSatisfaction(threeSatisfaction
						/ listSize);
			}
			Double surveySatisfaction = organizationConstruction
					.getSurveySatisfaction();
			if (surveySatisfaction != null) {
				organizationConstruction
						.setSurveySatisfaction(surveySatisfaction / listSize);
			}
			sumReportDataSummary
					.setOrganizationConstruction(organizationConstruction);

			sumReportDataSummary.setCapitalInvested(capitalInvested);

//			Double totalIncreaseRate = farmerPerIncomeInfo.getIncreaseRate();
//			if (totalIncreaseRate != null && totalIncreaseRate > 0) {
//				farmerPerIncomeInfo.setIncreaseRate(totalIncreaseRate
//						/ listSize);
//			}
			Double totalFarmerPerIncome = farmerPerIncomeInfo
					.getFarmerPerIncome();
			if (totalFarmerPerIncome != null && totalFarmerPerIncome > 0) {
				farmerPerIncomeInfo.setFarmerPerIncome(totalFarmerPerIncome
						/ listSize);
			}
			sumReportDataSummary.setFarmerPerIncomeInfo(farmerPerIncomeInfo);
		} else {
			Organization or = new Organization();
			or.setOrgName("合计");
			sumReportDataSummary.setReportOrg(or);
			sumReportDataSummary
					.setIndustryDevelopment(new IndustryDevelopment());
			sumReportDataSummary.setInfrastructure(new Infrastructure());
			sumReportDataSummary.setBasicYearInfo(new BasicYearInfo());
			sumReportDataSummary.setCommonServiceInfo(new CommonServiceInfo());
			sumReportDataSummary
					.setOrganizationConstruction(new OrganizationConstruction());
			sumReportDataSummary
					.setEnvironmentalReform(new EnvironmentalReform());
			sumReportDataSummary.setNewVillage(new NewVillage());

			sumReportDataSummary.setCapitalInvested(new CapitalInvested());
			sumReportDataSummary
					.setFarmerPerIncomeInfo(new FarmerPerIncomeInfo());
		}
		list.add(sumReportDataSummary);
		return list;
	}

	/**
	 * 处理报表中的基期数,同一个机构上报的不同年份数据累加
	 * @param beforeList
	 * @return
	 * @throws Exception 
	 */
	public ReportDataSummary dealSumListToOneReportDataSummary(
			List<ReportDataSummary> beforeList, String displayName,
			Organization org, Integer year) throws Exception {
		ReportDataSummary newReportDataSummary = new ReportDataSummary();
		//过滤分出相同机构的报表数据
		List<ReportDataSummary> newList = new ArrayList<ReportDataSummary>();
		for (ReportDataSummary reportDataSummary : beforeList) {
			Long reportOrgId = reportDataSummary.getReportOrg().getId();
			if (org != null && reportOrgId != null
					&& reportOrgId.equals(org.getId())) {
				newList.add(reportDataSummary);
			}
		}
		/*//处理多条数据为一条start
		//---BasicYearInfo
		List<BasicYearInfo> basicYearInfoList = new ArrayList<BasicYearInfo>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject basicYearInfoJson = JSONObject
					.fromObject(reportDataSummary.getBasicYearInfoData());
			BasicYearInfo basicYearInfo = (BasicYearInfo) (basicYearInfoJson
					.toBean(basicYearInfoJson, BasicYearInfo.class));
			if (basicYearInfo != null) {
				basicYearInfoList.add(basicYearInfo);
			}
		}
		BasicYearInfo basicYearInfo = NewVillageInfoUtil
				.dealList(basicYearInfoList);
		newReportDataSummary.setBasicYearInfo(basicYearInfo);*/
		//---NewVillage
		List<NewVillage> newVillageList = new ArrayList<NewVillage>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject newVillageJson = JSONObject.fromObject(reportDataSummary
					.getNewVillageData());
			NewVillage newVillage = (NewVillage) (newVillageJson.toBean(
					newVillageJson, NewVillage.class));
			if (newVillage != null) {
				newVillageList.add(newVillage);
			}
		}
		NewVillage newVillage = NewVillageInfoUtil.dealList(newVillageList);
		newReportDataSummary.setNewVillage(newVillage);
		//---CommonServiceInfo
		List<CommonServiceInfo> commonServiceInfoList = new ArrayList<CommonServiceInfo>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject commonServiceInfoJson = JSONObject
					.fromObject(reportDataSummary.getCommonServiceInfoData());
			CommonServiceInfo commonServiceInfo = (CommonServiceInfo) (commonServiceInfoJson
					.toBean(commonServiceInfoJson, CommonServiceInfo.class));
			if (commonServiceInfo != null) {
				commonServiceInfoList.add(commonServiceInfo);
			}
		}
		CommonServiceInfo commonServiceInfo = NewVillageInfoUtil
				.dealList(commonServiceInfoList);
		newReportDataSummary.setCommonServiceInfo(commonServiceInfo);
		//---EnvironmentalReform
		List<EnvironmentalReform> environmentalReformList = new ArrayList<EnvironmentalReform>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject environmentalReformJson = JSONObject
					.fromObject(reportDataSummary.getEnvironmentalReformData());
			EnvironmentalReform environmentalReform = (EnvironmentalReform) (environmentalReformJson
					.toBean(environmentalReformJson, EnvironmentalReform.class));
			if (environmentalReform != null) {
				environmentalReformList.add(environmentalReform);
			}
		}
		EnvironmentalReform environmentalReform = NewVillageInfoUtil
				.dealList(environmentalReformList);
		newReportDataSummary.setEnvironmentalReform(environmentalReform);
		//---IndustryDevelopment
		List<IndustryDevelopment> industryDevelopmentList = new ArrayList<IndustryDevelopment>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject industryDevelopmentJson = JSONObject
					.fromObject(reportDataSummary.getIndustryDevelopmentData());
			IndustryDevelopment industryDevelopment = (IndustryDevelopment) (industryDevelopmentJson
					.toBean(industryDevelopmentJson, IndustryDevelopment.class));
			if (industryDevelopment != null) {
				industryDevelopmentList.add(industryDevelopment);
			}
		}
		IndustryDevelopment industryDevelopment = NewVillageInfoUtil
				.dealList(industryDevelopmentList);
		newReportDataSummary.setIndustryDevelopment(industryDevelopment);
		//---Infrastructure
		List<Infrastructure> infrastructureList = new ArrayList<Infrastructure>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject infrastructureJson = JSONObject
					.fromObject(reportDataSummary.getInfrastructureData());
			Infrastructure infrastructure = (Infrastructure) (infrastructureJson
					.toBean(infrastructureJson, Infrastructure.class));
			if (infrastructure != null) {
				infrastructureList.add(infrastructure);
			}
		}
		Infrastructure infrastructure = NewVillageInfoUtil
				.dealList(infrastructureList);
		newReportDataSummary.setInfrastructure(infrastructure);
		//---OrganizationConstruction
		List<OrganizationConstruction> organizationConstructionList = new ArrayList<OrganizationConstruction>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject organizationConstructionJson = JSONObject
					.fromObject(reportDataSummary
							.getOrganizationConstructionData());
			OrganizationConstruction organizationConstruction = (OrganizationConstruction) (organizationConstructionJson
					.toBean(organizationConstructionJson,
							OrganizationConstruction.class));
			if (organizationConstruction != null) {
				organizationConstructionList.add(organizationConstruction);
			}
		}
		OrganizationConstruction organizationConstruction = NewVillageInfoUtil
				.dealList(organizationConstructionList);
		if (organizationConstructionList.size() > 1) {//处理满意度(需要相加后求平均值)
			BigDecimal surveySatisfaction = new BigDecimal(
					organizationConstruction.getSurveySatisfaction()
							/ organizationConstructionList.size());
			BigDecimal threeSatisfaction = new BigDecimal(
					organizationConstruction.getThreeSatisfaction()
							/ organizationConstructionList.size());
			organizationConstruction.setSurveySatisfaction(surveySatisfaction
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			organizationConstruction.setThreeSatisfaction(threeSatisfaction
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		newReportDataSummary
				.setOrganizationConstruction(organizationConstruction);
		//---CapitalInvested
		List<CapitalInvested> capitalInvestedList = new ArrayList<CapitalInvested>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject capitalInvestedJson = JSONObject
					.fromObject(reportDataSummary.getCapitalInvestedData());
			CapitalInvested capitalInvested = (CapitalInvested) (capitalInvestedJson
					.toBean(capitalInvestedJson, CapitalInvested.class));
			if (capitalInvested != null) {
				capitalInvestedList.add(capitalInvested);
			}
		}
		CapitalInvested capitalInvested = NewVillageInfoUtil
				.dealList(capitalInvestedList);
		newReportDataSummary.setCapitalInvested(capitalInvested);
		//---FarmerPerIncomeInfo
		List<FarmerPerIncomeInfo> farmerPerIncomeInfoList = new ArrayList<FarmerPerIncomeInfo>();
		for (ReportDataSummary reportDataSummary : newList) {
			JSONObject farmerPerIncomeInfoJson = JSONObject
					.fromObject(reportDataSummary.getFarmerPerIncomeInfoData());
			FarmerPerIncomeInfo farmerPerIncomeInfo = (FarmerPerIncomeInfo) (farmerPerIncomeInfoJson
					.toBean(farmerPerIncomeInfoJson, FarmerPerIncomeInfo.class));
			if (farmerPerIncomeInfo != null) {
				farmerPerIncomeInfoList.add(farmerPerIncomeInfo);
			}
		}
		FarmerPerIncomeInfo farmerPerIncomeInfo = NewVillageInfoUtil
				.dealList(farmerPerIncomeInfoList);
		if (farmerPerIncomeInfoList.size() > 1) {//(处理需要得出平均值的字段)
			BigDecimal farmerPerIncome = new BigDecimal(
					farmerPerIncomeInfo.getFarmerPerIncome()
							/ farmerPerIncomeInfoList.size());
			farmerPerIncomeInfo.setFarmerPerIncome(farmerPerIncome.setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		newReportDataSummary.setFarmerPerIncomeInfo(farmerPerIncomeInfo);
		//end
		newReportDataSummary.setStatisticsDimensions(year + displayName);
		return newReportDataSummary;
	}
}
