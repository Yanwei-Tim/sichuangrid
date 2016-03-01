package com.tianque.mobile.organization;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.newSocietyOrganizations.controller.NewSocietyOrganizationsController;
import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.service.NewSocietyOrganitionsService;
import com.tianque.controller.NewEconomicOrganizationsController;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.vo.SearchNewEconomicOrganizationsVo;
import com.tianque.mobile.base.BaseMobileAction;

/**
 * 
 * @ClassName: OrganizationMobileAdapterImpl
 * @Description: TODO手机端 组织机构适系统模块配器实现类
 * @author wanggz
 * @date 2014-9-29 上午09:53:41
 */

@Namespace("/mobile/organizationSystemManage")
public class OrganizationSystemMobileAdapterImpl extends BaseMobileAction
		implements OrganizationSystemMobileAdapter {

	private Long id;
	private Long organizationId;

	private SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo;
	private NewEconomicOrganizations economicOrganizations;// 非公有制经济组织对象

	@Autowired
	private NewEconomicOrganizationsController newEconomicOrganizationsController;
	private NewSocietyOrganizations societyOrganization; // 社会组织对象
	@Autowired
	private NewSocietyOrganizationsController newSocietyOrganizationsController;
	@Autowired
	private NewSocietyOrganitionsService newSocietyOrganitionsService;

	private Boolean hasNewEconomicOrganizations;

	/**
	 * 非公有制经济组织列表方法
	 */
	@Action(value = "findNewEconomicOrganizationsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String findNewEconomicOrganizationsForMobile() throws Exception {
		if (organizationId == null || searchNewEconomicOrganizationsVo == null
				|| searchNewEconomicOrganizationsVo.getIsEmphasis() == null
				|| judgeFourValues()) {
			errorMessage = "请确认必填参数是否正确和完善";
			return ERROR;
		}
		newEconomicOrganizationsController.setOrganizationId(organizationId);
		newEconomicOrganizationsController
				.setSearchNewEconomicOrganizationsVo(searchNewEconomicOrganizationsVo);
		newEconomicOrganizationsController.setRows(rows);
		newEconomicOrganizationsController.setPage(page);
		newEconomicOrganizationsController.setSidx(sidx);
		newEconomicOrganizationsController.setSord(sord);
		newEconomicOrganizationsController.findNewEconomicOrganizations();
		gridPage = newEconomicOrganizationsController.getGridPage();
		return SUCCESS;
	}

	private boolean judgeFourValues() {
		boolean flag = false;
		if (rows == null || page == null || !StringUtil.isStringAvaliable(sidx)
				|| !StringUtil.isStringAvaliable(sord)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @Title: findNewEconomicOrganizationsById
	 * @Description: TODO根据id查找非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 上午09:35:56
	 */
	@Override
	@Action(value = "findNewEconomicOrganizationsById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"economicOrganizations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findNewEconomicOrganizationsById() throws Exception {
		if (id == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		newEconomicOrganizationsController.setId(id);
		newEconomicOrganizationsController.getNewEconomicOrganizationsById();
		economicOrganizations = newEconomicOrganizationsController
				.getNewEconomicOrganizations();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: addNewEconomicOrganizations
	 * @Description: TODO新增非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 上午10:50:10
	 */
	@Override
	@Action(value = "addNewEconomicOrganizationsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addNewEconomicOrganizationsForMobile() throws Exception {
		if (!judgeEconomicOrganizations()) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		newEconomicOrganizationsController.setMode(DialogMode.ADD_MODE);
		newEconomicOrganizationsController
				.setNewEconomicOrganizations(economicOrganizations);
		newEconomicOrganizationsController.saveNewEconomicOrganizations();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: updateNewEconomicOrganizationsForMobileById
	 * @Description: TODO修改非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 上午11:24:54
	 */
	@Override
	@Action(value = "updateNewEconomicOrganizationsForMobileById", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateNewEconomicOrganizationsForMobileById()
			throws Exception {
		if (!judgeEconomicOrganizations()
				|| economicOrganizations.getId() == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		newEconomicOrganizationsController.setMode(DialogMode.EDIT_MODE);
		newEconomicOrganizationsController
				.setNewEconomicOrganizations(economicOrganizations);
		newEconomicOrganizationsController.saveNewEconomicOrganizations();
		return SUCCESS;
	}

	/** 判断必传参数是否完善 true表示完善 false表示不完善 */
	private boolean judgeEconomicOrganizations() {
		boolean flag = true;
		if (economicOrganizations == null
				|| economicOrganizations.getOrganization() == null
				|| economicOrganizations.getOrganization().getId() == null
				|| !StringUtil.isStringAvaliable(economicOrganizations
						.getName())
				|| !StringUtil.isStringAvaliable(economicOrganizations
						.getResidence())
				|| !StringUtil.isStringAvaliable(economicOrganizations
						.getLicenseNumber())
				|| economicOrganizations.getValidityStartDate() == null
				|| economicOrganizations.getValidityEndDate() == null
				|| economicOrganizations.getStyle() == null
				|| economicOrganizations.getStyle().getId() == null
				|| !StringUtil.isStringAvaliable(economicOrganizations
						.getChief())) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * @Title: searchNewEconomicOrganizationsForMobile
	 * @Description: TODO查询非公有制经济组织
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-10 下午05:17:06
	 */
	@Override
	@Action(value = "searchNewEconomicOrganizationsForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchNewEconomicOrganizationsForMobile() throws Exception {
		if (organizationId == null || judgeFourValues()) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		newEconomicOrganizationsController.setOrganizationId(organizationId);
		newEconomicOrganizationsController
				.setSearchNewEconomicOrganizationsVo(searchNewEconomicOrganizationsVo);
		newEconomicOrganizationsController.setRows(rows);
		newEconomicOrganizationsController.setPage(page);
		newEconomicOrganizationsController.setSidx(sidx);
		newEconomicOrganizationsController.setSord(sord);
		newEconomicOrganizationsController.searchNewEconomicOrganizations();
		gridPage = newEconomicOrganizationsController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: hasDuplicateNewSocietyOrganizationsName
	 * @Description: TODO查询组织名称是否重复
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-15 上午01:42:16
	 */
	@Override
	@Action(value = "hasDuplicateNewSocietyOrganizationsName", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }),
			@Result(name = "mobile_error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateNewSocietyOrganizationsName() throws Exception {
		if (societyOrganization == null
				|| !StringUtil.isStringAvaliable(societyOrganization.getName())
				|| societyOrganization.getOrganization() == null
				|| societyOrganization.getOrganization().getId() == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return MOBILE_ERROR;
		}
		return newSocietyOrganitionsService
				.hasDuplicateNewSocietyOrganizationsName(societyOrganization
						.getOrganization().getId(), societyOrganization
						.getName(), societyOrganization.getId()) ? SUCCESS
				: ERROR;
	}

	/**
	 * 
	 * @Title: hasDuplicateNewEconomicOrganizations
	 * @Description: TODO查询非公有制经济组织名称是否重复
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-15 下午02:29:17
	 */
	@Override
	@Action(value = "hasDuplicateNewEconomicOrganizationsName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasNewEconomicOrganizations" }),
			@Result(name = "mobile_error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateNewEconomicOrganizationsName() throws Exception {
		if (organizationId == null
				|| !StringUtil.isStringAvaliable(economicOrganizations
						.getName())) {
			errorMessage = "请确认必填参数是否正确完善";
			return MOBILE_ERROR;
		}
		newEconomicOrganizationsController.setOrganizationId(organizationId);
		newEconomicOrganizationsController
				.setNewEconomicOrganizations(economicOrganizations);
		newEconomicOrganizationsController
				.hasDuplicateNewEconomicOrganizations();
		hasNewEconomicOrganizations = newEconomicOrganizationsController
				.getHasDuplicateNewEconomicOrganizations();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: hasDuplicateNewEconomicOrganizationsLicenseNumber
	 * @Description: TODO查询非公有制经济组织营业执照号是否重复
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-15 下午02:46:03
	 */
	@Override
	@Action(value = "hasDuplicateNewEconomicOrganizationsLicenseNumber", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasNewEconomicOrganizations" }),
			@Result(name = "mobile_error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateNewEconomicOrganizationsLicenseNumber()
			throws Exception {
		if (organizationId == null
				|| !StringUtil.isStringAvaliable(economicOrganizations
						.getLicenseNumber())) {
			errorMessage = "请确认必填参数是否正确完善";
			return MOBILE_ERROR;
		}
		newEconomicOrganizationsController.setOrganizationId(organizationId);
		newEconomicOrganizationsController
				.setNewEconomicOrganizations(economicOrganizations);
		newEconomicOrganizationsController
				.hasDuplicateNewEconomicOrganizations();
		hasNewEconomicOrganizations = newEconomicOrganizationsController
				.getHasDuplicateNewEconomicOrganizations();
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchNewEconomicOrganizationsVo getSearchNewEconomicOrganizationsVo() {
		return searchNewEconomicOrganizationsVo;
	}

	public void setSearchNewEconomicOrganizationsVo(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		this.searchNewEconomicOrganizationsVo = searchNewEconomicOrganizationsVo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NewEconomicOrganizations getEconomicOrganizations() {
		return economicOrganizations;
	}

	public void setEconomicOrganizations(
			NewEconomicOrganizations economicOrganizations) {
		this.economicOrganizations = economicOrganizations;
	}

	public NewSocietyOrganizations getSocietyOrganization() {
		return societyOrganization;
	}

	public void setSocietyOrganization(
			NewSocietyOrganizations societyOrganization) {
		this.societyOrganization = societyOrganization;
	}

	public Boolean getHasNewEconomicOrganizations() {
		return hasNewEconomicOrganizations;
	}

	public void setHasNewEconomicOrganizations(
			Boolean hasNewEconomicOrganizations) {
		this.hasNewEconomicOrganizations = hasNewEconomicOrganizations;
	}

}
