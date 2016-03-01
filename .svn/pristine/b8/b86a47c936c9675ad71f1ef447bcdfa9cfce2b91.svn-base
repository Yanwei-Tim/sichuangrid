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
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.domain.Message;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.ReceiveMsgInfo;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;
import com.tianque.tenHouseholdsJoint.service.MessageInfoService;
import com.tianque.tenHouseholdsJoint.service.ReceiveMsgInfoService;

/**
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月2日 下午5:40:11
 */

@Namespace("/tenHouseholdsJoint/receiveMsgInfo")
@Scope("prototype")
@Controller("ReceiveMsgInfoController")
public class ReceiveMsgInfoController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ReceiveMsgInfoService receiveMsgInfoService;
	@Autowired
	private FamilyTeamService familyTeamService;
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private MessageInfoService messageInfoService;

	private ReceiveMsgInfo msgBean;
	private MessageInfoVo messageInfoVo;
	private Integer receiveNum;
	private List<FamilyTeam> familyTeams;

	@Action(value = "dispatch", results = {
			@Result(name = "view", location = "/template/tenHouseholdsJoint/sms/mainMessageDlg.ftl"),
			@Result(name = "search", location = "/template/tenHouseholdsJoint/sms/searchReceiveBox.ftl"),
			@Result(name = "gisSms", location = "/template/tenHouseholdsJoint/gis/viewSmsDlg.ftl"),
			@Result(type = "json", name = "success", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			msgBean = receiveMsgInfoService.getReceiveMsgInfoById(id);
			msgBean.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(msgBean
							.getOrganization().getId(),
							organizationDubboService));
			return mode;
		} else if ("gisSms".equals(mode)) {
			msgBean = receiveMsgInfoService.getReceiveMsgInfoById(id);
			msgBean.getOrganization().setOrgName(
					organizationDubboService.getSimpleOrgById(
							msgBean.getOrganization().getId()).getOrgName());
			return mode;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			familyTeams = familyTeamService
					.queryFamilyTeamByOrgIdForList(messageInfoVo.getOrgId());
			return mode;
		} else if (DialogMode.ADD_MODE.equals(mode)) {
			Message message = mockMessage(messageInfoVo);
			messageInfoService.addMessageInfo(message);
		}
		return SUCCESS;
	}

	/** 表信息列表 */
	@Action(value = "findReceiveMsgInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findReceiveMsgInfos() throws Exception {
		if (messageInfoVo == null) {
			gridPage = new GridPage();
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(messageInfoVo.getOrgId());
			messageInfoVo.setOrgCode(organization.getOrgInternalCode());
			messageInfoVo.setSortField(sidx);
			messageInfoVo.setOrder(sord);
			PageResult<ReceiveMsgInfo> pageResult = receiveMsgInfoService
					.queryReceiveMsgInfosForPageResult(messageInfoVo,
							defaultSortAndPage());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					pageResult, organizationDubboService,
					new String[] { "organization" }, messageInfoVo.getOrgId()));
		}
		return SUCCESS;
	}

	@Action(value = "dealReceiveMsgInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "familyInfo" }) })
	public String dealReceiveMsgInfo() throws Exception {
		if (id == null) {
			return ERROR;
		}
		receiveMsgInfoService.dealReceiveMsgInfo(id);
		return SUCCESS;
	}

	@Action(value = "deleteReceiveMsgInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteReceiveMsgInfoById() throws Exception {
		receiveMsgInfoService.deleteReceiveMsgInfo(id);
		return SUCCESS;
	}

	/** 获取收件箱未处理数量 */
	@Action(value = "getReceiveBoxConditionNum", results = { @Result(type = "json", name = "success", params = {
			"root", "receiveNum", "ignoreHierarchy", "false" }) })
	public String getReceiveBoxConditionNum() throws Exception {
		User user = ThreadVariable.getUser();
		receiveNum = receiveMsgInfoService.getReceiveBoxConditionNum(user
				.getOrgInternalCode());
		return SUCCESS;
	}

	public ReceiveMsgInfo getMsgBean() {
		return msgBean;
	}

	public void setMsgBean(ReceiveMsgInfo msgBean) {
		this.msgBean = msgBean;
	}

	public MessageInfoVo getMessageInfoVo() {
		return messageInfoVo;
	}

	public void setMessageInfoVo(MessageInfoVo messageInfoVo) {
		this.messageInfoVo = messageInfoVo;
	}

	public Integer getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}

	public List<FamilyTeam> getFamilyTeams() {
		return familyTeams;
	}

	public void setFamilyTeams(List<FamilyTeam> familyTeams) {
		this.familyTeams = familyTeams;
	}

	private Message mockMessage(MessageInfoVo messageInfoVo) {
		PropertyDict messageType = new PropertyDict();
		messageType.setId(messageInfoVo.getMessageTypeId());
		Message message = new Message();
		message.setMessageType(messageType);
		message.setSendAddress(messageInfoVo.getTelephone());
		message.setText(messageInfoVo.getText());
		return message;
	}
}
