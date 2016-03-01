package com.tianque.plugin.dataManage.base.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.DateUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SearchVo extends BaseDomain {
	private Long searchType;
	private String searchArgs;
	private Organization importDepartment;
	private Organization targetOrg;
	private String mode;
	private String bigType;
	private Integer dispose;// 数据处理状态,是否要处理
	private Integer claimAvailable;// 数据是否可认领
	private Long orgId;
	// 人口高级搜索
	private String name;
	private String idCardNo;
	private PropertyDict gender;
	private String currentAddress;
	// 房屋高级搜索
	// 场所高级搜索
	private String address;
	// 部件高级搜索
	private String dustbinCode;
	private Long partType;
	private Long partName;
	// 高级搜索通用
	/** 数据状态 */
	private Integer dataState;// 已认领，未认领状态
	private String dateType;// 认领时间，还是导入时间
	private Date beginDate;
	private Date endDate;

	// 楼宇
	private String buildingname;
	private String buildingaddress;
	private String owner;
	private String responsibleperson;
	private String phone;
	private Long buildingstructures;

	private String classifiCationEn;// 数据管理--单位场所--二级英文名称

	public Long getSearchType() {
		return searchType;
	}

	public void setSearchType(Long searchType) {
		this.searchType = searchType;
	}

	public String getSearchArgs() {
		return searchArgs;
	}

	public void setSearchArgs(String searchArgs) {
		this.searchArgs = searchArgs;
	}

	public Organization getImportDepartment() {
		return importDepartment;
	}

	public void setImportDepartment(Organization importDepartment) {
		this.importDepartment = importDepartment;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getBigType() {
		return bigType;
	}

	public void setBigType(String bigType) {
		this.bigType = bigType;
	}

	public Integer getDataState() {
		return dataState;
	}

	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = DateUtil.getEndTime(endDate);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Integer getDispose() {
		return dispose;
	}

	public void setDispose(Integer dispose) {
		this.dispose = dispose;
	}

	public Integer getClaimAvailable() {
		return claimAvailable;
	}

	public void setClaimAvailable(Integer claimAvailable) {
		this.claimAvailable = claimAvailable;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getDustbinCode() {
		return dustbinCode;
	}

	public void setDustbinCode(String dustbinCode) {
		this.dustbinCode = dustbinCode;
	}

	public Long getPartType() {
		return partType;
	}

	public void setPartType(Long partType) {
		this.partType = partType;
	}

	public Long getPartName() {
		return partName;
	}

	public void setPartName(Long partName) {
		this.partName = partName;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getBuildingaddress() {
		return buildingaddress;
	}

	public void setBuildingaddress(String buildingaddress) {
		this.buildingaddress = buildingaddress;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getResponsibleperson() {
		return responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getBuildingstructures() {
		return buildingstructures;
	}

	public void setBuildingstructures(Long buildingstructures) {
		this.buildingstructures = buildingstructures;
	}

	public String getClassifiCationEn() {
		return classifiCationEn;
	}

	public void setClassifiCationEn(String classifiCationEn) {
		this.classifiCationEn = classifiCationEn;
	}

}
