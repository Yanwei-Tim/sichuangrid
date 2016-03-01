package com.tianque.baseInfo.buildDatas.domain;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.base.BaseDomain;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2013-1-29 上午10:45:38
 **/
public class HouseBuilddatasBinding extends BaseDomain {

	private Long houseId;

	private Long builddatasId;

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getBuilddatasId() {
		return builddatasId;
	}

	public void setBuilddatasId(Long builddatasId) {
		this.builddatasId = builddatasId;
	}

	private HouseInfo houseInfo;

	private Builddatas builddatas;

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public Builddatas getBuilddatas() {
		return builddatas;
	}

	public void setBuilddatas(Builddatas builddatas) {
		this.builddatas = builddatas;
	}

}
