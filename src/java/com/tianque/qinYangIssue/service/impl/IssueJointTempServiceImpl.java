package com.tianque.qinYangIssue.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.controller.strategy.impl.DefaultIssueManageStrategy;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.qinYangIssue.constant.StateType;
import com.tianque.qinYangIssue.dao.IssueJointTempDAO;
import com.tianque.qinYangIssue.domain.IssueJointTemp;
import com.tianque.qinYangIssue.service.IssueJointTempService;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @Description:青羊事件对接临时表业务实现
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-14 下午03:11:53
 */
@Service("issueJointTempService")
public class IssueJointTempServiceImpl implements IssueJointTempService {
	private static Logger logger = LoggerFactory
			.getLogger(IssueJointTempServiceImpl.class);
	@Autowired
	private IssueJointTempDAO issueJointTempDAO;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DefaultIssueManageStrategy strategy;
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Qualifier("issueJointTempValidator")
	@Autowired
	private DomainValidator<IssueJointTemp> issueJointTempValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private IssueTypeDomainService issueTypeDomainService;

	private IssueService getActualIssueServiceInstance() {
		return issueServiceFactory
				.getIssueServiceBySeries(IssueServiceFactory.DEFAULT_SERIES);
	}

	@Override
	@Transactional
	public void syncIssueJointTempData(Boolean isWorkTimeBreak) {
		try {
			int count = issueJointTempDAO
					.getIssueJointTempCountByState(StateType.SYNC_PENDING);
			int modulo = count % StateType.PAGE_SIZE;
			int pageCount = count / StateType.PAGE_SIZE;
			List<IssueJointTemp> issueJointTemps = null;
			if (modulo > 0) {
				pageCount = pageCount + 1;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 7);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Calendar endcalendar = Calendar.getInstance();
			endcalendar.set(Calendar.HOUR_OF_DAY, 21);
			endcalendar.set(Calendar.MINUTE, 0);
			endcalendar.set(Calendar.SECOND, 0);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", StateType.SYNC_PENDING);
			map.put("endRow", StateType.PAGE_SIZE);
			map.put("startRow", 0);
			for (int i = 0; i < pageCount; i++) {
				if (System.currentTimeMillis() > calendar.getTimeInMillis()
						&& System.currentTimeMillis() < endcalendar
								.getTimeInMillis() && isWorkTimeBreak) {
					break;
				}
				issueJointTemps = issueJointTempDAO
						.queryIssueJointTempByStateForList(map);
				PropertyDict sourceKind = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.SOURCE_KIND,
								IssueConstants.JOINT_INPUT);
				if (issueJointTemps != null && issueJointTemps.size() > 0) {
					for (IssueJointTemp issueJointTemp : issueJointTemps) {
						addIssueByIssueJointTemp(issueJointTemp, sourceKind);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointTempServiceImpl类的syncIssueJointTempData方法错误",
					"青羊事件对接数据同步到社管job调用", e);
		}
	}

	@Override
	@Transactional
	public void syncIssueStatusToIssueJointTempData(Boolean isWorkTimeBreak) {
		try {
			issueJointTempDAO
					.updateIssueJointTempDataStatus(StateType.DEALING_CODE);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Map.Entry<Integer, Integer> entry : StateType.STATUSCODE
					.entrySet()) {
				map.put("statusCode", entry.getValue());
				map.put("status", entry.getKey());
				issueJointTempDAO
						.updateIssueJointTempDataStatusCodeByStatus(map);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointTempServiceImpl类的syncIssueStatusToIssueJointTempData方法错误",
					"社管对接事件当前处理状态同步到青羊事件对接数据job调用", e);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addIssueByIssueJointTemp(IssueJointTemp issueJointTemp,
			PropertyDict sourceKind) {
		if (issueJointTemp == null || "".equals(issueJointTemp.getId())) {
			return;
		}
		try {
			// 验证数据正确性（表要求的必填字段）
			ValidateResult vr = issueJointTempValidator
					.validate(issueJointTemp);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", issueJointTemp.getId());
			if (StringUtil.isStringAvaliable(vr.getErrorMessages())) {
				map.put("state", StateType.SYNC_DATA_ERROR);
				issueJointTempDAO.updateIssueJointTempById(map);
			} else if (!StringUtil.isStringAvaliable(vr.getErrorMessages())) {// 如果验证通过，则组装事件对象，并新增事件
				IssueNew issue = new IssueNew();
				issue = createIssueNew(issueJointTemp, issue);
				/** 统一设置为同步 */
				issue.setSourceKind(sourceKind);
				fillThreadVariable(issue);
				List<IssueAttachFile> files = createIssueAttachFile(
						issueJointTemp.getAttachFileName(),
						issueJointTemp.getAttachFileName_uuid());
				IssueViewObject issueVO = getActualIssueServiceInstance()
						.addIssue(issue, files, null,
								issueJointTemp.getMaincharacters(),
								issueJointTemp.getMobile(),
								issueJointTemp.getTelephone());
				// 修改时间的新增日志信息，组织机构改为当前时间的创建用户的信息
				// IssueLogNew log = new IssueLogNew();
				// User user = permissionService
				// .getFullUserByUerName(issueJointTemp.getCreateUser());
				// Organization org = user.getOrganization();
				// log.setCreateUser(issueJointTemp.getCreateUser());
				// log.setDealUserName(user.getName());
				// log.setDealOrg(org);
				// log.setIssue(issueService.getFullIssueByIssueId(issueVO
				// .getIssueId()));
				// issueLogService.changeIssueLogByIssueId(log);

				if (issueVO != null && issueVO.getIssueId() != null) {// 新增成功

					map.put("issueId", issueVO.getIssueId());
					map.put("state", StateType.SYNC_SUCCESS);
					map.put("status", StateType.DEALING_CODE);
					map.put("statusCode",
							StateType.STATUSCODE.get(StateType.DEALING_CODE));
					issueJointTempDAO.updateIssueJointTempById(map);
				} else {// 如果事件新增失败，则改变临时事件表的状态为StateType.SYNC_FAILURE
					map.put("state", StateType.SYNC_FAILURE);
					issueJointTempDAO.updateIssueJointTempById(map);
				}
			}
		} catch (Exception e) {
			logger.error("青羊对接事件同步错误，原因是：", e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", issueJointTemp.getId());
			map.put("state", StateType.SYNC_FAILURE);
			issueJointTempDAO.updateIssueJointTempById(map);
		}

	}

	/**
	 * 由于事件在新增的时候许多组织机构是从线程池中取的，所以填充数据，每条数据填充一次
	 * */
	private void fillThreadVariable(IssueNew issue) {

		Organization org = organizationDubboService.getSimpleOrgById(issue
				.getCreateOrg().getId());
		// User user = permissionService.getFullUserByUerName(issue
		// .getCreateUser());
		User user = new User();
		Session session = new Session();
		session.setUserName(issue.getCreateUser());
		session.setUserRealName(issue.getCreateUser());
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		session.setLogin(true);
		session.setUserId(user.getId());
		session.setSessionId(UUID.randomUUID().toString());
		session.setOrganization(org);
		session.setOrgInternalCode(org.getOrgInternalCode());
		user.setOrganization(org);
		ThreadVariable.setOrganization(org);
		ThreadVariable.setUser(user);
		ThreadVariable.setSession(session);

	}

	/**
	 * 填充并拷贝附件
	 * 
	 * @param attachFileName
	 * @param attachFileName_uuid
	 * @return
	 */
	private List<IssueAttachFile> createIssueAttachFile(String attachFileName,
			String attachFileName_uuid) {
		List<IssueAttachFile> attachFiles = null;
		if (StringUtil.isStringAvaliable(attachFileName)
				&& StringUtil.isStringAvaliable(attachFileName_uuid)) {
			IssueAttachFile issueAttachFile = null;
			String[] attachFileNames = attachFileName.split(";");
			String[] attachFileName_uuids = attachFileName_uuid.split(";");
			if (attachFileNames != null && attachFileName_uuids != null
					&& attachFileNames.length == attachFileName_uuids.length) {
				attachFiles = new ArrayList<IssueAttachFile>();
				for (int i = 0; i < attachFileName_uuids.length; i++) {

					issueAttachFile = new IssueAttachFile();
					StoredFile sf = FileUtil.copyIssueJointTmpFileToStoredFile(
							attachFileName_uuids[i],
							GridProperties.ISSUE_ATTACHFILE);

					issueAttachFile.setFileActualUrl(sf.getFullName());

					issueAttachFile.setFileName(attachFileNames[i]);
					attachFiles.add(issueAttachFile);
				}
			}
		}
		return attachFiles;
	}

	/**
	 * 组装事件各个表的数据
	 * 
	 * @param issueJointTemp
	 */
	private IssueNew createIssueNew(IssueJointTemp issueJointTemp,
			IssueNew issue) {
		issue.setSubject(issueJointTemp.getSubject());// 事件主题
		issueJointTemp.getOccurOrg().setOrgInternalCode(
				issueJointTemp.getOccurOrgInternalCode());
		issue.setOccurOrg(issueJointTemp.getOccurOrg());// 发生网格
		issueJointTemp.getCreateOrg().setOrgInternalCode(
				issueJointTemp.getCreateOrgInternalCode());
		issue.setCreateOrg(issueJointTemp.getCreateOrg());// 创建网格
		issue.setOccurOrgInternalCode(issueJointTemp.getOccurOrgInternalCode());// 发生网格的orgCode
		issue.setCreateOrgInternalCode(issueJointTemp
				.getCreateOrgInternalCode());// 创建网格的orgCode
		issue.setCreateUser(issueJointTemp.getCreateUser());
		issue.setOccurDate(issueJointTemp.getOccurDate());
		issue.setCreateDate(issueJointTemp.getCreateDate());
		issue.setLastOrg(issueJointTemp.getLastOrg());
		issue.setIssueKind(issueJointTemp.getIssueKind());
		/** 统一设置为同步 */
		// issue.setSourceKind(propertyDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// PropertyTypes.SOURCE_KIND, IssueConstants.PC_INPUT));
		issue.setRelatePeopleCount(issueJointTemp.getRelatePeopleCount());
		issue.setIssueContent(issueJointTemp.getIssueContent());
		issue.setOccurLocation(issueJointTemp.getOccurLocation());
		issue.setHours(issueJointTemp.getHours());
		issue.setMinute(issueJointTemp.getMinute());
		issue.setImportantPlace(issueJointTemp.getImportantPlace());
		issue.setStatus(issueJointTemp.getStatus());
		issue.setLastUsername(issueJointTemp.getLastUsername());
		issue.setLastOrgInternalCode(issueJointTemp.getLastUsername());
		issue.setLastOrg(issueJointTemp.getLastOrg());
		issue.setMainCharacters(issueJointTemp.getMaincharacters());
		// issue.setIssueType(false);
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		IssueType issueType = issueTypeService.getIssueTypeById(issueJointTemp
				.getIssueJointType().getId(), issueJointTemp
				.getIssueJointTypeSub().getId());
		issueTypes.add(issueType);
		issue.setIssueType(issueType);
		return issue;
	}
}
