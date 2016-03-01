package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueAccessConfigDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccessConfig;

@Repository("fourTeamsIssueAccessConfigDao")
public class FourTeamsIssueAccessConfigDaoImpl extends AbstractBaseDao
		implements FourTeamsIssueAccessConfigDao {
	@Autowired
	private CacheService cacheService;

	@Override
	public FourTeamsIssueAccessConfig addIssueAccessConfig(
			FourTeamsIssueAccessConfig bean) {
		getSqlMapClientTemplate().insert(
				"issueAccessConfig.addIssueAccessConfig", bean);
		return getIssueAccessConfig();
	}

	@Override
	public FourTeamsIssueAccessConfig getIssueAccessConfig() {
		FourTeamsIssueAccessConfig config = (FourTeamsIssueAccessConfig) cacheService
				.get(CacheKeyGenerator.generateCacheKeyFromString(
						FourTeamsIssueAccessConfig.class, "issueAccessConfig"));
		if (config == null) {
			config = (FourTeamsIssueAccessConfig) getSqlMapClientTemplate()
					.queryForObject("issueAccessConfig.getIssueAccessConfig");
			cacheService.set(CacheKeyGenerator.generateCacheKeyFromString(
					FourTeamsIssueAccessConfig.class, "issueAccessConfig"),
					config);
		}
		return config;
	}

	@Override
	public FourTeamsIssueAccessConfig updateIssueAccessConfig(
			FourTeamsIssueAccessConfig bean) {
		if (getSqlMapClientTemplate().update(
				"issueAccessConfig.updateIssueAccessConfig", bean) <= 0) {
			throw new BusinessValidationException(
					"类IssueAccessConfigDaoImpl中方法updateIssueAccessConfig：新增失败！");
		}
		cacheService.remove(CacheKeyGenerator.generateCacheKeyFromString(
				FourTeamsIssueAccessConfig.class, "issueAccessConfig"));
		return getIssueAccessConfig();
	}

}
