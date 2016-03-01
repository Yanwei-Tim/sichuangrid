package com.tianque.sms.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.job.JobHelper;
import com.tianque.sms.dao.MobileShortMessageDAO;
import com.tianque.sms.dao.SmsUplinkDao;
import com.tianque.sms.dao.SmsdownlinkDao;
import com.tianque.sms.domain.DownlinkMobileMessage;
import com.tianque.sms.domain.MobileShortMessage;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.service.MessageTableService;
import com.tianque.sysadmin.service.JobMonitorService;

@Component("smsDownlinkJob")
public class SmsDownlinkJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(SmsDownlinkJob.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private SmsdownlinkDao smsdownlinkDao;
	@Autowired
	private SmsUplinkDao smsUplinkDao;
	@Autowired
	private MobileShortMessageDAO mobileShortMessageDAO;
	@Autowired
	private MessageTableService messageTableService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		smsDownlinkJobDo();
	}

	private void smsDownlinkJobDo() {
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());

		try {
			updateUplink();
			addSmsDownlink();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "短信收件箱日志信息job！", true);
		} catch (Exception e) {
			logger.error("短信收件箱日志信息job执行错误:", e);
			jobMonitorService.updateJobMonitor(id,
					"短信收件箱日志信息job执行错误:" + e.getMessage(), false);
		}

	}

	private void updateUplink() throws Exception {
		SmsUplink smsUplink = smsUplinkDao.getSmsUplinkByMinIdAndProcessed();
		if (null == smsUplink) {
			return;
		}
		String tabName = messageTableService.getInsertTableName(
				smsUplink.getMessageId(), 1);
		List<MobileShortMessage> msgList = mobileShortMessageDAO
				.getSendTableList(tabName, smsUplink.getId(),
						MobileShortMessage.WAITING_TO_SEND);
		updateSmsUplink(msgList);
	}

	private void addSmsDownlink() throws Exception {
		Long bigId = smsdownlinkDao.getSmsdownlinkBigId();
		if (bigId == null) {
			bigId = 0L;
		}
		String downTabName = messageTableService.getInsertTableName(
				bigId == null ? 0L : bigId + 1L, 2);
		List<DownlinkMobileMessage> downlinkList = mobileShortMessageDAO
				.getSmsDownlinkList(bigId, downTabName);
		if (null != downlinkList && downlinkList.size() > 0) {
			smsdownlinkDao
					.addSmsdownlinkByBatch(createSmsdownlinkList(downlinkList));

		}
	}

	private void updateSmsUplink(List<MobileShortMessage> msgList) {
		if (null == msgList) {
			return;
		}
		List<SmsUplink> smsUplinkList = new ArrayList<SmsUplink>();
		for (MobileShortMessage msg : msgList) {
			smsUplinkList.add(createSmsUplink(msg));
		}
		smsUplinkDao.updateSmsUplinkStatusByBatch(smsUplinkList);
	}

	private List<Smsdownlink> createSmsdownlinkList(
			List<DownlinkMobileMessage> downList) {
		List<Smsdownlink> downlinkList = new ArrayList<Smsdownlink>();
		Long bigId = 0L;
		for (DownlinkMobileMessage down : downList) {
			downlinkList.add(createSmsdownlink(down));
			bigId = (Long) down.getId() > bigId ? (Long) down.getId() : bigId;
		}
		Long oldBigId = smsdownlinkDao.getSmsdownlinkBigId();
		if (null == oldBigId) {
			smsdownlinkDao.addSmsdownlinkBigId(bigId);
		} else {
			smsdownlinkDao.updateSmsdownlinkBigId(bigId);
		}
		return downlinkList;
	}

	private Smsdownlink createSmsdownlink(DownlinkMobileMessage down) {
		Smsdownlink smsdownlink = new Smsdownlink();
		smsdownlink.setContent(down.getMessage());
		smsdownlink.setSender(down.getSender());
		smsdownlink.setReceiverMobile(down.getReceiver());
		smsdownlink.setSendTime(down.getReceiveTime());
		return smsdownlink;
	}

	private SmsUplink createSmsUplink(MobileShortMessage message) {
		SmsUplink smsUplink = new SmsUplink();
		int sendEnd = MobileShortMessage.SENT_TO_END;
		Long status = 0L;
		if (message.getStatus() < sendEnd) {
			status = 3L;
		} else {
			status = message.getStatus() > sendEnd ? 5L : 4L;
		}
		smsUplink.setStatus(status);
		smsUplink.setId(message.getFromId());
		return smsUplink;
	}
}
