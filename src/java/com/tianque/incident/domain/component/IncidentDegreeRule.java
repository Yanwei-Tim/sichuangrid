package com.tianque.incident.domain.component;

import java.io.Serializable;

import com.tianque.domain.PropertyDict;

public class IncidentDegreeRule implements Serializable {

	private String rule;

	private PropertyDict degree;

	private long incidentTypeId;

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public PropertyDict getDegree() {
		return degree;
	}

	public void setDegree(PropertyDict degree) {
		this.degree = degree;
	}

	public long getIncidentTypeId() {
		return incidentTypeId;
	}

	public void setIncidentTypeId(long incidentTypeId) {
		this.incidentTypeId = incidentTypeId;
	}

	@Override
	public int hashCode() {
		int result = getClass().hashCode();
		final int prime = 31;
		if (degree != null) {
			result = prime * result + degree.hashCode();
		}
		if (rule != null) {
			result = prime * result + rule.hashCode();
		}
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !obj.getClass().equals(getClass())) {
			return false;
		}

		IncidentDegreeRule other = (IncidentDegreeRule) obj;
		if (getDegree() != null) {
			if (other.getDegree() == null || !getDegree().equals(other.getDegree())) {
				return false;
			}
		} else {
			if (other.getDegree() != null) {
				return false;
			}
		}

		if (getRule() != null) {
			if (other.getRule() == null || !getRule().equals(other.getRule())) {
				return false;
			}
		} else {
			if (other.getRule() != null) {
				return false;
			}
		}
		return true;
	}

}
