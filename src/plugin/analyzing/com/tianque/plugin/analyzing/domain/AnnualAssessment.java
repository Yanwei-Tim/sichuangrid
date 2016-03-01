package com.tianque.plugin.analyzing.domain;

import com.tianque.domain.Organization;

public class AnnualAssessment {

	private Organization organization;
	private float governmentLeading;// 党政主导
	private float funding;// 经费保障情况
	private float implementGrid;// 落实网格员情况
	private float mechanismConstruction;// 机制建设
	private float gridMap;// 网格地图
	private float workInnovation;// 工作创新

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public float getGovernmentLeading() {
		return governmentLeading;
	}

	public void setGovernmentLeading(float governmentLeading) {
		this.governmentLeading = governmentLeading;
	}

	public float getFunding() {
		return funding;
	}

	public void setFunding(float funding) {
		this.funding = funding;
	}

	public float getImplementGrid() {
		return implementGrid;
	}

	public void setImplementGrid(float implementGrid) {
		this.implementGrid = implementGrid;
	}

	public float getMechanismConstruction() {
		return mechanismConstruction;
	}

	public void setMechanismConstruction(float mechanismConstruction) {
		this.mechanismConstruction = mechanismConstruction;
	}

	public float getGridMap() {
		return gridMap;
	}

	public void setGridMap(float gridMap) {
		this.gridMap = gridMap;
	}

	public float getWorkInnovation() {
		return workInnovation;
	}

	public void setWorkInnovation(float workInnovation) {
		this.workInnovation = workInnovation;
	}

}
