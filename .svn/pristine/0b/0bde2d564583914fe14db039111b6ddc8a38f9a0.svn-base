package com.tianque.xichang.working.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalValueUtil {

	public static Map<Long, String> aspirationType = new LinkedHashMap<Long, String>();
	public static Map<Long, String> warningType = new LinkedHashMap<Long, String>();

	static {
		aspirationType.put(0l, "党纪政纪");
		aspirationType.put(1l, "组织人事");
		aspirationType.put(2l, "涉法涉诉");
		aspirationType.put(3l, "土地、林权");
		aspirationType.put(4l, "征地拆迁");
		aspirationType.put(5l, "水利电力");
		aspirationType.put(6l, "环保");
		aspirationType.put(7l, "扶贫济困");
		aspirationType.put(8l, "惠农政策及村(社区)政务、财务");
		aspirationType.put(9l, "人口与医疗卫生");
		aspirationType.put(10l, "劳动保障");
		aspirationType.put(11l, "交通运输");
		aspirationType.put(12l, "城建");
		aspirationType.put(13l, "安全生产");
		aspirationType.put(14l, "旅游");
		aspirationType.put(15l, "教育");
		aspirationType.put(16l, "企业改制");
		aspirationType.put(17l, "移民");
		aspirationType.put(18l, "涉军");
		aspirationType.put(19l, "其他");

		warningType.put(0l, "1-2人且事态在可控范围");
		warningType.put(1l, "涉及3-9人且事态恶化的可能性");
		warningType.put(2l, "涉及10-30人且事态有扩大趋势");
		warningType.put(3l, "涉及30人以上且事态还有扩大趋势的及涉及人数虽较少，但如不及时采取措施事态将恶化");

	}

	public static String getAspirationTypeValueByKey(Long key) {
		return aspirationType.get(key);
	}

	public static String getAspirationTypeKeyByValue(String value) {
		return aspirationType.get(Long.valueOf(value));
	}

	public static String getWarningTypeValueByKey(Long key) {
		return warningType.get(key);
	}

	public static String getWarningTypeKeyByValue(String value) {
		return warningType.get(Long.valueOf(value));
	}
}
