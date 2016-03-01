package com.tianque.baseInfo.companyPlace.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceAnnex;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceAnnexService;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBusinessService;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.KeyPlace;
import com.tianque.service.KeyPlaceService;

@Controller("companyPlaceBusinessController")
@Namespace("/baseinfo/companyPlaceBusinessManage")
@Scope("request")
public class CompanyPlaceBusinessController extends BaseAction {

	@Autowired
	private CompanyPlaceBusinessService companyPlaceBusinessService;
	@Autowired
	private CompanyPlaceAnnexService companyPlaceAnnexService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	private CompanyPlaceBusinessVO companyPlaceBusinessVO;
	private Long companyPlaceId;
	private CompanyPlaceAnnex companyPlaceAnnex;
	private String modulKey;
	private KeyPlace keyPlace;

	@Action(value = "saveCompanyPlaceBusiness", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String saveCompanyPlaceBusiness() throws Exception {
		if (companyPlaceBusinessVO == null) {
			errorMessage = "没有选中任何业务信息!";
			return ERROR;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			companyPlaceBusinessService.addCompanyPlaceBusiness(
					companyPlaceBusinessVO, companyPlaceId, modulKey);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			companyPlaceBusinessService.updateCompanyPlaceBusiness(
					companyPlaceBusinessVO, companyPlaceId, modulKey);
		}
		return SUCCESS;
	}

	@Action(value = "saveCompanyPlaceBusinessForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String saveCompanyPlaceBusinessForMobile() throws Exception {
		if (companyPlaceBusinessVO == null) {
			errorMessage = "没有选中任何业务信息!";
			return ERROR;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			companyPlaceBusinessService.addCompanyPlaceBusinessForMobile(
					companyPlaceBusinessVO, companyPlaceId, modulKey);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			companyPlaceBusinessService.updateCompanyPlaceBusinessForMobile(
					companyPlaceBusinessVO, companyPlaceId, modulKey);
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/baseinfo/companyPlace/companyPlaceBusinessDlg.jsp"),
			@Result(name = "add", location = "/baseinfo/companyPlace/companyPlaceBusinessDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/companyPlace/companyPlaceBusinessView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (id == null) {
			this.errorMessage = "单位场所传参出错!";
			return ERROR;
		}
		companyPlaceBusinessVO = companyPlaceBusinessService
				.queryCompanyPlaceBusinessVOByCompanyPlaceIdForList(id);
		return mode;
	}

	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "edit", location = "/baseinfo/companyPlace/companyPlaceBusinessDlg.jsp"),
			@Result(name = "add", location = "/baseinfo/companyPlace/companyPlaceBusinessDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/companyPlace/companyPlaceBusinessView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchByEncrypt() throws Exception {
		companyPlaceBusinessVO = companyPlaceBusinessService
				.queryCompanyPlaceBusinessVOByCompanyPlaceIdForList(companyPlaceId);
		return mode;
	}

	@Action(value = "downloadCompanyPlaceAnnexAttachFile")
	public String downloadCompanyPlaceAnnexAttachFile() throws Exception {
		if (null == companyPlaceAnnex || null == companyPlaceAnnex.getId()) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		companyPlaceAnnex = companyPlaceAnnexService
				.getCompanyPlaceAnnex(companyPlaceAnnex.getId());

		if (null == companyPlaceAnnex) {
			errorMessage = "附件不存在";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + companyPlaceAnnex.getAnnexAddress());
			downloadFileName = new String(companyPlaceAnnex.getFileName()
					.getBytes("gbk"), "ISO8859-1");
		} catch (FileNotFoundException e) {
			errorMessage = "附件文件不存在";
			return ERROR;
		} catch (UnsupportedEncodingException uee) {
			errorMessage = "文件编码格式不正确";
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	/**
	 * 根据ID和TYPE查询对应的keyplace信息
	 * 
	 * @return
	 */
	@Action(value = "getKeyPlaceByIdAndType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"keyPlace", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getKeyPlaceByIdAndType() {
		keyPlace = keyPlaceService.getKeyPlaceByIdAndType(id, type);
		return SUCCESS;
	}

	public CompanyPlaceBusinessVO getCompanyPlaceBusinessVO() {
		return companyPlaceBusinessVO;
	}

	public void setCompanyPlaceBusinessVO(
			CompanyPlaceBusinessVO companyPlaceBusinessVO) {
		this.companyPlaceBusinessVO = companyPlaceBusinessVO;
	}

	public Long getCompanyPlaceId() {
		return companyPlaceId;
	}

	public void setCompanyPlaceId(Long companyPlaceId) {
		this.companyPlaceId = companyPlaceId;
	}

	public CompanyPlaceAnnex getCompanyPlaceAnnex() {
		return companyPlaceAnnex;
	}

	public void setCompanyPlaceAnnex(CompanyPlaceAnnex companyPlaceAnnex) {
		this.companyPlaceAnnex = companyPlaceAnnex;
	}

	public String getModulKey() {
		return modulKey;
	}

	public void setModulKey(String modulKey) {
		this.modulKey = modulKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KeyPlace getKeyPlace() {
		return keyPlace;
	}

	public void setKeyPlace(KeyPlace keyPlace) {
		this.keyPlace = keyPlace;
	}
}
