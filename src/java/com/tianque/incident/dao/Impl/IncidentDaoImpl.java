package com.tianque.incident.dao.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.incident.dao.IncidentDao;
import com.tianque.incident.domain.IncidentStepFeedbacks;
import com.tianque.incident.domain.IncidentSteps;
import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.Incidents;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("incidentDao")
public class IncidentDaoImpl extends AbstractBaseDao implements IncidentDao {

	@Autowired
	PropertyDictService propertyDictService;

	@Override
	public List<IncidentSteps> findIncidentSteps(IncidentSteps incidentSteps) {
		return (List<IncidentSteps>) getSqlMapClientTemplate().queryForList(
				"incidentsSpace.findIncidentSteps", incidentSteps);
	}

	@Override
	public List<Incidents> findIncidentsList(Incidents incidents) {
		List<Incidents> incidentList = (List<Incidents>) getSqlMapClientTemplate()
				.queryForList("incidentsSpace.findIncidentsList", incidents);
		List<Incidents> sortIncidentList = new ArrayList<Incidents>(16);
		sortAllIncidentsList(sortIncidentList, incidentList);
		return sortIncidentList;
	}

	private void sortAllIncidentsList(List<Incidents> sortIncidentList,
			List<Incidents> incidentList) {
		List<Incidents> addSortIncidentList = new ArrayList<Incidents>(16);
		if (incidentList != null && incidentList.size() > 0) {
			for (Incidents incident : incidentList) {
				if (incident.getDegree() != null) {
					incident.setDegree(propertyDictService
							.getPropertyDictById(incident.getDegree().getId()));
					sortIncidentList.add(incident);
				} else {
					addSortIncidentList.add(incident);
				}
			}
			Collections.sort(sortIncidentList, new Comparator() {
				public int compare(Object o1, Object o2) {
					Incidents inci1 = (Incidents) o1;
					Incidents inci2 = (Incidents) o2;
					return inci1.getDegree().getInternalId()
							- inci2.getDegree().getInternalId();
				}
			});

			sortIncidentList.addAll(addSortIncidentList);
		}
	}

	@Override
	public Incidents findIncidents(Incidents incidents) {
		return (Incidents) getSqlMapClientTemplate().queryForObject(
				"incidentsSpace.findIncidentsList", incidents);
	}

	@Override
	public void updateIncidentsCaseLibrary(Incidents incidents) {
		getSqlMapClientTemplate().update(
				"incidentsSpace.updateIncidentsCaseLibrary", incidents);
	}

	@Override
	public void updateIncidentsHandle(Incidents incidents) {
		getSqlMapClientTemplate().update(
				"incidentsSpace.updateIncidentsHandle", incidents);
	}

	@Override
	public void updateIncidentsteps(IncidentSteps incidentSteps) {
		getSqlMapClientTemplate().update("incidentsSpace.updateIncidentsteps",
				incidentSteps);
	}

	@Override
	public void addIncidentsteps(IncidentSteps incidentSteps) {
		getSqlMapClientTemplate().insert("incidentsSpace.addIncidentsteps",
				incidentSteps);
	}

	@Override
	public IncidentSteps findIncidentStep(IncidentSteps incidentSteps) {
		return (IncidentSteps) getSqlMapClientTemplate().queryForObject(
				"incidentsSpace.findIncidentSteps", incidentSteps);
	}

	@Override
	public void addIncidentStepFeedback(
			IncidentStepFeedbacks incidentStepFeedback) {
		getSqlMapClientTemplate()
				.insert("incidentsSpace.addIncidentStepFeedbacks",
						incidentStepFeedback);
	}

	@Override
	public List<IncidentStepFeedbacks> findIncidentStepFeedbackList(
			Long incidentStepId) {

		return (List<IncidentStepFeedbacks>) getSqlMapClientTemplate()
				.queryForList("incidentsSpace.findIncidentStepFeedbackList",
						incidentStepId);
	}

	@Override
	public List<Incidents> loadCaseLibraryList(Incidents incidents) {
		return getSqlMapClientTemplate().queryForList(
				"incidentsSpace.loadCaseLibraryList", incidents);
	}

	@Override
	public Incidents loadCaseLibrary(Long id) {
		return (Incidents) getSqlMapClientTemplate().queryForObject(
				"incidentsSpace.loadCaseLibrary", id);
	}

	@Override
	public List<IncidentSteps> loadloadIncidentSteps(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"incidentsSpace.loadloadIncidentSteps", id);
	}

	@Override
	public List<IncidentType> findPlansSubdivisionBySubjection(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"incidentsSpace.findPlansSubdivisionBySubjection", id);
	}

	@Override
	public Incidents addEarlyWarningIncidents(Incidents incidents) {
		getSqlMapClientTemplate().insert("incidentsSpace.addIncidents",
				incidents);
		return findIncidents(incidents);
	}

	@Override
	public Incidents updateEarlyWarningIncidents(Incidents incidents) {
		getSqlMapClientTemplate().update(
				"incidentsSpace.updateEarlyWarningIncidents", incidents);
		return findIncidents(incidents);
	}
}
