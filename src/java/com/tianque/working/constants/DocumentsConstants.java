package com.tianque.working.constants;

public class DocumentsConstants {

	/** 已经签收 */
	public static final Long isSign = 1L;// 已经签收
	/** 未签收 */
	public static final Long noSign = 0L;// 未签收

	/** 已阅读 */
	public static final Integer isRead = 1;// 已阅读
	/** 未阅读 */
	public static final Integer notRead = 0;// 未阅读

	/** 已经发送 */
	public static final Long isSend = 1L;// 已经发送
	/** 未发送 */
	public static final Long unSend = 0L;// 未发送

	/** 主送 */
	public static final Long mainSend = 0L;// 主送
	/** 抄送 */
	public static final Long copySend = 1L;// 抄送

	/** 主送收件人选择 */
	public static final String DCOUMENT_SELECT = "documentSelect";
	/** 抄送收件人选择 */
	public static final String DOCUMENT_COPY_SELECT = "documentSelectCopy";

	/** 历史数据截止时间 */
	public static final String HISTORY_DATE = "2015-06-26";

	/** textarea换行符 */
	public static final String TEXTAREA_WRAP = "&#13;&#10;";
}
