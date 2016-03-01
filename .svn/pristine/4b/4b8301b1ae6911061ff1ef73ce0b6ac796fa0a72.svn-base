package com.tianque.plugin.analysisNew.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analysisNew.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;

@DynamicIbatisDAO(value = "DepartmentPartyNewDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("DepartmentPartyNewDAO")
public interface DepartmentPartyNewDAO {
	/**
	 * 组织机构-党委部门报表数据
	 * 
	 * @param map
	 * @return
	 */
	public List<SearchPrimaryOrganizationDataColumnVo> queryDepartmentPartyDataByYearMonthColumnVoForList(
			Map<String, Object> map);

	/***
	 * 清除组织机构-党委部门分布表数据
	 */
	public void deleteDepartmentParty(Map<String, Object> map);

	/***
	 * 将组织机构-党委部门的数据插入对应的分布表中
	 */
	public void addDepartmentParty(Map<String, Object> map);

	/**
	 * 查询 图表数据
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> queryDepartmentPartyListFromHistoryForList(
			StatisticSearchVo statisticSearchVo);
}
