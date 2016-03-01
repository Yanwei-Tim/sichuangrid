package com.tianque.fourTeams.fourTeamsIssue.domain.vo;

import java.util.Map;

public class FourTeamsIssueTypesVo {

	private Long id;
	private String name;
	private Map childMap;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map getChildMap() {
		return childMap;
	}

	public void setChildMap(Map childMap) {
		this.childMap = childMap;
	}

}
