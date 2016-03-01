package com.tianque.service.impl;

import java.util.List;

import org.apache.struts2.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.dao.IssueAttachFileDao;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueNewDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.service.IssueHistoryService;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.IssueLogService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueStateType;

@Service("issueLogService")
@Transactional
public class IssueLogServiceImpl implements IssueLogService {

	private static Logger logger = LoggerFactory
			.getLogger(IssueLogServiceImpl.class);

	@Autowired
	private IssueBusinessDelegate issueBusinessDelegate;
	@Autowired
	private IssueLogDaoNew issueLogDao;
	@Autowired
	private IssueNewDao issueNewDao;

	@Autowired
	private IssueAttachFileDao issueAttachFileDao;
	@Autowired
	private IssueHistoryService issueHistoryService;

	@Override
	public IssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId) {
		return issueLogDao.findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
				issueId, targeorgId, dealorgId);
	}

	@Override
	public IssueLogNew concept(IssueLogNew issueLogVo) {
		IssueLogNew forIssueLog = issueLogDao.getIssueLogById(issueLogVo
				.getId());
		if (isDealedIssueLog(forIssueLog)) {
			throw new BusinessValidationException("该服务事项已经处理，无须重复处理");
		}
		forIssueLog.setDealState(IssueDealState.STEP_COMPLETE);

		issueLogVo.setTargeOrg(issueLogVo.getDealOrg());
		issueBusinessDelegate.autoFillIssueLog(issueLogVo, forIssueLog,
				IssueDealState.DOING);

		IssueNew issueNew = issueBusinessDelegate.getModifyIssueField(
				forIssueLog.getIssue().getId(), issueLogVo.getTargeOrg()
						.getOrgName(), issueLogVo.getDealUserName(), issueLogVo
						.getDealOrg(), issueLogVo.getDealOrgInternalCode(),
				IssueStateType.PROCESSING);

		return issueBusinessDelegate.concept(issueBusinessDelegate
				.createIssueContext(forIssueLog, issueLogVo, issueNew));
	}

	@Override
	public List<IssueLogNew> findIssueLogsByIssueId(Long issueId) {
		if (issueId == null) {
			throw new BusinessValidationException("参数不对");
		}
		List<IssueLogNew> issueLogNewList = issueLogDao
				.findIssueLogsByIssueId(issueId);
		// 事件日志表中查询为null，查询历史事件日志表
		if (issueLogNewList == null || issueLogNewList.size() == 0) {
			issueLogNewList = issueHistoryService
					.loadIssueOperationLogsHistoryByIssueId(issueId);
		}
		return issueLogNewList;
	}

	@Override
	public void command(IssueLogNew issueLogVo) {
		IssueLogNew forIssueLog = issueLogDao.getIssueLogById(issueLogVo
				.getId());
		if (forIssueLog == null) {
			throw new BusinessValidationException("参数不对");
		}
		issueLogVo.setTargeOrg(forIssueLog.getTargeOrg());
		issueBusinessDelegate.autoFillIssueLog(issueLogVo, forIssueLog,
				IssueDealState.COMMAND);

		issueLogDao.addIssueLog(issueLogVo);
	}

	@Override
	public IssueViewObject superviseIssueLog(IssueLogNew issueLogVo,
			int points, String superviseType) throws JSONException {
		IssueLogNew issueLogNew = issueLogDao.getIssueLogById(issueLogVo
				.getId());
		if (issueLogNew == null)
			throw new BusinessValidationException("参数不正确");
		IssueLogNew issueLog = issueBusinessDelegate.superviseIssueLog(
				issueLogNew, issueLogVo);
		if (issueLog == null) {
			throw new BusinessValidationException("督办失败");
		} else {
			try {
				issueLogDao.updateSupervisionState(issueLogNew.getId(),
						issueLog.getDealType());
				issueBusinessDelegate.deductPoints(issueLog, points,
						superviseType);
			} catch (Exception e) {
				logger.error("异常信息", e);
			}
		}
		return issueNewDao.getViewIssueViewObjectById(issueLogNew.getId());
	}

	@Override
	public IssueViewObject cancelSuperviseIssueLog(IssueLogNew issueLogVo) {
		IssueLogNew issueLogNew = issueLogDao.getIssueLogById(issueLogVo
				.getId());
		if (issueLogNew == null)
			throw new BusinessValidationException("参数不正确");
		IssueLogNew issueLog = issueBusinessDelegate.superviseIssueLog(
				issueLogNew, issueLogVo);
		if (issueLog == null) {
			throw new BusinessValidationException("取消督办失败");
		} else {
			issueLogDao.updateSupervisionState(issueLogNew.getId(),
					issueLog.getDealType());
		}
		return issueNewDao.getViewIssueViewObjectById(issueLogNew.getId());
	}

	@Override
	public IssueLogNew getFullIssueLogById(Long id) {
		return issueLogDao.getIssueLogById(id);
	}

	@Override
	public IssueLogNew getIssueLogByIdAndSuperviseState(Long issueLogId,
			Long superviseState, Long doneState, Long completeState) {
		return issueLogDao.getIssueLogByIdAndSuperviseState(issueLogId,
				superviseState, doneState, completeState);
	}

	@Override
	public List findIssueLogAttachFilesById(Long issueId) {
		return issueAttachFileDao.findIssueLogAttachFilesByIssueId(issueId);
	}

	@Override
	public IssueLogNew findLastOperationLog(Long issueId, Long orgId, Long state) {
		return issueLogDao.getLastOperationLog(issueId, orgId, state);
	}

	private void saveIssueAttachFiles(IssueNew issue, IssueLogNew log,
			String[] files) throws Exception {
		if (files == null || files.length == 0)
			return;
		for (String fileName : files) {
			StoredFile file = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.ISSUE_ATTACHFILE);
			String fileActualPath = file.getStoredFilePath() + "/"
					+ file.getStoredFileName();
			IssueAttachFile attachFile = new IssueAttachFile();
			attachFile.setFileActualUrl(fileActualPath);
			attachFile.setFileName(fileName);
			attachFile.setIssue(issue);
			attachFile.setIssueLog(log);
			issueAttachFileDao.addIssueAttachFile(attachFile);
		}

	}

	@Override
	public IssueAttachFile getIssueAttachFileById(Long id) {
		return issueAttachFileDao.getIssueAttachFileById(id);
	}

	private boolean isDealedIssueLog(IssueLogNew log) {
		return log.getDealState() == null ? false
				: ((log.getDealState() != null && log.getDealState()
						.longValue() > 1000) ? false : true);
	}

	@Override
	public IssueLogNew getCompleteLogByIssueId(Long issueId) {
		return issueLogDao.getCompleteIssueLogNewByIssueId(issueId);
	}

	@Override
	public void changeIssueLogByIssueId(IssueLogNew log) {
		issueLogDao.changeIssueLogByIssueId(log);
	}

}
