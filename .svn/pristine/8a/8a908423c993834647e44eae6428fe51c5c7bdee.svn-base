package com.tianque.baseInfo.base.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description:删除重复数据记录日志类【包括读、删除、修改、和有错误的】
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-6 上午08:45:52
 */
public class PoepleDuplicateRemovalLog extends BaseDomain {
	/** 数据所在组织机构 */
	private Organization dataOrg;
	/** 数据所在组织机构编码 */
	private String dataInternalcode;
	/** 数据Id */
	private Long dataId;
	/** 身份证号码 */
	private String idCardNo;
	/** 姓名 */
	private String name;
	/** 数据类别（数据所在的表） */
	private String dataType;
	/** 数据所对应的baseInfoId */
	private Long baseInfoId;
	/** 数据所对应的实口id */
	private Long actualId;
	/** 数据说对应的类别 */
	private String actualType;
	/** 数据所对应的业务类别 */
	private String populationType;
	private String operateType;
	/** 操作时间 */
	private Date operateDate;
	/** 针对baseInfo删除的baseInfo的json */
	private String baseInfo;

	public Organization getDataOrg() {
		return dataOrg;
	}

	public void setDataOrg(Organization dataOrg) {
		this.dataOrg = dataOrg;
	}

	public String getDataInternalcode() {
		return dataInternalcode;
	}

	public void setDataInternalcode(String dataInternalcode) {
		this.dataInternalcode = dataInternalcode;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(Long baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public Long getActualId() {
		return actualId;
	}

	public void setActualId(Long actualId) {
		this.actualId = actualId;
	}

	public String getActualType() {
		return actualType;
	}

	public void setActualType(String actualType) {
		this.actualType = actualType;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}

}
