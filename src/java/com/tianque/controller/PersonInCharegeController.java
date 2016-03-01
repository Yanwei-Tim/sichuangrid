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
import com.tianque.domain.PersonInCharges;
import com.tianque.service.PersonInChargesService;

@Controller("personInCharegeController")
@SuppressWarnings("serial")
@Scope("prototype")
public class PersonInCharegeController extends BaseAction {
	@Autowired
	private PersonInChargesService personInChargesService;

	private Long placeId;
	private String placeType;
	private PersonInCharges personInCharges;
	private List<PersonInCharges> perList;
	private String tag;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();

	public String findPersonInCharegeForListPage() throws Exception {
		if (!validatePaceTypeAndId()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(personInChargesService.findPersonInCharges(
					placeId, page, rows, sidx, sord, placeType));
		}
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			personInCharges = new PersonInCharges();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			personInCharges = personInChargesService
					.getPersonInChargesById(personInCharges.getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			personInCharges = personInChargesService
					.getPersonInChargesById(personInCharges.getId());
		}
		if (null == placeType) {
			return SUCCESS;
		}
		if (placeType.equals("securityKey")) {
			return "security";
		}
		if (placeType.equals("lettingHouses")) {
			return "lettingHouseKeyType";
		}
		return SUCCESS;
	}

	public String getPersonInCharegeByIdAndPlace() throws Exception {
		if (!this.validatePaceTypeAndId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		perList = personInChargesService.getPerPersonInChargesForList(placeId,
				placeType);
		return SUCCESS;
	}

	public String findPersonInCharegeForList() throws Exception {
		if (!this.validatePaceTypeAndId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		perList = personInChargesService.findperPersonInChargesForList(placeId,
				placeType, tag);
		for (PersonInCharges personInCharges : perList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(personInCharges.getName());
			autoCompleteData.setDesc("");
			autoCompleteData.setValue(personInCharges.getName());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	public String addPersonInCharege() throws Exception {
		// if(!validateInput()){
		// this.errorMessage = "参数错误";
		// return ERROR;
		// }
		personInCharges = personInChargesService
				.addPersonInCharges(personInCharges);
		return SUCCESS;
	}

	public String updatePersonInCharege() throws Exception {
		// if(!validateInput()){
		// this.errorMessage = "参数错误";
		// return ERROR;
		// }
		personInCharges = personInChargesService
				.updatePersonInCharges(personInCharges);
		return SUCCESS;
	}

	public String deletePersonInCharege() throws Exception {
		personInChargesService.deletePersonInChargesById(personInCharges
				.getId());
		return SUCCESS;
	}

	private boolean validatePaceTypeAndId() {
		if (!StringUtil.isStringAvaliable("placeType")) {
			return false;
		}
		return true;
	}

	// private boolean validateInput(){
	// if(personInCharges == null ||
	// !StringUtil.isStringAvaliable(personInCharges.getName()) ||
	// !StringUtil.isStringAvaliable(personInCharges.getPlaceType())){
	// return false;
	// }
	// return true;
	// }

	private PageInfo<PersonInCharges> emptyPage(int pageSize) {
		PageInfo<PersonInCharges> pageInfo = new PageInfo<PersonInCharges>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PersonInCharges>());
		return pageInfo;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public PersonInCharges getPersonInCharges() {
		return personInCharges;
	}

	public void setPersonInCharges(PersonInCharges personInCharges) {
		this.personInCharges = personInCharges;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public List<PersonInCharges> getPerList() {
		return perList;
	}

	public void setPerList(List<PersonInCharges> perList) {
		this.perList = perList;
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

}
