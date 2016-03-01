package com.tianque.jms.msg;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.jms.OperateMode;

public class BusinesPopulationMsg extends BaseMsg {
	private String idCardNo;
	private String actualPopulationType;
	private static final long serialVersionUID = 1L;

	public BusinesPopulationMsg() {
		setMsgType("businessPopulation");
	}

	public BusinesPopulationMsg(AttentionPopulation attentionPopulation, OperateMode mode) {
		setObjectId(attentionPopulation.getId());
		setObjectType(attentionPopulation.getAttentionPopulationType());
		setIdCardNo(attentionPopulation.getIdCardNo());
		setOrgId(attentionPopulation.getOrganization().getId());
		setMode(mode);
		setMsgType("businessPopulation");
		setActualPopulationType(attentionPopulation.getActualPopulationType());
	}

	public BusinesPopulationMsg(String objectList, String attentionPopulationType, OperateMode mode) {
		setObjectType(attentionPopulationType);
		setObjectList(objectList);
		setMode(mode);
		setMsgType("businessPopulation");
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getActualPopulationType() {
		return actualPopulationType;
	}

	public void setActualPopulationType(String actualPopulationType) {
		this.actualPopulationType = actualPopulationType;
	}

}
