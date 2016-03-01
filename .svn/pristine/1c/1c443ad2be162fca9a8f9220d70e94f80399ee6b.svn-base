package com.tianque.plugin.judgmentAnalysis.util;

import java.util.Arrays;

public class StringUtil {

	public static String firstCharLowCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static String firstCharUpCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String addPrefix(int num, String prefix, int length) {
		return String.format("%04d", num);
	}

	public static boolean isStringAvaliable(String string) {
		return string != null && !"".equals(string.trim());
	}

	public static Boolean notSame(String dest, String scre) {
		if (null == dest || null == scre)
			return false;
		else
			return !dest.equals(scre);
	}

	/**
	 * 去掉字符串前后空格
	 * 
	 * @param s
	 * @return
	 */
	public static String trim(String s) {
		int i = s.length();
		int j = 0;// 字符串第一个字符
		int k = 0;// 中间变量
		char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
		while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
			++j;// 确定字符串前面的空格数
		while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
			--i;// 确定字符串后面的空格数
		return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
	}

	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");

		return str.replaceAll("[(/>)<]", "");
	}

	/**
	 * 将字符串中的英文单双引号替换是html用的
	 * 
	 * @param str
	 * @return
	 */
	public static String changeJavaToHtml(String str) {
		if (isEmpty(str))
			return "";
		str = str.replace("'", "&#39;");
		str = str.replace("\"", "&quot;");
		return str;
	}

	public static boolean isEmpty(String str) {
		return !isStringAvaliable(str);
	}

	public static String enCodeExeclDownLoadName(String str) {
		if (isEmpty(str))
			return "";
		try {
			return new String(str.getBytes("gbk"), "ISO8859-1");
		} catch (Exception e) {
			return "";
		}
	}

	public static String arrToString(Object[] arr) {
		return Arrays.toString(arr).replace("[", "").replace("]", "").replace(" ", "");
	}

	/***
	 * 截取orgCode 的市級別的orgCode
	 * 1.1.3.5.1.12. 返回1.1.3.
	 */
	public static String subOrgCode(String text) {
		if (!isStringAvaliable(text))
			return "";

		String[] arr = text.split("\\.");
		if (arr.length > 3)
			return arr[0] + "." + arr[1] + "." + arr[2] + ".";
		else
			return text;
	}

	/***
	 * 截取orgCode父级code
	 * 1.1.3.5.1.12. 返回1.1.3.5.1.
	 */
	public static String parentOrgCode(String text) {
		if (!isStringAvaliable(text))
			return "";

		String[] arr = text.split("\\.");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length-1; i++) {
			sb.append(arr[i]).append(".");
		}
		return sb.toString();
	}
	
}
