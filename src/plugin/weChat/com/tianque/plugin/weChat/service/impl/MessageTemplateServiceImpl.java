package com.tianque.plugin.weChat.service.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.constant.MessageTemplateConstant;
import com.tianque.plugin.weChat.dao.MessageTemplateDao;
import com.tianque.plugin.weChat.domain.messageTemplate.MessageTemplate;
import com.tianque.plugin.weChat.domain.messageTemplate.TemplateMessageRecord;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.proxy.service.BaseHttpClientService;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.MessageTemplateService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.MessageTemplateUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 微信模板消息
 */
@Service("messageTemplateService")
@Transactional
public class MessageTemplateServiceImpl extends AbstractBaseService implements
		MessageTemplateService {
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private BaseHttpClientService baseHttpClientService;
	@Autowired
	private FanService fanService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private MessageTemplateDao messageTemplateDao;

	@Override
	public String sendTemplateMessage(Integer templateType, MessageTemplate messageTemplate) {
		verificationTemplate(templateType, messageTemplate);
		if (!StringUtil.isStringAvaliable(messageTemplate.getOpenId())) {
			throw new ServiceException("发送对象不能为空");
		}
		int templateTypeValue = templateType.intValue();
		String content = "";
		content = dealMessageTemplateByType(messageTemplate, templateTypeValue, content);
		String accessToken = getTencentUserAccessToken(messageTemplate);
		try {
			requestBroker(content, accessToken);
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的sendTemplateMessage方法出现异常，原因：", e);
			throw new ServiceException("模板消息发送出错");
		}
		return null;
	}

	/**
	 * 按不同的消息模板类型，进行相应的模板处理(回复)
	 */
	private String dealMessageTemplateByType(MessageTemplate messageTemplate,
			int templateTypeValue, String content) {
		if (templateTypeValue == MessageTemplateConstant.REPORT_PROGRESS_NOTICE_VALUE) {
			TemplateMessageRecord template = getTemplateMessageRecordByWidAndTnum(
					messageTemplate.getWeChatUserId(),
					MessageTemplateConstant.REPORT_PROGRESS_NOTICE_NUM, ThreadVariable
							.getOrganization().getId());
			String templateIdStr = template.getTemplateId();
			if (template == null || !StringUtil.isStringAvaliable(templateIdStr)) {
				throw new ServiceException("该微信公众号获取消息模板失败！");
			}
			messageTemplate.setTemplate_id(templateIdStr);
			content = MessageTemplateUtil.makeReportProgressOrTreatNotice(messageTemplate);
		} else {
			throw new ServiceException("消息模板类型有误");
		}
		return content;
	}

	@Override
	public String forwardTemplateMessage(Integer templateType, MessageTemplate messageTemplate,
			String openIdStr) {
		verificationTemplate(templateType, messageTemplate);
		if (!StringUtil.isStringAvaliable(openIdStr)) {
			throw new ServiceException("发送对象(粉丝号)不能为空");
		}
		int templateTypeValue = templateType.intValue();
		String content = "";
		dealForwardTemplateByType(messageTemplate, templateTypeValue);
		String backStr = "";
		String accessToken = "";
		accessToken = getTencentUserAccessToken(messageTemplate);
		if (accessToken.equals("代理错误")) {
			throw new ServiceException("请检查微信代理是否正常！");
		}
		try {
			if (openIdStr.startsWith("fan_")) {
				String openId = openIdStr.replaceAll("fan_", "");
				/**拼装发送消息的url*/
				String[] strTemp = openId.split(",");
				for (int i = 0; i < strTemp.length; i++) {
					backStr = setOnlyOneMessageTemplate(messageTemplate, templateTypeValue,
							content, accessToken, strTemp[i]);
				}
			} else if (openIdStr.startsWith("group_")) {
				String groupIds = openIdStr.replaceAll("group_", "");
				List<Fan> fanList = fanService.getFanListByGroupIdsAndWeChatUserId(groupIds,
						messageTemplate.getWeChatUserId());
				if (fanList.size() > 0) {
					for (int i = 0; i < fanList.size(); i++) {
						backStr = setOnlyOneMessageTemplate(messageTemplate, templateTypeValue,
								content, accessToken, fanList.get(i).getOpenId());
					}
				}
			}
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的forwardTemplateMessage方法出现异常，原因：", e);
			throw new ServiceException("模板消息发送出错");
		}
		if (!StringUtil.isStringAvaliable(accessToken) || accessToken.equals("null")) {
			throw new ServiceException("access_token验证失败！");
		}
		if (backStr.indexOf("invalid template_id") != -1) {
			throw new ServiceException("该消息模板不存在，请检查是否有被修改！");
		}
		if (backStr.indexOf("data format error") != -1) {
			throw new ServiceException("填写的格式有问题！");
		}
		if (backStr.equals("代理错误")) {
			throw new ServiceException("请检查微信代理是否正常！");
		}
		return null;
	}

	/**
	 * 按不同的消息模板类型，进行相应的模板处理(转发)
	 */
	private void dealForwardTemplateByType(MessageTemplate messageTemplate, int templateTypeValue) {
		TemplateMessageRecord template = null;
		if (templateTypeValue == MessageTemplateConstant.NOTICE_TO_BE_PROCESSED_VALUE) {
			template = getTemplateMessageRecordByWidAndTnum(messageTemplate.getWeChatUserId(),
					MessageTemplateConstant.NOTICE_TO_BE_PROCESSED_NUM, ThreadVariable
							.getOrganization().getId());
		} else if (templateTypeValue == MessageTemplateConstant.REPORT_PROGRESS_NOTICE_VALUE) {
			template = getTemplateMessageRecordByWidAndTnum(messageTemplate.getWeChatUserId(),
					MessageTemplateConstant.REPORT_PROGRESS_NOTICE_NUM, ThreadVariable
							.getOrganization().getId());
		} else {
			throw new ServiceException("消息模板类型有误");
		}
		if (template == null) {
			throw new ServiceException("该微信公众号获取消息模板失败！");
		}
		String templateIdStr = template.getTemplateId();
		if (!StringUtil.isStringAvaliable(templateIdStr)) {
			throw new ServiceException("该微信公众号获取消息模板失败！");
		}
		messageTemplate.setTemplate_id(templateIdStr);
	}

	/**
	 * 设置单条信息模板记录
	 */
	private String setOnlyOneMessageTemplate(MessageTemplate messageTemplate,
			int templateTypeValue, String content, String accessToken, String openId) {
		messageTemplate.setOpenId(openId);
		if (templateTypeValue == MessageTemplateConstant.NOTICE_TO_BE_PROCESSED_VALUE
				|| templateTypeValue == MessageTemplateConstant.REPORT_PROGRESS_NOTICE_VALUE) {
			content = MessageTemplateUtil.makeReportProgressOrTreatNotice(messageTemplate);
		}
		String backStr = "";
		backStr = requestBroker(content, accessToken);
		return backStr;
	}

	/**
	 * 验证模板消息和微信连接号
	 */
	private void verificationTemplate(Integer templateType, MessageTemplate messageTemplate) {
		if (messageTemplate == null || templateType == null) {
			throw new ServiceException("模板消息发送出错");
		}
		if (!StringUtil.isStringAvaliable(messageTemplate.getWeChatUserId())) {
			throw new ServiceException("微信连接号不能为空");
		}
	}

	/**
	 * 获取公众账号对象和Token
	 */
	private String getTencentUserAccessToken(MessageTemplate messageTemplate) {
		try {
			//获取公众账号对象
			TencentUser tencentUser = tencentUserService
					.getTencentUserByWeChatUserId(messageTemplate.getWeChatUserId());
			String accessToken = weChatService.getAccessToken(tencentUser);
			return accessToken;
		} catch (Exception e) {
			throw new ServiceException("token验证出错!");
		}
	}

	/**
	 * 拼装发送消息的url并请求代理
	 */
	private String requestBroker(String content, String accessToken) {
		/**拼装发送消息的url*/
		String url = Constants.SEND_MESSAGETEMPLATE_URL.replace("ACCESS_TOKEN", accessToken)
				+ "&requestType=templateMessage&content=" + URLEncoder.encode(content);
		String backStr = baseHttpClientService.postMethod(url);
		//处理微信代理，返回的信息
		try {
			backStr = URLDecoder.decode(backStr, "UTF-8");
			new ByteArrayInputStream(backStr.getBytes());
		} catch (UnsupportedEncodingException e) {
			logger.error("类MessageTemplateServiceImpl的requestBroker方法出现异常，原因：", e);
		}
		return backStr;
	}

	@Override
	public TemplateMessageRecord addTemplateMessageRecord(TemplateMessageRecord templateRecord) {
		verificationTemplateRecord(templateRecord);
		onlyVerificationForAdd(templateRecord);
		try {
			//根据部门id重新获取部门,以便得到完整的部门信息,如：orgInternalCode
			templateRecord.setOrg(organizationService.getFullOrgById(templateRecord.getOrg()
					.getId()));
			return messageTemplateDao.addTemplateMessageRecord(templateRecord);
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的addTemplateMessageRecord方法出现异常，原因：", e);
			throw new ServiceException("新增消息模板出错");
		}
	}

	/**
	 * 验证新增操作的模板id、模板标题、模板编号是否存在
	 */
	private void onlyVerificationForAdd(TemplateMessageRecord templateRecord) {
		Long orgId = templateRecord.getOrg().getId();
		//验证模板ID唯一
		if (getTemplateMessageRecordByWidAndTid(templateRecord.getWeChatUserId(),
				templateRecord.getTemplateId(), orgId) != null) {
			throw new ServiceException("模板id已经存在！");
		}
		//验证模板编号唯一
		if (getTemplateMessageRecordByWidAndTnum(templateRecord.getWeChatUserId(),
				templateRecord.getTemplateNum(), orgId) != null) {
			throw new ServiceException("模板编号已经存在！");
		}
		//验证模板标题唯一
		if (getTemplateMessageRecordByWidAndTitle(templateRecord.getWeChatUserId(),
				templateRecord.getTitle(), orgId) != null) {
			throw new ServiceException("模板标题已经存在！");
		}
	}

	/**
	 * 验证消息模板
	 */
	private void verificationTemplateRecord(TemplateMessageRecord templateRecord) {
		if (templateRecord == null) {
			throw new ServiceException("获取消息模板参数出错！");
		}
		if (templateRecord.getOrg() == null || templateRecord.getOrg().getId() == null) {
			throw new ServiceException("当前辖区不能为空！");
		}
		if (!StringUtil.isStringAvaliable(templateRecord.getTemplateId())) {
			throw new ServiceException("模板ID不能为空！");
		}
		if (!StringUtil.isStringAvaliable(templateRecord.getTemplateNum())) {
			throw new ServiceException("模板编号不能为空！");
		}
		if (!StringUtil.isStringAvaliable(templateRecord.getTitle())) {
			throw new ServiceException("模板标题不能为空！");
		}
		if (!StringUtil.isStringAvaliable(templateRecord.getWeChatUserId())) {
			throw new ServiceException("微信连接号(公众服务号)不能为空");
		}
	}

	@Override
	public TemplateMessageRecord updateTemplateMessageRecord(TemplateMessageRecord templateRecord) {
		if (templateRecord == null || templateRecord.getId() == null) {
			throw new ServiceException("修改消息模板出错！");
		}
		verificationTemplateRecord(templateRecord);
		verificationIdNumTitleIsOnly(templateRecord);
		try {
			return messageTemplateDao.updateTemplateMessageRecord(templateRecord);
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的updateTemplateMessageRecord方法出现异常，原因：", e);
			throw new ServiceException("修改消息模板记录出错");
		}
	}

	/**
	 * 验证修改操作的模板id、模板标题、模板编号是否存在
	 */
	private void verificationIdNumTitleIsOnly(TemplateMessageRecord templateRecord) {
		Long orgId = templateRecord.getOrg().getId();
		TemplateMessageRecord templateIdObject = getTemplateMessageRecordByWidAndTid(
				templateRecord.getWeChatUserId(), templateRecord.getTemplateId(), orgId);
		TemplateMessageRecord titleObject = getTemplateMessageRecordByWidAndTitle(
				templateRecord.getWeChatUserId(), templateRecord.getTitle(), orgId);
		TemplateMessageRecord templateNumObject = getTemplateMessageRecordByWidAndTnum(
				templateRecord.getWeChatUserId(), templateRecord.getTemplateNum(), orgId);
		if (templateIdObject != null
				&& templateIdObject.getId().longValue() != templateRecord.getId().longValue()) {
			throw new ServiceException("模板id已经存在！");
		}
		if (templateNumObject != null
				&& templateNumObject.getId().longValue() != templateRecord.getId().longValue()) {
			throw new ServiceException("模板编号已经存在！");
		}
		if (titleObject != null
				&& titleObject.getId().longValue() != templateRecord.getId().longValue()) {
			throw new ServiceException("模板标题已经存在！");
		}
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordById(Long id) {
		if (id == null) {
			throw new ServiceException("获取消息模板出错,请刷新后重试!");
		}
		try {
			return messageTemplateDao.getTemplateMessageRecordById(id);
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的getTemplateMessageRecordById方法出现异常，原因：", e);
			throw new ServiceException("获取消息模板出错");
		}
	}

	@Override
	public void deleteTemplateMessageRecordById(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new ServiceException("请选择一条数据删除！");
		}
		try {
			for (Long id : ids) {
				messageTemplateDao.deleteTemplateMessageRecordById(id);
			}
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的deleteTemplateMessageRecordById方法出现异常，原因：", e);
			throw new ServiceException("删除消息模板出错");
		}
	}

	@Override
	public PageInfo<TemplateMessageRecord> findTemplateMessageRecordByPage(
			TemplateMessageRecord templateRecord, int pageNum, int pageSize, String sortField,
			String order) {
		if (templateRecord == null || templateRecord.getOrg() == null
				|| templateRecord.getOrg().getId() == null) {
			throw new ServiceException("查询消息模板列表出错,请刷新后重试!");
		}
		try {
			PageInfo<TemplateMessageRecord> pageInfo = messageTemplateDao
					.findTemplateMessageRecordByPage(templateRecord, pageNum, pageSize, sortField,
							order);
			return pageInfo;
		} catch (Exception e) {
			logger.error("类MessageTemplateServiceImpl的findTemplateMessageRecordByPage方法出现异常，原因：", e);
			throw new ServiceException("查询消息模板列表出错");
		}
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTid(String weChatUserId,
			String templateId, Long orgId) {
		if (!StringUtil.isStringAvaliable(weChatUserId)
				|| !StringUtil.isStringAvaliable(templateId) || orgId == null) {
			throw new ServiceException("获取消息模板出错!");
		}
		try {
			return messageTemplateDao.getTemplateMessageRecordByWidAndTid(weChatUserId, templateId,
					orgId);
		} catch (Exception e) {
			logger.error(
					"类MessageTemplateServiceImpl的getTemplateMessageRecordByWidAndTid方法出现异常，原因：", e);
			throw new ServiceException("获取消息模板出错");
		}
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTnum(String weChatUserId,
			String templateNum, Long orgId) {
		if (!StringUtil.isStringAvaliable(weChatUserId)
				|| !StringUtil.isStringAvaliable(templateNum) || orgId == null) {
			throw new ServiceException("获取消息模板出错!");
		}
		try {
			return messageTemplateDao.getTemplateMessageRecordByWidAndTnum(weChatUserId,
					templateNum, orgId);
		} catch (Exception e) {
			logger.error(
					"类MessageTemplateServiceImpl的getTemplateMessageRecordByWidAndTnum方法出现异常，原因：", e);
			throw new ServiceException("获取消息模板出错");
		}
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTitle(String weChatUserId,
			String title, Long orgId) {
		if (!StringUtil.isStringAvaliable(weChatUserId) || !StringUtil.isStringAvaliable(title)
				|| orgId == null) {
			throw new ServiceException("获取消息模板出错!");
		}
		try {
			return messageTemplateDao.getTemplateMessageRecordByWidAndTitle(weChatUserId, title,
					orgId);
		} catch (Exception e) {
			logger.error(
					"类MessageTemplateServiceImpl的getTemplateMessageRecordByWidAndTitle方法出现异常，原因：",
					e);
			throw new ServiceException("获取消息模板出错");
		}
	}
}
