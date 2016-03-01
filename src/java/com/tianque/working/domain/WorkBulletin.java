package com.tianque.working.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.User;

public class WorkBulletin extends BaseDomain {

	private static final long serialVersionUID = -2827369102322985662L;
	/** 所属组织标号 */
	private Long orgId;
	/** 创建人编号 */
	private Long useId;
	/** 创建人姓名 */
	private String userName;
	/** 工作简报名称 */
	private String bulletinName;
	/** 简报时间 */
	private Date bulletinDate;
	/** 填写简报时间 */
	private Date fillDate;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 简报附件列表 */
	List<WorkBulletinAttachFiles> workBulletinAttachFileList;
	private Organization organization;
	private User user;

	public List<WorkBulletinAttachFiles> getWorkBulletinAttachFileList() {
		if (null == workBulletinAttachFileList) {
			workBulletinAttachFileList = new ArrayList<WorkBulletinAttachFiles>();
		}
		return workBulletinAttachFileList;
	}

	public void setWorkBulletinAttachFileList(
			List<WorkBulletinAttachFiles> workBulletinAttachFileList) {
		this.workBulletinAttachFileList = workBulletinAttachFileList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUseId() {
		return useId;
	}

	public void setUseId(Long useId) {
		this.useId = useId;
	}

	public String getUserName() {
		if (null == userName || "".equals(userName)) {
			if (null != user) {
				userName = user.getUserName();
			}
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBulletinName() {
		return bulletinName;
	}

	public void setBulletinName(String bulletinName) {
		this.bulletinName = bulletinName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBulletinDate() {
		return bulletinDate;
	}

	public void setBulletinDate(Date bulletinDate) {
		this.bulletinDate = bulletinDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		if (null == organization) {
			organization = new Organization();
		}
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getUser() {
		if (null == user) {
			user = new User();
		}

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

}