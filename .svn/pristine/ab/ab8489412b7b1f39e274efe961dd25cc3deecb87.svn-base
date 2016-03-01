package com.tianque.issue.event;

import java.util.List;

import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.state.IssueOperate;

public class IssueChangeEvent {
	/** 事件处理记录 */
	private IssueLogNew operateLog;

	private List<IssueAttachFile> operateFiles;
	/** 操作 */
	private IssueOperate operate;

	public IssueChangeEvent(IssueLogNew log, List<IssueAttachFile> operateFiles,
			IssueOperate operate) {
		this.operateLog = log;
		this.operate = operate;
		this.operateFiles = operateFiles;
	}

	public IssueLogNew getOperateLog() {
		return operateLog;
	}

	public void setOperateLog(IssueLogNew operateLog) {
		this.operateLog = operateLog;
	}

	public List<IssueAttachFile> getOperateFiles() {
		return operateFiles;
	}

	public void setOperateFiles(List<IssueAttachFile> operateFiles) {
		this.operateFiles = operateFiles;
	}

	public IssueOperate getOperate() {
		return operate;
	}

	public void setOperate(IssueOperate operate) {
		this.operate = operate;
	}

}
