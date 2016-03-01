package com.tianque.publicSecurity.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 天网表:实体类(SKYNET)
 * 
 * @author
 * @date 2014-02-10 14:21:16
 */
public class Skynet extends publicSecurityCommon implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 编号(SKYNET_NO) */
	private String skynetNo;

	public Skynet() {

	}

	public Skynet(String skynetNo) {
		super();
		this.skynetNo = skynetNo;
	}

	/** get 编号(skynetNo) */
	public String getSkynetNo() {
		return skynetNo;
	}

	/** set 编号(SKYNET_NO) */
	public void setSkynetNo(String skynetNo) {
		this.skynetNo = skynetNo;
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
