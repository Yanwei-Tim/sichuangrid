package com.tianque.openLayersMap.domian.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 二维地图	划区域统计信息的实体类
 * @author jiangjinling
 *
 */
public class DrawAreaStatisticVo extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/** 已绑定重点人员数量 */
	private Integer keyPersonBoundMapNum;
	/** 重点人员总数 */
	private Integer keyPersonSum;
	/** 已绑定重点场所数量 */
	private Integer keyPlaceBoundMapNum;
	/** 重点场所总数 */
	private Integer keyPlaceSum;
	/** 已绑定住房数量 */
	private Integer housePropertyBoundMapNum;
	/** 住房总数 */
	private Integer housePropertySum;
	/** 已绑定服务办事数量 */
	private Integer issueBoundMapNum;
	/** 服务办事总数 */
	private Integer issueSum;
	/** 已绑定楼宇数量 */
	private Integer buildDataBoundMapNum;
	/** 楼宇总数 */
	private Integer buildDataSum;
	/** 已绑定房屋数量 */
	private Integer hourseBoundMapNum;
    /** 房屋数量 */
	private Integer hourseNum;
	/**所属网格*/
	private Organization organization;
	
	private String gisType="3D";
	
	public String getGisType() {
		return gisType;
	}
	public void setGisType(String gisType) {
		this.gisType = gisType;
	}
	public Integer getHourseBoundMapNum() {
		return hourseBoundMapNum;
	}
	public void setHourseBoundMapNum(Integer hourseBoundMapNum) {
		this.hourseBoundMapNum = hourseBoundMapNum;
	}
	public Integer getKeyPersonBoundMapNum() {
		return keyPersonBoundMapNum;
	}
	public void setKeyPersonBoundMapNum(Integer keyPersonBoundMapNum) {
		this.keyPersonBoundMapNum = keyPersonBoundMapNum;
	}
	public Integer getKeyPersonSum() {
		return keyPersonSum;
	}
	public void setKeyPersonSum(Integer keyPersonSum) {
		this.keyPersonSum = keyPersonSum;
	}
	public Integer getKeyPlaceBoundMapNum() {
		return keyPlaceBoundMapNum;
	}
	public void setKeyPlaceBoundMapNum(Integer keyPlaceBoundMapNum) {
		this.keyPlaceBoundMapNum = keyPlaceBoundMapNum;
	}
	public Integer getKeyPlaceSum() {
		return keyPlaceSum;
	}
	public void setKeyPlaceSum(Integer keyPlaceSum) {
		this.keyPlaceSum = keyPlaceSum;
	}
	public Integer getHousePropertyBoundMapNum() {
		return housePropertyBoundMapNum;
	}
	public void setHousePropertyBoundMapNum(Integer housePropertyBoundMapNum) {
		this.housePropertyBoundMapNum = housePropertyBoundMapNum;
	}
	public Integer getHousePropertySum() {
		return housePropertySum;
	}
	public void setHousePropertySum(Integer housePropertySum) {
		this.housePropertySum = housePropertySum;
	}
	public Integer getIssueBoundMapNum() {
		return issueBoundMapNum;
	}
	public void setIssueBoundMapNum(Integer issueBoundMapNum) {
		this.issueBoundMapNum = issueBoundMapNum;
	}
	public Integer getIssueSum() {
		return issueSum;
	}
	public void setIssueSum(Integer issueSum) {
		this.issueSum = issueSum;
	}
	public Integer getBuildDataBoundMapNum() {
		return buildDataBoundMapNum;
	}
	public void setBuildDataBoundMapNum(Integer buildDataBoundMapNum) {
		this.buildDataBoundMapNum = buildDataBoundMapNum;
	}
	public Integer getBuildDataSum() {
		return buildDataSum;
	}
	public void setBuildDataSum(Integer buildDataSum) {
		this.buildDataSum = buildDataSum;
	}
	public Integer getHourseNum() {
		return hourseNum;
	}
	public void setHourseNum(Integer hourseNum) {
		this.hourseNum = hourseNum;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
