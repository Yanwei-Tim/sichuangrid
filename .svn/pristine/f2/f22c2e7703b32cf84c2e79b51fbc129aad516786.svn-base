package com.tianque.plugin.dataManage.base.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.Organization;
import com.tianque.domain.User;

public class ClaimDetail implements Serializable {
	/** 导入数据是否经过处理(0否1是) */
	private Integer dispose;
	/** 数据是否可认领 (0否1是) */
	private Integer claimAvailable;
	/** 认领状态(0未认领，1被未，10已认领，11被认) */
	private Integer claimState;
	/** 认领日期 */
	private Date claimDate;
	/** 认领人用户名 */
	private String claimUserName;
	/** 认领人 */
	private User claimUser;
	/** 认领人网格 */
	private Organization claimOrg;
	/** 导入部门 */
	private Organization importDepartment;
	/** 刚导入时的所属网格 */
	private Organization importOrganization;
	/** 认领时插入到原库中数据id */
	private Long inId;
	/** 导入时间 */
	private Date importDate;
	/** 导入时数据所在的县区的orgId */
	private Long districtOrgId;

	public ClaimDetail() {
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimUserName() {
		return claimUserName;
	}

	public void setClaimUserName(String claimUserName) {
		this.claimUserName = claimUserName;
	}

	public User getClaimUser() {
		return claimUser;
	}

	public void setClaimUser(User claimUser) {
		this.claimUser = claimUser;
	}

	public Organization getClaimOrg() {
		return claimOrg;
	}

	public void setClaimOrg(Organization claimOrg) {
		this.claimOrg = claimOrg;
	}

	public Organization getImportDepartment() {
		return importDepartment;
	}

	public void setImportDepartment(Organization importDepartment) {
		this.importDepartment = importDepartment;
	}

	public Organization getImportOrganization() {
		return importOrganization;
	}

	public void setImportOrganization(Organization importOrganization) {
		this.importOrganization = importOrganization;
	}

	public Long getInId() {
		return inId;
	}

	public void setInId(Long inId) {
		this.inId = inId;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public Long getDistrictOrgId() {
		return districtOrgId;
	}

	public void setDistrictOrgId(Long districtOrgId) {
		this.districtOrgId = districtOrgId;
	}

	public Integer getDispose() {
		return dispose;
	}

	public void setDispose(Integer dispose) {
		this.dispose = dispose;
	}

	public Integer getClaimAvailable() {
		return claimAvailable;
	}

	public void setClaimAvailable(Integer claimAvailable) {
		this.claimAvailable = claimAvailable;
	}

	public Integer getClaimState() {
		return claimState;
	}

	public void setClaimState(Integer claimState) {
		this.claimState = claimState;
	}

}