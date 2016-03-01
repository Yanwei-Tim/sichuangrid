package com.tianque.xichang.working.peopleAspiration.domain;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.xichang.working.domain.CommonWorking;

/**
 * 民生诉求表:实体类(PEOPLEASPIRATIONS)
 * 
 * @author
 * @date 2013-12-23 17:57:24
 */
public class PeopleAspirations extends CommonWorking implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 反应群体人数(RESPONSEGROUPNO) */
	private Integer responseGroupNo;
	/** 诉求人类别(APPEALHUMANTYPE) */
	private PropertyDict appealHumanType;
	/** 项目类别(大类)(ITEMCATEGORY) */
	private PropertyDict itemCategory;
	/** 项目类别(子类)(ITEMCATEGORYSUB) */
	private PropertyDict itemCategorySub;
	/** 主要愿望或诉求(APPEALCONTENT) */
	private String appealContent;

	public PeopleAspirations(Integer responseGroupNo,
			PropertyDict appealHumanType, PropertyDict itemCategory,
			PropertyDict itemCategorySub, String appealContent) {
		super();
		this.responseGroupNo = responseGroupNo;
		this.appealHumanType = appealHumanType;
		this.itemCategory = itemCategory;
		this.itemCategorySub = itemCategorySub;
		this.appealContent = appealContent;
	}

	public PeopleAspirations() {
	}

	public Integer getResponseGroupNo() {
		return responseGroupNo;
	}

	public void setResponseGroupNo(Integer responseGroupNo) {
		this.responseGroupNo = responseGroupNo;
	}

	public PropertyDict getAppealHumanType() {
		return appealHumanType;
	}

	public void setAppealHumanType(PropertyDict appealHumanType) {
		this.appealHumanType = appealHumanType;
	}

	public PropertyDict getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(PropertyDict itemCategory) {
		this.itemCategory = itemCategory;
	}

	public PropertyDict getItemCategorySub() {
		return itemCategorySub;
	}

	public void setItemCategorySub(PropertyDict itemCategorySub) {
		this.itemCategorySub = itemCategorySub;
	}

	public String getAppealContent() {
		return appealContent;
	}

	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}
}
