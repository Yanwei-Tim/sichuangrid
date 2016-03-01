package com.tianque.plugin.weChat.controller;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.plugin.weChat.domain.WeChatJsSDKConfig;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.proxy.service.BaseHttpClientService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.JsSDKSign;
import com.tianque.util.HttpClientUtils;

/** 微信js_sdk */
@Namespace("/WeChatJsSDKConfig")
@Scope("prototype")
@Controller("WeChatJsSDKConfigController")
public class WeChatJsSDKConfigController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(WeChatJsSDKConfigController.class);
	private static final Logger log = LoggerFactory
			.getLogger(WeChatJsSDKConfigController.class);
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private BaseHttpClientService baseHttpClientService;

	private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
	private String toUserName;
	private String targetUrl;
	private WeChatJsSDKConfig weChatJsSDKConfig;

	public WeChatJsSDKConfig getWeChatJsSDKConfig() {
		return weChatJsSDKConfig;
	}

	public void setWeChatJsSDKConfig(WeChatJsSDKConfig weChatJsSDKConfig) {
		this.weChatJsSDKConfig = weChatJsSDKConfig;
	}

	@Action(value = "findWeChatJsSDKConfig", results = {
			@Result(type = "json", name = "success", params = { "root",
					"weChatJsSDKConfig" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findWeChatJsSDKConfig() {
		String accessToken;
		String ticket;
		if (StringUtils.isBlank(targetUrl)) {
			errorMessage = "jsapiTicket获取失败,当前url为空！！";
			return ERROR;
		}
		int index = targetUrl.indexOf("#");
		if (index > 0) {
			targetUrl = targetUrl.substring(0, index);
			// targetUrl = targetUrl.toLowerCase();
			logger.debug("targetUrl：" + targetUrl);
		}

		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(toUserName);// getToUserName()
															// 开发者微信号
		if (tencentUser == null) {
			errorMessage = "服务号获取失败！";
			return ERROR;
		}

		accessToken = getAccessToken(tencentUser);
		ticket = (String) cacheService.get("weChatTicket"
				+ tencentUser.getWeChatUserId());

		if (ticket == null) {
			ticket = getJsApiTicketByWX(accessToken);
			if (ticket != null) {
				cacheService.set(
						"weChatTicket" + tencentUser.getWeChatUserId(), 7100,
						ticket);
			}
		}

		if (ticket == null || ticket.length() == 0) {
			errorMessage = "jsapiTicket获取失败！！";
			return ERROR;
		}

		Map<String, String> ret = JsSDKSign.sign(ticket, targetUrl);
		weChatJsSDKConfig = new WeChatJsSDKConfig();
		weChatJsSDKConfig.setAppId(tencentUser.getAppId());
		weChatJsSDKConfig.setNonceStr(ret.get("nonceStr"));
		weChatJsSDKConfig.setTimestamp(ret.get("timestamp"));
		weChatJsSDKConfig.setSignature(ret.get("signature"));
		logger.debug(weChatJsSDKConfig.toString());
		return SUCCESS;

	}

	/**
	 * 获取公众号的全局唯一票据
	 */
	public String getAccessToken(TencentUser tencentUser) {
		String accessToken = (String) cacheService.get("weChatAccessToken"
				+ tencentUser.getWeChatUserId());
		if (accessToken != null && "代理错误".equals(accessToken)) {
			accessToken = null;
			cacheService.remove("weChatAccessToken"
					+ tencentUser.getWeChatUserId());
		}
		if (accessToken == null) {
			String appid = tencentUser.getAppId();
			String secret = tencentUser.getAppSecret();
			String url = Constants.ACCESS_TOKEN_URL.replace("APPID", appid)
					.replace("APPSECRET", secret)
					+ "&requestType=getAccessToken";
			accessToken = baseHttpClientService.postMethod(url);
			cacheService.set(
					"weChatAccessToken" + tencentUser.getWeChatUserId(), 7100,
					accessToken);
		}
		return accessToken;

	}

	public String getJsApiTicketByWX(String token) {
		String jsAPITicktUrl = JSAPI_TICKET_URL + "&access_token=" + token;
		String jsAPITick = null;
		try {

			String resultStr = HttpClientUtils.postProxyToOutside(
					jsAPITicktUrl, null);
			Map<String, String> resultMap = null;
			resultMap = JSON.parseObject(resultStr, Map.class);
			jsAPITick = resultMap.get("ticket");// 访问页面的微信用户openid

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return jsAPITick;

	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
}
