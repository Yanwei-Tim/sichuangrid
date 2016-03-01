package com.tianque.job.optmize;

import java.util.regex.Pattern;

/**
 * 简单的身份证号码验证
 * 
 * @author Evan
 */
public class IdCardValidator {

	static Pattern _common_pattern = Pattern
			.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
	static Pattern _15_len_normal_pattern = Pattern
			.compile("^(\\d{6})(\\d{2})(((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-9]|(3[0-1]))))(\\d{3})$");
	static Pattern _18_len_normal_pattern = Pattern
			.compile("^(\\d{6})((20[0-9][0-9])|(19[0-9][0-9]))(((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-9]|(3[0-1]))))(\\d{3}(\\d|x|X))$");

	static boolean validate(String cardNo) {
		int len = null == cardNo ? 0 : cardNo.length();
		return len == 15 ? (_15_len_normal_pattern.matcher(cardNo).matches())
				: (len == 18 ? _18_len_normal_pattern.matcher(cardNo).matches()
						: false);
	}

	public static void main(String[] args) {
		System.out.println(validate("51070219541026111X"));
		System.out.println(validate("512922541003144"));
	}
}
