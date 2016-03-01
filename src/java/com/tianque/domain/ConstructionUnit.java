package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 建筑施工单位实体类
 * 
 * @author hjw
 */
@SuppressWarnings("serial")
public class ConstructionUnit extends BaseDomain {

	private Organization organization;
	private String orgInternalCode;
	private String projectName;
	private String fullPinyin;
	private String simplePinyin;
	private String projectAddress;
	private String developmentUnit;
	private String developmentContactPerson;
	private String developmentContactPersonPhone;
	private String contractor;
	private String contractorContactPerson;
	private String contractorContactPersonPhone;
	private String laborer;
	private String laborerContactPerson;
	private String laborerContactPersonPhone;
	private Date salaryPayDate;
	private Double actualTotalSalary;
	private Integer shouldSignContractNumber;
	private Integer notSignContractNumber;
	private Integer workersNumber;
	private Date startTime;
	private PropertyDict type;
	private String remark;
	private Long isEmphasis;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getDevelopmentUnit() {
		return developmentUnit;
	}

	public void setDevelopmentUnit(String developmentUnit) {
		this.developmentUnit = developmentUnit;
	}

	public String getDevelopmentContactPerson() {
		return developmentContactPerson;
	}

	public void setDevelopmentContactPerson(String developmentContactPerson) {
		this.developmentContactPerson = developmentContactPerson;
	}

	public String getDevelopmentContactPersonPhone() {
		return developmentContactPersonPhone;
	}

	public void setDevelopmentContactPersonPhone(String developmentContactPersonPhone) {
		this.developmentContactPersonPhone = developmentContactPersonPhone;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public String getContractorContactPerson() {
		return contractorContactPerson;
	}

	public void setContractorContactPerson(String contractorContactPerson) {
		this.contractorContactPerson = contractorContactPerson;
	}

	public String getContractorContactPersonPhone() {
		return contractorContactPersonPhone;
	}

	public void setContractorContactPersonPhone(String contractorContactPersonPhone) {
		this.contractorContactPersonPhone = contractorContactPersonPhone;
	}

	public String getLaborer() {
		return laborer;
	}

	public void setLaborer(String laborer) {
		this.laborer = laborer;
	}

	public String getLaborerContactPerson() {
		return laborerContactPerson;
	}

	public void setLaborerContactPerson(String laborerContactPerson) {
		this.laborerContactPerson = laborerContactPerson;
	}

	public String getLaborerContactPersonPhone() {
		return laborerContactPersonPhone;
	}

	public void setLaborerContactPersonPhone(String laborerContactPersonPhone) {
		this.laborerContactPersonPhone = laborerContactPersonPhone;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSalaryPayDate() {
		return salaryPayDate;
	}

	public void setSalaryPayDate(Date salaryPayDate) {
		this.salaryPayDate = salaryPayDate;
	}

	public Double getActualTotalSalary() {
		return actualTotalSalary;
	}

	public void setActualTotalSalary(Double actualTotalSalary) {
		this.actualTotalSalary = actualTotalSalary;
	}

	public Integer getShouldSignContractNumber() {
		return shouldSignContractNumber;
	}

	public void setShouldSignContractNumber(Integer shouldSignContractNumber) {
		this.shouldSignContractNumber = shouldSignContractNumber;
	}

	public Integer getNotSignContractNumber() {
		return notSignContractNumber;
	}

	public void setNotSignContractNumber(Integer notSignContractNumber) {
		this.notSignContractNumber = notSignContractNumber;
	}

	public Integer getWorkersNumber() {
		return workersNumber;
	}

	public void setWorkersNumber(Integer workersNumber) {
		this.workersNumber = workersNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

}
