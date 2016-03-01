package com.tianque.gis.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.GisCountNumVo;
import com.tianque.gis.domain.vo.IssueNewsVo;

public interface SearchGisIssueNewsService {
	public List<GisCountNumVo> countActualHouseByOrgId(Long orgId);

	public PageInfo<IssueNewsVo> issueNewsListSearchByQueryStrAndOrgId(Long orgId, String queryStr,
			Integer page, Integer rows, String sidx, String sord);

	public List<IssueNewsVo> getIssueNewsDatialInfoByIssueId(Long orgId, Long issueId);

	public PageInfo<IssueNewsVo> searchKeyIssueNewsListByOrgId(Long orgId, String issueNewsType,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueNewsVo> gisIssueNewsFutureSearchByOrgId(Long orgId, String issueNewsType,
			String issueNewsState, Integer page, Integer rows, String sidx, String sord);

	public List<IssueNewsVo> findAllBindingIssueNewsByOrgId(final Long orgId);
}
