package com.tianque.plugin.serviceTeam.serviceRecord.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordAttachment;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyMember;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecordRelyObject;

public class ServiceRecordVo extends ServiceRecord implements Serializable {

	/** 层级等级Id **/
	private int internalId;

	/** 附件名称 **/
	private String attachmentName;
	/** 附件 **/
	private List<ServiceRecordAttachment> serviceRecordAttachments;

	/** 组织分类 **/
	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;
	/** 显示年份 **/
	private String displayYear;

	/** 服务起始时间 **/
	private Date occurDateStart;
	/** 服务结束时间 **/
	private Date occurDateEnd;

	/** 服务团队名称 */
	private String teamName;

	/** 对象Id **/
	private Long objectId;

	/** 服务对象依赖关系队列 */
	private List<ServiceRecordRelyObject> objects;
	/** 记录所属人依赖关系队列 */
	private List<ServiceRecordRelyMember> members;

	/** 服务记录起始新增时间 */
	private Date recordAddDateStart;
	/** 服务记录结束新增时间 */
	private Date recordAddDateEnd;

	public Date getRecordAddDateStart() {
		return recordAddDateStart;
	}

	public void setRecordAddDateStart(Date recordAddDateStart) {
		this.recordAddDateStart = recordAddDateStart;
	}

	public Date getRecordAddDateEnd() {
		return recordAddDateEnd;
	}

	public void setRecordAddDateEnd(Date recordAddDateEnd) {
		this.recordAddDateEnd = recordAddDateEnd;
	}

	/* 服务对象类型 */
	private List<String> objectTypeList;

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public List<ServiceRecordAttachment> getServiceRecordAttachments() {
		return serviceRecordAttachments;
	}

	public void setServiceRecordAttachments(
			List<ServiceRecordAttachment> serviceRecordAttachments) {
		this.serviceRecordAttachments = serviceRecordAttachments;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getDisplayYear() {
		return displayYear;
	}

	public void setDisplayYear(String displayYear) {
		this.displayYear = displayYear;
	}

	public Date getOccurDateStart() {
		return occurDateStart;
	}

	public void setOccurDateStart(Date occurDateStart) {
		this.occurDateStart = occurDateStart;
	}

	public Date getOccurDateEnd() {
		return occurDateEnd;
	}

	public void setOccurDateEnd(Date occurDateEnd) {
		this.occurDateEnd = occurDateEnd;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public List<ServiceRecordRelyObject> getObjects() {
		return objects;
	}

	public void setObjects(List<ServiceRecordRelyObject> objects) {
		this.objects = objects;
	}

	public List<ServiceRecordRelyMember> getMembers() {
		return members;
	}

	public void setMembers(List<ServiceRecordRelyMember> members) {
		this.members = members;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), super
				.getOrganization().getOrgInternalCode(), null);
	}

	public List<String> getObjectTypeList() {
		return objectTypeList;
	}

	public void setObjectTypeList(List<String> objectTypeList) {
		this.objectTypeList = objectTypeList;
	}

}
