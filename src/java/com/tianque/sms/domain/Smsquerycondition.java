package com.tianque.sms.domain;

import java.util.Date;

import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

/**
 * 查询条件管理:实体类(SMSQUERYCONDITION)
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
public class Smsquerycondition extends com.tianque.core.base.BaseDomain
		implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 描述 */
	private String description;
	/** 发送对象 */
	private SmsSendObject smsSendObject;
	/** sql语句模板 */
	private String sqlTemplate;
	/** 中间key */
	private String key;
	/** 表字段 */
	private String field;
	/** 类型 */
	private String type;
	/** 是否必填 */
	private Boolean isNull;

	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSqlTemplate() {
		return sqlTemplate;
	}

	public void setSqlTemplate(String sqlTemplate) {
		this.sqlTemplate = sqlTemplate;
	}

	public Boolean getIsNull() {
		return isNull;
	}

	public void setIsNull(Boolean isNull) {
		this.isNull = isNull;
	}

	public SmsSendObject getSmsSendObject() {
		return smsSendObject;
	}

	public void setSmsSendObject(SmsSendObject smsSendObject) {
		this.smsSendObject = smsSendObject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
