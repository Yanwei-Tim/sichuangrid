package com.tianque.plugin.orgchange.service;

import org.oproject.framework.orm.PageResult;

import com.tianque.plugin.orgchange.domain.OrgChangeLog;

/***
 * 组织机构迁移合并
 * 
 * @author N-223
 * 
 */
public interface OrgChangeLogInfoService {

	public PageResult<OrgChangeLog> findOrgChangeLogInfoByChangeInfoId(Long id,
			int pageNum, int pageSize, String sidx, String sord);

	public OrgChangeLog getOrgChangeLogById(Long id);

	public void addLog(OrgChangeLog log);
}
