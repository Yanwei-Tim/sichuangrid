package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyConstructionFilesDao;
import com.tianque.domain.PartyConstructionFiles;

@Repository("partyConstructionFilesDao")
public class PartyConstructionFilesDaoImpl extends AbstractBaseDao implements
		PartyConstructionFilesDao {

	@Override
	public PartyConstructionFiles addPartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"partyConstructionFiles.addPartyConstructionFiles", partyConstructionFiles);
		partyConstructionFiles = getPartyConstructionFilesById(id);
		return partyConstructionFiles;
	}

	@Override
	public PageInfo<PartyConstructionFiles> findPartyConstructionFilesByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord, String dalei) {
		List<PartyConstructionFiles> partyConstructionFiles = null;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("dalei", dalei);
		query.put("sortField", sidx);
		query.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"partyConstructionFiles.countPartyConstructionFiles", query);
		partyConstructionFiles = getSqlMapClientTemplate().queryForList(
				"partyConstructionFiles.findPartyConstructionFiles", query,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, partyConstructionFiles);
	}

	private PageInfo<PartyConstructionFiles> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<PartyConstructionFiles> pageInfo = new PageInfo<PartyConstructionFiles>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PartyConstructionFiles getPartyConstructionFilesById(Long id) {
		return (PartyConstructionFiles) getSqlMapClientTemplate().queryForObject(
				"partyConstructionFiles.getPartyConstructionFilesById", id);
	}

	@Override
	public void deletePartyConstructionFiles(Long id) {
		getSqlMapClientTemplate().delete("partyConstructionFiles.deletePartyConstructionFiles", id);

	}

	@Override
	public List<PartyConstructionFiles> searchPartyConstructionFilesByTitleAndOrgId(String title,
			Long orgId, String sidx, String sord) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("title", title);
		query.put("sortField", sidx);
		query.put("order", sord);
		return getSqlMapClientTemplate().queryForList(
				"partyConstructionFiles.searchPartyConstructionFilesByTitleAndOrgId", query);
	}

	@Override
	public PartyConstructionFiles getPartyConstructionFilesByTitleAndOrgId(String title, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("title", title);
		return (PartyConstructionFiles) getSqlMapClientTemplate().queryForObject(
				"partyConstructionFiles.getPartyConstructionFilesByTitleAndOrgId", query);
	}

	@Override
	public PartyConstructionFiles updatePartyConstructionFilesBaseInfo(
			PartyConstructionFiles partyConstructionFiles) {
		getSqlMapClientTemplate().update(
				"partyConstructionFiles.updatePartyConstructionFilesBaseInfo",
				partyConstructionFiles);
		partyConstructionFiles = getPartyConstructionFilesById(partyConstructionFiles.getId());
		return partyConstructionFiles;
	}

}
