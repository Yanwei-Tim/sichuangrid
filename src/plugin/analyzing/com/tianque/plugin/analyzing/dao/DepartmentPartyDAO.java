package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analyzing.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;

@DynamicIbatisDAO(value = "DepartmentPartyDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("DepartmentPartyDAO")
public interface DepartmentPartyDAO {
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
