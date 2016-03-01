package com.tianque.leaderAnalysis.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.leaderAnalysis.domain.IssueAnalysisToMobile;

/**
 * @Description:手机事件的研判分析业务实现
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-31 下午05:24:32
 */
@Repository("IssueAnalysisToMobileDAO")
@DynamicIbatisDAO(sqlMapClientTemplate = "sqlMapClientTemplate", value = "IssueAnalysisToMobileDAO")
public interface IssueAnalysisToMobileDAO {
	/**
	 * 某个类型的办理情况统计，并新增到表里面
	 * 
	 * @param map
	 */
	public void addIssueAnalysisToMobile(Map<String, Object> map);

	/**
	 * 清除数据
	 * 
	 * @param map
	 */
	public void deleteAllDataByYearAndMonth(Map<String, Object> map);

	/**
	 * 获取某一个组织机构下辖包含职能部分的办理情况总数
	 * 
	 * @param map
	 * @return
	 */
	public IssueAnalysisToMobile getIssueDatasListTotalByOrgParentId(
			Map<String, Object> map);

	/**
	 * 获取某一个组织机构下辖不包含职能部分的办理情况
	 * 
	 * @param map
	 * @return
	 */
	public List<IssueAnalysisToMobile> queryIssueDatasListForList(
			Map<String, Object> map);

}
