/**
 * 
 */
package com.tianque.plugin.weChat.domain.sendMessage;

/**
 * 语音消息
 * @author 
 *  @date  2014年4月17日
 */
public class VoiceMessage extends BaseMessage {
	//语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
