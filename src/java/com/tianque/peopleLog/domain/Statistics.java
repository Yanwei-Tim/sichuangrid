package com.tianque.peopleLog.domain;

import com.tianque.core.base.BaseDomain;

public class Statistics extends BaseDomain {
	/* 今日新增 */
	private Long todayNum;
	/* 本周新增 */
	private Long weekNum;
	/* 本月新增 */
	private Long mouthNum;
	/* 本月点评 */
	private Long mouthCommentNum;
	/* 日志总数 */
	private long countNum;
	/* 点评总数 */
	private long commentNum;
	/* 直辖日志总数 */
	private long countSubMeNum;
	/* 直辖日志点评总数 */
	private long commentSubMeNum;
	/* 下辖日志总数 */
	private long countSubAllNum;
	/* 下辖日志点评总数 */
	private long commentSubAllNum;

	public Long getTodayNum() {
		return todayNum;
	}

	public void setTodayNum(Long todayNum) {
		this.todayNum = todayNum;
	}

	public Long getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(Long weekNum) {
		this.weekNum = weekNum;
	}

	public Long getMouthNum() {
		return mouthNum;
	}

	public void setMouthNum(Long mouthNum) {
		this.mouthNum = mouthNum;
	}

	public Long getMouthCommentNum() {
		return mouthCommentNum;
	}

	public void setMouthCommentNum(Long mouthCommentNum) {
		this.mouthCommentNum = mouthCommentNum;
	}

	public long getCountNum() {
		return countNum;
	}

	public void setCountNum(long countNum) {
		this.countNum = countNum;
	}

	public long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(long commentNum) {
		this.commentNum = commentNum;
	}

	public long getCountSubMeNum() {
		return countSubMeNum;
	}

	public void setCountSubMeNum(long countSubMeNum) {
		this.countSubMeNum = countSubMeNum;
	}

	public long getCommentSubMeNum() {
		return commentSubMeNum;
	}

	public void setCommentSubMeNum(long commentSubMeNum) {
		this.commentSubMeNum = commentSubMeNum;
	}

	public long getCountSubAllNum() {
		return countSubAllNum;
	}

	public void setCountSubAllNum(long countSubAllNum) {
		this.countSubAllNum = countSubAllNum;
	}

	public long getCommentSubAllNum() {
		return commentSubAllNum;
	}

	public void setCommentSubAllNum(long commentSubAllNum) {
		this.commentSubAllNum = commentSubAllNum;
	}

}
