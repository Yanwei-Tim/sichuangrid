package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVo;
import com.tianque.issue.vo.IssueViewObject;

public interface SearchServiceDaoNew {
	PageInfo<IssueViewObject> searchServicesByOrgInternalCode(SearchIssueVo searchServicesVo,
			Integer page, Integer rows, String sidx, String sord);

	PageInfo<IssueViewObject> searchSecuritytroubleByOrgInternalCode(
			SearchIssueVo searchSecuritytroubleVo, Integer page, Integer rows, String sidx,
			String sord);

	PageInfo<IssueViewObject> searchServicesByOrgId(SearchIssueVo searchServicesVo, Integer page,
			Integer rows, String sidx, String sord);

}
