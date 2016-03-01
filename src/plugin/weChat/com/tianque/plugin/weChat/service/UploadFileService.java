/**
 * 
 */
package com.tianque.plugin.weChat.service;

import java.util.List;

import com.tianque.plugin.weChat.domain.WeiXinMedia;
import com.tianque.plugin.weChat.domain.sendMessage.News;

/**
 * 上传文件服务
 *  @date  2014年4月18日
 */
public interface UploadFileService {

	/**
	 * 上传媒体文件
	 * @param accessToken接口访问凭证
	 * @param type 类型
	 * @param mediaFileUrl 媒体url
	 * @return
	 */
	public WeiXinMedia uploadMedia(String accessToken, String type, String mediaFileUrl);

	/**
	 * 上传图文文件（用于群发消息）
	 * @param accessToken
	 * @param list
	 * @return
	 */
	public WeiXinMedia uploadNews(String accessToken, List<News> list);
}
