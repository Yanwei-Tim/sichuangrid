package com.tianque.baseInfo.primaryOrg.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
/***
 * 红袖套队伍管理实体类
 * @author wangchao
 *
 */
public class RedCuffTeam extends BaseDomain{

	private Organization organization;//组织机构
	private String memeberName;//成员名称
	private String idCardNo;//身份证号码
	private PropertyDict gender;//性别
	private Date birthDate;//出生日期
	private String phoneNumber;//手机号码
	private PropertyDict nation;//民族
	private PropertyDict teamType;//队伍类别
	private PropertyDict subTeamType;//队伍类别子类
	private PropertyDict political;//政治面貌
	private PropertyDict education;//文化程度
	private String occupation;//职业
	private String fimalyAddress;//家庭住址
	private String registeredPerson;//登记人
	private Date registeredDate;//登记时间
	private String remark;//备注
	private Integer isCertification;//是否认证
	private String wechatNo;//微信号
	private Date wechatAuthenticationDate;//微信认证时间
	private String latitudeX;//维度
	private String longitudeY;//经度
	private String precision;//精度
	private Date bindingTime;//绑定时间
	private String telephone;//固定电话
	private Integer exitRedCuffTeam;//是否退出红袖套
	
	// 搜索使用：是否排除没有定位数据
	private Boolean notNoBindingTime;
	
	public Date getWechatAuthenticationDate() {
		return wechatAuthenticationDate;
	}
	public void setWechatAuthenticationDate(Date wechatAuthenticationDate) {
		this.wechatAuthenticationDate = wechatAuthenticationDate;
	}
	public String getLatitudeX() {
		return latitudeX;
	}
	public void setLatitudeX(String latitudeX) {
		this.latitudeX = latitudeX;
	}
	public String getLongitudeY() {
		return longitudeY;
	}
	public void setLongitudeY(String longitudeY) {
		this.longitudeY = longitudeY;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getBindingTime() {
		return bindingTime;
	}
	public void setBindingTime(Date bindingTime) {
		this.bindingTime = bindingTime;
	}
	public PropertyDict getSubTeamType() {
		return subTeamType;
	}
	public void setSubTeamType(PropertyDict subTeamType) {
		this.subTeamType = subTeamType;
	}
	public Integer getIsCertification() {
		return isCertification;
	}
	public void setIsCertification(Integer isCertification) {
		this.isCertification = isCertification;
	}
	public String getWechatNo() {
		return wechatNo;
	}
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getMemeberName() {
		return memeberName;
	}
	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
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
	@JSON(format = "yyyy-MM-dd")
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public PropertyDict getNation() {
		return nation;
	}
	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}
	public PropertyDict getTeamType() {
		return teamType;
	}
	public void setTeamType(PropertyDict teamType) {
		this.teamType = teamType;
	}
	public PropertyDict getPolitical() {
		return political;
	}
	public void setPolitical(PropertyDict political) {
		this.political = political;
	}
	public PropertyDict getEducation() {
		return education;
	}
	public void setEducation(PropertyDict education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getFimalyAddress() {
		return fimalyAddress;
	}
	public void setFimalyAddress(String fimalyAddress) {
		this.fimalyAddress = fimalyAddress;
	}
	public String getRegisteredPerson() {
		return registeredPerson;
	}
	public void setRegisteredPerson(String registeredPerson) {
		this.registeredPerson = registeredPerson;
	}
	@JSON(format = "yyyy-MM-dd")
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getExitRedCuffTeam() {
		return exitRedCuffTeam;
	}
	public void setExitRedCuffTeam(Integer exitRedCuffTeam) {
		this.exitRedCuffTeam = exitRedCuffTeam;
	}

	public Boolean getNotNoBindingTime() {
		return notNoBindingTime;
	}

	public void setNotNoBindingTime(Boolean notNoBindingTime) {
		this.notNoBindingTime = notNoBindingTime;
	}
	
	
	
}
