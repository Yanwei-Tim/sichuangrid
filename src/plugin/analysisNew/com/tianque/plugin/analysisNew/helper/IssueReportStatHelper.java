package com.tianque.plugin.analysisNew.helper;

import java.math.BigDecimal;

import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;

public class IssueReportStatHelper {

	private final static int ACCURACY = 3;// 精确度
	/**
	 * 是否是当前月，季，年
	 */
	public static final boolean ISDATEOVER = true;

	public static void autoFillIssueProportionStatRate(
			IssueAreaStatNew issueAreaStat) {
		double historySum = issueAreaStat.getHistorySum() == null ? 0d
				: issueAreaStat.getHistorySum();
		double nowSum = issueAreaStat.getNowSum() == null ? 0d : issueAreaStat
				.getNowSum();
		if (historySum == 0d) {
			if (nowSum == 0d) {
				issueAreaStat.setGrowthRate("0.0%");
				issueAreaStat.setGrowthSpeeding("0.0%");
			} else {
				issueAreaStat.setGrowthRate("100.0%");
				issueAreaStat.setGrowthSpeeding("100.0%");
			}
		} else {
			issueAreaStat.setGrowthRate(div((nowSum - historySum) * 100,
					historySum, ACCURACY) + "%");
			issueAreaStat.setGrowthSpeeding(div(nowSum * 100, historySum,
					ACCURACY) + "%");
		}
	}

	public static void autoFillCompletionRate(IssueAreaStatNew issueAreaStat) {
		double issueSum = issueAreaStat.getIssueSum() == null ? 0d
				: issueAreaStat.getIssueSum();
		double doneIssueSum = issueAreaStat.getDoneIssueSum() == null ? 0d
				: issueAreaStat.getDoneIssueSum();
		if (issueSum == 0d) {
			issueAreaStat.setCompletionRate("0.0%");
		} else {
			issueAreaStat.setCompletionRate(div(doneIssueSum * 100, issueSum,
					ACCURACY) + "%");
		}
	}

	public static void autoFillExtendedRate(IssueAreaStatNew issueAreaStat) {
		if ((issueAreaStat.getExtendedDoingSum() == null || issueAreaStat
				.getExtendedDoingSum() == 0)
				&& (issueAreaStat.getExtendedDoneSum() == null || issueAreaStat
						.getExtendedDoneSum() == 0)) {
			issueAreaStat.setExtendedRate("0.0%");
		} else {
			double extendedDoneSum = issueAreaStat.getExtendedDoneSum();
			double extendedSum = issueAreaStat.getExtendedDoingSum()
					+ extendedDoneSum;
			issueAreaStat.setExtendedRate(div(extendedDoneSum * 100,
					extendedSum, ACCURACY) + "%");
		}
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	private static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
