package com.tianque.sms.domain;

import org.apache.struts2.json.JSONUtil;

/**
 * 短信模板:实体类(SMSTEMPLATE)
 * 
 * @author
 * @date 2013-07-03 11:27:49
 */
public class Smstemplate extends com.tianque.core.base.BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 模板名称(NAME) */
	private String name;
	/** 短信模板key(KEY) */
	private String key;
	/** 模板内容(CONTENT) */
	private String content;
	/** 模板类型 */
	private String type;

	public Smstemplate() {

	}

	public Smstemplate(String name, String key, String content) {
		this.name = name;
		this.key = key;
		this.content = content;
	}

	/** get 模板名称(name) */
	public String getName() {
		return name;
	}

	/** set 模板名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 短信模板key(key) */
	public String getKey() {
		return key;
	}

	/** set 短信模板key(KEY) */
	public void setKey(String key) {
		this.key = key;
	}

	/** get 模板内容(content) */
	public String getContent() {
		return content;
	}

	/** set 模板内容(CONTENT) */
	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
