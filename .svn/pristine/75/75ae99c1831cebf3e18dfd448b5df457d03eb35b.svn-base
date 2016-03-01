package com.tianque.upgradeContent.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.upgradeContent.constants.CheckState;
import com.tianque.upgradeContent.dao.UserCheckUpgradeContentDAO;
import com.tianque.upgradeContent.domain.UpgradeContent;
import com.tianque.upgradeContent.domain.UserCheckUpgradeContent;
import com.tianque.upgradeContent.service.UpgradeContentService;
import com.tianque.upgradeContent.service.UserCheckUpgradeContentService;

@Service("userCheckUpgradeContentService")
@Transactional
public class UserCheckUpgradeContentServiceImpl implements
		UserCheckUpgradeContentService {

	@Autowired
	private UserCheckUpgradeContentDAO userCheckUpgradeContentDao;

	@Autowired
	private UpgradeContentService upgradeContentService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public UserCheckUpgradeContent getUserCheckUpgradeContentByUserIdAndUpgradeContentId(
			Long userId) {
		// 得到最新的升级内容
		UpgradeContent upgradeContent = upgradeContentService
				.getTheLatestUpgradeContent();
		if (upgradeContent == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("upgradeContentId", upgradeContent.getId());
		UserCheckUpgradeContent userCheckUpgradeContent = userCheckUpgradeContentDao
				.getUserCheckUpgradeContentByUserIdAndUpgradeContentId(map);
		userCheckUpgradeContent = checkUserCheckUpgradeContent(
				userCheckUpgradeContent, userId, upgradeContent.getId());
		if (userCheckUpgradeContent != null) {
			userCheckUpgradeContent.setUpgradeContent(upgradeContent);
		}
		return userCheckUpgradeContent;
	}

	@Override
	public UserCheckUpgradeContent addUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userCheckUpgradeContent.getUser().getId());
		map.put("upgradeContentId", userCheckUpgradeContent.getUpgradeContent()
				.getId());
		Long id = userCheckUpgradeContentDao
				.addUserCheckUpgradeContent(userCheckUpgradeContent);
		if (id == null) {
			return null;
		}
		return getUserCheckUpgradeContentById(id);
	}

	@Override
	public void updateUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent) {
		userCheckUpgradeContent.setCheckDate(CalendarUtil.now());
		userCheckUpgradeContentDao
				.updateUserCheckUpgradeContent(userCheckUpgradeContent);
	}

	@Override
	public void deleteUserCheckUpgradeContent(Long id) {
		userCheckUpgradeContentDao.deleteUserCheckUpgradeContent(id);
	}

	@Override
	public UserCheckUpgradeContent getUserCheckUpgradeContentById(Long id) {
		return userCheckUpgradeContentDao.getUserCheckUpgradeContentById(id);
	}

	// 将查询出来的结果进行判断
	private UserCheckUpgradeContent checkUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent, Long userId,
			Long upgradeContentId) {
		if (userCheckUpgradeContent == null) {
			// 未查看
			userCheckUpgradeContent = new UserCheckUpgradeContent();
			User user = permissionService.getSimpleUserById(userId);
			UpgradeContent content = upgradeContentService
					.getUpgradeContentById(upgradeContentId);
			userCheckUpgradeContent.setUser(user);
			userCheckUpgradeContent.setUpgradeContent(content);
			userCheckUpgradeContent.setCheckDate(CalendarUtil.now());
			userCheckUpgradeContent.setCheckState(CheckState.DIDNOTSEE);
			userCheckUpgradeContent = addUserCheckUpgradeContent(userCheckUpgradeContent);
			return userCheckUpgradeContent;
		}
//		if (CheckState.WEEKNOTSEE.equals(userCheckUpgradeContent
//				.getCheckState())
//				&& CalendarUtil.daysBetween(
//						userCheckUpgradeContent.getCheckDate(),
//						CalendarUtil.now()) < 7) {
//			// 七天不查看
//			return null;
//		} else if (CheckState.LONGERSEE.equals(userCheckUpgradeContent
//				.getCheckState())) {
//			// 不再查看
//			return null;
//		} else {
//			return userCheckUpgradeContent;
//		}
		return userCheckUpgradeContent;
	}

	@Override
	public UserCheckUpgradeContent getUserCheckUpgradeContentByUserIdAndUpgradeContentId(
			Map<String, Object> map) {
		UserCheckUpgradeContent userCheckUpgradeContent = userCheckUpgradeContentDao
				.getUserCheckUpgradeContentByUserIdAndUpgradeContentId(map);
		return userCheckUpgradeContent;
	}

	@Override
	public void deleteUserCheckUpgradeContentByUpgradeContentId(
			Long upgradeContentId) {
		userCheckUpgradeContentDao
				.deleteUserCheckUpgradeContentByUpgradeContentId(upgradeContentId);
	}
}
