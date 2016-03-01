package com.tianque.domain.vo;

public class ExamineItemVo {
	private Long id;
	private String catalogIndex; // 大则序号
	private String catalogShortName; // 大则内容
	private String examineIndex; // 小则序号
	private String examineContent; // 小则内容
	private String planScore; // 基本分值
	private String examineOrganizationNames; // 参与单位
	private String remark; // 备注

	private boolean isRowspanCatalog; // 是否 需要合并大则的单元格
	private int rowspanCatalogCount; // 大则单元格 合并 行数
	private boolean isRowspanItem; // 是否需要合并小则的单元格
	private int rowspanItemCount; // 小则单元格 合并 行数
	private boolean isMiddleItem; // 是否中则
	private Boolean selected; // 是否选中
	// 是否可以被修盖
	private boolean editabled;

	public String getCatalogIndex() {
		return catalogIndex;
	}

	public void setCatalogIndex(String catalogIndex) {
		this.catalogIndex = catalogIndex;
	}

	public String getCatalogShortName() {
		return catalogShortName;
	}

	public void setCatalogShortName(String catalogShortName) {
		this.catalogShortName = catalogShortName;
	}

	public String getExamineIndex() {
		return examineIndex;
	}

	public void setExamineIndex(String examineIndex) {
		this.examineIndex = examineIndex;
	}

	public String getExamineContent() {
		return examineContent;
	}

	public void setExamineContent(String examineContent) {
		this.examineContent = examineContent;
	}

	public String getPlanScore() {
		return planScore;
	}

	public void setPlanScore(String planScore) {
		this.planScore = planScore;
	}

	public String getExamineOrganizationNames() {
		return examineOrganizationNames;
	}

	public void setExamineOrganizationNames(String examineOrganizationNames) {
		this.examineOrganizationNames = examineOrganizationNames;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getRowspanCatalogCount() {
		return rowspanCatalogCount;
	}

	public void setRowspanCatalogCount(int rowspanCatalog) {
		this.rowspanCatalogCount = rowspanCatalog;
	}

	public int getRowspanItemCount() {
		return rowspanItemCount;
	}

	public void setRowspanItemCount(int rowspanItem) {
		this.rowspanItemCount = rowspanItem;
	}

	public boolean isRowspanCatalog() {
		return isRowspanCatalog;
	}

	public void setRowspanCatalog(boolean isRowspanCatalog) {
		this.isRowspanCatalog = isRowspanCatalog;
	}

	public boolean isRowspanItem() {
		return isRowspanItem;
	}

	public void setRowspanItem(boolean isRowspanItem) {
		this.isRowspanItem = isRowspanItem;
	}

	public boolean isMiddleItem() {
		return isMiddleItem;
	}

	public void setMiddleItem(boolean isMiddleItem) {
		this.isMiddleItem = isMiddleItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public boolean isEditabled() {
		return editabled;
	}

	public void setEditabled(boolean editabled) {
		this.editabled = editabled;
	}

}
