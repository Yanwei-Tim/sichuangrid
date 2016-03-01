package com.tianque.issueAbutmentJoint.dao;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.issueAbutmentJoint.domain.IssueJointStep;

/**
 * @Description:事件对接步骤dao
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:31:20
 */
@DynamicIbatisDAO(value = "IssueJointStepDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("IssueJointStepDAO")
public interface IssueJointStepDAO {

	/***
	 * 对接事件导入新增步骤【由于要保持对接有些数据是取冗余的字段的值（如创建时间等）】
	 * 
	 * @param issueJointStep
	 * @return
	 */
	public Long addIssueJointStepByIssueJointByImport(
			IssueJointStep issueJointStep);

	/**
	 * 根据Id获取步骤
	 * 
	 * @param id
	 * @return
	 */
	public IssueJointStep getIssueJointStepById(Long id);

}
