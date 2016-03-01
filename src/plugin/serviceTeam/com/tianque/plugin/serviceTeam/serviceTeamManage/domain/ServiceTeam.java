package com.tianque.plugin.serviceTeam.serviceTeamManage.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class ServiceTeam extends BaseDomain {

	/** 组织所属网格 **/
	private Organization org;
	/** 组织名称 **/
	private String teamName;
	/** 组织类别 **/
	private PropertyDict teamType;
	/** 组织所属网格编码 **/
	private String orgCode;
	/** 组织名称简拼 **/
	private String simplePinyin;
	/** 组织名称全拼 **/
	private String fullPinyin;
	/** 组织创建时间 **/
	private Date buildDate;
	/** 备注 **/
	private String remark;
	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 是否注销 */
	private Long logOut;
	/** 成员数 */
	private Integer memberNum;
	/** 记录数 */
	private Integer recordNum;
	/** 服务对象 */
	private String serviceObject;

	public PropertyDict getTeamType() {
		return teamType;
	}

	public void setTeamType(PropertyDict teamType) {
		this.teamType = teamType;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBuildDate() {
		return buildDate;
	}

	public String getRemark() {
		return remark;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	public String getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(String serviceObject) {
		this.serviceObject = serviceObject;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgCode, null);
	}
}