package com.tianque.baseInfo.familyInfo.domain;

import java.util.List;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class HouseFamily extends ActualPopulation {

	private CensusRegisterFamily censusRegisterFamily;

	/** 与户主关系 **/
	private PropertyDict relationShipWithHead;

	/** 家庭称呼 */
	private List<PropertyDict> houseMarchType;

	/** 家庭成员数 */
	private Integer memberNums;

	/** 未死亡未注销的家庭成员数 */
	private Integer effectiveMemberNums;

	public CensusRegisterFamily getCensusRegisterFamily() {
		return censusRegisterFamily;
	}

	public void setCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		this.censusRegisterFamily = censusRegisterFamily;
	}

	public PropertyDict getRelationShipWithHead() {
		return relationShipWithHead;
	}

	public void setRelationShipWithHead(PropertyDict relationShipWithHead) {
		this.relationShipWithHead = relationShipWithHead;
	}

	public List<PropertyDict> getHouseMarchType() {
		return houseMarchType;
	}

	public void setHouseMarchType(List<PropertyDict> houseMarchType) {
		this.houseMarchType = houseMarchType;
	}

	public Integer getMemberNums() {
		return memberNums;
	}

	public void setMemberNums(Integer memberNums) {
		this.memberNums = memberNums;
	}

	public Integer getEffectiveMemberNums() {
		return effectiveMemberNums;
	}

	public void setEffectiveMemberNums(Integer effectiveMemberNums) {
		this.effectiveMemberNums = effectiveMemberNums;
	}
}
