package com.tianque.fourTeams.fourTeamsIssue.domain.vo;

/**
 * 村社区平安三联创:SrarchVO(PEACE_VILLAGE)
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
public class SearchFourTeamsPeaceVillageVo extends com.tianque.core.base.BaseDomain {

	/** 最大 */
	private java.util.Date maxCreatedate;
	/** 最小 */
	private java.util.Date minCreatedate;
	/** 最大 */
	private java.util.Date maxUpdatedate;
	/** 最小 */
	private java.util.Date minUpdatedate;
	/** 所属网格 */
	private com.tianque.domain.Organization organization;
	/** 年 */
	private Long year;
	/** 月 */
	private Long month;
	/** 无刑事案件 */
	private Integer noCriminal;
	/** 无毒品 */
	private Integer noDrugs;
	/** 无安全事故 */
	private Integer noAccident;
	/** 无群体性事件 */
	private Integer noGroupEvents;
	/** 无脏乱差 */
	private Integer noDirty;
	/** 有自治组织 */
	private Integer hasOrganization;
	/** 有三防措施 */
	private Integer hasMeasures;
	/** 有法制宣传 */
	private Integer hasLegalAdvocacy;
	/** 有社会力量参与 */
	private Integer hasSocialForces;
	/** 有群众文化活动 */
	private Integer hasCulturalActivities;
	/** 所属网格编号 */
	private String orgInternalCode;

	/** get 最大 */
	public java.util.Date getMaxCreatedate() {
		return maxCreatedate;
	}

	/** set 最大 */
	public void setMaxCreatedate(java.util.Date maxCreatedate) {
		this.maxCreatedate = maxCreatedate;
	}

	/** get 最小 */
	public java.util.Date getMinCreatedate() {
		return minCreatedate;
	}

	/** set 最小 */
	public void setMinCreatedate(java.util.Date minCreatedate) {
		this.minCreatedate = minCreatedate;
	}

	/** get 最大 */
	public java.util.Date getMaxUpdatedate() {
		return maxUpdatedate;
	}

	/** set 最大 */
	public void setMaxUpdatedate(java.util.Date maxUpdatedate) {
		this.maxUpdatedate = maxUpdatedate;
	}

	/** get 最小 */
	public java.util.Date getMinUpdatedate() {
		return minUpdatedate;
	}

	/** set 最小 */
	public void setMinUpdatedate(java.util.Date minUpdatedate) {
		this.minUpdatedate = minUpdatedate;
	}

	/** get 所属网格 */
	public com.tianque.domain.Organization getOrganization() {
		return organization;
	}

	/** set 所属网格 */
	public void setOrganization(com.tianque.domain.Organization organization) {
		this.organization = organization;
	}

	/** get 年 */
	public Long getYear() {
		return year;
	}

	/** set 年 */
	public void setYear(Long year) {
		this.year = year;
	}

	/** get 月 */
	public Long getMonth() {
		return month;
	}

	/** set 月 */
	public void setMonth(Long month) {
		this.month = month;
	}

	/** get 无刑事案件 */
	public Integer getNoCriminal() {
		return noCriminal;
	}

	/** set 无刑事案件 */
	public void setNoCriminal(Integer noCriminal) {
		this.noCriminal = noCriminal;
	}

	/** get 无毒品 */
	public Integer getNoDrugs() {
		return noDrugs;
	}

	/** set 无毒品 */
	public void setNoDrugs(Integer noDrugs) {
		this.noDrugs = noDrugs;
	}

	/** get 无安全事故 */
	public Integer getNoAccident() {
		return noAccident;
	}

	/** set 无安全事故 */
	public void setNoAccident(Integer noAccident) {
		this.noAccident = noAccident;
	}

	/** get 无群体性事件 */
	public Integer getNoGroupEvents() {
		return noGroupEvents;
	}

	/** set 无群体性事件 */
	public void setNoGroupEvents(Integer noGroupEvents) {
		this.noGroupEvents = noGroupEvents;
	}

	/** get 无脏乱差 */
	public Integer getNoDirty() {
		return noDirty;
	}

	/** set 无脏乱差 */
	public void setNoDirty(Integer noDirty) {
		this.noDirty = noDirty;
	}

	/** get 有自治组织 */
	public Integer getHasOrganization() {
		return hasOrganization;
	}

	/** set 有自治组织 */
	public void setHasOrganization(Integer hasOrganization) {
		this.hasOrganization = hasOrganization;
	}

	/** get 有三防措施 */
	public Integer getHasMeasures() {
		return hasMeasures;
	}

	/** set 有三防措施 */
	public void setHasMeasures(Integer hasMeasures) {
		this.hasMeasures = hasMeasures;
	}

	/** get 有法制宣传 */
	public Integer getHasLegalAdvocacy() {
		return hasLegalAdvocacy;
	}

	/** set 有法制宣传 */
	public void setHasLegalAdvocacy(Integer hasLegalAdvocacy) {
		this.hasLegalAdvocacy = hasLegalAdvocacy;
	}

	/** get 有社会力量参与 */
	public Integer getHasSocialForces() {
		return hasSocialForces;
	}

	/** set 有社会力量参与 */
	public void setHasSocialForces(Integer hasSocialForces) {
		this.hasSocialForces = hasSocialForces;
	}

	/** get 有群众文化活动 */
	public Integer getHasCulturalActivities() {
		return hasCulturalActivities;
	}

	/** set 有群众文化活动 */
	public void setHasCulturalActivities(Integer hasCulturalActivities) {
		this.hasCulturalActivities = hasCulturalActivities;
	}

	/** get 所属网格编号 */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号 */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

}
