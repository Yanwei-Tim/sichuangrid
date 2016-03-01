package com.tianque.plugin.weChat.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.WechatInboxVoicePromptDao;
import com.tianque.plugin.weChat.domain.ModuleTable;
import com.tianque.plugin.weChat.domain.inbox.WechatInboxVoicePrompt;

@Repository("wechatInboxVoicePromptDao")
public class WechatInboxVoicePromptDaoImpl extends AbstractBaseDao implements
		WechatInboxVoicePromptDao {

	@Override
	public WechatInboxVoicePrompt findWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {
		return (WechatInboxVoicePrompt) getSqlMapClientTemplate()
				.queryForObject(
						"wechatInboxVoicePrompt.getWechatInboxVoicePrompt",
						wechatInboxVoicePrompt);
	}

	@Override
	public void addWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {
		getSqlMapClientTemplate().insert(
				"wechatInboxVoicePrompt.addWechatInboxVoicePrompt",
				wechatInboxVoicePrompt);
	}

	@Override
	public PageInfo<WechatInboxVoicePrompt> findWechatInboxVoicePrompts(
			Map<String, Object> parameterMap, Integer pageNum, Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<ModuleTable>(),
				"wechatInboxVoicePrompt.countFindWechatInboxVoicePrompts",
				"wechatInboxVoicePrompt.findWechatInboxVoicePrompts", pageNum,
				pageSize, parameterMap);
	}

	@Override
	public void updateWechatInboxVoicePromptById(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {
		getSqlMapClientTemplate().update(
				"wechatInboxVoicePrompt.updateWechatInboxVoicePromptById",
				wechatInboxVoicePrompt);
	}
}
