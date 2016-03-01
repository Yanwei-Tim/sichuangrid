package com.tianque.report.vo;

import java.util.ArrayList;
import java.util.List;

public class IssueInvestigationMediateVo {
	private String typeDisplayName;
	private Long typeId;
	private List<IssueInvestigationMediateItemVo> items;

	public String getTypeDisplayName() {
		return typeDisplayName;
	}

	public void setTypeDisplayName(String typeDisplayName) {
		this.typeDisplayName = typeDisplayName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<IssueInvestigationMediateItemVo> getItems() {
		if (items == null) {
			items = new ArrayList<IssueInvestigationMediateItemVo>();
		}
		return items;
	}

	public void setItems(List<IssueInvestigationMediateItemVo> items) {
		this.items = items;
	}

	public void additem(IssueInvestigationMediateItemVo item) {
		getItems().add(item);
	}

	public boolean isPersonCountData() {
		return false;
	}

}
