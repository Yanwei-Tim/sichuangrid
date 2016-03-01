package com.tianque.plugin.weChat.service.impl;

import com.tianque.baseInfo.primaryOrg.dao.RedCuffTeamDao;
import com.tianque.baseInfo.primaryOrg.domain.RedCuffTeam;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.job.JobHelper;
import com.tianque.plugin.weChat.dao.SmsAccountDao;
import com.tianque.plugin.weChat.dao.SmsSendGroupDao;
import com.tianque.plugin.weChat.dao.SmsSendResultDao;
import com.tianque.plugin.weChat.domain.sms.SmsAccount;
import com.tianque.plugin.weChat.domain.sms.SmsSendGroup;
import com.tianque.plugin.weChat.domain.sms.SmsSendResult;
import com.tianque.plugin.weChat.service.SmsSendGroupService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("smsSendGroupService")
@Transactional
public class SmsSendGroupServiceImpl implements SmsSendGroupService {
	private static Logger logger = LoggerFactory.getLogger(SmsSendGroupServiceImpl.class);
	@Autowired
	private SmsSendGroupDao smsSendGroupDao;
	@Autowired
	private RedCuffTeamDao redCuffTeamDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SmsAccountDao smsAccountDao;
	@Autowired
	private SmsSendResultDao smsSendResultDao;

	private static Map<String, String> smsCallbackState = new HashMap<String, String>();

	static {
		smsCallbackState.put("DELIVRD", "短消息转发成功");
		smsCallbackState.put("EXPIRED", "短短消息超过有效期转发成功");
		smsCallbackState.put("UNDELIV", "短消息是不可达的");
		smsCallbackState.put("UNKNOWN", "未知短消息状态");
		smsCallbackState.put("REJECTD", "短消息被短信中心拒绝");
		smsCallbackState.put("DTBLACK", "目的号码是黑名单号码");
		smsCallbackState.put("ERR:104", "系统忙");
		smsCallbackState.put("REJECT", "审核驳回");
		smsCallbackState.put("其他", "短信网关内部状态");
	}

	private static final String SMS_URL = "http://web.cr6868.com/asmx/smsservice.aspx";

	@Override
	public Long sendSaveSmsSendGroup(SmsSendGroup smsSendGroup, String[] phoneNumber) {
		if (smsSendGroup == null || smsSendGroup.getOrg() == null
				|| smsSendGroup.getOrg().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (StringUtil.isBlank(smsSendGroup.getSmsContent())) {
			throw new BusinessValidationException("短信内容不能为空");
		}
		Organization org = organizationDubboService.getSimpleOrgById(smsSendGroup.getOrg().getId());
		SmsAccount smsAccount = smsAccountDao.getById(smsSendGroup.getSenderAccountId());
		if (smsAccount == null) {
			throw new BusinessValidationException("未找到短信账号");
		}
		if (!ThreadVariable.getOrganization().getOrgInternalCode()
				.equals(smsAccount.getOrg().getOrgInternalCode())) {
			throw new BusinessValidationException("短信账号不属于当前用户的组织层级");
		}
		// ======接收消息的手机号 start ======
		StringBuilder receiverMobile = new StringBuilder();
		int total = 0;
		if (phoneNumber != null) {
			for (String str : phoneNumber) {
				if (StringUtil.isBlank(str)) {
					continue;
				}
				if (receiverMobile.length() > 0) {
					receiverMobile.append(",");
				}
				receiverMobile.append(str);
				total++;
			}
		}
		// ====== 接收消息的手机号 end ======

//			if (StringUtil.isBlank(smsSendGroup.getReceiverRedCuffTeamType())) {
//				throw new BusinessValidationException("请选择红袖套成员类别");
//			}

		// ====== 红袖套队员手机号 start ======
		// 根据类别查出红袖套成员
		if (StringUtils.isNotBlank(smsSendGroup.getReceiverRedCuffTeamType())) {
			String type = smsSendGroup.getReceiverRedCuffTeamType().replaceAll(" ", "");
			List<RedCuffTeam> redCuffTeamList = redCuffTeamDao.getRedCuffTeamListByTeamType(
					org.getOrgInternalCode(), type.split(","), null);
			if (redCuffTeamList == null || redCuffTeamList.isEmpty()) {
				throw new BusinessValidationException("所选类别没有红袖套成员");
			}
			// 发送手机号拼接
			for (RedCuffTeam r : redCuffTeamList) {
				if (StringUtil.isBlank(r.getPhoneNumber())) {
					continue;
				}
				if (receiverMobile.length() > 0) {
					receiverMobile.append(",");
				}
				receiverMobile.append(r.getPhoneNumber());
				total++;
			}
		}
		// ====== 红袖套队员手机号 end ======
		if (total == 0) {
			throw new BusinessValidationException("没有消息接收手机号");
		}
		// 短信服务发送
		Map<String, String> smsParams = new HashMap<String, String>();
		smsParams.put("type", "pt");// 固定值
		smsParams.put("name", smsAccount.getName());
		smsParams.put("pwd", smsAccount.getPwd());
		smsParams.put("content", smsSendGroup.getSmsContent());
		smsParams.put("mobile", receiverMobile.toString());
		String smsResult = HttpClientUtils.postProxyToOutside(SMS_URL, smsParams);
		// 提交成功:    状态,发送编号,无效号码数,成功提交数,黑名单数和消息
		// 失败:        只有状态和消息
		String[] smsResultArray = smsResult.split(",");
		if ("0".equals(smsResultArray[0])) {// 
			smsSendGroup.setSmsSendId(smsResultArray[1]);
			smsSendGroup.setFailNumber(Integer.parseInt(smsResultArray[2])
					+ Integer.parseInt(smsResultArray[4]));
		} else {
			throw new BusinessValidationException("短信发送失败：" + smsResultArray[1]);
		}
		// 发送记录保存
		smsSendGroup.setTotalNumber(total);
		smsSendGroup.setSuccessNumber(0);
		smsSendGroup.setReceiverMobile(receiverMobile.toString());
		smsSendGroup.setSenderAccountName(smsAccount.getName());
		smsSendGroup.setSenderUserId(ThreadVariable.getUser().getId());
		smsSendGroup.setSenderUserName(ThreadVariable.getUser().getName());
		return smsSendGroupDao.saveSmsSendGroup(smsSendGroup);
	}

	@Override
	public SmsSendGroup getById(Long id) {
		return smsSendGroupDao.getById(id);
	}

	@Override
	public PageInfo<SmsSendGroup> findSmsSendGroup(SmsSendGroup smsSendGroup, Integer pageNum,
												   Integer pageSize, String sidx, String sord) {
		if (smsSendGroup == null || smsSendGroup.getOrg() == null
				|| smsSendGroup.getOrg().getOrgInternalCode() == null) {
			throw new BusinessValidationException("未获取到组织机构");
		}
		return smsSendGroupDao.findSmsSendGroup(smsSendGroup, pageNum, pageSize, sidx, sord);
	}

	@Override
	public boolean smsSendCallBack(String name, String pwd, String sendId, String time,
								   String mobile, String state) {
		//		logger.error(MessageFormat.format("短信发送结果回调：sendId:{0},time:{1},mobile:{2},state:{3}",
		//				sendId, time, mobile, state));
		JobHelper.createMockAdminSession();
		SmsAccount smsAccount = smsAccountDao.getSmsAccountByNameAndOrgCode(name, null);
		if (pwd == null || smsAccount == null || !pwd.equals(smsAccount.getCallbackPwd())) {
			String error = "短信回调账户密码验证错误：" + name;
			logger.error(error);
			throw new BusinessValidationException(error);
		}
		// 组结果更新
		SmsSendGroup smsSendGroup = smsSendGroupDao.getBySmsSendId(sendId);
		if (smsSendGroup == null) {
			logger.error("未知的短信sendId：" + sendId);
			return false;
		}
		String[] mobileArray = mobile.split(",");
		if ("DELIVRD".equals(state)) {
			smsSendGroup.setSuccessNumber(smsSendGroup.getSuccessNumber() + mobileArray.length);
		} else {
			smsSendGroup.setFailNumber(smsSendGroup.getFailNumber() + mobileArray.length);
		}
		smsSendGroup.setLastResultDate(new Date());
		smsSendGroupDao.updateSmsSendGroup(smsSendGroup);
		// 单个结果保存
		state = smsCallbackState.get(state);
		List<SmsSendResult> smsSendResultList = new ArrayList<SmsSendResult>(mobileArray.length);
		for (String m : mobileArray) {
			if (StringUtil.isBlank(m)) {
				continue;
			}
			SmsSendResult smsSendResult = new SmsSendResult();
			smsSendResult.setGroupId(smsSendGroup.getId());
			smsSendResult.setSmsSendId(sendId);
			smsSendResult.setState(state);
			smsSendResult.setMobile(m);
			smsSendResult.setCreateUser("system");
			smsSendResult.setCreateDate(new Date());
			smsSendResultList.add(smsSendResult);
		}
		smsSendResultDao.saveSmsSendResult(smsSendResultList);
		return true;
	}

	@Override
	public PageInfo<SmsSendResult> findSmsSendResult(SmsSendResult smsSendResult, Integer pageNum,
													 Integer pageSize, String sidx, String sord) {
		return smsSendResultDao.findSmsSendResult(smsSendResult, pageNum, pageSize, sidx, sord);
	}

	@Override
	public List<RedCuffTeam> getRedCuffTeamNameAndPhoneListByOrgCode(String orgCode) {
		return redCuffTeamDao.getRedCuffTeamNameAndPhoneListByOrgCode(orgCode);
	}

	@Override
	public int countRedCuffTeamListByTeamType(String orgCode, String type) {
		type = type.replaceAll(" ", "");
		return redCuffTeamDao.countRedCuffTeamListByTeamType(orgCode, type.split(","), null);
	}

}
