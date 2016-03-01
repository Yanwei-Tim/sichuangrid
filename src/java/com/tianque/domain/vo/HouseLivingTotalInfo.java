package com.tianque.domain.vo;

import java.io.Serializable;

import com.tianque.core.util.StringUtil;
import com.tianque.service.util.PopulationCatalog;

public class HouseLivingTotalInfo implements Serializable {
	private PopulationCatalog type;

	private String typeParantCatalogName;

	private String typeCatalogName;

	private int populationCount;

	private String orgCode;

	private String orgName;

	public PopulationCatalog getType() {
		if (type == null && StringUtil.isStringAvaliable(typeCatalogName)) {
			type = PopulationCatalog.parse(typeCatalogName);
		}
		return type;
	}

	public void setType(PopulationCatalog type) {
		this.type = type;
	}

	public int getPopulationCount() {
		return populationCount;
	}

	public void setPopulationCount(int populationCount) {
		this.populationCount = populationCount;
	}

	public String getTypeParantCatalogName() {
		return typeParantCatalogName;
	}

	public void setTypeParantCatalogName(String typeParantCatalogName) {
		this.typeParantCatalogName = typeParantCatalogName;
	}

	public String getTypeCatalogName() {
		return typeCatalogName;
	}

	public void setTypeCatalogName(String typeCatalogName) {
		this.typeCatalogName = typeCatalogName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
