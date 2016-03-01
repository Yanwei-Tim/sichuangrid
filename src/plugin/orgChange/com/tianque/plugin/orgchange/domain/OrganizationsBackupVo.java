package com.tianque.plugin.orgchange.domain;

import java.util.Date;

/**
 * 组织机构备份更新信息
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年10月22日
 */
public class OrganizationsBackupVo {
	private Long id;
	private int changeType;
	private Date changeDate;

	public OrganizationsBackupVo() {

	}

	public OrganizationsBackupVo(Long id, int changeType, Date changeDate) {
		this.id = id;
		this.changeType = changeType;
		this.changeDate = changeDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getChangeType() {
		return changeType;
	}

	public void setChangeType(int changeType) {
		this.changeType = changeType;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

}
