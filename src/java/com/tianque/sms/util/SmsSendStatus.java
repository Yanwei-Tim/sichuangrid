package com.tianque.sms.util;

public final class SmsSendStatus {
	/** 待处理 */
	public static final Long PENDING = 1L;
	/** 处理中 */
	public static final Long PROCESSING = 2L;
	/** 待发送 */
	public static final Long TO_SEND = 3L;
	/** 发送成功 */
	public static final Long SENT_SUCCESS = 4L;
	/** 发送失败 */
	public static final Long SENT_FAILED = 5L;

}
