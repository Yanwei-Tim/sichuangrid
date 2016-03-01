package com.tianque.domain.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class SearchFamiliesMovedRecordVo {
	/**
	 * 所属网格编号
	 */
	private String orgInternalCode;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String idCardNo;
	/**
	 * 迁出地网格
	 */
	private Long orgIdFrom;
	/**
	 * 迁入地网格
	 */
	private Long orgIdTo;
	/**
	 * 申请迁入时间开始日期
	 */
	private Date dateToBegin;
	/**
	 * 申请迁入时间结束日期
	 */
	private Date dateToEnd;
	/**
	 * 批准迁出时间开始日期
	 */
	private Date dateFromBegin;
	/**
	 * 批准迁出时间结束日期
	 */
	private Date dateFromEnd;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Long getOrgIdFrom() {
		return orgIdFrom;
	}

	public void setOrgIdFrom(Long orgIdFrom) {
		this.orgIdFrom = orgIdFrom;
	}

	public Long getOrgIdTo() {
		return orgIdTo;
	}

	public void setOrgIdTo(Long orgIdTo) {
		this.orgIdTo = orgIdTo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDateToBegin() {
		return dateToBegin;
	}

	public void setDateToBegin(Date dateToBegin) {
		this.dateToBegin = dateToBegin;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDateToEnd() {
		return dateToEnd;
	}

	public void setDateToEnd(Date dateToEnd) {
		this.dateToEnd = dateToEnd;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDateFromBegin() {
		return dateFromBegin;
	}

	public void setDateFromBegin(Date dateFromBegin) {
		this.dateFromBegin = dateFromBegin;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDateFromEnd() {
		return dateFromEnd;
	}

	public void setDateFromEnd(Date dateFromEnd) {
		this.dateFromEnd = dateFromEnd;
	}

}
