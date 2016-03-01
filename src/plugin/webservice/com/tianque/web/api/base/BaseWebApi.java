package com.tianque.web.api.base;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.web.api.vo.Body;
import com.tianque.web.api.vo.Head;
import com.tianque.web.api.vo.Response;

/**
 * @ClassName: BaseWebApi
 * @Description: 基类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月22日 下午3:48:47
 */
public class BaseWebApi extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat simpleFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat fullFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final String WEB_API_SUCCESS = "webApiSuccess";
	protected Response response;
	protected Head head;
	protected Body body;
	protected List<Map<String, Object>> resultList;
	protected String emptyResult = "";
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

	protected InputStream inputStream;

	@Autowired
	public PropertyDictService propertyDictService;

	protected String findDictName(PropertyDict d) {
		if (d == null) {
			return "";
		}
		PropertyDict dict = propertyDictService.getPropertyDictById(d.getId());
		if (dict == null) {
			return "";
		} else {
			return dict.getDisplayName();
		}
	}

	public BaseWebApi() {
		head = new Head();
		body = new Body();
		response = new Response(head, body);
		resultList = new ArrayList<Map<String, Object>>();
	}

	public static String formateSimpleDate(Date date) {
		if (date != null) {
			return simpleFormat.format(date);
		}
		return null;
	}

	public static String formateFullDate(Date date) {
		if (date != null) {
			return fullFormat.format(date);
		}
		return null;
	}

	public Response getResponse() {
		addErrorHeader();
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void addErrorHeader() {
		ServletActionContext.getResponse().setHeader("Error", "1");
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public static boolean isEmpty(String val) {
		return val == null || val.trim().equals("");
	}

	public static String emptyValue(String val) {
		return val != null ? val : "";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
