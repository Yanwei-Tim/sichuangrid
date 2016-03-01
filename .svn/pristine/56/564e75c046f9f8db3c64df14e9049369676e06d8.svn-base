package com.tianque.plugin.judgmentAnalysis.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 
 * @author lu
 *
 */
public class ScheduleJob extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1038379454601339538L;

	private String name;

	private String cronExpression;

	private Integer runType;

	private String currentCycle;

	private String beanName;

	private BusinessModel businessModel;

	private DimensionCombination dimensionCombination;

	private Boolean enable;

	private Date nextStartTime;

	private Integer prefixZero = 1;

	private List<ScheduleJobInfo> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public List<ScheduleJobInfo> getItems() {
		return items;
	}

	public void setItems(List<ScheduleJobInfo> items) {
		this.items = items;
	}

	public Integer getRunType() {
		return runType;
	}

	public void setRunType(Integer runType) {
		this.runType = runType;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public BusinessModel getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(BusinessModel businessModel) {
		this.businessModel = businessModel;
	}

	public DimensionCombination getDimensionCombination() {
		return dimensionCombination;
	}

	public void setDimensionCombination(
			DimensionCombination dimensionCombination) {
		this.dimensionCombination = dimensionCombination;
	}

	public String getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(String currentCycle) {
		this.currentCycle = currentCycle;
	}

	public Integer getPrefixZero() {
		return prefixZero;
	}

	public void setPrefixZero(Integer prefixZero) {
		this.prefixZero = prefixZero;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getNextStartTime() {
		return nextStartTime;
	}

	public void setNextStartTime(Date nextStartTime) {
		this.nextStartTime = nextStartTime;
	}
}
