package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SearchInfoVo implements Serializable {
	private Date minDate;
	private Date maxDate;
	private String gisType = "3D";

	private String searchValue;
	private Long orgId;
	private Long orgLevelId;
	private String orgInternalCode;
	private ScreenCoordinateVo screenVo;
	private String typeName;
	private String tableName;
	/**String元素为orgId集合的字符串表达式，如'1,2,3,4,5' */
	private List<String> orgIdsList;
	
	public SearchInfoVo() {
		super();
	}
	
	public SearchInfoVo(String gisType, Long orgId, String typeName) {
		super();
		this.gisType = gisType;
		this.orgId = orgId;
		this.typeName = typeName;
	}



	public SearchInfoVo(String gisType, Long orgId,
			ScreenCoordinateVo screenVo, String searchValue, String typeName, String tableName) {
		super();
		this.gisType = gisType;
		this.searchValue = searchValue;
		this.orgId = orgId;
		this.screenVo = screenVo;
		this.typeName = typeName;
		this.tableName = tableName;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public String getGisType() {
		return gisType;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

	public ScreenCoordinateVo getScreenVo() {
		return screenVo;
	}

	public void setScreenVo(ScreenCoordinateVo screenVo) {
		this.screenVo = screenVo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getOrgLevelId() {
		return orgLevelId;
	}

	public void setOrgLevelId(Long orgLevelId) {
		this.orgLevelId = orgLevelId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public List<String> getOrgIdsList() {
		return orgIdsList;
	}

	public void setOrgIdsList(List<String> orgIdsList) {
		this.orgIdsList = orgIdsList;
	}

}
