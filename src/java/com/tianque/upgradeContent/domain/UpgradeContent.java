package com.tianque.upgradeContent.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class UpgradeContent extends BaseDomain {
	/**
	 * 升级时间
	 */
	private Date upgradeDate;
	/**
	 * 升级内容
	 */
	private String upgradeContent;
	/**
	 * 是否显示 升级内容
	 */
	private Long isUpgrad;

	@JSON(format = "yyyy-MM-dd")
	public Date getUpgradeDate() {
		return upgradeDate;
	}

	public void setUpgradeDate(Date upgradeDate) {
		this.upgradeDate = upgradeDate;
	}

	public String getUpgradeContent() {
		return upgradeContent;
	}

	public void setUpgradeContent(String upgradeContent) {
		this.upgradeContent = upgradeContent;
	}

	public Long getIsUpgrad() {
		return isUpgrad;
	}

	public void setIsUpgrad(Long isUpgrad) {
		this.isUpgrad = isUpgrad;
	}
}
