package com.tianque.incident.service;

import java.util.List;

import com.tianque.incident.domain.IncidentType;
import com.tianque.incident.domain.component.IncidentDegreeRule;

public interface IncidentSystemManagerService {

	public IncidentType getIncidentTypeById(Long id);

	public List<IncidentType> getIncidentsBysubjectionTypeId(Long subjectionTypeId);

	public IncidentType addIncidentType(IncidentType incidentType);

	public IncidentDegreeRule addIncidentDegreeRule(IncidentDegreeRule incidentDegreeRule);

	public IncidentType updateIncidentType(IncidentType incidentType);

	public IncidentDegreeRule updateIncidentDegreeRule(IncidentDegreeRule incidentDegreeRule);

	public void deleteIncidentType(Long subjectTypeId);

	public boolean hasDuplicateIncidentType(Long subjectTypeId, String name, Long exceptedId);

}
