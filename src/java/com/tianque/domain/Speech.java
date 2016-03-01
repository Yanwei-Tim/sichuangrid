package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

/**
 * 心得和社会评价
 * 
 * @author xiaofeiyang
 */
public class Speech extends BaseDomain {

	private String title;// 标题
	private String content;// 内容
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 所属网格 */
	private Long orgId;

	/** 类别 00心得，01社会评价 */
	private String speechType;
	/** 创建者真实姓名，有别于createusername */
	private String createUserRealName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSpeechType() {
		return speechType;
	}

	public void setSpeechType(String speechType) {
		this.speechType = speechType;
	}

	public String getCreateUserRealName() {
		return createUserRealName;
	}

	public void setCreateUserRealName(String createUserRealName) {
		this.createUserRealName = createUserRealName;
	}

}
