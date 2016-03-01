package com.tianque.baseInfo.primaryOrg.service;

import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.domain.vo.SearchFourLevelPlatformVo;
import com.tianque.core.base.BaseService;

/**
 * 四级平台表:业务逻辑层接口
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
public interface FourLevelPlatformService extends
		BaseService<FourLevelPlatform, SearchFourLevelPlatformVo> {

	/**
	 * 统计总数据
	 * 
	 * @param searchFourLevelPlatformVo
	 * @return
	 */
	Integer getCount(SearchFourLevelPlatformVo searchFourLevelPlatformVo);

	/**
	 * 根据id统计数据
	 * 
	 * @param orgId
	 * @return
	 */
	Integer countFourLevelPlatformByOrgId(Long orgId);

	/**
	 * 根据ids删除四级平台
	 * 
	 * @param ids
	 * @return
	 */
	int deleteFourLevelPlatformByIds(String ids, Long isFourLevelPlatform);

}
