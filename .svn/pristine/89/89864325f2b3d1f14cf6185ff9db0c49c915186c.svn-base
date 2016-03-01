package com.tianque.baseInfo.personnelTrackInfo.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.personnelTrackInfo.domain.PersonnelTrackInfo;
import com.tianque.baseInfo.personnelTrackInfo.service.PersonnelTrackInfoService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Controller("personnelTrackInfoController")
@Namespace("/baseinfo/personnelTrackInfoManage")
@Transactional
public class PersonnelTrackInfoController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(PersonnelTrackInfoController.class);

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PersonnelTrackInfoService personnelTrackInfoService;
	private String idCardNo;

	@Action(value = "findpersonnelTracksByidCardNo", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPersonnelTrackInfoByIdCardNo() {
		if (!StringUtil.isStringAvaliable(idCardNo)) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		PageInfo<PersonnelTrackInfo> pageInfo = ControllerHelper.processAllOrgRelativeName(
				personnelTrackInfoService.findPersonnelTrackInfoByIdCardNo(idCardNo, page, rows,
						sidx, sord), organizationDubboService, new String[] { "operationOrganization",
						"personnelOrganization", "oldPersonnelOrganization" }, null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private PageInfo emptyPage(Integer pageSize) {
		PageInfo<PersonnelTrackInfo> pageInfo = new PageInfo<PersonnelTrackInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PersonnelTrackInfo>());
		return pageInfo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

}
