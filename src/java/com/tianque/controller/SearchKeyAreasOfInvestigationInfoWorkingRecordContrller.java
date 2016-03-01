package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.dao.SearchKeyAreasOfInvestigationInfoWorkingRecordDao;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.service.DailyDirectoryService;

@Controller("searchKeyAreasOfInvestigationInfoWorkingRecordContrller")
@Scope("prototype")
@SuppressWarnings("serial")
@Transactional
public class SearchKeyAreasOfInvestigationInfoWorkingRecordContrller extends BaseAction {
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Organization organization;
	private Long orgId;
	@Autowired
	private SearchKeyAreasOfInvestigationInfoWorkingRecordDao searchKeyAreasOfInvestigationInfoWorkingRecordDao;

	private KeyAreasOfInvestigationInfoVo searchKeyAreasOfInvestigationInfoWorkingRecord;

	public String searchKeyAreasOfInvestigationInfoWorkingRecords() {
		String allDailyDirectoryId = "";

		if (searchKeyAreasOfInvestigationInfoWorkingRecord == null
				|| searchKeyAreasOfInvestigationInfoWorkingRecord.getDailyDirectory().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (orgId == null) {
			orgId = organization.getId();
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		searchKeyAreasOfInvestigationInfoWorkingRecord.setOrganization(organization);
		if(organization!=null && StringUtil.isStringAvaliable(organization.getOrgName())){
			OrganizationVo orgVo = new OrganizationVo();
			orgVo.setOrgName(organization.getOrgName());
			List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(orgVo);
			if(orgIdList!=null && orgIdList.size()>0){
				searchKeyAreasOfInvestigationInfoWorkingRecord.setOrgIdsList(ParametersConvertUtil.convertToListString(orgIdList));
			}else{
				searchKeyAreasOfInvestigationInfoWorkingRecord.setOrgIdsList(ParametersConvertUtil.nullStringList());
			}
		}
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(searchKeyAreasOfInvestigationInfoWorkingRecord
						.getDailyDirectory().getId());
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'"
					+ searchKeyAreasOfInvestigationInfoWorkingRecord.getDailyDirectory().getId()
					+ "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId() + "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchKeyAreasOfInvestigationInfoWorkingRecordDao
						.searchKeyAreasOfInvestigationInfoWorkingRecord(
								searchKeyAreasOfInvestigationInfoWorkingRecord, page, rows, sidx,
								sord, allDailyDirectoryId), organizationDubboService,
				new String[] { "investigationOrg" }, orgId));
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public KeyAreasOfInvestigationInfoVo getSearchKeyAreasOfInvestigationInfoWorkingRecord() {
		return searchKeyAreasOfInvestigationInfoWorkingRecord;
	}

	public void setSearchKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoVo searchKeyAreasOfInvestigationInfoWorkingRecord) {
		this.searchKeyAreasOfInvestigationInfoWorkingRecord = searchKeyAreasOfInvestigationInfoWorkingRecord;
	}

}
