package com.tianque.sms.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sms.dao.SmstemplateDao;
import com.tianque.sms.domain.Smstemplate;
import com.tianque.sms.domain.vo.SearchSmstemplateVo;
import com.tianque.sms.service.SmstemplateService;

/**
 * 短信模板:业务逻辑层
 * 
 * @author
 * @date 2013-07-03 11:27:49
 */
@Repository("smstemplateService")
public class SmstemplateServiceImpl extends
		BaseServiceImpl<Smstemplate, SearchSmstemplateVo> implements
		SmstemplateService {

	@Autowired
	@Qualifier("smstemplateValidator")
	private DomainValidator<Smstemplate> domainValidator;

	@Resource(name = "smstemplateDao")
	public void setBaseDao(SmstemplateDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Smstemplate add(Smstemplate smstemplate) {
		smstemplateValidator(smstemplate);
		try {
			smstemplate = getBaseDao().add(smstemplate);
			return smstemplate;
		} catch (Exception e) {
			return dealException(this, "add", "新增短信模板信息出现错误", e);
		}
	}

	@Override
	public Smstemplate update(Smstemplate smstemplate) {
		smstemplateValidator(smstemplate);
		try {
			smstemplate = getBaseDao().update(smstemplate);
			return smstemplate;
		} catch (Exception e) {
			return dealException(this, "update", "更新短信模板信息出现错误", e);
		}
	}

	private void smstemplateValidator(Smstemplate smstemplate) {
		ValidateResult baseDataValidator = domainValidator
				.validate(smstemplate);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String getContent(BaseDomain baseDomain, String templateType) {
		if (baseDomain == null || templateType == null) {
			throw new BusinessValidationException("获取短信模板内容错误");
		}
		SearchSmstemplateVo searchSmstemplateVo = new SearchSmstemplateVo();
		searchSmstemplateVo.setKey(baseDomain.getClass().getName());
		searchSmstemplateVo.setType(templateType);
		Smstemplate smstemplate = ((SmstemplateDao) getBaseDao())
				.getSmstemplateBySearchSmstemplateVo(searchSmstemplateVo);
		if (smstemplate == null) {
			throw new BusinessValidationException("该模板不存在");
		}

		String content = smstemplate.getContent();
		Pattern p = Pattern.compile("\\$\\{\\w+\\}");
		Matcher m = p.matcher(content);
		List<String> list = new ArrayList<String>();
		while (m.find()) {// 匹配${}
			list.add(m.group());
		}
		List<String> fieldList = getFields(list);
		Field[] fields = baseDomain.getClass().getDeclaredFields();

		Object[] obj = new Object[fieldList.size()];

		for (int i = 0; i < fieldList.size(); i++) {
			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				if (fields[j].getName().equals(fieldList.get(i))) {
					try {
						if (fields[j].getType().getName()
								.equals(java.util.Date.class.getName())) {
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyy年MM月dd日 HH时mm分");
							obj[i] = dateFormat
									.format(((java.util.Date) fields[j]
											.get(baseDomain)));
						} else {
							obj[i] = fields[j].get(baseDomain);
						}

						break;
					} catch (IllegalArgumentException e) {
						e.initCause(e);
					} catch (IllegalAccessException e) {
						e.initCause(e);
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			content = content.replace(list.get(i), obj[i].toString());
		}
		return content;
	}

	/**
	 * 得到"${xxx}" 中的xxx
	 * 
	 * @param collection
	 * @return List
	 */
	private List<String> getFields(List<String> collection) {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < collection.size(); i++) {
			list.add(collection.get(i).substring(2,
					collection.get(i).length() - 1));
		}

		return list;
	}
}
