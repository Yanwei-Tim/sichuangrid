package com.tianque.peopleLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.dao.PeopleLogDao;
import com.tianque.peopleLog.dao.SubLogDao;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("SubLogService")
public class SubLogServiceImpl extends LogableService implements SubLogService {

	@Autowired
	private SubLogDao subLogDao;

	@Autowired
	private PeopleLogDao peopleLogDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sortField, String sord,
			boolean isPeer) {
		try {
			String orgCode = organizationDubboService.getSimpleOrgById(orgId)
					.getOrgInternalCode();
			if (isPeer) {
				return subLogDao.findPeopleLogForPage(orgCode, pageNum,
						pageSize, sortField, sord);
			} else {
				return subLogDao.findPeopleLogForPageByOrgInternalCode(orgCode,
						pageNum, pageSize, sortField, sord);
			}
		} catch (Exception e) {
			return dealException(this, "findSubLogListByOrgInternalCode",
					"查找下辖日志数据错误", e);
		}
	}

	public PeopleLog findLogByLogId(Long logId) {
		try {
			return subLogDao.findLogByLogId(logId);
		} catch (Exception e) {
			return dealException(this, "findLogByLogId", "根据日志Id查找下辖日志数据错误", e);
		}
	}

	@Transactional
	@Override
	public CommentLog addComments(CommentLog comment) {
		try {
			subLogDao.addComment(comment);
			PeopleLog p = peopleLogDao.getPeopleLogById(comment.getLogId());
			if (p.getCommentNums() == null) {
				p.setCommentNums(0);
			}
			p.setCommentNums(p.getCommentNums() + 1);
			peopleLogDao.commentPeopleLog(p.getCommentNums(), p.getId());

			return subLogDao.findCommentLogById(comment.getId());
		} catch (Exception e) {
			return dealException(this, "addComments", "新增点评数据错误", e);
		}

	}

	@Override
	public PageInfo<PeopleLog> findSubLogListByOrgInternalCode4Bench(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, boolean isPeer) {
		try {
			if ("orgName".equals(sidx)) {
				sidx = "orgInternalCode";
			}
			if (isPeer) {
				return subLogDao.findSubLogListByOrgInternalCode4All(
						orgInternalCode, page, rows, sidx, sord);
			} else {
				return subLogDao.findSubLogListByOrgInternalCode4Me(
						orgInternalCode, page, rows, sidx, sord);
			}
		} catch (Exception e) {
			return dealException(this, "findSubLogListByOrgInternalCode4Bench",
					"查找下辖日志数据错误", e);
		}
	}

}
