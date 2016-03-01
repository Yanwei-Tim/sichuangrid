package com.tianque.plugin.weChat.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.messageTemplate.MessageTemplate;
import com.tianque.plugin.weChat.domain.messageTemplate.TemplateMessageRecord;

/**
 * 微信消息模板
 */
public interface MessageTemplateService {
	/**
	 * 微信消息模板发送(回复)
	 */
	public String sendTemplateMessage(Integer templateType, MessageTemplate messageTemplate);

	/**
	 * 微信消息模板发送(转发)
	 */
	public String forwardTemplateMessage(Integer templateType, MessageTemplate messageTemplate,
			String openIdStr);

	/**
	 * 消息模板记录-新增
	 */
	public TemplateMessageRecord addTemplateMessageRecord(TemplateMessageRecord templateRecord);

	/**
	 * 消息模板记录-修改
	 */
	public TemplateMessageRecord updateTemplateMessageRecord(TemplateMessageRecord templateRecord);

	/**
	 * 消息模板记录-查看
	 */
	public TemplateMessageRecord getTemplateMessageRecordById(Long id);

	/**
	 * 消息模板记录-删除
	 */
	public void deleteTemplateMessageRecordById(Long[] ids);

	/**
	 * 消息模板记录-列表分页
	 */
	public PageInfo<TemplateMessageRecord> findTemplateMessageRecordByPage(
			TemplateMessageRecord templateRecord, int pageNum, int pageSize, String sortField,
			String order);

	/**
	 * 根据微信服务号、模板ID-查看本部门的一条消息模板记录
	 */
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTid(String weChatUserId,
			String templateId,Long orgId);

	/**
	 * 根据微信服务号、模板编号-查看本部门的一条消息模板记录
	 */
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTnum(String weChatUserId,
			String templateNum,Long orgId);
	
	/**
	 * 根据微信服务号、模板标题-查看本部门的一条消息模板记录
	 */
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTitle(String weChatUserId,
			String title,Long orgId);
}
