package com.tianque.baseInfo.primaryOrg.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.primaryOrg.dao.FourLevelPlatformDao;
import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.domain.vo.SearchFourLevelPlatformVo;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;

/**
 * 四级平台表:数据操作层
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
@Repository("fourLevelPlatformDao")
public class FourLevelPlatformDaoImpl extends
		BaseDaoImpl<FourLevelPlatform, SearchFourLevelPlatformVo> implements
		FourLevelPlatformDao {

	@Override
	public Integer getCount(SearchFourLevelPlatformVo searchFourLevelPlatformVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourLevelPlatform.countFourLevelPlatformsBySearchVo",
				searchFourLevelPlatformVo);
	}

	@Override
	public Integer countFourLevelPlatformByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourLevelPlatform.countFourLevelPlatformByOrgId", orgId);
	}

	@Override
	public int deleteFourLevelPlatform(Long deleteId) {
		return getSqlMapClientTemplate().delete(
				"fourLevelPlatform.deleteFourLevelPlatformById", deleteId);
	}

}
