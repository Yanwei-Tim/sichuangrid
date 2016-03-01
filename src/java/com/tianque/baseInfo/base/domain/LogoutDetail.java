package com.tianque.baseInfo.base.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.service.vo.IsEmphasis;

public class LogoutDetail extends BaseDomain {
	public static String LOGOUT_REASON_FOR_DEATH = "修改为死亡，自动注销";
	// public static String CANCLE_LOGOUT_REASON_FOR_CANCLEDEATH="取消死亡，自动重新关注";

	private Long logout;// 注销关注状态
	private Date logoutDate;// 注销时间
	private String logoutReason;// 注销原因

	// private Date cancelLogoutDate;//取消注销时间
	// private String cancelLogoutReason;//取消注销原因

	public Date getLogoutDate() {
		return logoutDate;
	}

	public Long getLogout() {
		return logout;
	}

	public void setLogout(Long logout) {
		this.logout = logout;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getLogoutReason() {
		return logoutReason;
	}

	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}

	public static LogoutDetail buildLogoutDetail(Boolean death) {
		LogoutDetail result = new LogoutDetail();
		if (death) {
			result.setLogoutDate(new Date());
			result.setLogoutReason(LogoutDetail.LOGOUT_REASON_FOR_DEATH);
			result.setLogout(IsEmphasis.IsNotEmphasis);
		} else if (!death) {
			result.setLogout(IsEmphasis.Emphasis);
		}
		return result;
	}
}
