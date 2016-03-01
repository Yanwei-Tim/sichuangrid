package com.tianque.service.util;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;

/**
 * 工作台常用设置
 * 
 * @author pursuer
 */
public final class InformationTrainConfiger {

	public final static Map<String, InformationTrainConfiger> allInformationCatalogs = new HashMap<String, InformationTrainConfiger>();
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

	/** 页面类型（1 信息直通车页面 2 其他页面） **/
	private int type;

	/** 最大化页面（页面类型为1是） **/
	private String maxUrl;

	private String imgUrl;

	private static void init() {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			InformationTrainConfigers informationTrainConfigers = (InformationTrainConfigers) xmlToBean
					.xmlToBean(getInformationTrainConfigPath(), InformationTrainConfigers.class,
							getMapInputInformationTrainConfigPath());
			initMap(informationTrainConfigers);
		} catch (Exception e) {
			System.err.println("problem in execute!");
		}
	}

	private static void initMap(InformationTrainConfigers informationTrainConfigers) {
		for (InformationTrainConfiger informationTrainConfiger : informationTrainConfigers
				.getInformationTrainConfigers()) {
			allInformationCatalogs.put(informationTrainConfiger.getEname(),
					informationTrainConfiger);
		}
	}

	protected static String getInformationTrainConfigPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/informationTrainConfiger.xml";
	}

	protected static String getMapInputInformationTrainConfigPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/mapInputPatel.xml";
	}

	public static void main(String[] args) {
		for (String string : InformationTrainConfiger.allInformationCatalogs.keySet()) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMaxUrl() {
		return maxUrl;
	}

	public void setMaxUrl(String maxUrl) {
		this.maxUrl = maxUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
