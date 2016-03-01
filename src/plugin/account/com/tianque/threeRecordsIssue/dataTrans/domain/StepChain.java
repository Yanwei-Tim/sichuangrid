package com.tianque.threeRecordsIssue.dataTrans.domain;

/**
 * Created by daniel on 2015/4/26.
 */
public class StepChain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public String getRegCardCode() {
		return regCardCode;
	}

	public void setRegCardCode(String regCardCode) {
		this.regCardCode = regCardCode;
	}

	public String getRegUnit() {
		return regUnit;
	}

	public void setRegUnit(String regUnit) {
		this.regUnit = regUnit;
	}

	public String getcFlow() {
		return cFlow;
	}

	public void setcFlow(String cFlow) {
		this.cFlow = cFlow;
	}

	public String getzFlow() {
		return zFlow;
	}

	public void setzFlow(String zFlow) {
		this.zFlow = zFlow;
	}

	public String getbFlow() {
		return bFlow;
	}

	public void setbFlow(String bFlow) {
		this.bFlow = bFlow;
	}

	public String getsFlow() {
		return sFlow;
	}

	public void setsFlow(String sFlow) {
		this.sFlow = sFlow;
	}

	public String getcWay() {
		return cWay;
	}

	public void setcWay(String cWay) {
		this.cWay = cWay;
	}

	public String getzWay() {
		return zWay;
	}

	public void setzWay(String zWay) {
		this.zWay = zWay;
	}

	public String getbWay() {
		return bWay;
	}

	public void setbWay(String bWay) {
		this.bWay = bWay;
	}

	public String getSzWay() {
		return szWay;
	}

	public void setSzWay(String szWay) {
		this.szWay = szWay;
	}

	public String getcDate() {
		if (cDate != null && !cDate.equals("")) {
			if (cDate.contains("1899") || cDate.contains("1900")) {
				return null;
			}
		}

		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getzDate() {
		if (zDate != null && !zDate.equals("")) {
			if (zDate.contains("1899") || zDate.contains("1900")) {
				return null;
			}
		}
		return zDate;
	}

	public void setzDate(String zDate) {
		this.zDate = zDate;
	}

	public String getbDate() {
		if (bDate != null && !bDate.equals("")) {
			if (bDate.contains("1899") || bDate.contains("1900")) {
				return null;
			}
		}
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public String getSzDate() {
		if (szDate != null && !szDate.equals("")) {
			if (szDate.contains("1899") || szDate.contains("1900")) {
				return null;
			}
		}
		return szDate;
	}

	public void setSzDate(String szDate) {
		this.szDate = szDate;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getzContent() {
		return zContent;
	}

	public void setzContent(String zContent) {
		this.zContent = zContent;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getSzContent() {
		return szContent;
	}

	public void setSzContent(String szContent) {
		this.szContent = szContent;
	}

	public String getcFeedWay() {
		return cFeedWay;
	}

	public void setcFeedWay(String cFeedWay) {
		this.cFeedWay = cFeedWay;
	}

	public String getzFeedWay() {
		return zFeedWay;
	}

	public void setzFeedWay(String zFeedWay) {
		this.zFeedWay = zFeedWay;
	}

	public String getbFeedWay() {
		return bFeedWay;
	}

	public void setbFeedWay(String bFeedWay) {
		this.bFeedWay = bFeedWay;
	}

	public String getSzFeedWay() {
		return szFeedWay;
	}

	public void setSzFeedWay(String szFeedWay) {
		this.szFeedWay = szFeedWay;
	}

	public String getcFeedResult() {
		return cFeedResult;
	}

	public void setcFeedResult(String cFeedResult) {
		this.cFeedResult = cFeedResult;
	}

	public String getzFeedResult() {
		return zFeedResult;
	}

	public void setzFeedResult(String zFeedResult) {
		this.zFeedResult = zFeedResult;
	}

	public String getbFeedResult() {
		return bFeedResult;
	}

	public void setbFeedResult(String bFeedResult) {
		this.bFeedResult = bFeedResult;
	}

	public String getSzFeedResult() {
		return szFeedResult;
	}

	public void setSzFeedResult(String szFeedResult) {
		this.szFeedResult = szFeedResult;
	}

	public String getToSZRegCardCode() {
		return toSZRegCardCode;
	}

	public void setToSZRegCardCode(String toSZRegCardCode) {
		this.toSZRegCardCode = toSZRegCardCode;
	}

	public String getFromRegCardCode() {
		return fromRegCardCode;
	}

	public void setFromRegCardCode(String fromRegCardCode) {
		this.fromRegCardCode = fromRegCardCode;
	}

	public String getToCRegCardCode() {
		return toCRegCardCode;
	}

	public void setToCRegCardCode(String toCRegCardCode) {
		this.toCRegCardCode = toCRegCardCode;
	}

	public String getToZRegCardCode() {
		return toZRegCardCode;
	}

	public void setToZRegCardCode(String toZRegCardCode) {
		this.toZRegCardCode = toZRegCardCode;
	}

	public String getToBRegCardCode() {
		return toBRegCardCode;
	}

	public void setToBRegCardCode(String toBRegCardCode) {
		this.toBRegCardCode = toBRegCardCode;
	}

	public String getShFlow() {
		return shFlow;
	}

	public void setShFlow(String shFlow) {
		this.shFlow = shFlow;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEnbjCode() {
		return enbjCode;
	}

	public void setEnbjCode(String enbjCode) {
		this.enbjCode = enbjCode;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerTele() {
		return serverTele;
	}

	public void setServerTele(String serverTele) {
		this.serverTele = serverTele;
	}

	private String regCardCode;
	private String regUnit;
	private String cFlow;
	private String shFlow;
	private String zFlow;
	private String bFlow;
	private String sFlow;
	private String cWay;
	private String zWay;
	private String bWay;
	private String szWay;
	private String cDate;
	private String zDate;
	private String bDate;
	private String szDate;
	private String cContent;
	private String zContent;
	private String bContent;
	private String szContent;

	private String cFeedWay;
	private String zFeedWay;
	private String bFeedWay;
	private String szFeedWay;

	private String cFeedResult;
	private String zFeedResult;
	private String bFeedResult;
	private String szFeedResult;

	private String fromRegCardCode;
	private String toCRegCardCode;
	private String toZRegCardCode;
	private String toBRegCardCode;
	private String toSZRegCardCode;
	private String department;
	private String enbjCode;
	private String serverName;
	private String serverTele;

}
