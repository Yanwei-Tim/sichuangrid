package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class WeChatType extends BaseProperty {
	public final static int wenben = 1;
	public final static int tupian = 2;
	public final static int tuwen = 3;
	public final static int yuyin = 4;

	public final static String wenben_CN = "文本";
	public final static String tupian_CN = "图片";
	public final static String tuwen_CN = "图文";
	public final static String yuyin_CN = "语音";

	public final static Map<Integer, String> MAP = new HashMap<Integer, String>();
	static {
		MAP.put(wenben, wenben_CN);
		MAP.put(tupian, tupian_CN);
		MAP.put(tuwen, tuwen_CN);
		MAP.put(yuyin, yuyin_CN);
	}

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(wenben, wenben_CN));
		properties.add(getInternalProperty(tupian, tupian_CN));
		properties.add(getInternalProperty(tuwen, tuwen_CN));
		properties.add(getInternalProperty(yuyin, yuyin_CN));

		return properties;
	}

	public final static String WECHAT_TYPE_KEY = PropertyTypes.WECHAT_TYPE;
}
