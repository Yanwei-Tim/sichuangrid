package com.tianque.sms.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sms.dao.SmsqueryconditionDao;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.service.SmsqueryconditionService;

/**
 * 查询条件管理:业务逻辑层
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
@Repository("smsqueryconditionService")
@Transactional
public class SmsqueryconditionServiceImpl extends
		BaseServiceImpl<Smsquerycondition, Smsquerycondition> implements
		SmsqueryconditionService {

	@Autowired
	@Qualifier("smsqueryconditionValidator")
	private DomainValidator<Smsquerycondition> domainValidator;
	@Autowired
	private SmsqueryconditionDao smsqueryconditionDao;

	@Resource(name = "smsqueryconditionDao")
	public void setBaseDao(SmsqueryconditionDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Smsquerycondition add(Smsquerycondition smsquerycondition) {
		smsqueryconditionValidator(smsquerycondition);
		try {
			validateSql(smsquerycondition);
			smsquerycondition = getBaseDao().add(smsquerycondition);
			return smsquerycondition;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SmsqueryconditionServiceImpl的add方法出现异常，原因", e);

		}
	}

	@Override
	public Smsquerycondition update(Smsquerycondition smsquerycondition) {
		smsqueryconditionValidator(smsquerycondition);
		try {
			validateSql(smsquerycondition);
			smsquerycondition = getBaseDao().update(smsquerycondition);
			return smsquerycondition;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类SmsqueryconditionServiceImpl的update方法出现异常，原因：", e);
		}
	}

	@Override
	public void deleteSmsqueryconditionByObjectId(Long objectId) {
		if (null == objectId) {
			throw new BusinessValidationException("参数为null，操作无效！");
		}
		smsqueryconditionDao.deleteSmsqueryconditionByObjectId(objectId);
	}

	@Override
	public List<Smsquerycondition> findSmsqueryconditionsBySmsSendObjectId(
			Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数为null，操作无效！");
		}
		return smsqueryconditionDao.findSmsqueryconditionsBySmsSendObjectId(id);
	}

	@Override
	public Smsquerycondition getSqlBasicStatementByObjectId(Long objectId) {
		if (null == objectId) {
			throw new BusinessValidationException("参数为null，操作无效！");
		}
		return smsqueryconditionDao.getSqlBasicStatement(objectId);
	}

	@Override
	public boolean validateDescription(Long id, Long objectId, String field) {
		Smsquerycondition query = smsqueryconditionDao.validateDescription(
				objectId, field);
		return null == query ? true : query.getId().equals(id);
	}

	@Override
	public boolean validateKey(Long id, Long objectId, String field) {
		Smsquerycondition query = smsqueryconditionDao.validateKey(objectId,
				field);
		return null == query ? true : query.getId().equals(id);
	}

	private void validateSql(Smsquerycondition query) {
		Smsquerycondition sqlBasic = getSqlBasicStatement(query);
		StringBuffer sbSql = new StringBuffer();
		isNull(sqlBasic, sbSql);
		isNull(query, sbSql);
		try {
			smsqueryconditionDao.validateSql(sbSql.toString());
		} catch (Exception e) {
			throw new BusinessValidationException("sql语句错误，请检查！");
		}

	}

	private Smsquerycondition getSqlBasicStatement(Smsquerycondition query) {
		Smsquerycondition sqlBasic = getSqlBasicStatementByObjectId(query
				.getSmsSendObject().getId());
		if (null == sqlBasic && !"table".equals(query.getKey())) {
			throw new BusinessValidationException(
					"基础sql语句不存在,应先建立 如 ：select name as name, mobilenumber as mobile, orgid , orginternalcode from tableXXX where 1=1 and mobilenumber is not null,且中间key是table的查询条件。");
		}
		if (null == sqlBasic) {
			return null;
		}
		if (sqlBasic.getId().equals(query.getId())
				&& !"table".equals(query.getKey())) {
			throw new BusinessValidationException("基础sql语句中间key不可以修改。");
		}
		if ("table".equals(query.getType())) {
			throw new BusinessValidationException("只有基础sql语句的类型是table");
		}
		if (sqlBasic.getId().equals(query.getId())) {
			return null;
		}
		return sqlBasic;
	}

	private StringBuffer isNull(Smsquerycondition sqc, StringBuffer sbSql) {
		if (null == sqc) {
			return sbSql;
		}
		String sql = replaceStr(sqc);
		if (null != sqc.getIsNull() && sqc.getIsNull().booleanValue()) {
			sbSql.append(" ").append(sql);
		} else if (StringUtil.isStringAvaliable(sql)) {
			sbSql.append(" ").append(sql);
		}
		return sbSql;
	}

	private String replaceStr(Smsquerycondition sqc) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher m = p.matcher(sqc.getSqlTemplate());
		while (m.find()) {
			if ("date".equals(sqc.getType())) {
				m.appendReplacement(sb, "2060-01-01");
			} else {
				m.appendReplacement(sb, "0");
			}
		}
		return m.appendTail(sb).toString();
	}

	private void smsqueryconditionValidator(Smsquerycondition smsquerycondition) {
		ValidateResult baseDataValidator = domainValidator
				.validate(smsquerycondition);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

}
