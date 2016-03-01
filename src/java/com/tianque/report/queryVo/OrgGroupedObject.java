package com.tianque.report.queryVo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrgGroupedObject implements Serializable {
	private Long orgId;

	private Long subgroupid1;

	private Long subgroupid2;

	private Long countresult1;

	private Long countresult2;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getSubgroupid1() {
		return subgroupid1;
	}

	public void setSubgroupid1(Long subgroupid1) {
		this.subgroupid1 = subgroupid1;
	}

	public Long getSubgroupid2() {
		return subgroupid2;
	}

	public void setSubgroupid2(Long subgroupid2) {
		this.subgroupid2 = subgroupid2;
	}

	public Long getCountresult1() {
		return countresult1;
	}

	public void setCountresult1(Long countresult1) {
		this.countresult1 = countresult1;
	}

	public Long getCountresult2() {
		return countresult2;
	}

	public void setCountresult2(Long countresult2) {
		this.countresult2 = countresult2;
	}

}
