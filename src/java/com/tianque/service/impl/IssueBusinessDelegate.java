package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.IssueAttachFileDao;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueNewDao;
import com.tianque.dao.IssueWorkFlowDao;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.IssueDealRegradeReason;
import com.tianque.domain.vo.IssueRegisteRegradedReason;
import com.tianque.domain.vo.IssueRegradedReason;
import com.tianque.domain.vo.RegradedReason;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.HistoricalIssue;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.domain.PublicltyCass;
import com.tianque.issue.service.IssueAccessConfigService;
import com.tianque.issue.service.IssueSkipruleService;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperateHelper;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.HistoricalIssueService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.PublicltyCassService;
import com.tianque.service.RegradedPointService;
import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateFactoryNew;
import com.tianque.state.IssueStateNew;
import com.tianque.state.IssueStateType;
import com.tianque.state.RegradedType;
import com.tianque.state.impl.CityAdministrativeRegionUnConceptStateNew;
import com.tianque.state.impl.CityFunctionalOrgUnConceptStateNew;
import com.tianque.state.impl.DistrictAdministrativeRegionUnConceptStateNew;
import com.tianque.state.impl.DistrictFunctionalOrgUnConceptStateNew;
import com.tianque.state.impl.GridUnConceptStateNew;
import com.tianque.state.impl.TownUnConceptStateNew;
import com.tianque.state.impl.ViliageUnConceptStateNew;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tqSearch.common.TqSolrSearchCommonOperate;
import com.tianque.tqSearch.constant.TqSolrSearchOperateType;
import com.tianque.tqSearch.convert.IssueSolrDocumentConvert;

@Component("issueBusinessDelegate")
@Transactional
public class IssueBusinessDelegate {
	@Autowired
	private IssueLogDaoNew issueLogDao;
	@Autowired
	private IssueNewDao issueDao;
	@Autowired
	private IssueWorkFlowDao issueWorkFlowDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RegradedPointService regradedPointService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IssueAttachFileDao issueAttachFileDao;
	@Autowired
	private HistoricalIssueService historicalIssueService;
	@Autowired
	private PublicltyCassService publicltyCassService;
	@Autowired
	private IssueAccessConfigService issueAccessConfigService;
	@Autowired
	private IssueSkipruleService issueSkipruleService;
	@Autowired
	private TqSolrSearchCommonOperate tqSolrSearchCommonOperate;

	private IssueStateNew issueState;

	public IssueStep register(IssueNew issue) {
		if (!validateRegisterIssueLog(issue)) {
			throw new BusinessValidationException("参数不正确");
		}
		issue.setCreateOrg(organizationDubboService.getFullOrgById(issue
				.getCreateOrg().getId()));
		issue.setOccurOrg(organizationDubboService.getFullOrgById(issue
				.getOccurOrg().getId()));
		IssueStep reuslt = issueWorkFlowDao.addIssueStep(IssueOperateHelper
				.register(issue));
		issueDao.updateIssueCurrentStepAndOrg(issue);
		sendPersonelMessageOnCreateIssue(issue);
		try {
			addRatingRecord(issue.getOccurOrg(),
					createRegistIssueRegradedReason(issue), 1,
					issue.getCreateDate());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		return reuslt;
	}

	public IssueLogNew registerIssueLog(IssueNew issueNew) {
		if (!validateRegisterIssueLog(issueNew)) {
			throw new BusinessValidationException("参数不正确");
		}
		IssueLogNew issueLogNew = addLogWhenAddIssue(issueNew);
		// if(issueNew.getOccurOrg().getId().longValue() ==
		// getUser().getOrganization().getId().longValue()){
		issueLogNew = conceptIssueWhenAddIssue(issueLogNew, issueNew);
		// }
		sendPersonelMessageOnCreateIssue(issueNew);
		try {
			addRatingRecord(issueNew.getOccurOrg(),
					createRegistIssueRegradedReason(issueNew), 1,
					issueNew.getCreateDate());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		return issueLogNew;
	}

	private IssueLogNew conceptIssueWhenAddIssue(IssueLogNew forIssueLog,
			IssueNew issueNew) {
		IssueLogNew issueLogVo = new IssueLogNew();
		issueLogVo.setIssue(issueNew);
		issueLogVo.setDealType(IssueDealType.CONCEPT);
		issueLogVo.setDealOrg(getUser().getOrganization());
		issueLogVo.setDealUserName(getUser().getName());
		issueLogVo.setMobile(getUser().getMobile());

		forIssueLog.setDealState(IssueDealState.STEP_COMPLETE);
		issueLogVo.setTargeOrg(issueLogVo.getDealOrg());

		autoFillIssueLog(issueLogVo, forIssueLog, IssueDealState.DOING);

		return concept(createIssueContext(forIssueLog, issueLogVo, issueNew));
	}

	private IssueLogNew addLogWhenAddIssue(IssueNew issueNew) {
		IssueLogNew issueLogNew = new IssueLogNew();
		issueLogNew.setIssue(issueNew);
		issueLogNew.setDealTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		issueLogNew.setDealOrg(issueNew.getCreateOrg());
		issueLogNew.setDealOrgInternalCode(issueNew.getCreateOrgInternalCode());
		issueLogNew.setDealStepIndex(1L);
		issueLogNew.setDealType(IssueDealType.Add);
		issueLogNew.setDealDescription(IssueDealType.toString(IssueDealType.Add
				.intValue()));
		issueLogNew.setDealState(IssueDealState.UN_DONE);
		issueLogNew.setDealUserName(getUser().getName());
		issueLogNew.setMobile(getUser().getMobile());
		issueLogNew.setTargeOrg(organizationDubboService
				.getFullOrgById(issueNew.getCreateOrg().getId()));
		issueLogNew.setTargeOrgInternalCode(issueNew.getOccurOrgInternalCode());
		issueLogNew.setStateClass(getStateClassName(issueLogNew.getTargeOrg()));

		issueDao.updateIssueWhenDeal(this.getModifyIssueField(issueNew.getId(),
				issueLogNew.getTargeOrg().getOrgName(),
				issueLogNew.getDealUserName(), issueLogNew.getDealOrg(),
				issueLogNew.getDealOrgInternalCode(), IssueStateType.PROCESSING));
		return issueLogDao.addIssueLog(issueLogNew);
	}

	public IssueLogNew superviseIssueLog(IssueLogNew issueLogNew,
			IssueLogNew issueLogVo) {
		issueLogVo.setTargeOrg(issueLogNew.getTargeOrg());
		autoFillIssueLog(issueLogVo, issueLogNew, issueLogVo.getDealState());
		sendPersonelMessageToOrganization(issueLogNew.getTargeOrg().getId(),
				getSuperviseMessage(issueLogVo));
		return issueLogDao.addIssueLog(issueLogVo);
	}

	public void deductPoints(IssueLogNew issueLog, double points,
			String superviseType) throws JSONException {
		IssueRegradedReason regradedReason = new IssueRegradedReason();
		IssueNew issueNew = issueDao.getSimpleIssueById(issueLog.getIssue()
				.getId());
		Organization targeOrg = organizationDubboService
				.getSimpleOrgById(issueLog.getTargeOrg().getId());
		regradedReason.setIssueId(issueNew.getId());
		regradedReason.setIssueLogId(issueLog.getForIssueLog().getId());
		regradedReason.setPoints(points);
		regradedReason.setSerialNumber(issueNew.getSerialNumber());
		regradedPointService
				.deductPoints(targeOrg, superviseType, regradedReason, points,
						CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
	}

	private boolean validateRegisterIssueLog(IssueNew issue) {
		if (issue == null
				|| issue.getId() == null
				|| issue.getCreateOrg() == null
				|| issue.getCreateOrgInternalCode() == null
				|| !StringUtil.isStringAvaliable(issue
						.getCreateOrgInternalCode())
				|| issue.getCreateUser() == null
				|| !StringUtil.isStringAvaliable(issue.getCreateUser())
				|| issue.getOccurOrg() == null
				|| issue.getOccurOrg().getId() == null
				|| issue.getOccurOrg().getId().intValue() == 0
				|| issue.getOccurOrgInternalCode() == null
				|| !StringUtil.isStringAvaliable(issue
						.getOccurOrgInternalCode())) {
			return false;
		}
		return true;
	}

	private String getStateClassName(Organization org) {
		int level = organizationDubboService.getFullOrgById(org.getId())
				.getOrgLevel().getInternalId();
		int type = organizationDubboService.getFullOrgById(org.getId())
				.getOrgType().getInternalId();
		if (level == OrganizationLevel.CITY
				&& type == OrganizationType.ADMINISTRATIVE_REGION) {
			return CityAdministrativeRegionUnConceptStateNew.class.getName();
		} else if (level == OrganizationLevel.CITY
				&& type == OrganizationType.FUNCTIONAL_ORG) {
			return CityFunctionalOrgUnConceptStateNew.class.getName();
		} else if (level == OrganizationLevel.DISTRICT
				&& type == OrganizationType.ADMINISTRATIVE_REGION) {
			return DistrictAdministrativeRegionUnConceptStateNew.class
					.getName();
		} else if (level == OrganizationLevel.DISTRICT
				&& type == OrganizationType.FUNCTIONAL_ORG) {
			return DistrictFunctionalOrgUnConceptStateNew.class.getName();
		} else if (level == OrganizationLevel.TOWN) {
			return TownUnConceptStateNew.class.getName();
		} else if (level == OrganizationLevel.VILLAGE) {
			return ViliageUnConceptStateNew.class.getName();
		} else if (level == OrganizationLevel.GRID) {
			return GridUnConceptStateNew.class.getName();
		} else {
			return "";
		}
	}

	public IssueLogNew concept(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.concept(issueContext);

		IssueLogNew issueLogReturn = issueLogDao.addIssueLog(issueContext
				.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogReturn.getDealTime());
		issueDao.updateIssueWhenDeal((IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY));

		return issueLogReturn;
	}

	public IssueLogNew read(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.read(issueContext);
		IssueLogNew issueLogReturn = issueLogDao.addIssueLog(issueContext
				.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogReturn.getDealTime());
		try {
			IssueNew issue = issueDao.getSimpleIssueById(issueContext
					.getForIssueLog().getIssue().getId());
			addRatingRecord(
					getCurrentLoginedOrganization(),
					createIssueDealRegradedReason(issue, issueLogReturn,
							IssueDealType.READ), 1, CalendarUtil.today());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		return issueLogReturn;
	}

	public IssueLogNew complete(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.complete(issueContext);

		IssueLogNew issueLogReturn = issueLogDao.addIssueLog(issueContext
				.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogReturn.getDealTime());
		issueDao.updateIssueWhenDeal((IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY));
		try {
			IssueNew issue = issueDao.getSimpleIssueById(issueContext
					.getIssue().getId());
			addRatingRecord(
					getCurrentLoginedOrganization(),
					createIssueDealRegradedReason(issue, issueLogReturn,
							IssueDealType.COMPLETE), 1, CalendarUtil.today());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		return issueLogReturn;
	}

	public IssueLogNew comment(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.comment(issueContext);

		IssueLogNew issueLogreReturn = issueLogDao.addIssueLog(issueContext
				.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		issueDao.updateIssueWhenDeal((IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY));

		return issueLogreReturn;
	}

	public IssueLogNew submitForward(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		issueState.submitForward(issueContext);
		IssueLogNew issueLogVo = issueContext.getIssueLogNew();
		IssueLogNew issueLogreReturn = issueLogDao.addIssueLog(issueLogVo);
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		issueDao.updateIssueWhenDeal((IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY));
		IssueNew issue = (IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY);
		sendPersonelMessageToOrganization(issueLogVo.getTargeOrg().getId(),
				getTransferIssueMessage(issue));
		try {
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue), 1,
					CalendarUtil.today());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		doTell(issueContext, issueLogVo, issue);
		return issueLogreReturn;
	}

	public IssueLogNew assign(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		issueState.assign(issueContext);
		IssueLogNew issueLogVo = issueContext.getIssueLogNew();
		IssueLogNew issueLogreReturn = issueLogDao.addIssueLog(issueLogVo);
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		IssueNew issue = (IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY);
		issueDao.updateIssueWhenDeal(issue);
		sendPersonelMessageToOrganization(issueLogVo.getTargeOrg().getId(),
				getTransferIssueMessage(issue));
		try {
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue), 1,
					CalendarUtil.today());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		doTell(issueContext, issueLogVo, issue);
		return issueLogreReturn;
	}

	public IssueLogNew sendback(IssueContext issueContext) {
		issueState = createIssueState(issueContext);
		issueState.sendback(issueContext);
		IssueLogNew issueLogVo = issueContext.getIssueLogNew();
		IssueLogNew issueLogreReturn = issueLogDao.addIssueLog(issueLogVo);
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		issueDao.updateIssueWhenDeal((IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY));
		IssueNew issue = (IssueNew) issueContext
				.getValue(IssueContext.ISSUE_KEY);
		sendPersonelMessageToOrganization(issueLogVo.getTargeOrg().getId(),
				getTransferIssueMessage(issue));
		try {
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue), 1,
					CalendarUtil.today());
		} catch (JSONException e) {
			throw new BusinessValidationException("添加打分记录时发生错误");
		}
		return issueLogreReturn;
	}

	private void doTell(IssueContext issueContext, IssueLogNew issueLogVo,
			IssueNew issue) {
		if (issueContext.getValue(IssueContext.TELL_TARGET_ORG_ID_LIST_KEY) != null) {
			IssueLogNew forIssueLog = issueLogDao.getIssueLogById(issueLogVo
					.getForIssueLog().getId());
			String message = getTellPersonelMessage(issueLogVo, forIssueLog);
			// IssueLogNew forIssueLog =
			// issueLogDao.getUnDoIssueLogByIssueIdAndTargeOrgId(issueLogVo.getIssue().getId(),
			// issueLogVo.getTargeOrg().getId());
			for (Long orgId : (List<Long>) issueContext
					.getValue(IssueContext.TELL_TARGET_ORG_ID_LIST_KEY)) {
				issueLogVo.setDealType(IssueDealType.TELL);
				Organization org = new Organization();
				org.setId(orgId);
				issueLogVo.setTargeOrg(org);
				autoFillIssueLog(issueLogVo, issueLogVo, IssueDealState.DOING);
				issueState.tell(issueContext);
				issueLogVo.setDealState(IssueDealState.UN_READ);
				issueLogDao.addIssueLog(issueLogVo);
				sendPersonelMessageToOrganization(issueLogVo.getTargeOrg()
						.getId(), message);
				try {
					addRatingRecord(getCurrentLoginedOrganization(),
							createIssueTransferRegradedReason(issue), 1,
							CalendarUtil.today());
				} catch (JSONException e) {
					throw new BusinessValidationException("添加打分记录时发生错误");
				}
			}
		}
	}

	private String getTellPersonelMessage(IssueLogNew issueLogVo,
			IssueLogNew forLog) {
		String orgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getSession().getOrganization().getId())
				.getOrgName();
		return "由"
				+ orgName
				+ "移交到"
				+ issueLogVo.getTargeOrg().getOrgName()
				+ "一件服务事项,需要你处协助处理，单号为:"
				+ issueDao.getSimpleIssueById(issueLogVo.getIssue().getId())
						.getSerialNumber() + "。请及时处理，谢谢";
	}

	private IssueStateNew createIssueState(IssueContext issueContext) {
		if (issueContext == null
				|| issueContext.getValue(IssueContext.FOR_ISSUE_LOG_KEY) == null) {
			throw new BusinessValidationException("参数不对");
		}
		IssueLogNew forIssueLogNew = (IssueLogNew) issueContext
				.getValue(IssueContext.FOR_ISSUE_LOG_KEY);
		IssueStateNew issueState = IssueStateFactoryNew
				.createIssueStateInstance(forIssueLogNew.getStateClass());
		return issueState;
	}

	@SuppressWarnings("deprecation")
	public IssueNew getModifyIssueField(Long issueId,
			String currentOrgDisplayName, String lastUsername,
			Organization lastOrg, String lastOrgInternalCode, Integer stateType) {
		IssueNew issueNew = issueDao.getSimpleIssueById(issueId);
		issueNew.setId(issueId);
		issueNew.setCurrentOrgDisplayName(currentOrgDisplayName);
		issueNew.setLastUsername(lastUsername);
		issueNew.setLastOrg(lastOrg);
		issueNew.setLastOrgInternalCode(lastOrgInternalCode);
		issueNew.setStatus(stateType);
		return issueNew;
	}

	public void autoFillIssueLog(IssueLogNew issueLogVo,
			IssueLogNew forIssueLog, Long dealState) {
		autoFillDealOrgaInternalCode(issueLogVo);
		autoFillTargeOrgaInternalCode(issueLogVo);
		issueLogVo.setDealTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		Long dealStepIndexMax = issueLogDao
				.getDealMaxStepIndexByIssueId(forIssueLog.getIssue().getId());
		issueLogVo.setDealStepIndex(dealStepIndexMax == null ? 0
				: ++dealStepIndexMax);
		issueLogVo.setDealState(dealState);
		issueLogVo.setForIssueLog(forIssueLog);
		issueLogVo.setDealDescription(IssueDealType.toString(issueLogVo
				.getDealType().intValue()));
		autoFillBackIssueLog(issueLogVo, forIssueLog);
	}

	private void autoFillBackIssueLog(IssueLogNew issueLogVo,
			IssueLogNew forIssueLog) {
		if (issueLogVo.getDealType().intValue() == IssueDealType.CONCEPT
				.intValue()) {
			if (forIssueLog.getForIssueLog() != null) {
				issueLogVo.setBackIssueLog(forIssueLog);
			}
		} else if (issueLogVo.getDealType().intValue() == IssueDealType.COMMENT
				.intValue()
				|| issueLogVo.getDealType().intValue() == IssueDealType.SUBMIT_FORWARD
						.intValue()
				|| issueLogVo.getDealType().intValue() == IssueDealType.ASSIGN
						.intValue()) {
			issueLogVo.setBackIssueLog(forIssueLog.getBackIssueLog());
		} else if (issueLogVo.getDealType().intValue() == IssueDealType.SEND_BACK
				.intValue()) {
			issueLogVo.setBackIssueLog(issueLogDao.getIssueLogById(
					forIssueLog.getBackIssueLog().getId()).getBackIssueLog());
		}
	}

	private void autoFillDealOrgaInternalCode(IssueLogNew issueLogNew) {
		if (issueLogNew.getDealOrg() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			issueLogNew.setDealOrgInternalCode(getOrgaInternalCode(issueLogNew
					.getDealOrg().getId()));
		}
	}

	private void autoFillTargeOrgaInternalCode(IssueLogNew issueLogNew) {
		if (issueLogNew.getTargeOrg() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			issueLogNew.setTargeOrg(organizationDubboService
					.getFullOrgById(issueLogNew.getTargeOrg().getId()));
			issueLogNew.setTargeOrgInternalCode(getOrgaInternalCode(issueLogNew
					.getTargeOrg().getId()));
		}
	}

	private String getOrgaInternalCode(Long orgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		return organization.getOrgInternalCode();
	}

	private User getUser() {
		return permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
	}

	public IssueContext createIssueContext(IssueLogNew forIssueLog,
			IssueLogNew issueLogVo, IssueNew issue) {
		IssueContext issueContext = new IssueContext();
		if (forIssueLog != null) {
			issueContext.setVale(IssueContext.FOR_ISSUE_LOG_KEY, forIssueLog);
		}
		issueContext.setVale(IssueContext.ISSUE_LOG_NEW_KEY, issueLogVo);
		if (issue != null) {
			issueContext.setVale(IssueContext.ISSUE_KEY, issue);
		}
		return issueContext;
	}

	void sendPersonelMessageOnCreateIssue(IssueNew issue) {
		sendMessageToIssueOccurOrganization(issue);
		sendSeriousIssueMessage(issue);
	}

	private void sendSeriousIssueMessage(IssueNew issue) {
		if (issue.getIsEmergency() == null || !issue.getIsEmergency()
				|| issue.getImportant() == null || !issue.getImportant()) {
			return;
		}
		issue = issueDao.getFullIssueById(issue.getId());
		List<IssueType> contradictionsTypes = issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION);
		List<IssueType> securitytroubleTypes = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE);
		contradictionsTypes.addAll(securitytroubleTypes);
		// contradictionsTypes.retainAll(issue.getIssueTypes());
		if (contradictionsTypes.size() < 1)
			return;
		if (issue.getRelatePeopleCount() > 50) {
			String message = getSeriousIssueMessage(issue);
			Organization messageTarget = organizationDubboService
					.getSimpleOrgById(organizationDubboService
							.getTownOrganizationId(issue.getOccurOrg().getId()));
			if (messageTarget != null) {
				sendPersonelMessageToOrganization(messageTarget.getId(),
						message);
			}
			if (issue.getRelatePeopleCount() >= 100 && messageTarget != null) {
				messageTarget = organizationDubboService
						.getSimpleOrgById(messageTarget.getParentOrg().getId());
				sendPersonelMessageToOrganization(messageTarget.getId(),
						message);
				if (issue.getRelatePeopleCount() >= 200) {
					messageTarget = organizationDubboService
							.getSimpleOrgById(messageTarget.getParentOrg()
									.getId());
					sendPersonelMessageToOrganization(messageTarget.getId(),
							message);
				}
			}
		}
	}

	private void sendMessageToIssueOccurOrganization(IssueNew issue) {
		if (!selfCreateIssue(issue)) {
			sendPersonelMessageToOrganization(issue.getOccurOrg().getId(),
					getTransferIssueMessage(issue));
		}
	}

	private Organization getCurrentLoginedOrganization() {
		return organizationDubboService.getSimpleOrgById(ThreadVariable
				.getSession().getOrganization().getId());
	}

	private String getSeriousIssueMessage(IssueNew issue) {
		Organization occurOrg = organizationDubboService.getSimpleOrgById(issue
				.getOccurOrg().getId());
		return "在" + occurOrg.getOrgName() + "发生一起涉及"
				+ issue.getRelatePeopleCount() + "人的群体性事件,时间情况为："
				+ issue.getIssueContent() + "，单号为:" + issue.getSerialNumber()
				+ "。请及时处理，谢谢";
	}

	private boolean selfCreateIssue(IssueNew issue) {
		return ThreadVariable.getSession().getOrganization().getId()
				.equals(issue.getOccurOrg().getId());
	}

	@SuppressWarnings("deprecation")
	private void sendPersonelMessageToOrganization(Long orgId, String message) {
		platformaMessageService
				.sendPlatformMessageToOrg(orgId, message, "平台提醒");
	}

	private String getTransferIssueMessage(IssueNew issue) {
		String orgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getSession().getOrganization().getId())
				.getOrgName();
		return "由" + orgName + "移交到你处一件事项，单号为:" + issue.getSerialNumber()
				+ "。请及时处理，谢谢";
	}

	private String getSuperviseMessage(IssueLogNew issueLog) {
		String orgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getSession().getOrganization().getId())
				.getOrgName();
		return "由" + orgName
				+ getSuperviseTypeDescription(issueLog.getDealState())
				+ "你处一起事件处理。请及时处理，谢谢";
	}

	private String getSuperviseTypeDescription(Long dealState) {
		switch (dealState.intValue()) {
		case 2004:
			return "普通督办";
		case 2005:
			return "黄牌督办";
		case 2006:
			return "红牌督办";
		default:
			return "督办";
		}
	}

	private void addRatingRecord(Organization org, RegradedReason reason,
			double point, Date applyDate) throws JSONException {
		regradedPointService.bonusPoints(org, RegradedType.ASSESSMENTUSER,
				reason, point, applyDate);
	}

	private void addRatingRecord(Organization org, RegradedReason reason,
			IssueOperate operate, Date applyDate) throws JSONException {
		double cent = issueAccessConfigService.getIssueScoresConfig(operate);
		regradedPointService.bonusPoints(org, RegradedType.ASSESSMENTUSER,
				reason, cent, applyDate);
	}

	private void deductSupervisePoint(IssueStep step, IssueOperate superviseType)
			throws JSONException {
		IssueRegradedReason regradedReason = new IssueRegradedReason();
		IssueNew issueNew = issueDao
				.getSimpleIssueById(step.getIssue().getId());
		Organization targeOrg = organizationDubboService.getSimpleOrgById(step
				.getTarget().getId());
		regradedReason.setIssueId(issueNew.getId());
		regradedReason.setIssueLogId(step.getId());
		double cent = issueAccessConfigService
				.getIssueScoresConfig(superviseType);
		regradedReason.setPoints(cent);
		regradedReason.setSerialNumber(issueNew.getSerialNumber());
		regradedPointService.deductPoints(targeOrg, superviseType.toString(),
				regradedReason, superviseType.getCent(),
				CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
	}

	private RegradedReason createRegistIssueRegradedReason(IssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		String organizationName = organizationDubboService.getSimpleOrgById(
				issue.getOccurOrg().getId()).getOrgName();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getUser().getName() + "于" + getNowDateStringValue()
				+ "新增一项事件处理到" + organizationName);
		return result;
	}

	private RegradedReason createIssueTransferRegradedReason(IssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		String organizationName = organizationDubboService.getSimpleOrgById(
				issue.getOccurOrg().getId()).getOrgName();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getUser().getName() + getNowDateStringValue()
				+ "移交一项事件处理到" + organizationName + "。服务单号为"
				+ issue.getSerialNumber());
		return result;
	}

	private RegradedReason createIssueDealRegradedReason(IssueNew issue,
			IssueLogNew log, Long dealType) {
		IssueDealRegradeReason result = new IssueDealRegradeReason();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setLogId(log.getId());
		String dealString = convertDealTypeToString(dealType);
		result.setDealType(dealString);
		result.setContent(getUser().getUserName() + getNowDateStringValue()
				+ "处理了一项事件处理,将其" + dealString + "。服务单号为"
				+ issue.getSerialNumber());
		return result;
	}

	private String getNowDateStringValue() {
		return CalendarUtil.format("yyyy-MM-dd HH:mm:ss", new Date());
	}

	private String convertDealTypeToString(Long dealType) {
		return IssueDealType.toString(dealType.intValue());
	}

	private Organization getCommandCenter() {
		Organization root = organizationDubboService
				.findOrganizationsByParentId(null).get(0);
		return organizationDubboService.getFullOrgById(root.getId());
	}

	public IssueLogNew addIssueLog(IssueLogNew log) {
		return issueLogDao.addIssueLog(log);
	}

	public List<IssueOperate> getCurrentLoginedOrgCanDo(IssueNew issue,
			Organization org) {
		if (org == null) {
			org = ThreadVariable.getSession().getOrganization();
		}
		org = organizationDubboService.getFullOrgById(org.getId());
		IssueStep step = issueWorkFlowDao.findLastNotCompleteIssueStepByOrg(
				issue.getId(), org.getId());
		if (step == null) {
			return new ArrayList<IssueOperate>();
		}
		step.setTarget(organizationDubboService.getFullOrgById(step.getTarget()
				.getId()));
		return IssueOperateHelper.getCanDoOperationByOrg(step, org);
	}

	public IssueStep getIssueStepById(Long stepId) {
		return issueWorkFlowDao.getIssueStepById(stepId);
	}

	public IssueStep reportTo(IssueNew issue, IssueStep step) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					getCommandCenter(), null);
			IssueStep reuslt = issueWorkFlowDao.addIssueStep(IssueOperateHelper
					.constructIssueStateFromStep(step).reportTo(issue, step,
							context));
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			sendPersonelMessageToOrganization(
					context.getTargetOrg().getId(),
					getTransferPersonelMessage(issue, context
							.getCurrentLoginedOrg().getOrgName()));
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue),
					IssueOperate.REPORT_TO, CalendarUtil.today());
			return reuslt;
		} catch (Exception e) {
			throw new BusinessValidationException("上报指挥中心过程中发生错误");
		}
	}

	public IssueStep giveTo(IssueNew issue, IssueStep step,
			IssueLogNew issueLog, List<Organization> tellOrgs,
			String[] attachFiles) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					issueLog.getTargeOrg(), tellOrgs);
			IssueStepGroup steps = IssueOperateHelper
					.constructIssueStateFromStep(step).giveTo(issue, step,
							context);
			IssueStep reuslt = saveTransferResult(issue, context, steps,
					IssueOperate.GIVETO);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, issueLog, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("交办过程中发生错误", e);
		}
	}

	public IssueStep concept(IssueNew issue, IssueStep step) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step).concept(issue,
					step, context);
			step = issueWorkFlowDao.updateIssueStepExceptOrg(step);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("受理中发生错误", e);
		}
	}

	public IssueStep comment(IssueNew issue, IssueStep step, IssueLogNew log,
			String[] attachFiles) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step).comment(issue,
					step, context);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			saveIssueAttachFiles(issue, log, attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("继续办理过程中发生错误", e);
		}
	}

	public IssueStep complete(IssueNew issue, IssueStep step, IssueLogNew log,
			String[] attachFiles) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step).complete(
					issue, step, context);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("办结过程中发生错误", e);
		}
	}

	public IssueStep backUps(IssueNew issue, IssueStep step, IssueLogNew log) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step).backUps(issue,
					step, context);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("备案过程中发生错误", e);
		}
	}

	public IssueStep assign(IssueNew issue, IssueStep step, IssueLogNew log,
			List<Organization> tells, String[] attachFiles) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					log.getTargeOrg(), tells);
			IssueStepGroup steps = IssueOperateHelper
					.constructIssueStateFromStep(step).assign(issue, step,
							context);
			IssueStep reuslt = saveTransferResult(issue, context, steps,
					IssueOperate.ASSIGN);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("交办过程中发生错误", e);
		}
	}

	public IssueStep back(IssueNew issue, IssueStep step, IssueLogNew log,
			String[] attachFiles) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					organizationDubboService.getFullOrgById(step.getBackTo()
							.getTarget().getId()), null);
			IssueStepGroup steps = IssueOperateHelper
					.constructIssueStateFromStep(step).back(issue, step,
							context);
			IssueStep reuslt = saveTransferResult(issue, context, steps,
					IssueOperate.BACK);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("回退过程中发生错误", e);
		}
	}

	public IssueStep submit(IssueNew issue, IssueStep step, IssueLogNew log,
			List<Organization> tells, String[] attachFiles) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					log.getTargeOrg(), tells);
			IssueSkiprule issueSkiprule = issueSkipruleService
					.getIssueSkipruleByIssue(issue, step);
			IssueStepGroup steps = IssueOperateHelper
					.constructIssueStateFromStep(step).submit(issue, step,
							context, issueSkiprule);
			IssueStep reuslt = saveTransferResult(issue, context, steps,
					IssueOperate.SUBMIT);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("上报过程中发生错误", e);
		}
	}

	public IssueStep read(IssueNew issue, IssueStep step) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step).read(issue,
					step, context);
			step = issueWorkFlowDao.updateIssueStepExceptOrg(step);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("阅读中发生错误", e);
		}
	}

	public IssueStep supervise(IssueNew issue, IssueStep step, int superviseType) {
		try {
			IssueOperationContext context = constructSuperviseContext(
					step.getTarget(), IssueOperate.parse(superviseType));
			IssueOperateHelper.constructIssueStateFromStep(step).supervise(
					issue, step, context);
			deductSupervisePoint(step, IssueOperate.parse(superviseType));
			return issueWorkFlowDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("督办中发生错误", e);
		}
	}

	public IssueStep cancelSupervise(IssueNew issue, IssueStep step) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step)
					.cancelSupervise(issue, step, context);
			return issueWorkFlowDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("督办中发生错误", e);
		}
	}

	public IssueStep instruct(IssueNew issue, IssueStep step) {
		try {
			IssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			IssueOperateHelper.constructIssueStateFromStep(step).instruct(
					issue, step, context);
			return issueWorkFlowDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("督办中发生错误", e);
		}
	}

	public IssueNew cancelUrgent(IssueNew issue) {
		issueDao.updateIssueUrgentState(issue.getId(), Boolean.FALSE);
		return issue;
	}

	public IssueNew urgent(IssueNew issue) {
		issueDao.updateIssueUrgentState(issue.getId(), Boolean.TRUE);
		return issue;
	}

	public void historical(IssueNew issue) {
		Organization org = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		HistoricalIssue history = new HistoricalIssue();
		history.setIssueNew(issue);
		history.setOrganization(org);
		history.setOrgInternalCode(org.getOrgInternalCode());
		historicalIssueService.addHistoricalIssue(history);
	}

	public void cancelHistorical(IssueNew issue) {
		historicalIssueService.deleteHistoricalIssueByIssueIdAndOrgId(
				issue.getId(), ThreadVariable.getUser().getOrganization()
						.getId());
	}

	public void publiclty(IssueNew issue, IssueLogNew issueLogNew) {
		Organization org = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		PublicltyCass publicltyCass = new PublicltyCass();
		publicltyCass.setIssueNew(issue);
		publicltyCass.setOrganization(org);
		publicltyCass.setIssueLogNew(issueLogNew);
		// publicltyCass.setOrgInternalCode(org.getOrgInternalCode());
		publicltyCassService.addPublicltyCass(publicltyCass);
	}

	public void cancelPubliclty(IssueNew issue) {
		publicltyCassService.deletePublicltyCassByIssueIdAndOrgId(
				issue.getId(), ThreadVariable.getUser().getOrganization()
						.getId());
	}

	private IssueOperationContext constructSuperviseContext(
			Organization target, IssueOperate superviseType) {
		Organization org = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		IssueOperationContext context = new IssueOperationContext(org, target,
				"", ThreadVariable.getSession().getUserRealName());
		context.addParameter(IssueOperationContext.SUPERVISE_LEVEL_KEY,
				getSuperviseLevel(superviseType));
		return context;
	}

	private int getSuperviseLevel(IssueOperate superviseType) {
		if (IssueOperate.NORMAL_SUPERVISE.equals(superviseType)) {
			return IssueState.NORMAL_SUPERVISE;
		} else if (IssueOperate.YELLOW_SUPERVISE.equals(superviseType)) {
			return IssueState.YELLOW_CARD_SUPERVISE;
		} else if (IssueOperate.RED_SUPERVISE.equals(superviseType)) {
			return IssueState.RED_CARD_SUPERVISE;
		} else {
			return IssueState.NO_SUPERVISE;
		}
	}

	private IssueOperationContext constructIssueOperationContext(
			Organization target, List<Organization> tells) {
		Organization org = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		IssueOperationContext context = new IssueOperationContext(org, target,
				"", ThreadVariable.getSession().getUserRealName());
		if (tells != null) {
			for (Organization tellorg : tells) {
				context.addTellOrg(tellorg);
			}
		}
		return context;
	}

	private IssueStep saveTransferResult(IssueNew issue,
			IssueOperationContext context, IssueStepGroup group,
			IssueOperate operate) throws Exception {
		IssueStep result = issueWorkFlowDao.addIssueStep(group.getKeyStep());
		sendPersonelMessageToOrganization(
				context.getTargetOrg().getId(),
				getTransferPersonelMessage(issue, context
						.getCurrentLoginedOrg().getOrgName()));
		addRatingRecord(getCurrentLoginedOrganization(),
				createIssueTransferRegradedReason(issue), operate,
				CalendarUtil.today());
		addRatingRecord(context.getTargetOrg(),
				createIssueTransferRegradedReason(issue), operate,
				CalendarUtil.today());
		if (group.hasIncidentalStep()) {
			String message = getTellPersonelMessage(issue, context
					.getCurrentLoginedOrg().getOrgName(), context
					.getTargetOrg().getOrgName());
			for (IssueStep ostep : group.getIncidentalSteps()) {
				IssueStep issueStep = issueWorkFlowDao.addIssueStep(ostep);
				tqSolrSearchCommonOperate.commonOperate(
						IssueSolrDocumentConvert.createIssueSolrDocument(group
								.getKeyStep().getIssue(), issueStep),
						TqSolrSearchOperateType.ADD_OR_UPDATE);
				sendPersonelMessageToOrganization(ostep.getTarget().getId(),
						message);
				addRatingRecord(ostep.getTarget(),
						createIssueTransferRegradedReason(issue), operate,
						CalendarUtil.today());
			}
		}
		return result;
	}

	public IssueStep getFullIssueStepById(Long stepId) {
		IssueStep step = getIssueStepById(stepId);
		step.setSource(organizationDubboService.getFullOrgById(step.getSource()
				.getId()));
		step.setTarget(organizationDubboService.getFullOrgById(step.getTarget()
				.getId()));
		if (step.getBackTo() != null) {
			step.setBackTo(getIssueStepById(step.getBackTo().getId()));
		}
		return step;
	}

	public List<Organization> findTellTargetsByName(IssueOperate issueOperate,
			Long stepId, String tag, String exceptOrgIds) {
		if (!StringUtil.isStringAvaliable(tag)) {
			return new ArrayList<Organization>();
		}
		IssueStep step = getIssueStepById(stepId);
		if (IssueOperate.GIVETO.equals(issueOperate)) {
			return findTellTargetsByName(tag, exceptOrgIds, step);
		} else if (IssueOperate.SUBMIT.equals(issueOperate)) {
			return findSubmitTellTargetsByName(tag, exceptOrgIds, step);
		} else if (IssueOperate.ASSIGN.equals(issueOperate)) {
			return findAssignTellTargetsByName(tag, exceptOrgIds, step);
		} else {
			return new ArrayList<Organization>();
		}
	}

	public List<Organization> findTransferTargetsByName(
			IssueOperate issueOperate, Long stepId, String tag,
			String exceptOrgIds, boolean searchAdmin) {
		if (!StringUtil.isStringAvaliable(tag)) {
			return new ArrayList<Organization>();
		}
		IssueStep step = getIssueStepById(stepId);
		if (IssueOperate.GIVETO.equals(issueOperate)) {
			return findGiveToTargetsByName(tag, exceptOrgIds, searchAdmin, step);
		} else if (IssueOperate.SUBMIT.equals(issueOperate)) {
			return findSubmitTargetsByName(tag, exceptOrgIds, searchAdmin, step);
		} else if (IssueOperate.ASSIGN.equals(issueOperate)) {
			return findAssignTargetsByName(tag, exceptOrgIds, searchAdmin, step);
		} else {
			return new ArrayList<Organization>();
		}
	}

	private List<Organization> findAssignTellTargetsByName(String tag,
			String exceptOrgIds, IssueStep step) {
		if (exceptOrgIds == null) {
			exceptOrgIds = "";
		}
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType().getInternalId()) {
			List<Organization> result = new ArrayList<Organization>();
			result.addAll(organizationDubboService
					.findFunOrgsByParentFunOrgIdAndName(org.getId(), tag));
			result.addAll(organizationDubboService
					.findAdminOrgsByParentIdAndName(org.getParentOrg().getId(),
							tag));
			exceptOrgIds = exceptOrgIds + ",";
			for (int index = result.size(); index > 0; index--) {
				if (exceptOrgIds.indexOf(result.get(index - 1).getId() + ",") > -1) {
					result.remove(index - 1);
				}
			}
			return result;
		} else {
			List<Organization> result = new ArrayList<Organization>();
			result.addAll(organizationDubboService
					.findFunOrgsByParentIdAndName(org.getId(), tag));
			result.addAll(organizationDubboService
					.findAdminOrgsByParentIdAndName(org.getId(), tag));
			exceptOrgIds = exceptOrgIds + ",";
			for (int index = result.size(); index > 0; index--) {
				if (exceptOrgIds.indexOf(result.get(index - 1).getId() + ",") > -1) {
					result.remove(index - 1);
				}
			}
			return result;
		}
	}

	private List<Organization> findSubmitTellTargetsByName(String tag,
			String exceptOrgIds, IssueStep step) {
		if (exceptOrgIds == null) {
			exceptOrgIds = "";
		}
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType().getInternalId()) {
			List<Organization> result = new ArrayList<Organization>();
			exceptOrgIds = exceptOrgIds + ",";
			if (org.getParentFunOrg() != null
					&& exceptOrgIds
							.indexOf(org.getParentFunOrg().getId() + ",") == -1) {
				result.add(organizationDubboService.getSimpleOrgById(org
						.getParentFunOrg().getId()));
			}
			if (org.getParentOrg() != null
					&& exceptOrgIds.indexOf(org.getParentOrg().getId() + ",") == -1) {
				result.add(organizationDubboService.getSimpleOrgById(org
						.getParentOrg().getId()));
			}
			for (int index = result.size(); index > 0; index--) {
				if (!result.get(index - 1).getOrgName().startsWith(tag)
						&& !result.get(index - 1).getSimplePinyin()
								.startsWith(tag)
						&& !result.get(index - 1).getFullPinyin()
								.startsWith(tag)) {
					result.remove(index - 1);
				}
			}
			return result;
		} else {
			List<Organization> result = new ArrayList<Organization>();
			if (org.getParentOrg() != null) {
				PropertyDict dict = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTIONAL_ORG).get(0);
				result = issueWorkFlowDao.findChildOrgsByParentIdAndName(dict,
						org.getParentOrg().getId(), tag, exceptOrgIds, 10);
				exceptOrgIds = exceptOrgIds + ",";
				if (exceptOrgIds.indexOf(org.getParentOrg().getId() + ",") == -1) {
					// Organization
					// parent=organizationDubboService.getSimpleOrgById(org.getParentOrg().getId());
					result.add(organizationDubboService.getSimpleOrgById(org
							.getParentOrg().getId()));
				}
				// if ((parent.getOrgName().startsWith(tag)
				// || parent.getSimplePinyin().startsWith(tag)
				// || parent.getFullPinyin().startsWith(tag))
				// &&){
				// result.add(parent);
				// }
				return result;
			} else {
				return new ArrayList<Organization>();
			}
		}
	}

	private List<Organization> findAssignTargetsByName(String tag,
			String exceptOrgIds, boolean searchAdmin, IssueStep step) {
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType().getInternalId()) {
			return issueWorkFlowDao.findChildOrgsByParentFunIdAndName(
					org.getId(), tag, exceptOrgIds, 10);
		} else {
			PropertyDict dict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							searchAdmin ? OrganizationType.ADMINISTRATIVE_REGION
									: OrganizationType.FUNCTIONAL_ORG).get(0);
			return issueWorkFlowDao.findChildOrgsByParentIdAndName(dict, step
					.getTarget().getId(), tag, exceptOrgIds, 10);
		}
	}

	private List<Organization> findSubmitTargetsByName(String tag,
			String exceptOrgIds, boolean searchAdmin, IssueStep step) {
		Organization org = organizationDubboService.getFullOrgById(step
				.getTarget().getId());
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						searchAdmin ? OrganizationType.ADMINISTRATIVE_REGION
								: OrganizationType.FUNCTIONAL_ORG).get(0);
		if (exceptOrgIds == null) {
			exceptOrgIds = "";
		}
		if (searchAdmin) {
			if (org.getParentOrg() == null) {
				return new ArrayList<Organization>();
			} else {
				exceptOrgIds = exceptOrgIds + ",";
				String parentId = org.getParentOrg().getId() + ",";
				if (exceptOrgIds.indexOf(parentId) != -1) {
					return new ArrayList<Organization>();
				} else {
					List<Organization> result = new ArrayList<Organization>();
					result.add(organizationDubboService.getSimpleOrgById(org
							.getParentOrg().getId()));
					return result;
				}
			}
		} else {
			if (OrganizationType.FUNCTIONAL_ORG == org.getOrgType()
					.getInternalId()) {
				if (org.getParentFunOrg() == null) {
					return new ArrayList<Organization>();
				} else {
					exceptOrgIds = exceptOrgIds + ",";
					String parentId = org.getParentFunOrg().getId() + ",";
					if (exceptOrgIds.indexOf(parentId) != -1) {
						return new ArrayList<Organization>();
					} else {
						List<Organization> result = new ArrayList<Organization>();
						result.add(organizationDubboService
								.getSimpleOrgById(org.getParentFunOrg().getId()));
						return result;
					}
				}
			} else {
				if (org.getParentOrg() != null) {
					return issueWorkFlowDao.findChildOrgsByParentIdAndName(
							dict, org.getParentOrg().getId(), tag,
							exceptOrgIds, 10);
				} else {
					return new ArrayList<Organization>();
				}
			}
		}
	}

	private List<Organization> findGiveToTargetsByName(String tag,
			String exceptOrgIds, boolean searchAdmin, IssueStep step) {
		Organization org = organizationDubboService.getSimpleOrgById(step
				.getTarget().getId());
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY,
						searchAdmin ? OrganizationType.ADMINISTRATIVE_REGION
								: OrganizationType.FUNCTIONAL_ORG).get(0);
		return issueWorkFlowDao.findChildOrgsByOrgcodeAndName(dict,
				org.getOrgInternalCode(), tag, exceptOrgIds, 10);
	}

	private List<Organization> findTellTargetsByName(String tag,
			String exceptOrgIds, IssueStep step) {
		Organization org = organizationDubboService.getSimpleOrgById(step
				.getTarget().getId());
		// PropertyDict
		// dict=propertyDictService.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
		// searchAdmin?OrganizationType.ADMINISTRATIVE_REGION:OrganizationType.FUNCTIONAL_ORG).get(0);
		return issueWorkFlowDao.findChildOrgsByOrgcodeAndName(null,
				org.getOrgInternalCode(), tag, exceptOrgIds, 10);
	}

	private String getTellPersonelMessage(IssueNew issue, String sourceName,
			String targetName) {
		return "由" + sourceName + "移交到" + targetName + "一件服务事项,需要你处协助处理，单号为:"
				+ issue.getSerialNumber() + "。请及时处理，谢谢";
	}

	private String getTransferPersonelMessage(IssueNew issue, String sourceName) {
		return "由" + sourceName + "移交到你处一件服务事项,单号为:" + issue.getSerialNumber()
				+ "。请及时处理，谢谢";
	}

	private void saveIssueAttachFiles(IssueNew issue, IssueLogNew log,
			String[] files) throws Exception {
		if (files == null || files.length == 0)
			return;
		for (String fileName : files) {
			StoredFile file = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.ISSUE_ATTACHFILE);
			String fileActualPath = file.getStoredFilePath() + "/"
					+ file.getStoredFileName();
			IssueAttachFile attachFile = new IssueAttachFile();
			attachFile.setFileActualUrl(fileActualPath);
			attachFile.setFileName(fileName);
			attachFile.setIssue(issue);
			attachFile.setIssueLog(log);
			issueAttachFileDao.addIssueAttachFile(attachFile);
		}

	}

}
