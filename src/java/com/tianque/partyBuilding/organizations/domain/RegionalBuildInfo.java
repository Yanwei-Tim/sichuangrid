package com.tianque.partyBuilding.organizations.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 区域联建情况
 * */
public class RegionalBuildInfo extends BaseDomain {
	/** 组织机构 */
	private Organization organization;
	/** 服务项目名称 */
	private String serviceItem;
	/** 推进情况 */
	private String advancementInfo;
	/** 是否有附件 */
	private Boolean isAttachment;
	/** 是否被认领 */
	private Boolean isClaim;

	/** 附件 */
	private List<RegionalBuildInfoAttachFile> regionalBuildInfoAttachFiles = new ArrayList<RegionalBuildInfoAttachFile>();

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}

	public String getAdvancementInfo() {
		return advancementInfo;
	}

	public void setAdvancementInfo(String advancementInfo) {
		this.advancementInfo = advancementInfo;
	}

	public Boolean getIsAttachment() {
		return isAttachment;
	}

	public void setIsAttachment(Boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

	public List<RegionalBuildInfoAttachFile> getRegionalBuildInfoAttachFiles() {
		return regionalBuildInfoAttachFiles;
	}

	public void setRegionalBuildInfoAttachFiles(
			List<RegionalBuildInfoAttachFile> regionalBuildInfoAttachFiles) {
		this.regionalBuildInfoAttachFiles = regionalBuildInfoAttachFiles;
	}

	public Boolean getIsClaim() {
		return isClaim;
	}

	public void setIsClaim(Boolean isClaim) {
		this.isClaim = isClaim;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.organization.getOrgInternalCode(), null);
	}
}
