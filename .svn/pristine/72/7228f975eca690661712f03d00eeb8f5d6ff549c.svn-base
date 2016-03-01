package com.tianque.statRegrad.service;

import java.util.List;

import com.tianque.statRegrad.domain.IntegratedIndicator;
import com.tianque.statRegrad.domain.IssueGradeNode;

/**
 * 
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年11月19日
 */
public interface IssueGradeService {

	public List<IssueGradeNode> findIssueGradeNode(String date, Long hash,
			List<Long> valueList);

	/**
	 * 事件处理成绩表综合指标
	 * 
	 * @param date
	 */
	public int statRegradedPoints(String date);

	/**
	 * 计算扣分分数
	 * 
	 * @param issueGradeNode
	 */
	public IntegratedIndicator computeGrade(IssueGradeNode issueGradeNode);

	/**
	 * 清除当月数据
	 * 
	 * @param orgList
	 *            <code>null<code>表示是清除所有
	 * @return 删除的记录数
	 */
	public int clearIssueGradeNode(List<Long> orgList);

}
