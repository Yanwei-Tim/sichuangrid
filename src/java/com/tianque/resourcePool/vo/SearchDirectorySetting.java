package com.tianque.resourcePool.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;
import com.tianque.resourcePool.domain.DirectorySetting;

public class SearchDirectorySetting extends BaseDomain {
	/** 上级个人目录 */
	private DirectorySetting parentPersonalDirectory;
	private PropertyDict type;
	/** 资料库类型(个人资料类型为null) */
	private PropertyDict resourcePoolType;
	/** 排序 */
	private Integer indexId;
	/** 创建目录用户ID */
	private Long userId;
	/** 目录等级 */
	private int level;
	private String name;
	/**
	 * 简拼
	 */
	private String simplePinyin;
	/**
	 * 全拼
	 */
	private String fullPinyin;
	/**
	 * 子元素个数
	 */
	private int count;

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public PropertyDict getResourcePoolType() {
		return resourcePoolType;
	}

	public void setResourcePoolType(PropertyDict resourcePoolType) {
		this.resourcePoolType = resourcePoolType;
	}

	public DirectorySetting getParentPersonalDirectory() {
		return parentPersonalDirectory;
	}

	public void setParentPersonalDirectory(DirectorySetting parentPersonalDirectory) {
		this.parentPersonalDirectory = parentPersonalDirectory;
	}

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
