package com.tianque.sms.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.sms.domain.SmsSendObject;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.domain.vo.SearchSmsdownlinkVo;
import com.tianque.sms.service.SmsSendObjectService;
import com.tianque.sms.service.SmsdownlinkService;

/**
 * 短信收件箱:服务前端控制类
 * 
 * @author
 * @date 2013-07-08 17:27:15
 */
@Namespace("/smsdownlinkManage")
@Scope("request")
@Controller("smsdownlinkController")
public class SmsdownlinkController extends BaseAction {

	@Autowired
	private SmsdownlinkService smsdownlinkService;
	@Autowired
	private SmsSendObjectService smsSendObjectService;

	private Smsdownlink smsdownlink;
	private SearchSmsdownlinkVo searchSmsdownlinkVo;
	private String ids;
	private Long organizationId;
	private String sender;
	private List<Smsdownlink> smsdownlinks;
	private List<SmsSendObject> smsSendObjectList;
	private Long isRead;

	/**
	 * 短信收件箱操作分发
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/interaction/newSMS/smsDownlink/searchSmsUplinkDlg.jsp"),
			@Result(name = "view", location = "/interaction/newSMS/smsDownlink/smsdownlinkViewDlg.jsp") }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			smsSendObjectList = smsSendObjectService.findSimpleSmsSendObjects();
			smsdownlinkService.updateSmsDownLinkBySender(1L, sender);// 修改为已读
			return "view";
		}
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 */
	@Action(value = "updateDeleteStatusBySender", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteSmsDownlink")
	public String updateDeleteStatusBySender() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		String[] senders = new String[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				senders[i] = idsStr[i];
			}
		}
		if (senders.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smsdownlinkService.updateDeleteStatusBySender(senders);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 */
	@Action(value = "findSmsdownlinkPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "smsDownlinkManagement")
	public String findSmsdownlinkPagerBySearchVo() throws Exception {
		if (searchSmsdownlinkVo == null) {
			searchSmsdownlinkVo = new SearchSmsdownlinkVo();
		}
		gridPage = new GridPage(smsdownlinkService.findPagerBySearchVo(
				searchSmsdownlinkVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "findSmsContentBySender", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsdownlinks", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSmsContentBySender() throws Exception {
		smsdownlinks = smsdownlinkService.findSmsContentBySender(sender);

		return SUCCESS;
	}

	@Action(value = "findSmsdownlinkForPager", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "smsDownlinkManagement")
	public String findSmsdownlinkForPager() throws Exception {
		gridPage = new GridPage(smsdownlinkService.findSmsdownlinkForPager(
				isRead, sender, page, rows, sidx, sord));
		return SUCCESS;
	}

	public Smsdownlink getSmsdownlink() {
		return smsdownlink;
	}

	public void setSmsdownlink(Smsdownlink smsdownlink) {
		this.smsdownlink = smsdownlink;
	}

	public SearchSmsdownlinkVo getSearchSmsdownlinkVo() {
		return searchSmsdownlinkVo;
	}

	public void setSearchSmsdownlinkVo(SearchSmsdownlinkVo searchsmsdownlinkVo) {
		this.searchSmsdownlinkVo = searchsmsdownlinkVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<Smsdownlink> getSmsdownlinks() {
		return smsdownlinks;
	}

	public void setSmsdownlinks(List<Smsdownlink> smsdownlinks) {
		this.smsdownlinks = smsdownlinks;
	}

	public List<SmsSendObject> getSmsSendObjectList() {
		return smsSendObjectList;
	}

	public void setSmsSendObjectList(List<SmsSendObject> smsSendObjectList) {
		this.smsSendObjectList = smsSendObjectList;
	}

	public Long getIsRead() {
		return isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}

}
