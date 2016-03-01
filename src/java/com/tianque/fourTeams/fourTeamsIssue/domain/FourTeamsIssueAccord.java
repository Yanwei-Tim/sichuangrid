package com.tianque.fourTeams.fourTeamsIssue.domain;

import com.tianque.core.base.BaseDomain;

/**
 * @ClassName: IssueAccord
 * @Description: 考核打分
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 7:26:56 PM
 */
public class FourTeamsIssueAccord extends BaseDomain {
	private Double score[];// 分值
	private Integer type[];// 1 加分 0减分
	private Long orgId[];// 打分机构
	private String info[];// 打分原因
	private Long logId[];// 日志ID

	public Double[] getScore() {
		return score;
	}

	public void setScore(Double[] score) {
		this.score = score;
	}

	public Integer[] getType() {
		return type;
	}

	public void setType(Integer[] type) {
		this.type = type;
	}

	public String[] getInfo() {
		return info;
	}

	public void setInfo(String[] info) {
		this.info = info;
	}

	public Long[] getOrgId() {
		return orgId;
	}

	public void setOrgId(Long[] orgId) {
		this.orgId = orgId;
	}

	public Long[] getLogId() {
		return logId;
	}

	public void setLogId(Long[] logId) {
		this.logId = logId;
	}
}
