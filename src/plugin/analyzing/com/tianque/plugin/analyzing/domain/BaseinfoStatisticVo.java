package com.tianque.plugin.analyzing.domain;

import java.io.Serializable;
import java.util.List;

import com.tianque.domain.Organization;

/**
 * 统计的汇总信息
 * 
 * @author
 */
public class BaseinfoStatisticVo implements Serializable{

	private Organization org;
	private long orgId;
	private String orgName;
	private long amount; // 总数
	private List<BaseinfoStatisticDetailVo> baseinfoStatisticDetailVo;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public List<BaseinfoStatisticDetailVo> getBaseinfoStatisticDetailVo() {
		return baseinfoStatisticDetailVo;
	}

	public void setBaseinfoStatisticDetailVo(
			List<BaseinfoStatisticDetailVo> baseinfoStatisticDetailVo) {
		this.baseinfoStatisticDetailVo = baseinfoStatisticDetailVo;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
