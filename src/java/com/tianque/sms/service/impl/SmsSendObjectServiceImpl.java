package com.tianque.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sms.dao.SmsSendObjectDao;
import com.tianque.sms.domain.SmsSendObject;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.service.SmsSendObjectService;
import com.tianque.sms.service.SmsqueryconditionService;

/**
 * 发送对象:业务逻辑层
 */
@Repository("smsSendObjectService")
@Transactional
public class SmsSendObjectServiceImpl extends
		BaseServiceImpl<SmsSendObject, SmsSendObject> implements
		SmsSendObjectService {
	private static Logger logger = LoggerFactory
			.getLogger(SmsSendObjectServiceImpl.class);

	@Autowired
	private SmsSendObjectDao smsSendObjectDao;
	@Autowired
	SmsqueryconditionService smsqueryconditionService;

	@Resource(name = "smsSendObjectDao")
	public void setBaseDao(SmsSendObjectDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public SmsSendObject add(SmsSendObject smsSendObject) {
		try {
			validateParam(smsSendObject);
			validatetTemplate(smsSendObject);
			SmsSendObject newSmsSendObject = getBaseDao().add(smsSendObject);
			return newSmsSendObject;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SmsSendObjectServiceImpl的add方法出现异常，原因", e);

		}
	}

	@Override
	public SmsSendObject update(SmsSendObject entity) {
		try {
			if (null == entity || null == entity.getId()) {
				throw new BusinessValidationException("参数无效！");
			}
			validateParam(entity);
			validatetTemplate(entity);
			SmsSendObject newSmsSendObject = getBaseDao().update(entity);
			return newSmsSendObject;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SmsSendObjectServiceImpl的update方法出现异常，原因", e);

		}

	}

	@Override
	public List<SmsSendObject> findSimpleSmsSendObjects() {
		return smsSendObjectDao.findSimpleSmsSendObjects();
	}

	@Override
	public void delete(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数无效!");
		}
		smsqueryconditionService.deleteSmsqueryconditionByObjectId(id);
		super.delete(id);

	}

	@Override
	public void delete(Long[] ids) {
		if (null == ids || ids.length <= 0) {
			throw new BusinessValidationException("参数无效!");
		}
		for (Long id : ids) {
			delete(id);
		}
	}

	private void validatetTemplate(SmsSendObject smsSendObject) {
		if (null == smsSendObject
				|| !StringUtil.isStringAvaliable(smsSendObject.getTemplate())) {
			return;
		}
		List<Smsquerycondition> sqcList = smsqueryconditionService
				.findSmsqueryconditionsBySmsSendObjectId(smsSendObject.getId());
		ArrayList<String> descriptions = new ArrayList<String>();
		for (Smsquerycondition sqc : sqcList) {
			descriptions.add(sqc.getDescription());
		}
		for (String str : queryList(smsSendObject.getTemplate())) {
			if (!descriptions.contains(str)) {
				throw new BusinessValidationException(str
						+ ",在条件管理中没有此描述，请检查查询条件！");
			}
		}

	}

	private ArrayList<String> queryList(String template) {
		Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher m = p.matcher(template);
		ArrayList<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(1));
		}
		return strs;
	}

	private void validateParam(SmsSendObject entity) {
		if (!StringUtil.isStringAvaliable(entity.getName())) {
			throw new BusinessValidationException("名称不能为空！");
		}
	}

}
