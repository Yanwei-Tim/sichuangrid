package com.tianque.sms.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 流量管理:实体类(SMSTRAFFICMANAGE)
 * 
 * @author
 * @date 2013-07-02 15:29:09
 */
public class Smstrafficmanage extends com.tianque.core.base.BaseDomain
		implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Long orgId;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	private Organization organization;
	private String orgName;
	/** 电信流量 */
	private Long telecomTraffic;
	/** 移动流量 */
	private Long mobileTraffic;
	/** 中国联通流量 */
	private Long chinaUnicomTraffic;
	/** 小灵通流量 */
	private Long smallUnicom;
	/** 已发送电信流量 */
	private Long hasTelecomTraffic;
	/** 已发送移动流量 */
	private Long hasMobileTraffic;
	/** 已发送联通流量 */
	private Long hasChinaUnicomTraffic;
	/** 部门名称 */
	private String deptName;

	public Smstrafficmanage() {

	}

	/** get 所属网格(orgId) */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格(ORGID) */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getTelecomTraffic() {
		return telecomTraffic;
	}

	public void setTelecomTraffic(Long telecomTraffic) {
		this.telecomTraffic = telecomTraffic;
	}

	public Long getMobileTraffic() {
		return mobileTraffic;
	}

	public void setMobileTraffic(Long mobileTraffic) {
		this.mobileTraffic = mobileTraffic;
	}

	public Long getChinaUnicomTraffic() {
		return chinaUnicomTraffic;
	}

	public void setChinaUnicomTraffic(Long chinaUnicomTraffic) {
		this.chinaUnicomTraffic = chinaUnicomTraffic;
	}

	public Long getSmallUnicom() {
		return smallUnicom;
	}

	public void setSmallUnicom(Long smallUnicom) {
		this.smallUnicom = smallUnicom;
	}

	public Long getHasTelecomTraffic() {
		return hasTelecomTraffic;
	}

	public void setHasTelecomTraffic(Long hasTelecomTraffic) {
		this.hasTelecomTraffic = hasTelecomTraffic;
	}

	public Long getHasMobileTraffic() {
		return hasMobileTraffic;
	}

	public void setHasMobileTraffic(Long hasMobileTraffic) {
		this.hasMobileTraffic = hasMobileTraffic;
	}

	public Long getHasChinaUnicomTraffic() {
		return hasChinaUnicomTraffic;
	}

	public void setHasChinaUnicomTraffic(Long hasChinaUnicomTraffic) {
		this.hasChinaUnicomTraffic = hasChinaUnicomTraffic;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}
}
