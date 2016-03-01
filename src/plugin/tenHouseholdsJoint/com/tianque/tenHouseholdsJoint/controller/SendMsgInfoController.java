/**
 * 
 */
package com.tianque.tenHouseholdsJoint.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.SendMsgInfo;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;
import com.tianque.tenHouseholdsJoint.service.SendMsgInfoService;

/**
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月2日 下午5:40:32
 */
@Namespace("/tenHouseholdsJoint/sendMsgInfo")
@Scope("prototype")
@Controller("SendMsgInfoController")
public class SendMsgInfoController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SendMsgInfoService sendMsgInfoService;
	@Autowired
	private FamilyTeamService familyTeamService;

	private SendMsgInfo msgBean;
	private MessageInfoVo messageInfoVo;
	private List<SendMsgInfo> sendMsgInfos;
	private List<FamilyTeam> familyTeams;

	@Action(value = "dispatch", results = {
			@Result(name = "view", location = "/template/tenHouseholdsJoint/sms/mainMessageDlg.ftl"),
			@Result(name = "search", location = "/template/tenHouseholdsJoint/sms/searchSendBox.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			msgBean = sendMsgInfoService.getSendMsgInfoById(id);
			msgBean.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(msgBean
							.getOrganization().getId(),
							organizationDubboService));
			return mode;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			familyTeams = familyTeamService
					.queryFamilyTeamByOrgIdForList(messageInfoVo.getOrgId());
			return mode;
		}
		return SUCCESS;
	}

	/** 表信息列表 */
	@Action(value = "findSendMsgInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSendMsgInfos() throws Exception {
		if (messageInfoVo == null) {
			gridPage = new GridPage();
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(messageInfoVo.getOrgId());
			messageInfoVo.setOrgCode(organization.getOrgInternalCode());
			PageResult<SendMsgInfo> pageResult = sendMsgInfoService
					.querySendMsgInfosForPageResult(messageInfoVo,
							defaultSortAndPage());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					pageResult, organizationDubboService,
					new String[] { "organization" }, messageInfoVo.getOrgId()));
		}
		return SUCCESS;
	}

	/** 表信息列表 */
	@Action(value = "findTeamFamilySendMsgInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"sendMsgInfos", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findTeamFamilySendMsgInfos() throws Exception {
		sendMsgInfos = sendMsgInfoService
				.findTeamFamilySendMsgInfos(messageInfoVo);
		return SUCCESS;
	}

	public SendMsgInfo getMsgBean() {
		return msgBean;
	}

	public void setMsgBean(SendMsgInfo msgBean) {
		this.msgBean = msgBean;
	}

	public MessageInfoVo getMessageInfoVo() {
		return messageInfoVo;
	}

	public void setMessageInfoVo(MessageInfoVo messageInfoVo) {
		this.messageInfoVo = messageInfoVo;
	}

	public List<SendMsgInfo> getSendMsgInfos() {
		return sendMsgInfos;
	}

	public void setSendMsgInfos(List<SendMsgInfo> sendMsgInfos) {
		this.sendMsgInfos = sendMsgInfos;
	}

	public List<FamilyTeam> getFamilyTeams() {
		return familyTeams;
	}

	public void setFamilyTeams(List<FamilyTeam> familyTeams) {
		this.familyTeams = familyTeams;
	}

}
