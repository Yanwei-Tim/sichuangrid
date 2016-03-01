package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.DialogMode;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Entourage;
import com.tianque.service.EntourageService;
import com.tianque.validate.impl.EntourageValidatorImpl;

/**
 * 未满16周岁的随行人员控制类。
 */
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("entourageController")
public class EntourageController extends BaseAction {
	private Entourage entourage;
	private boolean bol;
	private long existedCount;
	@Autowired
	private EntourageService entourageService;
	private String modeType;

	private EntourageValidatorImpl entourageValidatorImpl;
	@Autowired
	private ValidateHelper validateHelper;

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {

			entourage = new Entourage();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			entourage = entourageService.getEntourageById(entourage.getId());
		}
		return SUCCESS;
	}

	/**
	 * 根据流动人口id查询未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	public String findEntourageForPageByTrampResidentId() {
		if (entourage == null || entourage.getTrampResidentId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(entourageService.findEntourageForPageByTrampResidentId(
					entourage.getTrampResidentId(), sidx, sord));
		}
		return SUCCESS;
	}

	/**
	 * 新增未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	// @PermissionFilter(ename = "addEntourage")
	public String addEntourage() {
		entourageValidatorImpl = new EntourageValidatorImpl();
		entourageValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult entourageValidator = entourageValidatorImpl.validate(entourage);
		if (entourageValidator.hasError()) {
			return ERROR;
		}

		entourage = entourageService.addEntourage(entourage);
		return SUCCESS;
	}

	/**
	 * 修改未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	// @PermissionFilter(ename = "updateEntourage")
	public String updateEntourage() {
		entourageValidatorImpl = new EntourageValidatorImpl();
		entourageValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult entourageValidator = entourageValidatorImpl.validate(entourage);
		if (entourageValidator.hasError()) {
			return ERROR;
		}
		entourage = entourageService.updateEntourage(entourage);
		return SUCCESS;
	}

	/**
	 * 删除未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	// @PermissionFilter(ename = "deleteEntourage")
	public String deleteEntourage() {
		if (entourage == null || entourage.getId() == null) {
			return ERROR;
		} else {
			bol = entourageService.deleteEntourageById(entourage.getId()) >= 1 ? true : false;
		}
		return SUCCESS;
	}

	/**
	 * 根据ID获取未满16周岁的随行人员
	 * 
	 * @return SUCCESS
	 */
	public String getEntourageById() {
		if (entourage == null || entourage.getId() == null) {
			existedCount = 0;
		} else {
			entourage = entourageService.getEntourageById(entourage.getId());
		}
		return SUCCESS;
	}

	public String hasDuplicateEntourage() {
		if (entourage == null || entourage.getTrampResidentId() == null) {
			return ERROR;
		} else {
			String str = entourageService.hasDuplicateEntourage(entourage.getTrampResidentId(),
					entourage.getIdCardNo(), entourage.getId()) ? ERROR : SUCCESS;
			return str;
		}
	}

	private PageInfo<Entourage> emptyPage(int pageSize) {
		PageInfo<Entourage> pageInfo = new PageInfo<Entourage>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Entourage>());
		return pageInfo;
	}

	public Entourage getEntourage() {
		return entourage;
	}

	public void setEntourage(Entourage entourage) {
		this.entourage = entourage;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

}