package com.tianque.plugin.weChat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.plugin.weChat.dao.WeChatSourceAttachmentDao;
import com.tianque.plugin.weChat.domain.user.WeChatSourceAttachment;
import com.tianque.plugin.weChat.service.WeChatSourceAttachmentService;

@Service("weChatSourceAttachmentService")
@Transactional
public class WeChatSourceAttachmentServiceImpl extends AbstractBaseService implements
		WeChatSourceAttachmentService {

	@Autowired
	private WeChatSourceAttachmentDao weChatSourceAttachmentDao;

	/**
	 * 修改微信素材附件
	 * @param weChatSourceAttachment
	 * @return
	 */
	public Integer updateWeChatSouceAttachmentById(WeChatSourceAttachment weChatSourceAttachment) {
		return weChatSourceAttachmentDao.updateWeChatSouceAttachmentById(weChatSourceAttachment);
	}

	/**
	 * 添加微信素材附件
	 * @param WeChatSourceAttachment
	 * @return
	 */
	@Override
	public Long addWeChatSourceAttachment(WeChatSourceAttachment weChatSourceAttachment) {
		return weChatSourceAttachmentDao.addWeChatSourceAttachment(weChatSourceAttachment);
	}

	/**
	* 根据服务号和素材id加载微信附件
	* @param weChatUserId
	* @param sourceId
	* @return
	*/

	@Override
	public List<WeChatSourceAttachment>  getWeChatSouceAttachmentByWeChatuserIdAndSouceId(
			String weChatUserId, Long sourceId) {
		return weChatSourceAttachmentDao.getWeChatSouceAttachmentByWeChatuserIdAndSouceId(
				weChatUserId, sourceId);
	}

	/**
	 * 根据服务号和素材id删除微信附件
	 * @param weChatUserId
	 * @param sourceId
	 * @return
	 */
	@Override
	public Integer deleteWeChatSouceAttachmentBySouceIdAndWeChatuserId(String weChatUserId,
			Long sourceId) {
		return weChatSourceAttachmentDao.deleteWeChatSouceAttachmentBySouceIdAndWeChatuserId(
				weChatUserId, sourceId);
	}

	/**
	 * 根据id加载微信附件
	 * @param id
	 * @return
	 */
	@Override
	public WeChatSourceAttachment getWeChatSouceAttachmentById(Long id) {
		return weChatSourceAttachmentDao.getWeChatSouceAttachmentById(id);
	}

	/**
	 * 根据id删除微信附件
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteWeChatSouceAttachmentById(Long id) {
		return weChatSourceAttachmentDao.deleteWeChatSouceAttachmentById(id);
	}

	/**
	 * 根据sourceId删除微信附件
	 * @param sourceId
	 * @return
	 */
	@Override
	public Integer deleteWeChatSouceAttachmentBySourceId(Long sourceId) {
		return weChatSourceAttachmentDao.deleteWeChatSouceAttachmentBySourceId(sourceId);
	}

	/**
	 * 根据服务号删除微信附件
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentByWeChatUserId(String weChatUserId)
	{
		return weChatSourceAttachmentDao.deleteWeChatSouceAttachmentByWeChatUserId(weChatUserId);
	}
	/**
	 * 根据sourceId加载微信附件
	 * @param sourceId
	 * @return
	 */
	@Override
	public WeChatSourceAttachment getWeChatSouceAttachmentBySourceId(Long sourceId) {
		return weChatSourceAttachmentDao.getWeChatSouceAttachmentBySourceId(sourceId);
	}

}
