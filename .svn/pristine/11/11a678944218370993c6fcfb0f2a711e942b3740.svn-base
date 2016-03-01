package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analyzing.domain.BaseSituationStatement;

@DynamicIbatisDAO(value = "BaseSituationStatementDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("BaseSituationStatementDAO")
public interface BaseSituationStatementDAO {

	public List<BaseSituationStatement> queryBaseSituationStatementForList(
			Map<String, Object> map);

	public List<BaseSituationStatement> queryBaseSituationStatementNewForList(
			Map<String, Object> map);

	/** 清空表数据 */
	public void deleteBaseSituationStatement(String tableName);

	public void addBaseSituationStatementStatistics(
			BaseSituationStatement baseSituationStatement);

	public BaseSituationStatement getBaseSituationStatementByOrgIdAndType(
			Map<String, Object> map);

	// 统计事件对接里面东区的本月事件总数和本月社情民意数据
	public BaseSituationStatement getBaseSituationStatementByParams(
			Map<String, Object> map);
}
