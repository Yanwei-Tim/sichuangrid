package com.tianque.baseInfo.base.dao;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.UpdateIdcardNoLog;

/**
 * @Description:修改身份证号码dao类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-31 下午11:02:50
 */
@DynamicIbatisDAO(value = "UpdateIdcardNoLogDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("UpdateIdcardNoLogDAO")
public interface UpdateIdcardNoLogDAO {
	/**
	 * 新增修改身份证日志【修改日期为createDate 修改用户为createUser 】
	 * 
	 * @param updateIdcardNoLog
	 * @return
	 */
	public Long addUpdateIdcardNoLog(UpdateIdcardNoLog updateIdcardNoLog);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public UpdateIdcardNoLog getUpdateIdcardNoLogById(Long id);

}
