package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueLogDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

/**
 * 保存事件的处理记录
 */
@Repository("fourTeamsSavingOperationLog")
public class FourTeamsSavingOperationLog extends FourTeamsNothingDoIssueChangeListener {
	@Autowired
	private FourTeamsIssueLogDao issueLogDao;
	@Autowired
	protected FourTeamsIssueDao issueDao;

	@Override
	public void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {
		FourTeamsIssueLogNew log = new FourTeamsIssueLogNew();
		log.setDealDescription("新增");
		log.setDealOrg(getCurrentLoginedOrganization());
		log.setDealTime(step.getEntryDate());
		log.setDealUserName(getCurrentLoginedUserName());
		log.setIssue(issue);
		log.setMobile(ThreadVariable.getUser().getMobile());
		log.setCreateUser(getCurrentLoginedUserName());
		log.setCreateDate(CalendarUtil.now());
		log.setIssueStep(step);
		issueLogDao.addLog(log);
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
		FourTeamsIssueLogNew log = event.getOperateLog();
		if (currentOrgChanged(event)) {
			log.setTargeOrg(steps.getKeyStep().getTarget());
		}
		log.setDealDescription(assembleDesc(steps, event.getOperate()));
		log.setIssue(issue);
		log.setIssueStep(steps.getKeyStep());
		log = issueLogDao.addLog(log);
		fillIssueAttachFile(issue, log, event.getOperateFiles());
		issueDao.addIssueAttachFiles(event.getOperateFiles());
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		FourTeamsIssueLogNew log = event.getOperateLog();
		Organization targetOrg = log.getTargeOrg();
		log.setDealDescription(FourTeamsIssueOperate.COMPLETE.getDesc());
		log.setIssue(issue);
		log.setIssueStep(step);
		log = issueLogDao.addLog(log);
		log.setTargeOrg(targetOrg);
		fillIssueAttachFile(issue, log, event.getOperateFiles());
		issueDao.addIssueAttachFiles(event.getOperateFiles());
	}

	private String assembleDesc(FourTeamsIssueStepGroup steps, FourTeamsIssueOperate operate) {
		StringBuilder sb = new StringBuilder();
		sb.append(operate.toString());
		sb.append(descNeedContainOrgName(operate) ? steps.getKeyStep()
				.getTarget().getOrgName() : "");
		if (steps != null && steps.hasIncidentalStep()) {
			sb.append(" 并抄告给 ");
			for (FourTeamsIssueStep step : steps.getIncidentalSteps()) {
				sb.append(step.getTarget().getOrgName()).append(" ");
			}
		}
		return sb.toString();
	}

	private boolean descNeedContainOrgName(FourTeamsIssueOperate operate) {
		return !(FourTeamsIssueOperate.CONCEPT.equals(operate)
				|| FourTeamsIssueOperate.CANCEL_HISTORIC.equals(operate)
				|| FourTeamsIssueOperate.HISTORIC.equals(operate)
				|| FourTeamsIssueOperate.CANCEL_URGENT.equals(operate)
				|| FourTeamsIssueOperate.URGENT.equals(operate)
				|| FourTeamsIssueOperate.CANCEL_SUPERVISE.equals(operate) || FourTeamsIssueOperate.COMMENT
				.equals(operate));
	}

	private void fillIssueAttachFile(FourTeamsIssueNew issue, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> files) {
		if (files != null && !files.isEmpty()) {
			for (FourTeamsIssueAttachFile issueAttachFile : files) {
				issueAttachFile.setIssue(issue);
				issueAttachFile.setIssueLog(log);
			}
		}

	}
}
