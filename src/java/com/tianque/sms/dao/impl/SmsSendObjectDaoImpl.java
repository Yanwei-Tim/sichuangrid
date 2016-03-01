package com.tianque.sms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.sms.dao.SmsSendObjectDao;
import com.tianque.sms.domain.SmsSendObject;

/**
 * 发送对象:数据操作层
 */
@Repository("smsSendObjectDao")
public class SmsSendObjectDaoImpl extends
		BaseDaoImpl<SmsSendObject, SmsSendObject> implements SmsSendObjectDao {

	@Override
	public List<SmsSendObject> findSimpleSmsSendObjects() {

		return (List<SmsSendObject>) getSqlMapClientTemplate().queryForList(
				"smsSendObject.findSimpleSmsSendObjects");
	}

}
