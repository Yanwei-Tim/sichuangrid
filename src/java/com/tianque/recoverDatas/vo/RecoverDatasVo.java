package com.tianque.recoverDatas.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.recoverDatas.domain.RecoverDatas;

public class RecoverDatasVo extends RecoverDatas {

	private Date createDateStart;

	private Date createDateEnd;

	@JSON(format = "yyyy-MM-dd")
	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

}
