package com.tianque.mobile.mobileDictionary.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class MobileDictionary extends BaseDomain {

	/** 文件路径 */
	private String fileUrl;

	/** 更新日期 */
	private Date renewDate;

	/** 版本号 */
	private Long version;
	/** 类型，1：所有的数据字典项，2：增量的数据字典项 */
	private Integer type;
	
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(Date renewDate) {
		this.renewDate = renewDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
