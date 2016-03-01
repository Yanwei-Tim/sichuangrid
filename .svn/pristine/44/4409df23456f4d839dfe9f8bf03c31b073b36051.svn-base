package com.tianque.plugin.weChat.proxy.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GridProperties;

public class HttpProxyUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpProxyUtil.class);
	
	/**
	 * 代理服务器，GridProperties.WECHAT_REQUESTACTION
	 * @param proxyUrl 需要被代理的url
	 * @param requestType 代理服务器请求类型
	 * @param content 向被代理url发送的请求参数内容
	 * @return
	 */
	public static String postMethod(String proxyUrl, String requestType, String content) {
		String host = GridProperties.WECHAT_HOST;
		int port = Integer.valueOf(GridProperties.WECHAT_PORT);
		String requestAction = GridProperties.WECHAT_REQUESTACTION;
		String interfaceUrl = requestAction ;//+ "?url=" + URLEncoder.encode(url);
		logger.error("web端发送给代理端的Url:" + interfaceUrl);
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(host, port);
		PostMethod post = new UTF8PostMethod(interfaceUrl);
		//		if(proxyParam!=null&&!proxyParam.isEmpty()){
		//			 Set<Entry<String, String>> paramSet = proxyParam.entrySet();
		//			 Iterator i = paramSet.iterator();
		//			 while(i.hasNext()){
		//				 Entry<String, String> param = (Entry<String, String>) i.next();
		//				 post.addParameter(param.getKey(),param.getValue());
		//			 }
		//		}
		post.addParameter("url",proxyUrl);
		post.addParameter("requestType", requestType);
		post.addParameter("content", content);
		try {
			client.executeMethod(post);

			String str = post.getResponseBodyAsString();
			return str;
		} catch (Exception e) {
			logger.error("代理错误", e);
			return "代理错误";
		} finally {
			post.releaseConnection();
		}

	}
}
