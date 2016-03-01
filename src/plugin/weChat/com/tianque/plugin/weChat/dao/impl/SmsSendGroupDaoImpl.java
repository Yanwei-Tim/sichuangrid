package com.tianque.plugin.weChat.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.SmsSendGroupDao;
import com.tianque.plugin.weChat.domain.sms.SmsSendGroup;

@Repository("smsSendGroupDao")
public class SmsSendGroupDaoImpl extends AbstractBaseDao implements SmsSendGroupDao {

	@Override
	public Long saveSmsSendGroup(SmsSendGroup smsSendGroup) {
		smsSendGroup.setCreateUser(ThreadVariable.getUser().getUserName());
		smsSendGroup.setCreateDate(new Date());
		return (Long) getSqlMapClientTemplate().insert("smsSendGroup.saveSmsSendGroup",
				smsSendGroup);
	}

	@Override
	public SmsSendGroup getById(Long id) {
		return (SmsSendGroup) getSqlMapClientTemplate().queryForObject("smsSendGroup.getById",
				id);
	}

	@Override
	public void updateSmsSendGroup(SmsSendGroup smsSendGroup) {
		getSqlMapClientTemplate().update("smsSendGroup.updateSmsSendGroup", smsSendGroup);
	}

	@Override
	public PageInfo<SmsSendGroup> findSmsSendGroup(SmsSendGroup smsSendGroup,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsSendGroup", smsSendGroup);
		map.put("sortField", sidx);
		map.put("order", sord);
		return getPageInfoByParamMap(new PageInfo<SmsSendGroup>(),
				"smsSendGroup.countFindSmsSendGroup", "smsSendGroup.findSmsSendGroup",
				pageNum, pageSize, map);
	}

	@Override
	public SmsSendGroup getBySmsSendId(String smsSendId) {
		return (SmsSendGroup) getSqlMapClientTemplate().queryForObject(
				"smsSendGroup.getBySmsSendId", smsSendId);
	}

}
