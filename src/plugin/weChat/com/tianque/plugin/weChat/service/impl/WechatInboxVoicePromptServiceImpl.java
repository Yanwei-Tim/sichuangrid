package com.tianque.plugin.weChat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.plugin.weChat.dao.WechatInboxVoicePromptDao;
import com.tianque.plugin.weChat.domain.inbox.WechatInboxVoicePrompt;
import com.tianque.plugin.weChat.service.WechatInboxVoicePromptService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/** 微信消息提示语音设置类 */
@Service("wechatInboxVoicePromptService")
@Transactional
public class WechatInboxVoicePromptServiceImpl implements
		WechatInboxVoicePromptService {

	@Autowired
	WechatInboxVoicePromptDao wechatInboxVoicePromptDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public WechatInboxVoicePrompt findWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {

		return wechatInboxVoicePromptDao
				.findWechatInboxVoicePrompt(wechatInboxVoicePrompt);
	}

	@Override
	public void addWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {
		if (wechatInboxVoicePrompt == null
				|| wechatInboxVoicePrompt.getToUserName() == null
				|| wechatInboxVoicePrompt.getVoiceUrl() == null
				|| wechatInboxVoicePrompt.getVoicePromptStatus() == null) {
			throw new ServiceException("添加微信消息语音提示失败,未获取关键参数!");
		}

		Organization org = organizationDubboService
				.getSimpleOrgById(wechatInboxVoicePrompt.getOrg().getId());
		if (org == null) {
			throw new ServiceException("添加微信消息语音提示失败,未获取到组织机构!");
		}
		wechatInboxVoicePrompt.setOrg(org);
		wechatInboxVoicePromptDao
				.addWechatInboxVoicePrompt(wechatInboxVoicePrompt);
	}

	@Override
	public PageInfo<WechatInboxVoicePrompt> findwechatInboxVoicePrompts(
			WechatInboxVoicePrompt wechatInboxVoicePrompt, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("wechatInboxVoicePrompt", wechatInboxVoicePrompt);
		PageInfo<WechatInboxVoicePrompt> pageInfo = wechatInboxVoicePromptDao
				.findWechatInboxVoicePrompts(map, pageNum, pageSize);

		return pageInfo;
	}

	@Override
	public void updateWechatInboxVoicePromptById(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {
		Session session = ThreadVariable.getSession();
		if (wechatInboxVoicePrompt.getId() == null
				|| wechatInboxVoicePrompt.getVoicePromptStatus() == null
				|| wechatInboxVoicePrompt.getVoicePromptStatus().getId() == null) {
			throw new ServiceException("修改失败,未获取到关键参数!");
		}
		
		String fileName = wechatInboxVoicePrompt.getVoiceUrl().substring(1);
		StoredFile s = FileUtil.copyTmpFileToStoredFile(fileName,
				Constants.WECHAT_ATTACHFILE);
		wechatInboxVoicePrompt.setVoiceUrl("/" + s.getFullName().replaceAll("\\\\", "/"));
		
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(wechatInboxVoicePrompt
						.getVoicePromptStatus().getId());
		wechatInboxVoicePrompt.setVoicePromptStatus(propertyDict);
		wechatInboxVoicePrompt.setUpdateDate(session.getAccessTime());
		wechatInboxVoicePrompt.setUpdateUser(session.getUserName());
		wechatInboxVoicePromptDao
				.updateWechatInboxVoicePromptById(wechatInboxVoicePrompt);
	}

}
