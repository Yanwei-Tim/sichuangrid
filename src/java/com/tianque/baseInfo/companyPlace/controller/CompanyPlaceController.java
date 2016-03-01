package com.tianque.baseInfo.companyPlace.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBase;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVo;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBusinessService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.dataManage.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("companyPlaceController")
@Namespace("/baseinfo/companyPlaceManage")
@Scope("request")
public class CompanyPlaceController extends BaseAction {

	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CompanyPlaceBusinessService companyPlaceBusinessService;
	private Organization org; // 组织机构ID
	private String baseIds; // 基础信息ids
	private String cids; // companypalce主键ID
	private CompanyPlaceVo companyPlaceVo; // 查询对象
	private CompanyPlace companyPlace; // 实体对象
	private List<CompanyPlaceBase> companyPlaceBaseList;// 模糊匹配返回
	private String modulType; // 大类型(三级联动参数不可删除)
	private String modul; // 导航类型(三级联动参数不可删除)
	private String modulKey;// 模块名称(三级联动参数不可删除)
	private String isBusinessInfo = Constants.isBusinessInfo.BUSINESSINFO_NO;// 是否有业务信息
	private CompanyPlaceBusinessVO companyPlaceBusinessVO;
	/** 封装gridPage时，organizationId设置为null */
	private Long organizationId = null;
	private Long orgId;
	private String tableName;
	private Boolean isGrid = false;
	private String moduleKey;
	private String searchType;

	@Actions({ @Action(value = "queryCompanyPlaceList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String queryCompanyPlaceList() throws Exception {
		PageResult<CompanyPlace> pageResult = companyPlaceBaseService
				.queryCompanyPlaceForPageResult(companyPlaceVo, modulKey,
						defaultSortAndPage());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				pageResult, organizationDubboService,
				new String[] { "organization" }, organizationId));
		return SUCCESS;
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/companyPlace/companyPlaceBaseDlg.jsp"),
			@Result(name = "edit", location = "/baseinfo/companyPlace/companyPlaceBaseDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/companyPlace/companyPlaceView.jsp"),
			@Result(name = "search", location = "/baseinfo/companyPlace/searchCompanyPlaceDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (null == companyPlace && null == companyPlace.getOrg()
					&& null == companyPlace.getOrg().getId()) {
				this.errorMessage = "单位场所传参出现错误！";
				return ERROR;
			}
			if (null != id) {
				companyPlace = companyPlaceBaseService
						.getCompanyPlaceInfoByCid(id);
			}
			companyPlace.getOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(companyPlace
							.getOrg().getId(), organizationDubboService));
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (!StringUtil.isStringAvaliable(cids)) {
				this.errorMessage = "单位场所的参数错误！";
				return ERROR;
			}
			Long cid = Long.valueOf(cids);
			companyPlace = companyPlaceBaseService
					.getCompanyPlaceInfoByCid(cid);
			companyPlaceBusinessVO = companyPlaceBusinessService
					.queryCompanyPlaceBusinessVOByCompanyPlaceIdForList(cid);
			if (companyPlaceBusinessVO != null) {
				setIsBusinessInfo(Constants.isBusinessInfo.BUSINESSINFO_IS);
			}
		}
		return mode;
	}

	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/companyPlace/companyPlaceBaseDlg.jsp"),
			@Result(name = "edit", location = "/baseinfo/companyPlace/companyPlaceBaseDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/companyPlace/companyPlaceView.jsp"),
			@Result(name = "search", location = "/baseinfo/companyPlace/searchCompanyPlaceDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (null == companyPlace && null == companyPlace.getOrg()
					&& null == companyPlace.getOrg().getId()) {
				this.errorMessage = "单位场所传参出现错误！";
				return ERROR;
			}
			if (null != id) {
				companyPlace = companyPlaceBaseService
						.getCompanyPlaceInfoByCid(id);
			}
			companyPlace.getOrg().setOrgName(
					ControllerHelper.getOrganizationRelativeName(companyPlace
							.getOrg().getId(), organizationDubboService));
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (!StringUtil.isStringAvaliable(cids)) {
				this.errorMessage = "单位场所的参数错误！";
				return ERROR;
			}
			companyPlace = companyPlaceBaseService
					.getCompanyPlaceInfoByCid(Long.valueOf(cids));
		}
		return mode;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "newDeleteCompanyPlace")
	@Action(value = "delCompanyPlaceOperation", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String delCompanyPlaceOperation() throws Exception {
		if (!StringUtil.isStringAvaliable(cids)) {
			this.errorMessage = "单位场所的参数错误！";
			return ERROR;
		}
		companyPlaceBaseService.delteCompanyPlaceBaseByIds(cids, modulKey);
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "newDeleteCompanyPlace")
	@Action(value = "delCompanyPlaceOperationByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String delCompanyPlaceOperationByEncrypt() throws Exception {
		if (!StringUtil.isStringAvaliable(cids)) {
			this.errorMessage = "单位场所的参数错误！";
			return ERROR;
		}
		companyPlaceBaseService.delteCompanyPlaceBaseByIds(cids, modulKey);
		return SUCCESS;
	}

	@PermissionFilter(ename = "newAddCompanyPlace")
	@Action(value = "addCompanyPlaceOperation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"companyPlace" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addCompanyPlaceOperation() throws Exception {
		if (null == companyPlace) {
			this.errorMessage = "单位场所对象为空！";
			return ERROR;
		}
		// 点击的是上一步，执行更新操作
		if (null != id) {
			companyPlaceBaseService.updateCompanyPalceForAll(companyPlace,
					modulKey);
			companyPlace = companyPlaceBaseService.getCompanyPlaceInfoByCid(id);
		} else {
			companyPlace = companyPlaceBaseService.addCompanyPlaceBase(
					companyPlace, modulKey, id);
		}
		return SUCCESS;
	}

	@Action(value = "addCompanyPlaceOperationForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"companyPlace" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addCompanyPlaceOperationForMobile() throws Exception {
		if (null == companyPlace) {
			this.errorMessage = "单位场所对象为空！";
			return ERROR;
		}
		companyPlace = companyPlaceBaseService.addCompanyPlaceBaseForMobile(
				companyPlace, modulKey, id);
		return SUCCESS;
	}

	@PermissionFilter(ename = "newUpdateCompanyPlace")
	@Action(value = "updateCompanyPlaceOperation", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateCompanyPlaceOperation() throws Exception {
		if (null == companyPlace) {
			this.errorMessage = "单位场所对象为空！";
			return ERROR;
		}
		companyPlaceBaseService
				.updateCompanyPalceForAll(companyPlace, modulKey);
		return SUCCESS;
	}

	@Action(value = "checkCompanyPlaceHas", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String checkCompanyPlaceHas() throws Exception {
		if (companyPlace == null) {
			this.errorMessage = "单位场所对象为空！";
			return ERROR;
		}
		if (!companyPlaceBaseService.checkCompanyPlaceHas(companyPlace)) {
			return ERROR;
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "getCompanyPlaceName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"companyPlace" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	// 轨迹中显示单位场所的名称
	public String getCompanyPlaceName() throws Exception {
		if (null == companyPlace || companyPlace.getCid() == null) {
			this.errorMessage = "单位场所参数错误！";
			return ERROR;
		}
		companyPlace = companyPlaceBaseService
				.getCompanyPlaceInfoByCid(companyPlace.getCid());
		return SUCCESS;
	}

	@Action(value = "autocompleteByName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"companyPlaceBaseList" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String autocompleteByName() throws Exception {
		if (null == companyPlace) {
			this.errorMessage = "单位场所对象为空！";
			return ERROR;
		}
		companyPlaceBaseList = companyPlaceBaseService
				.queryCompanyplaceInfoLikeNameForList(companyPlace);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: statisticsBaseInfo
	 * @Description: TODO单位场所 领导视图
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-8-12 下午02:28:56
	 */
	@Actions({ @Action(value = "statisticsBaseInfo", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String statisticsBaseInfo() throws Exception {
		PageInfo<StatisticsBaseInfoAddCountVo> pageInfo = new PageInfo<StatisticsBaseInfoAddCountVo>();
		if (orgId == null) {
			gridPage = new GridPage(pageInfo.emptyPage(rows));
			return SUCCESS;
		}
		List<StatisticsBaseInfoAddCountVo> datas = companyPlaceBaseService
				.statisticsBaseInfo(orgId, moduleKey);
		pageInfo.setResult(datas);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: statisticsSummary
	 * @Description: TODO领导视图 线形图
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-8-12 下午03:12:32
	 */
	@Actions({ @Action(value = "statisticsSummary", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String statisticsSummary() throws Exception {
		PageInfo<StatisticsBaseInfoAddCountVo> pageInfo = new PageInfo<StatisticsBaseInfoAddCountVo>();
		if (orgId == null) {
			gridPage = new GridPage(pageInfo.emptyPage(rows));
			return SUCCESS;
		}
		List<StatisticsBaseInfoAddCountVo> datas = companyPlaceBaseService
				.statisticsSummary(orgId, moduleKey);
		pageInfo.setResult(datas);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getBaseIds() {
		return baseIds;
	}

	public void setBaseIds(String baseIds) {
		this.baseIds = baseIds;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public CompanyPlaceVo getCompanyPlaceVo() {
		return companyPlaceVo;
	}

	public void setCompanyPlaceVo(CompanyPlaceVo companyPlaceVo) {
		this.companyPlaceVo = companyPlaceVo;
	}

	public String getModul() {
		return modul;
	}

	public void setModul(String modul) {
		this.modul = modul;
	}

	public String getCids() {
		return cids;
	}

	public void setCids(String cids) {
		this.cids = cids;
	}

	public CompanyPlace getCompanyPlace() {
		return companyPlace;
	}

	public void setCompanyPlace(CompanyPlace companyPlace) {
		this.companyPlace = companyPlace;
	}

	public String getModulType() {
		return modulType;
	}

	public void setModulType(String modulType) {
		this.modulType = modulType;
	}

	public String getModulKey() {
		return modulKey;
	}

	public void setModulKey(String modulKey) {
		this.modulKey = modulKey;
	}

	public List<CompanyPlaceBase> getCompanyPlaceBaseList() {
		return companyPlaceBaseList;
	}

	public void setCompanyPlaceBaseList(
			List<CompanyPlaceBase> companyPlaceBaseList) {
		this.companyPlaceBaseList = companyPlaceBaseList;
	}

	public String getIsBusinessInfo() {
		return isBusinessInfo;
	}

	public void setIsBusinessInfo(String isBusinessInfo) {
		this.isBusinessInfo = isBusinessInfo;
	}

	public CompanyPlaceBusinessVO getCompanyPlaceBusinessVO() {
		return companyPlaceBusinessVO;
	}

	public void setCompanyPlaceBusinessVO(
			CompanyPlaceBusinessVO companyPlaceBusinessVO) {
		this.companyPlaceBusinessVO = companyPlaceBusinessVO;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Boolean getIsGrid() {
		return isGrid;
	}

	public void setIsGrid(Boolean isGrid) {
		this.isGrid = isGrid;
	}

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
