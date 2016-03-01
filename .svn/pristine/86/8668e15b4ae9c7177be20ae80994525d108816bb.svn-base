package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.plugin.tbSchedule.IScheduleTaskDealSingleBase;
import com.tianque.plugin.tbSchedule.constant.ScheduleNameConstant;
import com.tianque.statRegrad.domain.IssueGradeNode;
import com.tianque.statRegrad.service.IssueGradeService;

/**
 * 事件处理成绩表综合指标job
 * 
 * @author: 曾利民
 * @mail:zenglimin@hztianque.com
 * @date:2014年11月27日
 */
@Component("StatRegradeSchedule")
public class StatRegradeSchedule extends
		IScheduleTaskDealSingleBase<IssueGradeNode> {

	@Autowired
	private IssueGradeService issueGradeService;

	@Override
	public Comparator<IssueGradeNode> getComparator() {
		return null;
	}

	@Override
	public List<IssueGradeNode> queryTasks(String taskParameter,
			String ownSign, int taskItemNum,
			List<TaskItemDefine> queryCondition, int fetchNum) throws Exception {
		size.set(issueGradeService.statRegradedPoints(taskParameter));
		return new ArrayList<IssueGradeNode>();
	}

	@Override
	public boolean execute(IssueGradeNode issueGradeNode, String ownSign)
			throws Exception {
		return true;
	}

	@Override
	public void callBack() {
	}

	@Override
	public String getJobRemark() {
		return ScheduleNameConstant.ISSUE_GRAD_JOB_REMARK;
	}

}
