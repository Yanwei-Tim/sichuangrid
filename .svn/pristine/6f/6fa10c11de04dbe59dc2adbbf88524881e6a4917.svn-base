package com.tianque.plugin.orgchange.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.orgchange.domain.OrgChangeLog;

/**
 * 组织机构迁移合并日志DAO
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@DynamicIbatisDAO(value = "OrgChangeLogDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("OrgChangeLogDAO")
public interface OrgChangeLogDAO {

	public abstract Long addLog(OrgChangeLog log);

	public abstract void updateLog(OrgChangeLog log);

	public abstract List<OrgChangeLog> queryLogForList(OrgChangeLog condition,
			int start, int size);

	public abstract Integer getCount(OrgChangeLog condition);

	public PageResult<OrgChangeLog> queryOrgChangeLogInfoByIdForPageResult(
			Map<String, Object> map, int pageNum, int pageSize);

	public OrgChangeLog getOrgChangeLogById(Long id);

}
