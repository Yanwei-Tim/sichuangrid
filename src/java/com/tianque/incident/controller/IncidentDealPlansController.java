package com.tianque.incident.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.PropertyDict;
import com.tianque.incident.domain.IncidentDealPlan;
import com.tianque.incident.domain.IncidentDealTeam;
import com.tianque.incident.domain.IncidentPlanStep;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.service.IncidentDealPlanService;
import com.tianque.incident.service.IncidentSystemManagerService;
import com.tianque.incident.vo.IncidentConstants;

@Scope("prototype")
@Transactional
@Controller("incidentDealPlansController")
public class IncidentDealPlansController extends BaseAction {

	@Autowired
	private IncidentDealPlanService incidentDealPlanService;

	@Autowired
	private IncidentSystemManagerService incidentSystemManagerService;

	private List<IncidentDealTeam> incidentDealTeamList;

	private IncidentDealTeam incidentDealTeam;

	private IncidentDealTeam incidentDealTeam2;

	private IncidentDealPlan incidentDealPlan;

	private IncidentPlanStep incidentPlanStep;

	private String propertyDomainName;

	private List<IncidentType> incidentTypeList;

	private Long proId;

	private String idListStr;

	private String seqIndexIdListStr;

	public String editIncidentDealPlanTow() throws Exception {
		if (incidentDealPlan == null || incidentDealPlan.getId() == null) {
			errorMessage = "数据为空";
			return ERROR;
		}
		incidentDealPlan = incidentDealPlanService
				.updateIncidentDealPlanTow(incidentDealPlan);
		if (!incidentDealPlan.getComplyTeam()) {
			deleteTeamType(IncidentConstants.TEAM_MEMBER_TYPE);
		}

		if (!incidentDealPlan.getLeadTeam()) {
			deleteTeamType(IncidentConstants.LEAD_TEAM_TYPE);
		} else {

			incidentDealTeam.setIncidentDealPlan(incidentDealPlan);
			if (incidentDealTeam != null && incidentDealTeam.getId() != null) {
				incidentDealTeam = incidentDealPlanService
						.updateIncidentDealTeam(incidentDealTeam);
			} else {
				incidentDealPlanService.addIncidentDealTeam(incidentDealTeam);
			}
		}
		if (seqIndexIdListStr != null && !seqIndexIdListStr.equals("")) {
			incidentDealPlanService
					.updateIncidentPlanStepSeqIndex(seqIndexIdListStr);
		}
		return SUCCESS;
	}

	public String copyIncidentDealPlan() throws Exception {
		if (incidentDealPlan == null || incidentDealPlan.getId() == null) {
			errorMessage = "数据为空";
			return ERROR;
		}
		IncidentDealPlan incidentDealPlan3 = new IncidentDealPlan();
		incidentDealPlan3.setIncidentType(incidentDealPlan.getIncidentType());
		incidentDealPlan3.setDegree(incidentDealPlan.getDegree());
		IncidentDealPlan incidentDealPlan2 = incidentDealPlanService
				.findIncidentDealPlan(incidentDealPlan3);
		if (incidentDealPlan2 != null) {
			if (incidentDealPlan.getId().equals(incidentDealPlan2.getId())) {
				return "own";
			} else {
				return "otherExist";
			}
		} else {
			IncidentDealPlan idPlan = new IncidentDealPlan();
			idPlan.setId(incidentDealPlan.getId());
			incidentDealPlanService.copyIncidentDealPlanData(idPlan,
					incidentDealPlan);
		}
		return SUCCESS;
	}

	public String editIncidentDealPlan() throws Exception {
		if (incidentDealPlan == null) {
			errorMessage = "数据为空";
			return ERROR;
		}
		if (incidentDealPlan != null && incidentDealPlan.getId() != null) {
			incidentDealPlan = incidentDealPlanService
					.updateIncidentDealPlan(incidentDealPlan);
		} else {
			incidentDealPlan.setComplyTeam(true);
			incidentDealPlan.setLeadTeam(true);
			incidentDealPlanService.addIncidentDealPlan(incidentDealPlan);
		}
		return SUCCESS;
	}

	private void deleteTeamType(Integer teamType) {
		Map<String, Object> deleteConditonMap = new HashMap<String, Object>();
		deleteConditonMap.put("teamType", teamType);
		deleteConditonMap.put("incidentDealPlanId", incidentDealTeam
				.getIncidentDealPlan().getId());
		incidentDealPlanService.deleteIncidentDealTeams(deleteConditonMap);
	}

	public String getIncidentPlanAndDealTeam() throws Exception {
		mainInitData();
		incidentDealPlan
				.setIncidentType(incidentSystemManagerService
						.getIncidentTypeById(incidentDealPlan.getIncidentType()
								.getId()));
		return SUCCESS;

	}

	public String initIncidentPlan() throws Exception {
		mainInitData();
		return SUCCESS;
	}

	private void mainInitData() {
		if (incidentDealPlan == null)
			return;
		IncidentDealPlan incidentDealPlan2 = incidentDealPlanService
				.findIncidentDealPlan(incidentDealPlan);
		if (incidentDealPlan2 == null || incidentDealPlan2.getId() == null) {
			IncidentType incidentType = incidentDealPlan.getIncidentType();
			PropertyDict degree = incidentDealPlan.getDegree();
			incidentDealPlan = new IncidentDealPlan();
			incidentDealPlan.setIncidentType(incidentType);
			incidentDealPlan.setDegree(degree);
			incidentDealPlan.setComplyTeam(true);
			incidentDealPlan.setLeadTeam(true);
		} else {
			incidentDealPlan = incidentDealPlan2;
			if (incidentDealTeam == null) {
				incidentDealTeam = new IncidentDealTeam();
			}
			incidentDealTeam.setIncidentDealPlan(incidentDealPlan);
			incidentDealTeam.setTeamType(IncidentConstants.LEAD_TEAM_TYPE);
			incidentDealTeam = incidentDealPlanService
					.findIncidentDealTeams(incidentDealTeam);
			if (incidentDealTeam == null) {
				incidentDealTeam = new IncidentDealTeam();
				incidentDealTeam.setIncidentDealPlan(incidentDealPlan);
			}
			incidentDealPlan.getDealingTeams().add(incidentDealTeam);
		}

	}

	public String findIncidentPlanSteps() throws Exception {
		if (incidentPlanStep.getIncidentDealPlan() != null
				&& incidentPlanStep.getIncidentDealPlan().getId() != null) {
			incidentPlanStep.setSortField(sidx);
			incidentPlanStep.setOrder(sord);
			gridPage = new GridPage(
					incidentDealPlanService.findIncidentPlanSteps(
							incidentPlanStep, page, rows));
		}
		return SUCCESS;
	}

	public String initIncidentPlanStep() throws Exception {
		if (mode.equals(DialogMode.EDIT_MODE)) {
			incidentPlanStep = incidentDealPlanService
					.findIncidentPlanStep(incidentPlanStep);
		} else {
			incidentPlanStep = incidentDealPlanService
					.findMaxSeqIndexIncidentPlanStep(incidentPlanStep);
			incidentPlanStep.setContent(null);
		}
		return SUCCESS;
	}

	public String incidentPlanStepEdit() throws Exception {
		if (mode.equals(DialogMode.EDIT_MODE)) {
			incidentPlanStep = incidentDealPlanService
					.updateIncidentPlanStep(incidentPlanStep);
		} else {

			incidentPlanStep.setSeqIndex(incidentPlanStep.getSeqIndex() + 1);
			incidentDealPlanService.addIncidentPlanSteps(incidentPlanStep);
		}
		return SUCCESS;

	}

	public String initIncidentDealTeam() throws Exception {
		incidentDealTeam2 = incidentDealPlanService
				.findIncidentDealTeams(incidentDealTeam2);
		return SUCCESS;

	}

	public String incidentDealTeamEidt() throws Exception {
		if (mode.equals(DialogMode.EDIT_MODE)) {
			incidentDealTeam = incidentDealPlanService
					.updateIncidentDealTeam(incidentDealTeam2);
		} else {
			incidentDealPlanService.addIncidentDealTeam(incidentDealTeam2);
		}
		return SUCCESS;

	}

	public String deleteIncidentPlan() throws Exception {
		if (incidentDealPlan == null || incidentDealPlan.getId() == null) {
			errorMessage = "数据为空";
			return ERROR;
		}
		incidentDealPlanService.deleteIncidentDealPlan(incidentDealPlan);
		Map<String, Object> deleteConditonMap = new HashMap<String, Object>();
		deleteConditonMap.put("incidentDealPlanId", incidentDealPlan.getId());
		incidentDealPlanService.deleteIncidentDealTeams(deleteConditonMap);
		incidentDealPlanService.deleteIncidentPlanStep(deleteConditonMap);
		return SUCCESS;

	}

	public String deleteIncidentPlanStep() throws Exception {
		if (idListStr == null || idListStr.equals("")) {
			errorMessage = "选择为null";
			return ERROR;
		}
		String[] idList = idListStr.split(",");
		Map<String, Object> deleteConditonMap = new HashMap<String, Object>();
		deleteConditonMap.put("idList", idList);
		incidentDealPlanService.deleteIncidentPlanStep(deleteConditonMap);
		return SUCCESS;
	}

	public String deleteIncidentDealTeam() throws Exception {
		if (idListStr == null || idListStr.equals("")) {
			errorMessage = "选择为null";
			return ERROR;
		}
		String[] idList = idListStr.split(",");
		Map<String, Object> deleteConditonMap = new HashMap<String, Object>();
		deleteConditonMap.put("teamType", incidentDealTeam2.getTeamType());
		deleteConditonMap.put("idList", idList);
		incidentDealPlanService.deleteIncidentDealTeams(deleteConditonMap);
		return SUCCESS;
	}

	public String findIncidentTypeList() throws Exception {
		incidentTypeList = incidentDealPlanService
				.getIncidentTypeTreeListByPropertyDictId(proId);
		return SUCCESS;
	}

	public String findIncidentDealTeamsList() throws Exception {
		if (incidentDealTeam.getIncidentDealPlan() != null
				&& incidentDealTeam.getIncidentDealPlan().getId() != null) {
			incidentDealTeam.setSortField(sidx);
			incidentDealTeam.setOrder(sord);
			gridPage = new GridPage(
					incidentDealPlanService.findIncidentDealTeamsList(
							incidentDealTeam, page, rows));
		}
		return SUCCESS;
	}

	public List<IncidentDealTeam> getIncidentDealTeamList() {
		return incidentDealTeamList;
	}

	public void setIncidentDealTeamList(
			List<IncidentDealTeam> incidentDealTeamList) {
		this.incidentDealTeamList = incidentDealTeamList;
	}

	public IncidentDealTeam getIncidentDealTeam() {
		return incidentDealTeam;
	}

	public void setIncidentDealTeam(IncidentDealTeam incidentDealTeam) {
		this.incidentDealTeam = incidentDealTeam;
	}

	public IncidentDealPlan getIncidentDealPlan() {
		return incidentDealPlan;
	}

	public void setIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		this.incidentDealPlan = incidentDealPlan;
	}

	public IncidentPlanStep getIncidentPlanStep() {
		return incidentPlanStep;
	}

	public void setIncidentPlanStep(IncidentPlanStep incidentPlanStep) {
		this.incidentPlanStep = incidentPlanStep;
	}

	public String getPropertyDomainName() {
		return propertyDomainName;
	}

	public void setPropertyDomainName(String propertyDomainName) {
		this.propertyDomainName = propertyDomainName;
	}

	public List<IncidentType> getIncidentTypeList() {
		return incidentTypeList;
	}

	public void setIncidentTypeList(List<IncidentType> incidentTypeList) {
		this.incidentTypeList = incidentTypeList;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public String getIdListStr() {
		return idListStr;
	}

	public void setIdListStr(String idListStr) {
		this.idListStr = idListStr;
	}

	public String getSeqIndexIdListStr() {
		return seqIndexIdListStr;
	}

	public void setSeqIndexIdListStr(String seqIndexIdListStr) {
		this.seqIndexIdListStr = seqIndexIdListStr;
	}

	public IncidentDealTeam getIncidentDealTeam2() {
		return incidentDealTeam2;
	}

	public void setIncidentDealTeam2(IncidentDealTeam incidentDealTeam2) {
		this.incidentDealTeam2 = incidentDealTeam2;
	}

}
