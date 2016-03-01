package com.tianque.statRegrad.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.tianque.statRegrad.constant.IssueGradeUtils;

/**
 * 
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年11月19日
 */
public class IssueGradeNode {

	private Long orgId;
	private Long parentId;
	private Long orgtype;
	// 事件接件数量
	private Long issueSum;
	// 事件处理数量
	private Long dealIssueSum;

	private Set<Long> issueSumKinds;

	private List<Long>[] sumKind;
	// 行政部门
	private List<IssueGradeNode> adminChildren = new ArrayList<IssueGradeNode>();
	// 职能部门
	private List<IssueGradeNode> funChildren = new ArrayList<IssueGradeNode>();

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(Long orgtype) {
		this.orgtype = orgtype;
	}

	public Long getIssueSum() {
		return issueSum;
	}

	public void setIssueSum(Long issueSum) {
		this.issueSum = issueSum;
	}

	public Long getDealIssueSum() {
		return dealIssueSum;
	}

	public void setDealIssueSum(Long dealIssueSum) {
		this.dealIssueSum = dealIssueSum;
	}

	public Set<Long> getIssueSumKinds() {
		return issueSumKinds;
	}

	public void setIssueSumKinds(Set<Long> issueSumKinds) {
		this.issueSumKinds = issueSumKinds;
	}

	public void addAdminChild(IssueGradeNode child) {
		adminChildren.add(child);
	}

	public void addFunChild(IssueGradeNode child) {
		funChildren.add(child);
	}

	public List<IssueGradeNode> getAdminChildren() {
		return adminChildren;
	}

	public List<IssueGradeNode> getFunChildren() {
		return funChildren;
	}

	public List<Long>[] getSumKind() {
		return sumKind;
	}

	public void setSumKind(List<Long>[] sumKind) {
		this.sumKind = sumKind;
	}

	public IssueGradeNode findByOrgId(long orgId) {
		if (this.orgId == orgId) {
			return this;
		}
		List<IssueGradeNode> children = new ArrayList<IssueGradeNode>();
		children.addAll(adminChildren);
		children.addAll(funChildren);
		for (IssueGradeNode issueGradeNode : children) {
			return issueGradeNode.findByOrgId(orgId);
		}
		return null;
	}

	public void initIssueSumKinds() {
		if (!adminChildren.isEmpty()) {
			Set<Long> set = new TreeSet<Long>(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					if (o1 > o2) {
						return -1;
					} else if (o1.equals(o2)) {
						return 0;
					} else {
						return 1;
					}
				}
			});
			List<Long>[] sumKind = new List[3];
			for (IssueGradeNode issueGradeNode : adminChildren) {
				set.add(issueGradeNode.getIssueSum());
				issueGradeNode.setSumKind(sumKind);
				issueGradeNode.initIssueSumKinds();
			}
			IssueGradeUtils.allocationDegree(set, sumKind);
		}
		if (!funChildren.isEmpty()) {
			Set<Long> set = new TreeSet<Long>();
			List<Long>[] sumKind = new List[3];
			for (IssueGradeNode issueGradeNode : funChildren) {
				set.add(issueGradeNode.getIssueSum());
				issueGradeNode.setIssueSumKinds(set);
				issueGradeNode.setSumKind(sumKind);
				issueGradeNode.initIssueSumKinds();
			}
			IssueGradeUtils.allocationDegree(set, sumKind);
		}
	}

}
