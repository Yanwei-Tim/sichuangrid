package com.tianque.plugin.judgmentAnalysis.vo;

import java.io.Serializable;


/**
 * 历史统计：同比,环比
 * @author n-233
 *
 */
public class HistoryCycleSummaryVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long orgId;
	private String orgName;
	private Long first_amount=0l;  
	private Long second_amount=0l; 
	//增长率
	private Float growth_rate=0f;
	
	
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
 
	public Float getGrowth_rate() {
		return growth_rate;
	}
	public void setGrowth_rate(Float growthRate) {
		growth_rate = growthRate;
	}
	public Long getFirst_amount() {
		return first_amount;
	}
	public void setFirst_amount(Long firstAmount) {
		first_amount = firstAmount;
	}
	public Long getSecond_amount() {
		return second_amount;
	}
	public void setSecond_amount(Long secondAmount) {
		second_amount = secondAmount;
	}
	
	
	
	
	
	
	
	
}
