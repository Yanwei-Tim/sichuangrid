package com.tianque.plugin.dataManage.location.rentalHouseTemp.vo;

import com.tianque.plugin.dataManage.location.rentalHouseTemp.domain.RentalHouseTemp;

public class RentalHouseTempResultVo extends RentalHouseTemp {

	private boolean successful;
	private int errorType;
	private String errorInfo;

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
