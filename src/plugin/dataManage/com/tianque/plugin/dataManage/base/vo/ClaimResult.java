package com.tianque.plugin.dataManage.base.vo;

public class ClaimResult<T> {

	public T temp;

	private ClaimState claimState;

	public ClaimState getClaimState() {
		return claimState;
	}

	public void setClaimState(ClaimState claimState) {
		this.claimState = claimState;
	}

	public T getTemp() {
		return temp;
	}

	public void setTemp(T temp) {
		this.temp = temp;
	}

}
