package com.tianque.systemOperateLog.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.PeopleCEnameMapping;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.vo.CompareObjectLog;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.ContrastState;
import com.tianque.systemOperateLog.util.SystemOperateLogToHbase;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;
import com.tianque.util.CompareObjectUtil;

/**
 * 操作日志表:服务前端控制类
 * 
 */
@Namespace("/systemOperateLogManage")
@Scope("request")
@Controller("systemOperateLogController")
public class SystemOperateLogController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(SystemOperateLogController.class);
	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PropertyDomainService propertyDomainService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemOperateLogToHbase systemOperateLogToHbase;

	private SystemOperateLogVo systemOperateLogVo;

	private Long systemoOperateLogId;

	private List<CompareObjectLog> compareObjectLogResults;

	private String modulKey;

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
			.setVersion(2.2).create();

	@Action(value = "systemOperateLogToHbase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String systemOperateLogToHbase() throws Exception {
		systemOperateLogToHbase.systemOperateLogToHbasess();
		return SUCCESS;
	}

	@Action(value = "findSystemOperateLogsPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSystemOperateLogsPagerBySearchVo() throws Exception {

		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				produceTypeName(systemOperateLogService
						.findAllSystemLogsForPage(systemOperateLogVo, page,
								rows, sidx, sord)), organizationDubboService,
				new String[] { "operationOrganization", "dataOrgId",
						"dataBeforeOrgId" }, null));
		return SUCCESS;
	}

	@Action(value = "viewCompareData", results = {
			@Result(name = "success", location = "/sysadmin/systemOperateLogManage/compareDataAfterOperate.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String viewCompareData() throws Exception {
		SystemOperateLog systemOperateLog = systemOperateLogService
				.getSystemOperateLogById(systemoOperateLogId);
		if (systemOperateLog.getContrastState().equals(ContrastState.MODIFIED)) {
			compareObjectLogResults = gson.fromJson(
					systemOperateLog.getOperateDescribe(), List.class);
			return SUCCESS;
		}
		String businessType = systemOperateLog.getBusinessType();
		Object dataBeforeOperate = transforJson(
				systemOperateLog.getDataBeforeOperate(), businessType);
		Object dataAfterOperate = transforJson(
				systemOperateLog.getDataAfterOperate(), businessType);
		if (dataBeforeOperate == null || dataAfterOperate == null) {
			compareObjectLogResults = null;
			return SUCCESS;
		}
		if (NewBaseInfoTables.BASEINFO_KEY.equals(businessType)
				|| NewBaseInfoTables.UNSETTLEDPOPULATION_KEY
						.equals(businessType)
				|| NewBaseInfoTables.OVERSEAPERSONNEL_KEY.equals(businessType)) {
			compareObjectLogResults = CompareObjectUtil.compareAll(
					dataAfterOperate, dataBeforeOperate);
		} else if (NewBaseInfoTables.RENTALHOUSE_KEY.equals(businessType)
				|| NewBaseInfoTables.ACTUALHOUSE_KEY.equals(businessType)
				|| systemOperateLog.getModuleType().equals(
						NewBaseInfoTables.COMPANYPLACEKEY)) {
			compareObjectLogResults = CompareObjectUtil
					.compareHouseInfoBusiness(dataAfterOperate,
							dataBeforeOperate);
		} else {
			compareObjectLogResults = CompareObjectUtil.compareBusiness(
					dataAfterOperate, dataBeforeOperate);
		}
		compareObjectLogResults = produceFieldName(compareObjectLogResults,
				modulKey, dataAfterOperate);
		// if (compareObjectLogResults != null) {
		// systemOperateLog.setContrastState(ContrastState.MODIFIED);
		// systemOperateLog.setOperateDescribe(gson
		// .toJson(compareObjectLogResults));
		// systemOperateLogService
		// .updateSystemOperateLogById(systemOperateLog);
		// } else {
		// systemOperateLog.setContrastState(ContrastState.UNMODIFIED);
		// systemOperateLogService
		// .updateSystemOperateLogById(systemOperateLog);
		// }
		return SUCCESS;
	}

	private Object transforJson(String jsonString, String typeName) {
		try {
			if ((NewBaseInfoTables.AIDSPOPULATIONS_KEY + "s")
					.equalsIgnoreCase(typeName)) {
				typeName = NewBaseInfoTables.AIDSPOPULATIONS_KEY;
			}
			String className = NewBaseInfoTables.classTypeMap.get(typeName);
			return gson.fromJson(jsonString, Class.forName(className));
		} catch (Exception e) {
			logger.error("json转换对象失败" + e);
			return null;
		}
	}

	private List<CompareObjectLog> produceFieldName(
			List<CompareObjectLog> compareObjects, String modulKey,
			Object dataBeforeOperate) {
		if (compareObjects == null || compareObjects.size() < 1) {
			compareObjectLogResults = null;
			return compareObjectLogResults;
		}
		for (CompareObjectLog compareObject : compareObjects) {
			if (CompareObjectUtil.PROPERTYDICT.equals(compareObject.getType())) {
				if (compareObject.getOldValue() != null
						&& !"null".equals(compareObject.getOldValue())) {
					PropertyDict oldValue = propertyDictService
							.getPropertyDictById(Long.valueOf(compareObject
									.getOldValue()));
					if (oldValue != null) {
						compareObject.setOldValue(oldValue.getDisplayName());
						PropertyDomain temp = propertyDomainService
								.getPropertyDomainById(oldValue
										.getPropertyDomain().getId());
						if (temp != null) {
							compareObject.setCname(temp.getDomainName());
						}
					}
				} else {
					compareObject.setOldValue("");
				}
				if (compareObject.getNewValue() != null
						&& !"null".equals(compareObject.getNewValue())) {
					PropertyDict newValue = propertyDictService
							.getPropertyDictById(Long.valueOf(compareObject
									.getNewValue()));
					if (newValue != null) {
						compareObject.setNewValue(newValue.getDisplayName());
						PropertyDomain temp = propertyDomainService
								.getPropertyDomainById(newValue
										.getPropertyDomain().getId());
						if (temp != null) {
							compareObject.setCname(temp.getDomainName());
						}
					}
				} else {
					compareObject.setNewValue("");
				}
			} else if (CompareObjectUtil.ORGANIZATION.equals(compareObject
					.getType())) {
				if (compareObject.getOldValue() != null
						&& !"null".equals(compareObject.getOldValue())) {
					Organization oldValue = organizationDubboService
							.getSimpleOrgById(Long.valueOf(compareObject
									.getOldValue()));
					if (oldValue != null) {
						compareObject.setOldValue(oldValue.getOrgName());
					}
				} else {
					compareObject.setOldValue("");
				}
				if (compareObject.getNewValue() != null
						&& !"null".equals(compareObject.getNewValue())) {
					Organization newValue = organizationDubboService
							.getSimpleOrgById(Long.valueOf(compareObject
									.getNewValue()));
					if (newValue != null) {
						compareObject.setNewValue(newValue.getOrgName());
					}
				} else {
					compareObject.setNewValue("");
				}
				compareObject.setCname("所属网格");
			} else {
				if (compareObject.getOldValue() == null
						|| "null".equals(compareObject.getOldValue())) {
					compareObject.setOldValue("");
				}
				if (compareObject.getNewValue() == null
						|| "null".equals(compareObject.getNewValue())) {
					compareObject.setNewValue("");
				}
				if (modulKey != null
						&& modulKey.equals(NewBaseInfoTables.COMPANYPLACEKEY)) {
					String cname = PeopleCEnameMapping.companyPlaceCEMap
							.get(compareObject.getEname());
					cname = ModulTypes.AREACLUM
							.equals(compareObject.getEname()) ? ModulTypes
							.getAreaByName(((CompanyPlace) dataBeforeOperate)
									.getClassifiCationEn()) : cname;
					compareObject.setCname(cname == null ? compareObject
							.getEname() : cname);
				} else {
					String cname = PeopleCEnameMapping.allPeopleCEMap
							.get(compareObject.getEname());
					compareObject.setCname(cname == null ? compareObject
							.getEname() : cname);
				}
			}
		}
		return compareObjects;
	}

	private PageInfo produceTypeName(PageInfo<SystemOperateLog> pageInfo) {
		List<SystemOperateLog> results = pageInfo.getResult();
		for (SystemOperateLog systemOperateLog : results) {
			systemOperateLog.setModuleType(NewBaseInfoTables
					.getModuleName(systemOperateLog.getModuleType()));
			if (systemOperateLog.getBusinessType() != null
					&& !"".equals(systemOperateLog.getBusinessType())
					&& NewBaseInfoTables.HOUSEHOLDSTAFF_KEY
							.equals(systemOperateLog.getBusinessType())) {
				systemOperateLog.setBusinessType(NewBaseInfoTables
						.getPopulationName(systemOperateLog.getBusinessType()
								+ "Mode"));
			} else if (systemOperateLog.getBusinessType() != null
					&& !"".equals(systemOperateLog.getBusinessType())
					&& NewBaseInfoTables.FLOATINGPOPULATION_KEY
							.equals(systemOperateLog.getBusinessType())) {
				systemOperateLog.setBusinessType(NewBaseInfoTables
						.getPopulationName(systemOperateLog.getBusinessType()
								+ "Mode"));
			} else {
				systemOperateLog.setBusinessType(NewBaseInfoTables
						.getPopulationName(systemOperateLog.getBusinessType()));
			}
			systemOperateLog.setOperateSource(NewBaseInfoTables
					.getPopulationName(systemOperateLog.getOperateSource()));
		}
		pageInfo.setResult(results);
		return pageInfo;
	}

	public SystemOperateLogVo getSystemOperateLogVo() {
		return systemOperateLogVo;
	}

	public void setSystemOperateLogVo(SystemOperateLogVo systemOperateLogVo) {
		this.systemOperateLogVo = systemOperateLogVo;
	}

	public Long getSystemoOperateLogId() {
		return systemoOperateLogId;
	}

	public void setSystemoOperateLogId(Long systemoOperateLogId) {
		this.systemoOperateLogId = systemoOperateLogId;
	}

	public List<CompareObjectLog> getCompareObjectLogResults() {
		return compareObjectLogResults;
	}

	public void setCompareObjectLogResults(
			List<CompareObjectLog> compareObjectLogResults) {
		this.compareObjectLogResults = compareObjectLogResults;
	}

	public String getModulKey() {
		return modulKey;
	}

	public void setModulKey(String modulKey) {
		this.modulKey = modulKey;
	}
}
