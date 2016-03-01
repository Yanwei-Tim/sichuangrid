package com.tianque.upgradeContent.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.upgradeContent.constants.CheckState;
import com.tianque.upgradeContent.dao.UpgradeContentDAO;
import com.tianque.upgradeContent.domain.UpgradeContent;
import com.tianque.upgradeContent.domain.UserCheckUpgradeContent;
import com.tianque.upgradeContent.domain.VO.UpgradeContentVO;
import com.tianque.upgradeContent.service.UpgradeContentService;
import com.tianque.upgradeContent.service.UserCheckUpgradeContentService;

@Service("upgradeContentService")
@Transactional
public class UpgradeContentServiceImpl implements UpgradeContentService {
	@Autowired
	private UpgradeContentDAO upgradeContentDao;
	@Autowired
	private UserCheckUpgradeContentService userCheckUpgradeContentService;

	@Override
	public PageResult<UpgradeContent> queryUpgradeContenForPageResult(
			UpgradeContentVO upgradeContentVO,
			DefaultSortAndPage defaultSortAndPage) {
		return upgradeContentDao.queryUpgradeContenForPageResult(
				upgradeContentVO, defaultSortAndPage.getPage(),
				defaultSortAndPage.getRows());
	}

	@Override
	public UpgradeContent addUpgradeContent(UpgradeContent upgradeContent) {
		Long id = upgradeContentDao.addUpgradeContent(upgradeContent);
		if (id == null) {
			return null;
		}
		return getUpgradeContentById(id);

	}

	@Override
	public void updateUpgradeContent(UpgradeContent upgradeContent) {
		upgradeContentDao.updateUpgradeContent(upgradeContent);
	}

	@Override
	public void deleteUpgradeContent(List<Long> ids) {
		for (Long id : ids) {
			upgradeContentDao.deleteUpgradeContent(id);
			userCheckUpgradeContentService
					.deleteUserCheckUpgradeContentByUpgradeContentId(id);
		}
	}

	@Override
	public UpgradeContent getUpgradeContentById(Long id) {
		return upgradeContentDao.getUpgradeContentById(id);
	}

	@Override
	public UpgradeContent getTheLatestUpgradeContent() {
		return upgradeContentDao.getTheLatestUpgradeContent(null);
	}

	@Override
	public void updateIsUpgradUpgradeContent(UpgradeContent upgradeContent,
			Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upgradeContentId", upgradeContent.getId());
		map.put("userId", userId);
		UserCheckUpgradeContent userCheckUpgradeContent = userCheckUpgradeContentService
				.getUserCheckUpgradeContentByUserIdAndUpgradeContentId(map);
		if (userCheckUpgradeContent != null) {
			userCheckUpgradeContent.setCheckDate(CalendarUtil.now());
			userCheckUpgradeContent.setCheckState(CheckState.DIDNOTSEE);
			userCheckUpgradeContentService
					.updateUserCheckUpgradeContent(userCheckUpgradeContent);
		}
		updateUpgradeContent(upgradeContent);

	}
}
