package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

@SuppressWarnings("serial")
public class PartyConstructionFiles extends BaseDomain {

	/** 标题 */
	private String title;
	/** 描述信息 */
	private String describe;
	/** 附件地址 */
	private String url;
	/** 附件名称 */
	private String fileName;

	/** 附件大小 */
	private Long fileSize;
	private String dalei;
	private String xiaolei;

	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;

	private String orgInternalCode;
	private Organization organization;

	private PropertyDict categories;
	private PropertyDict smallClass;

	private String createUserRealName;

	private String author;

	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PropertyDict getCategories() {
		return categories;
	}

	public void setCategories(PropertyDict categories) {
		this.categories = categories;
	}

	public PropertyDict getSmallClass() {
		return smallClass;
	}

	public void setSmallClass(PropertyDict smallClass) {
		this.smallClass = smallClass;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDalei() {
		return dalei;
	}

	public void setDalei(String dalei) {
		this.dalei = dalei;
	}

	public String getXiaolei() {
		return xiaolei;
	}

	public void setXiaolei(String xiaolei) {
		this.xiaolei = xiaolei;
	}

	public String getCreateUserRealName() {
		return createUserRealName;
	}

	public void setCreateUserRealName(String createUserRealName) {
		this.createUserRealName = createUserRealName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
