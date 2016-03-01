package com.tianque.issueAbutmentJoint.dao;

import java.util.List;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.issueAbutmentJoint.domain.IssueJointLog;

/**
 * @Description:事件对接 日志dao
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:31:04
 */
@DynamicIbatisDAO(value = "IssueJointLogDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("IssueJointLogDAO")
public interface IssueJointLogDAO {

	/**
	 * 新增导入对接事件日志【由于要保持对接有些数据是取冗余的字段的值（如创建时间等）】
	 * 
	 * @param issueJointLog
	 * @return
	 */
	public Long addIssueJointLogByIssueJointByImport(IssueJointLog issueJointLog);

	/**
	 * 根据对接事件id查询对接事件日志
	 * 
	 * @param issueJointId
	 * @return
	 */
	public List<IssueJointLog> queryIssueJointLogByIssueJointIdForList(
			Long issueJointId);

	/**
	 * 根据id查询日志
	 * 
	 * @param id
	 * @return
	 */
	public IssueJointLog getIssueJointLogById(Long id);

}
