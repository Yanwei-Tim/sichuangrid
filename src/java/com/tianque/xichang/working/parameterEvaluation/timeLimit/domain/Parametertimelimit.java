package com.tianque.xichang.working.parameterEvaluation.timeLimit.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 三本台账时限标准表:实体类(PARAMETERTIMELIMIT)
 * 
 * @author
 * @date 2014-03-04 10:34:33
 */
public class Parametertimelimit extends com.tianque.core.base.BaseDomain
		implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 部门(DEPARTMENTLEVEL) */
	private com.tianque.domain.PropertyDict departmentlevel;
	/** 应用层级(USELEVEL) */
	private com.tianque.domain.PropertyDict uselevel;
	/** 部门类型(ORGTYPE) */
	private com.tianque.domain.PropertyDict orgtype;
	/** 办理时限(HANDLELIMIT) */
	private Integer handlelimit;
	/** 办结时限(TRANSFERREDLIMIT) */
	private Integer transferredlimit;
	/** 流转时限(CIRCULATIONLIMIT) */
	private Integer circulationlimit;

	public Parametertimelimit() {

	}

	public Parametertimelimit(com.tianque.domain.PropertyDict departmentlevel,
			com.tianque.domain.PropertyDict uselevel,
			com.tianque.domain.PropertyDict orgtype, Integer handlelimit,
			Integer transferredlimit, Integer circulationlimit) {
		this.departmentlevel = departmentlevel;
		this.uselevel = uselevel;
		this.orgtype = orgtype;
		this.handlelimit = handlelimit;
		this.transferredlimit = transferredlimit;
		this.circulationlimit = circulationlimit;
	}

	/** get 部门(departmentlevel) */
	public com.tianque.domain.PropertyDict getDepartmentlevel() {
		return departmentlevel;
	}

	/** set 部门(DEPARTMENTLEVEL) */
	public void setDepartmentlevel(
			com.tianque.domain.PropertyDict departmentlevel) {
		this.departmentlevel = departmentlevel;
	}

	/** get 应用层级(uselevel) */
	public com.tianque.domain.PropertyDict getUselevel() {
		return uselevel;
	}

	/** set 应用层级(USELEVEL) */
	public void setUselevel(com.tianque.domain.PropertyDict uselevel) {
		this.uselevel = uselevel;
	}

	/** get 部门类型(orgtype) */
	public com.tianque.domain.PropertyDict getOrgtype() {
		return orgtype;
	}

	/** set 部门类型(ORGTYPE) */
	public void setOrgtype(com.tianque.domain.PropertyDict orgtype) {
		this.orgtype = orgtype;
	}

	/** get 办理时限(handlelimit) */
	public Integer getHandlelimit() {
		return handlelimit;
	}

	/** set 办理时限(HANDLELIMIT) */
	public void setHandlelimit(Integer handlelimit) {
		this.handlelimit = handlelimit;
	}

	/** get 办结时限(transferredlimit) */
	public Integer getTransferredlimit() {
		return transferredlimit;
	}

	/** set 办结时限(TRANSFERREDLIMIT) */
	public void setTransferredlimit(Integer transferredlimit) {
		this.transferredlimit = transferredlimit;
	}

	/** get 流转时限(circulationlimit) */
	public Integer getCirculationlimit() {
		return circulationlimit;
	}

	/** set 流转时限(CIRCULATIONLIMIT) */
	public void setCirculationlimit(Integer circulationlimit) {
		this.circulationlimit = circulationlimit;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}
}
