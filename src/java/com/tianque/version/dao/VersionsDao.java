package com.tianque.version.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.version.domain.Versions;

/**
 * @author 杨立忠
 * @version V1.0
 * @创建时间：2013-5-13 下午02:18:58
 */
public interface VersionsDao {
	/**
	 * 新增版本信息
	 * 
	 * @param versions
	 * @return
	 */
	public void addVersion(Versions versions);

	/**
	 * 删除版本信息
	 * 
	 * @param versionId
	 */

	public void deleteVersion(String versionId);

	/**
	 * 修改版本信息
	 * 
	 * @param versions
	 * @return
	 */

	public void updateVersion(Map map);

	/**
	 * 获得列表
	 * 
	 * @return
	 */

	public List<Versions> getAllVersions();

	/**
	 * 根据版本号获得版本信息
	 * 
	 * @param versionId
	 * @return
	 */

	public Versions getVersionsById(String versionId);

	/**
	 * 分页获取版本列表
	 * 
	 * @return
	 */

	PageInfo<Versions> findGridPage(int pageNum, int pageSize, String sortField, String order);
}
