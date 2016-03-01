package com.tianque.incident.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.incident.dao.IncidentDao;
import com.tianque.incident.domain.IncidentDealPlan;
import com.tianque.incident.domain.IncidentDealTeam;
import com.tianque.incident.domain.IncidentPlanStep;
import com.tianque.incident.domain.IncidentStepFeedbacks;
import com.tianque.incident.domain.IncidentSteps;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.Incidents;
import com.tianque.incident.service.IncidentDealPlanService;
import com.tianque.incident.service.IncidentService;
import com.tianque.incident.vo.IncidentConstants;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("incidentService")
@Transactional
public class IncidentServiceImpl implements IncidentService {
	@Autowired
	private IncidentDao incidentDao;

	@Autowired
	private IncidentDealPlanService incidentDealPlanService;

	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public List<Incidents> findIncidentsList(Incidents incidents) {

		return incidentDao.findIncidentsList(incidents);
	}

	@Override
	public Incidents findIncidents(Incidents incidents) {
		return incidentDao.findIncidents(incidents);
	}

	@Override
	public void updateIncidentsHandle(Incidents incidents) {
		incidentDao.updateIncidentsHandle(incidents);
		IncidentDealPlan incidentDealPlan = new IncidentDealPlan();
		IncidentType incidentType = new IncidentType();
		incidentType.setId(incidents.getSubdivision());
		incidentDealPlan.setIncidentType(incidentType);
		incidentDealPlan.setDegree(incidents.getDegree());
		incidentDealPlan = incidentDealPlanService.findIncidentDealPlan(incidentDealPlan);
		if (incidentDealPlan == null)
			return;
		IncidentDealTeam incidentDealTeam = new IncidentDealTeam();
		incidentDealTeam.setIncidentDealPlan(incidentDealPlan);
		List<IncidentDealTeam> dealTeamList = incidentDealPlanService
				.findIncidentDealTeamsListByIncidentDealPlanId(incidentDealTeam);
		IncidentPlanStep incidentPlanStep = new IncidentPlanStep();
		incidentPlanStep.setIncidentDealPlan(incidentDealPlan);
		List<IncidentPlanStep> planStepList = incidentDealPlanService
				.findIncidentPlanStepsByIncidentDealPlanId(incidentPlanStep);
		List<IncidentSteps> incidentStepsList = new ArrayList<IncidentSteps>();
		if (dealTeamList != null) {
			for (IncidentDealTeam dealTeam : dealTeamList) {
				incidentStepsList.add(setIncidentStepsByIncidentDealTeam(dealTeam, incidents));
			}
		}
		if (planStepList != null) {
			int stepNum = 0;
			for (IncidentPlanStep planStep : planStepList) {
				stepNum++;
				incidentStepsList.add(setIncidentStepsByIncidentPlanStep(planStep, incidents,
						stepNum));
			}
		}
		addIncidentsteps(incidentStepsList);
	}

	private IncidentSteps setIncidentStepsByIncidentDealTeam(IncidentDealTeam dealTeam,
			Incidents incidents) {
		IncidentSteps incidentSteps = new IncidentSteps();
		incidentSteps.setIncidents(incidents);
		StringBuffer sb = new StringBuffer();
		sb.append("成立" + dealTeam.getTeamName());
		if (dealTeam.getResponsibility() != null) {
			sb.append(" , 职责是  " + dealTeam.getResponsibility());
		}
		if (dealTeam.getTeamType().equals(IncidentConstants.LEAD_TEAM_TYPE)) {
			incidentSteps.setStepType(IncidentConstants.LEADTEAM_STEPTYPE);
			incidentSteps.setPlanContent(sb.toString());
		} else {
			incidentSteps.setStepType(IncidentConstants.HANDTEAM_STEPTYPE);
			sb.append(" , 牵头部门是 " + dealTeam.getTeamLeader());
			sb.append(" , 参与部门是 " + dealTeam.getTeamMember());
			incidentSteps.setPlanContent(sb.toString());
		}
		incidentSteps.setStepName(dealTeam.getTeamName());
		incidentSteps.setSeqIndex(Integer.valueOf(dealTeam.getId().toString()));
		return incidentSteps;
	}

	private IncidentSteps setIncidentStepsByIncidentPlanStep(IncidentPlanStep planStep,
			Incidents incidents, int stepNum) {
		IncidentSteps incidentSteps = new IncidentSteps();
		incidentSteps.setIncidents(incidents);
		incidentSteps.setStepType(IncidentConstants.PLANSTEP_STEPTYPE);
		incidentSteps.setSeqIndex(planStep.getSeqIndex());
		incidentSteps.setPlanContent(planStep.getContent());
		incidentSteps.setStepName("步骤" + stepNum);
		return incidentSteps;
	}

	@Override
	public void updateIncidentsCaseLibrary(Incidents incidents) {
		incidentDao.updateIncidentsCaseLibrary(incidents);
	}

	@Override
	public void addIncidentsteps(List<IncidentSteps> incidentStepsList) {
		if (incidentStepsList != null && incidentStepsList.size() > 0) {
			for (IncidentSteps incidentSteps : incidentStepsList) {
				incidentDao.addIncidentsteps(incidentSteps);
			}
		}
	}

	@Override
	public List<IncidentSteps> findIncidentSteps(IncidentSteps incidentSteps) {
		return incidentDao.findIncidentSteps(incidentSteps);
	}

	@Override
	public IncidentSteps findIncidentStep(IncidentSteps incidentSteps) {
		IncidentSteps incidentStep = incidentDao.findIncidentStep(incidentSteps);
		incidentStep.setIncidentStepFeedBackList(incidentDao
				.findIncidentStepFeedbackList(incidentSteps.getId()));
		return incidentStep;
	}

	@Override
	public void updateIncidentsteps(IncidentSteps incidentSteps) {
		incidentDao.updateIncidentsteps(incidentSteps);
	}

	@Override
	public void addIncidentsteps(IncidentSteps incidentSteps) {
		incidentDao.addIncidentsteps(incidentSteps);
	}

	@Override
	public void addIncidentStepFeedback(IncidentStepFeedbacks incidentStepFeedback) {
		incidentDao.addIncidentStepFeedback(incidentStepFeedback);
	}

	@Override
	public List<Incidents> loadCaseLibraryList(Incidents incidents) {
		return incidentDao.loadCaseLibraryList(incidents);
	}

	@Override
	public Incidents loadCaseLibrary(Long id) {
		return incidentDao.loadCaseLibrary(id);
	}

	@Override
	public List<IncidentSteps> loadloadIncidentSteps(Long id) {
		return incidentDao.loadloadIncidentSteps(id);
	}

	@Override
	public List<IncidentType> findPlansSubdivisionBySubjection(Long id) {
		return incidentDao.findPlansSubdivisionBySubjection(id);
	}

	@Override
	public Incidents addEarlyWarningIncidents(Incidents incidents) {
		// TODO后台数据校验
		Incidents earlyWarningIncidents = incidentDao.addEarlyWarningIncidents(incidents);

		if (earlyWarningIncidents.getDegree() != null) {
			earlyWarningIncidents.setDegree(propertyDictService.getPropertyDictById(incidents
					.getDegree().getId()));
		}
		return earlyWarningIncidents;
	}

	@Override
	public Incidents updateEarlyWarningIncidents(Incidents incidents) {
		// TODO后台数据校验
		Incidents earlyWarningIncidents = incidentDao.updateEarlyWarningIncidents(incidents);

		if (earlyWarningIncidents.getDegree() != null) {
			earlyWarningIncidents.setDegree(propertyDictService.getPropertyDictById(incidents
					.getDegree().getId()));
		}
		return earlyWarningIncidents;
	}
}
