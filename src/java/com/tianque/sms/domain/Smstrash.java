package com.tianque.sms.domain;

import java.io.Serializable;

import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 垃圾短信箱:实体类(SMSTRASH)
 * 
 * @author
 * @date 2013-09-22 16:42:50
 */
public class Smstrash implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	/** 发送或接收时间(TIME) */
	private java.util.Date time;
	/** 号码(MOBILE) */
	private String mobile;
	/** 短信来源类型：1发件箱，2收件箱(FROMTYPE) */
	private Long fromType;
	/** 短信内容(CONTENT) */
	private String content;

	public Smstrash() {

	}

	public Smstrash(java.util.Date time, String mobile, Long fromType,
			String content) {
		this.time = time;
		this.mobile = mobile;
		this.fromType = fromType;
		this.content = content;
	}

	/** get 发送或接收时间(time) */
	@JSON(format = "yyyy-MM-dd")
	public java.util.Date getTime() {
		return time;
	}

	/** set 发送或接收时间(TIME) */
	public void setTime(java.util.Date time) {
		this.time = time;
	}

	/** get 号码(mobile) */
	public String getMobile() {
		return mobile;
	}

	/** set 号码(MOBILE) */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** get 短信来源类型：1发件箱，2收件箱(fromType) */
	public Long getFromType() {
		return fromType;
	}

	/** set 短信来源类型：1发件箱，2收件箱(FROMTYPE) */
	public void setFromType(Long fromType) {
		this.fromType = fromType;
	}

	/** get 短信内容(content) */
	public String getContent() {
		return content;
	}

	/** set 短信内容(CONTENT) */
	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(
				this.id != null ? Long.parseLong(this.id) : null, null, null);
	}
}
