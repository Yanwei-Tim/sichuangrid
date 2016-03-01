package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.HelpPersonnel;
import com.tianque.service.HelpPersonnelService;

@Controller("guardianToHandicappedController")
@SuppressWarnings("serial")
@Scope("prototype")
public class GuardianToHandicappedController extends BaseAction {
	@Autowired
	private HelpPersonnelService helpPersonnelService;

	private Long personnelId;
	private String personnelType;
	private HelpPersonnel helpPersonnel;
	private List<HelpPersonnel> helpPersonnelList;
	private String tag;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String personType;

	public String findHelpPersonnelForListPage() throws Exception {
		if (!validatePaceTypeAndId()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		gridPage = new GridPage(helpPersonnelService.findHelpPersonnel(
				personnelId, page, rows, sidx, sord, personnelType));
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			helpPersonnel = new HelpPersonnel();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			helpPersonnel = helpPersonnelService.getHelpPersonnel(helpPersonnel
					.getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			helpPersonnel = helpPersonnelService.getHelpPersonnel(helpPersonnel
					.getId());
		}
		return personType;
	}

	public String getHelpPersonnelByIdAndPlace() throws Exception {
		// if(!this.validatePaceTypeAndId()){
		// this.errorMessage = "参数错误";
		// return ERROR;
		// }
		helpPersonnelList = helpPersonnelService.findHelpPersonnelForList(
				personnelId, personnelType);
		return SUCCESS;
	}

	public String searchPersonInCharegeForAutoComplete() throws Exception {
		if (!this.validatePaceTypeAndId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		helpPersonnelList = helpPersonnelService
				.searchPersonInCharegeForAutoComplete(personnelId,
						personnelType, tag);
		for (HelpPersonnel helpPersonnel : helpPersonnelList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(helpPersonnel.getName());
			autoCompleteData.setDesc("");
			autoCompleteData.setValue(helpPersonnel.getName());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	public String addHelpPersonnel() throws Exception {
		// if(!validateInput()){
		// this.errorMessage = "参数错误";
		// return ERROR;
		// }
		helpPersonnel = helpPersonnelService.addHelpPersonnel(helpPersonnel);
		return SUCCESS;
	}

	public String updateHelpPersonnel() throws Exception {
		// if(!validateInput()){
		// this.errorMessage = "参数错误";
		// return ERROR;
		// }
		helpPersonnel = helpPersonnelService.updateHelpPersonnel(helpPersonnel);
		return SUCCESS;
	}

	public String deleteHelpPersonnel() throws Exception {
		if (helpPersonnel.getId() == null) {
			errorMessage = "对象ID不能为空";
			return ERROR;
		}
		helpPersonnelService.deleteHelpPersonnel(helpPersonnel.getId());
		return SUCCESS;
	}

	private boolean validatePaceTypeAndId() {
		if (personnelId == null || personnelId.longValue() == 0L
				|| !StringUtil.isStringAvaliable("personnelType")) {
			return false;
		}
		return true;
	}

	// private boolean validateInput(){
	// if(helpPersonnel == null ||
	// !StringUtil.isStringAvaliable(helpPersonnel.getName()) ||
	// helpPersonnel.getPersonId() == null ||
	// !StringUtil.isStringAvaliable(helpPersonnel.getPersonType())){
	// return false;
	// }
	// return true;
	// }

	private PageInfo<HelpPersonnel> emptyPage(int pageSize) {
		PageInfo<HelpPersonnel> pageInfo = new PageInfo<HelpPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HelpPersonnel>());
		return pageInfo;
	}

	public Long getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(Long personnelId) {
		this.personnelId = personnelId;
	}

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public HelpPersonnel getHelpPersonnel() {
		return helpPersonnel;
	}

	public void setHelpPersonnel(HelpPersonnel helpPersonnel) {
		this.helpPersonnel = helpPersonnel;
	}

	public List<HelpPersonnel> getHelpPersonnelList() {
		return helpPersonnelList;
	}

	public void setHelpPersonnelList(List<HelpPersonnel> helpPersonnelList) {
		this.helpPersonnelList = helpPersonnelList;
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

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

}
