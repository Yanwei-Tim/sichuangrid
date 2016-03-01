package com.tianque.upgradeContent.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.User;

public class UserCheckUpgradeContent extends BaseDomain {
	private User user;
	/**
	 * 升级内容
	 */
	private UpgradeContent upgradeContent;
	/**
	 * 查看时间
	 */
	private Date checkDate;
	/**
	 * 查看状态
	 */
	private Long checkState;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UpgradeContent getUpgradeContent() {
		return upgradeContent;
	}

	public void setUpgradeContent(UpgradeContent upgradeContent) {
		this.upgradeContent = upgradeContent;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Long getCheckState() {
		return checkState;
	}

	public void setCheckState(Long checkState) {
		this.checkState = checkState;
	}
}
