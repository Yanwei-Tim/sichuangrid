package com.tianque.issueAbutmentJoint.dao;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.issueAbutmentJoint.domain.IssueJoint;
import com.tianque.issueAbutmentJoint.domain.vo.IssueJointVo;

/**
 * @Description:事件对接事件dao
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:30:45
 */
@DynamicIbatisDAO(value = "IssueJointDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("IssueJointDAO")
public interface IssueJointDAO {

	/***
	 * 导入新增对接事件【由于要保持对接有些数据是取冗余的字段的值（如创建时间等）】
	 * 
	 * @param issueJoint
	 * @return
	 */
	public Long addIssueJointByImport(IssueJoint issueJoint);

	/**
	 * 根据id查询对接事件
	 * 
	 * @param id
	 * @return
	 */
	public IssueJoint getIssueJointById(Long id);

	/**
	 * 根据搜索类查询
	 * 
	 * @param issueJointVo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult<IssueJoint> queryIssueJointByIssueJointVoForPageResult(
			IssueJointVo issueJointVo, int pageNum, int pageSize);

	/**
	 * 根据搜索类统计总数
	 * 
	 * @param issueJointVo
	 * @return
	 */
	/*
	 * public Integer getIssueJointByIssueJointVoForPageResultCount(
	 * IssueJointVo issueJointVo);
	 */
}
