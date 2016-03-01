package com.tianque.fourTeams.fourTeamsIssue.event;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;

public class FourTeamsIssueChangeEvent {
	/** 事件处理记录 */
	private FourTeamsIssueLogNew operateLog;

	private List<FourTeamsIssueAttachFile> operateFiles;
	/** 操作 */
	private FourTeamsIssueOperate operate;

	public FourTeamsIssueChangeEvent(FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> operateFiles, FourTeamsIssueOperate operate) {
		this.operateLog = log;
		this.operate = operate;
		this.operateFiles = operateFiles;
	}

	public FourTeamsIssueLogNew getOperateLog() {
		return operateLog;
	}

	public void setOperateLog(FourTeamsIssueLogNew operateLog) {
		this.operateLog = operateLog;
	}

	public List<FourTeamsIssueAttachFile> getOperateFiles() {
		return operateFiles;
	}

	public void setOperateFiles(List<FourTeamsIssueAttachFile> operateFiles) {
		this.operateFiles = operateFiles;
	}

	public FourTeamsIssueOperate getOperate() {
		return operate;
	}

	public void setOperate(FourTeamsIssueOperate operate) {
		this.operate = operate;
	}

}
