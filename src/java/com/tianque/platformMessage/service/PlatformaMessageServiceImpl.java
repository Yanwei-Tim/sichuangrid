package com.tianque.platformMessage.service;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.earlyWarning.domain.EarlyWarning;
import com.tianque.baseInfo.earlyWarning.service.EarlyWarningService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.uitl.IssueToCMSUtil;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.constants.PlatformMessageSendType;
import com.tianque.platformMessage.constants.PlatformMessageType;
import com.tianque.platformMessage.constants.ReceiverType;
import com.tianque.platformMessage.constants.RoleScope;
import com.tianque.platformMessage.dao.PlatformMessageDao;
import com.tianque.platformMessage.dao.PlatformMessagesAttachFileDao;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.UserHasPlatformMessageTypesDubboService;
import com.tianque.util.TemplateConfiguration;

import freemarker.template.Template;

@Service("platformaMessageService")
@Transactional
public class PlatformaMessageServiceImpl implements PlatformMessageService {
	private static Logger logger = LoggerFactory
			.getLogger(PlatformaMessageServiceImpl.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserHasPlatformMessageTypesDubboService userHasPlatformMessageTypesDubboService;
	@Autowired
	private PlatformMessageDao platformMessageDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PlatformMessagesAttachFileDao platformMessagesAttachFileDao;
	@Autowired
	private TemplateConfiguration templateConfiguration;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private EarlyWarningService earlyWarningService;
	private XMPPConnection xmppConnection;

	private static final int searchUserIdsPageNum = 800;

	@Override
	public void sendPlatformMessageToUser(PlatformMessage pm) {
		List<Long> receiverIds = new ArrayList<Long>();
		receiverIds.add(pm.getReceiverId());
		sendPlatformMessage(receiverIds, ReceiverType.USER, pm, null, null);
	}

	/** 后台给一个部门发消息 */
	@Override
	public void sendPlatformMessageToOrg(Long orgId, PlatformMessage pm) {
		List<Long> receiverIds = new ArrayList<Long>();
		receiverIds.add(orgId);
		sendPlatformMessage(receiverIds, pm.getReceiverType(), pm, null, null);
	}

	public void sendPlatformMessageToOrg(PlatformMessage pm) {
		List<Long> receiverIds = new ArrayList<Long>();
		receiverIds.add(pm.getReceiverId());
		sendPlatformMessage(receiverIds, pm.getReceiverType(), pm, null, null);
	}

	/** 后台给多个部门发消息 */
	@Override
	public void sendPlatformMessageToOrg(List<Long> orgIds, PlatformMessage pm) {

		sendPlatformMessage(orgIds, pm.getReceiverType(), pm, null, null);
	}

	/** 给岗位发消息 */
	@Override
	public void sendPlatformMessageToRole(List<Long> roleReceiverIds,
			PlatformMessage pm, List<StoredFile> storedFileList) {
		if (pm == null) {
			throw new BusinessValidationException("消息发送失败，请重试");
		}
		try {
			fillPlatformMessageProperty(pm);
			pm.setReceiverType(ReceiverType.ROLE);
			if (pm.getId() != null
					&& pm.getPlatformMessageSource() != null
					&& PlatformMessageType.IS_DRAFT_MESSAGE.equals(pm
							.getPlatformMessageSource())) {
				// 删除草稿箱消息
				deletePlatformMessageFromOutboxById(pm.getId());
			}
			// 保存消息到发件箱
			savePlatforMessageToOutbox(pm, storedFileList);
			// 没有人就不保存到收件箱
			if (roleReceiverIds == null || roleReceiverIds.size() <= 0) {
				return;
			}
			PlatformMessage pme = platformMessageDao
					.addPlatformMesssgeToInbox(pm);
			for (Long receiverId : roleReceiverIds) {
				pm.setReceiverId(receiverId);
				pm.setReadState(PlatformMessageType.MESSAGE_UNREAD_STATUS);
				platformMessageDao.addkUserHasPlatformMessage(pm);
			}
			if (storedFileList != null && storedFileList.size() > 0
					&& pme != null) {
				List<StoredFile> fileList = copyFileToReceiver(storedFileList);
				saveOutboxPmAttachFile(pme.getId(), pm.getSender(), fileList,
						false);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	public String createRoleReceiverNames(Set<String> roleReceiverIds) {
		try {
			StringBuilder sb = new StringBuilder();
			for (String roleId_orgId : roleReceiverIds) {
				Long roleId = Long.parseLong(roleId_orgId.split("_")[0]);
				Long orgId = Long.parseLong(roleId_orgId.split("_")[1]);
				String roleName = permissionService.findRoleById(roleId)
						.getRoleName();
				String orgName = organizationDubboService.getSimpleOrgById(
						orgId).getOrgName();
				sb.append(orgName + " " + roleName).append(" ");
			}
			return sb.toString();
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	/** 发消息 */
	@Override
	public PlatformMessage sendPlatformMessage(List<Long> receiverIds,
			String receiverType, PlatformMessage pm,
			List<StoredFile> storedFileList, List<Long> transferUserIds) {
		try {
			if (pm == null) {
				throw new BusinessValidationException("消息发送失败，请重试！");
			}
			Long draftId = null;
			if (pm.getIsDraft() == null
					|| pm.getIsDraft().toString().trim().length() == 0) {
				pm.setIsDraft(PlatformMessageType.IS_NOT_DRAFT_MESSAGE);
			}
			if (PlatformMessageType.IS_NOT_DRAFT_MESSAGE
					.equals(pm.getIsDraft())) {
				// 参数校验
				validateParams(receiverIds, receiverType, storedFileList);
			}
			// 填充属性
			fillPlatformMessageProperty(pm);
			// 如果消息是转发的，那么将历史消息增加进去.
			if (pm.getMessageMode() != null
					&& pm.getMessageMode().trim().length() != 0
					&& (DialogMode.MESSAGE_OUTBOXFORWARD.equals(pm
							.getMessageMode()) || DialogMode.MESSAGE_INBOXFORWARD
							.endsWith(pm.getMessageMode()))) {
				fillInHistoryMessage(pm);
			}
			// set收件人类型
			pm.setReceiverType(receiverType);
			if (PlatformMessageType.IS_NOT_DRAFT_MESSAGE
					.equals(pm.getIsDraft())) {
				// set收件人名称
				if (!StringUtil.isStringAvaliable(pm.getReceiverNames())) {
					pm.setReceiverNames(createReceiverNames(receiverIds,
							receiverType));
				}
			}
			if (pm.getId() != null
					&& pm.getPlatformMessageSource() != null
					&& PlatformMessageType.IS_DRAFT_MESSAGE.equals(pm
							.getPlatformMessageSource())) {
				// 将草稿消息ID赋值给draftId，用于草稿消息发送后删除草稿箱消息
				draftId = pm.getId();
			}

			// 保存消息到发件箱
			pm = savePlatforMessageToOutbox(pm, storedFileList);

			if (PlatformMessageType.IS_NOT_DRAFT_MESSAGE
					.equals(pm.getIsDraft())) {
				// 保存消息到收件箱
				if (ReceiverType.USER.equals(receiverType)) {
					saveUserTypePlatformMessageToInbox(receiverIds, pm,
							storedFileList);

				} else if (ReceiverType.ORG.equals(receiverType)) {
					if (PlatformMessageSendType.SYSTERM_SNED.equals(pm
							.getSendType())) {
						// 给IM客户端发送平台消息
						sendPlatformMessageToIMClient(receiverIds, pm);
					}
					if (pm != null && transferUserIds == null) {
						// 筛选出当前用户所在的组织机构线
						saveOrgTypePlatformMessageToInbox(receiverIds, pm,
								storedFileList);

					}
					if (pm != null && transferUserIds != null) {

						saveOrgTypePlatformMessageToInboxByUserIds(
								transferUserIds, pm, storedFileList);
					}
				}
			}
			if (draftId != null) {
				deletePlatformMessageFromOutboxById(draftId);
			}
			return pm;
		} catch (Exception e) {
			throw new ServiceValidationException("操作失败，请重试", e);
		}
	}

	private void sendPlatformMessageToIMClient(List<Long> receiverIds,
			PlatformMessage pm) {
		if (!Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.IS_SENDER_MSG_TO_CLIENT))) {
			return;
		}
		for (Long receiverid : receiverIds) {
			List<User> list = permissionService.findUsersByOrgId(receiverid);
			for (User user : list) {
				StringBuilder stb = new StringBuilder();
				if (StringUtil.isStringAvaliable(pm.getUrl())) {
					stb.append("<a href='").append(GridProperties.WEB_APP_URL)
							.append(pm.getUrl()).append("'>点击这里查看</a>");
				} else {
					stb.append("");
				}
				sendMessageToClient(pm.getContent() + stb.toString(),
						user.getUserName());
			}
		}
	}

	/** 系统推送的消息发送到IM客户端 */
	private void sendMessageToClient(String messagecontent, String to) {

		try {
			Message message = new Message();
			message.setTo(to + "@" + GridProperties.OPENFIREHOST);
			message.setBody(messagecontent);
			logger.error("链接状态：" + getXmppConnection().isConnected());

			Chat mychat = getXmppConnection().getChatManager().createChat(
					to + "@" + GridProperties.OPENFIREHOST,
					new MessageListener() {
						@Override
						public void processMessage(Chat chat, Message message) {
						}
					});
			mychat.sendMessage(message); // 发送信息
		} catch (XMPPException e) {
			logger.error("发送消息到Im客户端失败", e);
		}
	}

	public XMPPConnection getXmppConnection() {
		try {
			if (null == xmppConnection || !xmppConnection.isConnected()) {
				ConnectionConfiguration config = new ConnectionConfiguration(
						GridProperties.OPENFIREHOST,
						Integer.valueOf(GridProperties.OPENFIREPORT));
				xmppConnection = new XMPPConnection(config);
				xmppConnection.connect();
				xmppConnection.login("admin", "admin");
			}
		} catch (XMPPException e) {
			throw new BusinessValidationException(e.getMessage());
		}
		return xmppConnection;
	}

	/** 回复消息 */
	@Override
	public PlatformMessage replyPlatformMessage(PlatformMessage pm,
			List<StoredFile> storedFileList) {
		if (pm == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		try {
			// 回复的消息都是人工发送的消息
			pm.setMessageType(PlatformMessageType.GENERAL_MESSAGE);
			pm.setReceiverType(ReceiverType.USER);
			pm.setSendType(PlatformMessageSendType.MANUAL_SNED);
			fillPlatformMessageProperty(pm);
			fillInHistoryMessage(pm);

			pm = platformMessageDao.addPlatformMesssgeToOutbox(pm);

			PlatformMessage pmsg = platformMessageDao
					.addPlatformMesssgeToInbox(pm);
			pmsg.setReadState(PlatformMessageType.MESSAGE_UNREAD_STATUS);
			platformMessageDao.addkUserHasPlatformMessage(pmsg);
			if (storedFileList != null && storedFileList.size() > 0) {
				List<StoredFile> fileList = copyFileToReceiver(storedFileList);
				saveOutboxPmAttachFile(pmsg.getId(), pm.getSender(), fileList,
						false);
			}
			return pmsg;
		} catch (Exception e) {
			throw new ServiceValidationException("消息回复出错", "操作失败，请重试", e);
		}
	}

	private void fillInHistoryMessage(PlatformMessage pm) {
		if (pm == null) {
			return;
		}
		PlatformMessage replyPm = null;
		try {
			Template template = templateConfiguration
					.getTemplate(GridProperties.HISTORY_MESSAGE_TEMPLATE);
			Map<String, String> map = new HashMap<String, String>();
			if (DialogMode.MESSAGE_OUTBOXFORWARD.equals(pm.getMessageMode())) {
				replyPm = getOutboxPlatformMessageById(pm.getReplyMessageId());
			} else {
				replyPm = getInboxPlatformMessageById(pm.getReplyMessageId());
			}
			if (replyPm == null) {
				return;
			}

			map.put("title", replyPm.getTitle());
			map.put("senderName", replyPm.getSender().getName());
			map.put("senderOrgName", replyPm.getSender().getOrganization()
					.getOrgName());
			map.put("sendDate",
					CalendarUtil.format("yyyy-MM-dd HH:mm:ss",
							replyPm.getSendDate()));
			if (ReceiverType.USER.equals(replyPm.getReceiverType())) {
				map.put("receiverNames", "个人");
			} else if (ReceiverType.ORG.equals(replyPm.getReceiverType())) {
				map.put("receiverNames", "部门");
			} else if (ReceiverType.ROLE.equals(replyPm.getReceiverType())) {
				map.put("receiverNames", "岗位");
			} else if (ReceiverType.MOBILE.equals(replyPm.getReceiverType())) {
				map.put("receiverNames", "手机");
			}
			map.put("content", replyPm.getContent());

			StringWriter out = new StringWriter();
			template.process(map, out);
			String rs = out.getBuffer().toString();
			if (replyPm.getHistoryMessage() == null) {
				pm.setHistoryMessage(rs);
			} else {
				pm.setHistoryMessage(rs + replyPm.getHistoryMessage());
			}
		} catch (Exception e) {
			throw new ServiceValidationException("保存历史消息出错", "操作失敗，請重試", e);
		}

	}

	private void saveOrgTypePlatformMessageToInbox(List<Long> receiverIds,
			PlatformMessage pm, List<StoredFile> storedFileList) {
		try {
			PlatformMessage pme = platformMessageDao
					.addPlatformMesssgeToInbox(pm);
			List<Long> userReceiverIdsByOrgIds = getUserReceiverIdsByOrgIds(receiverIds);

			for (Long receiverId : userReceiverIdsByOrgIds) {
				pm.setReceiverId(receiverId);
				pm.setReadState(PlatformMessageType.MESSAGE_UNREAD_STATUS);
				platformMessageDao.addkUserHasPlatformMessage(pm);
			}
			if (storedFileList != null && storedFileList.size() > 0
					&& pme != null) {
				List<StoredFile> fileList = copyFileToReceiver(storedFileList);
				saveOutboxPmAttachFile(pme.getId(), pm.getSender(), fileList,
						false);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("保存层级用户消息出错", "操作失败，请重试", e);
		}
	}

	private List<Long> getUserReceiverIdsByOrgIds(List<Long> orgIds) {
		List userReceiverIdsByOrgIds = new ArrayList<Long>();
		if (orgIds == null || orgIds.size() < 1) {
			return userReceiverIdsByOrgIds;
		}
		int pages = orgIds.size() / searchUserIdsPageNum;
		int start = 0;
		int end = 0;
		for (int i = 0; i < pages + 1; i++) {
			start = i * searchUserIdsPageNum;
			end = (i + 1) * searchUserIdsPageNum - 1;
			if (end > orgIds.size()) {
				end = orgIds.size();
			}
			List<Long> userReceiverIdTemps = permissionService
					.findUserIdsByOrgIds(orgIds.subList(start, end));
			userReceiverIdsByOrgIds.addAll(userReceiverIdTemps);
		}
		return userReceiverIdsByOrgIds;
	}

	private void saveOrgTypePlatformMessageToInboxByUserIds(List<Long> userIds,
			PlatformMessage pm, List<StoredFile> storedFileList) {
		if (userIds == null || userIds.size() <= 0) {
			return;
		}
		try {
			PlatformMessage pme = platformMessageDao
					.addPlatformMesssgeToInbox(pm);
			for (Long receiverId : userIds) {
				pm.setReceiverId(receiverId);
				pm.setReadState(PlatformMessageType.MESSAGE_UNREAD_STATUS);
				platformMessageDao.addkUserHasPlatformMessage(pm);
			}
			if (storedFileList != null && storedFileList.size() > 0
					&& pme != null) {
				List<StoredFile> fileList = copyFileToReceiver(storedFileList);
				saveOutboxPmAttachFile(pme.getId(), pm.getSender(), fileList,
						false);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("保存层级用户消息出错", "操作失败，请重试", e);
		}
	}

	private void saveUserTypePlatformMessageToInbox(List<Long> receiverIds,
			PlatformMessage pm, List<StoredFile> storedFileList) {
		try {
			PlatformMessage pmsg = platformMessageDao
					.addPlatformMesssgeToInbox(pm);
			for (Long receiverId : receiverIds) {
				pm.setReadState(PlatformMessageType.MESSAGE_UNREAD_STATUS);
				pm.setReceiverId(receiverId);
				platformMessageDao.addkUserHasPlatformMessage(pm);
			}
			if (storedFileList != null && storedFileList.size() > 0
					&& pmsg != null) {
				List<StoredFile> fileList = copyFileToReceiver(storedFileList);
				saveOutboxPmAttachFile(pmsg.getId(), pm.getSender(), fileList,
						false);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("保存用户消息出错", "操作失败，请重试", e);
		}
	}

	private PlatformMessage savePlatforMessageToOutbox(PlatformMessage pm,
			List<StoredFile> storedFileList) {
		if (pm == null) {
			throw new BusinessValidationException("消息保存出错，请重试");
		}
		try {
			pm = platformMessageDao.addPlatformMesssgeToOutbox(pm);

			if (storedFileList != null && storedFileList.size() > 0) {
				saveOutboxPmAttachFile(pm.getId(), pm.getSender(),
						storedFileList, true);
			}
			return pm;
		} catch (Exception e) {
			throw new ServiceValidationException("保存平台消息出错", "操作失败，请重试", e);
		}
	}

	/** 参数验证 */
	private void validateParams(List<Long> receiverIds, String receiverType,
			List<StoredFile> storedFileList) {
		if ((receiverIds == null || receiverIds.size() < 1)) {
			throw new BusinessValidationException("收件人不能为空");
		}
		if (!StringUtil.isStringAvaliable(receiverType)) {
			throw new BusinessValidationException("收件人类型不能为空");
		}
	}

	/** 校验附件大小 **/
	private void validateAttachFileSize(List<StoredFile> storedFileList) {
		long result = 0;
		if (storedFileList != null && storedFileList.size() > 0) {
			for (StoredFile file : storedFileList) {
				result = result + file.getFileSize();
			}
		}
		if (result > GridProperties.MAX_MAIL_ATTACH_FILE_SIZE) {
			throw new BusinessValidationException(
					"总附件不能超过"
							+ (GridProperties.MAX_MAIL_ATTACH_FILE_SIZE / (1024 * 1024))
							+ "M");
		}
	}

	/** set相关的属性 */
	private void fillPlatformMessageProperty(PlatformMessage pm) {
		if (!StringUtil.isStringAvaliable(pm.getTitle())) {
			pm.setTitle("无主题消息");
		}
		if (PlatformMessageSendType.SYSTERM_SNED.equals(pm.getSendType())) {
			pm.setSender(permissionService.findUserByUserName("admin"));
		} else {
			pm.setSender(getCurrentUser());
			if (pm.getOriginatorsName() == null
					|| pm.getOriginatorsName().trim().length() == 0) {
				pm.setOriginatorsName(getCurrentUser().getName());
			}
		}
		pm.setSendDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		if (pm.getOriginatorsDate() == null) {
			pm.setOriginatorsDate(CalendarUtil.now("yyyy-MM-dd HH:mm:SS"));
		}
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(pm
				.getTitle());
		pm.setFullPinyin((String) pinyin.get("fullPinyin"));
		pm.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	/** 构造收件人名称 */
	public String createReceiverNames(List<Long> receiverIds,
			String receiverType) {
		try {
			StringBuilder sb = new StringBuilder();
			if (ReceiverType.USER.equals(receiverType)) {
				for (Long id : receiverIds) {
					User user = permissionService.getSimpleUserById(id);
					sb.append(user.getName()).append(" ");
				}
			} else if (ReceiverType.ORG.equals(receiverType)) {
				for (Long id : receiverIds) {
					if (id < 0) {
						Organization cmsOrganization = IssueToCMSUtil
								.getLocationOrgNameByLocationId(id);
						sb.append(cmsOrganization.getOrgName());
					} else {
						sb.append(
								organizationDubboService.getSimpleOrgById(id)
										.getOrgName()).append(" ");
					}
				}
			}
			return sb.toString();
		} catch (Exception e) {
			throw new ServiceValidationException("构造收件人名称出错", "操作失败，请重试", e);
		}
	}

	/** 保存附件 */
	private void saveOutboxPmAttachFile(Long pmId, User user,
			List<StoredFile> storedFileList, boolean isSendAttachFile) {
		try {
			for (StoredFile file : storedFileList) {
				PlatformMessageAttachFile pmAttachFile = createPlatformMessageAttachFile(
						file, pmId, user, isSendAttachFile);
				platformMessagesAttachFileDao
						.addPlatformMessageAttachFile(pmAttachFile);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("消息保存附件出错:", "操作失败，请重试", e);
		}
	}

	private PlatformMessageAttachFile createPlatformMessageAttachFile(
			StoredFile file, Long pmId, User user, boolean isSendAttachFile) {
		PlatformMessageAttachFile result = new PlatformMessageAttachFile();
		result.setFileName(file.getStoredTruthFileName());
		result.setFileSize(file.getFileSize());
		result.setFileActualUrl(file.getStoredFilePath() + File.separator
				+ file.getStoredFileName());
		result.setPmId(pmId);
		result.setUser(user);
		result.setCreateDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		result.setCreateUser(user.getName());
		if (isSendAttachFile) {
			result.setAttachType(PlatformMessageAttachFile.SEND_ATTACH_FILE);
		} else {
			result.setAttachType(PlatformMessageAttachFile.RECEIVE_ATTACH_FILE);
		}
		return result;
	}

	/** 为每一个收件人复制一份附件 */
	private List<StoredFile> copyFileToReceiver(List<StoredFile> storedFileList) {
		List<StoredFile> files = new ArrayList<StoredFile>();
		for (StoredFile source : storedFileList) {
			try {
				files.add(FileUtil.fileCopyTo(source,
						GridProperties.MAIL_ATTACHFILE_PATH));
			} catch (Exception e) {
				throw new BusinessValidationException("操作失败，请重试");
			}
		}
		return files;
	}

	/** 发件箱消息列表 */
	@Override
	public PageInfo<PlatformMessage> findPlatformMessageFromOutboxBySenderId(
			Long senderId, Integer isDraft, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		try {
			if (senderId == null) {
				throw new BusinessValidationException("参数不正确");
			}
			PageInfo<PlatformMessage> pageInfo = platformMessageDao
					.findPlatformMessageFromOutboxBySenderId(senderId, isDraft,
							pageNum, pageSize, sidx, sord);
			setPlatformMessageExpiryDate(pageInfo.getResult());
			setPlatformMessageSender(pageInfo.getResult());
			setPlatformMessageAttachFiles(pageInfo.getResult(),
					PlatformMessageAttachFile.SEND_ATTACH_FILE);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("发件箱消息列表查询出错:", "操作失败，请重试", e);
		}
	}

	private void setPlatformMessageAttachFiles(List<PlatformMessage> result,
			int attachFileType) {
		try {
			for (PlatformMessage pm : result) {
				List<PlatformMessageAttachFile> pmAttachFiles = platformMessagesAttachFileDao
						.getPlatformMessageAttachFileByPmIdAndType(pm.getId(),
								attachFileType);
				pm.setPmAttachFiles(pmAttachFiles);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("给消息设置附件路径出错:", "操作失败，请重试", e);
		}

	}

	/** 设置消息有效期 */
	private void setPlatformMessageExpiryDate(List<PlatformMessage> pmList) {
		for (PlatformMessage pm : pmList) {
			pm.setExpiryDate(90 - diffDate(pm.getSendDate(), new Date()));
		}
	}

	private static int diffDate(Date startDate, Date endDate) {
		int result = 0;
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		result = (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
		return result;
	}

	/** 根据ID获取发件箱消息 */
	@Override
	public PlatformMessage getOutboxPlatformMessageById(Long id) {
		if (id == null || id == 0) {
			throw new BusinessValidationException("参数不正确");
		}
		try {
			PlatformMessage pm = platformMessageDao
					.getPlatformMesssgeFromOutboxById(id);
			if (pm == null) {
				return null;
			}
			List<PlatformMessageAttachFile> pmAttachFiles = platformMessagesAttachFileDao
					.getPlatformMessageAttachFileByPmIdAndType(pm.getId(),
							PlatformMessageAttachFile.SEND_ATTACH_FILE);
			pm.setPmAttachFiles(pmAttachFiles);
			pm.setExpiryDate(90 - diffDate(pm.getSendDate(), new Date()));
			User sender = permissionService.getSimpleUserById(pm.getSender()
					.getId());
			sender.setOrganization(organizationDubboService
					.getSimpleOrgById(sender.getOrganization().getId()));
			pm.setSender(sender);
			return pm;
		} catch (Exception e) {
			throw new ServiceValidationException("根据ID获取发件箱消息出错:", "操作失败，请重试",
					e);
		}
	}

	/** 删除发件箱消息和附件 */
	@Override
	public void deletePlatformMessageFromOutboxById(Long id) {
		if (id == null || id == 0) {
			throw new BusinessValidationException("参数不正确");
		}
		PlatformMessage pm = platformMessageDao
				.getPlatformMesssgeFromOutboxById(id);

		try {
			if (pm != null
					&& PlatformMessageType.IS_NOT_DRAFT_MESSAGE.equals(pm
							.getIsDraft())) {// 如果草稿箱消息，则不删除附件
				List<PlatformMessageAttachFile> pmAttachFiles = platformMessagesAttachFileDao
						.getPlatformMessageAttachFileByPmIdAndType(id,
								PlatformMessageAttachFile.SEND_ATTACH_FILE);
				if (pmAttachFiles != null && !pmAttachFiles.isEmpty()) {
					String webRootPath = FileUtil.getWebRoot();
					for (PlatformMessageAttachFile pmAttachFile : pmAttachFiles) {
						platformMessagesAttachFileDao
								.deletePlatformMessagesAttachFileById(pmAttachFile
										.getId());
						File file = new File(webRootPath + File.separator
								+ pmAttachFile.getFileActualUrl());
						if (file.exists()) {
							file.delete();
						}
					}
				}
			}
			platformMessageDao.deletePlatformMessageFromOutboxById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("删除发件箱消息和附件出错:", "操作失败，请重试", e);
		}
	}

	/** 发件箱附件下载 */
	@Override
	public PlatformMessageAttachFile getPlatformMessageAttachFileById(Long id) {
		try {
			return platformMessagesAttachFileDao
					.getPlatformMessageAttachFileById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("发件箱附件下载出错:", "操作失败，请重试", e);
		}
	}

	/** 收件箱列表 */
	@Override
	public PageInfo<PlatformMessage> findPlatformMessageFromInboxByUserId(
			Long userId, Integer isAdmin, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			List<Integer> platformMessageTypes = userHasPlatformMessageTypesDubboService
					.findUserHasPlatformMessageTypeByUserId(userId);
			PageInfo<PlatformMessage> pageInfo = platformMessageDao
					.findPlatformMessageFromInbox(userId, isAdmin, page, rows,
							sidx, sord, platformMessageTypes);
			List<PlatformMessage> pmList = pageInfo.getResult();
			if (pmList != null && pmList.size() > 0) {
				setPlatformMessageSender(pmList);
				setPlatformMessageExpiryDate(pmList);
				setPlatformMessageReadState(pmList);
				setPlatformMessageAttachFiles(pmList,
						PlatformMessageAttachFile.RECEIVE_ATTACH_FILE);
				pageInfo.setResult(pmList);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("收件箱列表查询出错:", "操作失败，请重试", e);
		}
	}

	private Long[] getUserRoleIds(Long userId) {
		try {
			List<Role> roleList = permissionService.findRolesByUserId(userId);
			Long[] roleIds = new Long[roleList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				roleIds[i] = roleList.get(i).getId();
			}
			return roleIds;
		} catch (Exception e) {
			throw new ServiceValidationException("获取用户岗位出错:", "操作失败，请重试", e);
		}
	}

	@Override
	public PageInfo<PlatformMessage> findUnreadPlatformMessageFromInboxByUserId(
			Long userId, Integer page, Integer rows, String sidx, String sord) {
		try {
			Long orgId = permissionService.getSimpleUserById(userId)
					.getOrganization().getId();
			List<Integer> platformMessageTypes = userHasPlatformMessageTypesDubboService
					.findUserHasPlatformMessageTypeByUserId(userId);
			PageInfo<PlatformMessage> pageInfo = platformMessageDao
					.findUnreadPlatformMessageFromInbox(userId, orgId, page,
							rows, sidx, sord, platformMessageTypes);
			List<PlatformMessage> pmList = pageInfo.getResult();
			if (pmList != null && pmList.size() > 0) {
				setPlatformMessageSender(pmList);
				setPlatformMessageExpiryDate(pmList);
				pageInfo.setResult(pmList);
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询消息出错:", "操作失败，请重试", e);
		}
	}

	/** 设置收件人类型为部门 和岗位的消息的阅读状态 */
	private void setPlatformMessageReadState(List<PlatformMessage> pmList) {
		try {
			for (PlatformMessage pm : pmList) {
				Integer readState = platformMessageDao
						.getUserHasPlatformMessageReadState(pm.getId(),
								ThreadVariable.getUser().getId());
				pm.setReadState(readState);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("设置收件人类型为部门和岗位的消息阅读状态出错:",
					"操作失败，请重试", e);
		}

	}

	/** 设置完整的发件人信息 */
	private void setPlatformMessageSender(List<PlatformMessage> result) {
		try {
			for (PlatformMessage pm : result) {
				if (pm.getSender() != null && pm.getSender().getId() != null) {
					pm.setSender(permissionService.getSimpleUserById(pm
							.getSender().getId()));
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("设置完整发件人消息出错:", "操作失败，请重试", e);
		}
	}

	/** 根据ID获取收件箱消息 */
	@Override
	public PlatformMessage getInboxPlatformMessageById(Long id) {
		try {
			if (id == null || id == 0) {
				throw new BusinessValidationException("参数不正确");
			}
			PlatformMessage pm = platformMessageDao
					.getPlatformMesssgeFromInboxById(id);
			if (pm == null) {
				return null;
			}
			List<PlatformMessageAttachFile> pmAttachFiles = platformMessagesAttachFileDao
					.getPlatformMessageAttachFileByPmIdAndType(pm.getId(),
							PlatformMessageAttachFile.RECEIVE_ATTACH_FILE);
			pm.setPmAttachFiles(pmAttachFiles);
			User user = permissionService.getSimpleUserById(pm.getSender()
					.getId());
			if (null != user) {
				user.setOrganization(organizationDubboService
						.getSimpleOrgById(user.getOrganization().getId()));
				pm.setSender(user);
			}
			pm.setExpiryDate(90 - diffDate(pm.getSendDate(), new Date()));
			Integer readeState = platformMessageDao
					.getUserHasPlatformMessageReadState(pm.getId(),
							ThreadVariable.getUser().getId());
			pm.setReadState(readeState);
			return pm;
		} catch (Exception e) {
			throw new ServiceValidationException("根据ID获取消息出错:", "操作失败，请重试", e);
		}
	}

	/** 标记收件箱消息为已读 */
	@Override
	public void markInboxPlatgformMessageRead(PlatformMessage pm) {
		if (pm == null) {
			throw new BusinessValidationException("消息标记出错，请重试");
		}
		try {
			pm.setReadDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));

			pm.setReceiverId(ThreadVariable.getUser().getId());
			platformMessageDao.markUserInboxPlatgformMessageRead(pm);

		} catch (Exception e) {
			throw new ServiceValidationException("收件箱消息标记已读出错:", "操作失败，请重试", e);
		}

	}

	/** 删除收件箱消息和附件 */
	@Override
	public void deleteInboxPlatformMessage(PlatformMessage pm) {
		if (pm == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}

		try {
			Long id = platformMessageDao.getUserHasPlatformMessageId(
					pm.getId(), ThreadVariable.getUser().getId());
			if (id != null) {
				platformMessageDao.markUserHasPlatformMessageDelete(pm.getId(),
						ThreadVariable.getUser().getId());
			}

		} catch (Exception e) {
			throw new ServiceValidationException("删除收件箱消息出错:", "操作失败，请重试", e);
		}

	}

	/** 获取用户未读消息 */
	@Override
	public int getUserUnreadPlatformMessageFromInbox(Long userId) {
		try {
			User user = permissionService.getSimpleUserById(userId);
			List<Integer> platformMessageTypes = userHasPlatformMessageTypesDubboService
					.findUserHasPlatformMessageTypeByUserId(userId);
			return platformMessageDao.getUserUnreadPlatformMessageFromInbox(
					user, platformMessageTypes);
		} catch (Exception e) {
			throw new ServiceValidationException("查询用户未读取信息出错:", "操作失败，请重试", e);
		}
	}

	@Override
	public List<Role> getRolesByOrgLevelAndRoleScope(Long orgLevel,
			Integer roleScope) {
		try {
			if (RoleScope.ONLY_SELF.equals(roleScope)) {
				return permissionService
						.findRolesByUserInLevelNoAdmin(orgLevel);
			}
			if (RoleScope.DIRECTLY_CHILD.equals(roleScope)) {
				return permissionService
						.findDirectlyChildRolesByUseInLevelNoAdmin(orgLevel);
			}
			if (RoleScope.ALL_CHILD.equals(roleScope)) {
				return permissionService
						.findAllChildRolesByUseInLevelNoAdmin(orgLevel);
			}

			return null;
		} catch (Exception e) {
			throw new ServiceValidationException("获取岗位信息出错:", "操作失败，请重试", e);
		}
	}

	private User getCurrentUser() {
		try {
			return permissionService.getSimpleUserById(ThreadVariable
					.getSession().getUserId());
		} catch (Exception e) {
			throw new ServiceValidationException("查询当前用户的信息出错:", "操作失败，请重试", e);
		}
	}

	@Override
	public void sendPlatformMessageToUser(Long userId, String messageContent,
			String messageTitle) {
		try {
			List<Long> receiverIds = new ArrayList<Long>();
			receiverIds.add(userId);
			PlatformMessage pm = new PlatformMessage(messageTitle,
					messageContent, PlatformMessageSendType.SYSTERM_SNED);
			sendPlatformMessage(receiverIds, ReceiverType.USER, pm, null, null);
		} catch (Exception e) {
			throw new ServiceValidationException("消息发送给用户出错:", "操作失败，请重试", e);
		}
	}

	@Override
	public void sendPlatformMessageToOrg(Long orgId, String messageContent,
			String messageTitle) {
		try {
			List<Long> receiverIds = new ArrayList<Long>();
			receiverIds.add(orgId);
			PlatformMessage pm = new PlatformMessage(messageTitle,
					messageContent, PlatformMessageSendType.SYSTERM_SNED);
			sendPlatformMessage(receiverIds, ReceiverType.ORG, pm, null, null);
		} catch (Exception e) {
			throw new ServiceValidationException("消息发送给层级出错:", "操作失败，请重试", e);
		}
	}

	@Override
	public void cleanInteractCommunicationMessage() {
		Map<String, Date> allTime = getMaxTimeByEarlyWarning();
		try {
			// 删除收件箱关联表数据
			platformMessageDao.cleanUserHasPlatformmessages(allTime);
			// 删除收件箱
			platformMessageDao.cleanInBoxplatformMessage(allTime);
			// 删除发件箱（不包含草稿）
			platformMessageDao.cleanOutBoxplatformMessage(allTime);
		} catch (Exception e) {
			throw new ServiceValidationException("job删除过期消息错误", "job删除过期消息错误",
					e);

		}

	}

	private static final String INDIVIDUAL_TIME = "platformMessageIndividual";
	private static final String DEPARTMENT_TIME = "platformMessageDepartment";

	private Map<String, Date> getMaxTimeByEarlyWarning() {
		/** 个人的 */
		EarlyWarning individual = earlyWarningService
				.getEarlyWarningByName(INDIVIDUAL_TIME);
		/** 部门的 */
		EarlyWarning department = earlyWarningService
				.getEarlyWarningByName(DEPARTMENT_TIME);
		if (department == null || department == null
				|| department.getRemindTime() == null
				|| department.getRemindTime() == null) {
			throw new BusinessValidationException("获取时间错误");
		}
		Date individualTime = JobHelper.getMaxTime(individual.getRemindTime()
				.intValue());
		Date departmentTime = JobHelper.getMaxTime(department.getRemindTime()
				.intValue());
		Map<String, Date> map = new HashMap<String, Date>();
		map.put(ONE_MONTH, individualTime);
		map.put(TWO_MONTH, departmentTime);

		return map;
	}

	/** 当前时间的上个月0：0：0 */
	private static final String ONE_MONTH = "oneMonth";
	/** 当前时间的两个月前0：0：0 */
	private static final String TWO_MONTH = "twoMonth";
}
