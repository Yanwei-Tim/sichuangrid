package com.tianque.plugin.analyzing.domain;

import java.io.Serializable;
import java.util.Map;

import com.tianque.domain.PropertyDict;

/**
 * 实有人口类型分布统计的条件
 * */
public class StatisticSearchPopulationVo implements Serializable, Cloneable {
	private String type;// 要查询的表的标示
	private long orgId;// 组织机构id（parentId）
	private String orginternalcode;// 组织机构code
	private String orgName;
	private String table;// 要查询的表
	private String tableDisplayName;// 表的中文名
	private String propertyField;// 属性的字段
	private int year;
	private int month;
	private String startDate;// 本月的第一天
	private String endDate;// 下个月的第一天
	private String domainName;// 大类的属性值，比如“户籍人员”
	private String displayName;// 小类的属性值 比如人户同在、户在人不在等
	private PropertyDict propertyDict;// 小类对象
	private String countField;// 要进行统计的字段
	private Long monthCreate;// 本月新增的数据数量
	private Long total;// 总数量
	private Long sumNum;// 小类人员总数量
	private String organizationLevel = "网格分级";
	private String organizationType = "网格类型";
	private Long logOut = 0l;// 是否注销
	private Map<String, Object> countFieldMap;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getOrginternalcode() {
		return orginternalcode;
	}

	public void setOrginternalcode(String orginternalcode) {
		this.orginternalcode = orginternalcode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTableDisplayName() {
		return tableDisplayName;
	}

	public void setTableDisplayName(String tableDisplayName) {
		this.tableDisplayName = tableDisplayName;
	}

	public String getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(String propertyField) {
		this.propertyField = propertyField;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public PropertyDict getPropertyDict() {
		return propertyDict;
	}

	public void setPropertyDict(PropertyDict propertyDict) {
		this.propertyDict = propertyDict;
	}

	public String getCountField() {
		return countField;
	}

	public void setCountField(String countField) {
		this.countField = countField;
	}

	public Long getMonthCreate() {
		return monthCreate;
	}

	public void setMonthCreate(Long monthCreate) {
		this.monthCreate = monthCreate;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getSumNum() {
		return sumNum;
	}

	public void setSumNum(Long sumNum) {
		this.sumNum = sumNum;
	}

	public String getOrganizationLevel() {
		return organizationLevel;
	}

	public void setOrganizationLevel(String organizationLevel) {
		this.organizationLevel = organizationLevel;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public Map<String, Object> getCountFieldMap() {
		return countFieldMap;
	}

	public void setCountFieldMap(Map<String, Object> countFieldMap) {
		this.countFieldMap = countFieldMap;
	}

	@Override
	public StatisticSearchPopulationVo clone() {
		try {
			return (StatisticSearchPopulationVo) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return this;
	}

}
