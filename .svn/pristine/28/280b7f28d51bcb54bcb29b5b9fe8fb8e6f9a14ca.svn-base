package com.tianque.plugin.analysisNew.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analysisNew.domain.BaseSituationStatement;

@DynamicIbatisDAO(value = "BaseSituationStatementNewDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("BaseSituationStatementNewDAO")
public interface BaseSituationStatementNewDAO {

	public List<BaseSituationStatement> queryBaseSituationStatementForList(
			Map<String, Object> map);

	/** 清空表数据 */
	public void deleteBaseSituationStatement(String tableName);

	public void addBaseSituationStatementStatistics(
			BaseSituationStatement baseSituationStatement);

	public BaseSituationStatement getBaseSituationStatementByOrgIdAndType(
			Map<String, Object> map);
}
