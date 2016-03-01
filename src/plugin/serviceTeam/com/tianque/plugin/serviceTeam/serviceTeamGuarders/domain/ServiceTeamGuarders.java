package com.tianque.plugin.serviceTeam.serviceTeamGuarders.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.serviceTeam.serviceGuardersWithObject.domain.ServiceGuardersWithObject;

/**
 * @ClassName: ServiceTeamGuarders
 * @Description: TODO()
 * @author tengjia
 * @date 2013-6-28 上午9:55:04
 */
public class ServiceTeamGuarders extends BaseDomain {
	Long id;
	/** 监护人姓名 **/
	private String guarderName;
	/** 性别 **/
	private PropertyDict gender;
	/** 身份（与被服务人员关系） **/
	private String relation;
	/** 身份证号 **/
	private String idCardNo;
	/** 固定电话 **/
	private String phone;
	/** 手机号码 **/
	private String mobile;
	/** 备注 **/
	private String remark;

	private ServiceGuardersWithObject serviceGuardersWithObject;

	public String getGuarderName() {
		return guarderName;
	}

	public void setGuarderName(String guarderName) {
		this.guarderName = guarderName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public ServiceGuardersWithObject getServiceGuardersWithObject() {
		return serviceGuardersWithObject;
	}

	public void setServiceGuardersWithObject(ServiceGuardersWithObject serviceGuardersWithObject) {
		this.serviceGuardersWithObject = serviceGuardersWithObject;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
