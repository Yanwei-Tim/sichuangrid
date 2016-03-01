package com.tianque.plugin.orgchange.domain;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.tianque.core.base.BaseDomain;

/**
 * 组织机构变更信息
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
public class OrgChangeInfo extends BaseDomain {

	private Long sourceOrgId;

	private String sourceOrgName;

	private Long sourceParentid;

	private String departmentNo;

	private Long targetorgId;

	private String targetorgCode;

	private String targetOrgName;

	private int operateType;

	private Boolean isDeal;

	private boolean isError;

	private Throwable throwable;

	private OrgChangeLog log;

	private ProgressInfo progressInfo;

	private TransactionStatus transactionStatus;

	private PlatformTransactionManager jtaTransactionManager;

	private List<OrgMapping> orgMappingList;

	public Long getSourceOrgId() {
		return sourceOrgId;
	}

	public void setSourceOrgId(Long sourceOrgId) {
		this.sourceOrgId = sourceOrgId;
	}

	public String getSourceOrgName() {
		return sourceOrgName;
	}

	public void setSourceOrgName(String sourceOrgName) {
		this.sourceOrgName = sourceOrgName;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Long getTargetorgId() {
		return targetorgId;
	}

	public void setTargetorgId(Long targetorgId) {
		this.targetorgId = targetorgId;
	}

	public String getTargetorgCode() {
		return targetorgCode;
	}

	public void setTargetorgCode(String targetorgCode) {
		this.targetorgCode = targetorgCode;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public Boolean getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Boolean isDeal) {
		this.isDeal = isDeal;
	}

	public List<OrgMapping> getOrgMappingList() {
		return orgMappingList;
	}

	public void setOrgMappingList(List<OrgMapping> orgMappingList) {
		this.orgMappingList = orgMappingList;
	}

	public String getTargetOrgName() {
		return targetOrgName;
	}

	public void setTargetOrgName(String targetOrgName) {
		this.targetOrgName = targetOrgName;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public OrgChangeLog getLog() {
		return log;
	}

	public void setLog(OrgChangeLog log) {
		this.log = log;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public void setJtaTransactionManager(
			PlatformTransactionManager jtaTransactionManager) {
		this.jtaTransactionManager = jtaTransactionManager;
	}

	public void commit() {
		if (transactionStatus.isNewTransaction())
			jtaTransactionManager.commit(transactionStatus);
	}

	public void rollback() {
		if (transactionStatus.isNewTransaction())
			jtaTransactionManager.rollback(transactionStatus);
	}

	@Override
	public String toString() {
		return "OrgChangeInfo [sourceOrgId=" + sourceOrgId + ", sourceOrgName="
				+ sourceOrgName + ", departmentNo=" + departmentNo
				+ ", targetorgId=" + targetorgId + ", targetorgCode="
				+ targetorgCode + ", targetOrgName=" + targetOrgName
				+ ", operateType=" + operateType + ", isDeal=" + isDeal
				+ ", orgMappingList=" + orgMappingList + ", getId()=" + getId()
				+ "]";
	}

	public ProgressInfo getProgressInfo() {
		return progressInfo;
	}

	public void setProgressInfo(ProgressInfo progressInfo) {
		this.progressInfo = progressInfo;
	}

	public Long getSourceParentid() {
		return sourceParentid;
	}

	public void setSourceParentid(Long sourceParentid) {
		this.sourceParentid = sourceParentid;
	}

}
