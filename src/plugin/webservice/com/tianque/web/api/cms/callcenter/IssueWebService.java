package com.tianque.web.api.cms.callcenter;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.controller.IssueController;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.mobile.vo.MobileIssueType;
import com.tianque.service.IssueNewService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.web.api.base.BaseWebApi;

/**
 * @ClassName: IssueWebService
 * @Description: 事件接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月22日 下午4:11:37
 */
@Namespace("/webApi/issue")
public class IssueWebService extends BaseWebApi {
	Logger logger = LoggerFactory.getLogger(IssueWebService.class);
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private IssueController issueController;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private File[] files;
	private String[] filesFileName;
	private IssueNew issue;
	private IssueLogNew issueLog;
	private Long stepId;
	private String serialNumber;
	private String fromSerialNumber;
	private String departmentNo;
	/** 审核标记 */
	private String sign;
	/** 事件处理记录实体类 */
	private IssueLogNew operation;
	/** 坐席的locationId */
	private Long locationId;
	private Long parentId;
	private int resultInfo;
	private Long orgId;

	private String issueRelatedPeopleNames;// 名字
	private String issueRelatedPeopleTelephones;// 手机
	private String issueRelatedPeopleFixPhones;// 固话
	private Boolean isAgree;// 是否同意办结

	private final static String issueBigTypeNames = "contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage";

	/**
	 * @Description: 诉求分流至社管，在社管中生成一条事件
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "addIssueAndDispatch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String addIssueAndDispatch() {
		String validateResult = validateAddIssueAndDispatch();
		if (validateResult != null) {
			head.setResultCode("0");
			head.setResultMsg(validateResult);
			body.setResult(false);
			return ERROR;
		}
		Organization createOrg = organizationDubboService
				.getSimpleOrgById(issueLog.getTargeOrg().getId());
		if (createOrg == null) {
			head.setResultCode("0");
			head.setResultMsg("参数错误，主办单位不存在！");
			body.setResult(false);
			return ERROR;
		}
		try {
			issue.setSourceKind(propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.SOURCE_KIND,
							IssueConstants.CALLCENTER_INPUT));
			issue.setDemandBranch(IssueNew.DemandBranch.CREATE_AND_ASSIGN);
			issueLog.setDemandBranch(IssueNew.DemandBranch.CREATE_AND_ASSIGN);
			issueLog.setDemandAssigndIssue(true);// 事件来源
			issueController.setSelectedTypes(issue.getIssueTypeIds());// 事件类型
			issueController.setAttachFiles(filesFileName);
			issueController.setOperation(issueLog);
			ThreadVariable.getSession().setUserRealName(
					issueLog.getDealUserName());
			ThreadVariable.getUser().setMobile(issueLog.getMobile());
			ThreadVariable.setOrganization(createOrg);
			ThreadVariable.getUser().setOrganization(createOrg);
			ThreadVariable.getSession().setOrganization(createOrg);
			issueController.setIssue(issue);
			issueController.setIssueRelatedPeopleNames(issueRelatedPeopleNames);
			issueController
					.setIssueRelatedPeopleTelephones(issueRelatedPeopleTelephones);
			issueController
					.setIssueRelatedPeopleFixPhones(issueRelatedPeopleFixPhones);
			if (SUCCESS.equals(issueController.addIssue())) {
				head.setResultCode("1");
				head.setResultMsg("事件添加成功");
				body.setResult(true);
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		head.setResultCode("0");
		head.setResultMsg(isEmpty(issueController.getErrorMessage()) ? "添加事件失败"
				: issueController.getErrorMessage());
		body.setResult(false);
		return ERROR;
	}

	public String validateAddIssueAndDispatch() {
		if (files != null && files.length > 10) {
			return "附件最多只能同时上传10个";
		}
		if (issue == null
				|| !StringUtil.isStringAvaliable(issue.getFromSerialNumber())) {
			return "分流序列号无效！";
		}
		if (issueLog == null || issueLog.getTargeOrg() == null) {
			return "主办单位不能为空！";
		}
		return null;
	}

	/**
	 * @Description: cms 撤销事件
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "issueRevoke", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String issueRevoke() {
		head.setResultCode("0");
		head.setResultMsg("操作失败！");
		body.setResult(false);
		try {
			IssueNew issueNew = null;
			if (StringUtil.isStringAvaliable(serialNumber)) {
				issueNew = issueNewService.getIssueBySerialNumber(serialNumber);
			} else {
				issueNew = issueNewService
						.getIssueByFromSerialNumber(fromSerialNumber);
			}
			if (issueNew != null) {// 这边的业务暂时只考虑到座席新增的事件撤销。
				IssueStep step = issueNewService
						.getSimpleIssueByIssueId(issueNew.getId());
				if (step != null
						&& new Integer(step.getStateCode())
								.equals(IssueState.UNCONCEPTED_CODE)) {
					issueController.setKeyId(issueNew.getId());
					issueController.deleteIssue();
					head.setResultCode("1");
					head.setResultMsg("操作成功");
				} else {
					head.setResultCode("0");
					head.setResultMsg("诉求已在社管事件中开始办理！");
					body.setResult(false);
				}
			} else {
				head.setResultCode("0");
				head.setResultMsg("诉求已在社管事件中被删除！");
				body.setResult(false);
			}
		} catch (Exception e) {
			logger.error("cms 撤销事件出现异常：", e);
			head.setResultCode("0");
			head.setResultMsg(e.getMessage());
			body.setResult(false);
		}
		return SUCCESS;
	}

	/**
	 * @Description: 是否通过延期申请web接口
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "auditPostponeApply", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String auditPostponeApply() {
		try {
			if (StringUtils.isEmpty(fromSerialNumber)) {
				head.setResultCode("0");
				head.setResultMsg("诉求编号不能为空！");
				body.setResult(false);
				return ERROR;
			}
			// IssueNew issue = issueNewService
			// .getIssueByFromSerialNumber(fromSerialNumber);
			// issueNewService.auditPostponeApply(issue.getCurrentStep().getId(),
			// issueLog, sign);
			// issueNewService.updatePostponeApplyState(issue.getId(), sign,
			// issueLog.getApplyDate());
			// IssueLogNew issueLogLast = issueNewService.getApplyUser(issue
			// .getId());
			// if (sign != null) {
			// String msg = "";
			// if (sign.equals("pass")) {
			// msg = "延期申请审核提醒,服务单号：" + issue.getSerialNumber()
			// + "的延期申请已被通过";
			// } else {
			// msg = "延期申请审核提醒,服务单号：" + issue.getSerialNumber()
			// + "的延期申请未被通过,原因:" + issueLog.getContent();
			// }
			// smsService.addSmsDownlink(issueLogLast.getMobile(), msg,
			// issueLog.getDealUserName(), GlobalValue.SHEGUANPLATFORM, null,
			// null);
			// }
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	/**
	 * @Description: 社管上报事件，cms做了转办操作后，添加记录至社管中
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "transferIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "resultInfo" }) })
	public String transferIssue() {
		if (issueLog == null || locationId == null) {
			return SUCCESS;
		}
		Organization organization = new Organization();
		organization.setId(locationId * -1);
		organization.setOrgInternalCode("report12345");
		issueLog.setDealOrg(organization);
		// issueLog.setDealType((long) IssueOperate.CMS_DISTRIBUTION);
		// issueLog.setDealTime(new Date());
		// IssueChangeEvent event = new IssueChangeEvent(issueLog,
		// new ArrayList<IssueAttachFile>(),
		// IssueOperate.DISTRIBUTION_DEAL);
		// IssueNew issueNew = issueService.getFullIssueByStepId(stepId);
		// if (null == issueNew) {
		// return SUCCESS;
		// }
		// IssueStep step = issueNewService.getSimpleIssueByIssueId(issueNew
		// .getId());
		//
		// IssueStepGroup steps = new IssueStepGroup();
		// steps.setKeyStep(step);
		// savingOperationLog.onChanged(issueNew, steps, event);
		resultInfo = 1;
		return SUCCESS;
	}

	/**
	 * @Description: 社管新增的事件，上报至呼叫中心，呼叫中心可回退此诉求
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "toGridByBack", results = { @Result(name = "success", type = "json", params = {
			"root", "resultInfo" }) })
	public String toGridByBack() {
		if (issueLog == null || locationId == null || stepId == null) {
			return SUCCESS;
		}
		Organization organization = new Organization();
		organization.setId(locationId * -1);
		organization.setOrgInternalCode("report12345");
		issueLog.setDealOrg(organization);
		issueLog.setDealTime(new Date());
		issueController.setKeyId(stepId);
		issueController.setDealCode(IssueOperate.BACK.getCode());
		issueController.setOperation(issueLog);
		try {
			ThreadVariable.getSession().setUserRealName(
					issueLog.getDealUserName());
			ThreadVariable.getUser().setMobile(issueLog.getMobile());
			ThreadVariable.setOrganization(organization);
			ThreadVariable.getUser().setOrganization(organization);
			ThreadVariable.getSession().setOrganization(organization);
			if (SUCCESS.equals(issueController.deal())) {
				resultInfo = 1;
			} else {
				logger.error("呼叫中心回退事件发生错误："
						+ issueController.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return SUCCESS;
	}

	/**
	 * @Description: 社管上报事件，再次交办至社管
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "report12345DemandToIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "resultInfo" }) })
	public String report12345DemandToIssue() {
		if (stepId == null) {
			return SUCCESS;
		}
		if (operation == null || operation.getContent() == null) {
			return SUCCESS;
		}
		Organization target = organizationDubboService.getFullOrgById(orgId);
		if (target == null) {
			return SUCCESS;
		}
		Organization organization = new Organization();
		organization.setId(locationId * -1);
		organization.setOrgInternalCode("report12345");
		operation.setDealOrg(organization);
		// IssueViewObject object = issueService.assign(stepId, operation,
		// target.getId(),
		// parseStringToLongArray(issueLog.getTellOrgIds()), null, true);
		// if (object != null) {
		// resultInfo = 1;
		// if (dealSendMobileMsg) {//发送短信给办理部门
		// IssueNew issueNew = null;
		// if (StringUtil.isStringAvaliable(serialNumber)) {//社管上报到座席的服务单号
		// issueNew = getActualIssueServiceInstance()
		// .getIssueBySerialNumber(serialNumber);
		// } else if (StringUtil.isStringAvaliable(fromSerialNumber))
		// {//社管保存的座席编号
		// issueNew = getActualIssueServiceInstance()
		// .getIssueByFromSerialNumber(fromSerialNumber);
		// }
		// if (issueNew != null) {
		// issue = new IssueNewVo();
		// issue.setSubject(issueNew.getSubject());
		// dealSendMsg();
		// }
		// }
		// }
		return SUCCESS;
	}

	/**
	 * @Description: 从呼叫中心分流至社管时，获得事件类型选择数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "getIssueTypeList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String getIssueTypeList() {
		List issueTypeReturnList = new ArrayList<Map<String, List<MobileIssueType>>>();
		try {
			String[] propertyTypes = issueBigTypeNames.split(",");
			List<MobileIssueType> issueTypeList = new ArrayList<MobileIssueType>();
			String keyName = "事件大类";
			if (parentId == null || parentId.longValue() == 0l) {
				for (String domainName : propertyTypes) {
					IssueTypeDomain issueTypeDomain = issueTypeService
							.getIssueTypeDoaminByDomainName(IssueTypes
									.getChineseName(domainName));
					MobileIssueType mobileIssueType = new MobileIssueType();
					mobileIssueType.setContent(issueTypeDomain.getDomainName());
					mobileIssueType.setId(issueTypeDomain.getId());
					issueTypeList.add(mobileIssueType);
				}
			} else {
				List<IssueType> issueTypes = issueTypeService
						.findIssueTypesByDomainId(parentId);
				IssueTypeDomain issueTypeDomain = issueTypeService
						.getIssueTypeDomainById(parentId);
				keyName = issueTypeDomain.getDomainName();
				for (IssueType issueType : issueTypes) {
					MobileIssueType mobileIssueType = new MobileIssueType();
					mobileIssueType.setContent(issueType.getIssueTypeName());
					mobileIssueType.setId(issueType.getId());
					issueTypeList.add(mobileIssueType);
				}
			}
			Map<String, List<MobileIssueType>> issueBigTypeMap = new HashMap<String, List<MobileIssueType>>();
			issueBigTypeMap.put(keyName, issueTypeList);
			issueTypeReturnList.add(issueBigTypeMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			head.setResultCode("0");
			head.setResultMsg("获取事件类型列表失败");
			body.setResult(resultList);
			return ERROR;
		}
		head.setResultCode("1");
		head.setResultMsg("获取事件类型列表成功");
		body.setResult(issueTypeReturnList);
		return SUCCESS;
	}

	/**
	 * @Description: 社管办结事件后，呼叫中心不同意结案，社管事件变成待受理事件
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "reAssign", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String reAssign() {
		if (StringUtils.isEmpty(fromSerialNumber)) {
			head.setResultCode("0");
			head.setResultMsg("诉求编号不能为空！");
			body.setResult(false);
			return ERROR;
		}

		if (StringUtils.isEmpty(departmentNo)) {
			head.setResultCode("0");
			head.setResultMsg("重新交办方组织机构不能为空！");
			body.setResult(false);
			return ERROR;
		}

		try {
			Organization org = organizationDubboService
					.getOrgByDepartmentNo(departmentNo);
			issueLog.setDealOrg(org);
			// issueLog.setDealType((long) IssueOperate.REACCEPT_CODE);
			// IssueChangeEvent event = new IssueChangeEvent(issueLog,
			// new ArrayList<IssueAttachFile>(), IssueOperate.REACCEPT);
			// IssueNew issue = issueService
			// .getIssueByFromSerialNumber(fromSerialNumber);
			if (null == issue) {
				head.setResultCode("0");
				head.setResultMsg("设置重新交办时出现错误！找不到事件！");
				body.setResult(false);
				return ERROR;
			}

			IssueStep step = issueNewService.getSimpleIssueByIssueId(issue
					.getId());
			issueController.setStepId(step.getId());
			// issueController.reAssign();
			Organization targetOrg = organizationDubboService
					.getSimpleOrgById(step.getTarget().getId());
			step.setTarget(targetOrg);
			IssueStepGroup steps = new IssueStepGroup();
			steps.setKeyStep(step);

			// savingOperationLog.onChanged(issue, steps, event);

			head.setResultCode("1");
			head.setResultMsg("该事件设置重新交办成功！");
			body.setResult(true);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			head.setResultCode("0");
			head.setResultMsg("设置重新交办时出现错误！" + e.getMessage());
			body.setResult(false);
			return ERROR;
		}
	}

	/**
	 * @Description: 社管上报事件，直接回复或者分流至接警中心或者分流至服务商后，调用此接口结案事件
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "replySheguanIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "resultInfo" }) })
	public String replySheguanIssue() {
		if (stepId == null) {
			return SUCCESS;
		}
		if (operation == null || operation.getContent() == null) {
			return SUCCESS;
		}
		Organization organization = new Organization();
		organization.setId(locationId * -1);
		organization.setOrgInternalCode("report12345");
		operation.setDealOrg(organization);
		issueController.setKeyId(stepId);
		issueController.setDealCode(IssueOperate.COMPLETE.getCode());
		issueController.setOperation(operation);
		try {
			if (SUCCESS.equals(issueController.deal())) {
				resultInfo = 1;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return SUCCESS;
	}

	/**
	 * @Description: 呼叫中心分流至社管的诉求，事件紧急，从呼叫中心催促社管办理（红牌督办）
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "urgeDealIssue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String urgeDealIssue() {
		if (StringUtils.isEmpty(fromSerialNumber)) {
			head.setResultCode("0");
			head.setResultMsg("诉求编号不能为空！");
			body.setResult(false);
			return ERROR;
		}
		if (StringUtils.isEmpty(departmentNo)) {
			head.setResultCode("0");
			head.setResultMsg("催办方组织机构不能为空！");
			body.setResult(false);
			return ERROR;
		}
		try {
			Organization org = organizationDubboService
					.getOrgByDepartmentNo(departmentNo);
			issueLog.setDealOrg(org);
			issueLog.setDealType((long) IssueOperate.RED_SUPERVISE.getCode());
			IssueNew issue = issueNewService
					.getIssueByFromSerialNumber(fromSerialNumber);
			if (null == issue) {
				head.setResultCode("0");
				head.setResultMsg("催办时出现错误！找不到事件！");
				body.setResult(false);
				return ERROR;
			}
			IssueStep step = issueNewService.getSimpleIssueByIssueId(issue
					.getId());
			issueController.setKeyId(step.getId());
			issueController.setDealCode(IssueOperate.RED_SUPERVISE.getCode());
			issueController.setOperation(issueLog);
			if (SUCCESS.equals(issueController.deal())) {
				head.setResultCode("1");
				head.setResultMsg("该事件已催办成功！");
				body.setResult(true);
				return SUCCESS;
			} else {
				head.setResultCode("0");
				head.setResultMsg(issueController.getErrorMessage());
				body.setResult(false);
				return ERROR;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			head.setResultCode("0");
			head.setResultMsg("催办时出现错误！" + e.getMessage());
			body.setResult(false);
			return ERROR;
		}
	}

	/**
	 * @Description: 从呼叫中心分流至社管时，获得事件规模选择数据
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "getIssueKindList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String getIssueKindList() {
		List<PropertyDict> list = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ISSUE_KIND);
		if (null != list && list.size() > 0) {
			List<Map> resultList = new ArrayList<Map>();
			for (PropertyDict dict : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", dict.getId());
				map.put("displayName", dict.getDisplayName());
				resultList.add(map);
			}
			head.setResultCode("1");
			head.setResultMsg("获取事件性质列表成功");
			body.setResult(resultList);
			return SUCCESS;
		}
		head.setResultCode("0");
		head.setResultMsg("获取事件性质列表失败");
		body.setResult(resultList);
		return ERROR;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public IssueLogNew getIssueLog() {
		return issueLog;
	}

	public void setIssueLog(IssueLogNew issueLog) {
		this.issueLog = issueLog;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFromSerialNumber() {
		return fromSerialNumber;
	}

	public void setFromSerialNumber(String fromSerialNumber) {
		this.fromSerialNumber = fromSerialNumber;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public IssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(IssueLogNew operation) {
		this.operation = operation;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public int getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(int resultInfo) {
		this.resultInfo = resultInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIssueRelatedPeopleNames() {
		return issueRelatedPeopleNames;
	}

	public void setIssueRelatedPeopleNames(String issueRelatedPeopleNames) {
		this.issueRelatedPeopleNames = issueRelatedPeopleNames;
	}

	public String getIssueRelatedPeopleTelephones() {
		return issueRelatedPeopleTelephones;
	}

	public void setIssueRelatedPeopleTelephones(
			String issueRelatedPeopleTelephones) {
		this.issueRelatedPeopleTelephones = issueRelatedPeopleTelephones;
	}

	public String getIssueRelatedPeopleFixPhones() {
		return issueRelatedPeopleFixPhones;
	}

	public void setIssueRelatedPeopleFixPhones(
			String issueRelatedPeopleFixPhones) {
		this.issueRelatedPeopleFixPhones = issueRelatedPeopleFixPhones;
	}

	public Boolean getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(Boolean isAgree) {
		this.isAgree = isAgree;
	}

}
