package com.tianque.dao;

import java.util.List;

import com.tianque.domain.PartyOrgActivityFile;

/**
 * 党组织活动附件数据接口
 */
public interface PartyOrgActivityFileDao {

	/**
	 * 新增党组织活动记录附件
	 * 
	 * @param partyOrgActivityFile
	 *        党组织活动记录附件
	 * @return partyOrgActivityFile 党组织活动记录附件
	 */
	public PartyOrgActivityFile addPartyOrgActivityFile(PartyOrgActivityFile partyOrgActivityFile);

	/**
	 * 根据ID获取党组织活动记录附件
	 * 
	 * @param id
	 *        党组织活动记录附件ID
	 * @return PartyOrgActivityFile 党组织活动记录附件
	 */
	public PartyOrgActivityFile getPartyOrgActivityFileById(Long id);

	/**
	 * 根据党组织ID获取活动记录附件
	 * 
	 * @param orgActivityId
	 *        党组织ID
	 * @return List<PartyOrgActivityFile> 党组织活动记录附件集合
	 */
	public List<PartyOrgActivityFile> getPartyOrgActivityFileByOrgActivityId(Long orgActivityId);

	/**
	 * 根据ID删除党组织活动附件信息
	 * 
	 * @param id
	 *        党组织活动附件信息ID
	 */
	public void deletePartyOrgActivityFileById(Long id);

	/**
	 * 根据党组织活动记录ID删除附件信息
	 * 
	 * @param orgActivityId
	 *        党组织活动信息ID
	 */
	public void deletePartyOrgActivityFileByOrgActivityId(Long orgActivityId);

}
