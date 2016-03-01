package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.SmsReceivedBox;
import com.tianque.domain.User;
import com.tianque.domain.vo.SearchSmsReceivedBoxVo;
import com.tianque.service.SearchSmsReceivedBoxService;

@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
@Controller("searchSmsReceivedBoxController")
public class SearchSmsReceivedBoxController extends BaseAction {

	@Autowired
	private SearchSmsReceivedBoxService searchSmsReceivedBoxService;
	private SearchSmsReceivedBoxVo searchSmsReceivedBoxVo;
	private User user;
	private Integer year;
	private Integer month;

	public String searchSmsReceivedBoxs() {
		if (searchSmsReceivedBoxVo == null) {
			this.errorMessage = "参数错误";
		}
		user = ThreadVariable.getUser();
		if (user == null || user.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			Organization org = user.getOrganization();
			searchSmsReceivedBoxVo.setOrgId(org.getId());
			gridPage = new GridPage(
					searchSmsReceivedBoxService.searchSmsReceivedBox(
							searchSmsReceivedBoxVo, year, month, page, rows,
							sidx, sord));
		}
		return SUCCESS;
	}

	private PageInfo<SmsReceivedBox> emptyPage(int pageSize) {
		PageInfo<SmsReceivedBox> pageInfo = new PageInfo<SmsReceivedBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsReceivedBox>());
		return pageInfo;
	}

	public SearchSmsReceivedBoxVo getSearchSmsReceivedBoxVo() {
		return searchSmsReceivedBoxVo;
	}

	public void setSearchSmsReceivedBoxVo(
			SearchSmsReceivedBoxVo searchSmsReceivedBoxVo) {
		this.searchSmsReceivedBoxVo = searchSmsReceivedBoxVo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
