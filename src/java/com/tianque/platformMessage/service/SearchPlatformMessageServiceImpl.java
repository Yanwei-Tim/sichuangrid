package com.tianque.platformMessage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.platformMessage.dao.PlatformMessageDao;
import com.tianque.platformMessage.dao.PlatformMessagesAttachFileDao;
import com.tianque.platformMessage.dao.SearchPlatformMessageDao;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;
import com.tianque.platformMessage.domain.SearchPlatformMessageVo;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.UserHasPlatformMessageTypesDubboService;

@Service("searchPlatformMessageService")
@Transactional
public class SearchPlatformMessageServiceImpl implements
		SearchPlatformMessageService {
	@Autowired
	private SearchPlatformMessageDao searchPlatformMessageDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PlatformMessagesAttachFileDao platformMessagesAttachFileDao;
	@Autowired
	private PlatformMessageDao platformMessageDao;
	@Autowired
	private UserHasPlatformMessageTypesDubboService userHasPlatformMessageTypesDubboService;

	@Override
	public PageInfo<PlatformMessage> searchOutboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer page,
			Integer rows, String sidx, String sord) {
		PageInfo<PlatformMessage> pageInfo = searchPlatformMessageDao
				.searchOutboxPlatformMessage(searchPlatformMessageVo, page,
						rows, sidx, sord);
		List<PlatformMessage> pmList = pageInfo.getResult();
		if (pmList != null && !pmList.isEmpty()) {
			setPlatformMessageExpiryDate(pmList);
			setPlatformMessageSender(pmList);
			setPlatformMessageAttachFiles(pmList,
					PlatformMessageAttachFile.SEND_ATTACH_FILE);
			pageInfo.setResult(pmList);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<PlatformMessage> searchInboxPlatformMessage(
			SearchPlatformMessageVo searchPlatformMessageVo, Integer page,
			Integer rows, String sidx, String sord) {
		List<Integer> platformMessageTypes = userHasPlatformMessageTypesDubboService
				.findUserHasPlatformMessageTypeByUserId(searchPlatformMessageVo
						.getUserId());
		searchPlatformMessageVo.setPlatformMessageTypes(platformMessageTypes);
		PageInfo<PlatformMessage> pageInfo = searchPlatformMessageDao
				.searchInboxPlatformMessage(searchPlatformMessageVo, page,
						rows, sidx, sord);
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
	}

	/** 设置消息有效期 */
	private void setPlatformMessageExpiryDate(List<PlatformMessage> pmList) {
		for (PlatformMessage pm : pmList) {
			pm.setExpiryDate(90 - diffDate(pm.getSendDate(), new Date()));
		}
	}

	/** 设置完整的发件人信息 */
	private void setPlatformMessageSender(List<PlatformMessage> result) {
		for (PlatformMessage pm : result) {
			if (pm.getSender() != null && pm.getSender().getId() != null) {
				pm.setSender(permissionService.getSimpleUserById(pm.getSender()
						.getId()));
			}
		}
	}

	private static int diffDate(Date startDate, Date endDate) {
		int result = 0;
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		result = (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
		return result;
	}

	/** 设置消息附件 */
	private void setPlatformMessageAttachFiles(List<PlatformMessage> result,
			int attachFileType) {
		for (PlatformMessage pm : result) {
			List<PlatformMessageAttachFile> pmAttachFiles = platformMessagesAttachFileDao
					.getPlatformMessageAttachFileByPmIdAndType(pm.getId(),
							attachFileType);
			pm.setPmAttachFiles(pmAttachFiles);
		}

	}

	/** 设置收件人类型为部门的消息的阅读状态 */
	private void setPlatformMessageReadState(List<PlatformMessage> pmList) {
		for (PlatformMessage pm : pmList) {
			Integer readState = platformMessageDao
					.getUserHasPlatformMessageReadState(pm.getId(),
							ThreadVariable.getUser().getId());
			pm.setReadState(readState);
		}

	}

}
