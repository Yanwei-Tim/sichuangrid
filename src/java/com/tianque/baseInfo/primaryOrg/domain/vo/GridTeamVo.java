package com.tianque.baseInfo.primaryOrg.domain.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 网格员报表统计
 */
public class GridTeamVo extends BaseDomain {

	/** 所属机构 */
	private Organization org;
	/** 下辖网格数 ,系统中网格数+自定义网格数*/
	private Long subGrids;
	/** 系统中网格数 */
	private Long sysGrids;
	/** 自定义网格数 */
	private Long userDefinedGrids;
	/** 网格员总量 */
	private Long gridTeamCount;
	/** 专职网格员 */
	private Long fullTimeGridTeamCount;
	/** 兼职网格员 */
	private Long partTimeGridTeamCount;
	/** 手机端网格员数量 */
	private Long phoneGridTeamCount;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Long getSubGrids() {
		if (subGrids != null) {
			return subGrids;
		}
		return (sysGrids == null ? 0 : sysGrids)
				+ (userDefinedGrids == null ? 0 : userDefinedGrids);
	}

	public void setSubGrids(Long subGrids) {
		this.subGrids = subGrids;
	}

	public Long getGridTeamCount() {
		if (gridTeamCount != null) {
			return gridTeamCount;
		}
		return (fullTimeGridTeamCount == null ? 0 : fullTimeGridTeamCount)
				+ (partTimeGridTeamCount == null ? 0 : partTimeGridTeamCount);
	}

	public void setGridTeamCount(Long gridTeamCount) {
		this.gridTeamCount = gridTeamCount;
	}

	public Long getFullTimeGridTeamCount() {
		return fullTimeGridTeamCount;
	}

	public void setFullTimeGridTeamCount(Long fullTimeGridTeamCount) {
		this.fullTimeGridTeamCount = fullTimeGridTeamCount;
	}

	public Long getPartTimeGridTeamCount() {
		return partTimeGridTeamCount;
	}

	public void setPartTimeGridTeamCount(Long partTimeGridTeamCount) {
		this.partTimeGridTeamCount = partTimeGridTeamCount;
	}

	public Long getPhoneGridTeamCount() {
		return phoneGridTeamCount;
	}

	public void setPhoneGridTeamCount(Long phoneGridTeamCount) {
		this.phoneGridTeamCount = phoneGridTeamCount;
	}

	public Long getSysGrids() {
		return sysGrids;
	}

	public void setSysGrids(Long sysGrids) {
		this.sysGrids = sysGrids;
	}

	public Long getUserDefinedGrids() {
		return userDefinedGrids;
	}

	public void setUserDefinedGrids(Long userDefinedGrids) {
		this.userDefinedGrids = userDefinedGrids;
	}

}
