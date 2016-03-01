package com.tianque.platformMessage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.User;

@SuppressWarnings("serial")
public class PlatformMessageAttachFile extends BaseDomain {
	/** 发件箱附件 */
	public final static int SEND_ATTACH_FILE = 1;
	/** 收件箱附件 */
	public final static int RECEIVE_ATTACH_FILE = 2;

	/** 附件关联消息ID */
	private Long pmId;
	/** 文件名称 */
	private String fileName;
	/** 文件路径 */
	private String fileActualUrl;
	/** 文件大小 */
	private Long fileSize;
	/** 用户对象 */
	private User user;
	/** 附件关联类别 */
	private int attachType;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getPmId() {
		return pmId;
	}

	public void setPmId(Long pmId) {
		this.pmId = pmId;
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public int getAttachType() {
		return attachType;
	}

	public void setAttachType(int attachType) {
		this.attachType = attachType;
	}

}
