package com.tianque.baseInfo.companyPlace.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class CompanyPlaceBusiness extends BaseDomain {
	/* 是否安全生产重点（是否消防安全重点、是否治安重点、是否染污源） */
	private Long isKeyType;
	/* 隐患情况 */
	private String hiddangerInfo;
	/* 隐患整改情况 */
	private String correctionInfo;
	/* 挂牌整治（单选 0：省，1：市，2：县） */
	private Long listedCorrection;
	/* 挂牌等级（下拉 0：黄牌，1：红牌） */
	private Long listedLeve;
	/* 整改开始时间 */
	private Date correctionBeiginDate;
	/* 整改结束时间 */
	private Date correctionEndDate;
	/* 效果评估（下拉：0：好，1：较好，2：差，3：较差） */
	private PropertyDict effectassessment;
	/* 污染类型（下拉：字典项） */
	private PropertyDict pollutionType;
	/* 污染情况 */
	private String pollutionInfo;

	private String companyPlaceSource;// 添加日志是判断该信息来源
	private Long companyPlaceId;

	/* 附件 */
	private List<CompanyPlaceAnnex> placeAnnexFiles = new ArrayList<CompanyPlaceAnnex>();

	public Long getIsKeyType() {
		return isKeyType;
	}

	public void setIsKeyType(Long isKeyType) {
		this.isKeyType = isKeyType;
	}

	public String getHiddangerInfo() {
		return hiddangerInfo;
	}

	public void setHiddangerInfo(String hiddangerInfo) {
		this.hiddangerInfo = hiddangerInfo;
	}

	public String getCorrectionInfo() {
		return correctionInfo;
	}

	public void setCorrectionInfo(String correctionInfo) {
		this.correctionInfo = correctionInfo;
	}

	public Long getListedCorrection() {
		return listedCorrection;
	}

	public void setListedCorrection(Long listedCorrection) {
		this.listedCorrection = listedCorrection;
	}

	public Long getListedLeve() {
		return listedLeve;
	}

	public void setListedLeve(Long listedLeve) {
		this.listedLeve = listedLeve;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCorrectionBeiginDate() {
		return correctionBeiginDate;
	}

	public void setCorrectionBeiginDate(Date correctionBeiginDate) {
		this.correctionBeiginDate = correctionBeiginDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCorrectionEndDate() {
		return correctionEndDate;
	}

	public void setCorrectionEndDate(Date correctionEndDate) {
		this.correctionEndDate = correctionEndDate;
	}

	public PropertyDict getEffectassessment() {
		return effectassessment;
	}

	public void setEffectassessment(PropertyDict effectassessment) {
		this.effectassessment = effectassessment;
	}

	public PropertyDict getPollutionType() {
		return pollutionType;
	}

	public void setPollutionType(PropertyDict pollutionType) {
		this.pollutionType = pollutionType;
	}

	public String getPollutionInfo() {
		return pollutionInfo;
	}

	public void setPollutionInfo(String pollutionInfo) {
		this.pollutionInfo = pollutionInfo;
	}

	public List<CompanyPlaceAnnex> getPlaceAnnexFiles() {
		return placeAnnexFiles;
	}

	public void setPlaceAnnexFiles(List<CompanyPlaceAnnex> placeAnnexFiles) {
		this.placeAnnexFiles = placeAnnexFiles;
	}

	public String getCompanyPlaceSource() {
		return companyPlaceSource;
	}

	public void setCompanyPlaceSource(String companyPlaceSource) {
		this.companyPlaceSource = companyPlaceSource;
	}

	public Long getCompanyPlaceId() {
		return companyPlaceId;
	}

	public void setCompanyPlaceId(Long companyPlaceId) {
		this.companyPlaceId = companyPlaceId;
	}
}