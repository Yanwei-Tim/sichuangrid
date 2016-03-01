package com.tianque.incident.vo;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.domain.PropertyDict;
import com.tianque.incident.domain.IncidentType;
import com.tianque.sysadmin.service.PropertyDictService;

public class IncidentTypeTreeData extends ExtTreeData {

	private String name;

	/** 字典Id */
	private Long propertyDictId;
	private int seq;

	private PropertyDict subjection;
	private Boolean degreed;
	private Boolean expandable;

	/**
	 * 是否可以新增 ，默认为false, 当为true时在读取 node.id 作为 incidentTypeId, 当为false时 返回null
	 **/
	private boolean enableAddIncident = false;
	/**
	 * 默认false, 当为true 时 读取 时间分级作为第三层树节点，否则 不读取。
	 */
	private final static boolean showDegree = false;
	@Autowired
	private PropertyDictService propertyDictService;

	public IncidentTypeTreeData() {
		setText("全部");
		setId(999999999999999999L);
		cls = "folder";
		level = 0;
		enableAddIncident = true;
		expandable = true;
		expanded = true;
		leaf = false;
	}

	public IncidentTypeTreeData(PropertyDict propertyDict, boolean isLeaf) {
		seq = propertyDict.getDisplaySeq();
		setText(propertyDict.getDisplayName());
		propertyDictId = propertyDict.getId();
		enableAddIncident = true;
		FirstconvertNodeAttribute(isLeaf);
	}

	public IncidentTypeTreeData(IncidentType incidentType,
			boolean ShowDegreeRule) {
		text = incidentType.getName();
		id = incidentType.getId();
		seq = incidentType.getSeq();
		propertyDictId = incidentType.getSubjection().getId();
		degreed = incidentType.getDegreed();
		enableAddIncident = false;
		ajaxConvertNodeAttribute(incidentType, ShowDegreeRule);
	}

	private void ajaxConvertNodeAttribute(IncidentType incidentType,
			boolean ShowDegreeRule) {
		level = 2;
		expanded = false;
		if (ShowDegreeRule) {
			if (incidentType.getDegreed()) {
				leaf = false;
				cls = "folder";
			} else {
				leaf = true;
				cls = "folder";
				icon = ServletActionContext.getRequest().getContextPath()
						+ FOLDER_LEAF;
			}
		} else {
			cls = "file";
			leaf = true;
			expanded = false;
			icon = ServletActionContext.getRequest().getContextPath()
					+ FUN_LEAF;
		}
	}

	private void FirstconvertNodeAttribute(boolean isLeaf) {
		if (!isLeaf) {
			leaf = false;
			level = 1;

		} else {
			leaf = true;
			level = 3;
		}
		expanded = false;
		cls = "folder";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyDict getSubjection() {
		return subjection;
	}

	public void setSubjection(PropertyDict subjection) {
		this.subjection = subjection;
	}

	public Boolean getDegreed() {
		return degreed;
	}

	public void setDegreed(Boolean degreed) {
		this.degreed = degreed;
	}

	public PropertyDictService getPropertyDictService() {
		return propertyDictService;
	}

	public void setPropertyDictService(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	public Long getPropertyDictId() {
		return propertyDictId;
	}

	public void setPropertyDictId(Long propertyDictId) {
		this.propertyDictId = propertyDictId;
	}

	public boolean isEnableAddIncident() {
		return enableAddIncident;
	}

	public void setEnableAddIncident(boolean enableAddIncident) {
		this.enableAddIncident = enableAddIncident;
	}

	public static boolean isShowdegree() {
		return showDegree;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

}
