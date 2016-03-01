package com.tianque.resourcePool.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ThreadPool;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.resourcePool.dao.UserHasMyProfilePermissionDao;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.UserHasMyProfilePermission;
import com.tianque.service.impl.LogableService;

@Service("userHasMyProfilePermissionService")
public class UserHasMyProfilePermissionServiceImpl extends LogableService
		implements UserHasMyProfilePermissionService {

	private static final Log logger = LogFactory
			.getLog(UserHasMyProfilePermissionServiceImpl.class);

	@Autowired
	private UserHasMyProfilePermissionDao userHasMyProfilePermissionDao;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private DirectorySettingService directorySettingService;

	@Override
	public void addUserMyProfilePermission(
			List<UserHasMyProfilePermission> domains, List<Long> orgs,
			List<Long> list, Long sendMessage, List<MyProfile> files) {

		List<String> shareDirectoryIds = new ArrayList<String>();
		for (MyProfile myProfile : files) {
			shareDirectoryIds.add(String.valueOf(myProfile.getShareDirectory()
					.getId()));
		}
		List<DirectorySetting> directorySettings = directorySettingService
				.findDirectorySettingByIdArray(shareDirectoryIds);
		for (int i = 0, len = files.size(); i < len; i++) {
			DirectorySetting ds = null;
			try {
				ds = directorySettings.get(i);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			files.get(i).setShareDirectory(ds);
		}
		if (list != null) {
			for (UserHasMyProfilePermission domain : domains) {
				for (Long orgid : orgs) {
					Organization org = new Organization();
					org.setId(orgid);
					domain.setOrganization(org);
					userHasMyProfilePermissionDao
							.addUserMyProfilePermission(domain);
				}
			}
			// userHasMyProfilePermissionDao.batchAddUserMyProfilePermission(domains);
		}

		if (MyProfile.SENDMESSAGE_YES.equals(sendMessage)) {
			PlatformMessage pm = platformMessageFactory
					.createSharInfomationPlatformMessage(files);
			SendPlatformaMsg sendMsgThread = new SendPlatformaMsg(pm, orgs,
					ThreadVariable.getSession());
			ThreadPool.getInstance().execute(sendMsgThread);
			// platformaMessageService.sendPlatformMessageToOrg(orgs, pm);
		}
	}

	@Override
	public void deleteMyProfilePermissionByMyProfileId(Long myProfileId) {
		userHasMyProfilePermissionDao
				.deleteMyProfilePermissionByMyProfileId(myProfileId);
	}

	@Override
	public void deleteMyProfilePermissionByMyProfileIds(String[] myProfileIds) {
		if (myProfileIds == null || myProfileIds.length < 0) {
			throw new BusinessValidationException("参数错误");
		}
		userHasMyProfilePermissionDao
				.deleteMyProfilePermissionByMyProfileIds(myProfileIds);
	}

	class SendPlatformaMsg extends Thread {
		private List<Long> orgs;
		private PlatformMessage msg;
		private Session session;

		public SendPlatformaMsg(PlatformMessage msg, List<Long> orgs,
				Session session) {
			this.orgs = orgs;
			this.msg = msg;
			this.session = session;
		}

		@Override
		public void run() {
			createThreadUser();
			platformaMessageService.sendPlatformMessageToOrg(orgs, msg);
		}

		private void createThreadUser() {
			ThreadVariable.setSession(session);
			User user = new User();
			user.setId(session.getUserId());
			user.setOrganization(session.getOrganization());
			ThreadVariable.setUser(user);
		}

	}

}
