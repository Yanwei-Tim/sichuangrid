package com.tianque.plugin.weChat.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.tianque.plugin.weChat.domain.receiveMessage.TextReceiveMessage;
import com.tianque.plugin.weChat.domain.receiveMessage.VideoMessage;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.ImageMessage;
import com.tianque.plugin.weChat.domain.sendMessage.Music;
import com.tianque.plugin.weChat.domain.sendMessage.MusicMessage;
import com.tianque.plugin.weChat.domain.sendMessage.News;
import com.tianque.plugin.weChat.domain.sendMessage.NewsMessage;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.sendMessage.VoiceMessage;
import com.tianque.plugin.weChat.vo.RedEnvelopeObjectVo;
import com.tianque.plugin.weChat.vo.RedEnvelopesPaymentRecordsVo;

/** 消息工具类 */
public class MessageUtil {
	static Logger logger = Logger.getLogger(MessageUtil.class);

	/**解析微信发来的请求（XML）*/
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(InputStream inputStream) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		/**得到xml根元素*/
		Element root = document.getRootElement();
		/** 得到根元素的所有子节点*/
		List<Element> elementList = root.elements();
		/**遍历所有子节点*/
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		/**释放资源*/
		inputStream.close();
		inputStream = null;
		return map;
	}

	/** 
	 * 文本消息对象转换成xml (响应（回复）微信发来的消息发送格式：xml)
	 *  
	 * @param textMessage 文本消息对象 
	 * @return xml 
	 */
	public static String textMessageToXml(TextReceiveMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/** 
	 * 文本消息对象转换成xml (响应（回复）微信发来的消息发送格式：xml)
	 *  
	 * @param textMessage 文本消息对象 
	 * @return xml 
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转换成xml(响应（回复）微信发来的消息发送格式：xml)
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转换成xml(响应（回复）微信发来的消息发送格式：xml)
	 * @param voiceMessage
	 * @return
	 */
	public static String voiceMessageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	/**
	 *  视频消息对象转换成xml(响应（回复）微信发来的消息发送格式：xml)
	 * @param videoMessage
	 * @return
	 */
	public static String videoMessageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/** 
	 * 音乐消息对象转换成xml (响应（回复）微信发来的消息发送格式：xml)
	 *  
	 * @param musicMessage 音乐消息对象 
	 * @return xml 
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/** 
	 * 图文消息对象转换成xml (响应（回复）微信发来的消息发送格式：xml)
	 *  
	 * @param newsMessage 图文消息对象 
	 * @return xml 
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/** 
	 * 红包(发送)对象转换成xml (响应-微信发来的消息发送格式：xml)
	 * @param redEnvelope 红包对象 
	 * @return xml 
	 */
	public static String redEnvelopeToXml(RedEnvelopeObjectVo redEnvelopeObject) {
		xstream.alias("xml", redEnvelopeObject.getClass());
		return xstream.toXML(redEnvelopeObject);
	}
	/** 
	 * 红包对象转换成xml (响应-微信发来的消息发送格式：xml)
	 * @param redEnvelope 红包对象 
	 * @return xml 
	 */
	public static String redEnvelopesPaymentRecordsVoToXml(RedEnvelopesPaymentRecordsVo redRecordObject) {
		xstream.alias("xml", redRecordObject.getClass());
		return xstream.toXML(redRecordObject);
	}

	/**
	 * 组装文本客服消息（web--微信service）
	 * @param openId
	 * @param content
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);

	}
	
	/**
	 * 组装文本客服消息（web--微信service）(根据OpenID列表群发)
	 */
	public static String makeTextCustomMessageForMass(String openId,String fixedOpenId, String content) {
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":[\"%s\",\"%s\"],\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId,fixedOpenId, content);

	}

	/**
	 *  组装图片客服消息（web--微信service）
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}
	
	/**
	 *  组装图片客服消息（web--微信service）(根据OpenID列表群发)
	 */
	public static String makeImageCustomMessageForMass(String openId,String fixedOpenId, String mediaId) {
		String jsonMsg = "{\"touser\":[\"%s\",\"%s\"],\"image\":{\"media_id\":\"%s\"},\"msgtype\":\"image\"}";
		return String.format(jsonMsg, openId,fixedOpenId, mediaId);
	}

	/**
	 *  组装语音客服消息（web--微信service）
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}
	
	/**
	 *  组装语音客服消息（web--微信service）(根据OpenID列表群发)
	 */
	public static String makeVoiceCustomMessageForMass(String openId,String fixedOpenId, String mediaId) {
		String jsonMsg = "{\"touser\":[\"%s\",\"%s\"],\"voice\":{\"media_id\":\"%s\"},\"msgtype\":\"voice\"}";
		return String.format(jsonMsg, openId,fixedOpenId, mediaId);
	}

	/**
	 * 组装视频客服消息（web--微信service）
	 * @param openId
	 * @param mediaId
	 * @param thumbMediaId
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装音乐客服消息（web--微信service）
	 * @param openId
	 * @param music
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
		jsonMsg = jsonMsg.replace("ThumbMediaId", "thumb_media_id");
		return jsonMsg;
	}

	/**
	 * 组装图文客服消息（web--微信service）
	 * @param openId
	 * @param articleList
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId, List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString()
				.replaceAll("\"", "\\\""));
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	
	/**
	 * 组装图文客服消息（web--微信service）(根据OpenID列表群发)
	 */
	public static String makeNewsCustomMessageForMass(String openId,String fixedOpenId,String mediaId) {
		String jsonMsg = "{\"touser\":[\"%s\",\"%s\"],\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
		return String.format(jsonMsg, openId,fixedOpenId, mediaId);
	}

	/**
	 * 组装上传图文消息（群发前上传图文）
	 * @param list
	 * @return
	 */

	public static String makeUploadNewsMessage(List<News> list) {
		String jsonMsg = "{\"articles\":%s}";
		jsonMsg = String.format(jsonMsg, JSONArray.fromObject(list).toString());
		jsonMsg = jsonMsg.replace("contentSourceUrl", "content_source_url").replace("thumbMediaId",
				"thumb_media_id");
		return jsonMsg;
	}

	/**
	 * 组装按组群发图文消息
	 * @param groupId
	 * @param newMediaId
	 * @return
	 */
	public static String makeNewsByGroupMessage(String groupId, String newMediaId) {
		String jsonStr = "{\"filter\":{\"group_id\":\"%s\"},\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
		jsonStr = String.format(jsonStr, groupId, newMediaId);

		return jsonStr;
	}

	/**
	 * 组装用户群发图文消息(根据OpenID列表群发)
	 * @param tousers OpenID列表
	 * @param newMediaId
	 * @return
	 */
	public static String makeNewsByMassUser(String[] tousers, String newMediaId) {
		String jsonStr = "{\"touser\":%s,\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
		jsonStr = String.format(jsonStr, JSON.toJSONString(tousers), newMediaId);

		return jsonStr;
	}

	public static String makeTextByMassUser(String[] tousers, String text) {
		String jsonStr = "{\"touser\":%s,\"text\":{\"content\":\"%s\"},\"msgtype\":\"text\"}";
		jsonStr = String.format(jsonStr, JSON.toJSONString(tousers), text);
		return jsonStr;
	}
	/** 
	 * 计算采用utf-8编码方式时字符串所占字节数 (微信所认定编码)
	 *  
	 * @param content 
	 * @return 
	 */
	public boolean getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节  
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (size < 2048)
			return true;
		else
			return false;

	}

	/** 
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss） 
	 *  
	 * @param createTime(秒) 消息创建时间 
	 * @return 
	 */
	public static String formatTime(String createTime) {
		// 将微信传入的CreateTime转换成long类型，再乘以1000  
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}

	/** 
	 * 判断是否是QQ表情 
	 *  
	 * @param content 
	 * @return 
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式  
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	/** 
	* emoji表情转换(hex -> utf-16) 
	*  
	* @param hexEmoji 
	* @return 
	*/
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**扩展xstream，使其支持CDATA块*/
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
}
