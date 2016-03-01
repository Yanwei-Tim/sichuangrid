package com.tianque.version.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.version.domain.Versions;

/**
 * @author 杨立忠
 * @version V1.0
 * @创建时间：2013-5-13 下午03:09:49
 */
public interface VersionsService {
	/**
	 * 新增版本信息
	 * 
	 * @param versions
	 * @return
	 */
	public Versions addVersion(Versions versions);

	/**
	 * 修改版本信息
	 * 
	 * @param versions
	 * @return
	 */

	public void updateVersion(Versions versions, String versionId);

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
