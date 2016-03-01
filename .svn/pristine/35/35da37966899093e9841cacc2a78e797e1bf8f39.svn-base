package com.tianque.baseInfo.goodSamaritan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.domain.PopulationAttachFile;
import com.tianque.baseInfo.goodSamaritan.service.GoodSamaritanService;
import com.tianque.baseInfo.goodSamaritan.service.PopulationAttachFileService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.FileUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Namespace("/baseinfo/goodSamaritanManage")
@Controller("goodSamaritanController")
@Scope("prototype")
public class GoodSamaritanController extends
		PopulationControllerAdapter<GoodSamaritan> {
	@Autowired
	private GoodSamaritanService goodSamaritanService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	private GoodSamaritan population;
	private Long orgId;
	/** 修改见义勇为业务信息的时候，获取附件列表 */
	private List<PopulationAttachFile> personAttachFileList;
	/** 参保类型大类的最后一个类型“其他“ */
	private PropertyDict insureSituationOfLast;
	@Autowired
	private PopulationAttachFileService populationAttachFileService;
	private PopulationAttachFile populationAttachFile;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/goodSamaritan/searchGoodSamaritansDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/goodSamaritan/viewGoodSamaritanDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/goodSamaritan/goodSamaritanForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/positiveInfo/viewPositiveinfoDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		actionName = "maintainGoodSamaritanBaseInfo";
		ajaxUrl = "hasDuplicateGoodSamaritan";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/goodSamaritan/viewGoodSamaritanDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/goodSamaritan/goodSamaritanForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainGoodSamaritanBaseInfo";
		ajaxUrl = "hasDuplicateGoodSamaritan";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Action(value = "goodSamaritanList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					goodSamaritanService.findGoodSamaritansForPageByOrgId(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@Actions({
			@Action(value = "maintainGoodSamaritanBaseInfo", results = {
					@Result(name = "addcache", type = "json", params = {
							"root", "cacheId", "ignoreHierarchy", "false" }),
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "maintainGoodSamaritanBaseInfoForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = goodSamaritanService
					.updateGoodSamaritanBaseInfo(population);
		} else {
			population = goodSamaritanService
					.addGoodSamaritanBaseInfo(population);
		}
		return SUCCESS;
	}

	private PageInfo<GoodSamaritan> emptyPage(int pageSize) {
		PageInfo<GoodSamaritan> pageInfo = new PageInfo<GoodSamaritan>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<GoodSamaritan>());
		return pageInfo;
	}

	@Action(value = "addGoodSamaritan", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String maintainBusinessInfo() throws Exception {
		population = goodSamaritanService
				.updateGoodSamaritanBusiness(population);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String updateEmphasiseById() throws Exception {
		if (StringUtils.isEmpty(populationIds)) {
			errorMessage = "populationIds参数不正确";
			return ERROR;
		}
		Long[] ids = analyzePopulationIds();
		populationIdList = goodSamaritanService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.GOOD_SAMARITAN_KEY, ids);
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
		if (StringUtils.isEmpty(populationIds)) {
			errorMessage = "populationIds参数不正确";
			return ERROR;
		}
		Long[] ids = analyzePopulationIds();
		populationIdList = goodSamaritanService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.GOOD_SAMARITAN_KEY, ids);
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewGoodSamaritan", results = {
					@Result(name = "success", location = "/baseinfo/goodSamaritan/goodSamaritanView.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getCommonPopulationByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"population", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = goodSamaritanService
					.getSimpleGoodSamaritanById(population.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
		}
		// 拆分图片路径字符串
		if (null != population && null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}

		personAttachFileList = populationAttachFileService
				.queryPopulationAttachFileByBusinessForList(population.getId());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		populations = goodSamaritanService.updateDeathByIds(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		populations = goodSamaritanService.updateDeathByIds(
				Arrays.asList(analyzePopulationIds()), population.isDeath());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateGoodSamaritan", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = goodSamaritanService.existGoodSamaritan(
				organizationId, population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "deleteGoodSamaritanByIdsForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "deleteGoodSamaritanByIds", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true", "excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String deleteByIds() throws Exception {
		goodSamaritanService.deleteGoodSamaritanByIds(analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "dispatchGoodSamaritanBusiness", results = {
			@Result(name = "success", location = "/baseinfo/goodSamaritan/goodSamaritanDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
			// 获取参保类型的"其他"选项，为了在页面上控制二级选择不显示
			List<PropertyDict> insureSituationTypeList = propertyDictDubboService
					.findPropertyDictByDomainName(PropertyTypes.INSURE_SITUATION);
			for (PropertyDict propertyDict : insureSituationTypeList) {
				if ("其他".equals(propertyDict.getDisplayName())) {
					insureSituationOfLast = propertyDict;
				}
			}
			personAttachFileList = populationAttachFileService
					.queryPopulationAttachFileByBusinessForList(population
							.getId());
		}
		return SUCCESS;
	}

	@Action(value = "downloadPersonAttachFile")
	public String downloadPersonAttachFile() throws Exception {
		if (null == populationAttachFile
				|| null == populationAttachFile.getId()) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		populationAttachFile = populationAttachFileService
				.getPopulationAttachFile(populationAttachFile.getId());

		if (null == populationAttachFile) {
			errorMessage = "附件不存在";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + populationAttachFile.getAnnexAddress());
			downloadFileName = new String(populationAttachFile.getFileName()
					.getBytes("gbk"), "ISO8859-1");
		} catch (FileNotFoundException e) {
			errorMessage = "附件文件不存在";
			return ERROR;
		} catch (UnsupportedEncodingException uee) {
			errorMessage = "文件编码格式不正确";
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	@Override
	protected GoodSamaritan getPopulationFetchOrgById(Long id) {
		GoodSamaritan population = goodSamaritanService
				.getGoodSamaritanById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
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

	public GoodSamaritan getPopulation() {
		return population;
	}

	public void setPopulation(GoodSamaritan population) {
		this.population = population;
	}

	public PropertyDict getInsureSituationOfLast() {
		return insureSituationOfLast;
	}

	public void setInsureSituationOfLast(PropertyDict insureSituationOfLast) {
		this.insureSituationOfLast = insureSituationOfLast;
	}

	public List<PopulationAttachFile> getPersonAttachFileList() {
		return personAttachFileList;
	}

	public void setPersonAttachFileList(
			List<PopulationAttachFile> personAttachFileList) {
		this.personAttachFileList = personAttachFileList;
	}

	public PopulationAttachFile getPopulationAttachFile() {
		return populationAttachFile;
	}

	public void setPopulationAttachFile(
			PopulationAttachFile populationAttachFile) {
		this.populationAttachFile = populationAttachFile;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}
