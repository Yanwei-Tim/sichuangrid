package com.tianque.platformMessage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.platformMessage.domain.PlatformMessage;

@Repository("platformMessageDao")
public class PlatformMessageDaoImpl extends AbstractBaseDao implements
		PlatformMessageDao {

	@Override
	public PageInfo<PlatformMessage> findPlatformMessageFromOutboxBySenderId(
			Long senderId, Integer isDraft, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (senderId == null || senderId == 0L) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("senderId", senderId);
		map.put("isDraft", isDraft);
		map.put("sortField", sidx);
		map.put("order", sord);

		Integer pmcount = (Integer) getSqlMapClientTemplate().queryForObject(
				"platformMessage.countOutboxPlatformMessageBySenderId", map);
		Integer pageCount = 0;
		if (pageSize != 0 && pmcount > 0) {
			pageCount = (pmcount - 1) / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<PlatformMessage> list = this.getSqlMapClientTemplate()
				.queryForList(
						"platformMessage.findOutboxPlatformMessageBySenderId",
						map, (pageNum - 1) * pageSize, pageSize);

		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(pmcount);
		pageInfo.setCurrentPage(pageNum);

		return pageInfo;
	}

	@Override
	public PageInfo<PlatformMessage> findPlatformMessageFromInbox(Long userId,
			Integer isAdmin, Integer pageNum, Integer pageSize, String sidx,
			String sord, List<Integer> platformMessageTypes) {
		if (userId == null || platformMessageTypes == null
				|| platformMessageTypes.size() < 1) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isAdmin", isAdmin);
		map.put("platformMessageTypes", platformMessageTypes);

		Integer userCount = (Integer) getSqlMapClientTemplate().queryForObject(
				"platformMessageNew.countUserInboxPlatformMessage", map);

		List<PlatformMessage> list = new ArrayList<PlatformMessage>();

		map.put("startRow", (pageNum - 1) * pageSize);
		map.put("endRow", pageNum * pageSize);
		list = getSqlMapClientTemplate().queryForList(
				"platformMessageNew.findUserInboxPlatformMessage", map);
		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(userCount);
		pageInfo.setCurrentPage(pageNum);
		return pageInfo;
	}

	@Override
	public PageInfo<PlatformMessage> findUnreadPlatformMessageFromInbox(
			Long userId, Long orgId, Integer pageNum, Integer pageSize,
			String sidx, String sord, List<Integer> platformMessageTypes) {
		if (userId == null || platformMessageTypes == null
				|| platformMessageTypes.size() < 1) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("orgId", orgId);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("platformMessageTypes", platformMessageTypes);

		Integer pmcount = (Integer) getSqlMapClientTemplate().queryForObject(
				"platformMessageNew.getUserUnreadUserPlatformMessageFromInbox",
				map);

		Integer pageCount = 0;
		if (pageSize != 0 && pmcount > 0) {
			pageCount = (pmcount - 1) / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<PlatformMessage> list = this.getSqlMapClientTemplate()
				.queryForList(
						"platformMessageNew.findUnreadInboxPlatformMessage",
						map, (pageNum - 1) * pageSize, pageSize);
		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setResult(list);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(pmcount);
		pageInfo.setCurrentPage(pageNum);
		return pageInfo;
	}

	private PageInfo<PlatformMessage> emptyPage(int pageSize) {
		PageInfo<PlatformMessage> pageInfo = new PageInfo<PlatformMessage>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PlatformMessage>());
		return pageInfo;
	}

	@Override
	public PlatformMessage addPlatformMesssgeToOutbox(PlatformMessage pm) {
		checkOutboxPm(pm);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"platformMessage.addPlatformMesssgeToOutbox", pm);
		return getPlatformMesssgeFromOutboxById(id);
	}

	private void checkOutboxPm(PlatformMessage pm) {
		if (pm == null) {
			throw new BusinessValidationException("参数错误！");
		}
		if (!StringUtil.isStringAvaliable(pm.getReceiverNames())) {
			throw new BusinessValidationException("收件人不能为空！");
		}
	}

	@Override
	public PlatformMessage addPlatformMesssgeToInbox(PlatformMessage pm) {
		if (pm == null) {
			throw new BusinessValidationException("参数错误！");
		}
		if (pm.getSender() == null || pm.getSender().getId() == null) {
			throw new BusinessValidationException("发件人不能为空！");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"platformMessage.addPlatformMessageToInbox", pm);
		return getPlatformMesssgeFromInboxById(id);
	}

	@Override
	public PlatformMessage getPlatformMesssgeFromOutboxById(Long id) {
		return (PlatformMessage) getSqlMapClientTemplate().queryForObject(
				"platformMessage.getPlatformMesssgeFromOutboxById", id);

	}

	@Override
	public PlatformMessage getPlatformMesssgeFromInboxById(Long id) {
		return (PlatformMessage) getSqlMapClientTemplate().queryForObject(
				"platformMessage.getPlatformMesssgeFromInboxById", id);
	}

	@Override
	public void deletePlatformMessageFromOutboxById(Long pmId) {

		this.getSqlMapClientTemplate().delete(
				"platformMessage.deletePlatformMessageFromOutboxById", pmId);
	}

	@Override
	public int getUserUnreadPlatformMessageFromInbox(User user, List<Integer> platformMessageTypes) {
		if(platformMessageTypes == null || platformMessageTypes.size() < 1){
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		map.put("platformMessageTypes", platformMessageTypes);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"platformMessageNew.getUserUnreadUserPlatformMessageFromInbox",
				map);

		return count;
	}

	@Override
	public void markUserInboxPlatgformMessageRead(PlatformMessage pm) {
		getSqlMapClientTemplate().update(
				"platformMessageNew.markUserInboxPlatgformMessageRead", pm);

	}

	@Override
	public void addkUserHasPlatformMessage(PlatformMessage pm) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmId", pm.getId());
		map.put("userId", pm.getReceiverId());
		map.put("userId", pm.getReceiverId());
		map.put("readState", pm.getReadState());
		map.put("readDate", pm.getReadDate());
		map.put("deleteState", pm.getDeleteState());
		map.put("messageType", pm.getMessageType());
		map.put("senderId", pm.getSender().getId());
		map.put("createDate", pm.getCreateDate());
		getSqlMapClientTemplate().insert(
				"platformMessage.addkUserHasPlatformMessage", map);
	}

	@Override
	public Long getUserHasPlatformMessageId(Long pmId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmId", pmId);
		map.put("userId", userId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"platformMessage.getUserHasPlatformMessageId", map);
	}

	@Override
	public void markUserHasPlatformMessageDelete(Long pmId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmId", pmId);
		map.put("userId", userId);
		this.getSqlMapClientTemplate().update(
				"platformMessage.markUserHasPlatformMessageDelete", map);
	}

	@Override
	public void deleteUserPlatformMessageFromInbox(PlatformMessage pm) {
		getSqlMapClientTemplate().delete(
				"platformMessage.deleteUserPlatformMessageFromInbox", pm);

	}

	@Override
	public Integer getUserHasPlatformMessageReadState(Long pmId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmId", pmId);
		map.put("userId", userId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"platformMessage.getUserHasPlatformMessageReadState", map);
	}

	@Override
	public void cleanInBoxplatformMessage(Map<String, Date> allTime) {
		if (allTime != null) {
			getSqlMapClientTemplate().delete(
					"platformMessage.cleanInBoxplatformMessage", allTime);
		}

	}

	@Override
	public void cleanOutBoxplatformMessage(Map<String, Date> allTime) {
		if (allTime != null) {
			getSqlMapClientTemplate().delete(
					"platformMessage.cleanOutBoxplatformMessage", allTime);
		}
	}

	@Override
	public void cleanUserHasPlatformmessages(Map<String, Date> allTime) {
		if (allTime != null) {
			getSqlMapClientTemplate().delete(
					"platformMessage.cleanUserHasPlatformmessages", allTime);
		}
	}
}
