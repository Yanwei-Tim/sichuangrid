package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyConstructionFiles;

public interface PartyConstructionFilesDao {

	public PartyConstructionFiles addPartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles);

	public PageInfo<PartyConstructionFiles> findPartyConstructionFilesByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord, String dalei);

	public PartyConstructionFiles updatePartyConstructionFilesBaseInfo(
			PartyConstructionFiles partyConstructionFiles);

	public PartyConstructionFiles getPartyConstructionFilesById(Long id);

	public void deletePartyConstructionFiles(Long id);

	/**
	 * 根据title来查询所有信息
	 * 
	 * @param title
	 * @param orgId
	 * @return
	 */
	public List<PartyConstructionFiles> searchPartyConstructionFilesByTitleAndOrgId(String title,
			Long orgId, String sidx, String sord);

	public PartyConstructionFiles getPartyConstructionFilesByTitleAndOrgId(String title, Long orgId);
}
