package com.tianque.plugin.weChat.dao;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.WeChatResponse;

public interface WeChatResponseDao {
	/**
	 * 发送统计，已发送成功和发送待返回结果数
	 * @Title: countByWechatName 
	 * @Description: TODO
	 * @param wechatUserName
	 * @param startDate
	 * @return
	 * @return: int
	 */
	public int countByWechatName(String wechatUserName, Date startDate);
	/**
	 * 添加微信响应
	 * @Title: saveWeChatResponse 
	 * @param weChatResponse
	 * @return: 
	 */
	public Long saveWeChatResponse(WeChatResponse weChatResponse);

	/**
	 * id获取微信响应
	 * @Title: getById 
	 * @Description: TODO
	 * @param id
	 * @return: WeChatResponse
	 */
	public WeChatResponse getById(Long id);

	/**
	 * 微信的msgId获取
	 * @Title: getByMsgId 
	 * @Description: TODO
	 * @param msgId
	 * @return
	 * @return: WeChatResponse
	 */
	public WeChatResponse getByMsgId(String msgId);

	/**
	 * 更新微信响应
	 * @Title: updateWeChatResponse 
	 * @param weChatResponse
	 * @return: void
	 */
	public void updateWeChatResponse(WeChatResponse weChatResponse);

	/**
	 * 分页查询微信响应
	 * @Title: findWeChatResponse 
	 * @param weChatResponse
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return: PageInfo<WeChatResponse>
	 */
	public PageInfo<WeChatResponse> findWeChatResponse(WeChatResponse weChatResponse,
			Integer pageNum,
			Integer pageSize, String sidx, String sord);

}
