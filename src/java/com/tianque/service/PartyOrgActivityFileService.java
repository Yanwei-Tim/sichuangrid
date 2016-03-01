package com.tianque.service;

import java.util.List;

import com.tianque.domain.PartyOrgActivityFile;

/**
 * 本级党建党组织活动记录附件接口
 */
public interface PartyOrgActivityFileService {

	/**
	 * 新增党组织活动记录附件
	 * 
	 * @param orgActivityId
	 *            党组织ID
	 * @param attachFiles
	 *            党组织活动记录附件
	 * @return List<PartyOrgActivityFile> 党组织活动记录附件
	 */
	public List<PartyOrgActivityFile> addPartyOrgActivityFile(
			Long orgActivityId, String[] attachFiles);

	/**
	 * 根据ID获取党组织活动记录附件
	 * 
	 * @param id
	 *            党组织活动记录附件ID
	 * @return PartyOrgActivityFile 党组织活动记录附件
	 */
	public PartyOrgActivityFile getPartyOrgActivityFileById(Long id);

	/**
	 * 根据ID获取党组织活动记录附件
	 * 
	 * @param orgActivityId
	 *            党组织ID
	 * @return List<PartyOrgActivityFile> 党组织活动记录附件集合
	 */
	public List<PartyOrgActivityFile> getPartyOrgActivityFileByOrgActivityId(
			Long orgActivityId);

	/**
	 * 修改党组织活动记录附件
	 * 
	 * @param orgActivityId
	 *            党组织ID
	 * @param attachFiles
	 *            党组织活动记录附件
	 */
	public List<PartyOrgActivityFile> updatePartyOrgActivityFile(
			Long orgActivityId, String[] attachFiles);

	/**
	 * 根据ID删除组织活动记录附件
	 * 
	 * @param id
	 *            党组织活动信息ID
	 */
	public void deletePartyOrgActivityFileById(Long id);

	/**
	 * 根据ID删除组织活动记录附件
	 * 
	 * @param id
	 *            党组织活动信息ID
	 */
	public void deletePartyOrgActivityFileByOrgActivityId(
			List<Long> orgActivityIds);
}
