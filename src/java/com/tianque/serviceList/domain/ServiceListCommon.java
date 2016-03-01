/**
 * 
 */
package com.tianque.serviceList.domain;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public class ServiceListCommon extends BaseDomain{
	/**
	 * 数据录入时间
	 */
	 private Date inputTime;
	 /**
	  * 地点
	  */
	 private String address;
	 /**
	  * 类别
	  */
	 private PropertyDict category;
	 /**
	  * 详细情况/情况描述
	  */
	 private String details;
	 /**
	  * 备注
	  */
	 private String remake;
	 /**
	  * 照片
	  */
	 private ArrayList<ServiceListAttch> photos;
	 /**
	  * 是否签收（是否签收 0：未签收 1：已签收）
	  */
	 private Integer isSign;
	 /**
	  * 是否回复（是否回复0：未回复1：已回复）
	  */
	 private Integer isReply;
	 /**
	  * 签收日期
	  */
	 private Date signDate;
	 /**
	  * 签收人
	  */
	 private String signPeople;
	 /**
	  * 签收意见
	  */
	 private String signContent;
	 /**
	  * 回复
	  */
	 private ArrayList<Reply> replies;
	 
	 private Organization organization;
	 
	 /***
	  * 网格员电话
	  */
	 private String telephone;
	 
	 /**
	  * 附件
	  */
	 private ArrayList<ServiceListAttch> attachs;
	 
	 private String[] attachFileNames;
	 
	 private Reply reply;
	 
	 private ServiceListAttch attch;
	 /**
	  * 删除的附件id
	  */
	 private String deleteAttachIds;
	 /**
	  * 督办超期
	  */
	 private Integer supervise;
	 
	 //导入时回复用
	 /**
	  * 回复时间
	  */
    private Date replyDate;

    /**
	  * 回复人
	  */
    private String replyPeople;

    /**
	  * 处理情况
	  */
    private String replyContent;
	 /**
	  *       
	  * 判断是否走网格配置清单查询
	  */
	private String mode;
	
	/**
	 * 登录的职能部门的网格id
	 */
	private Long funOrgId;
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public PropertyDict getCategory() {
		return category;
	}
	public void setCategory(PropertyDict category) {
		this.category = category;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	public ArrayList<ServiceListAttch> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<ServiceListAttch> photos) {
		this.photos = photos;
	}
	public Integer getIsSign() {
		return isSign;
	}
	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public String getSignPeople() {
		return signPeople;
	}
	public void setSignPeople(String signPeople) {
		this.signPeople = signPeople;
	}
	public String getSignContent() {
		return signContent;
	}
	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	public void setReplies(ArrayList<Reply> replies) {
		this.replies = replies;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public ArrayList<ServiceListAttch> getAttachs() {
		return attachs;
	}
	public void setAttachs(ArrayList<ServiceListAttch> attachs) {
		this.attachs = attachs;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public ServiceListAttch getAttch() {
		return attch;
	}
	public void setAttch(ServiceListAttch attch) {
		this.attch = attch;
	}
	public String getDeleteAttachIds() {
		return deleteAttachIds;
	}
	public void setDeleteAttachIds(String deleteAttachIds) {
		this.deleteAttachIds = deleteAttachIds;
	}
	public Integer getSupervise() {
		return supervise;
	}
	public void setSupervise(Integer supervise) {
		this.supervise = supervise;
	}
	public Integer getIsReply() {
		return isReply;
	}
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyPeople() {
		return replyPeople;
	}
	public void setReplyPeople(String replyPeople) {
		this.replyPeople = replyPeople;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Long getFunOrgId() {
		return funOrgId;
	}
	public void setFunOrgId(Long funOrgId) {
		this.funOrgId = funOrgId;
	}
	
}
