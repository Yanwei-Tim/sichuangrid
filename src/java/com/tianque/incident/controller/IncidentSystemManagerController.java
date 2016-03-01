package com.tianque.incident.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.incident.dao.IncidentSystemManagerDao;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.component.IncidentDegreeRule;
import com.tianque.incident.service.IncidentSystemManagerService;
import com.tianque.incident.vo.IncidentTypeTreeData;
import com.tianque.sysadmin.controller.OrganizationController;
import com.tianque.sysadmin.service.PropertyDictService;

@Namespace("/incident/systemManage")
@Transactional
@Scope("request")
@Controller("incidentSystemManagerController")
public class IncidentSystemManagerController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(OrganizationController.class);

	private List<IncidentType> incidentTypes;

	private IncidentType incidentType;

	private List<PropertyDict> propertyDicts;

	private Long subjectionTypeId;
	private Long incidentTypeId;
	private boolean degreed;
	private boolean showDegreeRule = true;
	private boolean excludeRoot;
	private List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
	@Autowired
	private IncidentSystemManagerService incidentsManagerService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IncidentSystemManagerDao incidentSystemManagerDao;

	/**
	 * 根据mode类型来判断操作是新增还是修改
	 * 
	 * @return
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/incident/incidentManage/maininIcidentType.jsp"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String dispatchIncidentTypeOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			incidentType = new IncidentType();
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			incidentType = incidentsManagerService
					.getIncidentTypeById(incidentTypeId);
		}
		return SUCCESS;
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "addIncidentType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"incidentType", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addIncidentType() throws Exception {
		if (incidentType == null || incidentType.getDegreed() == null
				|| !incidentType.getDegreed()) {
			incidentType.setDegreed(false);
			incidentType = incidentsManagerService
					.addIncidentType(incidentType);
		} else if (incidentType.getDegreed()) {
			List<IncidentDegreeRule> IncidentDegreeRules = incidentType
					.getDegreeRule();
			incidentType = incidentsManagerService
					.addIncidentType(incidentType);
			addIncidentDegreeRules(incidentType, IncidentDegreeRules);
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	@Action(value = "updateIncidentType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"incidentType", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateIncidentType() throws Exception {
		if (incidentType == null || incidentType.getDegreed() == null
				|| !incidentType.getDegreed()) {
			incidentSystemManagerDao
					.deleteIncidentDegreeRuleByIncidentTypeId(incidentType
							.getId());
			incidentType.setDegreed(false);
			incidentType = incidentsManagerService
					.updateIncidentType(incidentType);
		} else if (incidentType.getDegreed()) {
			List<IncidentDegreeRule> IncidentDegreeRules = incidentType
					.getDegreeRule();
			incidentType = incidentsManagerService
					.updateIncidentType(incidentType);
			updateIncidentDegreeRules(IncidentDegreeRules, incidentType);
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return success
	 */
	@Action(value = "deleteIncidentType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"incidentTypeId" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteIncidentType() throws Exception {
		incidentsManagerService.deleteIncidentType(incidentTypeId);
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action(value = "viewIncidentType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"incidentType", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			// @Result(name="success", location="/incident/incidentManage/viewIncidentType.jsp"),
			@Result(name = "error", type = "json", params = { "roor",
					"errorMessage" }) })
	public String viewIncidentType() throws Exception {
		incidentType = incidentsManagerService
				.getIncidentTypeById(incidentTypeId);
		return SUCCESS;
	}

	@Action(value = "hasDuplicateIncidentType", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateIncidentType() throws Exception {
		if (incidentType.getSubjection() == null
				|| incidentType.getSubjection().getId() == null) {
			return ERROR;
		} else {
			String str = incidentsManagerService.hasDuplicateIncidentType(
					incidentType.getSubjection().getId(),
					incidentType.getName(), incidentType.getId()) ? SUCCESS
					: ERROR;
			return str;
		}
	}

	/**
	 * 第一次请求 加载树
	 * 
	 * @return success
	 */

	@Action(value = "firstLoadExtTreeData", results = {
			@Result(name = "success", params = { "root", "extTreeDatas",
					"igonreHierarchy", "false", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String firstLoadExtTreeData() throws Exception {
		if (excludeRoot) {
			ExtTreeData rootExtTreeData = new IncidentTypeTreeData();
			rootExtTreeData.setChildren(getChildIncidents());
			extTreeDatas.add(rootExtTreeData);

			// ExtTreeData extTreeData = extTreeDatas.get(0);
			// extTreeData.setChildren(getChildIncidents());
		} else {
			propertyDicts = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.INCIDENT_SUBJECTION);
			for (PropertyDict propertyDict : propertyDicts) {
				ExtTreeData extTreeData = new IncidentTypeTreeData(
						propertyDict, false);
				extTreeDatas.add(extTreeData);
			}
		}
		return SUCCESS;
	}

	private List<ExtTreeData> getChildIncidents() {
		propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.INCIDENT_SUBJECTION);
		List<ExtTreeData> childextTreeDatas = new ArrayList<ExtTreeData>();
		for (PropertyDict propertyDict : propertyDicts) {
			ExtTreeData extTreeData = new IncidentTypeTreeData(propertyDict,
					false);
			childextTreeDatas.add(extTreeData);
		}
		return childextTreeDatas;
	}

	/**
	 * 异步加载 预警树
	 * 
	 * @return success
	 */
	@Action(value = "ajaxIncidentsForExtTree", results = {
			@Result(name = "success", params = { "root", "extTreeDatas",
					"ignoreHierarchy", "flase", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMEssage" }, type = "json") })
	public String ajaxIncidentsForExtTree() throws Exception {
		if (degreed) {
			List<PropertyDict> propertyDicts = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.INCIDENT_DEGREE);
			for (PropertyDict propertyDict : propertyDicts) {
				ExtTreeData extTreeData = new IncidentTypeTreeData(
						propertyDict, true);
				extTreeDatas.add(extTreeData);
			}
		} else {
			List<IncidentType> incidentTypeList = incidentsManagerService
					.getIncidentsBysubjectionTypeId(subjectionTypeId);
			ajaxIncidentForExtTree(incidentTypeList, showDegreeRule);
		}
		return SUCCESS;
	}

	private void addIncidentDegreeRules(IncidentType incidentType,
			List<IncidentDegreeRule> IncidentDegreeRules) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.INCIDENT_DEGREE);
		for (int j = 0; j < IncidentDegreeRules.size(); j++) {
			IncidentDegreeRules.get(j).setDegree(propertyDicts.get(j));
		}
		for (IncidentDegreeRule incidentDegreeRule : IncidentDegreeRules) {
			if (incidentType == null || incidentType.getId() == null) {
				throw new NullPointerException(
						"新增预警详细规则时,获取incidentType.getId()异常，为空");
			} else {
				incidentDegreeRule.setIncidentTypeId(incidentType.getId());
				incidentsManagerService
						.addIncidentDegreeRule(incidentDegreeRule);
			}
		}
	}

	/**
	 * 由于没有Id 所以更新时，采取先删除，在插入
	 */
	private void updateIncidentDegreeRules(
			List<IncidentDegreeRule> incidentDegreeRules,
			IncidentType incidentType) {
		try {
			incidentSystemManagerDao
					.deleteIncidentDegreeRuleByIncidentTypeId(incidentType
							.getId());
			List<PropertyDict> propertyDicts = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.INCIDENT_DEGREE);
			for (int j = 0; j < incidentDegreeRules.size(); j++) {
				incidentDegreeRules.get(j).setDegree(propertyDicts.get(j));
			}
			for (IncidentDegreeRule incidentDegreeRule : incidentDegreeRules) {
				incidentDegreeRule.setIncidentTypeId(incidentType.getId());
				incidentsManagerService
						.updateIncidentDegreeRule(incidentDegreeRule);
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
			logger.error("异常信息", e);
		}
	}

	private void ajaxIncidentForExtTree(List<IncidentType> incidentTypeList,
			boolean showDegreeRule) {
		for (IncidentType incidentType : incidentTypeList) {
			ExtTreeData extTreeData = new IncidentTypeTreeData(incidentType,
					showDegreeRule);
			extTreeDatas.add(extTreeData);
		}
	}

	public List<ExtTreeData> getExtTreeDatas() {
		return extTreeDatas;
	}

	public void setExtTreeDatas(List<ExtTreeData> extTreeDatas) {
		this.extTreeDatas = extTreeDatas;
	}

	public List<IncidentType> getIncidentTypes() {
		return incidentTypes;
	}

	public void setIncidentTypes(List<IncidentType> incidentTypes) {
		this.incidentTypes = incidentTypes;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public Long getSubjectionTypeId() {
		return subjectionTypeId;
	}

	public void setSubjectionTypeId(Long subjectionTypeId) {
		this.subjectionTypeId = subjectionTypeId;
	}

	public List<PropertyDict> getPropertyDicts() {
		return propertyDicts;
	}

	public void setPropertyDicts(List<PropertyDict> propertyDicts) {
		this.propertyDicts = propertyDicts;
	}

	public Long getIncidentTypeId() {
		return incidentTypeId;
	}

	public void setIncidentTypeId(Long incidentTypeId) {
		this.incidentTypeId = incidentTypeId;
	}

	public boolean isDegreed() {
		return degreed;
	}

	public void setDegreed(boolean degreed) {
		this.degreed = degreed;
	}

	public boolean isShowDegreeRule() {
		return showDegreeRule;
	}

	public void setShowDegreeRule(boolean showDegreeRule) {
		this.showDegreeRule = showDegreeRule;
	}

	public boolean isExcludeRoot() {
		return excludeRoot;
	}

	public void setExcludeRoot(boolean excludeRoot) {
		this.excludeRoot = excludeRoot;
	}
}
