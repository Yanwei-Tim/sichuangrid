package com.tianque.plugin.weChat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.weChat.dao.WeChatSourceAttachmentDao;
import com.tianque.plugin.weChat.domain.user.WeChatSourceAttachment;

@Repository("weChatSourceAttachmentDao")
public class WeChatSourceAttachmentDaoImpl extends AbstractBaseDao implements
		WeChatSourceAttachmentDao {

	/**
	 * 添加微信素材附件
	 * @param WeChatSourceAttachment
	 * @return
	 */
	public Long addWeChatSourceAttachment(WeChatSourceAttachment weChatSourceAttachment) {
		return (Long) getSqlMapClientTemplate().insert("souceAttachment.saveWeChatSouceAttachment",
				weChatSourceAttachment);
	}

	/**
	 * 修改微信素材附件
	 * @param weChatSourceAttachment
	 * @return
	 */
	public Integer updateWeChatSouceAttachmentById(WeChatSourceAttachment weChatSourceAttachment) {
		return (Integer) getSqlMapClientTemplate().update(
				"souceAttachment.updateWeChatSouceAttachmentById", weChatSourceAttachment);
	}

	/**
	 * 根据服务号和素材id加载微信附件
	 * @param weChatUserId
	 * @param sourceId
	 * @return
	 */
	public List<WeChatSourceAttachment> getWeChatSouceAttachmentByWeChatuserIdAndSouceId(
			String weChatUserId, Long sourceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		map.put("sourceId", sourceId);
		return (List<WeChatSourceAttachment>) getSqlMapClientTemplate().queryForList(
				"souceAttachment.getWeChatSouceAttachmentBySouceIdAndWeChatuserId", map);
	}

	/**
	 * 根据id加载微信附件
	 * @param id
	 * @return
	 */
	public WeChatSourceAttachment getWeChatSouceAttachmentById(Long id) {

		return (WeChatSourceAttachment) getSqlMapClientTemplate().queryForObject(
				"souceAttachment.getWeChatSouceAttachmentById", id);
	}

	/**
	 * 根据sourceId加载微信附件
	 * @param sourceId
	 * @return
	 */
	public WeChatSourceAttachment getWeChatSouceAttachmentBySourceId(Long sourceId) {

		return (WeChatSourceAttachment) getSqlMapClientTemplate().queryForObject(
				"souceAttachment.getWeChatSouceAttachmentBySourceId", sourceId);
	}

	/**
	 * 根据服务号和素材id删除微信附件
	 * @param weChatUserId
	 * @param sourceId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentBySouceIdAndWeChatuserId(String weChatUserId,
			Long sourceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		map.put("sourceId", sourceId);
		return (Integer) getSqlMapClientTemplate().delete(
				"souceAttachment.deleteWeChatSouceAttachmentBySouceIdAndWeChatuserId", map);
	}

	/**
	 * 根据id删除微信附件
	 * @param id
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentById(Long id) {
		return (Integer) getSqlMapClientTemplate().delete(
				"souceAttachment.deleteWeChatSouceAttachmentById", id);
	}
	
	/**
	 * 根据服务号删除微信附件
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentByWeChatUserId(String weChatUserId) {
		return (Integer) getSqlMapClientTemplate().delete(
				"souceAttachment.deleteWeChatSouceAttachmentByWeChatUserId", weChatUserId);
	}

	/**
	 * 根据sourceId删除微信附件
	 * @param sourceId
	 * @return
	 */
	public Integer deleteWeChatSouceAttachmentBySourceId(Long sourceId) {
		return (Integer) getSqlMapClientTemplate().delete(
				"souceAttachment.deleteWeChatSouceAttachmentBySourceId", sourceId);
	}
}
