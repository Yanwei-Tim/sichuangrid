package com.tianque.plugin.weChat.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esotericsoftware.minlog.Log;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.weChat.domain.WeChatResponse;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatResponseService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 三级响应：微信响应管理
 * @ClassName: WeChatResponseManagement 
 * @author: hesimin
 * @date: 2015年10月28日 下午5:36:25
 */
@Namespace("/weChatResponseManage")
@Scope("prototype")
@Controller
public class WeChatResponseController extends BaseAction {
	@Autowired
	private WeChatResponseService weChatResponseService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	private WeChatResponse weChatResponse;
	private List<TencentUser> listTencentUser;
	private List<PropertyDict> propertyDictList;
	private int sendCount;
	private Boolean isSendByWechatNo;// 是否通过微信号列表发送
	private String sendToWechatNo;//接收微信号列表

	@Action(value = "findWeChatResponse", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findWeChatResponse() {
		PageInfo<WeChatResponse> pageInfo = weChatResponseService.findWeChatResponse(
				weChatResponse, page, rows,
				sidx, sord);
		gridPage = new GridPage(pageInfo);
		return "success";
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/wechat/weChatResponse/weChatMassSendDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if ("weChatMassSendDlg".equals(mode)) {
				if (isSendByWechatNo != null && isSendByWechatNo) {
					if (StringUtils.isBlank(sendToWechatNo)) {
						errorMessage = "没有微信号";
						return ERROR;
					}
				}
				// 微信号，登录用户所在层级
				listTencentUser = tencentUserService.getTencentUserListByOrgId(ThreadVariable
						.getOrganization().getId());
				//				// 红袖套队伍类别
				//				propertyDictList = propertyDictDubboService
				//						.findPropertyDictByDomainName(PropertyTypes.RED_CUFF_TEAM_TYPE);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	@Action(value = "sendCount", results = {
			@Result(name = "success", type = "json", params = { "root", "sendCount" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String sendCount() {
		if (weChatResponse == null || StringUtils.isBlank(weChatResponse.getWechatUserName())) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		try {
			sendCount = weChatResponseService.countByWechatName(weChatResponse.getWechatUserName(),
					getTimesMonthmorning());
			return SUCCESS;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	// 获得本月第一天0点时间
	private static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	/**
	 * 群发微信
	 * @Title: weChatMassSend 
	 * @return
	 * @return: String
	 */
	@Action(value = "weChatMassSend", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String weChatMassSend() {
		try {
			if (isSendByWechatNo != null && isSendByWechatNo) {
				// 通过微信号列表群发，组织设为登录者组织
				weChatResponse.setOrg(ThreadVariable.getOrganization());
			}
			weChatResponseService.saveAndSendWeChatResponse(weChatResponse, StringUtils
					.isNotBlank(sendToWechatNo) ? sendToWechatNo.replaceAll(" ", "").split(",")
					: null);
		} catch (Exception e) {
			Log.error("群发微信controller:", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}
	public WeChatResponse getWeChatResponse() {
		return weChatResponse;
	}

	public void setWeChatResponse(WeChatResponse weChatResponse) {
		this.weChatResponse = weChatResponse;
	}

	public List<TencentUser> getListTencentUser() {
		return listTencentUser;
	}

	public void setListTencentUser(List<TencentUser> listTencentUser) {
		this.listTencentUser = listTencentUser;
	}

	public List<PropertyDict> getPropertyDictList() {
		return propertyDictList;
	}

	public void setPropertyDictList(List<PropertyDict> propertyDictList) {
		this.propertyDictList = propertyDictList;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public Boolean getIsSendByWechatNo() {
		return isSendByWechatNo;
	}

	public void setIsSendByWechatNo(Boolean isSendByWechatNo) {
		this.isSendByWechatNo = isSendByWechatNo;
	}

	public String getSendToWechatNo() {
		return sendToWechatNo;
	}

	public void setSendToWechatNo(String sendToWechatNo) {
		this.sendToWechatNo = sendToWechatNo;
	}


}
