package com.tianque.peopleLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.dao.SearchCommentLogDao;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("searchCommentLogService")
@Transactional
public class SearchCommentLogServiceImpl extends AbstractBaseService implements
		SearchCommentLogService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchCommentLogDao searchCommentLogDao;

	@Override
	public PageInfo<CommentLog> findCommentLogByCondition(
			SearchPeopleLogVo searchPeopleLogVo, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			return searchCommentLogDao.findCommentLogByCondition(
					searchPeopleLogVo, page, rows, sidx, sord);
		} catch (Exception e) {
			return dealException(this, "findCommentLogByCondition", "查找数据错误", e);
		}
	}

	@Override
	public PageInfo<PeopleLog> findSubLogByQueryCondition(Long orgId,
			SearchPeopleLogVo searchPeopleLogVo, Integer page, Integer rows,
			String sidx, String sord, boolean isPeer) {
		try {
			String orgInternalCode = organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode();
			if (!isPeer) {
				return searchCommentLogDao.findSubLogByQueryConditionIsPeer(
						orgInternalCode, searchPeopleLogVo, page, rows, sidx,
						sord);
			} else {
				return searchCommentLogDao.findSubLogByQueryConditionAll(
						orgInternalCode, searchPeopleLogVo, page, rows, sidx,
						sord);
			}
		} catch (Exception e) {
			return dealException(this, "findSubLogByQueryCondition", "查找数据错误",
					e);
		}
	}

}
