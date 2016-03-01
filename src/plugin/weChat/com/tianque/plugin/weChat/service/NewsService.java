/**
 * 
 */
package com.tianque.plugin.weChat.service;

import java.util.Map;

import com.tianque.plugin.weChat.domain.sendMessage.NewsMessage;

/**
 * 图文服务
 *
 *  @date  2014年4月18日
 */
public interface NewsService {
	/**
	 *根据类型获取图文
	 * @return
	 */
	public NewsMessage getArticleByType(int type, Map<String, String> messageMap);

}
