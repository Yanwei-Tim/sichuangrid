package com.tianque.fourTeams.service.impl;

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
import com.tianque.fourTeams.dao.FourTeamsIssueAttachFileDao;
import com.tianque.fourTeams.dao.FourTeamsIssueLogDaoNew;
import com.tianque.fourTeams.dao.FourTeamsIssueNewDao;
import com.tianque.fourTeams.dao.FourTeamsIssueWorkFlowDao;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsHistoricalIssue;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPublicltyCass;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueAccessConfigService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSkipruleService;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperateHelper;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.service.FourTeamsHistoricalIssueService;
import com.tianque.fourTeams.service.FourTeamsPublicltyCassService;
import com.tianque.fourTeams.state.FourTeamsIssueContext;
import com.tianque.fourTeams.state.FourTeamsIssueStateFactoryNew;
import com.tianque.fourTeams.state.FourTeamsIssueStateNew;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.RegradedPointService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueDealType;
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

@Component("fourTeamsIssueBusinessDelegate")
@Transactional
public class FourTeamsIssueBusinessDelegate {
	@Autowired
	private FourTeamsIssueLogDaoNew issueLogDao;
	@Autowired
	private FourTeamsIssueNewDao issueDao;
	@Autowired
	private FourTeamsIssueWorkFlowDao issueWorkFlowDao;
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
	private FourTeamsIssueAttachFileDao issueAttachFileDao;
	@Autowired
	private FourTeamsHistoricalIssueService historicalIssueService;
	@Autowired
	private FourTeamsPublicltyCassService publicltyCassService;
	@Autowired
	private FourTeamsIssueAccessConfigService issueAccessConfigService;
	@Autowired
	private FourTeamsIssueSkipruleService issueSkipruleService;

	private FourTeamsIssueStateNew issueState;

	public FourTeamsIssueStep register(FourTeamsIssueNew issue) {
		if (!validateRegisterIssueLog(issue)) {
			throw new BusinessValidationException("参数不正确");
		}
		issue.setCreateOrg(organizationDubboService.getFullOrgById(issue
				.getCreateOrg().getId()));
		issue.setOccurOrg(organizationDubboService.getFullOrgById(issue
				.getOccurOrg().getId()));
		FourTeamsIssueStep reuslt = issueWorkFlowDao
				.addIssueStep(FourTeamsIssueOperateHelper.register(issue));
		issueDao.updateIssueCurrentStepAndOrg(issue);
		sendPersonelMessageOnCreateIssue(issue);
		try {
			addRatingRecord(issue.getOccurOrg(),
					createRegistIssueRegradedReason(issue), 1,
					issue.getCreateDate());
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		return reuslt;
	}

	public FourTeamsIssueLogNew registerIssueLog(FourTeamsIssueNew issueNew) {
		if (!validateRegisterIssueLog(issueNew)) {
			throw new BusinessValidationException("参数不正确");
		}
		FourTeamsIssueLogNew issueLogNew = addLogWhenAddIssue(issueNew);
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
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		return issueLogNew;
	}

	private FourTeamsIssueLogNew conceptIssueWhenAddIssue(
			FourTeamsIssueLogNew forIssueLog, FourTeamsIssueNew issueNew) {
		FourTeamsIssueLogNew issueLogVo = new FourTeamsIssueLogNew();
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

	private FourTeamsIssueLogNew addLogWhenAddIssue(FourTeamsIssueNew issueNew) {
		FourTeamsIssueLogNew issueLogNew = new FourTeamsIssueLogNew();
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

	public FourTeamsIssueLogNew superviseIssueLog(
			FourTeamsIssueLogNew issueLogNew, FourTeamsIssueLogNew issueLogVo) {
		issueLogVo.setTargeOrg(issueLogNew.getTargeOrg());
		autoFillIssueLog(issueLogVo, issueLogNew, issueLogVo.getDealState());
		sendPersonelMessageToOrganization(issueLogNew.getTargeOrg().getId(),
				getSuperviseMessage(issueLogVo));
		return issueLogDao.addIssueLog(issueLogVo);
	}

	public void deductPoints(FourTeamsIssueLogNew issueLog, double points,
			String superviseType) throws JSONException {
		IssueRegradedReason regradedReason = new IssueRegradedReason();
		FourTeamsIssueNew issueNew = issueDao.getSimpleIssueById(issueLog
				.getIssue().getId());
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

	private boolean validateRegisterIssueLog(FourTeamsIssueNew issue) {
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

	public FourTeamsIssueLogNew concept(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.concept(issueContext);

		FourTeamsIssueLogNew issueLogReturn = issueLogDao
				.addIssueLog(issueContext.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogReturn.getDealTime());
		issueDao.updateIssueWhenDeal((FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY));

		return issueLogReturn;
	}

	public FourTeamsIssueLogNew read(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.read(issueContext);
		FourTeamsIssueLogNew issueLogReturn = issueLogDao
				.addIssueLog(issueContext.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogReturn.getDealTime());
		try {
			FourTeamsIssueNew issue = issueDao.getSimpleIssueById(issueContext
					.getForIssueLog().getIssue().getId());
			addRatingRecord(
					getCurrentLoginedOrganization(),
					createIssueDealRegradedReason(issue, issueLogReturn,
							IssueDealType.READ), 1, CalendarUtil.today());
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		return issueLogReturn;
	}

	public FourTeamsIssueLogNew complete(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.complete(issueContext);

		FourTeamsIssueLogNew issueLogReturn = issueLogDao
				.addIssueLog(issueContext.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogReturn.getDealTime());
		issueDao.updateIssueWhenDeal((FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY));
		try {
			FourTeamsIssueNew issue = issueDao.getSimpleIssueById(issueContext
					.getIssue().getId());
			addRatingRecord(
					getCurrentLoginedOrganization(),
					createIssueDealRegradedReason(issue, issueLogReturn,
							IssueDealType.COMPLETE), 1, CalendarUtil.today());
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		return issueLogReturn;
	}

	public FourTeamsIssueLogNew comment(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		if (issueState == null) {
			throw new BusinessValidationException("状态不正确!");
		}
		issueState.comment(issueContext);

		FourTeamsIssueLogNew issueLogreReturn = issueLogDao
				.addIssueLog(issueContext.getIssueLogNew());
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		issueDao.updateIssueWhenDeal((FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY));

		return issueLogreReturn;
	}

	public FourTeamsIssueLogNew submitForward(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		issueState.submitForward(issueContext);
		FourTeamsIssueLogNew issueLogVo = issueContext.getIssueLogNew();
		FourTeamsIssueLogNew issueLogreReturn = issueLogDao
				.addIssueLog(issueLogVo);
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		issueDao.updateIssueWhenDeal((FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY));
		FourTeamsIssueNew issue = (FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY);
		sendPersonelMessageToOrganization(issueLogVo.getTargeOrg().getId(),
				getTransferIssueMessage(issue));
		try {
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue), 1,
					CalendarUtil.today());
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		doTell(issueContext, issueLogVo, issue);
		return issueLogreReturn;
	}

	public FourTeamsIssueLogNew assign(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		issueState.assign(issueContext);
		FourTeamsIssueLogNew issueLogVo = issueContext.getIssueLogNew();
		FourTeamsIssueLogNew issueLogreReturn = issueLogDao
				.addIssueLog(issueLogVo);
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		FourTeamsIssueNew issue = (FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY);
		issueDao.updateIssueWhenDeal(issue);
		sendPersonelMessageToOrganization(issueLogVo.getTargeOrg().getId(),
				getTransferIssueMessage(issue));
		try {
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue), 1,
					CalendarUtil.today());
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		doTell(issueContext, issueLogVo, issue);
		return issueLogreReturn;
	}

	public FourTeamsIssueLogNew sendback(FourTeamsIssueContext issueContext) {
		issueState = createIssueState(issueContext);
		issueState.sendback(issueContext);
		FourTeamsIssueLogNew issueLogVo = issueContext.getIssueLogNew();
		FourTeamsIssueLogNew issueLogreReturn = issueLogDao
				.addIssueLog(issueLogVo);
		issueLogDao.updateIssueLogDealState(issueContext.getForIssueLog()
				.getId(), issueContext.getForIssueLog().getDealState());
		issueLogDao.updateIssueLogLogCompleteTime(issueContext.getForIssueLog()
				.getId(), issueLogreReturn.getDealTime());
		issueDao.updateIssueWhenDeal((FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY));
		FourTeamsIssueNew issue = (FourTeamsIssueNew) issueContext
				.getValue(FourTeamsIssueContext.ISSUE_KEY);
		sendPersonelMessageToOrganization(issueLogVo.getTargeOrg().getId(),
				getTransferIssueMessage(issue));
		try {
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue), 1,
					CalendarUtil.today());
		} catch (JSONException e) {
			throw new ServiceValidationException("添加打分记录时发生错误", e);
		}
		return issueLogreReturn;
	}

	private void doTell(FourTeamsIssueContext issueContext,
			FourTeamsIssueLogNew issueLogVo, FourTeamsIssueNew issue) {
		if (issueContext
				.getValue(FourTeamsIssueContext.TELL_TARGET_ORG_ID_LIST_KEY) != null) {
			FourTeamsIssueLogNew forIssueLog = issueLogDao
					.getIssueLogById(issueLogVo.getForIssueLog().getId());
			String message = getTellPersonelMessage(issueLogVo, forIssueLog);
			// IssueLogNew forIssueLog =
			// issueLogDao.getUnDoIssueLogByIssueIdAndTargeOrgId(issueLogVo.getIssue().getId(),
			// issueLogVo.getTargeOrg().getId());
			for (Long orgId : (List<Long>) issueContext
					.getValue(FourTeamsIssueContext.TELL_TARGET_ORG_ID_LIST_KEY)) {
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
					throw new ServiceValidationException("添加打分记录时发生错误", e);
				}
			}
		}
	}

	private String getTellPersonelMessage(FourTeamsIssueLogNew issueLogVo,
			FourTeamsIssueLogNew forLog) {
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

	private FourTeamsIssueStateNew createIssueState(
			FourTeamsIssueContext issueContext) {
		if (issueContext == null
				|| issueContext
						.getValue(FourTeamsIssueContext.FOR_ISSUE_LOG_KEY) == null) {
			throw new BusinessValidationException("参数不对");
		}
		FourTeamsIssueLogNew forIssueLogNew = (FourTeamsIssueLogNew) issueContext
				.getValue(FourTeamsIssueContext.FOR_ISSUE_LOG_KEY);
		FourTeamsIssueStateNew issueState = FourTeamsIssueStateFactoryNew
				.createIssueStateInstance(forIssueLogNew.getStateClass());
		return issueState;
	}

	@SuppressWarnings("deprecation")
	public FourTeamsIssueNew getModifyIssueField(Long issueId,
			String currentOrgDisplayName, String lastUsername,
			Organization lastOrg, String lastOrgInternalCode, Integer stateType) {
		FourTeamsIssueNew issueNew = issueDao.getSimpleIssueById(issueId);
		issueNew.setId(issueId);
		issueNew.setCurrentOrgDisplayName(currentOrgDisplayName);
		issueNew.setLastUsername(lastUsername);
		issueNew.setLastOrg(lastOrg);
		issueNew.setLastOrgInternalCode(lastOrgInternalCode);
		issueNew.setStatus(stateType);
		return issueNew;
	}

	public void autoFillIssueLog(FourTeamsIssueLogNew issueLogVo,
			FourTeamsIssueLogNew forIssueLog, Long dealState) {
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

	private void autoFillBackIssueLog(FourTeamsIssueLogNew issueLogVo,
			FourTeamsIssueLogNew forIssueLog) {
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

	private void autoFillDealOrgaInternalCode(FourTeamsIssueLogNew issueLogNew) {
		if (issueLogNew.getDealOrg() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			issueLogNew.setDealOrgInternalCode(getOrgaInternalCode(issueLogNew
					.getDealOrg().getId()));
		}
	}

	private void autoFillTargeOrgaInternalCode(FourTeamsIssueLogNew issueLogNew) {
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

	public FourTeamsIssueContext createIssueContext(
			FourTeamsIssueLogNew forIssueLog, FourTeamsIssueLogNew issueLogVo,
			FourTeamsIssueNew issue) {
		FourTeamsIssueContext issueContext = new FourTeamsIssueContext();
		if (forIssueLog != null) {
			issueContext.setVale(FourTeamsIssueContext.FOR_ISSUE_LOG_KEY,
					forIssueLog);
		}
		issueContext.setVale(FourTeamsIssueContext.ISSUE_LOG_NEW_KEY,
				issueLogVo);
		if (issue != null) {
			issueContext.setVale(FourTeamsIssueContext.ISSUE_KEY, issue);
		}
		return issueContext;
	}

	void sendPersonelMessageOnCreateIssue(FourTeamsIssueNew issue) {
		sendMessageToIssueOccurOrganization(issue);
		sendSeriousIssueMessage(issue);
	}

	private void sendSeriousIssueMessage(FourTeamsIssueNew issue) {
		if (issue.getIsEmergency() == null || !issue.getIsEmergency()
				|| issue.getImportant() == null || !issue.getImportant()) {
			return;
		}
		issue = issueDao.getFullIssueById(issue.getId());
		List<IssueType> contradictionsTypes = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.CONTRADICTION);
		List<IssueType> securitytroubleTypes = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.SECURITYTROUBLE);
		contradictionsTypes.addAll(securitytroubleTypes);
		contradictionsTypes.retainAll(issue.getIssueTypes());
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

	private void sendMessageToIssueOccurOrganization(FourTeamsIssueNew issue) {
		if (!selfCreateIssue(issue)) {
			sendPersonelMessageToOrganization(issue.getOccurOrg().getId(),
					getTransferIssueMessage(issue));
		}
	}

	private Organization getCurrentLoginedOrganization() {
		return organizationDubboService.getSimpleOrgById(ThreadVariable
				.getSession().getOrganization().getId());
	}

	private String getSeriousIssueMessage(FourTeamsIssueNew issue) {
		Organization occurOrg = organizationDubboService.getSimpleOrgById(issue
				.getOccurOrg().getId());
		return "在" + occurOrg.getOrgName() + "发生一起涉及"
				+ issue.getRelatePeopleCount() + "人的群体性事件,时间情况为："
				+ issue.getIssueContent() + "，单号为:" + issue.getSerialNumber()
				+ "。请及时处理，谢谢";
	}

	private boolean selfCreateIssue(FourTeamsIssueNew issue) {
		return ThreadVariable.getSession().getOrganization().getId()
				.equals(issue.getOccurOrg().getId());
	}

	@SuppressWarnings("deprecation")
	private void sendPersonelMessageToOrganization(Long orgId, String message) {
		platformaMessageService
				.sendPlatformMessageToOrg(orgId, message, "平台提醒");
	}

	private String getTransferIssueMessage(FourTeamsIssueNew issue) {
		String orgName = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getSession().getOrganization().getId())
				.getOrgName();
		return "由" + orgName + "移交到你处一件事项，单号为:" + issue.getSerialNumber()
				+ "。请及时处理，谢谢";
	}

	private String getSuperviseMessage(FourTeamsIssueLogNew issueLog) {
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
			FourTeamsIssueOperate operate, Date applyDate) throws JSONException {
		double cent = issueAccessConfigService.getIssueScoresConfig(operate);
		regradedPointService.bonusPoints(org, RegradedType.ASSESSMENTUSER,
				reason, cent, applyDate);
	}

	private void deductSupervisePoint(FourTeamsIssueStep step,
			FourTeamsIssueOperate superviseType) throws JSONException {
		IssueRegradedReason regradedReason = new IssueRegradedReason();
		FourTeamsIssueNew issueNew = issueDao.getSimpleIssueById(step
				.getIssue().getId());
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

	private RegradedReason createRegistIssueRegradedReason(
			FourTeamsIssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		String organizationName = organizationDubboService.getSimpleOrgById(
				issue.getOccurOrg().getId()).getOrgName();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getUser().getName() + "于" + getNowDateStringValue()
				+ "新增一项事件处理到" + organizationName);
		return result;
	}

	private RegradedReason createIssueTransferRegradedReason(
			FourTeamsIssueNew issue) {
		IssueRegisteRegradedReason result = new IssueRegisteRegradedReason();
		String organizationName = organizationDubboService.getSimpleOrgById(
				issue.getOccurOrg().getId()).getOrgName();
		result.setIssueSerialNumber(issue.getSerialNumber());
		result.setContent(getUser().getName() + getNowDateStringValue()
				+ "移交一项事件处理到" + organizationName + "。服务单号为"
				+ issue.getSerialNumber());
		return result;
	}

	private RegradedReason createIssueDealRegradedReason(
			FourTeamsIssueNew issue, FourTeamsIssueLogNew log, Long dealType) {
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

	public FourTeamsIssueLogNew addIssueLog(FourTeamsIssueLogNew log) {
		return issueLogDao.addIssueLog(log);
	}

	public List<FourTeamsIssueOperate> getCurrentLoginedOrgCanDo(
			FourTeamsIssueNew issue, Organization org) {
		if (org == null) {
			org = ThreadVariable.getSession().getOrganization();
		}
		org = organizationDubboService.getFullOrgById(org.getId());
		FourTeamsIssueStep step = issueWorkFlowDao
				.findLastNotCompleteIssueStepByOrg(issue.getId(), org.getId());
		if (step == null) {
			return new ArrayList<FourTeamsIssueOperate>();
		}
		step.setTarget(organizationDubboService.getFullOrgById(step.getTarget()
				.getId()));
		return FourTeamsIssueOperateHelper.getCanDoOperationByOrg(step, org);
	}

	public FourTeamsIssueStep getIssueStepById(Long stepId) {
		return issueWorkFlowDao.getIssueStepById(stepId);
	}

	public FourTeamsIssueStep reportTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep step) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					getCommandCenter(), null);
			FourTeamsIssueStep reuslt = issueWorkFlowDao
					.addIssueStep(FourTeamsIssueOperateHelper
							.constructIssueStateFromStep(step).reportTo(issue,
									step, context));
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			sendPersonelMessageToOrganization(
					context.getTargetOrg().getId(),
					getTransferPersonelMessage(issue, context
							.getCurrentLoginedOrg().getOrgName()));
			addRatingRecord(getCurrentLoginedOrganization(),
					createIssueTransferRegradedReason(issue),
					FourTeamsIssueOperate.REPORT_TO, CalendarUtil.today());
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("上报指挥中心过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep giveTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew issueLog,
			List<Organization> tellOrgs, String[] attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					issueLog.getTargeOrg(), tellOrgs);
			FourTeamsIssueStepGroup steps = FourTeamsIssueOperateHelper
					.constructIssueStateFromStep(step).giveTo(issue, step,
							context);
			FourTeamsIssueStep reuslt = saveTransferResult(issue, context,
					steps, FourTeamsIssueOperate.GIVETO);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, issueLog, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("交办过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep concept(FourTeamsIssueNew issue,
			FourTeamsIssueStep step) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.concept(issue, step, context);
			step = issueWorkFlowDao.updateIssueStepExceptOrg(step);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("受理中发生错误", e);
		}
	}

	public FourTeamsIssueStep comment(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			String[] attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.comment(issue, step, context);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			saveIssueAttachFiles(issue, log, attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("继续办理过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep complete(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			String[] attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.complete(issue, step, context);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("办结过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep backUps(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.backUps(issue, step, context);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("备案过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep assign(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<Organization> tells, String[] attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					log.getTargeOrg(), tells);
			FourTeamsIssueStepGroup steps = FourTeamsIssueOperateHelper
					.constructIssueStateFromStep(step).assign(issue, step,
							context);
			FourTeamsIssueStep reuslt = saveTransferResult(issue, context,
					steps, FourTeamsIssueOperate.ASSIGN);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("交办过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep back(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			String[] attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					organizationDubboService.getFullOrgById(step.getBackTo()
							.getTarget().getId()), null);
			FourTeamsIssueStepGroup steps = FourTeamsIssueOperateHelper
					.constructIssueStateFromStep(step).back(issue, step,
							context);
			FourTeamsIssueStep reuslt = saveTransferResult(issue, context,
					steps, FourTeamsIssueOperate.BACK);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("回退过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep submit(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, FourTeamsIssueLogNew log,
			List<Organization> tells, String[] attachFiles) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					log.getTargeOrg(), tells);
			FourTeamsIssueSkiprule issueSkiprule = issueSkipruleService
					.getFourTeamsIssueSkipruleByIssue(issue);
			FourTeamsIssueStepGroup steps = FourTeamsIssueOperateHelper
					.constructIssueStateFromStep(step).submit(issue, step,
							context, issueSkiprule);
			FourTeamsIssueStep reuslt = saveTransferResult(issue, context,
					steps, FourTeamsIssueOperate.SUBMIT);
			issueWorkFlowDao.updateIssueStepExceptOrg(step);
			issue.setCurrentStep(reuslt);
			issueDao.updateIssueCurrentStepAndOrg(issue);
			saveIssueAttachFiles(issue, log, attachFiles);
			return reuslt;
		} catch (Exception e) {
			throw new ServiceValidationException("上报过程中发生错误", e);
		}
	}

	public FourTeamsIssueStep read(FourTeamsIssueNew issue,
			FourTeamsIssueStep step) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step).read(
					issue, step, context);
			step = issueWorkFlowDao.updateIssueStepExceptOrg(step);
			return step;
		} catch (Exception e) {
			throw new ServiceValidationException("阅读中发生错误", e);
		}
	}

	public FourTeamsIssueStep supervise(FourTeamsIssueNew issue,
			FourTeamsIssueStep step, int superviseType) {
		try {
			FourTeamsIssueOperationContext context = constructSuperviseContext(
					step.getTarget(),
					FourTeamsIssueOperate.parse(superviseType));
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.supervise(issue, step, context);
			deductSupervisePoint(step,
					FourTeamsIssueOperate.parse(superviseType));
			return issueWorkFlowDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("督办中发生错误", e);
		}
	}

	public FourTeamsIssueStep cancelSupervise(FourTeamsIssueNew issue,
			FourTeamsIssueStep step) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.cancelSupervise(issue, step, context);
			return issueWorkFlowDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("督办中发生错误", e);
		}
	}

	public FourTeamsIssueStep instruct(FourTeamsIssueNew issue,
			FourTeamsIssueStep step) {
		try {
			FourTeamsIssueOperationContext context = constructIssueOperationContext(
					step.getTarget(), null);
			FourTeamsIssueOperateHelper.constructIssueStateFromStep(step)
					.instruct(issue, step, context);
			return issueWorkFlowDao.updateIssueStepExceptOrg(step);
		} catch (Exception e) {
			throw new ServiceValidationException("督办中发生错误", e);
		}
	}

	public FourTeamsIssueNew cancelUrgent(FourTeamsIssueNew issue) {
		issueDao.updateIssueUrgentState(issue.getId(), Boolean.FALSE);
		return issue;
	}

	public FourTeamsIssueNew urgent(FourTeamsIssueNew issue) {
		issueDao.updateIssueUrgentState(issue.getId(), Boolean.TRUE);
		return issue;
	}

	public void historical(FourTeamsIssueNew issue) {
		Organization org = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		FourTeamsHistoricalIssue history = new FourTeamsHistoricalIssue();
		history.setIssueNew(issue);
		history.setOrganization(org);
		history.setOrgInternalCode(org.getOrgInternalCode());
		historicalIssueService.addHistoricalIssue(history);
	}

	public void cancelHistorical(FourTeamsIssueNew issue) {
		historicalIssueService.deleteHistoricalIssueByIssueIdAndOrgId(
				issue.getId(), ThreadVariable.getUser().getOrganization()
						.getId());
	}

	public void publiclty(FourTeamsIssueNew issue,
			FourTeamsIssueLogNew issueLogNew) {
		Organization org = organizationDubboService
				.getSimpleOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		FourTeamsPublicltyCass publicltyCass = new FourTeamsPublicltyCass();
		publicltyCass.setIssueNew(issue);
		publicltyCass.setOrganization(org);
		publicltyCass.setIssueLogNew(issueLogNew);
		// publicltyCass.setOrgInternalCode(org.getOrgInternalCode());
		publicltyCassService.addPublicltyCass(publicltyCass);
	}

	public void cancelPubliclty(FourTeamsIssueNew issue) {
		publicltyCassService.deletePublicltyCassByIssueIdAndOrgId(
				issue.getId(), ThreadVariable.getUser().getOrganization()
						.getId());
	}

	private FourTeamsIssueOperationContext constructSuperviseContext(
			Organization target, FourTeamsIssueOperate superviseType) {
		Organization org = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		FourTeamsIssueOperationContext context = new FourTeamsIssueOperationContext(
				org, target, "", ThreadVariable.getSession().getUserRealName());
		context.addParameter(
				FourTeamsIssueOperationContext.SUPERVISE_LEVEL_KEY,
				getSuperviseLevel(superviseType));
		return context;
	}

	private int getSuperviseLevel(FourTeamsIssueOperate superviseType) {
		if (FourTeamsIssueOperate.NORMAL_SUPERVISE.equals(superviseType)) {
			return FourTeamsIssueState.NORMAL_SUPERVISE;
		} else if (FourTeamsIssueOperate.YELLOW_SUPERVISE.equals(superviseType)) {
			return FourTeamsIssueState.YELLOW_CARD_SUPERVISE;
		} else if (FourTeamsIssueOperate.RED_SUPERVISE.equals(superviseType)) {
			return FourTeamsIssueState.RED_CARD_SUPERVISE;
		} else {
			return FourTeamsIssueState.NO_SUPERVISE;
		}
	}

	private FourTeamsIssueOperationContext constructIssueOperationContext(
			Organization target, List<Organization> tells) {
		Organization org = organizationDubboService
				.getFullOrgById(ThreadVariable.getSession().getOrganization()
						.getId());
		FourTeamsIssueOperationContext context = new FourTeamsIssueOperationContext(
				org, target, "", ThreadVariable.getSession().getUserRealName());
		if (tells != null) {
			for (Organization tellorg : tells) {
				context.addTellOrg(tellorg);
			}
		}
		return context;
	}

	private FourTeamsIssueStep saveTransferResult(FourTeamsIssueNew issue,
			FourTeamsIssueOperationContext context,
			FourTeamsIssueStepGroup group, FourTeamsIssueOperate operate)
			throws Exception {
		FourTeamsIssueStep result = issueWorkFlowDao.addIssueStep(group
				.getKeyStep());
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
			for (FourTeamsIssueStep ostep : group.getIncidentalSteps()) {
				issueWorkFlowDao.addIssueStep(ostep);
				sendPersonelMessageToOrganization(ostep.getTarget().getId(),
						message);
				addRatingRecord(ostep.getTarget(),
						createIssueTransferRegradedReason(issue), operate,
						CalendarUtil.today());
			}
		}
		return result;
	}

	public FourTeamsIssueStep getFullIssueStepById(Long stepId) {
		FourTeamsIssueStep step = getIssueStepById(stepId);
		step.setSource(organizationDubboService.getFullOrgById(step.getSource()
				.getId()));
		step.setTarget(organizationDubboService.getFullOrgById(step.getTarget()
				.getId()));
		if (step.getBackTo() != null) {
			step.setBackTo(getIssueStepById(step.getBackTo().getId()));
		}
		return step;
	}

	public List<Organization> findTellTargetsByName(
			FourTeamsIssueOperate issueOperate, Long stepId, String tag,
			String exceptOrgIds) {
		if (!StringUtil.isStringAvaliable(tag)) {
			return new ArrayList<Organization>();
		}
		FourTeamsIssueStep step = getIssueStepById(stepId);
		if (FourTeamsIssueOperate.GIVETO.equals(issueOperate)) {
			return findTellTargetsByName(tag, exceptOrgIds, step);
		} else if (FourTeamsIssueOperate.SUBMIT.equals(issueOperate)) {
			return findSubmitTellTargetsByName(tag, exceptOrgIds, step);
		} else if (FourTeamsIssueOperate.ASSIGN.equals(issueOperate)) {
			return findAssignTellTargetsByName(tag, exceptOrgIds, step);
		} else {
			return new ArrayList<Organization>();
		}
	}

	public List<Organization> findTransferTargetsByName(
			FourTeamsIssueOperate issueOperate, Long stepId, String tag,
			String exceptOrgIds, boolean searchAdmin) {
		if (!StringUtil.isStringAvaliable(tag)) {
			return new ArrayList<Organization>();
		}
		FourTeamsIssueStep step = getIssueStepById(stepId);
		if (FourTeamsIssueOperate.GIVETO.equals(issueOperate)) {
			return findGiveToTargetsByName(tag, exceptOrgIds, searchAdmin, step);
		} else if (FourTeamsIssueOperate.SUBMIT.equals(issueOperate)) {
			return findSubmitTargetsByName(tag, exceptOrgIds, searchAdmin, step);
		} else if (FourTeamsIssueOperate.ASSIGN.equals(issueOperate)) {
			return findAssignTargetsByName(tag, exceptOrgIds, searchAdmin, step);
		} else {
			return new ArrayList<Organization>();
		}
	}

	private List<Organization> findAssignTellTargetsByName(String tag,
			String exceptOrgIds, FourTeamsIssueStep step) {
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
			String exceptOrgIds, FourTeamsIssueStep step) {
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
			String exceptOrgIds, boolean searchAdmin, FourTeamsIssueStep step) {
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
			String exceptOrgIds, boolean searchAdmin, FourTeamsIssueStep step) {
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
			String exceptOrgIds, boolean searchAdmin, FourTeamsIssueStep step) {
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
			String exceptOrgIds, FourTeamsIssueStep step) {
		Organization org = organizationDubboService.getSimpleOrgById(step
				.getTarget().getId());
		// PropertyDict
		// dict=propertyDictService.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
		// searchAdmin?OrganizationType.ADMINISTRATIVE_REGION:OrganizationType.FUNCTIONAL_ORG).get(0);
		return issueWorkFlowDao.findChildOrgsByOrgcodeAndName(null,
				org.getOrgInternalCode(), tag, exceptOrgIds, 10);
	}

	private String getTellPersonelMessage(FourTeamsIssueNew issue,
			String sourceName, String targetName) {
		return "由" + sourceName + "移交到" + targetName + "一件服务事项,需要你处协助处理，单号为:"
				+ issue.getSerialNumber() + "。请及时处理，谢谢";
	}

	private String getTransferPersonelMessage(FourTeamsIssueNew issue,
			String sourceName) {
		return "由" + sourceName + "移交到你处一件服务事项,单号为:" + issue.getSerialNumber()
				+ "。请及时处理，谢谢";
	}

	private void saveIssueAttachFiles(FourTeamsIssueNew issue,
			FourTeamsIssueLogNew log, String[] files) throws Exception {
		if (files == null || files.length == 0)
			return;
		for (String fileName : files) {
			StoredFile file = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.ISSUE_ATTACHFILE);
			String fileActualPath = file.getStoredFilePath() + "/"
					+ file.getStoredFileName();
			FourTeamsIssueAttachFile attachFile = new FourTeamsIssueAttachFile();
			attachFile.setFileActualUrl(fileActualPath);
			attachFile.setFileName(fileName);
			attachFile.setIssue(issue);
			attachFile.setIssueLog(log);
			issueAttachFileDao.addIssueAttachFile(attachFile);
		}

	}

}
