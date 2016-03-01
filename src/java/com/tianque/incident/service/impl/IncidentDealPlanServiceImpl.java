package com.tianque.incident.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.incident.dao.IncidentDealPlanDao;
import com.tianque.incident.domain.IncidentDealPlan;
import com.tianque.incident.domain.IncidentDealTeam;
import com.tianque.incident.domain.IncidentPlanStep;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.service.IncidentDealPlanService;

@Service("incidentDealPlanService")
public class IncidentDealPlanServiceImpl implements IncidentDealPlanService {
	@Autowired
	private IncidentDealPlanDao incidentDealPlanDao;

	@Override
	public IncidentDealPlan findIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		return incidentDealPlanDao.findIncidentDealPlan(incidentDealPlan);
	}

	@Override
	public PageInfo<IncidentDealTeam> findIncidentDealTeamsList(IncidentDealTeam incidentDealTeam,
			Integer pageNum, Integer pageSize) {
		return incidentDealPlanDao.findIncidentDealTeamsList(incidentDealTeam, pageNum, pageSize);
	}

	@Override
	public PageInfo<IncidentPlanStep> findIncidentPlanSteps(IncidentPlanStep incidentPlanStep,
			Integer pageNum, Integer pageSize) {
		return incidentDealPlanDao.findIncidentPlanSteps(incidentPlanStep, pageNum, pageSize);
	}

	@Override
	public List<IncidentType> getIncidentTypeTreeListByPropertyDictId(Long propertyDictId) {
		return incidentDealPlanDao.getIncidentTypeTreeListByPropertyDictId(propertyDictId);
	}

	@Override
	public void addIncidentDealTeam(IncidentDealTeam incidentDealTeam) {
		incidentDealPlanDao.addIncidentDealTeam(incidentDealTeam);
	}

	@Override
	public IncidentDealTeam updateIncidentDealTeam(IncidentDealTeam incidentDealTeam) {
		return incidentDealPlanDao.updateIncidentDealTeam(incidentDealTeam);
	}

	@Override
	public IncidentDealTeam findIncidentDealTeams(IncidentDealTeam incidentDealTeam) {
		return incidentDealPlanDao.findIncidentDealTeams(incidentDealTeam);
	}

	@Override
	public void deleteIncidentDealTeams(Map<String, Object> idtMap) {
		incidentDealPlanDao.deleteIncidentDealTeams(idtMap);
	}

	@Override
	public IncidentPlanStep findMaxSeqIndexIncidentPlanStep(IncidentPlanStep incidentPlanStep) {
		return incidentDealPlanDao.findMaxSeqIndexIncidentPlanStep(incidentPlanStep);
	}

	@Override
	public IncidentPlanStep findIncidentPlanStep(IncidentPlanStep incidentPlanStep) {
		return incidentDealPlanDao.findIncidentPlanStep(incidentPlanStep);
	}

	@Override
	public IncidentPlanStep updateIncidentPlanStep(IncidentPlanStep incidentPlanStep) {
		return incidentDealPlanDao.updateIncidentPlanStep(incidentPlanStep);
	}

	@Override
	public void addIncidentPlanSteps(IncidentPlanStep incidentPlanStep) {
		incidentDealPlanDao.addIncidentPlanSteps(incidentPlanStep);
	}

	@Override
	public void deleteIncidentPlanStep(Map<String, Object> idtMap) {
		incidentDealPlanDao.deleteIncidentPlanStep(idtMap);
	}

	@Override
	public IncidentDealPlan updateIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		return incidentDealPlanDao.updateIncidentDealPlan(incidentDealPlan);
	}

	@Override
	public void addIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		incidentDealPlanDao.addIncidentDealPlan(incidentDealPlan);
	}

	@Override
	public void deleteIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		incidentDealPlanDao.deleteIncidentDealPlan(incidentDealPlan);
	}

	@Override
	public void updateIncidentPlanStepSeqIndex(String seqIndexIdListStr) {
		incidentDealPlanDao.updateIncidentPlanStepSeqIndex(seqIndexIdListStr);
	}

	@Override
	public List<IncidentDealTeam> findIncidentDealTeamsListByIncidentDealPlanId(
			IncidentDealTeam incidentDealTeam) {

		return incidentDealPlanDao.findIncidentDealTeamsListByIncidentDealPlanId(incidentDealTeam);
	}

	@Override
	public List<IncidentPlanStep> findIncidentPlanStepsByIncidentDealPlanId(
			IncidentPlanStep incidentPlanStep) {
		return incidentDealPlanDao.findIncidentPlanStepsByIncidentDealPlanId(incidentPlanStep);
	}

	@Override
	public IncidentDealPlan updateIncidentDealPlanTow(IncidentDealPlan incidentDealPlan) {
		return incidentDealPlanDao.updateIncidentDealPlanTow(incidentDealPlan);
	}

	@Override
	public void copyIncidentDealPlanData(IncidentDealPlan incidentDealPlan,
			IncidentDealPlan incidentDealPlanOwn) {
		incidentDealPlan = incidentDealPlanDao.findIncidentDealPlan(incidentDealPlan);
		IncidentDealTeam incidentDealTeam = new IncidentDealTeam();
		incidentDealTeam.setIncidentDealPlan(incidentDealPlan);
		List<IncidentDealTeam> idealTeamList = incidentDealPlanDao
				.findIncidentDealTeamsListByIncidentDealPlanId(incidentDealTeam);
		IncidentPlanStep incidentPlanStep = new IncidentPlanStep();
		incidentPlanStep.setIncidentDealPlan(incidentDealPlan);
		List<IncidentPlanStep> iplanStepList = incidentDealPlanDao
				.findIncidentPlanStepsByIncidentDealPlanId(incidentPlanStep);
		incidentDealPlan.setIncidentType(incidentDealPlanOwn.getIncidentType());
		incidentDealPlan.setDegree(incidentDealPlanOwn.getDegree());
		incidentDealPlanDao.addIncidentDealPlan(incidentDealPlan);
		for (IncidentDealTeam idealTeam : idealTeamList) {
			idealTeam.setIncidentDealPlan(incidentDealPlan);
			incidentDealPlanDao.addIncidentDealTeam(idealTeam);
		}

		for (IncidentPlanStep planStep : iplanStepList) {
			planStep.setIncidentDealPlan(incidentDealPlan);
			incidentDealPlanDao.addIncidentPlanSteps(planStep);
		}

	}

}
