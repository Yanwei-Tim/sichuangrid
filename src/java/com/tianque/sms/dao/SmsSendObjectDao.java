package com.tianque.sms.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.sms.domain.SmsSendObject;

/**
 * 发送对象:数据操作层接口
 */
public interface SmsSendObjectDao extends BaseDao<SmsSendObject, SmsSendObject> {
	/**
	 * 得到所有启用的发送对象信息
	 * 
	 * @return
	 */
	public List<SmsSendObject> findSimpleSmsSendObjects();
}
