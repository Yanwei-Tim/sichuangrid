package com.tianque.viewObject.vo;

import java.util.List;
import java.util.Map;

import com.tianque.core.base.BaseDomain;

public class ViewObjectVo extends BaseDomain {

	/**
	 * 全部
	 */
	public static final String SELECTALL = "selectAll";
	/**
	 * 按层级
	 */
	public static final String SELECTBYLEVEL = "selectByLevel";
	/**
	 * 按上下级
	 */
	public static final String SELECTBYLINE = "selectByLine";
	/**
	 * 本机
	 */
	public static final String SELFLEVEL = "selfLevel";
	/**
	 * 直属下辖
	 */
	public static final String DIRECTDOWNLEVEL = "directDownLevel";
	/**
	 * 所有下辖
	 */
	public static final String ALLDOWNLEVEL = "allDownLevel";
	/**
	 * 所有上级
	 */
	public static final String ALLUPLEVEL = "allUpLevel";

	private String selectedRadio;// 哪个radio被选中，全部、按层级、按上下级

	private String[] selectedCheckBoxs;// 哪个checkBox被选择，包括省，市，县
	private String selectedCheckBoxStrs;
	private String selectedIdStrs;// 选择的id数组
	private String exclusiveIdStrs;// 排除的id数组

	private String selectedLevel;// 哪个层级被选中，本机，直属下辖，所有下辖，所有上级
	/**
	 * 每个层级实际被选中的个数统计
	 */
	private Integer provinceNum;
	private Integer cityNum;
	private Integer districtNum;
	private Integer townNum;
	private Integer villageNum;
	private Integer gridNum;
	private Integer provinceFucDepartmentNum;
	private Integer cityFucDepartmentNum;
	private Integer districtFucDepartmentNum;
	private Integer townFucDepartmentNum;

	/**
	 * 每个层级实际被选中的个数统计
	 */
	private Integer defProvinceNum;
	private Integer defCityNum;
	private Integer defDistrictNum;
	private Integer defTownNum;
	private Integer defVillageNum;
	private Integer defGridNum;
	private Integer defProvinceFucDepartmentNum;
	private Integer defCityFucDepartmentNum;
	private Integer defDistrictFucDepartmentNum;
	private Integer defTownFucDepartmentNum;

	private List<Map<String, String>> selectedOrgLevelTypeMapList;

	public Integer getProvinceNum() {
		return provinceNum;
	}

	public void setProvinceNum(Integer provinceNum) {
		this.provinceNum = provinceNum;
	}

	public Integer getCityNum() {
		return cityNum;
	}

	public void setCityNum(Integer cityNum) {
		this.cityNum = cityNum;
	}

	public Integer getDistrictNum() {
		return districtNum;
	}

	public void setDistrictNum(Integer districtNum) {
		this.districtNum = districtNum;
	}

	public Integer getTownNum() {
		return townNum;
	}

	public void setTownNum(Integer townNum) {
		this.townNum = townNum;
	}

	public Integer getVillageNum() {
		return villageNum;
	}

	public void setVillageNum(Integer villageNum) {
		this.villageNum = villageNum;
	}

	public Integer getGridNum() {
		return gridNum;
	}

	public void setGridNum(Integer gridNum) {
		this.gridNum = gridNum;
	}

	public String getSelectedRadio() {
		return selectedRadio;
	}

	public void setSelectedRadio(String selectedRadio) {
		this.selectedRadio = selectedRadio;
	}

	public String[] getSelectedCheckBoxs() {
		return selectedCheckBoxs;
	}

	public void setSelectedCheckBoxs(String[] selectedCheckBoxs) {
		this.selectedCheckBoxs = selectedCheckBoxs;
	}

	public String getSelectedLevel() {
		return selectedLevel;
	}

	public void setSelectedLevel(String selectedLevel) {
		this.selectedLevel = selectedLevel;
	}

	public Integer getProvinceFucDepartmentNum() {
		return provinceFucDepartmentNum;
	}

	public void setProvinceFucDepartmentNum(Integer provinceFucDepartmentNum) {
		this.provinceFucDepartmentNum = provinceFucDepartmentNum;
	}

	public Integer getCityFucDepartmentNum() {
		return cityFucDepartmentNum;
	}

	public void setCityFucDepartmentNum(Integer cityFucDepartmentNum) {
		this.cityFucDepartmentNum = cityFucDepartmentNum;
	}

	public Integer getDistrictFucDepartmentNum() {
		return districtFucDepartmentNum;
	}

	public void setDistrictFucDepartmentNum(Integer districtFucDepartmentNum) {
		this.districtFucDepartmentNum = districtFucDepartmentNum;
	}

	public Integer getTownFucDepartmentNum() {
		return townFucDepartmentNum;
	}

	public void setTownFucDepartmentNum(Integer townFucDepartmentNum) {
		this.townFucDepartmentNum = townFucDepartmentNum;
	}

	public String getSelectedCheckBoxStrs() {
		return selectedCheckBoxStrs;
	}

	public void setSelectedCheckBoxStrs(String selectedCheckBoxStrs) {
		this.selectedCheckBoxStrs = selectedCheckBoxStrs;
	}

	public String getSelectedIdStrs() {
		return selectedIdStrs;
	}

	public void setSelectedIdStrs(String selectedIdStrs) {
		this.selectedIdStrs = selectedIdStrs;
	}

	public String getExclusiveIdStrs() {
		return exclusiveIdStrs;
	}

	public void setExclusiveIdStrs(String exclusiveIdStrs) {
		this.exclusiveIdStrs = exclusiveIdStrs;
	}

	public Integer getDefProvinceNum() {
		return defProvinceNum;
	}

	public void setDefProvinceNum(Integer defProvinceNum) {
		this.defProvinceNum = defProvinceNum;
	}

	public Integer getDefCityNum() {
		return defCityNum;
	}

	public void setDefCityNum(Integer defCityNum) {
		this.defCityNum = defCityNum;
	}

	public Integer getDefDistrictNum() {
		return defDistrictNum;
	}

	public void setDefDistrictNum(Integer defDistrictNum) {
		this.defDistrictNum = defDistrictNum;
	}

	public Integer getDefTownNum() {
		return defTownNum;
	}

	public void setDefTownNum(Integer defTownNum) {
		this.defTownNum = defTownNum;
	}

	public Integer getDefVillageNum() {
		return defVillageNum;
	}

	public void setDefVillageNum(Integer defVillageNum) {
		this.defVillageNum = defVillageNum;
	}

	public Integer getDefGridNum() {
		return defGridNum;
	}

	public void setDefGridNum(Integer defGridNum) {
		this.defGridNum = defGridNum;
	}

	public Integer getDefProvinceFucDepartmentNum() {
		return defProvinceFucDepartmentNum;
	}

	public void setDefProvinceFucDepartmentNum(Integer defProvinceFucDepartmentNum) {
		this.defProvinceFucDepartmentNum = defProvinceFucDepartmentNum;
	}

	public Integer getDefCityFucDepartmentNum() {
		return defCityFucDepartmentNum;
	}

	public void setDefCityFucDepartmentNum(Integer defCityFucDepartmentNum) {
		this.defCityFucDepartmentNum = defCityFucDepartmentNum;
	}

	public Integer getDefDistrictFucDepartmentNum() {
		return defDistrictFucDepartmentNum;
	}

	public void setDefDistrictFucDepartmentNum(Integer defDistrictFucDepartmentNum) {
		this.defDistrictFucDepartmentNum = defDistrictFucDepartmentNum;
	}

	public Integer getDefTownFucDepartmentNum() {
		return defTownFucDepartmentNum;
	}

	public void setDefTownFucDepartmentNum(Integer defTownFucDepartmentNum) {
		this.defTownFucDepartmentNum = defTownFucDepartmentNum;
	}

	public String getSelectedObjectStatStr() {
		StringBuilder stb = new StringBuilder();
		if (null != provinceNum && provinceNum != 0) {
			stb.append("省级").append(provinceNum).append("个");
		}
		if (null != cityNum && cityNum != 0) {
			stb.append(",市级").append(cityNum).append("个");
		}
		if (null != districtNum && districtNum != 0) {
			stb.append(",县区级").append(districtNum).append("个");
		}
		if (null != townNum && townNum != 0) {
			stb.append(",乡镇").append(townNum).append("个");
		}
		if (null != villageNum && villageNum != 0) {
			stb.append(",乡村").append(villageNum).append("个");
		}
		if (null != gridNum && gridNum != 0) {
			stb.append(",网格").append(gridNum).append("个");
		}
		if (null != provinceFucDepartmentNum && provinceFucDepartmentNum != 0) {
			stb.append(",省级职能部门").append(provinceFucDepartmentNum).append("个");
		}
		if (null != cityFucDepartmentNum && cityFucDepartmentNum != 0) {
			stb.append(",市级职能部门").append(cityFucDepartmentNum).append("个");
		}
		if (null != districtFucDepartmentNum && districtFucDepartmentNum != 0) {
			stb.append(",县区级职能部门").append(districtFucDepartmentNum).append("个");
		}
		if (null != townFucDepartmentNum && townFucDepartmentNum != 0) {
			stb.append(",乡镇职能工作站").append(townFucDepartmentNum).append("个");
		}
		if (stb.indexOf(",") == 0) {
			return stb.substring(1);
		}
		return stb.toString();
	}

	public List<Map<String, String>> getSelectedOrgLevelTypeMapList() {
		return selectedOrgLevelTypeMapList;
	}

	public void setSelectedOrgLevelTypeMapList(List<Map<String, String>> selectedOrgLevelTypeMapList) {
		this.selectedOrgLevelTypeMapList = selectedOrgLevelTypeMapList;
	}

}
