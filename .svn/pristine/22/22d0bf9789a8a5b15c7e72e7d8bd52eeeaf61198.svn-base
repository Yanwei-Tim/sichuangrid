package com.tianque.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;

/**
 * 工作台常用设置
 * 
 * @author pursuer
 */
public final class WorkBenchTabConfiger {

	public final static Map<String, WorkBenchTabConfiger> allWorkBenchTabConfiger = new HashMap<String, WorkBenchTabConfiger>();
	static {
		init();
	}

	/** 标识符 **/
	private String keyName;

	/** 中文名称 **/
	private String cname;

	/** 链接地址 **/
	private String url;

	/** 权限名称 **/
	private String ename;

	/** 最大化页面（页面类型为1是） **/
	private String maxUrl;

	private ArrayList<TabPatel> tabPatels;

	private static void init() {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			WorkBenchTabConfigers workBenchTabConfigers = (WorkBenchTabConfigers) xmlToBean
					.xmlToBean(getWorkBenchTabConfigerPath(), WorkBenchTabConfigers.class,
							getMapInputWorkBenchTabConfigerPath());
			initMap(workBenchTabConfigers);
		} catch (Exception e) {
			System.err.println("problem in execute!");
			System.out.println(e.getCause().toString());
		}
	}

	private static void initMap(WorkBenchTabConfigers workBenchTabConfigers) {
		for (WorkBenchTabConfiger workBenchTabConfiger : workBenchTabConfigers
				.getWorkBenchTabConfigers()) {
			allWorkBenchTabConfiger.put(workBenchTabConfiger.getEname(), workBenchTabConfiger);
		}
	}

	protected static String getWorkBenchTabConfigerPath() {
		return "workBenchTabConfiger.xml";
	}

	protected static String getMapInputWorkBenchTabConfigerPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/mapInputPatel.xml";
	}

	public static void main(String[] args) {
		for (String string : WorkBenchTabConfiger.allWorkBenchTabConfiger.keySet()) {
			System.err.println(string);
		}
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getMaxUrl() {
		return maxUrl;
	}

	public void setMaxUrl(String maxUrl) {
		this.maxUrl = maxUrl;
	}

	public ArrayList<TabPatel> getTabPatels() {
		return tabPatels;
	}

	public void setTabPatels(ArrayList<TabPatel> tabPatels) {
		this.tabPatels = tabPatels;
	}

}
