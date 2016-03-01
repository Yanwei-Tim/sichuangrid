package com.tianque.xichang.working.poorPeople.domain;

import java.util.Date;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.xichang.working.domain.CommonWorking;

/**
 * @ClassName: PoorPeople
 * @Description: 困难群众实体类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 24, 2013 9:53:24 AM
 */
public class PoorPeople extends CommonWorking {

	private PropertyDict schooling;// 学历
	private PropertyDict insuranceType;// 纳入保险
	private Long memberNum;// 家庭人口
	private Double lastYearMemberIncome;// 上年度人均年收入
	private PropertyDict poorBigInfo;// 困难原因
	private PropertyDict poorInfo;// 困难原因
	private String helpInfo;// 帮扶需求
	private String yearHelpInfo;// 年度帮扶项目

	private Long memberNumz;// 家庭人口 范围 查询使用
	private Double lastYearMemberIncomez;// 上年度人均年收入 范围 查询使用
	private Date birthDayz;// 范围 查询使用
	private Date registrationTimez;// 范围 查询使用

	private Integer page;
	private Integer rows;
	/** 查询用到 */
	private Long orgId;
	private String orgCode;
	private Integer isfinish;

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public PropertyDict getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(PropertyDict insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Double getLastYearMemberIncome() {
		return lastYearMemberIncome;
	}

	public void setLastYearMemberIncome(Double lastYearMemberIncome) {
		this.lastYearMemberIncome = lastYearMemberIncome;
	}

	public PropertyDict getPoorBigInfo() {
		return poorBigInfo;
	}

	public void setPoorBigInfo(PropertyDict poorBigInfo) {
		this.poorBigInfo = poorBigInfo;
	}

	public PropertyDict getPoorInfo() {
		return poorInfo;
	}

	public void setPoorInfo(PropertyDict poorInfo) {
		this.poorInfo = poorInfo;
	}

	public String getHelpInfo() {
		return helpInfo;
	}

	public void setHelpInfo(String helpInfo) {
		this.helpInfo = helpInfo;
	}

	public String getYearHelpInfo() {
		return yearHelpInfo;
	}

	public void setYearHelpInfo(String yearHelpInfo) {
		this.yearHelpInfo = yearHelpInfo;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public Long getMemberNumz() {
		return memberNumz;
	}

	public void setMemberNumz(Long memberNumz) {
		this.memberNumz = memberNumz;
	}

	public Double getLastYearMemberIncomez() {
		return lastYearMemberIncomez;
	}

	public void setLastYearMemberIncomez(Double lastYearMemberIncomez) {
		this.lastYearMemberIncomez = lastYearMemberIncomez;
	}

	public Date getBirthDayz() {
		return birthDayz;
	}

	public void setBirthDayz(Date birthDayz) {
		this.birthDayz = birthDayz;
	}

	public Date getRegistrationTimez() {
		return registrationTimez;
	}

	public void setRegistrationTimez(Date registrationTimez) {
		this.registrationTimez = registrationTimez;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
	}

	public Integer getIsfinish() {
		return isfinish;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}

}
