package com.tianque.baseInfo.buildDatas.domain;

import java.util.List;

import net.sf.json.JSONArray;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class BuildLayout extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String colModel;
	private String colData;
	
	private Long buildId;
	
	private Organization org;
	/**总布局行数*/
	private Long totalRow;
	/**总布局列数*/
	private Long totalCol;
	/**布局信息*/
	private String layoutInfo;
	/**布局信息集合，解析layoutInfo所得*/
	private List<BuildLayoutCell> layoutCellList;
	
	public String getColModel() {
		return colModel;
	}
	public void setColModel(String colModel) {
		this.colModel = colModel;
	}
	public String getColData() {
		return colData;
	}
	public void setColData(String colData) {
		this.colData = colData;
	}
	public Long getBuildId() {
		return buildId;
	}
	public void setBuildId(Long buildId) {
		this.buildId = buildId;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public Long getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Long totalRow) {
		this.totalRow = totalRow;
	}
	public Long getTotalCol() {
		return totalCol;
	}
	public void setTotalCol(Long totalCol) {
		this.totalCol = totalCol;
	}
	public String getLayoutInfo() {
		return layoutInfo;
	}
	public void setLayoutInfo(String layoutInfo) {
		this.layoutInfo = layoutInfo;
		this.layoutCellList = null;
	}
	public List<BuildLayoutCell> getLayoutCellList() {
		if(this.layoutCellList != null){
			return this.layoutCellList;
		}
		if(this.layoutInfo!=null){
			JSONArray json = JSONArray.fromObject(this.layoutInfo);
			return (List) JSONArray.toCollection(json, BuildLayoutCell.class);
		}
		return null;
	}

}
