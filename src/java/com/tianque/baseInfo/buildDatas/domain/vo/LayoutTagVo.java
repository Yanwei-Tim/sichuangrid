/*
 * 文件名称: LayoutTagVo.java
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: N-126
 * 修改日期: 2013-4-17
 * 修改内容:
 */
package com.tianque.baseInfo.buildDatas.domain.vo;

/**
 * 楼宇信息表
 */
@SuppressWarnings("serial")
public class LayoutTagVo {
	/** 编号 */
	private Long id;
	/** 显示名 */
	private String label;
	/** 分类 */
	private String category;
	/**
	 * 建筑物类型
	 * 1房屋
	 * 2单位
	 * 3场所
	 */
	private Integer buildType;
	/** 重点场所类型 */
	private String keyPlaceType;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
