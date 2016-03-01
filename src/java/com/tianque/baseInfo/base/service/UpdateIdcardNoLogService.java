package com.tianque.baseInfo.base.service;

import com.tianque.baseInfo.base.domain.UpdateIdcardNoLog;

/**
 * @Description:修改身份证号码日志业务层接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-31 下午11:00:53
 */
public interface UpdateIdcardNoLogService {

	/**
	 * 新增修改身份证日志【修改日期为createDate 修改用户为createUser 】
	 * 
	 * @param updateIdcardNoLog
	 * @return
	 */
	public UpdateIdcardNoLog addUpdateIdcardNoLog(
			UpdateIdcardNoLog updateIdcardNoLog);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public UpdateIdcardNoLog getUpdateIdcardNoLogById(Long id);
}
