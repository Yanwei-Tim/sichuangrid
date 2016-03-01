package com.tianque.newVillage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
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
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Namespace("/baseinfo/newVillageBasicManage")
@Controller("newVillageBasicController")
public class NewVillageBasicController extends BaseAction {

	private Long id;
	private String ids;
	private Integer operateType;// 操作类别
	private Organization organization;
	private NewVillageBasic newVillageBasic;
	private Integer dataType;
	private Integer isPlaning;

	private BasicYearInfo basicYearInfo;
	private NewVillage newVillage;
	private CommonServiceInfo commonServiceInfo;
	private EnvironmentalReform environmentalReform;
	private IndustryDevelopment industryDevelopment;
	private Infrastructure infrastructure;
	private OrganizationConstruction orgConstruction;
	private CapitalInvested capitalInvested;
	private FarmerPerIncomeInfo farmerPerIncomeInfo;
	private Boolean flag = false;// newVillageBasic中的基本信息basicYearInfo是否可以更改和保存
	@Autowired
	private BasicYearInfoService basicYearInfoService;
	@Autowired
	private NewVillageBasicService newVillageBasicService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
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
	private OrganizationConstructionService orgConstructionService;
	@Autowired
	private CapitalInvestedService capitalInvestedService;
	@Autowired
	private FarmerPerIncomeInfoService farmerPerIncomeInfoService;

	/**
	 * 默认查询新村建设信息
	 */
	@Action(value = "findNewVillageBasicForList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findNewVillageBasicForList() throws Exception {
		if (newVillageBasic == null
				|| newVillageBasic.getOrganization() == null
				|| newVillageBasic.getOrganization().getId() == null) {
			errorMessage = "查询出错，未获得组织机构参数";
			return ERROR;
		}
		PageInfo<NewVillageBasic> pageInfo = ControllerHelper
				.processAllOrgRelativeName(newVillageBasicService
						.findNewVillageBasicForList(newVillageBasic, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, newVillageBasic
								.getOrganization().getId());
		gridPage = new GridPage<NewVillageBasic>(pageInfo);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "dispatchOpearte", results = {
			@Result(name = "success", location = "/newCountryside/newCountrysideInfo/newVillageBasicTab.jsp"),
			@Result(name = "addBasic", location = "/newCountryside/newCountrysideInfo/selectYearAndQuarter.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String dispatchOpearte() throws Exception {
		if ("addBasic".equals(mode)) {
			if (organization == null || organization.getId() == null
					|| isPlaning == null) {
				errorMessage = "操作失败，未获得组织机构参数";
				return ERROR;
			}
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			return mode;
		}
		if (DialogMode.ADD_MODE.equals(mode)
				|| DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (organization == null || organization.getId() == null
					|| newVillageBasic == null
					|| newVillageBasic.getId() == null || isPlaning == null) {
				errorMessage = "操作失败，未获得组织机构参数";
				return ERROR;
			}
			newVillageBasic = newVillageBasicService
					.getNewVillageBasicById(newVillageBasic.getId());
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());
		}
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "dispatchOpearteForMaintain", results = {
			@Result(name = "basicYearInfo", location = "/newCountryside/newCountrysideInfo/addBasicYearInfo.jsp"),
			@Result(name = "newVillage", location = "/newCountryside/newCountrysideInfo/addNewVillage.jsp"),
			@Result(name = "industryDevelopment", location = "/newCountryside/newCountrysideInfo/addIndustryDevelopment.jsp"),
			@Result(name = "infrastructure", location = "/newCountryside/newCountrysideInfo/addInfrastructure.jsp"),
			@Result(name = "commonServiceInfo", location = "/newCountryside/newCountrysideInfo/addCommonServiceInfo.jsp"),
			@Result(name = "environmentalReform", location = "/newCountryside/newCountrysideInfo/addEnvironmentalReform.jsp"),
			@Result(name = "organizationConstruction", location = "/newCountryside/newCountrysideInfo/addOrgConstruction.jsp"),
			@Result(name = "capitalInvested", location = "/newCountryside/newCountrysideInfo/addCapitalInvested.jsp"),
			@Result(name = "farmerPerIncomeInfo", location = "/newCountryside/newCountrysideInfo/addFarmerPerIncomeInfo.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String dispatchOpearteForMaintain() throws Exception {
		if (dataType == null || organization == null
				|| organization.getId() == null || newVillageBasic == null
				|| newVillageBasic.getId() == null) {
			errorMessage = "跳转出现错误，请重试";
			return ERROR;
		}
		newVillageBasic = newVillageBasicService
				.getNewVillageBasicById(newVillageBasic.getId());
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (ComprehensiveInfoConstant.BASICYEARINFO_KEY.equals(dataType)) {
			// 查出该年所有季度上报信息且包含基本信息,看是否有上报且没有被退回的,flag为true则有,其余该年份的基本信息不能编辑保存
			flag = newVillageBasicService.findSameYearNewVillageBasics(
					newVillageBasic.getOrganization().getId(),
					newVillageBasic.getYear());
			// 通过baseID查询 村基本信息
			basicYearInfo = basicYearInfoService
					.getBasicYearInfoByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.NEWVILLAGE_KEY.equals(dataType)) {
			// 通过baseID查询新村建设信息
			newVillage = newVillageService
					.getNewVillageByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.COMMONSERVICEINFO_KEY.equals(dataType)) {
			// 通过baseID查询公共服务信息
			commonServiceInfo = commonServiceInfoService
					.getCommonServiceInfoByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.ENVIRONMENTALREFORM_KEY.equals(dataType)) {
			// 通过baseID查询环境改造信息
			environmentalReform = environmentalReformService
					.getEnvironmentalReformByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.INDUSTRYDEVELOPMENT_KEY.equals(dataType)) {
			// 通过baseID查询产业发展信息
			industryDevelopment = industryDevelopmentService
					.getIndustryDevelopmentByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.INFRASTRUCTURE_KEY.equals(dataType)) {
			// 通过baseID查询基础设施信息
			infrastructure = infrastructureService
					.getInfrastructureByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.ORGANIZATIONCONSTRUCTION_KEY
				.equals(dataType)) {
			// 通过baseID查询资金投入信息
			orgConstruction = orgConstructionService
					.getOrgConstructionByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.CAPITALINVESTED_KEY.equals(dataType)) {
			// 通过baseID查询 资金投入信息
			capitalInvested = capitalInvestedService
					.getCapitalInvestedByBasicId(newVillageBasic.getId());
		}
		if (ComprehensiveInfoConstant.FARMERPERINCOMEINFO_KEY.equals(dataType)) {
			// 通过baseID查询农民人均收入信息
			farmerPerIncomeInfo = farmerPerIncomeInfoService
					.getFarmerPerIncomeInfoByBasicId(newVillageBasic.getId());
		}
		String operateType = ComprehensiveInfoConstant.map.get(dataType);
		if (!StringUtil.isStringAvaliable(operateType)) {
			errorMessage = "跳转出现错误，请重试";
			return ERROR;
		}
		return operateType;
	}

	@Action(value = "maintainNewVillageBasic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newVillageBasic", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainNewVillageBasic() throws Exception {
		if (newVillageBasic == null
				|| newVillageBasic.getOrganization() == null
				|| newVillageBasic.getOrganization().getId() == null
				|| newVillageBasic.getIsPlaning() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		newVillageBasic = newVillageBasicService
				.addNewVillageBasic(newVillageBasic);
		// 查出该年保存的基本信息,同样绑定到其中
		List<BasicYearInfo> list = basicYearInfoService
				.getBasicYearInfoByYearAndOrgId(newVillageBasic.getYear(),
						newVillageBasic.getOrganization().getId());
		if (list.size() > 0) {
			BasicYearInfo basicYear = list.get(0);
			basicYear.setNewVillageBasic(newVillageBasic);
			basicYearInfoService.addBasicYearInfo(list.get(0));
			basicYearInfo = list.get(0);// 用于回显
		}
		return SUCCESS;
	}

	@Action(value = "deleteNewVillageBasic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newVillageBasic", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteNewVillageBasic() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "删除失败，未获得删除数据";
			return ERROR;
		}
		newVillageBasicService.deleteNewVillageBasicByIds(ids.split(","));
		return SUCCESS;
	}

	/***
	 * 数据上报
	 * 
	 * @return
	 */
	@Action(value = "reportNewVillageBasicInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String reportNewVillageBasicInfo() throws Exception {
		if (id == null || isPlaning == null) {
			errorMessage = "上报失败，未获得上报数据";
			return ERROR;
		}

		newVillageBasicService.reportNewVillageBasicInfo(id, isPlaning);
		return SUCCESS;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public NewVillageBasic getNewVillageBasic() {
		return newVillageBasic;
	}

	public void setNewVillageBasic(NewVillageBasic newVillageBasic) {
		this.newVillageBasic = newVillageBasic;
	}

	public NewVillage getNewVillage() {
		return newVillage;
	}

	public void setNewVillage(NewVillage newVillage) {
		this.newVillage = newVillage;
	}

	public CommonServiceInfo getCommonServiceInfo() {
		return commonServiceInfo;
	}

	public void setCommonServiceInfo(CommonServiceInfo commonServiceInfo) {
		this.commonServiceInfo = commonServiceInfo;
	}

	public EnvironmentalReform getEnvironmentalReform() {
		return environmentalReform;
	}

	public void setEnvironmentalReform(EnvironmentalReform environmentalReform) {
		this.environmentalReform = environmentalReform;
	}

	public Infrastructure getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}

	public OrganizationConstruction getOrgConstruction() {
		return orgConstruction;
	}

	public void setOrgConstruction(OrganizationConstruction orgConstruction) {
		this.orgConstruction = orgConstruction;
	}

	public IndustryDevelopment getIndustryDevelopment() {
		return industryDevelopment;
	}

	public void setIndustryDevelopment(IndustryDevelopment industryDevelopment) {
		this.industryDevelopment = industryDevelopment;
	}

	public CapitalInvested getCapitalInvested() {
		return capitalInvested;
	}

	public void setCapitalInvested(CapitalInvested capitalInvested) {
		this.capitalInvested = capitalInvested;
	}

	public FarmerPerIncomeInfo getFarmerPerIncomeInfo() {
		return farmerPerIncomeInfo;
	}

	public void setFarmerPerIncomeInfo(FarmerPerIncomeInfo farmerPerIncomeInfo) {
		this.farmerPerIncomeInfo = farmerPerIncomeInfo;
	}

	public BasicYearInfo getBasicYearInfo() {
		return basicYearInfo;
	}

	public void setBasicYearInfo(BasicYearInfo basicYearInfo) {
		this.basicYearInfo = basicYearInfo;
	}

	public Boolean isFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getIsPlaning() {
		return isPlaning;
	}

	public void setIsPlaning(Integer isPlaning) {
		this.isPlaning = isPlaning;
	}

}
