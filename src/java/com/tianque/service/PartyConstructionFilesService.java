package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyConstructionFiles;

public interface PartyConstructionFilesService {

	/**
	 * 添加一条新经济组织信息
	 * 
	 * @param PartyConstructionFiles
	 * @return PartyConstructionFiles
	 **/
	public PartyConstructionFiles addPartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles);

	/**
	 * 根据id获取新经济组织对象
	 * 
	 * @param id
	 * @return
	 */
	public PartyConstructionFiles getPartyConstructionFilesById(Long id);

	public PageInfo<PartyConstructionFiles> findPartyConstructionFilesByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord, String dalei);

	public PartyConstructionFiles updatePartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles);

	public boolean deletePartyConstructionFilesById(Long id);

	public void deletePartyConstructionFilessByIdList(List<Long> idList);

	public List<PartyConstructionFiles> findPartyConstructionFilesByorgIdAndTitle(Long orgId,
			String title, Integer pageNum, String sidx, String sord);

	public boolean hasDuplicatePartyConstructionFilesByTitle(String title, Long orgId,
			Long exceptedId);

}
