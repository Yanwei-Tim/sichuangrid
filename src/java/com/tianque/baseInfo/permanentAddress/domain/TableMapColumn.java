package com.tianque.baseInfo.permanentAddress.domain;

/****
 * 表和字段对应关系
 * 
 * @author longzhendong
 * 
 */
public class TableMapColumn {

	private String tableName;
	private String provinceColumnName;// 省对应的字段名
	private String cityColumnName;// 市对应的字段名
	private String districtColumnName;// 县对应的字段名

	public TableMapColumn(String tableName, String provinceColumnName,
			String cityColumnName, String districtColumnName) {
		this.tableName = tableName;
		this.provinceColumnName = provinceColumnName;
		this.cityColumnName = cityColumnName;
		this.districtColumnName = districtColumnName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getProvinceColumnName() {
		return provinceColumnName;
	}

	public void setProvinceColumnName(String provinceColumnName) {
		this.provinceColumnName = provinceColumnName;
	}

	public String getCityColumnName() {
		return cityColumnName;
	}

	public void setCityColumnName(String cityColumnName) {
		this.cityColumnName = cityColumnName;
	}

	public String getDistrictColumnName() {
		return districtColumnName;
	}

	public void setDistrictColumnName(String districtColumnName) {
		this.districtColumnName = districtColumnName;
	}
}
