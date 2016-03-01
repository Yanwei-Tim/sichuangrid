package com.tianque.fourTeams.fourTeamsIssue.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.tianque.core.base.BaseDomain;

public class FourTeamsIssueAttachFile extends BaseDomain {

	/** 附件文件名称 */
	private String fileName;
	/** 附件文件的路径 */
	private String fileActualUrl;
	/** 附件所属的事件 */
	private FourTeamsIssueNew issue;
	/** 附件所属的事件办理记录 (在事件办理过程中添加的附件) */
	private FourTeamsIssueLogNew issueLog;

	public FourTeamsIssueNew getIssue() {
		return issue;
	}

	public void setIssue(FourTeamsIssueNew issue) {
		this.issue = issue;
	}

	public FourTeamsIssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(FourTeamsIssueLogNew issueLog) {
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
		if (!(obj instanceof FourTeamsIssueAttachFile)) {
			return false;
		}
		if (!((getClass().isAssignableFrom(obj.getClass())) || (obj.getClass()
				.isAssignableFrom(getClass())))) {
			return false;
		}

		FourTeamsIssueAttachFile file = (FourTeamsIssueAttachFile) obj;

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
