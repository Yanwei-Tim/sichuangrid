package com.tianque.plugin.weChat.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.SmsSendResultDao;
import com.tianque.plugin.weChat.domain.sms.SmsSendResult;

@Repository("smsSendResultDao")
public class SmsSendResultDaoImpl extends AbstractBaseDao implements SmsSendResultDao {

	@Override
	public Long saveSmsSendResult(SmsSendResult smsSendResult) {
		smsSendResult.setCreateUser(ThreadVariable.getUser().getUserName());
		smsSendResult.setCreateDate(new Date());
		return (Long) getSqlMapClientTemplate().insert("smsSendResult.saveSmsSendResult",
				smsSendResult);
	}

	@Override
	public SmsSendResult getById(Long id) {
		return (SmsSendResult) getSqlMapClientTemplate()
				.queryForObject("smsSendResult.getById",
				id);
	}

	@Override
	public void updateSmsSendResult(SmsSendResult smsSendResult) {
		getSqlMapClientTemplate().update("smsSendResult.updateSmsSendResult", smsSendResult);
	}

	@Override
	public PageInfo<SmsSendResult> findSmsSendResult(SmsSendResult smsSendResult,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsSendResult", smsSendResult);
		map.put("sortField", sidx);
		map.put("order", sord);
		return getPageInfoByParamMap(new PageInfo<SmsSendResult>(),
				"smsSendResult.countFindSmsSendResult", "smsSendResult.findSmsSendResult",
				pageNum, pageSize, map);
	}

	@Override
	public List<Long> saveSmsSendResult(List<SmsSendResult> smsSendResultList) {
		List<Long> list = super.batchInsertDate(smsSendResultList,
				"smsSendResult.saveSmsSendResult");
		return list;
	}

}
