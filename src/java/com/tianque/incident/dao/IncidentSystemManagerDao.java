package com.tianque.incident.dao;

import java.util.List;

import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.component.IncidentDegreeRule;

public interface IncidentSystemManagerDao {

	public List<IncidentType> getIncidentsBysubjectionTypeId(Long subjectionTypeId);

	public IncidentType getIncidentTypeById(Long id);

	public IncidentType addIncidentType(IncidentType incidentType);

	public IncidentDegreeRule addIncidentDegreeRule(IncidentDegreeRule incidentDegreeRule);

	public IncidentType updateIncidentType(IncidentType incidentType);

	public void deleteIncidentDegreeRuleByIncidentTypeId(long incidentTypeId);

	public void deleteIncidentTypeById(Long subjectTypeId);

	public IncidentType getIncidentTypeByNameAndSubjectTypeId(Long subjectTypeId, String name);

}
