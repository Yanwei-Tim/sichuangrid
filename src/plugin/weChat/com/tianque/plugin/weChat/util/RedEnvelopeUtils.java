package com.tianque.plugin.weChat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;
import com.tianque.core.util.StringUtil;

/**
 * 红包工具类
 */
public class RedEnvelopeUtils {
	static Logger logger = Logger.getLogger(RedEnvelopeUtils.class);

	public static void main(String[] args) {
		//		System.out.println(getRandomString(RedEnvelopeConstant.LETTERANDDIGITAL_STRING, 10));
		//System.out.println(getSignature());

	}

	/**
	 * 获取length位的随机字符串
	 * custom_str自定义字符串
	 */
	public static String getRandomString(String custom_str, int length) { //length表示生成字符串的长度
		if (!StringUtil.isStringAvaliable(custom_str) || length == 0) {
			logger.error("工具类RedEnvelopeUtils的getRandomString方法出现异常，原因：传入参数有误");
			return null;
		}
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(custom_str.length());
			sb.append(custom_str.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取md5加密后的字符串
	 */
	public static String getMd5Str(String str) {
		String signature = null;
		try {
			//确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//加密后的字符串
			md5.reset();
			md5.update(str.getBytes("UTF-8"));
			signature = byteToHex(md5.digest());
			return signature;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("工具类RedEnvelopeUtils的getMd5Str方法出现异常，原因：",e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("工具类RedEnvelopeUtils的getMd5Str方法出现异常，原因：",e);
		}
		return signature;
	}

	/**
	 * 获取签名（签名算法）
	 */
	public static String getSignature(Map<String, String> urlMap,String key) {
		Collection<String> keyset = urlMap.keySet();
		List<String> list = new ArrayList<String>(keyset);
		Collections.sort(list);
		String url = "";
		for (int i = 0; i < list.size(); i++) {
			url += list.get(i) + "=" + urlMap.get(list.get(i)) + "&";
		}
		//拼接API密钥
		String stringSignTemp = url + "key=" + key;
		//String stringSignTemp = "act_name=新年红包&client_ip=127.0.0.1&max_value=100&mch_billno=1236034802201506250110046545&mch_id=1236034802&min_value=100&nick_name=天阙&nonce_str=50780e0cca98c8c8e814883e5caa672e&re_openid=opUfotyeSilbwAh0MMaxvAad1-5g&remark=新年红包备注&send_name=天阙科技&total_amount=100&total_num=1&wishing=恭喜发财&wxappid=wx6492b54af681e08f&key=tianquekejitianqueshuaigeweixing";
		//获取md5加密后的字符串
		String sign = getMd5Str(stringSignTemp).toUpperCase();
//		logger.error("签名:"+sign);
//		logger.error("url:"+stringSignTemp);
		return sign;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
