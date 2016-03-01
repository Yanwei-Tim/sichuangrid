package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.domain.vo.GisCountNumVo;
import com.tianque.gis.domain.vo.IssueNewsVo;
import com.tianque.gis.service.SearchGisIssueNewsService;
import com.tianque.gis.util.GisCountTypeMapUtil;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.state.IssueState;
import com.tianque.service.IssueNewService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("searchGisIssueNewsService")
public class SearchGisIssueNewsServicImpl implements SearchGisIssueNewsService {
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public List<GisCountNumVo> countActualHouseByOrgId(Long orgId) {
		List<IssueNew> list = issueNewService.countActualHouseByOrgId(orgId);
		List<GisCountNumVo> gisCountNumVos = new ArrayList<GisCountNumVo>();
		for (IssueNew issueNew : list) {
			GisCountNumVo gisCountNumVo = new GisCountNumVo();
			gisCountNumVo.setGiscountNum(issueNew.getGiscountNum());
			gisCountNumVo.setDisplayName(issueNew.getGisSearchType());
			gisCountNumVo.setTypeCatalogName(GisCountTypeMapUtil
					.getGisCountType(issueNew.getGisSearchType()));
			gisCountNumVos.add(gisCountNumVo);
		}
		return gisCountNumVos;
	}

	// 列表检索
	@Override
	public PageInfo<IssueNewsVo> issueNewsListSearchByQueryStrAndOrgId(
			Long orgId, String queryStr, Integer page, Integer rows,
			String sidx, String sord) {
		PageInfo<IssueNew> issPageInfo = issueNewService
				.issueNewsListSearchByQueryStrAndOrgId(orgId, queryStr, page,
						rows, sidx, sord);
		return exchangeIssueNewToIssueNewsVoPageInfo(issPageInfo);
	}

	// 列表查询
	@Override
	public PageInfo<IssueNewsVo> searchKeyIssueNewsListByOrgId(Long orgId,
			String issueNewsType, Integer page, Integer rows, String sidx,
			String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("Gis事件处理列表搜索 时，获取orgid失败");
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			PageInfo<IssueNew> issPageInfo = issueNewService
					.searchKeyIssueNewsListByOrgCode(org.getOrgInternalCode(),
							GisCountTypeMapUtil
									.getGisIssueNewFilds(issueNewsType),
							issueNewsType, page, rows, sidx, sord);
			return exchangeIssueNewToIssueNewsVoPageInfo(issPageInfo);
		}
	}

	// 二次过滤
	@Override
	public PageInfo<IssueNewsVo> gisIssueNewsFutureSearchByOrgId(Long orgId,
			String issueNewsType, String issueNewsState, Integer page,
			Integer rows, String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("gis事件处理二次过滤异常,orgId获取失败！");
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			PageInfo<IssueNew> issPageInfo = issueNewService
					.gisIssueNewsFutureSearchByOrgCode(
							org.getOrgInternalCode(), GisCountTypeMapUtil
									.getGisIssueNewFilds(issueNewsType),
							issueNewsType, issueNewsState, page, rows, sidx,
							sord);
			return exchangeIssueNewToIssueNewsVoPageInfo(issPageInfo);
		}
	}

	private PageInfo<IssueNewsVo> exchangeIssueNewToIssueNewsVoPageInfo(
			PageInfo<IssueNew> issPageInfo) {
		List<IssueNew> issueNews = issPageInfo.getResult();
		List<IssueNewsVo> issueNewsVos = new ArrayList<IssueNewsVo>();
		for (IssueNew issueNew : issueNews) {
			IssueNewsVo issueNewsVo = new IssueNewsVo();
			if (issueNew != null && issueNew.getGisInfo() != null
					&& issueNew.getGisInfo().getCenterX() != null) {
				issueNewsVo.setGisInfo(issueNew.getGisInfo());
				issueNewsVo.setEnableBind(true);
			} else {
				issueNewsVo.setEnableBind(false);
			}
			issueNewsVo.setOrgId(issueNew.getOccurOrg().getId());
			issueNewsVo.setIssueId(issueNew.getId());
			issueNewsVo.setKeyPersonType(issueNew.getKeyPersonType());
			issueNewsVo.setOccurDate(issueNew.getOccurDate());
			issueNewsVo.setOccurLocation(issueNew.getOccurLocation());
			issueNewsVo.setSubject(issueNew.getSubject());
			issueNewsVos.add(issueNewsVo);
		}
		PageInfo<IssueNewsVo> pageInfo = new PageInfo<IssueNewsVo>();
		pageInfo.setResult(issueNewsVos);
		pageInfo.setTotalRowSize(issPageInfo.getTotalRowSize());
		return pageInfo;
	}

	@Override
	public List<IssueNewsVo> getIssueNewsDatialInfoByIssueId(Long orgId,
			Long issueId) {
		List<IssueNew> issueNews = issueNewService
				.getIssueNewsDatialInfoByIssueId(orgId, issueId);
		return shifViewIssueNewToIssueNewVo(issueNews, "issuseNew");
	}

	private List<IssueNewsVo> shifViewIssueNewToIssueNewVo(
			List<IssueNew> issueNews, String issueNewsType) {
		List<IssueNewsVo> issueNewsVos = new ArrayList<IssueNewsVo>();
		for (IssueNew issueNew : issueNews) {
			IssueNewsVo issueNewsVo = new IssueNewsVo();
			issueNewsVo.setSubject(issueNew.getSubject());
			issueNewsVo.setOccurLocation(issueNew.getOccurLocation());
			issueNewsVo.setOccurDate(issueNew.getOccurDate());
			issueNewsVo.setMaincharacters(issueNew.getMainCharacters());
			issueNewsVo.setCurrentOrgDisplayName(organizationDubboService
					.getSimpleOrgById(
							null == issueNew.getCurrentOrg() ? issueNew
									.getOccurOrg().getId() : issueNew
									.getCurrentOrg().getId()).getOrgName());
			if (null != issueNew.getStatus()) {
				if (issueNew.getStatus() == IssueState.ISSUECOMPLETE_CODE) {
					issueNewsVo.setStatecode(GisCountTypeMapUtil
							.gisCountState(issueNew.getStatus()));
				} else {
					issueNewsVo.setStatecode(GisCountTypeMapUtil.DEALING);
				}
			}
			issueNewsVo.setDomainname(issueNew.getDomainname());
			issueNewsVo.setKeyPersonType(issueNewsType);
			issueNewsVo.setGisInfo(issueNew.getGisInfo());
			if ("layerIssue".equals(issueNewsType)) {
				issueNewsVo.setIssueId(issueNew.getId());
				issueNewsVo.setOrgId(issueNew.getOccurOrg().getId());
			}
			issueNewsVos.add(issueNewsVo);
		}
		return issueNewsVos;
	}

	@Override
	public List<IssueNewsVo> findAllBindingIssueNewsByOrgId(Long orgId) {
		if (null == orgId || orgId < 1L) {
			return new ArrayList<IssueNewsVo>();
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		List<IssueNew> issueNews = issueNewService
				.findAllBindingIssueNewsByOrgInternalCode(org
						.getOrgInternalCode());
		return shifViewIssueNewToIssueNewVo(issueNews, "layerIssue");
	}

}
