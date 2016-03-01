package com.tianque.plugin.tqSearch.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class TqSearchHisHot implements Serializable {
	/** 名称 */
	private String name;
	/** 数量 */
	private long count;
	/** 索引类型 */
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String toFlashInfo(List<TqSearchHisHot> hishots) {
		StringBuffer strb = new StringBuffer();
		strb.append("<?xml version='1.0' encoding='utf-8'?>");
		strb.append("<billboard>");
		try {
			if (hishots != null && hishots.size() > 0) {
				for (TqSearchHisHot hishot : hishots) {
					String url = java.net.URLEncoder
							.encode("/baseinfo/tqSearch/dispatch.action?mode=list&sty="
									+ hishot.getType() + "&sq=" +hishot.getName() ,"utf-8");
					strb.append("<item url='").append(url).append("' query='")
							.append(hishot.getName())
							.append("' trend='0' intro='' url1=''/>");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		strb.append("</billboard>");
		return strb.toString();
	}
}
