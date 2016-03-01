package com.tianque.baseInfo.unemployedPeople.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.baseInfo.unemployedPeople.service.UnemployedPeopleService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/unemployedPeopleManage")
@Transactional
@Scope("request")
@Controller("unemployedController")
public class UnemployedPeopleController extends
		PopulationControllerAdapter<UnemployedPeople> {

	@Autowired
	private UnemployedPeopleService unemployedPeopleService;

	private UnemployedPeople population;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<Long> trainingIntentionIds;
	private List<Long> employmentIntentionIds;

	private String intentions;
	private String trainings;
	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称
	private String employmentIntention;// 拟就业意向，为手机端使用
	private String trainingIntention;// 拟参加培训意向，为手机端使用
	private Long orgId;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/civilAdministration/unemployedPeople/searchUnemployedPeopleDlg.jsp"),
					// @Result(name = "print", location =
					// "/baseinfo/civilAdministration/unemployedPeople/maintainUnemployedPeoplePrintDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleViewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleListForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "mainUnemployedPeopleBaseInfo";
		ajaxUrl = "hasDuplicateUnemployedPeople";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleViewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleListForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "mainUnemployedPeopleBaseInfo";
		ajaxUrl = "hasDuplicateUnemployedPeople";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected UnemployedPeople getPopulationFetchOrgById(Long id) {
		UnemployedPeople population = unemployedPeopleService
				.getUnemployedPeopleById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@PermissionFilter(ename = "addUnemployedPeople")
	@Action(value = "addUnemployedPeople", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = unemployedPeopleService.updateUnemployedPeopleBusiness(
				population, trainingIntentionIds, employmentIntentionIds);
		return SUCCESS;

	}

	@PermissionFilter(ename = "addUnemployedPeople")
	@Action(value = "mainUnemployedPeopleBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = unemployedPeopleService
					.updateUnemployedPeopleBaseInfo(population);
		} else {
			population = unemployedPeopleService
					.addUnemployedPeopleBaseInfo(population);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewUnemployedPeople", results = { @Result(name = "success", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleView.jsp") }),
			@Action(value = "getUnemployedPeopleByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = unemployedPeopleService
					.getFullUnemployedPeopleById(population.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
			List<PropertyDict> intentionList = population
					.getEmploymentIntention();
			if (intentionList != null && intentionList.size() > 0) {
				StringBuilder stb = new StringBuilder();
				for (PropertyDict p : intentionList) {
					stb.append(p.getDisplayName()).append(",");
				}
				intentions = stb.substring(0, stb.length() - 1);

			}

			List<PropertyDict> trainingList = population.getTrainingIntention();
			if (trainingList != null && trainingList.size() > 0) {
				StringBuilder stb = new StringBuilder();
				for (PropertyDict p : trainingList) {
					stb.append(p.getDisplayName()).append(",");
				}
				trainings = stb.substring(0, stb.length() - 1);

			}
		}
		// 拆分图片路径字符串
		if (null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		return SUCCESS;
	}

	@Action(value = "dispatchUnemployedPeopleBusiness", results = {
			@Result(name = "success", location = "/baseinfo/civilAdministration/unemployedPeople/unemployedPeopleBusiness.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "unemployedPeopleManagement")
	@Action(value = "unemployedPeopleList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					unemployedPeopleService
							.findUnemployedPeopleForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<UnemployedPeople> emptyPage(int pageSize) {
		PageInfo<UnemployedPeople> pageInfo = new PageInfo<UnemployedPeople>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<UnemployedPeople>());
		return pageInfo;
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		populationIdList = unemployedPeopleService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.UNEMPLOYEDPEOPLE_KEY, ids);
		return SUCCESS;
	}

	/**
	 * ID加密注销
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		populationIdList = unemployedPeopleService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.UNEMPLOYEDPEOPLE_KEY, ids);
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populations = unemployedPeopleService.updateDeathByIds(idList,
				population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populations = unemployedPeopleService.updateDeathByIds(idList,
				population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteUnemployedPeople")
	@Action(value = "deleteUnemployedPeopleByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		String[] deleteId = populationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		unemployedPeopleService.deleteUnemployedPeoplesByIdList(idList);
		return SUCCESS;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	@Action(value = "hasDuplicateUnemployedPeople", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		} else {
			hasDuplicatePopulation = unemployedPeopleService
					.hasDuplicateUnemployedPeople(organizationId,
							population.getIdCardNo(), population.getId());
			return SUCCESS;
		}
	}

	@Action(value = "addUnemployedPeopleForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addUnemployedPeopleForMobile() throws Exception {
		population = unemployedPeopleService.addUnemployedPeople(population);
		if (null != employmentIntention && !"".equals(employmentIntention)) {
			String[] employmentArr = employmentIntention.split(",");
			employmentIntentionIds = new ArrayList();
			for (String employmentId : employmentArr) {
				employmentIntentionIds.add(Long.parseLong(employmentId));
			}
			if (employmentIntentionIds != null) {
				unemployedPeopleService.addEmploymentIntention(
						population.getId(), employmentIntentionIds);
			}

		}
		if (null != trainingIntention && !"".equals(trainingIntention)) {
			String[] trainingArr = trainingIntention.split(",");
			trainingIntentionIds = new ArrayList();
			for (String trainingId : trainingArr) {
				trainingIntentionIds.add(Long.parseLong(trainingId));
			}
			if (trainingIntentionIds != null) {
				unemployedPeopleService.addTrainingIntention(
						population.getId(), trainingIntentionIds);
			}
		}
		return SUCCESS;
	}

	@Action(value = "updateUnemployedPeopleForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateUnemployedPeopleForMobile() throws Exception {
		population = unemployedPeopleService.updateUnemployedPeople(population);
		if (null != employmentIntention && !"".equals(employmentIntention)) {
			String[] employmentArr = employmentIntention.split(",");
			employmentIntentionIds = new ArrayList();
			for (String employmentId : employmentArr) {
				employmentIntentionIds.add(Long.parseLong(employmentId));
			}
			if (employmentIntentionIds != null) {
				unemployedPeopleService.regrantEmploymentIntentionIds(
						population.getId(), employmentIntentionIds);
			}

		}
		if (null != trainingIntention && !"".equals(trainingIntention)) {
			String[] trainingArr = trainingIntention.split(",");
			trainingIntentionIds = new ArrayList();
			for (String trainingId : trainingArr) {
				trainingIntentionIds.add(Long.parseLong(trainingId));
			}
			if (trainingIntentionIds != null) {
				unemployedPeopleService.regrantTrainingIntentionIds(
						population.getId(), trainingIntentionIds);
			}
		}
		return SUCCESS;
	}

	@Action(value = "findUnemployedPeopleByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findUnemployedPeopleByIdCardNoAndOrgId() throws Exception {
		population = unemployedPeopleService
				.getUnemployedPeopleByIdCardNoAndOrganizationId(
						population.getIdCardNo(), organizationId);

		return SUCCESS;
	}

	public UnemployedPeople getPopulation() {
		return population;
	}

	public void setPopulation(UnemployedPeople population) {
		this.population = population;
	}

	public List<Long> getTrainingIntentionIds() {
		return trainingIntentionIds;
	}

	public void setTrainingIntentionIds(List<Long> trainingIntentionIds) {
		this.trainingIntentionIds = trainingIntentionIds;
	}

	public List<Long> getEmploymentIntentionIds() {
		return employmentIntentionIds;
	}

	public void setEmploymentIntentionIds(List<Long> employmentIntentionIds) {
		this.employmentIntentionIds = employmentIntentionIds;
	}

	public String getIntentions() {
		return intentions;
	}

	public void setIntentions(String intentions) {
		this.intentions = intentions;
	}

	public String getTrainings() {
		return trainings;
	}

	public void setTrainings(String trainings) {
		this.trainings = trainings;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getEmploymentIntention() {
		return employmentIntention;
	}

	public void setEmploymentIntention(String employmentIntention) {
		this.employmentIntention = employmentIntention;
	}

	public String getTrainingIntention() {
		return trainingIntention;
	}

	public void setTrainingIntention(String trainingIntention) {
		this.trainingIntention = trainingIntention;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
