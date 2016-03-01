package com.tianque.plugin.weChat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.weChat.dao.SmsAccountDao;
import com.tianque.plugin.weChat.domain.RequestResult;
import com.tianque.plugin.weChat.domain.sms.SmsAccount;
import com.tianque.plugin.weChat.service.SmsAccountService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.HttpClientUtils;

@Service("smsAccountService")
@Transactional
public class SmsAccountServiceImpl implements SmsAccountService {
	@Autowired
	private SmsAccountDao smsAccountDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private static final String QUERY_SMSACCOUNT_BALANCE_URL = "http://web.cr6868.com/asmx/smsservice.aspx";
	private static final Map<String, String> QUERY_SMSACCOUNT_BALANCE_RESULT = new HashMap<String, String>();
	static {
		QUERY_SMSACCOUNT_BALANCE_RESULT.put("10", "账号不存在");
		QUERY_SMSACCOUNT_BALANCE_RESULT.put("11", "账号注销");
		QUERY_SMSACCOUNT_BALANCE_RESULT.put("12", "账号停用");
		QUERY_SMSACCOUNT_BALANCE_RESULT.put("13", "IP鉴权失败");
	}
	@Override
	public Long saveSmsAccount(SmsAccount smsAccount) {
		smsAccount.setType("1");// 现在就一种类别
		if (smsAccount == null || StringUtil.isBlank(smsAccount.getName())
				|| StringUtil.isBlank(smsAccount.getPwd()) || smsAccount.getOrg() == null
				|| smsAccount.getOrg().getId() == null) {
			throw new BusinessValidationException("参数不全");
		}
		Organization org = organizationDubboService.getSimpleOrgById(smsAccount.getOrg().getId());
		if (org == null) {
			throw new BusinessValidationException("未找到组织");
		}
		smsAccount.setOrg(org);
		SmsAccount hasBean = smsAccountDao
				.getSmsAccountByNameAndOrgCode(smsAccount.getName(), null);
		if (hasBean != null) {
			throw new BusinessValidationException("该短信账号已经存在");
		}
		smsAccount.setCallbackPwd(UUID.randomUUID().toString().replace("-", ""));
		return smsAccountDao.saveSmsAccount(smsAccount);
	}

	@Override
	public SmsAccount getById(Long id) {
		return smsAccountDao.getById(id);
	}

	@Override
	public void updateSmsAccount(SmsAccount smsAccount) {
		if (smsAccount == null || smsAccount.getId() == null
				|| StringUtil.isBlank(smsAccount.getName())
				|| StringUtil.isBlank(smsAccount.getPwd()) || smsAccount.getOrg() == null
				|| smsAccount.getOrg().getId() == null) {
			throw new BusinessValidationException("参数不全");
		}
		SmsAccount hasBean = smsAccountDao
				.getSmsAccountByNameAndOrgCode(smsAccount.getName(), null);
		if (hasBean != null && !smsAccount.getName().equals(hasBean.getName())) {
			throw new BusinessValidationException("该短信账号已经存在");
		}
		smsAccountDao.updateSmsAccount(smsAccount);
	}

	@Override
	public PageInfo<SmsAccount> findSmsAccount(SmsAccount smsAccount, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (smsAccount == null || smsAccount.getOrg() == null
				|| smsAccount.getOrg().getOrgInternalCode() == null) {
			throw new BusinessValidationException("未获取到组织机构");
		}
		return smsAccountDao.findSmsAccount(smsAccount, pageNum, pageSize, sidx, sord);
	}

	@Override
	public List<SmsAccount> findSmsAccountByOrgCode(String orgCode) {
		return smsAccountDao.findSmsAccountByOrgCode(orgCode);
	}

	@Override
	public RequestResult querySmsAccountBalance(Long id) {
		if (id == null) {
			return new RequestResult().setErrorCode("parameter error").setMessage("id错误");
		}
		SmsAccount smsAccount = smsAccountDao.getById(id);
		if (smsAccount == null) {
			return new RequestResult().setErrorCode("error").setMessage("没找到短信账号");
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", smsAccount.getName());
		paramMap.put("pwd", smsAccount.getPwd());
		paramMap.put("type", "balance");
		String queryRes = HttpClientUtils
				.postProxyToOutside(QUERY_SMSACCOUNT_BALANCE_URL, paramMap);
		String[] queryResArray = queryRes.split(",");
		if ("0".equals(queryResArray[0])) {
			return new RequestResult().setData(queryResArray[1]);
		} else {
			String msg = QUERY_SMSACCOUNT_BALANCE_RESULT.get(queryResArray[0]);
			return new RequestResult().setErrorCode("error").setMessage(
					msg != null ? msg : queryResArray[1]);
		}
	}

}
