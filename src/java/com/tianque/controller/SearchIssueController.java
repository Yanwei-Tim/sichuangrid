package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchIssueDao;
import com.tianque.domain.IssueType;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.Organization;
import com.tianque.domain.property.IssuePersonAndSiteType;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.SearchBaseInfoPersonnel;
import com.tianque.domain.vo.SearchBaseInfoPlace;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.controller.IssueOvertimeHelper;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.service.CommonPeopleService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.KeyPlaceService;
import com.tianque.solr.domain.CommonPopulationDocument;
import com.tianque.solr.domain.KeyPlaceDocument;
import com.tianque.solr.domain.KeyPopulationDocument;
import com.tianque.solr.service.SolrKeyPlaceService;
import com.tianque.solr.service.SolrKeyPopulationService;
import com.tianque.state.IssueQueryState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("searchIssueController")
@Scope("prototype")
public class SearchIssueController extends BaseAction {
	@Autowired
	private SearchIssueDao searchIssueDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private SolrKeyPlaceService solrKeyPlaceService;
	@Autowired
	private SolrKeyPopulationService solrKeyPopulationService;
	@Autowired
	private CommonPeopleService commonPopulationService;

	private List<AutoCompleteData> autoCompletePersonnelDatas = new ArrayList<AutoCompleteData>();
	private List<AutoCompleteData> autoCompletePlaceDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Long orgId;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private KeyPlaceService placeService;
	private SearchIssueVoNew searchIssueVo;
	private List<IssueType> contradiction;
	private List<IssueType> specialisation;
	private List<IssueType> peopleliveService;
	private List<IssueType> selContradiction;
	private List<IssueType> selSpecialisation;
	private List<IssueType> selPeopleliveService;
	private List<IssueType> itemIssueService;
	private String selContradictionId;
	private String selSpecialisationId;
	private String selPeopleliveServiceId;
	private String selOtherTypeId;
	private String selItemIssueServiceId;

	private String selResolveTheContradictionsId;
	private String selSecurityPrecautionsId;
	private String selSpecialPopulationsId;
	private String selSocialConditionsId;
	private String selPoliciesAndLawsId;
	private String selEmergenciesId;
	/* 事件类型查询改版成单个事件查询 */
	private IssueType selectedType;

	@Autowired
	private IssueOvertimeHelper issueOvertimeHelper;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@PermissionFilter(ename = "searchIssue")
	public String searchIssue() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchIssuesNew(searchIssueVo, page, rows, sidx,
						sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		List<IssueViewObject> list = gridPage.getRows();
		for (IssueViewObject issueViewObject : list) {
			if (issueViewObject != null
					&& issueViewObject.getOccurOrg() != null) {
				issueViewObject.setTargeOrg(issueViewObject.getOccurOrg());
				issueOvertimeHelper.fillEaringWarnField(issueViewObject);
			}
		}
		return SUCCESS;
	}

	public String searchHistoricalIssue() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchHistoricalIssueNew(searchIssueVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		return SUCCESS;
	}

	public String searchPublicltyCassIssue() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchPublicltyCassIssueNew(searchIssueVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		return SUCCESS;
	}

	public String searchDoneIssues() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		List<Long> dealStateList = new ArrayList<Long>();
		dealStateList.add(IssueQueryState.MY_DONE_ONE);
		dealStateList.add(IssueQueryState.MY_DONE_TWO);
		dealStateList.add(IssueQueryState.MY_DONE_THREE);
		searchIssueVo.setDealStateList(dealStateList);
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchDoneIssues(searchIssueVo, page, rows,
						sidx, sord), organizationDubboService, new String[] {
						"occurOrg", "lastOrg", "targeOrg", "currentOrg" },
				orgId));
		return SUCCESS;
	}

	// 下辖
	public String searchJurisdictions() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setDealState(Long.valueOf(IssueState.STEPCOMPLETE_CODE));
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchJurisdictionsIssues(searchIssueVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		return SUCCESS;
	}

	/***
	 * 查询我的事项-已办结事项
	 * 
	 * @return
	 */
	public String searchMyCompletedIssues() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setDealState(Long.parseLong(IssueState.ISSUECOMPLETE_CODE
				+ ""));
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchMyCompletedIssues(searchIssueVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		return SUCCESS;
	}

	// 已办
	public String searchJurisdictionsDoneIssues() throws Exception {
		if (null == searchIssueVo || searchIssueVo.getTargeOrgId() == null) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		Organization org = organizationDubboService.getSimpleOrgById(searchIssueVo
				.getTargeOrgId());
		searchIssueVo.setTargeOrgInternalCode(org.getOrgInternalCode());
		searchIssueVo.setDealState(Long.parseLong(IssueState.ISSUECOMPLETE_CODE
				+ ""));
		// preparePageData();
		// transToIssueType();
		fillIssueTypes();
		if (searchIssueVo.getEndTimeEnd() != null) {
			searchIssueVo.setEndTimeEnd(DateUtil.getEndTime(searchIssueVo
					.getEndTimeEnd()));
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchIssueDao.searchJurisdictionsDoneIssues(searchIssueVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "occurOrg" }, orgId));
		return SUCCESS;
	}

	private void fillIssueTypes() {
		if (null != selectedType && null != selectedType.getId()) {
			List<IssueType> selectedTypes = new ArrayList<IssueType>();
			selectedTypes.add(selectedType);
			searchIssueVo.setIssueTypes(selectedTypes);
		}
	}

	private void preparePageData() {
		contradiction = issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION);
		specialisation = issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE);
		peopleliveService = issueTypeService
				.findIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE);
		itemIssueService = issueTypeService
				.findIssueTypesByParentName(IssueTypes.ITEM);
	}

	/**
	 * 将页面提交的数据List<Long>类型转换成List<IssueType>类型. 并且将已选择的选项对应到一个完成的IssueType.
	 * 
	 * @return
	 */
	private void transToIssueType() {
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		List<Long> selContradictionIdList = parseSelToLong(selContradictionId);
		List<Long> selSpecialisationIdList = parseSelToLong(selSpecialisationId);
		List<Long> selPeopleliveServiceIdList = parseSelToLong(selPeopleliveServiceId);
		List<Long> selOtherTypeIdList = parseSelToLong(selOtherTypeId);
		List<Long> selResolveTheContradictionsIdList = parseSelToLong(selResolveTheContradictionsId);
		List<Long> selSecurityPrecautionsIdIdList = parseSelToLong(selSecurityPrecautionsId);
		List<Long> selSpecialPopulationsIdList = parseSelToLong(selSpecialPopulationsId);
		List<Long> selSocialConditionsIdList = parseSelToLong(selSocialConditionsId);
		List<Long> selPoliciesAndLawsIdList = parseSelToLong(selPoliciesAndLawsId);
		List<Long> selEmergenciesIdList = parseSelToLong(selEmergenciesId);
		if (selContradictionIdList != null && selContradictionIdList.size() > 0)
			for (Long issueTypeId : selContradictionIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selSpecialisationIdList != null
				&& selSpecialisationIdList.size() > 0)
			for (Long issueTypeId : selSpecialisationIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selPeopleliveServiceIdList != null
				&& selPeopleliveServiceIdList.size() > 0)
			for (Long issueTypeId : selPeopleliveServiceIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selOtherTypeIdList != null && selOtherTypeIdList.size() > 0)
			for (Long issueTypeId : selOtherTypeIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		if (StringUtil.isStringAvaliable(selItemIssueServiceId)) {
			issueTypes.add(createIssueTypeById(Long
					.valueOf(selItemIssueServiceId)));
		}

		if (selResolveTheContradictionsIdList != null
				&& selResolveTheContradictionsIdList.size() > 0)
			for (Long issueTypeId : selResolveTheContradictionsIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		if (selSecurityPrecautionsIdIdList != null
				&& selSecurityPrecautionsIdIdList.size() > 0)
			for (Long issueTypeId : selSecurityPrecautionsIdIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		if (selSpecialPopulationsIdList != null
				&& selSpecialPopulationsIdList.size() > 0)
			for (Long issueTypeId : selSpecialPopulationsIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		if (selSocialConditionsIdList != null
				&& selSocialConditionsIdList.size() > 0)
			for (Long issueTypeId : selSocialConditionsIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selPoliciesAndLawsIdList != null
				&& selPoliciesAndLawsIdList.size() > 0)
			for (Long issueTypeId : selPoliciesAndLawsIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		if (selEmergenciesIdList != null && selEmergenciesIdList.size() > 0)
			for (Long issueTypeId : selEmergenciesIdList) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		searchIssueVo.setIssueTypes(issueTypes);
	}

	private List<Long> parseSelToLong(String values) {
		List<Long> result = new ArrayList<Long>();
		if (values != null && StringUtil.isStringAvaliable(values)) {
			String[] objects = values.split(",");
			for (int i = 0; i < objects.length; i++) {
				result.add(Long.valueOf(objects[i].trim()));
			}
		}
		return result;
	}

	private IssueType createIssueTypeById(Long id) {
		IssueType issueType = new IssueType();
		issueType.setId(id);
		return issueType;
	}

	private PageInfo<IssueNew> emptyIssuePage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	public String searchPersonnelForAutoComplete() throws Exception {
		boolean boolSolr = false;
		if (tag == null || tag.trim().equals("")) {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				tag = "*:";
			} else {
				tag = "";
			}
		}
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		if (organizationDubboService.isTownOrganization(orgId)) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		} else {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				if (tag == null || tag.trim().equals("")) {
					boolSolr = true;
					tag = "*:";
				}
			} else {
				if (tag == null || tag.trim().equals("")) {
					boolSolr = true;
					tag = "";
				}
			}
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		String isSenderSolrMsg = (String) globalSettingService
				.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
		if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
			boolSolr = true;
		}
		if (boolSolr) {
			List<KeyPopulationDocument> list = solrKeyPopulationService
					.findKeyPopulationDocumentListByTag(tag,
							organization.getOrgInternalCode());
			for (int i = 0; i < list.size(); i++) {
				KeyPopulationDocument keyPopulationDocument = list.get(i);
				for (int j = 0; j < keyPopulationDocument.getIds().length; j++) {
					AutoCompleteData autoCompleteData = new AutoCompleteData();
					autoCompleteData.setLabel(keyPopulationDocument.getName());
					autoCompleteData
							.setDesc(keyPopulationDocument.getIdCardNo()
									+ (keyPopulationDocument.getAddresses() == null ? ""
											: keyPopulationDocument
													.getAddresses()[j])
									+ IssuePersonAndSiteType
											.toString(keyPopulationDocument
													.getTypes()[j].toString()));
					autoCompleteData
							.setValue(keyPopulationDocument.getTypes()[j] + "-"
									+ keyPopulationDocument.getIds()[j]);
					autoCompletePersonnelDatas.add(autoCompleteData);
				}
			}
		} else {
			List<People> list = commonPopulationService
					.searchNameInCommonPopulation(tag,
							organization.getOrgInternalCode());
			for (int i = 0; i < list.size(); i++) {
				People commonPopulation = list.get(i);
				AutoCompleteData autoCompleteData = new AutoCompleteData();
				autoCompleteData.setLabel(commonPopulation.getName());
				autoCompleteData.setDesc(propertyDictService
						.getPropertyDictById(
								commonPopulation.getGender().getId())
						.getDisplayName()
						+ commonPopulation.getIdCardNo()
						+ IssuePersonAndSiteType.toString(commonPopulation
								.getAttentionPopulationType()));
				autoCompleteData.setValue(commonPopulation
						.getAttentionPopulationType()
						+ "-"
						+ commonPopulation.getId());
				autoCompletePersonnelDatas.add(autoCompleteData);
			}
		}
		return SUCCESS;
	}

	public String searchPlaceForAutoComplete() throws Exception {
		boolean boolSolr = false;
		if (tag == null || tag.trim().equals("")) {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				tag = "*:";
			} else {
				tag = "";
			}
		}
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		if (organizationDubboService.isTownOrganization(orgId)) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		} else {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				if (tag == null || tag.trim().equals("")) {
					boolSolr = true;
					tag = "*:";
				}
			} else {
				if (tag == null || tag.trim().equals("")) {
					boolSolr = true;
					tag = "";
				}
			}
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		String isSenderSolrMsg = (String) globalSettingService
				.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
		if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
			boolSolr = true;
		}
		if (boolSolr) {
			List<KeyPlaceDocument> list = solrKeyPlaceService
					.findKeyPlaceDocumentListByTag(tag,
							organization.getOrgInternalCode());
			for (int i = 0; i < list.size(); i++) {
				KeyPlaceDocument keyPlaceDocument = list.get(i);
				AutoCompleteData autoCompleteData = new AutoCompleteData();
				autoCompleteData.setLabel(keyPlaceDocument.getName());
				autoCompleteData.setDesc(keyPlaceDocument.getAddress());
				autoCompleteData.setValue(keyPlaceDocument.getKey());
				autoCompletePlaceDatas.add(autoCompleteData);
			}
		} else {
			List<KeyPlace> list = placeService.searchKeyPlaceByName(tag,
					organization.getOrgInternalCode());
			for (int i = 0; i < list.size(); i++) {
				KeyPlace keyPlace = list.get(i);
				AutoCompleteData autoCompleteData = new AutoCompleteData();
				autoCompleteData.setLabel(keyPlace.getName());
				autoCompleteData.setDesc(keyPlace.getAddress());
				autoCompleteData.setValue(keyPlace.getType() + "-"
						+ keyPlace.getId());
				autoCompletePlaceDatas.add(autoCompleteData);
			}
		}
		return SUCCESS;
	}

	public String findPersonnelList() throws Exception {
		boolean boolSolr = false;
		if (tag == null || tag.trim().equals("")) {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				tag = "*:";
			} else {
				tag = "";
			}
		}
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		if (organizationDubboService.isTownOrganization(orgId)) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		} else {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				if (tag == null || "".equals(tag)) {
					tag = "*:";
				}
			} else {
				if (tag == null || "".equals(tag)) {
					tag = "";
				}
			}
		}
		String isSenderSolrMsg = (String) globalSettingService
				.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
		if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
			boolSolr = true;
		}
		// 修改为当前用户的的下辖和本级 (职能部门则选择使用其父节点的组织机构)
		// Organization organization = organizationDubboService
		// .getSimpleOrgById(ThreadVariable.getUser().getOrganization()
		// .getId());
		Organization organization = organizationDubboService
				.getFullOrgById(ThreadVariable.getUser().getOrganization()
						.getId());
		if (organization
				.getOrgType()
				.getDisplayName()
				.equals(OrganizationType.getInternalProperties()
						.get(OrganizationType.FUNCTIONAL_ORG).getDisplayName())) {
			organization = organizationDubboService.getSimpleOrgById(organization
					.getParentOrg().getId());
		}
		PageInfo<SearchBaseInfoPersonnel> pageInfo = new PageInfo<SearchBaseInfoPersonnel>();
		List<SearchBaseInfoPersonnel> lists = new ArrayList<SearchBaseInfoPersonnel>();
		if (boolSolr) {
			PageInfo<CommonPopulationDocument> commonPopulationDocuments = solrKeyPopulationService
					.findKeyPopulationDocumentPageByTag(tag,
							organization.getOrgInternalCode(), page, rows);
			if (commonPopulationDocuments != null) {
				for (int i = 0; i < commonPopulationDocuments.getResult()
						.size(); i++) {
					CommonPopulationDocument commonPopulationDocument = commonPopulationDocuments
							.getResult().get(i);
					SearchBaseInfoPersonnel searchBaseInfoPersonnel = new SearchBaseInfoPersonnel();
					searchBaseInfoPersonnel.setId(commonPopulationDocument
							.getKey());
					searchBaseInfoPersonnel.setName(commonPopulationDocument
							.getName());
					searchBaseInfoPersonnel
							.setIdCardNo(commonPopulationDocument.getIdCardNo());
					searchBaseInfoPersonnel.setSex("暂无");
					searchBaseInfoPersonnel
							.setPersonnelType(getPersonnelTypeById(searchBaseInfoPersonnel
									.getId()));
					lists.add(searchBaseInfoPersonnel);
				}
				pageInfo.setResult(lists);
				pageInfo.setTotalRowSize(commonPopulationDocuments
						.getTotalRowSize());
			}
		} else {
			PageInfo<People> commonPopulations = commonPopulationService
					.searchNamePageInCommonPopulation(tag,
							organization.getOrgInternalCode(), page, rows);
			if (commonPopulations != null) {
				for (int i = 0; i < commonPopulations.getResult().size(); i++) {
					People commonPopulation = commonPopulations.getResult()
							.get(i);
					SearchBaseInfoPersonnel searchBaseInfoPersonnel = new SearchBaseInfoPersonnel();
					searchBaseInfoPersonnel.setId(commonPopulation
							.getAttentionPopulationType()
							+ "-"
							+ commonPopulation.getId());
					searchBaseInfoPersonnel.setName(commonPopulation.getName());
					searchBaseInfoPersonnel.setIdCardNo(commonPopulation
							.getIdCardNo());
					searchBaseInfoPersonnel.setSex(propertyDictService
							.getPropertyDictById(
									commonPopulation.getGender().getId())
							.getDisplayName());
					searchBaseInfoPersonnel
							.setPersonnelType(IssuePersonAndSiteType
									.toString(commonPopulation
											.getAttentionPopulationType()));
					lists.add(searchBaseInfoPersonnel);
				}
				pageInfo.setResult(lists);
				pageInfo.setTotalRowSize(commonPopulations.getTotalRowSize());
			}

		}
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private String getPersonnelTypeById(String id) {
		String type = id.substring(0, id.indexOf("-"));

		if ("DRUGGY".equals(type)) {
			return "吸毒人员";
		} else if ("SUPERIOR_VISIT".equals(type)) {
			return "重点上访人员";
		} else if ("RECTIFICATIVEPERSON".equals(type)) {
			return "社区矫正人员";
		} else if ("POORPEOPLE".equals(type)) {
			return "需救助人员";
		} else if ("IDLE_YOUTH".equals(type)) {
			return "重点青少年";
		} else if ("POSITIVE_INFO".equals(type)) {
			return "刑释人员";
		} else if ("MENTAL_PATIENT".equals(type)) {
			return "严重精神障碍患者";
		} else if ("ATTENTION_OBJECT".equals(type)) {
			return "其他人员";
		} else if ("DANGEROUS_GOODS_PRACTITIONER".equals(type)) {
			return "危险品从业人员";
		} else {
			return "";
		}
	}

	public String findPlaceList() throws Exception {
		boolean boolSolr = false;
		if (tag == null || tag.trim().equals("")) {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				tag = "*:";
			} else {
				tag = "";
			}
		}
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		if (organizationDubboService.isTownOrganization(orgId)) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		} else {
			String isSenderSolrMsg = (String) globalSettingService
					.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
			if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
				boolSolr = true;
				if (tag == null || "".equals(tag)) {
					tag = "*:";
				}
			} else {
				if (tag == null || "".equals(tag)) {
					tag = "";
				}
			}
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		PageInfo<SearchBaseInfoPlace> pageInfo = new PageInfo<SearchBaseInfoPlace>();
		List<SearchBaseInfoPlace> lists = new ArrayList<SearchBaseInfoPlace>();
		String isSenderSolrMsg = (String) globalSettingService
				.getGlobalValue(GlobalSetting.IS_SENDER_SOLR_MSG);
		if (isSenderSolrMsg != null && Boolean.valueOf(isSenderSolrMsg)) {
			boolSolr = true;
		}
		if (boolSolr) {
			PageInfo<KeyPlaceDocument> keyPlaceDocuments = solrKeyPlaceService
					.findKeyPlaceDocumentPageByTag(tag,
							organization.getOrgInternalCode(), page, rows);
			if (keyPlaceDocuments != null) {
				for (int i = 0; i < keyPlaceDocuments.getResult().size(); i++) {
					KeyPlaceDocument keyPlaceDocument = keyPlaceDocuments
							.getResult().get(i);
					SearchBaseInfoPlace searchBaseInfoPlace = new SearchBaseInfoPlace();
					searchBaseInfoPlace.setId(keyPlaceDocument.getKey());
					searchBaseInfoPlace.setName(keyPlaceDocument.getName());
					searchBaseInfoPlace.setOrgName(organizationDubboService
							.getFullOrgById(keyPlaceDocument.getOrgId())
							.getOrgName());
					searchBaseInfoPlace.setAddress(keyPlaceDocument
							.getAddress());
					searchBaseInfoPlace.setTypeName(IssuePersonAndSiteType
							.toString(keyPlaceDocument.getType()));
					lists.add(searchBaseInfoPlace);
				}
				pageInfo.setResult(lists);
				pageInfo.setTotalRowSize(keyPlaceDocuments.getTotalRowSize());
			}
		} else {
			PageInfo<KeyPlace> keyPlaces = placeService
					.searchKeyPlacePageByName(tag,
							organization.getOrgInternalCode(), page, rows);
			if (keyPlaces != null) {
				for (int i = 0; i < keyPlaces.getResult().size(); i++) {
					KeyPlace keyPlace = keyPlaces.getResult().get(i);
					SearchBaseInfoPlace searchBaseInfoPlace = new SearchBaseInfoPlace();
					searchBaseInfoPlace.setId(keyPlace.getType() + "-"
							+ keyPlace.getId());
					searchBaseInfoPlace.setName(keyPlace.getName());
					searchBaseInfoPlace.setOrgName(organizationDubboService
							.getFullOrgById(keyPlace.getOrgId()).getOrgName());
					searchBaseInfoPlace.setAddress(keyPlace.getAddress());
					searchBaseInfoPlace.setTypeName(IssuePersonAndSiteType
							.toString(keyPlace.getType()));
					lists.add(searchBaseInfoPlace);
				}
			}
			pageInfo.setResult(lists);
			pageInfo.setTotalRowSize(keyPlaces.getTotalRowSize());
		}
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public SearchIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
	}

	public List<IssueType> getContradiction() {
		return contradiction;
	}

	public void setContradiction(List<IssueType> contradiction) {
		this.contradiction = contradiction;
	}

	public List<IssueType> getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(List<IssueType> specialisation) {
		this.specialisation = specialisation;
	}

	public List<IssueType> getPeopleliveService() {
		return peopleliveService;
	}

	public void setPeopleliveService(List<IssueType> peopleliveService) {
		this.peopleliveService = peopleliveService;
	}

	public List<IssueType> getSelContradiction() {
		return selContradiction;
	}

	public void setSelContradiction(List<IssueType> selContradiction) {
		this.selContradiction = selContradiction;
	}

	public List<IssueType> getSelSpecialisation() {
		return selSpecialisation;
	}

	public void setSelSpecialisation(List<IssueType> selSpecialisation) {
		this.selSpecialisation = selSpecialisation;
	}

	public List<IssueType> getSelPeopleliveService() {
		return selPeopleliveService;
	}

	public void setSelPeopleliveService(List<IssueType> selPeopleliveService) {
		this.selPeopleliveService = selPeopleliveService;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<AutoCompleteData> getAutoCompletePersonnelDatas() {
		return autoCompletePersonnelDatas;
	}

	public void setAutoCompletePersonnelDatas(
			List<AutoCompleteData> autoCompletePersonnelDatas) {
		this.autoCompletePersonnelDatas = autoCompletePersonnelDatas;
	}

	public List<AutoCompleteData> getAutoCompletePlaceDatas() {
		return autoCompletePlaceDatas;
	}

	public void setAutoCompletePlaceDatas(
			List<AutoCompleteData> autoCompletePlaceDatas) {
		this.autoCompletePlaceDatas = autoCompletePlaceDatas;
	}

	public SolrKeyPopulationService getSolrKeyPopulationService() {
		return solrKeyPopulationService;
	}

	public void setSolrKeyPopulationService(
			SolrKeyPopulationService solrKeyPopulationService) {
		this.solrKeyPopulationService = solrKeyPopulationService;
	}

	public void setSelContradictionId(String selContradictionId) {
		this.selContradictionId = selContradictionId;
	}

	public String getSelContradictionId() {
		return selContradictionId;
	}

	public void setSelSpecialisationId(String selSpecialisationId) {
		this.selSpecialisationId = selSpecialisationId;
	}

	public String getSelSpecialisationId() {
		return selSpecialisationId;
	}

	public void setSelPeopleliveServiceId(String selPeopleliveServiceId) {
		this.selPeopleliveServiceId = selPeopleliveServiceId;
	}

	public String getSelPeopleliveServiceId() {
		return selPeopleliveServiceId;
	}

	public void setSelOtherTypeId(String selOtherTypeId) {
		this.selOtherTypeId = selOtherTypeId;
	}

	public String getSelOtherTypeId() {
		return selOtherTypeId;
	}

	public String getSelItemIssueServiceId() {
		return selItemIssueServiceId;
	}

	public void setSelItemIssueServiceId(String selItemIssueServiceId) {
		this.selItemIssueServiceId = selItemIssueServiceId;
	}

	public List<IssueType> getItemIssueService() {
		return itemIssueService;
	}

	public void setItemIssueService(List<IssueType> itemIssueService) {
		this.itemIssueService = itemIssueService;
	}

	public void setSelResolveTheContradictionsId(
			String selResolveTheContradictionsId) {
		this.selResolveTheContradictionsId = selResolveTheContradictionsId;
	}

	public String getSelResolveTheContradictionsId() {
		return selResolveTheContradictionsId;
	}

	public void setSelSecurityPrecautionsId(String selSecurityPrecautionsId) {
		this.selSecurityPrecautionsId = selSecurityPrecautionsId;
	}

	public String getSelSecurityPrecautionsId() {
		return selSecurityPrecautionsId;
	}

	public void setSelSpecialPopulationsId(String selSpecialPopulationsId) {
		this.selSpecialPopulationsId = selSpecialPopulationsId;
	}

	public String getSelSpecialPopulationsId() {
		return selSpecialPopulationsId;
	}

	public void setSelSocialConditionsId(String selSocialConditionsId) {
		this.selSocialConditionsId = selSocialConditionsId;
	}

	public String getSelSocialConditionsId() {
		return selSocialConditionsId;
	}

	public void setSelEmergenciesId(String selEmergenciesId) {
		this.selEmergenciesId = selEmergenciesId;
	}

	public String getSelEmergenciesId() {
		return selEmergenciesId;
	}

	public void setSelPoliciesAndLawsId(String selPoliciesAndLawsId) {
		this.selPoliciesAndLawsId = selPoliciesAndLawsId;
	}

	public String getSelPoliciesAndLawsId() {
		return selPoliciesAndLawsId;
	}

	public IssueType getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(IssueType selectedType) {
		this.selectedType = selectedType;
	}

}
