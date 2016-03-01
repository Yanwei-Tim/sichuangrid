package com.tianque.issueAbutmentJoint.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.issueAbutmentJoint.domain.IssueJointAttachfile;

/**
 * @Description:事件对接已办结附件附件持久层
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-5 上午09:58:09
 */
@DynamicIbatisDAO(sqlMapClientTemplate = "sqlMapClientTemplate", value = "IssueJointAttachfileDAO")
@Repository("IssueJointAttachfileDAO")
public interface IssueJointAttachfileDAO {
	/**
	 * 根据事件id查询出事件所有的附件
	 * 
	 * @param issueId
	 * @return
	 */
	public List<IssueJointAttachfile> queryIssueJointAttachfileByIssueIdForList(
			Map<String, Object> map);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public IssueJointAttachfile getIssueJointAttachfileById(Long id);
}
