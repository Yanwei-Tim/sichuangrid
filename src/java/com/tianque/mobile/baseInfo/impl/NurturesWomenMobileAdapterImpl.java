package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.BaseInfoController;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.nurturesWomen.controller.SearchNurturesWomenController;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchNurturesWomenVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.NurturesWomenMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("nurturesWomenMobileAdapter")
public class NurturesWomenMobileAdapterImpl extends BaseMobileAction implements
		NurturesWomenMobileAdapter {
	@Autowired
	private SearchNurturesWomenController searchNurturesWomenController;
	private Long orgId;
	private SearchNurturesWomenVo searchNurturesWomenVo;
	private String idCardNo;
	@Autowired
	private BaseInfoController baseInfoController;
	private Countrymen countrymen;

	@Override
	public String findNurturesWomenList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchNurturesWomenVo == null) {
			searchNurturesWomenVo = new SearchNurturesWomenVo();
		}
		setControllerProperties();
		searchNurturesWomenController.searchNurturesWomen();
		gridPage = searchNurturesWomenController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchNurturesWomenController
				.setSearchNurturesWomenVo(searchNurturesWomenVo);
		searchNurturesWomenController.setOrganizationId(orgId);
		searchNurturesWomenController.setPage(page);
		searchNurturesWomenController.setRows(rows);
		searchNurturesWomenController.setSidx(sidx);
		searchNurturesWomenController.setSord(sord);
	}

	private PageInfo<NurturesWomen> emptyPage(int pageSize) {
		PageInfo<NurturesWomen> pageInfo = new PageInfo<NurturesWomen>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NurturesWomen>());
		return pageInfo;
	}

	/**
	 * 
	 * @Title: getManInfoByIdcard
	 * @Description: TODO根据身份证获取男方信息
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-10-14 下午06:10:09
	 */
	@Override
	public String getManInfoByIdcard() throws Exception {
		if (!StringUtil.isStringAvaliable(idCardNo)) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		baseInfoController.setIdCardNo(idCardNo);
		baseInfoController.getBaseInfoByIdCardNo();
		countrymen = baseInfoController.getCountrymen();
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchNurturesWomenVo getSearchNurturesWomenVo() {
		return searchNurturesWomenVo;
	}

	public void setSearchNurturesWomenVo(
			SearchNurturesWomenVo searchNurturesWomenVo) {
		this.searchNurturesWomenVo = searchNurturesWomenVo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Countrymen getCountrymen() {
		return countrymen;
	}

	public void setCountrymen(Countrymen countrymen) {
		this.countrymen = countrymen;
	}

}
