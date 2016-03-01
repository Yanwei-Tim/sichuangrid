package com.tianque.aidsPopulations.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.aidsPopulations.service.AidspopulationsService;
import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * :艾滋病人员控制类
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:04
 */

@Namespace("/baseinfo/aidspopulationsManage")
@Transactional
@Scope("request")
@Controller("aidspopulationsController")
public class AidspopulationController extends
		PopulationControllerAdapter<Aidspopulations> {

	private static Logger logger = LoggerFactory
			.getLogger(AidspopulationController.class);

	@Autowired
	private AidspopulationsService aidspopulationsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Aidspopulations population;
	private SearchAidspopulationsVo searchaidspopulationsVo;
	private String ids;
	private Long organizationId;
	private Long orgId;// 关注，取消关注的时候，修改count计数器

	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "add", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "edit", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "search", location = "/template/aidsPopulations/searchAidspopulationsDlg.ftl"),
			@Result(name = "view", location = "/template/aidsPopulations/aidspopulationsViewDlg.ftl"),
			@Result(name = "statistic", location = "/template/aidsPopulations/aidspopulationsListForStatistics.ftl"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainAidspopulationsBaseInfo";
		ajaxUrl = "hasDuplicateAidspopulationsPopulation";
		population = dispathBaseInfo(population);

		return getMode();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "edit", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/template/aidsPopulations/aidspopulationsViewDlg.ftl"),
			@Result(name = "statistic", location = "/template/aidsPopulations/aidspopulationsListForStatistics.ftl"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainAidspopulationsBaseInfo";
		ajaxUrl = "hasDuplicateAidspopulationsPopulation";
		population = dispathBaseInfo(population);

		return getMode();
	}

	protected Aidspopulations getPopulationFetchOrgById(Long id) {
		Aidspopulations population = aidspopulationsService
				.getAidspopulationsById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	/**
	 * 新增基本信息
	 */
	@PermissionFilter(ename = "addAidspopulations")
	@Action(value = "maintainAidspopulationsBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = aidspopulationsService
					.updateAidspopulationsBaseInfo(population);
		} else {
			population = aidspopulationsService
					.addAidspopulationBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "addAidspopulationsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addAidNeedPopulationForMobile() throws Exception {
		population = aidspopulationsService.addAidspopulations(population);
		return SUCCESS;
	}

	@Action(value = "findAidspopulationsByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findAidspopulationsByIdCardNoAndOrgId() throws Exception {
		population = aidspopulationsService.getAidspopulationsByIdCardNo(
				population.getIdCardNo(), organizationId);

		return SUCCESS;
	}

	@Action(value = "updateAidspopulationsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateAidspopulationsForMobile() throws Exception {
		population = aidspopulationsService.updateAidspopulations(population);
		return SUCCESS;
	}

	/**
	 * 删除艾滋病人员
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteAidspopulations")
	@Action(value = "deleteAidspopulationsByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		aidspopulationsService
				.deleteAidspopulationByIds(analyzePopulationIds());
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

	/**
	 * 判断艾滋病人员是否存在
	 */
	@Action(value = "hasDuplicateAidspopulationsPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = aidspopulationsService
				.hasDuplicateAidspopulations(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	/**
	 * 判断艾滋病人员身份证号是否存在
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateResultAidspopulationsPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateResultPopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		population = aidspopulationsService.hasDuplicateAidspopulations(
				organizationId, population.getIdCardNo());
		if (population != null) {
			if (population.getSourcesState() != 1) {
				hasDuplicatePopulation = true;
			}
		}
		return SUCCESS;
	}

	/**
	 * 新增艾滋病人员业务信息
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addAidspopulations")
	@Action(value = "addAidspopulations", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String maintainBusinessInfo() throws Exception {
		population = aidspopulationsService
				.updateAidspopulationsBusiness(population);
		return SUCCESS;
	}

	/**
	 * 设置状态
	 */
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = aidspopulationsService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.AIDSPOPULATIONS_KEY,
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
		populationIdList = aidspopulationsService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.AIDSPOPULATIONS_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * 取消死亡
	 * 
	 * @return
	 */
	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = aidspopulationsService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	/**
	 * ID加密取消死亡
	 */
	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		populations = aidspopulationsService.updateDeathByIds(
				analyzePopulationIds(), population.isDeath());
		return SUCCESS;
	}

	/**
	 * 根据ID获取艾滋病人员 查看页面-基本信息
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewAidspopulations", results = {
					@Result(name = "success", location = "/template/aidsPopulations/aidspopulationsView.ftl"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getAidspopulationsByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = aidspopulationsService
					.getAidspopulationsById(population.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "dispatchAidspopulationsBusiness", results = {
			@Result(name = "success", location = "/template/aidsPopulations/aidspopulationsBusiness.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {

		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	@Action(value = "aidspopulationsList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					aidspopulationsService.findAidspopulationsForPage(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<Aidspopulations> emptyPage(int pageSize) {
		PageInfo<Aidspopulations> pageInfo = new PageInfo<Aidspopulations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Aidspopulations>());
		return pageInfo;
	}

	public Aidspopulations getPopulation() {
		return population;
	}

	public void setPopulation(Aidspopulations population) {
		this.population = population;
	}

	public SearchAidspopulationsVo getSearchaidspopulationsVo() {
		return searchaidspopulationsVo;
	}

	public void setSearchaidspopulationsVo(
			SearchAidspopulationsVo searchaidspopulationsVo) {
		this.searchaidspopulationsVo = searchaidspopulationsVo;
	}

	public SearchAidspopulationsVo getSearchAidspopulationsVo() {
		return searchaidspopulationsVo;
	}

	public void setSearchAidspopulationsVo(
			SearchAidspopulationsVo searchaidspopulationsVo) {
		this.searchaidspopulationsVo = searchaidspopulationsVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
