package com.tianque.issue.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tianque.core.base.BaseDomain;

public class IssueAttachFile extends BaseDomain {

	/** 附件文件名称 */
	private String fileName;
	/** 附件文件的路径 */
	private String fileActualUrl;
	/** 附件所属的事件 */
	private IssueNew issue;
	/** 附件所属的事件办理记录 (在事件办理过程中添加的附件) */
	private IssueLogNew issueLog;

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public IssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(IssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileActualUrl() {
		return fileActualUrl;
	}

	public void setFileActualUrl(String fileActualUrl) {
		this.fileActualUrl = fileActualUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IssueAttachFile)) {
			return false;
		}
		if (!((getClass().isAssignableFrom(obj.getClass())) || (obj.getClass()
				.isAssignableFrom(getClass())))) {
			return false;
		}

		IssueAttachFile file = (IssueAttachFile) obj;

		if (getId() == null || file.getId() == null) {
			return false;
		}

		return getId().equals(file.getId());

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(19999, 88).toHashCode();
	}

}
