package com.tianque.baseInfo.goodSamaritan.domain;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

public class GoodSamaritan extends AttentionPopulation {
	public GoodSamaritan() {
		setAttentionPopulationType(BaseInfoTables.GOOD_SAMARITAN_KEY);
	}

	/** 伤残等级 */
	private PropertyDict disableGradeType;
	/** 是否牺牲 */
	private PropertyDict sacrificeType;
	/** 参保情况 大类 */
	private PropertyDict insureSituationType;
	/** 参保情况小类 */
	private PropertyDict insureSituationSmallType;
	/** 奖项类型 */
	private PropertyDict awardType;
	/** 困难类型 */
	private PropertyDict difficultType;
	/** 行为发生的地 */
	private String actOccurred;
	/** 获奖年份 */
	private String awardYear;
	/** 确认单位 */
	private String confirmUnit;
	/** 见义勇为事迹 */
	private String heroicDeeds;

	private String[] personAttachFiles;

	public PropertyDict getDisableGradeType() {
		return disableGradeType;
	}

	public void setDisableGradeType(PropertyDict disableGradeType) {
		this.disableGradeType = disableGradeType;
	}

	public PropertyDict getSacrificeType() {
		return sacrificeType;
	}

	public void setSacrificeType(PropertyDict sacrificeType) {
		this.sacrificeType = sacrificeType;
	}

	public PropertyDict getInsureSituationType() {
		return insureSituationType;
	}

	public void setInsureSituationType(PropertyDict insureSituationType) {
		this.insureSituationType = insureSituationType;
	}

	public PropertyDict getInsureSituationSmallType() {
		return insureSituationSmallType;
	}

	public void setInsureSituationSmallType(
			PropertyDict insureSituationSmallType) {
		this.insureSituationSmallType = insureSituationSmallType;
	}

	public PropertyDict getAwardType() {
		return awardType;
	}

	public void setAwardType(PropertyDict awardType) {
		this.awardType = awardType;
	}

	public PropertyDict getDifficultType() {
		return difficultType;
	}

	public void setDifficultType(PropertyDict difficultType) {
		this.difficultType = difficultType;
	}

	public String getActOccurred() {
		return actOccurred;
	}

	public void setActOccurred(String actOccurred) {
		this.actOccurred = actOccurred;
	}

	public String getAwardYear() {
		return awardYear;
	}

	public void setAwardYear(String awardYear) {
		this.awardYear = awardYear;
	}

	public String getConfirmUnit() {
		return confirmUnit;
	}

	public void setConfirmUnit(String confirmUnit) {
		this.confirmUnit = confirmUnit;
	}

	public String getHeroicDeeds() {
		return heroicDeeds;
	}

	public void setHeroicDeeds(String heroicDeeds) {
		this.heroicDeeds = heroicDeeds;
	}

	public String[] getPersonAttachFiles() {
		return personAttachFiles;
	}

	public void setPersonAttachFiles(String[] personAttachFiles) {
		this.personAttachFiles = personAttachFiles;
	}
}
