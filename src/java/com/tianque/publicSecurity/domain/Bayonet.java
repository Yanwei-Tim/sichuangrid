package com.tianque.publicSecurity.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 卡口表:实体类(BAYONET)
 * 
 * @author
 * @date 2014-02-11 10:44:36
 */
public class Bayonet extends publicSecurityCommon implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 编号(BAYONET_NO) */
	private String bayonetNo;

	public Bayonet() {

	}

	public Bayonet(String bayonetNo) {
		this.bayonetNo = bayonetNo;
	}

	/** get 编号(bayonetNo) */
	public String getBayonetNo() {
		return bayonetNo;
	}

	/** set 编号(BAYONET_NO) */
	public void setBayonetNo(String bayonetNo) {
		this.bayonetNo = bayonetNo;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}
}
