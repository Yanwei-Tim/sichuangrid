package com.tianque.plugin.weChat.service.impl;

import java.net.URLDecoder;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.plugin.weChat.domain.menu.Menu;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.proxy.service.BaseHttpClientService;
import com.tianque.plugin.weChat.proxy.util.HttpProxyUtil;
import com.tianque.plugin.weChat.service.MenuServiceByWeixin;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.util.Constants;

/**
 * 菜单服务
 * @author 
 *  @date  2014年4月18日
 */
@Service("menuServiceByWeixin")
@Transactional
public class MenuServiceByWeiXinImpl extends AbstractBaseService implements MenuServiceByWeixin {
	private static Logger logger = Logger.getLogger(MenuServiceByWeiXinImpl.class);
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private BaseHttpClientService baseHttpClientService;

	/**
	 * 创建菜单
	 * @param tencentUser
	 * @param menu
	 * @return
	 */
	@Override
	public String createMenu(TencentUser tencentUser, Menu menu) {
		String respMessage = null;
		// 将菜单对象转换成json字符串  
		String jsonMenu = JSONObject.fromObject(menu).toString();
		/**拼装发送消息的url*/
		String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN",
				weChatService.getAccessToken(tencentUser));
		try {
			String jsonText = HttpProxyUtil.postMethod(url, "createMenu", jsonMenu);
			JSONObject jsonObject = JSONObject.fromObject(URLDecoder.decode(jsonText, "UTF-8"));
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					respMessage = "创建菜单失败 " + jsonObject.getInt("errcode") + ":"
							+ jsonObject.getString("errmsg");
					logger.error("创建菜单失败 errcode:{}" + jsonObject.getInt("errcode") + " errmsg:{}"
							+ jsonObject.getString("errmsg"));
				} else {
					respMessage = "服务号：" + tencentUser.getWeChatUserId() + "菜单创建成功";
					logger.error(respMessage);
				}

			} else {
				respMessage = "创建菜单失败";
			}
		} catch (Exception e) {
			respMessage = "请求处理异常，请稍候尝试！";
			logger.error(e.getMessage());
			return respMessage;
		}
		return respMessage;
	}

	/**
	 * 删除菜单
	 * @param tencentUser
	 * @return
	 */

	@Override
	public String deleteMenu(TencentUser tencentUser) {
		String respMessage = null;
		/**拼装发送消息的url*/
		String url = Constants.MENU_DELETE_URL.replace("ACCESS_TOKEN",
				weChatService.getAccessToken(tencentUser))
				+ "&requestType=deleteMenu&content=deleteMenu";
		try {
			String jsonText = baseHttpClientService.postMethod(url);
			JSONObject jsonObject = JSONObject.fromObject(URLDecoder.decode(jsonText, "UTF-8"));
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					respMessage = "菜单删除失败 " + jsonObject.getInt("errcode") + ":"
							+ jsonObject.getString("errmsg");
					logger.error("菜单删除失败 errcode:{}" + jsonObject.getInt("errcode") + " errmsg:{}"
							+ jsonObject.getString("errmsg"));
				} else {
					respMessage = "菜单删除成功";
				}
			} else {
				respMessage = "菜单删除失败";
			}
		} catch (Exception e) {
			respMessage = "请求处理异常，请稍候尝试！";
			logger.error(e.getMessage());
			return respMessage;
		}
		return respMessage;
	}

	/**
	 * 查询菜单
	 * @param tencentUser
	 * @return
	 */

	@Override
	public String selectMenu(TencentUser tencentUser) {
		// TODO 查询菜单
		return null;
	}

}
