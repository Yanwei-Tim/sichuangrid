package com.tianque.publicSecurity.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 抓拍系统表:实体类(SNAPSHOTSYSTEM)
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
public class SnapshotSystem extends publicSecurityCommon implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 编号(SNAPSHOT_NO) */
	private String snapshotNo;

	public SnapshotSystem() {

	}

	public SnapshotSystem(String snapshotNo) {
		this.snapshotNo = snapshotNo;
	}

	/** get 编号(snapshotNo) */
	public String getSnapshotNo() {
		return snapshotNo;
	}

	/** set 编号(SNAPSHOT_NO) */
	public void setSnapshotNo(String snapshotNo) {
		this.snapshotNo = snapshotNo;
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
