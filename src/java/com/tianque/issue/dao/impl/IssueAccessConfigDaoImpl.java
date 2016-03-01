package com.tianque.issue.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.IssueAccessConfigDao;
import com.tianque.issue.domain.IssueAccessConfig;

@Repository("issueAccessConfigDao")
public class IssueAccessConfigDaoImpl extends AbstractBaseDao implements
		IssueAccessConfigDao {
	@Autowired
	private CacheService cacheService;

	@Override
	public IssueAccessConfig addIssueAccessConfig(IssueAccessConfig bean) {
		getSqlMapClientTemplate().insert(
				"issueAccessConfig.addIssueAccessConfig", bean);
		return getIssueAccessConfig();
	}

	@Override
	public IssueAccessConfig getIssueAccessConfig() {
		IssueAccessConfig config = (IssueAccessConfig) cacheService
				.get(CacheKeyGenerator.generateCacheKeyFromString(
						IssueAccessConfig.class, "issueAccessConfig"));
		if (config == null) {
			config = (IssueAccessConfig) getSqlMapClientTemplate()
					.queryForObject("issueAccessConfig.getIssueAccessConfig");
			cacheService.set(CacheKeyGenerator.generateCacheKeyFromString(
					IssueAccessConfig.class, "issueAccessConfig"), config);
		}
		return config;
	}

	@Override
	public IssueAccessConfig updateIssueAccessConfig(IssueAccessConfig bean) {
		if (getSqlMapClientTemplate().update(
				"issueAccessConfig.updateIssueAccessConfig", bean) <= 0) {
			throw new BusinessValidationException(
					"类IssueAccessConfigDaoImpl中方法updateIssueAccessConfig：新增失败！");
		}
		cacheService.remove(CacheKeyGenerator.generateCacheKeyFromString(
				IssueAccessConfig.class, "issueAccessConfig"));
		return getIssueAccessConfig();
	}

}
