package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.io.Serializable;

/**
 * 事件处理绩效考核配置
 */
public class FourTeamsIssueAccessConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 黄牌扣分:默认扣5分 */
	private Double yellowCardScores = 5.0;
	/** 红牌扣分: 默认扣10分 */
	private Double redCardScores = 10.0;
	/** 黄牌受理期限:默认1工作日 */
	private Long yellowCardAccepted = 1L;
	/** 红牌受理期限:默认2工作日 */
	private Long redCardAccepted = 2L;
	/** 黄牌处理期限:默认5工作日 */
	private Long yellowCardHandle = 5L;
	/** 红牌处理期限:默认10工作日 */
	private Long redCardHandle = 10L;
	/** 正常处理加分:默认加1分 */
	private Double normalScores = 1.0;

	public Double getYellowCardScores() {
		return yellowCardScores;
	}

	public void setYellowCardScores(Double yellowCardScores) {
		this.yellowCardScores = yellowCardScores;
	}

	public Double getRedCardScores() {
		return redCardScores;
	}

	public void setRedCardScores(Double redCardScores) {
		this.redCardScores = redCardScores;
	}

	public Long getYellowCardAccepted() {
		return yellowCardAccepted;
	}

	public void setYellowCardAccepted(Long yellowCardAccepted) {
		this.yellowCardAccepted = yellowCardAccepted;
	}

	public Long getRedCardAccepted() {
		return redCardAccepted;
	}

	public void setRedCardAccepted(Long redCardAccepted) {
		this.redCardAccepted = redCardAccepted;
	}

	public Long getYellowCardHandle() {
		return yellowCardHandle;
	}

	public void setYellowCardHandle(Long yellowCardHandle) {
		this.yellowCardHandle = yellowCardHandle;
	}

	public Long getRedCardHandle() {
		return redCardHandle;
	}

	public void setRedCardHandle(Long redCardHandle) {
		this.redCardHandle = redCardHandle;
	}

	public Double getNormalScores() {
		return normalScores;
	}

	public void setNormalScores(Double normalScores) {
		this.normalScores = normalScores;
	}

}
