package com.tianque.issue.event.listener;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.issue.dao.IssueDao;
import com.tianque.issue.dao.IssueLogDao;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.uitl.IssueAttachFileRetuenUtil;
import com.tianque.issue.uitl.IssueToCMSUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 保存事件的处理记录
 */
@Repository("savingOperationLog")
public class SavingOperationLog extends NothingDoIssueChangeListener {
	Logger logger = LoggerFactory.getLogger(SavingOperationLog.class);
	@Autowired
	private IssueLogDao issueLogDao;
	@Autowired
	protected IssueDao issueDao;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Override
	public void onEntry(IssueNew issue, IssueStep step) {
		IssueLogNew log = new IssueLogNew();
		log.setDealDescription("新增  本事件");
		log.setDealOrg(getCurrentLoginedOrganization());
		log.setTargeOrg(getCurrentLoginedOrganization());
		log.setDealUserName(getCurrentLoginedUserName());
		log.setMobile(ThreadVariable.getUser().getMobile());
		log.setDealTime(step.getEntryDate());
		log.setIssue(issue);
		log.setCreateUser(getCurrentLoginedUserName());
		log.setCreateDate(CalendarUtil.now());
		log.setIssueStep(step);
		issueLogDao.addLog(log);
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps,
			IssueChangeEvent event) {
		IssueLogNew log = event.getOperateLog();
		/** 直接赋值TargeOrgId为DealOrgId */
		log.setTargeOrg(log.getDealOrg());
		if (currentOrgChanged(event)) {
			log.setTargeOrg(steps.getKeyStep().getTarget());
		}
		log.setDealDescription(assembleDesc(steps, event.getOperate()));
		log.setIssue(issue);
		log.setIssueStep(steps.getKeyStep());

		log = issueLogDao.addLog(log);
		fillIssueAttachFile(issue, log, event.getOperateFiles());
		IssueAttachFileRetuenUtil attachFileRetuen = issueDao
				.addIssueAttachFiles(event.getOperateFiles());
		dealCallCenterData(issue, log, steps, attachFileRetuen);
	}

	public void dealCallCenterData(IssueNew issue, IssueLogNew log,
			IssueStepGroup steps, IssueAttachFileRetuenUtil attachFileRetuen) {
		if (issue.getFromSerialNumber() != null || log.isReport12345()) {
			List<Long> attachFileIds = attachFileRetuen.getAttachFileId();
			List<String> attachFileNames = attachFileRetuen.getAttachFileName();
			String issueAttachFiles = "";
			if (attachFileIds != null) {
				Integer filesidsSize = attachFileIds.size();
				for (int i = 0; i < filesidsSize; i++) {
					issueAttachFiles += attachFileIds.get(i).toString() + "|"
							+ attachFileNames.get(i).toString() + "，";
				}
			}
			Organization org = organizationDubboService.getSimpleOrgById(log
					.getDealOrg().getId());
			MultipartEntity data = new MultipartEntity();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date uDate = log.getDealTime();
			Date sDate = new Date(uDate.getTime());
			String strDate = df.format(sDate);

			try {
				if (issue.getFromSerialNumber() != null && !log.isReport12345()) {
					data.addPart(
							"demandsCode",
							new StringBody(issue.getFromSerialNumber(), Charset
									.forName("UTF-8")));
				}
				if (strDate != null) {
					data.addPart("transactionDate", new StringBody(strDate,
							Charset.forName("UTF-8")));
				}
				if (org != null) {
					data.addPart("orgName", new StringBody(org.getOrgName(),
							Charset.forName("UTF-8")));
					data.addPart(
							"ordId",
							new StringBody(Long.toString(org.getId()), Charset
									.forName("UTF-8")));
				} else {
					Organization cmsOrganization = IssueToCMSUtil
							.getLocationOrgNameByLocationId(log.getDealOrg()
									.getId());
					data.addPart("orgName",
							new StringBody(cmsOrganization.getOrgName(),
									Charset.forName("UTF-8")));
					data.addPart(
							"ordId",
							new StringBody(Long.toString(cmsOrganization
									.getId()), Charset.forName("UTF-8")));
				}
				if (log.getDealUserName() != null) {
					data.addPart(
							"dealUserName",
							new StringBody(log.getDealUserName(), Charset
									.forName("UTF-8")));
				}
				if (log.getDealType() != null) {
					data.addPart("dealType",
							new StringBody(Long.toString(log.getDealType()),
									Charset.forName("UTF-8")));
				}
				if (log.getMobile() != null) {
					data.addPart("mobile", new StringBody(log.getMobile(),
							Charset.forName("UTF-8")));
				}
				if (log.getDealDescription() != null) {
					data.addPart(
							"dealDescription",
							new StringBody(log.getDealDescription(), Charset
									.forName("UTF-8")));
				}
				if (log.getContent() != null) {
					data.addPart("content", new StringBody(log.getContent(),
							Charset.forName("UTF-8")));
				}
				if (issueAttachFiles != null) {
					data.addPart("issueAttachFiles", new StringBody(
							issueAttachFiles, Charset.forName("UTF-8")));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (!log.isReport12345()) {
				IssueToCMSUtil.post(
						GridProperties.CMS_CALLCENTER_ISSUE_TRANSACTION_LOG,
						data);
			} else {
				steps.getKeyStep().setMultipartEntityData(data);
			}
		}
	}

	@Override
	public void onVerification(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
		IssueLogNew log = event.getOperateLog();
		Organization targetOrg = log.getTargeOrg();
		log.setDealDescription(IssueOperate.VERIFICATION.getDesc() + "本事件");
		log.setIssue(issue);
		log.setIssueStep(step);
		log = issueLogDao.addLog(log);
		log.setTargeOrg(targetOrg);
		fillIssueAttachFile(issue, log, event.getOperateFiles());
		issueDao.addIssueAttachFiles(event.getOperateFiles());
	}

	private String assembleDesc(IssueStepGroup steps, IssueOperate operate) {
		StringBuilder sb = new StringBuilder();
		if ("上报".equals(operate.getDesc())) {
			sb.append("上报  本事件  至  ");
		} else if ("受理".equals(operate.getDesc())) {
			sb.append("受理  本事件  ");
		} else if ("新增".equals(operate.getDesc())) {
			sb.append("新增  本事件  ");
		} else if ("结案".equals(operate.getDesc())) {
			sb.append("结案  本事件  ");
		} else if ("验证".equals(operate.getDesc())) {
			sb.append("验证  本事件  ");
		} else if ("回退".equals(operate.getDesc())) {
			sb.append("回退  本事件  至   ");
		} else if ("交办".equals(operate.getDesc())) {
			sb.append("交办  本事件  至   ");
		} else if ("阅读".equals(operate.getDesc())) {
			sb.append("阅读  本事件  ");
		} else {
			sb.append(operate.toString() + "  ");
		}
		if (!"结案".equals(operate.getDesc())) {
			sb.append(descNeedContainOrgName(operate) ? steps.getKeyStep()
					.getTarget().getOrgName() : "");
		}
		if (steps != null && steps.hasIncidentalStep()) {
			sb.append(" 并抄告给 ");
			for (IssueStep step : steps.getIncidentalSteps()) {
				sb.append(step.getTarget().getOrgName()).append("、");
			}
			if (sb.toString().lastIndexOf('、') >= 0) {
				sb = sb.replace(sb.length() - 1, sb.length(), "");
			}
		}
		return sb.toString();
	}

	private boolean descNeedContainOrgName(IssueOperate operate) {
		return !(IssueOperate.CONCEPT.equals(operate)
				|| IssueOperate.CANCEL_HISTORIC.equals(operate)
				|| IssueOperate.HISTORIC.equals(operate)
				|| IssueOperate.CANCEL_URGENT.equals(operate)
				|| IssueOperate.URGENT.equals(operate)
				|| IssueOperate.CANCEL_SUPERVISE.equals(operate)
				|| IssueOperate.COMMENT.equals(operate) || IssueOperate.READ
				.equals(operate));
	}

	private void fillIssueAttachFile(IssueNew issue, IssueLogNew log,
			List<IssueAttachFile> files) {
		if (files != null && !files.isEmpty()) {
			for (IssueAttachFile issueAttachFile : files) {
				issueAttachFile.setIssue(issue);
				issueAttachFile.setIssueLog(log);
			}
		}

	}
}
