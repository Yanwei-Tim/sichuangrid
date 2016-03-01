package com.tianque.baseInfo.householdStaff.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.Property;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.PropertyDict;

/**
 * 户籍人口
 */
@SuppressWarnings("serial")
@Entity(noClassnameStored = true, value = "householdstaffs")
public class HouseholdStaff extends ActualPopulation {

	public HouseholdStaff() {
		setActualPopulationType(BaseInfoTables.HOUSEHOLDSTAFF_KEY);
	}

	/**
	 * 实口类型
	 */
	@NotSaved
	private String actualtype;
	/** 与户主关系 **/
	// @Property("relationShip")
	private PropertyDict relationShipWithHead;

	/** 与户主关系文本 */
	@NotSaved
	private String relationShipWithHeadText;
	/** 户籍家庭 **/
	private CensusRegisterFamily censusRegisterFamily;

	/** 是否外出 **/
	private Boolean outGone;
	/** 外出原因 **/
	private PropertyDict outReasons;
	/** 外出时间 **/
	private Date reasonsDate;
	/** 外出去向 **/
	private String outProvince;
	@NotSaved
	private String outCity;
	@NotSaved
	private String outDistrict;
	/** 外出详址 **/
	@Property("outAdd")
	private String goOutDetailedAddress;
	/** 户口号 **/
	@Property("accountNo")
	private String accountNumber;
	/** 家庭电话 **/
	@NotSaved
	private String homePhone;
	/** 人户状态 **/
	@NotSaved
	private PropertyDict residentStatus;
	/** 户口类别 */
	private PropertyDict residenceType;
	/** 家庭称呼 */
	@NotSaved
	private List<PropertyDict> houseMarchType;

	/** 落户时间 */
	@NotSaved
	private String settleTime;

	public CensusRegisterFamily getCensusRegisterFamily() {
		return censusRegisterFamily;
	}

	public void setCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		this.censusRegisterFamily = censusRegisterFamily;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public PropertyDict getRelationShipWithHead() {
		return relationShipWithHead;
	}

	public void setRelationShipWithHead(PropertyDict relationShipWithHead) {
		this.relationShipWithHead = relationShipWithHead;
	}

	public Boolean getOutGone() {
		return outGone == null ? false : outGone;
	}

	public void setOutGone(Boolean outGone) {
		this.outGone = outGone;
	}

	public PropertyDict getOutReasons() {
		return outReasons;
	}

	public void setOutReasons(PropertyDict outReasons) {
		this.outReasons = outReasons;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getReasonsDate() {
		return reasonsDate;
	}

	public void setReasonsDate(Date reasonsDate) {
		this.reasonsDate = reasonsDate;
	}

	public String getGoOutDetailedAddress() {
		return goOutDetailedAddress;
	}

	public void setGoOutDetailedAddress(String goOutDetailedAddress) {
		this.goOutDetailedAddress = goOutDetailedAddress;
	}

	public String getOutProvince() {
		return outProvince;
	}

	public void setOutProvince(String outProvince) {
		this.outProvince = outProvince;
	}

	public String getOutCity() {
		return outCity;
	}

	public void setOutCity(String outCity) {
		this.outCity = outCity;
	}

	public String getOutDistrict() {
		return outDistrict;
	}

	public void setOutDistrict(String outDistrict) {
		this.outDistrict = outDistrict;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getActualtype() {
		return actualtype;
	}

	public void setActualtype(String actualtype) {
		this.actualtype = actualtype;
	}

	public List<PropertyDict> getHouseMarchType() {
		return houseMarchType;
	}

	public void setHouseMarchType(List<PropertyDict> houseMarchType) {
		this.houseMarchType = houseMarchType;
	}

	public PropertyDict getResidentStatus() {
		return residentStatus;
	}

	public void setResidentStatus(PropertyDict residentStatus) {
		this.residentStatus = residentStatus;
	}

	public String getDetailOutAddress() {
		return new StringBuffer(null != getOutProvince() ? getOutProvince()
				: "").append(null != getOutCity() ? getOutCity() : "")
				.append(null != getOutDistrict() ? getOutDistrict() : "")
				.toString();
	}

	public String getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(String settleTime) {
		this.settleTime = settleTime;
	}

	public PropertyDict getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(PropertyDict residenceType) {
		this.residenceType = residenceType;
	}

	public String getRelationShipWithHeadText() {
		return relationShipWithHeadText;
	}

	public void setRelationShipWithHeadText(String relationShipWithHeadText) {
		this.relationShipWithHeadText = relationShipWithHeadText;
	}
}
