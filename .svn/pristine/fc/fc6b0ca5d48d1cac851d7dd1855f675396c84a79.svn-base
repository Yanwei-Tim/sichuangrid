package com.tianque.resourcePool.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

public class MyProfile extends BaseDomain implements Cloneable {
	public static String SOURCE_SELF_WRITE = "自行录入";
	public static String SOURCE_FROM_WORKING = "从台账中同步";
	public static String SOURCE_FROM_DOCUMENT = "从公文中同步";
	public static Long SENDMESSAGE_YES = 1L;
	public static Long SENDMESSAGE_NO = 0L;
	public static Long shareState_YES = 2L;
	public static Long shareState_NO = 1L;

	/** 网格内置编码 */
	private String orgInternalCode;
	/** 所属网格 */
	private Organization organization;
	/** 资料类型 */
	private DirectorySetting resourcePoolType;

	/** 名称 */
	private String name;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** 创建者Id */
	private Long userId;
	/** 时间、发文时间 */
	private Date releaseTime;
	/** 发文、所属单位 */
	private String releaseUnit;
	/** 内容 */
	private String content;
	/** 文件号 */
	private String fileNo;
	/** 文件主题 */
	private String documentSubject;
	/** 是否发送信息 0是不发送 1是发送 */
	private Long sendMessage;
	/** 共享状态 1是未共享 2是共享 */
	private Long shareState;
	/** 共享目录 */
	private DirectorySetting shareDirectory;
	/** 共享时间 */
	private Date shareDate;
	/** 共享来源 */
	private String source = MyProfile.SOURCE_SELF_WRITE;

	private String shareUserRealName;

	/** 附件 */
	private List<MyProfileAttachFile> myProfileAttachFile = new ArrayList<MyProfileAttachFile>();
	/**
	 * 是否共享给所有人的标识
	 */
	private boolean underJurisdiction = false;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public DirectorySetting getResourcePoolType() {
		return resourcePoolType;
	}

	public void setResourcePoolType(DirectorySetting resourcePoolType) {
		this.resourcePoolType = resourcePoolType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseUnit() {
		return releaseUnit;
	}

	public void setReleaseUnit(String releaseUnit) {
		this.releaseUnit = releaseUnit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getDocumentSubject() {
		return documentSubject;
	}

	public void setDocumentSubject(String documentSubject) {
		this.documentSubject = documentSubject;
	}

	public List<MyProfileAttachFile> getMyProfileAttachFile() {
		return myProfileAttachFile;
	}

	public void setMyProfileAttachFile(
			List<MyProfileAttachFile> myProfileAttachFile) {
		this.myProfileAttachFile = myProfileAttachFile;
	}

	public boolean isUnderJurisdiction() {
		return underJurisdiction;
	}

	public void setUnderJurisdiction(boolean underJurisdiction) {
		this.underJurisdiction = underJurisdiction;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getShareState() {
		return shareState;
	}

	public void setShareState(Long shareState) {
		this.shareState = shareState;
	}

	public DirectorySetting getShareDirectory() {
		return shareDirectory;
	}

	public void setShareDirectory(DirectorySetting shareDirectory) {
		this.shareDirectory = shareDirectory;
	}

	public Long getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(Long sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getShareStateStr() {
		if (MyProfile.shareState_NO.equals(this.shareState)) {
			return "未共享";
		}
		if (MyProfile.shareState_YES.equals(this.shareState)) {
			return "已共享";
		}
		return "";
	}

	public String getShareUserRealName() {
		return shareUserRealName;
	}

	public void setShareUserRealName(String shareUserRealName) {
		this.shareUserRealName = shareUserRealName;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyProfile myProfile = null;
		try {
			myProfile = (MyProfile) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return myProfile;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}

}
