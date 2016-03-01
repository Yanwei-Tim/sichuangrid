package com.tianque.issue.state;

import java.util.List;

import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public interface IssueState {

	final static Integer DEALING = 1; // 办理中
	final static Integer CLOSED = 250;// 关闭
	final static Integer DEALOVER = 200;//
	final static Integer COMPLETE = 150;// 事件办结（待验证）
	final static Integer VERIFICATION = 300; // 事件结案，验证通过
	final static Integer GRADEDELAY = 301;// 已评分，仅仅做页面显示不做实际存储

	public final static int RED_CARD_SUPERVISE = 200;
	public final static int YELLOW_CARD_SUPERVISE = 100;
	public final static int NORMAL_SUPERVISE = 1;
	public final static int NO_SUPERVISE = -1;
	public static final int ISSUE_EXTENDED = 1;// 超期督办
	public static final int ISSUE_NOT_EXTENDED = 0;// 取消督办

	public final static int UNGIVETO_CODE = 80;
	public final static int NEWISSUE_CODE = 100;
	public final static int UNCONCEPTED_CODE = 110;// 待受理
	public final static int DEALING_CODE = 120; // 处理中
	public final static int UNREAD_CODE = 140; // 待阅读
	public final static int STEPCOMPLETE_CODE = 500; // 转到下一个流程（新增一条流程信息）前，设置的值
	public final static int ISSUEVERIFICATION_CODE = 800;// 待验证
	public final static int ISSUEUNVERIFICATION_CODE = 900;// 验证未通过,回退 900
	public final static int ISSUECOMPLETE_CODE = 1000; // 已验证事件（验证通过的）
	/** 备案 即完成状态 */
	public final static int BACKUPS_CODE = 1100;

	/**
	 * @return 关闭
	 */
	IssueStep close(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 处理完成
	 */
	IssueStep dealOver(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 办理中
	 */
	void comment(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 上报到分流中心
	 */
	IssueStep reportTo(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 交办（派单）
	 */
	IssueStepGroup assign(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 完成
	 */
	IssueStepGroup complete(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 验证
	 */
	void verification(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 上报
	 */
	IssueStepGroup submit(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context, IssueSkiprule issueSkiprule)
			throws UnsupportOperationException, IssueOperationException;

	/**
	 * @return 领导批示
	 */
	void instruct(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 受理
	 */
	void concept(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 阅读
	 */
	void read(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 督办
	 */
	void supervise(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 取消督办
	 */
	void cancelSupervise(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 反馈
	 */
	IssueStep fedBack(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 下单（指挥中心下派）
	 */
	IssueStepGroup giveTo(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 回退
	 */
	IssueStepGroup back(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException;

	/**
	 * @return 获取能做的操作
	 */
	List<IssueOperate> getCando(IssueStepInfo step, OrganizationInfo orgInfo);

	/**
	 * 备案
	 */
	void backUps(IssueNew issue, IssueStep step, IssueOperationContext context);
}
