package com.tianque.openLayersMap.domian;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

/**
 * gis功能类别管理实体类(精确搜索、辖区分布、图层搜索)
 * 
 * @author yubin
 *
 */
@SuppressWarnings("serial")
public class GisFunction extends BaseDomain {
	/** 绑定事件中文名称 */
	private String boundEventName;

	/** 未绑事件中文名称 */
	private String unBoundEventName;

	/** 绑定事件 */
	private PropertyDict event;

	/** 绑定事件是否有效 */
	private Boolean boundEventIsValid;

	/** 未绑事件是否有效 */
	private Boolean unBoundEventIsValid;

	/** 主表id */
	private Long moduleId;

	/** 子表id */
	private Long sonClassId;

	/** 图标路径 */
	private String iconUrl;

	/** 是否显示图标 */
	private Boolean isViewIcon;

	/** 标题名称 */
	private String titleName;

	/** 标题内容 */
	private String titleValue;

	/** 详情查看url */
	private String detailsUrl;

	/** 要显示的字段名称A */
	private String fieldNameA;

	/** 字段属性A */
	private String fieldA;

	/** 要显示的字段名称B */
	private String fieldNameB;

	/** 字段属性B */
	private String fieldB;

	/** 要显示的字段名称C */
	private String fieldNameC;

	/** 字段属性C */
	private String fieldC;

	/** 要显示的字段名称D */
	private String fieldNameD;

	/** 字段属性D */
	private String fieldD;

	/** 要显示的字段名称E */
	private String fieldNameE;

	/** 字段属性E */
	private String fieldE;

	/** 搜索条件A */
	private String searchFieldA;

	/** 搜索条件B */
	private String searchFieldB;

	/** 搜索条件C */
	private String searchFieldC;

	/** 搜索条件A中文名 */
	private String searchFieldAName;

	/** 搜索条件B中文名 */
	private String searchFieldBName;

	/** 搜索条件C中文名 */
	private String searchFieldCName;
	
	/** 功能类型 */
	private String functionType;
	
	/**要显示的详情查看内容字段名称A*/
	private String detailFieldNameA;
	
	/**详情查看内容字段属性A*/
	private String detailFieldA;
	
	/**要显示的详情查看内容字段名称B*/
	private String detailFieldNameB;
	
	/**详情查看内容字段属性B*/
	private String detailFieldB;
	
	/**要显示的详情查看内容字段名称C*/
	private String detailFieldNameC;
	
	/**详情查看内容字段属性C*/
	private String detailFieldC;
	
	/**要显示的详情查看内容字段名称D*/
	private String detailFieldNameD;
	
	/**详情查看内容字段属性D*/
	private String detailFieldD;
	
	/**要显示的详情查看内容字段名称E*/
	private String detailFieldNameE;
	
	/**详情查看内容字段属性E*/
	private String detailFieldE;

	public PropertyDict getEvent() {
		return event;
	}

	public void setEvent(PropertyDict event) {
		this.event = event;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getSonClassId() {
		return sonClassId;
	}

	public void setSonClassId(Long sonClassId) {
		this.sonClassId = sonClassId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Boolean getIsViewIcon() {
		return isViewIcon;
	}

	public void setIsViewIcon(Boolean isViewIcon) {
		this.isViewIcon = isViewIcon;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleValue() {
		return titleValue;
	}

	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}

	public String getDetailsUrl() {
		return detailsUrl;
	}

	public void setDetailsUrl(String detailsUrl) {
		this.detailsUrl = detailsUrl;
	}

	public String getFieldNameA() {
		return fieldNameA;
	}

	public void setFieldNameA(String fieldNameA) {
		this.fieldNameA = fieldNameA;
	}

	public String getFieldA() {
		return fieldA;
	}

	public void setFieldA(String fieldA) {
		this.fieldA = fieldA;
	}

	public String getFieldNameB() {
		return fieldNameB;
	}

	public void setFieldNameB(String fieldNameB) {
		this.fieldNameB = fieldNameB;
	}

	public String getFieldB() {
		return fieldB;
	}

	public void setFieldB(String fieldB) {
		this.fieldB = fieldB;
	}

	public String getFieldNameC() {
		return fieldNameC;
	}

	public void setFieldNameC(String fieldNameC) {
		this.fieldNameC = fieldNameC;
	}

	public String getFieldC() {
		return fieldC;
	}

	public void setFieldC(String fieldC) {
		this.fieldC = fieldC;
	}

	public String getFieldNameD() {
		return fieldNameD;
	}

	public void setFieldNameD(String fieldNameD) {
		this.fieldNameD = fieldNameD;
	}

	public String getFieldD() {
		return fieldD;
	}

	public void setFieldD(String fieldD) {
		this.fieldD = fieldD;
	}

	public String getFieldNameE() {
		return fieldNameE;
	}

	public void setFieldNameE(String fieldNameE) {
		this.fieldNameE = fieldNameE;
	}

	public String getFieldE() {
		return fieldE;
	}

	public void setFieldE(String fieldE) {
		this.fieldE = fieldE;
	}

	public String getSearchFieldA() {
		return searchFieldA;
	}

	public void setSearchFieldA(String searchFieldA) {
		this.searchFieldA = searchFieldA;
	}

	public String getSearchFieldB() {
		return searchFieldB;
	}

	public void setSearchFieldB(String searchFieldB) {
		this.searchFieldB = searchFieldB;
	}

	public String getSearchFieldC() {
		return searchFieldC;
	}

	public void setSearchFieldC(String searchFieldC) {
		this.searchFieldC = searchFieldC;
	}

	public String getSearchFieldAName() {
		return searchFieldAName;
	}

	public void setSearchFieldAName(String searchFieldAName) {
		this.searchFieldAName = searchFieldAName;
	}

	public String getSearchFieldBName() {
		return searchFieldBName;
	}

	public void setSearchFieldBName(String searchFieldBName) {
		this.searchFieldBName = searchFieldBName;
	}

	public String getSearchFieldCName() {
		return searchFieldCName;
	}

	public void setSearchFieldCName(String searchFieldCName) {
		this.searchFieldCName = searchFieldCName;
	}

	public String getBoundEventName() {
		return boundEventName;
	}

	public void setBoundEventName(String boundEventName) {
		this.boundEventName = boundEventName;
	}

	public String getUnBoundEventName() {
		return unBoundEventName;
	}

	public void setUnBoundEventName(String unBoundEventName) {
		this.unBoundEventName = unBoundEventName;
	}

	public Boolean getBoundEventIsValid() {
		return boundEventIsValid;
	}

	public void setBoundEventIsValid(Boolean boundEventIsValid) {
		this.boundEventIsValid = boundEventIsValid;
	}

	public Boolean getUnBoundEventIsValid() {
		return unBoundEventIsValid;
	}

	public void setUnBoundEventIsValid(Boolean unBoundEventIsValid) {
		this.unBoundEventIsValid = unBoundEventIsValid;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getDetailFieldNameA() {
		return detailFieldNameA;
	}

	public void setDetailFieldNameA(String detailFieldNameA) {
		this.detailFieldNameA = detailFieldNameA;
	}

	public String getDetailFieldA() {
		return detailFieldA;
	}

	public void setDetailFieldA(String detailFieldA) {
		this.detailFieldA = detailFieldA;
	}

	public String getDetailFieldNameB() {
		return detailFieldNameB;
	}

	public void setDetailFieldNameB(String detailFieldNameB) {
		this.detailFieldNameB = detailFieldNameB;
	}

	public String getDetailFieldB() {
		return detailFieldB;
	}

	public void setDetailFieldB(String detailFieldB) {
		this.detailFieldB = detailFieldB;
	}

	public String getDetailFieldNameC() {
		return detailFieldNameC;
	}

	public void setDetailFieldNameC(String detailFieldNameC) {
		this.detailFieldNameC = detailFieldNameC;
	}

	public String getDetailFieldC() {
		return detailFieldC;
	}

	public void setDetailFieldC(String detailFieldC) {
		this.detailFieldC = detailFieldC;
	}

	public String getDetailFieldNameD() {
		return detailFieldNameD;
	}

	public void setDetailFieldNameD(String detailFieldNameD) {
		this.detailFieldNameD = detailFieldNameD;
	}

	public String getDetailFieldD() {
		return detailFieldD;
	}

	public void setDetailFieldD(String detailFieldD) {
		this.detailFieldD = detailFieldD;
	}

	public String getDetailFieldNameE() {
		return detailFieldNameE;
	}

	public void setDetailFieldNameE(String detailFieldNameE) {
		this.detailFieldNameE = detailFieldNameE;
	}

	public String getDetailFieldE() {
		return detailFieldE;
	}

	public void setDetailFieldE(String detailFieldE) {
		this.detailFieldE = detailFieldE;
	}
	

}
