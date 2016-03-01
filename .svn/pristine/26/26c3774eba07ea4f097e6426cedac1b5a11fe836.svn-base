package com.tianque.platformMessage.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.platformMessage.domain.PlatformMessage;

public interface PlatformMessageDao {

	/***
	 * 新增平台消息到发件箱
	 * 
	 * @param pm
	 * @return
	 */
	public PlatformMessage addPlatformMesssgeToOutbox(PlatformMessage pm);

	/***
	 * 新增平台消息到收件箱
	 * 
	 * @param pm
	 * @return
	 */
	public PlatformMessage addPlatformMesssgeToInbox(PlatformMessage pm);

	/***
	 * 根据id获取发件箱平台消息
	 * 
	 * @param pm
	 * @return
	 */
	public PlatformMessage getPlatformMesssgeFromOutboxById(Long id);

	/***
	 * 根据id获取收件箱平台消息
	 * 
	 * @param pm
	 * @return
	 */
	public PlatformMessage getPlatformMesssgeFromInboxById(Long id);

	/***
	 * 发件箱平台消息列表
	 */
	public PageInfo<PlatformMessage> findPlatformMessageFromOutboxBySenderId(
			Long senderId, Integer isDraft, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/***
	 * 收件箱平台消息列表
	 */
	public PageInfo<PlatformMessage> findPlatformMessageFromInbox(Long userId,
			Integer isAdmin, Integer page, Integer rows, String sidx,
			String sord, List<Integer> platformMessageTypes);

	/***
	 * 收件箱未读平台消息列表
	 */
	public PageInfo<PlatformMessage> findUnreadPlatformMessageFromInbox(
			Long userId, Long orgId, Integer page, Integer rows, String sidx,
			String sord, List<Integer> platformMessageTypes);

	/***
	 * 根据id删除发件箱平台消息
	 * 
	 * @param pmId
	 *            平台消息id
	 */
	public void deletePlatformMessageFromOutboxById(Long pmId);

	/***
	 * 用户收件箱未读消息数量
	 * 
	 * @param userId
	 *            用户id
	 * @param orgId
	 *            用户所在的orgid
	 * @param roleIds
	 *            用户的岗位ids
	 * @return 未读消息数量
	 */
	public int getUserUnreadPlatformMessageFromInbox(User user, List<Integer> platformMessageTypes);

	/***
	 * 标记收件人为user类型的平台消息为已读
	 * 
	 * @param pm
	 *            要标记的消息
	 */
	public void markUserInboxPlatgformMessageRead(PlatformMessage pm);

	/**
	 * 当阅读或删除收件人为org或role类型的消息时 保存用户与该消息的阅读状态或删除状态的关联关系
	 * 
	 * @param pm
	 */
	public void addkUserHasPlatformMessage(PlatformMessage pm);

	/***
	 * 根据用户id和消息id来获取用户与该消息的阅读状态或删除状态的关联关系的id
	 * 
	 * @param pmId
	 *            消息id
	 * @param userId
	 *            用户id
	 * @return 关联关系的id
	 */
	public Long getUserHasPlatformMessageId(Long pmId, Long userId);

	/***
	 * 标记收件人为org或role类型的消息为删除
	 * 
	 * @param id
	 */
	public void markUserHasPlatformMessageDelete(Long pmId, Long userId);

	/***
	 * 删除收件箱里收件人为user类型的消息
	 * 
	 * @param pm
	 *            要删除的消息
	 */
	public void deleteUserPlatformMessageFromInbox(PlatformMessage pm);

	/***
	 * 获取收件人为org或role类型的阅读状态
	 * 
	 * @param pmId
	 *            消息id
	 * @param userId
	 *            用户id
	 */
	public Integer getUserHasPlatformMessageReadState(Long pmId, Long userId);


	/**
	 * 清除收件箱的过期数据
	 * 
	 * @param allTime
	 */
	public void cleanInBoxplatformMessage(Map<String, Date> allTime);

	/**
	 * 清除发件箱的过期（不包含草稿箱）数据
	 * 
	 * @param allTime
	 */
	public void cleanOutBoxplatformMessage(Map<String, Date> allTime);

	/**
	 * 清除收件箱关联表的过期数据
	 * 
	 * @param allTime
	 */
	public void cleanUserHasPlatformmessages(Map<String, Date> allTime);

}
