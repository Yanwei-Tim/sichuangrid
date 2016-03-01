package com.tianque.mobileErrorLogs.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @author weiminglong 2016年1月7日下午2:46:12
 */
public class MobileErrorLogs extends BaseDomain {
    /**上传错误用户的用户名**/
	private String name;
    /**错误标题**/
	private String errorLogsName;
	/**组织机构Id **/
	private Long orgId;
	/**组织机构Code **/
	private String orgCode;
	/**错误发生时间**/
	private Date occurDate;
	/**错误日志物理路径**/
	private String errorLogsPath;
	/**错误起始时间**/
	private Date occurFromDate;
	/**错误结束时间**/
	private Date occurEndDate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrorLogsName() {
		return errorLogsName;
	}

	public void setErrorLogsName(String errorLogsName) {
		this.errorLogsName = errorLogsName;
	}
    
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getErrorLogsPath() {
		return errorLogsPath;
	}

	public void setErrorLogsPath(String errorLogsPath) {
		this.errorLogsPath = errorLogsPath;
	}

	public Date getOccurFromDate() {
		return occurFromDate;
	}

	public void setOccurFromDate(Date occurFromDate) {
		this.occurFromDate = occurFromDate;
	}

	public Date getOccurEndDate() {
		return occurEndDate;
	}

	public void setOccurEndDate(Date occurEndDate) {
		this.occurEndDate = occurEndDate;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
