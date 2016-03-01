package com.tianque.sms.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.User;
import com.tianque.job.JobHelper;
import com.tianque.sms.dao.MobileShortMessageDAO;
import com.tianque.sms.dao.SmsUplinkDao;
import com.tianque.sms.domain.MobileShortMessage;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.vo.SmsJobSqlVo;
import com.tianque.sms.domain.vo.SmsSendObjectVo;
import com.tianque.sms.util.SmsGlobalValue;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;

@Component("smsUplinkJob")
public class SmsUplinkJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(SmsUplinkJob.class);

	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private SmsUplinkDao smsUplinkDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	MobileShortMessageDAO mobileShortMessageDAO;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		smsUplinkJobDo();
	}

	private void smsUplinkJobDo() {
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());

		try {
			addSmsUplink();
			addMobileShortMessage();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "短信发件箱日志信息job！", true);
		} catch (Exception e) {
			logger.error("短信发件箱日志信息job执行错误:", e);
			jobMonitorService.updateJobMonitor(id,
					"短信发件箱日志信息job执行错误:" + e.getMessage(), false);
		}

	}

	private void addSmsUplink() {
		List<SmsJobSqlVo> voList = smsUplinkDao.findSmsJobSqlVoBySmsLevel();
		if (null == voList || voList.size() <= 0) {
			return;
		}
		for (SmsJobSqlVo vo : voList) {
			smsUplinkDao.addSmsUplinkByBatch(createSmsUplinkList(vo));
			vo.setSmsLevel(-1L);
			smsUplinkDao.updateSmsJobSqlVo(vo);
		}

	}

	private List<SmsUplink> createSmsUplinkList(SmsJobSqlVo jobVo) {
		List<SmsSendObjectVo> smsSendList = smsUplinkDao
				.findSmsSendObject(jobVo.getSql());
		List<SmsUplink> smsUplinkList = new ArrayList();
		if (null == smsSendList || smsSendList.size() <= 0) {
			return smsUplinkList;
		}
		User user = permissionService.getSimpleUserById(jobVo.getSender()
				.getId());
		for (SmsSendObjectVo sendVo : smsSendList) {
			smsUplinkList.add(createSmsUplink(user, sendVo, jobVo));
		}
		return smsUplinkList;
	}

	private SmsUplink createSmsUplink(User user, SmsSendObjectVo sendVo,
			SmsJobSqlVo jobVo) {
		SmsUplink smsUplink = new SmsUplink();
		smsUplink.setParentId(jobVo.getSmsuplinkId());
		smsUplink.setReceiverName(sendVo.getName());
		smsUplink.setReceiverMobile(sendVo.getMobile());
		smsUplink.setReceiverOrgId(sendVo.getOrgId().getId());
		smsUplink.setReceiverOrgInternalCode(sendVo.getOrgInternalCode());
		smsUplink.setSender(user);
		smsUplink.setSenderName(user.getName());
		smsUplink.setSenderOrgId(user.getOrganization().getId());
		smsUplink.setSenderOrgInternalCode(user.getOrgInternalCode());
		smsUplink.setMobileType(getMobileType(sendVo.getMobile()));
		smsUplink.setSmsLevel(jobVo.getSmsLevel());
		smsUplink.setContent(jobVo.getContent());
		smsUplink.setSendTime(new Date());
		return smsUplink;
	}

	private void addMobileShortMessage() throws Exception {
		for (int frequency = 0; frequency < 3; frequency++) {
			List<SmsUplink> smsList = smsUplinkDao
					.getSmsUplinkListBySendStatus(3000);
			if (null == smsList || smsList.size() <= 0) {
				break;
			}
			for (SmsUplink smsUplink : smsList) {
				MobileShortMessage message = mobileShortMessageDAO
						.add(createMobileShortMessage(smsUplink));
				if (null != message) {
					smsUplink.setMessageId(message.getId());
					smsUplink.setStatus(3L);// 处理中
				}
			}
			smsUplinkDao.updateSmsUplinkStatusByBatch(smsList);
		}
	}

	private MobileShortMessage createMobileShortMessage(SmsUplink smsUplink) {
		MobileShortMessage msg = new MobileShortMessage();
		msg.setFromId(smsUplink.getId());
		msg.setFromSystem(SmsGlobalValue.SMS_SYSTEN_NAME);
		msg.setMessage(smsUplink.getContent());
		msg.setReceiver(smsUplink.getReceiverMobile());
		msg.setSender(smsUplink.getSenderName());
		msg.setCreateDate(new Date());
		setMobileType(msg);
		msg.setPriority(smsUplink.getSmsLevel() == null ? 1 : smsUplink
				.getSmsLevel().intValue());
		return msg;
	}

	private MobileShortMessage setMobileType(MobileShortMessage message) {
		int type = getMobileType(message.getReceiver());
		if (type != 3) {
			message.setType(type);
			return message;
		}
		if (message.getReceiver().length() > 6
				&& !message.getReceiver().substring(0, 1).equals("1")
				&& message.getReceiver().length() <= 12) {
			if (!message.getReceiver().substring(0, 1).equals("0")) {
				if (message.getReceiver().length() >= 7
						&& message.getReceiver().length() <= 8) {
					message.setReceiver("0580" + message.getReceiver());
				} else {
					message.setReceiver("0" + message.getReceiver());
				}
			}
			message.setType(3);
		} else {
			message.setType(0);
		}
		return message;
	}

	private int getMobileType(String mobile) {
		int b = 0;
		int type = 0;
		try {
			b = Integer.parseInt(mobile.substring(0, 3));
		} catch (Exception e) {
			b = 0;
		}
		switch (b) {
		case 134:
		case 135:
		case 136:
		case 137:
		case 138:
		case 139:
		case 147:
		case 150:
		case 151:
		case 152:
		case 157:
		case 158:
		case 159:
		case 182:
		case 187:
		case 188:
			type = 1;
			break;
		case 133:
		case 153:
		case 180:
		case 189:
			type = 4;
			break;
		case 130:
		case 131:
		case 132:
		case 155:
		case 156:
		case 185:
		case 186:
			type = 2;
			break;
		default:
			type = 3;
			break;

		}
		return type;
	}
}
