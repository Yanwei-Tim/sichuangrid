package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class ExamineItem extends BaseDomain {
	private static final long serialVersionUID = 1L;

	// 年度计划ID
	private ExaminePlan plan;

	// 大则ID
	private ExamineCatalog catalog;

	// 小则序号
	private int index;

	// 评分基数，-1为扣分，1为加分
	private int examineType;

	// 上一级小则标题
	private ExamineItem ownerItem;

	// 显示的大则名称
	private String catalogDisplayName;

	// 小则内容
	private String examineContent;

	// 手动自动
	private boolean autoExamine;

	// 扣分规则
	private String scoreWayDetail;

	// 计划分值
	private double planScore;

	// 实际分值
	private double actualCaculateScore;

	// 是否可以被修盖
	private boolean editabled;

	// 是否选择
	private Boolean selected;
	// 引用的细类
	private ExamineItem refItem;

	// 考核部门
	private String examineOrganizationNames;

	// 备注
	private String remark;

	public ExaminePlan getPlan() {
		return plan;
	}

	public void setPlan(ExaminePlan plan) {
		this.plan = plan;
	}

	public ExamineCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ExamineCatalog catalog) {
		this.catalog = catalog;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getExamineType() {
		return examineType;
	}

	public void setExamineType(int examineType) {
		this.examineType = examineType;
	}

	public ExamineItem getOwnerItem() {
		return ownerItem;
	}

	public void setOwnerItem(ExamineItem ownerItem) {
		this.ownerItem = ownerItem;
	}

	public String getCatalogDisplayName() {
		return catalogDisplayName;
	}

	public void setCatalogDisplayName(String catalogDisplayName) {
		this.catalogDisplayName = catalogDisplayName;
	}

	public String getExamineContent() {
		return examineContent;
	}

	public void setExamineContent(String examineContent) {
		this.examineContent = examineContent;
	}

	public boolean isAutoExamine() {
		return autoExamine;
	}

	public void setAutoExamine(boolean autoExamine) {
		this.autoExamine = autoExamine;
	}

	public String getScoreWayDetail() {
		return scoreWayDetail;
	}

	public void setScoreWayDetail(String scoreWayDetail) {
		this.scoreWayDetail = scoreWayDetail;
	}

	public double getPlanScore() {
		return planScore;
	}

	public void setPlanScore(double planScore) {
		this.planScore = planScore;
	}

	public double getActualCaculateScore() {
		return actualCaculateScore;
	}

	public void setActualCaculateScore(double actualCaculateScore) {
		this.actualCaculateScore = actualCaculateScore;
	}

	public boolean isEditabled() {
		return editabled;
	}

	public void setEditabled(boolean editabled) {
		this.editabled = editabled;
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

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public ExamineItem getRefItem() {
		return refItem;
	}

	public void setRefItem(ExamineItem refItem) {
		this.refItem = refItem;
	}

}
