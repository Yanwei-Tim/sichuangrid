package com.tianque.incident.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.incident.domain.IncidentStepFeedbacks;
import com.tianque.incident.domain.IncidentSteps;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.Incidents;
import com.tianque.incident.service.IncidentService;

@Scope("prototype")
@Transactional
@Controller("incidentController")
public class IncidentController extends BaseAction {
	@Autowired
	private IncidentService incidentService;

	private Incidents incidents;

	private IncidentType incidentType;

	private List<Incidents> incidentsList;

	private List<IncidentType> incidentTypes;

	private String range;

	private IncidentSteps incidentSteps;

	private List<IncidentSteps> incidentStepList;

	private IncidentStepFeedbacks incidentStepFeedbacks;
	private String tabType;

	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			incidents = new Incidents();
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			incidents = incidentService.findIncidents(incidents);
		}
		return SUCCESS;
	}

	public String maintainHandleWarning() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			incidents.setApplyDate(new Date());
			if (range != null && !range.equals("")) {
				incidents.setRange(range);
			}
			incidents = incidentService.addEarlyWarningIncidents(incidents);
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			incidents.setApplyDate(new Date());
			if (range != null && !range.equals("")) {
				incidents.setRange(range);
			}
			incidents = incidentService.updateEarlyWarningIncidents(incidents);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String findPlansSubdivisionBySubjection() throws Exception {
		incidentTypes = incidentService
				.findPlansSubdivisionBySubjection(incidentType.getId());
		return SUCCESS;
	}

	public String findIncidentsList() throws Exception {
		incidentsList = incidentService.findIncidentsList(incidents);
		return SUCCESS;
	}

	public String caseLibIndex() throws Exception {
		incidentsList = incidentService.findIncidentsList(incidents);
		return SUCCESS;
	}

	public String findEarlyWarningContnet() throws Exception {
		if (incidents == null || incidents.getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		incidents = incidentService.findIncidents(incidents);
		return SUCCESS;
	}

	public String loadCaseLibraryList() throws Exception {
		if (incidents == null) {
			errorMessage = "后台请求参数为空";
			return ERROR;
		} else {
			incidentsList = incidentService.loadCaseLibraryList(incidents);
			return SUCCESS;
		}
	}

	public String loadCaseLibrary() throws Exception {
		if (incidents.getId() == null) {
			errorMessage = "请求参数为空";
			return ERROR;
		} else {
			incidents = incidentService.loadCaseLibrary(incidents.getId());
			incidentStepList = incidentService.loadloadIncidentSteps(incidents
					.getId());
			return SUCCESS;
		}
	}

	public String findIncidentStepFeedbacks() throws Exception {
		if (incidentStepFeedbacks.getIncidentSteps() == null
				|| incidentStepFeedbacks.getIncidentSteps().getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		incidentStepFeedbacks.setFeedBackDate(new Date());
		return SUCCESS;
	}

	public String editIncidentStepFeedbacks() throws Exception {
		if (incidentStepFeedbacks.getIncidentSteps() == null
				|| incidentStepFeedbacks.getIncidentSteps().getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		incidentStepFeedbacks.setFeedBackUser("admin");
		incidentService.addIncidentStepFeedback(incidentStepFeedbacks);
		return SUCCESS;

	}

	public String handleWarning() throws Exception {
		if (incidents == null || incidents.getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		if (range != null && !range.equals("")) {
			incidents.setRange(range);
		}
		incidents.setAuditDate(new Date());
		incidentService.updateIncidentsHandle(incidents);
		return SUCCESS;
	}

	public String editIncidentstep() throws Exception {
		if (mode.equals(DialogMode.EDIT_MODE)) {
			if (incidentSteps == null || incidentSteps.getId() == null) {
				errorMessage = "后台请求对象为空";
				return ERROR;
			}
			incidentSteps.setExecuteUser("admin");
			incidentService.updateIncidentsteps(incidentSteps);
		} else {
			if (incidentSteps == null) {
				errorMessage = "后台请求对象为空";
				return ERROR;
			}
			incidentSteps.setExecuteUser("admin");
			incidentService.addIncidentsteps(incidentSteps);
		}
		return SUCCESS;
	}

	public String cancelWarning() throws Exception {
		if (incidents == null || incidents.getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		incidents.setAuditDate(new Date());
		incidentService.updateIncidentsCaseLibrary(incidents);
		return SUCCESS;
	}

	public String findHandleContent() throws Exception {
		if (incidents == null || incidents.getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		incidents = incidentService.findIncidents(incidents);
		return SUCCESS;
	}

	public String findIncidentStepList() throws Exception {
		if (incidentSteps == null || incidentSteps.getIncidents() == null
				|| incidentSteps.getIncidents().getId() == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		incidentStepList = incidentService.findIncidentSteps(incidentSteps);
		return SUCCESS;
	}

	public String findIncidentStep() throws Exception {
		if (incidentSteps == null) {
			errorMessage = "后台请求对象为空";
			return ERROR;
		}
		if (mode.equals(DialogMode.VIEW_MODE)) {
			if (incidentSteps.getId() == null) {
				errorMessage = "后台请求对象为空";
				return ERROR;
			}
			incidentSteps = incidentService.findIncidentStep(incidentSteps);
			return SUCCESS;
		} else if (mode.equals(DialogMode.EDIT_MODE)) {
			if (incidentSteps.getId() == null) {
				errorMessage = "后台请求对象为空";
				return ERROR;
			}
			incidentSteps = incidentService.findIncidentStep(incidentSteps);
			incidentSteps.setExecuteDate(new Date());
			return "editView";
		} else {
			incidentSteps.setExecuteDate(new Date());
			return "editView";
		}
	}

	public String incidentsListPage() throws Exception {
		incidentsList = incidentService.findIncidentsList(incidents);
		tabType = "caseLibrary";
		return SUCCESS;
	}

	public String caseLibraryDetail() throws Exception {
		if (incidents == null || incidents.getId() == null) {
			errorMessage = "请求参数为空";
			return ERROR;
		} else {
			incidents = incidentService.loadCaseLibrary(incidents.getId());
			incidentStepList = incidentService.loadloadIncidentSteps(incidents
					.getId());
			return SUCCESS;
		}
	}

	public Incidents getIncidents() {
		return incidents;
	}

	public void setIncidents(Incidents incidents) {
		this.incidents = incidents;
	}

	public List<Incidents> getIncidentsList() {
		return incidentsList;
	}

	public void setIncidentsList(List<Incidents> incidentsList) {
		this.incidentsList = incidentsList;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public IncidentSteps getIncidentSteps() {
		return incidentSteps;
	}

	public void setIncidentSteps(IncidentSteps incidentSteps) {
		this.incidentSteps = incidentSteps;
	}

	public List<IncidentSteps> getIncidentStepList() {
		return incidentStepList;
	}

	public void setIncidentStepList(List<IncidentSteps> incidentStepList) {
		this.incidentStepList = incidentStepList;
	}

	public IncidentStepFeedbacks getIncidentStepFeedbacks() {
		return incidentStepFeedbacks;
	}

	public void setIncidentStepFeedbacks(
			IncidentStepFeedbacks incidentStepFeedbacks) {
		this.incidentStepFeedbacks = incidentStepFeedbacks;
	}

	public List<IncidentType> getIncidentTypes() {
		return incidentTypes;
	}

	public void setIncidentTypes(List<IncidentType> incidentTypes) {
		this.incidentTypes = incidentTypes;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public String getTabType() {
		return tabType;
	}

	public void setTabType(String tabType) {
		this.tabType = tabType;
	}

}
