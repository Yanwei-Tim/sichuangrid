package com.tianque.xichang.working.flow.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.WorkCalendarService;
import com.tianque.xichang.working.domain.CommonWorking;
import com.tianque.xichang.working.flow.constant.FinishType;
import com.tianque.xichang.working.flow.constant.HandleType;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.domain.AccountStep;
import com.tianque.xichang.working.flow.service.AccountLogsService;
import com.tianque.xichang.working.flow.service.AccountStepService;
import com.tianque.xichang.working.flow.service.AccountWarningService;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.service.ParametertimelimitService;

@Service("accountWarningService")
public class AccountWarningServiceImpl implements AccountWarningService {

	@Autowired
	private AccountLogsService accountLogsService;
	@Autowired
	private ParametertimelimitService parametertimelimitService;
	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private AccountStepService accountStepService;

	private int getUnDoHandleType(CommonWorking account, String accountType,
			Long orgId, Integer isFinish) {
		if (account == null) {
			throw new BusinessValidationException("参数错误");
		}
		Parametertimelimit parametertimelimit = parametertimelimitService
				.getParametertimelimitByOrgId(orgId);

		List<AccountLogs> list = accountLogsService
				.findAccountLogsByAccountIdAndAccountType(account.getId(),
						accountType, orgId);
		AccountStep step = accountStepService
				.getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
						account.getId(), accountType, orgId, isFinish);
		if (null == list || list.size() == 0) {
			Integer workDays = workCalendarService
					.getWorkDaysFromStartTimeToEndTime(step.getCreateDate(),
							new Date());
			if (workDays > parametertimelimit.getHandlelimit()) {
				return HandleType.OVERTIME_HANDLE;
			}
			return HandleType.NORMAL_HANDLE;
		} else {
			Integer workDays = workCalendarService
					.getWorkDaysFromStartTimeToEndTime(step.getCreateDate(),
							list.get(0).getCreateDate());
			if (workDays > parametertimelimit.getHandlelimit()) {
				return HandleType.OVERTIME_HANDLE;
			}
			return HandleType.NORMAL_HANDLE;
		}
	}

	@Override
	public void fillUnDoEaringWarn(Long orgId, String orgCode,
			GridPage<CommonWorking> gridPage, Integer isFinish,
			String accountType) {
		List<CommonWorking> list = gridPage.getRows();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (null == orgId) {
					Organization targetOrg = accountStepService
							.getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
									list.get(i).getId(), accountType, orgCode,
									isFinish).getTargetOrg();
					list.get(i).setEaringWarn(
							getUnDoHandleType(list.get(i), accountType,
									targetOrg.getId(), isFinish));
				} else {
					list.get(i).setEaringWarn(
							getUnDoHandleType(list.get(i), accountType, orgId,
									isFinish));
				}
			}
		}
	}

	@Override
	public void fillDoneEaringWarn(Long orgId, String orgCode,
			GridPage<CommonWorking> gridPage, Integer isFinish,
			String accountType) {
		List<CommonWorking> list = gridPage.getRows();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (null == orgId) {
					Organization targetOrg = accountStepService
							.getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
									list.get(i).getId(), accountType, orgCode,
									isFinish).getTargetOrg();
					list.get(i).setEaringWarn(
							getDoneHandlType(list.get(i), accountType,
									targetOrg.getId(), isFinish));
				} else {
					list.get(i).setEaringWarn(
							getDoneHandlType(list.get(i), accountType, orgId,
									isFinish));
				}
			}
		}
	}

	private int getDoneHandlType(CommonWorking account, String accountType,
			Long orgId, Integer isFinish) {
		if (account == null) {
			throw new BusinessValidationException("参数错误");
		}
		Parametertimelimit parametertimelimit = parametertimelimitService
				.getParametertimelimitByOrgId(orgId);

		List<AccountLogs> list = accountLogsService
				.findAccountLogsByAccountIdAndAccountType(account.getId(),
						accountType, orgId);
		AccountStep step = accountStepService
				.getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
						account.getId(), accountType, orgId, isFinish);

		if (null != list && list.size() == 1) {
			Integer workDays = workCalendarService
					.getWorkDaysFromStartTimeToEndTime(step.getCreateDate(),
							list.get(0).getCreateDate());
			return getFinishType(parametertimelimit, step, workDays);
		} else if (null != list && list.size() > 1) {
			Integer workDays = workCalendarService
					.getWorkDaysFromStartTimeToEndTime(list.get(0)
							.getCreateDate(), list.get(list.size() - 1)
							.getCreateDate());
			return getFinishType(parametertimelimit, step, workDays);
		}
		return HandleType.NORMAL_FINISH;

	}

	private int getFinishType(Parametertimelimit parametertimelimit,
			AccountStep step, Integer workDays) {
		if (null != step.getFinishType() && null != step.getRealOrCirculation()
				&& FinishType.PROGRAM_END == step.getFinishType()
				&& FinishType.CIRCULATION_FINISH == step.getRealOrCirculation()) {
			if (workDays > parametertimelimit.getCirculationlimit()) {
				return HandleType.OVERTIME_CIRCULATION;
			}
			return HandleType.NORMAL_FINISH;
		} else {
			if (workDays > parametertimelimit.getTransferredlimit()) {
				return HandleType.OVERTIME_FINISH;
			}
			return HandleType.NORMAL_FINISH;
		}
	}
}
