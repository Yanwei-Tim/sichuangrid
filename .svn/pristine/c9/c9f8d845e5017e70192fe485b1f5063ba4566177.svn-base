package com.tianque.partyBuilding.organizations.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.partyBuilding.organizations.constant.RegionalPartyType;
import com.tianque.partyBuilding.organizations.domain.RegionalPartyMember;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalPartyMemberVo;
import com.tianque.partyBuilding.organizations.service.RegionalPartyMemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 区域党委成员控制类
 * */
@Namespace("/partyBuilding/regionalPartyMemberManage")
@Scope("request")
@Controller("regionalPartyMemberController")
public class RegionalPartyMemberController extends BaseAction {

	@Autowired
	private RegionalPartyMemberService regionalPartyMemberService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private RegionalPartyMemberVo regionalPartyMemberVo;
	private RegionalPartyMember regionalPartyMember;
	private String ids;

	/**
	 * 根据条件查询区域党委成员同时根据传的参数不同可以用于list显示
	 * */
	@Action(value = "findRegionalPartyMemberPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findRegionalPartyMemberPagerBySearchVo() throws Exception {
		if (null == regionalPartyMemberVo
				|| regionalPartyMemberVo.getOrganization().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					this.regionalPartyMemberService
							.findRegionalPartyMemberPagerBySearchVo(
									regionalPartyMemberVo, page, rows, sidx,
									sord), organizationDubboService,
					new String[] { "organization" }, regionalPartyMemberVo
							.getOrganization().getId()));
		}

		return SUCCESS;
	}

	/** id加密控制跳转 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/regionalPartyMemberManage/regionalPartyMemberMaintainDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organizations/regionalPartyMemberManage/regionalPartyMemberView.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatchByEncrypt() throws Exception {
		if (RegionalPartyType.REGIONALPARTY_ADD.equals(mode)) {
			regionalPartyMember.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMember
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(mode)) {

			if (null == regionalPartyMember
					|| regionalPartyMember.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			regionalPartyMember = regionalPartyMemberService
					.get(regionalPartyMember.getId());
			regionalPartyMember.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMember
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_VIEW.equals(mode)) {
			if (null == regionalPartyMember
					|| regionalPartyMember.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			regionalPartyMember = regionalPartyMemberService
					.get(regionalPartyMember.getId());
			regionalPartyMember.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMember
							.getOrganization().getId(), organizationDubboService));
			return mode;

		} else if (RegionalPartyType.REGIONALPARTY_SEARCH.equals(mode)) {
			if (null == regionalPartyMemberVo
					|| regionalPartyMemberVo.getOrganization().getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			regionalPartyMemberVo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMemberVo
							.getOrganization().getId(), organizationDubboService));
			return mode;
		}
		return SUCCESS;
	}

	/** 控制跳转 */
	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/organizations/regionalPartyMemberManage/regionalPartyMemberMaintainDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organizations/regionalPartyMemberManage/regionalPartyMemberView.jsp"),
			@Result(name = "search", location = "/partyBuilding/organizations/regionalPartyMemberManage/searchRegionalPartyMemberDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatch() throws Exception {
		if (RegionalPartyType.REGIONALPARTY_ADD.equals(mode)) {
			regionalPartyMember.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMember
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(mode)) {

			if (null == regionalPartyMember
					|| regionalPartyMember.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			regionalPartyMember = regionalPartyMemberService
					.get(regionalPartyMember.getId());
			regionalPartyMember.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMember
							.getOrganization().getId(), organizationDubboService));
		} else if (RegionalPartyType.REGIONALPARTY_VIEW.equals(mode)) {
			if (null == regionalPartyMember
					|| regionalPartyMember.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			regionalPartyMember = regionalPartyMemberService
					.get(regionalPartyMember.getId());
			regionalPartyMember.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMember
							.getOrganization().getId(), organizationDubboService));
			return mode;

		} else if (RegionalPartyType.REGIONALPARTY_SEARCH.equals(mode)) {
			if (null == regionalPartyMemberVo
					|| regionalPartyMemberVo.getOrganization().getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			regionalPartyMemberVo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(regionalPartyMemberVo
							.getOrganization().getId(), organizationDubboService));
			return mode;
		}
		return SUCCESS;
	}

	/** 对区域党委成员的操作（新增，修改） */
	@Action(value = "maintainRegionalPartyMember", results = {
			@Result(type = "json", name = "success", params = { "root",
					"regionalPartyMember", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainRegionalPartyMember() throws Exception {
		if (null == regionalPartyMember
				|| regionalPartyMember.getOrganization().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		regionalPartyMember.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrgId(regionalPartyMember
						.getOrganization().getId(), organizationDubboService));
		if (RegionalPartyType.REGIONALPARTY_ADD.equals(mode)) {
			regionalPartyMember = regionalPartyMemberService
					.add(regionalPartyMember);
		} else if (RegionalPartyType.REGIONALPARTY_EDIT.equals(mode)) {
			regionalPartyMember = regionalPartyMemberService
					.update(regionalPartyMember);
		}
		return SUCCESS;
	}

	/** id加密对区域党委成员的删除 */
	@Action(value = "deleteRegionalPartyMemberByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String deleteRegionalPartyMemberByIdsByEncrypt() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		regionalPartyMemberService.delete(idsLong);
		return SUCCESS;
	}

	/** 对区域党委成员的删除 */
	@Action(value = "deleteRegionalPartyMemberByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteRegionalPartyMemberByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		regionalPartyMemberService.delete(idsLong);
		return SUCCESS;
	}

	public RegionalPartyMemberVo getRegionalPartyMemberVo() {
		return regionalPartyMemberVo;
	}

	public void setRegionalPartyMemberVo(
			RegionalPartyMemberVo regionalPartyMemberVo) {
		this.regionalPartyMemberVo = regionalPartyMemberVo;
	}

	public RegionalPartyMember getRegionalPartyMember() {
		return regionalPartyMember;
	}

	public void setRegionalPartyMember(RegionalPartyMember regionalPartyMember) {
		this.regionalPartyMember = regionalPartyMember;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
