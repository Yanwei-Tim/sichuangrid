package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Floorperson;
import com.tianque.domain.HelpPrecord;
import com.tianque.service.HelpPrecordService;

@Controller("helpPrecordController")
@Scope("prototype")
@Transactional
public class HelpPrecordController extends BaseAction {
	@Autowired
	private HelpPrecordService helpPrecordService;
	private HelpPrecord helpPrecord = new HelpPrecord();
	private Long personnelId;
	private String personnelType;
	private String precordType;

	public String addHelpPrecord() throws Exception {
		if (helpPrecord.getHelpTime() == null) {
			helpPrecord = new HelpPrecord();
			return SUCCESS;
		}
		helpPrecord = helpPrecordService.addHelpPrecord(helpPrecord);
		return SUCCESS;
	}

	public String updateHelpPrecord() throws Exception {
		if (helpPrecord.getHelpTime() == null) {
			helpPrecord = new HelpPrecord();
			return SUCCESS;
		}
		helpPrecord = helpPrecordService.updateHelpPrecord(helpPrecord);
		return SUCCESS;
	}

	public String findHelpPrecord() {
		if (personnelId == null || personnelId.longValue() == 0L
				|| !StringUtil.isStringAvaliable("personnelType")) {
			gridPage = new GridPage(new PageInfo<Floorperson>());
			return SUCCESS;
		}
		gridPage = new GridPage(helpPrecordService.findHelpPrecord(personnelId,
				page.intValue(), rows.intValue(), sidx, sord, personnelType));
		return SUCCESS;
	}

	public String deleteHelpPrecordById() {
		if (helpPrecord.getId() == null) {
			this.errorMessage = "对象Id不能为空";
			return ERROR;
		}
		helpPrecordService.deleteHelpPrecordById(helpPrecord.getId());
		return SUCCESS;
	}

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			helpPrecord = new HelpPrecord();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			helpPrecord = helpPrecordService
					.getHelpPrecord(helpPrecord.getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			helpPrecord = helpPrecordService
					.getHelpPrecord(helpPrecord.getId());
		}
		return precordType;
	}

	public HelpPrecord getHelpPrecord() {
		return helpPrecord;
	}

	public void setHelpPrecord(HelpPrecord helpPrecord) {
		this.helpPrecord = helpPrecord;
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

	public String getPrecordType() {
		return precordType;
	}

	public void setPrecordType(String precordType) {
		this.precordType = precordType;
	}

}
