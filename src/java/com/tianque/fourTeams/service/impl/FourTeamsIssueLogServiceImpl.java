package com.tianque.fourTeams.service.impl;

import java.util.List;

import org.apache.struts2.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.dao.FourTeamsIssueAttachFileDao;
import com.tianque.fourTeams.dao.FourTeamsIssueLogDaoNew;
import com.tianque.fourTeams.dao.FourTeamsIssueNewDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.fourTeams.service.FourTeamsIssueLogService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueStateType;

@Service("fourTeamsIssueLogService")
@Transactional
public class FourTeamsIssueLogServiceImpl implements FourTeamsIssueLogService {

	@Autowired
	private FourTeamsIssueBusinessDelegate issueBusinessDelegate;
	@Autowired
	private FourTeamsIssueLogDaoNew issueLogDao;
	@Autowired
	private FourTeamsIssueNewDao issueNewDao;

	@Autowired
	private FourTeamsIssueAttachFileDao issueAttachFileDao;

	@Override
	public FourTeamsIssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId) {
		return issueLogDao.findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
				issueId, targeorgId, dealorgId);
	}

	@Override
	public FourTeamsIssueLogNew concept(FourTeamsIssueLogNew issueLogVo) {
		FourTeamsIssueLogNew forIssueLog = issueLogDao
				.getIssueLogById(issueLogVo.getId());
		if (isDealedIssueLog(forIssueLog)) {
			throw new BusinessValidationException("该服务事项已经处理，无须重复处理");
		}
		forIssueLog.setDealState(IssueDealState.STEP_COMPLETE);

		issueLogVo.setTargeOrg(issueLogVo.getDealOrg());
		issueBusinessDelegate.autoFillIssueLog(issueLogVo, forIssueLog,
				IssueDealState.DOING);

		FourTeamsIssueNew issueNew = issueBusinessDelegate.getModifyIssueField(
				forIssueLog.getIssue().getId(), issueLogVo.getTargeOrg()
						.getOrgName(), issueLogVo.getDealUserName(), issueLogVo
						.getDealOrg(), issueLogVo.getDealOrgInternalCode(),
				IssueStateType.PROCESSING);

		return issueBusinessDelegate.concept(issueBusinessDelegate
				.createIssueContext(forIssueLog, issueLogVo, issueNew));
	}

	@Override
	public List<FourTeamsIssueLogNew> findIssueLogsByIssueId(Long issueId) {
		if (issueId == null) {
			throw new BusinessValidationException("参数不对");
		}
		return issueLogDao.findIssueLogsByIssueId(issueId);
	}

	@Override
	public void command(FourTeamsIssueLogNew issueLogVo) {
		FourTeamsIssueLogNew forIssueLog = issueLogDao
				.getIssueLogById(issueLogVo.getId());
		if (forIssueLog == null) {
			throw new BusinessValidationException("参数不对");
		}
		issueLogVo.setTargeOrg(forIssueLog.getTargeOrg());
		issueBusinessDelegate.autoFillIssueLog(issueLogVo, forIssueLog,
				IssueDealState.COMMAND);

		issueLogDao.addIssueLog(issueLogVo);
	}

	@Override
	public FourTeamsIssueViewObject superviseIssueLog(
			FourTeamsIssueLogNew issueLogVo, int points, String superviseType)
			throws JSONException {
		FourTeamsIssueLogNew issueLogNew = issueLogDao
				.getIssueLogById(issueLogVo.getId());
		if (issueLogNew == null)
			throw new BusinessValidationException("参数不正确");
		FourTeamsIssueLogNew issueLog = issueBusinessDelegate
				.superviseIssueLog(issueLogNew, issueLogVo);
		if (issueLog == null) {
			throw new BusinessValidationException("督办失败");
		} else {
			issueLogDao.updateSupervisionState(issueLogNew.getId(),
					issueLog.getDealType());
			issueBusinessDelegate.deductPoints(issueLog, points, superviseType);
		}
		return issueNewDao.getViewIssueViewObjectById(issueLogNew.getId());
	}

	@Override
	public FourTeamsIssueViewObject cancelSuperviseIssueLog(
			FourTeamsIssueLogNew issueLogVo) {
		FourTeamsIssueLogNew issueLogNew = issueLogDao
				.getIssueLogById(issueLogVo.getId());
		if (issueLogNew == null)
			throw new BusinessValidationException("参数不正确");
		FourTeamsIssueLogNew issueLog = issueBusinessDelegate
				.superviseIssueLog(issueLogNew, issueLogVo);
		if (issueLog == null) {
			throw new BusinessValidationException("取消督办失败");
		} else {
			issueLogDao.updateSupervisionState(issueLogNew.getId(),
					issueLog.getDealType());
		}
		return issueNewDao.getViewIssueViewObjectById(issueLogNew.getId());
	}

	@Override
	public FourTeamsIssueLogNew getFullIssueLogById(Long id) {
		return issueLogDao.getIssueLogById(id);
	}

	@Override
	public FourTeamsIssueLogNew getIssueLogByIdAndSuperviseState(
			Long issueLogId, Long superviseState, Long doneState,
			Long completeState) {
		return issueLogDao.getIssueLogByIdAndSuperviseState(issueLogId,
				superviseState, doneState, completeState);
	}

	@Override
	public List findIssueLogAttachFilesById(Long issueId) {
		return issueAttachFileDao.findIssueLogAttachFilesByIssueId(issueId);
	}

	@Override
	public FourTeamsIssueLogNew findLastOperationLog(Long issueId, Long orgId,
			Long state) {
		return issueLogDao.getLastOperationLog(issueId, orgId, state);
	}

	private void saveIssueAttachFiles(FourTeamsIssueNew issue,
			FourTeamsIssueLogNew log, String[] files) throws Exception {
		if (files == null || files.length == 0)
			return;
		for (String fileName : files) {
			StoredFile file = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.ISSUE_ATTACHFILE);
			String fileActualPath = file.getStoredFilePath() + "/"
					+ file.getStoredFileName();
			FourTeamsIssueAttachFile attachFile = new FourTeamsIssueAttachFile();
			attachFile.setFileActualUrl(fileActualPath);
			attachFile.setFileName(fileName);
			attachFile.setIssue(issue);
			attachFile.setIssueLog(log);
			issueAttachFileDao.addIssueAttachFile(attachFile);
		}

	}

	@Override
	public FourTeamsIssueAttachFile getIssueAttachFileById(Long id) {
		return issueAttachFileDao.getIssueAttachFileById(id);
	}

	private boolean isDealedIssueLog(FourTeamsIssueLogNew log) {
		return log.getDealState() == null ? false
				: ((log.getDealState() != null && log.getDealState()
						.longValue() > 1000) ? false : true);
	}

	@Override
	public FourTeamsIssueLogNew getCompleteLogByIssueId(Long issueId) {
		return issueLogDao.getCompleteIssueLogNewByIssueId(issueId);
	}

}
