package com.tianque.domain;

import java.io.Serializable;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 实有人口与业务信息关系
 */
@SuppressWarnings("serial")
public class PopulationTypeBean implements Serializable {

	/** 主键 */
	private Long id;

	/** 实有人口主键 */
	private Long actualId;

	/** 实有人口类型 */
	private String actualType;

	/** 业务人员主键 */
	private Long populationId;

	/** 业务人员类型 */
	private String populationType;
	
	private String orgInternalCode;

	public PopulationTypeBean() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActualId() {
		return actualId;
	}

	public void setActualId(Long actualId) {
		this.actualId = actualId;
	}

	public String getActualType() {
		return actualType;
	}

	public void setActualType(String actualType) {
		this.actualType = actualType;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}
	
	public String getEncryptPopulationId() {
		String orgCode = this.orgInternalCode;
		return BaseDomainIdEncryptUtil.encryptDomainId(this.populationId, orgCode,null);
	}
	
	public String getEncryptActualId() {
		String orgCode = this.orgInternalCode;
		return BaseDomainIdEncryptUtil.encryptDomainId(this.actualId, orgCode,null);
	}
}
