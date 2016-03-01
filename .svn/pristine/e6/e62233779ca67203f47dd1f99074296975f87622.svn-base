package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 新闻
 * 
 * @author xiaofeiyang
 */
public class News extends BaseDomain {

	private String title;// 标题
	private String content;// 内容
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 所属网格 */
	private Long orgId;

	/** 新闻类别 00文字新闻，01图片新闻 */
	private String newsType;
	/** 创建者真实姓名，有别于createusername */
	private String createUserRealName;
	/** 图片的路径 */
	private String imgUrl;

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

	public String getCreateUserRealName() {
		return createUserRealName;
	}

	public void setCreateUserRealName(String createUserRealName) {
		this.createUserRealName = createUserRealName;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getAppendDate() {
		return getCreateDate();

	}

}
