package com.tianque.baseInfo.scenicManage.praiseScenicSpot.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class PraiseScenicSpot extends BaseDomain {
	private Long scenicSpotId;
	private PropertyDict feedbackType;
	private Date feedbackTime;
	private String feedbackPerson;
	private String feedbackPersonTelephone;
	private String introduction;
	private String fullPinyin;
	private String simplePinyin;

	public Long getScenicSpotId() {
		return scenicSpotId;
	}

	public void setScenicSpotId(Long scenicSpotId) {
		this.scenicSpotId = scenicSpotId;
	}

	public PropertyDict getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(PropertyDict feedbackType) {
		this.feedbackType = feedbackType;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	public void setFeedbackPerson(String feedbackPerson) {
		this.feedbackPerson = feedbackPerson;
	}

	public String getFeedbackPersonTelephone() {
		return feedbackPersonTelephone;
	}

	public void setFeedbackPersonTelephone(String feedbackPersonTelephone) {
		this.feedbackPersonTelephone = feedbackPersonTelephone;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}
}
