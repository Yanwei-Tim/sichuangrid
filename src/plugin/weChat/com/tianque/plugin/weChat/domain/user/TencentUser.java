package com.tianque.plugin.weChat.domain.user;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class TencentUser extends BaseDomain {

	private Long tencentUserId;
	private String appId;
	private String appSecret;
	private Long state;
	private String name;
	private Long createDept;
	private Long updateDept;
	private Organization organization;
	private String weChatUserId;
	
	private String sourceId;
	/** 微信素材类型*/
	private PropertyDict sourceTypeDict;
	private String sourceDescription;
	
	private Long isAppendKeyWord ;

	

	
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public PropertyDict getSourceTypeDict() {
		return sourceTypeDict;
	}

	public void setSourceTypeDict(PropertyDict sourceTypeDict) {
		this.sourceTypeDict = sourceTypeDict;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public Long getIsAppendKeyWord() {
		return isAppendKeyWord;
	}

	public void setIsAppendKeyWord(Long isAppendKeyWord) {
		this.isAppendKeyWord = isAppendKeyWord;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Long getTencentUserId() {
		return tencentUserId;
	}

	public void setTencentUserId(Long tencentUserId) {
		this.tencentUserId = tencentUserId;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreateDept() {
		return createDept;
	}

	public void setCreateDept(Long createDept) {
		this.createDept = createDept;
	}

	public Long getUpdateDept() {
		return updateDept;
	}

	public void setUpdateDept(Long updateDept) {
		this.updateDept = updateDept;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
