package com.tianque.baseInfo.positiveInfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Namespace("/baseinfo/positiveInfoManage")
@Controller("positiveInfoController")
@Scope("prototype")
@Transactional
public class PositiveInfoController extends
		PopulationControllerAdapter<PositiveInfo> {
	@Autowired
	private PositiveInfoService positiveInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	private PositiveInfo population;
	private Boolean hasDuplicatePositiveInfoPopulation;
	private List<PositiveInfo> list;
	private String supervisorName;// 监管人员名称
	private String dailogName;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/positiveInfo/searchPositiveInfosDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/positiveInfo/viewPositiveinfoDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/positiveInfo/positiveInfoListForStatistics.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/positiveInfo/viewPositiveinfoDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "savePositiveInfoBaseInfo";
		ajaxUrl = "hasDuplicatePositiveInfo";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/positiveInfo/searchPositiveInfosDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/positiveInfo/viewPositiveinfoDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/positiveInfo/positiveInfoListForStatistics.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "savePositiveInfoBaseInfo";
		ajaxUrl = "hasDuplicatePositiveInfo";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Action(value = "toPositiveInfo", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String toPositiveInfo() throws Exception {
		actionName = "savePositiveInfoBaseInfoForRectificative";
		ajaxUrl = "hasDuplicatePositiveInfo";
		long pre = id == null ? population.getId() : id;
		if (null == id) {
			population = positiveInfoService.fromRectificativePerson(pre);
			// fateson add 这里有点奇怪 population.getId() 查询 population 的 id
			// 为null 手动在赋值一次
			// population.setId(pre);
		} else {
			population = positiveInfoService.getPositiveInfoById(id);
		}
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		population.setId(pre);// fateson add这里设置id 是为了查出houseid
		loadAddressInfo(population);
		if (id == null) {
			// 说明是第一次转化过来的， 如果 id 没有为null 可能是点击上一步 必须带上id 到前台 以便修改
			population.setId(null);// fateson add这里设置id 为null是为了前台 不存id
									// 新增刑事解教
		}
		return SUCCESS;

	}

	/**
	 * 转为刑释解教 ID加密
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "toPositiveInfoByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String toPositiveInfoByEncrypt() throws Exception {
		actionName = "savePositiveInfoBaseInfoForRectificative";
		ajaxUrl = "hasDuplicatePositiveInfo";
		long pre = id == null ? population.getId() : id;
		if (null == id) {
			population = positiveInfoService.fromRectificativePerson(pre);
			// fateson add 这里有点奇怪 population.getId() 查询 population 的 id
			// 为null 手动在赋值一次
			// population.setId(pre);
		} else {
			population = positiveInfoService.getPositiveInfoById(id);
		}
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		population.setId(pre);// fateson add这里设置id 是为了查出houseid
		loadAddressInfo(population);
		if (id == null) {
			// 说明是第一次转化过来的， 如果 id 没有为null 可能是点击上一步 必须带上id 到前台 以便修改
			population.setId(null);// fateson add这里设置id 为null是为了前台 不存id
									// 新增刑事解教
		}
		return SUCCESS;

	}

	@Override
	protected PositiveInfo getPopulationFetchOrgById(Long id) {
		PositiveInfo population = positiveInfoService.getPositiveInfoById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	/**
	 * 修改的时候，cache中有值就从cache中取
	 */
	@Action(value = "dispatchPositiveInfoBusiness", results = {
			@Result(name = "success", location = "/baseinfo/positiveInfo/positiveInfoDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addPositiveInfo")
	@Action(value = "savePositiveInfoBaseInfoForRectificative", results = {
			@Result(name = "cacheSuccess", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfoForRectificative() throws Exception {
		population = positiveInfoService
				.rectificativeToPositiveInfo(population);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addPositiveInfo")
	@Action(value = "savePositiveInfoBaseInfo", results = {
			@Result(name = "cacheSuccess", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = positiveInfoService
					.updatePositiveInfoBaseInfo(population);
		} else {
			population = positiveInfoService
					.addPositiveInfoBaseInfo(population);
		}
		return SUCCESS;

	}

	@PermissionFilter(ename = "addPositiveInfo")
	@Action(value = "savePositiveInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = positiveInfoService.updatePositiveInfoBusiness(population);
		return SUCCESS;
	}

	@Action(value = "addPositiveInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String addPositiveInfo() throws Exception {
		population = positiveInfoService.addPositiveInfo(population);
		return SUCCESS;
	}

	@PermissionFilter(ename = "updatePositiveInfo")
	@Action(value = "updatePositiveInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "population", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String updatePositiveInfo() throws Exception {
		population = positiveInfoService.updatePositiveInfoBaseInfo(population);
		population = positiveInfoService.updatePositiveInfoBusiness(population);
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "getPositiveInfoByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewPositiveInfoById", results = { @Result(name = "success", location = "/baseinfo/positiveInfo/viewSubPositiveinfo.jsp") }) })
	public String viewInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = positiveInfoService.getPositiveInfoById(population
					.getId());
			if (population != null && population.getOrganization() != null) {
				population.getOrganization().setOrgName(
						ControllerHelper
								.getOrganizationRelativeName(population
										.getOrganization().getId(),
										organizationDubboService));
			}
		}
		// 拆分图片路径字符串
		if (null != population && null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		if("true".equals(tqmobile)){
			//判断权限，有权限不隐藏
			if(permissionDubboService.
					isUserHasPermission(ThreadVariable.getUser().getId(), "isPositiveInfoManagementNotHidCard")){
				population.setDemoIdCardNo(population.getIdCardNo());
				return SUCCESS;
			}
			population.setDemoIdCardNo(IdCardUtil.hiddenIdCard(population.getIdCardNo()));
			return SUCCESS;
		}
		return SUCCESS;
	}

	@Action(value = "positiveInfoList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null || organizationId == 0L) {
			gridPage = new GridPage(new PageInfo<PositiveInfo>());
			return SUCCESS;
		}
		populateOrgProerty();

		/*
		 * // PageInfo<PositiveInfo> positiveInfoPageInfos = positiveInfoService
		 * // .findPositiveInfoForPage(population, page.intValue(), //
		 * rows.intValue(), sidx, sord, // population.getIsEmphasis());
		 */

		PageInfo<PositiveInfo> positiveInfoPageInfos = positiveInfoService
				.findPositiveInfosForPageByOrgInternalCode(organizationId,
						page, rows, sidx, sord, population.getIsEmphasis());

		PageInfo<PositiveInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(positiveInfoPageInfos,
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deletePositiveInfo")
	@Action(value = "deletePositiveInfoByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		positiveInfoService.deletePositiveInfoByIds(analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "hasDuplicatePositiveInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePositiveInfoPopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			return ERROR;
		} else {
			hasDuplicatePositiveInfoPopulation = positiveInfoService
					.hasDuplicatePosiviteInfos(organizationId,
							population.getIdCardNo(), population.getId());
			return SUCCESS;
		}
	}

	@Action(value = "hasDuplicatePositiveInfoForRectificative", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePositiveInfoPopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulationForRectificative() throws Exception {
		if (organizationId == null) {
			return ERROR;
		} else {
			hasDuplicatePositiveInfoPopulation = positiveInfoService
					.hasDuplicatePosiviteInfosForRectificative(organizationId,
							population.getIdCardNo(), population.getId());
			return SUCCESS;
		}
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = positiveInfoService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.POSITIVEINFO_KEY, analyzePopulationIds());
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
		populationIdList = positiveInfoService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.POSITIVEINFO_KEY, analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		positiveInfoService.changeDeathByIds(analyzePopulationIds(),
				population.isDeath());
		return SUCCESS;
	}

	/**
	 * ID加密 取消死亡
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		positiveInfoService.changeDeathByIds(analyzePopulationIds(),
				population.isDeath());
		return SUCCESS;
	}

	@Action(value = "findPositiveInfoByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPositiveInfoByIdCardNoAndOrgId() throws Exception {
		if (null != population && null != population.getIdCardNo()) {
			population = positiveInfoService
					.findPositiveInfoByIdCardNoAndOrgIdAndEmphasis(population
							.getIdCardNo(), population.getOrganization()
							.getId());
		}
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = populationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	private void populateOrgProerty() {
		if (organizationId != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			population.setOrganization(organization);
			population.setOrgInternalCode(organization.getOrgInternalCode());
		}
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

	public PositiveInfo getPopulation() {
		return population;
	}

	public void setPopulation(PositiveInfo population) {
		this.population = population;
	}

	public Boolean getHasDuplicatePositiveInfoPopulation() {
		return hasDuplicatePositiveInfoPopulation;
	}

	public void setHasDuplicatePositiveInfoPopulation(
			Boolean hasDuplicatePositiveInfoPopulation) {
		this.hasDuplicatePositiveInfoPopulation = hasDuplicatePositiveInfoPopulation;
	}

	public List<PositiveInfo> getList() {
		return list;
	}

	public void setList(List<PositiveInfo> list) {
		this.list = list;
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
		positiveInfoService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

}
