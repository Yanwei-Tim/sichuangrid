package com.tianque.plugin.serviceTeam.serviceTeamManage.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;

public class ServiceTeamVo extends ServiceTeam implements Serializable {
	/** 服务类型 */
	private String serviceType;
	/** 网格范围 **/
	private String orgScope;
	/** 成员数 */
	private int teamMembers;
	/** 最早成立时间 */
	private Date buildDateStart;
	/** 最晚成立时间 */
	private Date buildDateEnd;
	/** 最小成员数 */
	private Long teamCountMin;
	/** 最大成员数 */
	private Long teamCountMax;
	/** 组织分类 **/
	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;

	private int startRow;

	private int endRow;

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(int teamMembers) {
		this.teamMembers = teamMembers;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Date getBuildDateStart() {
		return buildDateStart;
	}

	public void setBuildDateStart(Date buildDateStart) {
		this.buildDateStart = buildDateStart;
	}

	public Date getBuildDateEnd() {
		return buildDateEnd;
	}

	public void setBuildDateEnd(Date buildDateEnd) {
		this.buildDateEnd = buildDateEnd;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getOrgScope() {
		return orgScope;
	}

	public void setOrgScope(String orgScope) {
		this.orgScope = orgScope;
	}

	public Long getTeamCountMin() {
		return teamCountMin;
	}

	public void setTeamCountMin(Long teamCountMin) {
		this.teamCountMin = teamCountMin;
	}

	public Long getTeamCountMax() {
		return teamCountMax;
	}

	public void setTeamCountMax(Long teamCountMax) {
		this.teamCountMax = teamCountMax;
	}

}
