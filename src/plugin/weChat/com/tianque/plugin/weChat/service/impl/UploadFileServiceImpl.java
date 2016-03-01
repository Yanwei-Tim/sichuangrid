/**
 * 
 */
package com.tianque.plugin.weChat.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.plugin.weChat.domain.WeiXinMedia;
import com.tianque.plugin.weChat.domain.sendMessage.News;
import com.tianque.plugin.weChat.proxy.service.BaseHttpClientService;
import com.tianque.plugin.weChat.proxy.util.HttpProxyUtil;
import com.tianque.plugin.weChat.service.UploadFileService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.MessageUtil;

/**
 * 
 * @date 2014年4月18日
 */
@Service("uploadFileService")
@Transactional
public class UploadFileServiceImpl extends AbstractBaseService implements
		UploadFileService {

	private static Logger logger = Logger
			.getLogger(UploadFileServiceImpl.class);

	@Autowired
	private BaseHttpClientService baseHttpClientService;

	/**
	 * 上传媒体文件
	 * 
	 * @param accessToken接口访问凭证
	 * @param type
	 * @param mediaFileUrl
	 *            媒体url
	 * @return
	 */
	public WeiXinMedia uploadMedia(String accessToken, String type,
			String mediaFileUrl) {
		WeiXinMedia weixinMedia = new WeiXinMedia();
		JSONObject jsonObject = null;
		try {

			/** 拼装发送消息的url */
			String url = Constants.UPLOAD_MEDIA_URL.replace("ACCESS_TOKEN",
					accessToken).replace("TYPE", type)
					+ "&requestType=uploadFile&content="
					+ URLEncoder.encode(mediaFileUrl, "utf-8");
			String jsonText = baseHttpClientService.postMethod(url);
			jsonObject = JSONObject.fromObject(URLDecoder.decode(jsonText,
					"UTF-8"));
			if (jsonObject != null && jsonObject.toString() != "null") {
				weixinMedia.setType(jsonObject.getString("type"));
				if ("thumb".equals(type))
					weixinMedia.setMediaId(jsonObject
							.getString("thumb_media_id"));
				else
					weixinMedia.setMediaId(jsonObject.getString("media_id"));
				weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
				logger.error("上传文件至微信服务器已完成！");
			} else {
				weixinMedia = null;
				logger.error("文件上传时， 返回json格式为空！");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			weixinMedia = null;
			if (jsonObject != null)
				logger.error("上传文件至微信服务器失败" + jsonObject.getInt("errcode")
						+ ":" + jsonObject.getString("errmsg"));
			else
				logger.error("上传文件至微信服务器失败");

			return null;
		}

		return weixinMedia;
	}

	/**
	 * 上传图文文件（用于群发消息）
	 * 
	 * @param accessToken
	 * @param list
	 * @return
	 */
	public WeiXinMedia uploadNews(String accessToken, List<News> list) {
		String jsonStr = MessageUtil.makeUploadNewsMessage(list);
		/** 拼装发送消息的url */
		String url = Constants.UPLOAD_NEWS_URL.replace("ACCESS_TOKEN", accessToken);
		String jsonText = HttpProxyUtil.postMethod(url, "uploadNews", jsonStr);
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(URLDecoder.decode(jsonText,
					"UTF-8"));
			if (jsonObject != null) {
				WeiXinMedia weixinMedia = new WeiXinMedia();
				weixinMedia.setType(jsonObject.getString("type"));
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
				weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
				return weixinMedia;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("群发时上传图文文件失败", e);
			return null;
		}

	}
}
