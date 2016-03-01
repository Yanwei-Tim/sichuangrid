package com.tianque.newVillage.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
import com.tianque.newVillage.dao.NewVillageBasicDao;
import com.tianque.newVillage.domain.BasicYearInfo;
import com.tianque.newVillage.domain.CapitalInvested;
import com.tianque.newVillage.domain.CommonServiceInfo;
import com.tianque.newVillage.domain.EnvironmentalReform;
import com.tianque.newVillage.domain.FarmerPerIncomeInfo;
import com.tianque.newVillage.domain.IndustryDevelopment;
import com.tianque.newVillage.domain.Infrastructure;
import com.tianque.newVillage.domain.NewVillage;
import com.tianque.newVillage.domain.NewVillageBasic;
import com.tianque.newVillage.domain.OrganizationConstruction;
import com.tianque.newVillage.domain.ReportDataSummary;
import com.tianque.newVillage.service.BasicYearInfoService;
import com.tianque.newVillage.service.CapitalInvestedService;
import com.tianque.newVillage.service.CommonServiceInfoService;
import com.tianque.newVillage.service.EnvironmentalReformService;
import com.tianque.newVillage.service.FarmerPerIncomeInfoService;
import com.tianque.newVillage.service.IndustryDevelopmentService;
import com.tianque.newVillage.service.InfrastructureService;
import com.tianque.newVillage.service.NewVillageBasicService;
import com.tianque.newVillage.service.NewVillageService;
import com.tianque.newVillage.service.OrganizationConstructionService;
import com.tianque.newVillage.service.VillageReportSummaryService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("newVillageBasicService")
@Transactional
public class NewVillageBasicServiceImpl implements NewVillageBasicService {

	@Autowired
	private NewVillageBasicDao newVillageBasicDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private VillageReportSummaryService villageReportSummaryService;
	@Autowired
	private NewVillageService newVillageService;
	@Autowired
	private CommonServiceInfoService commonServiceInfoService;
	@Autowired
	private EnvironmentalReformService environmentalReformService;
	@Autowired
	private IndustryDevelopmentService industryDevelopmentService;
	@Autowired
	private InfrastructureService infrastructureService;
	@Autowired
	private OrganizationConstructionService organizationConstructionService;
	@Autowired
	private CapitalInvestedService capitalInvestedService;
	@Autowired
	private FarmerPerIncomeInfoService farmerPerIncomeInfoService;
	@Autowired
	private BasicYearInfoService basicYearInfoService;
	@Qualifier("newVillageBasicValidator")
	@Autowired
	private DomainValidator<NewVillageBasic> domainValidator;

	@Override
	public NewVillageBasic addNewVillageBasic(NewVillageBasic newVillageBasic) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(newVillageBasic);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		// 验证数据库中是否已经存在同机构 同年 同季度 信息
		if (!getNewVillageBasicByYear(
				newVillageBasic.getOrganization().getId(),
				newVillageBasic.getYear(), newVillageBasic.getIsPlaning(),
				newVillageBasic.getQuarter())) {
			StringBuilder errorStringBuilder = new StringBuilder(30);
			errorStringBuilder.append("已存在").append(newVillageBasic.getYear())
					.append("年");
			if (newVillageBasic.getQuarter() != null
					&& newVillageBasic.getQuarter() > 0) {
				errorStringBuilder.append(newVillageBasic.getQuarter()).append(
						"季度");
			}
			throw new BusinessValidationException(errorStringBuilder.append(
					"的数据!").toString());
		}
		//设置相关组织机构信息
		Organization organization = organizationDubboService
				.getSimpleOrgById(newVillageBasic.getOrganization().getId());
		newVillageBasic.setOrganization(organization);
		newVillageBasic.setReportStatus(ComprehensiveInfoConstant.REPORT_NOT);
		newVillageBasic.setCheckStatus(ComprehensiveInfoConstant.CHECK_NOT);
		try {
			return newVillageBasicDao.addNewVillageBasic(newVillageBasic);
		} catch (Exception e) {
			throw new ServiceValidationException("新增出错", e);
		}
	}

	@Override
	public NewVillageBasic updateNewVillageBasic(NewVillageBasic newVillageBasic) {
		if (newVillageBasic.getOrganization() == null
				|| newVillageBasic.getOrganization().getId() == null) {
			throw new BusinessValidationException("修改出错");
		}
		try {
			return newVillageBasicDao.updateNewVillageBasic(newVillageBasic);
		} catch (Exception e) {
			throw new ServiceValidationException("修改出错", e);
		}
	}

	@Override
	public void reportNewVillageBasic(Long id, Integer reportStatus,
			Date reportDate, String reportUser) {
		if (id == null) {
			throw new BusinessValidationException("上报出错");
		}
		try {
			newVillageBasicDao.reportNewVillageBasic(id, reportStatus,
					reportDate, reportUser);
		} catch (Exception e) {
			throw new ServiceValidationException("上报出错", e);
		}
	}

	@Override
	public void checkNewVillageBasic(Long id, Integer checkStatus,
			Date checkDate, String checkUser) {
		if (id == null) {
			throw new BusinessValidationException("审核出错");
		}
		try {
			newVillageBasicDao.checkNewVillageBasic(id, checkStatus, checkDate,
					checkUser);
		} catch (Exception e) {
			throw new ServiceValidationException("审核出错", e);
		}

	}

	@Override
	public PageInfo<NewVillageBasic> findNewVillageBasicForList(
			NewVillageBasic newVillageBasic, Integer page, Integer rows,
			String sidx, String sord) {
		if (newVillageBasic == null
				|| newVillageBasic.getOrganization() == null
				|| newVillageBasic.getOrganization().getId() == null
				|| newVillageBasic.getIsPlaning() == null) {
			throw new BusinessValidationException("查询失败，未获得正确组织机构参数");
		}
		try {
			return newVillageBasicDao.findNewVillageBasicForList(
					newVillageBasic, page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("列表查询出错", e);
		}
	}

	@Override
	public NewVillageBasic getNewVillageBasicById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("查询失败，未获得关键参数");
		}
		return newVillageBasicDao.getNewVillageBasicById(id);
	}

	@Override
	public void deleteNewVillageBasicByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("删除失败，未获得删除参数");
		}
		try {
			for (String id : ids) {
				// 先通过basinfoId查询出9项上报数据，删除子数据再删除basic数据
				BasicYearInfo basicYearInfo = basicYearInfoService
						.getBasicYearInfoByBasicId(Long.parseLong(id));
				NewVillage newVillage = newVillageService
						.getNewVillageByBasicId(Long.parseLong(id));
				CommonServiceInfo commonServiceInfo = commonServiceInfoService
						.getCommonServiceInfoByBasicId(Long.parseLong(id));
				EnvironmentalReform environmentalReform = environmentalReformService
						.getEnvironmentalReformByBasicId(Long.parseLong(id));
				IndustryDevelopment industryDevelopment = industryDevelopmentService
						.getIndustryDevelopmentByBasicId(Long.parseLong(id));
				Infrastructure infrastructure = infrastructureService
						.getInfrastructureByBasicId(Long.parseLong(id));
				OrganizationConstruction orgConstruction = organizationConstructionService
						.getOrgConstructionByBasicId(Long.parseLong(id));
				CapitalInvested capitalInvested = capitalInvestedService
						.getCapitalInvestedByBasicId(Long.parseLong(id));
				FarmerPerIncomeInfo farmerPerIncomeInfo = farmerPerIncomeInfoService
						.getFarmerPerIncomeInfoByBasicId(Long.parseLong(id));

				if (basicYearInfo != null && basicYearInfo.getId() != null) {
					String[] del = { basicYearInfo.getId() + "" };
					basicYearInfoService.deleteBasicYearInfoById(del);
				}
				if (newVillage != null && newVillage.getId() != null) {
					newVillageService.deleteNewVillageById(newVillage.getId());
				}
				if (commonServiceInfo != null
						&& commonServiceInfo.getId() != null) {
					String[] del = { commonServiceInfo.getId() + "" };
					commonServiceInfoService.deleteCommonServiceInfoById(del);
				}
				if (environmentalReform != null
						&& environmentalReform.getId() != null) {
					String[] del = { environmentalReform.getId() + "" };
					environmentalReformService
							.deleteEnvironmentalReformById(del);
				}
				if (industryDevelopment != null
						&& industryDevelopment.getId() != null) {
					String[] del = { industryDevelopment.getId() + "" };
					industryDevelopmentService
							.deleteIndustryDevelopmentById(del);
				}
				if (infrastructure != null && infrastructure.getId() != null) {
					String[] del = { infrastructure.getId() + "" };
					infrastructureService.deleteInfrastructureById(del);
				}
				if (orgConstruction != null && orgConstruction.getId() != null) {
					String[] del = { orgConstruction.getId() + "" };
					organizationConstructionService
							.deleteOrgConstructionById(del);
				}
				if (capitalInvested != null && capitalInvested.getId() != null) {
					String[] del = { capitalInvested.getId() + "" };
					capitalInvestedService.deleteCapitalInvestedById(del);
				}
				if (farmerPerIncomeInfo != null
						&& farmerPerIncomeInfo.getId() != null) {
					String[] del = { farmerPerIncomeInfo.getId() + "" };
					farmerPerIncomeInfoService
							.deleteFarmerPerIncomeInfoById(del);
				}
			}
			newVillageBasicDao.deleteNewVillageBasicByIds(ids);
		} catch (Exception e) {
			throw new ServiceValidationException("删除失败", e);
		}
	}

	@Override
	public boolean getNewVillageBasicByYear(Long orgId, Integer year,
			Integer isPlaning, Integer quarter) {
		return newVillageBasicDao.getNewVillageBasicByYear(orgId, year,
				isPlaning, quarter);
	}

	@Override
	public void reportNewVillageBasicInfo(Long basicId, Integer isPlaning) {
		if (basicId == null) {
			throw new BusinessValidationException("上报失败，未获得上报数据");
		}
		NewVillageBasic newVillageBasic = getNewVillageBasicById(basicId);// 获得上报主数据
		if (newVillageBasic == null) {
			throw new BusinessValidationException("上报失败，未获得上报数据");
		}
		Organization currentOrg = organizationDubboService
				.getSimpleOrgById(newVillageBasic.getOrganization().getId());
		Organization parentOrg = organizationDubboService
				.getSimpleOrgById(currentOrg.getParentOrg().getId());
		ReportDataSummary reportDataSummary = new ReportDataSummary();
		reportDataSummary.setIsPlaning(isPlaning);
		reportDataSummary.setYear(newVillageBasic.getYear());
		reportDataSummary.setQuarter(newVillageBasic.getQuarter());
		reportDataSummary.setReportOrg(currentOrg);
		reportDataSummary.setOrganization(parentOrg);
		reportDataSummary.setReportStatus(ComprehensiveInfoConstant.REPORT_NOT);
		reportDataSummary.setCheckStatus(ComprehensiveInfoConstant.CHECK_NOT);
		reportDataSummary.setReportIds(newVillageBasic.getId().toString());
		// 分别获得上报的各个类别数据
		// 基本信息
		BasicYearInfo basicYearInfo = basicYearInfoService
				.getBasicYearInfoByBasicId(basicId);
		JSONObject basicYearInfoJson = JSONObject.fromObject(basicYearInfo);
		reportDataSummary.setBasicYearInfoData(basicYearInfoJson.toString());
		// 新村建设
		NewVillage newVillage = newVillageService
				.getNewVillageByBasicId(basicId);
		JSONObject newVillageJson = JSONObject.fromObject(newVillage);
		reportDataSummary.setNewVillageData(newVillageJson.toString());
		// 公共服务
		CommonServiceInfo commonServiceInfo = commonServiceInfoService
				.getCommonServiceInfoByBasicId(basicId);
		JSONObject commonServiceInfoJson = JSONObject
				.fromObject(commonServiceInfo);
		reportDataSummary.setCommonServiceInfoData(commonServiceInfoJson
				.toString());
		// 基础设施建设
		Infrastructure infrastructure = infrastructureService
				.getInfrastructureByBasicId(basicId);
		JSONObject infrastructureJson = JSONObject.fromObject(infrastructure);
		reportDataSummary.setInfrastructureData(infrastructureJson.toString());

		// 产业发展
		IndustryDevelopment industryDevelopment = industryDevelopmentService
				.getIndustryDevelopmentByBasicId(basicId);
		JSONObject industryDevelopmentJson = JSONObject
				.fromObject(industryDevelopment);
		reportDataSummary.setIndustryDevelopmentData(industryDevelopmentJson
				.toString());
		// 环境改造
		EnvironmentalReform environmentalReform = environmentalReformService
				.getEnvironmentalReformByBasicId(basicId);
		JSONObject environmentalReformJson = JSONObject
				.fromObject(environmentalReform);
		reportDataSummary.setEnvironmentalReformData(environmentalReformJson
				.toString());
		// 基层组织建设

		OrganizationConstruction organizationConstruction = organizationConstructionService
				.getOrgConstructionByBasicId(basicId);
		JSONObject organizationConstructionJson = JSONObject
				.fromObject(organizationConstruction);
		reportDataSummary
				.setOrganizationConstructionData(organizationConstructionJson
						.toString());
		// 资金投入
		CapitalInvested capitalInvested = capitalInvestedService
				.getCapitalInvestedByBasicId(basicId);
		JSONObject capitalInvestedJson = JSONObject.fromObject(capitalInvested);
		reportDataSummary
				.setCapitalInvestedData(capitalInvestedJson.toString());
		// 农民可支配收入
		FarmerPerIncomeInfo farmerPerIncomeInfo = farmerPerIncomeInfoService
				.getFarmerPerIncomeInfoByBasicId(basicId);
		JSONObject farmerPerIncomeInfoJson = JSONObject
				.fromObject(farmerPerIncomeInfo);
		reportDataSummary.setFarmerPerIncomeInfoData(farmerPerIncomeInfoJson
				.toString());
		// 新增上报数据
		villageReportSummaryService.addVillageReportSummary(reportDataSummary);
		// 修改原数据上报状态
		newVillageBasicDao.reportNewVillageBasic(basicId,
				ComprehensiveInfoConstant.REPORT_YES, CalendarUtil.now(),
				ThreadVariable.getUser().getUserName());
	}

	@Override
	public boolean findSameYearNewVillageBasics(Long orgId, Integer year) {
		if (orgId == null || year == null) {
			throw new BusinessValidationException("参数错误");
		}
		return newVillageBasicDao.findSameYearNewVillageBasics(orgId, year);
	}
}
