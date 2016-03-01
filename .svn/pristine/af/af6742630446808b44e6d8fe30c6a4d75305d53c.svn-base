package com.tianque.plugin.weChat.dao;

import java.util.List;

import com.tianque.plugin.weChat.domain.user.WeChatSourceAttachment;

public interface WeChatSourceAttachmentDao {
	/**
	 * 添加微信素材附件
	 * @param WeChatSourceAttachment
	 * @return
	 */
	public Long addWeChatSourceAttachment(WeChatSourceAttachment weChatSourceAttachment);

	/**
	 * 修改微信素材附件
	 * @param weChatSourceAttachment
	 * @return
	 */
	public Integer updateWeChatSouceAttachmentById(WeChatSourceAttachment weChatSourceAttachment);

	/**
	 * 根据服务号和素材id加载微信附件
	 * @param weChatUserId
	 * @param sourceId
	 * @return
	 */
	public List<WeChatSourceAttachment> getWeChatSouceAttachmentByWeChatuserIdAndSouceId(
			String weChatUserId, Long sourceId);

	/**
	 * 根据id加载微信附件
	 * @param id
	 * @return
	 */
	public WeChatSourceAttachment getWeChatSouceAttachmentById(Long id);

	/**
	 * 根据服务号和素材id删除微信附件
	 * @param weChatUserId
	 * @param sourceId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentBySouceIdAndWeChatuserId(String weChatUserId,
			Long sourceId);

	/**
	 * 根据id删除微信附件
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentById(Long id);

	/**
	 * 根据sourceId删除微信附件
	 * @param sourceId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentBySourceId(Long sourceId);

	/**
	 * 根据服务号删除微信附件
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentByWeChatUserId(String weChatUserId);
	/**
	 * 根据sourceId加载微信附件
	 * @param sourceId
	 * @return
	 */
	public WeChatSourceAttachment getWeChatSouceAttachmentBySourceId(Long sourceId);
}
