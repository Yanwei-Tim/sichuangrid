package com.tianque.jms.msg;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.jms.OperateMode;

public class ActualPopulationMsg extends BaseMsg {

	private String idCardNo;
	private static final long serialVersionUID = 1L;

	public ActualPopulationMsg() {
		setMsgType("actualPopulation");
	}

	public ActualPopulationMsg(ActualPopulation actualPopulation, OperateMode mode) {
		setObjectId(actualPopulation.getId());
		setObjectType(actualPopulation.getActualPopulationType());
		setIdCardNo(actualPopulation.getIdCardNo());
		setOrgId(actualPopulation.getOrganization().getId());
		setMode(mode);
		setMsgType("actualPopulation");
	}

	public ActualPopulationMsg(String objectList, String actualPopulationType, OperateMode mode) {
		setObjectType(actualPopulationType);
		setObjectList(objectList);
		setMode(mode);
		setMsgType("actualPopulation");
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

}
