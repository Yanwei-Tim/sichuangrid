package com.tianque.issueAbutmentJoint.domain.vo;

import java.util.Date;

import com.tianque.core.util.DateUtil;
import com.tianque.issueAbutmentJoint.domain.IssueJoint;

/**
 * @Description:对接事件搜索类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-24 下午09:58:49
 */
public class IssueJointVo extends IssueJoint {

	/** 涉及人数 */
	private Integer relatePeopleMinCount;
	/** 涉及人数 */
	private Integer relatePeopleMaxCount;
	/** 发生时间 */
	private Date occurDateStart;
	/** 发生时间 */
	private Date occurDateEnd;

	public Integer getRelatePeopleMinCount() {
		return relatePeopleMinCount;
	}

	public void setRelatePeopleMinCount(Integer relatePeopleMinCount) {
		this.relatePeopleMinCount = relatePeopleMinCount;
	}

	public Integer getRelatePeopleMaxCount() {
		return relatePeopleMaxCount;
	}

	public void setRelatePeopleMaxCount(Integer relatePeopleMaxCount) {
		this.relatePeopleMaxCount = relatePeopleMaxCount;
	}

	public Date getOccurDateStart() {
		return occurDateStart;
	}

	public void setOccurDateStart(Date occurDateStart) {
		this.occurDateStart = occurDateStart;
	}

	public Date getOccurDateEnd() {
		return occurDateEnd;
	}

	public void setOccurDateEnd(Date occurDateEnd) {
		if (occurDateEnd != null) {
			occurDateEnd = DateUtil.getEndTime(occurDateEnd);
		}
		this.occurDateEnd = occurDateEnd;
	}

}
