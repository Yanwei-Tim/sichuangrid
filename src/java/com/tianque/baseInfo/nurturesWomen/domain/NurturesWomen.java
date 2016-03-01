package com.tianque.baseInfo.nurturesWomen.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * 育妇
 */
public class NurturesWomen extends AttentionPopulation {

	public NurturesWomen() {
		setAttentionPopulationType(BaseInfoTables.NURTURESWOMEN_KEY);
	}

	/**
	 * 初婚时间
	 */
	private Date firstMarriageTime;
	/**
	 * 离婚时间
	 */
	private Date marriageRegistrationTime;
	/**
	 * 领证情况
	 */
	private PropertyDict licenseSituation;
	/**
	 * 最近再婚时间
	 */
	private Date marriageOrDivorceTime;
	/**
	 * 生育服务证号
	 */
	private String fertilityServicesNo;
	/**
	 * 领证时间
	 */
	private Date licenseTime;
	/**
	 * 避孕方法
	 */
	private String contraceptiveMethod;
	/**
	 * 避孕起始时间
	 */
	private Date contraceptiveTime;
	/**
	 * 男孩数
	 */
	private int boyNumber;
	/**
	 * 女孩数
	 */
	private int girlNumber;
	/**
	 * 夫妻双方独生子女情况
	 */
	private PropertyDict onechildOfCouple;
	/**
	 * 男方身份证号
	 */
	private String manIdcardNo;
	/**
	 * 男方姓名
	 */
	private String manName;
	/**
	 * 男方婚姻状况
	 */
	private PropertyDict manMaritalState;
	/**
	 * 男方初婚时间
	 */
	private Date manFirstMarriageTime;
	/**
	 * 男方联系手机
	 */
	private String manMobileNumber;
	/**
	 * 男方固定电话
	 */
	private String manTelephone;
	/**
	 * 男方出生日期
	 */
	private Date manBirthday;
	/**
	 * 男方民族
	 */
	private PropertyDict manNation;
	/**
	 * 男方政治面貌
	 */
	private PropertyDict manPoliticalBackground;
	/**
	 * 男方文化程度
	 */
	private PropertyDict manSchooling;
	/**
	 * 男方户口类型
	 */
	private PropertyDict manResidenceType;
	/**
	 * 男方职业
	 */
	private PropertyDict manCareer;
	/**
	 * 男方工作单位
	 */
	private String manWorkUnit;
	/**
	 * 男方户籍地 省
	 */
	private String manProvince;
	/**
	 * 男方户籍地 市
	 */
	private String manCity;
	/**
	 * 男方户籍地 区县
	 */
	private String manDistrict;
	/**
	 * 男方户籍地详址
	 */
	private String manNativeplaceAddress;
	/**
	 * 男方常住地址
	 */
	private String manCurrentAddress;
	/**
	 * 男方常住地址类型
	 */
	private PropertyDict manCurrentAddressType;
	/**
	 * 男方常住地址 商品房 小区
	 */
	private String manCommunity;
	/**
	 * 男方常住地址 商品房 幢
	 */
	private String manBlock;
	/**
	 * 男方常住地址 商品房 单元
	 */
	private String manUnit;
	/**
	 * 男方常住地址 商品房 室
	 */
	private String manRoom;
	/**
	 * 男方备注
	 */
	private String manRemark;

	/** 独生子女证号 */
	private String singleChildCardno;
	/** 独生子女证号领取时间 */
	private Date singleChildCDIssueTime;
	/** 发证单位：生育服务证 */
	private String certifiedUnit;
	/** 是否未婚先孕： */
	private String isUnmarriedPregnant;
	/** 是否征收： */
	private String isLevied;
	/** 是否有婚育证： */
	private String isMaternityCard;
	/** 发证单位： */
	private String maternityCardUnit;
	/** 证编号： */
	private String maternityCardNo;
	/** 发放时间： */
	private Date maternityCardIssueTime;
	/** 有效期： */
	private Date maternityCardValidityTime;
	/** 查验时间： */
	private Date maternityCardCheckTime;
	/** 查验情况： */
	private String maternityCardRemark;

	@JSON(format = "yyyy-MM-dd")
	public Date getFirstMarriageTime() {
		return firstMarriageTime;
	}

	public void setFirstMarriageTime(Date firstMarriageTime) {
		this.firstMarriageTime = firstMarriageTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getMarriageRegistrationTime() {
		return marriageRegistrationTime;
	}

	public void setMarriageRegistrationTime(Date marriageRegistrationTime) {
		this.marriageRegistrationTime = marriageRegistrationTime;
	}

	public PropertyDict getLicenseSituation() {
		return licenseSituation;
	}

	public void setLicenseSituation(PropertyDict licenseSituation) {
		this.licenseSituation = licenseSituation;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getMarriageOrDivorceTime() {
		return marriageOrDivorceTime;
	}

	public void setMarriageOrDivorceTime(Date marriageOrDivorceTime) {
		this.marriageOrDivorceTime = marriageOrDivorceTime;
	}

	public String getFertilityServicesNo() {
		return fertilityServicesNo;
	}

	public void setFertilityServicesNo(String fertilityServicesNo) {
		this.fertilityServicesNo = fertilityServicesNo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLicenseTime() {
		return licenseTime;
	}

	public void setLicenseTime(Date licenseTime) {
		this.licenseTime = licenseTime;
	}

	public String getContraceptiveMethod() {
		return contraceptiveMethod;
	}

	public void setContraceptiveMethod(String contraceptiveMethod) {
		this.contraceptiveMethod = contraceptiveMethod;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getContraceptiveTime() {
		return contraceptiveTime;
	}

	public void setContraceptiveTime(Date contraceptiveTime) {
		this.contraceptiveTime = contraceptiveTime;
	}

	public int getBoyNumber() {
		return boyNumber;
	}

	public void setBoyNumber(int boyNumber) {
		this.boyNumber = boyNumber;
	}

	public int getGirlNumber() {
		return girlNumber;
	}

	public void setGirlNumber(int girlNumber) {
		this.girlNumber = girlNumber;
	}

	public PropertyDict getOnechildOfCouple() {
		return onechildOfCouple;
	}

	public void setOnechildOfCouple(PropertyDict onechildOfCouple) {
		this.onechildOfCouple = onechildOfCouple;
	}

	public PropertyDict getManNation() {
		return manNation;
	}

	public void setManNation(PropertyDict manNation) {
		this.manNation = manNation;
	}

	public String getManIdcardNo() {
		return manIdcardNo;
	}

	public void setManIdcardNo(String manIdcardNo) {
		this.manIdcardNo = manIdcardNo;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public PropertyDict getManMaritalState() {
		return manMaritalState;
	}

	public void setManMaritalState(PropertyDict manMaritalState) {
		this.manMaritalState = manMaritalState;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getManFirstMarriageTime() {
		return manFirstMarriageTime;
	}

	public void setManFirstMarriageTime(Date manFirstMarriageTime) {
		this.manFirstMarriageTime = manFirstMarriageTime;
	}

	public String getManMobileNumber() {
		return manMobileNumber;
	}

	public void setManMobileNumber(String manMobileNumber) {
		this.manMobileNumber = manMobileNumber;
	}

	public String getManTelephone() {
		return manTelephone;
	}

	public void setManTelephone(String manTelephone) {
		this.manTelephone = manTelephone;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getManBirthday() {
		return manBirthday;
	}

	public void setManBirthday(Date manBirthday) {
		this.manBirthday = manBirthday;
	}

	public PropertyDict getManPoliticalBackground() {
		return manPoliticalBackground;
	}

	public void setManPoliticalBackground(PropertyDict manPoliticalBackground) {
		this.manPoliticalBackground = manPoliticalBackground;
	}

	public PropertyDict getManSchooling() {
		return manSchooling;
	}

	public void setManSchooling(PropertyDict manSchooling) {
		this.manSchooling = manSchooling;
	}

	public PropertyDict getManResidenceType() {
		return manResidenceType;
	}

	public void setManResidenceType(PropertyDict manResidenceType) {
		this.manResidenceType = manResidenceType;
	}

	public PropertyDict getManCareer() {
		return manCareer;
	}

	public void setManCareer(PropertyDict manCareer) {
		this.manCareer = manCareer;
	}

	public String getManWorkUnit() {
		return manWorkUnit;
	}

	public void setManWorkUnit(String manWorkUnit) {
		this.manWorkUnit = manWorkUnit;
	}

	public String getManProvince() {
		return manProvince;
	}

	public void setManProvince(String manProvince) {
		this.manProvince = manProvince;
	}

	public String getManCity() {
		return manCity;
	}

	public void setManCity(String manCity) {
		this.manCity = manCity;
	}

	public String getManDistrict() {
		return manDistrict;
	}

	public void setManDistrict(String manDistrict) {
		this.manDistrict = manDistrict;
	}

	public String getManNativeplaceAddress() {
		return manNativeplaceAddress;
	}

	public void setManNativeplaceAddress(String manNativeplaceAddress) {
		this.manNativeplaceAddress = manNativeplaceAddress;
	}

	public String getManCurrentAddress() {
		return manCurrentAddress;
	}

	public void setManCurrentAddress(String manCurrentAddress) {
		this.manCurrentAddress = manCurrentAddress;
	}

	public PropertyDict getManCurrentAddressType() {
		return manCurrentAddressType;
	}

	public void setManCurrentAddressType(PropertyDict manCurrentAddressType) {
		this.manCurrentAddressType = manCurrentAddressType;
	}

	public String getManCommunity() {
		return manCommunity;
	}

	public void setManCommunity(String manCommunity) {
		this.manCommunity = manCommunity;
	}

	public String getManBlock() {
		return manBlock;
	}

	public void setManBlock(String manBlock) {
		this.manBlock = manBlock;
	}

	public String getManUnit() {
		return manUnit;
	}

	public void setManUnit(String manUnit) {
		this.manUnit = manUnit;
	}

	public String getManRoom() {
		return manRoom;
	}

	public void setManRoom(String manRoom) {
		this.manRoom = manRoom;
	}

	public String getManRemark() {
		return manRemark;
	}

	public void setManRemark(String manRemark) {
		this.manRemark = manRemark;
	}

	public String getSingleChildCardno() {
		return singleChildCardno;
	}

	public void setSingleChildCardno(String singleChildCardno) {
		this.singleChildCardno = singleChildCardno;
	}

	public String getCertifiedUnit() {
		return certifiedUnit;
	}

	public void setCertifiedUnit(String certifiedUnit) {
		this.certifiedUnit = certifiedUnit;
	}

	public String getMaternityCardUnit() {
		return maternityCardUnit;
	}

	public void setMaternityCardUnit(String maternityCardUnit) {
		this.maternityCardUnit = maternityCardUnit;
	}

	public String getMaternityCardNo() {
		return maternityCardNo;
	}

	public void setMaternityCardNo(String maternityCardNo) {
		this.maternityCardNo = maternityCardNo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getMaternityCardIssueTime() {
		return maternityCardIssueTime;
	}

	public void setMaternityCardIssueTime(Date maternityCardIssueTime) {
		this.maternityCardIssueTime = maternityCardIssueTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getMaternityCardValidityTime() {
		return maternityCardValidityTime;
	}

	public void setMaternityCardValidityTime(Date maternityCardValidityTime) {
		this.maternityCardValidityTime = maternityCardValidityTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getMaternityCardCheckTime() {
		return maternityCardCheckTime;
	}

	public void setMaternityCardCheckTime(Date maternityCardCheckTime) {
		this.maternityCardCheckTime = maternityCardCheckTime;
	}

	public String getMaternityCardRemark() {
		return maternityCardRemark;
	}

	public void setMaternityCardRemark(String maternityCardRemark) {
		this.maternityCardRemark = maternityCardRemark;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSingleChildCDIssueTime() {
		return singleChildCDIssueTime;
	}

	public void setSingleChildCDIssueTime(Date singleChildCDIssueTime) {
		this.singleChildCDIssueTime = singleChildCDIssueTime;
	}

	public String getIsUnmarriedPregnant() {
		return isUnmarriedPregnant;
	}

	public void setIsUnmarriedPregnant(String isUnmarriedPregnant) {
		this.isUnmarriedPregnant = isUnmarriedPregnant;
	}

	public String getIsLevied() {
		return isLevied;
	}

	public void setIsLevied(String isLevied) {
		this.isLevied = isLevied;
	}

	public String getIsMaternityCard() {
		return isMaternityCard;
	}

	public void setIsMaternityCard(String isMaternityCard) {
		this.isMaternityCard = isMaternityCard;
	}

	public String getManDetailNativeAddress() {
		return new StringBuffer(null != getManProvince() ? getManProvince()
				: "").append(null != getManCity() ? getManCity() : "")
				.append(null != getManDistrict() ? getManDistrict() : "")
				.toString();
	}
}
