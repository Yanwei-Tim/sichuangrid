package com.tianque.serviceList.domain;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class Reply extends BaseDomain{

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
     * 关联的回复具体模块id
     */
    private Long serviceId;

    /**
     * 服务清单具体模块类型
     */
    private Integer serviceType;
    
    /**
     * 附件
     * @return
     */
    private ArrayList<ReplyAttch>replyAttchs;
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
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

	public ArrayList<ReplyAttch> getReplyAttchs() {
		return replyAttchs;
	}

	public void setReplyAttchs(ArrayList<ReplyAttch> replyAttchs) {
		this.replyAttchs = replyAttchs;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

}