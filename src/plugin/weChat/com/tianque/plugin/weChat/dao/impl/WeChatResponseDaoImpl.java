package com.tianque.plugin.weChat.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.WeChatResponseDao;
import com.tianque.plugin.weChat.domain.WeChatResponse;

@Repository("weChatResponseDao")
public class WeChatResponseDaoImpl extends AbstractBaseDao implements WeChatResponseDao {

	@Override
	public Long saveWeChatResponse(WeChatResponse weChatResponse) {
		weChatResponse.setCreateUser(ThreadVariable.getUser().getUserName());
		weChatResponse.setCreateDate(new Date());
		return (Long) getSqlMapClientTemplate().insert("weChatResponse.saveWeChatResponse",
				weChatResponse);
	}

	@Override
	public WeChatResponse getById(Long id) {
		return (WeChatResponse) getSqlMapClientTemplate().queryForObject("weChatResponse.getById",
				id);
	}

	@Override
	public void updateWeChatResponse(WeChatResponse weChatResponse) {
		getSqlMapClientTemplate().update("weChatResponse.updateWeChatResponse", weChatResponse);
	}

	@Override
	public PageInfo<WeChatResponse> findWeChatResponse(WeChatResponse weChatResponse,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatResponse", weChatResponse);
		map.put("sidx", sidx);
		map.put("sord", sord);
		return getPageInfoByParamMap(new PageInfo<WeChatResponse>(),
				"weChatResponse.countFindWeChatResponse", "weChatResponse.findWeChatResponse",
				pageNum, pageSize, map);
	}

	@Override
	public WeChatResponse getByMsgId(String msgId) {
		return (WeChatResponse) getSqlMapClientTemplate().queryForObject(
				"weChatResponse.getByMsgId", msgId);
	}

	@Override
	public int countByWechatName(String wechatUserName, Date startDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wechatUserName", wechatUserName);
		map.put("startDate", startDate);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"weChatResponse.countByWechatName", map);
	}

}
