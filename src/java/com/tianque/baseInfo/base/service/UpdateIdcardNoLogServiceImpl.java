package com.tianque.baseInfo.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.dao.UpdateIdcardNoLogDAO;
import com.tianque.baseInfo.base.domain.UpdateIdcardNoLog;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;

/**
 * @Description:修改身份证号码日志业务层实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-31 下午11:01:41
 */
@Service("updateIdcardNoLogService")
@Transactional
public class UpdateIdcardNoLogServiceImpl implements UpdateIdcardNoLogService {

	@Autowired
	private UpdateIdcardNoLogDAO updateIdcardNoLogDAO;

	@Autowired
	@Qualifier("updateIdcardNoLogValidator")
	private DomainValidator<UpdateIdcardNoLog> domainValidator;

	@Override
	public UpdateIdcardNoLog addUpdateIdcardNoLog(
			UpdateIdcardNoLog updateIdcardNoLog) {
		if (updateIdcardNoLog == null || updateIdcardNoLog.getDataOrg() == null
				|| updateIdcardNoLog.getDataOrg().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		ValidateResult result = domainValidator.validate(updateIdcardNoLog);
		if (result != null && result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			Long id = updateIdcardNoLogDAO
					.addUpdateIdcardNoLog(updateIdcardNoLog);
			return getUpdateIdcardNoLogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"UpdateIdcardNoLogServiceImpl类的addUpdateIdcardNoLog方法错误",
					"新增修改身份证号码日志错误", e);
		}
	}

	@Override
	public UpdateIdcardNoLog getUpdateIdcardNoLogById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return updateIdcardNoLogDAO.getUpdateIdcardNoLogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"UpdateIdcardNoLogServiceImpl类的getUpdateIdcardNoLogById方法错误",
					"查询修改身份证号码日志错误", e);
		}
	}

}
