package com.tianque.plugin.weChat.util;

import com.tianque.plugin.weChat.domain.messageTemplate.MessageTemplate;

/**
 * 模板消息
 */
public class MessageTemplateUtil {

	/**
	 * 模板消息-举报办理进度通知、待处理通知
	 */
	public static String makeReportProgressOrTreatNotice(MessageTemplate messageTemplate) {
		String remark = messageTemplate.getRemark();
		remark = processingLine(remark);
		String jsonMsg = "{\"touser\":\"%s\",\"template_id\":\"%s\"," + "\"url\":\"%s\","
				+ "\"topcolor\":\"#00CD00\"," + "\"data\":{" + "\"first\":{" + "\"value\":\"%s\","
				+ "\"color\":\"#173177\"" + "}," + "\"keyword1\":{" + "\"value\":\"%s\","
				+ "\"color\":\"#173177\"" + "}," + "\"keyword2\":{" + "\"value\":\"%s\","
				+ "\"color\":\"#173177\"" + "}," + "\"keyword3\":{" + "\"value\":\"%s\","
				+ "\"color\":\"#173177\"" + "}," + "\"remark\":{" + "\"value\":\"%s\","
				+ "\"color\":\"#173177\"" + "}" + "}}";
		return String.format(jsonMsg, messageTemplate.getOpenId(),
				messageTemplate.getTemplate_id(), messageTemplate.getUrl(),
				messageTemplate.getFirstWord(), messageTemplate.getKeywordOne(),
				messageTemplate.getKeywordTow(), messageTemplate.getKeywordThree(), remark);

	}

	/**
	 * 处理换行符
	 */
	private static String processingLine(String str) {
		String returnStr = "";
		if (str.contains("\r\n")) {//ie浏览器下
			String[] strTemp = str.split("\r\n");

			for (int i = 0; i < strTemp.length; i++) {
				if (i == strTemp.length - 1) {
					returnStr = returnStr + strTemp[i];
					break;
				}
				returnStr = returnStr + strTemp[i] + "\\n";
			}
			return returnStr;
		}
		if (str.contains("\n")) {//chrome浏览器下
			String[] strTemp = str.split("\n");
			for (int i = 0; i < strTemp.length; i++) {
				if (i == strTemp.length - 1) {
					returnStr = returnStr + strTemp[i];
					break;
				}
				returnStr = returnStr + strTemp[i] + "\\n";
			}
		} else {
			returnStr = str;
		}
		return returnStr;
	}

}
