package com.tianque.publicSecurity.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 抓拍系统表:实体类(SNAPSHOTSYSTEM)
 */
public class VideoSystem extends publicSecurityCommon implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 编号(video_NO) */
	private String videoNo;

	/** 视频名称**/
	private String videoName;

	/** 视频url**/
	private String url;

	/** 账号名**/
	private String account;

	/** 密码**/
	private String password;

	/** puid**/
	private String puid;

	/** 通道编号 **/
	private String channel;

	public VideoSystem() {

	}

	public VideoSystem(String videoNo) {
		this.videoNo = videoNo;
	}

	/** get 编号(videoNo) */
	public String getVideoNo() {
		return videoNo;
	}

	/** set 编号(videoNo) */
	public void setVideoNo(String videoNo) {
		this.videoNo = videoNo;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), getOrgInternalCode(), null);
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPuid() {
		return puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
