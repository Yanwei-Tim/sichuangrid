package com.tianque.baseInfo.rectificativePerson.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("rectificativePersonController")
@Namespace("/baseinfo/rectificativePersonManage")
public class RectificativePersonController extends
		PopulationControllerAdapter<RectificativePerson> {

	private static Logger logger = LoggerFactory
			.getLogger(RectificativePersonController.class);

	@Autowired
	private RectificativePersonService rectificativePersonService;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	
	@Autowired
	private PermissionDubboService permissionDubboService;

	private RectificativePerson population;

	private BaseInfoTables baseInfoTables;

	private String supervisorName;// 监管人员名称

	private String populationType;// 人员类型名称

	private String dailogName;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/rectify/searchRectificativePersonDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/rectify/rectifiyViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/rectify/rectifyListForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/rectify/rectifiyViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainRectificativePersonBaseInfo";
		ajaxUrl = "hasDuplicateRectificativePersonPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/rectify/searchRectificativePersonDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/rectify/rectifiyViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/rectify/rectifyListForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainRectificativePersonBaseInfo";
		ajaxUrl = "hasDuplicateRectificativePersonPopulation";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected RectificativePerson getPopulationFetchOrgById(Long id) {
		RectificativePerson population = rectificativePersonService
				.getRectificativePersonById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "hasDuplicateRectificativeForPositive", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulationForRectifive() throws Exception {
		if (organizationId == null) {
			return ERROR;
		}
		hasDuplicatePopulation = rectificativePersonService
				.hasDuplicateRectificativePersonForPositiveInfo(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateRectificativePersonPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			return ERROR;
		}
		hasDuplicatePopulation = rectificativePersonService
				.hasDuplicateRectificativePerson(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateResultRectificativePersonPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateResultRectificativePersonPopulation()
			throws Exception {
		if (organizationId == null) {
			return ERROR;
		}
		hasDuplicatePopulation = rectificativePersonService
				.hasDuplicateRectificativePerson(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@Action(value = "rectificativePersonList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					rectificativePersonService
							.findRectificativePersonsForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "maintainBusinessInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = rectificativePersonService
				.updateRectificativePersonBusiness(population);
		return SUCCESS;
	}

	@Action(value = "addRectificativePerson", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String addRectificativePerson() throws Exception {
		population = rectificativePersonService
				.addRectificativePerson(population);
		return SUCCESS;
	}

	@Action(value = "updateRectificativePerson", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updateRectificativePerson() throws Exception {
		population = rectificativePersonService
				.updateRectificativePerson(population);
		return SUCCESS;
	}

	@Action(value = "maintainRectificativePersonBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = rectificativePersonService
					.updateRectificativePersonBaseInfo(population);
		} else {
			population = rectificativePersonService
					.addRectificativePersonBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "dispatchRectificativePersonBusiness", results = {
			@Result(name = "success", location = "/baseinfo/rectify/maintainRectificativePersonDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = rectificativePersonService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.RECTIFICATIVEPERSON_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * ID加密 关注
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
		populationIdList = rectificativePersonService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.RECTIFICATIVEPERSON_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = rectificativePersonService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	/**
	 * 取消死亡 ID加密
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		populations = rectificativePersonService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "deleteRectificativePersonByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		rectificativePersonService
				.deleteRectificativePersonByIds(analyzePopulationIds());
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] selectId = populationIds.split(",");
		List<Long> idList;
		if (selectId[0].equals("")) {
			idList = initTargetId(selectId, 1);
		} else {
			idList = initTargetId(selectId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	/**
	 * 查看得方法 @ success
	 */
	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewRectificativePerson", results = { @Result(name = "success", location = "/baseinfo/rectify/rectifiyView.jsp") }),
			@Action(value = "getRectificativePersonByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = rectificativePersonService
					.getRectificativePersonById(population.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
		}
		// 拆分图片路径字符串
		if (null != population && null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		if("true".equals(tqmobile)){
			//判断权限，有权限不隐藏
			if(permissionDubboService.
					isUserHasPermission(ThreadVariable.getUser().getId(), "isRectificativePersonManagementNotHidCard")){
				population.setDemoIdCardNo(population.getIdCardNo());
				return SUCCESS;
			}
			population.setDemoIdCardNo(IdCardUtil.hiddenIdCard(population.getIdCardNo()));
			return SUCCESS;
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "findRectificativePersonByIdCardeNo", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "findRectificativePersonByIdCardeNoAndOrgId", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String findRectificativePersonByIdCardeNo() throws Exception {
		if (population != null && population.getIdCardNo() != null) {
			population = rectificativePersonService
					.findRectificativePersonByIdCardNoAndOrgId(population
							.getIdCardNo(), population.getOrganization()
							.getId());
			if (population != null && population.getOrganization() != null)
				population.getOrganization().setOrgName(
						ControllerHelper
								.getOrganizationRelativeName(population
										.getOrganization().getId(),
										organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "findRectificativePersonByIdCardNoAndOrgIdAndEmphasis", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findRectificativePersonByIdCardNoAndOrgIdAndEmphasis()
			throws Exception {
		if (null != population && null != population.getIdCardNo()) {
			population = rectificativePersonService
					.findRectificativePersonByIdCardNoAndOrgIdAndEmphasis(
							population.getIdCardNo(), population
									.getOrganization().getId());
		}
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByIdCardNo() throws Exception {
		RectificativePerson rectificativePerson = rectificativePersonService
				.findRectificativePersonByIdCardNoAndOrgId(population
						.getIdCardNo(), population.getOrganization().getId());
		Long[] populations = { rectificativePerson.getId() };
		rectificativePersonService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.RECTIFICATIVEPERSON_KEY, populations);
		// .updateEmphasiseById(rectificativePerson.getId(), 0L);
		return SUCCESS;
	}

	private PageInfo<RectificativePerson> emptyPage(int pageSize) {
		PageInfo<RectificativePerson> pageInfo = new PageInfo<RectificativePerson>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RectificativePerson>());
		return pageInfo;
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

	public RectificativePerson getPopulation() {
		return population;
	}

	public void setPopulation(RectificativePerson population) {
		this.population = population;
	}

	public BaseInfoTables getBaseInfoTables() {
		return baseInfoTables;
	}

	public void setBaseInfoTables(BaseInfoTables baseInfoTables) {
		this.baseInfoTables = baseInfoTables;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	// fateson add 数据转移操作
	// current org id
	private Long orgId;
	Long targetOrgId;
	private String currentClassName = "";
	private Organization organization = null;
	// need move peoples id
	String ids = "";

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCurrentClassName() {
		return currentClassName;
	}

	public void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}

	public Long getTargetOrgId() {
		return targetOrgId;
	}

	public void setTargetOrgId(Long targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	@Action(value = "shiftDispatch", results = {
			@Result(name = "success", location = "/common/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftDispatch() throws Exception {

		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		if (StringUtils.isBlank(currentClassName)) {
			errorMessage = "系统忙";
			logger.error("deal shiftDispatch occur Error，currentClassName is empty");
			return ERROR;
		}

		return SUCCESS;
	}

	String SEPARATOR = ".";

	/**
	 * 数据转移操作
	 * 
	 * @return
	 */

	@Action(value = "shiftPerson", results = {
			@Result(name = "success", type = "json", params = {}),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftPerson() throws Exception {
		if (ids.equals("")) {
			errorMessage = "请选择要转移的数据";
			return ERROR;
		}

		if (orgId == 0 || targetOrgId == 0) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}

		if (targetOrgId.equals(orgId)) {
			errorMessage = "请选择其他组织机构";
			return ERROR;
		}

		String[] moveids = ids.split(",");
		rectificativePersonService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

}
