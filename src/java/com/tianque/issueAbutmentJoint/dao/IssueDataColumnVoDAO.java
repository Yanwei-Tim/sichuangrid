package com.tianque.issueAbutmentJoint.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.issueAbutmentJoint.domain.IssueDataColumnVo;

/**
 * @Description:对接事件已办结统计持久层
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-24 下午04:07:01
 */
@DynamicIbatisDAO(sqlMapClientTemplate = "sqlMapClientTemplate", value = "IssueDataColumnVoDAO")
@Repository("IssueDataColumnVoDAO")
public interface IssueDataColumnVoDAO {
	/**
	 * 查询统计
	 * 
	 * @param map
	 * @return
	 */
	public IssueDataColumnVo getIssueDataColumnVoByYearAndMonthAndIssueTypeAndOrgId(
			Map<String, Object> map);

	/**
	 * 修改统计
	 * 
	 * @param map
	 */
	public void updateIssueDataColumnVoById(Map<String, Object> map);

	/**
	 * 新增统计
	 * 
	 * @param issueDataColumnVo
	 */
	public void addIssueDataColumnVo(IssueDataColumnVo issueDataColumnVo);

	/**
	 * 获取事件对接表的统计数据
	 * 
	 * @param map
	 */
	public List<IssueDataColumnVo> queryIssueDataColumnVoToIssuejointForList(
			Map<String, Object> map);

	public Integer getIssueDataColumnVoToIssueHandleStatsToCTJD(
			Map<String, Object> map);
}
