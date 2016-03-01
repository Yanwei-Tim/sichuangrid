package com.tianque.plugin.weChat.domain.inbox;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.weChat.util.Constants;

/**
 * 精确消息
 */
public class PreciseInbox extends BaseDomain{
	
	/**开发者微信号*/
	private String toUserName;
	/**公众账号名称*/
	private String weChatUserName;
	/**群组id（微信方）*/
	private Long groupId;
	/**群组名称*/
	private String groupName;
	/**发送方帐号（一个OpenID）*/
	private String fromUserName;
	/**消息类型（text/image/location/link）*/
	private String msgType;
	/**消息id，64位整型*/
	private Long msgId;
	/**信息所属组织机构*/
	private Organization org;
	/**转成事件后对应事件的id*/
	private Long issueId;
	/**处理状态（1已受理，0未受理）*/
	private Long dealState;
	/**处理状态（显示名）*/
	private String dealStateName;
	/**搜索用，开始与结束时间*/
	private Date startCreateTime;
	private Date endCreateTime;
	/**回复*/
	private Long count;
	/**附件列表*/
	private List<InboxAttachment> inboxAttachments;
	/****有效或是无效 1：有效 2：无效**/
	private String availability;
	
	//系统昵称
	private String nickName;
	//事件服务单号
	private String serviceId;
	//纬度
	private String lat;
	//经度
	private String lng;
	
	/**转发状态（未转发:1 已转发:2）*/
	private Long forwardingState;
	/**转发状态（显示名）*/
	private String forwardingStateName;
	/**所属网格编号*/
	private String orgInternalCode;
	/**粉丝Id*/
	private Long fanId;
	/**事件大类*/
	private Long issueTypeDomainId;
	/**事件小类*/
	private Long issueTypeId ;
	/**事件名称*/
	private String issueName;
	/**发生地点*/
	private String occurLocation;
	/**上报人姓名*/
	private String reportPeopleName;
	/**上报人手机号码*/
	private Long reportPeoplePhoneNumber;
	/**上报人固定电话*/
	private String reportPeopleTelephone;
	/**事件规模*/
	private Long issueScale;
	/**涉及人数*/
	private Long relatePeopleCount;
	/**简述*/
	private String profile;
	/**消息类型(0,流动人口消息);(1,治安隐患消息);(2,其他消息);*/
	private Long inboxType;
	/**异常类型(对应任务清单的异常类型)*/
	private PropertyDict exceptionType;
	/**是否阅读(0为否,1为是)*/
	private Long isRead;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public List getInboxAttachments() {
		return inboxAttachments;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getDealState() {
		return dealState;
	}

	public void setDealState(Long dealState) {
		this.dealState = dealState;
	}

	public void setInboxAttachments(List<InboxAttachment> inboxAttachments) {
		this.inboxAttachments = inboxAttachments;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getDealStateName() {
		if (dealState != null && dealState.intValue() == Constants.NOT_ACCEPT.intValue()) {
			dealStateName = "未受理";
		} else if (dealState != null && dealState.intValue() == Constants.ACCEPT.intValue()) {
			dealStateName = "已受理";
		} else if (dealState != null && dealState.intValue() == Constants.FORWARD.intValue()) {
			dealStateName = "已转发";
		} 
		return dealStateName;
	}

	public void setDealStateName(String dealStateName) {
		this.dealStateName = dealStateName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getWeChatUserName() {
		return weChatUserName;
	}

	public void setWeChatUserName(String weChatUserName) {
		this.weChatUserName = weChatUserName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getForwardingState() {
		return forwardingState;
	}

	public void setForwardingState(Long forwardingState) {
		this.forwardingState = forwardingState;
	}

	public String getForwardingStateName() {
		if(forwardingState!=null && forwardingState.intValue() == Constants.NOT_FORWARD.intValue()){
			forwardingStateName = "未转发";
		}else if (forwardingState != null && forwardingState.intValue() == Constants.FORWARD.intValue()) {
			forwardingStateName = "已转发";
		}
		return forwardingStateName;
	}

	public void setForwardingStateName(String forwardingStateName) {
		this.forwardingStateName = forwardingStateName;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getFanId() {
		return fanId;
	}

	public void setFanId(Long fanId) {
		this.fanId = fanId;
	}

	public String getEncryptIdByIssueId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getIssueId(), null, null);
	}

	public Long getIssueTypeDomainId() {
		return issueTypeDomainId;
	}

	public void setIssueTypeDomainId(Long issueTypeDomainId) {
		this.issueTypeDomainId = issueTypeDomainId;
	}

	public Long getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

	public String getReportPeopleName() {
		return reportPeopleName;
	}

	public void setReportPeopleName(String reportPeopleName) {
		this.reportPeopleName = reportPeopleName;
	}

	public Long getReportPeoplePhoneNumber() {
		return reportPeoplePhoneNumber;
	}

	public void setReportPeoplePhoneNumber(Long reportPeoplePhoneNumber) {
		this.reportPeoplePhoneNumber = reportPeoplePhoneNumber;
	}

	public String getReportPeopleTelephone() {
		return reportPeopleTelephone;
	}

	public void setReportPeopleTelephone(String reportPeopleTelephone) {
		this.reportPeopleTelephone = reportPeopleTelephone;
	}

	public Long getIssueScale() {
		return issueScale;
	}

	public void setIssueScale(Long issueScale) {
		this.issueScale = issueScale;
	}

	public Long getRelatePeopleCount() {
		return relatePeopleCount;
	}

	public void setRelatePeopleCount(Long relatePeopleCount) {
		this.relatePeopleCount = relatePeopleCount;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public PropertyDict getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(PropertyDict exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Long getInboxType() {
		return inboxType;
	}

	public void setInboxType(Long inboxType) {
		this.inboxType = inboxType;
	}

	public Long getIsRead() {
		return isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}	
}
