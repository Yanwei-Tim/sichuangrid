package com.tianque.qinYangIssue.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.qinYangIssue.domain.IssueJointTemp;

/**
 * @Description:青羊事件对接临时表dao
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-14 下午03:12:41
 */
@DynamicIbatisDAO(value = "IssueJointTempDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("IssueJointTempDAO")
public interface IssueJointTempDAO {

	/** 根据状态统计的总数 */
	public Integer getIssueJointTempCountByState(Integer syncPending);

	/** 根据id修改处理状态 */
	public void updateIssueJointTempById(Map<String, Object> map);

	/** 根据开始、结束、状态sql分页查询数据 */
	public List<IssueJointTemp> queryIssueJointTempByStateForList(
			Map<String, Object> map);

	/** 修改对接临时表已同步的的事件办理状态 */
	public void updateIssueJointTempDataStatus(Integer state);

	/** 修改开放的事件处理状态 */
	public void updateIssueJointTempDataStatusCodeByStatus(
			Map<String, Object> map);

}
