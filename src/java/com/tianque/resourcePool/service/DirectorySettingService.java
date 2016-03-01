package com.tianque.resourcePool.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.vo.SearchDirectorySetting;

public interface DirectorySettingService extends
		BaseService<DirectorySetting, SearchDirectorySetting> {

	public List<DirectorySetting> findchildByparentId(Long parentId);

	public List<DirectorySetting> findDirectorySettingByParentId(Long parentId);

	public List<DirectorySetting> findDirectorySettingByDirectoryType(int directoryType);

	public DirectorySetting findDirectorySettingById(Long id);

	/**
	 * 通过父目录ID查找最大的序号
	 */
	public Integer getMaxIndexByParnetId(Long parentId);

	/**
	 * 根据资料类型ID获取该类型的文件个数
	 * 
	 * @param resourcePoolTypeId
	 * @return
	 */
	public Integer getCountByResourcePoolType(Long resourcePoolTypeId);

	/**
	 * 根据index ,parentId 获取子目录id小于传入ID的最大ID
	 */
	public Long getDirectoryIdByparentIdAndindexId(Integer index, Long parentId);

	/**
	 * 根据indexId ,parentId 获取子目录idindex小于传入indexId的最大ID
	 */
	public Long getMinIdByparentIdAndindexId(Integer index, Long parentId);

	/**
	 * 检索
	 * 
	 * @param value
	 * @return
	 */
	public List<DirectorySetting> SearchDirectorySetting(String value);

	/**
	 * 通过ID一级一级往上查找父节点treeId
	 * 
	 * @param id
	 * @return
	 */
	public List<Long> searchParentNodeIds(Long id);

	/**
	 * 通过ID一级一级往上查找父节点jqTreeGrid
	 * 
	 * @param id
	 * @return
	 */
	public List<Long> searchListParentNodeIds(Long id);

	public void updateMove(DirectorySetting directorySetting, String moveMode);

	/**
	 * 根据目录id数组获得 DirectorySetting
	 * 
	 * @param shareDirectoryIds
	 */
	public List<DirectorySetting> findDirectorySettingByIdArray(List<String> array);
}
