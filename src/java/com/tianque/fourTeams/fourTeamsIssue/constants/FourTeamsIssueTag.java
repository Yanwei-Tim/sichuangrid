package com.tianque.fourTeams.fourTeamsIssue.constants;

/**
 * 用于事件模块置顶操作时判断是哪个子模块的置顶操作
 * 
 * @author hjw
 */
public interface FourTeamsIssueTag {
	/** 待办事项 */
	final int NEEDDO_ISSUE = 1;
	/** 已办事项 */
	final int DONE_ISSUE = 2;
	/** 已办结事项 */
	final int COMPLETED_ISSUE = 3;
	/** 历史遗留 */
	final int HISTORICA_ISSUE = 4;
	/** 宣传案例 */
	final int PUBLICLTYCASS_ISSUE = 5;
	/** 上报 */
	final int SUBMIT_ISSUE = 6;
	/** 上级交办 */
	final int ASSIGN_ISSUE = 7;
	/** 越级 */
	final int SKIP_ISSUE = 8;
}
