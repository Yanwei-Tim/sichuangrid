package com.tianque.sms.util;

public final class SmsGlobalValue {

	private SmsGlobalValue() {

	}

	public static final String SMS_SYSTEN_NAME = "XXX社管系统";

	/** 短信存储表的最大数据存储量 */
	public static final Long SMSTABLES_MAXVALUE = 100000L;
	/** 待发送短信表 */
	public static final String SENDER_TABLENAME = "mobileshortmessage";
	/** 已接收短信表 */
	public static final String RECEIVE_TABLENAME = "upmobilemessage";

	/** 手机号码 */
	public static final Long MOBILE_NUMBER = 1L;
	/** 通讯录 */
	public static final Long CONTACTS = 2L;
	/** 复杂发送对象 */
	public static final Long SEND_OBJECT = 3L;
	/** 发送失败 */
	public static final Long FAILURE = 5L;

}
