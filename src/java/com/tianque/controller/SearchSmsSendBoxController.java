package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.SearchSmsSendBoxVo;
import com.tianque.service.SearchSmsSendBoxService;
import com.tianque.userAuth.api.ContacterDubboService;

@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
@Controller("searchSmsSendBoxController")
public class SearchSmsSendBoxController extends BaseAction {

	@Autowired
	private SearchSmsSendBoxService searchSmsSendBoxService;
	@Autowired
	private ContacterDubboService contacterDubboService;

	private SearchSmsSendBoxVo searchSmsSendBoxVo;

	private List<AutoCompleteData> autoCompletePersonnelDatas = new ArrayList<AutoCompleteData>();
	private List<AutoCompleteData> autoCompletePlaceDatas = new ArrayList<AutoCompleteData>();
	private User user;
	private String tag;
	private Integer year;
	private Integer month;

	public String searchSmsSendBoxs() {
		if (searchSmsSendBoxVo == null) {
			this.errorMessage = "参数错误";
		}
		user = ThreadVariable.getUser();
		if (user == null || user.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			searchSmsSendBoxVo.setUserId(user.getId());
			gridPage = new GridPage(searchSmsSendBoxService.searchSmsSendBox(
					searchSmsSendBoxVo, year, month, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	public String searchContacterForAutoComplete() {
		if (tag == null || tag.trim().equals("")) {
			return SUCCESS;
		}
		user = ThreadVariable.getUser();
		// 站内联系人
		List<WorkContacter> workContacterList = contacterDubboService
				.findWorkContactersByNameAndPinyin(tag);
		for (WorkContacter workContacter : workContacterList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(workContacter.getName());
			autoCompleteData.setValue(workContacter.getName() + "-"
					+ workContacter.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}

		// 我的联系人
		List<MyContacter> myContacterList = contacterDubboService
				.findMyContactersByNameAndPinyinAndOwnerId(tag, user.getId());
		for (MyContacter myContacter : myContacterList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(myContacter.getName());
			autoCompleteData.setValue(myContacter.getName() + "-"
					+ myContacter.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}

		// 我的群组
		List<MyGroup> myGroupList = contacterDubboService
				.findMyGroupsByNameAndPinyinAndOwnerId(tag, user.getId());
		for (MyGroup myGroup : myGroupList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(myGroup.getName());
			autoCompleteData.setValue(myGroup.getName() + "-"
					+ myGroup.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	private PageInfo<SmsSendBox> emptyPage(int pageSize) {
		PageInfo<SmsSendBox> pageInfo = new PageInfo<SmsSendBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsSendBox>());
		return pageInfo;
	}

	public SearchSmsSendBoxVo getSearchSmsSendBoxVo() {
		return searchSmsSendBoxVo;
	}

	public void setSearchSmsSendBoxVo(SearchSmsSendBoxVo searchSmsSendBoxVo) {
		this.searchSmsSendBoxVo = searchSmsSendBoxVo;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
