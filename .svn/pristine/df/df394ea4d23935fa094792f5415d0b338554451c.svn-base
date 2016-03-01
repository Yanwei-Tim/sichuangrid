package com.tianque.plugin.weChat.domain.user;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class WeChatMenu extends BaseDomain {

	private String menuKey;
	private String menuType;
	private String menuName;
	private String weChatUserId;
	private String sourceID;
	/** 微信素材类型*/
	private PropertyDict sourceTypeDict;
	private String sourceDescription;
	private long isLeaf;
	private long parentId;
	private Organization org;
	private String url;
	private Long rank;
	
	public WeChatMenu(){
		
	}
	public WeChatMenu(String weChatUserId,String name,String type,long parentId,long isLeaf, Long rank){
		this.weChatUserId=weChatUserId;
		this.menuName=name;
		this.menuType=type;
		this.isLeaf=isLeaf;
		this.rank=rank;
		this.parentId=parentId;
	}

	public Long getRank() {
		return rank;
	}



	public void setRank(Long rank) {
		this.rank = rank;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Organization getOrg() {
		return org;
	}

	

	public String getSourceID() {
		return sourceID;
	}



	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}



	public void setOrg(Organization org) {
		this.org = org;
	}

	public long getParentId() {
		return parentId;
	}

	public long getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(long isLeaf) {
		this.isLeaf = isLeaf;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
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

}
