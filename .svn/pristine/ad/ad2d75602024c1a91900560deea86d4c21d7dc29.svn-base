package com.tianque.baseInfo.dangerousGoodsPractitioner.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class DangerousGoodsPractitioner extends AttentionPopulation {

	public DangerousGoodsPractitioner() {
		setAttentionPopulationType(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY);
	}

	/** 危险从业类别 */
	private PropertyDict dangerousWorkingType;
	/** 法人 */
	private String legalPerson;
	/** 法人电话 */
	private String legalPersonTelephone;
	/** 法人手机 */
	private String legalPersonMobileNumber;
	/** 从业资格证 */
	private String workingCertificate;
	/** 从业资格证号 */
	private String workingCertificateNo;
	/** 有效开始日期 */
	private Date periodOfValidityStart;
	/** 有效结束日期 */
	private Date periodOfValidityEnd;

	public PropertyDict getDangerousWorkingType() {
		return dangerousWorkingType;
	}

	public void setDangerousWorkingType(PropertyDict dangerousWorkingType) {
		this.dangerousWorkingType = dangerousWorkingType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonTelephone() {
		return legalPersonTelephone;
	}

	public void setLegalPersonTelephone(String legalPersonTelephone) {
		this.legalPersonTelephone = legalPersonTelephone;
	}

	public String getLegalPersonMobileNumber() {
		return legalPersonMobileNumber;
	}

	public void setLegalPersonMobileNumber(String legalPersonMobileNumber) {
		this.legalPersonMobileNumber = legalPersonMobileNumber;
	}

	public String getWorkingCertificate() {
		return workingCertificate;
	}

	public void setWorkingCertificate(String workingCertificate) {
		this.workingCertificate = workingCertificate;
	}

	public String getWorkingCertificateNo() {
		return workingCertificateNo;
	}

	public void setWorkingCertificateNo(String workingCertificateNo) {
		this.workingCertificateNo = workingCertificateNo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPeriodOfValidityStart() {
		return periodOfValidityStart;
	}

	public void setPeriodOfValidityStart(Date periodOfValidityStart) {
		this.periodOfValidityStart = periodOfValidityStart;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPeriodOfValidityEnd() {
		return periodOfValidityEnd;
	}

	public void setPeriodOfValidityEnd(Date periodOfValidityEnd) {
		this.periodOfValidityEnd = periodOfValidityEnd;
	}
}
