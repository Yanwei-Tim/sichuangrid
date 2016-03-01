package com.tianque.xichang.working.poorPeople.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

/**
 * @ClassName: PoorPeopleMembers
 * @Description: 家庭成员
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 26, 2013 3:51:06 PM
 */
public class PoorPeopleMembers extends BaseDomain {
	private String name;// 姓名
	private PropertyDict gender;// 性别
	private Date birthday;// 生日
	private PropertyDict politiCalbackGround;// 政治面貌
	private PropertyDict schooling;// 学历
	private PropertyDict nation;// 民族
	private String career;// 职业
	private PropertyDict healthState;// 健康状况
	private PropertyDict relationShipWithHead;// 与户主关系
	private PropertyDict insuranceType;// 纳入低保（五保）情况
	private PoorPeople poorPeople;

	private Integer page = 1;
	private Integer rows = 20;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PropertyDict getGender() {
		return gender;
	}
	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public PropertyDict getPolitiCalbackGround() {
		return politiCalbackGround;
	}
	public void setPolitiCalbackGround(PropertyDict politiCalbackGround) {
		this.politiCalbackGround = politiCalbackGround;
	}
	public PropertyDict getSchooling() {
		return schooling;
	}
	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}
	public PropertyDict getNation() {
		return nation;
	}
	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public PropertyDict getHealthState() {
		return healthState;
	}
	public void setHealthState(PropertyDict healthState) {
		this.healthState = healthState;
	}
	public PropertyDict getRelationShipWithHead() {
		return relationShipWithHead;
	}
	public void setRelationShipWithHead(PropertyDict relationShipWithHead) {
		this.relationShipWithHead = relationShipWithHead;
	}
	public PropertyDict getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(PropertyDict insuranceType) {
		this.insuranceType = insuranceType;
	}
	public PoorPeople getPoorPeople() {
		return poorPeople;
	}
	public void setPoorPeople(PoorPeople poorPeople) {
		this.poorPeople = poorPeople;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
