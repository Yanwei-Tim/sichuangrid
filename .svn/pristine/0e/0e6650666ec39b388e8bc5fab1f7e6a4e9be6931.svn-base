package com.tianque.jms.msg;

import com.tianque.jms.OperateMode;

public class HouseMsg extends BaseMsg {

	private static final long serialVersionUID = 1L;

	/** 住房编号 */
	private String houseCode;

	private Long houseId;

	public HouseMsg() {
	}

	public HouseMsg(Long orgId, String houseCode, OperateMode mode) {
		this.orgId = orgId;
		this.houseCode = houseCode;
		this.mode = mode;
		this.msgType = "houseInfo";

	}

	public HouseMsg(Long houseId, OperateMode mode) {
		this.houseId = houseId;
		this.mode = mode;
		this.msgType = "houseInfo";
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

}
