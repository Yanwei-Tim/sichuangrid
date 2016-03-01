package com.tianque.plugin.weChat.domain.inbox;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.weChat.util.Constants;

/**收件箱（微信相关参数与系统JAVA命名不统一，此处不继承微信消息类的属性）*/
public class Inbox extends BaseDomain {
	private Long inboxId;
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
	/**文本内容*/
	private String content;
	/**信息所属组织机构*/
	private Organization org;
	/**转成事件后对应事件的id*/
	private Long issueId;
	/**处理状态（1已受理，0未受理,2已接入）*/
	private Long dealState;
	/**处理状态（显示名）*/
	private String dealStateName;
	/**微信处信息创建时间*/
	private Date createTime;
	/**搜索用，开始与结束时间*/
	private Date startCreateTime;
	private Date endCreateTime;
	/**回复条数*/
	private Long count;
	/**附件列表*/
	private List<InboxAttachment> inboxAttachments;
	/****有效或是无效 1：有效 2：无效**/
	private String availability;
	/****1：关键字消息 2：非关键字消息**/
	private Long isKeyWordMsg;
	
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
	
	/**未处理消息数*/
	private Long untreatedInboxNumber;
	/**是否阅读(0为未读,1为已读)*/
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

	public Long getIsKeyWordMsg() {
		return isKeyWordMsg;
	}

	public void setIsKeyWordMsg(Long isKeyWordMsg) {
		this.isKeyWordMsg = isKeyWordMsg;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Long getInboxId() {
		return inboxId;
	}

	public void setInboxId(Long inboxId) {
		this.inboxId = inboxId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setInboxAttachments(List<InboxAttachment> inboxAttachments) {
		this.inboxAttachments = inboxAttachments;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		} else if(dealState != null && dealState.intValue() == Constants.ACCESS.intValue()){
			dealStateName = "已接入";
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

	public Long getUntreatedInboxNumber() {
		return untreatedInboxNumber;
	}

	public void setUntreatedInboxNumber(Long untreatedInboxNumber) {
		this.untreatedInboxNumber = untreatedInboxNumber;
	}

	public Long getIsRead() {
		return isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}
}
