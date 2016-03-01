package com.tianque.baseInfo.primaryOrg.dao;

import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.domain.vo.SearchFourLevelPlatformVo;
import com.tianque.core.base.BaseDao;

/**
 * 四级平台表:数据操作层接口
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
public interface FourLevelPlatformDao extends
		BaseDao<FourLevelPlatform, SearchFourLevelPlatformVo> {

	/**
	 * 统计总数据
	 * 
	 * @param searchFourLevelPlatformVo
	 * @return
	 */
	Integer getCount(SearchFourLevelPlatformVo searchFourLevelPlatformVo);

	/**
	 * 根据组织机构id统计数据
	 * 
	 * @param orgId
	 * @return
	 */
	Integer countFourLevelPlatformByOrgId(Long orgId);

	/**
	 * 删除数据
	 * 
	 * @param deleteId
	 * @return int 删除条数
	 */
	int deleteFourLevelPlatform(Long deleteId);

}
