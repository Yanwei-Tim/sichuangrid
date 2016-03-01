package com.tianque.baseInfo.scenicManage.scenicTraffic.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * @ClassName: ScenicTraffic
 * @Description: 景区交通
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 10:58:07 AM
 */
public class ScenicTraffic extends BaseDomain {
	private Organization organization;
	private String startAddress;// 起点名称
	private String endAddress;// 终点名称
	private PropertyDict trafficType;// 交通类型
	private String phone;// 联系电话
	private String trafficName;// 交通线路名称
	private String managerUnit;// 负责单位
	private String managerPeople;// 负责人
	private String managerPeoplePhone;// 负责人电话
	private String aroundScenic;// 周边景点
	private String firstBusTime;// 最早班车时间
	private String lastBusTime;// 最晚班车时间
	private String remark;// 备注
	private String imgUrl;// 图片路径
	private String fullPinYin;// 全拼音
	private String simplePinYin;// 简拼音
	private String logOutReason;// 取消关注原因
	private Boolean isEmphasis = false;// 是否关注
	private Date logOutTime;// 取消关注时间

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	public PropertyDict getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(PropertyDict trafficType) {
		this.trafficType = trafficType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrafficName() {
		return trafficName;
	}

	public void setTrafficName(String trafficName) {
		this.trafficName = trafficName;
	}

	public String getManagerUnit() {
		return managerUnit;
	}

	public void setManagerUnit(String managerUnit) {
		this.managerUnit = managerUnit;
	}

	public String getManagerPeople() {
		return managerPeople;
	}

	public void setManagerPeople(String managerPeople) {
		this.managerPeople = managerPeople;
	}

	public String getManagerPeoplePhone() {
		return managerPeoplePhone;
	}

	public void setManagerPeoplePhone(String managerPeoplePhone) {
		this.managerPeoplePhone = managerPeoplePhone;
	}

	public String getAroundScenic() {
		return aroundScenic;
	}

	public void setAroundScenic(String aroundScenic) {
		this.aroundScenic = aroundScenic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getFullPinYin() {
		return fullPinYin;
	}

	public void setFullPinYin(String fullPinYin) {
		this.fullPinYin = fullPinYin;
	}

	public String getSimplePinYin() {
		return simplePinYin;
	}

	public void setSimplePinYin(String simplePinYin) {
		this.simplePinYin = simplePinYin;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getFirstBusTime() {
		return firstBusTime;
	}

	public void setFirstBusTime(String firstBusTime) {
		this.firstBusTime = firstBusTime;
	}

	public String getLastBusTime() {
		return lastBusTime;
	}

	public void setLastBusTime(String lastBusTime) {
		this.lastBusTime = lastBusTime;
	}

	// id orgcode加密
	public String getEncryptId() {

		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				organization.getOrgInternalCode(), null);
	}
}