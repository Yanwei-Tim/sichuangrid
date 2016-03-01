package com.tianque.baseInfo.primaryOrg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.baseInfo.primaryOrg.service.PrimaryOrgService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.BasicOrgType;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Scope("prototype")
@SuppressWarnings("serial")
@Controller("primaryOrgController")
public class PrimaryOrgController extends SearchBaseAction {
	@Autowired
	private OrganizationDubboService orgService;
	@Autowired
	private PrimaryOrgService primaryOrgService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	OrganizationDubboService organizationDubboService;

	@Autowired
	private PropertyDictService propertyDictService;

	/** 服务团队domain类 */
	private PrimaryOrganizations primaryOrg;
	/** 组织机构ID */
	private Long orgId;
	/** 组织小类 */
	private Long basicOrgId;
	/** 要删除或修改的行 */
	private String selectedIds;
	/** 删除的行数 */
	private int deleteCount;
	/** 修改的行数 */
	private int updateCount;
	/** 仅显示本级、所有下辖 、直属下辖 **/
	private String displayLevel;
	/** 所有显示的对象，列表，搜索结果 */
	private PrimaryOrgVo primaryOrgVo;
	/** 页面 对话框名称 */
	private String dailogName;

	private boolean pageOnly;

	private String teamTypeName;
	private Long typeId;
	// 用于区分组织类别
	Integer internalId;

	// 排序字段
	private Integer seq;
	private Long organizationId;
	// 组织名称EN
	private String name;
	// 综治委等类别
	private String isCommissionOrganization;

	/** 设置排序字段 */
	public String setSeq() throws Exception {
		primaryOrgVo = this.primaryOrgService.setPrimaryOrgSeq(id, seq);
		return SUCCESS;
	}

	/** 同步群防群治 */
	@EncryptAnnotation
	public String synchronizePrimaryOrgMembersToMasseduty() throws Exception {
		deleteCount = primaryOrgService
				.synchronizePrimaryOrgMembersToMasseduty(selectedIds);
		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/** 业务跳转 */
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			parameterProcessing();
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getPrimaryOrgById();
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getPrimaryOrgById();
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			parameterProcessing();
			procOrganization();
			return DialogMode.SEARCH_MODE;
		}
		return ERROR;
	}

	private void parameterProcessing() {
		if (StringUtil.isStringAvaliable(isCommissionOrganization)) {
			PropertyDict propertyDict = null;
			if (BasicOrgType.COMPREHENSIVE_KEY.equals(isCommissionOrganization)) {// 综治办
				// 获取综治办大类组织字典数据
				propertyDict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								BaseInfoTables.DEPARTMENTPARTY,
								BasicOrgType.ZOGNZHIBAN);
			} else if (BasicOrgType.COMMISSION_KEY
					.equals(isCommissionOrganization)) {// 综治委
				// 获得综治委大类字典数据
				propertyDict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								BaseInfoTables.DEPARTMENTPARTY,
								BasicOrgType.ZOGNZHIWEI);
			} else if (BasicOrgType.COMMISSIONMEMBER_KEY
					.equals(isCommissionOrganization)) {// 综治成员单位
				// 获得综治成员单位大类字典数据
				propertyDict = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								BaseInfoTables.DEPARTMENTPARTY,
								BasicOrgType.ZONGZHICHENGYUANDANWEI);
			}
			teamTypeName = propertyDict.getDisplayName();
			typeId = propertyDict.getId();
		}
	}

	/** 业务跳转 */
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			parameterProcessing();
			getPrimaryOrgById();
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getPrimaryOrgById();
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			procOrganization();
			return DialogMode.SEARCH_MODE;
		}
		return ERROR;
	}

	/**
	 * 根据层级和团队类型分页查询综治组织
	 * 
	 * @return SUCCESS
	 */

	public String findPrimaryOrgs() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				primaryOrgService.findPrimaryOrgs(primaryOrgVo, internalId,
						page, rows, sidx, sord), orgService,
				new String[] { "org" }, orgId));

		return SUCCESS;
	}

	/**
	 * 删除组织
	 * 
	 * @return 被删除记录数
	 */
	@EncryptAnnotation
	public String deletePrimaryOrg() throws Exception {
		deleteCount = primaryOrgService.deletePrimaryOrg(selectedIds);
		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/**
	 * 查看组织信息
	 * 
	 * @return
	 */
	public String viewPrimaryOrg() {
		getPrimaryOrgById();
		return "primaryOrg";
	}

	/**
	 * 新增综治组织
	 * 
	 * @return
	 */
	public String addPrimaryOrg() throws Exception {
		primaryOrgVo = primaryOrgService.addPrimaryOrg(primaryOrg);
		return SUCCESS;
	}

	/**
	 * 修改综治组织
	 * 
	 * @return
	 */
	public String updatePrimaryOrg() throws Exception {
		primaryOrgVo = primaryOrgService.updatePrimaryOrg(primaryOrg);
		return SUCCESS;
	}

	/**
	 * 导出组织信息
	 * 
	 * @return
	 */
	public String downloadPrimaryOrg() throws Exception {
		if (null == primaryOrgVo || primaryOrgVo.getOrg() == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		List<PrimaryOrganizations> records = null;
		if (pageOnly) {
			records = primaryOrgService.findPrimaryOrgs(primaryOrgVo,
					internalId, page, rows, sidx, sord).getResult();
		} else {
			records = primaryOrgService.findPrimaryOrgs(primaryOrgVo,
					internalId, 1, Integer.MAX_VALUE, sidx, sord).getResult();
		}
		inputStream = ExcelExportHelper.exportDateToExcel(SpecialGroupsContext
				.getPrimaryOrgPropertyArraySlf(primaryOrgVo.getTeamClazz()
						.getDisplayName()), records);
		downloadFileName = new String(primaryOrgVo.getTeamClazz()
				.getDisplayName().getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PRIMARYORGANIZATIONS,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ orgService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												primaryOrgVo.getOrg().getId(),
												OrganizationServiceHelper
														.getRootOrg(orgService)
														.getId()) + "导出组织信息",
						ObjectToJSON.convertJSON(new PrimaryOrganizations()));
		return STREAM_SUCCESS;
	}

	/**
	 * 导出组织信息
	 * 
	 * @return
	 */
	public void downloadPrimaryOrgAll() throws Exception {
		if (null == primaryOrgVo || primaryOrgVo.getOrg() == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		Integer count = primaryOrgService.getCount(primaryOrgVo);
		String[][] excelDefines = SpecialGroupsContext
				.getPrimaryOrgPropertyArraySlf(primaryOrgVo.getTeamClazz()
						.getDisplayName());
		exportDataAll(count, excelDefines, primaryOrgVo.getTeamClazz()
				.getDisplayName());
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PRIMARYORGANIZATIONS,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ orgService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												primaryOrgVo.getOrg().getId(),
												OrganizationServiceHelper
														.getRootOrg(orgService)
														.getId()) + "导出组织信息",
						ObjectToJSON.convertJSON(new PrimaryOrganizations()));
	}

	@Override
	public List<PrimaryOrganizations> getNeedExportDatas(int page) {
		return primaryOrgService.findPrimaryOrgs(primaryOrgVo, internalId,
				page, rows, sidx, sord).getResult();
	}

	private void getPrimaryOrgById() {
		Long primaryOrgId = primaryOrg.getId();
		primaryOrgVo = primaryOrgService.getPrimaryOrgById(primaryOrgId);
		primaryOrgVo.getOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(primaryOrgVo
						.getOrg().getId(), orgService));
	}

	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(organizationId);
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
				organization.getId(), organizationDubboService));
		primaryOrg = new PrimaryOrganizations();
		primaryOrg.setOrg(organization);
	}

	public OrganizationDubboService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationDubboService orgService) {
		this.orgService = orgService;
	}

	public PrimaryOrgService getPrimaryOrgService() {
		return primaryOrgService;
	}

	public void setPrimaryOrgService(PrimaryOrgService primaryOrgService) {
		this.primaryOrgService = primaryOrgService;
	}

	public PrimaryOrganizations getPrimaryOrg() {
		return primaryOrg;
	}

	public void setPrimaryOrg(PrimaryOrganizations primaryOrg) {
		this.primaryOrg = primaryOrg;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getBasicOrgId() {
		return basicOrgId;
	}

	public void setBasicOrgId(Long basicOrgId) {
		this.basicOrgId = basicOrgId;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public PrimaryOrgVo getPrimaryOrgVo() {
		return primaryOrgVo;
	}

	public void setPrimaryOrgVo(PrimaryOrgVo primaryOrgVo) {
		this.primaryOrgVo = primaryOrgVo;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getTeamTypeName() {
		return teamTypeName;
	}

	public void setTeamTypeName(String teamTypeName) {
		this.teamTypeName = teamTypeName;
	}

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIsCommissionOrganization() {
		return isCommissionOrganization;
	}

	public void setIsCommissionOrganization(String isCommissionOrganization) {
		this.isCommissionOrganization = isCommissionOrganization;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

}
