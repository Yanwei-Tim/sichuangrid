package com.tianque.mobile.mobileDictionary.domain;

import java.util.Date;

public class PropertyDictLog {
	private Long id;
	/**
	 * 操作类型1:新增，2:修改，3：删除
	 */
	private Integer operateType;
	
	/**
	 * 关联的字典项id
	 */
	private Long dictId;
	
	/**
	 * 关联的字典大类id
	 */
	private Long domainId;
	
	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public Long getDictId() {
		return dictId;
	}
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	
}
