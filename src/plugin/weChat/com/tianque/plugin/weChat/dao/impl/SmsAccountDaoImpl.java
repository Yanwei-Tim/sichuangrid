package com.tianque.plugin.weChat.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.SmsAccountDao;
import com.tianque.plugin.weChat.domain.sms.SmsAccount;

@Repository("smsAccountDao")
public class SmsAccountDaoImpl extends AbstractBaseDao implements SmsAccountDao {

	@Override
	public Long saveSmsAccount(SmsAccount smsAccount) {
		smsAccount.setCreateUser(ThreadVariable.getUser().getUserName());
		smsAccount.setCreateDate(new Date());
		return (Long) getSqlMapClientTemplate().insert("smsAccount.saveSmsAccount", smsAccount);
	}

	@Override
	public SmsAccount getById(Long id) {
		return (SmsAccount) getSqlMapClientTemplate().queryForObject("smsAccount.getById",
				id);
	}

	@Override
	public void updateSmsAccount(SmsAccount smsAccount) {
		getSqlMapClientTemplate().update("smsAccount.updateSmsAccount", smsAccount);
	}

	@Override
	public PageInfo<SmsAccount> findSmsAccount(SmsAccount smsAccount,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsAccount", smsAccount);
		map.put("sortField", sidx);
		map.put("order", sord);
		return getPageInfoByParamMap(new PageInfo<SmsAccount>(), "smsAccount.countFindSmsAccount",
				"smsAccount.findSmsAccount",
				pageNum, pageSize, map);
	}

	@Override
	public SmsAccount getSmsAccountByNameAndOrgCode(String name, String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgCode", orgCode);
		return (SmsAccount) getSqlMapClientTemplate().queryForObject(
				"smsAccount.getSmsAccountByNameAndOrgCode", map);
	}

	@Override
	public List<SmsAccount> findSmsAccountByOrgCode(String orgCode) {
		return getSqlMapClientTemplate()
				.queryForList("smsAccount.findSmsAccountByOrgCode", orgCode);
	}

}
