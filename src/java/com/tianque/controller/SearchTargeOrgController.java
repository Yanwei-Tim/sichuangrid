package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.state.TargeOrgManager;

@Controller("searchTargeOrgController")
@Scope("prototype")
@Transactional(readOnly = true)
public class SearchTargeOrgController extends BaseAction {
	@Autowired
	private TargeOrgManager targeOrgManager;

	private String tag;
	private Long issueId;
	private Long issueDealType;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private Organization targeOrg;
	private String orgIds;

	public String searchTargeOrgForAutoComplete() {
		if (tag == null || tag.trim().equals("")) {
			autoCompleteDatas.add(null);
			return SUCCESS;
		}
		validateInputDealType();
		List<Organization> list = targeOrgManager.getTargeOrg(this.issueDealType, tag);
		if (list == null || list.size() == 0) {
			appendErrorMessage("参数不正确");
			return ERROR;
		}
		for (Organization org : list) {
			if (orgIds != null && !orgIds.trim().equals("")) {
				String[] orgs = orgIds.split(",");
				boolean equalsbol = true;
				for (int i = 0; i < orgs.length; i++) {
					if (org.getId().toString().equals(orgs[i])) {
						equalsbol = false;
					}
				}
				if (equalsbol) {
					AutoCompleteData autoCompleteData = new AutoCompleteData();
					autoCompleteData.setValue(org.getId().toString());
					autoCompleteData.setLabel(org.getOrgName());
					autoCompleteData.setDesc(org.getRemark());
					autoCompleteDatas.add(autoCompleteData);
				}
			} else {
				AutoCompleteData autoCompleteData = new AutoCompleteData();
				autoCompleteData.setValue(org.getId().toString());
				autoCompleteData.setLabel(org.getOrgName());
				autoCompleteData.setDesc(org.getRemark());
				autoCompleteDatas.add(autoCompleteData);
			}
		}
		return SUCCESS;
	}

	public String selectTargeOrg() {
		return SUCCESS;
	}

	public String searchTargeOrgsForSelector() {
		validateInputDealType();
		List<Organization> list = targeOrgManager.getTargeOrg(this.issueDealType, tag);
		List<Organization> newList = new ArrayList<Organization>();
		for (Organization org : list) {
			if (orgIds != null && !orgIds.trim().equals("")) {
				String[] orgs = orgIds.split(",");
				boolean equalsbol = true;
				for (int i = 0; i < orgs.length; i++) {
					if (org.getId().toString().equals(orgs[i])) {
						equalsbol = false;
					}
				}
				if (equalsbol)
					newList.add(org);
			} else {
				newList = list;
			}
		}
		this.gridPage = new GridPage(newList);
		return SUCCESS;
	}

	public String searchForwardTargeOrg() {
		validateInputDealType();
		targeOrg = targeOrgManager.getSingleForwardOrg(issueDealType);
		return SUCCESS;
	}

	public String searchSendBackTargeOrg() {
		validateInputDealTypeAndIssueId();
		targeOrg = targeOrgManager.getSendbackTargeOrg(null, issueId);
		return SUCCESS;
	}

	private String validateInputDealType() {
		if (getIssueDealType() == null || getIssueDealType().intValue() == 0) {
			appendErrorMessage("参数不正确");
		}
		return ERROR;
	}

	private String validateInputDealTypeAndIssueId() {
		if (getIssueDealType() == null || getIssueDealType().intValue() == 0
				|| getIssueId() == null || getIssueId().intValue() == 0) {
			appendErrorMessage("参数不正确");
		}
		return ERROR;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getIssueDealType() {
		return issueDealType;
	}

	public void setIssueDealType(Long issueDealType) {
		this.issueDealType = issueDealType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public Organization getTargeOrg() {
		return targeOrg;
	}

	public void setTargeOrg(Organization targeOrg) {
		this.targeOrg = targeOrg;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
}
