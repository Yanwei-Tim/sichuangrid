package com.tianque.fourTeams.fourTeamsIssue.state;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public interface FourTeamsIssueState {

	final static Integer DEALING = 1;
	final static Integer CLOSED = 250;
	final static Integer DEALOVER = 200;
	final static Integer COMPLETE = 150;// 事件办结（待验证）
	final static Integer VERIFICATION = 300; // 事件结案，验证通过

	public final static int RED_CARD_SUPERVISE = 200;
	public final static int YELLOW_CARD_SUPERVISE = 100;
	public final static int NORMAL_SUPERVISE = 1;
	public final static int NO_SUPERVISE = -1;
	public static final int ISSUE_EXTENDED = 1;// 超期督办
	public static final int ISSUE_NOT_EXTENDED = 0;// 取消督办

	public final static int UNGIVETO_CODE = 80;
	public final static int NEWISSUE_CODE = 100;
	public final static int UNCONCEPTED_CODE = 110;
	public final static int DEALING_CODE = 120;
	public final static int UNREAD_CODE = 140;
	public final static int STEPCOMPLETE_CODE = 500;
	public final static int ISSUEVERIFICATION_CODE = 800;// 待验证
	public final static int ISSUEUNVERIFICATION_CODE = 900;// 验证未通过,回退 900
	public final static int ISSUECOMPLETE_CODE = 1000;
	/** 备案 即完成状态 */
	public final static int BACKUPS_CODE = 1100;

	/**
	 * @return 关闭
	 */
	FourTeamsIssueStep close(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 处理完成
	 */
	FourTeamsIssueStep dealOver(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 办理中
	 */
	void comment(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 上报到分流中心
	 */
	FourTeamsIssueStep reportTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 交办（派单）
	 */
	FourTeamsIssueStepGroup assign(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 完成
	 */
	void complete(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 上报
	 */
	FourTeamsIssueStepGroup submit(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context,
			FourTeamsIssueSkiprule issueSkiprule)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 领导批示
	 */
	void instruct(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 受理
	 */
	void concept(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 阅读
	 */
	void read(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 督办
	 */
	void supervise(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 取消督办
	 */
	void cancelSupervise(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 反馈
	 */
	FourTeamsIssueStep fedBack(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 下单（指挥中心下派）
	 */
	FourTeamsIssueStepGroup giveTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 回退
	 */
	FourTeamsIssueStepGroup back(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException;

	/**
	 * @return 获取能做的操作
	 */
	List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo);

	/**
	 * 备案
	 */
	void backUps(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueOperationContext context);
}
