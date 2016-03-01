package com.tianque.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Speech;
import com.tianque.service.SpeechService;

@Namespace("/baseinfo/speechManage")
@Scope("request")
@Transactional
@Controller("speechController")
public class SpeechController extends BaseAction {

	@Autowired
	private SpeechService speechService;
	// 列表显示的speech集合
	private List<Speech> speechList;
	private Long orgId;// 页面端传递过来的orgId
	private Speech speech;// speech实体
	private long speechId;// 页面传递过来的
	private String speechType;// 言论的类型：心得，服务评价
	private Speech obj;// 查看详细
	private Integer total;

	@Action(value = "dispatchOperate", results = {
			// @Result(name = "list", type = "json", params = { "root",
			// "speechList",
			// "ignoreHierarchy", "false" }),
			@Result(name = "list", location = "/baseinfo/partyConstruction/speech/speechListDlg.jsp"),
			@Result(name = "speechDlg", location = "/baseinfo/partyConstruction/speech/addSpeech.jsp") })
	public String dispatchOperate() throws Exception {
		// mode = getRetunString();
		// 查看列表
		/*
		 * if ("list".equalsIgnoreCase(mode)) { Map<String, Object> map = new
		 * HashMap<String, Object>(); map.put("orgId", orgId);
		 * map.put("speechType", speechType); map.put("sortField",
		 * "createDate"); map.put("order", "desc"); speechList =
		 * speechService.findSpeechs(map, page, rows); return "list"; }
		 */
		// 查看单条记录
		if ("view".equalsIgnoreCase(mode)) {
			speech = speechService.getSpeechById(speechId);
			return "speechDlg";
		}

		// 点击新增
		if ("add".equalsIgnoreCase(mode)) {
			speech = new Speech();
			speech.setCreateDate(new Date());
			speech.setCreateUserRealName(ThreadVariable.getSession()
					.getUserRealName());
			return "speechDlg";
		}
		if ("edit".equalsIgnoreCase(mode)) {
			speech = speechService.getSpeechById(speechId);
			return "speechDlg";
		}

		return SUCCESS;

	}

	// @PermissionFilter(ename =
	// "addFeelingExperience,updateFeelingExperience,addServeAssess,updateServeAssess")
	@Action(value = "saveSpeech", results = {
			@Result(name = "success", type = "json", params = { "root",
					"speech", "ignoreHierarchy", "false" }),
			// @Result(name = "success", type =
			// "redirect",location="/partyBuilding/list.jsp?speechType=00&module=speech&organizationId=${organizationId}"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String saveSpeech() throws Exception {
		if ("add".equalsIgnoreCase(mode)) {
			speech = speechService.addSpeech(speech);
		}
		if ("edit".equalsIgnoreCase(mode)) {
			speech = speechService.updateSpeech(speech);
		}
		return SUCCESS;
	}

	@Action(value = "detail", results = { @Result(name = "detail", location = "/${path}/partyBuilding/common/detail.jsp") })
	public String detail() throws Exception {
		obj = speechService.getSpeechById(speechId);
		return "detail";
	}

	@Action(value = "speechList", results = { @Result(name = "list", location = "/${path}/baseinfo/partyConstruction/speech/speechListDlg.jsp") })
	public String listSpeech() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("speechType", speechType);
		map.put("sortField", "createDate");
		map.put("order", "desc");
		PageInfo<Speech> pageInfo = speechService.findSpeechs(map, page, rows);
		speechList = pageInfo.getResult();
		total = pageInfo.getPageNum();
		return "list";
	}

	// @PermissionFilter(ename = "deleteServeAssess,deleteFeelingExperience")
	@Action(value = "deleteSpeech", results = { @Result(name = "success", type = "json") })
	public String deleteSpeech() throws Exception {
		speechService.deleteSpeechById(speechId);
		return SUCCESS;
	}

	public List<Speech> getSpeechList() {
		return speechList;
	}

	public void setSpeechList(List<Speech> speechList) {
		this.speechList = speechList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Speech getSpeech() {
		return speech;
	}

	public void setSpeech(Speech speech) {
		this.speech = speech;
	}

	public long getSpeechId() {
		return speechId;
	}

	public void setSpeechId(long speechId) {
		this.speechId = speechId;
	}

	public SpeechService getSpeechService() {
		return speechService;
	}

	public void setSpeechService(SpeechService speechService) {
		this.speechService = speechService;
	}

	public String getSpeechType() {
		return speechType;
	}

	public void setSpeechType(String speechType) {
		this.speechType = speechType;
	}

	public Speech getObj() {
		return obj;
	}

	public void setObj(Speech obj) {
		this.obj = obj;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
