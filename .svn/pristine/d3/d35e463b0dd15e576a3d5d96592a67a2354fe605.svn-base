package com.tianque.mobile.base;

import java.io.InputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.mobile.vo.ErrorResponse;

public class BaseMobileAction extends ActionSupport {
	protected String actionName;
	protected String domainName;
	protected String ajaxUrl;
	protected final static String MOBILE_ERROR = "mobile_error";

	protected Map<String, String> cacheId;

	/**
	 * 错误信息
	 */
	protected String errorMessage;
	protected String errorCode;
	protected ErrorResponse errorResponse;

	protected String downloadFileName;

	protected InputStream inputStream;

	/**
	 * 角色grid的数据源
	 */
	protected GridPage gridPage;
	/**
	 * 增加，修改，查看
	 */
	protected String mode = DialogMode.VIEW_MODE;

	/**
	 * 显示页
	 */
	protected Integer page = 1;
	/**
	 * 一页行数
	 */
	protected Integer rows = 15;
	/**
	 * 排序的字段名
	 */
	protected String sidx;
	/**
	 * 排序的顺序
	 */
	protected String sord;
	/**
	 * 是否手机端
	 */
	protected String tqmobile;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public String getMode() {
		return mode;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getRows() {
		return rows;
	}

	public String getSidx() {
		return sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	protected void appendErrorMessage(String msg) {
		if (errorMessage == null || errorMessage.trim().length() == 0) {
			errorMessage = msg;
		} else {
			errorMessage = errorMessage + "\n" + msg;
		}
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Map<String, String> getCacheId() {
		return cacheId;
	}

	public void setCacheId(Map<String, String> cacheId) {
		this.cacheId = cacheId;
	}

	public String getAjaxUrl() {
		return ajaxUrl;
	}

	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}

	public ErrorResponse getErrorResponse() {
		if (errorCode != null && errorCode.equals(ErrorResponse.SESSION_ERROR[0])) {
			return new ErrorResponse(errorMessage, ErrorResponse.SESSION_ERROR[0]);
		} else if (errorCode != null && errorCode.equals(ErrorResponse.SESSION_ERROR[1])) {
			return new ErrorResponse(errorMessage, ErrorResponse.SESSION_ERROR[1]);
		} else {
			return new ErrorResponse(errorMessage, ErrorResponse.NORMAL_ERROR);
		}

	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getTqmobile() {
		return tqmobile;
	}

	public void setTqmobile(String tqmobile) {
		this.tqmobile = tqmobile;
	}
}
