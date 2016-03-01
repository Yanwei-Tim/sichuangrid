package com.tianque.resourcePool.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.vo.SearchDirectorySetting;

public interface DirectorySettingDao extends BaseDao<DirectorySetting, SearchDirectorySetting> {
	/**
	 * 根据登录的用户userId 查该用户的所有目录
	 * 
	 * @param userId
	 * @return
	 */
	public List<DirectorySetting> findDirectorySettingForList(Long userId);

	/**
	 * 查找父节点为NULL的子节点
	 * 
	 * @param userId
	 *        用戶id
	 * @return
	 */
	public List<DirectorySetting> findTrunkDirectoryByUserId(Long userId);

	public List<DirectorySetting> findchildByparentId(Long parentId, Long userId);

	/**
	 * 根据父节点parentId查找该父节点的所有子目录
	 * 
	 * @param parentId
	 * @return
	 */

	public List<DirectorySetting> findDirectorySettingByParentId(Long parentId);

	/**
	 * 通过目录类型查找目录
	 */
	public List<DirectorySetting> findDirectorySettingByDirectoryType(int directoryType);

	/**
	 * 根据ID 查找对应的目录记录
	 * 
	 * @param id
	 * @return
	 */
	public DirectorySetting findDirectorySettingById(Long id);

	/**
	 * 通过父目录ID查找最大的序号
	 */
	public Integer getMaxIndexId(Long parentId);

	/**
	 * 根据资料类型ID获取该类型的文件个数(从文件表中查询)
	 * 
	 * @param resourcePoolTypeId
	 * @return
	 */
	public Integer getCountByResourcePoolType(Long resourcePoolTypeId);

	/**
	 * 根据indexId ,parentId 获取子目录indexId小于传入indexId的最大ID
	 * 
	 * @param indexId
	 * @param parentId
	 *        父id
	 * @return
	 */
	public Long getDirectoryIdByparentIdAndindexId(Integer indexId, Long parentId, Long userId);

	/**
	 * 根据indexId ,parentId 获取子目录idindexId大于传入indexId的最小ID
	 * 
	 * @param indexId
	 * @param parentId
	 * @return
	 */
	public Long getMinIdByParentIdAndLargerThanIndexId(Integer indexId, Long parentId, Long userId);

	/**
	 * 根据输入的关键字在目录名称中查找符合的记录列表
	 * 
	 * @param value
	 * @return
	 */
	public List<DirectorySetting> searchDirectorySetting(String value);

	/**
	 * 上下移动修改排序
	 * 
	 * @param index
	 *        序号
	 * @param id
	 */
	public void updateMove(Integer index, Long id);

	/**
	 * 根据目录ID array 获得 List<DirectorySetting>
	 * 
	 * @param array
	 * @return
	 */
	public List<DirectorySetting> findDirectorySettingByIdArray(List<String> array);

}
