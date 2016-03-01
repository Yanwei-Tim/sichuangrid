package com.tianque.baseInfo.buildDatas.domain;


import com.tianque.core.base.BaseDomain;
/**
 * 楼宇布局 每个单元格信息
 */
public class BuildLayoutCell extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/**第几行*/
	private Long row;
	/**第几列*/
	private Long col;
	/**占几列*/
	private Long colSpan;
	/**占几行*/
	private Long rowSpan;
	/**房间名称*/
	private String room;
	/**房屋ID*/
	private Long housePropertyId;
	/**
	 * 建筑物类型
	 * 1房屋
	 * 2单位
	 * 3场所
	 */
	private Integer buildType;
	/** 重点场所类型 */
	private String keyPlaceType;
	public Long getRow() {
		return row;
	}
	public void setRow(Long row) {
		this.row = row;
	}
	public Long getCol() {
		return col;
	}
	public void setCol(Long col) {
		this.col = col;
	}
	public Long getColSpan() {
		return colSpan;
	}
	public void setColSpan(Long colSpan) {
		this.colSpan = colSpan;
	}
	public Long getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(Long rowSpan) {
		this.rowSpan = rowSpan;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Long getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(Long housePropertyId) {
		this.housePropertyId = housePropertyId;
	}
	public Integer getBuildType() {
		return buildType;
	}
	public void setBuildType(Integer buildType) {
		this.buildType = buildType;
	}
	public String getKeyPlaceType() {
		return keyPlaceType;
	}
	public void setKeyPlaceType(String keyPlaceType) {
		this.keyPlaceType = keyPlaceType;
	}
}
