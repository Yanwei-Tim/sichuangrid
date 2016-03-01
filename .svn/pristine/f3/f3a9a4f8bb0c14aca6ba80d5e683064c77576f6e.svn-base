package com.tianque.platformMessage.service;

import java.util.List;
import java.util.Set;

import com.tianque.core.util.StoredFile;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Role;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;

public interface PlatformMessageService {

	/***
	 * 发送平台消息
	 * 
	 * @param PlatformMessage
	 *            消息实体 (后台发送时 请把title ,conten , sendType set进去
	 *            其中sendType要设置为PlatformMessageSendType.SYSTERM_SNED)
	 * @param receiverIds
	 *            收件人id 如果收件人类型是部门就是orgId,如果收件人类型是个人就是userId
	 * @param receiverType
	 *            收件人类型(如：ReceiverType.ORG 为部门类型,ReceiverType.USER为个人类型)
	 * @param storedFileList
	 *            附件后台发送时可设为null
	 * @param transferUserIds 根据orgId已查出的userIds 
	 *            当根据组织机构层级发送时，有大量orgId先查出userIds再发送     
	 * @return PlatformMessage 添加后的消息实体
	 */
	public PlatformMessage sendPlatformMessage(List<Long> receiverIds,
			String receiverType, PlatformMessage pm,
			List<StoredFile> storedFileList,List<Long> transferUserIds);

	/** IssueBusinessDelegate类中的sendPersonelMessageToOrganization方法会调用一下 不确定是否还用 */
	@Deprecated
	public void sendPlatformMessageToOrg(Long orgId, String messageContent,
			String messageTitle);

	/** 有几个模块在调用该方法 不确定是否还用 */
	@Deprecated
	public void sendPlatformMessageToUser(Long userId, String messageContent,
			String messageTitle);

	public void sendPlatformMessageToUser(PlatformMessage pm);

	public void sendPlatformMessageToOrg(PlatformMessage pm);

	/***
	 * 后台给一个部门发送平台消息
	 * 
	 * @param orgId
	 *            部门id
	 * @param pm
	 *            消息封装类
	 * @return PlatformMessage 发送后的消息实体
	 */
	public void sendPlatformMessageToOrg(Long orgId, PlatformMessage pm);

	/***
	 * 后台给多个个部门发送平台消息
	 * 
	 * @param orgIds
	 *            部门id集合
	 * @param pm
	 *            消息封装类
	 * @return PlatformMessage 添加后的消息实体
	 */
	public void sendPlatformMessageToOrg(List<Long> orgIds, PlatformMessage pm);

	/**
	 * 发件箱消息列表
	 * 
	 * @param senderId
	 *            发件人id
	 * @param page
	 *            当前页码
	 * @param rows
	 *            每页记录数
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式(asc/desc)
	 * @return
	 */
	public PageInfo<PlatformMessage> findPlatformMessageFromOutboxBySenderId(
			Long senderId, Integer isDraft, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 根据平台消息id获取发件箱消息
	 * 
	 * @param id
	 *            平台消息id
	 * @return PlatformMessage 消息封装类
	 */
	public PlatformMessage getOutboxPlatformMessageById(Long id);

	/**
	 * 收件箱消息列表
	 * 
	 * @param userId
	 * @param page
	 *            当前页码
	 * @param rows
	 *            每页记录数
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式(asc/desc)
	 * @return
	 */
	public PageInfo<PlatformMessage> findPlatformMessageFromInboxByUserId(
			Long userId, Integer isAdmin, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 根据id获取收件箱消息
	 * 
	 * @param id
	 * @return
	 */
	public PlatformMessage getInboxPlatformMessageById(Long id);

	/***
	 * 删除发件箱消息
	 * 
	 * @param pmId
	 */
	public void deletePlatformMessageFromOutboxById(Long id);

	/***
	 * 获取用户收件箱未读平台消息
	 * 
	 * @param userId
	 *            用户id
	 * @return 未读消息数量
	 */
	public int getUserUnreadPlatformMessageFromInbox(Long userId);

	/**
	 * 发件箱附件下载
	 * 
	 * @param id
	 * @return
	 */
	public PlatformMessageAttachFile getPlatformMessageAttachFileById(Long id);

	/**
	 * 回复平台消息
	 */
	public PlatformMessage replyPlatformMessage(
			PlatformMessage platformMessage, List<StoredFile> storedFileList);

	/***
	 * 标记收件箱消息为已读
	 * 
	 * @param pm
	 * @param user
	 */
	public void markInboxPlatgformMessageRead(PlatformMessage pm);

	/***
	 * 删除收件箱消息
	 * 
	 * @param platformMessage
	 *            要删除的平台消息
	 */
	public void deleteInboxPlatformMessage(PlatformMessage platformMessage);

	/**
	 * 获取用户的未读消息
	 */
	public PageInfo<PlatformMessage> findUnreadPlatformMessageFromInboxByUserId(
			Long receiverId, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * 获取部门下的岗位
	 * 
	 * @param orgLevel
	 * @param roleScope
	 * @return
	 */
	public List<Role> getRolesByOrgLevelAndRoleScope(Long orgLevel,
			Integer roleScope);

	/***
	 * 给岗位发送平台消息
	 * 
	 * @param roleReceiverIds
	 *            岗位id_orgId
	 * @param platformMessage
	 *            消息实体
	 * @return
	 */
	public void sendPlatformMessageToRole(List<Long> roleReceiverIds,
			PlatformMessage platformMessage, List<StoredFile> storedFileList);

	/**
	 * 删除互动交流收、发件箱过期消息
	 * 
	 */
	public void cleanInteractCommunicationMessage();
	
	public String createReceiverNames(List<Long> receiverIds,
			String receiverType);
	
	public String createRoleReceiverNames(Set<String> roleReceiverIds);
}
