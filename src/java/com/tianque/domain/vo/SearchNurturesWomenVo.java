package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.util.BaseInfoTables;

public class SearchNurturesWomenVo extends BasePersonnelSearchCondition {

	private String fastSearchKeyWords;
	private Long isDeath;

	private Long childNumberStart;

	private Long childNumberEnd;

	private String manIdCardNo;

	private String manName;

	private Long licenseSituation;

	private String fertilityServicesNo;

	private Date licenseTimeStart;

	private Date licenseTimeEnd;

	private String contraceptiveMethod;

	private Date contraceptiveTimeStart;

	private Date contraceptiveTimeEnd;

	private Long nation;

	private Long politicalBackground;
	/**
	 * 婚姻状况
	 */
	private Long maritalState;

	private Long schooling;

	private Long career;
	/**
	 * 身高
	 */
	private Long statureStart;

	private Long statureEnd;

	private Long bloodType;

	private Long faith;

	private Long residenceType;

	private Long death;

	private Date firstMarriageTimeStart;

	private Date firstMarriageTimeEnd;

	private Date marriageRegistrationTimeStart;

	private Date marriageRegistrationTimeEnd;

	private Date marriageOrDivorceTimeStart;

	private Date marriageOrDivorceTimeEnd;

	private Long isEmphasis;

	/** 生育服务证发证单位 */
	private String certifiedUnit;
	/** 是否领独生子女证：1:是 0否 */
	private String isSingleChildCard;

	/** 独生子女证号： */
	private String singleChildCardno;

	/** 独生子女证领证时间开始： */
	private Date singleChildCardIssueStart;
	/** 独生子女证领证时间结束： */
	private Date singleChildCardIssueEnd;

	/** 是否持有婚育证：1:是 0否 **/
	private String isMaternityCard;
	/** 婚育证编号： */
	private String maternityCardNo;
	/** 婚育证发放单位： */
	private String maternityCardUnit;

	/** 婚育证发放时间岂止时间段_开始时间 */
	private Date maternityCardIssueTimeStart;
	/** 婚育证发放时间岂止时间段_结束时间 */
	private Date maternityCardIssueTimeEnd;
	/** 有效期时间段_开始时间： */
	private Date maternityCardValidityTimeStart;
	/** 有效期时间段_结束时间： */
	private Date maternityCardValidityTimeEnd;

	/** 检查时间段_开始时间 */
	private Date maternityCardCheckTimeStart;
	/** 检查时间段_结束时间 */
	private Date maternityCardCheckTimeEnd;

	/** 是否征收：1:是 0否 */
	private String isLevied;
	/** 是否未婚先孕 ：1:是 0否 */
	private String isUnmarriedPregnant;
	/** 夫妻双方独生子女情况： */
	private long onechildOfCouple;
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;

	public String getCertifiedUnit() {
		return certifiedUnit;
	}

	public void setCertifiedUnit(String certifiedUnit) {
		this.certifiedUnit = certifiedUnit;
	}

	public Long getChildNumberStart() {
		return childNumberStart;
	}

	public void setChildNumberStart(Long childNumberStart) {
		this.childNumberStart = childNumberStart;
	}

	public Long getChildNumberEnd() {
		return childNumberEnd;
	}

	public void setChildNumberEnd(Long childNumberEnd) {
		this.childNumberEnd = childNumberEnd;
	}

	public String getManIdCardNo() {
		return manIdCardNo;
	}

	public void setManIdCardNo(String manIdCardNo) {
		this.manIdCardNo = manIdCardNo;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public Long getLicenseSituation() {
		return licenseSituation;
	}

	public void setLicenseSituation(Long licenseSituation) {
		this.licenseSituation = licenseSituation;
	}

	public String getFertilityServicesNo() {
		return fertilityServicesNo;
	}

	public void setFertilityServicesNo(String fertilityServicesNo) {
		this.fertilityServicesNo = fertilityServicesNo;
	}

	public Date getLicenseTimeStart() {
		return licenseTimeStart;
	}

	public void setLicenseTimeStart(Date licenseTimeStart) {
		this.licenseTimeStart = licenseTimeStart;
	}

	public Date getLicenseTimeEnd() {
		return licenseTimeEnd;
	}

	public void setLicenseTimeEnd(Date licenseTimeEnd) {
		this.licenseTimeEnd = licenseTimeEnd;
	}

	public String getContraceptiveMethod() {
		return contraceptiveMethod;
	}

	public void setContraceptiveMethod(String contraceptiveMethod) {
		this.contraceptiveMethod = contraceptiveMethod;
	}

	public Date getContraceptiveTimeStart() {
		return contraceptiveTimeStart;
	}

	public void setContraceptiveTimeStart(Date contraceptiveTimeStart) {
		this.contraceptiveTimeStart = contraceptiveTimeStart;
	}

	public Date getContraceptiveTimeEnd() {
		return contraceptiveTimeEnd;
	}

	public void setContraceptiveTimeEnd(Date contraceptiveTimeEnd) {
		this.contraceptiveTimeEnd = contraceptiveTimeEnd;
	}

	public Long getNation() {
		return nation;
	}

	public void setNation(Long nation) {
		this.nation = nation;
	}

	public Long getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(Long politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public Long getMaritalState() {
		return maritalState;
	}

	public void setMaritalState(Long maritalState) {
		this.maritalState = maritalState;
	}

	public Long getSchooling() {
		return schooling;
	}

	public void setSchooling(Long schooling) {
		this.schooling = schooling;
	}

	public Long getCareer() {
		return career;
	}

	public void setCareer(Long career) {
		this.career = career;
	}

	public Long getStatureStart() {
		return statureStart;
	}

	public void setStatureStart(Long statureStart) {
		this.statureStart = statureStart;
	}

	public Long getStatureEnd() {
		return statureEnd;
	}

	public void setStatureEnd(Long statureEnd) {
		this.statureEnd = statureEnd;
	}

	public Long getBloodType() {
		return bloodType;
	}

	public void setBloodType(Long bloodType) {
		this.bloodType = bloodType;
	}

	public Long getFaith() {
		return faith;
	}

	public void setFaith(Long faith) {
		this.faith = faith;
	}

	public Long getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Long residenceType) {
		this.residenceType = residenceType;
	}

	public void setDeath(Long death) {
		this.death = death;
	}

	public Long getDeath() {
		return death;
	}

	public Date getFirstMarriageTimeStart() {
		return firstMarriageTimeStart;
	}

	public void setFirstMarriageTimeStart(Date firstMarriageTimeStart) {
		this.firstMarriageTimeStart = firstMarriageTimeStart;
	}

	public Date getFirstMarriageTimeEnd() {
		return firstMarriageTimeEnd;
	}

	public void setFirstMarriageTimeEnd(Date firstMarriageTimeEnd) {
		this.firstMarriageTimeEnd = firstMarriageTimeEnd;
	}

	public Date getMarriageRegistrationTimeStart() {
		return marriageRegistrationTimeStart;
	}

	public void setMarriageRegistrationTimeStart(Date marriageRegistrationTimeStart) {
		this.marriageRegistrationTimeStart = marriageRegistrationTimeStart;
	}

	public Date getMarriageRegistrationTimeEnd() {
		return marriageRegistrationTimeEnd;
	}

	public void setMarriageRegistrationTimeEnd(Date marriageRegistrationTimeEnd) {
		this.marriageRegistrationTimeEnd = marriageRegistrationTimeEnd;
	}

	public Date getMarriageOrDivorceTimeStart() {
		return marriageOrDivorceTimeStart;
	}

	public void setMarriageOrDivorceTimeStart(Date marriageOrDivorceTimeStart) {
		this.marriageOrDivorceTimeStart = marriageOrDivorceTimeStart;
	}

	public Date getMarriageOrDivorceTimeEnd() {
		return marriageOrDivorceTimeEnd;
	}

	public void setMarriageOrDivorceTimeEnd(Date marriageOrDivorceTimeEnd) {
		this.marriageOrDivorceTimeEnd = marriageOrDivorceTimeEnd;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public Date getSingleChildCardIssueStart() {
		return singleChildCardIssueStart;
	}

	public void setSingleChildCardIssueStart(Date singleChildCardIssueStart) {
		this.singleChildCardIssueStart = singleChildCardIssueStart;
	}

	public Date getSingleChildCardIssueEnd() {
		return singleChildCardIssueEnd;
	}

	public void setSingleChildCardIssueEnd(Date singleChildCardIssueEnd) {
		this.singleChildCardIssueEnd = singleChildCardIssueEnd;
	}

	public String getMaternityCardNo() {
		return maternityCardNo;
	}

	public void setMaternityCardNo(String maternityCardNo) {
		this.maternityCardNo = maternityCardNo;
	}

	public String getMaternityCardUnit() {
		return maternityCardUnit;
	}

	public void setMaternityCardUnit(String maternityCardUnit) {
		this.maternityCardUnit = maternityCardUnit;
	}

	public Date getMaternityCardIssueTimeStart() {
		return maternityCardIssueTimeStart;
	}

	public void setMaternityCardIssueTimeStart(Date maternityCardIssueTimeStart) {
		this.maternityCardIssueTimeStart = maternityCardIssueTimeStart;
	}

	public Date getMaternityCardIssueTimeEnd() {
		return maternityCardIssueTimeEnd;
	}

	public void setMaternityCardIssueTimeEnd(Date maternityCardIssueTimeEnd) {
		this.maternityCardIssueTimeEnd = maternityCardIssueTimeEnd;
	}

	public Date getMaternityCardValidityTimeStart() {
		return maternityCardValidityTimeStart;
	}

	public void setMaternityCardValidityTimeStart(Date maternityCardValidityTimeStart) {
		this.maternityCardValidityTimeStart = maternityCardValidityTimeStart;
	}

	public Date getMaternityCardValidityTimeEnd() {
		return maternityCardValidityTimeEnd;
	}

	public void setMaternityCardValidityTimeEnd(Date maternityCardValidityTimeEnd) {
		this.maternityCardValidityTimeEnd = maternityCardValidityTimeEnd;
	}

	public Date getMaternityCardCheckTimeStart() {
		return maternityCardCheckTimeStart;
	}

	public void setMaternityCardCheckTimeStart(Date maternityCardCheckTimeStart) {
		this.maternityCardCheckTimeStart = maternityCardCheckTimeStart;
	}

	public Date getMaternityCardCheckTimeEnd() {
		return maternityCardCheckTimeEnd;
	}

	public void setMaternityCardCheckTimeEnd(Date maternityCardCheckTimeEnd) {
		this.maternityCardCheckTimeEnd = maternityCardCheckTimeEnd;
	}

	public String getSingleChildCardno() {
		return singleChildCardno;
	}

	public void setSingleChildCardno(String singleChildCardno) {
		this.singleChildCardno = singleChildCardno;
	}

	public String getIsLevied() {
		return isLevied;
	}

	public void setIsLevied(String isLevied) {
		this.isLevied = isLevied;
	}

	public String getIsUnmarriedPregnant() {
		return isUnmarriedPregnant;
	}

	public void setIsUnmarriedPregnant(String isUnmarriedPregnant) {
		this.isUnmarriedPregnant = isUnmarriedPregnant;
	}

	public long getOnechildOfCouple() {
		return onechildOfCouple;
	}

	public void setOnechildOfCouple(long onechildOfCouple) {
		this.onechildOfCouple = onechildOfCouple;
	}

	public String getIsSingleChildCard() {
		return isSingleChildCard;
	}

	public void setIsSingleChildCard(String isSingleChildCard) {
		this.isSingleChildCard = isSingleChildCard;
	}

	public String getIsMaternityCard() {
		return isMaternityCard;
	}

	public void setIsMaternityCard(String isMaternityCard) {
		this.isMaternityCard = isMaternityCard;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceTeamRecord() {
		return hasServiceTeamRecord;
	}

	public void setHasServiceTeamRecord(Long hasServiceTeamRecord) {
		this.hasServiceTeamRecord = hasServiceTeamRecord;
	}

	public String getAttentionPopulationType() {
		return BaseInfoTables.NURTURESWOMEN_KEY;
	}
}
