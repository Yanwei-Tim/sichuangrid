package com.tianque.baseInfo.handicapped.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * 残疾人
 * 
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Handicapped extends AttentionPopulation {
	/** 监护人 */
	private String guardianName;

	/** 残疾证号 */
	private String disabilityCardNo;
	/** 残疾原因 */
	private String disabilityReason;
	/** 残疾等级 */
	private PropertyDict disability;
	private List<PropertyDict> disabilitys;
	/** 残疾类别 */
	private PropertyDict disabilityType;
	private List<PropertyDict> disabilityTypes;
	/** 技能特长 */
	private PropertyDict skillProfile;
	/** 劳动能力 */
	private PropertyDict workProfile;

	/**
	 * 是否有残疾证
	 */
	private Boolean hadDisabilityCard;
	/**
	 * 残疾时间
	 */
	private Date disabilityDate;

	private PropertyDict disabilityIntellect;// 智力等级 数据导入时用到
	private PropertyDict disabilitysLimbs;// 肢体残疾等级
	private PropertyDict disabilitysMental;// 精神残疾等级
	private PropertyDict disabilitysHearing;// 听力残疾等级
	private PropertyDict disabilitysSpeech;// 言语残疾等级
	private PropertyDict disabilitysVision;// 视力残疾等级

	private List<Long> disabilityTypeIds;// 残疾类别
	private List<Long> disabilityLevelIds;// 残疾等级

	private String typeIdsForMobile;
	private String levelIdsForMobile;
	private Map<Long, String> disablilityLevelMapForMobile;

	public Handicapped() {
		setAttentionPopulationType(BaseInfoTables.HANDICAPPED_KEY);
	}

	public String getDisabilityReason() {
		return disabilityReason;
	}

	public void setDisabilityReason(String disabilityReason) {
		this.disabilityReason = disabilityReason;
	}

	public Boolean getHadDisabilityCard() {
		return hadDisabilityCard;
	}

	public void setHadDisabilityCard(Boolean hadDisabilityCard) {
		this.hadDisabilityCard = hadDisabilityCard;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDisabilityDate() {
		return disabilityDate;
	}

	public void setDisabilityDate(Date disabilityDate) {
		this.disabilityDate = disabilityDate;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public PropertyDict getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(PropertyDict disabilityType) {
		this.disabilityType = disabilityType;
	}

	public PropertyDict getWorkProfile() {
		return workProfile;
	}

	public void setWorkProfile(PropertyDict workProfile) {
		this.workProfile = workProfile;
	}

	public String getDisabilityCardNo() {
		return disabilityCardNo;
	}

	public void setDisabilityCardNo(String disabilityCardNo) {
		this.disabilityCardNo = disabilityCardNo;
	}

	public PropertyDict getDisability() {
		return disability;
	}

	public void setDisability(PropertyDict disability) {
		this.disability = disability;
	}

	public PropertyDict getSkillProfile() {
		return skillProfile;
	}

	public void setSkillProfile(PropertyDict skillProfile) {
		this.skillProfile = skillProfile;
	}

	public List<PropertyDict> getDisabilityTypes() {
		return disabilityTypes;
	}

	public void setDisabilityTypes(List<PropertyDict> disabilityTypes) {
		this.disabilityTypes = disabilityTypes;
	}

	public List<Long> getDisabilityTypeIds() {
		return disabilityTypeIds;
	}

	public void setDisabilityTypeIds(List<Long> disabilityTypeIds) {
		this.disabilityTypeIds = disabilityTypeIds;
	}

	public List<Long> getDisabilityLevelIds() {
		return disabilityLevelIds;
	}

	public void setDisabilityLevelIds(List<Long> disabilityLevelIds) {
		this.disabilityLevelIds = disabilityLevelIds;
	}

	public List<PropertyDict> getDisabilitys() {
		return disabilitys;
	}

	public void setDisabilitys(List<PropertyDict> disabilitys) {
		this.disabilitys = disabilitys;
	}

	public PropertyDict getDisabilityIntellect() {
		return disabilityIntellect;
	}

	public void setDisabilityIntellect(PropertyDict disabilityIntellect) {
		this.disabilityIntellect = disabilityIntellect;
	}

	public PropertyDict getDisabilitysLimbs() {
		return disabilitysLimbs;
	}

	public void setDisabilitysLimbs(PropertyDict disabilitysLimbs) {
		this.disabilitysLimbs = disabilitysLimbs;
	}

	public PropertyDict getDisabilitysMental() {
		return disabilitysMental;
	}

	public void setDisabilitysMental(PropertyDict disabilitysMental) {
		this.disabilitysMental = disabilitysMental;
	}

	public PropertyDict getDisabilitysHearing() {
		return disabilitysHearing;
	}

	public void setDisabilitysHearing(PropertyDict disabilitysHearing) {
		this.disabilitysHearing = disabilitysHearing;
	}

	public PropertyDict getDisabilitysSpeech() {
		return disabilitysSpeech;
	}

	public void setDisabilitysSpeech(PropertyDict disabilitysSpeech) {
		this.disabilitysSpeech = disabilitysSpeech;
	}

	public PropertyDict getDisabilitysVision() {
		return disabilitysVision;
	}

	public void setDisabilitysVision(PropertyDict disabilitysVision) {
		this.disabilitysVision = disabilitysVision;
	}

	public String getTypeIdsForMobile() {
		return typeIdsForMobile;
	}

	public void setTypeIdsForMobile(String typeIdsForMobile) {
		this.typeIdsForMobile = typeIdsForMobile;
	}

	public String getLevelIdsForMobile() {
		return levelIdsForMobile;
	}

	public void setLevelIdsForMobile(String levelIdsForMobile) {
		this.levelIdsForMobile = levelIdsForMobile;
	}

	public Map<Long, String> getDisablilityLevelMapForMobile() {
		return disablilityLevelMapForMobile;
	}

	public void setDisablilityLevelMapForMobile(
			Map<Long, String> disablilityLevelMapForMobile) {
		this.disablilityLevelMapForMobile = disablilityLevelMapForMobile;
	}

}
