package com.tianque.plugin.analyzing.util;

import java.text.DecimalFormat;

public class LingRateUtil {

	/**
	 * 计算百分比值 firstData:分子 secondData:分母
	 */
	public static String getLingRate(double firstData, double secondData) {
		DecimalFormat df = new DecimalFormat("######0.00");

		String chain = "";
		if (firstData == 0 || secondData == 0) {
			chain = "0.00%";
			return chain;
		}
		double data = firstData / secondData * 100;
		chain = df.format(data) + "%";
		return chain;
	}
}
