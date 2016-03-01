package com.tianque.issueAbutmentJoint.service.impl;

import java.util.Date;
import java.util.List;

import org.oproject.framework.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.SystemKeyInfo;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issueAbutmentJoint.dao.IssueJointDAO;
import com.tianque.issueAbutmentJoint.domain.IssueJoint;
import com.tianque.issueAbutmentJoint.domain.IssueJointLog;
import com.tianque.issueAbutmentJoint.domain.IssueJointStep;
import com.tianque.issueAbutmentJoint.domain.vo.IssueJointVo;
import com.tianque.issueAbutmentJoint.service.IssueJointLogService;
import com.tianque.issueAbutmentJoint.service.IssueJointService;
import com.tianque.issueAbutmentJoint.service.IssueJointStepService;
import com.tianque.service.SystemKeyInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * @Description:事件对接 事件业务层实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:26:01
 */
@Service("issueJointService")
@Transactional
public class IssueJointServiceImpl implements IssueJointService {
	public final static Logger logger = LoggerFactory
			.getLogger(IssueJointServiceImpl.class);
	private static final String DEFAULT_FIX_CONTEXT = "0000";

	@Autowired
	private IssueJointDAO issueJointDAO;
	@Autowired
	private IssueJointLogService issueJointLogService;
	@Autowired
	private IssueJointStepService issueJointStepService;

	@Qualifier("issueJointValidator")
	@Autowired
	DomainValidator<IssueJoint> issueJointValidator;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private SystemKeyInfoService systemKeyInfoService;

	@Override
	public IssueJoint addIssueJointByImport(IssueJoint issueJoint) {
		ValidateResult result = null;
		if (issueJoint == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (!ExcelImportHelper.isImport.get()) {
			result = issueJointValidator.validate(issueJoint);
		}

		if (result != null && result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			synchronized (this) {
				fillIssueJoint(issueJoint);
				Long id = issueJointDAO.addIssueJointByImport(issueJoint);
				IssueJoint resultIssue = getIssueJointById(id);
				issueJoint.setId(id);

				// 添加step
				IssueJointStep issueJointStep = addIssueJointStepByIssueJointByImport(issueJoint);
				// 添加log
				addIssueJointLogByIssueJointByImport(issueJoint, issueJointStep);

				return resultIssue;
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointServiceImpl类addIssueJointByImport方法错误",
					"导入对接事件错误", e);
		}
	}

	/**
	 * 导入对接事件根据导入的对接事件添加步骤
	 * 
	 * @param issueJoint
	 * @return
	 */
	private IssueJointStep addIssueJointStepByIssueJointByImport(
			IssueJoint issueJoint) {
		IssueJointStep issueJointStep = null;
		if (issueJoint != null) {
			issueJointStep = fillIssueJointStepByIssueJoint(issueJoint);
			issueJointStep = issueJointStepService
					.addIssueJointStepByIssueJointByImport(issueJointStep);
		}

		return issueJointStep;

	}

	/**
	 * 根据导入的对接事件填充事件步骤
	 * 
	 * @param issueJoint
	 * @return
	 */
	private IssueJointStep fillIssueJointStepByIssueJoint(IssueJoint issueJoint) {
		IssueJointStep issueJointStep = new IssueJointStep();

		/** 该步骤来源部门 */
		issueJointStep.setSource(issueJoint.getCreateOrg());
		/** 该步骤的处理部门 */
		issueJointStep.setTarget(issueJoint.getCreateOrg());
		/** 进入该步骤的时间 */
		issueJointStep.setEntryDate(issueJoint.getFeedbackTime());
		/** 该步骤的结束时间 */
		issueJointStep.setEndDate(issueJoint.getLastDealTime());
		/** 该步骤的最后处理时间 */
		issueJointStep.setLastDealDate(issueJoint.getLastDealTime());
		/** 该步骤的状态代码 */
		issueJointStep.setStateCode(IssueState.ISSUECOMPLETE_CODE);
		/** 该步骤处理的事件 */
		issueJointStep.setIssueJoint(issueJoint);

		return issueJointStep;
	}

	/**
	 * 导入对接事件根据导入的对接事件添加日志
	 * 
	 * @param issueJoint
	 * @return
	 */
	private List<IssueJointLog> addIssueJointLogByIssueJointByImport(
			IssueJoint issueJoint, IssueJointStep issueJointStep) {
		/** 新增log */
		IssueJointLog issueJointLogCreate = null;
		/** 办结log */
		IssueJointLog issueJointLogEnd = null;
		List<IssueJointLog> list = null;
		if (issueJoint != null && issueJointStep != null) {
			issueJointLogCreate = fillIssueJointStepByIssueJointAndIssueJointStep(
					issueJoint, issueJointStep);
			issueJointLogEnd = fillIssueJointStepByIssueJointAndIssueJointStep(
					issueJoint, issueJointStep);
			/** 特殊的字段新增的和办结的不同 */
			/** 处理类型 **/
			issueJointLogEnd.setDealType(((Integer) IssueOperate.COMPLETE
					.getCode()).longValue());
			/** 处理用户名称 **/
			/** 处理人 电话 **/
			issueJointLogCreate.setDealUserName(getLoginuserUserInfo()
					.getName());
			issueJointLogCreate.setMobile(getLoginuserUserInfo().getMobile());
			issueJointLogEnd.setDealUserName(issueJoint.getDealUserName());
			issueJointLogEnd.setMobile(issueJoint.getDealMobile());
			/** 处理描述 **/
			issueJointLogCreate
					.setDealDescription(IssueOperate.ISSUE_JOINT_LOG_DESCRIPTION);
			issueJointLogEnd
					.setDealDescription(IssueOperate.COMPLETE.getDesc());
			/** 处理时间 **/
			issueJointLogCreate.setDealTime(issueJoint.getFeedbackTime());
			issueJointLogEnd.setDealTime(issueJoint.getLastDealTime());
			/** 处理意见 */
			issueJointLogEnd.setContent(issueJoint.getDealContent());

			issueJointLogCreate = issueJointLogService
					.addIssueJointLogByIssueJointByImport(issueJointLogCreate);
			issueJointLogEnd = issueJointLogService
					.addIssueJointLogByIssueJointByImport(issueJointLogEnd);
			list = issueJointLogService
					.queryIssueJointLogByIssueJointIdForList(issueJoint.getId());
		}

		return list;
	}

	/**
	 * 导入对接事件根据事件和步骤生成日志
	 * 
	 * @param issueJoint
	 * @param issueJointStep
	 * @return
	 */
	private IssueJointLog fillIssueJointStepByIssueJointAndIssueJointStep(
			IssueJoint issueJoint, IssueJointStep issueJointStep) {
		IssueJointLog issueJointLog = new IssueJointLog();
		/** 对接事件 */
		issueJointLog.setIssueJoint(issueJoint);
		/** 事件处理步骤 **/
		issueJointLog.setIssueJointStep(issueJointStep);
		/** 处理目标id **/
		issueJointLog.setDealOrg(issueJoint.getCreateOrg());

		return issueJointLog;
	}

	/**
	 * 导入的数据自动填充出一些属性
	 * 
	 * @param issueJoint
	 */
	private void fillIssueJoint(IssueJoint issueJoint) {
		User user = getLoginuserUserInfo();

		// 填充服务单号
		issueJoint
				.setSerialNumber(getNextKey(getCurrentIssuePrefix(issueJoint)));
		// 创建网格【当前登录用户的组织机构】
		issueJoint.setCreateOrg(user.getOrganization());
		/** 最后处理部门 */
		issueJoint.setLastOrg(user.getOrganization());
		/** 最后处理用户 */
		issueJoint.setLastUsername(user.getName());
		/** 状态 因为导入默认都是已办结 */
		issueJoint.setStatus(IssueState.COMPLETE);
	}

	private User getLoginuserUserInfo() {
		User user = null;
		if (ThreadVariable.getUser() != null
				&& ThreadVariable.getUser().getId() != null) {
			user = permissionService.getSimpleUserById(ThreadVariable.getUser()
					.getId());
			user.setOrganization(organizationDubboService.getSimpleOrgById(user
					.getOrganization().getId()));

		}

		return user;
	}

	/**
	 * 拷贝事件的生成规则生成服务单号
	 * 
	 * @param issueJoint
	 * @return
	 */
	private String getCurrentIssuePrefix(IssueJoint issueJoint) {
		Organization district = organizationDubboService
				.findSomeLevelParentOrg(issueJoint.getOccurOrg().getId(),
						OrganizationLevel.DISTRICT);
		if (district == null) {
			district = organizationDubboService.findOrganizationsByParentId(
					null).get(0);
		}
		return CalendarUtil.format("yyMMdd", issueJoint.getFeedbackTime())
				+ CalendarUtil.format("HHmmss", new Date())
				+ district.getDepartmentNo();
	}

	private String getNextKey(String prefix) {
		// String prefix=getCurrentIssuePrefix();
		SystemKeyInfo systemKeyInfo = systemKeyInfoService
				.getSimpleSystemKeyInfoByName(prefix);
		if (systemKeyInfo == null)
			systemKeyInfo = addNewSystemKeyInfo(prefix);
		systemKeyInfo.setCurNum(systemKeyInfo.getCurNum()
				+ systemKeyInfo.getStep());
		systemKeyInfo = systemKeyInfoService.updateSystemKeyInfo(systemKeyInfo);
		String curNum = String.valueOf(systemKeyInfo.getCurNum());
		if (curNum.length() > 4)
			curNum = curNum.substring(0, 4);
		else
			curNum = DEFAULT_FIX_CONTEXT.substring(0, 4 - curNum.length())
					+ curNum;
		return prefix + curNum;
	}

	private SystemKeyInfo addNewSystemKeyInfo(String prefix) {
		return systemKeyInfoService
				.addSystemKeyInfo(createSystemKeyInfoInstance(prefix));
	}

	private SystemKeyInfo createSystemKeyInfoInstance(String prefix) {
		SystemKeyInfo systemKeyInfo = new SystemKeyInfo();
		systemKeyInfo.setName(prefix);
		systemKeyInfo.setCurNum(0L);
		systemKeyInfo.setStep(1);
		return systemKeyInfo;
	}

	@Override
	public PageResult<IssueJoint> queryIssueJointByIssueJointVoForPageResult(
			IssueJointVo issueJointVo, String sidx, String sord, int pageNum,
			int pageSize) {
		if (issueJointVo == null || issueJointVo.getCreateOrg() == null
				|| issueJointVo.getCreateOrg().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			if (null != issueJointVo && null != issueJointVo.getCreateOrg()
					&& null != issueJointVo.getCreateOrg().getId()) {
				Organization createOrg = organizationDubboService
						.getSimpleOrgById(issueJointVo.getCreateOrg().getId());
				issueJointVo.setCreateOrg(createOrg);
			}
			issueJointVo.setSortField(sidx);
			issueJointVo.setOrder(sord);
			PageResult<IssueJoint> issueJointList = issueJointDAO
					.queryIssueJointByIssueJointVoForPageResult(issueJointVo,
							pageNum, pageSize);
			return issueJointList;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointServiceImpl类queryIssueJointByIssueJointVoForPageResult方法错误",
					"查询对接事件错误", e);
		}
	}

	@Override
	public IssueJoint getIssueJointById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			IssueJoint issueJoint = issueJointDAO.getIssueJointById(id);
			issueJoint.setOccurOrg(organizationDubboService
					.getSimpleOrgById(issueJoint.getOccurOrg().getId()));
			return issueJoint;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointServiceImpl类getIssueJointById方法错误", "查询对接事件错误",
					e);
		}
	}
}
