package com.tianque.plugin.orgchange.service;

import java.util.HashMap;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.orgchange.dao.OrgChangeLogDAO;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;

@Transactional
@Service("orgChangeLogInfoService")
public class OrgChangeLogInfoServiceImpl implements OrgChangeLogInfoService {

	@Autowired
	private OrgChangeLogDAO orgChangeLogDAO;

	@Override
	public PageResult<OrgChangeLog> findOrgChangeLogInfoByChangeInfoId(Long id,
			int pageNum, int pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sortField", sidx);
		map.put("order", sord);
		return orgChangeLogDAO.queryOrgChangeLogInfoByIdForPageResult(map,
				pageNum, pageSize);
	}

	@Override
	public OrgChangeLog getOrgChangeLogById(Long id) {
		return orgChangeLogDAO.getOrgChangeLogById(id);
	}

	@Override
	public void addLog(OrgChangeLog log) {
		if (log != null)
			orgChangeLogDAO.addLog(log);
	}

}
